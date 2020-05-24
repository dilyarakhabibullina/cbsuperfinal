package ru.itpark.servlet;

import ru.itpark.domain.Recipe;
import ru.itpark.service.CookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


public class CookServlet extends HttpServlet {
    CookService service = new CookService();
    public Path uploadPath = Paths.get(System.getenv("UPLOAD_PATH"));

    public CookServlet() {
    }
//    public CookServlet(CookService service) {
//        this.service = service;
//    }


    //    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
//        req.setCharacterEncoding ("cp1251");
//        filterChain.doFilter (req, resp);
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI().substring(req.getContextPath().length());

        if (url.equals("/")) {
            //  resp.setContentType("image/*");
            req.setAttribute("myrecipes", "Мои рецепты");


            try {
                req.setAttribute("recipes", service.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
             }
            req.getRequestDispatcher("/WEB-INF/frontpage.jsp").forward(req, resp);

        }
        if (url.equals("/search")) {
            req.setCharacterEncoding("cp1251");

            final String q = new String(req.getParameter("q").getBytes("ISO-8859-1"), "cp1251");
            try {
                req.setAttribute("myrecipes", "Вот что нашлось");
                req.setAttribute("recipes", service.searchByName (q));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/frontpage.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // CookService service = new CookService ( );
        String url = req.getRequestURI().substring(req.getContextPath().length());
        final String action = req.getParameter("action");
        Recipe recipe = new Recipe();
        //final String id = req.getParameter ("id");

        if (action.equals("save")) {
            final String id = req.getParameter("id");
            //   req.setCharacterEncoding ("cp1281");
            final String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "cp1251");
            //new String(req.getParameter("q").getBytes("ISO-8859-1"), "cp1251");
            final String ingredients = new String(req.getParameter("ingredients").getBytes("ISO-8859-1"), "cp1251");
            ;
            final String description = new String(req.getParameter("description").getBytes("ISO-8859-1"), "cp1251");
            final Part file = req.getPart("file");

            //  String MyValue = new String(source_string.getBytes("utf-8"),"cp1251");


            try {
                // String MyValue = new String(req.getParameter("name").getBytes(req.getCharacterEncoding()), "UTF-8");
                service.saveDataBase(new Recipe(recipe.getId(), name, ingredients, description));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //resp.setCharacterEncoding("cp1251");
            resp.sendRedirect(req.getRequestURI());
            return;
        }
        if (action.equals("remove")) {

            final String id = req.getParameter("id");
            try {
                service.removeById(id);
            } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getRequestURI());
            return;
        }
        if (action.equals("edit")) {
            final String id = req.getParameter("id");
            try {
                req.setAttribute("item", service.getById(id));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                req.setAttribute("recipes", service.deleteId(id));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
        }
//        if (url.startsWith("/images/")) {
//            String id = url.substring("/images/".length());
//            System.out.println(id);
//            final Path image = uploadPath.resolve(id);
//            if (Files.exists(image)) {
//                Files.copy(image, resp.getOutputStream());
//                return;
//            }
//
//            try {
//                Files.copy(Paths.get(getServletContext().getResource("/WEB-INF/404.png").toURI()), resp.getOutputStream());
//            } catch (URISyntaxException e) {
//                throw new IOException(e);
//            }
//        }

      //  req.getRequestDispatcher("/WEB-INF/404.jsp").forward(req, resp);
    }
}



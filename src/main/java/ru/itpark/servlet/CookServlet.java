package ru.itpark.servlet;

import ru.itpark.domain.Recipe;
import ru.itpark.service.CookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class CookServlet extends HttpServlet {

//    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
//        req.setCharacterEncoding ("cp1251");
//        filterChain.doFilter (req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CookService service = new CookService ( );
        String url = req.getRequestURI ( ).substring (req.getContextPath ( ).length ( ));
        if (url.equals ("/")) {
            req.setAttribute ("myrecipes", "Мои рецепты");


            try {
                req.setAttribute ("recipes", service.getAll ( ));
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            req.getRequestDispatcher ("/WEB-INF/frontpage.jsp").forward (req, resp);

        }
        if (url.equals ("/search")) {
            //  req.setCharacterEncoding ("cp1251");

            final String q = new String (req.getParameter ("q").getBytes ("ISO-8859-1"), "cp1251");
            try {
                req.setAttribute ("myrecipes", "Вот что нашлось");
                req.setAttribute ("recipes", service.searchByName (q));
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            req.getRequestDispatcher ("/WEB-INF/frontpage.jsp").forward (req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CookService service = new CookService ( );
        final String action = req.getParameter ("action");

        Recipe recipe = new Recipe ( );
        final String id = req.getParameter ("id");
        if (action.equals ("remove")) {
            try {
               service.removeById (id);
            } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace ( );
            }
            resp.sendRedirect (req.getRequestURI ( ));
            return;
        }
    }
}

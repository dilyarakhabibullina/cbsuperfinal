package ru.itpark.servlet;

import ru.itpark.service.CookService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class CookServlet extends HttpServlet {

    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding ("cp1251");
        filterChain.doFilter (req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding ("cp1251");
        resp.setCharacterEncoding ("cp1251");
        resp.setContentType ("text/html;charset=cp1251");
        CookService service = new CookService ( );
        String url = req.getRequestURI ( ).substring (req.getContextPath ( ).length ( ));
        if (url.equals ("/")) {
            req.setAttribute ("myrecipes", "Мои рецепты");
            //req.getRequestDispatcher ("/WEB-INF/frontpage.jsp").forward (req, resp);


            try {
                req.setAttribute ("recipes", service.getAll ( ));
                // req.setAttribute ("searchres", service.searchByName ("???"));
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            req.getRequestDispatcher ("/WEB-INF/frontpage.jsp").forward (req, resp);

        }
        if (url.equals ("/search")) {
            req.setCharacterEncoding ("cp1251");

            final String q = new String (req.getParameter ("q").getBytes ("ISO-8859-1"), "cp1251");
            try {
                req.setCharacterEncoding ("cp1251");
                req.setAttribute ("myrecipes", "Вот что нашлось");
                req.setCharacterEncoding ("cp1251");
                req.setAttribute ("recipes", service.searchByName (q));
            } catch (SQLException e) {
                e.printStackTrace ( );
            }
            req.getRequestDispatcher ("/WEB-INF/frontpage.jsp").forward (req, resp);
        }
    }
}

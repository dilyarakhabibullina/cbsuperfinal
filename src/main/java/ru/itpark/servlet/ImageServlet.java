package ru.itpark.servlet;

import ru.itpark.service.CookService;
import ru.itpark.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageServlet extends HttpServlet {
    private CookService service;
    private Path uploadPath;

    @Override
    public void init() throws ServletException {
        try {
           // service = new HouseService();
//            service.save(new House("", "Ново-Савиновский", Arrays.asList("Яшьлек", "Козья"), 4, null), uploadPath);
//            service.save(new House("", "Кировский", Collections.emptyList(), 2, null), uploadPath);
            uploadPath = Paths.get(System.getenv("UPLOAD_PATH"));
            // получает путь до картинки
            if (Files.notExists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }
            System.out.println(uploadPath);
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }
   // @Override
//    public void init() throws ServletException {
//        try {
//            InitialContext context = new InitialContext();
//            fileService = (FileService) context.lookup("java:/comp/env/bean/file-service");
//        } catch (NamingException e) {
//            e.printStackTrace();
//            throw new ServletException(e);
//        }
//    }jpg

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        String url = req.getRequestURI().substring(req.getContextPath().length());
     //   resp.setContentType("image/jpg");
        String id = url.substring("/images/".length());
        System.out.println(id);
        //формируем итоговый путь к картинке
        final Path image = uploadPath.resolve(id);
        if (Files.exists(image)) {
            Files.copy(image, resp.getOutputStream());
            return;
        }

//        try {
//            Files.copy(Paths.get(getServletContext().getResource("/WEB-INF/404.png").toURI()), resp.getOutputStream());
//        } catch (URISyntaxException e) {
//            throw new IOException(e);
//        }


       // req.getRequestDispatcher("/WEB-INF/404.jsp").forward(req, resp);


}}



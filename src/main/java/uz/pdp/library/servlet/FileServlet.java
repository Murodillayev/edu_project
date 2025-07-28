package uz.pdp.library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.library.util.FileUtil;

import java.io.IOException;
import java.nio.file.Path;

@WebServlet("/images/*")
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getHttpServletMapping().getMatchValue();

        byte[] bytes = FileUtil.getFileBytes(fileName);

        resp.setContentType("application/octet-stream");
        String originalName = fileName.substring(fileName.indexOf("_") + 1);
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");
        resp.setContentLength(bytes.length);
        resp.getOutputStream().write(bytes);
    }
}

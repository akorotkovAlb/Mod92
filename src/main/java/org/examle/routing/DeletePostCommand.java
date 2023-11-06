package org.examle.routing;

import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeletePostCommand implements Command {
    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp,
                         TemplateEngine engine, PostRepository repository) throws IOException {
        Map<String, String[]> params = req.getParameterMap();
        if (params.containsKey("id")) {
            String id = params.get("id")[0];
            repository.deleteById(id);
        }

        resp.sendRedirect("/Mod92/post/get/all/posts");
    }
}

package org.examle.routing;

import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetAllPostsCommand implements Command {
    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp,
                         TemplateEngine engine, PostRepositoryImpl repository) throws IOException {
        resp.setContentType("text/html");

        Context simpleContext = new Context(req.getLocale(), Map.of("posts", repository.getAllPosts()));

        engine.process("newGetPosts", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}

package org.examle.ptg;

import org.examle.ptg.post.Post;
import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

//@WebServlet("/post-to-get/*")
public class PostToGetServlet extends HttpServlet {
    private TemplateEngine engine;
    private PostRepository repository;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();
        repository = new PostRepositoryImpl();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("C:/java_dev/9/Mod92/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        Context simpleContext = new Context(req.getLocale(), Map.of("posts", repository.getAllPosts()));

        engine.process("getPosts", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("delete")) {
            doDelete(req, resp);
        } else {
            String author = req.getParameter("author");
            String post = req.getParameter("post");
            String id = UUID.randomUUID().toString();

            Post newPost = new Post(id, author, post);
            repository.addPost(newPost);

            resp.sendRedirect("/Mod92/post-to-get");
        }
    }

    @Override
    protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        if (params.containsKey("id")) {
            String id = params.get("id")[0];
            repository.deleteById(id);
        }

        resp.sendRedirect("/Mod92/post-to-get");
    }
}

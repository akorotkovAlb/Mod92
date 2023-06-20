package org.examle.routing;

import org.examle.ptg.post.Post;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class CreatePostCommand implements Command {
    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp,
                         TemplateEngine engine, PostRepositoryImpl repository) throws IOException {
        String author = req.getParameter("author");
        String post = req.getParameter("post");
        String id = UUID.randomUUID().toString();

        Post newPost = new Post(id, author, post);
        repository.addPost(newPost);

        resp.sendRedirect("/Mod92/get/all/posts");
    }
}

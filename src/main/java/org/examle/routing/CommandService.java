package org.examle.routing;

import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommandService {

    private Map<String, Command> commands;

    public CommandService () {
        this.commands = new HashMap<>();

        this.commands.put("GET /Mod92/post/get/all/posts", new GetAllPostsCommand());
        this.commands.put("POST /Mod92/post/delete/post", new DeletePostCommand());
        this.commands.put("POST /Mod92/post/create/post", new CreatePostCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp,
                        TemplateEngine engine, PostRepository repository) throws IOException {
        String uri = req.getRequestURI();
        String method = req.getMethod();
        String commandKey = method + " " + uri;
        if (commands.containsKey(commandKey)) {
            commands.get(commandKey).process(req, resp, engine, repository);
        } else {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.print("<h1>Error!</h1>");
            resp.setStatus(418);
            // do something!
        }
    }
}

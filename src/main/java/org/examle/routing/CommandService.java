package org.examle.routing;

import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                        TemplateEngine engine, PostRepositoryImpl repository) throws IOException {
        String uri = req.getRequestURI();
        String method = req.getMethod();
        String commandKey = method + " " + uri;
        if (commands.containsKey(uri)) {
            commands.get(commandKey).process(req, resp, engine, repository);
        } else {
            // do something!
        }
    }
}

package org.examle.routing;

import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void process(HttpServletRequest req, HttpServletResponse resp,
                 TemplateEngine engine, PostRepository repository) throws IOException;
}

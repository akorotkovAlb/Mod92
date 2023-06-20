package org.examle.routing;

import org.examle.ptg.post.Post;
import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebServlet("/post/*")
public class FrontControllerServlet extends HttpServlet {
    private TemplateEngine engine;
    private PostRepositoryImpl repository;
    private CommandService commandService;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();
        repository = new PostRepositoryImpl();
        commandService = new CommandService();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("C:/java_dev/9/Mod92/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);
    }

    @Override
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        commandService.process(req, res, engine, repository);
    }
}

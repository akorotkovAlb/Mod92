package org.examle.routing;

import lombok.SneakyThrows;
import org.examle.DBRepositoryImpl;
import org.examle.PostgresDatabase;
import org.examle.props.PropertyReader;
import org.examle.ptg.post.PostRepository;
import org.examle.ptg.post.PostRepositoryImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
    private TemplateEngine engine;
    private PostRepository repository;
    private CommandService commandService;

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        Connection connection = DriverManager.getConnection(
                Objects.requireNonNull(PropertyReader.getConnectionUrlForPostgres()),
                PropertyReader.getUserForPostgres(), PropertyReader.getPasswordForPostgres());
        engine = new TemplateEngine();
        repository = new DBRepositoryImpl(connection);
        commandService = new CommandService();

        JavaxServletWebApplication jswa =
                JavaxServletWebApplication.buildApplication(this.getServletContext());

        WebApplicationTemplateResolver
                resolver = new WebApplicationTemplateResolver(jswa);
        resolver.setPrefix("/WEB-INF/temp/");
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

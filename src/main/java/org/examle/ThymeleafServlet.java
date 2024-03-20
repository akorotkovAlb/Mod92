package org.examle;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

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

@WebServlet("/thymeleaf")
public class ThymeleafServlet extends HttpServlet {
    private TemplateEngine engine;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();

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
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

//        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> params = new LinkedHashMap<>();

//        for (Map.Entry<String, String[]> keyValue : parameterMap.entrySet()) {
//            params.put(keyValue.getKey(), Arrays.toString(keyValue.getValue()));
//        }

        int numbers = (int) ((Math.random() * (10 - 2)) + 2);
        for (int i = 0; i < numbers; i++) {
            params.put(generateString(), generateString());
        }

        Context data = new Context(req.getLocale(), Map.of("queryParams", params, "textForH3", "Some text for H3!"));

        engine.process("first", data, resp.getWriter());
        resp.getWriter().close();
    }

    private String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}

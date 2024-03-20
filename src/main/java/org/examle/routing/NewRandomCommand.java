package org.examle.routing;

import org.examle.ptg.post.PostRepository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class NewRandomCommand implements Command {

    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine, PostRepository repository) throws IOException {

        Map<String, String> params = new LinkedHashMap<>();
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

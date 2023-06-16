package org.examle;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;


// for creating war file -> compiler:compile -> war:war
@WebServlet(value = "/Simple")
public class Simple extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print("<h3>Hello World with New Servlet!</h3>");

//        // TODO get parameter by name.
//        //  getParameter can throw NullPointer exception, when parameter with given name Not Found.
//        String param = request.getParameter("name");
//        out.print("<p>Parameter name = name; Value = ${value}</p>"
//                .replace("${value}", param));

//        // TODO get query parameters (http://localhost:8080/Mod82Maven/Simple?name=Andrii&name=Serhii&transport=car)
//        Map<String, String[]> queryParams = request.getParameterMap();
//        queryParams.forEach((name, value) -> {
//            out.print("<p>Parameter name = ${name}; Value = ${value}</p>"
//                    .replace("${name}", name)
//                    .replace("${value}", Arrays.toString(value)));
//        });

//        //TODO add localDateTime to response
//        out.write("<p>${dateTime}</p>"
//                .replace("${dateTime}", LocalDateTime.now()
//                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"))));

        // -------------------------------------

//        //TODO work with headers
//        Iterator<String> headerNames = request.getHeaderNames().asIterator();
//        while(headerNames.hasNext()) {
//            String name = headerNames.next();
//            String value = request.getHeader(name);
//            out.print("<p>Header name = ${name}; Value = ${value}</p>"
//                    .replace("${name}", name)
//                    .replace("${value}", value));
//        }

//        //TODO add refresh header to response (MDM)
//        response.setHeader("Refresh", "5");
//        out.write("<p>${dateTime}</p>"
//                .replace("${dateTime}", LocalDateTime.now()
//                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"))));



        out.print("</body></html>");
        out.close();
    }

    // TODO work with POST body form.html
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print("<h3>Hello World with New Servlet POST!</h3>");

        Iterator<String> headerNames = request.getHeaderNames().asIterator();
        while(headerNames.hasNext()) {
            String name = headerNames.next();
            String value = request.getHeader(name);
            out.print("<p>Header name = ${name}; Value = ${value}</p>"
                    .replace("${name}", name)
                    .replace("${value}", value));
        }

//        //TODO for work with JSON format
//        if(!request.getHeader("content-type").equals("application/json")) {

        //TODO for work with x-www-form-urlencoded
        Map<String, String[]> queryParams = request.getParameterMap();
        queryParams.forEach((name, value) -> {
            out.print("<p>Parameter name = ${name}; Value = ${value}</p>"
                    .replace("${name}", name)
                    .replace("${value}", Arrays.toString(value)));
        });

//        } else {
//            //TODO for work with JSON format
//            String body = request.getReader().lines().collect(Collectors.joining("\n"));
//            User user = new ObjectMapper().readValue(body, User.class);
//            out.print("<p>Body parameter converted to user with ${firstName} and ${lastName}</p>"
//                    .replace("${firstName}", user.getFirstName())
//                    .replace("${lastName}", user.getLastName()));
//        }


        out.print("</body></html>");
        out.close();
    }
}

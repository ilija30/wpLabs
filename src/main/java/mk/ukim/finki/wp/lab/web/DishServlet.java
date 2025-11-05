package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "dishServlet", urlPatterns = "/dish")
@AllArgsConstructor
public class DishServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        long chefId = Long.parseLong(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("dishes", dishService.listDishes());
        webContext.setVariable("chefId", chefId);
        webContext.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());

        templateEngine.process("dishesList.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefName = req.getParameter("chefId");
        resp.sendRedirect("/dish?chefId="  + chefName);
    }
}

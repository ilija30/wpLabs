package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@AllArgsConstructor
@WebServlet(name = "chefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final ChefService chefService;
    private final DishService dishService;
    private final SpringTemplateEngine templateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        long chefId = -1L;
        chefId = Long.parseLong(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());
        webContext.setVariable("chefBio", chef.getBio());
        webContext.setVariable("dishes", chef.getDishes());

        templateEngine.process("chefDetails.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long chefId = -1L;
        chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        Dish dish = dishService.findByDishId(dishId);
        Chef chef = chefService.addDishToChef(chefId, dish.getDishId());
        resp.sendRedirect("/chefDetails?chefId=" + chef.getId());
    }
}

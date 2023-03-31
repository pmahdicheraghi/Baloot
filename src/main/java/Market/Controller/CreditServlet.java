package Market.Controller;

import Market.MarketManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            request.getRequestDispatcher("Credit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            String username = marketManager.getLoggedInUser();
            int amount = Integer.parseInt(request.getParameter("amount"));
            marketManager.addCreditToUser(username, amount);
            response.sendRedirect("/");
        }
    }
}

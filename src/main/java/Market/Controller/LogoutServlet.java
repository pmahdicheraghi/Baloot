package Market.Controller;

import Market.MarketManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        marketManager.logout();
        response.sendRedirect("/login");
    }
}

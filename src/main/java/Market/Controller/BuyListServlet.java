package Market.Controller;

import Market.MarketManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            request.getRequestDispatcher("BuyList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            String username = marketManager.getLoggedInUser();
            if (request.getParameter("commodityId") != null) {
                int commodityId = Integer.parseInt(request.getParameter("commodityId"));
                marketManager.removeFromBuyList(username, commodityId);
                response.sendRedirect("/buyList");
            } else if (request.getParameter("discount") != null) {
                String discountCode = request.getParameter("discount");
                marketManager.canUserUseDiscount(username, discountCode);
                request.setAttribute("discount", discountCode);
                request.getRequestDispatcher("BuyList.jsp").forward(request, response);
            } else if (request.getParameter("purchase") != null) {
                String discountCode = request.getParameter("purchase");
                if (discountCode.equals("")) {
                    marketManager.purchase(username);
                }
                else {
                    marketManager.purchase(username, discountCode);
                }
                response.sendRedirect("/buyList");
            } else {
                throw new RuntimeException("Invalid Action");
            }
        }
    }
}

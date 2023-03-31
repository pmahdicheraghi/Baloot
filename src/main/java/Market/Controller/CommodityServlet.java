package Market.Controller;

import Market.MarketManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommodityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null) {
                request.getRequestDispatcher("/Commodities.jsp").forward(request, response);
            } else {
                String[] pathParts = pathInfo.split("/");
                String commodityId = pathParts[1];
                request.setAttribute("commodityId", commodityId);
                request.getRequestDispatcher("/Commodity.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarketManager marketManager = MarketManager.getInstance();
        if (!marketManager.isUserLoggedIn()) {
            response.sendRedirect("/login");
        } else {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null) {
                throw new RuntimeException("Invalid method");
            } else {
                String[] pathParts = pathInfo.split("/");
                int commodityId = Integer.parseInt(pathParts[1]);
                String username = marketManager.getLoggedInUser();
                if (request.getParameter("rate") != null) {
                    marketManager.rateCommodity(username, commodityId, Integer.parseInt(request.getParameter("rate")));
                } else if (request.getParameter("add") != null) {
                    marketManager.addToBuyList(username, commodityId);
                } else if (request.getParameter("comment") != null) {
                    marketManager.addComment(username, commodityId, request.getParameter("comment"));
                } else if (request.getParameter("like") != null) {
                    marketManager.vote(username, 1, Integer.parseInt(request.getParameter("like")));
                } else if (request.getParameter("dislike") != null) {
                    marketManager.vote(username, -1, Integer.parseInt(request.getParameter("dislike")));
                } else {
                    throw new RuntimeException("Invalid Action");
                }
                response.sendRedirect("/commodities/" + commodityId);
            }
        }
    }
}

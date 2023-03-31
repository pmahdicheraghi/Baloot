package Market.Controller;

import Market.MarketManager;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    public void init() {
        MarketManager marketManager = MarketManager.getInstance();
        marketManager.init();
        System.out.println("MarketManager initialized");
    }
}

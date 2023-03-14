package Market;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        MarketManager mm = MarketManager.getInstance();
        mm.init();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);

        app.get("commodities", ctx -> {
            List<Commodity> commoditiesList = mm.getCommoditiesList();
            File commoditiesFile = new File("src/main/resources/Commodities.html");
            Document doc = Jsoup.parse(commoditiesFile, "UTF-8", "http://http://localhost:7070/");
            for (Commodity commodity : commoditiesList) {
                doc.getElementById("tableID").append("<tr>" +
                        "<td>" + commodity.getId() + "</td>\n" +
                        "<td>" + commodity.getName() + "</td> \n" +
                        "<td>" + commodity.getProviderId() + "</td>\n" +
                        "<td>" + commodity.getPrice() + "</td>\n" +
                        "<td>" + commodity.getCategories() + "</td>\n" +
                        "<td>" + commodity.getRating() + "</td>\n" +
                        "<td>" + commodity.getInStock() + "</td>\n" +
                        "<td><a href=\"/commodities/" + commodity.getId() + "\">Link</a></td>" +
                        "</tr>");
            }
            ctx.html(doc.html());
        });

        app.get("/commodities/{commodity_id}", ctx -> {
            int commodityId = Integer.parseInt(ctx.pathParam("commodity_id"));
            Commodity commodity = mm.getCommodityById(commodityId);
            File commodityFile = new File("src/main/resources/Commodity.html");
            Document doc = Jsoup.parse(commodityFile, "UTF-8", "http://http://localhost:7070/");
            doc.getElementById("id").html("Id: " + commodity.getId());
            doc.getElementById("name").html("Name: " + commodity.getName());
            doc.getElementById("providerId").html("Provider Id: " + commodity.getProviderId());
            doc.getElementById("price").html("Price: " + commodity.getPrice());
            doc.getElementById("categories").html("Category: " + commodity.getCategories());
            doc.getElementById("rating").html("Rating: " + commodity.getRating());
            doc.getElementById("inStock").html("In Stock: " + commodity.getInStock());
            // TODO: add comments
            ctx.html(doc.html());
        });

        app.get("/providers/{provider_id}", ctx -> {
            int providerId = Integer.parseInt(ctx.pathParam("provider_id"));
            Provider provider = mm.getProviderById(providerId);
            File providerFile = new File("src/main/resources/Provider.html");
            Document doc = Jsoup.parse(providerFile, "UTF-8", "http://http://localhost:7070/");
            doc.getElementById("id").html("Id: " + provider.getId());
            doc.getElementById("name").html("Name: " + provider.getName());
            doc.getElementById("registryDate").html("Registry Date: " + provider.getRegistryDate().toString());
            List<Commodity> commoditiesList = mm.getCommoditiesList();
            for (Commodity commodity : commoditiesList) {
                if (commodity.getProviderId() == providerId) {
                    doc.getElementById("tableID").append("<tr>" +
                            "<td>" + commodity.getId() + "</td>\n" +
                            "<td>" + commodity.getName() + "</td> \n" +
                            "<td>" + commodity.getPrice() + "</td>\n" +
                            "<td>" + commodity.getCategories() + "</td>\n" +
                            "<td>" + commodity.getRating() + "</td>\n" +
                            "<td>" + commodity.getInStock() + "</td>\n" +
                            "<td><a href=\"/commodities/" + commodity.getId() + "\">Link</a></td>" +
                            "</tr>");
                }
            }
            ctx.html(doc.html());
        });

        app.get("/users/{user_id}", ctx -> {
            String user_id = ctx.pathParam("user_id");
            User user = mm.getUserByUsername(user_id);
            File usersFile = new File("src/main/resources/User.html");
            Document doc = Jsoup.parse(usersFile, "UTF-8", "http://http://localhost:7070/");
            doc.getElementById("username").html("Username: " + user.getUsername());
            doc.getElementById("email").html("Email: " + user.getEmail());
            doc.getElementById("birthDate").html("Birth Date: " + user.getBirthDay());
            doc.getElementById("address").html("Address: " + user.getAddress());
            doc.getElementById("credit").html("Credit: " + user.getCredit());
            List<Commodity> buyList = mm.getBuyList(user_id);
            for (Commodity commodity : buyList) {
                doc.getElementById("buyList").append("<tr>" +
                        "<td>" + commodity.getId() + "</td>\n" +
                        "<td>" + commodity.getName() + "</td> \n" +
                        "<td>" + commodity.getProviderId() + "</td> \n" +
                        "<td>" + commodity.getPrice() + "</td>\n" +
                        "<td>" + commodity.getCategories() + "</td>\n" +
                        "<td>" + commodity.getRating() + "</td>\n" +
                        "<td>" + commodity.getInStock() + "</td>\n" +
                        "<td><a href=\"/commodities/" + commodity.getId() + "\">Link</a></td>" +
                        "<td><button onclick=\"window.location.href='/removeFromBuyList/" + user_id + "/" + commodity.getId() + "'\">Remove</button></td>" +
                        "</tr>");
            }
            List<Commodity> purchasedList = mm.getPurchasedList(user_id);
            for (Commodity commodity : purchasedList) {
                doc.getElementById("purchasedList").append("<tr>" +
                        "<td>" + commodity.getId() + "</td>\n" +
                        "<td>" + commodity.getName() + "</td> \n" +
                        "<td>" + commodity.getPrice() + "</td>\n" +
                        "<td>" + commodity.getCategories() + "</td>\n" +
                        "<td>" + commodity.getRating() + "</td>\n" +
                        "<td>" + commodity.getInStock() + "</td>\n" +
                        "<td><a href=\"/commodities/" + commodity.getId() + "\">Link</a></td>" +
                        "</tr>");
            }
            ctx.html(doc.html());
        });

        app.get("/addCredit/{user_id}/{credit}", ctx -> {
            String user_id = ctx.pathParam("user_id");
            int credit = Integer.parseInt(ctx.pathParam("credit"));
            mm.addCreditToUser(user_id, credit);
            ctx.redirect("/users/" + user_id);
        });
    }
}

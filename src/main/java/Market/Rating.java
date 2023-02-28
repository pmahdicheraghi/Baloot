package Market;

public class Rating {
    private String username;
    private int commodityId;
    private int score;

    public Rating(String username, int commodityId, int score) {
        this.username = username;
        this.commodityId = commodityId;
        this.score = score;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public int getScore() {
        return score;
    }
}

package Market;

import java.util.ArrayList;
import java.util.Date;


public class Comment {
    private final String username;
    private final int commodityId;
    private final int id;
    private final String comment;
    private final Date date;
    private final ArrayList<String> upVotes = new ArrayList<>();
    private final ArrayList<String> downVotes = new ArrayList<>();


    public Comment(int id, String username, int commodityId, String comment, Date date) {
        this.username = username;
        this.commodityId = commodityId;
        this.id = id;
        this.comment = comment;
        this.date = date;
    }

    public void upVote(String username) {
        downVotes.removeIf(user -> user.equals(username));
        for (String user : upVotes) {
            if (user.equals(username)) {
                return;
            }
        }
        upVotes.add(username);
    }

    public void downVote(String username) {
        upVotes.removeIf(user -> user.equals(username));
        for (String user : downVotes) {
            if (user.equals(username)) {
                return;
            }
        }
        downVotes.add(username);
    }

    public void removeVote(String username) {
        for (String user : upVotes) {
            if (user.equals(username)) {
                upVotes.remove(user);
                return;
            }
        }
        for (String user : downVotes) {
            if (user.equals(username)) {
                downVotes.remove(user);
                return;
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return upVotes.size();
    }

    public int getDislikes() {
        return downVotes.size();
    }
}

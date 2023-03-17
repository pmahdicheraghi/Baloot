package Market;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Comment {

    private static int count=0;
    private final String username;
    private final int commodityId;
    private final int id;
    private final String comment;
    private final Date date;
    private int upvote=0;
    private int downvote=0;


    public Comment(String username,int commodityId,String comment,Date date){
        this.username=username;
        this.commodityId=commodityId;
        this.id=this.count;
        this.comment=comment;
        this.date=date;
        this.increaseCount();
    }
    public void upvote(){upvote++;}
    public void downvote(){downvote++;}
    private void increaseCount(){count++;}
    public int getId(){return id;}
    public int getCommodityId(){return commodityId;}

    public String getUsername(){return username;}
    public String getComment(){return comment;}
    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
    public int getLikes(){return upvote;}
    public int getDislikes(){return downvote;}
}

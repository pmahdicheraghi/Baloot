package Market;

import java.util.Date;

public class Comment {

    private static int count=0;
    private final String username;
    private final int commodityId;
    private final int id;
    private final String comment;
    private final Date date;
    private int like=0;
    private int dislike=0;

    public Comment(String username,int commodityId,String comment,Date date){
        this.username=username;
        this.commodityId=commodityId;
        this.id=this.count;
        this.comment=comment;
        this.date=date;
        this.increaseCount();
    }
    public void likeComment(){like++;}
    public void dislikeComment(){like--;}
    private void increaseCount(){count++;}
}

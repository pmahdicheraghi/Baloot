package Market;

public class Vote {
    private int vote;
    private final int commentId;
    private final String username;

    public Vote(int vote,int commentId,String username){
        this.vote=vote;
        this.commentId=commentId;
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public int getVote(){
        return vote;
    }
    public int getCommentId(){
        return commentId;
    }
    public void updateVote(int newVote){
        vote=newVote;
    }
}


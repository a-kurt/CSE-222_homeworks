import java.util.Arrays;

public class Post {
    private int postId;
    private int accountId;
    private Like[] likes;
    private Comment[] comments;
    private String content;

    public Post(int postId, int accountId, String content) {
        this.postId = postId;
        this.accountId = accountId;
        this.likes = new Like[0];
        this.comments = new Comment[0];
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public Like[] getLikes() {
        return likes;
    }

    public Comment[] getComments() {
        return comments;
    }

    public String getContent() {
        return content;
    }

    public void addLike(Like like) {
        Like[] temp = new Like[this.likes.length + 1];
        for (int i = 0; i < this.likes.length; i++)
            temp[i] = this.likes[i];
        temp[this.likes.length] = like;
        this.likes = temp;
    }

    public void addComment(Comment comment) {
        Comment[] temp = new Comment[this.comments.length + 1];
        for (int i = 0; i < this.comments.length; i++)
            temp[i] = this.comments[i];
        temp[this.comments.length] = comment;
        this.comments = temp;
    }
    public void displayPost() {
        System.out.println("(Post ID: " + this.postId + ") " + this.accountId + ": " + this.content);
    }


    public void displayInteraction() {
        System.out.println("----------------------------------------");
        System.out.println("(Post ID: " + this.postId + "): " + this.content);
        System.out.print("The post was liked by the fallowing account(s): ");

        // TODO: there is no comment ve number of comment değiştirilecek. Step 21 22 yapılacak. Message ID düzeltilecek.
        for (int i = 0; i < this.likes.length; i++)
            System.out.print(this.likes[i].getAccountId() + ", ");
        System.out.println();

        System.out.print("The post has " + this.comments.length + " comment(s)...");
        System.out.println();
        for (int i = 0; i < this.comments.length; i++)
            System.out.println("Comment " + (i + 1) + ": '" + this.comments[i].getAccountId() + "' said'" + this.comments[i].getContent() + "'");

    }
}

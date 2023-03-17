package main.post;

import main.interaction.*;
import java.util.Arrays;

/**
 * Represents social media post.
 */
public class Post {
    /**
     * Unique post identifier
     */
    private int postId;
    /**
     * Unique identifier for the account that created post
     */
    private String accountId;

    /**
     * Array of likes of post
     */
    private Like[] likes;

    /**
     * Array of comments of post
     */
    private Comment[] comments;

    /**
     * The content of the post
     */
    private String content;

    /**
     * Constructer of Post object.
     *
     * @param postId Unique post identifier
     * @param accountId Unique identifier for the account that created post
     * @param content The content of the post.
     */
    public Post(int postId, String accountId, String content) throws  IllegalArgumentException {
        if (postId < 0) {
            throw new IllegalArgumentException("Post ID must be a positive integer.");
        }
        if (accountId == null || accountId.equals("")) {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
        if (content == null || content.equals("")) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        this.postId = postId;
        this.accountId = accountId;
        this.likes = new Like[0];
        this.comments = new Comment[0];
        this.content = content;
    }

    /**
     * Getter for the post ID.
     *
     * @return The post ID.
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Getter for the account ID.
     *
     * @return The account ID.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Getter for the array of likes for the post.
     *
     * @return The array of likes for the post.
     */
    public Like[] getLikes() {
        return likes;
    }

    /**
     * Getter for the array of comments for the post.
     *
     * @return The array of comments for the post.
     */
    public Comment[] getComments() {
        return comments;
    }

    /**
     * Adds a like to the post.
     *
     * @param like The like to be added to the post.
     */
    public void addLike(Like like) throws IllegalArgumentException {
        if (like == null) {
            throw new IllegalArgumentException("Like cannot be null.");
        }
        Like[] temp = new Like[this.likes.length + 1];
        for (int i = 0; i < this.likes.length; i++)
            temp[i] = this.likes[i];
        temp[this.likes.length] = like;
        this.likes = temp;
    }

    /**
     * Adds a comment to the post.
     *
     * @param comment The comment to be added to the post.
     */
    public void addComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }
        Comment[] temp = new Comment[this.comments.length + 1];
        for (int i = 0; i < this.comments.length; i++)
            temp[i] = this.comments[i];
        temp[this.comments.length] = comment;
        this.comments = temp;
    }

    /**
     * Displays the post information.
     */
    public void displayPost() {
        System.out.println("(Post ID: " + this.postId + ") " + this.accountId + ": " + this.content);
    }


    /**
     * Displays the post's likes and comments.
     */
    public void displayInteraction() {
        System.out.println("----------------------------------------");
        System.out.println("(Post ID: " + this.postId + "): " + this.content);
        if (this.likes.length > 0) {
            System.out.print("The post was liked by the fallowing account(s): ");

            for (int i = 0; i < this.likes.length; i++)
                System.out.print(this.likes[i].getAccountId() + ", ");
            System.out.println();
        } else System.out.println("The post has no likes.");

        if (this.comments.length > 0) {
            System.out.print("The post has " + this.comments.length + " comment(s)...");
            System.out.println();
            for (int i = 0; i < this.comments.length; i++)
                System.out.println("Comment " + (i + 1) + ": '" + this.comments[i].getAccountId() + "' said'" + this.comments[i].getContent() + "'");
        } else System.out.println("The post has no comments.");

    }

    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", accountId='" + accountId + '\'' +
                ", likes=" + Arrays.toString(likes) +
                ", comments=" + Arrays.toString(comments) +
                ", content='" + content + '\'' +
                '}';
    }
}

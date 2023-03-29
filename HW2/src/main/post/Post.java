package main.post;

import main.interaction.*;
import java.util.Arrays;
import java.util.ArrayList;

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
     * ArrayList of likes of post
     */
    private ArrayList<Like> likes;

    /**
     * Array of comments of post
     */
    private ArrayList<Comment> comments;

    /**
     * The content of the post
     */
    private String content;

    /**
     * Constructor of Post object.
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
        this.likes = new ArrayList<Like>();
        this.comments = new ArrayList<Comment>();
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
    public ArrayList<Like> getLikes() {
        return likes;
    }

    /**
     * Getter for the array of comments for the post.
     *
     * @return The array of comments for the post.
     */
    public ArrayList<Comment> getComments() {
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
        this.likes.add(like);
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
        this.comments.add(comment);
    }


    public boolean removeComment(String accountId, int interactionId) {
        for (int i = 0; i < this.comments.size(); i++) {
            if (this.comments.get(i).getAccountId() == accountId && this.comments.get(i).getInteractionId() == interactionId) {
                this.comments.remove(this.comments.get(i));
                return true;
            }
        }
        return false;
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
        if (this.likes.size() > 0) {
            System.out.print("The post was liked by the fallowing account(s): ");

            for (int i = 0; i < this.likes.size(); i++)
                System.out.print(this.likes.get(i).getAccountId() + ", ");
            System.out.println();
        } else System.out.println("The post has no likes.");

        if (this.comments.size() > 0) {
            System.out.print("The post has " + this.comments.size() + " comment(s)...");
            System.out.println();
            for (int i = 0; i < this.comments.size(); i++)
                System.out.println("Comment " + (i + 1) + ": '" + this.comments.get(i).getAccountId() + "' said'" + this.comments.get(i).getContent() + "'");
        } else System.out.println("The post has no comments.");

    }

    /**
     * Remove the given like from like Array based on the username.
     *
     * @param accountId account username to be removed to filter which like
     *
     * @return boolean returns true if successfully removed else returns false.
     */
    public boolean removeLike(String accountId) {
        for (int i = 0; i < this.likes.size(); i++)
            if (this.likes.get(i).getAccountId() == accountId) {
                this.likes.remove(this.likes.get(i));
                return true;
            }
        return false;
    }

    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", accountId='" + accountId + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                ", content='" + content + '\'' +
                '}';
    }
}

package main.interaction;


/**
 * Represents a comment on a post.
 */
public class Comment extends Interaction {
    /**
     * The text content of the comment.
     */
    private String content;

    /**
     * Constructor of Comment object.
     * @param interactionId Unique interaction identifier for the comment.
     * @param accountId Unique identifier for the account that made the comment.
     * @param postId The ID of the post that the comment is on.
     * @param content The text content of the comment.
     */
    public Comment(int interactionId, String accountId, int postId, String content) throws IllegalArgumentException {
        super(interactionId, accountId, postId);

        if (content == null || content.equals("")) {
            throw new IllegalArgumentException("Comment content must not be null or empty");
        }
        this.content = content;
    }

    /**
     * Gets the content of the comment.
     * @return The text content of the comment.
     */
    public String getContent() {
        return content;
    }


    @Override
    public String toString() {
        return "Comment{" +
                super.toString() + " "  + this.content ;
    }
}

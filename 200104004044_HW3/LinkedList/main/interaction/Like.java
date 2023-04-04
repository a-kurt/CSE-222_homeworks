package LinkedList.main.interaction;

/**
 * Represents a like for the Post
 */
public class Like extends Interaction {
    /**
     * Constructor of the Like object
     * @param interactionId Unique identifier of Like
     * @param accountId Unique identifier of account
     * @param postId Unique identifier of post
     */
    public Like(int interactionId, String accountId, int postId) {
        super(interactionId, accountId, postId);
    }

}

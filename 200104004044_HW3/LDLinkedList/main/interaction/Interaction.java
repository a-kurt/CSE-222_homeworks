package LDLinkedList.main.interaction;

/**
 * Represents an interaction between an account and a post.
 */
public class Interaction {
    /**
     * Unique identifier of the interaction.
     */
    private int interactionId;

    /**
     * Unique identifier of the account.
     */
    private String accountId;

    /**
     * Unique identifier of the post.
     */
    private int postId;

    /**
    * Constructs an Interaction object with given parameters.
    * @param interactionId Unique identifier of the interaction.
    * @param accountId Unique identifier of the account.
    * @param postId Unique identifier of the post.
     * */
    public Interaction(int interactionId, String accountId, int postId) throws IllegalArgumentException {
        if (interactionId < 0) {
            throw new IllegalArgumentException("interactionId must be a positive integer");
        }
        if (accountId == null || accountId.equals("")) {
            throw new IllegalArgumentException("accountId must not be null or empty");
        }
        if (postId < 0) {
            throw new IllegalArgumentException("postId must be a positive integer");
        }
        this.interactionId = interactionId;
        this.accountId = accountId;
        this.postId = postId;
    }

    /**
     * Getter of accountId
     * @return Unique identifier of account Id
     */
    public String getAccountId() {
        return accountId;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public String toString() {
        return "Interaction{" +
                "interactionId=" + interactionId +
                ", accountId='" + accountId + '\'' +
                ", postId=" + postId +
                '}';
    }
}

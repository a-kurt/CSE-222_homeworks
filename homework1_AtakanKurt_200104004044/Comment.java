public class Comment extends Interaction {
    private String content;

    public Comment(int interactionId, int accountId, int postId, String content) {
        super(interactionId, accountId, postId);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

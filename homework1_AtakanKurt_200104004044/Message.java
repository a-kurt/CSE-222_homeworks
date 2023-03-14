public class Message {
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;

    public Message(int messageId, int senderId, int receiverId, String content) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public void display() {
        System.out.println("Message ID: " + this.messageId);
        System.out.println("From: " + this.senderId);
        System.out.println("To: " + this.receiverId);
        System.out.println("Message: " + this.content);
    }
}

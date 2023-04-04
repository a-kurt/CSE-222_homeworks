package ArrayList.main.message;

/**
 * Represents a message sent from a sender to a receiver.
 */
public class Message {
    /**
     * Unique identifier for message
     */
    private int messageId;

    /**
     * Unique identifier for sender
     */
    private String senderId;

    /**
     * Unique identifier for receiver
     */
    private String receiverId;

    /**
     * Content of the message
     */
    private String content;

    /**
     * Constructor of the Message object
     *
     * @param messageId Unique identifier for message
     * @param senderId Unique identifier for sender
     * @param receiverId Unique identifier for receiver
     * @param content Content of the message
     */
    public Message(int messageId, String senderId, String receiverId, String content) throws IllegalArgumentException {
        if (messageId < 0) {
            throw new IllegalArgumentException("Message ID must be positive");
        }
        if (senderId == null || receiverId == null) {
            throw new IllegalArgumentException("Sender ID and receiver ID cannot be null");
        }
        if (content.equals("")) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    /**
     * Getter for senderId
     * @return The sender ID
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Getter for receiverId
     * @return The receiver ID
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Display message information
     */
    public void display() {
        System.out.println("----------------------");
        System.out.println("Message ID: " + this.messageId);
        System.out.println("From: " + this.senderId);
        System.out.println("To: " + this.receiverId);
        System.out.println("Message: " + this.content);
    }

    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

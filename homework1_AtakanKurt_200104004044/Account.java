import java.util.Arrays;

/**
 * Represents a social media account.
 */
public class Account {
    /**
     * Unique identifier of account.
     */
    private int accountId;

    /**
     * Username of account.
     */
    private String username;

    /**
     * Birthdate of the account.
     */
    private String birthdate;
    /**
     * Location of the account.
     */
    private String location;

    /**
     * Array of posts of Account.
     */
    private Post[] posts;

    /**
     * Array of messages of Account.
     */
    private Message[] messages;

    /**
     * Array of account that user follows.
     */
    private Account[] following;

    /**
     * Array of account that user's followers
     */
    private Account[] followers;

    /**
     * Boolean if account is logged in.
     */
    private boolean logged;

    /**
     * Array of blocked accounts.
     */
    private Account[] blockedAccounts;

    /**
     * Constructor of account object.
     *
     * @param accountId Unique identifier of account.
     * @param username Username of the account.
     * @param birthdate Birthdate of the account user.
     * @param location Location of the user.
     */
    public Account(int accountId, String username, String birthdate, String location) throws IllegalArgumentException {
        if (accountId < 0) {
            throw new IllegalArgumentException("Account ID must be a positive integer.");
        }
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (birthdate == null || birthdate.equals("")) {
            throw new IllegalArgumentException("Birthdate cannot be null or empty.");
        }
        if (location == null || location.equals("")) {
            throw new IllegalArgumentException("Location cannot be null or empty.");
        }

        this.accountId = accountId;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;

        this.logged = false;
        this.posts = new Post[0];
        this.messages = new Message[0];
        this.following = new Account[0];
        this.followers = new Account[0];
        this.blockedAccounts = new Account[0];

        System.out.printf("An account with username %s has been created.\n", this.username);
    }


    /**
     * Logs into account and makes available use some functions.
     * @param inUsername Inputted username.
     * @param inAccountId Inputted account ID.
     */
    public void login(String inUsername, int inAccountId) throws IllegalArgumentException {
        if (inAccountId < 0) {
            throw new IllegalArgumentException("Account ID must be a positive integer.");
        }
        if (inUsername == null || inUsername.equals("")) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (inUsername.equals(this.username) && inAccountId == this.accountId)
            this.logged = true;
        else System.out.println("ERROR! Wrong username or ID!");
    }

    /**
     * Logs out from account.
     */
    public void logout() {
        this.logged = false;
    }

    /**
     * Getter of username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of post array.
     * @return Post array.
     */
    public Post[] getPosts() {
        return posts;
    }

    /**
     * Get number of posts
     * @return number of posts
     */
    public int getPostsNumber() {
        return this.posts.length;
    }

    /**
     * Get number of fallowed accounts.
     * @return number of fallowed accounts.
     */
    public int getFollowingNumber() {
        return this.following.length;
    }

    /**
     * Get number of followers.
     * @return number of followers.
     */
    public int getFollowersNumber() {
        return this.followers.length;
    }

    /**
     * Get number of messages.
     * @return number of messages.
     */
    public int getMessageNumber() { return this.messages.length; }

    /**
     * Fallow account.
     * @param account account that will be fallowed
     */
    public void follow(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to follow someone.");
            return;
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: You cannot follow an account that has blocked you.");
            return;
        }
        Account[] temp = new Account[this.getFollowingNumber() + 1];

        for (int i = 0; i < this.getFollowingNumber(); i++)
            temp[i] = this.following[i];
        temp[this.getFollowingNumber()] = account;
        this.following = temp;

        account.addFollower(this);
    }

    /**
     * Add follower to the array.
     * @param account account that will be added.
     */
    public void addFollower(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        Account[] temp = new Account[this.getFollowersNumber() + 1];

        for (int i = 0; i < this.getFollowersNumber(); i++)
            temp[i] = this.followers[i];
        temp[this.getFollowersNumber()] = account;
        this.followers = temp;
    }

    /**
     * View profile the selected account.
     * @param account account that will view
     */
    public void viewProfile(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view someone's profile.");
            return;
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: You cannot view the profile of an account that has blocked you.");
            return;
        }
        account.display();
    }

    /**
     * Display account information.
     */
    public void display() {
        System.out.println("----------------------");
        System.out.println("User ID: " + this.accountId);
        System.out.println("Username: " + this.username);
        System.out.println("Location: " + this.location);
        System.out.println("Birth Date: " + this.birthdate);
        System.out.println(this.username + " is following " + this.getFollowingNumber() + " account(s) and has " + this.getFollowersNumber() + " follower(s)");

        if (this.getFollowersNumber() > 0) {
            System.out.print("The followers of " + this.username + " are: ");
            for (int i = 0; i < this.getFollowersNumber(); i++) {
                System.out.print(this.followers[i].getUsername() + ", ");
            }
            System.out.println();
        }

        if (this.getFollowingNumber() > 0) {
            System.out.print(this.username + " is following: ");
            for (int i = 0; i < this.getFollowingNumber(); i++) {
                System.out.print(this.following[i].getUsername() + ", ");
            }
            System.out.println();
        }

        System.out.println(this.username + " has " + this.getPostsNumber() + " posts.");
    }

    /**
     * Share the post.
     * @param post post that will be shared.
     */
    public void sharePost(Post post) throws IllegalArgumentException {
        if (post == null) {
            throw new IllegalArgumentException("Post must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to share post.");
            return;
        }
        Post[] temp = new Post[this.getPostsNumber() + 1];
        for (int i = 0; i < this.getPostsNumber(); i++)
            temp[i] = this.posts[i];
        temp[this.getPostsNumber()] = post;
        this.posts = temp;

    }

    /**
     * View the posts of user.
     * @param account account that will be viewed posts.
     */
    public void viewPost(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view someone's post.");
            return;
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: You cannot view post of an account that has blocked you.");
            return;
        }
        for (int i = 0; i < account.getPostsNumber(); i++)
            account.getPosts()[i].displayPost();
    }

    /**
     * Display interactions of post.
     * @param account account posts that will be show interactions.
     */
    public void viewPostInteraction(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: You cannot view the post interaction of an account that has blocked you.");
            return;
        }
        for (int i = 0; i < account.getPostsNumber(); i++)
            account.getPosts()[i].displayInteraction();
    }


    /**
     * Adds a like to the specified post.
     * @param post the post to like
     */
    public void likePost(Post post) throws IllegalArgumentException {
        if (post == null) {
            throw new IllegalArgumentException("Post must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to like someone's post.");
            return;
        }
        Like like = new Like(post.getLikes().length, this.username, post.getPostId());
        post.addLike(like);
    }

    /**
     * Adds a comment to the specified post.
     *
     * @param post the post to comment on
     * @param content the content of the comment
     */
    public void commentPost(Post post, String content) throws IllegalArgumentException {
        if (post == null) {
            throw new IllegalArgumentException("Post must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to comment.");
            return;
        }
        if (content.equals("")) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }
        Comment comment = new Comment(post.getComments().length, this.username, post.getPostId(), content);
        post.addComment(comment);

    }

    /**
     * Sends a message to a specific account.
     *
     * @param account the account to send the message to.
     * @param message the message to send.
     */
    public void sendMessage(Account account, Message message) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to send message.");
            return;
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: You cannot send message to an account that has blocked you.");
            return;
        }
        boolean followed = false;
        for (int i = 0; i < this.getFollowingNumber(); i++)
            if (this.following[i] == account) followed = true;
        if (followed) {
            Message[] temp = new Message[this.messages.length + 1];
            for (int i = 0; i < this.messages.length; i++)
                temp[i] = this.messages[i];
            temp[this.messages.length] = message;
            this.messages = temp;

            account.receiveMessage(message);
        } else System.out.println("ERROR: You have to fallow the receiver to send message.");
    }

    /**
     * Adds a new message to the user's inbox.
     * @param message the message to be added to the inbox.
     */
    public void receiveMessage(Message message) throws IllegalArgumentException {
        if (message == null) {
            throw new IllegalArgumentException("Message must be valid.");
        }
        Message[] temp = new Message[this.messages.length + 1];
        for (int i = 0; i < this.messages.length; i++)
            temp[i] = this.messages[i];
        temp[this.messages.length] = message;
        this.messages = temp;
    }

    /**
     * Displays the number of messages in the inbox for the user.
     */
    public void getInboxNumber() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view inbox number.");
            return;
        }
        int count = 0;
        for (int i = 0; i < this.messages.length; i++)
            if (this.messages[i].getReceiverId().equals(this.username)) count++;
        System.out.println("There is/are " + count + " message(s) in the inbox.");
    }

    /**
     * Displays the number of messages in the outbox for the user.
     */
    public void getOutboxNumber() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view outbox number.");
            return;
        }
        int count = 0;
        for (int i = 0; i < this.messages.length; i++)
            if (this.messages[i].getSenderId().equals(this.username)) count++;
        System.out.println("There is/are " + count + " message(s) in the outbox.");
    }

    /**
     * Displays all messages sent by the user from the inbox.
     */
    public void displayInbox() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view inbox.");
            return;
        }
        for (int i = 0; i < this.messages.length; i++) {
            if (this.messages[i].getReceiverId().equals(this.username)) {
                this.messages[i].display();
            }
        }
    }

    /**
     * Displays all messages sent by the user from the outbox.
     */
    public void displayOutbox() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view outbox.");
            return;
        }

        for (int i = 0; i < this.messages.length; i++) {
            if (this.messages[i].getSenderId().equals(this.username)) {
                this.messages[i].display();
            }
        }
    }

    /**
     * Blocks the given account if the user is logged in and the account is not already blocked.
     * Removes the blocked account from followers and fallowing list.
     *
     * @param account The account to be blocked.
     */
    public void blockAccount(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to block someone.");
            return;
        }
        if (account.checkBlocked(this)) {
            System.out.println("ERROR: User already in the blocked list");
            return;
        }
        int index = -1;
        for (int i = 0; i < this.followers.length; i++) {
            if (this.followers[i] == account) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < this.followers.length - 1; i++) {
                this.followers[i] = this.followers[i + 1];
            }

            Account[] temp = new Account[this.followers.length-1];
            for (int i = 0; i < this.followers.length-1; i++)
                temp[i] = this.followers[i];
            this.followers = temp;
        }

        index = -1;
        for (int i = 0; i < this.following.length; i++) {
            if (this.following[i] == account) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < this.following.length - 1; i++) {
                this.following[i] = this.following[i + 1];
            }
            Account[] temp = new Account[this.following.length-1];
            for (int i = 0; i < this.following.length-1; i++)
                temp[i] = this.following[i];
            this.following = temp;
        }

        Account[] temp = new Account[this.blockedAccounts.length+1];
        for (int i = 0; i < this.blockedAccounts.length; i++)
            temp[i] = this.blockedAccounts[i];
        temp[this.blockedAccounts.length] = account;
        this.blockedAccounts = temp;
    }

    /**
     * Checks if an account is blocked.
     *
     * @param account The account to check.
     * @return true if the account is blocked, false otherwise.
     */
    public boolean checkBlocked(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        boolean blocked = false;
        for (int i = 0; i < this.blockedAccounts.length; i++) {
            if (blockedAccounts[i] == account) {
                blocked = true;
                break;
            }
        }
        return blocked;
    }

    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", location='" + location + '\'' +
                ", posts=" + Arrays.toString(posts) +
                ", messages=" + Arrays.toString(messages) +
                ", following=" + Arrays.toString(following) +
                ", followers=" + Arrays.toString(followers) +
                ", logged=" + logged +
                ", blockedAccounts=" + Arrays.toString(blockedAccounts) +
                '}';
    }
}

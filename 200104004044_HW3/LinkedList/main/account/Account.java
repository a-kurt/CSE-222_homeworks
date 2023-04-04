package LinkedList.main.account;

import LinkedList.main.post.Post;
import LinkedList.main.interaction.*;
import LinkedList.main.message.Message;

import java.util.LinkedList;
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
     * LinkedList of posts of Account.
     */
    private LinkedList<Post> posts;

    /**
     * LinkedList of messages of Account.
     */
    private LinkedList<Message> messages;


    /**
     * LinkedList of account that user follows.
     */
    private LinkedList<Account> following;

    /**
     * LinkedList of account that user's followers
     */
    private LinkedList<Account> followers;

    /**
     * Boolean if account is logged in.
     */
    private boolean logged;

    /**
     * LinkedList of blocked accounts.
     */
    private LinkedList<Account> blockedAccounts;

    /**
     * LinkedList of user's action.
     */
    private LinkedList<String> userActions;

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
        this.posts = new LinkedList<Post>();
        this.messages = new LinkedList<Message>();
        this.following = new LinkedList<Account>();
        this.followers = new LinkedList<Account>();
        this.blockedAccounts = new LinkedList<Account>();
        this.userActions = new LinkedList<String>();

        System.out.printf("An account with username %s has been created.\n", this.username);
    }


    /**
     * Logs into account and makes available use some functions.
     * @param inUsername Inputted username.
     * @param inAccountId Inputted account ID.
     */
    public void login(String inUsername, int inAccountId) throws IllegalArgumentException {
        if (this.logged) {
            System.out.println("ERROR! You are already logged in");
            return;
        }
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
        if (!this.logged) {
            System.out.println("ERROR! Can not log out without logging in.");
            return;
        }
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
    public LinkedList<Post> getPosts() {
        return posts;
    }

    /**
     * Get number of posts
     * @return number of posts
     */
    public int getPostsNumber() {
        return this.posts.size();
    }

    /**
     * Get number of fallowed accounts.
     * @return number of fallowed accounts.
     */
    public int getFollowingNumber() {
        return this.following.size();
    }

    /**
     * Get number of followers.
     * @return number of followers.
     */
    public int getFollowersNumber() {
        return this.followers.size();
    }

    /**
     * Get number of messages.
     * @return number of messages.
     */
    public int getMessageNumber() { return this.messages.size(); }

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

        this.following.add(account);
        account.addFollower(this);
        this.addAction("You followed " + account.getUsername());
    }

    /**
     * Add follower to the array.
     * @param account account that will be added.
     */
    public void addFollower(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        this.followers.add(account);
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
        this.addAction("You viewed " + account.getUsername() + "'s profile");
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
                System.out.print(this.followers.get(i).getUsername() + ", ");
            }
            System.out.println();
        }

        if (this.getFollowingNumber() > 0) {
            System.out.print(this.username + " is following: ");
            for (int i = 0; i < this.getFollowingNumber(); i++) {
                System.out.print(this.following.get(i).getUsername() + ", ");
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
        this.posts.add(post);
        this.addAction("You shared post id:  " + post.getPostId());
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
            account.getPosts().get(i).displayPost();
        this.addAction("You viewed " + account.getUsername() + "'s posts.");
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
            account.getPosts().get(i).displayInteraction();
        this.addAction("You viewed " + account.getUsername() + "'s post interactions.");
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
        Like like = new Like(post.getLikes().size(), this.username, post.getPostId());
        post.addLike(like);
        this.addAction("You liked " + post.getAccountId() + "'s post id: " + post.getPostId());
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
        Comment comment = new Comment(post.getComments().size(), this.username, post.getPostId(), content);
        post.addComment(comment);
        this.addAction("You commented " + post.getAccountId() + "'s post id: " + post.getPostId());
    }

    public void uncommentPost(Post post, int interactionId) {
        if (post == null) {
            throw new IllegalArgumentException("Post must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to comment.");
            return;
        }

        if (post.removeComment(this.username, interactionId)) {
            System.out.println("Successfully Removed the Comment.");
            this.addAction("You uncommented " + post.getAccountId() + " post");
        }
        else System.out.println("Can not remove comment. It's either not exist or not belongs to you.");
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
            if (this.following.get(i) == account) followed = true;
        if (followed) {
            this.messages.add(message);
            account.receiveMessage(message);
            this.addAction("You sent message to " + account.getUsername());
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
        this.messages.add(message);
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
        for (int i = 0; i < this.messages.size(); i++)
            if (this.messages.get(i).getReceiverId().equals(this.username)) count++;
        System.out.println("There is/are " + count + " message(s) in the inbox.");
        this.addAction("You have checked number of messages in inbox");
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
        for (int i = 0; i < this.messages.size(); i++)
            if (this.messages.get(i).getSenderId().equals(this.username)) count++;
        System.out.println("There is/are " + count + " message(s) in the outbox.");
        this.addAction("You have checked number of messages in outbox");
    }

    /**
     * Displays all messages sent by the user from the inbox.
     */
    public void displayInbox() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view inbox.");
            return;
        }
        for (int i = 0; i < this.messages.size(); i++) {
            if (this.messages.get(i).getReceiverId().equals(this.username)) {
                this.messages.get(i).display();
            }
        }
        this.addAction("You displayed inbox");
    }

    /**
     * Displays all messages sent by the user from the outbox.
     */
    public void displayOutbox() {
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to view outbox.");
            return;
        }

        for (int i = 0; i < this.messages.size(); i++) {
            if (this.messages.get(i).getSenderId().equals(this.username)) {
                this.messages.get(i).display();
            }
        }
        this.addAction("You displayed outbox");
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
        if (this.followers.remove(account));
        if (this.following.remove(account));
        this.blockedAccounts.add(account);
        this.addAction("You blocked " + account.getUsername());
    }

    /**
     * Unblocks the account.
     * @param account account to be unblocked
     * @throws IllegalArgumentException
     */
    public void unblockAccount(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to block someone.");
            return;
        }

        this.blockedAccounts.remove(account);

        this.addAction("You unblocked " + account.getUsername());
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
        for (int i = 0; i < this.blockedAccounts.size(); i++) {
            if (blockedAccounts.get(i).accountId == account.getAccountId()) {
                blocked = true;
                break;
            }
        }
        return blocked;
    }

    public int getAccountId() {
        return accountId;
    }

    /**
     * Unlike a post User liked
     *
     * @param post The post to be unliked.
     */
    public void unlike(Post post) throws IllegalArgumentException {
        if (post == null) {
            throw new IllegalArgumentException("Post must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to unlike post.");
            return;
        }
        if (post.removeLike(this.username)) {
            System.out.println("Successfully Unliked Post");
            this.addAction("You unliked " + post.getAccountId() + "'s post");
        }
        else System.out.println("Can not remove message. It's either not exist or not belong to you.");
    }

    public void unfollow(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (!this.logged) {
            System.out.println("ERROR: You need to log in to block someone.");
            return;
        }
        if (this.following.remove(account)) {
            this.addAction("You unfollowed " + account.getUsername());
            account.removeFollower(this);
        }
    }

    public void removeFollower(Account account) throws IllegalArgumentException {
        if (account == null) {
            throw new IllegalArgumentException("Account must be valid.");
        }
        if (this.followers.remove(account));
    }

    public LinkedList<String> getUserActions() {
        return userActions;
    }

    private void addAction(String action) {
        this.userActions.add(action);
    }

    public void displayActions() {
        for (int i = 0; i < this.userActions.size(); i++)
            System.out.println(this.userActions.get(i));
    }

    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", location='" + location + '\'' +
                ", posts=" + posts +
                ", messages=" + messages +
                ", following=" + following +
                ", followers=" + followers +
                ", logged=" + logged +
                ", blockedAccounts=" + blockedAccounts +
                '}';
    }
}

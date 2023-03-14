import java.util.Scanner;

public class Account {
    private int accountId;
    private String username;
    private String birthdate;
    private String location;
    private Post[] posts;
    private Message[] messages;
    private Account[] following;
    private Account[] followers;

    private boolean logged;
    public Account(int accountId, String username, String birthdate, String location) {
        this.accountId = accountId;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;

        this.logged = false;
        this.posts = new Post[0];
        this.messages = new Message[0];
        this.following = new Account[0];
        this.followers = new Account[0];

        System.out.printf("An account with username %s has been created.\n", this.username);
    }

    public boolean isLogged() {
        return logged;
    }

    public void login(String inUsername, int inAccountId) {
        if (inUsername == this.username && inAccountId == this.accountId)
            this.logged = true;
        else System.out.println("ERROR! Wrong username or ID!");
    }

    public void logout() {
        this.logged = false;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getLocation() {
        return location;
    }

    public Post[] getPosts() {
        return posts;
    }

    public int getPostsNumber() {
        return this.posts.length;
    }

    public Message[] getMessages() {
        return messages;
    }

    public Account[] getFollowing() {
        return following;
    }

    public int getFollowingNumber() {
        return this.following.length;
    }

    public Account[] getFollowers() {
        return followers;
    }

    public int getFollowersNumber() {
        return this.followers.length;
    }

    public int getMessageNumber() { return this.messages.length; }
    public void follow(Account account) {
        if (this.logged == true) {
            Account[] temp = new Account[this.getFollowingNumber() + 1];

            for (int i = 0; i < this.getFollowingNumber(); i++)
                temp[i] = this.following[i];
            temp[this.getFollowingNumber()] = account;
            this.following = temp;

            account.addFollower(this);
        }
    }

    public void addFollower(Account account) {
        Account[] temp = new Account[this.getFollowersNumber() + 1];

        for (int i = 0; i < this.getFollowersNumber(); i++)
            temp[i] = this.followers[i];
        temp[this.getFollowersNumber()] = account;
        this.followers = temp;
    }

    public void viewProfile(Account account) {
        if (this.logged == true) {
            account.display();
        }
    }

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

    public void sharePost(Post post) {
        if (this.logged == true) {
/*
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter post content: ");
            String content = scanner.nextLine();
            Post post = new Post(this.getPostsNumber()+1, this.accountId, content);
*/
            Post[] temp = new Post[this.getPostsNumber() + 1];
            for (int i = 0; i < this.getPostsNumber(); i++)
                temp[i] = this.posts[i];
            temp[this.getPostsNumber()] = post;
            this.posts = temp;
        }
    }

    public void viewPost(Account account) {
        if (this.logged == true) {
            for (int i = 0; i < account.getPostsNumber(); i++) {
                account.getPosts()[i].displayPost();
            }
        }
    }

    public void viewPostInteraction(Account account) {
        for (int i = 0; i < account.getPostsNumber(); i++) {
            account.getPosts()[i].displayInteraction();
        }
    }

    public void likePost(Post post) {
        if (this.logged == true) {
            Like like = new Like(post.getLikes().length, this.accountId, post.getPostId());
            post.addLike(like);
        }
    }

    public void commentPost(Post post, String content) {
        if (this.logged == true) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter comment content: ");
//            String content = scanner.nextLine();
            Comment comment = new Comment(post.getComments().length, this.accountId, post.getPostId(), content);
            post.addComment(comment);
        }
    }

    public void sendMessage(Account account, Message message) {
        boolean followed = false;
        for (int i = 0; i < this.getFollowingNumber(); i++)
            if (this.following[i] == account) followed = true;
        if (this.logged && followed) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter message content: ");
//            String content = scanner.nextLine();
//            Message message = new Message(this.messages.length + 1, this.accountId, account.getAccountId(), content);

            Message[] temp = new Message[this.messages.length + 1];
            for (int i = 0; i < this.messages.length; i++)
                temp[i] = this.messages[i];
            temp[this.messages.length] = message;
            this.messages = temp;

            account.receiveMessage(message);
        }
    }

    public void receiveMessage(Message message) {
        Message[] temp = new Message[this.messages.length + 1];
        for (int i = 0; i < this.messages.length; i++)
            temp[i] = this.messages[i];
        temp[this.messages.length] = message;
        this.messages = temp;
    }

    public void getInboxNumber() {
        if (this.logged == true) {
            int count = 0;
            for (int i = 0; i < this.messages.length; i++)
                if (this.messages[i].getReceiverId() == this.accountId) count++;
            System.out.println("There is/are " + count + " message(s) in the inbox.");
        }
    }

    public void getOutboxNumber() {
        if (this.logged == true) {
            int count = 0;
            for (int i = 0; i < this.messages.length; i++)
                if (this.messages[i].getSenderId() == this.accountId) count++;
            System.out.println("There is/are " + count + " message(s) in the outbox.");
        }
    }

    public void displayInbox() {
        if(this.logged == true) {
            for (int i = 0; i < this.messages.length; i++) {
                if (this.messages[i].getReceiverId() == this.accountId) {
                    this.messages[i].display();
                }
            }
        }
    }

    public void displayOutbox() {
        if(this.logged == true) {
            for (int i = 0; i < this.messages.length; i++) {
                if (this.messages[i].getSenderId() == this.accountId) {
                    this.messages[i].display();
                }
            }
        }
    }

//    @Override
//    public String toString() {
//        String output = "";
//          output += "User ID: " + this.accountId + "\n";
//          output += "Username: " + this.username + "\n";
//          output += "Location: " + this.location + "\n";
//          output += "Birth Date: " + this.birthdate + "\n";
//          output += this.username + " is following " + this.getFollowingNumber() + " account(s) and has " + this.getFollowersNumber() + " follower(s)\n";
//
//          if (this.getFollowersNumber() > 0) {
//              output += "The followers of " + this.username + " are: ";
//              for (int i = 0; i < this.getFollowersNumber(); i++)
//                  output += this.followers[i] + ", ";
//          }
//          output += "\n";
//
//          if (this.getFollowingNumber() > 0) {
//              output += this.username + " is following: ";
//              for (int i = 0; i < this.getFollowingNumber(); i++)
//                  output += this.following[i] + ", ";
//          }
//
//          output += "\n";
//
//          output += this.username + " has " + this.getPostsNumber() + "posts.\n";
//
//          return output;
//    }
}

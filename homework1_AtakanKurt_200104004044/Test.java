public class Test {
    public static void main(String[] args) {
        /* Scenario 1 */
        // System.out.println("\u001B[1m\u001B[0m");
        System.out.println("\u001B[1mStep 1...\u001B[0m Creating accounts...");
        Account gizemsungu = new Account(1, "gizemsungu", "01.01.1998", "Gebze");
        Account sibelgulmez = new Account(2, "sibelgulmez", "10.03.1995", "Istanbul");
        Account gokhankaya = new Account(3, "gokhankaya", "02.02.1994", "Mersin");

        System.out.println("\u001B[1mStep 2...\u001B[0m Logging into an account (username: sibelgulmez)…");
        sibelgulmez.login("sibelgulmez", 2);

        System.out.println("\u001B[1mStep 3...\u001B[0m Sharing two posts…");
        Post post1 = new Post(sibelgulmez.getPostsNumber()+1, sibelgulmez.getAccountId(), "I like Java.");
        sibelgulmez.sharePost(post1);
        Post post2 = new Post(sibelgulmez.getPostsNumber()+1, sibelgulmez.getAccountId(), "Java the coffee...");
        sibelgulmez.sharePost(post2);

        System.out.println("\u001B[1mStep 4...\u001B[0m Following gizemsungu and gokhankaya...");
        sibelgulmez.follow(gizemsungu);
        sibelgulmez.follow(gokhankaya);

        System.out.println("\u001B[1mStep 5...\u001B[0m Logging out from account 'sibelgulmez'…");
        sibelgulmez.logout();

        System.out.println("\u001B[1mStep 6...\u001B[0m Logging into another account (username: gokhankaya)…");
        gokhankaya.login("gokhankaya", 3);

        System.out.println("\u001B[1mStep 7...\u001B[0m Viewing sibelgulmez's profile...");
        gokhankaya.viewProfile(sibelgulmez);

        System.out.println("\u001B[1mStep 8...\u001B[0m Viewing sibelgulmez'posts...");
        gokhankaya.viewPost(sibelgulmez);

        System.out.println("\u001B[1mStep 9...\u001B[0m Liking a post of sibelgulmez...");
        gokhankaya.likePost(sibelgulmez.getPosts()[0]);

        System.out.println("\u001B[1mStep 10...\u001B[0m Adding a comment on a post of sibelgulmez...");
        gokhankaya.commentPost(sibelgulmez.getPosts()[0], "me too!");

        System.out.println("\u001B[1mStep 11...\u001B[0m Following sibelgulmez and gizemsungu...");
        gokhankaya.follow(sibelgulmez);
        gokhankaya.follow(gizemsungu);

        System.out.println("\u001B[1mStep 12...\u001B[0m Sending a message to gizemsungu...");
        Message message1 = new Message(gokhankaya.getMessageNumber(), gokhankaya.getAccountId(), gizemsungu.getAccountId(), "This homework is too easy!");
        gokhankaya.sendMessage(gizemsungu, message1);

        System.out.println("\u001B[1mStep 13...\u001B[0m Logging out from account 'gokhankaya'..");
        gokhankaya.logout();

        System.out.println("\u001B[1mStep 14...\u001B[0m Logging into another account (username: gizemsungu)…");
        gizemsungu.login("gizemsungu", 1);

        System.out.println("\u001B[1mStep 15...\u001B[0m Checking outbox...");
        gizemsungu.getOutboxNumber();

        System.out.println("\u001B[1mStep 16...\u001B[0m Checking inbox...");
        gizemsungu.getInboxNumber();

        System.out.println("\u001B[1mStep 17...\u001B[0m Viewing inbox...");
        gizemsungu.displayInbox();

        System.out.println("\u001B[1mStep 18...\u001B[0m Viewing sibelgulmez's profile...");
        gizemsungu.viewProfile(sibelgulmez);

        System.out.println("\u001B[1mStep 19...\u001B[0m Viewing sibelgulmez's posts...");
        gizemsungu.viewPost(sibelgulmez);

        System.out.println("\u001B[1mStep 20...\u001B[0m Viewing sibelgulmez's posts' interactions...");
        gizemsungu.viewPostInteraction(sibelgulmez);
    }
}

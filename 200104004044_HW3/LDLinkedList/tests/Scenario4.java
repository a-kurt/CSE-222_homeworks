package LDLinkedList.tests;

import LDLinkedList.main.account.Account;
import LDLinkedList.main.post.Post;
import LDLinkedList.main.message.Message;

public class Scenario4 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            System.out.println("\u001B[1mStep 1...\u001B[0m Creating accounts...");
            Account gizemsungu = new Account(1, "gizemsungu", "01.01.1998", "Gebze");
            Account sibelgulmez = new Account(2, "sibelgulmez", "10.03.1995", "Istanbul");
            Account gokhankaya = new Account(3, "gokhankaya", "02.02.1994", "Mersin");
            Account atakankurt = new Account(4, "atakankurt", "26.05.2002", "Safranbolu");
            Account beyzacam = new Account(5, "beyzacam", "31.12.2002", "Trabzon");
            Account savasyaldız = new Account(6, "savasyaldız", "12.04.2002", "Antep");
            Account bedoaksoy = new Account(7, "bedoaksoy", "07.03.2002", "Bartın");
            Account cansengun = new Account(8, "cansengun", "23.08.2001", "Izmit");
            Account efebayrak = new Account(9, "efebayrak", "03.03.2003", "Afyon");
            Account yunuscacan = new Account(10, "yunuscacan", "01.11.2000", "Trabzon");

            System.out.println();

            System.out.println("\u001B[1mStep 2...\u001B[0m Logging into an account (username: sibelgulmez)…");
            sibelgulmez.login("sibelgulmez", 2);
            System.out.println();

            System.out.println("\u001B[1mStep 3...\u001B[0m Sharing two posts…");
            Post post1 = new Post(sibelgulmez.getPostsNumber() + 1, sibelgulmez.getUsername(), "I like Java.");
            sibelgulmez.sharePost(post1);
            Post post2 = new Post(sibelgulmez.getPostsNumber() + 1, sibelgulmez.getUsername(), "Java the coffee...");
            sibelgulmez.sharePost(post2);
            System.out.println();

            System.out.println("\u001B[1mStep 4...\u001B[0m Following gizemsungu and gokhankaya...");
            sibelgulmez.follow(gizemsungu);
            sibelgulmez.follow(gokhankaya);
            System.out.println();

            System.out.println("\u001B[1mStep 5...\u001B[0m Logging out from account 'sibelgulmez'…");
            sibelgulmez.logout();
            System.out.println();

            System.out.println("\u001B[1mStep 6...\u001B[0m Logging into another account (username: gokhankaya)…");
            gokhankaya.login("gokhankaya", 3);
            System.out.println();

            System.out.println("\u001B[1mStep 7...\u001B[0m Viewing sibelgulmez's profile...");
            gokhankaya.viewProfile(sibelgulmez);
            System.out.println();

            System.out.println("\u001B[1mStep 8...\u001B[0m Viewing sibelgulmez'posts...");
            gokhankaya.viewPost(sibelgulmez);
            System.out.println();

            System.out.println("\u001B[1mStep 9...\u001B[0m Liking a post of sibelgulmez...");
            gokhankaya.likePost(sibelgulmez.getPosts().get(0));
            System.out.println();

            System.out.println("\u001B[1mStep 10...\u001B[0m Adding a comment on a post of sibelgulmez...");
            gokhankaya.commentPost(sibelgulmez.getPosts().get(0), "me too!");
            System.out.println();

            System.out.println("\u001B[1mStep 11...\u001B[0m Following sibelgulmez and gizemsungu...");
            gokhankaya.follow(sibelgulmez);
            gokhankaya.follow(gizemsungu);
            System.out.println();

            System.out.println("\u001B[1mStep 12...\u001B[0m Sending a message to gizemsungu...");
            Message message1 = new Message(gokhankaya.getMessageNumber(), gokhankaya.getUsername(), gizemsungu.getUsername(), "This homework is too easy!");
            gokhankaya.sendMessage(gizemsungu, message1);
            System.out.println();

            System.out.println("\u001B[1mStep 13...\u001B[0m Logging out from account 'gokhankaya'..");
            gokhankaya.logout();
            System.out.println();

            System.out.println("\u001B[1mStep 14...\u001B[0m Logging into another account (username: gizemsungu)…");
            gizemsungu.login("gizemsungu", 1);
            System.out.println();

            System.out.println("\u001B[1mStep 15...\u001B[0m Checking outbox...");
            gizemsungu.getOutboxNumber();
            System.out.println();

            System.out.println("\u001B[1mStep 16...\u001B[0m Checking inbox...");
            gizemsungu.getInboxNumber();
            System.out.println();

            System.out.println("\u001B[1mStep 17...\u001B[0m Viewing inbox...");
            gizemsungu.displayInbox();
            System.out.println();

            System.out.println("\u001B[1mStep 18...\u001B[0m Viewing sibelgulmez's profile...");
            gizemsungu.viewProfile(sibelgulmez);
            System.out.println();

            System.out.println("\u001B[1mStep 19...\u001B[0m Viewing sibelgulmez's posts...");
            gizemsungu.viewPost(sibelgulmez);
            System.out.println();

            System.out.println("\u001B[1mStep 20...\u001B[0m Viewing sibelgulmez's posts' interactions...");
            gizemsungu.viewPostInteraction(sibelgulmez);
            System.out.println();

            System.out.println("\u001B[1mStep 21...\u001B[0m Liking sibelgulmez's posts...");
            gizemsungu.likePost(sibelgulmez.getPosts().get(0));
            gizemsungu.likePost(sibelgulmez.getPosts().get(1));
            System.out.println();

            System.out.println("\u001B[1mStep 22...\u001B[0m Viewing sibelgulmez's posts' interactions...");
            gizemsungu.viewPostInteraction(sibelgulmez);
            gizemsungu.logout();
            System.out.println();
            System.out.println();

            System.out.println("\u001B[1m------------Scenario 4...--------------\u001B[0m");
            System.out.println("\u001B[1mStep 1...\u001B[0m Logining gizemsungu...");
            gizemsungu.login("gizemsungu", 1);
            System.out.println();


            System.out.println("\u001B[1mStep 2...\u001B[0m Following other accounts");
            gizemsungu.follow(atakankurt);
            gizemsungu.follow(beyzacam);
            gizemsungu.follow(bedoaksoy);
            gizemsungu.follow(savasyaldız);
            gizemsungu.follow(cansengun);
            gizemsungu.follow(efebayrak);
            gizemsungu.follow(yunuscacan);
            System.out.println();


            System.out.println("\u001B[1mStep 3...\u001B[0m Viewing sibelgulmez's posts' interactions...");
            gizemsungu.viewPostInteraction(sibelgulmez);
            System.out.println();


            System.out.println("\u001B[1mStep 4...\u001B[0m Unlike first post of sibelgulmez...");
            gizemsungu.unlike(sibelgulmez.getPosts().get(0));
            System.out.println();


            System.out.println("\u001B[1mStep 5...\u001B[0m Viewing sibelgulmez's posts' interactions...");
            gizemsungu.viewPostInteraction(sibelgulmez);
            System.out.println();


            System.out.println("\u001B[1mStep 6...\u001B[0m Block atakankurt, efebayrak, beyzacam...");
            gizemsungu.blockAccount(atakankurt);
            gizemsungu.blockAccount(efebayrak);
            gizemsungu.blockAccount(beyzacam);
            System.out.println();


            System.out.println("\u001B[1mStep 7...\u001B[0m Unblock efebayrak...");
            gizemsungu.unblockAccount(efebayrak);
            System.out.println();


            System.out.println("\u001B[1mStep 8...\u001B[0m Logout from gizemsungu...");
            gizemsungu.logout();
            System.out.println();


            System.out.println("\u001B[1mStep 9...\u001B[0m Login atakankurt...");
            atakankurt.login("atakankurt", 4);
            System.out.println();


            System.out.println("\u001B[1mStep 10...\u001B[0m Trying to view gizemsungu profile...");
            atakankurt.viewProfile(gizemsungu);
            System.out.println();


            System.out.println("\u001B[1mStep 11...\u001B[0m Log out from atakankurt...");
            atakankurt.logout();
            System.out.println();


            System.out.println("\u001B[1mStep 12...\u001B[0m Login efebayrak...");
            efebayrak.login("efebayrak", 9);
            System.out.println();


            System.out.println("\u001B[1mStep 13...\u001B[0m Try to view gizemsungu profile...");
            efebayrak.viewProfile(gizemsungu);
            System.out.println();


            System.out.println("\u001B[1mStep 14...\u001B[0m Viewing sibelgulmez post interactions...");
            efebayrak.viewPostInteraction(sibelgulmez);
            System.out.println();


            System.out.println("\u001B[1mStep 15...\u001B[0m Commenting on first post of sibelgulmez...");
            efebayrak.commentPost(sibelgulmez.getPosts().get(0), "WAOOOOOOOOOOOOOOOOV!");
            System.out.println();


            System.out.println("\u001B[1mStep 16...\u001B[0m View sibelgulmez post interactions...");
            efebayrak.viewPostInteraction(sibelgulmez);
            System.out.println();


            System.out.println("\u001B[1mStep 17...\u001B[0m Uncomment post...");
            efebayrak.uncommentPost(sibelgulmez.getPosts().get(0), 1);
            System.out.println();


            System.out.println("\u001B[1mStep 18...\u001B[0m View sibelgulmez post interactions...");
            efebayrak.viewPostInteraction(sibelgulmez);
            System.out.println();


            System.out.println("\u001B[1mStep 19...\u001B[0m Follow other accounts...");
            efebayrak.follow(atakankurt);
            efebayrak.follow(beyzacam);
            efebayrak.follow(bedoaksoy);
            efebayrak.follow(savasyaldız);
            efebayrak.follow(cansengun);
            efebayrak.follow(yunuscacan);
            System.out.println();


            System.out.println("\u001B[1mStep 20...\u001B[0m Unfollow bedoaksoy...");
            efebayrak.unfollow(bedoaksoy);
            System.out.println();


            System.out.println("\u001B[1mStep 21...\u001B[0m View profile of ourself...");
            efebayrak.viewProfile(efebayrak);
            System.out.println();


            System.out.println("\u001B[1mStep 22...\u001B[0m Share post...");
            Post post5 = new Post(efebayrak.getPostsNumber(), efebayrak.getUsername(), "I hate CSS!");
            efebayrak.sharePost(post5);
            System.out.println();


            System.out.println("\u001B[1mStep 23...\u001B[0m View action history...");
            efebayrak.displayActions();
            System.out.println();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        long endTime = System.nanoTime();
        float totalTime = endTime - startTime;
        System.out.println(totalTime/1000000000);
    }
}

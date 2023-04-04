package LinkedList.tests;

import LinkedList.main.account.Account;
import LinkedList.main.post.Post;
import LinkedList.main.message.Message;

public class Scenario2 {
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
        try {
            System.out.println("\u001B[1mStep 1...\u001B[0m Creating accounts...");
            Account gizemsungu = new Account(1, "gizemsungu", "01.01.1998", "Gebze");
            Account sibelgulmez = new Account(2, "sibelgulmez", "10.03.1995", "Istanbul");
            Account gokhankaya = new Account(3, "gokhankaya", "02.02.1994", "Mersin");
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
            System.out.println();
            gizemsungu.logout();

            System.out.println("\u001B[1m---------SCENARIO 2-----------\u001B[0m");
            System.out.println("\u001B[1mPart 1...\u001B[0m “gizemsungu” logs in.");
            gizemsungu.login("gizemsungu", 1);
            System.out.println();

            System.out.println("\u001B[1ma.\u001B[0m shares a post (let’s call it Post1).");
            Post Post1 = new Post(gizemsungu.getPostsNumber() + 1, gizemsungu.getUsername(), "My name is gizem sungu. This is my first post.");
            gizemsungu.sharePost(Post1);
            System.out.println();

            System.out.println("\u001B[1mb.\u001B[0m shares another post (let’s call it Post2).");
            Post Post2 = new Post(gizemsungu.getPostsNumber() + 1, gizemsungu.getUsername(), "Safranbolu has the best delight.");
            gizemsungu.sharePost(Post2);
            System.out.println();

            System.out.println("\u001B[1mc.\u001B[0m logs out.");
            gizemsungu.logout();
            System.out.println();

            System.out.println("\u001B[1mPart 2...\u001B[0m “sibelgulmez” logs in.");
            sibelgulmez.login("sibelgulmez", 2);
            System.out.println();

            System.out.println("\u001B[1ma.\u001B[0m views the profile of “gizemsungu”.");
            sibelgulmez.viewProfile(gizemsungu);
            System.out.println();

            System.out.println("\u001B[1mb.\u001B[0m likes Post1.");
            sibelgulmez.likePost(gizemsungu.getPosts().get(0));
            System.out.println();

            System.out.println("\u001B[1mc.\u001B[0m logs out.");
            sibelgulmez.logout();
            System.out.println();

            System.out.println("\u001B[1mPart 3...\u001B[0m “gokhankaya” logs in.");
            gokhankaya.login("gokhankaya", 3);
            System.out.println();

            System.out.println("\u001B[1ma.\u001B[0m views the profile of “gizemsungu”.");
            gokhankaya.viewProfile(gizemsungu);
            System.out.println();

            System.out.println("\u001B[1mb.\u001B[0m comments on Post2 (ex: Nice!”).");
            gokhankaya.commentPost(gizemsungu.getPosts().get(1), "Nice!");
            System.out.println();

            System.out.println("\u001B[1mc.\u001B[0m sends a message to “gizemsungu” (ex: “Hello!”)");
            Message message2 = new Message(gokhankaya.getMessageNumber(), gokhankaya.getUsername(), gizemsungu.getUsername(), "Hello!");
            gokhankaya.sendMessage(gizemsungu, message2);
            System.out.println();

            System.out.println("\u001B[1md.\u001B[0m logs out.");
            gokhankaya.logout();
            System.out.println();

            System.out.println("\u001B[1mPart 4...\u001B[0m “gizemsungu” logs in.");
            gizemsungu.login("gizemsungu", 1);
            System.out.println();

            System.out.println("\u001B[1ma.\u001B[0m views her own profile.");
            gizemsungu.viewProfile(gizemsungu);

            System.out.println("\u001B[1mb.\u001B[0m reads the message from “gokhankaya”.");
            gizemsungu.displayInbox();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endTime = System.nanoTime();
        float totalTime = endTime - startTime;
        System.out.println(totalTime/1000000000);
    }
}

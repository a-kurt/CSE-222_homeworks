package Tests;

import SecuritySystem.SecuritySystem;

public class Scenario1 {
    public static void main(String[] args) {
        SecuritySystem s1 = new SecuritySystem();
        int[] n = {4, 17, 29};

        // Test 1
        System.out.println("Test 1");
        s1.login("sibelgulmez", "[rac()ecar]", 74, n);

        // Test 2
        System.out.println("Test 2");
        s1.login("", "[rac()ecar]", 74, n);

        // Test 3
        System.out.println("Test 3");
        s1.login("sibel1", "[rac()ecar]", 74, n);

        // Test 4
        System.out.println("Test 4");
        s1.login("sibel", "pass[]", 74, n);

        // Test 5
        System.out.println("Test 5");
        s1.login("sibel", "abcdabcd", 74, n);

        // Test 6
        System.out.println("Test 6");
        s1.login("sibel", "[[[[]]]]", 74, n);

        // Test 7
        System.out.println("Test 7");
        s1.login("sibel", "[no](no)", 74, n);

        // Test 8
        System.out.println("Test 8");
        s1.login("sibel", "[rac()ecar]]", 74, n);

        // Test 9
        System.out.println("Test 9");
        s1.login("sibel", "[rac()ecars]", 74, n);

        // Test 10
        System.out.println("Test 10");
        s1.login("sibel", "[rac()ecar]", 5, n);

        // Test 11
        System.out.println("Test 11");
        s1.login("sibel", "[rac()ecar]", 35, n);
    }
}

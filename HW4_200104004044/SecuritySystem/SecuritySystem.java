package SecuritySystem;

import java.util.Stack;

/**
 * The SecuritySystem class that has methods for checking the validity of a username, password.
 * @author Atakan Kurt
 * @version 1.0.0
 */
public class SecuritySystem {

    /**
     * This method is used to log in the user with the given username and password information.
     * @param username a string value that represents the user's username
     * @param password1 a string value that represents the user's first password
     * @param password2 an integer value that represents the user's second password
     * @param denominations an array of integer values that represent the denominations used for password2 verification
     */
    public void login(String username, String password1, int password2, int [] denominations) {
        boolean control1 = this.checkIfValidUsername(username);
        boolean control2 = this.containsUserNameSpirit(username, password1);
        boolean control3 = this.isBalancedPassword(password1);
        boolean control4 = this.isPalindromePossible(password1);
        boolean control5 = this.isExactDivision(password2, denominations);
        if (password2 < 10 || password2 > 10000) {
            System.out.println("The integer password is invalid due it must be between 10 and 10000. Try again...");
            return;
        }
        if (!control5) System.out.println("The integer password is invalid due to no exact division. Try again...");
        if (control1 && control2 && control3 && control4 && control5) {
            System.out.println("\u001B[32mThe username and passwords are valid. The door is opening, please wait...\u001B[0m");
        }
    }

    /**
     * Checks if the given username is valid.
     * @param username the username to be checked
     * @return true if the username is valid, false otherwise
     */
    public boolean checkIfValidUsername(String username) {
        // base case
        if (username.length() < 1) {
            System.out.println("The username is invalid. Can not be empty. Try again...");
            return false;
        }
        if (!Character.isLetter(username.charAt(0))) {
            System.out.println("The username is invalid. It must contain only letters. Try again...");
            return false;
        }
        if (username.length() == 1) {
            return true;
        }
        String slicedUsername = username.substring(1);

        // recursive case
        return checkIfValidUsername(slicedUsername);
    }

    /**
     * Checks if the given password contains at least one letter from the username.
     * @param username the username to be checked
     * @param password1 the password to be checked
     * @return true if the password contains at least one letter from the username, false otherwise
     */
    public boolean containsUserNameSpirit(String username, String password1) {
        if (username == null || username.isEmpty()) {
            System.out.println("The username is invalid. It can't be empty or null. Try again...");
            return false;
        }
        if (password1 == null || password1.isEmpty()) {
            System.out.println("The string password is invalid. It cannot be empty or null. Try again...");
            return false;
        }

        Stack<Character> letters = new Stack<>();
        int bracketCount = 0;

        for (int i = 0; i < password1.length(); i++) {
            Character letter = password1.charAt(i);
            if (letter == '{' || letter == '}' || letter == '(' || letter == ')' || letter == '[' || letter == ']') {
                bracketCount++;
                letters.push(letter);
            }
            else if (!Character.isLetter(letter)) {
                System.out.println("The string password is invalid. It has non letter character in it. Try again...");
                return false;
            } else letters.push(letter);
        }
        if (bracketCount < 2) {
            System.out.println("The string password is invalid. Must contain at least 2 brackets. Try again...");
            return false;
        }
        if (password1.length() < 8) {
            System.out.println("The string password is invalid. Minimum length is 8. Try again...");
            return false;
        }

        while (!letters.isEmpty()) {
            Character letter = letters.pop();
            if (letter == '{' || letter == '}' || letter == '(' || letter == ')' || letter == '[' || letter == ']') {
                continue;
            } else if (username.contains(letter.toString())) {
                return true;
            }
        }
        System.out.println("The string password is invalid. It is not contains at least one letter of the username. Try again...");
        return false;
    }

    /**
     * Checks if the given password has balanced brackets.
     * @param password1 the password to be checked.
     * @return true if the password has balanced brackets, false otherwise.
     */
    public boolean isBalancedPassword(String password1) {
        if (password1 == null || password1.isEmpty()) {
            System.out.println("The string password is invalid. It cannot be empty or null. Try again...");
            return false;
        }
        Stack<Character> openBrackets = new Stack<>();

        for (int i = 0; i < password1.length(); i++) {
            char character = password1.charAt(i);
            if (character == '{' || character == '[' || character == '(') {
                openBrackets.push(character);
            } else if (character == '}' || character == ']' || character == ')') {
                if (openBrackets.isEmpty()) {
                    System.out.println("The string password is invalid due to invalid brackets. Try again...");
                    return false;
                }
                if (character == '}' && '{' == openBrackets.pop()) continue;
                else if (character == ')' && '(' == openBrackets.pop()) continue;
                else if (character == ']' && '[' == openBrackets.pop()) continue;
                else {
                    System.out.println("The string password is invalid due to invalid brackets. Try again...");
                    return false;
                }
            }
        }
        if (openBrackets.isEmpty()) {
            return true;
        } else {
            System.out.println("The string password is invalid due to invalid brackets. Try again...");
            return false;
        }
    }

    /**
     * Checks if a palindrome can be created from the given password.
     * @param password1 the password to be checked.
     * @return true if a palindrome can be created, false otherwise.
     */
    public boolean isPalindromePossible(String password1) {
        String newPassword1 = password1.replaceAll("[\\[\\](){}]","");
        if (newPassword1.length() <= 1) {
            return true;
        }
        char firstLetter = newPassword1.charAt(0);
        char secondLetter = newPassword1.charAt(1);
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < newPassword1.length(); i++) {
            if (newPassword1.charAt(i) == firstLetter) count++;
            else if (newPassword1.charAt(i) == secondLetter) count2++;
        }
        if (count % 2 == 0) {
            String newStr = newPassword1.replaceAll(Character.toString(firstLetter), "");
            return isPalindromePossible(newStr);
        } else if (count2 % 2 == 0) {
            String newStr = newPassword1.replaceAll(Character.toString(secondLetter), "");
            return isPalindromePossible(newStr);
        } else {
            System.out.println("The string password is invalid. No palindrome. Try again...");
            return false;
        }
    }

    /**
     * Checks if an integer can be exactly divided by a combination of denominations.
     * @param password2 the integer password to be checked for exact division.
     * @param denominations an array of denominations to be used for exact division.
     * @return true if the integer can be exactly divided by a combination of denominations, false otherwise.
     */
    public boolean isExactDivision(int password2, int [] denominations) {
        if (password2 < 0) return false;
        if (password2 == 0) return true;
        int[] psbArr = new int[denominations.length];
        for (int i = 0; i < psbArr.length; i++) {
            psbArr[i] = password2 - denominations[i];
            if (psbArr[i] == 0) return true;
        }
        for (int i = 0; i < psbArr.length; i++) {
            if (isExactDivision(psbArr[i], denominations)) {
                return true;
            }
        }
        return false;
    }
}

package SecuritySystem;

import java.util.Stack;

/**
 * The SecuritySystem class that has methods for checking the validity of a username, password.
 * @author Atakan Kurt
 * @version 1.0.0
 */
public class SecuritySystem {
    public void login(String username, String password1, int password2, int [] denominations) {
        boolean control1 = this.checkIfValidUsername(username);
        boolean control2 = this.containsUserNameSpirit(username, password1);
        boolean control3 = this.isBalancedPassword(password1);
        boolean control4 = this.isPalindromePossible(password1);
        boolean control5 = this.isExactDivision(password2, denominations);

        if (control1 && control2 && control3 && control4 && control5) {
            System.out.println("\u001B[32mThe username and passwords are valid. The door is opening, please wait...\u001B[0m");
        }
    }

    /**
     * Checks if the given username is valid.
     * @param username the username to be checked
     * @return true if the username is valid, false otherwise
     * @throws IllegalArgumentException if the username is empty or contains non-letter characters
     */
    public boolean checkIfValidUsername(String username) throws IllegalArgumentException {
        // base case
        if (username.length() < 1) throw new IllegalArgumentException("The username is invalid. Can not be empty. Try again...");
        if (!Character.isLetter(username.charAt(0))) throw new IllegalArgumentException("The username is invalid. It must contain only letters. Try again...");
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
     * @throws IllegalArgumentException if either the username or the password is null or empty, or it is not contains at least one letter from username.
     */
    public boolean containsUserNameSpirit(String username, String password1) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("The username is invalid. It can't be empty or null. Try again...");
        if (password1 == null || password1.isEmpty()) throw new IllegalArgumentException("The string password is invalid. It cannot be empty or null. Try again...");

        Stack<Character> letters = new Stack<>();
        for (int i = 0; i < password1.length(); i++)
            letters.push(password1.charAt(i));

        while (!letters.isEmpty()) {
            Character letter = letters.pop();
            if (letter == '{' || letter == '}' || letter == '(' || letter == ')' || letter == '[' || letter == ']') {
                continue;
            } else if (username.contains(letter.toString())) {
                return true;
            }
        }
        throw new IllegalArgumentException("The string password is invalid. It is not contains at least one letter of the username. Try again...");
    }

    /**
     * Checks if the given password has balanced brackets.
     * @param password1 the password to be checked.
     * @return true if the password has balanced brackets, false otherwise.
     * @throws IllegalArgumentException if the password is null or empty or has unbalanced brackets.
     */
    public boolean isBalancedPassword(String password1) throws IllegalArgumentException {
        if (password1 == null || password1.isEmpty()) throw new IllegalArgumentException("The string password is invalid. It cannot be empty or null. Try again...");
        Stack<Character> openBrackets = new Stack<>();

        for (int i = 0; i < password1.length(); i++) {
            char character = password1.charAt(i);
            if (character == '{' || character == '[' || character == '(') {
                openBrackets.push(character);
            } else if (character == '}' || character == ']' || character == ')') {
                if (openBrackets.isEmpty()) throw new IllegalArgumentException("The string password is invalid due to invalid brackets. Try again...");
                if (character == '}' && '{' == openBrackets.pop()) continue;
                else if (character == ')' && '(' == openBrackets.pop()) continue;
                else if (character == ']' && '[' == openBrackets.pop()) continue;
                else throw new IllegalArgumentException("The string password is invalid due to invalid brackets. Try again...");
            }
        }
        if (openBrackets.isEmpty()) {
            return true;
        } else throw new IllegalArgumentException("The string password is invalid due to invalid brackets. Try again...");
    }

    /**
     * Checks if a palindrome can be created from the given password.
     * @param password1 the password to be checked.
     * @return true if a palindrome can be created, false otherwise.
     * @throws IllegalArgumentException if the password is null or empty or no palindrome can be created.
     */
    public boolean isPalindromePossible(String password1) throws IllegalArgumentException { // TODO: Test this functions.
        if (password1 == null || password1.isEmpty()) throw new IllegalArgumentException("The string password is invalid. It cannot be empty or null. Try again...");
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
            throw new IllegalArgumentException("The string password is invalid. No palindrome. Try again...");
        }
    }

    /**
     * Checks if an integer can be exactly divided by a combination of denominations.
     * @param password2 the integer password to be checked for exact division.
     * @param denominations an array of denominations to be used for exact division.
     * @return true if the integer can be exactly divided by a combination of denominations, false otherwise.
     * @throws IllegalArgumentException if the integer password is negative or cannot be exactly divided by any combination of denominations.
     */
    public boolean isExactDivision(int password2, int [] denominations) throws IllegalArgumentException {
        if (password2 < 0) return false;
        if (password2 == 0) return true;
        int[] psbArr = new int[denominations.length];
        for (int i = 0; i < psbArr.length; i++) {
            psbArr[i] = password2 - denominations[i];
            if (psbArr[i] == 0) return true;
        }
        for (int i = 0; i < psbArr.length; i++) {
            if (isExactDivision(psbArr[i], denominations)) {
                System.out.println(psbArr[i]);
                return true;
            }
        }
        return false;
    }
}
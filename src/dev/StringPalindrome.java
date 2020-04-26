package dev;

import java.util.Scanner;

public class StringPalindrome {

    // if aabaa , should find out if the given string is pallindrome

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        scanner.close();

        reverse(string);
        boolean isPallindrome = palindrome(string);
        if(isPallindrome){
            System.out.println(" Pallindrome");
        }


    }

    private static void isPallinDrome(String pallindromeCheck) {
        char[] chars = pallindromeCheck.toCharArray();
        String reverseOrder = "";
        for (int i = chars.length - 1; i > (chars.length - 1) / 2; i--) {
            reverseOrder = reverseOrder + chars[i];
        }
        String forwardOrder = "";
        for (int i = 0; i < (chars.length) / 2; i++) {
            forwardOrder = forwardOrder + chars[i];
        }
        if (forwardOrder.equals(reverseOrder)) {
            System.out.println("PallinDrom " + forwardOrder + ", " + reverseOrder);
        } else {
            System.out.println("Not a pallindrome " + forwardOrder + ", " + reverseOrder);
        }


    }

    private static void reverse(String palString) {
        String reverse = "";
        char[] character = palString.toCharArray();
        for (int i = character.length - 1; i >= 0; i--) {
            reverse = reverse + character[i];
        }
        System.out.println(reverse);

    }

    private static boolean palindrome(String string) {
        boolean isPallinDrome =false;
        char[] ch = string.toCharArray();
        for (int i = 0; i < ch.length / 2; i++) {
            if (ch[i] != ch[ch.length - 1 - i]) {
                isPallinDrome = false;
            }
            else {
                isPallinDrome = true;
            }

        }
        return  isPallinDrome;
    }

}

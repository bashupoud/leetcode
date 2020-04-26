package dev;


public class PrimeNumber {
    public static void main(String[] args) {
        listOfPrimeNumber(10);

    }

    public static void listOfPrimeNumber(int n) {
        int num;
        String primeNumbers = "";
        for (int i = 0; i <= n; i++) {

            int counter = 0;
            for (num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                }
            }
            // Number should be divisible by either 1 or itself, just 2 times
            if (counter == 2) {
                primeNumbers = primeNumbers + i + ",";
            }
        }
        System.out.println(primeNumbers);

    }
}
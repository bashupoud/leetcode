package leetcode.stringmultiply;


public class StringMultiply {

    public static void main(String[] args) {
        StringMultiply stringMultiply = new StringMultiply();
        String num1 = "123";
        String num2 = "456";

        String product = stringMultiply.multiply(num1, num2);
        System.out.println("Product: " + product);

    }

    public String multiply(String num1, String num2) {
        String product = "";
        //If any of the two number is zero the product will be zer0
        if (num1.length() == 0 || num2.length() == 0) {
            return "0";
        }
        //Multiply the second number by the first one by one
        int n2 = num2.length();
        // Create a String Array to hold the numbers
        String[] strings = new String[n2];

        for (int i = 0; i < n2; i++) {
            //Pass one Character at a time
            char multiplyNumber = num2.charAt(n2 - 1 - i);
            /*
            First Iteration is a pure multiplication, but from the second iteration, we need to account for trailing zeros
            for adding purpose

                          1|2|3
                          3|2|1 //Keep in mind multiplication Carry
                          ------
                          1|2|3
                        2|4|6|0  // Addition Carry
                      3|6|9|0|0
                      ----------
                      3|9|4|8|3 =>39483
           */
            StringBuilder endingZeros = new StringBuilder();
            int k = i;
            while (k > 0) {
                endingZeros.append("0");
                k--;
            }

            strings[i] = multiplyTwoString(num1, multiplyNumber) + endingZeros.toString();
        }
        for (String s : strings) {
            System.out.println(s);

        }
        // At this point you hold all the number in String Arrays, need to add them all

        String sum = "0";
        for (int i = 0; i < strings.length; i++) {
            sum = addString(sum, strings[i]);

        }
        System.out.println();
        product = sum;

        // remove the leading zeros
        int zeros =0;
        while(zeros<product.length() && product.charAt(zeros)=='0'){
            zeros++;
        }
        StringBuilder sb = new StringBuilder(product);
        product = sb.replace(0,zeros,"").toString();
        if (product.equals("")){
            return "0";
        }

        return product;
    }

    private String addString(String num1, String num2) {
        // Get the length -1 for the indexing purpose
        int lengthNum1 = num1.length() - 1, lengthNum2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        char[] charArrayNum1 = num1.toCharArray();
        char[] charArrayNum2 = num2.toCharArray();
        while (lengthNum1 >= 0 || lengthNum2 >= 0) {
            // add backward
            int firstNumber = 0;
            int secondNumber = 0;
            if (lengthNum1 >= 0 && lengthNum2 >= 0) {
                firstNumber = charArrayNum1[lengthNum1] - '0';
                secondNumber = charArrayNum2[lengthNum2] - '0';
                lengthNum1--;
                lengthNum2--;
            } else if (lengthNum1 >= 0 && lengthNum2 < 0) {
                firstNumber = charArrayNum1[lengthNum1] - '0';
                lengthNum1--;

            } else if (lengthNum1 < 0 && lengthNum2 >= 0) {
                secondNumber = charArrayNum2[lengthNum2] - '0';
                lengthNum2--;
            }
            int sum = firstNumber + secondNumber + carry;
            carry = sum / 10;
            int remainder = sum % 10;
            sb.append(remainder);
        }
        if(carry!=0){
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    private String multiplyTwoString(String num1, char multiplyNumber) {
        int carry = 0;
        char[] charArrayNum1 = num1.toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = charArrayNum1.length - 1;
        while (length >= 0) {
            int firstNumber = charArrayNum1[length] - '0';
            int secondNumber = multiplyNumber - '0';
            length--;

            int product = firstNumber * secondNumber + carry;
            carry = product / 10;
            int remainder = product % 10;
            sb.append(remainder);
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();


    }
}



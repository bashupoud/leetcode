package test;

import java.util.*;

public class ArrayTest {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};  /// Primitive type,

        //addition(array);

        //doubleArray();
        int arr[] = {10, 5, 3, 4, 3, 5, 6};
         printFirstRepeating(arr);


    }


    public static void addition(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
            sum = sum + array[i]; // array[0] = 1;

        System.out.println("total : " + sum);

    }

    public static void doubleArray() {

        int[][] arr = {{1, 2}, {3, 4}};
        //int[][] arr = {{1, 2}, {3, 4}}; //{{{1,2},{2,3},{4,4}}};

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                System.out.println("arr[" + i + "][" + j + "] = " + arr[i][j]);
    }

    static void printFirstRepeating(int array[]) {
        // Initialize index of first repeating element
        int min = -1;
        // First Create a Empty Hash Set
        HashSet<Integer> set = new HashSet<>();
        // Traverse the input array from right to left
        for (int i = array.length - 1; i >= 0; i--) {
            // If element is already in hash set, update min
            if (set.contains(array[i]))
                min = i;

            else   // Else add element to hash set
                set.add(array[i]);
        }

        if (min != -1)
            System.out.println("The first repeating element is " + array[min]);
        else
            System.out.println("There are no repeating elements");
    }


   //COllection Framework
    public static void arrayListExample(){


        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
    }

    public static  void hashSetExample(){
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
    }

    public static  void hashMapExample(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"One");
        map.put(2,"One");
    }

    // LIST, MAP, SET







    /* find the first repeating element in an array of integers? (solution)
    Given an array of integers, find the first repeating element in it. We need to find the element that occurs more than once and whose index of the first occurrence is smallest.

    Examples:

    Input:  input [] = {10, 5, 3, 4, 3, 5, 6}
    Output: 5 [5 is the first element that repeats]
*/
}

package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class MainDish {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        MainDish mainDish = new MainDish();
        Stream<Dish> dishStream = mainDish.menu.stream();
        List<Integer> d = dishStream
                .filter(dish -> dish.getCalories() > 600)
                .map(Dish::getCalories)
                .collect(toList());
        System.out.println(d);

        List<Dish> vegetarianDishes =
                mainDish.menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        System.out.println(vegetarianDishes.toString());


        List<Integer> dishNameLengths = mainDish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);


        //Map
        Map<Integer, String> map = mainDish.menu
                .stream()
                .collect(Collectors
                        .toMap(Dish::getCalories, Dish::getName));
        System.out.println(map);


        // Flat Map
        List<String> list = Arrays.asList("Goodbye", "World");
        List<String> s = list.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        boolean isHealthy = mainDish.menu.stream()
                .allMatch(dw -> dw.getCalories() < 1000);
        System.out.println(isHealthy);


        // Counting
        long howManyDishes = mainDish.menu.stream().collect(Collectors.counting());
        System.out.println("How many Dishes :" + howManyDishes);

        // Create a comparator;
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = mainDish.menu.stream()
                .collect(maxBy(dishComparator));
        System.out.println("Most Calorie Dish: " + mostCalorieDish);

        // Summing: Collectors class has special collector to collect summing Int

        int totalCalories = mainDish.menu.stream().collect(summingInt(Dish::getCalories)); //--> this is collector

        //OR
        int totalCalories1 = mainDish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("TOTAL CAL: " + totalCalories1);

        // Int Summary Statistics
        IntSummaryStatistics menuStatistics = mainDish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        //Joining
        String shortMenu = mainDish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println("Short Menu: " + shortMenu);
        String shortMenuSeperated = mainDish.menu.stream().map(Dish::getName).collect(joining(" , "));
        System.out.println("Short Menu Seperated: " + shortMenuSeperated);

        // Reducing with 3 parameters
        int totalCaloriesreducing = mainDish.menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));

        System.out.println("Total Calorie reducing: " + totalCaloriesreducing);

        // Reducing with two params
        Optional<Dish> mostCalorieDishReducing = mainDish.menu.stream().collect(reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        System.out.println("MOST CALORIED WITH :" + mostCalorieDishReducing.get());

        // Grouping By
        Map<Dish.Type, List<Dish>> groupByDish = mainDish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(groupByDish);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = mainDish.menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        System.out.println("DISHES BY CALORIES: " + dishesByCaloricLevel);

        // Multi Level Grouping
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> multiLevelGrouping = mainDish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}
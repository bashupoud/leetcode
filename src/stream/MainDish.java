package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


/**
 * This example is taken from java 8 in action,
 * Purpose of this class is to display the different factory method of collector and stream
 */
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
        System.out.println("CALORIE GREATER THAN 600: " + d);

        List<Dish> vegetarianDishes =
                mainDish.menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        System.out.println("VEGETARIAN DISHES: " + vegetarianDishes.toString());


        List<Integer> dishNameLengths = mainDish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println("DISH NAME LENGTHS: " + dishNameLengths);


        //Map
        Map<Integer, String> map = mainDish.menu
                .stream()
                .collect(Collectors
                        .toMap(Dish::getCalories, Dish::getName));
        System.out.println("CALORIE and NAME: " + map);


        // Flat Map
        List<String> list = Arrays.asList("Goodbye", "World");
        List<String> s = list.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        boolean isHealthy = mainDish.menu.stream()
                .allMatch(dw -> dw.getCalories() < 1000);
        System.out.println("HEALTHY: " + isHealthy);


        // Counting
        long howManyDishes = mainDish.menu.stream().collect(Collectors.counting());
        System.out.println("HOW MANY DISHES :" + howManyDishes);

        // Create a comparator;
        Comparator<Dish> dishComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = mainDish.menu.stream()
                .collect(maxBy(dishComparator));
        System.out.println("MOST CALORIE DISH: " + mostCalorieDish);

        // Summing: Collectors class has special collector to collect summing Int

        int totalCalories = mainDish.menu.stream().collect(summingInt(Dish::getCalories)); //--> this is collector
        System.out.println("TOTAL CAL: " + totalCalories);

        //OR
        int totalCalories1 = mainDish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("TOTAL CAL: " + totalCalories1);

        // Int Summary Statistics
        IntSummaryStatistics menuStatistics = mainDish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("MENU STATISTICS :" + menuStatistics);

        //Joining
        String shortMenu = mainDish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println("SHORT MENU " + shortMenu);
        String shortMenuSeperated = mainDish.menu.stream().map(Dish::getName).collect(joining(" , "));
        System.out.println("SHORT MENU SEPERATED: " + shortMenuSeperated);

        // Reducing with 3 parameters
        int totalCaloriesreducing = mainDish.menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));

        System.out.println("TOTAL CALORIE REDUCING: " + totalCaloriesreducing);

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

        //Collecting data in subgroups, for multilevel grouping second collector can be of any type, in this case we are using counting
        Map<Dish.Type, Long> typesCount = mainDish.menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println("COLLECTING DATE IN SUBGROUPS: " + typesCount);


        //GROUPING
        Map<Dish.Type, Dish> mostCaloricByType = mainDish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("MOST CAL BY TYPE: " + mostCaloricByType);


        Map<Dish.Type, Integer> totalCaloriesByType = mainDish.menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
        System.out.println("TOTAL CALORIES BY TYPE: " + totalCaloriesByType);

        //
        Map<Dish.Type, Set<CaloricLevel>> calorieLevelByType = mainDish.menu.stream()
                .collect(groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else return CaloricLevel.FAT;

                        }, toSet())));

        System.out.println("CALORIE LEVEL BY TYPE  :" + calorieLevelByType);


        Map<Dish.Type, Set<CaloricLevel>> calorieLevelByTypetoCollection = mainDish.menu.stream()
                .collect(groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else return CaloricLevel.FAT;

                        }, toCollection(HashSet::new))));
        System.out.println("calorieLevelByTypetoCollection : "+ calorieLevelByTypetoCollection);


    }


    public enum CaloricLevel {DIET, NORMAL, FAT}
}

/**
 * OUTPUT
 * CALORIE GREATER THAN 600: [800, 700]
 * VEGETARIAN DISHES: [french fries, rice, season fruit, pizza]
 * DISH NAME LENGTHS: [4, 4, 7, 12, 4, 12, 5, 6, 6]
 * CALORIE and NAME: {400=chicken, 800=pork, 450=salmon, 530=french fries, 550=pizza, 120=season fruit, 300=prawns, 700=beef, 350=rice}
 * HEALTHY: true
 * HOW MANY DISHES :9
 * MOST CALORIE DISH: Optional[pork]
 * TOTAL CAL: 4200
 * TOTAL CAL: 4200
 * MENU STATISTICS :IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
 * SHORT MENU porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
 * SHORT MENU SEPERATED: pork , beef , chicken , french fries , rice , season fruit , pizza , prawns , salmon
 * TOTAL CALORIE REDUCING: 4200
 * MOST CALORIED WITH :pork
 * {FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken]}
 * DISHES BY CALORIES: {FAT=[pork], DIET=[chicken, rice, season fruit, prawns], NORMAL=[beef, french fries, pizza, salmon]}
 * COLLECTING DATE IN SUBGROUPS: {FISH=2, OTHER=4, MEAT=3}
 * MOST CAL BY TYPE: {FISH=salmon, OTHER=pizza, MEAT=pork}
 * TOTAL CALORIES BY TYPE: {FISH=750, OTHER=1550, MEAT=1900}
 * CALORIE LEVEL BY TYPE  :{FISH=[DIET, NORMAL], OTHER=[NORMAL, DIET], MEAT=[FAT, NORMAL, DIET]}
 * calorieLevelByTypetoCollection : {FISH=[DIET, NORMAL], OTHER=[NORMAL, DIET], MEAT=[FAT, NORMAL, DIET]}
 */
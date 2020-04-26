package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class MainTrader {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        MainTrader mainTrader = new MainTrader();
        //Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transactions1 = mainTrader.transactions
                .stream()
                .sorted((comparing(Transaction::getValue)))
                .collect(Collectors.toList());
        System.out.println(transactions1.toString());

        System.out.println("=============================");
        System.out.println();

        //What are all the unique cities where the traders work?

        List<String> transactions2 = mainTrader.transactions
                .stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(transactions2.toString());

        System.out.println("=============================");
        System.out.println();

        //Find all traders from Cambridge and sort them by name.
        List<Trader> traders = mainTrader.transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders.toString());
        System.out.println("=============================");

        //Return a string of all traders’ names sorted alphabetically.
        List<String> tradersName = mainTrader.transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(tradersName.toString());

        System.out.println("=============================");
        System.out.println();

        //Return a string of all traders’ names sorted alphabetically

        String string = mainTrader.transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s, s2) -> s + s2);
        System.out.println("concat String: " + string);

        System.out.println("=============================");
        System.out.println();

        //Are any traders based in Milan?

        boolean isMilanBased = mainTrader.transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("Milan Based: " + isMilanBased);
        System.out.println("=============================");
        System.out.println();

        //Print all transactions’ values from the traders living in Cambridge.
        List<Integer> value = mainTrader.transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        System.out.println("Value: "+ value.toString());
        System.out.println("=============================");
        System.out.println();

        mainTrader.transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("=============================");
        System.out.println();

        //What’s the highest value of all the transactions?
        Optional<Integer> max= mainTrader.transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("Maximum: "+max);

        //Find the smallest value of transaction

        Integer min= mainTrader.transactions.stream().map(Transaction::getValue).reduce(Integer::min).get();

        System.out.println("Minimum: "+min);

        System.out.println("=============================");
        System.out.println();

        //Find the transaction with the smallest value.

      Optional<Transaction> smallestTransaction = mainTrader.transactions
                .stream()
                .reduce((s, transaction) -> s.getValue() < transaction.getValue() ? s : transaction);
        System.out.println("Smallest Transaction: "+ smallestTransaction.toString());

        System.out.println("=============================");
        System.out.println();

        Optional<Transaction> minTransaction = mainTrader.transactions.stream().min(comparing(Transaction::getValue));
        System.out.println(minTransaction.toString());


        System.out.println("=============================");
        System.out.println();





    }
}

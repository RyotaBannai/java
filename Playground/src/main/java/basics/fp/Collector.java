package basics.fp;

import basics.fp.JavaBean.Person;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Collector {
    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );

        /**
         * Collect's arguments...
         * supplier/factory：make Container
         * accumulator: way to add item to Container
         * combiner: way to combine Container with another container.
         **/
        List<Person> olderThan20 = people.stream()
                .filter(person -> person.getAge() > 20)
//                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                .collect(toList()); // convenient method.
        System.out.println("Older then 20:" + olderThan20);


        Map<Integer, List<Person>> peopleByAge =
                people.stream()
                        .collect(groupingBy(Person::getAge));
        System.out.println("Group by age:" + peopleByAge);

        // グルーピングした結果を操作する
        Map<Integer, List<String>> peopleNameByAge =
                people.stream()
                        .collect(
                                groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println("Group by age and list their name:" + peopleNameByAge);


        // grouping and reducing.
        Comparator<Person> byAge = comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter:");
        System.out.println(oldestPersonOfEachLetter);
    }

}

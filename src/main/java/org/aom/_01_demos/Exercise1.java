package org.aom._01_demos;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here
        StreamSources.intNumbersStream().forEach(System.out::println);
        System.out.println("--------------------");

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here
        StreamSources.intNumbersStream()
                .filter(num -> num < 5)
                .forEach(System.out::println);
        System.out.println("--------------------");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here
        StreamSources.intNumbersStream()
                .filter(num -> num > 5)
                .skip(1) // Skip the first number
                .limit(2) // Take the next two numbers
                .forEach(System.out::println);
        System.out.println("--------------------");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        int firstGreaterThanFive = StreamSources.intNumbersStream()
                .filter(num -> num > 5)
                .findFirst()
                .orElse(-1);
        System.out.println(firstGreaterThanFive);
        System.out.println("--------------------");

        // Print first names of all users in userStream
        // TODO: Write code here
        StreamSources.userStream()
                .map(User::getFirstName)
                .forEach(System.out::println);
        System.out.println("--------------------");

        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here
/*        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream()
                        .anyMatch(num -> num == user.getId()))
                .map(User::getFirstName)
                .forEach(System.out::println);*/
        System.out.println("--------------------");

        // Alternative solution using flatMap
        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream()
                        .filter(user -> user.getId() == id))
                .map(User::getFirstName)
                .forEach(System.out::println);
        System.out.println("--------------------");

        // Print last names of all users in userStream that have IDs from number stream. Get distinct users.
        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream()
                        .filter(user -> user.getId() == id))
                .map(User::getLastName)
                .distinct() // Ensure distinct last names
                .forEach(System.out::println);
        System.out.println("--------------------");
    }
}

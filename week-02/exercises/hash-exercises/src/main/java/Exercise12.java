import learn.Vehicle;

import java.util.HashSet;
import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> numbers = new HashSet<>();


        // 1. Generate 10 unique random numbers between 0 and 100.
        for (int i = 0; numbers.size() < 10; i++) {
            int newNum = (int) Math.floor(Math.random()*101);
            numbers.add(newNum);
        }
        // 2. Print the numbers to the console.
        // (Hint: You can add random numbers to the `numbers` HashSet until its size is 10.
        // That guarantees the numbers are unique.)

        for (int n : numbers) {
            System.out.println(n);
        }
    }

    }


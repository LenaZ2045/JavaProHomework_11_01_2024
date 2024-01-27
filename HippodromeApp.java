package com.proftelran.org.lesson27.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HippodromeApp {

    public static final int LOOP_LENGTH = 1000;

    public static final Map<Horse, Long> resultMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Random random = new Random();
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            horses.add(new Horse("H-" + i, random.nextInt(5)));
        }

// the line below is another way how we can write the code for lines 26 -to- 28
//        horses.stream().map(horse -> new Thread(horse)).collect(Collectors.toList()).stream().forEach(Thread -> Thread.start());
        List<Thread> threads = new ArrayList<>();
        horses.forEach(horse -> threads.add(new Thread(horse)));
        threads.forEach(Thread::start);

        boolean hasWinner = false;
        while (!hasWinner) {
            for (Horse horse : horses) {
                horse.nextStep();
                if (horse.getPosition() > LOOP_LENGTH) {
                    hasWinner = true;
                    System.out.println("Horse " + horse.getName() + " is finished");
                    break;
                }
            }
        }

        for (Horse horse : horses) {
            System.out.println("Horse " + horse.getName() + " position " + horse.getPosition());
        }
    }
}
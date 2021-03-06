package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class Turnstile {

    public static void main(String[] args) {

        int time[] = {0, 0, 1, 5};
        List<Integer> times = Arrays.stream(time).boxed().collect(Collectors.toList());
        int direction[] = {0, 1, 1, 0};
        List<Integer> directions = Arrays.stream(time).boxed().collect(Collectors.toList());
        int n = 4;

        TurnstileSolution turnstileSolution = new TurnstileSolution();

//        List<Integer> persons = turnstileSolution.solve(n, times, directions);
//        System.out.println("# ");
//        persons.forEach(i-> System.out.println(" " + i.toString()));

        int arr[] = turnstileSolution.getTimes(n, time, direction);
        System.out.println(Arrays.toString(arr));
    }

}

class TurnstileSolution {
/*
    public List<Integer> solve(int n, List<Integer> times, List<Integer> directions) {

        int[] time = new int[n];

        // build the 2 queues
        List<Queue<List<Integer>>> queues = new ArrayList<>();
        queues.add(new LinkedList<>());
        queues.add(new LinkedList<>());

        //covert list to arr
        int arrTimes[] = new int[times.size()];
        for (int i = 0; i < times.size(); i++) {
            arrTimes[i] = times.get(i);
        }

        for(int i = 0; i < n; i++)
            queues.get(directions.get(i)).add(Arrays.asList(arrTimes[i], i));

        // compare elements at the top to decide which queue is to process
        int currentTime = 0,
                priority = 1;
        while(!queues.get(0).isEmpty() || !queues.get(1).isEmpty()) {
            Integer time0 = queues.get(0).isEmpty() ? Integer.MAX_VALUE : queues.get(0).peek().get(0),
                    time1 = queues.get(1).isEmpty() ? Integer.MAX_VALUE : queues.get(1).peek().get(0);
            Integer faster = Math.min(time0, time1);

            // reset the priority
            if(faster - currentTime > 1)
                priority = 1;

            currentTime = Math.max(currentTime, faster);

            int comp = time0.compareTo(time1);
            int queueNumber = (comp == - 1 || comp == 0 && priority == 0) ? 0 : 1;

            do {
                List<Integer> current = queues.get(queueNumber).poll();
                time[current.get(1)] = currentTime;
                currentTime++;
            } while (!queues.get(queueNumber).isEmpty() && queues.get(queueNumber).peek().get(0) <= currentTime);

            priority = queueNumber;
        }

        List<Integer> result = Arrays.stream(time).boxed().collect(Collectors.toList());
        return result;

    }
    */

    /*
    Just maintain 2 queues, one for incoming and one for outgoing.
    Process both the elements at the front of the queue simulatenously
    and see which one goes in/out first according to the rules.
    */

    public int[] getTimes(int n, int[] arrTime, int[] direction) {

        if (arrTime == null || direction == null)
            return new int[0]; //?

        int[] times = new int[n];

        // build the 2 queues
        List<Queue<List<Integer>>> queues = new ArrayList<>();
        queues.add(new LinkedList<>()); //0 in
        queues.add(new LinkedList<>()); //1 out
        for(int i = 0; i < n; i++) {
            queues.get(direction[i]).add(Arrays.asList(arrTime[i], i));
            //System.out.println(" " + Arrays.toString(queues.toArray()));
        }

        // compare elements at the top to decide which queue is to process
        int currentTime = 0, priority = 1;

        while(!queues.get(0).isEmpty() || !queues.get(1).isEmpty()){
            Integer time0 = queues.get(0).isEmpty() ? Integer.MAX_VALUE : queues.get(0).peek().get(0),
                    time1 = queues.get(1).isEmpty() ? Integer.MAX_VALUE : queues.get(1).peek().get(0);
            Integer faster = Math.min(time0, time1);

            // reset the priority
            if(faster - currentTime > 1)
                priority = 1;

            currentTime = Math.max(currentTime, faster);

            int comp = time0.compareTo(time1);
            int queueNumber = (comp == - 1 || comp == 0 && priority == 0) ? 0 : 1;

            do {
                List<Integer> current = queues.get(queueNumber).poll();
                times[current.get(1)] = currentTime;
                currentTime++;
            } while (!queues.get(queueNumber).isEmpty() && queues.get(queueNumber).peek().get(0) <= currentTime);

            priority = queueNumber;
        }

        return times;
    }
}

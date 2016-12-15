package com.company;

/**
 * Created by hmaduri on 5/30/16.
 */
public class MinimumTimeSkippingOfTasks {
    public static void main(String[] args) {
//        int[] tasks = {10,5,7,10};
//        int[] tasks = {10};
//        int[] tasks = {10,30};
        int[] tasks={10,5,2,4,8,6,7,10};
        int minTimeToFinishTasks = minimumTimeToExecTasks(tasks.length-1, tasks, true);
        System.out.println(" minimum time to finish tasks:" + minTimeToFinishTasks);
    }

    private static int minimumTimeToExecTasks(int size, int[] tasks, boolean skippable) {
        if (size < 0 )
            return 0;
        int minimumTimeToExecuteTasks = Integer.MAX_VALUE;
        if(skippable)
            minimumTimeToExecuteTasks = minimumTimeToExecTasks(size-1,tasks,false);
        minimumTimeToExecuteTasks = Integer.min(minimumTimeToExecuteTasks,
                tasks[size] + minimumTimeToExecTasks(size-1,tasks,true));
        return minimumTimeToExecuteTasks;
    }
}

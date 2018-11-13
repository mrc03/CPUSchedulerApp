package mrc.osscheduler;

import android.util.Log;

/**
 * Created by HP on 13-11-2018.
 */

public class FCFS {

    FCFS() {

    }

    int[] findWaitingTime(int n, int bt[], int at[]) {
        int wt[] = new int[n];
        int service_time[] = new int[n];
        service_time[0] = 0;
        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            service_time[i] = service_time[i - 1] + bt[i - 1];
            wt[i] = service_time[i] - at[i];
            if (wt[i] < 0)
                wt[i] = 0;
        }

        return wt;
    }

    int[] findTurnAroundTime(int n, int bt[],
                             int wt[]) {
        // Calculating turnaround time by adding bt[i] + wt[i]
        int tat[]=new int [n];
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
        return tat;
    }



}


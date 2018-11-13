package mrc.osscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mPid;
    private EditText mBurstTime;
    private EditText mArrTime;
    private RadioGroup radioGroup;

    public ArrayList<Integer> pids = new ArrayList<>();
    public ArrayList<Integer> bur_times = new ArrayList<>();
    public ArrayList<Integer> arr_times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPid = findViewById(R.id.pid);
        mBurstTime = findViewById(R.id.burst_time);
        mArrTime = findViewById(R.id.arrival_time);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_processes:
                if (!TextUtils.isEmpty(mPid.getText()) && !TextUtils.isEmpty(mBurstTime.getText()) &&
                        !TextUtils.isEmpty(mArrTime.getText())) {
                    int p = Integer.parseInt(mPid.getText().toString());
                    int b = Integer.parseInt(mBurstTime.getText().toString());
                    int a = Integer.parseInt(mArrTime.getText().toString());

                    pids.add(p);
                    bur_times.add(b);
                    arr_times.add(a);

                    mPid.setText("");
                    mBurstTime.setText("");
                    mArrTime.setText("");


                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Complete Details", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.submit:

//                System.out.println(pids);
//                System.out.println(arr_times);
//                System.out.println(bur_times);

                int[] pid_arr = new int[pids.size()];
                int[] bur_arr = new int[bur_times.size()];
                int[] arrv_arr = new int[arr_times.size()];

                for (int i = 0; i < pids.size(); i++) {
                    pid_arr[i] = pids.get(i);
                }

                for (int i = 0; i < bur_times.size(); i++) {
                    bur_arr[i] = bur_times.get(i);
                }

                for (int i = 0; i < arr_times.size(); i++) {
                    arrv_arr[i] = arr_times.get(i);
                }


                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select the scheduling algorithm", Toast.LENGTH_SHORT).show();
                } else {
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    Log.e("wdd", "" + selectedId);
                    switch (selectedId) {
                        case R.id.radioButton1:
                            // sjf
                            SJF sjf = new SJF();
                            int[] sjf_wt = sjf.findWaitingTime(pid_arr, bur_arr, arrv_arr, bur_times.size());
                            int[] sjf_turn = sjf.findTurnAroundTime(bur_arr, bur_times.size(), sjf_wt);

                            Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                            resultIntent.putExtra("waiting time", sjf_wt);
                            resultIntent.putExtra("turn_around_time", sjf_turn);
                            resultIntent.putExtra("burst time", bur_arr);
                            resultIntent.putExtra("pids", pid_arr);
                            resultIntent.putExtra("algo", "Shortest Job First (SJF)");
                            startActivity(resultIntent);

                            break;

                        case R.id.radioButton2:
                            RoundRobin rr = new RoundRobin();
                            int[] rr_wt = rr.findWaitingTime(pid_arr, bur_arr, arrv_arr, bur_times.size());
                            int[] rr_turn = rr.findTurnAroundTime(bur_arr, bur_times.size(), rr_wt);

                            Intent resultIntent1 = new Intent(MainActivity.this, ResultActivity.class);
                            resultIntent1.putExtra("waiting time", rr_wt);
                            resultIntent1.putExtra("turn_around_time", rr_turn);
                            resultIntent1.putExtra("burst time", bur_arr);
                            resultIntent1.putExtra("pids", pid_arr);
                            resultIntent1.putExtra("algo", "RoundRobin");
                            startActivity(resultIntent1);
                            break;
                        case R.id.radioButton3:
                            // fcfs
                            FCFS fcfs = new FCFS();
                            int[] fcfs_wt = fcfs.findWaitingTime(bur_times.size(), bur_arr, arrv_arr);
                            int[] fcfs_turn = fcfs.findTurnAroundTime(bur_times.size(), bur_arr, fcfs_wt);

                            Intent resultIntent2 = new Intent(MainActivity.this, ResultActivity.class);
                            resultIntent2.putExtra("waiting time", fcfs_wt);
                            resultIntent2.putExtra("turn_around_time", fcfs_turn);
                            resultIntent2.putExtra("burst time", bur_arr);
                            resultIntent2.putExtra("pids", pid_arr);
                            resultIntent2.putExtra("algo", "First Come First Serve (FCFS)");
                            startActivity(resultIntent2);
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "bfwe", Toast.LENGTH_SHORT).show();


                    }

                }


        }
    }
}

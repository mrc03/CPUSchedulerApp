package mrc.osscheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView mAlgo;
    private TextView mPid;
    private TextView mBurst;
    private TextView mWait;
    private TextView mTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAlgo = findViewById(R.id.result_algo_text_view);
        mPid = findViewById(R.id.result_act_pid);
        mBurst = findViewById(R.id.result_act_burst_time);
        mWait = findViewById(R.id.result_act_wait_time);
        mTurn = findViewById(R.id.result_act_turn_time);


        Bundle extras = getIntent().getExtras();

        int[] wt = extras.getIntArray("waiting time");
        int[] tat = extras.getIntArray("turn_around_time");
        int[] pids = extras.getIntArray("pids");
        int[] bt = extras.getIntArray("burst time");
        String algo = extras.getString("algo");

        int n = wt.length;

        mAlgo.setText(algo);

        for (int i = 0; i < pids.length; i++) {
            mPid.setText(mPid.getText() + "\n" + pids[i]);

            mBurst.setText(mBurst.getText() + "\n" + bt[i]);
            mWait.setText(mWait.getText() + "\n" + wt[i]);
            mTurn.setText(mTurn.getText() + "\n" + tat[i]);

        }


    }
}

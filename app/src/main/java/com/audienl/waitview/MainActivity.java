package com.audienl.waitview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.audienl.wait_view_core.WaitView;

public class MainActivity extends AppCompatActivity {

    private WaitView mWaitView;
    private Button mBtnToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaitView = (WaitView) findViewById(R.id.wait_view);

        mBtnToggle = (Button) findViewById(R.id.btn_toggle);
        mBtnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWaitView.isAnimatorStarted()) {
                    mWaitView.stopAnimator();
                } else {
                    mWaitView.startAnimator();
                }
            }
        });
    }
}

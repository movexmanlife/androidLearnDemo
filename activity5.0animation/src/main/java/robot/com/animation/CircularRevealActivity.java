package robot.com.animation;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import robot.com.animation.R;

public class CircularRevealActivity extends AppCompatActivity implements View.OnClickListener {
    private View secondView;
    private View secondViewBg;
    private FloatingActionButton floatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        secondViewBg = findViewById(R.id.secondBg);
        secondView = findViewById(R.id.second);
        floatBtn = (FloatingActionButton) findViewById(R.id.floatBtn);
        floatBtn.setOnClickListener(this);
        secondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "secondView", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v) {
        secondView.setVisibility(View.VISIBLE);
        /**
         * 这里求出控件中心点的位置
         */
        int centerX = (v.getLeft() + v.getRight()) / 2;
        int centerY = (v.getTop() + v.getBottom()) / 2;

        /**
         * 这里求斜边，其实就是控件的中心店到父控件的左上角的那个点的距离。
         */
        float finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
        Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
                secondView, centerX, centerY, 0, finalRadius);

        mCircularReveal.setDuration(400).start();
    }


}

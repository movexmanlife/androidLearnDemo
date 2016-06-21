package robot.com.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import robot.com.animation.R;

public class WindowTransitionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_transitions);
    }

    public void onCellClick(View v) {
        Intent intent = new Intent(this, SecondWindowTransActivity.class);
        /**
         * 如果仅仅使用startActivity(intent)是不会有动画效果的。
         */
        /**
         * TODO!!!在第二个Activity中需要使用getWindow()和setExitTransition()、setEnterTransition()进行设置。
         */
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
}

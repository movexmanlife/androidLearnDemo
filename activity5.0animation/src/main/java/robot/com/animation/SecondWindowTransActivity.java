package robot.com.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import robot.com.animation.R;

public class SecondWindowTransActivity extends AppCompatActivity {

    /**
     * java.lang.RuntimeException: Unable to start activity ComponentInfo java.lang.IllegalArgumentException: Invalid slide direction
     * 将end改为right才行
     *
     * <slide xmlns:android="http://schemas.android.com/apk/res/android"
     android:interpolator="@android:interpolator/decelerate_cubic"
     android:slideEdge="right" />

     参考网址：
     https://github.com/lgvalle/Material-Animations/issues/17
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_second_window_trans);
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
    }
}

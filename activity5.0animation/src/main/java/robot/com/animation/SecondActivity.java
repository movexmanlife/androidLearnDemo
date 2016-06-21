package robot.com.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import robot.com.animation.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition explode = TransitionInflater.from(this).
                inflateTransition(R.transition.explode);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_second);

        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
    }
}

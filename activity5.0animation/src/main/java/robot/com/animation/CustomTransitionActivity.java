package robot.com.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import robot.com.animation.R;

public class CustomTransitionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, SecondCustomTransActivity.class);

        /**
         * TODO!!!为什么这里R.layout.activity_custom_transition不加android:transitionName="transition_morph_view"是可以的。
         *
         * TODO!!!第二个SecondCustomTransActivity中的layout中View必须加上这个属性android:transitionName="transition_morph_view"才行。
         */
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (this, v, "transition_morph_view");
        startActivity(intent, options.toBundle());
    }
}

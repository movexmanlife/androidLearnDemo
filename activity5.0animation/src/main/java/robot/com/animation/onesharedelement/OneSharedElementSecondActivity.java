package robot.com.animation.onesharedelement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import robot.com.animation.R;
import robot.com.animation.SecondShareElemActivity;

/**
 * Created by Administrator on 2016/6/21.
 */
public class OneSharedElementSecondActivity extends AppCompatActivity {
    private View firstSharedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_shared_element_second);
        getSupportActionBar().setTitle("第二个Activity");

        firstSharedView = findViewById(R.id.firstSharedView);
        firstSharedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }
}

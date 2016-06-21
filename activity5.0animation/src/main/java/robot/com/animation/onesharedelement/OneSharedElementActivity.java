package robot.com.animation.onesharedelement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import robot.com.animation.R;
import robot.com.animation.SecondShareElemActivity;

/**
 * Created by Administrator on 2016/6/21.
 */
public class OneSharedElementActivity extends AppCompatActivity {
    private View firstSharedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_shared_element);
        getSupportActionBar().setTitle("第一个Activity");

        firstSharedView = findViewById(R.id.firstSharedView);
        firstSharedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }


    public void test() {
        Intent intent = new Intent(this, OneSharedElementSecondActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, firstSharedView, "coding").toBundle());
    }
}

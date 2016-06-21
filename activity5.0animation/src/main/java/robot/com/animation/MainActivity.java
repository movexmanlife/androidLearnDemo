package robot.com.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import robot.com.animation.R;
import robot.com.animation.onesharedelement.OneSharedElementActivity;

public class MainActivity extends AppCompatActivity {
    private Button circularRevealBtn;
    private Button windowTransitionsActivity;
    private Button sharedElement;
    private Button custom;
    private Button oneshared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circularRevealBtn = (Button) findViewById(R.id.circularRevealBtn);
        windowTransitionsActivity = (Button) findViewById(R.id.windowTransitionsActivity);
        sharedElement = (Button) findViewById(R.id.sharedElement);
        custom = (Button) findViewById(R.id.custom);
        oneshared = (Button) findViewById(R.id.oneshared);
    }

    public void onClick(View v) {
        if (v == circularRevealBtn) {
            Intent intent = new Intent(this, CircularRevealActivity.class);
            startActivity(intent);
        } else if (v == windowTransitionsActivity) {
            Intent intent = new Intent(this, WindowTransitionsActivity.class);
            startActivity(intent);
        } else if (v == sharedElement) {
            Intent intent = new Intent(this, SharedElementActivity.class);
            startActivity(intent);
        } else if (v == custom) {
            Intent intent = new Intent(this, CustomTransitionActivity.class);
            startActivity(intent);
        } else if (v == oneshared) {
            Intent intent = new Intent(this, OneSharedElementActivity.class);
            startActivity(intent);
        }
    }
}
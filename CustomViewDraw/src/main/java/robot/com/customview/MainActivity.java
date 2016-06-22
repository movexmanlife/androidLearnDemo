package robot.com.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_my_view);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent = new Intent(this, DrawPathActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                gotoDragBubble();
                break;
        }
    }

    private void gotoDragBubble() {
        Intent intent = new Intent(this, DragBubbleActivity.class);
        startActivity(intent);
    }
}

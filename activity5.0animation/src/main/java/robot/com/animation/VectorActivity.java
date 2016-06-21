package robot.com.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import robot.com.animation.R;

public class VectorActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        for (final Drawable drawable : tv.getCompoundDrawables()) {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        }
        return super.onTouchEvent(event);
    }
}

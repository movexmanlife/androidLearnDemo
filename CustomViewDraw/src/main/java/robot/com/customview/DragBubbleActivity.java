package robot.com.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DragBubbleActivity extends AppCompatActivity {
    DragBubbleView bubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_bubble_view);
    }
}

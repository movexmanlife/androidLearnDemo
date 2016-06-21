package robot.com.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import robot.com.animation.R;

public class SecondShareElemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_share_elem);
        getSupportActionBar().setTitle("第二个Activity");
    }

    /**
     * TODO!!!为何点击结束之后没有共享元素的反向动画？？？
     *
     * 而按下back键会有共享元素的反向动画。
     */
    public void onClick(View v) {
        finish();
    }

    /**
     * 解决办法就是调用finishAfterTransition();
     */
}

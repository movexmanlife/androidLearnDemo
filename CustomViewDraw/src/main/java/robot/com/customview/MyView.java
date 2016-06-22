package robot.com.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * android:paddingLeft="20dp"
         * android:paddingTop="80dp"
         * android:paddingRight="50dp"
         * android:paddingBottom="40dp"
         */
        // 相对应的。
        int paddingLeft = getPaddingLeft(); // 40
        int paddingTop = getPaddingTop(); // 160
        int paddingRight = getPaddingRight(); // 100
        int paddingBottom = getPaddingBottom(); // 80

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        /**
         * 这个是忽略padding进行绘制。
         * canvas.drawLine(0, 0, getWidth(), getHeight(), mPaint);
         */

        // TODO!!!这里就是加上padding值进行绘制
        // TODO!!!绘制的起点和终点算出来
        canvas.drawLine(paddingLeft, paddingTop, getWidth() - paddingRight, getHeight() - paddingBottom, mPaint);
    }

}

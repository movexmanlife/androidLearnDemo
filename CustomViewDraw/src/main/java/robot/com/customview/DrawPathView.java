package robot.com.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DrawPathView extends View {
    private Paint mPaint;
    private Path mPath;

    public DrawPathView(Context context) {
        super(context);
        init(null, 0);
    }

    public DrawPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DrawPathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mPaint.setStrokeWidth(4);
        // TODO!!!如果不设置这个值的话默认是使用Paint.Style.FILL
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(200, 200);
        // 第一个坐标是对应的控制的坐标，第二个坐标是终点坐标
        mPath.quadTo(400, 250, 600, 200);

        canvas.drawPath(mPath, mPaint);


        /**
         * TODO!!!这里将画布的坐标系向下移动200，但是要注意的就是这里的Path也会跟着移动。
         */
        canvas.translate(0, 200);
        // 调用close，就会首尾闭合连接
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

}

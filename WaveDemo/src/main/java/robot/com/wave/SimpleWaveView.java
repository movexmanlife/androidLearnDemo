package robot.com.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;


public class SimpleWaveView extends View {
    private float mInitialRadius; // 初始波纹半径
    private float mMaxRadiusRate = 0.85f; // 如果没有设置mMaxRadius，可mMaxRadius = 最小长度
    private float mMaxRadius; // 最大波纹半径
    private long mDuration = 2000; // 一个波纹从创建到消失的持续时间
    private int mSpeed = 500; // 波纹的创建速度，每500ms创建一个
    private Interpolator mInterpolator = new LinearInterpolator();

    private List<Circle> mCircleList = new ArrayList<>();
    private boolean mIsRunning;

    /**
     * TODO!!!啥含义
     */
    private boolean mMaxRadiusSet;

    private Paint mPaint;
    /**
     * 上次创建Circle的时间，记录此值，防止创建多次。
     */
    private long mLastCreateTime;

    /**
     * 每隔mSpeed时间创建新的Circle
     */
    private Runnable mCreateCircleRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                createNewCircle();
                postDelayed(mCreateCircleRunnable, mSpeed);
            }
        }
    };

    public SimpleWaveView(Context context) {
        super(context);
        init(null, 0);
    }

    public SimpleWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SimpleWaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setStyle(Paint.Style.FILL);
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!mMaxRadiusSet) {
            /**
             * 计算最大的半径，这里的含义其实就是空间的宽高最小值，乘以0.85，再除以2.0
             */
            mMaxRadius = Math.min(w, h) * mMaxRadiusRate / 2.0f;
        }
    }

    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircleRunnable.run();
        }
    }

    public void stop() {
        mIsRunning = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mCircleList.size(); i++) {
            Circle circle = mCircleList.get(i);

            /**
             * 当前绘制的时间减去Circle创建的时间，大于mDuration（Circle持续的时间），则移除这个Circle。
             */
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getCurrentAlpha());

                /**
                 * 画圆需要原点，所以原点就是宽和高的一半
                 */
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, circle.getCurrentRadius(), mPaint);
            } else {
                mCircleList.remove(circle);
                i--;
            }
        }

        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }
    }

    /**
     * 创建新的Circle
     */
    private void createNewCircle() {
        long currentTime = System.currentTimeMillis();
        /**
         * 当前的时间减去上次创建Circle的时间，判断如果小于创建的间隔时间则不创建
         */
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle();
        mCircleList.add(circle);
        /**
         * 创建Circle之后，进行绘制
         */
        invalidate();
        mLastCreateTime = currentTime;
    }

    /**
     * Circle有一个创建的时间的属性。
     */
    public class Circle {
        private long mCreateTime;

        public Circle() {
            this.mCreateTime = System.currentTimeMillis();
        }

        /**
         * 获取当前的alpha值，这个值随时间变化而变化
         */
        public int getCurrentAlpha() {
            /**
             * 当前事件减去创建时间，在Circle持续时间中所占的比例
             */
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int)((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        /**
         * 获取当前的radius值，这个值随时间变化而变化
         */
        public float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;

            /**
             * 注意这里是LinearInterpolator，所以输入和输出其实是一样的
             */
            return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
        }
    }
}

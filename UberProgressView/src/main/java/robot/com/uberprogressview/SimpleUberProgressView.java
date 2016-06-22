package robot.com.uberprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个是中心圆加上增长圆的动画
 */
public class SimpleUberProgressView extends View {
    private static final String TAG = SimpleUberProgressView.class.getSimpleName();
    private static final String CIRCLE_COLOR = "#29B6F6";
    private static final int MAX_FADING_CIRCLE_ALPHA = 100;
    private static final int TOTAL_ANIMATION_TIME = 450;
    private float cXStationary; // 中心圆的X坐标
    private float cYStationary; // 中心圆的Y坐标

    private float rStationary; // 这个是中心固定圆的半径
    private float rStationaryGF; // 增长圆的半径
    // 一个是固定的圆的画笔，一个是有渐变的圆的画笔。
    private final Paint mPaintStationaryCircle = new Paint();
    private final Paint mPaintGrowingFadingCircle = new Paint();

    private int stationaryCircleColor; // 固定圆的颜色
    private int fadingCircleColor; // 增长圆的颜色

    // Animation calculation fields
    private float currentAnimationTime;
    private float delta;

    private int fadingCircleAlpha = 255;

    private RefreshViewRunnable refreshViewRunnable;

    public SimpleUberProgressView(Context context) {
        super(context);
        init();
    }
    public SimpleUberProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public SimpleUberProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attributeSet) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.UberProgressView,
                0, 0
        );

        try {
            stationaryCircleColor = typedArray.getColor(R.styleable.UberProgressView_stationary_circle_color, Color.parseColor(CIRCLE_COLOR));
            fadingCircleColor = typedArray.getColor(R.styleable.UberProgressView_fading_circle_color, Color.parseColor(CIRCLE_COLOR));
            rStationary = typedArray.getDimension(R.styleable.UberProgressView_stationary_circle_radius, 12f);
        } finally {
            typedArray.recycle();
        }

        setupColorPallets();
    }

    private void init() {
        stationaryCircleColor = Color.parseColor(CIRCLE_COLOR);
        fadingCircleColor = Color.parseColor(CIRCLE_COLOR);
        rStationary = 12f;
        setupColorPallets();
    }

    private void setupColorPallets() {
        mPaintGrowingFadingCircle.setColor(fadingCircleColor);
        mPaintGrowingFadingCircle.setAntiAlias(true);
        mPaintStationaryCircle.setColor(stationaryCircleColor);
        mPaintStationaryCircle.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cXStationary = w / 2;
        cYStationary = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //TODO!!!在画中间固定的一个圆，
        canvas.drawCircle(cXStationary, cYStationary, rStationary, mPaintStationaryCircle);

        //TODO!!!在画中间的增长圆
        canvas.drawCircle(cXStationary, cYStationary, rStationaryGF, mPaintGrowingFadingCircle);

        mPaintGrowingFadingCircle.setAlpha(fadingCircleAlpha);
    }

    /**
     * TODO!!!将这段代码去掉的话，就不会画圈圈了。
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (View.GONE == visibility) {
            removeCallbacks(refreshViewRunnable);
        } else {
            removeCallbacks(refreshViewRunnable);
            refreshViewRunnable = new RefreshViewRunnable();
            post(refreshViewRunnable);
        }
    }
    // TODO!!!这里如此使用是相当于做一个Thread一直在循环，而这里比较简便的使用了内部的postDelayed()来做
    private class RefreshViewRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (SimpleUberProgressView.this) {
                if (currentAnimationTime >= 0) {
                    currentAnimationTime += 5;
                    delta = currentAnimationTime/TOTAL_ANIMATION_TIME;
                    rStationaryGF = rStationary * 4 * delta;
                    if (delta >= 1.0) {
                        currentAnimationTime = 0;
                        rStationaryGF = 0f;
                    }
                    fadingCircleAlpha = MAX_FADING_CIRCLE_ALPHA - (int)(delta * MAX_FADING_CIRCLE_ALPHA);
                    invalidate();
                    postDelayed(this, 16);
                }
            }
        }
    }
}
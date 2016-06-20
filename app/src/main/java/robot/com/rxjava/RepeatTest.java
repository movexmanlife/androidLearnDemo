package robot.com.rxjava;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RepeatTest {
    private static final String TAG = RepeatTest.class.getSimpleName();
    private Context context;
    public RepeatTest(Context context) {
        this.context = context;
    }

    /**
     * 深入到内部其实这个repeat创建一个Observable，新的流。
     *
     * 其中要注意new Observable不代表一个新的流。
     */
    public void testRepeat(Void avoid) {
        Observable.just(1, 2, 3, 4, 5)
                .repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, String.valueOf(integer));
                    }
                });
    }

}

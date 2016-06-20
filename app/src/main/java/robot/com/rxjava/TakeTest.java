package robot.com.rxjava;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TakeTest {
    private static final String TAG = TakeTest.class.getSimpleName();
    private Context context;
    public TakeTest(Context context) {
        this.context = context;
    }

    /**
     * 用这种方式可以做倒计时的效果，take就是截取的含义。
     */
    public void testTake(Void avoid) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, String.valueOf(aLong));
                    }
                });
    }

    public void testTake2(Void avoid) {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, String.valueOf(aLong));
                    }
                });
    }

}

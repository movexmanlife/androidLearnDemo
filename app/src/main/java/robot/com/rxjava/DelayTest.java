package robot.com.rxjava;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DelayTest {
    private static final String TAG = DelayTest.class.getSimpleName();
    private Context context;
    public DelayTest(Context context) {
        this.context = context;
    }

    /**
     *
     */
    public void testDelay(Void avoid) {
        class Student {
            public int age;
            public String name;

            public Student(int age, String name) {
                this.age = age;
                this.name = name;
            }

            @Override
            public String toString() {
                return "Student{" +
                        "age=" + age +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        Observable.just("1", 12, new Student(23, "jack"))
                .delay(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e(TAG, String.valueOf(o));
                    }
                });
    }

    /**
     * 最后一个just产生的ob起作用，所以这里打印了10。
     */
    public void testDelay2(Void avoid) {
        Observable.just(53)
                .just(12)
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        Log.e(TAG, String.valueOf(integer + 10));
                        return integer + 10;
                    }
                })
                .delay(5, TimeUnit.SECONDS)
                .just(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer value) {
                        Log.e(TAG, String.valueOf(value));
                    }
                });
    }

    /**
     * 因为这里just创建了一个ob，timer也创建了一个ob，ob换成了timer的那个了。
     * 所以这里打印的是0，
     *
     * timer()：用于创建Observable，延迟发送一次。
     * delay()：用于事件流中，可以延迟发送事件流中的某一次发送。
     */
    public void testDelay3(Void avoid) {
        Observable.just(234, 43, 532)
                .timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, String.valueOf(aLong));
                    }
                });
    }

    /**
     * 这里过5s之后打印234,43，532
     *
     * timer()：用于创建Observable，延迟发送一次。
     * delay()：用于事件流中，可以延迟发送事件流中的某一次发送。
     */
    public void testDelay4(Void avoid) {
        Observable.just(234, 43, 532)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer value) {
                        Log.e(TAG, String.valueOf(value));
                    }
                });
    }
}

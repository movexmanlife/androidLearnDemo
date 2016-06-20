package robot.com.rxjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class JustAndFromTest {
    private static final String TAG = JustAndFromTest.class.getSimpleName();
    private Context context;
    public JustAndFromTest(Context context) {
        this.context = context;
    }

    /**
     * 可以看到just是一个个的发射数据，每次发射一个数据就会调用一次call(Object o)。
     *
     * 经过调试可以发现
     * OnSubscribeFromArray<T>里面会调用onNext()和onCompleted()。
     */
    public void testjust(Void avoid) {
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

        Observable.just("2", 1, 3, new Student(23, "jack"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e(TAG, String.valueOf(o));
                    }
                });
    }

    public void testjust2(Void avoid) {
        Integer number = 23;

        /**
         * 从这里的类型是Action1<Serializable>，其实说明了Serializable是String和Integer的父类型。
         */
        Observable.just("jack", 23, 19, "rose")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Serializable>() {
                    @Override
                    public void call(Serializable serializable) {
                        Log.e(TAG, String.valueOf(serializable));
                    }
                });
    }

    /**
     * 其实可以看到just本质上是调用from来实现的。
     *
     * 也就是说在from里面，经过调试可以发现
     * OnSubscribeFromArray<T>里面会调用onNext()和onCompleted()。
     */
    public void testFrom(Void avoid) {
        Observable.from(new Integer[]{12, 234, 45, 76})
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

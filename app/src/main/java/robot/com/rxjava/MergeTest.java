package robot.com.rxjava;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MergeTest {
    private Context context;
    public MergeTest(Context context) {
        this.context = context;
    }

    /**
     * （1）注意这里首先输出的是valueTwo的值，然后在输出values的值。
     * 也就是merge的结果，受到每个流自己的时间的影响。
     *
     * （2）这里假如说返回的数据不一致的话，可以使用同一个的类型进行替换。比如说这里List<String>和String的共同类型是Object。
     */
    public void testMerge(Void avoid) {
        List<String> values = new ArrayList<>();
        String valueTwo = "two";

        Observable<List<String>> ob1 = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                for (int i = 0; i < 7; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                values.add("11");
                values.add("111");
                subscriber.onNext(values);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> ob2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(valueTwo);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());


        Observable.merge(ob1, ob2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Toast.makeText(context, String.valueOf(o), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * （1）这里采取统一的数据类型，进行合并。
     *
     * （2）可以看到两个流的输出因为这里的时间所以是交替进行的。
     */
    public void testMerge2(Void avoid) {
        String valueOne = "one";
        String valueTwo = "two";

        Observable<String> ob1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 7; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(valueOne + i);
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> ob2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(valueTwo + i);
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());


        Observable.merge(ob1, ob2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                Toast.makeText(context, String.valueOf(str), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

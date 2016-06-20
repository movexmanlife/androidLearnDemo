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

public class ZipTest {
    private Context context;
    public ZipTest(Context context) {
        this.context = context;
    }
    /**
     * 要注意这里一个流需要7s，另一个流直接就执行了。但是最终zip之后，
     * public void call(Subscriber<? super List<String>> subscriber)调用的时候
     * 是7s之后才会执行。也就是需要等到两个流都返回了才会执行。
     */
    public void testzip(Void avoid) {
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


        Observable.zip(ob1, ob2, new Func2<List<String>, String, Integer>() {
            @Override
            public Integer call(List<String> input1, String input2) {
                int number = 23;
                if (TextUtils.equals(input1.get(1), input2)) {
                    number++;
                } else {
                    number--;
                }
                return number;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Toast.makeText(context, String.valueOf(integer), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 这里将只执行一次Integer call(List<String> input1, String input2)，
     * 而且是在1s之后执行。
     */
    public void testzip2(Void avoid) {
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
                    values.add(String.valueOf("data" +i));
                    subscriber.onNext(values);
                }

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


        Observable.zip(ob1, ob2, new Func2<List<String>, String, Integer>() {
            @Override
            public Integer call(List<String> input1, String input2) {
                int number = 23;
                if (input1.size() == 0) {
                    number++;
                } else {
                    number--;
                }
                return number;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Toast.makeText(context, String.valueOf(integer), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

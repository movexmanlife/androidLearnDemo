package robot.com.rxjava;

import android.content.Context;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class JoinTest {
    private Context context;
    public JoinTest(Context context) {
        this.context = context;
    }

    /**
     * 这里ob1执行完成了7次之后，会执行public void call(String str)，
     * 然后才会执行ob2，最后再执行public void call(String str)。
     */
    public void testjoin(Void avoid) {
        String value = "xman";
        String valueTwo = "two";

        Observable<String> ob1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newValue = "";
                for (int i = 0; i < 7; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    newValue = value + i;
                }
                subscriber.onNext(newValue);
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


        Observable.concat(ob1, ob2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String str) {
                        Toast.makeText(context, String.valueOf(str), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 这里ob1每执行一次subscriber.onNext(newValue);就会执行一次，public void call(String str)，
     * 共执行7次。
     *
     * 然后在执行ob2的subscriber.onNext(valueTwo);，最后执行public void call(String str)。
     */
    public void testjoin2(Void avoid) {
        String value = "xman";
        String valueTwo = "two";

        Observable<String> ob1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newValue = "";
                for (int i = 0; i < 7; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    newValue = value + i;
                    subscriber.onNext(newValue);
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


        Observable.concat(ob1, ob2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String str) {
                        Toast.makeText(context, String.valueOf(str), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

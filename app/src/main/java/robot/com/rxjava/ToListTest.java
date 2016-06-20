package robot.com.rxjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ToListTest {
    private static final String TAG = ToListTest.class.getSimpleName();
    private Context context;
    public ToListTest(Context context) {
        this.context = context;
    }

    /**
     * 这里会执行subscriber.onNext(valueTwo)三次，然后执行onCompleted()。
     *
     * 最后才会执行call(List<String> strings)。
     *
     * 给自己的感觉就是不断的发射数据，发射到一个“缓存池中”，然后执行完成了，将“缓存池”中的数据，
     * 一下转化为List<String>。
     */
    public void testToList(Void avoid) {
        String valueTwo = "2222";

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(valueTwo);
                subscriber.onNext("dddd");
                subscriber.onNext("34324");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        for (String str: strings) {
                            Log.e(TAG, str);
                        }
                    }
                });
    }

    /**
     * 注意from会以此发送里面数组的元素，每次发射一次，map都会执行一次，
     * 也就是from内部会调用onNext()，发射完成了会调用onCompleted()函数。
     *
     * 还有要注意就是泛型不能使用基本类型。
     * 比如这里的int[]类型。
     *
     */
    public void testToList2(Void avoid) {
        Integer[] intArray = {12, 34, 654};

        Observable.from(intArray).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer value) {
                return String.valueOf(value);
            }
        }).subscribeOn(Schedulers.io()).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        for (String str: strings) {
                            Log.e(TAG, str);
                        }
                    }
                });
    }

    public void testToList3(Void avoid) {
        Integer[] intArray = {1, 19, 16, 12, 34, 23, 63, 61, 28, 3,654};

        Observable.from(intArray).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 20;
            }
        }).subscribeOn(Schedulers.io()).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> intList) {
                        for (Integer value: intList) {
                            Log.e(TAG, String.valueOf(value));
                        }
                    }
                });
    }

    public void testGenericMethod() {
        int[] arr = {12, 343, 45, 46};
        // test(arr); // 这里的写法是有问题的。
    }

    /**
     * 要记住泛型方法，表示的类型应该放在方法的返回值前面。
     */
    public <T> void test(T[] data) {
    }
}

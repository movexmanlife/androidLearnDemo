package robot.com.rxjava;

import android.content.Context;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FilterTest {
    private static final String TAG = FilterTest.class.getSimpleName();
    private Context context;
    public FilterTest(Context context) {
        this.context = context;
    }

    /**
     * 这里将所有大于20的数字过滤出来，统一转化为一个List。
     * 最后调用public void call(List<Integer> intList)进行输出。
     */
    public void testFilter(Void avoid) {
        Integer[] intArray = {1, 19, 16, 7, 8, 12, 34, 23, 63, 61, 28, 3, 654};

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

}

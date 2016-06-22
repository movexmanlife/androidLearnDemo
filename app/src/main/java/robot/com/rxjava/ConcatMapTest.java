package robot.com.rxjava;


import android.content.Context;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ConcatMapTest {
    private static final String TAG = ConcatMapTest.class.getSimpleName();
    private Context context;

    public ConcatMapTest(Context context) {
        this.context = context;
    }

    /**
     */
    static int number = 23;
    public void testConcatMap(Void avoid) {
        String[] arr = new String[]{"xman", "vinctor", "miaoshu", "group king", "gebi miaoshu"};

        Observable.from(arr).concatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(s + String.valueOf(number++));
                        /**
                         * TODO!!!非常要注意这里需要做的就是加上下面这句话，否则只会执行第一个因为这个执行时顺序的。需要执行完当前的，才会执行下一个。
                         */
                        subscriber.onCompleted();
                    }
                });
            }
        })
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
     */
    public void testConcatMap2(Void avoid) {
        Observable.from(Arrays.asList("www.baidu.com",
                "www.bing.com",
                "www.sina.com"))
                .concatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return createIpObservableMultiThread(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String str) {
                        Toast.makeText(context, String.valueOf(str), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 获取ip
    private synchronized Observable<String> createIpObservableMultiThread(final String url) {
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            InetAddress myServer = getServerIP(url);
                            String ip = myServer.toString();
                            if (!TextUtils.isEmpty(ip)) {
                                ip = ip.substring(ip.indexOf('/') + 1, ip.length());
                                subscriber.onNext(ip);
                            } else {
                                subscriber.onNext(null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            //subscriber.onError(e);
                            subscriber.onNext(null);
                        }
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public InetAddress getServerIP(String url) {
        InetAddress myServer = null;
        try {
            myServer = InetAddress.getByName(url);
        } catch (UnknownHostException e) {
        }
        return myServer;
    }

    public void testConcatMap3(Void avoid) {
        String[] arr = new String[]{"xman", "vinctor", "miaoshu", "group king", "gebi miaoshu"};
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }

        /**
         * 假设create部分为Api返回的数据列表
         */
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(list);
            }
            // 将列表的数据依次进行发送处理
        }).concatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strList) {
                return Observable.from(strList);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                Log.e(TAG, str + "帅帅帅!");
            }
        });
    }
}

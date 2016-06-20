package robot.com.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import robot.com.rxjava.R;

/**
 * 这里可以实现中间的item放大效果。
 */
public class WheelViewActivity extends AppCompatActivity {
    private static final String TAG = WheelViewActivity.class.getSimpleName();
    ListView listview;
    List<String> mListData;
    WheelViewAdapter wheelViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = (ListView) findViewById(R.id.listview);
        String[] mData = getResources().getStringArray(R.array.list_examples);
        mListData = new ArrayList<>();
        for (int i = 0; i < mData.length; i++) {
            mListData.add(mData[i]);
        }
        wheelViewAdapter =
                new WheelViewAdapter(this, mListData);
        listview.setAdapter(wheelViewAdapter);
        listview.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(final AbsListView view, final int scrollState){

            }

            @Override
            public void onScroll(final AbsListView view,
                                 final int firstVisibleItem,
                                 final int visibleItemCount,
                                 final int totalItemCount){
                if (visibleItemCount != 0){
                    final int midPosition = firstVisibleItem + visibleItemCount / 2;

                    Log.i(TAG, "firstVisibleItem: "
                            + firstVisibleItem
                            + "\nvisibleItemCount: "
                            + visibleItemCount
                            + "\nmidPosition: "
                            + midPosition);

                    if (wheelViewAdapter != null && wheelViewAdapter.getLastPosition() != midPosition) {
                        wheelViewAdapter.setMiddlePosition(midPosition);
                        wheelViewAdapter.notifyDataSetChanged();
                    }

                    wheelViewAdapter.setLastPosition(midPosition);
                }

                listview.requestLayout();
            }
        });
    }
}
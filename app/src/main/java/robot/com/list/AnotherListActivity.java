package robot.com.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import robot.com.rxjava.R;

/**
 * 使用Lambda表达式来
 */
public class AnotherListActivity extends AppCompatActivity {
    private static final String TAG = AnotherListActivity.class.getSimpleName();
    ListView listview;
    List<String> mListData;

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
        AnotherAdapter mBaseAdapter = new AnotherAdapter(this, mListData);
        listview.setAdapter(mBaseAdapter);
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
                    final int midPosition = visibleItemCount - (visibleItemCount / 2);

                    Log.e(TAG, "firstVisibleItem: "
                            + firstVisibleItem
                            + "\nvisibleItemCount: "
                            + visibleItemCount
                            + "\nmidPosition: "
                            + midPosition);


                    LinearLayout listItem = (LinearLayout) listview.getChildAt(midPosition);
                    listItem.setLayoutParams(
                            new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, R.dimen.middle_item_height));

                    int count = visibleItemCount;
                    while (count >= 0) {
                        LinearLayout notMiddleListItem = (LinearLayout) listview.getChildAt(midPosition);
                        if (notMiddleListItem != null && count != midPosition) {
                            Log.e(TAG, "count: " + count);
                            notMiddleListItem.setLayoutParams(
                                    new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                                            (int) getResources().getDimension(R.dimen.normal_item_height)));
                        }
                        count--;

                    }
                }
            }
        });
    }
}
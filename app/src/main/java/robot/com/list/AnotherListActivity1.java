package robot.com.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import robot.com.rxjava.R;

/**
 * 使用Lambda表达式来
 */
public class AnotherListActivity1 extends AppCompatActivity {
    private static final String TAG = AnotherListActivity1.class.getSimpleName();
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

                    Log.i(TAG, "firstVisibleItem: "
                            + firstVisibleItem
                            + "\nvisibleItemCount: "
                            + visibleItemCount);

                    final int midPosition = visibleItemCount - (visibleItemCount / 2);

                    LinearLayout listItem = (LinearLayout) listview.getChildAt(midPosition);
                    LinearLayout layout = (LinearLayout) listItem.findViewById(R.id.layout);
                    layout.setLayoutParams(
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,R.dimen.middle_item_height));
                    listItem.requestLayout();

                    int count = visibleItemCount;
                    while (count >= 0){
                        LinearLayout notMiddleListItem = (LinearLayout) listview.getChildAt(midPosition);
                        if (notMiddleListItem != null) {
                            LinearLayout notMiddleLayout = (LinearLayout) listItem.findViewById(R.id.layout);
                            if (notMiddleLayout != null && count != midPosition) {
                                notMiddleLayout.setLayoutParams(
                                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                (int) getResources().getDimension(R.dimen.normal_item_height)));
                            }
                            notMiddleListItem.requestLayout();
                        }
                        count--;

                    }
                }
            }
        });
    }
}
package robot.com.list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import robot.com.rxjava.R;
import robot.com.rxjava.TakeTest;

/**
 * 这里可以实现中间的item放大效果。
 */
public class ListActivity extends AppCompatActivity {
    private static final String TAG = ListActivity.class.getSimpleName();
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
        ArrayAdapter<String> mBaseAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        mListData);
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
                    final TextView listItem = (TextView) listview.getChildAt(midPosition);
                    listItem.setHeight(200); //Only works if you're using
                    //android.R.simple_list_item_1 since it's
                    //not a ViewGroup, but rather a TextView

                    int count = visibleItemCount;
                    while (count >= 0){ // Here we need to loop through and make sure
                        // all recycled items are returned to their
                        // original height.
                        final TextView item = (TextView) listview.getChildAt(count);
                        if (item != null && count != midPosition){
                            item.setHeight(100);
                        }
                        count--;

                    }

                }

                listview.requestLayout();
            }
        });
    }
}
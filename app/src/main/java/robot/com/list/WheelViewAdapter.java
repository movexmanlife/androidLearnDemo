package robot.com.list;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import robot.com.rxjava.R;

public class WheelViewAdapter extends BaseAdapter {
    private static final String TAG = WheelViewAdapter.class.getSimpleName();
    Context context;
    List<String> datas;
    private int mMiddlePosition;
    private int mLastPosition;

    public int getMiddlePosition() {
        return mMiddlePosition;
    }

    public void setMiddlePosition(int middlePosition) {
        this.mMiddlePosition = middlePosition;
    }

    public int getLastPosition() {
        return mLastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.mLastPosition = lastPosition;
    }

    public WheelViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder mHolder;
        if (convertView == null) {
            Log.e(TAG, "position: " + position);
            /**
             * convertView = LayoutInflater.from(context).inflate(R.layout.list_wheel_view, parent);
             * 相当于调用
             * convertView = LayoutInflater.from(context).inflate(R.layout.list_wheel_view, parent, true);
             *
             * 设置如下
             * LayoutInflater.from(context).inflate(R.layout.list_wheel_view, parent, true)
             * 会出现这个错误，UnsupportedOperationException。
             *   @Override
             *   public void addView(View child) {
             *      throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
             *   }
             */
            convertView = LayoutInflater.from(context).inflate(R.layout.list_wheel_view, null, false);
            mHolder = new Holder();

            mHolder.mText = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(mHolder);
        } else {
            mHolder = (Holder) convertView.getTag();
        }

        int newpos = position;
        if (position >= datas.size()) {
            newpos = position % datas.size();
        }

        /**
         * 这里为什么不是newpos == mMiddlePosition呢？
         *
         * firstVisibleItem其实是第一条可见的item的位置，如果datas很大，则firstVisibleItem可以很大的。
         */
        if (newpos == (mMiddlePosition) % datas.size()) {
            mHolder.mText.setTextColor(Color.RED);
        } else {
            mHolder.mText.setTextColor(Color.BLACK);
        }

        mHolder.mText.setText(datas.get(newpos));
        return convertView;
    }

    static class Holder {
        TextView mText;
    }

}

package robot.com.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import robot.com.rxjava.R;

public class AnotherAdapter extends BaseAdapter {
    private static final String TAG = AnotherAdapter.class.getSimpleName();
    Context context;
    List<String> datas;

    public AnotherAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        if (convertView == null){
            Log.e(TAG, "position: " + position );
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            mHolder = new Holder();

            mHolder.mImg = (ImageView)convertView.findViewById(R.id.img);
            mHolder.mText = (TextView)convertView.findViewById(R.id.txt);
            convertView.setTag(mHolder);
        } else {
            mHolder = (Holder) convertView.getTag();
        }

        mHolder.mImg.setImageResource(R.mipmap.ic_launcher);
        mHolder.mText.setText(getItem(position).toString());
        return convertView;
    }

    static class Holder{
        ImageView mImg;
        TextView mText;
    }

}

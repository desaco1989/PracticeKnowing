package com.desaco.practiceknowing.view_conflict;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/5/23.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SimpleBean> mRecordsList;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRecordsList = new ArrayList<SimpleBean>();
    }

    // 清空集合，将新的数据加入到集合中
    public void clearAndPutList(List<SimpleBean> list) {
        if (list == null) {
            return;
        }
        if (mRecordsList != null && mRecordsList.size() > 0) {
            mRecordsList.clear();
        }
        directPutList(list);
    }

    // 直接将新的数据加入到集合中
    public void directPutList(List<SimpleBean> list) {
        if (list == null) {
            return;
        }
        Log.e("desaco", "size=" + list.size());
        mRecordsList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordsHolder(mInflater.inflate(R.layout.item_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleBean recordsBean = mRecordsList.get(position);
        ((RecordsHolder) holder).titleTv.setText(recordsBean.getTitle());
        ((RecordsHolder) holder).msgTv.setText(recordsBean.getMsg());
    }

    @Override
    public int getItemCount() {
        return mRecordsList.size();
    }

    class RecordsHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView msgTv;

        RecordsHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.title_tv);
            msgTv = (TextView) view.findViewById(R.id.msg_tv);
        }
    }
}

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
        Log.e("desaco", "onBindViewHolder() position="+position);
        final SimpleBean recordsBean = mRecordsList.get(position);
        recordsBean.setPosition(position);
        ((RecordsHolder) holder).titleTv.setText(recordsBean.getTitle());
        ((RecordsHolder) holder).msgTv.setText(recordsBean.getMsg());
        if (recordsBean.isSubScribe()) {
            Log.e("desaco", "onBindViewHolder() no,recordsBean.isSubScribe()="+recordsBean.isSubScribe());
            ((RecordsHolder) holder).subscribeTv.setBackgroundColor(mContext.getResources().getColor(R.color.color_4d0000));
            ((RecordsHolder) holder).subscribeTv.setText("已订阅");

        } else {
            Log.e("desaco", "onBindViewHolder() yes,recordsBean.isSubScribe()="+recordsBean.isSubScribe());
            //subscribeTv
            ((RecordsHolder) holder).subscribeTv.setBackgroundColor(mContext.getResources().getColor(R.color.color_d32f24));
            ((RecordsHolder) holder).subscribeTv.setText("订阅");
            ((RecordsHolder) holder).subscribeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickEvent != null) {
                        onClickEvent.onClickWhere(recordsBean, "");
                    }
                }
            });
        }
    }

    public void updateItem(int position, SimpleBean bean) {//更新每个item子条目
        //TODO 唯一字段比较找到标志ID相同的集合 position; 把需要更新的List position删除，再把新的数据添加到集合中
        int size = mRecordsList.size();
        Log.e("desaco", "updateItem,,before isSuscribe=" + bean.isSubScribe() + "---" + mRecordsList.size());
        for (int i = 0; i < size; i++) {
            if (i == position) {
                mRecordsList.remove(i);
                mRecordsList.add(position, bean);
                Log.e("desaco", "updateItem,,after isSuscribe=" + mRecordsList.get(position).isSubScribe() + "---" + mRecordsList.size());
                notifyItemChanged(position);

//                Log.e("desaco","updateItem,,after isSuscribe="+bean.isSubScribe()+"---"+mRecordsList.size());
//                List<SimpleBean> pgcSubscribeList = new ArrayList<>();
//                pgcSubscribeList.addAll(mRecordsList);
//                Log.e("desaco","updateItem,,add pgcSubscribeList size="+pgcSubscribeList.size());
//                clearAndPutList(pgcSubscribeList);

                return;
            }
        }

    }

    @Override
    public int getItemCount() {
        return mRecordsList.size();
    }

    class RecordsHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView msgTv;
        TextView subscribeTv;

        RecordsHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.title_tv);
            msgTv = (TextView) view.findViewById(R.id.msg_tv);
            //
            subscribeTv = (TextView) view.findViewById(R.id.subscribe_tv);
        }
    }

    //接口回调
    private IClickEvent onClickEvent;

    public void setOnClickEvent(IClickEvent onClickEvent) {
        this.onClickEvent = onClickEvent;
    }

    public interface IClickEvent {
        void onClickWhere(SimpleBean bean, String clickType);
    }
}

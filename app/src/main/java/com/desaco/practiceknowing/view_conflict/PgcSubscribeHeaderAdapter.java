package com.desaco.practiceknowing.view_conflict;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
 * @author desaco
 *         <p>
 *         https://www.jianshu.com/p/991062d964cf#
 *         https://www.jianshu.com/p/258d0dca4791
 *         读创号PGC 订阅号列表的Adapter
 */
public class PgcSubscribeHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView mRecyclerView;

    private Context mContext;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    //Type
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    private String mListType;//列表的类型：1.订阅列表 2.带头部的订阅列表
    private List<SimpleBean> mRecordsList;//订阅列表
    private LayoutInflater mInflater;


    public PgcSubscribeHeaderAdapter(Context mContext, String listType) {
        this.mContext = mContext;
        mListType = listType;

        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRecordsList = new ArrayList<>();
    }

    // 清空集合，将新的数据加入到集合中； 下拉刷新
    public void clearAndPutList(List<SimpleBean> list) {
        if (list == null) {
            return;
        }
        if (mRecordsList != null && mRecordsList.size() > 0) {
            mRecordsList.clear();
        }
        directPutList(list);
    }

    // 直接将新的数据加入到集合中；上拉加载更多
    public void directPutList(List<SimpleBean> list) {
        if (list == null) {
            return;
        }
        mRecordsList.addAll(list);
        notifyDataSetChanged();
    }

    //添加头部View
    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {

        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    //删除头部View
    public void removeHeaderView() {
        if (haveHeaderView()) {
            VIEW_HEADER = null;
            notifyItemRemoved(0);
        }
    }

    //添加底部View
    public void addFooterView(View footerView) {
        if (haveFooterView()) {

        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    //删除底部View
    public void removeFooterView() {
        if (haveFooterView()) {
            VIEW_FOOTER = null;
            notifyItemRemoved(getItemCount() - 1);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new RecordsHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new RecordsHolder(VIEW_HEADER);
        } else {
            return new RecordsHolder(getLayout(R.layout.item_simple));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("desaco","onBindViewHolder() ,no payloads");
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) {
                position--;
            }
            //TODO bean里面有个字段ID 可以用来标志点击
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
    }

    public void updateItem(int position, SimpleBean bean) {//更新每个item子条目
        //TODO 唯一字段比较找到标志ID相同的集合 position; 把需要更新的List position删除，再把新的数据添加到集合中
//        if(mPgcSubscribeList.get(position) != null){
//
//        }
        int size = mRecordsList.size();
        Log.e("desaco","updateItem,,before isSuscribe="+bean.isSubScribe()+"---"+mRecordsList.size());
        for(int i=0;i<size;i++){
            if(i == position){
                if (haveHeaderView()) {
                    position++;
                }
//                mRecordsList.remove(i);
//                mRecordsList.add(position,bean);
//                LogUtil.e("desaco","updateItem,,after isSuscribe="+bean.isSuscribe()+"---"+mPgcSubscribeList.size());

////                notifyItemChanged(position);
//                notifyItemChanged(position,bean);
                mRecordsList.remove(i);
                mRecordsList.add(i,bean);

                notifyItemChanged(position);
//                getViewForPositionAndType(Recycler recycler, int position, int type)


//                notifyItemInserted(position);

//                List<PgcSubscribeBean> pgcSubscribeList = new ArrayList<>();
//                pgcSubscribeList.addAll(mPgcSubscribeList);
//                LogUtil.e("desaco","updateItem,,add isSuscribe="+bean.isSuscribe()+"---"+pgcSubscribeList.size());
//                clearAndPutList(pgcSubscribeList);
//                notifyDataSetChanged();

                return;
            }
        }

    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        int count = (mRecordsList == null ? 0 : mRecordsList.size());
        if (VIEW_FOOTER != null) {
            count++;
        }
        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            if (haveHeaderView()) {
                position--;
            }
            return mRecordsList.get(position).getNewType();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }


    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }


//    public class MyHolder extends RecyclerView.ViewHolder {
//        public MyHolder(View itemView) {
//            super(itemView);
//        }
//    }

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
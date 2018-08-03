//package com.desaco.practiceknowing.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.nayun.framework.R;
//import com.nayun.framework.activity.pgcTab.PgcSubscribeBean;
//import com.nayun.framework.colorUI.widget.ColorRelativeLayout;
//import com.nayun.framework.util.LogUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by desaco on 2018/7/24.
// */
//
//public class PgcHeadFootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Context mContext;
//    private String mListType;//列表的类型：1.订阅列表 2.带头部的订阅列表
//    private List<PgcSubscribeBean> mPgcSubscribeList;//订阅列表
//    private LayoutInflater mInflater;
//
//
//    public PgcHeadFootAdapter(Context mContext, String listType) {
//        this.mContext = mContext;
//        mListType = listType;
//
//        mInflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mPgcSubscribeList = new ArrayList<>();
//    }
//
//    // 清空集合，将新的数据加入到集合中； 下拉刷新
//    public void clearAndPutList(List<PgcSubscribeBean> list) {
//        if (list == null) {
//            return;
//        }
//        if (mPgcSubscribeList != null && mPgcSubscribeList.size() > 0) {
//            mPgcSubscribeList.clear();
//        }
//        directPutList(list);
//    }
//
//    // 直接将新的数据加入到集合中；上拉加载更多
//    public void directPutList(List<PgcSubscribeBean> list) {
//        if (list == null) {
//            return;
//        }
//        mPgcSubscribeList.addAll(list);
//        notifyDataSetChanged();
//    }
//    public void updateItem(int position, PgcSubscribeBean bean) {//更新每个item子条目
//        //TODO 唯一字段比较找到标志ID相同的集合 position; 把需要更新的List position删除，再把新的数据添加到集合中
//        int size = mPgcSubscribeList.size();
//        for(int i=0;i<size;i++){
//            if(i == position){
//                mPgcSubscribeList.remove(i);
//                LogUtil.e("desaco","updateItem,,before isSuscribe="+bean.isSuscribe()+"---"+mPgcSubscribeList.size());
//                mPgcSubscribeList.add(i,bean);
//                notifyItemChanged(i);
//                LogUtil.e("desaco","updateItem,,after isSuscribe="+bean.isSuscribe()+"---"+mPgcSubscribeList.size());
//                return;
//            }
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        LogUtil.e("desaco","onBindViewHolder() ,no payloads");
//
//            //TODO bean里面有个字段ID 可以用来标志点击
//            final PgcSubscribeBean bean = mPgcSubscribeList.get(position);
//            bean.setItemPostion(position);
//            PgcSubscribeViewHolder viewHolder = (PgcSubscribeViewHolder) holder;
//            viewHolder.tvPgcAuthor.setText(bean.getPgcAuthor());
//            viewHolder.tvPgcAuthorDes.setText(bean.getPgcAuthorDesript());
//            if (bean.isSuscribe()) {
//                viewHolder.tvIsSubscribe.setBackgroundColor(mContext.getResources().getColor(R.color.color_4d0000));
//                viewHolder.tvIsSubscribe.setText("已订阅");
//            } else {
//                viewHolder.tvIsSubscribe.setBackgroundColor(mContext.getResources().getColor(R.color.color_d32f24));
//                viewHolder.tvIsSubscribe.setText("订阅");
//
//                viewHolder.tvIsSubscribe.setOnClickListener(new View.OnClickListener() {//TODO 订阅按钮的点击
//                    @Override
//                    public void onClick(View view) {
//                        if (onClickEvent != null) {
////                            LogUtil.e("desaco", "position=" + bean.getItemPostion());
//                            onClickEvent.onClickWhere(bean, "btClick");//订阅按钮的点击
//                        }
//                    }
//                });
//            }
//
//            viewHolder.itemPgcLayout.setOnClickListener(new View.OnClickListener() {//TODO item的点击
//                @Override
//                public void onClick(View view) {
//                    if (onClickEvent != null) {
//                        LogUtil.e("desaco", "position=" + bean.getItemPostion());
//                        onClickEvent.onClickWhere(bean, "itemClick");//item的点击
//                    }
//                }
//            });
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new PgcSubscribeViewHolder(getLayout(R.layout.item_pgc_author_list));
//    }
//    private View getLayout(int layoutId) {
//        return LayoutInflater.from(mContext).inflate(layoutId, null);
//    }
//
//    @Override
//    public int getItemCount() {
//        int count = (mPgcSubscribeList == null ? 0 : mPgcSubscribeList.size());
//        return count;
//    }
//
//    class PgcSubscribeViewHolder extends RecyclerView.ViewHolder {
//        TextView tvPgcAuthor;
//        TextView tvPgcAuthorDes;
//        TextView tvIsSubscribe;
//        ColorRelativeLayout itemPgcLayout;
//
//        public PgcSubscribeViewHolder(View itemView) {
//            super(itemView);
//
//            tvPgcAuthor = (TextView) itemView.findViewById(R.id.pgc_author);
//            tvPgcAuthorDes = (TextView) itemView.findViewById(R.id.pgc_author_descript);
//            tvIsSubscribe = (TextView) itemView.findViewById(R.id.subscribe_pgc);
//            itemPgcLayout = (ColorRelativeLayout) itemView.findViewById(R.id.item_pgc_layout);
//        }
//    }
//
//    private PgcSubscribeListAdapter.IClickEvent onClickEvent;
//
//    public void setOnClickEvent(PgcSubscribeListAdapter.IClickEvent onClickEvent) {
//        this.onClickEvent = onClickEvent;
//    }
//
//    public interface IClickEvent {
//        void onClickWhere(PgcSubscribeBean bean, String clickType);
//    }
//}

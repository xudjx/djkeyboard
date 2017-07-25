package cn.baymax.android.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xud 2017-03-09
 */
public abstract class KeyboardSearchBaseAdapter extends RecyclerView.Adapter<KeyboardSearchBaseAdapter.BaseViewHolder> {

    public Context context;
    public Resources resources;
    public LayoutInflater mLayoutInflater;
    protected List listData;
    protected View.OnClickListener itemClickListener;

    public KeyboardSearchBaseAdapter(Context context, List listData) {
        this.context = context;
        this.listData = listData;
        this.resources = context.getResources();
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public List getAdapterData() {
        return listData;
    }

    protected void setAdapterData(List listData) {
        this.listData = listData;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(listData != null ? listData.get(position) : null);
    }



    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            if (itemClickListener != null)
                itemView.setOnClickListener(itemClickListener);
        }

        protected void injectView() {
        }


    }

}
package com.zy.activity.adapter;

import static com.zy.activity.bean.BaseBean.HEAD;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zy.activity.R;
import com.zy.activity.bean.BaseBean;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    List<BaseBean> mainBeanList;
    private ClickCallBack clickCallBack;

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public BaseAdapter(List<BaseBean> mainBeanList) {
        this.mainBeanList = mainBeanList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == HEAD) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_menu_head_item, viewGroup, false);
            return new BaseHeadViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_menu_body_item, viewGroup, false);
            return new BaseBodyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.bind(mainBeanList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mainBeanList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mainBeanList.size();
    }


    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind(BaseBean bean);
    }

    class BaseHeadViewHolder extends BaseViewHolder {
        private final TextView title;

        public BaseHeadViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.base_menu_head_title);
        }

        public void bind(BaseBean bean) {
            if (bean != null) {
                title.setText(bean.getDes());
            }
        }
    }


    class BaseBodyViewHolder extends BaseViewHolder {

        private final Button title;
        private BaseBean bean;

        public BaseBodyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.base_menu_item_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickCallBack != null) {
                        clickCallBack.click(bean);
                    }
                }
            });
        }

        public void bind(BaseBean bean) {
            this.bean = bean;
            if (this.bean != null) {
                title.setText(this.bean.getDes());
            }
        }
    }

    public interface ClickCallBack {
        void click(BaseBean bean);
    }
}

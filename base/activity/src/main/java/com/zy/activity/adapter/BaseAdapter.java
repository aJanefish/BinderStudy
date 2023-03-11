package com.zy.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zy.activity.R;
import com.zy.activity.bean.BaseBean;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.AnimationMenuViewHolder> {

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
    public AnimationMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_menu_item, viewGroup, false);
        return new AnimationMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimationMenuViewHolder animationTestMainViewHolder, int position) {
        animationTestMainViewHolder.bind(mainBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return mainBeanList.size();
    }


    class AnimationMenuViewHolder extends RecyclerView.ViewHolder {

        private final Button title;
        private BaseBean bean;

        public AnimationMenuViewHolder(@NonNull View itemView) {
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
                title.setText(this.bean.getTitle());
            }
        }
    }

    public interface ClickCallBack {
        void click(BaseBean bean);
    }
}

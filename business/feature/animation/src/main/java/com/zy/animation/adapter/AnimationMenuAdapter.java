package com.zy.animation.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zy.animation.AnimationMainActivity;
import com.zy.animation.R;
import com.zy.animation.bean.AnimationMenuBean;


import java.util.List;

public class AnimationMenuAdapter extends RecyclerView.Adapter<AnimationMenuAdapter.AnimationMenuViewHolder> {

    List<AnimationMenuBean> mainBeanList;

    private View mView;

    public AnimationMenuAdapter(List<AnimationMenuBean> mainBeanList) {
        this.mainBeanList = mainBeanList;
    }


    public void setView(View mView) {
        this.mView = mView;
    }

    @NonNull
    @Override
    public AnimationMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.animation_menu_item, viewGroup, false);
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
        private AnimationMenuBean bean;

        public AnimationMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.animation_menu_item_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AnimationMainActivity.class);
                    intent.putExtra("type", bean.getType());
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(AnimationMenuBean bean) {
            this.bean = bean;
            if (this.bean != null) {
                title.setText(this.bean.getTitle());
            }
        }
    }
}
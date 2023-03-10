package com.zy.animation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zy.animation.fragment.SingleAnimationDesFragment;
import com.zy.animation.fragment.Two1AnimationDesFragment;
import com.zy.animation.fragment.Two2AnimationDesFragment;
import com.zy.animation.fragment.TwoAnimationDesFragment;

public class AnimationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);

        int type = getIntent().getIntExtra("type", 0);

        Fragment fragment;
        if (type == 1) {
            fragment = new SingleAnimationDesFragment();
        } else if (type == 2) {
            fragment = new TwoAnimationDesFragment();
        } else if (type == 3) {
            fragment = new Two1AnimationDesFragment();
        } else if (type == 4) {
            fragment = new Two2AnimationDesFragment();
        } else {
            fragment = new SingleAnimationDesFragment();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.zy_animation_container, fragment)
                .commitNowAllowingStateLoss();

    }
}
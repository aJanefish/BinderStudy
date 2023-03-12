package com.zy.animation;

import android.support.v4.app.Fragment;

import com.zy.activity.BaseMainActivity;
import com.zy.animation.fragment.SingleAnimationDesFragment;
import com.zy.animation.fragment.Two1AnimationDesFragment;
import com.zy.animation.fragment.Two2AnimationDesFragment;
import com.zy.animation.fragment.TwoAnimationDesFragment;

public class AnimationMainActivity extends BaseMainActivity {

    @Override
    protected Fragment getContainerFragment() {
        int code = getIntent().getIntExtra("code", 0);

        Fragment fragment;
        if (code == 1) {
            fragment = new SingleAnimationDesFragment();
        } else if (code == 2) {
            fragment = new TwoAnimationDesFragment();
        } else if (code == 3) {
            fragment = new Two1AnimationDesFragment();
        } else if (code == 4) {
            fragment = new Two2AnimationDesFragment();
        } else {
            fragment = new SingleAnimationDesFragment();
        }

        return fragment;
    }
}
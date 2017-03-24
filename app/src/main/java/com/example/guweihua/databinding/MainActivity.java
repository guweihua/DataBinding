package com.example.guweihua.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guweihua.databinding.adapter.QuFragmnetAdapter;
import com.example.guweihua.databinding.annoation.ActivityFragmentAnnoation;
import com.example.guweihua.databinding.base.BaseActivity;
import com.example.guweihua.databinding.databinding.ActivityMainBinding;

@ActivityFragmentAnnoation(contentId = R.layout.activity_main)
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void initActivityImpl() {
        binding.setAdapter(new QuFragmnetAdapter(getSupportFragmentManager()));
    }
}

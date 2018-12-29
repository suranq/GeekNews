package com.example.geeknews.activitys.weixin.ganhuo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.beas.activity.SimpleActivity;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziActivity extends SimpleActivity {

    @BindView(R.id.iv_girl_detail)
    ImageView mIvGirlDetail;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String meinv = intent.getStringExtra("meinv");
    }
    @Override
    protected int createLayoutId() {
        return R.layout.activity_meizi;
    }
}

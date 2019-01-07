package com.example.geeknews.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geeknews.MainActivity;
import com.example.geeknews.R;
import com.example.geeknews.app.Constants;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.shezhidao.SheZhi;
import com.example.geeknews.shezhidao.SheZhiku;
import com.example.geeknews.utils.ACache;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShezhiFragment extends SimpleFragment implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    LinearLayout mLlSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;
    @BindView(R.id.tv_setting_update)
    TextView mTvSettingUpdate;
    @BindView(R.id.ll_setting_update)
    LinearLayout mLlSettingUpdate;
    Unbinder unbinder;
    Unbinder unbinder1;
    Unbinder unbinder2;
    private SheZhi mSheZhi;
    private File mChcheFile;

    public ShezhiFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shezhi;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        List<SheZhi> sheZhis = SheZhiku.getInsh().selectAll();
        for (int i = 0; i < sheZhis.size(); i++) {
            mSheZhi = sheZhis.get(i);
            Log.e("tttttttt", sheZhis.get(i).getIsHuan() + "");
            mCbSettingNight.setChecked(sheZhis.get(i).getIsNight());
            mCbSettingImage.setChecked(sheZhis.get(i).getIsWutu());
            mCbSettingCache.setChecked(sheZhis.get(i).getIsHuan());
        }

        mChcheFile = new File(Constants.PATH_CACHE);
        mTvSettingClear.setText(ACache.getCacheSize(mChcheFile));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @OnClick({R.id.cb_setting_cache, R.id.cb_setting_image, R.id.cb_setting_night})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_setting_cache:
                if (mCbSettingCache.isChecked()) {
                    mSheZhi.setIsHuan(true);
                    SheZhiku.getInsh().updata(mSheZhi);
                } else {
                    mSheZhi.setIsHuan(false);
                    SheZhiku.getInsh().updata(mSheZhi);
                }
                break;
            case R.id.cb_setting_image:
                if (mCbSettingImage.isChecked()) {
                    mSheZhi.setIsWutu(true);
                    SheZhiku.getInsh().updata(mSheZhi);
                } else {
                    mSheZhi.setIsWutu(false);
                    SheZhiku.getInsh().updata(mSheZhi);
                }
                break;
            case R.id.cb_setting_night:
                if (mCbSettingNight.isChecked()) {
                    mSheZhi.setIsNight(true);
                    SheZhiku.getInsh().updata(mSheZhi);
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    MainActivity.mDelegate.setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ?
                            AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                    // 同样需要调用recreate方法使之生效
                    getActivity().recreate();
                } else {
                    mSheZhi.setIsNight(false);
                    SheZhiku.getInsh().updata(mSheZhi);
                }
                break;
        }
    }

    @OnClick(R.id.tv_setting_clear)
    public void onViewClicked() {
        ACache.deleteDir(mChcheFile);
        mTvSettingClear.setText(ACache.getCacheSize(mChcheFile));
    }
}

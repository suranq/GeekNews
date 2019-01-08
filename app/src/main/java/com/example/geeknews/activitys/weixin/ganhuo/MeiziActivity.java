package com.example.geeknews.activitys.weixin.ganhuo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.beas.activity.SimpleActivity;
import com.example.geeknews.utils.ShareUtil;
import com.example.geeknews.utils.SystemUtil;
import com.example.geeknews.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MeiziActivity extends SimpleActivity {

    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.iv_fen)
    ImageView mIvFen;
    @BindView(R.id.iv_bao)
    ImageView mIvBao;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String mMeinv;
    RxPermissions rxPermissions;
    private static int ACTION_SAVE = 0x00;
    private static int ACTION_SHARE = 0x01;
    Bitmap bitmap;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mMeinv = intent.getStringExtra("meinv");

        Glide.with(MeiziActivity.this).load(mMeinv).into(mImage);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_meizi;
    }

    @OnClick({R.id.iv_show, R.id.iv_fen, R.id.iv_bao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show:
                if (mIvShow.isSelected()) {

                } else {

                }
                break;
            case R.id.iv_fen:
                checkPermissionAndAction(ACTION_SHARE);
                break;
            case R.id.iv_bao:
                checkPermissionAndAction(ACTION_SAVE);
                break;
        }
    }

    private void checkPermissionAndAction(final int actionSave) {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            if (actionSave == ACTION_SAVE) {
                                SystemUtil.saveBitmapToFile(MeiziActivity.this, mMeinv, bitmap, mImage, false);
//                                SaveBitmapFromView(mImage);
                            } else {
                                ShareUtil.shareImage(MeiziActivity.this, SystemUtil.saveBitmapToFile(MeiziActivity.this, mMeinv, bitmap, mImage, true), "分享一只妹纸");
                            }
                        } else {
                            ToastUtil.shortShow("获取写入权限失败");
                        }
                    }
                });

    }

//    private void SaveBitmapFromView(View view) {
//        int w = view.getWidth();
//        int h = view.getHeight();
//        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(bmp);
//        view.layout(0, 0, w, h);
//        view.draw(c);
//        // 缩小图片
//        Matrix matrix = new Matrix();
//        matrix.postScale(0.5f, 0.5f); //长和宽放大缩小的比例
//        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
//        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        try {
//            saveBitmap(bmp, format.parse(String.valueOf(new Date())) + ".JPEG");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return bmp;
//    }
//
//    private void saveBitmap(Bitmap bmp, String s) {
//        String fileName;
//        File file;
//        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
//            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
//        } else {  // Meizu 、Oppo
//            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
//        }
//        file = new File(fileName);
//
//        if (file.exists()) {
//            file.delete();
//        }
//        FileOutputStream out;
//        try {
//            out = new FileOutputStream(file);
//            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
//            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
//                out.flush();
//                out.close();
//// 插入图库
//                MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), bitName, null);
//
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        // 发送广播，通知刷新图库的显示
//        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
//    }
}

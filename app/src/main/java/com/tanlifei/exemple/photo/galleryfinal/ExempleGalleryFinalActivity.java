package com.tanlifei.exemple.photo.galleryfinal;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.support.adapter.abslistview.AbsCommonAdapter;
import com.support.adapter.abslistview.AbsViewHolder;
import com.support.galleryfinal.GalleryFinal;
import com.support.galleryfinal.model.PhotoInfo;
import com.support.imageloader.FanImageLoader;
import com.tanlifei.common.ui.activity.actionbar.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.ui.BaseApplication;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.support.utils.ToastUtils;
import com.uikit.dialog.listener.OnOperItemClickL;
import com.uikit.dialog.widget.ActionSheetDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.exemple_gallery_final_activity)
public class ExempleGalleryFinalActivity extends BaseActionBarActivity {

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;


    @ViewById(R.id.lv_photo)
    GridView mLvPhoto;
    private List<PhotoInfo> mPhotoList;
    private AbsCommonAdapter<PhotoInfo> mChoosePhotoListAdapter;
    private Button mOpenGallery;

    @AfterViews
    void init() {
        initActionBar();
        actionBarView.setActionbarTitle("GalleryFinal 特效");
        mLvPhoto = (GridView) findViewById(R.id.lv_photo);
        mPhotoList = new ArrayList<>();
        mChoosePhotoListAdapter = new AbsCommonAdapter<PhotoInfo>(this,R.layout.exemple_gallery_final_adapter_photo_list_item,mPhotoList) {
            @Override
            protected void convert(AbsViewHolder holder, PhotoInfo photoInfo, int position) {
                FanImageLoader.create("file://" + photoInfo.getPhotoPath()).setAllRes(R.mipmap.ic_gf_default_photo).into(holder.getView(R.id.iv_photo));
            }
        };
        mLvPhoto.setAdapter(mChoosePhotoListAdapter);
        mOpenGallery = (Button) findViewById(R.id.btn_open_gallery);

        mOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] stringItems = {"相册", "拍照", "裁剪", "编辑"};
                final ActionSheetDialog dialog = new ActionSheetDialog(ExempleGalleryFinalActivity.this, stringItems, null);
                dialog.isTitleShow(false).show();

                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String path = GlobalConstants.DOWNLOAD_PATH+"/test.jpg";
                        switch (position) {
                            case 0:
                                //if (mutiSelect) {
                                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, 4, mOnHanlderResultCallback);
                                /*} else {
                                    GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, BaseApplication.functionConfig, mOnHanlderResultCallback);
                                }*/
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, BaseApplication.functionConfig, mOnHanlderResultCallback);
                                break;
                            case 2:
                                if (new File(path).exists()) {
                                    GalleryFinal.openCrop(REQUEST_CODE_CROP, BaseApplication.functionConfig, path, mOnHanlderResultCallback);
                                } else {
                                    ToastUtils.show(ExempleGalleryFinalActivity.this, "图片不存在", Toast.LENGTH_SHORT);
                                }
                                break;
                            case 3:
                                if (new File(path).exists()) {
                                    GalleryFinal.openEdit(REQUEST_CODE_EDIT, BaseApplication.functionConfig, path, mOnHanlderResultCallback);
                                } else {
                                    Toast.makeText(ExempleGalleryFinalActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                mChoosePhotoListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            ToastUtils.show(ExempleGalleryFinalActivity.this, errorMsg);
        }
    };

}

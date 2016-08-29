package com.support.utils;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.support.R;

/**
 * Created by tanlifei on 16/8/29.
 */
public enum ImageLoadUtils {

    INSTANCE;
    /**
     * 标准配置
     */
    private DisplayImageOptions normalOptions;
    /**
     * 圆形配置
     */
    private DisplayImageOptions circleOptions;
    /**
     * 圆角图片配置
     */
    private DisplayImageOptions roundedOptions;


    private BitmapDisplayer simpleBitmapDisplayer;
    private BitmapDisplayer circleBitmapDisplayer;
    private BitmapDisplayer roundedBitmapDisplayer;

    private int onLoadingImageResId;
    private int onEmptyImageResId;
    private int onFailedImageResId;

    /**
     * 构造方法 参数初始化 单例形式 只会初始化一次 避免不必要的资源开支
     */
    private ImageLoadUtils() {
        //初始化 全局默认图片
        onLoadingImageResId = R.mipmap.ic_gf_default_photo;
        onEmptyImageResId = R.mipmap.ic_gf_default_photo;
        onFailedImageResId = R.mipmap.ic_gf_default_photo;

        simpleBitmapDisplayer = new SimpleBitmapDisplayer();
        normalOptions = getOption(onLoadingImageResId, onEmptyImageResId, onFailedImageResId, simpleBitmapDisplayer);

        circleBitmapDisplayer = new CircleBitmapDisplayer();
        circleOptions = getOption(onLoadingImageResId, onEmptyImageResId, onFailedImageResId, circleBitmapDisplayer);

        //圆角图片 圆角半径dp
        int cornerRadiusDp = 10;
        //圆角大小通过 dp2px转换 使得 不同分辨率设备上呈现一致显示效果
        roundedBitmapDisplayer = new RoundedBitmapDisplayer(cornerRadiusDp);
        roundedOptions = getOption(onLoadingImageResId, onEmptyImageResId, onFailedImageResId, roundedBitmapDisplayer);

    }

    /**
     * 重构 抽取出的通用生成Option方法
     *
     * @param onLoadingImageResId
     * @param onEmptyImageResId
     * @param onFailedImageResId
     * @param bitmapDisplayer     normal 或圆形、圆角 bitmapDisplayer
     * @return
     */
    private DisplayImageOptions getOption(int onLoadingImageResId, int onEmptyImageResId, int onFailedImageResId, BitmapDisplayer bitmapDisplayer) {
        return new DisplayImageOptions.Builder()
                //.showImageOnLoading(onLoadingImageResId)
                .showImageForEmptyUri(onEmptyImageResId)
                .showImageOnFail(onFailedImageResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(bitmapDisplayer)
                .build();
    }

    /**
     * 初始化图片加载缓存
     *
     * @param context
     * @return
     */
    public void init(Context context) {
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context).threadPriority(3)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(1024 * 1024 * 2))
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCacheSize(1024 * 1024 * 50).build());
    }

    public void loadImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, normalOptions);
    }

    public void loadImageView(ImageView iv, String url,ImageLoadingListener progressListener) {
        ImageLoader.getInstance().displayImage(url, iv, normalOptions,  progressListener);
    }


    public void loadCircleImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, circleOptions);
    }

    public void loadRoundedImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, roundedOptions);
    }

}

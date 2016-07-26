package com.fans.loader.internal.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * time: 2015/10/14
 * description:
 *
 * @author fandong
 */
public class BitmapUtils {
    /**
     * 根据一张图片的输入流和显示视图的宽高，返回指定宽高的bitmap
     *
     * @param is     原始的图片数据
     * @param width  目标宽度
     * @param height 目标高度
     * @return 指定尺寸的位图
     */
    public static Bitmap createScaledBitmap(InputStream is, int width, int height) {
        try {
            //1.加到一个byte数组当中
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            IoUtils.copyStream(is, bos, null);
            byte[] buffer = bos.toByteArray();
            bos.close();
            is.close();
            //2.计算宽高
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);
            float width_ratio = options.outWidth / (float) width;
            float height_ratio = options.outHeight / (float) height;
            int ratio = (int) (width_ratio > height_ratio ? width_ratio : height_ratio);
            ratio = ratio > 1 ? ratio : 1;
            //3.decode
            options.inJustDecodeBounds = false;
            options.inPurgeable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = ratio;
            return BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * 转换图片成圆�?
     *
     * @param bitmap
     *            传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = null;
        try {
            output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right,
                    (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top,
                    (int) dst_right, (int) dst_bottom);
            final RectF rectF = new RectF(dst);

            paint.setAntiAlias(true);

            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
        }

        return output;
    }

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     */
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        return bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }
}

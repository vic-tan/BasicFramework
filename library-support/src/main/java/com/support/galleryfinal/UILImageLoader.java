/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.support.galleryfinal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.support.galleryfinal.widget.GFImageView;
import com.support.imageloader.FanImageLoader;
import com.support.imageloader.internal.core.DisplayImageOptions;
import com.support.imageloader.internal.core.assist.ImageSize;


/**
 * Desction:
 * Author:pengjianbo
 * Date:15/10/10 下午5:52
 */
public class UILImageLoader implements GalleryFinalImageLoader {

    private Bitmap.Config mImageConfig;

    public UILImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public UILImageLoader(Bitmap.Config config) {
        this.mImageConfig = config;
    }

    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(false)
                .bitmapConfig(mImageConfig)
                .build();
        ImageSize imageSize = new ImageSize(width, height);
        FanImageLoader.create("file://" + path).setImageSize(imageSize).into(imageView);
       // ImageLoader.getInstance().displayImage("file://" + path, new ImageViewAware(imageView), options, imageSize, null, null);
    }

    @Override
    public void clearMemoryCache() {

    }
}

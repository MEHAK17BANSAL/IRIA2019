package com.brandappz.dhfl.event.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.brandappz.dhfl.event.constants.AppConstants;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImagesManager {

	private ImageLoader loader;
	private DisplayImageOptions options;
	private DisplayImageOptions circleOptions;
	private String path;

	public ImagesManager(Context context) {

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(3)
				.memoryCache(new WeakMemoryCache()).build();

		options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(false)
				.bitmapConfig(Bitmap.Config.ARGB_8888).build();

		path = "assets://" + AppConstants.IMAGE_FILEPATH;

		loader = ImageLoader.getInstance();
		loader.init(config);

	}

	public void loadImage(String filename, ImageView imageView) {
		loader.displayImage(path + filename, imageView, options);
	}

	public void loadTwitterImage(String url, ImageView imageView) {
		loader.displayImage(url, imageView, circleOptions);
	}

}

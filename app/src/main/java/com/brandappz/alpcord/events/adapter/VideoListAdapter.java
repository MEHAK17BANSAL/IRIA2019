package com.brandappz.alpcord.events.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.fragments.Beanclass;
import com.brandappz.alpcord.events.model.Video;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideoListAdapter extends BaseAdapter{
    public RelativeLayout rootview;
    Context context;
    ArrayList<Beanclass> data;
    private Activity mActivity;
    private FragmentManager fm;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private Map<View, YouTubeThumbnailLoader> mLoaders;
    private List<Video> videos = new ArrayList<Video>();

    public VideoListAdapter(Activity context, ArrayList<Video> beanlist, FragmentManager fragmentManager) {
        this.videos = beanlist;
        this.fm = fragmentManager;
        this.mActivity = context;
        mLoaders = new HashMap<>();
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public Video getItem(int position) {
        return videos.get(position);
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.cell_video_info, null);
            viewHolder.rootview = (RelativeLayout) convertView.findViewById(R.id.rootview);
            viewHolder.videoname = (TextView) convertView.findViewById(R.id.videoname);
            viewHolder.videoThumbnail = (YouTubeThumbnailView) convertView.findViewById(R.id.imageView_thumbnail);
            //viewHolder.videoThumbnail.setTag(position);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(viewHolder);


        } else {


            viewHolder = (ViewHolder) convertView.getTag();

        }

        if (URLUtil.isValidUrl(videos.get(position).getThumbNailUrl())) {
            Picasso.with(mActivity).load(videos.get(position).getThumbNailUrl()).into(viewHolder.videoThumbnail);
            Picasso.with(mActivity).load(videos.get(position).getContentData()).into(viewHolder.imageView);
        }

        viewHolder.rootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(videos.get(position).getTitle().toString().trim()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mActivity.startActivity(intent);

            }
        });
      //  viewHolder.videoThumbnail.setTag(position);
        //viewHolder.imageView.setTag(position);
        viewHolder.videoname.setText(videos.get(position).getContentData());
        //viewHolder.rootview.setTag(position);
        return convertView;
    }




    private class ViewHolder {
        public RelativeLayout rootview;
        YouTubeThumbnailView videoThumbnail;
        ImageView imageView;
        TextView likesCount, videoname;
        TextView viewCount;
    }
}




























/*import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.brandappz.dhfl.event.R;
import com.brandappz.dhfl.event.fragments.Beanclass;

import com.brandappz.dhfl.event.fragments.SearchScreenUserPhotoAdapter;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Map;

import static com.brandappz.dhfl.event.R.id.pager;


public class VideoListAdapter extends BaseAdapter implements YouTubeThumbnailView.OnInitializedListener {

    private Context mContext;
    FragmentManager fragmentManager;
    private Map<View, YouTubeThumbnailLoader> mLoaders;
    LayoutInflater inflate;
    Dialog d;
    Context context;
    GalleryPagerAdapter _adapter;
    ArrayList<Beanclass> data;
    private DisplayImageOptions options;
    public VideoListAdapter(Context context, ArrayList<Beanclass> beanlist, FragmentManager fragmentManager) {
        mContext = context;
        this.fragmentManager = fragmentManager;
        this.data = beanlist;
        inflate = LayoutInflater.from(this.context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.brandlogo)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
       // mLoaders = new HashMap<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.videolistadapter, null);
            // convertView = inflate.inflate(R.layout.item_grid_image, null);

            assert convertView != null;
            holder.root = (LinearLayout) convertView.findViewById(R.id.root);
            holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);
            //holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
           *//* holder.root = (LinearLayout) convertView.findViewById(R.id.root);
            holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);*//*
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }
        MediaStore.Video.getInstance()
                .displayImage(data.get(position).getImageurl().toString(), holder.imgQueue, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                       *//* holder.progressBar.setProgress(0);
                        holder.progressBar.setVisibility(View.VISIBLE);*//*
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        //holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        Log.e("gettingtag", imageUri.toString());
                        //holder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                       // holder.progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });
      *//*  Picasso.with(context)
                .load(data.get(position).getPhototext())
                .placeholder(R.drawable.image_1)
                .error(R.drawable.image_1)
                .into(imgQueue);
*//*

        holder.root.setOnClickListener(this);
        holder.root.setTag(position);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.root) {
            final int pos = (int) v.getTag();
            d = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            //00 d = new Dialog(context);
            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = d.getWindow();
            d.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
            //   window.setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            // 00d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(1000, 0, 0, 0)));


            d.setTitle("Dialog");
            d.setContentView(R.layout.dialog_home_image_user);
            d.show();

            pager = (ViewPager) d.findViewById(pager);
            _adapter = new GalleryPagerAdapter((FragmentActivity) context, data);


            pager.setAdapter(_adapter);

            pager.setCurrentItem(pos);
        }
    }
    private static class ViewHolder {
        TextView tv_topic;
        ImageView imgQueue, iv_check;
        RadioGroup rb_group;
        ProgressBar progressBar;
        LinearLayout root;

    }

    class GalleryPagerAdapter extends PagerAdapter {
        private FragmentActivity activity;
        private ArrayList<Beanclass> carImageBean;
        private DisplayImageOptions options;
        public GalleryPagerAdapter(FragmentActivity activity, ArrayList<Beanclass> imageBean) {
            this.activity = activity;
            this.carImageBean = imageBean;
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.top_band)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public int getCount() {
            return carImageBean.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(activity);
            // imageView.setMaxZoom(4f);
            //    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            // imageView.setScaleType(ImageView.ScaleType.FIT_XY);



              *//*  Picasso.with(activity)
                        .load(carImageBean.get(position).getPhototext())
                        .placeholder(R.drawable.action_bar_background)
                        .error(R.drawable.action_bar_background)
                        .into(imageView);*//*
            //  imageView.setBackgroundResource(Integer.parseInt(String.valueOf(carImageBean.get(position).getImageurl())));
            ImageLoader.getInstance()
                    .displayImage(carImageBean.get(position).getImageurl(), imageView, options, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            Log.e("gettingtag", imageUri.toString());
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {

                        }
                    });
            ((ViewPager) container).addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

    }*/
   /* @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentRow = convertView;
        VideoHolder holder;

        //The item at the current position
        final YouTubeContent.YouTubeVideo item = YouTubeContent.ITEMS.get(position);

        if (currentRow == null) {
            //Create the row
            final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentRow = inflater.inflate(R.layout.row_layout, parent, false);

            //Create the video holder
            holder = new VideoHolder();

            //Set the title
            holder.title = (TextView) currentRow.findViewById(R.id.textView_title);
            holder.title.setText(item.title);

            //Initialise the thumbnail
            holder.thumb = (YouTubeThumbnailView) currentRow.findViewById(R.id.imageView_thumbnail);
            holder.thumb.setTag(item.id);
//            holder.thumb.initialize(mContext.getString(R.string.DEVELOPER_KEY), this);

            currentRow.setTag(holder);
        } else {
            //Create it again
            holder = (VideoHolder) currentRow.getTag();
            final YouTubeThumbnailLoader loader = mLoaders.get(holder.thumb);

            //Set the title
            if (item != null) {
                holder.title.setText(item.title);

                if (loader == null) {
                    //Loader is currently initialising
                    holder.thumb.setTag(item.id);
                } else {
                    //The loader is already initialised
                    loader.setVideo(item.id);
                }

            }
        }
        return currentRow;

    }


    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
        mLoaders.put(view, loader);
        loader.setVideo((String) view.getTag());
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView thumbnailView, YouTubeInitializationResult errorReason) {
        final String errorMessage = errorReason.toString();
        Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
    }


    static class VideoHolder {
        YouTubeThumbnailView thumb;
        TextView title;
    }*/


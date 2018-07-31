package com.brandappz.dhfl.event.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;


public class SearchScreenUserPhotoAdapter extends BaseAdapter implements View.OnClickListener {
    public ImageView imgQueue, liked;
    public LinearLayout root;
    public ArrayList<Linkedinimages> hold, temp;
    public LinearLayout lickeclick;
    public ImageView likeup;
    Context context;
    ArrayList<Beanclass> data;
    LayoutInflater inflater;
    ImageView imageView;
    String contentURL;
    Context activity;
    ProgressBar progressDialog;
    ArrayList<Linkedinimages> image;
    Dialog d;
    GalleryPagerAdapter _adapter;
    ViewPager pager;
    private LinearLayout pagerindicator;
    private int count = 0;
    private ImageView[] dots;
    private FragmentManager fm;
    LayoutInflater inflate;
    private DisplayImageOptions options;
    public SearchScreenUserPhotoAdapter(Context context, ArrayList<Beanclass> values, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        this.data = values;
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
       /* inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);*/
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
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.searchuserphotoitem, null);
           // convertView = inflate.inflate(R.layout.item_grid_image, null);

            assert convertView != null;
            holder.root = (LinearLayout) convertView.findViewById(R.id.root);
            holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
           /* holder.root = (LinearLayout) convertView.findViewById(R.id.root);
            holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);*/
            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();
        }
      //  holder.imgQueue.setBackgroundResource(Integer.parseInt(data.get(position).getImageurl()));
        ImageLoader.getInstance()
                .displayImage(data.get(position).getImageurl().toString(), holder.imgQueue, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        holder.progressBar.setProgress(0);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        Log.e("gettingtag", imageUri.toString());
                        holder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                       holder.progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });


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

            pager = (ViewPager) d.findViewById(R.id.pager);
            _adapter = new GalleryPagerAdapter((FragmentActivity) context, data);


            pager.setAdapter(_adapter);

            pager.setCurrentItem(pos);

           /* ((ImageView) d.findViewById(R.id.left)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(pager.getCurrentItem() - 1);
                    if (data.get((pager.getCurrentItem())).getLiked().trim().toString().equalsIgnoreCase("true")) {
                        Toast.makeText(context, "like" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "unlike" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                    }
                    _adapter.notifyDataSetChanged();

                }
            });*/
            /*((ImageView) d.findViewById(R.id.rit)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pager.setCurrentItem((pager.getCurrentItem() + 1));
                    if (data.get((pager.getCurrentItem())).getLiked().trim().toString().equalsIgnoreCase("true")) {

                        Toast.makeText(context, "like" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "unlike" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                    }
                    _adapter.notifyDataSetChanged();

                }
            });*/
           /* ((ImageView) d.findViewById(R.id.close_iv)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });*/


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



              /*  Picasso.with(activity)
                        .load(carImageBean.get(position).getPhototext())
                        .placeholder(R.drawable.action_bar_background)
                        .error(R.drawable.action_bar_background)
                        .into(imageView);*/
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

    }


}

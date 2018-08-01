package com.brandappz.alpcord.events.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.fragments.GalleryFragment;
import com.brandappz.alpcord.events.fragments.SingleViewActivity;


public class ImageGridAdapter extends BaseAdapter{

    /*public ImageView imgQueue, liked;
    public LinearLayout root;
    public ArrayList<Linkedinimages> hold, temp;
    public LinearLayout lickeclick;
    public ImageView likeup;
    Context context;
    ArrayList<Linkedinimages> data;
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

    public ImageGridAdapter(Context context, ArrayList<Linkedinimages> values, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        this.data = values;
        inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getViewTypeCount() {
        if (data != null)
            return data.size();
        else
            return super.getViewTypeCount();
    }

    public int getCount() {
        if (data != null)
            return data.size();
        else
            return 0;
    }

    public Object getItem(int position) {
        if (data != null)
            return data.get(position);
        else
            return position;
    }


    public long getItemId(int position) {

        return data.indexOf(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.searchuserphotoitem, null);
        root = (LinearLayout) vi.findViewById(R.id.root);
        imgQueue = (ImageView) vi.findViewById(R.id.imgQueue);
        liked = (ImageView) vi.findViewById(R.id.liked);
        Picasso.with(context)
                .load(data.get(position).getPhototext())
                .placeholder(R.drawable.viwd_img_2)
                .error(R.drawable.viwd_img_2)
                .into(imgQueue);
        if (data.get(position).getLiked().trim().toString().equalsIgnoreCase("true")) {
            Picasso.with(context)
                    .load(R.mipmap.grid_pic_liked)
                    .placeholder(R.drawable.viwd_img_2)
                    .error(R.drawable.viwd_img_2)
                    .into(liked);
        } else {
            Picasso.with(context)
                    .load(R.mipmap.grid_pic_like)
                    .placeholder(R.drawable.viwd_img_2)
                    .error(R.drawable.viwd_img_2)
                    .into(liked);
        }

        liked.setOnClickListener(this);
        liked.setTag(position);
        root.setOnClickListener(this);
        root.setTag(position);

        return vi;
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
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(1000, 255, 255, 255)));


            d.setTitle("Dialog");
            d.setContentView(R.layout.dialog_home_image_user);
            d.show();
            likeup = (ImageView) d.findViewById(R.id.likeup);
            pager = (ViewPager) d.findViewById(R.id.pager);
            _adapter = new GalleryPagerAdapter((FragmentActivity) context, data);


            ((LinearLayout) d.findViewById(R.id.lickeclick)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            }
        });
        pager.setAdapter(_adapter);

        pager.setCurrentItem(pos);


        ((ImageView) d.findViewById(R.id.left)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() - 1);
                if (data.get((pager.getCurrentItem())).getLiked().trim().toString().equalsIgnoreCase("true")) {
                    likeup.setBackgroundResource(R.mipmap.like_selected);
                    Toast.makeText(context, "like" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                } else {
                    likeup.setBackgroundResource(R.mipmap.like_unselect);
                    Toast.makeText(context, "unlike" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                }
                _adapter.notifyDataSetChanged();

            }
        });
        ((ImageView) d.findViewById(R.id.rit)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pager.setCurrentItem((pager.getCurrentItem() + 1));
                if (data.get((pager.getCurrentItem())).getLiked().trim().toString().equalsIgnoreCase("true")) {
                    likeup.setBackgroundResource(R.mipmap.like_selected);
                    Toast.makeText(context, "like" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                } else {
                    likeup.setBackgroundResource(R.mipmap.like_unselect);
                    Toast.makeText(context, "unlike" + pager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                }
                _adapter.notifyDataSetChanged();

            }
        });
        ((ImageView) d.findViewById(R.id.close_iv)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });


    } else if (v.getId() == R.id.liked) {
        int pos = (int) v.getTag();



        Toast.makeText(context, "" + pos, Toast.LENGTH_SHORT).show();
    }
}





class GalleryPagerAdapter extends PagerAdapter {
    private FragmentActivity activity;
    private ArrayList<Linkedinimages> carImageBean;

    public GalleryPagerAdapter(FragmentActivity activity, ArrayList<Linkedinimages> imageBean) {
        this.activity = activity;
        this.carImageBean = imageBean;
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
        if (carImageBean.get(position).getPhototext() != "" || !carImageBean.get(position).getPhototext().isEmpty()) {


            Picasso.with(activity)
                    .load(carImageBean.get(position).getPhototext())
                    .placeholder(R.drawable.viwd_img_2)
                    .error(R.drawable.viwd_img_2)
                    .into(imageView);


            ((ViewPager) container).addView(imageView);
        } else {

        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }*/
    private Context mContext;

    public ImageGridAdapter(Context c, GalleryFragment galleryFragment) {
        mContext = c;
    }

    public ImageGridAdapter(SingleViewActivity singleViewActivity) {

    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(360, 360));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(2, 2, 2, 2);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    public Integer[] mThumbIds = { R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8

    };
}

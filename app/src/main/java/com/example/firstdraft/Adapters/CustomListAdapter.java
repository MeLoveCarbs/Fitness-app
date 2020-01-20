package com.example.firstdraft.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.firstdraft.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.Objects;

public class CustomListAdapter  extends ArrayAdapter<Card> {

    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView title;
        ImageView image;
        ProgressBar dialog;
        Button button;
        TextView likecount;
        ImageView profPic;
        TextView nickName;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomListAdapter(Context context, int resource, ArrayList<Card> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

        //sets up the image loader library
        setupImageLoader();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        String title = Objects.requireNonNull(getItem(position)).getTitle();
        String imgUrl = Objects.requireNonNull(getItem(position)).getImgURL();
        String profPic = Objects.requireNonNull(getItem(position)).getProfileImg();
        String nickName = Objects.requireNonNull(getItem(position)).getNickName();

        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.cardTitle);
                holder.image = (ImageView) convertView.findViewById(R.id.cardImage);
                holder.dialog = (ProgressBar) convertView.findViewById(R.id.cardProgressDialog);
                holder.button = (Button) convertView.findViewById(R.id.button);
                holder.likecount = (TextView) convertView.findViewById(R.id.likeview);
                holder.profPic = (ImageView) convertView.findViewById(R.id.profileView);
                holder.nickName = (TextView) convertView.findViewById(R.id.nicknameView);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


//            Animation animation = AnimationUtils.loadAnimation(mContext,
//                    (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
//            result.startAnimation(animation);
            lastPosition = position;

            holder.title.setText(title);
            holder.nickName.setText(nickName);
            //create the imageloader object
            ImageLoader imageLoader = ImageLoader.getInstance();

            int defaultImage = mContext.getResources().getIdentifier("@drawable/exerciseslogo.png",null,mContext.getPackageName());
            int defaultProfile = mContext.getResources().getIdentifier("@drawable/defProfile", null, mContext.getPackageName());

            //create display options
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultImage)
                    .showImageOnFail(defaultImage)
                    .showImageOnLoading(defaultImage).build();

            DisplayImageOptions profileOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true).resetViewBeforeLoading(true)
                    .showImageForEmptyUri(defaultProfile)
                    .showImageOnFail(defaultProfile)
                    .showImageOnLoading(defaultProfile).build();

            //download and display image from url
            imageLoader.displayImage(imgUrl, holder.image, options,new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    holder.dialog.setVisibility(View.VISIBLE);
                }
                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    holder.dialog.setVisibility(View.GONE);
                }
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    holder.dialog.setVisibility(View.GONE);
                }
                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }}

            );

            imageLoader.displayImage(profPic, holder.profPic, profileOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

            return convertView;
        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }

    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}
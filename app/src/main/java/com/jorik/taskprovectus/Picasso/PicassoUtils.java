package com.jorik.taskprovectus.Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.jorik.taskprovectus.Model.Enum.AccessDirectoryKind;
import com.jorik.taskprovectus.Picasso.Transformation.CircleTransformer;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.CachePhotoUtils;
import com.jorik.taskprovectus.Utils.ResourceUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

public class PicassoUtils {

  private View mView;
  private Context mContext;

  public PicassoUtils(View view) {
    mView = view;
    mContext = mView.getContext();
  }

  public void loadImageAndCache(String url, boolean needCache) {
    CachePhotoUtils cachePhotoUtils = new CachePhotoUtils(mContext, AccessDirectoryKind.KIND);

    Picasso
      .with(mContext)
      .load(url)
      .transform(new CircleTransformer(true))
      .into(new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
          if (needCache)
            cachePhotoUtils.loadBitmap(bitmap, url);
          chooseViewToLoadBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
          if (needCache) {
            Bitmap cacheBitmap = null;
            if (url != null) cacheBitmap = cachePhotoUtils.uploadBitmap(url);
            if (cacheBitmap != null) chooseViewToLoadBitmap(cacheBitmap);
            else chooseViewToLoadDefault(R.mipmap.ic_account_image_default);
          } else chooseViewToLoadDefault(R.mipmap.ic_account_image_default);
        }
      });
  }

  private void chooseViewToLoadDefault(int resource) {
    if (mView instanceof ImageView) {
      ((ImageView) mView).setImageResource(resource);
    } else if (mView instanceof Toolbar) {
      ((Toolbar) mView).setLogo(ResourceUtils.with(mContext).drawable(resource));
    }
  }

  private void chooseViewToLoadBitmap(Bitmap bitmap) {
    if (mView instanceof ImageView) {
      ((ImageView) mView).setImageBitmap(bitmap);
    } else if (mView instanceof Toolbar) {
      ((Toolbar) mView).setLogo(new BitmapDrawable(mContext.getResources(), bitmap));
    }
  }
}

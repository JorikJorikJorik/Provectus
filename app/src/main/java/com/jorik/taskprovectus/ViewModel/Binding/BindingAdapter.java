package com.jorik.taskprovectus.ViewModel.Binding;

import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jorik.taskprovectus.Model.Enum.AccessDirectoryKind;
import com.jorik.taskprovectus.PicassoTransformation.CircleTransformer;
import com.jorik.taskprovectus.R;
import com.jorik.taskprovectus.Utils.CachePhotoUtils;
import com.jorik.taskprovectus.Utils.ImageUtils;
import com.jorik.taskprovectus.Utils.ResourceUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

public class BindingAdapter {

  private static final int EMPTY_DATA_RESOURCE = 0;
  private static boolean pressed;

  @android.databinding.BindingAdapter(value = {"adapter", "divider_res"}, requireAll = false)
  public static <T extends Adapter> void recyclerViewAdapter(RecyclerView recyclerView, T adapter, int dividerRes) {
    if (dividerRes != 0) {
      Drawable drawableDivider = VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ? recyclerView.getContext().getDrawable(dividerRes) : recyclerView.getContext().getResources().getDrawable(dividerRes);
      if (drawableDivider != null) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(drawableDivider);
        recyclerView.addItemDecoration(itemDecoration);
      }
    }
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    recyclerView.setAdapter(adapter);
  }

  @android.databinding.BindingAdapter(value = {"loading_by_scroll"})
  public static void recyclerScrollListener(RecyclerView recyclerView, Runnable event) {
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
          if (linearLayoutManager.getItemCount() - 1 == linearLayoutManager.findLastVisibleItemPosition() && event != null)
            event.run();
        }
      }
    });
  }

  @android.databinding.BindingAdapter(value = {"image_url", "image_need_cache"}, requireAll = false)
  public static void loadImagePicasso(ImageView imageView, String url, boolean needCache) {

    CachePhotoUtils cachePhotoUtils = new CachePhotoUtils(imageView.getContext(), AccessDirectoryKind.KIND);

    Picasso
      .with(imageView.getContext())
      .load(url)
      .transform(new CircleTransformer(true))
      .into(new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
          if (needCache)
            cachePhotoUtils.loadBitmap(bitmap, url);
          imageView.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
          if (needCache) {
            Bitmap cacheBitmap = null;
            if (url != null) cacheBitmap = cachePhotoUtils.uploadBitmap(url);
            if (cacheBitmap != null) imageView.setImageBitmap(cacheBitmap);
            else imageView.setImageResource(R.mipmap.ic_account_image_default);
          } else imageView.setImageResource(R.mipmap.ic_account_image_default);
        }
      });
  }

  @android.databinding.BindingAdapter("error")
  public static void textInputLayoutError(TextInputLayout textInputLayout, String error) {
    textInputLayout.setErrorEnabled(error != null && !error.isEmpty());
    textInputLayout.setError(error);
  }

  @android.databinding.BindingAdapter("resource")
  public static void setResourceImage(ImageView imageView, Integer resource) {
    if (resource != null) {
      imageView.setImageResource(resource);
    }
  }

  @android.databinding.BindingAdapter(value = {"gone"})
  public static void setVisibilityGone(View view, boolean state) {
    view.setVisibility(state ? View.VISIBLE : View.GONE);
  }

  @android.databinding.BindingAdapter(value = {"invisible"})
  public static void setVisibilityInvisible(View view, boolean state) {
    view.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
  }

  @android.databinding.BindingAdapter("enable")
  public static void setEnable(View view, boolean enable) {
    view.setEnabled(enable);
  }

  @android.databinding.BindingAdapter(value = {"pressed", "resource_pressed"}, requireAll = false)
  public static void setPressedView(View view, boolean isPressed, int resource) {

    int colorPressed = ResourceUtils.with(view.getContext()).color(isPressed ? R.color.state_error_view_pressed : R.color.state_error_view_unpressed);

    if (view instanceof TextView) {
      ((TextView) view).setTextColor(colorPressed);
    } else if (view instanceof ImageView) {
      if (resource > EMPTY_DATA_RESOURCE) {
        ImageUtils imageUtils = new ImageUtils(view.getContext());
        Bitmap imageBitmap = imageUtils.changeColorByResource(resource, colorPressed);
        ((ImageView) view).setImageBitmap(imageBitmap);
      }
    }
  }

  @android.databinding.BindingAdapter(value = {"pressed_listener", "action_pressed", "pressed_listener_on_change"}, requireAll = false)
  public static void setPressedListener(View view, boolean pressedEnable, Runnable actionPressed, InverseBindingListener listener) {
    view.setOnTouchListener((v, event) -> {
      int eventAction = event.getAction();
      pressed = eventAction == MotionEvent.ACTION_DOWN || !(eventAction == MotionEvent.ACTION_UP);
      listener.onChange();

      if (!pressed && actionPressed != null)
        actionPressed.run();
      return true;
    });
  }

  @InverseBindingAdapter(attribute = "pressed_listener", event = "pressed_listener_on_change")
  public static boolean getPressedListenerResult(View view) {
    return pressed;
  }

  @android.databinding.BindingAdapter("style_progress")
  public static void styleProgress(ProgressBar progressBar, boolean isStyle) {
    if (isStyle) {
      progressBar.setIndeterminate(true);
      progressBar.getIndeterminateDrawable().setColorFilter(ResourceUtils.with(progressBar.getContext()).color(R.color.state_progress_progress_bar), Mode.MULTIPLY);
    }
  }

  @android.databinding.BindingAdapter(value = {"action_swipe", "state"})
  public static void setDataForSwipeRefresh(SwipeRefreshLayout swipeRefresh, Runnable action, boolean stateRefreshing) {
    swipeRefresh.setColorSchemeColors(ResourceUtils.with(swipeRefresh.getContext()).color(R.color.swipe_refresh_layout_scheme_colors_main));
    swipeRefresh.setOnRefreshListener(() -> {
      if (swipeRefresh.isRefreshing() && action != null)
        action.run();
    });
    swipeRefresh.setRefreshing(stateRefreshing);
  }

  @android.databinding.BindingAdapter("onClick")
  public static void setOnClick(View view, Runnable event) {
    view.setOnClickListener(v -> {
      if (event != null)
        event.run();
    });
  }

  @android.databinding.BindingAdapter("listener")
  public static void setValidation(TextInputEditText editText, Runnable event) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (editable != null && event != null) {
          event.run();
        }
      }
    });
  }

  @android.databinding.BindingAdapter(value = {"padding_left", "padding_right", "color_separator"}, requireAll = false)
  public static void separatorSetting(View view, int paddingLeft, int paddingRight, int colorSeparator) {
    view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, view.getPaddingBottom());
    view.setBackgroundColor(colorSeparator);
  }

}


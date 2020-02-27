package com.lfh.custom.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 加载对话框，提示消息可以自定义。
 * Created by lifuhai on 2017/2/6 0006.
 */
public class LoadingDialogFragment extends DialogFragment {
    private static final String PARAM = "param";

    public static LoadingDialogFragment newInstance(LoadingDialogParam pParam) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM, pParam);
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean cancelable = false;
        Bundle bundle = getArguments();

        if (null != bundle) {
            LoadingDialogParam param = bundle.getParcelable(PARAM);

            if (null != param) {
                cancelable = param.isCancelable();
            }
        }

        setCancelable(cancelable);

        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String message = "";
        View view = null;
        Bundle bundle = getArguments();

        if (null != bundle) {
            LoadingDialogParam param = bundle.getParcelable(PARAM);

            if (null != param) {
                message = param.getMessage();
            }
        }

        final Window window = getDialog().getWindow();

        if (null != window) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.dimAmount = 0.3f;
            window.setAttributes(lp);
            view = LayoutInflater.from(getContext()).inflate(R.layout.general_dialog_loading_fragment, ((ViewGroup) window.findViewById(android.R.id.content)), false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_fragent_loading);
            Animation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            //循环
            animation.setRepeatCount(-1);
            animation.setDuration(1000);
            // 匀速
            animation.setInterpolator(new LinearInterpolator());
            imageView.setAnimation(animation);
            TextView tvMessage = (TextView) view.findViewById(R.id.tv_fragent_loading_message);

            if (!TextUtils.isEmpty(message)) {
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(message);
            } else {
                tvMessage.setVisibility(View.GONE);
            }

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, dip2px(getContext(), 101f));

        }

        return view;
    }

    private int dip2px(Context context, float dpValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics);

        return (int) px;
    }
}

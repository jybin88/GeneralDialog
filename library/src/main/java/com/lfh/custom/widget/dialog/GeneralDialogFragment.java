package com.lfh.custom.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 常规对话框。包含标题、提示信息、三个按钮
 * Created by lifuhai on 2017/1/25 0025.
 */
public class GeneralDialogFragment extends DialogFragment {
    private static final String PARAM = "param";
    private OnButtonClickListener mOnButtonClickListener;
    private OnNegativeButtonClickListener mOnNegativeButtonClickListener;
    private OnPositiveButtonClickListener mOnPositiveButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener pOnButtonClickListener) {
        mOnButtonClickListener = pOnButtonClickListener;
    }

    public void setOnNegativeButtonClickListener(OnNegativeButtonClickListener pOnNegativeButtonClickListener) {
        mOnNegativeButtonClickListener = pOnNegativeButtonClickListener;
    }

    public void setOnPositiveButtonClickListener(OnPositiveButtonClickListener pOnPositiveButtonClickListener) {
        mOnPositiveButtonClickListener = pOnPositiveButtonClickListener;
    }

    public static GeneralDialogFragment newInstance(DialogParam pParam) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARAM, pParam);
        GeneralDialogFragment fragment = new GeneralDialogFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "";
        String message = "";
        String leftText = "";
        String negativeText = "";
        String positiveText = "";

        Bundle bundle = getArguments();

        if (null != bundle) {
            DialogParam param = bundle.getParcelable(PARAM);

            if (null != param) {
                title = param.getTitle();
                message = param.getMessage();
                leftText = param.getLeftButtonText();
                negativeText = param.getNegativeText();
                positiveText = param.getPositionText();
            }
        }


        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(getContext()).inflate(R.layout.general_dialog_button_fragment, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_dialog_message);
        TextView tvLeftButton = (TextView) view.findViewById(R.id.tv_dialog_left_button);
        TextView tvNegativeButton = (TextView) view.findViewById(R.id.tv_dialog_negative_button);
        TextView tvPositiveButton = (TextView) view.findViewById(R.id.tv_dialog_positive_button);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        if (!TextUtils.isEmpty(message)) {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(message);
        }

        if (!TextUtils.isEmpty(leftText)) {
            tvLeftButton.setVisibility(View.VISIBLE);
            tvLeftButton.setText(leftText);
            tvLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View pView) {
                    if (null != mOnButtonClickListener) {
                        mOnButtonClickListener.onButtonClick();
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(negativeText)) {
            tvNegativeButton.setVisibility(View.VISIBLE);
            tvNegativeButton.setText(negativeText);
            tvNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnNegativeButtonClickListener) {
                        mOnNegativeButtonClickListener.onNegativeButtonClick();
                    }
                }
            });

        }

        if (!TextUtils.isEmpty(positiveText)) {
            tvPositiveButton.setVisibility(View.VISIBLE);
            tvPositiveButton.setText(positiveText);
            tvPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnPositiveButtonClickListener) {
                        mOnPositiveButtonClickListener.onPositionButtonClick();
                    }
                }
            });
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        setCancelable(false);

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressWarnings("ConstantConditions")
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();

        if (null != lp) {
            lp.dimAmount = 0.3f;
            getDialog().getWindow().setAttributes(lp);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    public interface OnNegativeButtonClickListener {
        void onNegativeButtonClick();
    }

    public interface OnPositiveButtonClickListener {
        void onPositionButtonClick();
    }
}

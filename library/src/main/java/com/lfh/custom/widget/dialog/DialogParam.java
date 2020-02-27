package com.lfh.custom.widget.dialog;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

/**
 * 对话框参数
 * Created by lifuhai on 2017/1/25 0025.
 */
public class DialogParam implements Parcelable {
    private String mTitle;
    private String mMessage;
    private String mLeftButtonText;
    private String mNegativeText;
    private String mPositionText;
    private int mTipImageResId;

    protected DialogParam(Parcel in) {
        mTitle = in.readString();
        mMessage = in.readString();
        mLeftButtonText = in.readString();
        mNegativeText = in.readString();
        mPositionText = in.readString();
        mTipImageResId = in.readInt();
    }

    public static final Creator<DialogParam> CREATOR = new Creator<DialogParam>() {
        @Override
        public DialogParam createFromParcel(Parcel in) {
            return new DialogParam(in);
        }

        @Override
        public DialogParam[] newArray(int size) {
            return new DialogParam[size];
        }
    };

    public static Builder builder() {
        return new Builder();
    }

    private DialogParam(Builder pBuilder) {
        mTitle = pBuilder.mTitle;
        mMessage = pBuilder.mMessage;
        mLeftButtonText = pBuilder.mLeftButtonText;
        mNegativeText = pBuilder.mNegativeText;
        mPositionText = pBuilder.mPositionText;
        mTipImageResId = pBuilder.mTipImageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getLeftButtonText() {
        return mLeftButtonText;
    }

    public String getNegativeText() {
        return mNegativeText;
    }

    public String getPositionText() {
        return mPositionText;
    }

    public int getTipImageResId() {
        return mTipImageResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pParcel, int pI) {
        pParcel.writeString(mTitle);
        pParcel.writeString(mMessage);
        pParcel.writeString(mLeftButtonText);
        pParcel.writeString(mNegativeText);
        pParcel.writeString(mPositionText);
        pParcel.writeInt(mTipImageResId);
    }

    /**
     * 参数构造者类
     */
    public static class Builder {
        private String mTitle;
        private String mMessage;
        private String mLeftButtonText;
        private String mNegativeText;
        private String mPositionText;
        private int mTipImageResId;

        public Builder setTitle(String pTitle) {
            mTitle = pTitle;

            return this;
        }

        public Builder setMessage(String pMessage) {
            mMessage = pMessage;

            return this;
        }

        public Builder setLeftButtonText(String pLeftButtonText) {
            mLeftButtonText = pLeftButtonText;

            return this;
        }

        public Builder setNegativeText(String pNegativeText) {
            mNegativeText = pNegativeText;

            return this;
        }

        public Builder setPositionText(String pPositionText) {
            mPositionText = pPositionText;

            return this;
        }

        public Builder setTipImageResId(@DrawableRes int pTipImageResId) {
            mTipImageResId = pTipImageResId;

            return this;
        }

        public DialogParam build() {
            return new DialogParam(this);
        }
    }
}

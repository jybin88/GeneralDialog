package com.lfh.custom.widget.dialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 加载对话框参数
 * Created by lifuhai on 2017/2/6 0006.
 */
public class LoadingDialogParam implements Parcelable {
    private String mMessage;
    private boolean mCancelable;

    private LoadingDialogParam(Builder pBuilder) {
        this.mMessage = pBuilder.mMessage;
        this.mCancelable = pBuilder.mCancelable;
    }

    public static Builder builder() {
        return new Builder();
    }

    protected LoadingDialogParam(Parcel in) {
        mMessage = in.readString();
        mCancelable = in.readByte() != 0;
    }

    public static final Creator<LoadingDialogParam> CREATOR = new Creator<LoadingDialogParam>() {
        @Override
        public LoadingDialogParam createFromParcel(Parcel in) {
            return new LoadingDialogParam(in);
        }

        @Override
        public LoadingDialogParam[] newArray(int size) {
            return new LoadingDialogParam[size];
        }
    };

    public String getMessage() {
        return mMessage;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pParcel, int pI) {
        pParcel.writeString(mMessage);
        pParcel.writeByte((byte) (mCancelable ? 1 : 0));
    }

    /**
     * 参数构造者类
     */
    public static class Builder {
        private String mMessage;
        private boolean mCancelable;

        public Builder setMessage(String pMessage) {
            mMessage = pMessage;
            return this;
        }

        public Builder setCancelable(boolean pCancelable) {
            mCancelable = pCancelable;
            return this;
        }

        public LoadingDialogParam build() {
            return new LoadingDialogParam(this);
        }
    }
}

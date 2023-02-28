package com.example.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import com.zy.zlog.ZLog;


public class ApkInfo implements Parcelable {
    private static final String TAG = "ApkInfo";

    String pkgName;
    String filePath;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pkgName);
        dest.writeString(this.filePath);
    }

    public ApkInfo(String pkgName, String filePath) {
        this.pkgName = pkgName;
        this.filePath = filePath;
    }

    public ApkInfo() {
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {

        this.pkgName = pkgName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        ZLog.d(TAG, "setFilePath:" + filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "ApkInfo{" +
                "pkgName='" + pkgName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}' + hashCode();
    }

    protected ApkInfo(Parcel in) {
        this.pkgName = in.readString();
        this.filePath = in.readString();
    }

    public static final Parcelable.Creator<ApkInfo> CREATOR = new Parcelable.Creator<ApkInfo>() {
        @Override
        public ApkInfo createFromParcel(Parcel source) {
            return new ApkInfo(source);
        }

        @Override
        public ApkInfo[] newArray(int size) {
            return new ApkInfo[size];
        }
    };
}

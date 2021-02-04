package com.wx1998.maptrack;


import android.content.Context;
import android.location.Location;
import android.telephony.TelephonyManager;


/**
 * 坐标数据模型
 */
public class LocationDO {

    //用户
    String user = "";
    //时间
    long time;
    //经度
    private double mLatitude = 0.0;
    //维度
    private double mLongitude = 0.0;
    //地理对象
    Location location;

    public LocationDO(String user) {
        if ("".equals(user)){
            setUser();
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUser() {
        TelephonyManager tm = (TelephonyManager)MainActivity.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        this.user = tm.getDeviceId();

    }
}

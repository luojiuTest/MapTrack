package com.wx1998.maptrack;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 坐标数据监听
 */
public class LocationListener implements android.location.LocationListener {

    DatabaseHelper databaseHelper;

    public void onLocationChanged(Location location) {
        //调试信息
//        Toast.makeText(MainActivity.getInstance(), "onLocationChanged" + location.toString(), Toast.LENGTH_SHORT).show();
        Log.e("LocationListener", "onLocationChanged" + location.toString());

        //写入地理信息到数据库
        databaseHelper = new DatabaseHelper(MainActivity.getInstance(), "location_db", null, 1);

        MainActivity.girList.add(location.getLatitude()+"=="+location.getLongitude()+"="+new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
        MainActivity.textView.setAdapter(new MainActivity.MyAdapter ());


    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(MainActivity.getInstance(), "onStatusChanged" + status, Toast.LENGTH_SHORT).show();
        Log.e("LocationListener", "onStatusChanged" + status);

    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(MainActivity.getInstance(), "onProviderEnabled", Toast.LENGTH_SHORT).show();
        Log.e("LocationListener", "onProviderEnabled");

    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.getInstance(), "onProviderDisabled", Toast.LENGTH_SHORT).show();
        Log.e("LocationListener", "onProviderDisabled");

    }

}
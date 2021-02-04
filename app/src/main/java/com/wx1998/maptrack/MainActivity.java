package com.wx1998.maptrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MainActivity application;
    public static ListView textView;
    private Button button1;

    public static List<String> girList;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = this;
        girList = new ArrayList<String>();
        //注册控件
        setListeners();
        //启动服务
//        startService();

        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//      测试用代码
//        List<String> list = locationManager.getProviders(true);
//        LocationProvider lpGps = locationManager.getProvider(LocationManager.GPS_PROVIDER);
//        LocationProvider lpNet = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
//        LocationProvider lpPsv = locationManager.getProvider(LocationManager.PASSIVE_PROVIDER);
//        Log.e("gzq", "name:" + lpGps+","+ lpNet+","+ lpPsv+",");

        // Criteria是一组筛选条件
        Criteria criteria = new Criteria();
        //设置定位精准度
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //是否要求海拔
        criteria.setAltitudeRequired(false);
        //是否要求方向
        criteria.setBearingRequired(false);
        //是否要求收费
        criteria.setCostAllowed(true);
        //是否要求速度
        criteria.setSpeedRequired(true);
        //设置电池耗电要求
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        //设置方向精确度
        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
        //设置速度精确度
        criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);
        //设置水平方向精确度
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        //设置垂直方向精确度
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

        //返回满足条件的当前设备可用的provider，第二个参数为false时返回当前设备所有provider中最符合条件的那个provider，但是不一定可用
//        String mProvider = locationManager.getBestProvider(criteria, true);
//        if (mProvider != null) {Log.e("gzq", "mProvider:" + mProvider); }

        //监听
        locationListener = new LocationListener();
        //鉴权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, locationListener);
        }
        //注册
        locationManager.removeUpdates(locationListener);

        //每秒获取一次
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                textView.setAdapter(new MyAdapter());
            }
        });
    }



    private void setListeners() {
        button1 = findViewById(R.id.bt1);
        textView = findViewById(R.id.tv1);
    }

    public void startService() {
        startService(new Intent(getBaseContext(), TrackService.class));
    }

    public void stopService() {
        stopService(new Intent(getBaseContext(), TrackService.class));
    }

    public static MainActivity getInstance() {
        return application;
    }

    //适配器
    static class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return girList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv = new TextView(MainActivity.getInstance());

            tv.setTextSize(18);
            //获取集合中的元素
            String girl = girList.get(position);
            tv.setText(girl.toString());

            return tv;
        }

    }
}
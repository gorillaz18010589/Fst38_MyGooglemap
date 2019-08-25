package tw.brad.apps.brad38mymap;
//goggle map sdk api :https://developers.google.com/maps/documentation/?hl=zh-tw
//=>Map Objects:
//=>Camera and View
//1.build.gradle(app) 加上implementation 'com.google.android.gms:play-services-maps:17.0.0'
//2.把這個fragment xmlns:android="http://schemas.android.com/apk/res/android",掛上去xml
//3.實做 OnMapReadyCallback
//4.取得遠端map導入我的寫的Mycallback方法
//5.複製google_maps_api 貼上values
//6.檔案總館加上權限 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
//7.在google網頁新增一個項目讓專案同個key可以玩家上tw.brad.apps.brad38mymap,同個key儲存
//另外單元android httpurlconnection https ,android volley https
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    private  GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //4.取得遠端map導入我的寫的Mycallback方法
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new MyCallback());//這裡取得遠端map的物件(用我寫的方法)
    }
    //按下去zoon int
    public void test1(View view) {
//        googleMap.setMyLocationEnabled();設定使用者所在的位置
        //googleMap.setIndoorEnabled();//設定室內
        //googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);//顯示地形圖
//        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);//顯示遠景圖

        googleMap.animateCamera(CameraUpdateFactory.zoomIn());//點下去直接zoonin



    }
    //按下去zoon out
    public void test2(View view) {
        googleMap.animateCamera(CameraUpdateFactory.zoomOut());//點下去直接zoonout


    }
    //按下去到指定經緯度
    public void test3(View view) {
        //指定到我要去的經緯度
        LatLng nowPos =googleMap.getCameraPosition().target;
        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(nowPos,12.0f));
    }

    //3.實做OnMapReady
    private class MyCallback implements OnMapReadyCallback{
        //當地圖準備好時候
        @Override
        public void onMapReady(GoogleMap googleMap) {
            Log.v("brad","ok");
           MainActivity.this.googleMap = googleMap; //呼叫Mainactivy.的我這個裡面.googleMap物件實體

           //家上鏡頭
//            LatLng sydney = new LatLng(24, 120);//修改(精度,緯度)
//            MainActivity.this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));//這個畫面移動到寫好的(經緯度)

            //一開始就拉到你指定的位置
//            LatLng tw = new LatLng(23.476525, 120.449937);
//            LatLng tw =googleMap.getCameraPosition().target;
//            googleMap.animateCamera(
//                    CameraUpdateFactory.newLatLngZoom(tw,12.0f));



            LatLng tw = new LatLng(23.476525, 120.449937);//設定台灣做標
            MainActivity.this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(tw));
            googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(tw, 12.0f));

            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    Log.v("brad", "zoom:" + cameraPosition.zoom);
                }
            });
        }


    }
}

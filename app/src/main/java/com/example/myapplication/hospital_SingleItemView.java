package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.FragmentManager;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class hospital_SingleItemView extends Activity implements OnMapReadyCallback {
    // Declare Variables
    TextView txthospital;
    String hospital;
    ViewFlipper v_fllipper;

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_detail);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.hospital_detail_googleMap);

        mapFragment.getMapAsync(this);

        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        hospital = i.getStringExtra("hospital");

        // Locate the TextViews in singleitemview.xml
        txthospital = findViewById(R.id.hospital);

        // Load the results into the TextViews
        txthospital.setText(hospital);

        int[] images = {
                R.drawable.cat,
                R.drawable.dog,
        };
        v_fllipper = findViewById(R.id.hospital_detail_imgslide);

        for (int image : images) {
            fllipperImages(image);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(37.300020, 126.981537);    //위도, 경도
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("천천중");
        markerOptions.snippet("학교");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
    // 이미지 슬라이더 구현 메서드
    public void fllipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_fllipper.addView(imageView);      // 이미지 추가
        v_fllipper.setFlipInterval(2000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_fllipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

}
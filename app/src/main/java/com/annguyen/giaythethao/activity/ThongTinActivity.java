package com.annguyen.giaythethao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.annguyen.giaythethao.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;

public class ThongTinActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Toolbar toolbarThongTinAnNguyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        toolbarThongTinAnNguyen = findViewById(R.id.toolbarThongTin);
        setMyActionBar();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarThongTinAnNguyen);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbarThongTinAnNguyen.setNavigationIcon(android.R.drawable.ic_media_previous);

        //Sự kiện khi click ActionBar
        toolbarThongTinAnNguyen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng AnsShoes = new LatLng(16.081360, 108.219036);
        mMap.addMarker(new MarkerOptions().position(AnsShoes).title("Shop giày An's Shoes.vn").snippet("Địa chỉ: Hai Chau, Da Nang").icon(BitmapDescriptorFactory.defaultMarker()));

        //Trỏ camera đến vị trí shop khi google run lên, và zoom vị trí lên
        CameraPosition cameraPosition = new CameraPosition.Builder().target(AnsShoes).zoom(13).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}

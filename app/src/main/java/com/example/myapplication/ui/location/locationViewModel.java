package com.example.myapplication.ui.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;
import com.example.myapplication.ui.search.SearchActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class locationViewModel extends Fragment implements OnMapReadyCallback {
    ArrayList<Double> lat_list;
    ArrayList<Double> lng_list;
    ArrayList<String> name_list;
    ArrayList<String> vicinity_list;

    ArrayList<Marker> markers_list;
    String[] category_name_array={
            "모두","ATM","은행","미용실","카페","교회","주유소","식당"
    };
    String[] category_value_array = {
            "all","atm","bank","beauty_salon","cafe","church","gas_station","restaurant"
    };
    ImageButton imageButton;
    private static final int REQUEST_CODE_PERMISSIONS = 1000;
   
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    locationModalFrag locationModalFrag;

    FragmentManager fm;
    GoogleMap mMap;
    MapView mapView;
    List<Marker> previous_marker = null;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        View view = inflater.inflate(R.layout.fragment_location2, container, false);
        fm = getActivity().getSupportFragmentManager();
        locationModalFrag=new locationModalFrag();
        mapView = (MapView) view.findViewById(R.id.mapView);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            previous_marker = new ArrayList<Marker>();

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        lat_list = new ArrayList<>();
        lng_list = new ArrayList<>();
        name_list = new ArrayList<>();
        vicinity_list = new ArrayList<>();
        markers_list = new ArrayList<>();

        LinearLayout layout = view.findViewById(R.id.layoutSearch);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {

        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng map = new LatLng(37.52839, 126.70866);
        mMap.addMarker(new MarkerOptions().position(map).title("효성동 미도아파트"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(map));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSIONS);
        }



        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            private static final int REQUEST_CODE_PERMISSIONS = 1000;

            @Override
            public boolean onMyLocationButtonClick() {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                            REQUEST_CODE_PERMISSIONS);
                }

//                mMap.moveCamera(CameraUpdateFactory.newLatLng(map));

                return true;
            }
        });
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(mylocation)
                            .title("현재위치"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                locationModalFrag.show(fm,"locationModalFrg");
                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                            REQUEST_CODE_PERMISSIONS);{
                        Toast.makeText(getContext(),"권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                    }
                }


        }

    }
}
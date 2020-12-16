package com.example.trolleyhood;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class SetLocation extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerDragListener {
    private GoogleMap mMap;
    private Geocoder geocoder;
    Button buttonSearchAddress, buttonConfirmLocation;
    private EditText editTextStreet, editTextBuildingNo, editTextApartmentNo, editTextCity;
    private String street, building, apartment, city;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        geocoder = new Geocoder(this);

        buttonConfirmLocation = (Button) findViewById(R.id.buttonConfirmLocation);
        buttonSearchAddress = (Button) findViewById(R.id.buttonSearchAddress);
        buttonConfirmLocation.setOnClickListener(this);
        buttonSearchAddress.setOnClickListener(this);

        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextBuildingNo = (EditText) findViewById(R.id.editTextBuildingNo);
        editTextApartmentNo = (EditText) findViewById(R.id.editTextApartmentNo);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonConfirmLocation:
                confirmLocation();
                startActivity(new Intent(getApplicationContext(), HelpOrAsk.class));
                break;
            case R.id.buttonSearchAddress:
                searchAddress();
                break;
        }
    }

    private void confirmLocation() {
        UserAddress address = new UserAddress(street, building, apartment, city, latLng);
    }

    private void searchAddress() {
        street = editTextStreet.getText().toString().trim();
        building = editTextBuildingNo.getText().toString().trim();
        apartment= editTextApartmentNo.getText().toString().trim();
        city = editTextCity.getText().toString().trim();
        String addressName = street + ' ' + building + ", " + city;


        try {
            List<Address> addresses = geocoder.getFromLocationName(addressName, 1);
            Address address = addresses.get(0);
            latLng = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(latLng);
            mMap.addMarker(markerOptions).isDraggable();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        latLng = marker.getPosition();
    }
}
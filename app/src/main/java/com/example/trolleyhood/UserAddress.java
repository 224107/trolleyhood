package com.example.trolleyhood;

import com.google.android.gms.maps.model.LatLng;

public class UserAddress {
    public String street, buildingNo, apartmentNo, city;
    public LatLng latLng;

    public UserAddress(String street, String buildingNo, String apartmentNo, String city, LatLng latLng) {
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.city = city;
        this.latLng = latLng;
    }
}

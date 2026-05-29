package com.indivaragroup.lambda.exam.property.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PropertyAsset {
    private String id;
    private String propertyName;
    private String propertyType;
    private String location;
    private double price;
    private int areaSize;
    private int buildingSize;
    private boolean wasSold;
    private int yearBuilt;

    public PropertyAsset() {
    }

    public PropertyAsset(String id) {
        this.id = id;
    }

    public PropertyAsset(String id, String propertyName, String propertyType, String location, double price, int areaSize, int buildingSize, boolean wasSold, int yearBuilt) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.location = location;
        this.price = price;
        this.areaSize = areaSize;
        this.buildingSize = buildingSize;
        this.wasSold = wasSold;
        this.yearBuilt = yearBuilt;
    }

    public void cetak() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        String formatHarga = formatter.format(price);
        System.out.println("ID: " + id + ", Nama: " + propertyName + ", Tipe: " + propertyType +
                ", Lokasi: " + location + ", Harga: Rp " + formatHarga +
                ", Luas: " + areaSize + " m², Status: " + (wasSold ? "Terjual" : "Belum Terjual"));
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getAreaSize() { return areaSize; }
    public void setAreaSize(int areaSize) { this.areaSize = areaSize; }

    public int getBuildingSize() { return buildingSize; }
    public void setBuildingSize(int buildingSize) { this.buildingSize = buildingSize; }

    public boolean isWasSold() { return wasSold; }
    public void setWasSold(boolean wasSold) { this.wasSold = wasSold; }

    public int getYearBuilt() { return yearBuilt; }
    public void setYearBuilt(int yearBuilt) { this.yearBuilt = yearBuilt; }
}

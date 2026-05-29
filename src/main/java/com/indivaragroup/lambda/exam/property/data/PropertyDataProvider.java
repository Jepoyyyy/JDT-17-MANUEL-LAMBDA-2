package com.indivaragroup.lambda.exam.property.data;

import com.indivaragroup.lambda.exam.property.model.PropertyAsset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PropertyDataProvider implements Supplier<List<PropertyAsset>> {
    @Override
    public List<PropertyAsset> get() {
        List<PropertyAsset> list = new ArrayList<>();
        list.add(new PropertyAsset("P01", "Rumah Cluster Emerald", "RUMAH", "Bekasi Timur", 1200000000.0, 120, 90, false, 2020));
        list.add(new PropertyAsset("P02", "Apartemen Grand Bekasi", "APARTEMEN", "Bekasi Barat", 650000000.0, 0, 45, true, 2019));
        list.add(new PropertyAsset("P03", "Ruko Jalan Ahmad Yani", "RUKO", "Bekasi Selatan", 2500000000.0, 80, 160, false, 2018));
        list.add(new PropertyAsset("P04", "Tanah Kavling Jatibening", "TANAH", "Bekasi Timur", 800000000.0, 200, 0, false, 0));
        list.add(new PropertyAsset("P05", "Rumah Minimalis Pekayon", "RUMAH", "Bekasi Selatan", 950000000.0, 90, 70, true, 2021));
        list.add(new PropertyAsset("P06", "Apartemen Meikarta Tower B", "APARTEMEN", "Bekasi Barat", 450000000.0, 0, 36, false, 2022));
        list.add(new PropertyAsset("P07", "Ruko Galaxy Bekasi", "RUKO", "Bekasi Utara", 3100000000.0, 100, 200, true, 2017));
        list.add(new PropertyAsset("P08", "Rumah Mewah Kemang Pratama", "RUMAH", "Bekasi Selatan", 4500000000.0, 300, 250, false, 2023));
        return list;
    }
}
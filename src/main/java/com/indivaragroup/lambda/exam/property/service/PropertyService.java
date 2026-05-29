package com.indivaragroup.lambda.exam.property.service;

import com.indivaragroup.lambda.exam.property.model.PropertyAsset;
import com.indivaragroup.lambda.exam.property.util.PropertyFormatter;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PropertyService {

    private final List<PropertyAsset> products;

    public PropertyService(List<PropertyAsset> products) {
        this.products = products;
    }

    public void tampilkanSemuaProperty() {
        products.forEach(PropertyAsset::cetak);
    }

    public void filterBelumTerjual() {
        List<PropertyAsset> copyList = new ArrayList<>(products);
        copyList.removeIf(PropertyAsset::isWasSold);
        copyList.forEach(PropertyAsset::cetak);
    }

    public void sortingHargaAscending() {
        List<PropertyAsset> copyList = new ArrayList<>(products);
        copyList.sort(Comparator.comparingDouble(PropertyAsset::getPrice));
        copyList.forEach(PropertyAsset::cetak);
    }

    public void sortingHargaDescending() {
        List<PropertyAsset> copyList = new ArrayList<>(products);
        copyList.sort(Comparator.comparingDouble(PropertyAsset::getPrice).reversed());
        copyList.forEach(PropertyAsset::cetak);
    }

    public void filterStreamSkenario() {
        System.out.println("1. Tipe RUMAH saja:");
        products.stream().filter(p -> p.getPropertyType().equals("RUMAH")).forEach(PropertyAsset::cetak);

        System.out.println("\n2. Lokasi Bekasi Selatan:");
        products.stream().filter(p -> p.getLocation().equals("Bekasi Selatan")).forEach(PropertyAsset::cetak);

        System.out.println("\n3. Harga di bawah 1 Milyar:");
        products.stream().filter(p -> p.getPrice() < 1000000000.0).forEach(PropertyAsset::cetak);

        System.out.println("\n4. Luas Tanah > 100m² DAN Belum Terjual:");
        Predicate<PropertyAsset> luasLebih100 = p -> p.getAreaSize() > 100;
        Predicate<PropertyAsset> belumTerjual = p -> !p.isWasSold();
        products.stream().filter(luasLebih100.and(belumTerjual)).forEach(PropertyAsset::cetak);
    }

    public void transformasiData() {
        System.out.println("1 & 2. Nama Property UPPERCASE:");
        products.stream()
                .map(PropertyAsset::getPropertyName)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\n3. Format Harga ke Rupiah:");
        products.stream()
                .map(PropertyAsset::getPrice)
                .map(PropertyFormatter::formatRupiah)
                .forEach(System.out::println);
    }

    public void hitungTotalNilaiAssets() {
        double totalTersedia = products.stream()
                .filter(p -> !p.isWasSold())
                .mapToDouble(PropertyAsset::getPrice)
                .sum();
        System.out.println("Total harga property belum terjual: " + PropertyFormatter.formatRupiah(totalTersedia));

        System.out.println("\nRata-rata harga per tipe:");
        Map<String, Double> rataRataPerTipe = products.stream()
                .collect(Collectors.groupingBy(
                        PropertyAsset::getPropertyType,
                        Collectors.averagingDouble(PropertyAsset::getPrice)
                ));
        rataRataPerTipe.forEach((tipe, rata) ->
                System.out.println(tipe + " : " + PropertyFormatter.formatRupiah(rata)));
    }

    public void groupingCollectors() {
        System.out.println("1. Group berdasarkan Tipe:");
        Map<String, List<PropertyAsset>> byTipe = products.stream().collect(Collectors.groupingBy(PropertyAsset::getPropertyType));
        byTipe.forEach((tipe, list) -> {
            System.out.println("[" + tipe + "]");
            list.forEach(PropertyAsset::cetak);
        });

        System.out.println("\n2. Group berdasarkan Lokasi (Count):");
        Map<String, Long> countByLokasi = products.stream().collect(Collectors.groupingBy(PropertyAsset::getLocation, Collectors.counting()));
        countByLokasi.forEach((lokasi, count) -> System.out.println(lokasi + " : " + count + " property"));

        System.out.println("\n3. Group berdasarkan Status Terjual:");
        Map<Boolean, List<PropertyAsset>> byStatus = products.stream().collect(Collectors.groupingBy(PropertyAsset::isWasSold));
        System.out.println("[TERJUAL]");
        byStatus.getOrDefault(true, Collections.emptyList()).forEach(PropertyAsset::cetak);
        System.out.println("[TERSEDIA / BELUM TERJUAL]");
        byStatus.getOrDefault(false, Collections.emptyList()).forEach(PropertyAsset::cetak);
    }

    public PropertyAsset buatPropertyBaru(Supplier<PropertyAsset> supplier) {
        return supplier.get();
    }

    public void customComparatorChained() {
        List<PropertyAsset> copyList1 = new ArrayList<>(products);
        copyList1.sort(
                Comparator.comparing(PropertyAsset::getLocation)
                        .thenComparing(Comparator.comparingDouble(PropertyAsset::getPrice).reversed())
        );
        System.out.println("1. Sort Lokasi (Asc) -> Harga (Desc):");
        copyList1.forEach(PropertyAsset::cetak);

        List<PropertyAsset> copyList2 = new ArrayList<>(products);
        copyList2.sort(
                Comparator.comparing(PropertyAsset::getPropertyType)
                        .thenComparing(Comparator.comparingInt(PropertyAsset::getYearBuilt).reversed())
        );
        System.out.println("\n2. Sort Tipe (Asc) -> Tahun Dibangun (Desc):");
        copyList2.forEach(PropertyAsset::cetak);
    }

    public void generateReport() {
        long totalProperty = products.size();
        long terjual = products.stream().filter(PropertyAsset::isWasSold).count();
        long tersedia = totalProperty - terjual;

        double totalNilaiTersedia = products.stream()
                .filter(p -> !p.isWasSold())
                .mapToDouble(PropertyAsset::getPrice)
                .sum();

        Map<String, Long> distribusiLokasi = products.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation, Collectors.counting()));

        System.out.println("========================================");
        System.out.println("   LAPORAN ASSETS PROPERTY BEKASI REALTY GROUP");
        System.out.println("========================================");
        System.out.println("Total Property       : " + totalProperty);
        System.out.println("Property Terjual     : " + terjual);
        System.out.println("Property Tersedia    : " + tersedia);
        System.out.println("\n--- PROPERTY TERSEDIA (Sorted by Harga) ---");

        List<PropertyAsset> listTersedia = products.stream()
                .filter(p -> !p.isWasSold())
                .sorted(Comparator.comparingDouble(PropertyAsset::getPrice))
                .collect(Collectors.toList());

        final int[] index = {1};
        listTersedia.forEach(p -> System.out.println((index[0]++) + ". [" + p.getId() + "] " + p.getPropertyName() + " | " + PropertyFormatter.formatRupiah(p.getPrice())));

        System.out.println("\n--- TOTAL NILAI ASSETS TERSEDIA ---");
        System.out.println("Total: " + PropertyFormatter.formatRupiah(totalNilaiTersedia));
        System.out.println("\n--- DISTRIBUSI PER LOKASI ---");
        distribusiLokasi.forEach((lokasi, count) -> System.out.println(String.format("%-15s : %d property", lokasi, count)));
        System.out.println("========================================");
    }

    // BONUS CHALLENGE 1
    public List<PropertyAsset> filterTipeDanLokasi(String tipe, String lokasi) {
        BiFunction<String, String, List<PropertyAsset>> filterBiFunc = (t, l) -> products.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase(t) && p.getLocation().equalsIgnoreCase(l))
                .collect(Collectors.toList());
        return filterBiFunc.apply(tipe, lokasi);
    }

    // BONUS CHALLENGE 2
    public Map<String, DoubleSummaryStatistics> dapatkanStatistikHargaPerTipe() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        PropertyAsset::getPropertyType,
                        Collectors.summarizingDouble(PropertyAsset::getPrice)
                ));
    }

    // BONUS CHALLENGE 3
    public Optional<PropertyAsset> cariBerdasarkanId(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
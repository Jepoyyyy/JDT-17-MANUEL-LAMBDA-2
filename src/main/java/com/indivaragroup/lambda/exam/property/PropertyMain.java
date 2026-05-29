package com.indivaragroup.lambda.exam.property;

import com.indivaragroup.lambda.exam.property.data.PropertyDataProvider;
import com.indivaragroup.lambda.exam.property.model.PropertyAsset;
import com.indivaragroup.lambda.exam.property.service.PropertyService;
import com.indivaragroup.lambda.exam.property.util.PropertyFormatter;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PropertyMain {
    public static void main(String[] args) {
        PropertyDataProvider provider = new PropertyDataProvider();
        List<PropertyAsset> dataAwal = provider.get();
        PropertyService service = new PropertyService(dataAwal);

        System.out.println("=== TASK 1: TAMPILKAN SEMUA PROPERTY ===");
        service.tampilkanSemuaProperty();
        System.out.println("\n");

        System.out.println("=== TASK 2: FILTER PROPERTY BELUM TERJUAL ===");
        service.filterBelumTerjual();
        System.out.println("\n");

        System.out.println("=== TASK 3: SORTING HARGA ASCENDING ===");
        service.sortingHargaAscending();
        System.out.println("\n--- HARGA DESCENDING (BONUS) ---");
        service.sortingHargaDescending();
        System.out.println("\n");

        System.out.println("=== TASK 4: FILTER PROPERTY DENGAN STREAM ===");
        service.filterStreamSkenario();
        System.out.println("\n");

        System.out.println("=== TASK 5: TRANSFORMASI DATA ===");
        service.transformasiData();
        System.out.println("\n");

        System.out.println("=== TASK 6: HITUNG TOTAL NILAI ASSETS ===");
        service.hitungTotalNilaiAssets();
        System.out.println("\n");

        System.out.println("=== TASK 7: GROUPING DENGAN COLLECTORS ===");
        service.groupingCollectors();
        System.out.println("\n");

        System.out.println("=== TASK 8: CONSTRUCTOR REFERENCE & SUPPLIER ===");
        PropertyAsset assetBaru = service.buatPropertyBaru(PropertyAsset::new);
        System.out.println("Object baru sukses dibentuk via Supplier constructor reference: " + assetBaru);

        Function<String, PropertyAsset> factoryId = PropertyAsset::new;
        PropertyAsset assetDenganId = factoryId.apply("P09");
        System.out.println("Object baru sukses dibentuk via Function constructor reference dengan ID: " + assetDenganId.getId());
        System.out.println("\n");

        System.out.println("=== TASK 9: CUSTOM COMPARATOR CHAINED ===");
        service.customComparatorChained();
        System.out.println("\n");

        System.out.println("=== TASK 10: REPORTING FINAL ===");
        service.generateReport();
        System.out.println("\n");

        System.out.println("=== 🔥 BONUS CHALLENGE 🔥 ===");
        System.out.println("1. BiFunction (RUMAH & Bekasi Selatan):");
        List<PropertyAsset> hasilBonus1 = service.filterTipeDanLokasi("RUMAH", "Bekasi Selatan");
        hasilBonus1.forEach(PropertyAsset::cetak);

        System.out.println("\n2. Map DoubleSummaryStatistics Harga Per Tipe:");
        Map<String, DoubleSummaryStatistics> statsMap = service.dapatkanStatistikHargaPerTipe();
        statsMap.forEach((tipe, stats) -> {
            System.out.println("[" + tipe + "]");
            System.out.println("   Jumlah  : " + stats.getCount());
            System.out.println("   Min     : " + PropertyFormatter.formatRupiah(stats.getMin()));
            System.out.println("   Max     : " + PropertyFormatter.formatRupiah(stats.getMax()));
            System.out.println("   Average : " + PropertyFormatter.formatRupiah(stats.getAverage()));
        });

        System.out.println("\n3. Handle Optional pencarian ID:");
        Optional<PropertyAsset> ditemukan = service.cariBerdasarkanId("P03");
        ditemukan.ifPresent(PropertyAsset::cetak);

        Optional<PropertyAsset> tidakDitemukan = service.cariBerdasarkanId("P99");
        if (tidakDitemukan.isEmpty()) {
            System.out.println("Property ID P99 tidak ditemukan (Handled by Optional Safe).");
        }
    }
}
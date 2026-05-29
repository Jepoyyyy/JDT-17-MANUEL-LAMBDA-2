package com.indivaragroup.lambda.exam.property.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PropertyFormatter {
    public static String formatRupiah(double harga) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return "Rp " + formatter.format(harga);
    }
}

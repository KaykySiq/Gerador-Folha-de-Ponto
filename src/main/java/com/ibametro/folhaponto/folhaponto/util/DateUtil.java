package com.ibametro.folhaponto.folhaponto.util;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {

    public static int getMonth(String monthName) {
        // Converte o nome do mês para o número correspondente
        for (int i = 1; i <= 12; i++) {
            YearMonth yearMonth = YearMonth.of(2023, i); // Ano arbitrário
            String name = yearMonth.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            if (name.equalsIgnoreCase(monthName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Mês inválido: " + monthName);
    }
}

package com.ibametro.folhaponto.folhaponto.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ibametro.folhaponto.folhaponto.util.DateUtil.getMonth;

public class HolidayService {

    public static List<Integer> getHolidays(int year, String mes) {
        int month = getMonth(mes);

        String apiKey = "FzoEC809GIVM1e9lkty7Hch305P6wWmm";
        String country = "BR";
        String urlString = String.format("https://calendarific.com/api/v2/holidays?api_key=%s&country=%s&year=%d&month=%s",
                apiKey, country, year, month);

        List<Integer> holidays = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonObject = new JSONObject(informationString.toString());
                JSONArray holidaysArray = jsonObject.getJSONObject("response")
                        .getJSONArray("holidays");


                // Extrair os dias dos feriados
                for (int i = 0; i < holidaysArray.length(); i++) {
                    JSONObject holiday = holidaysArray.getJSONObject(i);
                    int day = holiday.getJSONObject("date")
                            .getJSONObject("datetime")
                            .getInt("day");
                    holidays.add(day);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return holidays;
    }
}

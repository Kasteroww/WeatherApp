package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;



public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_pressure;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();

            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=f7e35f032cf220c34a4ffe4f77359dc6&units=metric");{
            if (!output.isEmpty()) {

                JSONObject obj = new JSONObject(output);
                temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp")+ " °С");
                temp_feels.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like")+ " °С");
                temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max")+ " °С");
                temp_min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min")+ " °С");
                temp_pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure")+ " мм рт. ст.");

            }
        }
    });

}

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такой город не был найден!");
        }
        return content.toString();
    }
}

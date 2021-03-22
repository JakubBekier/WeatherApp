import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InputPanel {
    public JPanel panel;
    public JLabel inputLabel;
    public JTextField textField;
    public JButton inputButton;

    public InputPanel(){
        this.panel = new JPanel();
        this.inputLabel = new JLabel("Enter a city name");
        this.textField = new JTextField("Warsaw", 20);
        this.inputButton = new JButton("Get weather");

        this.panel.setLayout(null);

        this.inputLabel.setBounds(new Rectangle(135, 50, 140, 100));
        this.inputLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.textField.setBounds(new Rectangle(140, 150, 120, 30));
        this.textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        this.inputButton.setBounds(new Rectangle(140, 200, 120, 50));

        this.panel.add(inputLabel);
        this.panel.add(textField);
        this.panel.add(inputButton);

        this.panel.setVisible(true);

    }

    public void downloadContent(OutputPanel outputPanel, Response response) throws IOException {
        String myResponse = response.body().string();

        JSONObject obj = new JSONObject(myResponse);

        JSONObject temperature = obj.getJSONObject("main");
        String avg = temperature.get("temp").toString();
        float avgF = Float.parseFloat(avg);
        avgF -= 273.15f;
        avgF = (float)Math.round(avgF * 100.0)/100.0f;
        avg = Float.toString(avgF);
        String max = temperature.get("temp_max").toString();
        float maxF = Float.parseFloat(max);
        maxF -= 273.15f;
        maxF = (float)Math.round(maxF * 100.0)/100.0f;
        max = Float.toString(maxF);
        String min = temperature.get("temp_min").toString();
        float minF = Float.parseFloat(min);
        minF -= 273.15f;
        minF = (float)Math.round(minF * 100.0)/100.0f;
        min = Float.toString(minF);
        String app = temperature.get("feels_like").toString();
        float appF = Float.parseFloat(app);
        appF -= 273.15f;
        appF = (float)Math.round(appF * 100.0)/100.0f;
        app = Float.toString(appF);
        String press = temperature.get("pressure").toString();
        String hum = temperature.get("humidity").toString();
        String city = obj.getString("name");

        JSONArray weather = obj.getJSONArray("weather");
        JSONObject weatherObject = (JSONObject) weather.get(0);
        String skyDescr = weatherObject.get("description").toString();


        JSONObject windObj = obj.getJSONObject("wind");
        String speed = windObj.get("speed").toString();

        outputPanel.cityLabel.setText("City: " + city);
        outputPanel.skyLabel.setText("Weather: " + skyDescr);
        outputPanel.tempAvgLabel.setText("Avg Temp: " + avg + "\u2103");
        outputPanel.tempMinLabel.setText("Min Temp: " + min + "\u2103");
        outputPanel.tempMaxLabel.setText("Max Temp: " + max + "\u2103");
        outputPanel.tempApparentLabel.setText("App Temp: " + app + "\u2103");
        outputPanel.pressureLabel.setText("Pressure: " + press + "hPa");
        outputPanel.humidityLabel.setText("Humidity: " + hum + "%");
        outputPanel.windLabel.setText("Wind Speed: " + speed + "m/s");

    }
}

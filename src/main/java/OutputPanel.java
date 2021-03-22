import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class OutputPanel {
    public JPanel panel;
    public JLabel cityLabel;
    public JLabel skyLabel;
    public JLabel tempAvgLabel;
    public JLabel tempMinLabel;
    public JLabel tempMaxLabel;
    public JLabel tempApparentLabel;
    public JLabel pressureLabel;
    public JLabel humidityLabel;
    public JLabel windLabel;
    public JButton goBackButton;
    public GridBagLayout grid;
    public GridBagConstraints gbc;

    public OutputPanel(){
        this.panel = new JPanel();

        this.cityLabel = new JLabel();
        this.skyLabel = new JLabel();
        this.tempAvgLabel = new JLabel();
        this.tempMinLabel = new JLabel();
        this.tempMaxLabel = new JLabel();
        this.tempApparentLabel = new JLabel();
        this.pressureLabel = new JLabel();
        this.humidityLabel = new JLabel();
        this.windLabel = new JLabel();
        this.goBackButton = new JButton("Go back");
        this.grid = new GridBagLayout();
        this.panel.setLayout(grid);
        this.gbc = new GridBagConstraints();
        this.prepareGrid();

    }

    public void prepareGrid(){
        Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        UIManager.put("Label.font", labelFont);

        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.ipadx = 20;
        this.gbc.ipady = 10;
        this.panel.add(this.cityLabel, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.panel.add(this.windLabel,this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.panel.add(this.tempAvgLabel, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.panel.add(this.tempMinLabel, this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.panel.add(this.tempMaxLabel, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 2;
        this.panel.add(this.tempApparentLabel, this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 3;
        this.panel.add(this.pressureLabel, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 3;
        this.panel.add(this.humidityLabel, this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 4;
        this.gbc.gridwidth = 2;
        this.panel.add(this.skyLabel, this.gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 5;
        this.gbc.ipady = 10;
        this.gbc.gridwidth = 2;
        this.panel.add(this.goBackButton, this.gbc);

    }

    public void fillWithData(Response response) throws IOException {
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

        this.cityLabel.setText("City: " + city);
        this.skyLabel.setText("Weather: " + skyDescr);
        this.tempAvgLabel.setText("Avg Temp: " + avg + "\u2103");
        this.tempMinLabel.setText("Min Temp: " + min + "\u2103");
        this.tempMaxLabel.setText("Max Temp: " + max + "\u2103");
        this.tempApparentLabel.setText("App Temp: " + app + "\u2103");
        this.pressureLabel.setText("Pressure: " + press + "hPa");
        this.humidityLabel.setText("Humidity: " + hum + "%");
        this.windLabel.setText("Wind Speed: " + speed + "m/s");
    }

}

import javax.swing.*;
import java.awt.*;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class AppFrame extends JFrame {

    private InputPanel inputPanel = new InputPanel();
    private OutputPanel outputPanel = new OutputPanel();

    String key = "713d261e9b3a42dc8b8ed7145689eee8";

    public AppFrame(){
        super();
        this.setSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Weather app");
        this.setLocationRelativeTo(null);
        this.add(inputPanel);

        inputPanel.inputButton.addActionListener(e -> {
            OkHttpClient client = new OkHttpClient();
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" +
                    inputPanel.textField.getText() +
                    "&appid=" +
                    key;
            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        outputPanel.fillWithData(response);
                        changePanel(outputPanel);
                    }
                }
            });
        });

        outputPanel.goBackButton.addActionListener(e -> changePanel(inputPanel));

        this.changePanel(inputPanel);


        this.setVisible(true);
    }

    public void changePanel(JPanel panel){
        this.getContentPane().removeAll();
        this.add(panel);
        this.validate();
        this.repaint();
    }

}

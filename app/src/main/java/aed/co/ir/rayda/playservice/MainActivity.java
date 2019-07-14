package aed.co.ir.rayda.playservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageIoClientConnection msgio = new MessageIoClientConnection();
        Socket msocket = msgio.getSocket();


        msocket.connect();

        msocket.on("hello", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                String hi;

                try {
                    hi = obj.getString("hi");
                    Log.d("MY MSG.IO", hi);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }
}

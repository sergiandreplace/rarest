package com.sergiandreplace.rarest_test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.sergiandreplace.rarest.service.Api;
import com.sergiandreplace.rarest.xml.XmlLoader;

import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends Activity {
    InputStream is = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            is = getAssets().open("test.xml");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Long time = System.currentTimeMillis();
                Api api = XmlLoader.load(is);
                Log.i("timing", "XmlLoader=" + (System.currentTimeMillis() - time));
                time = System.currentTimeMillis();
                Api api2 = XmlLoader.load(is);
                Log.i("timing", "XmlLoader=" + (System.currentTimeMillis() - time));
                return null;
            }
        }.execute();
    }
}

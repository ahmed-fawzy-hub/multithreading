package com.hanynemr.yat730multithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    ExecutorService pool;

    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        pool = Executors.newFixedThreadPool(4);
        names = Arrays.asList("ahmed", "said", "omar", "alaa", "tamer");
    }

    @Override
    protected void onDestroy() {
        pool.shutdown();
        super.onDestroy();
    }

    public void show(View view) {

        //solution1
//        pool.execute(() -> {
//            int index = names.indexOf("alaa");
//
//            runOnUiThread(() ->tv.setText("found in "+index));
//
//        });

        //solution2
        MyTask task = new MyTask();
        task.execute("alaa");

        MyTask task1 = new MyTask();
        task1.execute(new String[]{"ahmed", "tamer"});


//        task.cancel()

    }

    class MyTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... strings) {
            int index = names.indexOf(strings[0]);
            return index;
        }

        @Override
        protected void onPostExecute(Integer index) {
            super.onPostExecute(index);
            tv.setText("found in " + index);
            Toast.makeText(MainActivity.this, "found in " + index, Toast.LENGTH_SHORT).show();
        }
    }

}
package com.experiments.trieapp.FileIO;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadAsync extends AsyncTask<String, Integer, String[]> {
    private ProgressBar bar;
    private Context context;
    private Results results = null;
    private String FILE_NAME ="english.txt";
    public ReadAsync(Context context, Results results, String FILE_NAME) {
        this.context = context;
        this.results = results;
        this.FILE_NAME = FILE_NAME;
    }
    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    @Override
    protected String[] doInBackground(String... params) {
        try {
            InputStream stream = this.context.getAssets().open(FILE_NAME);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String str = new String(buffer);
            return str.split("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String[] s) {
        super.onPostExecute(s);
        bar.setVisibility(View.INVISIBLE);
        results.processFinish(s);
    }

    @Override
    protected void onProgressUpdate(Integer... val) {
        super.onProgressUpdate(val);
        if (this.bar != null) {
            bar.setProgress(val[0]);
        }
    }
}

package com.experiments.trieapp.Trie;


import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class WordsAsync extends AsyncTask<String,Integer,List<String> > {
    private ProgressBar bar;
    private Context context;
    private TriNode root;
    private String word;
    private Suggestion results;

    public WordsAsync(Suggestion results, TriNode root) {
       this.root = root;
       this.results = results;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        return root.search(root, 0,word);
    }

    public void searchWords(String word){
        this.word = word;
    }

    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<String> s) {
        super.onPostExecute(s);
        bar.setVisibility(View.INVISIBLE);
        results.processFinish(s);
    }
}

package com.experiments.trieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.experiments.trieapp.FileIO.ReadAsync;
import com.experiments.trieapp.FileIO.Results;
import com.experiments.trieapp.KeyBox.OnRVItemClickListener;
import com.experiments.trieapp.KeyBox.RVAdapter;
import com.experiments.trieapp.Trie.TriNode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressBar readProgress;
    private TextView enterWord;
    private TriNode root;
    private List<String> suggestion = new ArrayList<>();

    // Keyboard Code
    private List<String> keysList = new ArrayList<>();
    private String[] keyVal = {
            "", "", "", "", "", "", "", "", "", "",
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l", "z",
            ".", "x", "c", "v", "b", "n", "m", "del","tts","space",
            "send", "done"
    };

    private RVAdapter rvAdapter;
    private String msg = "";
    private String curr = "";
    private String selected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = new TriNode();
        readProgress = findViewById(R.id.readProgress);
        enterWord = findViewById(R.id.enter_word);
        drawKeyboard();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadDictionary("english.txt");
    }

    public void drawKeyboard() {
        for (int i = 0; i < keyVal.length; i++) {
            keysList.add(keyVal[i]);
        }
        RecyclerView recyclerView = findViewById(R.id.rv_view);
        rvAdapter = new RVAdapter(this);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setData(keyVal);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 10);
        recyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 30 ||position == 37 || position == 38 || position == 40 || position == 41) {
                    return 2;
                } else if (position == 39) {
                    return 4;
                } else {
                    return 1;
                }
            }
        });

        rvAdapter.setOnRVItemClickListener(new OnRVItemClickListener() {
            @Override
            public void onClickListener(int pos) {
                if (pos < 10) {
                    msg = msg.substring(0,msg.lastIndexOf(" ")+1)+keyVal[pos]+" ";
                } else if (pos == 37 && msg.length() > 0) {
                    msg = removeLastChar(msg);
                } else if (pos == 39) {
                    msg = msg + " ";
                } else {
                    msg += keyVal[pos];
                }

                String curr = msg.substring(msg.lastIndexOf(" ")+1);

                // pass last word for suggestion
                suggestion = root.search(root, 0,curr);
                if (suggestion!=null && suggestion.size() > 0) {
                    int t = 0;
                    for (String word :suggestion) {
                        keyVal[t] = word;
                        t++;
                    }
                }
                rvAdapter.setData(keyVal);
                enterWord.setText(msg);
            }
        });
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public void ReadDictionary(String filename) {
        ReadAsync task = new ReadAsync(this, new Results() {
            @Override
            public void processFinish(final String[] output) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (String word : output) {
                            root.addNode(root, 0, word);
                        }
                    }
                });
            }
        }, filename);
        task.setProgressBar(readProgress);
        task.execute();
    }
}
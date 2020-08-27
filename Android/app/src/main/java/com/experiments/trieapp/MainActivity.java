package com.experiments.trieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.experiments.trieapp.Trie.TriNode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText enterWord;
    private TextView suggTv;
    private TriNode root;
    private List<String> words = new ArrayList<>();
    private List<String> suggestion = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         root = new TriNode();
        addWords();
        for (String word: words) {
            root.addNode(root, 0, word);
        }

        suggTv = findViewById(R.id.suggestions);
        enterWord = findViewById(R.id.enter_word);
        enterWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                suggTv.setText("");
                suggestion = root.search(root,0,enterWord.getText().toString());
                for(String word: suggestion.size()>10?suggestion:suggestion){
                    suggTv.append(word+"\n");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void addWords(){
        words.add("ace");
        words.add("ape");
        words.add("apple");
        words.add("applet");
        words.add("boy");
        words.add("bond");
        words.add("bold");
        words.add("ban");
        words.add("bread");
        words.add("break");
        words.add("cat");
        words.add("cast");
        words.add("come");
    }
}
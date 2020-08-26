package com.experiments.trieapp.Trie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TriNode {
    private char x;
    private boolean terminal = false;
    private HashMap<Character, TriNode> branch=new HashMap<>();
    private List<String> words = new ArrayList<>();

    public TriNode() {

    }

    public TriNode(char x) {
        this.x = x;
    }

    public void addNode(TriNode triNode, int i, String word){
        if(i==word.length()){
            triNode.terminal = true;
            return;
        }

        char ch = word.charAt(i);
        if (triNode.branch.get(ch)==null) {
            triNode.branch.put(ch,new TriNode(ch));
        }

        triNode.words.add(word);
        addNode(triNode.branch.get(ch),i+1,word);
    }

    public List<String> search(TriNode triNode, int i, String word){
        if (i == word.length()) {
            return triNode.words;
        }

        char ch = word.charAt(i);
        if (triNode.branch.get(ch)==null) {
            return null;
        }
        return search(triNode.branch.get(ch), i + 1, word);
    }
}

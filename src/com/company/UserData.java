package com.company;

public class UserData {
    private String word;
    private int numberOfWord;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumberOfWord() {
        return numberOfWord;
    }

    public void setNumberOfWord(int numberOfWord) {
        this.numberOfWord = numberOfWord;
    }

    public UserData(String word, int numberOfWord) {

        this.word = word;
        this.numberOfWord = numberOfWord;
    }
}

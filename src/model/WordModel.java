package model;

import java.util.Arrays;
import java.util.Random;

public class WordModel {
    protected String word;
    protected boolean[] letters;

    public WordModel(String word) {
        this.word = word;
        this.letters = new boolean[word.length()];
    }

    public int getWordLength() {
        return this.word.length();
    }

    public boolean guessLetter(char letter) {
        boolean foundLetter = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                letters[i] = true;
                foundLetter = true;

            }
        }
        return foundLetter;
    }

    public boolean guessWord(String guessWord){

        if(guessWord.equals(this.word)){
            Arrays.fill(this.letters, true);
            return true;
        }
        return false;
    }


    public boolean areAllLettersGuessed() {
        for (boolean letter : letters) {
            if (!letter) {
                return false;
            }
        }
        return true;
    }

    public void getLetterFromWord() {
        Random random = new Random();
        if (getCountOfCorrectLetters() == getWordLength()) {
            throw new NoMoreLettersException("All letters have been selected");
        }

        while (true) {
            int randint = random.nextInt(getWordLength());
            if (!letters[randint]) {
                letters[randint] = true;
                break;
            }
        }
    }

    public String toString(){
        StringBuilder printWord  = new StringBuilder();
        for(int i = 0; i < this.word.length(); i++){
            if(this.letters[i]){
                printWord.append(this.word.charAt(i));
            } else {
                printWord.append("-");
            }
        }
        return printWord.toString();
    }

    private int getCountOfCorrectLetters() {
        int count = 0;
        for (boolean letter : this.letters) {
            if (letter) {
                count++;
            }
        }
        return count;
    }
}
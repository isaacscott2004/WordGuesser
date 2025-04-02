package gamePlay;

import model.WordModel;

import java.util.Arrays;
import java.util.Scanner;

public class Repl {
    private final WordModel wordModel;
    private final String word;

    public Repl(){
        while(true){
            String pWord = WordFetcher.getWord();
            if(pWord != null){
                this.word = pWord;
                break;
            }
        }
        this.wordModel = new WordModel(this.word);
    }

    public void run(Scanner scanner) {

            System.out.println("Welcome to WordGuesser! \nyour word length is " +
                    this.wordModel.getWordLength() + ": " + this.wordModel + " \n" + "COMMANDS \n" + help());
            int count = 0;
            int triesRemaining = 7;
            int hintsRemaining = 3;
            while (count < 7) {
                printPrompt(triesRemaining, hintsRemaining);
                String input = scanner.nextLine();
                String result = eval(input);
                if (input.toLowerCase().contains("hint")) {
                    if (hintsRemaining > 0) {
                        hintsRemaining--;
                    } else {
                        result = "No hints remaining!";
                    }
                }
                System.out.println(result);
                if (result.equals("Game Over!")) {
                    break;
                }
                if (this.wordModel.areAllLettersGuessed()) {
                    System.out.println("Congratulations, you've guessed the word!");
                    break;
                }
                if (input.toLowerCase().contains("guessletter") || input.toLowerCase().contains("guessword")) {
                    count++;
                    triesRemaining--;
                }
            }
            if (count >= 7 && !(this.wordModel.areAllLettersGuessed())) {
                System.out.println("Sorry you did not guess '" + this.word + "' in 7 tries.");
            }
    }

    private void printPrompt(int count, int hints) {
        System.out.print("tries remaining: " + count + "\n" + "hints remaining: " + hints + "\n>>> ");
    }

    private  String eval(String input){

        String[] tokens = input.stripLeading().split(" ");
        String command = (tokens.length > 0) ? tokens[0].toLowerCase() : "help";
        String[] params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch(command){
            case "guessletter" -> {
                if (params.length == 1 && params[0].length() == 1) {
                    char letter = params[0].charAt(0);
                    boolean result = this.wordModel.guessLetter(letter);
                    if(result){
                        yield  "correct guess! " + this.wordModel;
                    } else{
                        yield "incorrect guess! " + this.wordModel;
                    }
            } else {
                    yield "Error: Expected 'guessLetter <letter>'";
                }
        }
            case "guessword" ->{
                if(params.length == 1){
                    String guessWord = params[0];
                    boolean result = this.wordModel.guessWord(guessWord);
                    if(result){
                        yield "correct guess! " + this.wordModel;
                    } else{
                        yield "incorrect guess! " + this.wordModel;
                    }
                } else {
                    yield "Error: Expected 'guessword <word>'";
                }
        }
            case "hint" -> {
                if (params.length == 0) {
                    this.wordModel.getLetterFromWord();
                    yield "Here's your hint: " + this.wordModel;
                } else {
                    yield "Error: Expected 'hint'";
                }
        }
            case "quit" -> "Game Over!";
            default -> help();
            };

        }

    private String help(){
        return """
            help
            guessletter <letter>
            guessword <word>
            hint
            quit
            """;
    }


    }

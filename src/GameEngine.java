import assignmentfiles.*;

public class GameEngine {
    private String targetWord;
    private int attemptsLeft;
    private boolean isWin;

    public GameEngine(String targetWord) {
        this.targetWord = targetWord;
        this.attemptsLeft = 6;
        this.isWin = false;
    }

    public class Main {
        public static void main(String[] args) {
            WordLoader wordLoader = new WordLoader();
            GameEngine gameEngine = new GameEngine(wordLoader.getRandomWord());
            System.out.println(gameEngine.getTargetWord());
            GameUI gameUI = new GameUI();
            String userGuess;
            boolean isGameRunning = true;
            while (isGameRunning) {
                userGuess = gameUI.readUserGuess();
                String feedback = gameEngine.playGuess(userGuess);
                if (!userGuess.equals(gameEngine.getTargetWord()))
                    GameUI.displayResult(feedback, userGuess, gameEngine.getAttemptsLeft());
                else {
                    gameEngine.setWinner();

                }
                if (gameEngine.getTargetWord().equals(userGuess) || gameEngine.isGameOver())
                    isGameRunning = false;
            }
            if (gameEngine.isGameOver()) {
                if (gameEngine.getIsWin()) {
                    GameUI.displayWin();
                } else {
                    GameUI.displayLoss(gameEngine.getTargetWord());
                }
            } else {
                GameUI.displayWin();
            }
        }
    }


    public static String evaluateGuess(String targetWord, String guess) {
        String shorterWord;
        int shorterLength;
        if (targetWord.length() > guess.length()) {
            shorterLength = guess.length();
            shorterWord = guess;
        } else {
            shorterLength = targetWord.length();
            shorterWord = targetWord;
        }
        for (int i = 0; i < shorterLength; i++) {
            if (guess.charAt(i) == targetWord.charAt(i)) {
                shorterWord = shorterWord.substring(0, i) + '*' + shorterWord.substring(i + 1);
            } else if (containsChar(targetWord, guess.charAt(i))) {
                shorterWord = shorterWord.substring(0, i) + '+' + shorterWord.substring(i + 1);
            } else {
                shorterWord = shorterWord.substring(0, i) + '-' + shorterWord.substring(i + 1);
            }
        }
        return shorterWord;

    }

    public static boolean containsChar(String word, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (ch == word.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        if (attemptsLeft == 0 || this.isWin == true) {
            return true;
        } else {
            return false;
        }
    }

    public String playGuess(String guess) {
        if (guess.equals(targetWord)) {
            setWinner();
        } else {
            attemptsLeft--;
        }
        return evaluateGuess(this.targetWord, guess);
    }

    public boolean getIsWin() {
        return this.isWin;
    }

    public int getAttemptsLeft() {
        return this.attemptsLeft;
    }

    public String getTargetWord() {
        return this.targetWord;
    }

    public void setWinner() {
        this.isWin = true;
    }
}

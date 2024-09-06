import java.util.Scanner;

public class QuizGame {
    private Question[] questions;
    private int score;

    public QuizGame(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());

            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            Timer timer = new Timer(30); // 30 seconds per question
            timer.start();

            int answerIndex = -1;
            while (!timer.isTimeUp()) {
                if (scanner.hasNextInt()) {
                    answerIndex = scanner.nextInt() - 1;
                    if (answerIndex >= 0 && answerIndex < options.length) {
                        break;
                    }
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }

            if (timer.isTimeUp()) {
                System.out.println("Time's up!");
                answerIndex = -1; // Invalid answer due to time out
            }

            if (answerIndex != -1 && question.isCorrect(answerIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was option " +(question.correctAnswerIndex + 1));
            }
            System.out.println();
        }

        displayResult();
        scanner.close();
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1),
            new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"}, 0),
            new Question("which ocean is the busiest one?", new String[]{"Indian Ocean", "Atlantic Ocean", "Arctic Ocean", "Pacific Ocean"}, 0),
            new Question("What is the smallest prime number?", new String[]{"0", "1", "2", "3"}, 2)
        };

        QuizGame game = new QuizGame(questions);
        game.start();
        
    }
    
}

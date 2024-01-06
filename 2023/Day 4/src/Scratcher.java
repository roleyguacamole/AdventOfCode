import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scratcher {
    private static final Pattern numberPattern = Pattern.compile("\\d+");
    public static void main(String[] args) throws FileNotFoundException {
        final File input = new File("../data/input.txt");
        final Scanner inputReader = new Scanner(input);

        Integer totalPoints = 0;
        while (inputReader.hasNextLine()) {
            totalPoints += getScore(inputReader.nextLine());
        }

        inputReader.close();

        System.out.println(String.format("Answer 1: %d", totalPoints));
    }

    private static Integer getScore(String scratchCard) {
        // Get the winning numbers and our numbers from the card
        final HashSet<Integer> winningNumbers = new HashSet<Integer>();
        final HashSet<Integer> ourNumbers = new HashSet<Integer>();
        final Matcher numberMatcher = numberPattern.matcher(scratchCard);
        Integer currentNumber = 0;
        while (numberMatcher.find()) {
            if (currentNumber > 0 && currentNumber <= 10) {
                winningNumbers.add(Integer.parseInt(numberMatcher.group()));
            }
            else if (currentNumber > 10) {
                ourNumbers.add(Integer.parseInt(numberMatcher.group()));
            }
            currentNumber += 1;
        }

        // Get the intersection of winning numbers and our numbers
        winningNumbers.retainAll(ourNumbers);

        // Get the score of the scratcher based on the size of the intersection
        final Integer score;
        if (winningNumbers.size() == 0) {
            score = 0;
        }
        else {
            score = Double.valueOf(Math.pow(2, winningNumbers.size() - 1)).intValue();
        }
        return score;
    }
}

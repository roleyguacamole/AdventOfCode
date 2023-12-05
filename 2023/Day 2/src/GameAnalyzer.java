import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameAnalyzer {

    private static Pattern gameIdPattern = Pattern.compile("Game\\s(?<id>\\d+):.*");
    private static Pattern rollPattern = Pattern.compile("[\\d+\\s[blue|green|red][,\\s]?]+;?");
    private static Pattern dicePattern = Pattern.compile("(?<number>\\d+)\\s(?<color>blue|green|red)");

    private static HashMap<String, Integer> answerOnePossibleCubes = new HashMap<String, Integer>()
    {
        {
            put("blue", 14);
            put("green", 13);
            put("red", 12);
        }
    };

    public static void main(String[] args) throws FileNotFoundException{
        final File input = new File("../data/input.txt");
        final Scanner inputReader = new Scanner(input);
        Integer idSums = 0;
        Integer powerSums = 0;

        while (inputReader.hasNextLine()) {
            final String line = inputReader.nextLine();

            if (isGamePossible(line)) {
                idSums = idSums + getGameId(line);
            }
            powerSums = powerSums + getPowerOfGame(line);
        }

        inputReader.close();
        System.out.println(String.format("Answer 1: %d", idSums));
        System.out.println(String.format("Answer 2: %d", powerSums));
    }

    private static Integer getGameId(String line) {
        final Matcher idMatcher = gameIdPattern.matcher(line);
        idMatcher.find();
        final String id = idMatcher.group("id");
        return Integer.parseInt(id);
    }

    private static Boolean isGamePossible(String line) {
        Boolean result = true;
        final Matcher rollMatcher = rollPattern.matcher(line);
        while (rollMatcher.find()) {
            if (!isRollPossible(rollMatcher.group())) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static Boolean isRollPossible(String dicePull) {
        Boolean result = true;
        final Matcher diceMatcher = dicePattern.matcher(dicePull);
        while (diceMatcher.find()) {
            final Integer numberOfDice = Integer.parseInt(diceMatcher.group("number"));
            final String colorOfDice = diceMatcher.group("color");
            if (answerOnePossibleCubes.get(colorOfDice) < numberOfDice) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static Integer getPowerOfGame(String line) {
        HashMap<String, Integer> minimumDice = new HashMap<String, Integer>()
        {
            {
                put("blue", 0);
                put("green", 0);
                put("red", 0);
            }
        };
    
        final Matcher rollMatcher = rollPattern.matcher(line);
        while (rollMatcher.find()) {
            final Matcher diceMatcher = dicePattern.matcher(rollMatcher.group());
            while (diceMatcher.find()) {
                final Integer numberOfDice = Integer.parseInt(diceMatcher.group("number"));
                final String colorOfDice = diceMatcher.group("color");
                if (minimumDice.get(colorOfDice) < numberOfDice) {
                    minimumDice.put(colorOfDice, numberOfDice);
                }
            }
        }

        return minimumDice.get("blue") * minimumDice.get("green") * minimumDice.get("red");
    }
}

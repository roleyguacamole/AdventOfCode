import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnginePartAdder {
    private static final Pattern numberPattern = Pattern.compile("\\d+");
    private static final Pattern gearPattern = Pattern.compile("\\*");
    private static final Integer MAX_LENGTH = 140;
    private static final ArrayList<String> inputList = new ArrayList<String>();
    private static final HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> gearCandidates = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        final File input = new File("../data/input.txt");
        final Scanner inputReader = new Scanner(input);

        while (inputReader.hasNextLine()) {
             inputList.add(inputReader.nextLine());
        }
        inputReader.close();

        final Integer result = sumEngineParts();
        final Integer resultTwo = sumGearRatios();
        System.out.println(String.format("Answer 1: %d", result));
        System.out.println(String.format("Answer 2: %d", resultTwo));
    }

    private static Integer sumEngineParts() {
        Integer rowSums = 0;
        for (int i = 0; i < MAX_LENGTH; i++) {
            rowSums = rowSums + sumRowParts(inputList.get(i), i);
        }
        return rowSums;
    }

    private static Integer sumGearRatios() {
        createGearCandidatesMap();
        Integer rowSums = 0;
        for (int i = 0; i < MAX_LENGTH; i++) {
            rowSums = rowSums + sumRowGearRatios(inputList.get(i), i);
        }
        return rowSums;
    }

    private static Integer sumRowParts(String rowString, Integer rowNumber) {
        Integer rowSum = 0;
        final Matcher numberMatcher = numberPattern.matcher(rowString);
        while (numberMatcher.find()) {
            if (isEnginePart(numberMatcher.group(), numberMatcher.start(), rowNumber)) {
                rowSum = rowSum + Integer.parseInt(numberMatcher.group());
            }
        }
        return rowSum;
    }

    private static Integer sumRowGearRatios(String rowString, Integer rowNumber) {
        Integer rowSum = 0;
        final Matcher gearMatcher = gearPattern.matcher(rowString);
        while (gearMatcher.find()) {
            if (isGear(gearMatcher.start(), rowNumber)) {
                rowSum = rowSum + getGearRatio(gearMatcher.start(), rowNumber);
            }
        }
        return rowSum;
    }

    private static Boolean isEnginePart(String candidate, Integer col, Integer row) {
        Boolean result = false;
        for (int i = row - 1; i <= row +1; i++) {
            for (int j = col - 1; j <= col + candidate.length(); j++) {
                if (i >= 0 && i < MAX_LENGTH && j >= 0 && j < MAX_LENGTH) {
                    if (!getCharAt(j, i).equals('.') && !Character.isDigit(getCharAt(j, i))) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    private static Boolean isGear(Integer col, Integer row) {
        return gearCandidates.get(row).get(col).size() == 2;
    }

    private static Integer getGearRatio(Integer col, Integer row) {
        ArrayList<Integer> gears = gearCandidates.get(row).get(col);
        return gears.get(0) * gears.get(1);
    }

    private static void createGearCandidatesMap() {
        for (int i = 0; i < MAX_LENGTH; i++) {
            final HashMap<Integer, ArrayList<Integer>> columnToInts = new HashMap<>();
            for (int j = 0; j < MAX_LENGTH; j++) {
                columnToInts.put(j, new ArrayList<>());
            }
            gearCandidates.put(i, columnToInts);
        }
        for (int i = 0; i < MAX_LENGTH; i++) {
            final String rowString = inputList.get(i);
            final Matcher numberMatcher = numberPattern.matcher(rowString);
            while(numberMatcher.find()) {
                addToGearMap(numberMatcher.group(), numberMatcher.start(), i);
            }
        }
    }

    private static void addToGearMap(String candidate, Integer col, Integer row) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + candidate.length(); j++) {
                if (i >= 0 && i < MAX_LENGTH && j >= 0 && j < MAX_LENGTH) {
                    if (getCharAt(j, i).equals('*')) {
                        gearCandidates.get(i).get(j).add(Integer.parseInt(candidate));
                    }
                }
            }
        }
    }
    
    private static Character getCharAt(Integer col, Integer row) {
        return inputList.get(row).charAt(col);
    }
}

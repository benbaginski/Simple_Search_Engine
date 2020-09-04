package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String> lines;
    static Map<String, Set<Integer>> invertedIndex;
    static SearchContext searchContext = new SearchContext();

    public static void main(String[] args) {
        lines = readFile(args[1]);
        invertedIndex = createInvertedIndex(lines);
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Find a line");
            System.out.println("2. Print all lines");
            System.out.println("0. Exit");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    searchContext.search(lines, invertedIndex);
                    break;
                case "2":
                    System.out.println("\n=== List of lines ===");
                    for (String s : lines) System.out.println(s);
                    break;
                case "0":
                    System.out.println();
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public static ArrayList<String> readFile(String fileName) {
        File file = new File(fileName);
        ArrayList<String> array = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                array.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
        return array;
    }

    public static Map<String, Set<Integer>> createInvertedIndex(ArrayList<String> array) {
        Map<String, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            String[] split = array.get(i).split(" ");
            for (String s : split) {
                Set<Integer> tmp = map.getOrDefault(s, new HashSet<>());
                tmp.add(i);
                map.put(s, tmp);
            }
        }
        return map;
    }
}

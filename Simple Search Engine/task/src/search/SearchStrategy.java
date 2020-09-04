package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface SearchStrategy {
    void search(ArrayList<String> lines, Map<String, Set<Integer>> invertedIndex, String searchTerm);
}

class SearchAll implements SearchStrategy {

    @Override
    public void search(ArrayList<String> lines, Map<String, Set<Integer>> invertedIndex, String searchTerm) {
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < lines.size(); i++) {
            resultSet.add(i);
        }

        for (String st : searchTerm.split(" ")) {
            Pattern pattern = Pattern.compile(st, Pattern.CASE_INSENSITIVE);

            for (String e : invertedIndex.keySet()) {
                Matcher matcher = pattern.matcher(e);

                if (matcher.matches()) {
                    resultSet.retainAll(invertedIndex.get(e));
                }
            }
        }
        if (!resultSet.isEmpty()) {
            for (int i : resultSet) {
                System.out.println(lines.get(i));
            }
        } else System.out.println("No Matching Lines!");
    }
}

class SearchAny implements SearchStrategy {

    @Override
    public void search(ArrayList<String> lines, Map<String, Set<Integer>> invertedIndex, String searchTerm) {
        Set<Integer> resultSet = new HashSet<>();

        for (String st : searchTerm.split(" ")) {
            Pattern pattern = Pattern.compile(st, Pattern.CASE_INSENSITIVE);

            for (String e : invertedIndex.keySet()) {
                Matcher matcher = pattern.matcher(e);

                if (matcher.matches()) {
                    resultSet.addAll(invertedIndex.get(e));
                }
            }
        }
        if (!resultSet.isEmpty()) {
            for (int i : resultSet) {
                System.out.println(lines.get(i));
            }
        } else System.out.println("No Matching Lines!");
    }
}

class SearchNone implements SearchStrategy {

    @Override
    public void search(ArrayList<String> lines, Map<String, Set<Integer>> invertedIndex, String searchTerm) {
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < lines.size(); i++) {
            resultSet.add(i);
        }
        for (String st : searchTerm.split(" ")) {
            Pattern pattern = Pattern.compile(st, Pattern.CASE_INSENSITIVE);

            for (String e : invertedIndex.keySet()) {
                Matcher matcher = pattern.matcher(e);

                if (matcher.matches()) {
                    resultSet.removeAll(invertedIndex.get(e));
                }
            }
        }
        if (!resultSet.isEmpty()) {
            for (int i : resultSet) {
                System.out.println(lines.get(i));
            }
        } else System.out.println("No Matching Lines!");
    }
}

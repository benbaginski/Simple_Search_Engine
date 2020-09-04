package search;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SearchContext {

    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public boolean setSearchStrategy(String searchStrategy) {

        switch (searchStrategy) {
            case "ALL":
                setSearchStrategy(new SearchAll());
                return true;
            case "ANY":
                setSearchStrategy(new SearchAny());
                return true;
            case "NONE":
                setSearchStrategy(new SearchNone());
                return true;
            default:
                return false;
        }
    }

    public void search(ArrayList<String> lines, Map<String, Set<Integer>> invertedIndex) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        if (!setSearchStrategy(scanner.nextLine().trim())) {
            System.out.println("Invalid Search Strategy!");
            return;
        } else {
            System.out.println("Enter a line of search terms");
            String searchTerm = scanner.nextLine();
            this.searchStrategy.search(lines, invertedIndex, searchTerm);
        }
    }
}

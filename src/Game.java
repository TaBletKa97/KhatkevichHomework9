import java.util.TreeMap;

public class Game {
    private String name;
    private TreeMap<Integer, Player> rating = new TreeMap<>();

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<Integer, Player> getRating() {
        return rating;
    }

    public void setRating(TreeMap<Integer, Player> rating) {
        this.rating = rating;
    }
}

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Game implements Serializable {
    private String name;
    private Map<Player, Integer> rating = new HashMap<>();

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Player, Integer> getRating() {
        return rating;
    }

    public void setRating(HashMap<Player, Integer> rating) {
        this.rating = rating;
    }
}

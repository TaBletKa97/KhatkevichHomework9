import java.util.HashMap;
import java.util.Map;

public class Player {
    private String nickname;
    private Map<Game, Integer> gameRating = new HashMap<>();

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public Map getGameRating() {
        return gameRating;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Ник: " + nickname + "\n";
        for (Map.Entry<Game, Integer> entry:gameRating.entrySet()) {
            result += entry.getKey().getName() + ": " + entry.getValue() + "\n";
        }
        return result;
    }
}

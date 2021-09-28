import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    private String nickname;
    private Map<Game, Integer> gameRating = new HashMap<>();

//    public Player() {
//    }
//
//    /*
//    *Этот конструктор чисто для ручного добавления в прогу людей
//    *чтобы не добавлять каждый раз при запуске
//     */
//
//    public Player(String nickname, Game game1, Game game2) {
//        this.nickname = nickname;
//        this.gameRating.put(game1, 5600);
//        this.gameRating.put(game2, 2500);
//    }

    public Player(String nickname, Game game1) {
        this.nickname = nickname;
        this.gameRating.put(game1, 4000);
    }

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

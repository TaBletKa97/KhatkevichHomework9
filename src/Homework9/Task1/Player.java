package Homework9.Task1;

import java.util.AbstractMap;
import java.util.HashMap;

public class Player {
    private String nickname;
    private AbstractMap gameRating = new HashMap<String, Integer>();

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }


    public AbstractMap getGameRating() {
        return gameRating;
    }

}

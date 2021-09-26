package Homework9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Core {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Player> players = new ArrayList<>();
    private static List<String> games = new ArrayList<>();
    private final static int STANDART_MMR = 3000;

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        Core.players = players;
    }

    public static List<String> getGames() {
        return games;
    }

    public static void setGames(List<String> games) {
        Core.games = games;
    }

    public static void registerPlayer(Player player) {
        players.add(player);
    }

    private static void createPlayer() {
        String nick = "";
        try {
            System.out.println("Введите никнейм");
            nick = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Некорректный никнейм.");
            createPlayer();
        }
        if (players.contains(nick)) {
            System.out.println("Данный ник уже занят");
            createPlayer();
            return;
        } else {
            Player player = new Player(nick);
            players.add(player);
        }
        System.out.println("Выберите игры, в которые вы играете");
        showGames();
        System.out.printf("%d. %s\n", games.size()+1, "Закончить выбор");
        int gameIndex = 0;
        do {
            gameIndex = chooseGame();
            if (gameIndex < 0 || gameIndex > games.size()) {
                System.out.println("Некорректный выбор. Попробуйте еще раз");
                gameIndex = chooseGame();
            } else {
                // не забыть написать проверку на наличие иргы у игрока
                players.get(players.size()-1).getGameRating().put(games.get(gameIndex-1), STANDART_MMR);
            }
        } while (gameIndex != games.size()+1);

    }

    public static void showGames() {
        for (int i = 0; i < games.size(); i++) {
            System.out.printf("%d. %s\n", i+1, games.get(i));
        }
    }

    private static int chooseGame() {
        try {
            int game = scanner.nextInt();
            return game;
        } catch (Exception e) {
            System.out.println("Некорректный выбор. Попробуйте еще раз.");
            return chooseGame();
        }
    }
}

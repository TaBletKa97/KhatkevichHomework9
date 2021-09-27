import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Core {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Player> players = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();
    private final static int STANDART_MMR = 3000;

    public static List<Player> getPlayers() {
        return players;
    }

    public static List<Game> getGames() {
        return games;
    }

    public static void createPlayer() {
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
        System.out.printf("%d. %s\n", games.size() + 1, "Закончить выбор");
        int gameIndex = 0;
        do {
            gameIndex = chooseGame();
            if (gameIndex < 0 || gameIndex > games.size()+1) {
                System.out.println("Некорректный выбор. Попробуйте еще раз");
                gameIndex = chooseGame();
            } else if (gameIndex == games.size()+1 && players.get(players.size() - 1).getGameRating().isEmpty()) {
                System.out.println("Выберите как минимум одну игру");
                gameIndex = -1;
            } else if (gameIndex > 0 && gameIndex < games.size()) {
                if (!players.get(players.size() - 1).getGameRating().containsKey(games.get(gameIndex - 1))) {
                    players.get(players.size() - 1).getGameRating().put(games.get(gameIndex - 1), STANDART_MMR);
                    games.get(gameIndex -1).getRating().put(STANDART_MMR, players.get(players.size()-1));
                } else {
                    System.out.println("Эта игра уже закреплена за вашим аккаунтом");
                }
            }
        } while (gameIndex != games.size() + 1);
    }

    public static void showGames() {
        for (int i = 0; i < games.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, games.get(i).getName());
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

    public static void showPlayerStats() {
        System.out.println("Введите ник игрока:");
        String nick = correctString();
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getNickname().equalsIgnoreCase(nick));
            System.out.println(players.get(i).toString());
            return;
        }
        System.out.println("Игрока с данным никнеймом не найдено");
    }

    private static String correctString() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка ввода попробуйте еще раз");
            return correctString();
        }
    }

    private static int correctInt() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка ввода, попробуйте еще раз");
            return correctInt();
        }
    }

    private Player searchPlayer() {
        Player player = null;
        System.out.println("Введите ник игрока");
        String name = correctString();
        for (int i =0; i < players.size(); i++) {
            if (players.get(i).getNickname().equalsIgnoreCase(name)) {
                player = players.get(i);
            }
        }
        return player;
    }

    private void playGame() {

    }

    private void playGame(Player player, Game game) {
        Random random = new Random();
        if (random.nextBoolean()) {

        } else {

        }
    }
}

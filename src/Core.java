import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Core implements Serializable {
    private List<Player> players = new ArrayList<>();
    private List<Game> games = new ArrayList<>();
    private final static int STANDART_MMR = 3000;

    public List<Player> getPlayers() {
        return players;
    }

    public List<Game> getGames() {
        return games;
    }

    private void createPlayer() {
        Scanner scanner = new Scanner(System.in);
        String nick = "";
        try {
            System.out.println("Введите никнейм");
            nick = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Некорректный никнейм.");
            createPlayer();
        }
        if (playersContainsNick(nick)) {
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
            gameIndex = correctInt()-1;
            if (gameIndex < 0 || gameIndex > games.size()) {
                System.out.println("Некорректный выбор. Попробуйте еще раз");
                gameIndex = correctInt() -1;
            } else if (gameIndex == games.size() && players.get(players.size() - 1).getGameRating().isEmpty()) {
                System.out.println("Выберите как минимум одну игру");
                gameIndex = -1;
            } else if (gameIndex < games.size()) {
                if (!players.get(players.size() - 1).getGameRating().containsKey(games.get(gameIndex))) {
                    players.get(players.size() - 1).getGameRating().put(games.get(gameIndex), STANDART_MMR);
                    games.get(gameIndex).getRating().put(players.get(players.size()-1), STANDART_MMR);
                } else {
                    System.out.println("Эта игра уже закреплена за вашим аккаунтом");
                }
            }
        } while (gameIndex != games.size());
    }

    private void showGames() {
        for (int i = 0; i < games.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, games.get(i).getName());
        }
    }

    private void showPlayerStats() {
        System.out.println("Введите ник игрока:");
        String nick = correctString();
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getNickname().equalsIgnoreCase(nick)) {
                System.out.println(players.get(i).toString());
                return;
            }
        }
        System.out.println("Игрока с данным никнеймом не найдено");
    }

    private String correctString() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка ввода попробуйте еще раз");
            return correctString();
        }
    }

    private int correctInt() {
        try {
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
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

    private Game searchGame() {
        System.out.println("Выберите игру:");
        showGames();
        int chsgame = correctInt();
        if (chsgame < 1 || chsgame > games.size()) {
            System.out.println("Неверное значение");
            return searchGame();
        }
        chsgame -= 1;
        return games.get(chsgame);
    }

    private void playGame() {
        Player player = searchPlayer();
        Game game = searchGame();
        System.out.println("Введите количество игр, кторые хотите сыграть");
        Integer count = correctInt();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextBoolean()) {
                Object mmr = player.getGameRating().get(game);
                if (mmr == null) {
                    System.out.println("Игрок не играет в данную игру");
                    return;
                }
                int newMMR = (int) mmr;
                newMMR += 25 + random.nextInt(10);
                player.getGameRating().put(game, newMMR);
                game.getRating().put(player, newMMR);
            } else {
                Object mmr = player.getGameRating().get(game);
                if (mmr == null) {
                    System.out.println("Игрок не играет в данную игру");
                    return;
                }
                int newMMR = (int) mmr;
                newMMR -= 25 + random.nextInt(10);
                player.getGameRating().put(game, newMMR);
                game.getRating().put(player, newMMR);
            }
        }
    }

    private boolean playersContainsNick(String nick) {
        boolean result = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getNickname().equalsIgnoreCase(nick)) {
                result = true;
            }
        }
        return result;
    }

    private static void top10byGame() {

    }

    public void go() {
        int n = -1;
        do {
            showMenu();
            n = choose(7);
            switch (n) {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    showGames();
                    break;
                case 3:
                    playGame();
                    break;
                case 4:
                    top10byGame();
                    break;
                case 5:
                    System.out.println("Данная функция пока еще в разработке");
                    break;
                case 6:
                    showPlayerStats();
                    break;
                case 7:
                    save();
                    break;
            }
        } while (n != 7);
    }

    private int choose(int n) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > n) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Некорректный выбор. Попробуйте снова");
            choice = choose(n);
        }
        return choice;
    }

    private void showMenu() {
        System.out.println("1. Добавить игрока");
        System.out.println("2. Отобразить список игр");
        System.out.println("3. Сыграть в игру");
        System.out.println("4. Вывести топ 10 по игре");
        System.out.println("5. Вывести топ 10 по всем играм");
        System.out.println("6. Вывести рейтинг игрока");
        System.out.println("7. Сохранить и выйти");
    }

    private void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("sevedMMR.txt"));
            objectOutputStream.writeObject(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

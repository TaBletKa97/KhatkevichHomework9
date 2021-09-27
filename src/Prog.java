public class Prog {
    public static void main(String[] args) {
        Core.getGames().add(new Game("Dota 2"));
        Core.getGames().add(new Game("CS:GO"));
        Core.getGames().add(new Game("PUBG"));
        Core.getGames().add(new Game("LoL"));
        Core.createPlayer();
        Core.showPlayerStats();
    }
}

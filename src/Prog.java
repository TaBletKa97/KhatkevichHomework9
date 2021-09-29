import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Prog {
    public static void main(String[] args) {
        Core core = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("sevedMMR.txt"));
            core = (Core) objectInputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            core = new Core();
        }
        core.getPlayers().stream().forEach(System.out::println);
//        core.getGames().add(new Game("Dota 2"));
//        core.getGames().add(new Game("CS:GO"));
//        core.getGames().add(new Game("PUBG"));
//        core.getGames().add(new Game("LoL"));
        core.go();
    }
}

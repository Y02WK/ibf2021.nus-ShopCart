package ibf2021.nus;

import java.io.File;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        File dbDir;
        if (args.length == 0) {
            dbDir = Path.of("./db").toFile();
        } else {
            System.out.println(args[0]);
            dbDir = Path.of("./%s", args[0]).toFile();
        }
        if (!dbDir.exists()) {
            dbDir.mkdir();
        }
        
        shopCart shop = new shopCart();
        shop.openShop();
    }
}

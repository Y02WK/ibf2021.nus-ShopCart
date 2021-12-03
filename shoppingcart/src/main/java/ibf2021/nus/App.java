package ibf2021.nus;

import java.io.File;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        String userDB = "";
        if (args.length > 0) {
            userDB = args[0];
        }

        ShoppingCart shop = new ShoppingCart(userDB);
        shop.openShop();
    }
}

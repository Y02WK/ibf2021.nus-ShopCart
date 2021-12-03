package ibf2021.nus;

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

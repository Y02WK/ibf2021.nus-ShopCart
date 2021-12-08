package ibf2021.nus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class ShoppingCart {

    private ArrayList<String> alist = new ArrayList<>();
    private Set<String> set = new HashSet<>();
    private ShoppingCartDB dbOperations;

    public ShoppingCart(String userDB) {
        if (!userDB.isBlank())
            this.dbOperations = new ShoppingCartDB(userDB);
        else
            this.dbOperations = new ShoppingCartDB();

    }

    public void openShop(InputStream inputStream) {
        /**
         * Prints a welcome message and starts the input process
         */

        // Case for testing purposes. No welcome message for testing.
        if (inputStream != System.in) {
            this.testInput(inputStream);
        } else {
            System.out.println("Welcome to your shopping cart");
            while (true) {
                String input = this.getInput(System.in);
                if (!this.processInput(input)) {
                    return;
                }
            }
        }
    }

    private boolean processInput(String input) {
        /**
         * @param String input
         * @return false if cmd is "quit", else returns true
         */
        if (input.isBlank()) {
            System.err.println("Input cannot be blank.");
            return true;
        }

        Scanner scan = new Scanner(input);
        String cmd = scan.next();

        switch (cmd.trim().toLowerCase()) {
            case "list":
                this.printItems();
                break;
            case "add":
                scan.useDelimiter(",");
                while (scan.hasNext()) {
                    String item = scan.next().trim();
                    this.addToCart(item);
                }
                break;
            case "delete":
                int index;
                try {
                    index = Integer.parseInt(scan.next().trim()) - 1;
                } catch (Exception e) {
                    System.out.println("Error. Please enter only numerical inputs for the index.");
                    break;
                }
                this.deleteFromCart(index);
                break;
            case "login":
                try {
                    String username = scan.next().trim().replaceAll(" ", "_");
                    dbOperations.login(username, this.alist, this.set);
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Username cannot be blank");
                    break;
                }
            case "users":
                dbOperations.users();
                break;
            case "save":
                dbOperations.save(this.alist);
                break;
            case "quit":
                scan.close();
                System.out.println("Bye bye");
                return false;
            default:
                System.err.println("Command not found.");
        }
        scan.close();
        return true;
    }

    private String getInput(InputStream inputStream) {
        /**
         * Gets input from the console and returns it as a String
         * 
         * @return String input
         */
        String input = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            input = br.readLine();
            return input;
        } catch (IOException e) {
            System.err.println(e);
        }
        return input;
    }

    private void addToCart(String item) {
        /**
         * Method to add item to cart. O(1) time complexity. Uses a set to check if item
         * is already
         * in cart rather than iterating through the ArrayList, which will be O(n).
         * 
         * @params a String item
         */

        // Check if item already added to cart using the set
        if (this.set.add(item.toLowerCase())) {
            this.alist.add(item);
            System.out.printf("%s added to cart\n", item);
            return;
        }

        System.out.printf("You have %s in your cart\n", item);
        return;
    }

    private void deleteFromCart(int index) {
        /**
         * Deletes an item from the cart
         * 
         * @params an int index containing the index to remove
         */
        if (index < this.set.size() && index >= 0) {
            String item = this.alist.remove(index);
            this.set.remove(item);
            System.out.printf("%s removed from cart\n", item);
        } else {
            System.err.println("Incorrect item index");
        }
    }

    private void printItems() {
        /**
         * Prints the list of items in the cart
         */
        if (this.alist.size() == 0) {
            System.out.println("Your cart is empty");
            return;
        } else {
            // O(n) time complexity
            for (int i = 0; i < this.alist.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.alist.get(i));
            }
            return;
        }
    }

    private void testInput(InputStream inputStream) {
        String input = this.getInput(inputStream);
        this.processInput(input);
    }

    public ArrayList<String> getCart() {
        return this.alist;
    }
}

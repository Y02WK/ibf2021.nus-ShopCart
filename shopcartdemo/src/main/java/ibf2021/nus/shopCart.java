package ibf2021.nus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.io.Console;

public class shopCart {

    private ArrayList<String> alist = new ArrayList<>();
    private Set<String> set = new HashSet<>();

    void openShop() {
        /**
         * Prints a welcome message and starts the input process
         */
        System.out.println("Welcome to your shopping cart");
        while (true) {
            String input = this.getInput();
            if (!this.processInput(input)) {
                return;
            }
        }
    }

    private boolean processInput(String input) {
        /**
        @param String input
        @return false if cmd is "quit", else returns true
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
                }
                catch (Exception e) {
                    System.out.println("Error. Please enter only numerical inputs for the index.");
                    break;
                }
                this.deleteFromCart(index);
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

    private String getInput() {
        /**
        * Gets input from the console and returns it as a String
        * @return String input
        */
        Console console = System.console();
        String input = console.readLine();
        return input;
    }

    private void addToCart(String item) {
        /** 
        * Method to add item to cart. 
        * O(1) time complexity. Uses a set to check if item is already in cart 
        * rather than iterating through the ArrayList, which will be O(n).
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
         * @params an int index containing the index to remove 
         */
        if (index < this.set.size() && index >= 0) {
            String item = this.alist.remove(index);
            this.set.remove(item);
            System.out.printf("%s removed from cart\n", item);
        } 
        else {
            System.err.println("Incorrect item index");
        }
    }

    private void printItems() {
        /** 
         * Prints the list of items in the cart
         */
        if (this.set.size() == 0) {
            System.out.println("Your cart is empty");
            return;
        } 
        else {
            // O(n) time complexity
            for (int i = 0; i < this.set.size(); i++) {
                System.out.printf("%d. %s\n", i+1, this.alist.get(i));
            }
            return;
        }
    }

}

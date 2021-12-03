package ibf2021.nus;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

public class ShoppingCartDB {

    // Private attribute containing the directory for the database.
    private File dbDir = Path.of("./db").toFile();
    private File userDB;
    private boolean login = false;

    public ShoppingCartDB() {
        validateAndCreateDirectory();
    };

    public ShoppingCartDB(String dirName) {
        this.dbDir = Path.of("./" + dirName).toFile();
        validateAndCreateDirectory();
    }

    protected void login(String username, ArrayList<String> alist, Set<String> set) {
        String dbFile = dbDir.getAbsolutePath() + "/" + username + ".db";
        this.userDB = Path.of(dbFile).toFile();
        this.validateAndCreateDB();
        this.loadUserCart(alist, set);
    }

    private void loadUserCart(ArrayList<String> alist, Set<String> set) {
        String item;

        // If already logged in, clear the cart before populating the cart with the items in the db 
        if (login) {
            alist.clear();
            set.clear();
        }
        try {
            BufferedReader reader = Files.newBufferedReader(this.userDB.toPath());
            while ((item=reader.readLine()) != null) {
                alist.add(item);
                set.add(item);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
 
    protected void save(ArrayList<String> cart) {
        
    }

    protected void users() {
        for (File user: this.dbDir.listFiles()) {
            System.out.println(user.getName());
        }
    }

    private void validateAndCreateDirectory() {
        if (!this.dbDir.exists()) {
            this.dbDir.mkdir();
        }
        return;
    }

    private void validateAndCreateDB() {
        if (!this.userDB.exists()) {
            try {
                this.userDB.createNewFile();
            } catch (IOException e) {
                System.err.println(e);
            }
            System.out.println("User not found. Created a new login for user.");
        } else {
            System.out.println("User found. Login successful.");
        }
        this.login = true;
        return;
    }
}

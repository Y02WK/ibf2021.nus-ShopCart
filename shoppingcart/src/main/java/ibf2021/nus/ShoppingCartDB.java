package ibf2021.nus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ShoppingCartDB {

    // Private attribute containing the directory for the database.
    private File dbDir = Path.of("./db").toFile();
    private File userDB;

    public ShoppingCartDB() {
        validateAndCreateDirectory();
    };

    public ShoppingCartDB(String dirName) {
        this.dbDir = Path.of("./" + dirName).toFile();
        validateAndCreateDirectory();
    }

    protected void login(String username) {
        String dbFile = dbDir.getAbsolutePath() + "/" + username + ".db";
        this.userDB = Path.of(dbFile).toFile();
        try {
            validateAndCreateDB();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void save() {}

    private void users() {}

    private void validateAndCreateDirectory() {
        if (!this.dbDir.exists()) {
            this.dbDir.mkdir();
        }
        return;
    }

    private void validateAndCreateDB() throws IOException {
        if (!this.userDB.exists()) {
            this.userDB.createNewFile();
            System.out.println("User not found. Created a new login for user.");
        } else {
            System.out.println("User found. Login successful.");
        }
        return;
    }
}

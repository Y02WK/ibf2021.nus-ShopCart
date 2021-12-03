package ibf2021.nus;

import java.io.File;
import java.nio.file.Path;

public class ShoppingCartDB {

    // Private attribute containing the directory for the database.
    private File dbDir = Path.of("./db").toFile();;

    public ShoppingCartDB() {
        validateAndCreateDirectory();
    };

    public ShoppingCartDB(String dirName) {
        this.dbDir = Path.of("./" + dirName).toFile();
        validateAndCreateDirectory();
    }

    private void login(String username) {}

    private void save() {}

    private void users() {}

    private void validateAndCreateDirectory() {
        if (!this.dbDir.exists()) {
            this.dbDir.mkdir();
        }
        return;
    }
}

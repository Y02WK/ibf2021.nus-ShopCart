package ibf2021.nus;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    // @Test
    // public void shouldAnswerWithTrue()
    // {
    // assertTrue( true );
    // }

    @Test
    public void testAdd() {
        ShoppingCart shop = new ShoppingCart("");
        shop.openShop(new ByteArrayInputStream("add apple".getBytes()));
        String item = shop.getCart().get(0);

        assertTrue(item.equals("apple"));
    }

    @Test
    public void testdelete() {
        ShoppingCart shop = new ShoppingCart("");
        shop.openShop(new ByteArrayInputStream("add apple, pear, iphone 13".getBytes()));
        shop.openShop(new ByteArrayInputStream("delete 2".getBytes()));
        ArrayList<String> items = new ArrayList<>();
        items.add("apple");
        items.add("iphone 13");
        assertTrue(shop.getCart().equals(items));
    }
}

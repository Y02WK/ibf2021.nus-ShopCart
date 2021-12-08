package ibf2021.nus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
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
    // 2 different carts for testing
    ShoppingCart cart;
    ShoppingCart cart2;
    // to obtain print stream for testing
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() {
        cart = new ShoppingCart("");
        cart2 = new ShoppingCart("db2");

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        cart = null;
        cart2 = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testAdd() {
        cart.openShop(new ByteArrayInputStream("add apple".getBytes()));
        String item = cart.getCart().get(0);

        assertTrue(item.equals("apple"));
    }

    @Test
    public void testdelete() {
        cart.openShop(new ByteArrayInputStream("add apple, pear, iphone 13".getBytes()));
        cart.openShop(new ByteArrayInputStream("delete 2".getBytes()));
        ArrayList<String> items = new ArrayList<>();
        items.add("apple");
        items.add("iphone 13");
        assertTrue(cart.getCart().equals(items));
    }

    @Test
    public void testInvalidDelete() {
        cart.openShop(new ByteArrayInputStream("add apple, pear, iphone 13".getBytes()));
        cart.openShop(new ByteArrayInputStream("delete f423".getBytes()));

        assertEquals(
                "apple added to cart\npear added to cart\niphone 13 added to cart\nError. Please enter only numerical inputs for the index.\n",
                outContent.toString());
    }

    @Test
    public void testList() {
        cart.openShop(new ByteArrayInputStream("add apple".getBytes()));
        cart.openShop(new ByteArrayInputStream("list".getBytes()));

        assertEquals("apple added to cart\n1. apple\n", outContent.toString());
    }

    @Test
    public void testList2() {
        cart.openShop(new ByteArrayInputStream("add apple, pear, iphone 13".getBytes()));
        cart.openShop(new ByteArrayInputStream("list".getBytes()));

        assertEquals(
                "apple added to cart\npear added to cart\niphone 13 added to cart\n1. apple\n2. pear\n3. iphone 13\n",
                outContent.toString());
    }
}

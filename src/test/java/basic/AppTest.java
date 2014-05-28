package basic;

import com.google.common.base.Joiner;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class AppTest
{
    @Test
    public void should_have_guave() {
        String hw = "Hello, World!";
        String joined = Joiner.on(", ").join("Hello", "World!");

        assertTrue(joined.equals(hw));
    }
}

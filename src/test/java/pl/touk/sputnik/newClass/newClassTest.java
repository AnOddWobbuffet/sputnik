package pl.touk.sputnik.newClass;

import org.junit.jupiter.api.Test;

public class newClassTest {
    @Test
    public void testNewMethod() {
        newClass newClass = new newClass();
        assert newClass.newMethod().equals("newMethod return value");
    }
}

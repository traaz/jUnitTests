package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach                 //beforeEach -> testMethod -> afterEach  asagidaki sıra onemli degil cikti soldaki gibi olur.
    void setUp(){                 // beforeAll ve afterAll ise bunlarin disinde kalan once ve sonra calisir. tum testlerden once ve sonra. beforeEach ve afterEach her test icin yazilir.
        demoUtils = new DemoUtils();
        System.out.println("Before Each worked.");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After Each worked.");
    }
    @Test
    @Disabled
    void testEqualsAndNotEquals(){

        int exceptedValue = 8;
        assertEquals(8, demoUtils.add(6,2));
        assertNotEquals(6, demoUtils.add(6,2));
        System.out.println("Test worked.");
    }
    @Test
    @DisplayName("Kelimeler Eşit Mi Değil mi ?")
    void testSameAndNotSame(){
        String str = "abc";
        assertSame(demoUtils.kelimeGetir(), demoUtils.ikinciKelimeGetir());
        assertNotSame(str, demoUtils.kelimeGetir());
    }
    @Test
    @EnabledForJreRange(min = JRE.JAVA_18)
    void testTrueFalse(){
        int sayi = 10;
        int sayi2 = 5;

        assertTrue(demoUtils.isGreater(sayi,sayi2));
        assertFalse(demoUtils.isGreater(sayi2,sayi));
    }

    @Test
    void testThrowAndNotThrow(){
        assertThrows(Exception.class, () -> demoUtils.throwException(-1) );

    }
}
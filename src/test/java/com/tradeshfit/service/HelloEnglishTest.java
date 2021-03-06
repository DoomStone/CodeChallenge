package com.tradeshfit.service;

import com.tradeshift.services.EnglishHelloService;
import com.tradeshift.services.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class HelloEnglishTest {

    private HelloService service;

    @Before
    public void setUp(){
        this.service = new EnglishHelloService();
    }

    @Test
    public void testNoNameEntered(){
        try{
            String result = service.formatName("");
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testNullEntered(){
        try{
            String result = service.formatName(null);
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testWhitespaceEntered(){
        try{
            service.formatName("   ");
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testWithName(){
        String result = service.formatName("Kasper");
        Assert.assertEquals("Hello Kasper", result);
    }

    @Test
    public void testWithNameWithSpecialCharacters(){
        String result = service.formatName("!!Kasper$$§§§");
        Assert.assertEquals("Hello !!Kasper$$§§§", result);
    }
}

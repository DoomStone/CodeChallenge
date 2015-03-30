package com.tradeshfit.service;

import com.tradeshift.service.DanishHelloService;
import com.tradeshift.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class HelloDanishTest {

    private HelloService service;

    @Before
    public void setUp(){

        this.service = new DanishHelloService();
    }

    @Test
    public void testNoNameEntered(){
        try{
            service.formatName("");
            Assert.fail();
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testNullEntered(){
        try{
            service.formatName(null);
            Assert.fail();
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testWhitespaceEntered(){
        try{
            service.formatName("   ");
            Assert.fail();
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testWithName(){
        String result = service.formatName("Kasper");
        Assert.assertEquals("Hej Kasper", result);
    }

    @Test
    public void tstWithNameWithSpecialCharacters(){
        String result = service.formatName("!!Kasper$$§§§");
        Assert.assertEquals("Hej !!Kasper$$§§§", result);
    }
}

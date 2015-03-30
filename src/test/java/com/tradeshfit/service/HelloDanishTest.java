package com.tradeshfit.service;

import com.tradeshift.service.DanishHelloService;
import com.tradeshift.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class HelloDanishTest {

    private HelloService service;

    @Before
    public void SetUp(){

        this.service = new DanishHelloService();
    }

    @Test
    public void TestNoNameEntered(){
        try{
            service.formatName("");
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void TestNullEntered(){
        try{
            service.formatName(null);
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void TestWhitespaceEntered(){
        try{
            service.formatName("   ");
            Assert.assertTrue(false);
        } catch(IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestWithName(){
        String result = service.formatName("Kasper");
        Assert.assertTrue(result.equals("Hej Kasper"));
    }

    @Test
    public void TestWithNameWithSpecialCharacters(){
        String result = service.formatName("!!Kasper$$§§§");
        Assert.assertTrue(result.equals("Hej !!Kasper$$§§§"));
    }
}

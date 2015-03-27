package com.tradeshfit.service;

import com.tradeshift.service.EnglishHelloService;
import com.tradeshift.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class HelloEnglishTest {

    private HelloService service;

    @Before
    public void SetUp(){
        this.service = new EnglishHelloService();
    }

    @Test
    public void TestNoNameEntered(){
        try{
            String result = service.formatName("");
            Assert.assertTrue(false);
        } catch(NullPointerException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void TestNullEntered(){
        try{
            String result = service.formatName(null);
            Assert.assertTrue(false);
        } catch(NullPointerException exp){
            Assert.assertTrue(true);
        }
    }


    @Test
    public void TestWhitespaceEntered(){
        try{
            service.formatName("   ");
            Assert.assertTrue(false);
        } catch(NullPointerException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestWithName(){
        String result = service.formatName("Kasper");
        Assert.assertTrue(result.equals("Hello Kasper"));
    }

    @Test
    public void TestWithNameWithSpecialCharacters(){
        String result = service.formatName("!!Kasper$$§§§");
        Assert.assertTrue(result.equals("Hello !!Kasper$$§§§"));
    }
}

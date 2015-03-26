package com.tradeshfit.service;

import com.tradeshift.service.DanishHelloService;
import com.tradeshift.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

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
            Assert.isTrue(false);
        } catch(NullPointerException exp){
            Assert.isTrue(true);
        }
    }


    @Test
    public void TestNullEntered(){
        try{
            service.formatName(null);
            Assert.isTrue(false);
        } catch(NullPointerException exp){
            Assert.isTrue(true);
        }
    }


    @Test
    public void TestWhitespaceEntered(){
        try{
            service.formatName("   ");
            Assert.isTrue(false);
        } catch(NullPointerException exp){
            Assert.isTrue(true);
        }
    }

    @Test
    public void TestWithName(){
        String result = service.formatName("Kasper");
        Assert.isTrue(result.equals("Hej Kasper"));
    }

    @Test
    public void TestWithNameWithSpecialCharacters(){
        String result = service.formatName("!!Kasper$$§§§");
        Assert.isTrue(result.equals("Hej !!Kasper$$§§§"));
    }
}

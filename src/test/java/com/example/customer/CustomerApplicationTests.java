package com.example.customer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
class CustomerApplicationTests {


    @Test
    void testOctogenarian(){
        OctogenarianTest octogenarianTest = new OctogenarianTest();
        Assert.assertTrue(octogenarianTest.isOctogenarian(79));

        Assert.assertTrue(octogenarianTest.isOctogenarian(80));
        Assert.assertTrue(octogenarianTest.isOctogenarian(81));
        Assert.assertTrue(octogenarianTest.isOctogenarian(88));
        Assert.assertTrue(octogenarianTest.isOctogenarian(89));
        Assert.assertFalse(octogenarianTest.isOctogenarian(90));
    }
}

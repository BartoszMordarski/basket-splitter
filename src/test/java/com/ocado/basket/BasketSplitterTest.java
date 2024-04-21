package com.ocado.basket;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;


public class BasketSplitterTest {

    private BasketSplitter basketSplitter;

    @Before
    public void setUp() throws Exception {
        URL resourceUrl = getClass().getClassLoader().getResource("config.json");
        URI resourceUri = resourceUrl.toURI();
        byte[] jsonData = Files.readAllBytes(Paths.get(resourceUri));
        basketSplitter = new BasketSplitter();
        basketSplitter.mapConfigData(jsonData);
    }

    @org.junit.Test
    public void invalidConfigTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new BasketSplitter("invalid.json"));
    }


    @org.junit.Test
    public void splitScenario1Test() {
        List<String> items = Arrays.asList("Cookies Oatmeal Raisin", "Cheese Cloth");
        Map<String, List<String>> result = basketSplitter.split(items);
        assertThat(result.get("Pick-up point")).containsAnyElementsOf(Arrays.asList("Cookies Oatmeal Raisin", "Cheese Cloth"));
    }

}

//package com.ocado.basket;
//
//import static org.junit.Assert.*;
//
//        import java.io.IOException;
//
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//public class BasketSplitterTest {
//
//    //import static org.junit.jupiter.api.Assertions.assertEquals;
//    private BasketSplitter basketSplitter;
//
//    @BeforeEach
//    public void setUp() {
//        String absolutPathToConfigFile = "config.json";
//        try {
//            basketSplitter = new BasketSplitter(absolutPathToConfigFile);
//        } catch (IOException e) {
//            System.out.println("Error while reading config file");
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void testSplit() {
//        //            List<String> items = Arrays.asList("Cookies Oatmeal Raisin", "Cheese Cloth");
//        //            Map<String, List<String>> expected = new HashMap<>();
//        //            expected.put("Cookies Oatmeal Raisin", Arrays.asList("Pick-up point", "Parcel locker"));
//        //            expected.put("Cheese Cloth", Arrays.asList("Courier", "Parcel locker", "Same day delivery", "Next day shipping", "Pick-up point"));
//        //
//        //            Map<String, List<String>> actual = basketSplitter.split(items);
//        //
//        //            assertEquals(expected, actual);
//        assertTrue(true);
//    }
//}
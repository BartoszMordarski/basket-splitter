package com.ocado.basket;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        BasketSplitter basketSplitter = new BasketSplitter("C:/in/config.json");
        basketSplitter.split(null);
        System.out.println( "Hello World!" );
    }
}

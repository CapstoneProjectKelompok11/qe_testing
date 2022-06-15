package utils;

import java.util.Random;

public class General {

    public static String base_url = "http://ec2-18-206-213-94.compute-1.amazonaws.com/api";

    Random rand = new Random();

    public String randomUser(){
        return "user" + + rand.nextInt(1000);
    }

    public String randomEmail(){
        return "email" + + rand.nextInt(1000) + "@gmail.com";
    }
}

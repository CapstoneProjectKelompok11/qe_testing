package Utils;

import java.util.Random;

public class General {

    Random rand = new Random();

    public String randomEmail(){
        return "user" + rand.nextInt(1000) + "@gmail.com";
    }
}

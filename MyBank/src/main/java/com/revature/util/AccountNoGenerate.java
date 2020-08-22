package com.revature.util;
import java.util.Random;

public class AccountNoGenerate {

    public static String newAccountNo()
    {
        Random rand = new Random();
        String newNumber = "1000";
        for (int i = 0; i < 3; i++)
        {
            int n = rand.nextInt(10) + 0;
            newNumber += Integer.toString(n);
        }
            return newNumber;
    }


}

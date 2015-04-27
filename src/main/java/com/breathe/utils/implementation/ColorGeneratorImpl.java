package com.breathe.utils.implementation;

import com.breathe.utils.ColorGenerator;

import java.awt.*;
import java.util.Random;

/**
 * Created by denis on 17.04.15.
 */
@org.springframework.stereotype.Component
public class ColorGeneratorImpl implements ColorGenerator {
    public Color getRandomColor() {
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(255);
        int green = randomGenerator.nextInt(255);
        int blue = randomGenerator.nextInt(255);
        return new Color(red,green,blue);
    }

    public String getRandomColorString() {
        String[] letters = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String color = "#";
        for (int i = 0; i < 6; i++ ) {
            color += letters[(int) Math.round(Math.random() * 15)];
        }
        return color;
    }
}

package com.mygdx.bounceodyssey.Counter;

import com.badlogic.gdx.utils.Timer;

public class Counter {
    private int count = 0;
    private Timer timer;

    public Counter() {
        timer = new Timer();
        float delayInSeconds = 1.0f; // Starte nach 1 Sekunde
        float intervalInSeconds = 1.0f; // Wiederhole alle 1 Sekunde
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                count++;
            }
        }, delayInSeconds, intervalInSeconds);
    }

    public int getCount() {
        return count;
    }


}


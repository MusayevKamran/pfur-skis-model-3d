package ru.pfur.skis;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kamran on 5/28/2016.
 */
public class Service {

    private static AtomicInteger index = new AtomicInteger(-1);

    public static void center(JFrame frame, Integer width, Integer height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2) - width / 2;
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2) - height / 2;
        frame.setLocation(x, y);
    }

    public static Integer getNextIndex() {
        return index.incrementAndGet();
    }
}

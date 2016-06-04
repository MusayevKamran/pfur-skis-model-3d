package ru.pfur.skis.model.scheme;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 5/28/2016.
 */
public class TestScheme implements Scheme {
    public TestScheme(Model model) {
        generate(model);
//        action(model);
    }

    @Override
    public void generate(Model model) {
        double t = 0;
        double s = 0;
        Node prev = null;
        int num = 0;
        double f = (Math.PI / 2 - (-Math.PI / 2)) / 200;


        for (t = (-Math.PI / 2); t < (Math.PI / 2); t = t + f) {
            for (s = 0; s < (Math.PI / 2); s = s + 0.05) {

                num++;
                int x = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.cos(2 * (2 * Math.PI * s)) * 100);
                int y = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.sin(2 * (2 * Math.PI * s)) * 100);
                int z = (int) (Math.sin(2 * (Math.PI / 2 * t)) * Math.sin(2 * Math.PI * s) * 100);
                Node n = new Node(y, z, x);
                new AddNodeCommand(model, n);

                if (prev != null && prev != n) {
                    new AddBarCommand(model, new Bar(prev, n));
                    num++;
                }
                prev = n;
            }
        }
    }

    @Override
    public void action(Model model) {
        Thread n = new Thread(new Runnable() {
            @Override
            public void run() {
                double t = 0;
                double s = 0;
                Node prev = null;
                int num = 0;
                double f = (Math.PI / 2 - (-Math.PI / 2)) / 200;
                for (t = (-Math.PI / 2); t < (Math.PI / 2); t = t + f) {
                    for (s = 0; s < (Math.PI / 2); s = s + 0.05) {

                        num++;
                        int x = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.cos(2 * (2 * Math.PI * s)) * 100);
                        int y = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.sin(2 * (2 * Math.PI * s)) * 100);
                        int z = (int) (Math.sin(2 * (Math.PI / 2 * t)) * Math.sin(2 * Math.PI * s) * 100);
                        Node n = new Node(x, y, z);
                        new AddNodeCommand(model, n);

                        if (prev != null && prev != n) {
                            new AddBarCommand(model, new Bar(prev, n));
                            num++;
                        }
                        try {
                            Thread.currentThread().sleep(50);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        prev = n;
                    }
                }
            }
        });
        n.start();
    }
}

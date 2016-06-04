package ru.pfur.skis.model.scheme;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 6/2/2016.
 */
public class Ellipsoid implements Scheme {
    public Ellipsoid(Model model) {
        generate(model);
        action(model);
    }

    @Override
    public void generate(Model model) {
        double t;
        double s;
        Node prev = null;
        int num = 0;

        double f = (Math.PI / 2) / 100;

        for (t = 0; t < (Math.PI / 2); t = t + f) {
            for (s = 0; s < Math.PI / 3; s = s + 0.05) {
                num++;
                int x = (int) ((Math.sin(Math.PI / 2 * t) * Math.cos(Math.PI * s)) * 50);
                int y = (int) ((3 * Math.sin(Math.PI / 2 * t) * Math.sin(Math.PI * s)) * 50);
                int z = (int) ((2 * Math.cos(Math.PI * t)) * 50);
                Node n = new Node(x, y, z);
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
                double t;
                double s;
                Node prev = null;
                int num = 0;

                double f = (Math.PI / 2) / 100;

                for (t = 0; t < (Math.PI / 2); t = t + f) {
                    for (s = 0; s < Math.PI / 3; s = s + 0.05) {
                        num++;
                        int x = (int) ((Math.sin(Math.PI / 2 * t) * Math.cos(Math.PI * s)) * 50);
                        int y = (int) ((3 * Math.sin(Math.PI / 2 * t) * Math.sin(Math.PI * s)) * 50);
                        int z = (int) ((2 * Math.cos(Math.PI * t)) * 50);
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

package ru.pfur.skis.model.scheme;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 6/2/2016.
 */
public class SemiCon implements Scheme {
    public SemiCon(Model model) {
        generate(model);
    }

    @Override
    public void generate(Model model) {
        double t = 0;
        double s = 0;
        Node prev = null;
        int num = 0;

        double f = (Math.PI / 2 - (-Math.PI / 2)) / 500;

        for (t = 0; t < 1; t = t + f) {

            for (s = 0; s < 2 * Math.PI; s = s + 0.1) {
                num++;
                int x = (int) ((4 * (1 - t)) * 50);
                int y = (int) (((4 - 2 * t) * Math.cos(Math.PI * s)) * 50);
                int z = (int) ((Math.sin(Math.PI * s)) * 50);
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

    }
}

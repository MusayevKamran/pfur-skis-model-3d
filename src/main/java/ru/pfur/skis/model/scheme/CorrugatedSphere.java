package ru.pfur.skis.model.scheme;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 6/2/2016.
 */
public class CorrugatedSphere implements Scheme {
    public CorrugatedSphere(Model model) {
        generate(model);
    }

    @Override
    public void generate(Model model) {
        double t = 0;
        double s = 0;
        Node prev = null;
        int num = 0;

        for (t = 0; t < 2 * Math.PI; t = t + 0.05) {
            for (s = 0; s < Math.PI; s = s + 0.05) {

                num++;
                int x = (int) (((5 + Math.cos(2 * Math.PI * t) + 2 * (1 - Math.sin(Math.PI * t)) * Math.cos(6 * 2 * Math.PI * s)) * Math.cos(2 * Math.PI * s)) * 20);
                int y = (int) (((5 + Math.cos(2 * Math.PI * t) + 2 * (1 - Math.sin(Math.PI * t)) * Math.cos(6 * 2 * Math.PI * s)) * Math.sin(2 * Math.PI * s)) * 20);
                int z = (int) ((5 * Math.sin(Math.PI * t)) * 20);

                Node n = new Node(z, y, x);
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

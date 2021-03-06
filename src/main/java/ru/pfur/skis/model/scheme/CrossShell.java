package ru.pfur.skis.model.scheme;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 6/2/2016.
 */
public class CrossShell implements Scheme {

    public CrossShell(Model model) {
        generate(model);
    }
    @Override
    public void generate(Model model) {
        double t;
        double s;
        Node prev = null;
        int num = 0;

        for (t = 0; t < Math.PI / 3; t = t + 0.1) {
            for (s = 0; s < Math.PI; s = s + 0.1) {
                num++;
                int x = (int) ((3.1415 * t - 3.1415 * 0.5) * Math.sin(2 * Math.PI * s)) * 20;
                int y = (int) ((3.1415 * t - 3.1415 * 0.5) * Math.cos(2 * Math.PI * s)) * 20;
                int z = (int) (((2 * (Math.pow((3.1415 * t - 3.1415 * 0.5), 4) * (Math.sin(Math.pow((4 * Math.PI * s), 2))))) / 4) * 20);
                Node n = new Node(x, y, z);
                new AddNodeCommand(model, n);

                if (prev != null && prev != n) {
                    new AddBarCommand(model, new Bar(prev, n));
                    num++;
                }
                prev = n;
            }

            prev = null;
        }
    }

    @Override
    public void action(Model model) {

    }
}

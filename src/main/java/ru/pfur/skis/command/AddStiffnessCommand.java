package ru.pfur.skis.command;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Stiffness;


public class AddStiffnessCommand extends AbstractCommand implements Command {
    private Bar bar = null;
    private Stiffness stiffness = null;


    public AddStiffnessCommand(Model model, Bar bar, Stiffness stiffness) {
        super(model);
        this.bar = bar;
        this.stiffness = stiffness;
    }

    @Override
    public boolean execute() {
        try {
            bar.setStiffness(stiffness);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean unExecute() {
        try {
            bar.removeStiffness();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


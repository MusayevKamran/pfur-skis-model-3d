/**
 *
 */
package ru.pfur.skis.command;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;

public class AddBarCommand extends AbstractCommand implements Command {
    private Bar bar = null;

    public AddBarCommand(Model model, Bar bar) {
        super(model);
        this.bar = bar;
        manager.invokeCommand(this);
    }

    @Override
    public boolean execute() {
        try {
            model.addBar(bar);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean unExecute() {
        try {
            model.removeBar(bar);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}

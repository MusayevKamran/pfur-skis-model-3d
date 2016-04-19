/**
 *
 */
package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;

public class RedoCommand extends AbstractCommand implements Redo {

    public RedoCommand(Model model) {
        super(model);
        manager.invokeCommand(this);
    }

    @Override
    public boolean execute() {
        throw new NoSuchMethodError();
    }

    @Override
    public boolean unExecute() {
        throw new NoSuchMethodError();
    }

}

/**
 *
 */
package ru.pfur.skis.command;


import ru.pfur.skis.model.Model;

public class UndoCommand extends AbstractCommand implements Undo {

    public UndoCommand(Model model) {
        super(model);
        AbstractCommand.manager.invokeCommand(this);
    }

    /*
     * (non-Javadoc)
     * @see vs3.core.commands.Command#execute()
     */
    @Override
    public boolean execute() {
        throw new NoSuchMethodError();
    }

    /*
     * (non-Javadoc)
     * @see vs3.core.commands.Command#unExecute()
     */
    @Override
    public boolean unExecute() {
        throw new NoSuchMethodError();
    }

}

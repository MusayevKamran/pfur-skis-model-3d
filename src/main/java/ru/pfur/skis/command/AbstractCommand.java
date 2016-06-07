package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;

public abstract class AbstractCommand implements Command {
    protected final static CommandManager manager = CommandManager.getInstance();
    protected Model model = null;

    public AbstractCommand(Model model) {
        this.model = model;
    }
}

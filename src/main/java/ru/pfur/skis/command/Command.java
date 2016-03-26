package ru.pfur.skis.command;

public interface Command {
    boolean execute();

    boolean unExecute();
}

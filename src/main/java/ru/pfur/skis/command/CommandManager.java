/**
 *
 */
package ru.pfur.skis.command;

import java.util.LinkedList;

public class CommandManager {

    private static CommandManager instance = new CommandManager();
    private int maxHistoryLength = 100;
    private LinkedList<Command> history = new LinkedList<Command>();
    private LinkedList<Command> redoList = new LinkedList<Command>();

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        return CommandManager.instance;
    }

    private void addToHistory(AbstractCommand command) {
        history.addFirst(command);
        if (history.size() > maxHistoryLength) {
            history.removeLast();
        }
    }

    public void invokeCommand(AbstractCommand command) {
        if (command instanceof Undo) {
            undo();
            return;
        }
        if (command instanceof Redo) {
            redo();
            return;
        }
        if (command.execute()) {
            addToHistory(command);
        } else {
            history.clear();
        }
        if (redoList.size() > 0) {
            redoList.clear();
        }
    }

    private void redo() {

        if (redoList.size() > 0) {
            Command redoCmd = redoList.removeFirst();
            redoCmd.execute();
            history.addFirst(redoCmd);
        }

    }

    private void undo() {

        if (history.size() > 0) {
            Command undoCmd = history.removeFirst();
            undoCmd.unExecute();
            redoList.addFirst(undoCmd);
        }

    }
}

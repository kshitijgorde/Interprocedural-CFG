// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

import java.util.Vector;

public class CommandManager
{
    private int maxHistoryLength;
    private Vector history;
    private Vector redoList;
    
    private void addToHistory(final AbstractCommand abstractCommand) {
        this.history.insertElementAt(abstractCommand, 0);
        if (this.history.size() > this.maxHistoryLength) {
            this.history.removeElement(this.history.lastElement());
        }
    }
    
    public int getHistoryLength() {
        return this.history.size();
    }
    
    public String getLastCommandName() {
        if (this.history.size() > 0) {
            return this.history.firstElement().getName();
        }
        return "";
    }
    
    public void invokeCommand(final AbstractCommand abstractCommand) {
        if (abstractCommand instanceof Undo) {
            this.undo();
            return;
        }
        if (abstractCommand instanceof Redo) {
            this.redo();
            return;
        }
        if (abstractCommand.doIt()) {
            this.addToHistory(abstractCommand);
        }
        else {
            this.history.removeAllElements();
        }
        if (this.redoList.size() > 0) {
            this.redoList.removeAllElements();
        }
    }
    
    private void redo() {
        if (this.redoList.size() > 0) {
            final AbstractCommand abstractCommand = this.redoList.firstElement();
            this.redoList.removeElement(abstractCommand);
            abstractCommand.doIt();
            this.history.insertElementAt(abstractCommand, 0);
        }
    }
    
    private void undo() {
        if (this.history.size() > 0) {
            final AbstractCommand abstractCommand = this.history.firstElement();
            this.history.removeElement(abstractCommand);
            abstractCommand.undoIt();
            this.redoList.insertElementAt(abstractCommand, 0);
        }
    }
    
    public CommandManager() {
        this.maxHistoryLength = 5;
        this.history = new Vector();
        this.redoList = new Vector();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.command;

public abstract class AbstractCommand
{
    public static final CommandManager manager;
    protected String name;
    
    static {
        manager = new CommandManager();
    }
    
    public abstract boolean doIt();
    
    public String getName() {
        return this.name;
    }
    
    public abstract boolean undoIt();
    
    public AbstractCommand() {
        this.name = "";
    }
}

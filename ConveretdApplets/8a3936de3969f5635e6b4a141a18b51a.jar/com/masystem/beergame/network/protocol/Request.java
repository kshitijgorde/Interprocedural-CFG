// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import java.io.Serializable;

public class Request implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static int ID_NONE;
    private static int requestId;
    private int id;
    private Command command;
    private Object[] arguments;
    
    public Request(final Command command) {
        this(Request.ID_NONE, command, new Object[0]);
    }
    
    public Request(final Command command, final Object... array) {
        this(Request.ID_NONE, command, array);
    }
    
    public Request(final int id, final Command command, final Object... arguments) {
        this.id = id;
        this.setCommand(command);
        this.setArguments(arguments);
    }
    
    public final int getId() {
        return this.id;
    }
    
    public final void setId(final int id) {
        this.id = id;
    }
    
    public final void assignId() {
        this.id = Request.requestId++;
    }
    
    public final Command getCommand() {
        return this.command;
    }
    
    public final void setCommand(final Command command) {
        this.command = ((command != null) ? command : Command.NONE);
    }
    
    public final Object getArgument() {
        if (this.arguments != null && this.arguments.length > 0) {
            return this.arguments[0];
        }
        return null;
    }
    
    public final Object[] getArguments() {
        return this.arguments;
    }
    
    public final void setArguments(final Object... array) {
        if (array != null && array.length != 0) {
            System.arraycopy(array, 0, this.arguments = new Object[array.length], 0, array.length);
            return;
        }
        this.arguments = null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("Request [ command: ");
        sb.append(this.command);
        if (this.arguments != null) {
            sb.append(", arguments: ");
            for (int length = this.arguments.length, i = 0; i < length; ++i) {
                sb.append('\"');
                sb.append(this.arguments[i]);
                sb.append("\" ");
            }
            sb.append("]");
        }
        else {
            sb.append(" ]");
        }
        return sb.toString();
    }
    
    static {
        Request.ID_NONE = -1;
    }
}

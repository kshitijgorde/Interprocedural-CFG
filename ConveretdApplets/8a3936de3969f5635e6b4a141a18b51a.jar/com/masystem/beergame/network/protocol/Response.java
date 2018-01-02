// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

public class Response extends Request
{
    private static final long serialVersionUID = 1L;
    private Status status;
    
    public Response(final int id, final Command command, final Status status, final Object... arguments) {
        super(id, command, arguments);
        this.setId(id);
        this.setCommand(command);
        this.status = ((status != null) ? status : Status.NONE);
        this.setArguments(arguments);
    }
    
    public final Status getStatus() {
        return this.status;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("Response [ command: ");
        sb.append(this.getCommand());
        sb.append(", status: ");
        sb.append(this.status);
        final Object[] arguments = this.getArguments();
        if (this.getArguments() != null) {
            sb.append(", arguments: ");
            for (int length = arguments.length, i = 0; i < length; ++i) {
                sb.append('\"');
                sb.append(arguments[i]);
                sb.append("\" ");
            }
            sb.append("]");
        }
        else {
            sb.append(" ]");
        }
        return sb.toString();
    }
}

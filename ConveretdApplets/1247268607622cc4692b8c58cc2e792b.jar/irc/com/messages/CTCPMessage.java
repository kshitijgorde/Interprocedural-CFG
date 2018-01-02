// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.messages;

import java.util.Hashtable;

public class CTCPMessage
{
    public static final int UNKNOWN = 0;
    public static final int ACTION = 1;
    public static final int PING = 2;
    public static final int DCC = 3;
    public static final int VERSION = 4;
    private int command;
    private String command_string;
    private String parameter;
    private boolean hasParameter;
    private static Hashtable command_list;
    
    public static boolean isCTCPMessage(final String s) {
        return s != null && !s.equals("") && s.charAt(0) == '\u0001';
    }
    
    public CTCPMessage(final String s) {
        int length = s.length();
        if (s.endsWith("\u0001")) {
            --length;
        }
        final String substring = s.substring(1, length);
        final int index = substring.indexOf(32);
        if (-1 == index) {
            this.command_string = substring;
        }
        else {
            this.command_string = substring.substring(0, index);
            final int length2 = substring.length();
            final int n = index + 1;
            if (n < length2) {
                this.hasParameter = true;
                this.parameter = substring.substring(n, length2);
            }
        }
        final Integer n2 = CTCPMessage.command_list.get(this.command_string.toLowerCase());
        this.command = ((null != n2) ? n2 : 0);
    }
    
    public CTCPMessage(final String command_string, final String parameter) {
        this.command_string = command_string;
        this.parameter = parameter;
        this.hasParameter = true;
        final Integer n = CTCPMessage.command_list.get(command_string.toLowerCase());
        this.command = ((null != n) ? n : 0);
    }
    
    public int getCommand() {
        return this.command;
    }
    
    public String getCommandString() {
        return this.command_string;
    }
    
    public String getParameter() {
        return this.parameter;
    }
    
    public boolean hasParameter() {
        return this.hasParameter;
    }
    
    @Override
    public String toString() {
        String s = "\u0001".concat(this.command_string);
        if (this.hasParameter) {
            s = s.concat(" ").concat(this.parameter);
        }
        return s.concat("\u0001");
    }
    
    static {
        (CTCPMessage.command_list = new Hashtable()).put("action", new Integer(1));
        CTCPMessage.command_list.put("ping", new Integer(2));
        CTCPMessage.command_list.put("dcc", new Integer(3));
        CTCPMessage.command_list.put("version", new Integer(4));
    }
}

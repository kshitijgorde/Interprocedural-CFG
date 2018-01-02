// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.messages;

import irc.com.utils.MyVector;
import java.util.StringTokenizer;
import java.text.ParseException;

public class Message
{
    protected String prefix;
    protected boolean from_server;
    protected String command;
    protected String[] parameters;
    
    public Message(final String s) throws ParseException {
        if (s.trim().length() == 0) {
            throw new ParseException("Empty message", 0);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        if (!stringTokenizer.hasMoreTokens()) {
            throw new ParseException("Empty message", 0);
        }
        String command = stringTokenizer.nextToken();
        if (command.charAt(0) == ':') {
            if (command.length() == 1) {
                throw new ParseException("Empty prefix", 1);
            }
            this.prefix = command.substring(1);
            if (this.prefix.indexOf(33) < 0 && this.prefix.indexOf(64) < 0) {
                this.from_server = true;
            }
            if (!stringTokenizer.hasMoreTokens()) {
                throw new ParseException("Command expected", 0);
            }
            command = stringTokenizer.nextToken();
        }
        else {
            this.prefix = "";
        }
        this.command = command;
        if (!stringTokenizer.hasMoreTokens()) {
            throw new ParseException("Parameters expected", 0);
        }
        final MyVector myVector = new MyVector();
        final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(""), " ");
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken = stringTokenizer2.nextToken();
            if (nextToken.charAt(0) == ':') {
                break;
            }
            myVector.addElement(nextToken);
        }
        final int index = s.indexOf(":", s.indexOf(this.command));
        if (index != -1) {
            myVector.addElement(s.substring(index + 1));
        }
        myVector.copyInto(this.parameters = new String[myVector.size()]);
    }
    
    public Message(final String prefix, final String command, final String[] parameters) {
        this.prefix = prefix;
        this.command = command;
        this.parameters = parameters;
    }
    
    public Message(final String s, final String[] array) {
        this("", s, array);
    }
    
    public void close() {
        this.prefix = null;
        this.command = null;
        this.parameters = null;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public String[] getParameters() {
        return this.parameters;
    }
    
    protected String getParametersString() {
        if (this.parameters == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        if (this.parameters.length > 0) {
            for (int i = 0; i < this.parameters.length - 1; ++i) {
                final String trim = this.parameters[i].trim();
                if (trim != null || !trim.equals("")) {
                    sb.append(' ').append(trim);
                }
            }
            sb.append(" :").append(this.parameters[this.parameters.length - 1]);
        }
        return sb.toString();
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public boolean isFromServer() {
        return this.from_server;
    }
    
    public String[] slices() {
        String string = "";
        if (this.prefix.length() != 0) {
            string = string + ":" + this.prefix + " ";
        }
        final String string2 = string + this.command;
        final String parametersString = this.getParametersString();
        final MyVector myVector = new MyVector();
        final int n = 510 - string2.length();
        if (n < 0) {
            throw new RuntimeException("Prefix:" + this.prefix + " (length: " + this.prefix.length() + ")");
        }
        int i = 0;
        do {
            final int min = Math.min(n, parametersString.length() - i);
            myVector.addElement(string2 + parametersString.substring(i, i + min) + "\r\n");
            i += min;
        } while (i < parametersString.length());
        final String[] array = new String[myVector.size()];
        myVector.copyInto(array);
        return array;
    }
    
    @Override
    public String toString() {
        String string = "";
        if (this.prefix.length() != 0) {
            string = string + ":" + this.prefix + " ";
        }
        return string + this.command + this.getParametersString() + "\r\n";
    }
}

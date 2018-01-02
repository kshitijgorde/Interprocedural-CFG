// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;

public abstract class RootInterpretor extends IRCObject implements Interpretor
{
    protected StringParser _parser;
    protected Interpretor _next;
    
    public RootInterpretor(final IRCConfiguration ircConfiguration) {
        this(ircConfiguration, null);
    }
    
    public RootInterpretor(final IRCConfiguration ircConfiguration, final Interpretor nextInterpretor) {
        super(ircConfiguration);
        this.setNextInterpretor(nextInterpretor);
        this._parser = new StringParser();
    }
    
    public void setNextInterpretor(final Interpretor next) {
        this._next = next;
    }
    
    public Interpretor getNextInterpretor() {
        return this._next;
    }
    
    public boolean isInside(Interpretor nextInterpretor) {
        while (nextInterpretor != null) {
            if (nextInterpretor == this) {
                return true;
            }
            nextInterpretor = nextInterpretor.getNextInterpretor();
        }
        return false;
    }
    
    public void addLast(final Interpretor nextInterpretor) {
        if (this.isInside(nextInterpretor)) {
            return;
        }
        Interpretor nextInterpretor2;
        for (nextInterpretor2 = this; nextInterpretor2.getNextInterpretor() != null; nextInterpretor2 = nextInterpretor2.getNextInterpretor()) {}
        nextInterpretor2.setNextInterpretor(nextInterpretor);
    }
    
    protected void test(final String s, final String[] array, final int n) throws NotEnoughParametersException {
        if (array.length <= n) {
            throw new NotEnoughParametersException(s);
        }
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        if (this._next == null) {
            final Server server = source.getServer();
            if (server.isConnected()) {
                server.execute(array2[0]);
            }
            else {
                source.report(this.getText(1285));
            }
        }
        else {
            this._next.sendString(source, "/" + array2[0]);
        }
    }
    
    public void sendString(final Source source, String substring) {
        if (substring.length() == 0) {
            return;
        }
        if (substring.startsWith("/")) {
            substring = substring.substring(1);
            final String[] string = this._parser.parseString(substring);
            final String[] array = new String[string.length];
            for (int i = 0; i < array.length; ++i) {
                array[i] = "";
                for (int j = i; j < string.length; ++j) {
                    final StringBuffer sb = new StringBuffer();
                    final String[] array2 = array;
                    final int n = i;
                    array2[n] = sb.append(array2[n]).append(string[j]).append(" ").toString();
                }
                array[i] = StringParser.trim(array[i]);
            }
            for (int k = 0; k < string.length; ++k) {
                if (string[k].startsWith("\"") && string[k].endsWith("\"")) {
                    string[k] = string[k].substring(1, string[k].length() - 1);
                }
                else if (string[k].startsWith("'") && string[k].endsWith("'")) {
                    string[k] = string[k].substring(1, string[k].length() - 1);
                }
            }
            this.handleCommand(source, string[0].toLowerCase(Locale.ENGLISH), string, array);
        }
        else {
            this.say(source, substring);
        }
    }
    
    protected void say(final Source source, final String s) {
        final Server server = source.getServer();
        if (source.talkable()) {
            source.messageReceived(server.getNick(), s);
            server.say(source.getName(), s);
        }
        else {
            source.report(this.getText(1));
        }
    }
}

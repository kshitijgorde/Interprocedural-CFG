import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Component;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetProtocol extends Panel
{
    private IRCQNet theApp;
    public IRCQNetTCPClient Server;
    public IRCQNetIRCProtocol Parser;
    public Thread RunThread;
    
    public IRCQNetProtocol(final IRCQNet theApp) {
        this.theApp = theApp;
        this.add(this.Parser = new IRCQNetIRCProtocol(theApp));
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
    
    public IRCQNetParam Params() {
        return this.theApp.MPanel.getParams();
    }
    
    public IRCQNetTCPClient getServer() {
        return this.Server;
    }
    
    public boolean Connect() {
        try {
            this.add(this.Server = new IRCQNetTCPClient(this.theApp));
            (this.RunThread = new Thread(this.Server)).setPriority(1);
            this.RunThread.start();
        }
        catch (NullPointerException ex) {
            return false;
        }
        return true;
    }
    
    public boolean Login() {
        this.Server.Send("USER " + this.Params().user + " " + this.Params().userInfo + " " + this.Params().userInfo + " " + this.Params().userInfo);
        this.Server.Send("NICK " + this.Params().nick);
        this.Server.Connected();
        return true;
    }
    
    public boolean ChangeNick(final String s) {
        this.Server.Send("NICK " + s);
        return true;
    }
    
    public boolean Message(final String s, final String s2) {
        this.Server.Send("PRIVMSG " + s + " :" + s2);
        return true;
    }
    
    public void Send(final String s) {
        if (this.Server != null && this.Params().Connected) {
            this.Server.Send(s);
        }
    }
    
    public boolean Disconnect() {
        try {
            this.Server.Disconnect();
        }
        catch (NullPointerException ex) {
            return false;
        }
        return true;
    }
    
    public boolean cleanUp() {
        try {
            this.Server.cleanUp();
        }
        catch (NullPointerException ex) {
            return false;
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 10007: {
                this.parse((String)event.arg);
                return true;
            }
            case 10008: {
                this.parseToSend((String)event.arg);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public boolean Print(final String s, final String s2) {
        try {
            this.theApp.MPanel.Print(s, s2);
        }
        catch (RuntimeException ex) {
            return false;
        }
        return true;
    }
    
    public boolean parse(final String s) {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        try {
            if (s.startsWith("PING")) {
                this.Send("PONG" + s.substring(4));
                this.Print("!STATUS!", "\u00033Ping, Pong");
                return true;
            }
        }
        catch (NullPointerException ex) {
            return false;
        }
        if (s.startsWith(":")) {
            try {
                final String nextToken = stringTokenizer.nextToken(":");
                if (nextToken == null) {
                    return true;
                }
                final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken);
                if (nextToken.indexOf("!") != -1) {
                    this.parseFromUser(new String(stringTokenizer2.nextToken("!")), s);
                    return true;
                }
                if (stringTokenizer2.nextToken(" ").equalsIgnoreCase(this.Params().nick)) {
                    this.parseFromUser(this.Params().nick, s);
                    return true;
                }
                this.parseFromServer(s);
                return true;
            }
            catch (NullPointerException ex2) {
                return false;
            }
            catch (StringIndexOutOfBoundsException ex3) {
                return false;
            }
        }
        try {
            this.parseFromUser(this.Params().server, ":" + this.Params().server + " " + s);
        }
        catch (NullPointerException ex4) {
            return false;
        }
        catch (StringIndexOutOfBoundsException ex5) {
            return false;
        }
        return true;
    }
    
    public boolean parseFromUser(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>(5, 2);
        String nextToken = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, ":");
        final int countTokens = stringTokenizer.countTokens();
        StringTokenizer stringTokenizer2;
        if (countTokens >= 2) {
            stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(":"), " ");
        }
        else {
            stringTokenizer2 = new StringTokenizer(s2.substring(1));
        }
        final int countTokens2 = stringTokenizer2.countTokens();
        vector.addElement(s);
        for (int i = 0; i < countTokens2; ++i) {
            switch (i) {
                case 0: {
                    stringTokenizer2.nextToken(" ");
                    break;
                }
                case 1: {
                    nextToken = stringTokenizer2.nextToken(" ");
                    break;
                }
                default: {
                    vector.addElement(stringTokenizer2.nextToken(" "));
                    break;
                }
            }
        }
        if (countTokens >= 2) {
            vector.addElement(s2.substring(s2.indexOf(58, 2) + 1));
        }
        if (nextToken != null) {
            this.Parser.findFunction(nextToken, vector, false);
        }
        return true;
    }
    
    public boolean parseFromServer(final String s) {
        final Vector<String> vector = new Vector<String>(5, 2);
        boolean b = false;
        String nextToken = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(new StringTokenizer(s).nextToken(":"), " ");
        if (new StringTokenizer(s, ":").countTokens() < 2) {
            b = true;
        }
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            switch (i) {
                case 0: {
                    stringTokenizer.nextToken(" ");
                    break;
                }
                case 1: {
                    nextToken = stringTokenizer.nextToken(" ");
                    break;
                }
                default: {
                    vector.addElement(stringTokenizer.nextToken(" "));
                    break;
                }
            }
        }
        if (!b) {
            vector.addElement(s.substring(s.indexOf(58, 2) + 1));
        }
        if (nextToken != null) {
            this.Parser.findFunction(nextToken, vector, true);
        }
        return true;
    }
    
    public void parseToSend(final String s) {
        final int index;
        if ((index = s.indexOf(";")) >= 0) {
            this.parseToSend(s.substring(0, index), s.substring(index + 1));
        }
    }
    
    public void parseToSend(final String s, final String s2) {
        if (!this.Params().Connected) {
            return;
        }
        if (!s2.startsWith("/")) {
            if (!s.equalsIgnoreCase("!STATUS!")) {
                this.Print(s, "\u0002\u00034<\u00035" + this.Params().nick + "\u0003" + "4> " + "\u0002\u0003" + "1" + s2);
                this.Message(s, s2);
            }
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ");
            if (stringTokenizer.countTokens() > 0) {
                final String nextToken = stringTokenizer.nextToken(" ");
                if (nextToken.equalsIgnoreCase("/MSG")) {
                    if (stringTokenizer.countTokens() < 2) {
                        this.Print("!STATUS!", "/msg - Not enough parameters");
                        return;
                    }
                    this.Message(stringTokenizer.nextToken(" "), stringTokenizer.nextToken("").substring(1));
                }
                else if (nextToken.equalsIgnoreCase("/NICK")) {
                    if (stringTokenizer.countTokens() < 1) {
                        this.Print("!STATUS!", "/nick - Not enough parameters");
                        return;
                    }
                    this.ChangeNick(stringTokenizer.nextToken("").substring(1));
                }
                else if (nextToken.equalsIgnoreCase("/PING")) {
                    if (stringTokenizer.countTokens() < 1) {
                        this.Print("!STATUS!", "/ping - Not enough parameters");
                        return;
                    }
                    final String substring = stringTokenizer.nextToken("").substring(1);
                    if (this.Params().allowPing()) {
                        this.Server.Send("PRIVMSG " + substring + " :" + "\u0001PING " + System.currentTimeMillis() + "\u0001");
                        this.theApp.MPanel.PrintToCurrent("\u00034 -> [" + substring + "] PING");
                        return;
                    }
                    this.theApp.MPanel.PrintToCurrent("\u00034 -> [" + substring + "] PING " + "\u0003" + "2(flood detection, Ignoring)");
                }
                else if (nextToken.equalsIgnoreCase("/ME")) {
                    if (stringTokenizer.countTokens() < 1) {
                        this.Print("!STATUS!", "/me - Not enough parameters");
                        return;
                    }
                    if (s.equalsIgnoreCase("!STATUS!")) {
                        return;
                    }
                    final String substring2 = stringTokenizer.nextToken("").substring(1);
                    this.Message(s, "\u0001ACTION " + substring2);
                    this.Print(s, "\u00036* " + this.Params().nick + " " + substring2);
                }
                else if (nextToken.equalsIgnoreCase("/J")) {
                    if (stringTokenizer.countTokens() < 1) {
                        this.Print("!STATUS!", "/join - Not enough parameters");
                        return;
                    }
                    this.Send("JOIN " + stringTokenizer.nextToken("").substring(1));
                }
                else {
                    this.Send(s2.substring(1));
                }
            }
        }
    }
}

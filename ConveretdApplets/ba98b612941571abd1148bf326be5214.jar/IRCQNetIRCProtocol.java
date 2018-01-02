import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.awt.Event;
import java.util.Vector;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetIRCProtocol extends Panel
{
    private IRCQNet theApp;
    
    public IRCQNetIRCProtocol(final IRCQNet theApp) {
        this.theApp = theApp;
    }
    
    public boolean Print(final String s, final String s2) {
        this.theApp.MPanel.Print(s, s2);
        return true;
    }
    
    public IRCQNetParam Params() {
        return this.theApp.MPanel.getParams();
    }
    
    public boolean privateMessage(final String s, final String s2) {
        this.theApp.MPanel.getClient().Server.Send("PRIVMSG " + s + " :" + s2);
        return true;
    }
    
    public boolean Send(final String s) {
        this.theApp.MPanel.getClient().Server.Send(s);
        return true;
    }
    
    public boolean findFunction(final String s, final Vector vector, final boolean b) {
        if (s.length() < 1) {
            return false;
        }
        if (b && Character.isDigit(s.charAt(0))) {
            switch (Integer.parseInt(s)) {
                case 1: {
                    return this.M001(vector);
                }
                case 4: {
                    return this.M004(vector);
                }
                case 321: {
                    this.postEvent(new Event(this, 10002, "CleanChannelList"));
                    return true;
                }
                case 322: {
                    return this.M322(vector);
                }
                case 323: {
                    this.postEvent(new Event(this, 10003, "ShowChannelList"));
                    return true;
                }
                case 332: {
                    return this.M332(vector);
                }
                case 353: {
                    return this.M353(vector);
                }
                case 404: {
                    this.Print("!STATUS!", "\u00032*** Cannot send to channel");
                    return true;
                }
                case 405: {
                    this.Print("!STATUS!", "\u00032*** You have joined too many channels");
                    return true;
                }
                case 406: {
                    this.Print("!STATUS!", "\u00032*** There was no such nickname");
                    return true;
                }
                case 407: {
                    this.Print("!STATUS!", "\u00032*** uplicate recipients. No message delivered");
                    return true;
                }
                case 409: {
                    this.Print("!STATUS!", "\u00032*** No origin specified");
                    return true;
                }
                case 432: {
                    return this.M432(vector);
                }
                case 433: {
                    return this.M433(vector);
                }
                default: {
                    try {
                        this.Print("!STATUS!", vector.elementAt(vector.size() - 1));
                    }
                    catch (NoSuchElementException ex) {}
                    break;
                }
            }
        }
        else {
            if (s.equalsIgnoreCase("PRIVMSG")) {
                return this.PRIVMSG(vector);
            }
            if (s.equalsIgnoreCase("MODE")) {
                return this.MODE(vector);
            }
            if (s.equalsIgnoreCase("NICK")) {
                return this.NICK(vector);
            }
            if (s.equalsIgnoreCase("NOTICE")) {
                return this.NOTICE(vector);
            }
            if (s.equalsIgnoreCase("TOPIC")) {
                return this.TOPIC(vector);
            }
            if (s.equalsIgnoreCase("JOIN")) {
                return this.JOIN(vector);
            }
            if (s.equalsIgnoreCase("PART")) {
                return this.PART(vector);
            }
            if (s.equalsIgnoreCase("KICK")) {
                return this.KICK(vector);
            }
            if (s.equalsIgnoreCase("PONG")) {
                return this.PONG(vector);
            }
            if (s.equalsIgnoreCase("QUIT")) {
                return this.QUIT(vector);
            }
        }
        return false;
    }
    
    private boolean M001(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        this.Params().nick = vector.elementAt(0);
        this.Print("!STATUS!", vector.elementAt(1));
        this.Params().Connected = true;
        this.postEvent(new Event(this, 10004, "DoLogin"));
        return true;
    }
    
    private boolean M004(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        String string = "";
        for (int i = 1; i < vector.size(); ++i) {
            string = string + vector.elementAt(i) + " ";
        }
        this.Print("!STATUS!", string);
        return true;
    }
    
    private boolean M321(final Vector vector) {
        this.postEvent(new Event(this, 10002, "CleanChannelList"));
        return true;
    }
    
    private boolean M322(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final Vector<String> vector2 = new Vector<String>(3, 1);
        int i;
        for (i = 1; i < vector.size(); ++i) {
            vector2.addElement(vector.elementAt(i));
        }
        while (i < 4) {
            vector2.addElement("");
            ++i;
        }
        this.postEvent(new Event(this, 10001, vector2));
        return true;
    }
    
    private boolean M323(final Vector vector) {
        this.postEvent(new Event(this, 10003, "ShowChannelList"));
        return true;
    }
    
    private boolean M332(final Vector vector) {
        if (vector.size() < 3) {
            return false;
        }
        if (this.theApp.MPanel.GetChanControl().GetChannelWnd(vector.elementAt(1)) != null) {
            this.theApp.MPanel.GetChanControl().GetChannelWnd(vector.elementAt(1)).postEvent(new Event(this.theApp.MPanel.GetChanControl().GetChannelWnd(vector.elementAt(1)), 10018, vector.elementAt(2)));
            return true;
        }
        return true;
    }
    
    private boolean M353(final Vector vector) {
        if (vector.size() < 4) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(vector.elementAt(3), " ");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            this.theApp.MPanel.GetChanControl().AddUser(vector.elementAt(2), stringTokenizer.nextToken(" "));
        }
        return true;
    }
    
    private boolean M404(final Vector vector) {
        this.Print("!STATUS!", "\u00032*** Cannot send to channel");
        return true;
    }
    
    private boolean M405(final Vector vector) {
        this.Print("!STATUS!", "\u00032*** You have joined too many channels");
        return true;
    }
    
    private boolean M406(final Vector vector) {
        this.Print("!STATUS!", "\u00032*** There was no such nickname");
        return true;
    }
    
    private boolean M407(final Vector vector) {
        this.Print("!STATUS!", "\u00032*** uplicate recipients. No message delivered");
        return true;
    }
    
    private boolean M409(final Vector vector) {
        this.Print("!STATUS!", "\u00032*** No origin specified");
        return true;
    }
    
    private boolean M432(final Vector vector) {
        if (vector.size() < 3) {
            return false;
        }
        this.Print("!STATUS!", "\u00032*** Nickname must start with a letter");
        this.postEvent(new Event(this, 10016, "NicknameError"));
        return true;
    }
    
    private boolean M433(final Vector vector) {
        if (vector.size() < 3) {
            return false;
        }
        this.Print("!STATUS!", vector.elementAt(2));
        this.postEvent(new Event(this, 10016, "Nickname in use"));
        return true;
    }
    
    private boolean PRIVMSG(final Vector vector) {
        if (vector.size() < 3) {
            return false;
        }
        final String s = vector.elementAt(2);
        final String s2 = vector.elementAt(0);
        String s3;
        if (vector.elementAt(1).startsWith("#")) {
            s3 = vector.elementAt(1);
        }
        else {
            s3 = s2;
        }
        if (s.startsWith("\u0001ACTION")) {
            this.Print(s3, "\u00036* " + s2 + " " + s.substring(7));
            return true;
        }
        if (s.startsWith("\u0001SOUND")) {
            return true;
        }
        if (!s.startsWith("\u0001PING")) {
            this.Print(s3, "\u0002\u00032<\u000312" + s2 + "\u0003" + "2> \u0002\u0003" + "1" + s);
            return true;
        }
        if (s.indexOf("\u0001") == -1 || s.indexOf("\u0001", 2) == -1) {
            return false;
        }
        if (this.Params().allowPing()) {
            this.Send("NOTICE " + s2 + " :" + "\u0001" + "PING " + s.substring(s.indexOf(" ", 1) + 1, s.indexOf("\u0001", 2)) + "\u0001");
            this.Print("!STATUS!", "\u00034 -> [" + s2 + "] PING");
        }
        else {
            this.Print("!STATUS!", "\u00034 -> [" + s2 + "] PING " + "\u0003" + "2(flood detection, Ignoring)");
        }
        return true;
    }
    
    private boolean MODE(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final String s = vector.elementAt(0);
        String s2;
        if (vector.elementAt(1).startsWith("#")) {
            s2 = vector.elementAt(1);
        }
        else {
            s2 = "!STATUS!";
        }
        String string = "";
        for (int i = 1; i < vector.size(); ++i) {
            string = string + vector.elementAt(i) + " ";
        }
        this.Print(s2, "\u00033*** " + s + " sets mode: " + string);
        if (vector.size() > 2) {
            if (vector.elementAt(2).equalsIgnoreCase("+o")) {
                if (vector.size() < 2) {
                    return false;
                }
                final String s3 = vector.elementAt(3);
                this.theApp.MPanel.GetChanControl().ChangeUser(s3, "@" + s3);
            }
            if (vector.elementAt(2).equalsIgnoreCase("-o")) {
                if (vector.size() < 2) {
                    return false;
                }
                final String s4 = vector.elementAt(3);
                this.theApp.MPanel.GetChanControl().RemoveUser(vector.elementAt(1), s4);
                this.theApp.MPanel.GetChanControl().AddUser(vector.elementAt(1), s4);
            }
            if (vector.elementAt(2).equals("+v")) {
                if (vector.size() < 2) {
                    return false;
                }
                final String s5 = vector.elementAt(3);
                if (this.theApp.MPanel.GetChanControl().getUser(vector.elementAt(1), s5) == null) {
                    this.theApp.MPanel.GetChanControl().AddUser(vector.elementAt(1), "+" + s5);
                    return false;
                }
                if (!this.theApp.MPanel.GetChanControl().getUser(vector.elementAt(1), s5).startsWith("@")) {
                    this.theApp.MPanel.GetChanControl().ChangeUser(s5, "+" + s5);
                }
                return true;
            }
            else if (vector.elementAt(2).equals("-v")) {
                if (vector.size() < 2) {
                    return false;
                }
                final String s6 = vector.elementAt(3);
                if (this.theApp.MPanel.GetChanControl().getUser(vector.elementAt(1), s6) == null) {
                    this.theApp.MPanel.GetChanControl().AddUser(vector.elementAt(1), s6);
                    return false;
                }
                if (!this.theApp.MPanel.GetChanControl().getUser(vector.elementAt(1), s6).startsWith("@")) {
                    this.theApp.MPanel.GetChanControl().RemoveUser(vector.elementAt(1), s6);
                    this.theApp.MPanel.GetChanControl().AddUser(vector.elementAt(1), s6);
                }
                return true;
            }
        }
        return true;
    }
    
    private boolean NICK(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final String s = vector.elementAt(0);
        final String nick = vector.elementAt(1);
        if (s.equalsIgnoreCase(this.Params().nick)) {
            this.theApp.MPanel.PrintNickChange("You have changed your nick to: " + vector.elementAt(1));
            this.Params().nick = nick;
            this.theApp.MPanel.GetChanControl().ChangeUser(s, nick);
        }
        else {
            this.theApp.MPanel.PrintNickChange(s + " has changed his nick to: " + vector.elementAt(1));
            this.theApp.MPanel.GetChanControl().ChangeUser(s, nick);
        }
        return true;
    }
    
    private boolean PONG(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        this.theApp.MPanel.PrintToCurrent("\u00034 [" + vector.elementAt(vector.size() - 1) + "] PONG");
        return true;
    }
    
    private boolean NOTICE(final Vector vector) {
        try {
            if (vector.size() < 3) {
                return false;
            }
            if (vector.elementAt(1).charAt(0) == '#') {
                this.Print(vector.elementAt(1), "\u0002\u00035[ " + vector.elementAt(0) + " - " + vector.elementAt(1) + " ]: \u0002\u0003" + "1" + vector.elementAt(2));
            }
            else if (vector.elementAt(2).startsWith("\u0001PING")) {
                this.Print("!STATUS!", "\u00035[" + vector.elementAt(0) + " PING reply]: " + (int)((System.currentTimeMillis() - new Long(vector.elementAt(2).substring(6, vector.elementAt(2).length() - 1))) / 1000L) + "sec");
            }
            else {
                this.Print("!STATUS!", "\u00035-" + vector.elementAt(0) + "- " + vector.elementAt(2));
            }
        }
        catch (Exception ex) {
            return true;
        }
        return true;
    }
    
    private boolean TOPIC(final Vector vector) {
        if (vector.size() < 3) {
            return false;
        }
        final String s = vector.elementAt(1);
        final String s2 = vector.elementAt(2);
        if (this.theApp.MPanel.GetChanControl().GetChannelWnd(s) != null) {
            this.theApp.MPanel.GetChanControl().GetChannelWnd(s).postEvent(new Event(this.theApp.MPanel.GetChanControl().GetChannelWnd(s), 10018, s2));
            return true;
        }
        return false;
    }
    
    private boolean JOIN(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final String s = vector.elementAt(0);
        final String s2 = vector.elementAt(1);
        if (s.equalsIgnoreCase(this.Params().nick)) {
            this.theApp.MPanel.NewChannelCard(s2);
        }
        else {
            this.theApp.MPanel.GetChanControl().AddUser(s2, s);
        }
        return true;
    }
    
    private boolean PART(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final String s = vector.elementAt(0);
        final String s2 = vector.elementAt(1);
        if (s.equalsIgnoreCase(this.Params().nick)) {
            this.theApp.MPanel.DelChannelCard(s2);
            return true;
        }
        this.theApp.MPanel.GetChanControl().RemoveUser(s2, s);
        return true;
    }
    
    private boolean QUIT(final Vector vector) {
        if (vector.size() < 2) {
            return false;
        }
        final String s = vector.elementAt(0);
        this.theApp.MPanel.GetChanControl().RemoveUserQ(s, s + " has quit \u0002\u0003" + "12IrCQNet\u0002\u0003" + "2 (" + vector.elementAt(1) + ")");
        return true;
    }
    
    private boolean KICK(final Vector vector) {
        if (vector.size() < 4) {
            return false;
        }
        final String s = vector.elementAt(0);
        final String s2 = vector.elementAt(1);
        final String s3 = vector.elementAt(2);
        final String s4 = vector.elementAt(3);
        if (s3.equalsIgnoreCase(this.Params().nick)) {
            this.Print("!STATUS!", "\u00033*** You were kicked from " + s2 + " by " + s + " (" + s4 + ")");
            this.theApp.MPanel.DelChannelCard(s2);
        }
        else {
            this.Print(s2, "\u00033*** " + s3 + " was kicked by " + s + " (" + s4 + ")");
            this.theApp.MPanel.GetChanControl().RemoveUser(s2, s3);
        }
        return true;
    }
}

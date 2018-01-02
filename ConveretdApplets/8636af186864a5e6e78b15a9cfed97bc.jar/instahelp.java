import java.awt.Graphics;
import netscape.javascript.JSObject;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class instahelp extends Applet
{
    boolean startserver;
    static boolean serverstarted;
    static Client clsocket;
    JSObject m_JScriptWin;
    
    public void stop() {
    }
    
    public void send_typingendnotification() {
        try {
            instahelp.clsocket.typingendnotification();
        }
        catch (Exception ex) {}
    }
    
    public void get_typingendnotification() {
        try {
            if (this.m_JScriptWin != null) {
                this.m_JScriptWin.call("get_typingendnotification", (Object[])new String[] { "placeholdertext" });
            }
        }
        catch (Exception ex) {}
    }
    
    public instahelp() {
        this.startserver = true;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void destroy() {
        try {
            instahelp.clsocket.clsocket.close();
        }
        catch (Exception ex) {}
    }
    
    public void getmessage(final String s) {
        try {
            if (this.m_JScriptWin != null) {
                this.m_JScriptWin.call("getmessage", (Object[])new String[] { s });
            }
        }
        catch (Exception ex) {}
    }
    
    public void sendmessage(final String s) {
        try {
            instahelp.clsocket.sendmessage(s);
        }
        catch (Exception ex) {
            try {
                if (this.m_JScriptWin != null) {
                    this.m_JScriptWin.call("disconnected", (Object[])new String[] { "The Chat Session Could Not Be Started Due to a Network Error" });
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void disconnected() {
        try {
            if (this.m_JScriptWin != null) {
                this.m_JScriptWin.call("disconnected", (Object[])new String[] { "The Chat Session Was Terminated  Due to a Network Error" });
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        instahelp.serverstarted = false;
    }
    
    public void send_typingnotification() {
        try {
            instahelp.clsocket.typingnotification();
        }
        catch (Exception ex) {}
    }
    
    public void get_typingnotification() {
        try {
            if (this.m_JScriptWin != null) {
                this.m_JScriptWin.call("get_typingnotification", (Object[])new String[] { "placeholdertext" });
            }
        }
        catch (Exception ex) {}
    }
    
    private static String unescape(final String s) {
        final StringBuffer sb = new StringBuffer();
        final int length = s.length();
        int n = 0;
        int i = 0;
        int n2 = -1;
        while (i < length) {
            final char char1;
            int n3 = 0;
            switch (char1 = s.charAt(i)) {
                case '%': {
                    final char char2 = s.charAt(++i);
                    final char c = (char)((Character.isDigit(char2) ? (char2 - '0') : ('\n' + Character.toLowerCase(char2) - 'a')) & '\u000f');
                    final char char3 = s.charAt(++i);
                    n3 = (c << 4 | ((Character.isDigit(char3) ? (char3 - '0') : ('\n' + Character.toLowerCase(char3) - 'a')) & '\u000f'));
                    break;
                }
                case '+': {
                    n3 = 32;
                    break;
                }
                default: {
                    n3 = char1;
                    break;
                }
            }
            if ((n3 & 0xC0) == 0x80) {
                n = (n << 6 | (n3 & 0x3F));
                if (--n2 == 0) {
                    sb.append((char)n);
                }
            }
            else if ((n3 & 0x80) == 0x0) {
                sb.append((char)n3);
            }
            else if ((n3 & 0xE0) == 0xC0) {
                n = (n3 & 0x1F);
                n2 = 1;
            }
            else if ((n3 & 0xF0) == 0xE0) {
                n = (n3 & 0xF);
                n2 = 2;
            }
            else if ((n3 & 0xF8) == 0xF0) {
                n = (n3 & 0x7);
                n2 = 3;
            }
            else if ((n3 & 0xFC) == 0xF8) {
                n = (n3 & 0x3);
                n2 = 4;
            }
            else {
                n = (n3 & 0x1);
                n2 = 5;
            }
            ++i;
        }
        return sb.toString();
    }
    
    public void terminate() {
        try {
            if (this.m_JScriptWin != null) {
                this.m_JScriptWin.call("terminate", (Object[])new String[] { "The Chat Session Was Terminated  Due to a Network Error" });
                this.destroy();
            }
        }
        catch (Exception ex) {}
    }
    
    public void init() {
        if (System.getProperty("java.vendor").toString().startsWith("Microsoft")) {
            try {
                if (Class.forName("com.ms.security.PolicyEngine") != null) {}
            }
            catch (Throwable t) {
                this.m_JScriptWin.call("disconnected", (Object[])new String[] { "The Chat Session Could Not Be Started Due To Unavailable Permissions" });
            }
        }
        try {
            this.m_JScriptWin = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        final String s = "Chat Applet Loaded";
        final String s2 = "Please Wait for The Operator To Accept the connection";
        this.getmessage("<font color=\"#000000\">" + s + "</font>");
        this.getmessage("<font color=\"#000000\">" + s2 + "</font>");
        try {
            boolean b = false;
            if (this.getParameter("ssl").compareTo("true") == 0) {
                b = true;
                this.getmessage("Starting SSL Session");
            }
            (instahelp.clsocket = new Client(this, this.getParameter("server"), this.getParameter("operatorid"), this.getParameter("sessid"), b, Integer.parseInt(this.getParameter("port")))).sendmessage("You are talking to " + unescape(this.getParameter("visitorname")));
        }
        catch (Exception ex2) {
            try {
                if (this.m_JScriptWin != null) {
                    this.m_JScriptWin.call("disconnected", (Object[])new String[] { "The Chat Session Could Not Be Started Due to a Network Error" });
                }
            }
            catch (Exception ex3) {}
        }
    }
}

import java.io.InputStream;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class password extends Applet implements Runnable
{
    protected final int FWD = 1;
    protected final int BWD = 2;
    protected final int GET = 3;
    protected final int ADD = 4;
    protected final int MAX = 50;
    private String startURL;
    private String name;
    private String password;
    private boolean cancel;
    private NavPanel navpan;
    private URL homeURL;
    private URL errorURL;
    private URL pswURL;
    private boolean useBase;
    private Vector keys;
    private cipher c3;
    private PswFrame pswWin;
    private Thread pswMon;
    private Vector list;
    private int curr;
    private boolean load;
    
    public void init() {
        if (this.startURL == null) {
            final String parameter = this.getParameter("start_url");
            this.startURL = ((parameter == null) ? new String() : parameter);
        }
        final String parameter2 = this.getParameter("bgcolor");
        int rgb;
        try {
            rgb = ((parameter2 == null) ? Color.white.getRGB() : Integer.parseInt(parameter2, 16));
        }
        catch (Exception ex7) {
            rgb = Color.white.getRGB();
        }
        this.setBackground(new Color(rgb));
        final String parameter3 = this.getParameter("home_url");
        if (parameter3 != null) {
            try {
                if (parameter3.indexOf(":") == -1) {
                    this.homeURL = new URL(this.getDocumentBase(), parameter3);
                }
                else {
                    this.homeURL = new URL(parameter3);
                }
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        if (this.homeURL == null) {
            try {
                this.homeURL = new URL(this.getDocumentBase(), "./");
            }
            catch (Exception ex2) {
                System.err.println(ex2);
            }
        }
        final String parameter4 = this.getParameter("error_url");
        if (parameter4 != null) {
            try {
                if (parameter4.indexOf(":") == -1) {
                    this.errorURL = new URL(this.getDocumentBase(), parameter4);
                }
                else {
                    this.errorURL = new URL(parameter4);
                }
            }
            catch (Exception ex3) {
                System.err.println(ex3);
            }
        }
        if (this.errorURL == null) {
            try {
                this.errorURL = new URL(this.getDocumentBase(), "./error.htm");
            }
            catch (Exception ex4) {
                System.err.println(ex4);
            }
        }
        if (this.pswURL == null) {
            final String parameter5 = this.getParameter("psw_url");
            if (parameter5 != null) {
                try {
                    if (parameter5.indexOf(":") == -1) {
                        this.pswURL = new URL(this.getDocumentBase(), parameter5);
                    }
                    else {
                        this.pswURL = new URL(parameter5);
                    }
                }
                catch (Exception ex5) {
                    System.err.println(ex5);
                }
            }
        }
        if (this.pswURL == null) {
            try {
                this.pswURL = new URL(this.getDocumentBase(), "./users.txt");
            }
            catch (Exception ex6) {
                System.err.println(ex6);
            }
        }
        final String parameter6 = this.getParameter("use_base");
        if (parameter6 != null) {
            this.useBase = !parameter6.equalsIgnoreCase("false");
        }
        this.setFont(new Font("SansSerif", 0, 14));
        (this.navpan = new NavPanel(this)).setVisible(false);
        this.add(this.navpan);
    }
    
    public void start() {
        if (this.pswMon == null) {
            this.pswWin = new PswFrame(this, this.pswURL);
            (this.pswMon = new Thread(this)).start();
        }
    }
    
    public void run() {
        int n = 0;
        while (this.pswWin.isShowing() && n < 120) {
            try {
                Thread.sleep(500L);
                ++n;
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (this.pswWin.isValid()) {
            this.pswWin.dispose();
            this.navpan.setVisible(true);
            this.goStart();
            return;
        }
        this.pswWin.dispose();
        this.redirect(this.errorURL, "_top");
    }
    
    public void stop() {
        this.pswMon = null;
    }
    
    public void setStartURL(final String startURL) {
        if (startURL != "") {
            this.startURL = startURL;
        }
    }
    
    public void setPswURL(final String s) {
        if (s != "") {
            try {
                if (s.indexOf(":") == -1) {
                    this.pswURL = new URL(this.getDocumentBase(), s);
                    return;
                }
                this.pswURL = new URL(s);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }
    
    public synchronized String getURL() {
        String content = new String();
        if (this.load) {
            content = this.getContent(this.list.elementAt(this.curr));
        }
        this.load = false;
        return content;
    }
    
    public synchronized void setList(final int n, final URL url) {
        if (n == 2) {
            if (this.curr > 0) {
                --this.curr;
                this.load = true;
            }
        }
        else if (n == 1) {
            if (this.curr < this.list.size() - 1) {
                ++this.curr;
                this.load = true;
            }
        }
        else if (n == 3) {
            this.load = true;
        }
        else if (n == 4) {
            if (this.curr == this.list.size() - 1) {
                if (this.list.size() < 50) {
                    this.list.addElement(url);
                    ++this.curr;
                }
                else {
                    this.list.removeElementAt(0);
                    this.list.addElement(url);
                }
            }
            else {
                this.list.insertElementAt(url, this.curr + 1);
                ++this.curr;
                for (int i = this.list.size() - 1; i > this.curr; --i) {
                    this.list.removeElementAt(i);
                }
            }
        }
        if (this.curr > 0) {
            this.navpan.setEnabled("Back", true);
        }
        else {
            this.navpan.setEnabled("Back", false);
        }
        if (this.curr < this.list.size() - 1) {
            this.navpan.setEnabled("Next", true);
            return;
        }
        this.navpan.setEnabled("Next", false);
    }
    
    private void goStart() {
        System.out.println("Loading start page...");
        final char c = (char)(this.startURL.charAt(0) - 'd');
        final String decode = this.c3.decode(this.startURL.substring(c + '\u0001'), this.startURL.substring(1, c + '\u0001'));
        try {
            URL url;
            if (decode.indexOf(":") == -1) {
                url = new URL(this.getDocumentBase(), decode);
            }
            else {
                url = new URL(decode);
            }
            this.setList(4, url);
            this.setList(3, null);
        }
        catch (Exception ex) {
            System.err.println(ex);
            this.redirect(this.errorURL, "_top");
        }
    }
    
    public String fetchURL(final String s) {
        return this.loadPage(s.substring(2), this.keys.elementAt(Integer.valueOf(s.substring(0, 2))));
    }
    
    private String loadPage(String decode, final String s) {
        String s2 = new String();
        decode = this.c3.decode(decode, s);
        try {
            URL url;
            if (decode.indexOf(":") == -1) {
                url = new URL(this.getDocumentBase(), decode);
            }
            else {
                url = new URL(decode);
            }
            this.setList(4, url);
            s2 = this.getContent(url);
        }
        catch (Exception ex) {
            System.err.println(ex);
            s2 = String.valueOf(s2) + "<hr><h3>Invalid URL</h3>";
        }
        return s2;
    }
    
    private String getContent(final URL url) {
        final StringBuffer sb = new StringBuffer();
        this.navpan.setEnabled("Reload", false);
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openStream());
            if (this.useBase) {
                sb.append("<BASE HREF=\"" + url.toString().substring(0, url.toString().lastIndexOf(47) + 1) + "\">\n");
            }
            this.keys = new Vector(10);
            while (true) {
                final char c = (char)dataInputStream.readByte();
                if (c == '<') {
                    sb.append(this.checkTag(readTag(dataInputStream)));
                }
                else {
                    sb.append(c);
                }
            }
        }
        catch (EOFException ex2) {
            System.out.println("Done loading page " + this.curr + ": " + sb.length() + " bytes");
        }
        catch (Exception ex) {
            System.err.println(ex);
            sb.append("<hr><h3>Error Opening Input Stream</h3>");
        }
        this.navpan.setEnabled("Reload", true);
        return sb.toString();
    }
    
    private static String readTag(final DataInputStream dataInputStream) {
        final StringBuffer sb = new StringBuffer("<");
        char c = '<';
        try {
            while (c != '>') {
                c = (char)dataInputStream.readByte();
                sb.append(c);
            }
        }
        catch (EOFException ex2) {}
        catch (Exception ex) {
            System.err.println(ex);
        }
        return sb.toString();
    }
    
    private String checkTag(String s) {
        final StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '\r' || sb.charAt(i) == '\n') {
                sb.setCharAt(i, ' ');
            }
        }
        final String string = sb.toString();
        try {
            final String upperCase = string.toUpperCase();
            if (upperCase.startsWith("<A HREF")) {
                final int index = upperCase.indexOf("PARENT.NAVPANE.FETCH(");
                if (index == -1) {
                    return s;
                }
                int n;
                for (n = index + 21; Character.isWhitespace(upperCase.charAt(n)); ++n) {}
                if (upperCase.charAt(n) == '\'') {
                    ++n;
                }
                int n2;
                for (n2 = n + 1; !Character.isWhitespace(upperCase.charAt(n2)) && upperCase.charAt(n2) != '\'' && upperCase.charAt(n2) != ')'; ++n2) {}
                final String substring = s.substring(n, n2);
                final String key = this.c3.getKey(substring);
                this.keys.addElement(key);
                final String encode = this.c3.encode(substring, key);
                final String string2 = Integer.toString(this.keys.size() - 1);
                s = String.valueOf(string.substring(0, n)) + ((string2.length() < 2) ? ("0" + string2 + encode) : (String.valueOf(string2) + encode)) + string.substring(n2, string.length());
            }
            else {
                if (upperCase.startsWith("<TITLE>")) {
                    s = "<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">\n" + string;
                    return s;
                }
                return s;
            }
        }
        catch (StringIndexOutOfBoundsException ex2) {}
        catch (Exception ex) {
            System.err.println(ex);
        }
        return s;
    }
    
    public void home() {
        this.redirect(this.homeURL, "_top");
    }
    
    private void redirect(final URL url, final String s) {
        try {
            final InputStream openStream = url.openStream();
            openStream.read();
            openStream.close();
            this.getAppletContext().showDocument(url, s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public password() {
        this.useBase = true;
        this.c3 = new cipher3();
        this.list = new Vector(10);
        this.curr = -1;
        this.load = false;
    }
}

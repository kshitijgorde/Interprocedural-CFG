import java.awt.Color;
import java.util.Date;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends Thread
{
    public String a4;
    public String a3;
    public String a2;
    public Chat a1;
    public boolean a0;
    public boolean a_;
    public InputStream az;
    public OutputStream ay;
    public n ax;
    public BufferedReader aw;
    public PrintStream av;
    public String au;
    public String at;
    public StringTokenizer as;
    public String ar;
    public String aq;
    public String ap;
    public String ao;
    public Vector an;
    public Dimension am;
    public boolean al;
    public int ak;
    
    public f() {
        super("IRC Protocal Engine Thread");
        this.a4 = String.valueOf('\u0001');
        this.a3 = "\u0003";
        this.a0 = true;
        this.a_ = false;
        this.am = new Dimension(400, 250);
        this.al = false;
        this.an = new Vector();
    }
    
    public final void aq(final Chat a1) {
        this.a1 = a1;
    }
    
    private final boolean ap() {
        try {
            this.ax = new n(this.a1.bk(), this.a1.hc.e1, this.a1.hc.e4);
            this.ae(this.a1.hc.cu);
            this.a1.bv(true);
            this.az = this.ax.bc();
            this.ay = this.ax.bb();
            this.aw = new BufferedReader(new InputStreamReader(this.az));
            this.av = new PrintStream(this.ay);
        }
        catch (IOException ex) {
            this.a1.ar("Unable to connect to server.");
            this.a1.w(true);
            this.z();
            return false;
        }
        return true;
    }
    
    public final void run() {
        this.ap();
        if (this.aw == null || this.av == null) {
            return;
        }
        this.aj();
    Label_0024_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.a2 = this.aw.readLine();
                        if (this.a2 == null) {
                            break;
                        }
                        if (this.am(this.a2)) {
                            continue Label_0024_Outer;
                        }
                        if (this.a2.startsWith("PING :")) {
                            this.ak(this.a2.substring(5));
                        }
                        else if (this.a2.startsWith("ERROR :")) {
                            this.al(this.a2.substring(7));
                        }
                        else {
                            if (this.a2.startsWith("NOTICE ") && this.a1.hc.dl) {
                                continue Label_0024_Outer;
                            }
                            this.ae("*** " + this.a2);
                        }
                    }
                    this.a1.w(this.a0);
                    this.z();
                    return;
                }
                catch (IOException ex) {
                    this.a1.ar(ex.toString());
                    this.a1.w();
                    this.z();
                    continue Label_0024_Outer;
                }
                catch (NoSuchElementException ex2) {
                    continue Label_0024_Outer;
                }
                continue;
            }
        }
    }
    
    public final void ao(final String s, final String s2, final String s3, final String s4, final String s5) {
        int intValue;
        try {
            intValue = new Integer(s2);
        }
        catch (NumberFormatException ex) {
            intValue = -1;
        }
        if (intValue >= 250 && intValue <= 269 && this.a1.hc.dl) {
            return;
        }
        switch (intValue) {
            case 1: {
                if (!this.a1.hc.dl) {
                    this.ae("*** " + s4 + " " + s5);
                }
                this.al = true;
                return;
            }
            case 2: {
                final p hc = this.a1.hc;
                this.a1.hc.e3 = s3;
                hc.e0 = s3;
                this.a1.gk.setText(this.a1.hc.e0);
                this.a0 = false;
                this.y("#" + this.a1.hc.e2);
                return;
            }
            case 3: {
                if (this.a1.hc.dl) {
                    return;
                }
            }
            case 4: {
                if (this.a1.hc.dl) {
                    return;
                }
            }
            case 252: {
                this.ae("*** " + s4 + " " + s5);
                return;
            }
            case 254: {
                this.ae("*** " + s4 + " " + s5);
                return;
            }
            case 311: {
                this.ae("*** [User of " + s4 + " :" + s5 + "]");
                return;
            }
            case 312: {
                this.ae("*** [Server of " + s4 + " :" + s5 + "]");
                return;
            }
            case 317: {
                this.ae("*** [Idle time of " + s4 + " :" + s5 + "]");
                return;
            }
            case 318: {
                this.ae("*** " + s5);
                return;
            }
            case 319: {
                this.ae("*** [Channel of " + s4 + ":" + s5 + "]");
                return;
            }
            case 332: {
                this.ae("*** Topic of " + s4 + " :" + s5);
                this.a1.g5.c(s5);
                return;
            }
            case 352: {
                this.ae("[" + s4 + " :" + s5 + "]");
                return;
            }
            case 353: {
                if (this.a1.he) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s5);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.a1.br(stringTokenizer.nextToken());
                    }
                }
                return;
            }
            case 366: {
                this.a1.he = false;
                return;
            }
            case 433: {
                this.ae("*** NickName Already in use: " + this.a1.hc.e3);
                if (!this.al && this.ak <= 9) {
                    if (this.ak > 0) {
                        this.a1.hc.e3 = this.a1.hc.e3.substring(0, this.a1.hc.e3.length() - 1);
                    }
                    this.a1.hc.e3 = String.valueOf(this.a1.hc.e3) + ++this.ak;
                    this.a1.gk.setText(this.a1.hc.e3);
                    this.ae("*** Try to use " + this.a1.hc.e3 + " now ....");
                    this.ag("NICK " + this.a1.hc.e3);
                    return;
                }
                this.ac("*** Type /nick new_name to enter");
                return;
            }
            case 372: {
                if (this.a1.hc.dm) {
                    return;
                }
            }
            case 375: {
                if (this.a1.hc.dm) {
                    return;
                }
            }
            case 376: {
                if (this.a1.hc.dm) {
                    return;
                }
                break;
            }
            case 333: {
                return;
            }
        }
        this.ae("*** " + s4 + " " + s5);
    }
    
    public final void an(final String s, final String s2, final String s3, String trim, String e0) {
        String substring;
        try {
            substring = s.substring(0, s.indexOf("!"));
        }
        catch (StringIndexOutOfBoundsException ex2) {
            substring = s;
        }
        if (s2.equals("QUIT")) {
            if (!this.a1.hc.dk) {
                this.ad("*** " + substring + " Signoff (" + e0 + ")");
            }
            this.a1.bq(substring);
            return;
        }
        if (s2.equals("NICK")) {
            if (substring.equals(this.a1.hc.e0)) {
                this.a1.hc.e0 = e0;
                this.a1.hc.e3 = this.a1.hc.e0;
                this.a1.gk.setText(this.a1.hc.e3);
            }
            this.ad("*** " + substring + " is now Known as " + e0);
            this.a1.bo(substring, e0);
            return;
        }
        if (!s2.equals("PART")) {
            if (s2.equals("JOIN")) {
                if (!this.a1.hc.dk) {
                    this.ad("*** " + substring + " (" + s.substring(s.indexOf("!") + 1) + ") has joined the channel " + e0);
                }
                if (substring.equals(this.a1.hc.e0)) {
                    this.a1.hc.e_ = e0.substring(1);
                    this.a1.hc.e2 = this.a1.hc.e_;
                    this.a1.gj.setText(this.a1.hc.e2);
                    this.a1.he = true;
                    this.a1.bs();
                }
                else {
                    this.a1.br(substring);
                }
                if (!this.a_) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.a1.hc.ev, ";", false);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.a1.as(stringTokenizer.nextToken(), false, true);
                    }
                    this.a_ = true;
                }
            }
            else if (s2.equals("PRIVMSG")) {
                if (s3.equalsIgnoreCase("#" + this.a1.hc.e_)) {
                    if (e0.startsWith(this.a4)) {
                        e0 = e0.trim();
                        this.v(substring, e0);
                        return;
                    }
                    if (this.a1.bp(substring)) {
                        this.aa(substring, e0);
                        return;
                    }
                    this.aa(String.valueOf(substring) + "/" + s3, e0);
                }
                else if (s3.equalsIgnoreCase(this.a1.hc.e0)) {
                    if (e0.startsWith(this.a4)) {
                        e0 = e0.trim();
                        this.v(substring, e0);
                        return;
                    }
                    if (!this.s(substring, e0)) {
                        this.aa("*" + substring + "*", e0);
                    }
                }
            }
            else {
                if (s2.equalsIgnoreCase("MODE")) {
                    if (s3.startsWith("#")) {
                        trim.trim();
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(trim);
                        final String nextToken = stringTokenizer2.nextToken();
                        final int length = nextToken.length();
                        boolean b = false;
                        try {
                            for (int i = 0; i < length; ++i) {
                                final String substring2 = nextToken.substring(i, i + 1);
                                if (substring2.equals("+")) {
                                    b = true;
                                }
                                else if (substring2.equals("-")) {
                                    b = false;
                                }
                                else {
                                    String s4;
                                    if (substring2.equals("o")) {
                                        s4 = "@";
                                    }
                                    else if (substring2.equals("v")) {
                                        s4 = "+";
                                    }
                                    else {
                                        s4 = "";
                                    }
                                    if (!s4.equals("")) {
                                        this.a1.bn(stringTokenizer2.nextToken(), s4, b);
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (!this.a1.hc.dj) {
                            this.ad("*** Mode change \"" + trim + "\" on " + "channel " + s3 + " by " + substring);
                        }
                    }
                    else {
                        if (this.a1.hc.dj) {
                            return;
                        }
                        this.ad("*** Mode change \"" + e0 + "\" for user " + s3 + " by " + substring);
                    }
                    return;
                }
                if (s2.equalsIgnoreCase("NOTICE")) {
                    if (e0.startsWith(this.a4)) {
                        this.ad("*** CTCP reply from " + substring + " :" + e0.substring(1));
                        return;
                    }
                    this.ad("--" + substring + "-- " + e0);
                }
                else if (s2.equalsIgnoreCase("KICK")) {
                    trim = trim.trim();
                    this.ad("*** " + trim + " has been kicked off " + "channel " + s3 + " by " + substring + " (" + e0 + ")");
                    if (trim.equalsIgnoreCase(this.a1.hc.e0)) {
                        this.a1.gj.setText(this.a1.hc.e_);
                        this.a1.hc.e_ = "";
                        this.a1.bt();
                        return;
                    }
                    this.a1.bq(trim);
                }
                else {
                    if (s2.equalsIgnoreCase("TOPIC")) {
                        this.ad("*** " + substring + " has changed the topic to " + "\"" + e0 + " \u000f\"  on channel " + s3);
                        this.a1.g5.c(e0);
                        return;
                    }
                    this.ad("<#" + substring + "#>" + s2 + " " + trim + " " + e0);
                }
            }
            return;
        }
        if (substring.equals(this.a1.hc.e0)) {
            if (!this.a1.hc.dk) {
                this.ad("*** You  have left the channel " + s3);
            }
            this.a1.hc.e_ = "";
            this.a1.bt();
            return;
        }
        if (!this.a1.hc.dk) {
            this.ad("*** " + substring + " has left the channel " + s3);
        }
        this.a1.bq(substring);
    }
    
    public final boolean am(final String s) {
        if (s.startsWith(":")) {
            int index = s.indexOf(" :", 1);
            if (index != -1) {
                ++index;
                this.au = s.substring(1, index);
            }
            else {
                this.au = s.substring(1);
            }
            this.as = new StringTokenizer(this.au);
            this.ar = this.as.nextToken();
            this.aq = this.as.nextToken();
            if (this.as.hasMoreTokens()) {
                this.ap = this.as.nextToken();
            }
            else {
                this.ap = "";
            }
            this.ao = "";
            while (this.as.hasMoreTokens()) {
                this.ao = String.valueOf(this.ao) + this.as.nextToken() + " ";
            }
            if (index != -1) {
                this.at = s.substring(index + 1);
            }
            else {
                this.at = "";
            }
            try {
                Integer.parseInt(this.aq);
                this.ao(this.ar, this.aq, this.ap, this.ao, this.at);
                return true;
            }
            catch (NumberFormatException ex) {
                this.an(this.ar, this.aq, this.ap, this.ao, this.at);
                return true;
            }
        }
        return false;
    }
    
    public final void al(final String s) {
        this.ac(s);
    }
    
    public final void ak(final String s) {
        this.ag("PONG " + s);
    }
    
    public final void aj() {
        if (this.a1.hc.ey != null && !this.a1.hc.ey.equals("")) {
            this.ag("PASS " + this.a1.hc.ey);
        }
        this.ag("NICK " + this.a1.hc.e3 + " " + this.a1.hc.ew);
        if (!this.a1.hc.bm.equals("")) {
            this.ag("USER " + this.a1.hc.bm + " " + this.a1.hc.e9 + " " + this.a1.hc.e1 + " :" + this.a1.hc.ez);
            return;
        }
        this.ag("USER " + this.a1.hc.e3 + " " + this.a1.hc.e9 + " " + this.a1.hc.e1 + " :" + this.a1.hc.ez);
    }
    
    public final void ai() {
        if (this.al) {
            this.ag("QUIT :" + this.a1.hc.cs);
        }
        this.al = false;
    }
    
    public final void ah(String r) {
        r = this.r(r);
        try {
            if (r.startsWith("/debug") || r.startsWith("/quote")) {
                this.ag(r.substring(6));
                return;
            }
            if (r.startsWith("/nick ")) {
                this.x(r.substring("/nick".length() + 1));
                return;
            }
            if (r.startsWith("/s ")) {
                this.ad("* " + this.a1.hc.e3 + " " + "SOUND " + r.substring(3));
                this.a1.bj(r.substring(3));
                this.ag("PRIVMSG #" + this.a1.hc.e_ + " :" + this.a4 + "SOUND " + r.substring(3) + this.a4);
                return;
            }
            if (r.startsWith("/join ")) {
                this.y(r.substring("/join".length() + 1));
                return;
            }
            if (r.startsWith("/msg ")) {
                try {
                    final String nextToken = new StringTokenizer(r.substring("/msg".length() + 1)).nextToken();
                    final String substring = r.substring(("/msg " + nextToken).length() + 1);
                    this.ad("<" + this.a1.hc.e3 + " * " + nextToken + "> " + substring);
                    this.ag("PRIVMSG " + nextToken + " :" + substring);
                    return;
                }
                catch (Exception ex) {
                    this.ac("*** MSG format failed.");
                    return;
                }
            }
            if (r.startsWith("/pchat ")) {
                try {
                    this.s(new StringTokenizer(r.substring("/pchat".length() + 1)).nextToken());
                    return;
                }
                catch (Exception ex2) {
                    this.ac("*** MSG format failed.");
                    return;
                }
            }
            if (r.startsWith("/clear")) {
                this.a1.bw();
                return;
            }
            if (r.startsWith("/ctcp ")) {
                try {
                    final String nextToken2 = new StringTokenizer(r.substring("/ctcp".length() + 1)).nextToken();
                    this.ag("PRIVMSG " + nextToken2 + " :" + this.a4 + r.substring(("/ctcp " + nextToken2).length() + 1).toUpperCase() + this.a4);
                    return;
                }
                catch (Exception ex3) {
                    this.ac("*** CTCP format failed.");
                    return;
                }
            }
            if (r.startsWith("/showurl")) {
                this.ad("* " + this.a1.hc.e0 + " URL " + r.substring("/showurl".length() + 1));
                this.ag("PRIVMSG #" + this.a1.hc.e_ + " :" + this.a4 + "URL " + r.substring("/showurl".length() + 1) + this.a4);
                this.a1.bx(r.substring("/showurl".length() + 1));
                return;
            }
            if (r.startsWith("/mode ")) {
                this.ag("MODE " + r.substring(6));
                return;
            }
            if (r.startsWith("/quit")) {
                this.ag("QUIT :" + r.substring(5));
                this.a1.w();
                this.z();
                return;
            }
            if (r.startsWith("/kick ")) {
                String string = "";
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(r.substring("/kick".length() + 1));
                    final String string2 = String.valueOf(stringTokenizer.nextToken()) + " " + stringTokenizer.nextToken();
                    while (stringTokenizer.hasMoreTokens()) {
                        string = String.valueOf(string) + " " + stringTokenizer.nextToken();
                    }
                    this.ag("KICK " + string2 + " :" + string);
                    return;
                }
                catch (Exception ex4) {
                    this.ac("*** Kick format failed.");
                    return;
                }
            }
            if (r.startsWith("/topic ")) {
                String string3 = "#" + this.a1.hc.e_;
                String nextToken3 = "";
                String s = "";
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(r.substring("/topic".length() + 1));
                    nextToken3 = stringTokenizer2.nextToken();
                    while (stringTokenizer2.hasMoreTokens()) {
                        s = String.valueOf(s) + stringTokenizer2.nextToken() + " ";
                    }
                }
                catch (Exception ex5) {}
                nextToken3.trim();
                s.trim();
                if (nextToken3.startsWith("#")) {
                    string3 = nextToken3;
                }
                else {
                    s = String.valueOf(nextToken3) + " " + s;
                }
                if (s.equals("")) {
                    this.ag("TOPIC " + string3);
                    return;
                }
                this.ag("TOPIC " + string3 + " :" + s);
            }
            else {
                if (r.startsWith("/me ")) {
                    this.ag("PRIVMSG #" + this.a1.hc.e_ + " :" + this.a4 + "ACTION " + r.substring("/me".length() + 1) + this.a4);
                    this.ab("* " + this.a1.hc.e0 + " " + r.substring("/me".length() + 1));
                    return;
                }
                if (r.startsWith("/")) {
                    this.ag(r.substring(1));
                    return;
                }
                if (this.a1.hc.e_.equals("")) {
                    this.ac("*** Not in any channel yet, please type \"/join #your_channel\" first.");
                    return;
                }
                this.aa(this.a1.hc.e0, r);
                this.u();
                this.ag("PRIVMSG #" + this.a1.hc.e_ + " :" + r);
            }
        }
        catch (StringIndexOutOfBoundsException ex6) {
            this.a1.ar("*** Insufficient arguments ....");
        }
    }
    
    public final void ag(final String s) {
        this.av.println(s);
    }
    
    public final void af(final String s) {
        this.a1.ar(s);
    }
    
    public final void ae(final String s) {
        this.a1.g3.i(s, this.a1.hc.ej);
    }
    
    public final void ad(final String s) {
        this.a1.g3.i(s, this.a1.hc.eh);
    }
    
    public final void ac(final String s) {
        this.a1.g3.i(s, this.a1.hc.eg);
    }
    
    public final void ab(final String s) {
        this.a1.g3.i(s, this.a1.hc.ei);
    }
    
    public final void aa(String string, final String s) {
        if (this.a1.hc.ed != -1) {
            string = String.valueOf(this.a3) + this.a1.hc.ed + "<" + string + ">" + "\u000f";
            this.a1.g3.i(String.valueOf(string) + " " + s);
            return;
        }
        this.a1.g3.i("<" + string + "> " + s);
    }
    
    public final void z() {
        if (this.ax != null) {
            this.ax.az();
        }
        this.stop();
        this.t();
        this.al = false;
    }
    
    public final void y(final String s) {
        String ex;
        if (this.a1.hc.ex == null || this.a1.hc.ex.equals("")) {
            ex = "";
        }
        else {
            ex = this.a1.hc.ex;
        }
        if (this.a1.hc.e_.equals("")) {
            this.ag("JOIN " + s + " " + ex);
            return;
        }
        this.ag("PART #" + this.a1.hc.e_);
        this.a1.bs();
        this.ag("JOIN " + s + " " + ex);
    }
    
    public final void x(final String s) {
        this.a1.hc.e3 = s.replace(' ', '_');
        this.ag("nick " + this.a1.hc.e3);
    }
    
    public final void w() {
        this.stop();
        if (this.ax != null) {
            this.ax.az();
        }
        this.a1.ar(this.a1.hc.ct);
        this.t();
        this.al = false;
    }
    
    public final void v(final String s, final String s2) {
        if (s2.equalsIgnoreCase("FINGER")) {
            this.ag("NOTICE " + s + " :" + this.a4 + "FINGER " + this.a1.hc.ez + " Idle unknown" + this.a4);
            return;
        }
        if (s2.startsWith("ACTION ") || s2.startsWith("action ")) {
            this.ab("* " + s + " " + s2.substring(7));
            return;
        }
        if (s2.startsWith("PING ")) {
            this.ag("NOTICE " + s + " :" + this.a4 + s2 + this.a4);
            return;
        }
        if (s2.startsWith("ECHO") || s2.startsWith("echo")) {
            this.ag("NOTICE " + s + " :" + this.a4 + "ECHO " + s2.substring(4) + this.a4);
            return;
        }
        if (s2.equalsIgnoreCase("CLIENTINFO")) {
            this.ag("NOTICE " + s + " :" + this.a4 + "CLIENTINFO FINGER PING ECHO CLIENTINFO TIME " + "VERSION ( no DCC support yet)" + this.a4);
            return;
        }
        if (s2.equalsIgnoreCase("TIME")) {
            this.ag("NOTICE " + s + " :" + this.a4 + "TIME " + new Date().toString() + this.a4);
            return;
        }
        if (s2.equalsIgnoreCase("VERSION")) {
            this.ag("NOTICE " + s + " :" + this.a4 + "VERSION " + "JPilot IRC Java Client " + "2.5.1" + " " + this.a4);
            return;
        }
        if (s2.startsWith("URL ")) {
            try {
                this.a1.bx(s2.substring(4));
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        if (s2.startsWith("SOUND ")) {
            try {
                this.ad("* " + s + " " + "SOUND " + s2.substring(6));
                this.a1.bj(s2.substring(6));
                return;
            }
            catch (Exception ex2) {
                return;
            }
        }
        this.ad("* " + s + " " + s2);
    }
    
    public final void u() {
        if (this.a1.hj) {
            return;
        }
        this.a1.ar("###### Unregistered copy, evaluation only.");
        this.a1.ar("###### Please ask webmaster to register it.");
    }
    
    public final void t() {
        for (int size = this.an.size(), i = 0; i < size; ++i) {
            ((g)this.an.elementAt(i)).dispose();
        }
        this.an.removeAllElements();
    }
    
    public final boolean s(final String s) {
        return this.s(s, null);
    }
    
    public final boolean s(final String s, final String s2) {
        return this.s(s, s2, null);
    }
    
    public final boolean s(final String s, final String s2, final Color color) {
        new Dimension(400, 250);
        if (!this.a1.hc.dp) {
            return false;
        }
        final int size = this.an.size();
        for (int i = 0; i < size; ++i) {
            final g g = this.an.elementAt(i);
            if (g.getName().equals(s)) {
                if (s2 != null) {
                    g.ar(s2, color);
                }
                return true;
            }
        }
        if (size + 1 >= 10) {
            this.af("*** Max. private chat window reached");
            return false;
        }
        final g g2 = new g(40, 10, s, this.am, this);
        this.an.addElement(g2);
        if (s2 != null) {
            g2.ar(s2, color);
        }
        g2.show();
        return true;
    }
    
    public final String r(final String s) {
        String s2;
        int index;
        String substring;
        String substring2;
        for (s2 = s; (index = s2.indexOf("%C")) >= 0; s2 = String.valueOf(substring) + "\u0003" + substring2) {
            substring = s2.substring(0, index);
            try {
                substring2 = s2.substring(index + 2);
            }
            catch (Exception ex) {
                substring2 = "";
            }
        }
        int index2;
        while ((index2 = s2.indexOf("%B")) >= 0) {
            final String substring3 = s2.substring(0, index2);
            String substring4;
            try {
                substring4 = s2.substring(index2 + 2);
            }
            catch (Exception ex2) {
                substring4 = "";
            }
            s2 = String.valueOf(substring3) + "\u0002" + substring4;
        }
        int index3;
        while ((index3 = s2.indexOf("%R")) >= 0) {
            final String substring5 = s2.substring(0, index3);
            String substring6;
            try {
                substring6 = s2.substring(index3 + 2);
            }
            catch (Exception ex3) {
                substring6 = "";
            }
            s2 = String.valueOf(substring5) + "\u0016" + substring6;
        }
        int index4;
        while ((index4 = s2.indexOf("%U")) >= 0) {
            final String substring7 = s2.substring(0, index4);
            String substring8;
            try {
                substring8 = s2.substring(index4 + 2);
            }
            catch (Exception ex4) {
                substring8 = "";
            }
            s2 = String.valueOf(substring7) + "\u001f" + substring8;
        }
        int index5;
        while ((index5 = s2.indexOf("%O")) >= 0) {
            final String substring9 = s2.substring(0, index5);
            String substring10;
            try {
                substring10 = s2.substring(index5 + 2);
            }
            catch (Exception ex5) {
                substring10 = "";
            }
            s2 = String.valueOf(substring9) + "\u000f" + substring10;
        }
        return s2;
    }
}

import java.awt.Dimension;
import java.awt.Color;
import java.util.Date;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
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
    public String a3;
    public String a2;
    public String a1;
    public Chat a0;
    public boolean a_;
    public boolean az;
    public InputStream ay;
    public OutputStream ax;
    public n aw;
    public BufferedReader av;
    public PrintStream au;
    public String at;
    public String as;
    public StringTokenizer ar;
    public String aq;
    public String ap;
    public String ao;
    public String an;
    public Vector am;
    public boolean al;
    public int ak;
    
    public f() {
        super("IRC Protocal Engine Thread");
        this.a3 = String.valueOf('\u0001');
        this.a2 = "\u0003";
        this.a_ = true;
        this.az = false;
        this.al = false;
        this.am = new Vector();
    }
    
    public final void as(final Chat a0) {
        this.a0 = a0;
    }
    
    private final boolean ar() {
        try {
            this.aw = new n(this.a0.bp(), this.a0.hs.fc, this.a0.hs.fg, this.a0.hs.e4);
            this.ag(this.a0.hs.c3);
            this.a0.b0(true);
            this.ay = this.aw.bf();
            this.ax = this.aw.be();
            this.av = new BufferedReader(new InputStreamReader(this.ay));
            this.au = new PrintStream(this.ax);
        }
        catch (IOException ex) {
            this.a0.au("Unable to connect to server.");
            this.a0.y(true);
            this.ab();
            return false;
        }
        return true;
    }
    
    public final void run() {
        this.ar();
        if (this.av == null || this.au == null) {
            return;
        }
        this.al();
    Label_0024_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.a1 = this.av.readLine();
                        if (this.a1 == null) {
                            break;
                        }
                        if (this.ao(this.a1)) {
                            continue Label_0024_Outer;
                        }
                        if (this.a1.startsWith("PING :")) {
                            this.am(this.a1.substring(5));
                        }
                        else if (this.a1.startsWith("ERROR :")) {
                            this.an(this.a1.substring(7));
                        }
                        else {
                            if (this.a1.startsWith("NOTICE ") && this.a0.hs.dt) {
                                continue Label_0024_Outer;
                            }
                            this.ag("*** " + this.a1);
                        }
                    }
                    this.a0.y(this.a_);
                    this.ab();
                    return;
                }
                catch (IOException ex) {
                    this.a0.au(ex.toString());
                    this.a0.y();
                    this.ab();
                    continue Label_0024_Outer;
                }
                catch (NoSuchElementException ex2) {
                    continue Label_0024_Outer;
                }
                continue;
            }
        }
    }
    
    public final void aq(final String s, final String s2, final String s3, final String s4, final String s5) {
        int intValue;
        try {
            intValue = new Integer(s2);
        }
        catch (NumberFormatException ex) {
            intValue = -1;
        }
        if (intValue >= 250 && intValue <= 269 && this.a0.hs.dt) {
            return;
        }
        if (this.s(String.valueOf(intValue))) {
            return;
        }
        switch (intValue) {
            case 1: {
                if (!this.a0.hs.dt) {
                    this.ag("*** " + s4 + " " + s5);
                }
                this.al = true;
                return;
            }
            case 2: {
                final p hs = this.a0.hs;
                this.a0.hs.ff = s3;
                hs.fb = s3;
                this.a0.g_.setText(this.a0.hs.fb);
                this.a_ = false;
                this.aa(this.a0.hs.fe);
                return;
            }
            case 3: {
                if (this.a0.hs.dt) {
                    return;
                }
            }
            case 4: {
                if (this.a0.hs.dt) {
                    return;
                }
            }
            case 252: {
                this.ag("*** " + s4 + " " + s5);
                return;
            }
            case 254: {
                this.ag("*** " + s4 + " " + s5);
                return;
            }
            case 311: {
                this.ag("*** [User of " + s4 + " :" + s5 + "]");
                return;
            }
            case 312: {
                this.ag("*** [Server of " + s4 + " :" + s5 + "]");
                return;
            }
            case 317: {
                this.ag("*** [Idle time of " + s4 + " :" + s5 + "]");
                return;
            }
            case 318: {
                this.ag("*** " + s5);
                return;
            }
            case 319: {
                this.ag("*** [Channel of " + s4 + ":" + s5 + "]");
                return;
            }
            case 332: {
                this.ag("*** Topic of " + s4 + " :" + s5);
                this.a0.hl.c(s5);
                return;
            }
            case 352: {
                this.ag("[" + s4 + " :" + s5 + "]");
                return;
            }
            case 353: {
                if (this.a0.hu) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s5);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.a0.bw(stringTokenizer.nextToken());
                    }
                }
                return;
            }
            case 366: {
                this.a0.hu = false;
                return;
            }
            case 433: {
                this.ag("*** NickName Already in use: " + this.a0.hs.ff);
                if (!this.al && this.ak <= 9) {
                    if (this.ak > 0) {
                        this.a0.hs.ff = this.a0.hs.ff.substring(0, this.a0.hs.ff.length() - 1);
                    }
                    this.a0.hs.ff = String.valueOf(this.a0.hs.ff) + ++this.ak;
                    this.a0.g_.setText(this.a0.hs.ff);
                    this.ag("*** Try to use " + this.a0.hs.ff + " now ....");
                    this.ai("NICK " + this.a0.hs.ff);
                    return;
                }
                this.ae("*** Type /nick new_name to enter");
                return;
            }
            case 372: {
                if (this.a0.hs.du) {
                    return;
                }
            }
            case 375: {
                if (this.a0.hs.du) {
                    return;
                }
            }
            case 376: {
                if (this.a0.hs.du) {
                    return;
                }
                break;
            }
            case 333: {
                return;
            }
        }
        this.ag("*** " + s4 + " " + s5);
    }
    
    public final void ap(final String s, final String s2, final String s3, String trim, String s4) {
        String substring;
        try {
            substring = s.substring(0, s.indexOf("!"));
        }
        catch (StringIndexOutOfBoundsException ex2) {
            substring = s;
        }
        if (this.s(s2)) {
            return;
        }
        if (s2.equals("QUIT")) {
            if (!this.a0.hs.ds) {
                this.af("*** " + substring + " Signoff (" + s4 + ")");
            }
            this.a0.bv(substring);
            return;
        }
        if (s2.equals("NICK")) {
            if (substring.equals(this.a0.hs.fb)) {
                this.a0.hs.fb = s4;
                this.a0.hs.ff = this.a0.hs.fb;
                this.a0.g_.setText(this.a0.hs.ff);
            }
            this.af("*** " + substring + " is now Known as " + s4);
            this.a0.bt(substring, s4);
            return;
        }
        if (!s2.equals("PART")) {
            if (s2.equals("JOIN")) {
                if (!this.a0.hs.ds) {
                    this.af("*** " + substring + " (" + s.substring(s.indexOf("!") + 1) + ") has joined the channel " + s4);
                }
                if (this.a0.hs.dv) {
                    this.a0.bo("join");
                }
                if (substring.equals(this.a0.hs.fb)) {
                    this.a0.hs.fa = s4;
                    this.a0.hs.fe = this.a0.hs.fa;
                    this.a0.gz.setText(this.a0.hs.fe);
                    this.a0.hu = true;
                    this.a0.by();
                }
                else {
                    this.a0.bw(substring);
                }
                if (!this.az) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.a0.hs.e5, ";", false);
                    while (stringTokenizer.hasMoreTokens()) {
                        this.a0.av(stringTokenizer.nextToken(), false, true);
                    }
                    this.az = true;
                }
            }
            else if (s2.equals("PRIVMSG")) {
                if (this.a0.bm(substring)) {
                    return;
                }
                if (s3.equalsIgnoreCase(this.a0.hs.fa)) {
                    if (s4.startsWith(this.a3)) {
                        s4 = s4.trim();
                        this.x(substring, s4);
                        return;
                    }
                    if (this.a0.bu(substring)) {
                        this.ac(substring, s4);
                        return;
                    }
                    this.ac(String.valueOf(substring) + "/" + s3, s4);
                }
                else if (s3.equalsIgnoreCase(this.a0.hs.fb)) {
                    if (s4.startsWith(this.a3)) {
                        s4 = s4.trim();
                        this.x(substring, s4);
                        return;
                    }
                    if (!this.u(substring, s4)) {
                        this.ac("*" + substring + "*", s4);
                    }
                }
            }
            else if (s2.equalsIgnoreCase("MODE")) {
                if (s3.startsWith("#") || s3.startsWith("!") || s3.startsWith("&")) {
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
                                String s5;
                                if (substring2.equals("o")) {
                                    s5 = "@";
                                }
                                else if (substring2.equals("v")) {
                                    s5 = "+";
                                }
                                else if (substring2.equals("h")) {
                                    s5 = "%";
                                }
                                else {
                                    s5 = "";
                                }
                                if (!s5.equals("")) {
                                    this.a0.bs(stringTokenizer2.nextToken(), s5, b);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (!this.a0.hs.dr) {
                        this.af("*** Mode change \"" + trim + "\" on " + "channel " + s3 + " by " + substring);
                    }
                }
                else if (!this.a0.hs.dr) {
                    this.af("*** Mode change \"" + s4 + "\" for user " + s3 + " by " + substring);
                }
            }
            else if (s2.equalsIgnoreCase("NOTICE")) {
                if (s4.startsWith(this.a3)) {
                    this.af("*** CTCP reply from " + substring + " :" + s4.substring(1));
                    return;
                }
                this.af("--" + substring + "-- " + s4);
            }
            else if (s2.equalsIgnoreCase("KICK")) {
                trim = trim.trim();
                this.af("*** " + trim + " has been kicked off " + "channel " + s3 + " by " + substring + " (" + s4 + ")");
                if (trim.equalsIgnoreCase(this.a0.hs.fb)) {
                    this.a0.gz.setText(this.a0.hs.fa);
                    this.a0.hs.fa = "";
                    this.a0.bz();
                    return;
                }
                this.a0.bv(trim);
            }
            else {
                if (s2.equalsIgnoreCase("TOPIC")) {
                    this.af("*** " + substring + " has changed the topic to " + "\"" + s4 + " \u000f\"  on channel " + s3);
                    this.a0.hl.c(s4);
                    return;
                }
                this.af("<#" + substring + "#>" + s2 + " " + trim + " " + s4);
            }
            return;
        }
        if (substring.equals(this.a0.hs.fb)) {
            if (!this.a0.hs.ds) {
                this.af("*** You  have left the channel " + s3);
            }
            this.a0.hs.fa = "";
            this.a0.bz();
            return;
        }
        if (!this.a0.hs.ds) {
            this.af("*** " + substring + " has left the channel " + s3);
        }
        this.a0.bv(substring);
    }
    
    public final boolean ao(final String s) {
        if (s.startsWith(":")) {
            int index = s.indexOf(" :", 1);
            if (index != -1) {
                ++index;
                this.at = s.substring(1, index);
            }
            else {
                this.at = s.substring(1);
            }
            this.ar = new StringTokenizer(this.at);
            this.aq = this.ar.nextToken();
            this.ap = this.ar.nextToken();
            if (this.ar.hasMoreTokens()) {
                this.ao = this.ar.nextToken();
            }
            else {
                this.ao = "";
            }
            this.an = "";
            while (this.ar.hasMoreTokens()) {
                this.an = String.valueOf(this.an) + this.ar.nextToken() + " ";
            }
            if (index != -1) {
                this.as = s.substring(index + 1);
            }
            else {
                this.as = "";
            }
            try {
                Integer.parseInt(this.ap);
                this.aq(this.aq, this.ap, this.ao, this.an, this.as);
                return true;
            }
            catch (NumberFormatException ex) {
                this.ap(this.aq, this.ap, this.ao, this.an, this.as);
                return true;
            }
        }
        return false;
    }
    
    public final void an(final String s) {
        this.ae(s);
    }
    
    public final void am(final String s) {
        this.ai("PONG " + s);
    }
    
    public final void al() {
        if (this.a0.hs.e8 != null && !this.a0.hs.e8.equals("")) {
            this.ai("PASS " + this.a0.hs.e8);
        }
        this.ai("NICK " + this.a0.hs.ff + " " + this.a0.hs.e6);
        if (!this.a0.hs.bl.equals("")) {
            this.ai("USER " + this.a0.hs.bl + " " + this.a0.hs.fl + " " + this.a0.hs.fc + " :" + this.a0.hs.e9);
        }
        else {
            this.ai("USER " + this.a0.hs.ff + " " + this.a0.hs.fl + " " + this.a0.hs.fc + " :" + this.a0.hs.e9);
        }
        if (!this.a0.hs.e6.equals("") && !this.a0.hs.cs.equals("")) {
            this.ai(String.valueOf(this.a0.hs.cs) + " " + this.a0.hs.e6);
        }
    }
    
    public final void ak() {
        if (this.al) {
            this.ai("QUIT :" + this.a0.hs.c1);
        }
        this.al = false;
    }
    
    public final void aj(String r) {
        r = this.r(r);
        try {
            if (r.startsWith("/debug") || r.startsWith("/quote")) {
                this.ai(r.substring(6));
                return;
            }
            if (r.startsWith("/nick ")) {
                this.z(r.substring("/nick".length() + 1));
                return;
            }
            if (r.startsWith("/s ")) {
                this.af("* " + this.a0.hs.ff + " " + "SOUND " + r.substring(3));
                this.a0.bo(r.substring(3));
                this.ai("PRIVMSG " + this.a0.hs.fa + " :" + this.a3 + "SOUND " + r.substring(3) + this.a3);
                return;
            }
            if (r.startsWith("/join ")) {
                this.aa(r.substring("/join".length() + 1));
                return;
            }
            if (r.startsWith("/msg ")) {
                try {
                    final String nextToken = new StringTokenizer(r.substring("/msg".length() + 1)).nextToken();
                    final String substring = r.substring(("/msg " + nextToken).length() + 1);
                    this.af("<" + this.a0.hs.ff + " * " + nextToken + "> " + substring);
                    this.ai("PRIVMSG " + nextToken + " :" + substring);
                    return;
                }
                catch (Exception ex2) {
                    this.ae("*** MSG format failed.");
                    return;
                }
            }
            if (r.startsWith("/ignore ")) {
                final String substring2 = r.substring("/ignore ".length());
                if (substring2 != null && !substring2.equals("")) {
                    this.a0.bn(substring2);
                }
                return;
            }
            if (r.startsWith("/pchat ")) {
                try {
                    this.u(new StringTokenizer(r.substring("/pchat".length() + 1)).nextToken());
                    return;
                }
                catch (Exception ex3) {
                    this.ae("*** MSG format failed.");
                    return;
                }
            }
            if (r.startsWith("/clear")) {
                this.a0.b1();
                return;
            }
            if (r.startsWith("/ctcp ")) {
                try {
                    final String nextToken2 = new StringTokenizer(r.substring("/ctcp".length() + 1)).nextToken();
                    this.ai("PRIVMSG " + nextToken2 + " :" + this.a3 + r.substring(("/ctcp " + nextToken2).length() + 1).toUpperCase() + this.a3);
                    return;
                }
                catch (Exception ex4) {
                    this.ae("*** CTCP format failed.");
                    return;
                }
            }
            if (r.startsWith("/showurl")) {
                this.af("* " + this.a0.hs.fb + " URL " + r.substring("/showurl".length() + 1));
                this.ai("PRIVMSG " + this.a0.hs.fa + " :" + this.a3 + "URL " + r.substring("/showurl".length() + 1) + this.a3);
                this.a0.b2(r.substring("/showurl".length() + 1));
                return;
            }
            if (r.startsWith("/mode ")) {
                this.ai("MODE " + r.substring(6));
                return;
            }
            if (r.startsWith("/quit")) {
                this.ai("QUIT :" + r.substring(5));
                this.a0.y();
                this.ab();
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
                    this.ai("KICK " + string2 + " :" + string);
                    return;
                }
                catch (Exception ex5) {
                    this.ae("*** Kick format failed.");
                    return;
                }
            }
            if (r.startsWith("/topic ")) {
                String fa = this.a0.hs.fa;
                String nextToken3 = "";
                String s = "";
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(r.substring("/topic".length() + 1));
                    nextToken3 = stringTokenizer2.nextToken();
                    while (stringTokenizer2.hasMoreTokens()) {
                        s = String.valueOf(s) + stringTokenizer2.nextToken() + " ";
                    }
                }
                catch (Exception ex6) {}
                nextToken3.trim();
                s.trim();
                if (nextToken3.startsWith("#") || nextToken3.startsWith("&") || nextToken3.startsWith("!")) {
                    fa = nextToken3;
                }
                else {
                    s = String.valueOf(nextToken3) + " " + s;
                }
                if (s.equals("")) {
                    this.ai("TOPIC " + fa);
                    return;
                }
                this.ai("TOPIC " + fa + " :" + s);
            }
            else {
                if (r.startsWith("/me ")) {
                    this.ai("PRIVMSG " + this.a0.hs.fa + " :" + this.a3 + "ACTION " + r.substring("/me".length() + 1) + this.a3);
                    this.ad("* " + this.a0.hs.fb + " " + r.substring("/me".length() + 1));
                    return;
                }
                if (r.startsWith("/away ")) {
                    this.ai("AWAY :" + r.substring(6));
                    return;
                }
                if (r.startsWith("/notice ")) {
                    final int index = r.indexOf(" ", 8);
                    if (index >= 0) {
                        this.ai("NOTICE " + r.substring(8, index) + " :" + r.substring(index + 1));
                    }
                }
                else {
                    if (r.startsWith("/")) {
                        this.ai(r.substring(1));
                        return;
                    }
                    if (this.a0.hs.fa.equals("")) {
                        this.ae("*** Not in any channel yet, please type \"/join #your_channel\" first.");
                        return;
                    }
                    this.ac(this.a0.hs.fb, r);
                    this.w();
                    this.ai("PRIVMSG " + this.a0.hs.fa + " :" + r);
                }
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            this.a0.au("*** Insufficient arguments ....");
        }
    }
    
    public final void ai(final String s) {
        this.au.println(s);
    }
    
    public final void ah(final String s) {
        this.a0.au(s);
    }
    
    public final void ag(final String s) {
        this.a0.hj.i(s, this.a0.hs.et);
    }
    
    public final void af(final String s) {
        this.a0.hj.i(s, this.a0.hs.er);
    }
    
    public final void ae(final String s) {
        this.a0.hj.i(s, this.a0.hs.eq);
    }
    
    public final void ad(final String s) {
        this.a0.hj.i(s, this.a0.hs.es);
    }
    
    public final void ac(String string, final String s) {
        if (this.a0.hs.en != -1) {
            string = String.valueOf(this.a2) + this.a0.hs.en + "" + this.a0.hs.cu + "" + string + "" + this.a0.hs.ct + "" + "\u000f";
            this.a0.hj.i(String.valueOf(string) + " " + s);
            return;
        }
        this.a0.hj.i(String.valueOf(this.a0.hs.cu) + "" + string + this.a0.hs.ct + " " + s);
    }
    
    public final void ab() {
        if (this.aw != null) {
            this.aw.a1();
        }
        this.stop();
        this.v();
        this.al = false;
    }
    
    public final void aa(final String s) {
        String e7;
        if (this.a0.hs.e7 == null || this.a0.hs.e7.equals("")) {
            e7 = "";
        }
        else {
            e7 = this.a0.hs.e7;
        }
        if (this.a0.hs.fa.equals("")) {
            this.ai("JOIN " + s + " " + e7);
            return;
        }
        this.ai("PART " + this.a0.hs.fa);
        this.a0.by();
        this.ai("JOIN " + s + " " + e7);
    }
    
    public final void z(final String s) {
        this.a0.hs.ff = s.replace(' ', '_');
        this.ai("nick " + this.a0.hs.ff);
    }
    
    public final void y() {
        this.stop();
        if (this.aw != null) {
            this.aw.a1();
        }
        this.a0.au(this.a0.hs.c2);
        this.v();
        this.al = false;
    }
    
    public final void x(final String s, final String s2) {
        if (s2.equalsIgnoreCase("FINGER")) {
            this.ai("NOTICE " + s + " :" + this.a3 + "FINGER " + this.a0.hs.e9 + " Idle unknown" + this.a3);
            return;
        }
        if (s2.startsWith("ACTION ") || s2.startsWith("action ")) {
            final String substring = s2.substring(7);
            if (this.t(s)) {
                this.u(s, "*** " + substring, this.a0.hs.es);
                return;
            }
            this.ad("* " + s + " " + substring);
        }
        else {
            if (s2.startsWith("PING ")) {
                this.ai("NOTICE " + s + " :" + this.a3 + s2 + this.a3);
                return;
            }
            if (s2.startsWith("ECHO") || s2.startsWith("echo")) {
                this.ai("NOTICE " + s + " :" + this.a3 + "ECHO " + s2.substring(4) + this.a3);
                return;
            }
            if (s2.equalsIgnoreCase("CLIENTINFO")) {
                this.ai("NOTICE " + s + " :" + this.a3 + "CLIENTINFO FINGER PING ECHO CLIENTINFO TIME " + "VERSION ( no DCC support yet)" + this.a3);
                return;
            }
            if (s2.equalsIgnoreCase("TIME")) {
                this.ai("NOTICE " + s + " :" + this.a3 + "TIME " + new Date().toString() + this.a3);
                return;
            }
            if (s2.equalsIgnoreCase("VERSION")) {
                this.ai("NOTICE " + s + " :" + this.a3 + "VERSION " + "JPilot IRC Java Client " + "2.6.0" + " " + this.a3);
                return;
            }
            if (s2.startsWith("URL ")) {
                try {
                    this.a0.b2(s2.substring(4));
                    return;
                }
                catch (Exception ex) {
                    return;
                }
            }
            if (s2.startsWith("SOUND ")) {
                try {
                    this.af("* " + s + " " + this.a0.hs.cr);
                    this.a0.bo(s2.substring(6));
                    return;
                }
                catch (Exception ex2) {
                    return;
                }
            }
            this.af("* " + s + " " + s2);
        }
    }
    
    public final void w() {
        if (this.a0.h_) {
            return;
        }
        this.a0.au("###### Unregistered copy, evaluation only.");
        this.a0.au("###### Please ask webmaster to register it.");
    }
    
    public final void v() {
        for (int size = this.am.size(), i = 0; i < size; ++i) {
            ((g)this.am.elementAt(i)).dispose();
        }
        this.am.removeAllElements();
    }
    
    public final boolean u(final String s) {
        return this.u(s, null);
    }
    
    public final boolean u(final String s, final String s2) {
        return this.u(s, s2, null);
    }
    
    public final boolean u(final String s, final String s2, final Color color) {
        final Dimension dimension = new Dimension(this.a0.hs.fn, this.a0.hs.fm);
        if (!this.a0.hs.dz) {
            return false;
        }
        final int size = this.am.size();
        for (int i = 0; i < size; ++i) {
            final g g = this.am.elementAt(i);
            if (g.getName().equals(s)) {
                if (s2 != null) {
                    g.au(s2, color);
                }
                return true;
            }
        }
        if (size + 1 >= 10) {
            this.ah("*** Max. private chat window reached");
            return false;
        }
        final g g2 = new g(40, 10, s, dimension, this);
        this.am.addElement(g2);
        if (s2 != null) {
            g2.au(s2, color);
        }
        g2.show();
        return true;
    }
    
    public final boolean t(final String s) {
        for (int size = this.am.size(), i = 0; i < size; ++i) {
            if (((g)this.am.elementAt(i)).getName().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean s(final String s) {
        return this.a0.hs.ft.get(s) != null;
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

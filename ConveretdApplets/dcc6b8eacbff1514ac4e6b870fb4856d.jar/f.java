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
    public String a0;
    public String a_;
    public Chat az;
    public boolean ay;
    public InputStream ax;
    public OutputStream aw;
    public Connection av;
    public BufferedReader au;
    public PrintStream at;
    public String as;
    public String ar;
    public StringTokenizer aq;
    public String ap;
    public String ao;
    public String an;
    public String am;
    public Vector al;
    public Dimension ak;
    public boolean aj;
    public int ai;
    
    public f() {
        super("IRC Protocal Engine Thread");
        this.a0 = String.valueOf('\u0001');
        this.ay = true;
        this.ak = new Dimension(400, 250);
        this.aj = false;
        this.al = new Vector();
    }
    
    public final void as(final Chat az) {
        this.az = az;
    }
    
    private final boolean ar() {
        try {
            this.av = new Connection(this.az.bk(), this.az.gl.eh, this.az.gl.ek);
            this.ag("Connected, please wait....");
            this.az.bv(true);
            this.ax = this.av.bc();
            this.aw = this.av.bb();
            this.au = new BufferedReader(new InputStreamReader(this.ax));
            this.at = new PrintStream(this.aw);
        }
        catch (IOException ex) {
            this.az.at("Unable to connect to server.");
            this.az.y(true);
            this.ab();
            return false;
        }
        return true;
    }
    
    public final void run() {
        this.ar();
        if (this.au == null || this.at == null) {
            return;
        }
        this.al();
    Label_0024_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.a_ = this.au.readLine();
                        if (this.a_ == null) {
                            break;
                        }
                        if (this.ao(this.a_)) {
                            continue Label_0024_Outer;
                        }
                        if (this.a_.startsWith("PING :")) {
                            this.am(this.a_.substring(5));
                        }
                        else if (this.a_.startsWith("ERROR :")) {
                            this.an(this.a_.substring(7));
                        }
                        else {
                            this.ag("*** " + this.a_);
                        }
                    }
                    this.az.y(this.ay);
                    this.ab();
                    return;
                }
                catch (IOException ex) {
                    this.az.at(ex.toString());
                    this.az.y();
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
        if (intValue >= 251 && intValue <= 269 && this.r()) {
            return;
        }
        switch (intValue) {
            case 1: {
                if (!this.r()) {
                    this.ag("*** " + s4 + " " + s5);
                }
                this.aj = true;
                return;
            }
            case 2: {
                final n gl = this.az.gl;
                this.az.gl.ej = s3;
                gl.eg = s3;
                this.az.ft.setText(this.az.gl.eg);
                this.ay = false;
                this.aa("#" + this.az.gl.ei);
                final StringTokenizer stringTokenizer = new StringTokenizer(this.az.gl.eb, ";", false);
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.startsWith("/")) {
                        this.aj(nextToken);
                    }
                    else {
                        this.ag("<" + this.az.gl.eg + "> " + nextToken);
                        this.ai("PRIVMSG #" + this.az.gl.ei + " :" + nextToken);
                    }
                }
                return;
            }
            case 3: {
                if (this.r()) {
                    return;
                }
            }
            case 4: {
                if (this.r()) {
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
                this.az.ge.b(s5);
                return;
            }
            case 352: {
                this.ag("[" + s4 + " :" + s5 + "]");
                return;
            }
            case 353: {
                if (this.az.gn) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s5);
                    while (stringTokenizer2.hasMoreTokens()) {
                        this.az.br(stringTokenizer2.nextToken());
                    }
                }
                return;
            }
            case 366: {
                this.az.gn = false;
                return;
            }
            case 433: {
                this.ag("*** NickName Already in use: " + this.az.gl.ej);
                if (!this.aj && this.ai <= 9) {
                    if (this.ai > 0) {
                        this.az.gl.ej = this.az.gl.ej.substring(0, this.az.gl.ej.length() - 1);
                    }
                    this.az.gl.ej = String.valueOf(this.az.gl.ej) + ++this.ai;
                    this.az.ft.setText(this.az.gl.ej);
                    this.ag("*** Try to use " + this.az.gl.ej + " now ....");
                    this.ai("NICK " + this.az.gl.ej);
                    return;
                }
                this.ae("*** Type /nick new_name to enter");
                return;
            }
            case 372: {
                if (this.s()) {
                    return;
                }
            }
            case 375: {
                if (this.s()) {
                    return;
                }
            }
            case 376: {
                if (this.s()) {
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
    
    public final void ap(final String s, final String s2, final String s3, String trim, String eg) {
        String substring;
        try {
            substring = s.substring(0, s.indexOf("!"));
        }
        catch (StringIndexOutOfBoundsException ex2) {
            substring = s;
        }
        if (s2.equals("QUIT")) {
            if (!this.t()) {
                this.af("*** " + substring + " Signoff (" + eg + ")");
            }
            this.az.bq(substring);
            return;
        }
        if (s2.equals("NICK")) {
            if (substring.equals(this.az.gl.eg)) {
                this.az.gl.eg = eg;
                this.az.gl.ej = this.az.gl.eg;
                this.az.ft.setText(this.az.gl.ej);
            }
            this.af("*** " + substring + " is now Known as " + eg);
            this.az.bo(substring, eg);
            return;
        }
        if (s2.equals("PART")) {
            if (substring.equals(this.az.gl.eg)) {
                if (!this.t()) {
                    this.af("*** You  have left the channel " + s3);
                }
                this.az.gl.ef = "";
                this.az.bt();
                return;
            }
            if (!this.t()) {
                this.af("*** " + substring + " has left the channel " + s3);
            }
            this.az.bq(substring);
        }
        else {
            if (!s2.equals("JOIN")) {
                if (s2.equals("PRIVMSG")) {
                    if (s3.equalsIgnoreCase("#" + this.az.gl.ef)) {
                        if (eg.startsWith(this.a0)) {
                            eg = eg.trim();
                            this.x(substring, eg);
                            return;
                        }
                        if (this.az.bp(substring)) {
                            this.ac("<" + substring + "> " + eg);
                            return;
                        }
                        this.ac("<" + substring + "/" + s3 + " >" + eg);
                    }
                    else if (s3.equalsIgnoreCase(this.az.gl.eg)) {
                        if (eg.startsWith(this.a0)) {
                            eg = eg.trim();
                            this.x(substring, eg);
                            return;
                        }
                        if (!this.u(substring, eg)) {
                            this.ac("<*" + substring + "*> " + eg);
                        }
                    }
                }
                else if (s2.equalsIgnoreCase("MODE")) {
                    if (s3.startsWith("#")) {
                        trim.trim();
                        final StringTokenizer stringTokenizer = new StringTokenizer(trim);
                        final String nextToken = stringTokenizer.nextToken();
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
                                        this.az.bn(stringTokenizer.nextToken(), s4, b);
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        this.af("*** Mode change \"" + trim + "\" on " + "channel " + s3 + " by " + substring);
                        return;
                    }
                    this.af("*** Mode change \"" + eg + "\" for user " + s3 + " by " + substring);
                }
                else if (s2.equalsIgnoreCase("NOTICE")) {
                    if (eg.startsWith(this.a0)) {
                        this.af("*** CTCP reply from " + substring + " :" + eg.substring(1));
                        return;
                    }
                    this.af("--" + substring + "-- " + eg);
                }
                else if (s2.equalsIgnoreCase("KICK")) {
                    trim = trim.trim();
                    this.af("*** " + trim + " has been kicked off " + "channel " + s3 + " by " + substring + " (" + eg + ")");
                    if (trim.equalsIgnoreCase(this.az.gl.eg)) {
                        this.az.fs.setText(this.az.gl.ef);
                        this.az.gl.ef = "";
                        this.az.bt();
                        return;
                    }
                    this.az.bq(trim);
                }
                else {
                    if (s2.equalsIgnoreCase("TOPIC")) {
                        this.af("*** " + substring + " has changed the topic to " + "\"" + eg + "\u000f\"  on channel " + s3);
                        this.az.ge.b(eg);
                        return;
                    }
                    this.af("<#" + substring + "#>" + s2 + " " + trim + " " + eg);
                }
                return;
            }
            if (!this.t()) {
                this.af("*** " + substring + " (" + s.substring(s.indexOf("!") + 1) + ") has joined the channel " + eg);
            }
            if (substring.equals(this.az.gl.eg)) {
                this.az.gl.ef = eg.substring(1);
                this.az.gl.ei = this.az.gl.ef;
                this.az.fs.setText(this.az.gl.ei);
                this.az.gn = true;
                this.az.bs();
                return;
            }
            this.az.br(substring);
        }
    }
    
    public final boolean ao(final String s) {
        if (s.startsWith(":")) {
            final int index = s.indexOf(":", 1);
            if (index != -1) {
                this.as = s.substring(1, index);
            }
            else {
                this.as = s.substring(1);
            }
            this.aq = new StringTokenizer(this.as);
            this.ap = this.aq.nextToken();
            this.ao = this.aq.nextToken();
            if (this.aq.hasMoreTokens()) {
                this.an = this.aq.nextToken();
            }
            else {
                this.an = "";
            }
            this.am = "";
            while (this.aq.hasMoreTokens()) {
                this.am = String.valueOf(this.am) + this.aq.nextToken() + " ";
            }
            if (index != -1) {
                this.ar = s.substring(index + 1);
            }
            else {
                this.ar = "";
            }
            try {
                Integer.parseInt(this.ao);
                this.aq(this.ap, this.ao, this.an, this.am, this.ar);
                return true;
            }
            catch (NumberFormatException ex) {
                this.ap(this.ap, this.ao, this.an, this.am, this.ar);
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
        if (this.az.gl.ed != null && !this.az.gl.ed.equals("")) {
            this.ai("PASS " + this.az.gl.ed);
        }
        this.ai("NICK " + this.az.gl.ej);
        if (!this.az.gl.bi.equals("")) {
            this.ai("USER " + this.az.gl.bi + " " + this.az.gl.ep + " " + this.az.gl.eh + " :" + this.az.gl.ee);
            return;
        }
        this.ai("USER " + this.az.gl.ej + " " + this.az.gl.ep + " " + this.az.gl.eh + " :" + this.az.gl.ee);
    }
    
    public final void ak() {
        this.ai("QUIT Leaving");
        this.aj = false;
    }
    
    public final void aj(String q) {
        q = this.q(q);
        try {
            if (q.startsWith("/debug") || q.startsWith("/quote")) {
                this.ai(q.substring(6));
                return;
            }
            if (q.startsWith("/nick ")) {
                this.z(q.substring("/nick".length() + 1));
                return;
            }
            if (q.startsWith("/s ")) {
                this.af("* " + this.az.gl.ej + " " + "SOUND " + q.substring(3));
                this.az.bj(q.substring(3));
                this.ai("PRIVMSG #" + this.az.gl.ef + " :" + this.a0 + "SOUND " + q.substring(3) + this.a0);
                return;
            }
            if (q.startsWith("/join")) {
                this.aa(q.substring("/join".length() + 1));
                return;
            }
            if (q.startsWith("/msg")) {
                try {
                    final String nextToken = new StringTokenizer(q.substring("/msg".length() + 1)).nextToken();
                    final String substring = q.substring(("/msg " + nextToken).length() + 1);
                    this.af("<" + this.az.gl.ej + " * " + nextToken + "> " + substring);
                    this.ai("PRIVMSG " + nextToken + " :" + substring);
                    return;
                }
                catch (Exception ex) {
                    this.ae("*** MSG format failed.");
                    return;
                }
            }
            if (q.startsWith("/pchat")) {
                try {
                    this.u(new StringTokenizer(q.substring("/pchat".length() + 1)).nextToken());
                    return;
                }
                catch (Exception ex2) {
                    this.ae("*** MSG format failed.");
                    return;
                }
            }
            if (q.startsWith("/clear")) {
                this.az.bw();
                return;
            }
            if (q.startsWith("/ctcp")) {
                try {
                    final String nextToken2 = new StringTokenizer(q.substring("/ctcp".length() + 1)).nextToken();
                    this.ai("PRIVMSG " + nextToken2 + " :" + this.a0 + q.substring(("/ctcp " + nextToken2).length() + 1).toUpperCase() + this.a0);
                    return;
                }
                catch (Exception ex3) {
                    this.ae("*** CTCP format failed.");
                    return;
                }
            }
            if (q.startsWith("/showurl")) {
                this.af("* " + this.az.gl.eg + " URL " + q.substring("/showurl".length() + 1));
                this.ai("PRIVMSG #" + this.az.gl.ef + " :" + this.a0 + "URL " + q.substring("/showurl".length() + 1) + this.a0);
                this.az.bx(q.substring("/showurl".length() + 1));
                return;
            }
            if (q.startsWith("/mode")) {
                this.ai("MODE " + q.substring(6));
                return;
            }
            if (q.startsWith("/quit")) {
                this.ai("QUIT :" + q.substring(5));
                this.az.y();
                this.ab();
                return;
            }
            if (q.startsWith("/kick")) {
                String string = "";
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(q.substring("/kick".length() + 1));
                    final String string2 = String.valueOf(stringTokenizer.nextToken()) + " " + stringTokenizer.nextToken();
                    while (stringTokenizer.hasMoreTokens()) {
                        string = String.valueOf(string) + " " + stringTokenizer.nextToken();
                    }
                    this.ai("KICK " + string2 + " :" + string);
                    return;
                }
                catch (Exception ex4) {
                    this.ae("*** Kick format failed.");
                    return;
                }
            }
            if (q.startsWith("/topic")) {
                String string3 = "#" + this.az.gl.ef;
                String nextToken3 = "";
                String s = "";
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(q.substring("/topic".length() + 1));
                    nextToken3 = stringTokenizer2.nextToken();
                    while (stringTokenizer2.hasMoreTokens()) {
                        s = String.valueOf(s) + " " + stringTokenizer2.nextToken();
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
                    this.ai("TOPIC " + string3);
                    return;
                }
                this.ai("TOPIC " + string3 + " :" + s);
            }
            else {
                if (q.startsWith("/me")) {
                    this.ai("PRIVMSG #" + this.az.gl.ef + " :" + this.a0 + "ACTION " + q.substring("/me".length() + 1) + this.a0);
                    this.ad("* " + this.az.gl.eg + " " + q.substring("/me".length() + 1));
                    return;
                }
                if (q.startsWith("/")) {
                    this.ai(q.substring(1));
                    return;
                }
                if (this.az.gl.ef.equals("")) {
                    this.ae("*** Not in any channel yet, please type \"/join #your_channel\" first.");
                    return;
                }
                this.ac("<" + this.az.gl.eg + "> " + q);
                this.w();
                this.ai("PRIVMSG #" + this.az.gl.ef + " :" + q);
            }
        }
        catch (StringIndexOutOfBoundsException ex6) {
            this.az.at("*** Insufficient arguments ....");
        }
    }
    
    public final void ai(final String s) {
        this.at.println(s);
    }
    
    public final void ah(final String s) {
        this.az.at(s);
    }
    
    public final void ag(final String s) {
        this.az.gc.h(s, this.az.gl.d_);
    }
    
    public final void af(final String s) {
        this.az.gc.h(s, this.az.gl.dw);
    }
    
    public final void ae(final String s) {
        this.az.gc.h(s, this.az.gl.dv);
    }
    
    public final void ad(final String s) {
        this.az.gc.h(s, this.az.gl.dz);
    }
    
    public final void ac(final String s) {
        this.az.gc.h(s);
    }
    
    public final void ab() {
        this.av.a0();
        this.stop();
        this.v();
        this.aj = false;
    }
    
    public final void aa(final String s) {
        String ec;
        if (this.az.gl.ec == null || this.az.gl.ec.equals("")) {
            ec = "";
        }
        else {
            ec = this.az.gl.ec;
        }
        if (this.az.gl.ef.equals("")) {
            this.ai("JOIN " + s + " " + ec);
            return;
        }
        this.ai("PART #" + this.az.gl.ef);
        this.az.bs();
        this.ai("JOIN " + s + " " + ec);
    }
    
    public final void z(final String s) {
        this.az.gl.ej = s.replace(' ', '_');
        this.ai("nick " + this.az.gl.ej);
    }
    
    public final void y() {
        this.stop();
        this.av.a0();
        this.az.at("Connection closed");
        this.v();
        this.aj = false;
    }
    
    public final void x(final String s, final String s2) {
        if (s2.equalsIgnoreCase("FINGER")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "FINGER " + this.az.gl.ee + " Idle unknown" + this.a0);
            return;
        }
        if (s2.startsWith("ACTION ") || s2.startsWith("action ")) {
            this.ad("* " + s + " " + s2.substring(7));
            return;
        }
        if (s2.equalsIgnoreCase("PING")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "PING " + new Date().getTime() + this.a0);
            return;
        }
        if (s2.startsWith("ECHO") || s2.startsWith("echo")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "ECHO " + s2.substring(4) + this.a0);
            return;
        }
        if (s2.equalsIgnoreCase("CLIENTINFO")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "CLIENTINFO FINGER PING ECHO CLIENTINFO TIME " + "VERSION ( no DCC support yet)" + this.a0);
            return;
        }
        if (s2.equalsIgnoreCase("TIME")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "TIME " + new Date().toString() + this.a0);
            return;
        }
        if (s2.equalsIgnoreCase("VERSION")) {
            this.ai("NOTICE " + s + " :" + this.a0 + "VERSION " + "JPilot IRC Java Client " + "2.42.1" + " " + this.a0);
            return;
        }
        if (s2.startsWith("URL ")) {
            try {
                this.az.bx(s2.substring(4));
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        if (s2.startsWith("SOUND ")) {
            try {
                this.az.bj(s2.substring(6));
                return;
            }
            catch (Exception ex2) {
                return;
            }
        }
        this.af("* " + s + " " + s2);
    }
    
    public final void w() {
        if (this.az.gs) {
            return;
        }
        this.az.at("###### Unregistered copy, evaluation only.");
        this.az.at("###### Please ask webmaster to register it.");
    }
    
    public final void v() {
        for (int size = this.al.size(), i = 0; i < size; ++i) {
            ((g)this.al.elementAt(i)).dispose();
        }
        this.al.removeAllElements();
    }
    
    public final boolean u(final String s) {
        return this.u(s, null);
    }
    
    public final boolean u(final String s, final String s2) {
        return this.u(s, s2, null);
    }
    
    public final boolean u(final String s, final String s2, final Color color) {
        new Dimension(400, 250);
        if (!this.az.gl.c8) {
            return false;
        }
        final int size = this.al.size();
        for (int i = 0; i < size; ++i) {
            final g g = this.al.elementAt(i);
            if (g.getName().equals(s)) {
                if (s2 != null) {
                    g.at(s2, color);
                }
                g.show();
                return true;
            }
        }
        if (size + 1 >= 10) {
            this.ah("*** Max. private chat window reached");
            return false;
        }
        final g g2 = new g(40, 10, s, this.ak, this);
        this.al.addElement(g2);
        if (s2 != null) {
            g2.at(s2, color);
        }
        g2.show();
        return true;
    }
    
    public final boolean t() {
        return this.az.gl.c6 == 1 || this.az.gl.c6 >= 3;
    }
    
    public final boolean s() {
        return this.az.gl.c6 >= 2;
    }
    
    public final boolean r() {
        return this.az.gl.c6 == 4;
    }
    
    public final String q(final String s) {
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

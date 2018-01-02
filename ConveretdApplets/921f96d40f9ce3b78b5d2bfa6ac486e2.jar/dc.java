import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class dc extends Panel implements do, KeyListener, ActionListener
{
    private y p;
    public boolean d;
    private TextField a;
    private dh n;
    private dh v;
    private boolean i;
    private String l;
    private String b;
    private long c;
    private Vector e;
    private Vector f;
    private Vector g;
    private Vector h;
    private int j;
    private x[] k;
    public boolean m;
    private static String o;
    private static String[] q;
    public boolean r;
    private String s;
    private int t;
    private long u;
    private String w;
    private int x;
    private long y;
    
    public dc(final y p) {
        this.d = false;
        this.i = false;
        this.l = "";
        this.b = "";
        this.e = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.h = new Vector();
        this.k = new x[8];
        this.m = false;
        this.r = false;
        this.s = "";
        this.u = System.currentTimeMillis();
        this.w = "";
        this.y = System.currentTimeMillis();
        this.p = p;
        (this.a = new TextField()).setFont(dw.l);
        this.a.addActionListener(this);
        this.a.addKeyListener(this);
        final int a = p.a();
        final int n = (a == 2) ? 70 : ((a == 1) ? 55 : 40);
        this.n = new dh(p, p.p(), 240, 0);
        (this.v = new dh(p, p.p(), 100, 0)).p(0, n);
        this.setLayout(new BorderLayout());
        this.add("North", this.v);
        this.add("Center", this.n);
        this.add("South", this.a);
        this.a.requestFocus();
    }
    
    public final void p() {
        final Color[] array = dw.p[this.p.p()];
        this.v.setBackground(array[8]);
        this.n.p(array[9]);
        this.n.setBackground(array[10]);
        this.a.setBackground(Color.white);
        this.a.setForeground(Color.black);
        for (int i = 0; i < this.j; ++i) {
            this.k[i].l();
        }
    }
    
    public final void b(final String s) {
        this.n.p(s);
    }
    
    public final void invalidate() {
        super.invalidate();
        this.n.invalidate();
        this.v.invalidate();
        this.a.invalidate();
    }
    
    public final void p(final String text) {
        this.a.setText(text);
        this.a.select(0, text.length());
        this.c = System.currentTimeMillis();
    }
    
    public final void addNotify() {
        super.addNotify();
        if (!this.m) {
            this.p(0);
            this.m = true;
        }
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(310, 0);
    }
    
    public final void requestFocus() {
        this.a.requestFocus();
    }
    
    public final void i(final String s) {
        this.v.i(s);
    }
    
    public final void l(final String s) {
        this.n.i(s);
    }
    
    public final void a() {
        this.n.d();
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final i p = this.p.p();
        final String d = p.d();
        if (!keyEvent.isActionKey() || keyEvent.getModifiers() != 0) {
            return;
        }
        if (keyCode == 112) {
            if (this.a.getText().startsWith("/")) {
                return;
            }
            if (!this.a.getText().startsWith("!")) {
                this.a.setText("!" + this.a.getText());
            }
            this.l = this.p(this.a);
            if (this.l.length() == 0 || this.l.equals("!")) {
                return;
            }
            if (this.c + 4000L > System.currentTimeMillis()) {
                du.p((int)(this.c + 4000L - System.currentTimeMillis()));
            }
            this.t = 0;
            this.u = System.currentTimeMillis();
            if (p.p("SHOUT " + this.l.substring(1))) {
                if (this.p.p(d)) {
                    this.l("<6>!" + d + ": <10>" + this.l.substring(1));
                }
                else {
                    this.l("<1>!" + d + ": <10>" + this.l.substring(1));
                }
            }
        }
        else if (keyCode == 113) {
            if (!this.a.getText().startsWith(this.b)) {
                this.a.setText(String.valueOf(this.b) + " " + this.a.getText());
                this.a.select(this.b.length() + 1, this.b.length() + 1);
            }
        }
        else if (keyCode == 114) {
            this.p(this.l);
        }
        else if (keyCode == 115) {
            this.a.setText("");
        }
        else if (keyCode >= 116 && keyCode <= 121) {
            new t(this.p, "10 450 350 General Help Messages|<1>|<1>|<6>Please note the following general situations:|<1>|<12>1. If someone offend you for any reason (bad chat included), consider to <4>Mute<12> the person yourself. Click on the offending chat, then on Mute.|<12>2. If you don't like playing games with someone (cheaters included), please <4>do not play<12> with that person! If you think that person is bad enough, consider posting a message in the forum to help others to avoid the person.|<12>3. If you like privacy, consider open your own table with command <4>/open<12> and <4>set your table private<12> with a password using the Options menu.");
            if (this.a.getText().trim().length() == 0) {
                return;
            }
            if (this.a.getText().startsWith("/")) {
                return;
            }
            this.l = this.p(this.a);
            if (this.l.length() == 0) {
                return;
            }
            if (this.c + 4000L > System.currentTimeMillis()) {
                du.p((int)(this.c + 4000L - System.currentTimeMillis()));
            }
            if (p.p("SENDOP " + this.l)) {
                if (this.p.p(d)) {
                    this.l("<17>+*+" + d + ": <12>" + this.l);
                }
                else {
                    this.l("<4>+*+" + d + ": <12>" + this.l);
                }
            }
        }
        else if (keyCode == 123 && this.a.getText().trim().length() > 0) {
            if (this.a.getText().startsWith("/")) {
                return;
            }
            this.l = this.p(this.a);
            if (this.l.length() == 0) {
                return;
            }
            if (this.c + 4000L > System.currentTimeMillis()) {
                du.p((int)(this.c + 4000L - System.currentTimeMillis()));
            }
            if (!this.d && p.p("WALLRH " + this.l)) {
                for (int i = 0; i < this.j; ++i) {
                    if (this.k[i].p().equalsIgnoreCase("*RoomHelpers*")) {
                        this.k[i].p("<3>{" + d + "} <12>" + this.l);
                    }
                }
                this.l("<3>+RR+" + d + ": <12>" + this.l);
            }
        }
        else if (keyCode == 122 && this.a.getText().trim().length() > 0) {
            if (this.a.getText().startsWith("/")) {
                return;
            }
            this.l = this.p(this.a);
            if (this.l.length() == 0) {
                return;
            }
            if (this.c + 4000L > System.currentTimeMillis()) {
                du.p((int)(this.c + 4000L - System.currentTimeMillis()));
            }
            if (!this.d && p.p("WALLHELPER " + this.l)) {
                for (int j = 0; j < this.j; ++j) {
                    if (this.k[j].p().equalsIgnoreCase("*Helpers*")) {
                        this.k[j].p("<3>{" + d + "} <12>" + this.l);
                    }
                }
                this.l("<3>+HH+" + d + ": <12>" + this.l);
            }
        }
        this.requestFocus();
    }
    
    public static final String p(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            if (dc.o.indexOf(s.charAt(i)) == -1) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    private static final boolean p(String string) {
        string = " " + p(string.toLowerCase()) + " ";
        for (int i = 0; i < dc.q.length; ++i) {
            if (string.indexOf(dc.q[i]) != -1) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean d(final String s) {
        return this.e.indexOf(s.toLowerCase()) != -1;
    }
    
    public final boolean a(final String s) {
        return this.d(s) || this.g.indexOf(s.toLowerCase()) != -1 || false;
    }
    
    public final boolean n(final String s) {
        return this.d(s) || this.f.indexOf(s.toLowerCase()) != -1 || false;
    }
    
    public final void d(final String s) {
        final Vector h = this.h;
        final String lowerCase = s.toLowerCase();
        if (h.indexOf(lowerCase) == -1) {
            h.addElement(lowerCase);
        }
        final Vector e = this.e;
        final String lowerCase2 = s.toLowerCase();
        if (e.indexOf(lowerCase2) != -1) {
            e.removeElement(lowerCase2);
        }
        final Vector f = this.f;
        final String lowerCase3 = s.toLowerCase();
        if (f.indexOf(lowerCase3) != -1) {
            f.removeElement(lowerCase3);
        }
        final Vector g = this.g;
        final String lowerCase4 = s.toLowerCase();
        if (g.indexOf(lowerCase4) != -1) {
            g.removeElement(lowerCase4);
        }
        this.l("<2>+++" + s + " is no longer muted");
    }
    
    public final void v(final String s) {
        final Vector e = this.e;
        final String lowerCase = s.toLowerCase();
        boolean b;
        if (e.indexOf(lowerCase) != -1) {
            b = false;
        }
        else {
            e.addElement(lowerCase);
            b = true;
        }
        if (b) {
            final Vector h = this.h;
            final String lowerCase2 = s.toLowerCase();
            if (h.indexOf(lowerCase2) != -1) {
                h.removeElement(lowerCase2);
            }
            final Vector f = this.f;
            final String lowerCase3 = s.toLowerCase();
            if (f.indexOf(lowerCase3) != -1) {
                f.removeElement(lowerCase3);
            }
            final Vector g = this.g;
            final String lowerCase4 = s.toLowerCase();
            if (g.indexOf(lowerCase4) != -1) {
                g.removeElement(lowerCase4);
            }
            this.n.d(s);
            this.l("<2>+++" + s + " has been muted");
            return;
        }
        this.l("<2>+++" + s + " is already muted");
    }
    
    public final void a(final String s) {
        if (!this.p.p().l()) {
            return;
        }
        if (this.h.indexOf(s.toLowerCase()) != -1 || false) {
            return;
        }
        final Vector f = this.f;
        final String lowerCase = s.toLowerCase();
        boolean b;
        if (f.indexOf(lowerCase) != -1) {
            b = false;
        }
        else {
            f.addElement(lowerCase);
            b = true;
        }
        if (b) {
            this.l("<1>+++flooding detected");
        }
    }
    
    public final void n(final String s) {
        if (!this.p.p().l()) {
            return;
        }
        if (this.h.indexOf(s.toLowerCase()) != -1 || false) {
            return;
        }
        final Vector g = this.g;
        final String lowerCase = s.toLowerCase();
        boolean b;
        if (g.indexOf(lowerCase) != -1) {
            b = false;
        }
        else {
            g.addElement(lowerCase);
            b = true;
        }
        if (b) {
            this.l("<1>+++flooding detected");
        }
    }
    
    public final void d() {
        this.l("<2>+++mute list (" + du.p(this.e) + ")" + " flooder: (" + du.p(this.f) + " " + du.p(this.g) + ")");
    }
    
    private final void p(final int n) {
        this.l("");
        if (n == 1) {
            this.l("<12>***Chat help");
            this.l("<5>Type a message and press Enter to send it to everyone in table");
            this.l("<5>Type '/m name message' to send a private message");
            this.l("<5>Type a message and press F1 to send it to everyone in room");
            this.l("<5>Press F2 to display /m prompt");
            this.l("<5>Press F3 to redisplay last input text");
            this.l("<5>Press F4 to clear input text field");
            this.l("<5>Type a message and press F6 to send it to operators");
        }
        else if (n == 2) {
            this.l("<12>***Image help");
            this.l("<5>Type ' smile or ' h to display 'smile");
            this.l("<5>Type ' sad or ' s to display 'sad");
            this.l("<5>Here are all the codes (without ' sign): angel angry glass grin nose cat confuse cry devil duck elephant fish frog pig scream inlove quiet tongue wazzup wink alien blush bomb bunny down heart laugh perfect punk shock sick sleepy smile star up rose sad smoke co ro chuon bich.  Each of the above images has a one character short cut, from a-z, 0-9, +, (, or c1, c2, c3, c4.  <4>Some images are available to bluenick members only.");
        }
        else if (n == 3) {
            this.l("<12>***Color help");
            this.l("<5>To change text color, using the sequence: < number >");
            this.l("<5>For example, to type a red message, type: <<5>4<5>> message");
            this.l("<5>Here are all the color numbers available: <1>1<2>2<3>3<4>4<5>5<6>6<7>7<8>8<9>9<10>10<11>11<12>12");
        }
        else if (n == 4) {
            this.l("<12>***Table control help (for table owner)");
            this.l("<5>Type '/topic message' to set header message");
            this.l("<5>Type '/private key' to set key as password for your table");
            this.l("<5>Type '/public' to make table public");
            this.l("<5>Type '/eject nick' to eject someone out");
            this.l("<5>Type '/block nick' to preven someone from using your table");
            this.l("<5>Type '/unblock nick' to undo /banname");
            this.l("<5>Type '/blocklist' to list current bans");
            this.l("<5>Type '/addowner nick' to add nick to table's ownerlist");
            this.l("<5>Type '/delowner nick' to remove nick from owner list");
            this.l("<5>Type '/ownerlist' to display current owner list");
        }
        else if (n == 5) {
            this.l("<12>***General game help");
            this.l("<5>Click on FindTable to find a new table");
            this.l("<5>Click on Tables to list table details");
            this.l("<5>Click on Sit to play in current table");
        }
        else if (n == 6) {
            this.l("<12>***Message control help");
            this.l("<5>Type '/ignore name' to ignore messages from name");
            this.l("<5>Type '/unignore name' to ignore messages from name");
            this.l("<5>Type '/listignore name' to list names being ignored");
        }
        else if (n == 7) {
            this.l("<12>***Buddy list help");
            this.l("<5>Type '/buddy add name' to add name to buddy list");
            this.l("<5>Type '/buddy del name' to delete name from buddy list");
            this.l("<5>Type '/buddy list' to view your buddy list");
            this.l("<5>Type '/buddy rlist' to show people whose buddy list contains you");
            this.l("<5>Type '/buddy toggle' to toggle your on-login notification");
        }
        else if (n == 8) {
            this.l("<12>***Local mail help");
            this.l("<5>Type '/mail name message' to send name a local mail");
            this.l("<5>Type '/readmail' to read your local mails");
            this.l("<5>Type '/deletemail all' to delete all mails");
            this.l("<5>Type '/deletemail mailNumericID' to delete a particular mail");
        }
        else if (n == 9) {
            this.l("<12>***Miscelaneous help");
            this.l("<5>Type '/who my_friend' to look up information of my_friend");
            this.l("<5>Type '/donate my_friend amount' to give my_friend money");
            this.l("<5>Type '/LOTTERY number price' to buy lottery ticket");
        }
        else {
            this.l("<12>***Help sessions");
            this.l("<5>Type /1 for chat help");
            this.l("<5>Type /2 for image help");
            this.l("<5>Type /3 for color help");
            this.l("<5>Type /4 for table control help");
            this.l("<5>Type /5 for general game help");
            this.l("<5>Type /6 for message control help");
            this.l("<5>Type /7 for buddy list help");
            this.l("<5>Type /8 for local mail help");
            this.l("<5>Type /9 for misc help");
        }
        this.l("");
        this.l("<12>Type /? for help sessions");
        this.l("");
    }
    
    public final String p(final TextField textField) {
        final String text = textField.getText();
        textField.setText("");
        if (text.trim().length() == 0) {
            return "";
        }
        if (this.p.p(this.p.p().d())) {
            return text;
        }
        boolean b = false;
        final StringBuffer sb = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(text);
        final String[] p = this.p.p().p();
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            boolean b2 = false;
            for (int i = 0; i < p.length; ++i) {
                if (p[i].equalsIgnoreCase(nextToken)) {
                    b2 = true;
                    break;
                }
            }
            if (b2) {
                b = true;
            }
            else {
                sb.append(nextToken).append(" ");
            }
        }
        if (!b) {
            return text;
        }
        if (!this.r) {
            this.r = true;
            this.l("<4>+++Warning: some of the images you used are reserved for <12>bluenick<4> members");
            return text;
        }
        return sb.toString();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() != this.a) {
            return;
        }
        final i p = this.p.p();
        final String d = p.d();
        final String p2 = this.p(this.a);
        if (p2.length() == 0) {
            return;
        }
        if (this.c + 3000L > System.currentTimeMillis()) {
            du.p((int)(this.c + 3000L - System.currentTimeMillis()));
        }
        this.l = p2;
        this.i |= p(p2);
        if (p2.startsWith("/")) {
            final String substring = p2.substring(1);
            if (substring.equals("?")) {
                this.p(0);
                return;
            }
            if (substring.equals("1")) {
                this.p(1);
                return;
            }
            if (substring.equals("2")) {
                this.p(2);
                return;
            }
            if (substring.equals("3")) {
                this.p(3);
                return;
            }
            if (substring.equals("4")) {
                this.p(4);
                return;
            }
            if (substring.equals("5")) {
                this.p(5);
                return;
            }
            if (substring.equals("6")) {
                this.p(6);
                return;
            }
            if (substring.equals("7")) {
                this.p(7);
                return;
            }
            if (substring.equals("8")) {
                this.p(8);
                return;
            }
            if (substring.equals("9")) {
                this.p(9);
                return;
            }
            if (substring.equals("0")) {
                dw.p(0, 2);
                dw.p(1, 3);
                this.l("<12>* The board color has been swapped");
                this.p.i();
                return;
            }
            if (substring.toLowerCase().startsWith("m ") || substring.toLowerCase().startsWith("w ") || substring.toLowerCase().startsWith("msg ")) {
                this.p(du.p(substring, 1, ' '), du.d(substring, 1, ' '));
                return;
            }
            if (substring.toLowerCase().startsWith("q ")) {
                if (p.p("MSG service " + substring.substring(2))) {
                    this.l("<4>+%+ <6>" + substring.substring(2));
                }
            }
            else if (substring.toLowerCase().startsWith("buddy")) {
                if (p.p("MSG service " + substring + " ")) {
                    this.l("<4>*-* <6>" + substring);
                }
            }
            else {
                if (substring.toLowerCase().equals("ignore")) {
                    this.l("<2>+++Usage /ignore name");
                    return;
                }
                if (substring.toLowerCase().startsWith("ignore ")) {
                    this.v(substring.substring(7).trim());
                    return;
                }
                if (substring.toLowerCase().equals("unignore")) {
                    this.l("<2>+++Usage /unignore name");
                    return;
                }
                if (substring.toLowerCase().startsWith("unignore ")) {
                    this.d(substring.substring(9).trim());
                    return;
                }
                if (substring.toLowerCase().startsWith("listignore")) {
                    this.d();
                    return;
                }
                if (substring.toLowerCase().startsWith("move") || substring.toLowerCase().startsWith("play") || substring.toLowerCase().startsWith("enter") || this.d || !p.p(substring)) {
                    return;
                }
                this.l("<4>!!! <6>" + substring);
            }
        }
        else if (p2.startsWith("!")) {
            this.t = 0;
            this.u = System.currentTimeMillis();
            if (p2.length() > 1 && p.p("SHOUT " + p2.substring(1))) {
                if (this.p.p(d)) {
                    this.l("<6>!" + d + ": <10>" + p2.substring(1));
                    return;
                }
                this.l("<1>!" + d + ": <10>" + p2.substring(1));
            }
        }
        else {
            final String p3 = du.p(p2, 0, ' ');
            if (p3.endsWith(")") && p3.indexOf(":") == -1) {
                this.p(p3.substring(0, p3.length() - 1), du.d(p2, 0, ' '));
                return;
            }
            this.x = 0;
            this.y = System.currentTimeMillis();
            if (p.p("TALK " + p2)) {
                if (this.p.p(d)) {
                    this.l("<6>[" + d + "] <5>" + p2);
                    return;
                }
                this.l("<1>[" + d + "] <5>" + p2);
            }
        }
    }
    
    private final void p(final String s, final String s2) {
        final i p2 = this.p.p();
        final String d = p2.d();
        if (s2 != null) {
            this.b = "/m " + s;
            if (p2.p("M " + s + " " + s2)) {
                this.l("<6>{to " + s + "} <5>" + s2);
                for (int i = 0; i < this.j; ++i) {
                    if (this.k[i].p().equalsIgnoreCase(s)) {
                        this.k[i].p("<12>{" + d + "} <5>" + s2);
                    }
                }
            }
        }
    }
    
    public final void a(final String s, final String s2) {
        if (this.u - System.currentTimeMillis() > 60000L) {
            this.s = "";
        }
        if (this.n(s)) {
            return;
        }
        if ((this.i || !p(s2)) && this.p.p().n()) {
            if (this.p.p(s)) {
                this.l("<12>!" + s + ": <10>" + s2);
            }
            else {
                this.l("<10>!" + s + ": <10>" + s2);
            }
        }
        if (s.equalsIgnoreCase(this.s) && !s.equals("service")) {
            ++this.t;
            if (this.t == 5) {
                this.a(s);
            }
        }
        else {
            this.u = System.currentTimeMillis();
            this.t = 1;
            this.s = s;
        }
    }
    
    public final void d(final String w, final String s) {
        if (this.y - System.currentTimeMillis() >= 6000L) {
            this.w = "";
        }
        if (this.a(w)) {
            return;
        }
        if ((this.i || !p(s)) && (this.p.p().v() || this.p.p().p(w) != -1)) {
            if (this.p.p(w)) {
                this.l("<12>[" + w + "] <5>" + s);
            }
            else {
                this.l("<1>[" + w + "] <5>" + s);
            }
        }
        if (w.equalsIgnoreCase(this.w) && !w.equals("service")) {
            ++this.x;
            if (this.x == 12) {
                this.n(w);
                this.w = "";
            }
        }
        else {
            this.y = System.currentTimeMillis();
            this.x = 1;
            this.w = w;
        }
    }
    
    public final boolean p(final String s, final String s2) {
        if (s.equals("TALK")) {
            final String p2 = du.p(s2, 0, ' ');
            this.d(p2, s2.substring(p2.length()).trim());
            return true;
        }
        if (s.equals("SHOUT")) {
            final String p3 = du.p(s2, 0, ' ');
            this.a(p3, s2.substring(p3.length()).trim());
            return true;
        }
        if (s.equals("WALLRH")) {
            final String p4 = du.p(s2, 0, ' ');
            final String trim = s2.substring(p4.length()).trim();
            if (!this.d(p4) && (this.i || !p(trim))) {
                if (this.p.p().p()) {
                    this.c("*RoomHelpers*");
                }
                for (int i = 0; i < this.j; ++i) {
                    if (this.k[i].p().equalsIgnoreCase("*RoomHelpers*")) {
                        this.k[i].p("<3>{" + p4 + "} <12>" + trim);
                    }
                }
                this.l("<3>+RR+" + p4 + ": <12>" + trim);
            }
            return true;
        }
        if (s.equals("WALLHELPER")) {
            final String p5 = du.p(s2, 0, ' ');
            final String trim2 = s2.substring(p5.length()).trim();
            if (!this.d(p5) && (this.i || !p(trim2))) {
                if (this.p.p().p()) {
                    this.c("*Helpers*");
                }
                for (int j = 0; j < this.j; ++j) {
                    if (this.k[j].p().equalsIgnoreCase("*Helpers*")) {
                        this.k[j].p("<3>{" + p5 + "} <12>" + trim2);
                    }
                }
                this.l("<3>+HH+" + p5 + ": <12>" + trim2);
            }
            return true;
        }
        if (s.equals("SENDOP")) {
            final String p6 = du.p(s2, 0, ' ');
            final String trim3 = s2.substring(p6.length()).trim();
            if (!this.d(p6) && (this.i || !p(trim3))) {
                for (int k = 0; k < this.j; ++k) {
                    if (this.k[k].p().equalsIgnoreCase("*Helpers*")) {
                        this.k[k].p("<4>+*+" + p6 + ": <12>" + trim3);
                    }
                }
                this.l("<4>+*+" + p6 + ": <12>" + trim3);
            }
            return true;
        }
        if (s.equals("WHO")) {
            this.l("<2>***info: " + s2);
            return true;
        }
        if (s.equals("MAIL")) {
            final String p7 = du.p(s2, 0, ' ');
            final String p8 = du.p(s2, 1, ' ');
            final String p9 = du.p(s2, 2, ' ');
            final String d = du.d(s2, 2, ' ');
            if (!this.d(p9)) {
                this.l("<5>***#" + p7 + ":" + p9 + ":" + p8 + " <12>" + d);
            }
            return true;
        }
        if (s.startsWith("SYSTEM")) {
            this.l("<1>***" + s2);
            return true;
        }
        if (s.equals("ADVERTISE")) {
            if (s2.startsWith("http://") || s2.startsWith("https://") || s2.startsWith("cmd://")) {
                this.l(String.valueOf(du.p(s2, 0, ' ')) + " <12>*** " + du.d(s2, 0, ' '));
            }
            else {
                this.l("<12>*** " + s2);
            }
            return true;
        }
        if (s.equals("ERROR")) {
            this.l("<4>***Error: " + s2);
            return true;
        }
        if (s.equals("MSGERROR")) {
            final String p10 = du.p(s2, 0, ' ');
            final String substring = s2.substring(1 + p10.length());
            for (int l = 0; l < this.j; ++l) {
                if (this.k[l].p().equalsIgnoreCase(p10)) {
                    this.k[l].p("<4>ERROR: " + substring);
                }
            }
            this.l("<4>{Error " + p10 + "} " + substring);
            return true;
        }
        if (s.equals("MSG")) {
            final String p11 = du.p(s2, 0, ' ');
            final String substring2 = s2.substring(1 + p11.length());
            if ((this.i || !p(substring2)) && !this.d(p11)) {
                if (this.p.p().p()) {
                    this.c(p11);
                }
                for (int n = 0; n < this.j; ++n) {
                    if (this.k[n].p().equalsIgnoreCase(p11)) {
                        this.k[n].p("<6>{" + p11 + "} <5>" + substring2);
                    }
                }
                this.l("<6>{from " + p11 + "} <5>" + substring2);
            }
            return true;
        }
        return false;
    }
    
    public final void c(final String s) {
        if (s.equalsIgnoreCase("service")) {
            return;
        }
        for (int i = 0; i < this.j; ++i) {
            if (this.k[i].p().equalsIgnoreCase(s)) {
                this.k[i].d();
                return;
            }
        }
        if (this.j == 8) {
            return;
        }
        this.k[this.j++] = new x(this.p, s);
        this.k[this.j - 1].show();
        this.k[this.j - 1].requestFocus();
    }
    
    public final void e(final String s) {
        for (int i = 0; i < this.j; ++i) {
            if (this.k[i].p().equalsIgnoreCase(s)) {
                for (int j = i + 1; j < this.j; ++j) {
                    this.k[j - 1] = this.k[j];
                }
                --this.j;
            }
        }
    }
    
    public final void n() {
        for (int i = 0; i < this.j; ++i) {
            this.k[i].dispose();
        }
        this.j = 0;
    }
    
    static {
        dc.o = ":/\\~`!@#$%^&()-_+=.,?";
        dc.q = new String[] { "fuck", "f**k", "f*ck", "fucking", " bitch ", " ass ", " cock ", " dick ", " d*ck ", " shit ", " sh*t ", " sh**", " asshole ", "phuk", "fuk", " fuc ", "f*k", "f*c", "du ma", "du me", "con cat", "cai lon", "con ddi", "con ddiem", "con diem", "thang ddi", "dit me", "dit ma" };
    }
}

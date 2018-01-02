import java.text.DateFormat;
import java.awt.Point;
import java.io.IOException;
import java.applet.Applet;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Thread
{
    private e p;
    private m x;
    private long s;
    private long H;
    private int goto;
    private String w;
    public String do;
    public String new;
    private boolean B;
    private boolean E;
    private boolean v;
    private boolean d;
    private boolean z;
    private DataInputStream char;
    private BufferedReader F;
    private String c;
    private Object l;
    private int M;
    private String e;
    private String o;
    private String for;
    private String j;
    private String K;
    private String void;
    public int P;
    public boolean try;
    private boolean u;
    private Vector h;
    private Vector g;
    private int[][] J;
    private int r;
    private int if;
    private NumberFormat a;
    private NumberFormat G;
    static Date L;
    static SimpleDateFormat case;
    private TimeZone I;
    private Calendar y;
    private long t;
    private long null;
    private long long;
    private boolean int;
    public static final int A = 1;
    public static final int f = 2;
    public static final int q = 3;
    public static final int byte = 4;
    public static final int O = 5;
    public static final int k = 6;
    public static final int n = 7;
    public static final int N = 8;
    public static final int D = 9;
    public static final short else = 0;
    public static final short b = 1;
    public static final short m = 2;
    public static final short C = 3;
    public static final short i = 4;
    
    public l(final e p4, final String w, final String for1, final String j) {
        this.x = null;
        this.B = false;
        this.E = false;
        this.v = true;
        this.d = false;
        this.z = false;
        this.c = "test";
        this.l = new Object();
        this.M = 70;
        this.e = "234";
        this.o = "unknown";
        this.K = "";
        this.void = "";
        this.P = -1;
        this.try = true;
        this.u = false;
        this.I = TimeZone.getDefault();
        this.y = Calendar.getInstance();
        this.t = Calendar.getInstance().get(15) / 1000;
        this.int = false;
        final long n = 3000L;
        this.H = n;
        this.s = n;
        this.goto = 10000;
        this.start();
        this.p = p4;
        this.for = for1;
        this.j = j;
        if (p4 instanceof Applet) {
            String c = ((Applet)p4).getParameter("name");
            if (c == null) {
                c = ((Applet)p4).getParameter("id");
            }
            if (c != null) {
                this.c = c;
            }
            final String parameter = ((Applet)p4).getParameter("USBondsFormat");
            if (parameter != null && (parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("yes"))) {
                this.u = true;
            }
        }
        this.w = w;
    }
    
    public void a(final int goto1) {
        this.goto = goto1;
    }
    
    public void for(final String k) {
        this.K = k;
    }
    
    public void if(final String void1) {
        this.void = void1;
    }
    
    public l(final e p7, final String w, final String do1, final String s, final String for1, final String j, final String e) {
        this.x = null;
        this.B = false;
        this.E = false;
        this.v = true;
        this.d = false;
        this.z = false;
        this.c = "test";
        this.l = new Object();
        this.M = 70;
        this.e = "234";
        this.o = "unknown";
        this.K = "";
        this.void = "";
        this.P = -1;
        this.try = true;
        this.u = false;
        this.I = TimeZone.getDefault();
        this.y = Calendar.getInstance();
        this.t = Calendar.getInstance().get(15) / 1000;
        this.int = false;
        final long n = 3000L;
        this.H = n;
        this.s = n;
        this.goto = 10000;
        this.start();
        this.for = for1;
        this.j = j;
        this.new = this.do(s);
        this.if = (this.new.length() + 1) / 2;
        this.r = c.if(do1, "|").size();
        this.J = new int[this.r][];
        for (int i = 0; i < this.r; ++i) {
            this.J[i] = new int[this.if];
            for (int k = 0; k < this.if; ++k) {
                this.J[i][k] = 4;
            }
        }
        this.a = NumberFormat.getInstance();
        (this.G = NumberFormat.getInstance()).setMinimumIntegerDigits(2);
        this.G.setMaximumIntegerDigits(2);
        this.p = p7;
        if (p7 instanceof Applet) {
            String c = ((Applet)p7).getParameter("name");
            if (c == null) {
                c = ((Applet)p7).getParameter("id");
            }
            if (c != null) {
                this.c = c;
            }
            final String parameter = ((Applet)p7).getParameter("USBondsFormat");
            if (parameter != null && (parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("yes"))) {
                this.u = true;
            }
        }
        this.w = w;
        this.do = do1;
        this.e = e;
        this.h = new Vector();
        this.g = new Vector();
    }
    
    public void run() {
        while (!this.B) {
            this.case();
            this.do();
            this.case();
            this.long();
        }
    }
    
    public void a(final long n) {
        this.s = n;
        this.H = n;
    }
    
    public void a(final String e) {
        this.e = e;
    }
    
    public synchronized void do() {
        if (!this.v) {
            if (this.x == null) {
                this.d = true;
                try {
                    this.x = new m(this.w, this.do, this.new, this.c, this.M, this.e, this.for, this.j, this.K, this.void, this.P);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    this.p.onQuoteMonitorError("No connection to server\n" + ex);
                    this.if(this.goto);
                }
            }
            else {
                if (this.x.if()) {
                    return;
                }
                this.d = false;
                try {
                    this.null = System.currentTimeMillis();
                    this.long = this.null;
                    this.x.for();
                }
                catch (Exception ex2) {
                    this.long = System.currentTimeMillis();
                    this.p.onQuoteMonitorError("Connection to server lost (" + (this.long - this.null) + ")\n" + ex2);
                    this.if((int)this.H);
                }
            }
            if (this.x != null) {
                this.char = this.x.a();
                if (this.char == null) {
                    System.out.println("br is null");
                    this.if(this.goto);
                }
                if (this.int() && !this.E) {
                    this.if();
                }
                try {
                    this.char.close();
                }
                catch (IOException ex3) {
                    System.out.println("IOException 1");
                }
                this.char = null;
            }
            try {
                if (this.F != null) {
                    this.F.close();
                }
            }
            catch (IOException ex4) {
                System.out.println("IOException 2");
            }
        }
        else {
            System.out.println("Data Frozen");
        }
    }
    
    private synchronized void long() {
        try {
            this.wait(this.H);
            this.H = this.s;
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interrupted " + ex);
        }
    }
    
    public synchronized void char() {
        this.try();
    }
    
    public void goto() {
        this.B = true;
    }
    
    protected final synchronized void case() {
        if (this.E) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException threadCheckSuspend");
            }
            this.E = false;
        }
    }
    
    public synchronized void try() {
        if (this.E) {
            this.notify();
        }
    }
    
    public void byte() {
        this.v = false;
    }
    
    private void if(final int n) {
        this.x = null;
        System.out.println("Schedule Restart");
        this.H = n;
    }
    
    private synchronized void if() {
        if (this.d) {
            this.p.onQuoteHistory(this.h, this.g);
        }
        else {
            this.p.onQuoteUpdate(this.h, this.g);
        }
    }
    
    public synchronized boolean int() {
        try {
            this.h.removeAllElements();
            this.g.removeAllElements();
            this.char.readShort();
            this.char.readShort();
            this.char.readShort();
            for (short short1 = this.char.readShort(), n = 0; n < short1; ++n) {
                final byte byte1 = this.char.readByte();
                switch (byte1) {
                    case 1: {
                        this.a(false);
                        break;
                    }
                    case 6: {
                        this.a(true);
                        break;
                    }
                    case 2:
                    case 7:
                    case 8: {
                        this.a(byte1);
                        break;
                    }
                    case 3: {
                        this.for();
                        break;
                    }
                    case 4: {
                        this.new();
                        break;
                    }
                    case 5: {
                        this.a();
                        break;
                    }
                    case 9: {
                        this.else();
                        break;
                    }
                    default: {
                        System.out.println("Unknown data type " + byte1);
                        break;
                    }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Read error");
            return false;
        }
        return true;
    }
    
    private synchronized void a(final boolean b) throws IOException {
        int byte1 = -1;
        if (b) {
            byte1 = this.char.readByte();
        }
        final byte[] array = new byte[6];
        final int unsignedShort = this.char.readUnsignedShort();
        final int unsignedShort2 = this.char.readUnsignedShort();
        final int unsignedShort3 = this.char.readUnsignedShort();
        final int n = unsignedShort2;
        final float intBitsToFloat = Float.intBitsToFloat(unsignedShort << 16 | unsignedShort3);
        final Float n2 = new Float(intBitsToFloat);
        final Point do1 = this.do(n);
        if (b) {
            this.J[do1.x][do1.y] = byte1;
        }
        final int minimumFractionDigits = this.J[do1.x][do1.y];
        if (minimumFractionDigits >= 0) {
            this.a.setMinimumFractionDigits(minimumFractionDigits);
            this.a.setMaximumFractionDigits(minimumFractionDigits + 1);
            this.h.addElement(do1);
            this.g.addElement(this.a.format(intBitsToFloat));
        }
        else {
            final String string = n2.toString();
            final int index = string.indexOf(".");
            Integer.parseInt(string.substring(index + 1));
            final float n3 = intBitsToFloat - (int)intBitsToFloat;
            int n4 = 1 << -minimumFractionDigits;
            int n5 = (int)(n3 * n4);
            this.h.addElement(do1);
            if (this.u) {
                this.a(string, minimumFractionDigits, index, n5);
            }
            else if (n5 != 0) {
                if (this.int) {
                    while (n5 % 2 == 0) {
                        n5 /= 2;
                        n4 /= 2;
                    }
                }
                this.g.addElement((((int)intBitsToFloat != 0) ? (string.substring(0, index) + " ") : "") + n5 + "/" + n4);
            }
            else {
                this.g.addElement(string.substring(0, index));
            }
        }
    }
    
    private synchronized void a(final String s, final int n, final int n2, final int n3) {
        final float n4 = n3 * 1.0f / (1 << -n - 5);
        this.G.setMaximumFractionDigits(-n - 5);
        this.G.setMinimumFractionDigits(-n - 5);
        this.g.addElement(s.substring(0, n2) + "'" + this.G.format(n4));
    }
    
    private synchronized void a(final byte b) throws IOException {
        final int unsignedShort = this.char.readUnsignedShort();
        final int unsignedShort2 = this.char.readUnsignedShort();
        final int unsignedShort3 = this.char.readUnsignedShort();
        final int n = unsignedShort2;
        final int n2 = unsignedShort << 16 | unsignedShort3;
        switch (b) {
            case 2: {
                final Integer n3 = new Integer(n2);
                this.h.addElement(this.do(n));
                this.g.addElement(n3.toString());
                break;
            }
            case 8: {
                l.L.setTime(n2 * 1000L);
                l.case.applyPattern("dd MMM yyyy");
                this.h.addElement(this.do(n));
                this.g.addElement(l.case.format(l.L));
                break;
            }
            case 7: {
                long time = n2 * 1000L;
                this.y.setTime(new Date(time));
                if (this.try) {
                    final TimeZone i = this.I;
                    final Calendar y = this.y;
                    final Calendar y2 = this.y;
                    final int value = y.get(0);
                    final Calendar y3 = this.y;
                    final Calendar y4 = this.y;
                    final int value2 = y3.get(1);
                    final Calendar y5 = this.y;
                    final Calendar y6 = this.y;
                    final int value3 = y5.get(2);
                    final Calendar y7 = this.y;
                    final Calendar y8 = this.y;
                    final int value4 = y7.get(5);
                    final Calendar y9 = this.y;
                    final Calendar y10 = this.y;
                    final int value5 = y9.get(7);
                    final Calendar y11 = this.y;
                    final Calendar y12 = this.y;
                    this.t = i.getOffset(value, value2, value3, value4, value5, y11.get(14));
                    time -= this.t;
                }
                l.L.setTime(time);
                l.case.applyPattern("HH:mm:ss");
                this.h.addElement(this.do(n));
                this.g.addElement(l.case.format(l.L));
                break;
            }
        }
    }
    
    private synchronized void for() throws IOException {
        final short short1 = this.char.readShort();
        final byte byte1 = this.char.readByte();
        final byte[] array = new byte[byte1];
        this.char.readFully(array);
        final String s = new String(array, 0, byte1);
        s.indexOf(0);
        this.h.addElement(this.do(short1));
        this.g.addElement(s);
    }
    
    private synchronized void new() throws IOException {
        final short short1 = this.char.readShort();
        final int int1 = this.char.readInt();
        switch (short1) {
            case 0: {
                this.x.a(int1);
                break;
            }
            case 1: {
                this.x.if(int1);
                break;
            }
        }
    }
    
    private synchronized void a() throws IOException {
        final byte byte1 = this.char.readByte();
        final short short1 = this.char.readShort();
        final byte[] array = new byte[byte1];
        this.char.readFully(array);
        final String s = new String(array, 0, byte1 - 1);
        s.indexOf(0);
        switch (short1) {
            case 2: {
                if (s.equalsIgnoreCase("RESET")) {
                    this.z = false;
                    this.x = null;
                    break;
                }
                if (s.equalsIgnoreCase("RELOAD")) {
                    this.p.onReload(10);
                    break;
                }
                break;
            }
            case 3: {
                this.p.onQuoteMonitorError(s);
                break;
            }
            case 4: {
                this.p.onQuoteMonitorInfo(s);
                break;
            }
        }
    }
    
    private void else() throws IOException {
        for (byte byte1 = this.char.readByte(), b = 0; b < byte1; ++b) {
            this.char.readByte();
        }
    }
    
    private Point do(final int n) {
        return new Point(n % 1000, n / 1000);
    }
    
    private String do(final String s) {
        final int index = s.indexOf("Y");
        if (index < 0) {
            return s;
        }
        return s.substring(0, index) + "G" + s.substring(index + 1) + "|B|A";
    }
    
    static {
        l.L = new Date(0L);
        (l.case = (SimpleDateFormat)DateFormat.getDateTimeInstance(1, 1)).setCalendar(Calendar.getInstance());
    }
}

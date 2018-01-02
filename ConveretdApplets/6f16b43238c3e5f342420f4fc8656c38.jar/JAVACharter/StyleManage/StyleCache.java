// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.StyleManage;

import java.util.StringTokenizer;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.util.Hashtable;
import com.microstar.xml.a;
import com.microstar.xml.XmlHandler;

public class StyleCache implements XmlHandler
{
    private String case;
    private String l;
    private String char;
    private int if;
    private int k;
    public int new;
    private a int;
    private int for;
    private int goto;
    private int j;
    private int else;
    private int try;
    private Hashtable d;
    private Color f;
    private Vector h;
    private JAVACharter.StyleManage.a e;
    private c do;
    private JAVACharter.c.c long;
    public static final int g = 0;
    public static final int null = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int void = 4;
    private String i;
    private String a;
    private String byte;
    
    public StyleCache(final String char1, final String case1, final JAVACharter.c.c long1, final String s) {
        this.for = 0;
        this.goto = 0;
        this.j = 0;
        this.else = 0;
        this.try = 0;
        this.d = new Hashtable();
        this.f = new Color(255, 255, 255);
        this.h = new Vector();
        this.do = new c();
        this.case = case1;
        this.char = char1;
        if (s == null) {
            this.l = this.case + "JAVACharter/styles.asp?style=" + char1;
        }
        else {
            this.l = s + "/styles.asp?style=" + char1;
        }
        this.long = long1;
        this.i = "us";
        this.a = "0";
    }
    
    public String long() {
        return this.i;
    }
    
    public void case() {
        this.i = this.d.get("list");
        this.else = 0;
        this.d.clear();
    }
    
    public String void() {
        return this.a;
    }
    
    public void e() {
        this.a = this.d.get("value");
        this.else = 0;
        this.d.clear();
    }
    
    public String j() {
        return this.byte;
    }
    
    public void g() {
        this.byte = this.d.get("value");
        this.else = 0;
        this.d.clear();
    }
    
    public void b() {
        try {
            this.int(this.l);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    void int(final String s) throws Exception {
        (this.int = new a()).a(this);
        this.int.a(s, null, (String)null);
    }
    
    public int int() {
        return this.for;
    }
    
    public int k() {
        return this.new;
    }
    
    public c c() {
        return this.do;
    }
    
    public void new(final String s) {
        this.do.if(s);
    }
    
    public JAVACharter.StyleManage.a a(final int n) {
        return this.h.elementAt(n);
    }
    
    public Object a(final String s, final String s2) {
        return null;
    }
    
    public void if(final String s) {
    }
    
    public void for(final String s) {
    }
    
    public void if() {
    }
    
    public void a() {
    }
    
    public void a(final String s, final String s2, final String s3) {
    }
    
    public void a(final String s, final String s2, final boolean b) {
        if (b) {
            this.d.put(s, s2);
            ++this.else;
        }
    }
    
    public void a(final String s) {
        try {
            if (s.equalsIgnoreCase("bcx")) {
                System.out.println("");
            }
            else if (s.equalsIgnoreCase("canvas")) {
                this.null();
            }
            else if (s.equalsIgnoreCase("activedisplay")) {
                this.f();
            }
            else if (s.equalsIgnoreCase("cancolor")) {
                this.a(this.f);
            }
            else if (s.equalsIgnoreCase("chart")) {
                this.do();
            }
            else if (s.equalsIgnoreCase("drawstyle")) {
                this.i();
            }
            else if (s.equalsIgnoreCase("palettes")) {
                this.try();
            }
            else if (s.equalsIgnoreCase("palcolor")) {
                this.new();
            }
            else if (s.equalsIgnoreCase("bgcolor")) {
                if (this.e != null) {
                    this.a(this.e.a);
                }
            }
            else if (s.equalsIgnoreCase("horizcolor")) {
                if (this.e != null) {
                    this.e.do(this.for());
                }
            }
            else if (s.equalsIgnoreCase("vertcolor")) {
                if (this.e != null) {
                    this.e.for(this.for());
                }
            }
            else if (s.equalsIgnoreCase("hlabels")) {
                if (this.e != null) {
                    this.e.a(this.for());
                }
            }
            else if (s.equalsIgnoreCase("vlabels")) {
                if (this.e != null) {
                    this.e.if(this.for());
                }
            }
            else if (s.equalsIgnoreCase("legend")) {
                if (this.e != null) {
                    this.a(this.e);
                }
            }
            else if (s.equalsIgnoreCase("line")) {
                this.goto();
            }
            else if (s.equalsIgnoreCase("label")) {
                this.char();
            }
            else if (s.equalsIgnoreCase("field")) {
                this.byte();
            }
            else if (s.equalsIgnoreCase("font")) {
                this.d();
            }
            else if (s.equalsIgnoreCase("countries")) {
                this.case();
            }
            else if (s.equalsIgnoreCase("partnerid")) {
                this.e();
            }
            else if (s.equalsIgnoreCase("clientid")) {
                this.g();
            }
            else if (s.equalsIgnoreCase("formats")) {
                this.else();
            }
            else if (s.equalsIgnoreCase("markerstyle")) {
                this.h();
            }
        }
        catch (Exception ex) {
            System.out.println("STYLE SHEET ERROR: " + ex);
            ex.printStackTrace(System.out);
        }
    }
    
    public void null() {
        final String s = this.d.get("width");
        final String s2 = this.d.get("height");
        if (s != null) {
            this.if = Integer.parseInt(s);
        }
        else {
            this.if = 100;
        }
        if (s2 != null) {
            this.k = Integer.parseInt(s2);
        }
        else {
            this.k = 100;
        }
        this.else = 0;
        this.d.clear();
    }
    
    public void f() {
        final String s = this.d.get("placement");
        final String s2 = this.d.get("height");
        final String s3 = this.d.get("width");
        if (s.equalsIgnoreCase("bottom")) {
            this.for = 1;
        }
        else if (s.equalsIgnoreCase("onchart")) {
            this.for = 2;
        }
        else if (s.equalsIgnoreCase("left")) {
            this.for = 3;
        }
        else if (s.equalsIgnoreCase("right")) {
            this.for = 4;
        }
        else {
            this.for = 0;
        }
        if (s2 != null) {
            this.goto = Integer.parseInt(s2);
        }
        else {
            this.goto = 36;
        }
        if (s3 != null) {
            this.j = Integer.parseInt(s3);
        }
        else {
            this.j = 496;
        }
        this.long.a(this.j, this.goto);
        this.else = 0;
        this.d.clear();
    }
    
    public void goto() {
        final String s = this.d.get("x1");
        final String s2 = this.d.get("y1");
        final String s3 = this.d.get("x2");
        final String s4 = this.d.get("y2");
        final String s5 = this.d.get("color");
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        int int4 = 0;
        Color color = new Color(16777215);
        try {
            if (s != "") {
                int1 = Integer.parseInt(s);
            }
            if (s2 != "") {
                int2 = Integer.parseInt(s2);
            }
            if (s3 != "") {
                int3 = Integer.parseInt(s3);
            }
            if (s4 != "") {
                int4 = Integer.parseInt(s4);
            }
            if (s5 != "") {
                color = new Color(Integer.parseInt(s5, 16));
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.long.a(int1, int2, int3, int4, color);
        this.else = 0;
        this.d.clear();
    }
    
    public void char() {
        final String s = this.d.get("value");
        final String s2 = this.d.get("x");
        final String s3 = this.d.get("y");
        final String s4 = this.d.get("width");
        final String s5 = this.d.get("height");
        final String s6 = this.d.get("color");
        final String s7 = this.d.get("textcolor");
        final String s8 = this.d.get("chartid");
        final String s9 = this.d.get("justify");
        final String s10 = this.d.get("font");
        int int1 = 0;
        int int2 = 0;
        int int3 = 60;
        int int4 = 16;
        Color color = new Color(16777215);
        Color color2 = new Color(0);
        int int5 = -1;
        int int6 = 0;
        try {
            if (s2 != null) {
                int1 = Integer.parseInt(s2);
            }
            if (s3 != null) {
                int2 = Integer.parseInt(s3);
            }
            if (s4 != null) {
                int3 = Integer.parseInt(s4);
            }
            if (s5 != null) {
                int4 = Integer.parseInt(s5);
            }
            if (s6 != null) {
                color = new Color(Integer.parseInt(s6, 16));
            }
            if (s7 != null) {
                color2 = new Color(Integer.parseInt(s7, 16));
            }
            if (s8 != null) {
                int5 = Integer.parseInt(s8);
            }
            if (s9 != null) {
                int6 = Integer.parseInt(s9);
                if (int6 == 0) {
                    int6 = 0;
                }
                else if (int6 == 1) {
                    int6 = 1;
                }
                else if (int6 == 2) {
                    int6 = 2;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.long.a(s, int1, int2, int3, int4, color, color2, s10, int5, int6);
        this.else = 0;
        this.d.clear();
    }
    
    public void byte() {
        final String s = this.d.get("key");
        final String s2 = this.d.get("value");
        final String s3 = this.d.get("x");
        final String s4 = this.d.get("y");
        final String s5 = this.d.get("width");
        final String s6 = this.d.get("height");
        final String s7 = this.d.get("color");
        final String s8 = this.d.get("textcolor");
        final String s9 = this.d.get("chartid");
        final String s10 = this.d.get("justify");
        final String s11 = this.d.get("font");
        if (s == "") {
            System.out.println("You must enter a key for all Labels");
        }
        int int1 = 0;
        int int2 = 0;
        int int3 = 60;
        int int4 = 16;
        Color color = new Color(16777215);
        Color color2 = new Color(0);
        int int5 = -1;
        int int6 = 0;
        try {
            if (s3 != null) {
                int1 = Integer.parseInt(s3);
            }
            if (s4 != null) {
                int2 = Integer.parseInt(s4);
            }
            if (s5 != null) {
                int3 = Integer.parseInt(s5);
            }
            if (s6 != null) {
                int4 = Integer.parseInt(s6);
            }
            if (s7 != null) {
                color = new Color(Integer.parseInt(s7, 16));
            }
            if (s8 != null) {
                color2 = new Color(Integer.parseInt(s8, 16));
            }
            if (s9 != null) {
                int5 = Integer.parseInt(s9);
            }
            if (s10 != null) {
                int6 = Integer.parseInt(s10);
                if (int6 == 0) {
                    int6 = 0;
                }
                else if (int6 == 1) {
                    int6 = 1;
                }
                else if (int6 == 2) {
                    int6 = 2;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.long.a(s, s2, int1, int2, int3, int4, color, color2, s11, int5, int6);
        this.else = 0;
        this.d.clear();
    }
    
    public void d() {
        final String s = this.d.get("key");
        final String s2 = this.d.get("style");
        final String s3 = this.d.get("size");
        if (s == "") {
            System.out.println("You must enter a key for all Labels");
        }
        int int1 = 0;
        int int2 = 10;
        try {
            if (s3 != null) {
                int2 = Integer.parseInt(s3);
            }
            if (s2 != null) {
                int1 = Integer.parseInt(s2);
                if (int1 == 0) {
                    int1 = 0;
                }
                else if (int1 == 1) {
                    int1 = 1;
                }
                else if (int1 == 2) {
                    int1 = 2;
                }
                else if (int1 == 3) {
                    int1 = 3;
                }
                else {
                    int1 = 0;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.long.a(s, int1, int2);
        this.else = 0;
        this.d.clear();
    }
    
    public void h() {
        final String s = this.d.get("name");
        final Font font = new Font("MarkerDefault", 0, 10);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.d.get("xpoints"), ",");
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.d.get("ypoints"), ",");
        final int countTokens = stringTokenizer.countTokens();
        final int countTokens2 = stringTokenizer2.countTokens();
        if (countTokens != countTokens2) {
            System.out.println("X and Y points don't line up in " + s);
        }
        final int[] array = new int[countTokens];
        final int[] array2 = new int[countTokens2];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
            array2[i] = Integer.parseInt(stringTokenizer2.nextToken());
        }
        final String s2 = this.d.get("r");
        final String s3 = this.d.get("g");
        final String s4 = this.d.get("b");
        if (s2 == "") {
            System.out.println("can't find red val for " + s);
        }
        else if (s3 == "") {
            System.out.println("can't find green val for " + s);
        }
        else if (s4 == "") {
            System.out.println("can't find blue val for " + s);
        }
        final Color color = new Color(Integer.parseInt(s2), Integer.parseInt(s3), Integer.parseInt(s4));
        final b b = new b(s, color, font, array, array2);
        this.long.a(s, color, font, array, array2);
    }
    
    public void a(Color color) {
        final String s = this.d.get("r");
        final String s2 = this.d.get("g");
        final String s3 = this.d.get("b");
        if (s == "") {
            System.out.println("can't find red val");
        }
        else if (s2 == "") {
            System.out.println("can't find green val");
        }
        else if (s3 == "") {
            System.out.println("can't find blue val");
        }
        if (this.e != null) {
            color = new Color(Integer.parseInt(s), Integer.parseInt(s2), Integer.parseInt(s3));
        }
        this.else = 0;
        this.d.clear();
    }
    
    public Color for() {
        final String s = this.d.get("r");
        final String s2 = this.d.get("g");
        final String s3 = this.d.get("b");
        if (s == "") {
            System.out.println("can't find red val");
        }
        else if (s2 == "") {
            System.out.println("can't find green val");
        }
        else if (s3 == "") {
            System.out.println("can't find blue val");
        }
        final Color color = new Color(Integer.parseInt(s), Integer.parseInt(s2), Integer.parseInt(s3));
        this.else = 0;
        this.d.clear();
        return color;
    }
    
    public void a(final JAVACharter.StyleManage.a a) {
        final String s = this.d.get("x");
        final String s2 = this.d.get("y");
        final String s3 = this.d.get("dx");
        final String s4 = this.d.get("dy");
        final String s5 = this.d.get("rightx");
        String s6 = this.d.get("font");
        final String s7 = this.d.get("textcolor");
        int int1 = a.e + 4;
        int int2 = a.d - 2;
        int int3 = 7;
        int int4 = 5;
        int int5 = a.char + a.e;
        Color color = new Color(0);
        try {
            if (s != null) {
                int1 = Integer.parseInt(s);
            }
            if (s2 != null) {
                int2 = Integer.parseInt(s2);
            }
            if (s3 != null) {
                int3 = Integer.parseInt(s3);
            }
            if (s4 != null) {
                int4 = Integer.parseInt(s4);
            }
            if (s5 != null) {
                int5 = Integer.parseInt(s5);
            }
            if (s6 == null) {
                s6 = "verysmallfont";
            }
            if (s7 != null) {
                color = new Color(Integer.parseInt(s7, 16));
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        a.a(int1, int2, int3, int4, int5, s6, color);
    }
    
    public void new() {
        final String s = this.d.get("r");
        final String s2 = this.d.get("g");
        final String s3 = this.d.get("b");
        final String lowerCase = this.d.get("type").toLowerCase();
        if (s == "") {
            System.out.println("can't find red val");
        }
        else if (s2 == "") {
            System.out.println("can't find green val");
        }
        else if (s3 == "") {
            System.out.println("can't find blue val");
        }
        this.do.a(lowerCase, new Color(Integer.parseInt(s), Integer.parseInt(s2), Integer.parseInt(s3)));
        this.else = 0;
        this.d.clear();
    }
    
    public void try() {
    }
    
    public void do() {
        final String s = this.d.get("id");
        final String s2 = this.d.get("style");
        final String s3 = this.d.get("DataX");
        final String s4 = this.d.get("DataY");
        final String s5 = this.d.get("DataHeight");
        final String s6 = this.d.get("DataWidth");
        final String s7 = this.d.get("ChartWidth");
        final String s8 = this.d.get("ChartHeight");
        final String s9 = this.d.get("XAxisX");
        final String s10 = this.d.get("XAxisY");
        final String s11 = this.d.get("YAxisX");
        final String s12 = this.d.get("YAxisY");
        final String k = this.d.get("image");
        final String s13 = this.d.get("hasxaxis");
        if (s == "") {
            System.out.println("can't find id val");
        }
        else if (s2 == "") {
            System.out.println("can't find style val");
        }
        else if (s3 == "") {
            System.out.println("can't find DataX val");
        }
        else if (s4 == "") {
            System.out.println("can't find DataY val");
        }
        else if (s5 == "") {
            System.out.println("can't find DataHeight val");
        }
        else if (s6 == "") {
            System.out.println("can't find DataWidth val");
        }
        final JAVACharter.StyleManage.a e = new JAVACharter.StyleManage.a(Integer.parseInt(s), s2, Integer.parseInt(s3), Integer.parseInt(s4), Integer.parseInt(s5), Integer.parseInt(s6));
        e.try = Integer.parseInt(s7);
        e.m = Integer.parseInt(s8);
        e.if = Integer.parseInt(s9);
        e.p = Integer.parseInt(s10);
        e.int = Integer.parseInt(s11);
        e.for = Integer.parseInt(s12);
        e.k = k;
        if (s13 != null && s13.equalsIgnoreCase("true")) {
            e.null = true;
        }
        this.e = e;
        this.h.addElement(this.e);
        ++this.new;
        this.else = 0;
        this.d.clear();
    }
    
    public void i() {
        final String else1 = this.d.get("type");
        final String o = this.d.get("dimension");
        final String s = this.d.get("zerobased");
        final String s2 = this.d.get("horizmouseline");
        if (else1 == "") {
            System.out.println("can't find type val");
        }
        else if (o == "") {
            System.out.println("can't find dimension val");
        }
        else if (s == "") {
            System.out.println("can't find zerobased val");
        }
        this.e.else = else1;
        this.e.o = o;
        this.e.do = Boolean.valueOf(s);
        if (s2 != null) {
            if (s2.equalsIgnoreCase("true")) {
                this.e.a(true);
            }
            else {
                this.e.a(false);
            }
        }
        this.else = 0;
        this.d.clear();
    }
    
    public void else() {
        final String s = this.d.get("shortdate");
        final String s2 = this.d.get("shortdatetime");
        this.long.for(s);
        this.long.if(s2);
    }
    
    public void do(final String s) {
    }
    
    public void if(final char[] array, final int n, final int n2) {
    }
    
    public void a(final char[] array, final int n, final int n2) {
    }
    
    public void if(final String s, final String s2) {
    }
    
    public void a(final String s, final String s2, final int n, final int n2) {
        throw new Error(s);
    }
}

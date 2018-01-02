import java.awt.Image;
import javax.swing.ToolTipManager;
import java.util.Locale;
import java.util.Vector;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aJ extends rp_bz implements rp_eP
{
    private rp_cR a;
    private rp_fK a;
    private rp_fb a;
    public rp_E a;
    private boolean n;
    private boolean o;
    public static int a;
    public static boolean a;
    private Color C;
    private Color D;
    public static String a;
    private static String h;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static int b;
    public static int c;
    public static int d;
    public static final Color a;
    public boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static String g;
    public static boolean l;
    public static boolean m;
    private static int g;
    public static int e;
    public static int f;
    public static Color b;
    public static Color c;
    public static Color d;
    public static Color e;
    public static Color f;
    public static Color g;
    public static Color h;
    public static Color i;
    public static Color j;
    public static Color k;
    public static Color l;
    public static Color m;
    public static Color n;
    public static Color o;
    public static Color p;
    public static Color q;
    public static Color r;
    public static Color s;
    public static Color t;
    public static Color u;
    public static Color v;
    public static Color w;
    public static final Font a;
    private static Color E;
    public static Color x;
    public static Color y;
    public static Color z;
    public static Color A;
    public static Color B;
    private HashMap a;
    
    public rp_aJ(final rp_fK a, final Vector vector) {
        super(a, vector);
        this.a = null;
        this.a = new rp_E();
        this.n = false;
        this.o = false;
        this.C = null;
        this.D = null;
        this.b = true;
        this.a = new HashMap();
        rp_C.a(2, "eep version: 5.60.36");
        this.a = a;
        rp_aJ.o = (this = this).a("c_rnd_bt", new Color(255, 204, 102));
        rp_aJ.p = this.a("c_rnd_bt_dep", new Color(102, 102, 51));
        rp_aJ.q = this.a("c_rnd_bt_border", new Color(0, 0, 0));
        rp_aJ.r = this.a("c_rnd_bt_txt", new Color(0, 0, 0));
        rp_aJ.E = this.a("c_tt_bgrd", new Color(255, 255, 204));
        rp_aJ.t = this.a("c_app_bgrd", new Color(204, 204, 204));
        rp_aJ.s = this.a("c_wall_mov", Color.green);
    }
    
    public final boolean a() {
        return this.n;
    }
    
    public final void a(final boolean n) {
        this.n = n;
    }
    
    public final boolean b() {
        return this.o;
    }
    
    public final void b(final boolean o) {
        this.o = o;
    }
    
    public final void a(final Color c, final Color d) {
        this.C = c;
        this.D = d;
    }
    
    public final Color a() {
        return this.C;
    }
    
    public final Color b() {
        return this.D;
    }
    
    public final void a() {
        this.a = new rp_cR(this.a("un_def"), true);
        this.b();
        rp_C.a(4, "Server path: " + rp_aJ.c);
        rp_C.a(4, "Server print page path: " + rp_aJ.d);
        if (this.a.a()) {
            rp_aJ.f = 48000;
            return;
        }
        rp_aJ.f = 50000;
    }
    
    public final void b() {
        rp_aJ.a = this.a("srv_sep", rp_aJ.a);
        rp_aJ.h = this.a("srv_psep", rp_aJ.h);
        rp_aJ.b = this.a("srv_p_data", rp_aJ.b);
        rp_aJ.d = (rp_aJ.c = rp_C.c(this.a("srv_p_servlet", rp_aJ.c)));
        rp_aJ.d = rp_C.c(this.a("srv_print_page", rp_aJ.d));
        rp_aJ.e = this.a("srv_gen_path", rp_aJ.e);
        rp_aJ.f = this.a("srv_sep_path", rp_aJ.f);
        rp_aJ.a = this.a("dbg", rp_aJ.a);
        rp_aJ.a = (this.a("profile", 0) == 1);
        rp_C.a(4, "Debug level: " + rp_aJ.a);
        rp_aJ.b = this.a("mg_dist");
        rp_aJ.c = this.a("mg_tol");
        rp_aJ.d = this.a("mg_etol");
        rp_aJ.b = this.a("c_plbg", Color.WHITE);
        rp_aJ.c = this.a("c_bgrd", new Color(255, 255, 204));
        rp_aJ.d = this.a("c_txt", Color.BLACK);
        rp_aJ.e = this.a("c_grid", new Color(224, 224, 224));
        this.a("c_sel", Color.BLUE);
        rp_aJ.f = this.a("c_rw_sel", Color.BLUE);
        rp_aJ.g = this.a("c_rw_nosel", rp_aJ.c);
        rp_aJ.h = this.a("c_rw_wall", Color.BLACK);
        rp_aJ.i = this.a("c_tab_sel", new Color(200, 221, 242));
        rp_aJ.j = this.a("c_hov_pen", new Color(51, 51, 102));
        rp_aJ.k = this.a("c_hov_fill", new Color(204, 204, 255));
    }
    
    final void c() {
        this.a.a(this.a.b("un_def", this.a.a));
        a(this.a.b("gr_tp", rp_au.a.b));
    }
    
    final void d() {
        this.a.a("un_def", this.a.a);
        this.a.a("gr_tp", rp_au.a.b);
    }
    
    public final rp_fK a() {
        return this.a;
    }
    
    public final rp_cR a() {
        return this.a;
    }
    
    public final rp_fb a() {
        return this.a;
    }
    
    public final void a(final rp_fb a) {
        this.a = a;
    }
    
    public final void a(final rp_eg rp_eg) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("unittype", "" + this.a.a));
        vector.addElement(rp_eg.a("keep_tape", "" + rp_C.a(rp_aJ.c)));
        vector.addElement(rp_eg.a("autoswitch_tape", "" + rp_C.a(rp_aJ.d)));
        rp_eg.a("options", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("language", Locale.getDefault().getLanguage()));
        vector.addElement(rp_eg.a("country", Locale.getDefault().getCountry()));
        rp_eg.a("locale", vector, "");
        vector.removeAllElements();
        vector.addElement(rp_eg.a("type", "" + rp_au.a.b));
        vector.addElement(rp_eg.a("size", "" + rp_au.a.a()));
        rp_eg.a("grid", vector);
        rp_eg.a("color", rp_aJ.e);
        rp_eg.a();
        final Vector<String> vector2;
        (vector2 = new Vector<String>()).addElement(rp_eg.a("grid", rp_C.a(rp_aJ.f)));
        vector2.addElement(rp_eg.a("dim", rp_C.a(rp_aJ.g)));
        vector2.addElement(rp_eg.a("portrait", rp_C.a(rp_aJ.h)));
        vector2.addElement(rp_eg.a("list", rp_C.a(rp_aJ.i)));
        vector2.addElement(rp_eg.a("sep-page", rp_C.a(rp_aJ.j)));
        vector2.addElement(rp_eg.a("infoontempl", rp_C.a(rp_aJ.k)));
        vector2.addElement(rp_eg.a("image-type", false ? "jpeg" : "png"));
        rp_eg.a("print", vector2);
        final Vector<String> vector3;
        (vector3 = new Vector<String>()).addElement(rp_eg.a("scale", rp_C.a(rp_aJ.l)));
        vector3.addElement(rp_eg.a("date", rp_C.a(rp_aJ.m)));
        rp_eg.a("header", vector3, rp_aJ.g);
        final Vector<String> vector4;
        (vector4 = new Vector<String>()).addElement(rp_eg.a("type", "" + rp_aJ.e));
        vector4.addElement(rp_eg.a("custom", "" + rp_aJ.f));
        rp_eg.a("scale", vector4, "");
        rp_eg.a();
        rp_eg.a();
    }
    
    public final int a() {
        return this.a(this.a.a() ? "mov_step_eng" : "mov_step_met");
    }
    
    public final int[] a(final boolean b, final int n) {
        final StringBuffer sb;
        (sb = new StringBuffer("rs")).append(b ? 'e' : 'm');
        sb.append('_');
        switch (n) {
            case 0: {
                sb.append('r');
                break;
            }
            case 1: {
                sb.append('l');
                break;
            }
            case 2: {
                sb.append('u');
                break;
            }
            case 3: {
                sb.append('t');
                break;
            }
        }
        return this.a(sb.toString());
    }
    
    public static void a(final int b) {
        final rp_fx a;
        if ((a = rp_au.a).b != b) {
            a.b = b;
            a.repaint();
        }
    }
    
    public static int b() {
        return rp_au.a.b;
    }
    
    public static void b(final int n) {
        final rp_fx a = rp_au.a;
        int c = n;
        final rp_fx rp_fx = a;
        if (c < 10000) {
            c = 10000;
        }
        if (c > 100000) {
            c = 100000;
        }
        if (rp_fx.c != c) {
            rp_fx.c = c;
            rp_fx.repaint();
        }
    }
    
    public static int c() {
        return rp_au.a.a();
    }
    
    public static String a(final String s) {
        final String string = "rgb(" + rp_aJ.E.getRed() + "," + rp_aJ.E.getGreen() + "," + rp_aJ.E.getBlue() + ")";
        return "<html><head><style type=\"text/css\">body{background-color:" + string + "}div{background-color:" + string + ";margin:8px}p{margin-bottom:5px}</style></head><div>" + s + "</div></html>";
    }
    
    public final String b(String s) {
        final rp_fb a = (this = this).a;
        s = s;
        return a(a.a(0, s));
    }
    
    public final void c(final boolean b) {
        this.b = b;
        ToolTipManager.sharedInstance().setEnabled(b);
    }
    
    public final float a() {
        return this.a("pstamp_quality", 70) / 100.0f;
    }
    
    public final void a(final String s, final Object o) {
        this.a.put(s, o);
    }
    
    public final Image a(final String s) {
        return this.a.get(s);
    }
    
    static {
        rp_aJ.a = 4;
        rp_aJ.a = false;
        rp_aJ.a = "/";
        rp_aJ.h = ":";
        rp_aJ.b = "" + rp_aJ.a;
        rp_aJ.c = null;
        rp_aJ.d = null;
        rp_aJ.e = "";
        rp_aJ.f = "sep4/";
        rp_aJ.b = 20000;
        rp_aJ.c = 1000;
        rp_aJ.d = 100;
        a = Color.black;
        rp_aJ.c = true;
        rp_aJ.d = true;
        rp_aJ.e = true;
        rp_aJ.f = true;
        rp_aJ.g = true;
        rp_aJ.h = true;
        rp_aJ.i = false;
        rp_aJ.j = false;
        rp_aJ.k = false;
        rp_aJ.g = "";
        rp_aJ.l = true;
        rp_aJ.m = false;
        rp_aJ.g = 0;
        rp_aJ.e = 2;
        rp_aJ.f = 50000;
        rp_aJ.l = Color.white;
        rp_aJ.m = new Color(218, 218, 218);
        rp_aJ.n = new Color(233, 233, 233);
        rp_aJ.u = new Color(102, 102, 102);
        rp_aJ.v = Color.WHITE;
        rp_aJ.w = new Color(193, 193, 193);
        a = new Font("Arial", 1, 10);
        new Font("SansSerif", 0, 12);
        rp_aJ.x = rp_aJ.t;
        rp_aJ.y = new Color(224, 224, 224);
        rp_aJ.z = Color.WHITE;
        rp_aJ.A = rp_aJ.u;
        rp_aJ.B = Color.BLACK;
    }
}

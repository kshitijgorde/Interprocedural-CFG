// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import com.eventim.common.transfer.saalplan.ReCaptchaDetails;
import com.eventim.common.transfer.saalplan.SaalplanPlatzkategorieDetails;
import com.eventim.applet.a.i;
import java.util.HashMap;
import java.util.Map;
import com.eventim.common.transfer.saalplan.SaalplanEventDetails;
import java.awt.Image;
import java.awt.Color;

public final class l
{
    private Color b;
    private boolean c;
    private Color d;
    private Color e;
    private Color f;
    private String g;
    private String h;
    private Image i;
    private String j;
    private String k;
    private int l;
    private String m;
    private String n;
    private String o;
    public SaalplanEventDetails a;
    private int p;
    private int q;
    private String r;
    private String s;
    private String t;
    private String u;
    private int v;
    private Color w;
    private Color x;
    private String y;
    private Color z;
    private Color A;
    private Color B;
    private int C;
    private k D;
    private boolean E;
    private Color F;
    private Color G;
    private Color H;
    private Color I;
    private int J;
    private int K;
    private EventimApplet L;
    private String M;
    private String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private Color T;
    private Color U;
    private boolean V;
    private Color W;
    private Color X;
    private Map Y;
    private int Z;
    private boolean aa;
    private String ab;
    private String ac;
    private Color ad;
    private int ae;
    
    public l(final EventimApplet l) {
        this.aa = true;
        this.h = "No ReCAPTCHA id specified.";
        this.g = "No ReCAPTCHA answer given.";
        this.j = "No ReCAPTCHA psig specified.";
        this.C = Integer.MAX_VALUE;
        this.ae = 10;
        this.Y = new HashMap();
        this.z = new Color(1, 45, 83);
        this.A = new Color(76, 107, 134);
        this.B = new Color(254, 241, 204);
        this.G = new Color(188, 198, 208);
        this.H = new Color(229, 232, 237);
        this.I = null;
        this.F = Color.white;
        this.W = this.G;
        this.X = null;
        this.w = new Color(164, 179, 192);
        this.x = this.G;
        this.T = new Color(251, 202, 58);
        this.U = new Color(253, 234, 178);
        this.e = null;
        this.f = Color.black;
        this.d = new Color(141, 160, 177);
        this.ad = Color.red;
        this.b = Color.white;
        this.L = l;
        EventimApplet.a("Version: 2.7.0.022 vom 15.01.2010", 2);
        this.t = this.L.getCodeBase().getHost();
        if (this.t.length() == 0) {
            this.t = "wwt1.eventim.de";
        }
        EventimApplet.a("Host: " + this.t, 2);
        this.M = this.a("servletstring", "");
        this.J = this.a("port", 80);
        this.v = this.a("imageport", 80);
        this.u = this.a("imagepfad", "img/applet/eventim/");
        if (this.u.length() > 0 && this.u.charAt(this.u.length() - 1) != '/') {
            this.u += "/";
            EventimApplet.a("Korrigierter ImagePfad: " + this.u, 1);
        }
        this.p = this.a("eventid", 0);
        this.K = this.a("promoid", 0);
        this.N = this.a("sessionid", "");
        this.o = this.b(this.a("errorurl", ""));
        EventimApplet.a("Korrigierte ErrorURL: " + this.o, 1);
        this.a("errortarget", "");
        this.s = this.b(this.a("forwardurl", ""));
        EventimApplet.a("Korrigierte ForwardURL: " + this.s, 1);
        this.r = this.a("forwardtarget", "");
        this.z = this.a(this.a("color1", ""), this.z);
        this.G = this.a(this.a("color2", ""), this.G);
        this.T = this.a(this.a("color3", ""), this.T);
        this.d = this.a(this.a("bgcolor", ""), this.d);
        this.F = this.a(this.a("ttbgcolor", ""), this.F);
        this.e = this.a(this.a("ttbocolor", ""), this.e);
        this.W = this.a(this.a("rowColor", ""), this.W);
        this.f = this.a(this.a("zoomTextColor", ""), this.f);
        this.z = this.a(this.a("mainPanelHeaderColour", ""), this.z);
        this.A = this.a(this.a("mainPanelHeaderFadeToColour", ""), this.A);
        this.B = this.a(this.a("mainPanelHeaderTextColour", ""), this.B);
        this.G = this.a(this.a("panelHeaderColour", ""), this.G);
        this.H = this.a(this.a("panelHeaderFadeToColour", ""), this.H);
        this.I = this.a(this.a("panelHeaderTextColour", ""), this.I);
        this.F = this.a(this.a("panelBackgroundColour", ""), this.F);
        this.W = this.a(this.a("tableHeaderColour", ""), this.W);
        this.X = this.a(this.a("tableHeaderTextColour", ""), this.X);
        this.w = this.a(this.a("inactivePanelHeaderColour", ""), this.w);
        this.x = this.a(this.a("inactivePanelHeaderFadeToColour", ""), this.x);
        this.T = this.a(this.a("signalColour", ""), this.T);
        this.U = this.a(this.a("signalFadeToColour", ""), this.U);
        this.e = this.a(this.a("buttonBackgroundColour", ""), this.e);
        this.f = this.a(this.a("buttonTextColour", ""), this.f);
        this.d = this.a(this.a("appletBackgroundColour", ""), this.d);
        this.ad = this.a(this.a("waitPanelTextColour", ""), this.ad);
        this.b = this.a(this.a("addToCartTextColour", ""), this.b);
        if (this.a("color2", (String)null) != null && this.a("inactivePanelHeaderFadeToColour", (String)null) == null) {
            this.w = this.a(this.a("color2", ""), this.w);
        }
        if ((this.a("mainPanelHeaderColour", (String)null) != null || this.a("color1", (String)null) != null) && this.a("mainPanelHeaderFadeToColour", (String)null) == null) {
            this.A = this.z;
        }
        if ((this.a("panelHeaderColour", (String)null) != null || this.a("color2", (String)null) != null) && this.a("panelHeaderFadeToColour", (String)null) == null) {
            this.H = this.G;
        }
        if (this.a("inactivePanelHeaderColour", (String)null) != null && this.a("inactivePanelHeaderFadeToColour", (String)null) == null) {
            this.x = this.w;
        }
        if (this.a("tableHeaderColour", (String)null) == null && this.a("rowColor", (String)null) == null) {
            this.W = this.G;
        }
        this.C = this.a("maxmenge", Integer.MAX_VALUE);
        this.ae = this.a("zumaxmenge", 10);
        this.ae = Math.max(2, this.ae);
        this.y = this.a("language", "de");
        this.D = new k(this.y, this.t + "/" + this.a("properties_path", "applet/properties/"), this.L.getDocumentBase().getProtocol());
        this.ac = this.a("venueort", this.D.a("ort"));
        this.ab = this.a("venuename", this.D.a("veranstaltungsst\u00e4tte"));
        this.k = this.a("currency", "EUR");
        this.n = this.a("diffShoppingCartIcon", "");
        this.m = this.a("diffDeleteIcon", "");
        this.a("diffKatContent", -1);
        this.a("locationWidth", -1);
        this.q = this.a("exclusiveDealId", -1);
        this.l = this.a("defaultDealId", -1);
        this.E = Boolean.valueOf(this.a("multiplePromotionCodes", "false"));
        if (this.E) {
            EventimApplet.a("Multiple promotions codes permitted.", 1);
        }
        else {
            EventimApplet.a("Single promotion code permitted.", 1);
        }
        this.P = Boolean.valueOf(this.a("showAllPromotions", "true"));
        if (this.P) {
            EventimApplet.a("Show all promotions enabled.", 1);
        }
        else {
            EventimApplet.a("Show all promotions disabled.", 1);
        }
        this.O = Boolean.valueOf(this.a("showAllPriceCategories", "true"));
        if (this.O) {
            EventimApplet.a("Show all price categories enabled.", 1);
        }
        else {
            EventimApplet.a("Show all price catgeories disabled.", 1);
        }
        this.R = Boolean.valueOf(this.a("showPriceCategoryColumn", "false"));
        if (this.R) {
            EventimApplet.a("Show price category column enabled.", 1);
        }
        else {
            EventimApplet.a("Show price category column disabled.", 1);
        }
        this.Q = Boolean.valueOf(this.a("showCurrencySymbol", "true"));
        if (this.Q) {
            EventimApplet.a("Showing currency symbol.", 1);
        }
        else {
            EventimApplet.a("Showing currency 3-character code.", 1);
        }
        this.V = Boolean.valueOf(this.a("sortPriceCategoriesByPrice", "true"));
        if (this.V) {
            EventimApplet.a("Showing price categories sorted by price.", 1);
        }
        this.c = Boolean.valueOf(this.a("alwaysShowTotalPriceCategoryAvailability", "false"));
        if (this.c) {
            EventimApplet.a("Price category will always show the total price category availability.", 1);
        }
        if (!(this.S = Boolean.valueOf(this.a("showRemainingPlacesCounts", "true")))) {
            EventimApplet.a("Hiding remaining places counts.", 1);
        }
        com.eventim.applet.a.i.a(this);
    }
    
    private String b(final String s) {
        final String lowerCase;
        if ((lowerCase = s.toLowerCase()).startsWith("http://") || lowerCase.startsWith("https://")) {
            return s;
        }
        if (s.startsWith("/")) {
            return this.L.getDocumentBase().getProtocol() + "://" + this.t + s;
        }
        return this.L.getDocumentBase().getProtocol() + "://" + this.t + "/" + s;
    }
    
    public final void a() {
        new HashMap();
        for (int i = 0; i < this.a.tdlSeatplanDetails.platzkategorieDetails.length; ++i) {
            final SaalplanPlatzkategorieDetails saalplanPlatzkategorieDetails = this.a.tdlSeatplanDetails.platzkategorieDetails[i];
            this.Y.put(new Long(saalplanPlatzkategorieDetails.getTdlPreisklasseId()), new Long(saalplanPlatzkategorieDetails.getId()));
        }
    }
    
    public final Color b() {
        return this.b;
    }
    
    public final boolean c() {
        return this.c;
    }
    
    public final Color d() {
        return this.d;
    }
    
    public final String e() {
        return this.g;
    }
    
    public final String f() {
        return this.h;
    }
    
    public final Image g() {
        return this.i;
    }
    
    public final String h() {
        return this.j;
    }
    
    public final String i() {
        return this.k;
    }
    
    public final int j() {
        return this.l;
    }
    
    public final String k() {
        return this.m;
    }
    
    public final String l() {
        return this.n;
    }
    
    public final int m() {
        return this.p;
    }
    
    public final int n() {
        return this.q;
    }
    
    public final String o() {
        return this.r;
    }
    
    public final String p() {
        return this.s;
    }
    
    public final String q() {
        return this.t;
    }
    
    public final String r() {
        return this.u;
    }
    
    public final int s() {
        return this.v;
    }
    
    public final Color t() {
        return this.w;
    }
    
    public final Color u() {
        return this.x;
    }
    
    private int a(final String s, int int1) {
        try {
            final String parameter;
            if ((parameter = this.L.getParameter(s)) != null && parameter.length() > 0) {
                int1 = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {
            EventimApplet.a("Exception in getIntParamValue(" + s + "): " + ex.toString(), 4);
        }
        EventimApplet.a(s + " = " + int1, 2);
        return int1;
    }
    
    public final String v() {
        return this.y;
    }
    
    public final Color w() {
        return this.z;
    }
    
    public final Color x() {
        return this.A;
    }
    
    public final Color y() {
        return this.B;
    }
    
    public final int z() {
        return this.C;
    }
    
    public final k A() {
        return this.D;
    }
    
    public final Color B() {
        return this.F;
    }
    
    public final Color C() {
        return this.G;
    }
    
    public final Color D() {
        return this.H;
    }
    
    public final int E() {
        return this.J;
    }
    
    public final int F() {
        return this.K;
    }
    
    public final String G() {
        return this.M;
    }
    
    public final String H() {
        return this.N;
    }
    
    public final boolean I() {
        return this.Q;
    }
    
    public final boolean J() {
        return this.S;
    }
    
    public final Color K() {
        return this.T;
    }
    
    public final Color L() {
        return this.U;
    }
    
    public final boolean M() {
        return this.V;
    }
    
    private String a(final String s, String s2) {
        try {
            final String parameter;
            if ((parameter = this.L.getParameter(s)) != null && parameter.length() > 0) {
                s2 = parameter;
            }
        }
        catch (Exception ex) {
            EventimApplet.a("Exception in getStringParamValue(" + s + "): " + ex.toString(), 4);
        }
        EventimApplet.a(s + " = " + s2, 2);
        return s2;
    }
    
    public final int N() {
        return this.Z;
    }
    
    public final boolean O() {
        return this.aa;
    }
    
    public final String P() {
        return this.ab;
    }
    
    public final String Q() {
        return this.ac;
    }
    
    public final Color R() {
        return this.ad;
    }
    
    public final Long a(final Long n) {
        return this.Y.get(n);
    }
    
    public final int S() {
        return this.ae;
    }
    
    public final boolean T() {
        return a(this.G);
    }
    
    public static boolean a(final Color color) {
        return color.getRed() < 127 && color.getGreen() < 127 && color.getBlue() < 127;
    }
    
    private Color a(final String s, final Color color) {
        final int c;
        if ((c = c(s)) >= 0) {
            return new Color(c);
        }
        return color;
    }
    
    private static int c(final String s) {
        if (s != null && s.length() > 0) {
            try {
                if (s.charAt(0) == '#') {
                    return Integer.parseInt(s.substring(1), 16);
                }
                return Integer.parseInt(s);
            }
            catch (Exception ex) {
                EventimApplet.a("Exception in setBgColor(" + s + "): " + ex.toString(), 4);
            }
        }
        return -1;
    }
    
    public final void a(final String g) {
        this.g = g;
    }
    
    public final void a(final int c) {
        this.C = c;
    }
    
    public final void a(final ReCaptchaDetails reCaptchaDetails) {
        if (reCaptchaDetails != null) {
            if (reCaptchaDetails.getChallengeId() != null) {
                this.h = reCaptchaDetails.getChallengeId();
            }
            if (reCaptchaDetails.getChallengeImage() != null) {
                this.i = reCaptchaDetails.getChallengeImage();
            }
            if (reCaptchaDetails.getChallengeImage() != null) {
                this.j = reCaptchaDetails.getPsig();
            }
        }
    }
    
    public final void b(final int z) {
        this.Z = z;
    }
    
    public final void a(final boolean aa) {
        this.aa = aa;
    }
    
    public final void c(final int ae) {
        this.ae = ae;
    }
}

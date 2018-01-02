// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.util.e;
import java.awt.Point;
import java.util.EventObject;

public class a3 extends EventObject
{
    private int a;
    private boolean b;
    private int c;
    private int d;
    private String e;
    private boolean f;
    private boolean g;
    private Point h;
    private double i;
    private int j;
    private Object k;
    private int l;
    private int m;
    private int n;
    private double o;
    private boolean p;
    private int q;
    private int r;
    private boolean s;
    
    private final String c(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "TRANSFORM";
                break;
            }
            case 2: {
                s = "UNDO";
                break;
            }
            case 3: {
                s = "PRINT_PAGE";
                break;
            }
            case 4: {
                s = "PRINT_VISIBLE";
                break;
            }
            case 5: {
                s = "OPENED";
                break;
            }
            case 6: {
                s = "SAVED";
                break;
            }
            case 7: {
                s = "CLICK";
                break;
            }
            case 8: {
                s = "DBLCLICK";
                break;
            }
            case 9: {
                s = "PAGE";
                break;
            }
            case 10: {
                s = "TIMEOUT";
                break;
            }
            case 11: {
                s = "CUSTOM";
                break;
            }
            case 12: {
                s = "SELECT";
                break;
            }
            case 13: {
                s = "UNSELECT";
                break;
            }
            case 14: {
                s = "MOUSE_DOWN";
                break;
            }
            case 15: {
                s = "MOUSE_UP";
                break;
            }
            case 16: {
                s = "NEXT_DOC";
                break;
            }
            case 17: {
                s = "PREV_DOC";
                break;
            }
            case 18: {
                s = "FIRST_DOC";
                break;
            }
            case 19: {
                s = "LAST_DOC";
                break;
            }
            case 20: {
                s = "SET_DOC";
                break;
            }
            case 21: {
                s = "END_TAB";
                break;
            }
            case 22: {
                s = "READY";
                break;
            }
            case 31: {
                s = "DOCUMENT_SAVE_FAILED";
                break;
            }
            case 34: {
                s = "PAGE_RENDERED";
                break;
            }
            case 35: {
                s = "AREA_SELECTED";
                break;
            }
            case 36: {
                s = "SIGNATUREPICKER";
                break;
            }
            case 37: {
                s = "KEYPRESS";
                break;
            }
            case 38: {
                s = "FULL_PAGE_RENDERED";
                break;
            }
            case 39: {
                s = "ALL_FULL_PAGES_RENDERED";
                break;
            }
            case 40: {
                s = "SHOW_TASK_LIST";
                break;
            }
            case 23: {
                s = "ANNOTATION";
                break;
            }
            case 24: {
                s = "ANNOTATIONS_SAVED_OK";
                break;
            }
            case 25: {
                s = "ANNOTATIONS_SAVED_FAILED";
                break;
            }
            case 26: {
                s = "PRINT_CANCELLED";
                break;
            }
            case 28: {
                s = "ANNOTATION_SELECTED";
                break;
            }
            case 29: {
                s = "ANNOTATION_DESELECTED";
                break;
            }
            case 30: {
                s = "ANNOTATION_CREATED";
                break;
            }
            case 32: {
                s = "ANNOTATION_UPDATED";
                break;
            }
            case 33: {
                s = "ANNOTATION_RESTORED";
                break;
            }
            case 41: {
                s = "KEEP_ALIVE";
                break;
            }
            case 42: {
                s = "SAVE_DOC";
                break;
            }
            case 43: {
                s = "CACHE_ACCESS_DENIED";
                break;
            }
            case 44: {
                s = "GET_ALTERNATIVE_URL";
                break;
            }
            case 45: {
                s = "GET_ALTERNATIVE_URL_ON_ERROR";
                break;
            }
            case 46: {
                s = "MAGNIFIER";
                break;
            }
            case 47: {
                s = "CANCELLED_SAVE";
                break;
            }
            case 48: {
                s = "ANNOTATIONS_BURNT";
                break;
            }
            case 49: {
                s = "LINK_COMMAND";
                break;
            }
            case 51: {
                s = "ANNOTATIONS_BURN_CANCELLED";
                break;
            }
            case 52: {
                s = "ANNOTATIONS_BURN_FAILED";
                break;
            }
            case 53: {
                s = "PRE_RENDER";
                break;
            }
            case 54: {
                s = "DOCUMENT_PRE_PROCESSING";
                break;
            }
            case 55: {
                s = "FILTER_TYPE_USED";
                break;
            }
            case 56: {
                s = "FILTER_TYPE_ATTEMPT_FAILED";
                break;
            }
            default: {
                s = "Unknown";
                break;
            }
        }
        return s;
    }
    
    public a3(final Object o, final int a, final int d, final String e, final int c) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.d = d;
        this.e = e;
        this.c = c;
    }
    
    public a3(final Object o, final int a, final int l, final int m, final int n) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.m = m;
        this.n = n;
    }
    
    public a3(final Object o, final int a, final int l, final boolean p5, final boolean s) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.p = p5;
        this.s = s;
    }
    
    public a3(final Object o, final int a, final int l, final int m, final int n, final int q, final int r) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.m = m;
        this.n = n;
        this.q = q;
        this.r = r;
    }
    
    public a3(final Object o, final int a, final int l) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
    }
    
    public a3(final Object o, final int a, final int l, final double o2) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.o = o2;
    }
    
    public a3(final Object o, final int a, final int l, final int m) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.m = m;
    }
    
    public a3(final Object o, final int a, final int l, final int m, final boolean p5) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.m = m;
        this.p = p5;
    }
    
    public a3(final Object o, final int a, final int l, final boolean p4) {
        super(o);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = new Point(0, 0);
        this.i = 0.0;
        this.j = 0;
        this.k = "none";
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.a = a;
        this.l = l;
        this.p = p4;
    }
    
    public boolean a() {
        return this.g;
    }
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public int b() {
        return this.l;
    }
    
    public int c() {
        return this.m;
    }
    
    public int d() {
        return this.n;
    }
    
    public double e() {
        return this.o;
    }
    
    public boolean f() {
        return this.p;
    }
    
    public int g() {
        return this.q;
    }
    
    public int h() {
        return this.r;
    }
    
    public boolean i() {
        return this.s;
    }
    
    public int j() {
        return this.a;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public int k() {
        return this.d;
    }
    
    public String l() {
        return this.e;
    }
    
    public void a(final String e) {
        this.e = e;
    }
    
    public int m() {
        return this.c;
    }
    
    public int n() {
        return this.h.x;
    }
    
    public int o() {
        return this.h.y;
    }
    
    public void a(final double i) {
        this.i = i;
    }
    
    public double p() {
        return this.i;
    }
    
    public void b(final boolean b) {
        this.b = b;
    }
    
    public boolean q() {
        return this.b;
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public int r() {
        return this.j;
    }
    
    public void a(final Point point) {
        this.h.x = point.x;
        this.h.y = point.y;
    }
    
    public String s() {
        return ji.util.e.a(this.getSource());
    }
    
    public void a(final Object k) {
        this.k = k;
    }
    
    public Object t() {
        try {
            return this.k.toString();
        }
        catch (Exception ex) {
            return "empty";
        }
    }
    
    public boolean u() {
        return this.f;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiJavaScriptEvent ").append(this.a).append("/").append(this.c(this.a)).append("/").append(this.e).append("/").append(this.c).append("/").append(this.f).append("/").append(this.s()).append("/").append(this.getSource())));
    }
}

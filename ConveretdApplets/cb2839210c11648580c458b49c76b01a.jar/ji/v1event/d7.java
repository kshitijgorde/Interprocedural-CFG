// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.Vector;
import java.awt.Rectangle;
import ji.awt.da;
import java.awt.Point;
import java.util.EventObject;

public class d7 extends EventObject
{
    private int a;
    private int b;
    private String c;
    private Object d;
    private boolean e;
    private boolean f;
    private boolean g;
    private Point h;
    private da i;
    private Rectangle j;
    private int k;
    private int l;
    
    public String toString() {
        if (this.c == null) {
            return String.valueOf(String.valueOf(new StringBuffer("jiDisplayEvent(").append(this.a).append(":").append(this.a(this.a)).append("/").append(this.b).append(", ").append(this.getSource()).append(")")));
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiDisplayEvent(").append(this.a).append(":").append(this.a(this.a)).append("/").append(this.c).append(", ").append(this.getSource()).append(")")));
    }
    
    private String a(final int n) {
        switch (n) {
            case 1: {
                return "PARTIAL";
            }
            case 2: {
                return "READY";
            }
            case 3: {
                return "COMPLETE";
            }
            case 4: {
                return "END_SELECT";
            }
            case 5: {
                return "ENHANCE";
            }
            case 6: {
                return "TRIAL";
            }
            case 7: {
                return "TRIAL_EXIT";
            }
            case 8: {
                return "THUMBS_DISPAYED";
            }
            case 9: {
                return "MAGNIFY_CLOSED";
            }
            case 10: {
                return "UPDATE";
            }
            case 11: {
                return "NOTBUSY";
            }
            case 12: {
                return "BUSY";
            }
            case 13: {
                return "PRINTING";
            }
            case 14: {
                return "TRANSFORM";
            }
            case 15: {
                return "UNDO";
            }
            case 16: {
                return "DRAW_COMPLETE";
            }
            case 17: {
                return "CODE_ACTIVATED";
            }
            case 18: {
                return "ANNOTATION_ACTIVATED";
            }
            case 19: {
                return "ANNOTATION_STARTED";
            }
            case 20: {
                return "ANNOTATION_ENDED";
            }
            case 21: {
                return "ANNOTATION_SAVED";
            }
            case 22: {
                return "ANNOTATION_DEACTIVATED";
            }
            case 23: {
                return "ANNOTATION_UPDATED";
            }
            case 24: {
                return "BUTTON_STATE_CHANGE";
            }
            case 25: {
                return "ANNOTATIONS_NEED_SAVING";
            }
            case 26: {
                return "ANNOTATION_JAVASCRIPT";
            }
            case 27: {
                return "OPEN_WEB";
            }
            case 28: {
                return "UPDATE_THUMBNAIL";
            }
            case 72: {
                return "UPDATE_THUMBNAILS";
            }
            case 39: {
                return "UPDATE_SCALE";
            }
            case 40: {
                return "ENHANCESTATE";
            }
            case 42: {
                return "RESTART_ANNOTATION";
            }
            case 47: {
                return "VALIDATE_CONTROLS";
            }
            case 48: {
                return "THUMBS_REDRAWN";
            }
            case 49: {
                return "OPENING_OK";
            }
            case 50: {
                return "DRAW_IMAGE_COMPLETE";
            }
            case 51: {
                return "ANNOTATION_SIGNATURE";
            }
            case 52: {
                return "PIXEL_DEPTH_CLICKED";
            }
            case 53: {
                return "RESOLUTION_CLICKED";
            }
            case 54: {
                return "IH_PAINT_COMPONENT";
            }
            case 55: {
                return "IH_PAINT1";
            }
            case 56: {
                return "IH_PAINT2";
            }
            case 57: {
                return "ST_PAINT_COMPONENT";
            }
            case 58: {
                return "ST_PAINT2";
            }
            case 59: {
                return "THUMBS_PAINT1";
            }
            case 60: {
                return "THUMBS_PAINT2";
            }
            case 61: {
                return "THUMBS_PAINT3";
            }
            case 62: {
                return "THUMBS_REPOSITION";
            }
            case 63: {
                return "DOC_LOADED_NOT_VIS";
            }
            case 64: {
                return "KEY_EVENT";
            }
            case 65: {
                return "ADJUSTMENT_ENABLED";
            }
            case 66: {
                return "LAST_THUMBS_DISPAYED";
            }
            case 68: {
                return "MAGNIFY_NOW_CLOSED";
            }
            case 69: {
                return "ENHANCE_CLICKED";
            }
            case 70: {
                return "DRAW_ACTUATED";
            }
            case 71: {
                return "SAVE_DIB";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    public d7(final Object o, final int a, final int b) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.b = b;
    }
    
    public d7(final Object o, final int a, final int k, final int l) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.k = k;
        this.l = l;
    }
    
    public d7(final Object o, final int a, final boolean g) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.g = g;
    }
    
    public d7(final Object o, final int a, final String c) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.c = c;
    }
    
    public d7(final Object o, final int a, final String c, final Object d) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.c = c;
        this.d = d;
    }
    
    public d7(final Object o, final int a, final Point h) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new da(0L, 0L, 0L, 0L);
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.a = a;
        this.h = h;
    }
    
    public final void a(final Rectangle j) {
        this.j = j;
    }
    
    public final Rectangle a() {
        return this.j;
    }
    
    public final Object b() {
        return this.d;
    }
    
    public final void a(final boolean f) {
        this.f = f;
    }
    
    public final boolean c() {
        return this.f;
    }
    
    public int d() {
        return this.a;
    }
    
    public da e() {
        return this.i;
    }
    
    public boolean f() {
        return this.g;
    }
    
    public String g() {
        return this.c;
    }
    
    public Point h() {
        return this.h;
    }
    
    public int i() {
        return this.b;
    }
    
    public int j() {
        return this.k;
    }
    
    public int k() {
        return this.l;
    }
    
    public boolean l() {
        return this.e;
    }
    
    public final boolean m() {
        boolean b = false;
        switch (this.a) {
            case 11:
            case 12:
            case 19:
            case 21:
            case 23:
            case 25:
            case 42: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public void a(final Object d) {
        this.d = d;
    }
    
    public v7 a(final Vector vector, final boolean b) {
        if (vector != null) {
            final Vector vector2 = new Vector<Object>(vector.size());
            for (int i = 0; i < vector.size(); ++i) {
                vector2.addElement(vector.elementAt(i));
            }
            return new v7(vector2, b);
        }
        return null;
    }
    
    public final class v7
    {
        private boolean a;
        private Vector b;
        
        public v7(final d7 d7, final Vector b, final boolean a) {
            this.b = b;
            this.a = a;
        }
        
        public boolean a() {
            return this.a;
        }
        
        public Vector b() {
            return this.b;
        }
    }
}

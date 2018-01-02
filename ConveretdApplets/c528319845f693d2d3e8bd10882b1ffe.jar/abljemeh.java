import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.awt.Event;
import java.awt.Component;
import java.awt.AWTEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemeh
{
    private static boolean a;
    protected int b;
    protected String c;
    protected String d;
    private static final abljemeh e;
    private static final abljemeh f;
    private static final abljemeh g;
    private static final abljemeh h;
    private static final abljemeh i;
    private static final abljemeh j;
    private static final abljemeh k;
    private static final abljemeh l;
    private static final abljemeh m;
    private static final abljemeh n;
    private static final abljemeh o;
    private static final abljemeh[] p;
    private static final abljemeh[] q;
    private static final abljemeh[] r;
    private static final abljemeh[] s;
    private static final abljemeh[] t;
    private static final abljemeh[] u;
    private static final abljemeh[] v;
    private static final abljemeh[] w;
    private static final abljemeh[] x;
    private static final String[] y;
    private static abljemeh[] z;
    private static String aa;
    private static String ab;
    
    public static boolean a() {
        return abljemeh.a;
    }
    
    public static long b() {
        return 252L;
    }
    
    public static void a(final AWTEvent awtEvent) {
        Event event = null;
        Component component;
        try {
            component = (Component)awtEvent.getSource();
        }
        catch (Throwable t) {
            return;
        }
        switch (awtEvent.getID()) {
            case 400: {
                event = a(component, awtEvent);
                break;
            }
            case 401: {
                event = b(component, awtEvent, 403);
                break;
            }
            case 402: {
                event = b(component, awtEvent, 404);
                break;
            }
            case 1004: {
                event = new Event(component, 1004, null);
                break;
            }
            case 1005: {
                event = new Event(component, 1005, null);
                break;
            }
            case 504: {
                event = a(component, awtEvent, 504);
                break;
            }
            case 503: {
                event = a(component, awtEvent, 503);
                break;
            }
            case 506: {
                event = a(component, awtEvent, 506);
                break;
            }
            case 501: {
                event = a(component, awtEvent, 501);
                break;
            }
            case 502: {
                event = a(component, awtEvent, 502);
                break;
            }
            case 1001: {
                event = new Event(component, 1001, null);
                break;
            }
            case 201: {
                event = new Event(component, 201, null);
                break;
            }
        }
        while (event != null) {
            final Event evt = event.evt;
            event.evt = null;
            if (b(event) && awtEvent instanceof InputEvent) {
                ((InputEvent)awtEvent).consume();
            }
            event = evt;
        }
    }
    
    private static Event a(final Component component, final AWTEvent awtEvent, final int n) {
        final MouseEvent mouseEvent = (MouseEvent)awtEvent;
        final Event event = new Event(component, n, null);
        event.x = mouseEvent.getX();
        event.y = mouseEvent.getY();
        event.clickCount = mouseEvent.getClickCount();
        event.modifiers = (mouseEvent.getModifiers() & 0xFFFFFFEF);
        return event;
    }
    
    private static Event a(final Component component, final AWTEvent awtEvent) {
        final KeyEvent keyEvent = (KeyEvent)awtEvent;
        final char keyChar = keyEvent.getKeyChar();
        final int n = keyEvent.getModifiers() & 0xFFFFFFDF;
        final Event a = a(component, 401, keyChar, n);
        a.evt = a(component, 402, keyChar, n);
        return a;
    }
    
    private static Event b(final Component component, final AWTEvent awtEvent, final int n) {
        final KeyEvent keyEvent = (KeyEvent)awtEvent;
        final int keyCode = keyEvent.getKeyCode();
        int n2 = 0;
        int modifiers = keyEvent.getModifiers();
        if (keyCode >= 112 && keyCode <= 123) {
            n2 = keyCode + 896;
        }
        else if (keyCode >= 61440 && keyCode <= 61441) {
            n2 = keyCode - 60432;
            modifiers |= 0x1;
        }
        else {
            switch (keyCode) {
                case 155: {
                    n2 = 1025;
                    break;
                }
                case 36: {
                    n2 = 1000;
                    break;
                }
                case 35: {
                    n2 = 1001;
                    break;
                }
                case 33: {
                    n2 = 1002;
                    break;
                }
                case 34: {
                    n2 = 1003;
                    break;
                }
                case 38:
                case 224: {
                    n2 = 1004;
                    break;
                }
                case 40:
                case 225: {
                    n2 = 1005;
                    break;
                }
                case 37:
                case 226: {
                    n2 = 1006;
                    break;
                }
                case 39:
                case 227: {
                    n2 = 1007;
                    break;
                }
            }
        }
        if (n2 == 0) {
            return null;
        }
        return a(component, n, n2, modifiers);
    }
    
    private static Event a(final Component component, final int n, final int key, final int modifiers) {
        final Event event = new Event(component, n, null);
        event.key = key;
        event.modifiers = modifiers;
        return event;
    }
    
    private static boolean b(final Event event) {
        if (event == null) {
            return false;
        }
        for (Component parent = (Component)event.target; parent != null; parent = parent.getParent()) {
            if (parent.handleEvent(event)) {
                return true;
            }
        }
        return false;
    }
    
    public abljemeh(final String s) {
        this(s.charAt(0), s.substring(1, (s.length() - 1) / 2 + 1), s.substring((s.length() - 1) / 2 + 1));
    }
    
    public abljemeh(final int b, final String s, final String s2) {
        this.b = b;
        this.c = String.valueOf(s) + " " + (char)b;
        this.d = String.valueOf(s2) + (char)b + (char)b;
    }
    
    private static void a(final abljem abljem, final String s) {
        if (s.equalsIgnoreCase("None")) {
            return;
        }
        if (s.equalsIgnoreCase("Custom")) {
            final Vector vector = new Vector<abljemeh>();
            int n = 1;
            while (true) {
                final String string = "deadkey" + n;
                final String a = abljem.a(string, "");
                if (a == "") {
                    break;
                }
                if (a.length() < 3 || (a.length() & 0x1) != 0x1) {
                    abljem.d("Custom deadkey malformed, ignored: " + string + "=" + a);
                }
                else {
                    abljem.d("Custom deadkey definition loaded: " + a);
                    vector.addElement(new abljemeh(a));
                }
                ++n;
            }
            abljemeh.z = new abljemeh[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                abljemeh.z[i] = vector.elementAt(i);
            }
        }
        else if (s.equalsIgnoreCase("French")) {
            abljemeh.z = abljemeh.q;
        }
        else if (s.equalsIgnoreCase("German")) {
            abljemeh.z = abljemeh.p;
        }
        else if (s.equalsIgnoreCase("US International")) {
            abljemeh.z = abljemeh.t;
        }
        else if (s.equalsIgnoreCase("Swiss French") || s.equalsIgnoreCase("Portuguese (Brazilian ABNT)") || s.equalsIgnoreCase("Portuguese") || s.equalsIgnoreCase("Swedish") || s.equalsIgnoreCase("Norwegian") || s.equalsIgnoreCase("Finnish") || s.equalsIgnoreCase("Danish") || s.equalsIgnoreCase("Belgian French") || s.equalsIgnoreCase("Spanish") || s.equalsIgnoreCase("Swiss German")) {
            abljemeh.z = abljemeh.x;
        }
        else if (s.equalsIgnoreCase("Canadian French")) {
            abljemeh.z = abljemeh.u;
        }
        else if (s.equalsIgnoreCase("Canadian French (Legacy)")) {
            abljemeh.z = abljemeh.v;
        }
        else if (s.equalsIgnoreCase("Canadian Multilingual Standard")) {
            abljemeh.z = abljemeh.w;
        }
        else if (s.equalsIgnoreCase("Dutch")) {
            abljemeh.z = abljemeh.s;
        }
        else {
            if (!s.equalsIgnoreCase("Icelandic")) {
                abljem.d("No deadkey logic set due unsupported keyboard name: " + s);
                abljem.d("Suported names are:");
                for (int j = 0; j < abljemeh.y.length; ++j) {
                    abljem.d("  " + abljemeh.y[j]);
                }
                return;
            }
            abljemeh.z = abljemeh.r;
        }
        abljem.d("Deadkey logic for " + s + " keyboard activated");
    }
    
    public static Event a(final Event event) {
        if (abljemeh.z == null) {
            return event;
        }
        for (int i = 0; i < abljemeh.z.length; ++i) {
            final abljemeh abljemeh = abljemeh.z[i];
            if (event.key == abljemeh.b) {
                abljemeh.aa = abljemeh.c;
                abljemeh.ab = abljemeh.d;
                return null;
            }
            if (abljemeh.aa != null) {
                final int index = abljemeh.aa.indexOf((char)event.key);
                if (index >= 0) {
                    event.key = abljemeh.ab.charAt(index);
                }
                abljemeh.aa = null;
                return event;
            }
        }
        return event;
    }
    
    public static void a(final abljem abljem) {
        if (System.getProperty("java.vendor").startsWith("Microsoft")) {
            abljemeh.a = false;
        }
        else {
            String a = "None";
            abljemeh.a = true;
            final String a2 = abljem.a("event_handling", "904");
            if (a2.equalsIgnoreCase("902")) {
                abljemeh.a = false;
                a = "None";
            }
            else if (!a2.equalsIgnoreCase("904")) {
                if (a2.equalsIgnoreCase("904FR")) {
                    abljemeh.a = false;
                    a = "French";
                }
                else if (a2.equalsIgnoreCase("904TAG")) {
                    abljemeh.a = false;
                    a = abljem.a("deadkey_support", "None");
                }
                else {
                    abljem.d("Using 904 event handling, invalid event_handling parameter ignored: " + a2);
                }
            }
            a(abljem, a);
        }
    }
    
    static {
        abljemeh.a = true;
        e = new abljemeh(180, "aAeEiIoOuUyY", "\u00e1\u00c1\u00e9\u00c9\u00ed\u00cd\u00f3\u00d3\u00fa\u00da\u00fd\u00dd");
        f = new abljemeh(96, "aAeEiIoOuU", "\u00e0\u00c0\u00e8\u00c8\u00ec\u00cc\u00f2\u00d2\u00f9\u00d9");
        g = new abljemeh(168, "aAeEiIoOuUy", "\u00e4\u00c4\u00eb\u00cb\u00ef\u00cf\u00f6\u00d6\u00fc\u00dc\u00ff");
        h = new abljemeh(184, "cC", "\u00e7\u00c7");
        i = new abljemeh(126, "aAnNoO", "\u00e3\u00c3\u00f1\u00d1\u00f5\u00d5");
        j = new abljemeh(94, "aAeEiIoOuU", "\u00e2\u00c2\u00ea\u00ca\u00ee\u00ce\u00f4\u00d4\u00fb\u00db");
        k = new abljemeh(39, "aAeEiIoOuUyY", "\u00e1\u00c1\u00e9\u00c9\u00ed\u00cd\u00f3\u00d3\u00fa\u00da\u00fd\u00dd");
        l = new abljemeh(34, "aAeEiIoOuUy", "\u00e4\u00c4\u00eb\u00cb\u00ef\u00cf\u00f6\u00d6\u00fc\u00dc\u00ff");
        m = new abljemeh(94, "aAcCeEgGhHiIjJoOsSuUwWyY", "\u00e2\u00c2\u0109\u0108\u00ea\u00ca\u011d\u011c\u0125\u0124\u00ee\u00ce\u0135\u0134\u00f4\u00d4\u015d\u015c\u00fb\u00db\u0175\u0174\u0177\u0176");
        n = new abljemeh(126, "aAiInNoOuU", "\u00e3\u00c3\u0129\u0128\u00f1\u00d1\u00f5\u00d5\u0169\u0168");
        o = new abljemeh(168, "aAeEiIoOuUyY", "\u00e4\u00c4\u00eb\u00cb\u00ef\u00cf\u00f6\u00d6\u00fc\u00dc\u00ff\u0178");
        p = new abljemeh[] { abljemeh.e, abljemeh.j, abljemeh.f };
        q = new abljemeh[] { abljemeh.g, abljemeh.j, abljemeh.f, abljemeh.i };
        r = new abljemeh[] { abljemeh.e, abljemeh.j, abljemeh.f, abljemeh.g };
        s = new abljemeh[] { abljemeh.e, abljemeh.j, abljemeh.f, abljemeh.g, abljemeh.i, abljemeh.h };
        t = new abljemeh[] { abljemeh.k, abljemeh.j, abljemeh.f, abljemeh.l, abljemeh.i };
        u = new abljemeh[] { abljemeh.e, abljemeh.j, abljemeh.f, abljemeh.g, abljemeh.h };
        v = new abljemeh[] { abljemeh.e, abljemeh.j, abljemeh.f, abljemeh.g, abljemeh.i, abljemeh.h };
        w = new abljemeh[] { abljemeh.o, abljemeh.m, abljemeh.f, abljemeh.n };
        x = new abljemeh[] { abljemeh.e, abljemeh.g, abljemeh.j, abljemeh.f, abljemeh.i };
        y = new String[] { "None", "Custom", "Belgian French", "Canadian French", "Canadian French (Legacy)", "Canadian Multilingual Standard", "Danish", "Dutch", "Finnish", "French", "German", "Icelandic", "Norwegian", "Portuguese", "Portuguese (Brazilian ABNT)", "Spanish", "Swedish", "Swiss French", "Swiss German", "US International" };
        abljemeh.z = null;
    }
}

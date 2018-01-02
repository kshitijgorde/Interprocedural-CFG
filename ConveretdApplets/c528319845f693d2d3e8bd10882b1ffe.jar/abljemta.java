import java.util.Enumeration;
import java.awt.MenuItem;
import java.awt.Event;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemta
{
    final int a = 128;
    abljema b;
    abljemml c;
    Vector d;
    Object e;
    Object f;
    Event g;
    boolean h;
    Vector i;
    StringBuffer j;
    private Vector[] k;
    
    public abljemta(final abljema abljema) {
        this.d = new Vector();
        this.e = new Object();
        this.f = new Object();
        this.h = false;
        this.i = new Vector();
        this.k = new Vector[10];
        this.b = abljema.j;
        if (abljema.i == 0) {
            this.c = new abljemml(abljema);
        }
        else {
            this.c = this.b.fb.ez;
        }
        for (int i = 0; i < this.k.length; ++i) {
            this.k[i] = new Vector();
        }
    }
    
    public void a() {
        this.d.removeAllElements();
    }
    
    public void b() {
        this.a();
        this.i = new Vector();
    }
    
    public void a(final int n, final int n2) {
        synchronized (this.e) {
            if (n > 0 && n < this.k.length) {
                this.k[n] = this.d;
            }
            this.d = this.k[(n2 < this.k.length) ? n2 : 0];
        }
        // monitorexit(this.e)
    }
    
    public boolean c() {
        return this.j != null && this.j.length() <= 128;
    }
    
    public String a(final String s) {
        if (s.length() < 1) {
            return "Macro not named";
        }
        if (this.j == null) {
            return "Macro empty";
        }
        if (this.j.length() > 128) {
            return "Macro is too long to save";
        }
        if (!this.c.a(s, this.j)) {
            return "Macro add failed";
        }
        this.j = null;
        return null;
    }
    
    public void a(final MenuItem menuItem) {
        this.a(this.c.a(menuItem));
    }
    
    public void b(final String s) {
        this.a(new StringBuffer(s));
    }
    
    public void a(final StringBuffer sb) {
        final char[] array = new char[10];
        if (sb == null) {
            return;
        }
        final Vector vector = new Vector();
        for (int i = 0; i < sb.length(); ++i) {
            try {
                Event a;
                if (sb.charAt(i) != '+') {
                    a = this.a(sb.charAt(i));
                }
                else {
                    ++i;
                    final char char1 = sb.charAt(i);
                    if (char1 >= '0' && char1 <= '9') {
                        sb.getChars(i, i + 1, array, 0);
                        ++i;
                        a = new Event(this.e, 400 + abljema.a(array, 0, 1), null);
                        sb.getChars(i, i + 2, array, 0);
                        i += 2;
                        a.modifiers = abljema.a(array, 0, 2);
                        sb.getChars(i, i + 4, array, 0);
                        i += 4;
                        a.key = abljema.a(array, 0, 4);
                        --i;
                    }
                    else {
                        a = new Event(this.e, 401, null);
                        switch (char1) {
                            case 43: {
                                a.key = 43;
                                break;
                            }
                            case 69: {
                                a.key = 10;
                                break;
                            }
                            case 84: {
                                a.key = 9;
                                break;
                            }
                            case 67: {
                                a.key = 127;
                                a.modifiers = 8;
                                break;
                            }
                            case 42: {
                                sb.getChars(i, i + 7, array, 0);
                                final String s = new String(array, 0, 7);
                                if (!s.equalsIgnoreCase("*USER09")) {
                                    abljem.d("Playback stopped at unknown '" + s + "' at offset " + i + " in " + (Object)sb);
                                    return;
                                }
                                final String ay = this.b.ay;
                                if (ay == null || ay.length() != 9) {
                                    abljem.d("Playback stopped because USER09 length is " + ((ay == null) ? -1 : ay.length()));
                                    return;
                                }
                                for (int j = 0; j < 9; ++j) {
                                    this.d(this.a(ay.charAt(j)));
                                }
                                i += 7;
                                --i;
                                continue;
                            }
                            default: {
                                abljem.d("Playback stopped at invalid character'" + char1 + "' at offset " + i + " in " + (Object)sb);
                                return;
                            }
                        }
                    }
                }
                this.d(this.f(a));
            }
            catch (Throwable t) {
                abljem.d("Playback stopped at xs=" + i + " in " + (Object)sb + "\n" + t.toString());
            }
        }
    }
    
    private Event a(final char key) {
        final Event event = new Event(this.e, 401, null);
        event.key = key;
        return event;
    }
    
    private void c(final Event event) {
        this.d.insertElementAt(event, 0);
    }
    
    private void d(final Event event) {
        this.d.addElement(event);
    }
    
    public void d() {
        this.i = new Vector();
        this.h = true;
    }
    
    public void e() {
        this.h = false;
        if (this.i.isEmpty()) {
            this.j = null;
        }
        else {
            this.j = this.a(this.i);
            this.i = new Vector();
        }
    }
    
    public void a(final String s, final boolean b) {
        if (s == null || s.length() < 1) {
            return;
        }
        for (int i = 0; i < s.length(); ++i) {
            final Event event = new Event(this.e, 401, null);
            event.key = s.charAt(i);
            if (event.key != 13) {
                if (event.key == 10) {
                    event.key = 1001;
                    event.modifiers = (b ? 0 : 8);
                }
                this.d(event);
            }
        }
    }
    
    public void a(final Event event, final Object arg) {
        final Event a = abljema.a(event);
        a.arg = arg;
        a.target = this.e;
        this.d(a);
    }
    
    public void a(final int key) {
        final Event event = new Event(this.e, 401, null);
        event.key = key;
        this.c(event);
    }
    
    public void a(final Event event) {
        this.c(event);
    }
    
    public Event f() {
        if (this.d.isEmpty()) {
            return null;
        }
        final Event event = this.d.elementAt(0);
        this.d.removeElementAt(0);
        return event;
    }
    
    public boolean a(final String s, final Event event, final int n, final char c) {
        if (event.arg != this.f) {
            this.d(s);
            if (c != ' ') {
                this.a(event, this.f);
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final Event g, final char c) {
        if (g == this.g) {
            return false;
        }
        if (g.arg == this.e) {
            return false;
        }
        this.g = g;
        if ((c != ' ' || this.h) && g.target != this.e && this.e(g)) {
            if (this.b.aj[this.b.ag] == 'E') {
                return true;
            }
            if (!this.b.b9 || g.id != 403 || (g.key != 1002 && g.key != 1003)) {
                if (this.h) {
                    this.b(this.f(g));
                    this.f(g);
                }
                if (c != ' ') {
                    if (g.modifiers == 8 && "ASas".indexOf(g.key) >= 0) {
                        this.a();
                        return false;
                    }
                    g.target = this.e;
                    this.d(g);
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean e(final Event event) {
        return (event.id == 401 || event.id == 403) && ((event.modifiers < 2 && (event.key < 256 || (event.key >= 1000 && event.key < 1020))) || (event.modifiers == 2 && event.key >= 1004 && event.key <= 1005) || (event.modifiers == 8 && "AFHIPSTafhipst\\".indexOf(event.key) >= 0) || (event.modifiers == 9 && "Gg".indexOf(event.key) >= 0) || event.key == 10);
    }
    
    private Event f(final Event event) {
        if (event.key == 10 && this.b.a4) {
            if (event.modifiers == 0) {
                event.modifiers = 2;
            }
            else if (event.modifiers == 2) {
                event.modifiers = 0;
            }
        }
        return event;
    }
    
    public void b(final Event event) {
        final Event a = abljema.a(event);
        a.target = this.e;
        if (this.i != null) {
            this.i.addElement(a);
        }
    }
    
    public void c(final String s) {
        if (s.length() <= 6 || !s.substring(3, 6).equals("AFT")) {
            return;
        }
        for (int i = 6; i < s.length(); ++i) {
            final Event event = new Event(this.e, 401, null);
            event.key = s.charAt(i);
            if (event.key == 43) {
                event.modifiers = 1;
            }
            this.b(event);
        }
    }
    
    public void d(final String s) {
        if (!this.h) {
            return;
        }
        Event a = new Event(this.e, 401, null);
        switch (s.charAt(0)) {
            case 'A': {
                a.key = 65;
                a.modifiers = 8;
                break;
            }
            case 'B': {
                a.key = 1000;
                a.modifiers = 8;
                break;
            }
            case 'D': {
                a.key = 1002;
                break;
            }
            case 'E': {
                a.key = 10;
                this.c(s);
                break;
            }
            case 'F': {
                a = this.a(a, s);
                break;
            }
            case 'H': {
                a.key = 72;
                a.modifiers = 8;
                break;
            }
            case 'I': {
                a.key = 73;
                a.modifiers = 8;
                break;
            }
            case 'N': {
                a.key = 1001;
                a.modifiers = 8;
                break;
            }
            case 'P': {
                a.key = 80;
                a.modifiers = 8;
                break;
            }
            case 'S': {
                a.key = 83;
                a.modifiers = 8;
                break;
            }
            case 'U': {
                a.key = 1003;
                break;
            }
            case 'X': {
                a.key = 10;
                a.modifiers = 2;
                break;
            }
            default: {
                a = null;
                break;
            }
        }
        if (a != null) {
            this.b(a);
        }
    }
    
    private Event a(final Event event, final String s) {
        if (s.length() < 3) {
            abljem.d("Did not record Fxx=" + s);
            return null;
        }
        int d = abljema.d(s.substring(1, 3));
        if (d < 1 || d > 24) {
            abljem.d("Did not record Fxx=" + s);
            return null;
        }
        if (d > 12) {
            event.modifiers = 1;
            d -= 12;
        }
        event.key = 1008 + d - 1;
        return event;
    }
    
    private StringBuffer a(final Vector vector) {
        final StringBuffer sb = new StringBuffer();
        final Enumeration<Event> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final Event nextElement = elements.nextElement();
            if (nextElement instanceof Event) {
                final Event event = nextElement;
                if (event.key == 0) {
                    continue;
                }
                if (event.key >= 32 && event.key != 43 && event.key != 127 && event.key < 255 && event.modifiers < 2 && event.id == 401 && (event.key != 32 || elements.hasMoreElements())) {
                    sb.append((char)event.key);
                }
                else {
                    sb.append('+');
                    sb.append(abljema.e(event.id - 400, 1));
                    sb.append(abljema.e(event.modifiers, 2));
                    sb.append(abljema.e(event.key, 4));
                }
            }
        }
        return sb;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.awt.Frame;
import ji.awt.bb;
import ji.awt.c;
import ji.io.h;
import ji.util.i;
import java.awt.event.FocusEvent;
import ji.util.e;
import java.util.Vector;
import ji.util.d;
import java.awt.Component;
import java.awt.event.FocusListener;

public class bo implements FocusListener
{
    Component a;
    Component b;
    sz c;
    Object d;
    Object e;
    String f;
    public static String g;
    
    public bo(final String f) {
        this.a = null;
        this.b = null;
        this.c = new sz();
        this.d = null;
        this.e = null;
        this.f = null;
        this.f = f;
    }
    
    public Object a() {
        return this.d;
    }
    
    public void a(final ar ar) {
        if (ji.util.d.b()) {
            return;
        }
        synchronized (this.c) {
            this.c.a(ar);
        }
        // monitorexit(this.c)
    }
    
    public void b(final ar ar) {
        synchronized (this.c) {
            this.c.b(ar);
        }
        // monitorexit(this.c)
    }
    
    public void a(final String s, final Component component) {
        try {
            if (component != null) {
                synchronized (this.c) {
                    final int a = this.c.a();
                    final Vector vector = new Vector<Integer>(100);
                    for (int i = 0; i < a; ++i) {
                        final Component a2 = this.c.a(i);
                        if (a2 == null || ji.util.d.a(a2, component) || this.d(a2)) {
                            vector.addElement(new Integer(i));
                        }
                    }
                    final int size = vector.size();
                    final int a3 = this.c.a();
                    for (int j = 0; j < size; ++j) {
                        try {
                            final int intValue = vector.elementAt(j);
                            if (intValue < a3) {
                                this.c.a((Object)null, intValue);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            ji.util.d.a(ex);
                        }
                    }
                    vector.removeAllElements();
                    for (int k = this.c.a() - 1; k >= 0; --k) {
                        if (this.c.a(k) == null) {
                            this.c.b(k);
                        }
                    }
                }
                // monitorexit(this.c)
            }
            this.a = null;
            this.b = null;
            this.d = null;
            this.e = null;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        if (this.c.a() != 0 && ji.util.d.cy() && ji.util.d.b2() <= 1) {
            System.out.println("couldn't empty, summary: (parent=".concat(String.valueOf(String.valueOf(component))));
            for (int a4 = this.c.a(), l = 0; l < a4; ++l) {
                final Component a5 = this.c.a(l);
                System.out.print(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(l))).append(": ").append(a5))));
                if (a5 != null) {
                    System.out.print(", ".concat(String.valueOf(String.valueOf(a5.getParent()))));
                }
                System.out.println();
            }
        }
    }
    
    private boolean d(final Component component) {
        return component != null && (component.getParent() == null || component.getParent().getParent() == null);
    }
    
    public Component a(final Component component) {
        return this.c.a(component);
    }
    
    public Component b(final Component component) {
        return this.c.b(component);
    }
    
    public Component c(final Component component) {
        return this.c.c(component);
    }
    
    public final void b() {
        if (!ji.util.d.c5() && !ji.util.e.c(this.a)) {
            final Component a = this.a(this.a);
            if (a != null) {
                if (ji.util.e.at()) {
                    ji.util.e.b(a);
                }
            }
            else {
                final Component b = this.b(this.a);
                if (b != null && ji.util.e.at()) {
                    ji.util.e.b(b);
                }
            }
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (i.c(102)) {
            h.d(ji.util.d.ek(), "".concat(String.valueOf(String.valueOf(focusEvent))));
        }
        this.d();
        final Object source = focusEvent.getSource();
        this.d = source;
        if (!ji.util.d.c5()) {
            try {
                if (source != null && source instanceof Component && ji.util.e.c((Component)source)) {
                    this.a(focusEvent);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (i.c(102)) {
            h.d(ji.util.d.ek(), "".concat(String.valueOf(String.valueOf(focusEvent))));
        }
        this.e = this.d;
        this.d = null;
        if (!ji.util.d.c5()) {
            try {
                if (focusEvent.getSource() instanceof Component) {
                    this.b(focusEvent);
                }
            }
            catch (Exception ex) {}
        }
        this.e();
    }
    
    private final void d() {
        ji.util.e.bb();
    }
    
    private final void e() {
        ji.util.e.bc();
    }
    
    private final void a(final FocusEvent focusEvent) {
        final Component component = (Component)focusEvent.getSource();
        if (!ji.util.d.c5() && !component.equals(this.a)) {
            this.a = (Component)focusEvent.getSource();
            ji.util.d.bk(ji.util.d.a((Component)focusEvent.getSource()));
        }
    }
    
    private final void b(final FocusEvent focusEvent) {
        final Component component = (Component)focusEvent.getSource();
        if (!ji.util.d.c5() && !component.equals(this.b)) {
            this.b = (Component)focusEvent.getSource();
        }
    }
    
    public Component c() {
        return this.a;
    }
    
    public final void a(final b b) {
        if (ji.util.d.b()) {
            return;
        }
        this.c.b(b);
    }
    
    public final void b(final b b) {
        this.c.a(b);
    }
    
    static {
        bo.g = "158";
    }
    
    class sz
    {
        private Vector a;
        private c b;
        
        sz() {
            this.a = new Vector();
        }
        
        public final void a(final b b) {
            if (this.b != null && this.b.a(b)) {
                this.b.b(b);
            }
        }
        
        public final void b(final b b) {
            if (this.b == null) {
                this.b = new c("jiFocusManager2", 2);
            }
            if (!this.b.a(b)) {
                this.b.c(b);
            }
        }
        
        protected final void a(final a3 a3) {
            if (ji.util.e.ar()) {
                try {
                    if (this.b != null) {
                        final c b = this.b;
                        for (int b2 = b.b(), i = 0; i < b2; ++i) {
                            if (!a3.u()) {
                                ((b)b.b(i)).javaScriptUpdate(a3);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        
        public void a(final Object o, final int n) {
            this.a.setElementAt(o, n);
        }
        
        public final void a(final ar ar) {
            if (ar != null && ar instanceof Component && this.d((Component)ar) < 0) {
                this.a.addElement(ar);
            }
        }
        
        public final boolean b(final ar ar) {
            if (ar == null) {
                return false;
            }
            if (!(ar instanceof Component)) {
                return false;
            }
            final int d = this.d((Component)ar);
            if (d >= 0) {
                this.a.removeElementAt(d);
                return true;
            }
            return false;
        }
        
        public final Component a(final Component component) {
            return this.a(component, 1);
        }
        
        public final Component b(final Component component) {
            return this.a(component, 0);
        }
        
        public final Component c(final Component component) {
            return this.a(component, -1);
        }
        
        public final Component a(final int n) {
            try {
                return this.a.elementAt(n);
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
                return null;
            }
        }
        
        public final void b(final int n) {
            this.a.removeElementAt(n);
        }
        
        private final Component a(final Component component, int n) {
            Component component2 = null;
            try {
                int d = 0;
                if (n != 0) {
                    d = this.d(component);
                }
                else {
                    n = 1;
                }
                final String e = this.e(component);
                final int max = Math.max(d, 0);
                final Frame b = ji.util.d.b(component);
                final boolean b2 = false;
                if (max >= 0) {
                    int n2 = this.a(max + n, n, b, e);
                    if (n2 < 0) {
                        if (!b2 && bo.this.d != null && bo.this.e != null) {
                            new bb(bo.this.f, new ade(component)).start();
                        }
                        if (n > 0) {
                            n2 = this.a(1, 1, b, e);
                        }
                        else {
                            n2 = this.a(this.a.size() - 1, -1, b, e);
                        }
                    }
                    if (n2 >= 0) {
                        component2 = (Component)this.a.elementAt(n2);
                    }
                }
            }
            catch (Exception ex) {}
            return component2;
        }
        
        public final int a() {
            try {
                return this.a.size();
            }
            catch (Exception ex) {
                return 0;
            }
        }
        
        private final int d(final Component component) {
            int n = -1;
            try {
                Block_5: {
                    for (int i = 0; i < this.a.size(); ++i) {
                        final Component component2 = this.a.elementAt(i);
                        if (component2 != null && component2.equals(component)) {
                            break Block_5;
                        }
                    }
                    return n;
                }
                int i = 0;
                n = i;
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            return n;
        }
        
        private final String e(final Component component) {
            try {
                if (component instanceof ar) {
                    return ((ar)component).getFocusRingId();
                }
                return null;
            }
            catch (Exception ex) {
                return null;
            }
        }
        
        private final int a(final int n, final int n2, final Frame frame, final String s) {
            int n3 = -1;
            try {
                if (n >= 0) {
                    if (n2 < 0) {
                        int i = 0;
                        Block_12: {
                            for (int i = n; i > 0; i += n2) {
                                final Component component = this.a.elementAt(i);
                                if (ji.util.e.c(component)) {
                                    if (frame != null) {
                                        final Frame b = ji.util.d.b(component);
                                        if (b != null) {
                                            if (frame.equals(b) && this.a(component, s)) {
                                                return i;
                                            }
                                        }
                                        else if (this.a(component, s)) {
                                            return i;
                                        }
                                    }
                                    else if (this.a(component, s)) {
                                        break Block_12;
                                    }
                                }
                            }
                            return n3;
                            n3 = i;
                            return n3;
                            n3 = i;
                            return n3;
                        }
                        n3 = i;
                    }
                    else {
                        int j = 0;
                        Block_20: {
                            for (int j = n; j < this.a.size(); j += n2) {
                                final Component component2 = this.a.elementAt(j);
                                if (ji.util.e.c(component2)) {
                                    if (frame != null) {
                                        final Frame b2 = ji.util.d.b(component2);
                                        if (b2 != null) {
                                            if (frame.equals(b2) && this.a(component2, s)) {
                                                return j;
                                            }
                                        }
                                        else if (this.a(component2, s)) {
                                            return j;
                                        }
                                    }
                                    else if (this.a(component2, s)) {
                                        break Block_20;
                                    }
                                }
                            }
                            return n3;
                            n3 = j;
                            return n3;
                            n3 = j;
                            return n3;
                        }
                        n3 = j;
                    }
                }
            }
            catch (Exception ex) {}
            return n3;
        }
        
        private final boolean a(final Component component, final String s) {
            Object focusRingId = null;
            if (component instanceof ar) {
                focusRingId = ((ar)component).getFocusRingId();
            }
            if (s != null) {
                return focusRingId != null && s.equals(focusRingId);
            }
            return focusRingId == null;
        }
        
        class ade implements Runnable
        {
            Component a;
            
            public ade(final Component a) {
                this.a = null;
                this.a = a;
            }
            
            public void run() {
                ji.util.d.b(500, 105, bo.this.f);
                sz.this.a(new a3(this.a, 21, 0, "End Tab", 9));
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.awt.Rectangle;
import ji.util.e;
import ji.io.h;
import ji.util.i;
import java.awt.event.KeyEvent;
import ji.util.d;
import java.awt.Component;
import ji.awt.c;
import java.awt.event.KeyListener;

public class bu implements KeyListener
{
    bo a;
    private c b;
    private c c;
    private boolean d;
    private int e;
    private String f;
    
    public bu(final String f) {
        this.a = null;
        this.b = null;
        this.c = new c("jiKeyManager1");
        this.d = true;
        this.e = -1;
        this.f = null;
        this.f = f;
    }
    
    public final void a(final boolean d) {
        if (!(this.d = d)) {
            this.e = -1;
        }
    }
    
    public final void a() {
        this.e = -1;
    }
    
    public final void a(final bo a) {
        this.a = a;
    }
    
    public void a(final int n, final Component component) {
        if (component != null && this.c(n, component) < 0) {
            this.c.c(new s8(n, component));
        }
    }
    
    public void b(final int n, final Component component) {
        try {
            if (component != null) {
                final int c = this.c(n, component);
                if (c >= 0) {
                    this.c.d(c);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Component component) {
        try {
            if (component != null) {
                int i = 1;
                while (i != 0) {
                    final int b = this.c.b();
                    i = 0;
                    for (int n = 0, n2 = 0; n2 < b && n == 0; ++n2) {
                        final Component b2 = ((s8)this.c.b(n2)).b();
                        if (b2 == null || ji.util.d.a(b2, component) || (b2 != null && b2.getParent() == null)) {
                            this.c.d(n2);
                            i = 1;
                            n = 1;
                        }
                    }
                }
                for (int j = 0; j < this.c.b(); ++j) {
                    if (this.c.b(j) == null) {
                        this.c.d(j);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int b() {
        return this.e;
    }
    
    private int c(final int n, final Component component) {
        int n2 = -1;
        try {
            Block_5: {
                for (int b = this.c.b(), i = 0; i < b; ++i) {
                    final s8 s8 = (s8)this.c.b(i);
                    if (s8.a() == n && s8.b().equals(component)) {
                        break Block_5;
                    }
                }
                return n2;
            }
            int i = 0;
            n2 = i;
        }
        catch (Exception ex) {}
        return n2;
    }
    
    private int a(final int n) {
        int n2 = -1;
        try {
            Block_4: {
                for (int b = this.c.b(), i = 0; i < b; ++i) {
                    if (((s8)this.c.b(i)).a() == n) {
                        break Block_4;
                    }
                }
                return n2;
            }
            int i = 0;
            n2 = i;
        }
        catch (Exception ex) {}
        return n2;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (!this.d) {
            return;
        }
        this.a(keyEvent);
        try {
            if (!keyEvent.isConsumed()) {
                this.e = keyEvent.getKeyCode();
                if (ji.util.d.dr() || i.c(34)) {
                    if (keyEvent.getModifiers() != 0) {
                        h.d(this.f, String.valueOf(String.valueOf(new StringBuffer("KeyPressed: ").append(this.e).append("(").append(KeyEvent.getKeyModifiersText(keyEvent.getModifiers())).append("/").append(keyEvent.getKeyChar()).append("-").append(KeyEvent.getKeyText(this.e)).append(")"))));
                    }
                    else {
                        h.d(this.f, String.valueOf(String.valueOf(new StringBuffer("KeyPressed: ").append(this.e).append("(").append(keyEvent.getKeyChar()).append("-").append(KeyEvent.getKeyText(this.e)).append(")"))));
                    }
                }
                if (keyEvent.getKeyCode() == 9 && keyEvent.getSource() instanceof Component) {
                    try {
                        Object o = null;
                        Object o2;
                        final Component component = (Component)(o2 = keyEvent.getSource());
                        int i = 0;
                        while (i == 0) {
                            i = 1;
                            if (keyEvent.isShiftDown()) {
                                o = this.a.c((Component)o2);
                            }
                            else {
                                o = this.a.a((Component)o2);
                            }
                            if (o != null) {
                                if (!((Component)o).isVisible()) {
                                    i = 0;
                                    o2 = o;
                                }
                                else if (((ar)o).ignoreTAB()) {
                                    i = 0;
                                    o2 = o;
                                }
                                else {
                                    final Rectangle bounds = ((Component)o).getBounds();
                                    if (bounds.x + bounds.width > 0 || bounds.y + bounds.height > 0) {
                                        continue;
                                    }
                                    i = 0;
                                    o2 = o;
                                }
                            }
                        }
                        if (o == null) {
                            this.a.b(component);
                        }
                        if (o != null) {
                            keyEvent.consume();
                            if (ji.util.e.at()) {
                                ji.util.e.b((Component)o);
                            }
                        }
                        else {
                            keyEvent.consume();
                            if (ji.util.e.at()) {
                                ji.util.e.b((Component)keyEvent.getSource());
                            }
                        }
                    }
                    catch (Exception ex) {
                        try {
                            keyEvent.consume();
                            if (ji.util.e.at()) {
                                ji.util.e.b((Component)keyEvent.getSource());
                            }
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.e = -1;
        if (!this.d) {
            return;
        }
        this.b(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (!this.d) {
            return;
        }
        this.c(keyEvent);
    }
    
    public final void a(final KeyListener keyListener, final Component component) {
        try {
            if (this.b == null) {
                this.b = new c("jiKeyManager2", 2);
            }
            if (!this.b.a(keyListener)) {
                this.b.c(new xk(keyListener, component));
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final KeyListener keyListener) {
        try {
            if (this.b != null) {
                final int b = this.b(keyListener);
                if (b >= 0) {
                    this.b.d(b);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final int b(final KeyListener keyListener) {
        int n = -1;
        if (this.b != null) {
            for (int i = 0; i < this.b.b(); ++i) {
                if (((xk)this.b.b(i)).a().equals(keyListener)) {
                    n = i;
                    break;
                }
            }
        }
        return n;
    }
    
    private final void a(final KeyEvent keyEvent) {
        if (this.b != null && this.a(keyEvent.getKeyCode()) < 0) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                if (!keyEvent.isConsumed()) {
                    final xk xk = (xk)this.b.b(i);
                    if (keyEvent != null && xk != null && this.a(keyEvent.getSource(), xk.b())) {
                        xk.a().keyPressed(keyEvent);
                    }
                }
            }
        }
    }
    
    private final void b(final KeyEvent keyEvent) {
        if (this.b != null && this.a(keyEvent.getKeyCode()) < 0) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                if (!keyEvent.isConsumed()) {
                    final xk xk = (xk)this.b.b(i);
                    if (keyEvent != null && xk != null && this.a(keyEvent.getSource(), xk.b())) {
                        xk.a().keyReleased(keyEvent);
                    }
                }
            }
        }
    }
    
    private final void c(final KeyEvent keyEvent) {
        if (this.b != null && this.a(keyEvent.getKeyCode()) < 0) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                if (!keyEvent.isConsumed()) {
                    final xk xk = (xk)this.b.b(i);
                    if (keyEvent != null && xk != null && this.a(keyEvent.getSource(), xk.b())) {
                        xk.a().keyTyped(keyEvent);
                    }
                }
            }
        }
    }
    
    private final boolean a(final Object o, final Component component) {
        boolean b = false;
        try {
            if (o instanceof Component && ji.util.d.b((Component)o).equals(ji.util.d.b(component))) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    class s8
    {
        int a;
        Component b;
        
        public s8(final bu bu, final int a, final Component b) {
            this.a = -999;
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public final int a() {
            return this.a;
        }
        
        public final Component b() {
            return this.b;
        }
    }
    
    class xk
    {
        private KeyListener a;
        private Component b;
        
        public xk(final bu bu, final KeyListener a, final Component b) {
            this.a = null;
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public final KeyListener a() {
            return this.a;
        }
        
        public final Component b() {
            return this.b;
        }
    }
}

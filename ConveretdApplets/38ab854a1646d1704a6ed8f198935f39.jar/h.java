import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import a.a.f;
import a.a.d;
import java.awt.Component;
import javax.swing.JInternalFrame;
import a.a.b;
import java.awt.Frame;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class h
{
    public static g a(final g g) {
        return new g() {
            private final /* synthetic */ g val$f = val$f;
            
            public boolean a(final n n) {
                return !this.val$f.a(n);
            }
            
            public String toString() {
                return "NOT " + this.val$f.toString();
            }
        };
    }
    
    public static void a(final Container container, final v v) {
        final d d = new d();
        final a.a.b.c c = new a.a.b.c(new Frame());
        c.a(d);
        final JInternalFrame internalFrame = new JInternalFrame("Filter", false, true, false, false);
        internalFrame.getContentPane().add(c);
        container.add(internalFrame);
        c.a();
        internalFrame.getContentPane().add(new f(new a.a.d() {
            private final /* synthetic */ JInternalFrame val$internal = val$internal;
            
            public void a() {
                this.val$internal.setVisible(false);
                c.if();
                v.a(d.if());
            }
        }, "OK"));
        internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), 1));
        internalFrame.pack();
        internalFrame.setVisible(true);
    }
    
    private static final class c extends a
    {
        public String if(final n n) {
            return n.if().if();
        }
        
        public String toString() {
            return "Path " + super.toString();
        }
    }
    
    private static final class b extends a
    {
        public String if(final n n) {
            return n.for().if;
        }
        
        public String toString() {
            return "IP " + super.toString();
        }
    }
    
    public abstract static class a implements g, j.a
    {
        private j a;
        
        public void a(final j a) {
            this.a = a;
        }
        
        public boolean a(final n n) {
            return this.a.a(this.if(n));
        }
        
        public abstract String if(final n p0);
        
        public String toString() {
            return (this.a == null) ? "" : this.a.toString();
        }
    }
    
    public static class d implements h.a
    {
        private j if;
        private a byte;
        private boolean try;
        private j.a[] for;
        private j[] a;
        private static j[] case;
        h int;
        h do;
        h char;
        private h[] new;
        
        static {
            d.case = new j[] { new ab(".*"), new ab(".*javawatch.*"), new ab(".*jpg"), new ab(".*html"), new ab(".*gif") };
        }
        
        public d() {
            this.for = new j.a[] { new b(), new c() };
            this.a = new j[] { new ab("163\\.1\\..*") };
            this.int = new a.a.h.d() {
                final /* synthetic */ d this$1 = this$1;
                
                public String a() {
                    return "Field";
                }
                
                public void a(final Object o) {
                    d.access$0(this.this$1, (a)o);
                }
                
                public Object if() {
                    return this.this$1.byte;
                }
                
                public Object[] do() {
                    return this.this$1.for;
                }
            };
            this.do = new f() {
                final /* synthetic */ d this$1 = this$1;
                
                public String a() {
                    return "Regex";
                }
                
                public String if(final Object o) {
                    return o.toString();
                }
                
                public Object a(final String s) {
                    return new ab(s);
                }
                
                public void a(final Object o) {
                    d.access$3(this.this$1, (j)o);
                }
                
                public Object if() {
                    return this.this$1.if;
                }
                
                public Object[] do() {
                    return (this.this$1.byte instanceof b) ? this.this$1.a : d.case;
                }
            };
            this.char = new m() {
                final /* synthetic */ d this$1 = this$1;
                
                public String a() {
                    return "Negate";
                }
                
                public void a(final boolean b) {
                    d.access$7(this.this$1, b);
                }
                
                public boolean byte() {
                    return this.this$1.try;
                }
            };
            this.new = new h[] { this.char, this.int, this.do };
        }
        
        public g if() {
            this.byte.a(this.if);
            return this.try ? h.a(this.byte) : this.byte;
        }
        
        public h[] a() {
            return this.new;
        }
        
        static /* synthetic */ void access$0(final d d, final a byte1) {
            d.byte = byte1;
        }
        
        static /* synthetic */ void access$3(final d d, final j if1) {
            d.if = if1;
        }
        
        static /* synthetic */ void access$7(final d d, final boolean try1) {
            d.try = try1;
        }
    }
}

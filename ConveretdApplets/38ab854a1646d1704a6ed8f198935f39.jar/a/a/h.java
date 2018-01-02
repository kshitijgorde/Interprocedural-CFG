// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.util.Vector;
import java.awt.Frame;
import java.io.Serializable;

public interface h extends Serializable
{
    String a();
    
    Object if();
    
    void a(final Object p0);
    
    public abstract static class e implements h
    {
        private String a;
        
        public e(final String a) {
            this.a = a;
        }
        
        public String a() {
            return this.a;
        }
        
        public abstract Object if();
        
        public abstract void a(final Object p0);
    }
    
    public interface h extends a.a.h
    {
        String if(final Object p0);
        
        Object a(final String p0);
    }
    
    public interface d extends h
    {
        Object[] do();
    }
    
    public interface f extends d, h
    {
        Object[] do();
    }
    
    public interface c extends d
    {
    }
    
    public interface a
    {
        h[] a();
    }
    
    public interface n extends h
    {
        a.a.e a(final Frame p0);
    }
    
    public abstract static class g implements n
    {
        public abstract void a(final Vector p0);
        
        public abstract Vector case();
        
        public final void a(final Object o) {
            this.a((Vector)o);
        }
        
        public final Object if() {
            return this.case();
        }
        
        public abstract String a();
        
        public abstract a.a.e a(final Frame p0);
    }
    
    public abstract static class l implements h
    {
        public abstract void if(final String p0);
        
        public abstract String try();
        
        public final void a(final Object o) {
            this.if((String)o);
        }
        
        public final Object if() {
            return this.try();
        }
        
        public final String if(final Object o) {
            return (String)o;
        }
        
        public final Object a(final String s) {
            return s;
        }
        
        public abstract String a();
    }
    
    public abstract static class b implements h
    {
        public abstract void a(final double p0);
        
        public abstract double for();
        
        public final void a(final Object o) {
            this.a((double)o);
        }
        
        public final Object if() {
            return new Double(this.for());
        }
        
        public final String if(final Object o) {
            return o.toString();
        }
        
        public final Object a(final String s) {
            return new Double(s);
        }
        
        public abstract String a();
    }
    
    public interface i extends h
    {
        void a(final boolean p0);
        
        boolean byte();
    }
    
    public abstract static class m implements i
    {
        public abstract void a(final boolean p0);
        
        public abstract boolean byte();
        
        public void a(final Object o) {
            this.a((boolean)o);
        }
        
        public Object if() {
            return new Boolean(this.byte());
        }
        
        public abstract String a();
    }
    
    public abstract static class k implements h
    {
        public abstract void a(final int p0);
        
        public abstract int new();
        
        public final void a(final Object o) {
            this.a((int)o);
        }
        
        public final Object if() {
            return new Integer(this.new());
        }
        
        public final String if(final Object o) {
            return o.toString();
        }
        
        public final Object a(final String s) {
            return new Integer(s);
        }
        
        public abstract String a();
    }
    
    public abstract static class j implements h
    {
        public abstract void a(final long p0);
        
        public abstract long int();
        
        public final void a(final Object o) {
            this.a((long)o);
        }
        
        public final Object if() {
            return new Long(this.int());
        }
        
        public final String if(final Object o) {
            return o.toString();
        }
        
        public final Object a(final String s) {
            return new Long(s);
        }
        
        public abstract String a();
    }
}

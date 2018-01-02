// 
// Decompiled by Procyon v0.5.30
// 

package org.a.c;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

class d
{
    private Logger a;
    
    public d() {
        this.a(Logger.getRootLogger());
    }
    
    public d(final String s) {
        this.a(Logger.getLogger(s));
    }
    
    public void a(final Logger a) {
        this.a = a;
    }
    
    public void a(final Object o) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.DEBUG)) {
            ((Category)this.a).debug(o);
        }
    }
    
    public void a(final Object o, final Throwable t) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.DEBUG)) {
            ((Category)this.a).debug(o, t);
        }
    }
    
    public void b(final Object o, final Throwable t) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.INFO)) {
            ((Category)this.a).info(o, t);
        }
    }
    
    public void b(final Object o) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.WARN)) {
            ((Category)this.a).warn(o);
        }
    }
    
    public void c(final Object o, final Throwable t) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.WARN)) {
            ((Category)this.a).warn(o, t);
        }
    }
    
    public void c(final Object o) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.ERROR)) {
            ((Category)this.a).error(o);
        }
    }
    
    public void d(final Object o, final Throwable t) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.ERROR)) {
            ((Category)this.a).error(o, t);
        }
    }
    
    public void d(final Object o) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.FATAL)) {
            ((Category)this.a).fatal(o);
        }
    }
    
    public void e(final Object o, final Throwable t) {
        if (this.a != null && ((Category)this.a).isEnabledFor((Priority)Level.FATAL)) {
            ((Category)this.a).fatal(o, t);
        }
    }
}

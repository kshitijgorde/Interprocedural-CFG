// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.Iterator;
import java.util.Collection;
import java.lang.ref.SoftReference;
import org.jboss.logging.Logger;

public class JBossObject implements JBossInterface
{
    protected Logger log;
    protected transient SoftReference toString;
    protected transient int hashCode;
    
    public static boolean equals(final Object one, final Object two) {
        return (one != null || two == null) && (one == null || one.equals(two));
    }
    
    public static boolean notEqual(final Object one, final Object two) {
        return !equals(one, two);
    }
    
    public static void list(final JBossStringBuilder buffer, final Collection objects) {
        if (objects == null) {
            return;
        }
        buffer.append('[');
        if (!objects.isEmpty()) {
            final Iterator i = objects.iterator();
            while (i.hasNext()) {
                final Object object = i.next();
                if (object instanceof JBossObject) {
                    ((JBossObject)object).toShortString(buffer);
                }
                else {
                    buffer.append(object.toString());
                }
                if (i.hasNext()) {
                    buffer.append(", ");
                }
            }
        }
        buffer.append(']');
    }
    
    public JBossObject() {
        this.hashCode = Integer.MIN_VALUE;
        this.log = Logger.getLogger(this.getClass());
    }
    
    public JBossObject(final Logger log) {
        this.hashCode = Integer.MIN_VALUE;
        this.log = ((log != null) ? log : Logger.getLogger(this.getClass()));
    }
    
    public String toString() {
        if (!this.cacheToString()) {
            return this.toStringImplementation();
        }
        String result = null;
        if (this.toString != null) {
            result = this.toString.get();
        }
        if (result == null) {
            result = this.toStringImplementation();
            this.toString = new SoftReference((T)result);
        }
        return result;
    }
    
    public int hashCode() {
        if (this.hashCode == Integer.MIN_VALUE || !this.cacheGetHashCode()) {
            this.hashCode = this.getHashCode();
        }
        return this.hashCode;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String toShortString() {
        final JBossStringBuilder buffer = new JBossStringBuilder();
        this.toShortString(buffer);
        return buffer.toString();
    }
    
    public void toShortString(final JBossStringBuilder buffer) {
    }
    
    public String getClassShortName() {
        final String longName = this.getClass().getName();
        final int dot = longName.lastIndexOf(46);
        if (dot != -1) {
            return longName.substring(dot + 1);
        }
        return longName;
    }
    
    protected String toStringImplementation() {
        final JBossStringBuilder buffer = new JBossStringBuilder();
        buffer.append(this.getClassShortName()).append('@');
        buffer.append(Integer.toHexString(System.identityHashCode(this)));
        buffer.append('{');
        this.toString(buffer);
        buffer.append('}');
        return buffer.toString();
    }
    
    protected void flushJBossObjectCache() {
        this.toString = null;
        this.hashCode = Integer.MIN_VALUE;
    }
    
    protected void toString(final JBossStringBuilder buffer) {
    }
    
    protected int getHashCode() {
        return super.hashCode();
    }
    
    protected boolean cacheToString() {
        return true;
    }
    
    protected boolean cacheGetHashCode() {
        return true;
    }
}

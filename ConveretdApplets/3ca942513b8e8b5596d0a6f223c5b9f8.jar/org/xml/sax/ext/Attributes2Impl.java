// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.ext;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class Attributes2Impl extends AttributesImpl implements Attributes2
{
    private boolean[] declared;
    private boolean[] specified;
    
    public Attributes2Impl() {
    }
    
    public Attributes2Impl(final Attributes attributes) {
        super(attributes);
    }
    
    public boolean isDeclared(final int n) {
        if (n < 0 || n >= this.getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + n);
        }
        return this.declared[n];
    }
    
    public boolean isDeclared(final String s, final String s2) {
        final int index = this.getIndex(s, s2);
        if (index < 0) {
            throw new IllegalArgumentException("No such attribute: local=" + s2 + ", namespace=" + s);
        }
        return this.declared[index];
    }
    
    public boolean isDeclared(final String s) {
        final int index = this.getIndex(s);
        if (index < 0) {
            throw new IllegalArgumentException("No such attribute: " + s);
        }
        return this.declared[index];
    }
    
    public boolean isSpecified(final int n) {
        if (n < 0 || n >= this.getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + n);
        }
        return this.specified[n];
    }
    
    public boolean isSpecified(final String s, final String s2) {
        final int index = this.getIndex(s, s2);
        if (index < 0) {
            throw new IllegalArgumentException("No such attribute: local=" + s2 + ", namespace=" + s);
        }
        return this.specified[index];
    }
    
    public boolean isSpecified(final String s) {
        final int index = this.getIndex(s);
        if (index < 0) {
            throw new IllegalArgumentException("No such attribute: " + s);
        }
        return this.specified[index];
    }
    
    public void setAttributes(final Attributes attributes) {
        final int length = attributes.getLength();
        super.setAttributes(attributes);
        this.declared = new boolean[length];
        this.specified = new boolean[length];
        if (attributes instanceof Attributes2) {
            final Attributes2 attributes2 = (Attributes2)attributes;
            for (int i = 0; i < length; ++i) {
                this.declared[i] = attributes2.isDeclared(i);
                this.specified[i] = attributes2.isSpecified(i);
            }
        }
        else {
            for (int j = 0; j < length; ++j) {
                this.declared[j] = !"CDATA".equals(attributes.getType(j));
                this.specified[j] = true;
            }
        }
    }
    
    public void addAttribute(final String s, final String s2, final String s3, final String s4, final String s5) {
        super.addAttribute(s, s2, s3, s4, s5);
        final int length = this.getLength();
        this.ensureCapacity(length);
        this.specified[length - 1] = true;
        this.declared[length - 1] = !"CDATA".equals(s4);
    }
    
    public void removeAttribute(final int n) {
        final int n2 = this.getLength() - 1;
        super.removeAttribute(n);
        if (n != n2) {
            System.arraycopy(this.declared, n + 1, this.declared, n, n2 - n);
            System.arraycopy(this.specified, n + 1, this.specified, n, n2 - n);
        }
    }
    
    public void setDeclared(final int n, final boolean b) {
        if (n < 0 || n >= this.getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + n);
        }
        this.declared[n] = b;
    }
    
    public void setSpecified(final int n, final boolean b) {
        if (n < 0 || n >= this.getLength()) {
            throw new ArrayIndexOutOfBoundsException("No attribute at index: " + n);
        }
        this.specified[n] = b;
    }
    
    private void ensureCapacity(final int n) {
        if (this.declared == null) {
            this.declared = new boolean[5];
            this.specified = new boolean[5];
        }
        else if (n > this.declared.length) {
            final int n2 = (this.declared.length > 0) ? (this.declared.length * 2) : 5;
            final boolean[] declared = new boolean[n2];
            System.arraycopy(this.declared, 0, declared, 0, this.declared.length);
            this.declared = declared;
            final boolean[] specified = new boolean[n2];
            System.arraycopy(this.specified, 0, specified, 0, this.specified.length);
            this.specified = specified;
        }
    }
}

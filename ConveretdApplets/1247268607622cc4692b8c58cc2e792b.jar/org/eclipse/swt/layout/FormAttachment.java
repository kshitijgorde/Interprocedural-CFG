// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

public final class FormAttachment
{
    public int numerator;
    public int denominator;
    public int offset;
    public Control control;
    public int alignment;
    
    public FormAttachment() {
        this.denominator = 100;
    }
    
    public FormAttachment(final int n) {
        this(n, 100, 0);
    }
    
    public FormAttachment(final int n, final int n2) {
        this(n, 100, n2);
    }
    
    public FormAttachment(final int numerator, final int denominator, final int offset) {
        this.denominator = 100;
        if (denominator == 0) {
            SWT.error(7);
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.offset = offset;
    }
    
    public FormAttachment(final Control control) {
        this(control, 0, -1);
    }
    
    public FormAttachment(final Control control, final int n) {
        this(control, n, -1);
    }
    
    public FormAttachment(final Control control, final int offset, final int alignment) {
        this.denominator = 100;
        this.control = control;
        this.offset = offset;
        this.alignment = alignment;
    }
    
    FormAttachment divide(final int n) {
        return new FormAttachment(this.numerator, this.denominator * n, this.offset / n);
    }
    
    int gcd(int abs, int i) {
        abs = Math.abs(abs);
        i = Math.abs(i);
        if (abs < i) {
            final int n = abs;
            abs = i;
            i = n;
        }
        while (i != 0) {
            final int n2 = abs;
            abs = i;
            i = n2 % i;
        }
        return abs;
    }
    
    FormAttachment minus(final FormAttachment formAttachment) {
        final FormAttachment formAttachment2 = new FormAttachment();
        formAttachment2.numerator = this.numerator * formAttachment.denominator - this.denominator * formAttachment.numerator;
        formAttachment2.denominator = this.denominator * formAttachment.denominator;
        final int gcd = this.gcd(formAttachment2.denominator, formAttachment2.numerator);
        formAttachment2.numerator /= gcd;
        formAttachment2.denominator /= gcd;
        formAttachment2.offset = this.offset - formAttachment.offset;
        return formAttachment2;
    }
    
    FormAttachment minus(final int n) {
        return new FormAttachment(this.numerator, this.denominator, this.offset - n);
    }
    
    FormAttachment plus(final FormAttachment formAttachment) {
        final FormAttachment formAttachment2 = new FormAttachment();
        formAttachment2.numerator = this.numerator * formAttachment.denominator + this.denominator * formAttachment.numerator;
        formAttachment2.denominator = this.denominator * formAttachment.denominator;
        final int gcd = this.gcd(formAttachment2.denominator, formAttachment2.numerator);
        formAttachment2.numerator /= gcd;
        formAttachment2.denominator /= gcd;
        formAttachment2.offset = this.offset + formAttachment.offset;
        return formAttachment2;
    }
    
    FormAttachment plus(final int n) {
        return new FormAttachment(this.numerator, this.denominator, this.offset + n);
    }
    
    int solveX(final int n) {
        if (this.denominator == 0) {
            SWT.error(7);
        }
        return this.numerator * n / this.denominator + this.offset;
    }
    
    int solveY(final int n) {
        if (this.numerator == 0) {
            SWT.error(7);
        }
        return (n - this.offset) * this.denominator / this.numerator;
    }
    
    public String toString() {
        return "{y = (" + ((this.control != null) ? this.control.toString() : (String.valueOf(this.numerator) + "/" + this.denominator)) + ((this.offset >= 0) ? (")x + " + this.offset) : (")x - " + -this.offset)) + "}";
    }
}

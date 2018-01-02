// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

final class di implements PropertyChangeListener
{
    final /* synthetic */ dg a;
    
    di(final dg a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final boolean q = g.q;
        Object o;
        final String s = (String)(o = propertyChangeEvent.getPropertyName());
        if (!q) {
            if (!s.equals(a("']40u%[\"?c2C>#d2K"))) {
                return;
            }
            o = propertyChangeEvent.getOldValue();
        }
        final i i = (i)o;
        final dg a2;
        Label_0158: {
            Label_0154: {
                if (propertyChangeEvent.getNewValue().equals(Boolean.TRUE)) {
                    final i a = dg.a(this.a);
                    if (!q) {
                        if (a == null) {
                            dg.a(this.a, i);
                            if (!q) {
                                break Label_0154;
                            }
                        }
                        a2 = this.a;
                        if (q) {
                            break Label_0158;
                        }
                        dg.a(a2);
                    }
                    if (a.equals(i)) {
                        break Label_0154;
                    }
                    dg.a(this.a).e().a(false);
                    dg.a(this.a, i);
                    if (!q) {
                        break Label_0154;
                    }
                }
                final dg a3 = this.a;
                if (!q) {
                    if (dg.a(a3) != null) {
                        final dg a4 = this.a;
                        if (q) {
                            break Label_0158;
                        }
                        if (!dg.a(a4).equals(i)) {
                            break Label_0154;
                        }
                    }
                    final dg a5 = this.a;
                }
                dg.a(a3, null);
            }
            final dg a6 = this.a;
        }
        a2.a(new ItemEvent(this.a, 701, dg.a(this.a), 1));
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'w';
                            break;
                        }
                        case 1: {
                            c2 = '\u000f';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = '`';
                            break;
                        }
                        default: {
                            c2 = '0';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}

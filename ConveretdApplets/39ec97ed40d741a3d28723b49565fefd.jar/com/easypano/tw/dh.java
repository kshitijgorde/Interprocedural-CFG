// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

final class dh implements PropertyChangeListener
{
    final /* synthetic */ df a;
    
    dh(final df a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final boolean q = g.q;
        Object o;
        final String s = (String)(o = propertyChangeEvent.getPropertyName());
        if (!q) {
            if (!s.equals(a("\u000faR\u000e(\rgD\u0001>\u001a\u007fX\u001d9\u001aw"))) {
                return;
            }
            o = propertyChangeEvent.getOldValue();
        }
        final i i = (i)o;
        final df a2;
        Label_0158: {
            Label_0154: {
                if (propertyChangeEvent.getNewValue().equals(Boolean.TRUE)) {
                    final i a = df.a(this.a);
                    if (!q) {
                        if (a == null) {
                            df.a(this.a, i);
                            if (!q) {
                                break Label_0154;
                            }
                        }
                        a2 = this.a;
                        if (q) {
                            break Label_0158;
                        }
                        df.a(a2);
                    }
                    if (a.equals(i)) {
                        break Label_0154;
                    }
                    df.a(this.a).e().a(false);
                    df.a(this.a, i);
                    if (!q) {
                        break Label_0154;
                    }
                }
                final df a3 = this.a;
                if (!q) {
                    if (df.a(a3) != null) {
                        final df a4 = this.a;
                        if (q) {
                            break Label_0158;
                        }
                        if (!df.a(a4).equals(i)) {
                            break Label_0154;
                        }
                    }
                    final df a5 = this.a;
                }
                df.a(a3, null);
            }
            final df a6 = this.a;
        }
        a2.a(new ItemEvent(this.a, 701, df.a(this.a), 1));
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
                            c2 = '_';
                            break;
                        }
                        case 1: {
                            c2 = '3';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = '^';
                            break;
                        }
                        default: {
                            c2 = 'm';
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

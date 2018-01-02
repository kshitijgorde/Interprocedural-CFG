// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.awt.Graphics;
import java.util.Observable;

abstract class o extends Observable
{
    Graph a;
    
    abstract void a();
    
    abstract int b();
    
    void a(final Graphics graphics) {
        this.setChanged();
        this.notifyObservers(graphics);
    }
    
    String[] c() {
        final boolean g = GraphSerie.G;
        final AbstractSerie abstractSerie = this.a.i.elementAt(0);
        final int n = (abstractSerie.b != null) ? abstractSerie.b.length : abstractSerie.a.length;
        final DateFormat dateInstance = DateFormat.getDateInstance(3, this.a.getLocale());
        final String[] array = new String[n];
        final int n2 = Integer.valueOf(this.a.bx.substring(0, 2)) - 1;
        final int intValue = Integer.valueOf(this.a.bx.substring(2, 4));
        final int intValue2 = Integer.valueOf(this.a.bx.substring(4));
        final Calendar instance = Calendar.getInstance();
        instance.set(5, intValue);
        instance.set(2, n2);
        instance.set(1, intValue2);
        this.a(array, 0, instance, dateInstance);
        int n3 = 1;
        while (true) {
            while (true) {
                Label_0294: {
                    if (!g) {
                        break Label_0294;
                    }
                    Label_0280: {
                        if (this.a.by == 0) {
                            instance.add(5, this.a.bz);
                            if (!g) {
                                break Label_0280;
                            }
                        }
                        if (this.a.by == 1) {
                            instance.add(5, this.a.bz * 7);
                            if (!g) {
                                break Label_0280;
                            }
                        }
                        if (this.a.by == 2) {
                            instance.add(2, this.a.bz);
                            if (!g) {
                                break Label_0280;
                            }
                        }
                        if (this.a.by == 3) {
                            instance.add(1, this.a.bz);
                        }
                    }
                    this.a(array, n3, instance, dateInstance);
                    ++n3;
                }
                if (n3 < n) {
                    continue;
                }
                break;
            }
            if (!g) {
                return array;
            }
            continue;
        }
    }
    
    protected void a(final String[] array, final int n, final Calendar calendar, final DateFormat dateFormat) {
        final boolean g = GraphSerie.G;
        final Date time = calendar.getTime();
        if (this.a.by == 0 || this.a.by == 1) {
            array[n] = dateFormat.format(time);
            if (!g) {
                return;
            }
        }
        if (this.a.by == 2) {
            array[n] = Integer.toString(calendar.get(2) + 1) + "/" + Integer.toString(calendar.get(1));
            if (!g) {
                return;
            }
        }
        if (this.a.by == 3) {
            array[n] = Integer.toString(calendar.get(1));
        }
    }
    
    int d() {
        final boolean g = GraphSerie.G;
        final Graphics graphics = this.a.getGraphics();
        int n = 0;
        final String[] r = this.a.r;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.a.X);
        final int n2 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        if (r != null) {
            int n3 = 0;
            int n4 = 0;
            while (true) {
                Label_0075: {
                    if (!g) {
                        break Label_0075;
                    }
                    n = n4;
                    ++n3;
                }
                if (n3 < r.length) {
                    continue;
                }
                n4 = n + 20;
                if (g) {
                    continue;
                }
                break;
            }
            n = n4;
        }
        return n;
    }
}

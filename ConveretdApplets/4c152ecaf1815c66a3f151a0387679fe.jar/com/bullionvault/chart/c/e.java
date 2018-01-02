// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Container;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class e
{
    private Calendar a;
    private int b;
    private int c;
    private String d;
    
    public e(final int b) {
        this.d = "None";
        this.a = Calendar.getInstance();
        this.b = b;
        this.c = a(this.b);
    }
    
    public e(final TimeZone timeZone, final int b) {
        this.d = "None";
        this.a = Calendar.getInstance(timeZone);
        this.b = b;
        this.c = a(this.b);
    }
    
    public final Date a(final Date date, final int n) {
        this.a(date);
        int b = this.b;
        int i = 1;
        while (i != 0) {
            switch (b) {
                case 13: {
                    this.a.set(13, this.a.getMinimum(13));
                    i = 0;
                    continue;
                }
                case 12: {
                    this.a.set(12, this.a.getMinimum(12));
                    b = 13;
                    continue;
                }
                case 11: {
                    this.a.set(11, this.a.getMinimum(11));
                    b = 12;
                    continue;
                }
                case 5: {
                    this.a.set(5, this.a.getMinimum(5));
                    b = 11;
                    continue;
                }
                case 3: {
                    this.a.set(3, this.a.getMinimum(3));
                    b = 11;
                    continue;
                }
                case 2: {
                    this.a.set(2, this.a.getMinimum(2));
                    b = 5;
                    continue;
                }
                case 1: {
                    this.a.set(1, this.a.getMinimum(1));
                    b = 2;
                    continue;
                }
                default: {
                    System.err.println("Error - unknown Calendar units [" + b + "]");
                    continue;
                }
            }
        }
        this.a(a(this.b), n);
        return (this = this).a.getTime();
    }
    
    public static int a(int n) {
        switch (n) {
            case 13: {
                return 12;
            }
            case 12: {
                n = 11;
                break;
            }
            case 11: {
                n = 5;
                break;
            }
            case 5: {
                n = 2;
                break;
            }
            case 3: {
                n = 2;
                break;
            }
            case 2: {
                n = 1;
                break;
            }
            case 1: {
                n = 1;
                break;
            }
            default: {
                throw new RuntimeException("Error - unknown Calendar units [" + n + "]");
            }
        }
        return n;
    }
    
    public final void a(final Date time) {
        this.a.setTime(time);
    }
    
    public final Date a() {
        return this.a.getTime();
    }
    
    public final void a(final int n, final int n2) {
        this.a.add(n, n2);
    }
    
    public final int b(final int n) {
        return this.a.get(n);
    }
    
    public final int c(final int n) {
        return this.a.getMinimum(n);
    }
    
    public final String b() {
        final String b = b(this.a.get(11), 2);
        final String b2 = b(this.a.get(12), 2);
        final String string = this.a.get(5) + "";
        final String string2 = this.a.get(2) + 1 + "";
        final String substring = (this.a.get(1) + "").substring(2, 4);
        String s = "";
        switch (this.b) {
            case 13: {
                s = b + ":" + b2;
                break;
            }
            case 12: {
                s = b;
                break;
            }
            case 11: {
                s = string + "/" + string2;
                break;
            }
            case 5: {
                s = string2 + "/" + substring;
                break;
            }
            case 3: {
                s = string2 + "/" + substring;
                break;
            }
            case 2: {
                s = substring;
                break;
            }
            case 1: {
                s = substring;
                break;
            }
        }
        return s;
    }
    
    public final String c() {
        final e e = this;
        final int b = this.b;
        this = e;
        switch (b) {
            case 13: {
                this.d = "Time in Hours and Minutes";
                break;
            }
            case 12: {
                this.d = "Time in Hours and Minutes";
                break;
            }
            case 11: {
                this.d = "Date";
                break;
            }
            case 5: {
                this.d = "Month and Year";
                break;
            }
            case 3: {
                this.d = "Month and Year";
                break;
            }
            case 2: {
                this.d = "Year";
                break;
            }
            case 1: {
                this.d = "Year";
                break;
            }
        }
        return this.d;
    }
    
    public final boolean d() {
        boolean b = false;
        switch (this.c) {
            case 13: {
                if (this.a.get(13) % 10 == 0) {
                    b = true;
                    break;
                }
                break;
            }
            case 12: {
                if (this.a.get(12) % 10 == 0) {
                    b = true;
                    break;
                }
                break;
            }
            case 11: {
                if (this.a.get(11) % 12 == 0) {
                    b = true;
                    break;
                }
                break;
            }
            case 5: {
                if (this.a.get(5) == this.a.getMinimum(5)) {
                    b = true;
                    break;
                }
                break;
            }
            case 3: {
                if (this.a.get(3) == this.a.getMinimum(3)) {
                    b = true;
                    break;
                }
                break;
            }
            case 2: {
                if (this.a.get(2) == this.a.getMinimum(2)) {
                    b = true;
                    break;
                }
                break;
            }
            case 1: {
                if (this.a.get(1) == this.a.getMinimum(1)) {
                    b = true;
                    break;
                }
                break;
            }
            default: {
                throw new RuntimeException("Error - unknown units specified [" + this.c + "]");
            }
        }
        return b;
    }
    
    private static String b(final int n, final int n2) {
        String s;
        for (s = n + ""; s.length() < 2; s = "0" + s) {}
        return s;
    }
    
    public e() {
    }
    
    public static void a(final Container container, final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int fill, final int anchor, final double weightx, final double n, final int n2, final int n3, final int n4, final int n5) {
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = 0.0;
        if (n2 + n4 + n3 + n5 > 0) {
            gridBagConstraints.insets = new Insets(n2, n3, n4, n5);
        }
        ((GridBagLayout)container.getLayout()).setConstraints(component, gridBagConstraints);
        container.add(component);
    }
}

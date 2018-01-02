// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util;

import java.util.StringTokenizer;
import java.util.Date;

public class $BC
{
    protected Date $CC;
    protected Date $DC;
    
    public $BC(String trim) {
        this.$CC = null;
        this.$DC = null;
        if (trim == null || trim.trim().length() == 0) {
            return;
        }
        trim = trim.trim();
        final int index = trim.indexOf(45);
        if (index >= 0) {
            (this.$DC = getDate(trim.substring(index + 1))).setHours(23);
            this.$DC.setMinutes(59);
            this.$DC.setSeconds(59);
            if (index != 0) {
                this.$CC = getDate(trim.substring(0, index));
            }
        }
        else {
            this.$CC = getDate(trim);
        }
    }
    
    public $BC(final Date $cc, final Date $dc) {
        this.$CC = null;
        this.$DC = null;
        this.$CC = $cc;
        this.$DC = $dc;
    }
    
    public boolean after(final Date date) {
        return date != null && this.$DC != null && date.after(this.$DC);
    }
    
    public boolean before(final Date date) {
        return date != null && this.$CC != null && date.before(this.$CC);
    }
    
    public boolean contains(final Date date) {
        return !this.before(date) && !this.after(date);
    }
    
    public static Date getDate(final String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ".", false);
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        final int int2 = Integer.parseInt(stringTokenizer.nextToken());
        int int3 = Integer.parseInt(stringTokenizer.nextToken());
        if (int3 < 100) {
            int3 += (1900 + new Date().getYear()) / 100 * 100;
        }
        return new Date(int3 - 1900, int2 - 1, int1);
    }
    
    public static String getString(final Date date) {
        if (date == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(8);
        sb.append(date.getDate());
        sb.append('.');
        sb.append(date.getMonth() + 1);
        sb.append('.');
        sb.append(date.getYear() + 1900);
        return sb.toString();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(getString(this.$CC));
        if (this.$DC != null) {
            sb.append('-');
            sb.append(getString(this.$DC));
        }
        return sb.toString();
    }
}

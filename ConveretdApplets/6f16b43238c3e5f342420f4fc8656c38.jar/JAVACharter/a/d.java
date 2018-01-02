// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import java.util.GregorianCalendar;
import java.util.Enumeration;
import JAVACharter.b.b;
import java.util.Hashtable;
import java.util.Date;
import java.net.URL;

public abstract class d
{
    protected f if;
    protected c int;
    protected URL try;
    protected Date do;
    protected Date null;
    protected Date goto;
    protected Date b;
    protected Date void;
    protected boolean long;
    protected int new;
    protected String byte;
    private static final int else = 0;
    private static final int char = 1;
    private static final int for = 0;
    private static final int a = 1;
    private static final int case = 2;
    
    public d(final f if1, final c int1, final URL try1) {
        this.void = null;
        this.long = false;
        this.new = 0;
        this.byte = "";
        this.if = if1;
        this.int = int1;
        this.try = try1;
    }
    
    public abstract Hashtable a(final String p0, final String p1, final int p2, final int p3, final Date p4, final Date p5);
    
    public abstract void a(final b p0, final int p1, final int p2);
    
    public abstract void if(final b p0, final int p1);
    
    public abstract void do(final b p0, final int p1);
    
    public abstract void a(final b p0, final int p1);
    
    public abstract void a(final int p0, final boolean p1);
    
    public abstract void a(final JAVACharter.b.f p0, final b p1, final int p2, final int p3);
    
    public abstract void a(final b p0, final boolean p1, final int p2);
    
    public abstract void a(final JAVACharter.b.d[] p0, final String p1);
    
    public boolean a(final int n, final JAVACharter.b.f f, final b b) {
        boolean b2 = false;
        boolean b3 = false;
        switch (n) {
            case 6: {
                this.int.for(JAVACharter.util.b.for(this.int.int()));
                break;
            }
            case 7: {
                this.int.for(JAVACharter.util.b.long(this.int.int()));
                break;
            }
            case 8: {
                this.int.for(JAVACharter.util.b.case(this.int.int()));
            }
            case 2: {
                this.int.for(JAVACharter.util.b.do(this.int.int()));
                break;
            }
            case 3: {
                this.int.for(JAVACharter.util.b.byte(this.int.int()));
                break;
            }
            case 4: {
                this.int.for(JAVACharter.util.b.int(this.int.int()));
                break;
            }
            case 5: {
                this.int.for(JAVACharter.util.b.if(this.int.int()));
                break;
            }
        }
        final int if1 = f.if(this.int.int());
        final int if2 = f.if(this.int.case());
        if (if1 > 0) {
            b3 = true;
        }
        if (if2 > 0) {
            b2 = true;
        }
        if (b2 && b3) {
            b.a(if1, if2);
            return true;
        }
        return false;
    }
    
    public void a(final String s, final String s2, b for1, JAVACharter.b.f f, final int n, final int n2) {
        switch (n) {
            case 9: {
                this.new = n2;
                break;
            }
            case 6: {
                this.new = n2 * 5;
                break;
            }
            case 7: {
                this.new = n2 * 15;
                break;
            }
            case 8: {
                this.new = n2 * 60;
                break;
            }
            case 1: {
                this.new = n2;
                break;
            }
            case 2: {
                this.new = n2 * 7;
                break;
            }
            case 3: {
                this.new = n2 * 35;
                break;
            }
            case 4: {
                this.new = n2;
                break;
            }
            case 5: {
                this.new = n2;
                break;
            }
        }
        final Date int1 = this.int.int();
        final Date case1 = this.int.case();
        boolean b = false;
        boolean b2 = false;
        int n3 = 0;
        boolean b3 = false;
        if (f != null) {
            final int if1 = f.if(int1);
            switch (if1) {
                case -3: {
                    n3 = this.new;
                    this.null = int1;
                    this.do = case1;
                    b3 = true;
                    b2 = true;
                    break;
                }
                case -2: {
                    n3 = this.new;
                    this.null = int1;
                    this.do = f.a(0);
                    b3 = true;
                    for1.if();
                    break;
                }
                case -1: {
                    b3 = false;
                    break;
                }
                default: {
                    if (if1 < this.new) {
                        n3 = this.new - if1;
                        this.null = f.a(0);
                        this.do = this.null;
                        for1.if();
                        b3 = true;
                        break;
                    }
                    n3 = 0;
                    b3 = false;
                    break;
                }
            }
            if (if1 != -3) {
                switch (f.if(case1)) {
                    case -1: {
                        n3 = this.new;
                        this.goto = case1;
                        this.b = f.a(f.new() - 1);
                        for1.do();
                        b = true;
                        break;
                    }
                    default: {
                        b = false;
                        break;
                    }
                }
            }
        }
        else {
            n3 = this.new;
            this.null = int1;
            this.do = case1;
            b3 = true;
            b2 = true;
        }
        if (b3) {
            Hashtable hashtable;
            if (this.int.for()) {
                hashtable = this.a(s, s2, 1, n3, this.null, this.do);
            }
            else {
                hashtable = this.a(s, s2, 9, n3, this.null, this.do);
            }
            if (hashtable.containsKey("error")) {
                this.long = true;
            }
            else {
                if (for1 == null) {
                    final JAVACharter.b.d[] a = this.int.a();
                    if (hashtable.containsKey("sid")) {
                        final String string = Long.toString(((long[])hashtable.get("sid"))[0]);
                        this.a(a, string);
                        for1 = a[0].for(string);
                        f = (JAVACharter.b.f)for1.for();
                    }
                    else {
                        this.long = true;
                    }
                }
                if (!this.long) {
                    final Enumeration<String> keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final String s3 = keys.nextElement();
                        for1.a(s3, 0, hashtable.get(s3));
                    }
                }
            }
        }
        if (b && !this.long) {
            Hashtable hashtable2;
            if (this.int.for()) {
                hashtable2 = this.a(s, s2, 1, 0, this.b, this.goto);
            }
            else {
                hashtable2 = this.a(s, s2, 9, 0, this.b, this.goto);
            }
            if (hashtable2.containsKey("error")) {
                this.long = true;
            }
            else {
                final int new1 = f.new();
                final Enumeration keys2 = hashtable2.keys();
                while (keys2.hasMoreElements()) {
                    final String s4 = keys2.nextElement();
                    try {
                        for1.a(s4, new1, hashtable2.get(s4));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace(System.out);
                        System.out.println(ex);
                    }
                }
            }
        }
        if (!this.long) {
            int n4 = 0;
            int n5 = 0;
            final int if2 = f.if(JAVACharter.util.b.try(this.int.int()));
            if (if2 >= 0) {
                n4 = if2;
            }
            else if (if2 == -2) {
                n4 = 0;
            }
            final int if3 = f.if(JAVACharter.util.b.try(this.int.case()));
            if (if3 >= 0) {
                n5 = if3;
            }
            else if (if3 == -1) {
                n5 = f.new() - 1;
            }
            if (b2) {
                if (!this.int.do()) {
                    this.int.a(f.a(n4));
                    this.int.if(f.a(n5));
                    this.int.new();
                }
                this.int.for(this.int.try());
                this.int.do(this.int.char());
            }
            for1.a(n4, n5);
            this.if.if(f.a(n4), f.a(n5));
        }
    }
    
    public void a(final int n, final JAVACharter.b.d d, final JAVACharter.b.d d2, final JAVACharter.b.f f, final b b, final JAVACharter.b.f f2, final b b2) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        final int n2 = -1 * this.new;
        final int a = f.a();
        final int n3 = a + this.new;
        final Date do1 = f.do(n2);
        gregorianCalendar.setTime(do1);
        int value = gregorianCalendar.get(1);
        int value2 = gregorianCalendar.get(2);
        int n4 = value2 / 3;
        gregorianCalendar.get(4);
        gregorianCalendar.get(5);
        int value3 = gregorianCalendar.get(7);
        int value4 = gregorianCalendar.get(11);
        final int value5 = gregorianCalendar.get(12);
        int n5 = value5 / 15;
        int n6 = value5 / 5;
        this.void = this.a(do1, n);
        int n7 = 0;
        int n8 = 0;
        this.a(b, n2, n3);
        for (int i = n2 + 1; i < a; ++i) {
            gregorianCalendar.setTime(f.do(i));
            final int value6 = gregorianCalendar.get(1);
            final int value7 = gregorianCalendar.get(2);
            final int n9 = value7 / 3;
            gregorianCalendar.get(4);
            gregorianCalendar.get(5);
            final int value8 = gregorianCalendar.get(7);
            final int value9 = gregorianCalendar.get(11);
            final int value10 = gregorianCalendar.get(12);
            final int n10 = value10 / 15;
            final int n11 = value10 / 5;
            boolean b3 = false;
            switch (n) {
                case 6: {
                    if (n11 == n6) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 7: {
                    if (n10 == n5) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 8: {
                    if (value9 == value4) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (value8 > value3) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 3: {
                    if (value7 == value2) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (n9 == n4) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 5: {
                    if (value6 == value) {
                        b3 = true;
                        break;
                    }
                    break;
                }
            }
            if (i == a - 1) {
                b3 = false;
            }
            if (b3) {
                this.if(b, i);
            }
            else {
                final int if1 = f2.if(this.void);
                switch (if1) {
                    case -3:
                    case -2: {
                        this.a(n7, true);
                        ++n7;
                        break;
                    }
                    case -1: {
                        this.a(n8, false);
                        ++n8;
                        break;
                    }
                    default: {
                        this.a(b2, if1);
                        break;
                    }
                }
                this.a(f, b, i, n);
            }
            value = value6;
            n4 = n9;
            value2 = value7;
            value3 = value8;
            value4 = value9;
            n5 = n10;
            n6 = n11;
        }
        if (n7 > 0) {
            this.a(b2, true, n7);
        }
        if (n8 > 0) {
            this.a(b2, false, n8);
        }
        int if2 = f2.if(this.int.int());
        int if3 = f2.if(this.int.case());
        if (if2 == -2 || if2 == -3) {
            if2 = 0;
        }
        if (if3 == -1) {
            if3 = f2.new() - 1;
        }
        if (if2 == if3 && if2 != 0) {
            --if2;
        }
        b2.a(if2, if3);
    }
    
    public Date a(final Date date, final int n) {
        Date date3 = null;
        switch (n) {
            case 2: {
                Date date2 = new Date(date.getTime() - 86400000L);
                if (date2.getHours() == 23) {
                    date2 = new Date(date2.getTime() + 3600000L);
                }
                while (date2.getDay() != 0) {
                    date2 = new Date(date2.getTime() - 86400000L);
                    if (date2.getHours() == 23) {
                        date2 = new Date(date2.getTime() + 3600000L);
                    }
                    else {
                        if (date2.getHours() != 1) {
                            continue;
                        }
                        date2 = new Date(date2.getTime() - 3600000L);
                    }
                }
                date3 = date2;
                break;
            }
            default: {
                date3 = date;
                break;
            }
        }
        return date3;
    }
}

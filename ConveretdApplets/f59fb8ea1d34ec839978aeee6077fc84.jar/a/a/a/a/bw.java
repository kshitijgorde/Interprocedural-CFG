// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Enumeration;
import java.util.Hashtable;

public class bw
{
    bh do;
    bh a;
    int for;
    int if;
    
    public bw() {
        this.do = null;
        this.a = null;
    }
    
    public void if(final String do1) {
        if (do1.compareTo("\n") == 0) {
            return;
        }
        if (this.a == null) {
            return;
        }
        b2 case1;
        for (b2 if1 = case1 = this.a.case; if1 != null; if1 = case1.if) {
            case1 = if1;
        }
        final b2 b2 = new b2();
        b2.do = do1;
        b2.if = null;
        if (this.a.case == null) {
            this.a.case = b2;
        }
        else {
            case1.if = b2;
        }
    }
    
    public void a(final String s, final int a) {
        if (s.compareTo("\n") == 0) {
            return;
        }
        if (this.a == null) {
            return;
        }
        b2 int1;
        for (b2 if1 = int1 = this.a.int; if1 != null; if1 = int1.if) {
            int1 = if1;
        }
        final b2 b2 = new b2();
        b2.do = s.substring(2, s.length() - 2);
        b2.a = a;
        b2.if = null;
        if (this.a.int == null) {
            this.a.int = b2;
        }
        else {
            int1.if = b2;
        }
    }
    
    public void a(final String a, final Hashtable hashtable) {
        final bh a2 = new bh();
        a2.a = a;
        a2.if = null;
        a2.for = null;
        a2.do = hashtable.size();
        a2.case = null;
        a2.try = new String[a2.do];
        a2.new = new String[a2.do];
        final Enumeration<String> keys = hashtable.keys();
        for (int i = 0; i < a2.do; ++i) {
            try {
                a2.try[i] = keys.nextElement();
                a2.new[i] = hashtable.get(a2.try[i]);
            }
            catch (Exception ex) {}
        }
        while (this.if > this.for) {
            this.a = this.a.byte;
            --this.if;
        }
        if (this.do == null) {
            a2.byte = null;
            this.do = a2;
            this.a = this.do;
        }
        else if (this.if == this.for) {
            this.a.for = a2;
            a2.byte = this.a.byte;
            this.a = a2;
        }
        else {
            this.a.if = a2;
            a2.byte = this.a;
            this.a = a2;
        }
        this.if = this.for;
        ++this.for;
    }
    
    public void a(final String s) {
        --this.for;
    }
    
    public void if() {
        this.for = 0;
        this.if = 0;
        this.do = null;
    }
    
    public void a() {
    }
}

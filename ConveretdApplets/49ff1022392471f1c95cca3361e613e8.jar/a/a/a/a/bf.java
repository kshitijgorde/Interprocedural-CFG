// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Enumeration;
import java.util.Hashtable;

public class bf
{
    a2 do;
    a2 a;
    int for;
    int if;
    
    public bf() {
        this.do = null;
        this.a = null;
    }
    
    public void if(final String if1) {
        if (if1.compareTo("\n") == 0) {
            return;
        }
        if (this.a == null) {
            return;
        }
        bk case1;
        for (bk a = case1 = this.a.case; a != null; a = case1.a) {
            case1 = a;
        }
        final bk bk = new bk();
        bk.if = if1;
        bk.a = null;
        if (this.a.case == null) {
            this.a.case = bk;
        }
        else {
            case1.a = bk;
        }
    }
    
    public void do(final String s) {
        if (s.compareTo("\n") == 0) {
            return;
        }
        if (this.a == null) {
            return;
        }
        bk int1;
        for (bk a = int1 = this.a.int; a != null; a = int1.a) {
            int1 = a;
        }
        final bk bk = new bk();
        bk.if = s.substring(2, s.length() - 2);
        bk.a = null;
        if (this.a.int == null) {
            this.a.int = bk;
        }
        else {
            int1.a = bk;
        }
    }
    
    public void a(final String a, final Hashtable hashtable) {
        final a2 a2 = new a2();
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

import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ae
{
    n do;
    n a;
    int for;
    int if;
    
    public ae() {
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
        m case1;
        for (m if1 = case1 = this.a.case; if1 != null; if1 = case1.if) {
            case1 = if1;
        }
        final m m = new m();
        m.do = do1;
        m.if = null;
        if (this.a.case == null) {
            this.a.case = m;
        }
        else {
            case1.if = m;
        }
    }
    
    public void a(final String s, final int a) {
        if (s.compareTo("\n") == 0) {
            return;
        }
        if (this.a == null) {
            return;
        }
        m int1;
        for (m if1 = int1 = this.a.int; if1 != null; if1 = int1.if) {
            int1 = if1;
        }
        final m m = new m();
        m.do = s.substring(2, s.length() - 2);
        m.a = a;
        m.if = null;
        if (this.a.int == null) {
            this.a.int = m;
        }
        else {
            int1.if = m;
        }
    }
    
    public void a(final String a, final Hashtable hashtable) {
        final n a2 = new n();
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

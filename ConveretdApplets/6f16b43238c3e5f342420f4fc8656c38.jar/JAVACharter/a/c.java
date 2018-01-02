// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import JAVACharter.util.b;
import JAVACharter.b.d;
import java.util.Date;

public class c
{
    private Date goto;
    private Date long;
    private Date for;
    private Date null;
    private Date char;
    private Date do;
    private Date try;
    private Date case;
    private boolean new;
    private boolean int;
    private boolean byte;
    private boolean a;
    private boolean else;
    private d[] if;
    private d[] void;
    
    public c(final d[] if1, final d[] void1) {
        this.new = true;
        this.int = true;
        this.byte = true;
        this.a = false;
        this.else = false;
        this.if = if1;
        this.void = void1;
    }
    
    public boolean byte() {
        return this.int;
    }
    
    public void do(final boolean int1) {
        this.int = int1;
    }
    
    public boolean if() {
        return this.byte;
    }
    
    public void if(final boolean byte1) {
        this.byte = byte1;
    }
    
    public void a(final boolean new1) {
        this.new = new1;
    }
    
    public boolean for() {
        return this.new;
    }
    
    public d[] a() {
        if (this.new) {
            return this.if;
        }
        return this.void;
    }
    
    public void for(final Date date) {
        if (this.new) {
            this.for = b.try(date);
        }
        else {
            this.try = b.try(date);
        }
    }
    
    public void do(final Date date) {
        if (this.new) {
            this.null = date;
        }
        else {
            this.case = date;
        }
    }
    
    public void a(final Date date) {
        if (this.new) {
            this.goto = date;
        }
        else {
            this.char = date;
        }
    }
    
    public void if(final Date date) {
        if (this.new) {
            this.long = date;
        }
        else {
            this.do = date;
        }
    }
    
    public boolean do() {
        if (this.new) {
            return this.a;
        }
        return this.else;
    }
    
    public void new() {
        if (this.new) {
            this.a = true;
        }
        else {
            this.else = true;
        }
    }
    
    public Date int() {
        if (this.new) {
            return this.for;
        }
        return this.try;
    }
    
    public Date case() {
        if (this.new) {
            return this.null;
        }
        return this.case;
    }
    
    public Date try() {
        if (this.new) {
            return this.goto;
        }
        return this.char;
    }
    
    public Date char() {
        if (this.new) {
            return this.long;
        }
        return this.do;
    }
}

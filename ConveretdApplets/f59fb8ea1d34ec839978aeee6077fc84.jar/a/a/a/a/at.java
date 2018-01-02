// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class at
{
    private az a;
    private bi if;
    
    at() {
        this.a = null;
        this.if = null;
        this.a = new az();
        this.if = new bi();
    }
    
    public void a() {
        this.a = null;
        this.if.a();
        this.if = null;
    }
    
    void a(final bi bi, final bi bi2) {
        if (bi.char > bi2.char) {
            this.if.char = bi2.char;
            switch (bi2.char) {
                case 1: {
                    this.if.long = bi2.long;
                    break;
                }
                case 2: {
                    this.if.case = bi2.case;
                    break;
                }
                case 3: {
                    this.if.else = bi2.else;
                    break;
                }
                case 4: {
                    this.if.int = bi2.int;
                    break;
                }
                case 5: {
                    this.if.a = bi2.a;
                    break;
                }
            }
            bi2.char = bi.char;
            this.a.a(bi2, this.if, true);
        }
        else if (bi.char < bi2.char) {
            this.if.char = bi.char;
            switch (bi.char) {
                case 1: {
                    this.if.long = bi.long;
                    break;
                }
                case 2: {
                    this.if.case = bi.case;
                    break;
                }
                case 3: {
                    this.if.else = bi.else;
                    break;
                }
                case 4: {
                    this.if.int = bi.int;
                    break;
                }
                case 5: {
                    this.if.a = bi.a;
                    break;
                }
            }
            bi.char = bi2.char;
            this.a.a(bi, this.if, true);
        }
    }
    
    void byte(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char == 5 || bi3.char == 5) {
            throw new a5("invalid type for a + operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 4: {
                bi.int = i.a(bi.int, bi2.int, bi3.int);
                break;
            }
            case 3: {
                bi.else = bi2.else + bi3.else;
                break;
            }
            case 2: {
                bi.case = bi2.case + bi3.case;
                break;
            }
            case 1: {
                bi.long = (bi2.long || bi3.long);
                break;
            }
        }
    }
    
    void new(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 3 || bi3.char > 3) {
            throw new a5("invalid type for a - operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 3: {
                bi.else = bi2.else - bi3.else;
                break;
            }
            case 2: {
                bi.case = bi2.case - bi3.case;
                break;
            }
            case 1: {
                bi.long = (bi2.long ^ bi3.long);
                break;
            }
        }
    }
    
    void do(final bi bi, final bi bi2) throws a5 {
        if (bi2.char > 3) {
            throw new a5("invalid type for a -(unary) operation");
        }
        switch (bi.char = bi2.char) {
            case 3: {
                bi.else = -bi2.else;
                break;
            }
            case 2: {
                bi.case = -bi2.case;
                break;
            }
            case 1: {
                bi.long = !bi2.long;
                break;
            }
        }
    }
    
    void int(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 3 || bi3.char > 3) {
            throw new a5("invalid type for a * operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 3: {
                bi.else = bi2.else * bi3.else;
                break;
            }
            case 2: {
                bi.case = bi2.case * bi3.case;
                break;
            }
            case 1: {
                bi.long = (bi2.long && bi3.long);
                break;
            }
        }
    }
    
    void else(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 3 || bi3.char > 3) {
            throw new a5("invalid type for a / operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 3: {
                bi.else = bi2.else / bi3.else;
                break;
            }
            case 2: {
                if (bi3.case == 0L) {
                    throw new a5("division by 0");
                }
                bi.case = bi2.case / bi3.case;
                break;
            }
            case 1: {
                bi.long = (bi2.long && bi3.long);
                break;
            }
        }
    }
    
    void try(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 2 || bi3.char > 2) {
            throw new a5("invalid type for a binary and operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 2: {
                bi.case = (bi2.case & bi3.case);
                break;
            }
            case 1: {
                bi.long = (bi2.long & bi3.long);
                break;
            }
        }
    }
    
    void void(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 2 || bi3.char > 2) {
            throw new a5("invalid type for a binary or operation");
        }
        this.a(bi2, bi3);
        bi.char = bi2.char;
        switch (bi2.char) {
            case 2: {
                bi.case = (bi2.case | bi3.case);
                break;
            }
            case 1: {
                bi.long = (bi2.long | bi3.long);
                break;
            }
        }
    }
    
    void do(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 1 || bi3.char > 1) {
            throw new a5("invalid type for an and operation");
        }
        bi.long = (bi2.long && bi3.long);
    }
    
    void long(final bi bi, final bi bi2, final bi bi3) throws a5 {
        if (bi2.char > 1 || bi3.char > 1) {
            throw new a5("invalid type for an or operation");
        }
        bi.long = (bi2.long || bi3.long);
    }
    
    void if(final bi bi, final bi bi2) throws a5 {
        if (bi2.char > 1) {
            throw new a5("invalid type for a not operation");
        }
        bi.long = !bi2.long;
    }
    
    void case(final bi bi, final bi bi2, final bi bi3) {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) == 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else == bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case == bi3.case);
                break;
            }
            case 1: {
                bi.long = (bi2.long == bi3.long);
                break;
            }
        }
    }
    
    void goto(final bi bi, final bi bi2, final bi bi3) throws a5 {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) > 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else > bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case > bi3.case);
                break;
            }
            case 1: {
                throw new a5("invalid type for a more operation");
            }
        }
    }
    
    void for(final bi bi, final bi bi2, final bi bi3) throws a5 {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) < 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else < bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case < bi3.case);
                break;
            }
            case 1: {
                throw new a5("invalid type for a less operation");
            }
        }
    }
    
    void if(final bi bi, final bi bi2, final bi bi3) throws a5 {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) >= 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else >= bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case >= bi3.case);
                break;
            }
            case 1: {
                throw new a5("invalid type for a more or equal operation");
            }
        }
    }
    
    void a(final bi bi, final bi bi2, final bi bi3) throws a5 {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) <= 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else <= bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case <= bi3.case);
                break;
            }
            case 1: {
                throw new a5("invalid type for a less or equal operation");
            }
        }
    }
    
    void char(final bi bi, final bi bi2, final bi bi3) {
        this.a(bi2, bi3);
        bi.char = 1;
        switch (bi2.char) {
            case 4: {
                if (i.if(bi2.int, bi3.int) != 0) {
                    bi.long = true;
                    break;
                }
                bi.long = false;
                break;
            }
            case 3: {
                bi.long = (bi2.else != bi3.else);
                break;
            }
            case 2: {
                bi.long = (bi2.case != bi3.case);
                break;
            }
            case 1: {
                bi.long = (bi2.long ^ bi3.long);
                break;
            }
        }
    }
}

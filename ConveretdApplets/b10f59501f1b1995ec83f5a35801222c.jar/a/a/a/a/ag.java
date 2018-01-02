// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ag
{
    private al a;
    private a3 if;
    
    ag() {
        this.a = null;
        this.if = null;
        this.a = new al();
        this.if = new a3();
    }
    
    public void a() {
        this.a = null;
        this.if.a();
        this.if = null;
    }
    
    void a(final a3 a3, final a3 a4) {
        if (a3.char > a4.char) {
            this.if.char = a4.char;
            switch (a4.char) {
                case 1: {
                    this.if.long = a4.long;
                    break;
                }
                case 2: {
                    this.if.case = a4.case;
                    break;
                }
                case 3: {
                    this.if.else = a4.else;
                    break;
                }
                case 4: {
                    this.if.int = a4.int;
                    break;
                }
                case 5: {
                    this.if.a = a4.a;
                    break;
                }
            }
            a4.char = a3.char;
            this.a.a(a4, this.if, true);
        }
        else if (a3.char < a4.char) {
            this.if.char = a3.char;
            switch (a3.char) {
                case 1: {
                    this.if.long = a3.long;
                    break;
                }
                case 2: {
                    this.if.case = a3.case;
                    break;
                }
                case 3: {
                    this.if.else = a3.else;
                    break;
                }
                case 4: {
                    this.if.int = a3.int;
                    break;
                }
                case 5: {
                    this.if.a = a3.a;
                    break;
                }
            }
            a3.char = a4.char;
            this.a.a(a3, this.if, true);
        }
    }
    
    void byte(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char == 5 || a5.char == 5) {
            throw new ar("invalid type for a + operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 4: {
                a3.int = g.a(a3.int, a4.int, a5.int);
                break;
            }
            case 3: {
                a3.else = a4.else + a5.else;
                break;
            }
            case 2: {
                a3.case = a4.case + a5.case;
                break;
            }
            case 1: {
                a3.long = (a4.long || a5.long);
                break;
            }
        }
    }
    
    void new(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 3 || a5.char > 3) {
            throw new ar("invalid type for a - operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 3: {
                a3.else = a4.else - a5.else;
                break;
            }
            case 2: {
                a3.case = a4.case - a5.case;
                break;
            }
            case 1: {
                a3.long = (a4.long != a5.long);
                break;
            }
        }
    }
    
    void do(final a3 a3, final a3 a4) throws ar {
        if (a4.char > 3) {
            throw new ar("invalid type for a -(unary) operation");
        }
        switch (a3.char = a4.char) {
            case 3: {
                a3.else = -a4.else;
                break;
            }
            case 2: {
                a3.case = -a4.case;
                break;
            }
            case 1: {
                a3.long = !a4.long;
                break;
            }
        }
    }
    
    void int(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 3 || a5.char > 3) {
            throw new ar("invalid type for a * operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 3: {
                a3.else = a4.else * a5.else;
                break;
            }
            case 2: {
                a3.case = a4.case * a5.case;
                break;
            }
            case 1: {
                a3.long = (a4.long && a5.long);
                break;
            }
        }
    }
    
    void else(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 3 || a5.char > 3) {
            throw new ar("invalid type for a / operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 3: {
                a3.else = a4.else / a5.else;
                break;
            }
            case 2: {
                if (a5.case == 0L) {
                    throw new ar("division by 0");
                }
                a3.case = a4.case / a5.case;
                break;
            }
            case 1: {
                a3.long = (a4.long && a5.long);
                break;
            }
        }
    }
    
    void try(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 2 || a5.char > 2) {
            throw new ar("invalid type for a binary and operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 2: {
                a3.case = (a4.case & a5.case);
                break;
            }
            case 1: {
                a3.long = (a4.long & a5.long);
                break;
            }
        }
    }
    
    void void(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 2 || a5.char > 2) {
            throw new ar("invalid type for a binary or operation");
        }
        this.a(a4, a5);
        a3.char = a4.char;
        switch (a4.char) {
            case 2: {
                a3.case = (a4.case | a5.case);
                break;
            }
            case 1: {
                a3.long = (a4.long | a5.long);
                break;
            }
        }
    }
    
    void do(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 1 || a5.char > 1) {
            throw new ar("invalid type for an and operation");
        }
        a3.long = (a4.long && a5.long);
    }
    
    void long(final a3 a3, final a3 a4, final a3 a5) throws ar {
        if (a4.char > 1 || a5.char > 1) {
            throw new ar("invalid type for an or operation");
        }
        a3.long = (a4.long || a5.long);
    }
    
    void if(final a3 a3, final a3 a4) throws ar {
        if (a4.char > 1) {
            throw new ar("invalid type for a not operation");
        }
        a3.long = !a4.long;
    }
    
    void case(final a3 a3, final a3 a4, final a3 a5) {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) == 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else == a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case == a5.case);
                break;
            }
            case 1: {
                a3.long = (a4.long == a5.long);
                break;
            }
        }
    }
    
    void goto(final a3 a3, final a3 a4, final a3 a5) throws ar {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) > 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else > a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case > a5.case);
                break;
            }
            case 1: {
                throw new ar("invalid type for a more operation");
            }
        }
    }
    
    void for(final a3 a3, final a3 a4, final a3 a5) throws ar {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) < 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else < a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case < a5.case);
                break;
            }
            case 1: {
                throw new ar("invalid type for a less operation");
            }
        }
    }
    
    void if(final a3 a3, final a3 a4, final a3 a5) throws ar {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) >= 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else >= a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case >= a5.case);
                break;
            }
            case 1: {
                throw new ar("invalid type for a more or equal operation");
            }
        }
    }
    
    void a(final a3 a3, final a3 a4, final a3 a5) throws ar {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) <= 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else <= a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case <= a5.case);
                break;
            }
            case 1: {
                throw new ar("invalid type for a less or equal operation");
            }
        }
    }
    
    void char(final a3 a3, final a3 a4, final a3 a5) {
        this.a(a4, a5);
        a3.char = 1;
        switch (a4.char) {
            case 4: {
                if (g.if(a4.int, a5.int) != 0) {
                    a3.long = true;
                    break;
                }
                a3.long = false;
                break;
            }
            case 3: {
                a3.long = (a4.else != a5.else);
                break;
            }
            case 2: {
                a3.long = (a4.case != a5.case);
                break;
            }
            case 1: {
                a3.long = (a4.long != a5.long);
                break;
            }
        }
    }
}

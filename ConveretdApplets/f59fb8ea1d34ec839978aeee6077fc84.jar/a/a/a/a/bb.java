// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bb
{
    static final int do = 64;
    static final int if = 64;
    static final int e = 256;
    static final int d = 64;
    static final int g = 0;
    static final int void = 1;
    static final int else = 2;
    static final int a = 3;
    b3 case;
    bi[] char;
    int[] f;
    int try;
    int c;
    p goto;
    au for;
    ak new;
    at byte;
    bc int;
    az long;
    private boolean b;
    
    bb(final b3 case1) {
        this.try = 0;
        this.b = false;
        this.char = new bi[258];
        this.c = 0;
        this.f = new int[66];
        this.case = case1;
    }
    
    au do() {
        return this.for;
    }
    
    void a(final p goto1) {
        this.goto = goto1;
        this.for = new au(this.goto.if());
        this.byte = new at();
        this.int = new bc(this.case);
        this.long = new az();
    }
    
    void for() {
        this.byte.a();
        this.byte = null;
        this.int.a();
        this.int = null;
        this.long = null;
        this.f = null;
        for (int i = 0; i < this.char.length; ++i) {
            if (this.char[i] != null) {
                this.char[i].a();
            }
            this.char[i] = null;
        }
        this.char = null;
        this.goto.a();
        this.goto = null;
        this.for.a();
        this.for = null;
    }
    
    bi a(final char[] array) {
        try {
            if (this.b) {
                System.out.println("Recursive external script function call detected. Function call ignored: " + new String(array, 0, i.a(array)));
                return null;
            }
            this.b = true;
            final bi[] array2 = new bi[a.a.a.a.c.do];
            bi bi = null;
            int n = 0;
            int a = -1;
            final au au = new au(array);
            final ak new1 = au.new();
            if (new1.for == 1) {
                int if1;
                if ((if1 = this.goto.if(new1.do)) == -1) {
                    if ((a = this.int.a(new1.do)) == -1) {
                        this.b = false;
                        return null;
                    }
                    if1 = -2;
                }
                final ak new2 = au.new();
                if (new2.for == 0 && new2.do[0] == '(') {
                    ak ak = au.new();
                    int n2 = 0;
                    if (ak.do[0] == '-') {
                        n2 = 1;
                        ak = au.new();
                    }
                    else if (ak.do[0] == '+') {
                        ak = au.new();
                    }
                    while (ak.for == 5 || ak.for == 6 || ak.for == 7 || ak.for == 8) {
                        switch (ak.for) {
                            case 5: {
                                array2[n] = new bi();
                                if (ak.if == 0L) {
                                    array2[n].long = false;
                                }
                                else {
                                    array2[n].long = true;
                                }
                                array2[n].char = 1;
                                break;
                            }
                            case 6: {
                                array2[n] = new bi();
                                array2[n].case = ak.if * ((n2 != 0) ? -1 : 1);
                                array2[n].char = 2;
                                break;
                            }
                            case 7: {
                                array2[n] = new bi();
                                array2[n].else = ak.int * ((n2 != 0) ? -1 : 1);
                                array2[n].char = 3;
                                break;
                            }
                            case 8: {
                                array2[n] = new bi();
                                array2[n].int = ak.do;
                                ak.do = new char[1024];
                                array2[n].char = 4;
                                break;
                            }
                        }
                        ++n;
                        ak = au.new();
                        if (ak.for != 0) {
                            break;
                        }
                        if (ak.do[0] != ',') {
                            break;
                        }
                        ak = au.new();
                        n2 = 0;
                        if (ak.do[0] == '-') {
                            n2 = 1;
                            ak = au.new();
                        }
                        else {
                            if (ak.do[0] != '+') {
                                continue;
                            }
                            ak = au.new();
                        }
                    }
                    if (ak.for != 0 || ak.do[0] != ')') {
                        for (int i = 0; i < n; ++i) {
                            array2[i] = null;
                        }
                        this.b = false;
                        return null;
                    }
                    if (if1 != -2) {
                        final c a2 = this.goto.a(if1);
                        if (n != a2.int) {
                            for (int j = 0; j < n; ++j) {
                                array2[j] = null;
                            }
                            this.b = false;
                            return null;
                        }
                        for (int k = 0; k < n; ++k) {
                            array2[k].do = a2.a[k].do;
                            this.a(true, array2[k]);
                        }
                        this.for.a(a2.new);
                        this.if(true);
                        bi = this.char[this.c - 1];
                        this.case();
                    }
                    else {
                        bi = new bi();
                        this.int.a(bi, array2, a, n);
                    }
                }
            }
            this.b = false;
            return bi;
        }
        catch (a5 a3) {
            a3.a();
            System.out.print(this.case.c + this.do().try());
            a3.a(this.do());
            for (int l = 0; l < this.c; ++l) {
                this.char[l] = null;
            }
            this.c = 0;
            this.b = false;
            System.out.println(String.valueOf(this.case.B) + " script call \"" + new String(array, 0, i.a(array)) + "\" aborted.\n");
            return new bi();
        }
    }
    
    void a() throws a5 {
        this.int();
        this.else();
        this.new = this.for.new();
        if (this.new.for != 9) {
            throw new a5("end of file expected");
        }
    }
    
    int a(final boolean b, final char[] array) {
        if (b && this.c > 0) {
            for (int i = this.f[this.try]; i < this.c; ++i) {
                if (i.a(this.char[i].do, array, 31) == 0) {
                    return i;
                }
            }
        }
        else if (!b) {
            return this.goto.do(array);
        }
        return -1;
    }
    
    void a(final boolean b, final bi bi) throws a5 {
        if (this.a(b, bi.do) != -1) {
            throw new a5("variable redefinition");
        }
        if (b) {
            this.char[this.c] = bi;
            ++this.c;
            if (this.c > 256) {
                throw new a5("stack overflow");
            }
        }
        else {
            this.goto.a(bi);
        }
    }
    
    void case() {
        for (int i = this.f[this.try]; i < this.c; ++i) {
            this.char[this.c - 1] = null;
            --this.c;
        }
        this.c = this.f[this.try];
    }
    
    void new() throws a5 {
        if (this.new.for != 0 || this.new.do[0] != ';') {
            throw new a5("';' expected");
        }
    }
    
    void try() throws a5 {
        while (this.new.for == 2 && this.new.if == 0L) {
            this.char();
            this.new = this.for.new();
            this.byte();
            if (this.new.for == 9) {
                throw new a5("unexpected end of file");
            }
            final int else1 = this.for.else();
            this.new = this.for.new();
            if (this.new.for != 2 || this.new.if != 1L) {
                this.for.a(else1);
                return;
            }
            this.new = this.for.new();
        }
        this.byte();
    }
    
    void char() throws a5 {
        int n = 0;
        this.new = this.for.new();
        if (this.new.for != 0 || this.new.do[0] != '(') {
            throw new a5("'(' expected.");
        }
        while (true) {
            if (this.new.for == 0 && this.new.do[0] == '(') {
                ++n;
            }
            else if (this.new.for == 0 && this.new.do[0] == ')') {
                if (--n == 0) {
                    return;
                }
            }
            else if (this.new.for == 9) {
                throw new a5("unexpected end of file");
            }
            this.new = this.for.new();
        }
    }
    
    void byte() throws a5 {
        int n = 0;
        while (true) {
            if (this.new.for == 0 && this.new.do[0] == '{') {
                ++n;
            }
            else if (this.new.for == 0 && this.new.do[0] == '}') {
                if (--n == 0) {
                    return;
                }
            }
            else if (this.new.for == 0 && this.new.do[0] == ';' && n == 0) {
                if (n == 0) {
                    return;
                }
            }
            else if (this.new.for == 9) {
                throw new a5("unexpected end of file");
            }
            this.new = this.for.new();
        }
    }
    
    void a(final boolean b) throws a5 {
        this.new = this.for.new();
        while (this.new.for == 2 && this.new.if == 5L) {
            this.new = this.for.new();
            if (this.new.for == 1) {
                final bi bi = new bi();
                i.for(this.new.do, bi.do);
                this.a(b, bi);
                Label_0103: {
                    try {
                        this.if();
                        break Label_0103;
                    }
                    catch (a5 a5) {
                        if (this.new.do[0] != ';') {
                            throw a5;
                        }
                        break Label_0103;
                    }
                    throw new a5("identifier expected");
                }
                this.new = this.for.new();
                continue;
            }
            throw new a5("identifier expected");
        }
    }
    
    void int() throws a5 {
        this.new = this.for.new();
        if (this.new.for == 2 && this.new.if == 6L) {
            this.new = this.for.new();
            if (this.new.for != 0 || this.new.do[0] != '{') {
                throw new a5("'{' expected");
            }
            this.a(false);
            if (this.new.for != 0 || this.new.do[0] != '}') {
                throw new a5("'}' expected");
            }
        }
    }
    
    void else() throws a5 {
        this.new = this.for.new();
        while (this.new.for == 2 && this.new.if == 7L) {
            this.new = this.for.new();
            if (this.new.for != 1) {
                throw new a5("identifier expected");
            }
            if (this.goto.if(this.new.do) != -1) {
                throw new a5("function redefinition");
            }
            final c c = new c();
            i.for(this.new.do, c.if);
            int int1 = 0;
            this.new = this.for.new();
            if (this.new.for != 0 || this.new.do[0] != '(') {
                throw new a5("'(' expected");
            }
            this.new = this.for.new();
            while (this.new.for == 1) {
                for (int i = 0; i < int1; ++i) {
                    if (i.a(c.a[i].do, this.new.do, 31) == 0) {
                        throw new a5("duplicate argument");
                    }
                }
                if (int1 > a.a.a.a.c.do) {
                    throw new a5("too many arguments");
                }
                final bi bi = new bi();
                i.for(this.new.do, bi.do);
                bi.case = 0L;
                bi.char = 2;
                c.a[int1] = bi;
                ++int1;
                this.new = this.for.new();
                if (this.new.for != 0 || this.new.do[0] != ',') {
                    break;
                }
                this.new = this.for.new();
            }
            if (this.new.for != 0 || this.new.do[0] != ')') {
                throw new a5("')' expected");
            }
            c.int = int1;
            c.new = this.for.else();
            this.goto.a(c);
            this.byte();
            this.new = this.for.new();
        }
    }
    
    int if() throws a5 {
        if (this.new.for == 1) {
            final char[] array = new char[1024];
            i.for(this.new.do, array);
            this.new = this.for.new();
            if (this.new.for == 4) {
                boolean b = false;
                int n;
                if ((n = this.a(true, array)) == -1) {
                    if ((n = this.a(false, array)) == -1) {
                        throw new a5("left operator must be a variable");
                    }
                }
                else {
                    b = true;
                }
                final bi bi = new bi();
                this.new = this.for.new();
                this.try(bi);
                if (b) {
                    this.long.a(this.char[n], bi, false);
                }
                else {
                    this.goto.a(n, bi);
                }
            }
            else if (this.new.for == 0 && this.new.do[0] == '(') {
                this.a(array, new bi());
            }
            else {
                if (this.new.for != 0 || this.new.do[0] != '[') {
                    throw new a5("'=' expected");
                }
                boolean b2 = false;
                int n2;
                if ((n2 = this.a(true, array)) == -1) {
                    if ((n2 = this.a(false, array)) == -1) {
                        throw new a5("left operator must be a variable");
                    }
                }
                else {
                    b2 = true;
                }
                final bi bi2 = new bi();
                this.new = this.for.new();
                this.try(bi2);
                bi if1;
                if (b2) {
                    if1 = this.char[n2];
                }
                else {
                    if1 = this.goto.if(n2);
                }
                int n3;
                if (bi2.char == 3) {
                    n3 = (int)bi2.else;
                }
                else {
                    if (bi2.char != 2) {
                        throw new a5("Value must be an integer");
                    }
                    n3 = (int)bi2.case;
                }
                if (if1.char == 5) {
                    if (n3 < 0 || n3 >= if1.goto) {
                        throw new a5("Array out of bound exception. Array size is " + if1.goto + " and you try to access element at " + n3);
                    }
                    final bi bi3 = if1.a[n3];
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new a5("']' expected");
                    }
                    this.new = this.for.new();
                    if (this.new.for != 4) {
                        throw new a5("'=' expected!");
                    }
                    this.new = this.for.new();
                    this.try(bi2);
                    this.long.a(bi3, bi2, false);
                }
                else {
                    if1.char = 4;
                    if1.a(n3);
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new a5("']' expected");
                    }
                    this.new = this.for.new();
                    if (this.new.for != 4) {
                        throw new a5("'=' expected!");
                    }
                    this.new = this.for.new();
                    this.try(bi2);
                    int n4;
                    if (bi2.char == 3) {
                        n4 = (int)bi2.else;
                    }
                    else {
                        if (bi2.char != 2) {
                            throw new a5("Right operand value must be an integer");
                        }
                        n4 = (int)bi2.case;
                    }
                    if1.int[n3] = (char)n4;
                }
            }
            this.new();
            return 0;
        }
        if (this.new.for == 2) {
            switch ((int)this.new.if) {
                case 4: {
                    this.new = this.for.new();
                    if (this.new.for != 0 || this.new.do[0] != ';') {
                        this.try(this.char[this.c - 1]);
                        this.new();
                    }
                    return 2;
                }
                case 0: {
                    final bi bi4 = new bi();
                    this.new = this.for.new();
                    if (this.new.for != 0 || this.new.do[0] != '(') {
                        throw new a5("'(' expected");
                    }
                    this.new = this.for.new();
                    this.try(bi4);
                    if (this.new.for != 0 || this.new.do[0] != ')') {
                        throw new a5("')' expected");
                    }
                    if (bi4.char != 1) {
                        throw new a5("boolean expression expected");
                    }
                    if (bi4.long) {
                        switch (this.if(false)) {
                            case 2: {
                                return 2;
                            }
                            case 1: {
                                return 1;
                            }
                            default: {
                                final int else1 = this.for.else();
                                this.new = this.for.new();
                                if (this.new.for == 2 && this.new.if == 1L) {
                                    this.new = this.for.new();
                                    this.try();
                                }
                                else {
                                    this.for.a(else1);
                                }
                                return 0;
                            }
                        }
                    }
                    else {
                        this.byte();
                        final int else2 = this.for.else();
                        this.new = this.for.new();
                        if (this.new.for != 2 || this.new.if != 1L) {
                            this.for.a(else2);
                            return 0;
                        }
                        switch (this.if(false)) {
                            case 2: {
                                return 2;
                            }
                            case 1: {
                                return 1;
                            }
                            default: {
                                return 0;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    final bi bi5 = new bi();
                    this.new = this.for.new();
                    if (this.new.for != 0 || this.new.do[0] != '(') {
                        throw new a5("'(' expected");
                    }
                    final int else3 = this.for.else();
                    this.new = this.for.new();
                    this.try(bi5);
                    if (this.new.for != 0 || this.new.do[0] != ')') {
                        throw new a5("')' expected");
                    }
                    if (bi5.char != 1) {
                        throw new a5("boolean expression expected");
                    }
                    final int else4 = this.for.else();
                    while (bi5.long) {
                        switch (this.if(false)) {
                            case 2: {
                                return 2;
                            }
                            case 1: {
                                this.for.a(else4);
                                this.byte();
                                return 0;
                            }
                            default: {
                                this.for.a(else3);
                                this.new = this.for.new();
                                this.try(bi5);
                                this.for.a(else4);
                                continue;
                            }
                        }
                    }
                    this.byte();
                    return 0;
                }
            }
        }
        return 3;
    }
    
    int if(final boolean b) throws a5 {
        this.new = this.for.new();
        if (this.new.for != 0 || this.new.do[0] != '{') {
            return this.if();
        }
        if (b) {
            this.a(true);
            final bi bi = new bi();
            bi.char = 2;
            bi.case = 0L;
            this.a(true, bi);
        }
        else {
            this.new = this.for.new();
        }
        while (true) {
            final int if1 = this.if();
            if (if1 == 3) {
                if (this.new.for != 0 || this.new.do[0] != '}') {
                    throw new a5("'}' expected");
                }
                return -1;
            }
            else {
                if (if1 != 0) {
                    return if1;
                }
                this.new = this.for.new();
            }
        }
    }
    
    void a(final char[] array, final bi bi) throws a5 {
        final bi[] array2 = null;
        final bi[] array3 = new bi[a.a.a.a.c.do];
        int n = 0;
        int if1 = 0;
        final int a = this.int.a(array);
        if (a == -1 && (if1 = this.goto.if(array)) == -1) {
            throw new a5("unknown function");
        }
        this.new = this.for.new();
        while (this.new.for != 0 || this.new.do[0] != ')') {
            this.try(array3[n] = new bi());
            ++n;
            if (this.new.for != 0) {
                break;
            }
            if (this.new.do[0] != ',') {
                break;
            }
            this.new = this.for.new();
        }
        if (a != -1) {
            this.int.a(bi, array3, a, n);
        }
        else {
            final c a2 = this.goto.a(if1);
            if (n != a2.int) {
                throw new a5("bad number of arguments");
            }
            ++this.try;
            if (this.try >= 64) {
                throw new a5("stack overflow");
            }
            this.f[this.try] = this.c;
            for (int i = 0; i < n; ++i) {
                array3[i].do = a2.a[i].do;
                this.a(true, array3[i]);
            }
            final int else1 = this.for.else();
            this.for.a(a2.new);
            this.if(true);
            this.long.a(bi, this.char[this.c - 1], false);
            this.case();
            --this.try;
            this.for.a(else1);
        }
        this.new = this.for.new();
    }
    
    void try(final bi bi) throws a5 {
        this.for(bi);
        while (this.new.for == 3 && (this.new.if == 14L || this.new.if == 15L)) {
            final long if1 = this.new.if;
            final bi bi2 = new bi();
            final bi bi3 = new bi();
            this.new = this.for.new();
            this.for(bi3);
            this.long.a(bi2, bi, false);
            if (if1 == 14L) {
                this.byte.do(bi, bi2, bi3);
            }
            else {
                this.byte.long(bi, bi2, bi3);
            }
        }
    }
    
    void for(final bi bi) throws a5 {
        this.if(bi);
        if (this.new.for == 3 && this.new.if >= 17L && this.new.if <= 22L) {
            final long if1 = this.new.if;
            final bi bi2 = new bi();
            final bi bi3 = new bi();
            this.new = this.for.new();
            this.if(bi3);
            this.long.a(bi2, bi, false);
            switch ((int)if1) {
                case 17: {
                    this.byte.case(bi, bi2, bi3);
                    break;
                }
                case 19: {
                    this.byte.for(bi, bi2, bi3);
                    break;
                }
                case 18: {
                    this.byte.goto(bi, bi2, bi3);
                    break;
                }
                case 21: {
                    this.byte.a(bi, bi2, bi3);
                    break;
                }
                case 20: {
                    this.byte.if(bi, bi2, bi3);
                    break;
                }
                case 22: {
                    this.byte.char(bi, bi2, bi3);
                    break;
                }
            }
        }
    }
    
    void if(final bi bi) throws a5 {
        this.a(bi);
        while (this.new.for == 3 && (this.new.if == 8L || this.new.if == 9L || this.new.if == 12L || this.new.if == 13L)) {
            final long if1 = this.new.if;
            final bi bi2 = new bi();
            final bi bi3 = new bi();
            this.new = this.for.new();
            this.a(bi3);
            this.long.a(bi2, bi, false);
            switch ((int)if1) {
                case 8: {
                    this.byte.byte(bi, bi2, bi3);
                    continue;
                }
                case 9: {
                    this.byte.new(bi, bi2, bi3);
                    continue;
                }
                case 12: {
                    this.byte.try(bi, bi2, bi3);
                    continue;
                }
                case 13: {
                    this.byte.void(bi, bi2, bi3);
                    continue;
                }
            }
        }
    }
    
    void a(final bi bi) throws a5 {
        this.new(bi);
        while (this.new.for == 3 && (this.new.if == 10L || this.new.if == 11L)) {
            final long if1 = this.new.if;
            final bi bi2 = new bi();
            final bi bi3 = new bi();
            this.new = this.for.new();
            this.a(bi3);
            this.long.a(bi2, bi, false);
            if (if1 == 10L) {
                this.byte.int(bi, bi2, bi3);
            }
            else {
                this.byte.else(bi, bi2, bi3);
            }
        }
    }
    
    void new(final bi bi) throws a5 {
        long if1 = -1L;
        if (this.new.for == 3 && (this.new.if == 9L || this.new.if == 16L)) {
            if1 = this.new.if;
            this.new = this.for.new();
        }
        this.int(bi);
        if (if1 == 9L) {
            final bi bi2 = new bi();
            this.long.a(bi2, bi, false);
            this.byte.do(bi, bi2);
        }
        else if (if1 == 16L) {
            final bi bi3 = new bi();
            this.long.a(bi3, bi, false);
            this.byte.if(bi, bi3);
        }
    }
    
    void int(final bi bi) throws a5 {
        if (this.new.for == 0 && this.new.do[0] == '(') {
            this.new = this.for.new();
            this.try(bi);
            if (this.new.for != 0 || this.new.do[0] != ')') {
                throw new a5("')' expected");
            }
            this.new = this.for.new();
        }
        else {
            this.do(bi);
        }
    }
    
    void do(final bi bi) throws a5 {
        switch (this.new.for) {
            case 8: {
                bi.char = 4;
                bi.int = new char[this.new.do.length + 1];
                i.for(this.new.do, bi.int);
                break;
            }
            case 7: {
                bi.char = 3;
                bi.else = this.new.int;
                break;
            }
            case 6: {
                bi.char = 2;
                bi.case = this.new.if;
                break;
            }
            case 5: {
                bi.char = 1;
                if (this.new.if == 0L) {
                    bi.long = false;
                    break;
                }
                bi.long = true;
                break;
            }
            case 1: {
                final char[] array = new char[this.new.do.length];
                i.for(this.new.do, array);
                this.new = this.for.new();
                if (this.new.for == 0 && this.new.do[0] == '(') {
                    this.a(array, bi);
                }
                else if (this.new.for == 0 && this.new.do[0] == '[') {
                    this.new = this.for.new();
                    this.try(bi);
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new a5("']' expected");
                    }
                    boolean b = false;
                    int n;
                    if ((n = this.a(true, array)) == -1) {
                        if ((n = this.a(false, array)) == -1) {
                            throw new a5("unknown variable");
                        }
                    }
                    else {
                        b = true;
                    }
                    bi if1;
                    if (b) {
                        if1 = this.char[n];
                    }
                    else {
                        if1 = this.goto.if(n);
                    }
                    int n2;
                    if (bi.char == 3) {
                        n2 = (int)bi.else;
                    }
                    else {
                        if (bi.char != 2) {
                            throw new a5("Value must be an integer");
                        }
                        n2 = (int)bi.case;
                    }
                    if (if1.char == 5) {
                        if (n2 < 0 || n2 >= if1.goto) {
                            throw new a5("Array out of bound exception. Array size is " + if1.goto + " and you try to access element at " + n2);
                        }
                        this.long.a(bi, if1.a[n2], false);
                    }
                    else {
                        if (if1.char != 4) {
                            throw new a5("String or array variable expected");
                        }
                        if (n2 < 0) {
                            throw new a5("String out of bound exception. You try to access element at " + n2);
                        }
                        if (n2 > i.a(if1.int)) {
                            bi.case = 0L;
                        }
                        else {
                            bi.case = if1.int[n2];
                        }
                        bi.char = 2;
                    }
                    this.new = this.for.new();
                }
                else {
                    boolean b2 = false;
                    int n3;
                    if ((n3 = this.a(true, array)) == -1) {
                        if ((n3 = this.a(false, array)) == -1) {
                            throw new a5("unknown variable");
                        }
                    }
                    else {
                        b2 = true;
                    }
                    if (b2) {
                        this.long.a(bi, this.char[n3], false);
                    }
                    else {
                        this.goto.a(bi, n3);
                    }
                }
                return;
            }
            default: {
                throw new a5("word unexpected");
            }
        }
        this.new = this.for.new();
    }
}

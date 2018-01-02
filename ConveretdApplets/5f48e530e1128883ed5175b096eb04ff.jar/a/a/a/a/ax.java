// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ax
{
    static final int do = 64;
    static final int if = 64;
    static final int e = 256;
    static final int d = 64;
    static final int g = 0;
    static final int void = 1;
    static final int else = 2;
    static final int a = 3;
    bl case;
    a3[] char;
    int[] f;
    int try;
    int c;
    j goto;
    ah for;
    z new;
    ag byte;
    ay int;
    al long;
    private boolean b;
    
    ax(final bl case1) {
        this.try = 0;
        this.b = false;
        this.char = new a3[258];
        this.c = 0;
        this.f = new int[66];
        this.case = case1;
    }
    
    ah do() {
        return this.for;
    }
    
    void a(final j goto1) {
        this.goto = goto1;
        this.for = new ah(this.goto.if());
        this.byte = new ag();
        this.int = new ay(this.case);
        this.long = new al();
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
    
    a3 a(final char[] array) {
        try {
            if (this.b) {
                System.out.println("Recursive external script function call detected. Function call ignored: " + new String(array, 0, a.a.a.a.g.a(array)));
                return null;
            }
            this.b = true;
            final a3[] array2 = new a3[a.a.a.a.b.do];
            a3 a3 = null;
            int n = 0;
            int a4 = -1;
            final ah ah = new ah(array);
            final z new1 = ah.new();
            if (new1.for == 1) {
                int if1;
                if ((if1 = this.goto.if(new1.do)) == -1) {
                    if ((a4 = this.int.a(new1.do)) == -1) {
                        this.b = false;
                        return null;
                    }
                    if1 = -2;
                }
                final z new2 = ah.new();
                if (new2.for == 0 && new2.do[0] == '(') {
                    z z = ah.new();
                    int n2 = 0;
                    if (z.do[0] == '-') {
                        n2 = 1;
                        z = ah.new();
                    }
                    else if (z.do[0] == '+') {
                        z = ah.new();
                    }
                    while (z.for == 5 || z.for == 6 || z.for == 7 || z.for == 8) {
                        switch (z.for) {
                            case 5: {
                                array2[n] = new a3();
                                if (z.if == 0L) {
                                    array2[n].long = false;
                                }
                                else {
                                    array2[n].long = true;
                                }
                                array2[n].char = 1;
                                break;
                            }
                            case 6: {
                                array2[n] = new a3();
                                array2[n].case = z.if * ((n2 != 0) ? -1 : 1);
                                array2[n].char = 2;
                                break;
                            }
                            case 7: {
                                array2[n] = new a3();
                                array2[n].else = z.int * ((n2 != 0) ? -1 : 1);
                                array2[n].char = 3;
                                break;
                            }
                            case 8: {
                                array2[n] = new a3();
                                array2[n].int = z.do;
                                z.do = new char[1024];
                                array2[n].char = 4;
                                break;
                            }
                        }
                        ++n;
                        z = ah.new();
                        if (z.for != 0) {
                            break;
                        }
                        if (z.do[0] != ',') {
                            break;
                        }
                        z = ah.new();
                        n2 = 0;
                        if (z.do[0] == '-') {
                            n2 = 1;
                            z = ah.new();
                        }
                        else {
                            if (z.do[0] != '+') {
                                continue;
                            }
                            z = ah.new();
                        }
                    }
                    if (z.for != 0 || z.do[0] != ')') {
                        for (int i = 0; i < n; ++i) {
                            array2[i] = null;
                        }
                        this.b = false;
                        return null;
                    }
                    if (if1 != -2) {
                        final b a5 = this.goto.a(if1);
                        if (n != a5.int) {
                            for (int j = 0; j < n; ++j) {
                                array2[j] = null;
                            }
                            this.b = false;
                            return null;
                        }
                        for (int k = 0; k < n; ++k) {
                            array2[k].do = a5.a[k].do;
                            this.a(true, array2[k]);
                        }
                        this.for.a(a5.new);
                        this.if(true);
                        a3 = this.char[this.c - 1];
                        this.case();
                    }
                    else {
                        a3 = new a3();
                        this.int.a(a3, array2, a4, n);
                    }
                }
            }
            this.b = false;
            return a3;
        }
        catch (ar ar) {
            ar.a();
            System.out.print(this.case.c + this.do().try());
            ar.a(this.do());
            for (int l = 0; l < this.c; ++l) {
                this.char[l] = null;
            }
            this.c = 0;
            this.b = false;
            System.out.println(String.valueOf(this.case.B) + " script call \"" + new String(array, 0, a.a.a.a.g.a(array)) + "\" aborted.\n");
            return new a3();
        }
    }
    
    void a() throws ar {
        this.int();
        this.else();
        this.new = this.for.new();
        if (this.new.for != 9) {
            throw new ar("end of file expected");
        }
    }
    
    int a(final boolean b, final char[] array) {
        if (b && this.c > 0) {
            for (int i = this.f[this.try]; i < this.c; ++i) {
                if (a.a.a.a.g.a(this.char[i].do, array, 31) == 0) {
                    return i;
                }
            }
        }
        else if (!b) {
            return this.goto.do(array);
        }
        return -1;
    }
    
    void a(final boolean b, final a3 a3) throws ar {
        if (this.a(b, a3.do) != -1) {
            throw new ar("variable redefinition");
        }
        if (b) {
            this.char[this.c] = a3;
            ++this.c;
            if (this.c > 256) {
                throw new ar("stack overflow");
            }
        }
        else {
            this.goto.a(a3);
        }
    }
    
    void case() {
        for (int i = this.f[this.try]; i < this.c; ++i) {
            this.char[this.c - 1] = null;
            --this.c;
        }
        this.c = this.f[this.try];
    }
    
    void new() throws ar {
        if (this.new.for != 0 || this.new.do[0] != ';') {
            throw new ar("';' expected");
        }
    }
    
    void try() throws ar {
        while (this.new.for == 2 && this.new.if == 0L) {
            this.char();
            this.new = this.for.new();
            this.byte();
            if (this.new.for == 9) {
                throw new ar("unexpected end of file");
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
    
    void char() throws ar {
        int n = 0;
        this.new = this.for.new();
        if (this.new.for != 0 || this.new.do[0] != '(') {
            throw new ar("'(' expected.");
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
                throw new ar("unexpected end of file");
            }
            this.new = this.for.new();
        }
    }
    
    void byte() throws ar {
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
                throw new ar("unexpected end of file");
            }
            this.new = this.for.new();
        }
    }
    
    void a(final boolean b) throws ar {
        this.new = this.for.new();
        while (this.new.for == 2 && this.new.if == 5L) {
            this.new = this.for.new();
            if (this.new.for == 1) {
                final a3 a3 = new a3();
                a.a.a.a.g.for(this.new.do, a3.do);
                this.a(b, a3);
                Label_0103: {
                    try {
                        this.if();
                        break Label_0103;
                    }
                    catch (ar ar) {
                        if (this.new.do[0] != ';') {
                            throw ar;
                        }
                        break Label_0103;
                    }
                    throw new ar("identifier expected");
                }
                this.new = this.for.new();
                continue;
            }
            throw new ar("identifier expected");
        }
    }
    
    void int() throws ar {
        this.new = this.for.new();
        if (this.new.for == 2 && this.new.if == 6L) {
            this.new = this.for.new();
            if (this.new.for != 0 || this.new.do[0] != '{') {
                throw new ar("'{' expected");
            }
            this.a(false);
            if (this.new.for != 0 || this.new.do[0] != '}') {
                throw new ar("'}' expected");
            }
        }
    }
    
    void else() throws ar {
        this.new = this.for.new();
        while (this.new.for == 2 && this.new.if == 7L) {
            this.new = this.for.new();
            if (this.new.for != 1) {
                throw new ar("identifier expected");
            }
            if (this.goto.if(this.new.do) != -1) {
                throw new ar("function redefinition");
            }
            final b b = new b();
            a.a.a.a.g.for(this.new.do, b.if);
            int int1 = 0;
            this.new = this.for.new();
            if (this.new.for != 0 || this.new.do[0] != '(') {
                throw new ar("'(' expected");
            }
            this.new = this.for.new();
            while (this.new.for == 1) {
                for (int i = 0; i < int1; ++i) {
                    if (a.a.a.a.g.a(b.a[i].do, this.new.do, 31) == 0) {
                        throw new ar("duplicate argument");
                    }
                }
                if (int1 > a.a.a.a.b.do) {
                    throw new ar("too many arguments");
                }
                final a3 a3 = new a3();
                a.a.a.a.g.for(this.new.do, a3.do);
                a3.case = 0L;
                a3.char = 2;
                b.a[int1] = a3;
                ++int1;
                this.new = this.for.new();
                if (this.new.for != 0 || this.new.do[0] != ',') {
                    break;
                }
                this.new = this.for.new();
            }
            if (this.new.for != 0 || this.new.do[0] != ')') {
                throw new ar("')' expected");
            }
            b.int = int1;
            b.new = this.for.else();
            this.goto.a(b);
            this.byte();
            this.new = this.for.new();
        }
    }
    
    int if() throws ar {
        if (this.new.for == 1) {
            final char[] array = new char[1024];
            a.a.a.a.g.for(this.new.do, array);
            this.new = this.for.new();
            if (this.new.for == 4) {
                boolean b = false;
                int n;
                if ((n = this.a(true, array)) == -1) {
                    if ((n = this.a(false, array)) == -1) {
                        throw new ar("left operator must be a variable");
                    }
                }
                else {
                    b = true;
                }
                final a3 a3 = new a3();
                this.new = this.for.new();
                this.try(a3);
                if (b) {
                    this.long.a(this.char[n], a3, false);
                }
                else {
                    this.goto.a(n, a3);
                }
            }
            else if (this.new.for == 0 && this.new.do[0] == '(') {
                this.a(array, new a3());
            }
            else {
                if (this.new.for != 0 || this.new.do[0] != '[') {
                    throw new ar("'=' expected");
                }
                boolean b2 = false;
                int n2;
                if ((n2 = this.a(true, array)) == -1) {
                    if ((n2 = this.a(false, array)) == -1) {
                        throw new ar("left operator must be a variable");
                    }
                }
                else {
                    b2 = true;
                }
                final a3 a4 = new a3();
                this.new = this.for.new();
                this.try(a4);
                a3 if1;
                if (b2) {
                    if1 = this.char[n2];
                }
                else {
                    if1 = this.goto.if(n2);
                }
                int n3;
                if (a4.char == 3) {
                    n3 = (int)a4.else;
                }
                else {
                    if (a4.char != 2) {
                        throw new ar("Value must be an integer");
                    }
                    n3 = (int)a4.case;
                }
                if (if1.char == 5) {
                    if (n3 < 0 || n3 >= if1.goto) {
                        throw new ar("Array out of bound exception. Array size is " + if1.goto + " and you try to access element at " + n3);
                    }
                    final a3 a5 = if1.a[n3];
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new ar("']' expected");
                    }
                    this.new = this.for.new();
                    if (this.new.for != 4) {
                        throw new ar("'=' expected!");
                    }
                    this.new = this.for.new();
                    this.try(a4);
                    this.long.a(a5, a4, false);
                }
                else {
                    if1.char = 4;
                    if1.a(n3);
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new ar("']' expected");
                    }
                    this.new = this.for.new();
                    if (this.new.for != 4) {
                        throw new ar("'=' expected!");
                    }
                    this.new = this.for.new();
                    this.try(a4);
                    int n4;
                    if (a4.char == 3) {
                        n4 = (int)a4.else;
                    }
                    else {
                        if (a4.char != 2) {
                            throw new ar("Right operand value must be an integer");
                        }
                        n4 = (int)a4.case;
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
                    final a3 a6 = new a3();
                    this.new = this.for.new();
                    if (this.new.for != 0 || this.new.do[0] != '(') {
                        throw new ar("'(' expected");
                    }
                    this.new = this.for.new();
                    this.try(a6);
                    if (this.new.for != 0 || this.new.do[0] != ')') {
                        throw new ar("')' expected");
                    }
                    if (a6.char != 1) {
                        throw new ar("boolean expression expected");
                    }
                    if (a6.long) {
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
                    final a3 a7 = new a3();
                    this.new = this.for.new();
                    if (this.new.for != 0 || this.new.do[0] != '(') {
                        throw new ar("'(' expected");
                    }
                    final int else3 = this.for.else();
                    this.new = this.for.new();
                    this.try(a7);
                    if (this.new.for != 0 || this.new.do[0] != ')') {
                        throw new ar("')' expected");
                    }
                    if (a7.char != 1) {
                        throw new ar("boolean expression expected");
                    }
                    final int else4 = this.for.else();
                    while (a7.long) {
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
                                this.try(a7);
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
    
    int if(final boolean b) throws ar {
        this.new = this.for.new();
        if (this.new.for != 0 || this.new.do[0] != '{') {
            return this.if();
        }
        if (b) {
            this.a(true);
            final a3 a3 = new a3();
            a3.char = 2;
            a3.case = 0L;
            this.a(true, a3);
        }
        else {
            this.new = this.for.new();
        }
        while (true) {
            final int if1 = this.if();
            if (if1 == 3) {
                if (this.new.for != 0 || this.new.do[0] != '}') {
                    throw new ar("'}' expected");
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
    
    void a(final char[] array, final a3 a3) throws ar {
        final a3[] array2 = null;
        final a3[] array3 = new a3[a.a.a.a.b.do];
        int n = 0;
        int if1 = 0;
        final int a4 = this.int.a(array);
        if (a4 == -1 && (if1 = this.goto.if(array)) == -1) {
            throw new ar("unknown function");
        }
        this.new = this.for.new();
        while (this.new.for != 0 || this.new.do[0] != ')') {
            this.try(array3[n] = new a3());
            ++n;
            if (this.new.for != 0) {
                break;
            }
            if (this.new.do[0] != ',') {
                break;
            }
            this.new = this.for.new();
        }
        if (a4 != -1) {
            this.int.a(a3, array3, a4, n);
        }
        else {
            final b a5 = this.goto.a(if1);
            if (n != a5.int) {
                throw new ar("bad number of arguments");
            }
            ++this.try;
            if (this.try >= 64) {
                throw new ar("stack overflow");
            }
            this.f[this.try] = this.c;
            for (int i = 0; i < n; ++i) {
                array3[i].do = a5.a[i].do;
                this.a(true, array3[i]);
            }
            final int else1 = this.for.else();
            this.for.a(a5.new);
            this.if(true);
            this.long.a(a3, this.char[this.c - 1], false);
            this.case();
            --this.try;
            this.for.a(else1);
        }
        this.new = this.for.new();
    }
    
    void try(final a3 a3) throws ar {
        this.for(a3);
        while (this.new.for == 3 && (this.new.if == 14L || this.new.if == 15L)) {
            final long if1 = this.new.if;
            final a3 a4 = new a3();
            final a3 a5 = new a3();
            this.new = this.for.new();
            this.for(a5);
            this.long.a(a4, a3, false);
            if (if1 == 14L) {
                this.byte.do(a3, a4, a5);
            }
            else {
                this.byte.long(a3, a4, a5);
            }
        }
    }
    
    void for(final a3 a3) throws ar {
        this.if(a3);
        if (this.new.for == 3 && this.new.if >= 17L && this.new.if <= 22L) {
            final long if1 = this.new.if;
            final a3 a4 = new a3();
            final a3 a5 = new a3();
            this.new = this.for.new();
            this.if(a5);
            this.long.a(a4, a3, false);
            switch ((int)if1) {
                case 17: {
                    this.byte.case(a3, a4, a5);
                    break;
                }
                case 19: {
                    this.byte.for(a3, a4, a5);
                    break;
                }
                case 18: {
                    this.byte.goto(a3, a4, a5);
                    break;
                }
                case 21: {
                    this.byte.a(a3, a4, a5);
                    break;
                }
                case 20: {
                    this.byte.if(a3, a4, a5);
                    break;
                }
                case 22: {
                    this.byte.char(a3, a4, a5);
                    break;
                }
            }
        }
    }
    
    void if(final a3 a3) throws ar {
        this.a(a3);
        while (this.new.for == 3 && (this.new.if == 8L || this.new.if == 9L || this.new.if == 12L || this.new.if == 13L)) {
            final long if1 = this.new.if;
            final a3 a4 = new a3();
            final a3 a5 = new a3();
            this.new = this.for.new();
            this.a(a5);
            this.long.a(a4, a3, false);
            switch ((int)if1) {
                case 8: {
                    this.byte.byte(a3, a4, a5);
                    continue;
                }
                case 9: {
                    this.byte.new(a3, a4, a5);
                    continue;
                }
                case 12: {
                    this.byte.try(a3, a4, a5);
                    continue;
                }
                case 13: {
                    this.byte.void(a3, a4, a5);
                    continue;
                }
            }
        }
    }
    
    void a(final a3 a3) throws ar {
        this.new(a3);
        while (this.new.for == 3 && (this.new.if == 10L || this.new.if == 11L)) {
            final long if1 = this.new.if;
            final a3 a4 = new a3();
            final a3 a5 = new a3();
            this.new = this.for.new();
            this.a(a5);
            this.long.a(a4, a3, false);
            if (if1 == 10L) {
                this.byte.int(a3, a4, a5);
            }
            else {
                this.byte.else(a3, a4, a5);
            }
        }
    }
    
    void new(final a3 a3) throws ar {
        long if1 = -1L;
        if (this.new.for == 3 && (this.new.if == 9L || this.new.if == 16L)) {
            if1 = this.new.if;
            this.new = this.for.new();
        }
        this.int(a3);
        if (if1 == 9L) {
            final a3 a4 = new a3();
            this.long.a(a4, a3, false);
            this.byte.do(a3, a4);
        }
        else if (if1 == 16L) {
            final a3 a5 = new a3();
            this.long.a(a5, a3, false);
            this.byte.if(a3, a5);
        }
    }
    
    void int(final a3 a3) throws ar {
        if (this.new.for == 0 && this.new.do[0] == '(') {
            this.new = this.for.new();
            this.try(a3);
            if (this.new.for != 0 || this.new.do[0] != ')') {
                throw new ar("')' expected");
            }
            this.new = this.for.new();
        }
        else {
            this.do(a3);
        }
    }
    
    void do(final a3 a3) throws ar {
        switch (this.new.for) {
            case 8: {
                a3.char = 4;
                a3.int = new char[this.new.do.length + 1];
                a.a.a.a.g.for(this.new.do, a3.int);
                break;
            }
            case 7: {
                a3.char = 3;
                a3.else = this.new.int;
                break;
            }
            case 6: {
                a3.char = 2;
                a3.case = this.new.if;
                break;
            }
            case 5: {
                a3.char = 1;
                if (this.new.if == 0L) {
                    a3.long = false;
                    break;
                }
                a3.long = true;
                break;
            }
            case 1: {
                final char[] array = new char[this.new.do.length];
                a.a.a.a.g.for(this.new.do, array);
                this.new = this.for.new();
                if (this.new.for == 0 && this.new.do[0] == '(') {
                    this.a(array, a3);
                }
                else if (this.new.for == 0 && this.new.do[0] == '[') {
                    this.new = this.for.new();
                    this.try(a3);
                    if (this.new.for != 0 && this.new.do[0] != ']') {
                        throw new ar("']' expected");
                    }
                    boolean b = false;
                    int n;
                    if ((n = this.a(true, array)) == -1) {
                        if ((n = this.a(false, array)) == -1) {
                            throw new ar("unknown variable");
                        }
                    }
                    else {
                        b = true;
                    }
                    a3 if1;
                    if (b) {
                        if1 = this.char[n];
                    }
                    else {
                        if1 = this.goto.if(n);
                    }
                    int n2;
                    if (a3.char == 3) {
                        n2 = (int)a3.else;
                    }
                    else {
                        if (a3.char != 2) {
                            throw new ar("Value must be an integer");
                        }
                        n2 = (int)a3.case;
                    }
                    if (if1.char == 5) {
                        if (n2 < 0 || n2 >= if1.goto) {
                            throw new ar("Array out of bound exception. Array size is " + if1.goto + " and you try to access element at " + n2);
                        }
                        this.long.a(a3, if1.a[n2], false);
                    }
                    else {
                        if (if1.char != 4) {
                            throw new ar("String or array variable expected");
                        }
                        if (n2 < 0) {
                            throw new ar("String out of bound exception. You try to access element at " + n2);
                        }
                        if (n2 > a.a.a.a.g.a(if1.int)) {
                            a3.case = 0L;
                        }
                        else {
                            a3.case = if1.int[n2];
                        }
                        a3.char = 2;
                    }
                    this.new = this.for.new();
                }
                else {
                    boolean b2 = false;
                    int n3;
                    if ((n3 = this.a(true, array)) == -1) {
                        if ((n3 = this.a(false, array)) == -1) {
                            throw new ar("unknown variable");
                        }
                    }
                    else {
                        b2 = true;
                    }
                    if (b2) {
                        this.long.a(a3, this.char[n3], false);
                    }
                    else {
                        this.goto.a(a3, n3);
                    }
                }
                return;
            }
            default: {
                throw new ar("word unexpected");
            }
        }
        this.new = this.for.new();
    }
}

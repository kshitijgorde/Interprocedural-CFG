import java.util.Hashtable;
import java.util.Vector;
import java.io.PrintStream;
import java.util.Dictionary;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ParseLatex
{
    Dictionary I;
    PrintStream add;
    Vector Z;
    Dictionary addElement;
    Dictionary append;
    Dictionary C;
    ParseLatex$Render B;
    ParseLatex$Render avalua;
    int D;
    int F;
    int f;
    int forName;
    int get;
    int J;
    final ParseLatex$CallbackEval getAt;
    final ParseLatex$CallbackEval getClass;
    static Class S;
    static Class getMessage;
    
    final void Error(final int n, final int n2, final String s, final String s2) {
        this.add.print("Error: ");
        if (s.length() != 0) {
            this.add.print("%s: " + s);
        }
        if (n != 0) {
            this.add.print("fila %d: " + n);
        }
        if (n2 != 0) {
            this.add.print("columna %d: " + n2);
        }
        this.add.print(s2);
        if (this.B != null && this.B.I != null) {
            this.B.I.Error(n, n2, s, s2);
        }
    }
    
    final int I(final char c) {
        final Integer n = this.I.get("" + c);
        if (n == null) {
            return 65536;
        }
        return n;
    }
    
    final int I(final String s) {
        Integer n = this.I.get(s);
        if (n == null) {
            n = new Integer(this.forName++);
            this.I.put(s, n);
            this.C.put(n, s);
        }
        return n;
    }
    
    final String I(final Token token) {
        if (token instanceof ParseLatex$TokenCompost) {
            final ParseLatex$TokenCompost parseLatex$TokenCompost = (ParseLatex$TokenCompost)token;
            String s = this.I(parseLatex$TokenCompost.I);
            for (int i = 0; i < parseLatex$TokenCompost.Z.length; ++i) {
                s += this.I(parseLatex$TokenCompost.Z[i]);
            }
            return s;
        }
        return this.C.get(new Integer(token.C()));
    }
    
    final Token I() {
        while (!this.Z.isEmpty()) {
            final Token i = this.Z.lastElement().I();
            if (i.Z()) {
                this.Z.removeElementAt(this.Z.size() - 1);
            }
            if (!i.Z()) {
                return i;
            }
        }
        return new ParseLatex$TokenSimple(this, 0, 0);
    }
    
    final void Z() {
        Token token;
        for (token = this.I(); (token.C() == 65540 || token.C() == 65536 || token.C() == 65541 || token.C() == 65555) && token.C() != 0; token = this.I()) {}
        this.Z(token);
    }
    
    final void add(Token i, final ParseLatex$LlistaTokens parseLatex$LlistaTokens, final ParseLatex$LlistaTokens parseLatex$LlistaTokens2) {
        int n = 0;
        if (i.C() == 0) {
            return;
        }
        if (i.C() == 65543) {
            while (i.C() != 0) {
                if (i.C() == 65543) {
                    ++n;
                }
                parseLatex$LlistaTokens.add(i);
                if (i.C() == 65544 && --n == 0) {
                    break;
                }
                i = this.I();
                parseLatex$LlistaTokens2.add(i);
            }
            if (i.C() == 0) {
                this.Z(i);
            }
        }
        else {
            parseLatex$LlistaTokens.add(i);
        }
    }
    
    final void Z(final Token token) {
        this.Z.addElement(new ParseLatex$TokenejadorUnToken(this, token));
    }
    
    final ParseLatex$LlistaTokens[] I(final int n) {
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens = new ParseLatex$LlistaTokens(this);
        ParseLatex$LlistaTokens parseLatex$LlistaTokens2 = new ParseLatex$LlistaTokens(this);
        for (int i = 0; i < n; ++i) {
            this.Z();
            final Token j = this.I();
            parseLatex$LlistaTokens.add(j);
            if (j.C() == 65543) {
                final ParseLatex$LlistaTokens parseLatex$LlistaTokens3 = new ParseLatex$LlistaTokens(this);
                Token token = this.I();
                parseLatex$LlistaTokens.add(token);
                while (token.C() != 0 && token.C() != 65544) {
                    this.add(token, parseLatex$LlistaTokens3, parseLatex$LlistaTokens);
                    token = this.I();
                    parseLatex$LlistaTokens.add(token);
                }
                final int size = parseLatex$LlistaTokens3.size();
                if (size > 1) {
                    parseLatex$LlistaTokens2.add(new ParseLatex$TokenCompost(this, new ParseLatex$TokenSimple(this, 65545, 0), parseLatex$LlistaTokens3.tokens(), 0));
                }
                else if (size == 0) {
                    parseLatex$LlistaTokens2.add(new ParseLatex$TokenSimple(this, 65539, 0));
                }
                else {
                    parseLatex$LlistaTokens2.add(parseLatex$LlistaTokens3.getAt(0));
                }
                if (token.C() == 0) {
                    this.Error(0, 0, "", "Esperava token \"}\".\n");
                    parseLatex$LlistaTokens2 = null;
                    break;
                }
            }
            else {
                if (j.C() == 0) {
                    this.Error(0, 0, "", "No tinc prous arguments.\n");
                    parseLatex$LlistaTokens2 = null;
                    break;
                }
                parseLatex$LlistaTokens2.add(j);
            }
        }
        return new ParseLatex$LlistaTokens[] { parseLatex$LlistaTokens2, parseLatex$LlistaTokens };
    }
    
    final Token C() {
        Token token;
        do {
            token = this.I();
            if (token.C() != 0) {
                token = this.Z(token.C()).f(token);
            }
        } while (token.C() == 65536);
        if (token instanceof ParseLatex$TokenCompost) {
            final ParseLatex$TokenCompost parseLatex$TokenCompost = (ParseLatex$TokenCompost)token;
            if (parseLatex$TokenCompost.I.C() == 65545) {
                token = parseLatex$TokenCompost.Z[0];
                this.Z.addElement(new ParseLatex$TokenejadorLlista(this, parseLatex$TokenCompost.Z, 1, parseLatex$TokenCompost.Z.length - 1));
            }
        }
        return token;
    }
    
    final ParseLatex$CallbackEval Z(final int n) {
        final ParseLatex$CallbackEval parseLatex$CallbackEval = this.addElement.get(new Integer(n));
        if (parseLatex$CallbackEval != null) {
            return parseLatex$CallbackEval;
        }
        if (this.append.get(new Integer(n)) == null) {
            return this.getAt;
        }
        return this.getClass;
    }
    
    final Token C(Token token) {
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens = new ParseLatex$LlistaTokens(this);
        if (token.getClass() == ((ParseLatex.S == null) ? (ParseLatex.S = C("ParseLatex$TokenSimple")) : ParseLatex.S)) {
            return token.avalua();
        }
        if (token.getClass() != ((ParseLatex.getMessage == null) ? (ParseLatex.getMessage = C("ParseLatex$TokenCompost")) : ParseLatex.getMessage)) {
            return token;
        }
        final ParseLatex$TokenCompost parseLatex$TokenCompost = (ParseLatex$TokenCompost)token;
        if (parseLatex$TokenCompost.I.C() != 65545) {
            return token;
        }
        this.Z.addElement(new ParseLatex$TokenejadorUnToken(this, new ParseLatex$TokenSimple(this, 0, 0)));
        this.Z.addElement(new ParseLatex$TokenejadorLlista(this, parseLatex$TokenCompost.Z));
        for (token = this.I(); !token.I(); token = this.I()) {
            token = token.avalua();
            if (token.C() != 65536) {
                parseLatex$LlistaTokens.add(token);
            }
        }
        if (parseLatex$LlistaTokens.size() > 1) {
            return new ParseLatex$TokenCompost(this, new ParseLatex$TokenSimple(this, 65545, 0), parseLatex$LlistaTokens.tokens(), 0);
        }
        return parseLatex$LlistaTokens.getAt(0);
    }
    
    ParseLatex(final ParseLatex$UserRender parseLatex$UserRender, final PrintStream add) {
        this.getAt = new ParseLatex$1(this);
        this.getClass = new ParseLatex$2(this);
        this.I = new Hashtable();
        this.Z = new Vector();
        this.addElement = new Hashtable();
        this.append = new Hashtable();
        this.C = new Hashtable();
        this.B = new ParseLatex$Render(this);
        this.avalua = new ParseLatex$Render(this);
        this.add = add;
        this.get = 0;
        this.forName = 65556;
        this.B.I(parseLatex$UserRender);
        parseLatex$UserRender.F = this;
        this.I.put("\t", new Integer(65536));
        for (int i = 32; i < 256; ++i) {
            if (i != 92) {
                this.I.put("" + (char)i, new Integer(i));
            }
        }
        this.I.put("\u2212", new Integer(8722));
        this.I.put("\u017e", new Integer(382));
        for (int j = 913; j <= 974; ++j) {
            if (j != 92) {
                this.I.put("" + (char)j, new Integer(j));
            }
        }
        this.I.put("\\_", new Integer(65554));
        this.I.put("\\Gamma", new Integer(915));
        this.I.put("\\Delta", new Integer(916));
        this.I.put("\\Theta", new Integer(920));
        this.I.put("\\Lambda", new Integer(923));
        this.I.put("\\Xi", new Integer(926));
        this.I.put("\\Pi", new Integer(928));
        this.I.put("\\Sigma", new Integer(931));
        this.I.put("\\Upsilon", new Integer(933));
        this.I.put("\\Phi", new Integer(934));
        this.I.put("\\Psi", new Integer(936));
        this.I.put("\\Omega", new Integer(937));
        this.I.put("\\alpha", new Integer(945));
        this.I.put("\\beta", new Integer(946));
        this.I.put("\\gamma", new Integer(947));
        this.I.put("\\delta", new Integer(948));
        this.I.put("\\varepsilon", new Integer(949));
        this.I.put("\\zeta", new Integer(950));
        this.I.put("\\eta", new Integer(951));
        this.I.put("\\vartheta", new Integer(952));
        this.I.put("\\iota", new Integer(953));
        this.I.put("\\kappa", new Integer(954));
        this.I.put("\\lambda", new Integer(955));
        this.I.put("\\mu", new Integer(956));
        this.I.put("\\nu", new Integer(957));
        this.I.put("\\xi", new Integer(958));
        this.I.put("\\pi", new Integer(960));
        this.I.put("\\varrho", new Integer(961));
        this.I.put("\\varsigma", new Integer(962));
        this.I.put("\\sigma", new Integer(963));
        this.I.put("\\tau", new Integer(964));
        this.I.put("\\upsilon", new Integer(965));
        this.I.put("\\varphi", new Integer(966));
        this.I.put("\\chi", new Integer(967));
        this.I.put("\\psi", new Integer(968));
        this.I.put("\\omega", new Integer(969));
        this.addElement.put(new Integer(65540), new ParseLatex$AvaluadorEspai(this));
        this.addElement.put(new Integer(65541), new ParseLatex$AvaluadorEspai(this));
        this.addElement.put(new Integer(65555), new ParseLatex$AvaluadorEspai(this));
        this.addElement.put(new Integer(65543), new ParseLatex$3(this));
        this.addElement.put(new Integer(65544), new ParseLatex$4(this));
        this.I.put("\\", new Integer(65542));
        this.I.put("{", new Integer(65543));
        this.I.put("}", new Integer(65544));
        this.I.put("\n", new Integer(65541));
        this.I.put("\r", new Integer(65555));
        this.I.put("%", new Integer(65538));
        this.I.put(" ", new Integer(65540));
        this.I.put(" ", new Integer(65540));
        this.I.put("\\cr", new Integer(65550));
        this.I.put("&", new Integer(65551));
        this.C.put(new Integer(65543), "{");
        this.C.put(new Integer(65544), "}");
        this.C.put(new Integer(65540), " ");
        this.C.put(new Integer(65542), "\\");
        this.C.put(new Integer(65547), "#");
        this.C.put(new Integer(65541), "\n");
        this.C.put(new Integer(65555), "\r");
        this.C.put(new Integer(this.I("\\ ")), "\\ ");
        this.C.put(new Integer(65554), "_");
        this.D = this.I(new String("\\begingroup"));
        this.F = this.I(new String("\\endgroup"));
        this.f = this.I(new String("\\par"));
        final ParseLatex$CallbackEval parseLatex$CallbackEval = new ParseLatex$CallbackEval(this, null);
        this.addElement.put(new Integer(this.D), parseLatex$CallbackEval);
        this.addElement.put(new Integer(this.F), parseLatex$CallbackEval);
        this.addElement.put(new Integer(65536), parseLatex$CallbackEval);
        this.addElement.put(new Integer(this.I("\\beginstream")), new ParseLatex$AvaluadorStream(this));
        this.J = this.I("\\endstream");
    }
    
    final void I(final String s, final int n, final ParseLatex$FuncRender parseLatex$FuncRender) {
        final int i = this.I(s);
        this.addElement.put(new Integer(i), new ParseLatex$AvaluadorFuncio(this, n));
        this.B.Z.put(new Integer(i), new ParseLatex$RenderitzadorUserFuncio(this, parseLatex$FuncRender));
        this.C.put(new Integer(i), s);
    }
    
    final void I(final String s, final String s2, final ParseLatex$FuncRender parseLatex$FuncRender) {
        final int i = this.I(s);
        if (i + 1 != this.I(s2)) {
            System.out.println("Els codis han de ser consecutius");
            return;
        }
        this.addElement.put(new Integer(i), new ParseLatex$AvaluadorTaula(this));
        this.B.Z.put(new Integer(i), new ParseLatex$RenderitzadorUserFuncio(this, parseLatex$FuncRender));
        this.C.put(new Integer(i), s);
    }
    
    final void addElement(final String s) {
        this.Z.addElement(new ParseLatex$TokenejadorCadena(this, s));
    }
    
    final void Z(final String s) {
        this.addElement(s);
        this.append();
    }
    
    final void append() {
        for (Token token = this.I(); !token.I(); token = this.I()) {
            final Token avalua = token.avalua();
            if (avalua.C() != 65536) {
                avalua.renderitza(this.B);
            }
        }
        this.B.I();
    }
    
    static final Class C(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}

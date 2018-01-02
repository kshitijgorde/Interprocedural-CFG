// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.parser.generated;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.Reader;
import org.xmodel.xpath.function.FunctionFactory;
import org.xmodel.xpath.expression.K;
import org.xmodel.xpath.expression.F;
import org.xmodel.xpath.expression.H;
import org.xmodel.xpath.expression.E;
import org.xmodel.CanonicalPath;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.xpath.expression.LetExpression;
import org.xmodel.xpath.expression.I;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.C;
import org.xmodel.xpath.expression.ArithmeticExpression;
import org.xmodel.xpath.expression.EqualityExpression;
import org.xmodel.xpath.expression.RelationalExpression;
import org.xmodel.xpath.expression.LogicalExpression;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.D;
import org.xmodel.G;
import org.xmodel.xpath.PathElement;
import org.xmodel.B;
import org.xmodel.xpath.expression.J;
import java.util.List;
import org.xmodel.xpath.expression.P;

public class A implements XPathParserConstants
{
    String U;
    P d;
    public XPathParserTokenManager Y;
    SimpleCharStream _;
    public Token T;
    public Token g;
    private int b;
    private Token X;
    private Token Q;
    private int W;
    private int a;
    private final int[] S;
    private static int[] P;
    private static int[] O;
    private final _A[] h;
    private boolean c;
    private int e;
    private final _B M;
    private List<int[]> V;
    private int[] Z;
    private int N;
    private int[] f;
    private int R;
    
    static {
        \u010e();
        W();
    }
    
    public void A(final String u) {
        this.U = u;
    }
    
    protected static String A(final Token token) {
        return token.image.substring(1, token.image.length() - 1);
    }
    
    private static final B A(int n, final String s, final J j) {
        if (s != null && s.equals("text()")) {
            n = 128;
        }
        if (j != null) {
            return new PathElement(n, s, j);
        }
        return new PathElement(n, s);
    }
    
    private static final void A(final D d, final B b) {
        if (b.axis() == 16) {
            d.addElement(new PathElement(32, b.type(), b.predicate()));
        }
        else {
            d.addElement(new PathElement(34, null, null));
            d.addElement(b);
        }
    }
    
    private static final IExpression D(final IExpression expression, final IExpression expression2) {
        return new LogicalExpression(LogicalExpression.Operator.OR, expression, expression2);
    }
    
    private static final IExpression H(final IExpression expression, final IExpression expression2) {
        return new LogicalExpression(LogicalExpression.Operator.AND, expression, expression2);
    }
    
    private static final IExpression N(final IExpression expression, final IExpression expression2) {
        return new RelationalExpression(RelationalExpression.Operator.GT, expression, expression2);
    }
    
    private static final IExpression G(final IExpression expression, final IExpression expression2) {
        return new RelationalExpression(RelationalExpression.Operator.GE, expression, expression2);
    }
    
    private static final IExpression F(final IExpression expression, final IExpression expression2) {
        return new RelationalExpression(RelationalExpression.Operator.LT, expression, expression2);
    }
    
    private static final IExpression L(final IExpression expression, final IExpression expression2) {
        return new RelationalExpression(RelationalExpression.Operator.LE, expression, expression2);
    }
    
    private static final IExpression J(final IExpression expression, final IExpression expression2) {
        return new EqualityExpression(EqualityExpression.Operator.EQ, expression, expression2);
    }
    
    private static final IExpression M(final IExpression expression, final IExpression expression2) {
        return new EqualityExpression(EqualityExpression.Operator.NEQ, expression, expression2);
    }
    
    private static final IExpression I(final IExpression expression, final IExpression expression2) {
        return new ArithmeticExpression(ArithmeticExpression.Operator.ADD, expression, expression2);
    }
    
    private static final IExpression K(final IExpression expression, final IExpression expression2) {
        return new ArithmeticExpression(ArithmeticExpression.Operator.SUB, expression, expression2);
    }
    
    private static final IExpression C(final IExpression expression, final IExpression expression2) {
        return new ArithmeticExpression(ArithmeticExpression.Operator.MUL, expression, expression2);
    }
    
    private static final IExpression A(final IExpression expression, final IExpression expression2) {
        return new ArithmeticExpression(ArithmeticExpression.Operator.DIV, expression, expression2);
    }
    
    private static final IExpression B(final IExpression expression, final IExpression expression2) {
        return new ArithmeticExpression(ArithmeticExpression.Operator.MOD, expression, expression2);
    }
    
    private static final IExpression A(final IExpression expression) {
        return new C(expression);
    }
    
    private static final IExpression E(final IExpression expression, final IExpression expression2) {
        return new org.xmodel.xpath.expression.G(expression, expression2);
    }
    
    private static final IExpression B(final IExpression expression, final D d) {
        return new I(expression, new org.xmodel.xpath.expression.A(d));
    }
    
    private static final IExpression A(final IExpression expression, final D d) {
        d.getPathElement(0).setAxis(32);
        return new I(expression, new org.xmodel.xpath.expression.A(d));
    }
    
    private final void A(final IPath path, final D d) {
        final B pathElement = path.getPathElement(0);
        d.addElement(new PathElement(1, pathElement.type(), pathElement.predicate()));
        final J j = (J)pathElement.predicate();
        if (j != null) {
            j.A(d);
        }
        for (int i = 1; i < path.length(); ++i) {
            final B pathElement2 = path.getPathElement(i);
            d.addElement(pathElement2);
            final J k = (J)pathElement2.predicate();
            if (k != null) {
                k.A(d);
            }
        }
    }
    
    private final void B(final IPath path, final D d) {
        final B pathElement = path.getPathElement(0);
        d.addElement(new PathElement(1, null, null));
        d.addElement(new PathElement(34, pathElement.type(), pathElement.predicate()));
        final J j = (J)pathElement.predicate();
        if (j != null) {
            j.A(d);
        }
        for (int i = 1; i < path.length(); ++i) {
            final B pathElement2 = path.getPathElement(i);
            d.addElement(pathElement2);
            final J k = (J)pathElement2.predicate();
            if (k != null) {
                k.A(d);
            }
        }
    }
    
    private final void B(final D d) {
        d.addElement(new PathElement(1));
    }
    
    public final void \u00fa() throws ParseException {
    }
    
    public final void D(final D d) throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 6:
            case 14: {
                this.A(d);
                break;
            }
            case 5:
            case 11:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 54:
            case 55: {
                this.C(d);
                break;
            }
            default: {
                this.S[0] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
        this.J(0);
    }
    
    public final IExpression \u00ed() throws ParseException {
        boolean b = false;
        final LetExpression letExpression = new LetExpression();
        this.d = new P();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 24: {
                    this.A(this.d, letExpression);
                    b = true;
                    continue;
                }
                default: {
                    this.S[1] = this.a;
                    final IExpression \u00f0 = this.\u00d0();
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 52: {
                            this.J(52);
                            break;
                        }
                        default: {
                            this.S[2] = this.a;
                            break;
                        }
                    }
                    this.J(0);
                    if (b) {
                        letExpression.addArgument(\u00f0);
                        this.d.addArgument(letExpression);
                        return this.d;
                    }
                    this.d.addArgument(\u00f0);
                    return this.d;
                }
            }
        }
    }
    
    public final void A(final P p2, final LetExpression letExpression) throws ParseException {
        this.J(24);
        this.J(53);
        final Token j = this.J(50);
        this.J(18);
        final IExpression \u00f0 = this.\u00d0();
        this.J(52);
        final IVariableSource variableSource = p2.getVariableSource();
        final P p3 = new P(\u00f0);
        letExpression.A(p3, j.image);
        p3.A(variableSource);
    }
    
    public final void A(final D d) throws ParseException {
        final CanonicalPath canonicalPath = new CanonicalPath();
        Label_0183: {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 14: {
                    this.J(14);
                    this.C(canonicalPath);
                    this.B(canonicalPath, d);
                    break;
                }
                default: {
                    this.S[3] = this.a;
                    if (this.B(2)) {
                        this.J(6);
                        this.C(canonicalPath);
                        this.A(canonicalPath, d);
                        break;
                    }
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 6: {
                            this.J(6);
                            this.B(d);
                            break Label_0183;
                        }
                        default: {
                            this.S[4] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public final D C(final D d) throws ParseException {
        d.addElement(this.B((IPath)d));
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 6:
                case 14: {
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 6: {
                            this.J(6);
                            d.addElement(this.B((IPath)d));
                            continue;
                        }
                        case 14: {
                            this.J(14);
                            A(d, this.B((IPath)d));
                            continue;
                        }
                        default: {
                            this.S[6] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.S[5] = this.a;
                    return d;
                }
            }
        }
    }
    
    public final B B(final IPath path) throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 5:
            case 11:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50: {
                return A(this.\u010f(), this.M(), this.A(path));
            }
            case 54: {
                this.J(54);
                return A(2, null, this.A(path));
            }
            case 55: {
                this.J(55);
                return A(8, null, this.A(path));
            }
            default: {
                this.S[7] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
    }
    
    public final int \u010f() throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 5: {
                this.J(5);
                return 128;
            }
            default: {
                this.S[8] = this.a;
                if (this.H(Integer.MAX_VALUE)) {
                    return this.g();
                }
                this.\u00fa();
                return 16;
            }
        }
    }
    
    public final int g() throws ParseException {
        int n = 0;
        switch ((this.b == -1) ? this.h() : this.b) {
            case 37: {
                this.J(37);
                n = 4;
                break;
            }
            case 45: {
                this.J(45);
                n = 6;
                break;
            }
            case 41: {
                this.J(41);
                n = 128;
                break;
            }
            case 33: {
                this.J(33);
                n = 16;
                break;
            }
            case 43: {
                this.J(43);
                n = 32;
                break;
            }
            case 48: {
                this.J(48);
                n = 34;
                break;
            }
            case 35: {
                this.J(35);
                n = 64;
                break;
            }
            case 44: {
                this.J(44);
                n = 66;
                break;
            }
            case 39: {
                this.J(39);
                n = 256;
                break;
            }
            case 46: {
                this.J(46);
                n = 512;
                break;
            }
            case 42: {
                this.J(42);
                n = -1;
                break;
            }
            case 36: {
                this.J(36);
                n = 8;
                break;
            }
            case 40: {
                this.J(40);
                n = 1024;
                break;
            }
            case 47: {
                this.J(47);
                n = 2048;
                break;
            }
            case 30: {
                this.J(30);
                n = 2;
                break;
            }
            default: {
                this.S[9] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
        this.J(19);
        if (n < 0) {
            throw new ParseException("Axis not supported: " + this.K(0));
        }
        return n;
    }
    
    public final String M() throws ParseException {
        if (this.F(Integer.MAX_VALUE)) {
            final String p = this.p();
            this.J(56);
            this.J(57);
            return String.valueOf(p) + "()";
        }
        switch ((this.b == -1) ? this.h() : this.b) {
            case 11:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50: {
                return this.\u00c4();
            }
            default: {
                this.S[10] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
    }
    
    public final String \u00c4() throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 11: {
                this.J(11);
                return null;
            }
            default: {
                this.S[11] = this.a;
                if (this.D(Integer.MAX_VALUE)) {
                    final String \u00f4 = this.\u00d4();
                    this.J(58);
                    this.J(11);
                    return String.valueOf(\u00f4) + ':' + '*';
                }
                switch ((this.b == -1) ? this.h() : this.b) {
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50: {
                        return this.\u00fb();
                    }
                    default: {
                        this.S[12] = this.a;
                        this.J(-1);
                        throw new ParseException();
                    }
                }
                break;
            }
        }
    }
    
    public final String p() throws ParseException {
        Token token = null;
        switch ((this.b == -1) ? this.h() : this.b) {
            case 31: {
                token = this.J(31);
                break;
            }
            case 32: {
                token = this.J(32);
                break;
            }
            case 49: {
                this.e();
                break;
            }
            default: {
                this.S[13] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
        return token.image;
    }
    
    public final String e() throws ParseException {
        return this.J(49).image;
    }
    
    public final IExpression \u00d8() throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 53: {
                this.J(53);
                return new E(this.J(50).image);
            }
            case 56: {
                this.J(56);
                final IExpression \u00f0 = this.\u00d0();
                this.J(57);
                return \u00f0;
            }
            case 2: {
                return new H(A(this.J(2)));
            }
            case 4: {
                return new H(Double.parseDouble(this.J(4).image));
            }
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50: {
                return this.º();
            }
            default: {
                this.S[14] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
    }
    
    public final IExpression \u00d0() throws ParseException {
        if (this.L(Integer.MAX_VALUE)) {
            return this.\u00d5();
        }
        if (this.G(Integer.MAX_VALUE)) {
            return this.J();
        }
        switch ((this.b == -1) ? this.h() : this.b) {
            case 2:
            case 4:
            case 5:
            case 6:
            case 11:
            case 12:
            case 14:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56: {
                return this.\u00c8();
            }
            default: {
                this.S[15] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
    }
    
    public final IExpression \u00d5() throws ParseException {
        this.J(23);
        this.J(53);
        final Token j = this.J(50);
        this.J(21);
        final IExpression \u00f0 = this.\u00d0();
        final F f = new F(j.image);
        f.addArgument(\u00f0);
        F f2 = f;
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 59: {
                    this.J(59);
                    this.J(53);
                    final Token i = this.J(50);
                    this.J(21);
                    final IExpression \u00f02 = this.\u00d0();
                    final F f3 = new F(i.image);
                    f3.addArgument(\u00f02);
                    f2.addArgument(f3);
                    f2 = f3;
                    continue;
                }
                default: {
                    this.S[16] = this.a;
                    this.J(34);
                    f2.addArgument(this.\u00d0());
                    return f;
                }
            }
        }
    }
    
    public final IExpression J() throws ParseException {
        this.J(22);
        final IExpression \u00f0 = this.\u00d0();
        this.J(28);
        final IExpression \u00f02 = this.\u00d0();
        this.J(29);
        final IExpression \u00f03 = this.\u00d0();
        final K k = new K();
        k.addArgument(\u00f0);
        k.addArgument(\u00f02);
        k.addArgument(\u00f03);
        return k;
    }
    
    public final IExpression \u00c8() throws ParseException {
        IExpression expression = this.\u0112();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 20: {
                    this.J(20);
                    expression = D(expression, this.\u0112());
                    continue;
                }
                default: {
                    this.S[17] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression \u0112() throws ParseException {
        IExpression expression = this.\u00f2();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 25: {
                    this.J(25);
                    expression = H(expression, this.\u00f2());
                    continue;
                }
                default: {
                    this.S[18] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression \u00f2() throws ParseException {
        IExpression expression = this.\u0109();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 7:
                case 15: {
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 7: {
                            this.J(7);
                            expression = J(expression, this.\u0109());
                            continue;
                        }
                        case 15: {
                            this.J(15);
                            expression = M(expression, this.\u0109());
                            continue;
                        }
                        default: {
                            this.S[20] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.S[19] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression \u0109() throws ParseException {
        IExpression expression = this.¤();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 8:
                case 9:
                case 16:
                case 17: {
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 8: {
                            this.J(8);
                            expression = F(expression, this.¤());
                            continue;
                        }
                        case 9: {
                            this.J(9);
                            expression = N(expression, this.¤());
                            continue;
                        }
                        case 16: {
                            this.J(16);
                            expression = L(expression, this.¤());
                            continue;
                        }
                        case 17: {
                            this.J(17);
                            expression = G(expression, this.¤());
                            continue;
                        }
                        default: {
                            this.S[22] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.S[21] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression ¤() throws ParseException {
        IExpression expression = this.\u00dc();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 10:
                case 12: {
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 10: {
                            this.J(10);
                            expression = I(expression, this.\u00dc());
                            continue;
                        }
                        case 12: {
                            this.J(12);
                            expression = K(expression, this.\u00dc());
                            continue;
                        }
                        default: {
                            this.S[24] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.S[23] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression \u00dc() throws ParseException {
        IExpression expression = this.\u00fc();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 11:
                case 26:
                case 27: {
                    switch ((this.b == -1) ? this.h() : this.b) {
                        case 26: {
                            this.J(26);
                            expression = A(expression, this.\u00fc());
                            continue;
                        }
                        case 27: {
                            this.J(27);
                            expression = B(expression, this.\u00fc());
                            continue;
                        }
                        case 11: {
                            this.J(11);
                            expression = C(expression, this.\u00fc());
                            continue;
                        }
                        default: {
                            this.S[26] = this.a;
                            this.J(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.S[25] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression \u00fc() throws ParseException {
        switch ((this.b == -1) ? this.h() : this.b) {
            case 12: {
                this.J(12);
                return A(this.\u00fc());
            }
            case 2:
            case 4:
            case 5:
            case 6:
            case 11:
            case 14:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 53:
            case 54:
            case 55:
            case 56: {
                return this.\u00cf();
            }
            default: {
                this.S[27] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
    }
    
    public final IExpression \u00cf() throws ParseException {
        IExpression expression = this.O();
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 13: {
                    this.J(13);
                    expression = E(expression, this.O());
                    continue;
                }
                default: {
                    this.S[28] = this.a;
                    return expression;
                }
            }
        }
    }
    
    public final IExpression r() throws ParseException {
        final IExpression \u00f8 = this.\u00d8();
        final J a = this.A((IPath)null);
        if (a == null) {
            return \u00f8;
        }
        return new I(\u00f8, a);
    }
    
    public final J A(final IPath path) throws ParseException {
        final J j = new J(path);
        while (true) {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 60: {
                    j.addArgument(this.\u00f0());
                    continue;
                }
                default: {
                    this.S[29] = this.a;
                    if (j.getArguments().size() > 0) {
                        return j;
                    }
                    return null;
                }
            }
        }
    }
    
    public final IExpression \u00f0() throws ParseException {
        this.J(60);
        final IExpression \u00f0 = this.\u00d0();
        this.J(61);
        return \u00f0;
    }
    
    public final IExpression º() throws ParseException {
        final IExpression z = this.z();
        this.J(56);
        Label_0366: {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 2:
                case 4:
                case 5:
                case 6:
                case 11:
                case 12:
                case 14:
                case 20:
                case 21:
                case 22:
                case 23:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 53:
                case 54:
                case 55:
                case 56: {
                    z.addArgument(this.\u00d0());
                    while (true) {
                        switch ((this.b == -1) ? this.h() : this.b) {
                            case 59: {
                                this.J(59);
                                z.addArgument(this.\u00d0());
                                continue;
                            }
                            default: {
                                this.S[30] = this.a;
                                break Label_0366;
                            }
                        }
                    }
                    break;
                }
                default: {
                    this.S[31] = this.a;
                    break;
                }
            }
        }
        this.J(57);
        return z;
    }
    
    public final IExpression z() throws ParseException {
        final String \u0119 = this.\u0119();
        final IExpression function = FunctionFactory.getInstance().createFunction(\u0119);
        if (function == null) {
            throw new ParseException("Undefined function: " + \u0119);
        }
        return function;
    }
    
    public final IExpression O() throws ParseException {
        final CanonicalPath canonicalPath = new CanonicalPath(this.d);
        IExpression expression = null;
        Label_0555: {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 6:
                case 14: {
                    this.A(canonicalPath);
                    expression = new org.xmodel.xpath.expression.A(canonicalPath);
                    break;
                }
                default: {
                    this.S[34] = this.a;
                    if (this.E(Integer.MAX_VALUE)) {
                        expression = this.r();
                        switch ((this.b == -1) ? this.h() : this.b) {
                            case 6:
                            case 14: {
                                switch ((this.b == -1) ? this.h() : this.b) {
                                    case 6: {
                                        this.J(6);
                                        this.C(canonicalPath);
                                        expression = B(expression, canonicalPath);
                                        break Label_0555;
                                    }
                                    case 14: {
                                        this.J(14);
                                        this.C(canonicalPath);
                                        expression = A(expression, canonicalPath);
                                        break Label_0555;
                                    }
                                    default: {
                                        this.S[32] = this.a;
                                        this.J(-1);
                                        throw new ParseException();
                                    }
                                }
                                break;
                            }
                            default: {
                                this.S[33] = this.a;
                                break Label_0555;
                            }
                        }
                    }
                    else {
                        switch ((this.b == -1) ? this.h() : this.b) {
                            case 5:
                            case 11:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                            case 54:
                            case 55: {
                                this.C(canonicalPath);
                                expression = new org.xmodel.xpath.expression.A(canonicalPath);
                                break Label_0555;
                            }
                            default: {
                                this.S[35] = this.a;
                                this.J(-1);
                                throw new ParseException();
                            }
                        }
                    }
                    break;
                }
            }
        }
        return expression;
    }
    
    public final String \u00fb() throws ParseException {
        String \u00f4 = null;
        final String \u00f42 = this.\u00d4();
        switch ((this.b == -1) ? this.h() : this.b) {
            case 58: {
                this.J(58);
                \u00f4 = this.\u00d4();
                break;
            }
            default: {
                this.S[36] = this.a;
                break;
            }
        }
        if (\u00f4 != null) {
            return String.valueOf(\u00f42) + ":" + \u00f4;
        }
        return \u00f42;
    }
    
    public final String \u0119() throws ParseException {
        Object \u00f4 = null;
        String s = null;
        if (this.C(Integer.MAX_VALUE)) {
            \u00f4 = this.\u00d4();
            this.J(58);
            s = this.\u00d4();
        }
        else {
            switch ((this.b == -1) ? this.h() : this.b) {
                case 20:
                case 21:
                case 22:
                case 23:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 50: {
                    s = this.\u00e5();
                    break;
                }
                default: {
                    this.S[37] = this.a;
                    this.J(-1);
                    throw new ParseException();
                }
            }
        }
        if (\u00f4 == null) {
            return s;
        }
        return String.valueOf(\u00f4) + ":" + s;
    }
    
    public final String \u00d4() throws ParseException {
        Token token = null;
        switch ((this.b == -1) ? this.h() : this.b) {
            case 50: {
                token = this.J(50);
                break;
            }
            case 22: {
                token = this.J(22);
                break;
            }
            case 29: {
                token = this.J(29);
                break;
            }
            case 28: {
                token = this.J(28);
                break;
            }
            case 23: {
                token = this.J(23);
                break;
            }
            case 21: {
                token = this.J(21);
                break;
            }
            case 34: {
                token = this.J(34);
                break;
            }
            case 20: {
                token = this.J(20);
                break;
            }
            case 25: {
                token = this.J(25);
                break;
            }
            case 26: {
                token = this.J(26);
                break;
            }
            case 27: {
                token = this.J(27);
                break;
            }
            case 37: {
                token = this.J(37);
                break;
            }
            case 45: {
                token = this.J(45);
                break;
            }
            case 41: {
                token = this.J(41);
                break;
            }
            case 33: {
                token = this.J(33);
                break;
            }
            case 43: {
                token = this.J(43);
                break;
            }
            case 48: {
                token = this.J(48);
                break;
            }
            case 35: {
                token = this.J(35);
                break;
            }
            case 44: {
                token = this.J(44);
                break;
            }
            case 39: {
                token = this.J(39);
                break;
            }
            case 46: {
                token = this.J(46);
                break;
            }
            case 42: {
                token = this.J(42);
                break;
            }
            case 36: {
                token = this.J(36);
                break;
            }
            case 40: {
                token = this.J(40);
                break;
            }
            case 47: {
                token = this.J(47);
                break;
            }
            case 30: {
                token = this.J(30);
                break;
            }
            case 38: {
                token = this.J(38);
                break;
            }
            case 31: {
                token = this.J(31);
                break;
            }
            case 49: {
                token = this.J(49);
                break;
            }
            case 32: {
                token = this.J(32);
                break;
            }
            default: {
                this.S[38] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
        return token.image;
    }
    
    public final String \u00e5() throws ParseException {
        Token token = null;
        switch ((this.b == -1) ? this.h() : this.b) {
            case 50: {
                token = this.J(50);
                break;
            }
            case 22: {
                token = this.J(22);
                break;
            }
            case 29: {
                token = this.J(29);
                break;
            }
            case 28: {
                token = this.J(28);
                break;
            }
            case 23: {
                token = this.J(23);
                break;
            }
            case 21: {
                token = this.J(21);
                break;
            }
            case 34: {
                token = this.J(34);
                break;
            }
            case 20: {
                token = this.J(20);
                break;
            }
            case 25: {
                token = this.J(25);
                break;
            }
            case 26: {
                token = this.J(26);
                break;
            }
            case 27: {
                token = this.J(27);
                break;
            }
            case 37: {
                token = this.J(37);
                break;
            }
            case 45: {
                token = this.J(45);
                break;
            }
            case 41: {
                token = this.J(41);
                break;
            }
            case 33: {
                token = this.J(33);
                break;
            }
            case 43: {
                token = this.J(43);
                break;
            }
            case 48: {
                token = this.J(48);
                break;
            }
            case 35: {
                token = this.J(35);
                break;
            }
            case 44: {
                token = this.J(44);
                break;
            }
            case 39: {
                token = this.J(39);
                break;
            }
            case 46: {
                token = this.J(46);
                break;
            }
            case 42: {
                token = this.J(42);
                break;
            }
            case 36: {
                token = this.J(36);
                break;
            }
            case 40: {
                token = this.J(40);
                break;
            }
            case 47: {
                token = this.J(47);
                break;
            }
            case 30: {
                token = this.J(30);
                break;
            }
            default: {
                this.S[39] = this.a;
                this.J(-1);
                throw new ParseException();
            }
        }
        return token.image;
    }
    
    private boolean B(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.ª();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(0, w);
        }
    }
    
    private boolean H(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.\u00e1();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(1, w);
        }
    }
    
    private boolean F(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.\u0101();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(2, w);
        }
    }
    
    private boolean D(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.H();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(3, w);
        }
    }
    
    private boolean L(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.l();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(4, w);
        }
    }
    
    private boolean G(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.\u00cb();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(5, w);
        }
    }
    
    private boolean E(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.\u00eb();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(6, w);
        }
    }
    
    private boolean C(final int w) {
        this.W = w;
        final Token t = this.T;
        this.X = t;
        this.Q = t;
        try {
            return !this.\u010b();
        }
        catch (_B b) {
            return true;
        }
        finally {
            this.E(7, w);
        }
    }
    
    private boolean \u00e7() {
        return this.I(53) || this.I(50);
    }
    
    private boolean D() {
        final Token x = this.X;
        if (this.\u00e7()) {
            this.X = x;
            if (this.\u0105()) {
                this.X = x;
                if (this.P()) {
                    this.X = x;
                    if (this.s()) {
                        this.X = x;
                        if (this.\u00d2()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean \u00db() {
        return this.D() || this.\u00e6();
    }
    
    private boolean \u0111() {
        return this.I(49);
    }
    
    private boolean C() {
        return this.\u0111();
    }
    
    private boolean \u00ce() {
        return this.I(13) || this.o();
    }
    
    private boolean H() {
        return this.X() || this.I(58) || this.I(11);
    }
    
    private boolean K() {
        if (this.o()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u00ce());
        this.X = x;
        return false;
    }
    
    private boolean ¢() {
        final Token x = this.X;
        if (this.I(31)) {
            this.X = x;
            if (this.I(32)) {
                this.X = x;
                if (this.C()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00e4() {
        return this.K();
    }
    
    private boolean k() {
        return this.\u00ca();
    }
    
    private boolean F() {
        return this.X() || this.I(58) || this.I(11);
    }
    
    private boolean \u00c3() {
        return this.I(12) || this.\u00d9();
    }
    
    private boolean \u00d9() {
        final Token x = this.X;
        if (this.\u00c3()) {
            this.X = x;
            if (this.\u00e4()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u0101() {
        return this.¢() || this.I(56) || this.I(57);
    }
    
    private boolean \u00ff() {
        return this.I(11);
    }
    
    private boolean \u00df() {
        final Token x = this.X;
        if (this.\u00ff()) {
            this.X = x;
            if (this.F()) {
                this.X = x;
                if (this.k()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean ¥() {
        return this.\u00df();
    }
    
    private boolean q() {
        return this.I(11) || this.\u00d9();
    }
    
    private boolean L() {
        return this.I(27) || this.\u00d9();
    }
    
    private boolean Z() {
        return this.¢() || this.I(56) || this.I(57);
    }
    
    private boolean \u00c0() {
        final Token x = this.X;
        if (this.Z()) {
            this.X = x;
            if (this.¥()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u0104() {
        return this.I(26) || this.\u00d9();
    }
    
    private boolean \u00f8() {
        final Token x = this.X;
        if (this.\u0104()) {
            this.X = x;
            if (this.L()) {
                this.X = x;
                if (this.q()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00ee() {
        if (this.\u00d9()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u00f8());
        this.X = x;
        return false;
    }
    
    private boolean \u00e2() {
        final Token x = this.X;
        if (this.I(50)) {
            this.X = x;
            if (this.I(22)) {
                this.X = x;
                if (this.I(29)) {
                    this.X = x;
                    if (this.I(28)) {
                        this.X = x;
                        if (this.I(23)) {
                            this.X = x;
                            if (this.I(21)) {
                                this.X = x;
                                if (this.I(34)) {
                                    this.X = x;
                                    if (this.I(20)) {
                                        this.X = x;
                                        if (this.I(25)) {
                                            this.X = x;
                                            if (this.I(26)) {
                                                this.X = x;
                                                if (this.I(27)) {
                                                    this.X = x;
                                                    if (this.I(37)) {
                                                        this.X = x;
                                                        if (this.I(45)) {
                                                            this.X = x;
                                                            if (this.I(41)) {
                                                                this.X = x;
                                                                if (this.I(33)) {
                                                                    this.X = x;
                                                                    if (this.I(43)) {
                                                                        this.X = x;
                                                                        if (this.I(48)) {
                                                                            this.X = x;
                                                                            if (this.I(35)) {
                                                                                this.X = x;
                                                                                if (this.I(44)) {
                                                                                    this.X = x;
                                                                                    if (this.I(39)) {
                                                                                        this.X = x;
                                                                                        if (this.I(46)) {
                                                                                            this.X = x;
                                                                                            if (this.I(42)) {
                                                                                                this.X = x;
                                                                                                if (this.I(36)) {
                                                                                                    this.X = x;
                                                                                                    if (this.I(40)) {
                                                                                                        this.X = x;
                                                                                                        if (this.I(47)) {
                                                                                                            this.X = x;
                                                                                                            if (this.I(30)) {
                                                                                                                return true;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean \u00c2() {
        return this.I(30);
    }
    
    private boolean c() {
        return this.I(47);
    }
    
    private boolean \u0116() {
        return this.I(40);
    }
    
    private boolean \u00f6() {
        return this.I(36);
    }
    
    private boolean \u00d6() {
        return this.I(42);
    }
    
    private boolean v() {
        return this.I(46);
    }
    
    private boolean d() {
        return this.I(12) || this.\u00ee();
    }
    
    private boolean S() {
        return this.I(39);
    }
    
    private boolean \u0117() {
        return this.I(10) || this.\u00ee();
    }
    
    private boolean \u010c() {
        final Token x = this.X;
        if (this.\u0117()) {
            this.X = x;
            if (this.d()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u0108() {
        return this.I(44);
    }
    
    private boolean T() {
        return this.I(35);
    }
    
    private boolean \u010a() {
        return this.I(48);
    }
    
    private boolean \u00ec() {
        return this.I(43);
    }
    
    private boolean \u0102() {
        if (this.\u00ee()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u010c());
        this.X = x;
        return false;
    }
    
    private boolean \u00cc() {
        return this.I(33);
    }
    
    private boolean m() {
        return this.I(41);
    }
    
    private boolean G() {
        return this.I(45);
    }
    
    private boolean \u00e1() {
        return this.X() || this.I(19);
    }
    
    private boolean \u0100() {
        return this.I(37);
    }
    
    private boolean \u0115() {
        final Token x = this.X;
        if (this.\u0100()) {
            this.X = x;
            if (this.G()) {
                this.X = x;
                if (this.m()) {
                    this.X = x;
                    if (this.\u00cc()) {
                        this.X = x;
                        if (this.\u00ec()) {
                            this.X = x;
                            if (this.\u010a()) {
                                this.X = x;
                                if (this.T()) {
                                    this.X = x;
                                    if (this.\u0108()) {
                                        this.X = x;
                                        if (this.S()) {
                                            this.X = x;
                                            if (this.v()) {
                                                this.X = x;
                                                if (this.\u00d6()) {
                                                    this.X = x;
                                                    if (this.\u00f6()) {
                                                        this.X = x;
                                                        if (this.\u0116()) {
                                                            this.X = x;
                                                            if (this.c()) {
                                                                this.X = x;
                                                                if (this.\u00c2()) {
                                                                    return true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return this.I(19);
    }
    
    private boolean w() {
        return this.I(17) || this.\u0102();
    }
    
    private boolean \u00da() {
        return this.I(16) || this.\u0102();
    }
    
    private boolean \u0107() {
        return this.b();
    }
    
    private boolean x() {
        return this.I(9) || this.\u0102();
    }
    
    private boolean U() {
        return this.I(8) || this.\u0102();
    }
    
    private boolean I() {
        final Token x = this.X;
        if (this.U()) {
            this.X = x;
            if (this.x()) {
                this.X = x;
                if (this.\u00da()) {
                    this.X = x;
                    if (this.w()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean \u00e8() {
        return this.\u0115();
    }
    
    private boolean \u00e3() {
        if (this.\u0102()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.I());
        this.X = x;
        return false;
    }
    
    private boolean E() {
        final Token x = this.X;
        if (this.\u00c7()) {
            this.X = x;
            if (this.\u00e8()) {
                this.X = x;
                if (this.\u0107()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00c7() {
        return this.I(5);
    }
    
    private boolean \u00f1() {
        return this.I(55) || this.\u00e6();
    }
    
    private boolean \u0114() {
        return this.I(54) || this.\u00e6();
    }
    
    private boolean \u00cd() {
        return this.I(15) || this.\u00e3();
    }
    
    private boolean \u0103() {
        final Token x = this.X;
        if (this.n()) {
            this.X = x;
            if (this.\u00cd()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean n() {
        return this.I(7) || this.\u00e3();
    }
    
    private boolean \u00f4() {
        return this.E() || this.\u00c0() || this.\u00e6();
    }
    
    private boolean i() {
        final Token x = this.X;
        if (this.\u00f4()) {
            this.X = x;
            if (this.\u0114()) {
                this.X = x;
                if (this.\u00f1()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00c1() {
        if (this.\u00e3()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u0103());
        this.X = x;
        return false;
    }
    
    private boolean \u010b() {
        return this.X() || this.I(58);
    }
    
    private boolean X() {
        final Token x = this.X;
        if (this.I(50)) {
            this.X = x;
            if (this.I(22)) {
                this.X = x;
                if (this.I(29)) {
                    this.X = x;
                    if (this.I(28)) {
                        this.X = x;
                        if (this.I(23)) {
                            this.X = x;
                            if (this.I(21)) {
                                this.X = x;
                                if (this.I(34)) {
                                    this.X = x;
                                    if (this.I(20)) {
                                        this.X = x;
                                        if (this.I(25)) {
                                            this.X = x;
                                            if (this.I(26)) {
                                                this.X = x;
                                                if (this.I(27)) {
                                                    this.X = x;
                                                    if (this.I(37)) {
                                                        this.X = x;
                                                        if (this.I(45)) {
                                                            this.X = x;
                                                            if (this.I(41)) {
                                                                this.X = x;
                                                                if (this.I(33)) {
                                                                    this.X = x;
                                                                    if (this.I(43)) {
                                                                        this.X = x;
                                                                        if (this.I(48)) {
                                                                            this.X = x;
                                                                            if (this.I(35)) {
                                                                                this.X = x;
                                                                                if (this.I(44)) {
                                                                                    this.X = x;
                                                                                    if (this.I(39)) {
                                                                                        this.X = x;
                                                                                        if (this.I(46)) {
                                                                                            this.X = x;
                                                                                            if (this.I(42)) {
                                                                                                this.X = x;
                                                                                                if (this.I(36)) {
                                                                                                    this.X = x;
                                                                                                    if (this.I(40)) {
                                                                                                        this.X = x;
                                                                                                        if (this.I(47)) {
                                                                                                            this.X = x;
                                                                                                            if (this.I(30)) {
                                                                                                                this.X = x;
                                                                                                                if (this.I(38)) {
                                                                                                                    this.X = x;
                                                                                                                    if (this.I(31)) {
                                                                                                                        this.X = x;
                                                                                                                        if (this.I(49)) {
                                                                                                                            this.X = x;
                                                                                                                            if (this.I(32)) {
                                                                                                                                return true;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean \u00e0() {
        return this.\u00e2();
    }
    
    private boolean _() {
        return this.I(25) || this.\u00c1();
    }
    
    private boolean µ() {
        return this.X() || this.I(58) || this.X();
    }
    
    private boolean \u0113() {
        return this.I(14) || this.i();
    }
    
    private boolean R() {
        if (this.\u00c1()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this._());
        this.X = x;
        return false;
    }
    
    private boolean \u00f3() {
        return this.I(6) || this.i();
    }
    
    private boolean t() {
        final Token x = this.X;
        if (this.\u00f3()) {
            this.X = x;
            if (this.\u0113()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u00d3() {
        final Token x = this.X;
        if (this.µ()) {
            this.X = x;
            if (this.\u00e0()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u00ea() {
        return this.I(58) || this.X();
    }
    
    private boolean \u0110() {
        if (this.i()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.t());
        this.X = x;
        return false;
    }
    
    private boolean \u00c6() {
        return this.I(6);
    }
    
    private boolean ª() {
        return this.I(6) || this.\u0110();
    }
    
    private boolean u() {
        return this.I(20) || this.R();
    }
    
    private boolean \u011a() {
        return this.I(14) || this.\u0110();
    }
    
    private boolean j() {
        if (this.R()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.u());
        this.X = x;
        return false;
    }
    
    private boolean y() {
        final Token x = this.X;
        if (this.\u011a()) {
            this.X = x;
            if (this.ª()) {
                this.X = x;
                if (this.\u00c6()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00ca() {
        if (this.X()) {
            return true;
        }
        final Token x = this.X;
        if (this.\u00ea()) {
            this.X = x;
        }
        return false;
    }
    
    private boolean \u00eb() {
        return this.D();
    }
    
    private boolean V() {
        return this.\u0110();
    }
    
    private boolean Q() {
        return this.I(14) || this.\u0110();
    }
    
    private boolean \u00fd() {
        return this.I(22) || this.f() || this.I(28) || this.f() || this.I(29) || this.f();
    }
    
    private boolean \u0106() {
        return this.I(6) || this.\u0110();
    }
    
    private boolean \u00f9() {
        final Token x = this.X;
        if (this.\u0106()) {
            this.X = x;
            if (this.Q()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean \u010d() {
        if (this.\u00db()) {
            return true;
        }
        final Token x = this.X;
        if (this.\u00f9()) {
            this.X = x;
        }
        return false;
    }
    
    private boolean \u00ef() {
        return this.y();
    }
    
    private boolean o() {
        final Token x = this.X;
        if (this.\u00ef()) {
            this.X = x;
            if (this.\u010d()) {
                this.X = x;
                if (this.V()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00c9() {
        return this.\u00d3();
    }
    
    private boolean \u00f5() {
        return this.I(59) || this.f();
    }
    
    private boolean \u00c5() {
        return this.I(59) || this.I(53) || this.I(50) || this.I(21) || this.f();
    }
    
    private boolean \u00e9() {
        if (this.f()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u00f5());
        this.X = x;
        return false;
    }
    
    private boolean \u00cb() {
        return this.\u00fd();
    }
    
    private boolean \u00fe() {
        if (this.\u00c9()) {
            return true;
        }
        if (this.I(56)) {
            return true;
        }
        final Token x = this.X;
        if (this.\u00e9()) {
            this.X = x;
        }
        return this.I(57);
    }
    
    private boolean \u00dd() {
        if (this.I(23)) {
            return true;
        }
        if (this.I(53)) {
            return true;
        }
        if (this.I(50)) {
            return true;
        }
        if (this.I(21)) {
            return true;
        }
        if (this.f()) {
            return true;
        }
        Token x;
        do {
            x = this.X;
        } while (!this.\u00c5());
        this.X = x;
        return this.I(34) || this.f();
    }
    
    private boolean l() {
        return this.\u00dd();
    }
    
    private boolean a() {
        return this.I(60) || this.f() || this.I(61);
    }
    
    private boolean \u00de() {
        return this.j();
    }
    
    private boolean £() {
        return this.\u00fd();
    }
    
    private boolean b() {
        return false;
    }
    
    private boolean Y() {
        return this.\u00dd();
    }
    
    private boolean f() {
        final Token x = this.X;
        if (this.Y()) {
            this.X = x;
            if (this.£()) {
                this.X = x;
                if (this.\u00de()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean \u00d2() {
        return this.\u00fe();
    }
    
    private boolean s() {
        return this.I(4);
    }
    
    private boolean P() {
        return this.I(2);
    }
    
    private boolean \u00d1() {
        return this.a();
    }
    
    private boolean \u0105() {
        return this.I(56) || this.f() || this.I(57);
    }
    
    private boolean \u00e6() {
        Token x;
        do {
            x = this.X;
        } while (!this.\u00d1());
        this.X = x;
        return false;
    }
    
    private static final void \u010e() {
        A.P = new int[] { -17807264, 16777216, 0, 16384, 64, 16448, 16448, -17823712, 32, 1073741824, -17823744, 2048, -17825792, Integer.MIN_VALUE, -17825772, -17803148, 0, 1048576, 33554432, 32896, 32896, 197376, 197376, 5120, 5120, 201328640, 201328640, -17803148, 8192, 0, 0, -17803148, 16448, 16448, 16448, -17823712, 0, 2129657856, -17825792, 2129657856 };
    }
    
    private static final void W() {
        A.O = new int[] { 13107199, 0, 1048576, 0, 0, 0, 0, 13107199, 0, 131002, 524287, 0, 524287, 131073, 19398655, 31981567, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31981567, 0, 268435456, 134217728, 31981567, 0, 0, 0, 13107199, 67108864, 393150, 524287, 393150 };
    }
    
    public A(final Reader reader) {
        this.S = new int[40];
        this.h = new _A[8];
        this.c = false;
        this.e = 0;
        this.M = new _B((_B)null);
        this.V = new ArrayList<int[]>();
        this.N = -1;
        this.f = new int[100];
        this._ = new SimpleCharStream(reader, 1, 1);
        this.Y = new XPathParserTokenManager(this._);
        this.T = new Token();
        this.b = -1;
        this.a = 0;
        for (int i = 0; i < 40; ++i) {
            this.S[i] = -1;
        }
        for (int j = 0; j < this.h.length; ++j) {
            this.h[j] = new _A();
        }
    }
    
    private Token J(final int n) throws ParseException {
        final Token t;
        if ((t = this.T).next != null) {
            this.T = this.T.next;
        }
        else {
            final Token t2 = this.T;
            final Token nextToken = this.Y.getNextToken();
            t2.next = nextToken;
            this.T = nextToken;
        }
        this.b = -1;
        if (this.T.kind == n) {
            ++this.a;
            if (++this.e > 100) {
                this.e = 0;
                for (int i = 0; i < this.h.length; ++i) {
                    for (_A b = this.h[i]; b != null; b = b.B) {
                        if (b.D < this.a) {
                            b.C = null;
                        }
                    }
                }
            }
            return this.T;
        }
        this.T = t;
        this.N = n;
        throw this.N();
    }
    
    private boolean I(final int n) {
        if (this.X == this.Q) {
            --this.W;
            if (this.X.next == null) {
                final Token x = this.X;
                final Token nextToken = this.Y.getNextToken();
                x.next = nextToken;
                this.X = nextToken;
                this.Q = nextToken;
            }
            else {
                final Token next = this.X.next;
                this.X = next;
                this.Q = next;
            }
        }
        else {
            this.X = this.X.next;
        }
        if (this.c) {
            int n2 = 0;
            Token token;
            for (token = this.T; token != null && token != this.X; token = token.next) {
                ++n2;
            }
            if (token != null) {
                this.F(n, n2);
            }
        }
        if (this.X.kind != n) {
            return true;
        }
        if (this.W == 0 && this.X == this.Q) {
            throw this.M;
        }
        return false;
    }
    
    public final Token K(final int n) {
        Token token = this.T;
        for (int i = 0; i < n; ++i) {
            if (token.next != null) {
                token = token.next;
            }
            else {
                final Token token2 = token;
                final Token nextToken = this.Y.getNextToken();
                token2.next = nextToken;
                token = nextToken;
            }
        }
        return token;
    }
    
    private int h() {
        final Token next = this.T.next;
        this.g = next;
        if (next == null) {
            final Token t = this.T;
            final Token nextToken = this.Y.getNextToken();
            t.next = nextToken;
            return this.b = nextToken.kind;
        }
        return this.b = this.g.kind;
    }
    
    private void F(final int n, final int r) {
        if (r >= 100) {
            return;
        }
        if (r == this.R + 1) {
            this.f[this.R++] = n;
        }
        else if (this.R != 0) {
            this.Z = new int[this.R];
            for (int i = 0; i < this.R; ++i) {
                this.Z[i] = this.f[i];
            }
        Label_0171:
            for (final int[] array : this.V) {
                if (array.length == this.Z.length) {
                    for (int j = 0; j < this.Z.length; ++j) {
                        if (array[j] != this.Z[j]) {
                            continue Label_0171;
                        }
                    }
                    this.V.add(this.Z);
                    break;
                }
            }
            if (r != 0) {
                this.f[(this.R = r) - 1] = n;
            }
        }
    }
    
    public ParseException N() {
        this.V.clear();
        final boolean[] array = new boolean[62];
        if (this.N >= 0) {
            array[this.N] = true;
            this.N = -1;
        }
        for (int i = 0; i < 40; ++i) {
            if (this.S[i] == this.a) {
                for (int j = 0; j < 32; ++j) {
                    if ((A.P[i] & 1 << j) != 0x0) {
                        array[j] = true;
                    }
                    if ((A.O[i] & 1 << j) != 0x0) {
                        array[32 + j] = true;
                    }
                }
            }
        }
        for (int k = 0; k < 62; ++k) {
            if (array[k]) {
                (this.Z = new int[1])[0] = k;
                this.V.add(this.Z);
            }
        }
        this.R = 0;
        this.\u0118();
        this.F(0, 0);
        final int[][] array2 = new int[this.V.size()][];
        for (int l = 0; l < this.V.size(); ++l) {
            array2[l] = this.V.get(l);
        }
        return new ParseException(this.T, array2, A.tokenImage);
    }
    
    private void \u0118() {
        this.c = true;
        for (int i = 0; i < 8; ++i) {
            try {
                _A b = this.h[i];
                do {
                    if (b.D > this.a) {
                        this.W = b.A;
                        final Token c = b.C;
                        this.X = c;
                        this.Q = c;
                        switch (i) {
                            case 0: {
                                this.ª();
                                break;
                            }
                            case 1: {
                                this.\u00e1();
                                break;
                            }
                            case 2: {
                                this.\u0101();
                                break;
                            }
                            case 3: {
                                this.H();
                                break;
                            }
                            case 4: {
                                this.l();
                                break;
                            }
                            case 5: {
                                this.\u00cb();
                                break;
                            }
                            case 6: {
                                this.\u00eb();
                                break;
                            }
                            case 7: {
                                this.\u010b();
                                break;
                            }
                        }
                    }
                    b = b.B;
                } while (b != null);
            }
            catch (_B b2) {}
        }
        this.c = false;
    }
    
    private void E(final int n, final int a) {
        _A b;
        for (b = this.h[n]; b.D > this.a; b = b.B) {
            if (b.B == null) {
                final _A a2 = b;
                final _A b2 = new _A();
                a2.B = b2;
                b = b2;
                break;
            }
        }
        b.D = this.a + a - this.W;
        b.C = this.T;
        b.A = a;
    }
    
    static final class _A
    {
        int D;
        Token C;
        int A;
        _A B;
    }
    
    private static final class _B extends Error
    {
    }
}

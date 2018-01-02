// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression.parser;

import prefuse.data.expression.Function;
import java.io.InputStream;
import prefuse.data.expression.IfExpression;
import prefuse.data.expression.FunctionTable;
import prefuse.data.expression.ColumnExpression;
import prefuse.data.expression.BooleanLiteral;
import prefuse.data.expression.ObjectLiteral;
import prefuse.data.expression.NotPredicate;
import prefuse.data.Tuple;
import prefuse.data.expression.NumericLiteral;
import prefuse.data.expression.ArithmeticExpression;
import prefuse.data.expression.ComparisonPredicate;
import prefuse.data.expression.AndPredicate;
import prefuse.data.expression.XorPredicate;
import prefuse.data.expression.OrPredicate;
import prefuse.data.expression.Predicate;
import prefuse.util.StringLib;
import java.io.Reader;
import java.io.StringReader;
import prefuse.data.expression.Expression;
import java.util.Vector;
import java.util.logging.Logger;

public class ExpressionParser implements ExpressionParserConstants
{
    private static final Logger s_logger;
    private static boolean s_init;
    private static Throwable s_error;
    private static boolean jj_initialized_once;
    public static ExpressionParserTokenManager token_source;
    static JavaCharStream jj_input_stream;
    public static Token token;
    public static Token jj_nt;
    private static int jj_ntk;
    private static int jj_gen;
    private static final int[] jj_la1;
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private static Vector jj_expentries;
    private static int[] jj_expentry;
    private static int jj_kind;
    
    public static synchronized Expression parse(final String s, final boolean b) {
        if (!ExpressionParser.s_init) {
            new ExpressionParser(new StringReader(s));
            ExpressionParser.s_init = true;
        }
        else {
            ReInit(new StringReader(s));
        }
        try {
            final Expression parse = Parse();
            ExpressionParser.s_error = null;
            ExpressionParser.s_logger.info("Parsed Expression: " + parse);
            return parse;
        }
        catch (ParseException s_error) {
            ExpressionParser.s_error = s_error;
            if (b) {
                throw s_error;
            }
            ExpressionParser.s_logger.warning("Expression Parse Error: " + s_error.getMessage() + "\n" + StringLib.getStackTrace(s_error));
            return null;
        }
    }
    
    public static synchronized Expression parse(final String s) {
        return parse(s, false);
    }
    
    public static synchronized Predicate predicate(final String s) {
        final Expression parse = parse(s, false);
        if (parse == null) {
            return null;
        }
        if (parse instanceof Predicate) {
            return (Predicate)parse;
        }
        ExpressionParser.s_error = new ClassCastException("Expression is not a predicate");
        return null;
    }
    
    public static synchronized Throwable getError() {
        return ExpressionParser.s_error;
    }
    
    private static String unescape(final String s) {
        final int length = s.length();
        int n = 0;
        final String s2 = "tnrbf\\\"'";
        final String s3 = "\t\n\r\b\f\\\"'";
        StringBuffer sb = null;
        int index;
        while ((index = s.indexOf(92, n)) != -1) {
            if (sb != null) {
                sb.append(s.substring(n, index));
            }
            if (index + 1 == length) {
                break;
            }
            final char char1 = s.charAt(index + 1);
            final int index2 = s2.indexOf(char1);
            if (index2 == -1) {
                sb.append('\\');
                sb.append(char1);
            }
            else {
                if (sb == null) {
                    sb = new StringBuffer(s.substring(n, index));
                }
                sb.append(s3.charAt(index2));
            }
            n = index + 2;
        }
        if (sb != null && n < length) {
            sb.append(s.substring(n));
        }
        return (sb == null) ? s : sb.toString();
    }
    
    public static final String Name() throws ParseException {
        return jj_consume_token(26).image;
    }
    
    public static final String Quoted() throws ParseException {
        final Token jj_consume_token = jj_consume_token(25);
        return jj_consume_token.image.substring(1, jj_consume_token.image.length() - 1);
    }
    
    public static final Expression Parse() throws ParseException {
        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 14:
            case 16:
            case 20:
            case 21:
            case 22:
            case 24:
            case 25:
            case 26:
            case 29:
            case 37:
            case 38: {
                final Expression expression = Expression();
                jj_consume_token(0);
                return expression;
            }
            case 0: {
                jj_consume_token(0);
                throw new ParseException("No expression provided");
            }
            default: {
                ExpressionParser.jj_la1[0] = ExpressionParser.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public static final Expression Expression() throws ParseException {
        return OrExpression();
    }
    
    public static final Expression OrExpression() throws ParseException {
        Expression xorExpression = XorExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 13: {
                    jj_consume_token(13);
                    final Expression xorExpression2 = XorExpression();
                    if (xorExpression instanceof OrPredicate) {
                        ((OrPredicate)xorExpression).add((Predicate)xorExpression2);
                        continue;
                    }
                    xorExpression = new OrPredicate((Predicate)xorExpression, (Predicate)xorExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[1] = ExpressionParser.jj_gen;
                    return xorExpression;
                }
            }
        }
    }
    
    public static final Expression XorExpression() throws ParseException {
        Expression andExpression = AndExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 15: {
                    jj_consume_token(15);
                    final Expression andExpression2 = AndExpression();
                    if (andExpression instanceof XorPredicate) {
                        ((XorPredicate)andExpression).add((Predicate)andExpression2);
                        continue;
                    }
                    andExpression = new XorPredicate((Predicate)andExpression, (Predicate)andExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[2] = ExpressionParser.jj_gen;
                    return andExpression;
                }
            }
        }
    }
    
    public static final Expression AndExpression() throws ParseException {
        Expression equalityExpression = EqualityExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 12: {
                    jj_consume_token(12);
                    final Expression equalityExpression2 = EqualityExpression();
                    if (equalityExpression instanceof AndPredicate) {
                        ((AndPredicate)equalityExpression).add((Predicate)equalityExpression2);
                        continue;
                    }
                    equalityExpression = new AndPredicate((Predicate)equalityExpression, (Predicate)equalityExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[3] = ExpressionParser.jj_gen;
                    return equalityExpression;
                }
            }
        }
    }
    
    public static final Expression EqualityExpression() throws ParseException {
        Expression relationalExpression = RelationalExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 31:
                case 36: {
                    Token token = null;
                    switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                        case 31: {
                            token = jj_consume_token(31);
                            break;
                        }
                        case 36: {
                            token = jj_consume_token(36);
                            break;
                        }
                        default: {
                            ExpressionParser.jj_la1[5] = ExpressionParser.jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    relationalExpression = new ComparisonPredicate((token.kind == 31) ? 2 : 3, relationalExpression, RelationalExpression());
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[4] = ExpressionParser.jj_gen;
                    return relationalExpression;
                }
            }
        }
    }
    
    public static final Expression RelationalExpression() throws ParseException {
        int n = -1;
        Expression additiveExpression = AdditiveExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 32:
                case 33:
                case 34:
                case 35: {
                    Token token = null;
                    switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                        case 33: {
                            token = jj_consume_token(33);
                            break;
                        }
                        case 32: {
                            token = jj_consume_token(32);
                            break;
                        }
                        case 34: {
                            token = jj_consume_token(34);
                            break;
                        }
                        case 35: {
                            token = jj_consume_token(35);
                            break;
                        }
                        default: {
                            ExpressionParser.jj_la1[7] = ExpressionParser.jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    final Expression additiveExpression2 = AdditiveExpression();
                    switch (token.kind) {
                        case 33: {
                            n = 0;
                            break;
                        }
                        case 32: {
                            n = 1;
                            break;
                        }
                        case 34: {
                            n = 4;
                            break;
                        }
                        case 35: {
                            n = 5;
                            break;
                        }
                    }
                    additiveExpression = new ComparisonPredicate(n, additiveExpression, additiveExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[6] = ExpressionParser.jj_gen;
                    return additiveExpression;
                }
            }
        }
    }
    
    public static final Expression AdditiveExpression() throws ParseException {
        int n = -1;
        Expression multiplicativeExpression = MultiplicativeExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 37:
                case 38:
                case 42: {
                    Token token = null;
                    switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                        case 37: {
                            token = jj_consume_token(37);
                            break;
                        }
                        case 38: {
                            token = jj_consume_token(38);
                            break;
                        }
                        case 42: {
                            token = jj_consume_token(42);
                            break;
                        }
                        default: {
                            ExpressionParser.jj_la1[9] = ExpressionParser.jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    final Expression multiplicativeExpression2 = MultiplicativeExpression();
                    switch (token.kind) {
                        case 37: {
                            n = 0;
                            break;
                        }
                        case 38: {
                            n = 1;
                            break;
                        }
                        case 42: {
                            n = 5;
                            break;
                        }
                    }
                    multiplicativeExpression = new ArithmeticExpression(n, multiplicativeExpression, multiplicativeExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[8] = ExpressionParser.jj_gen;
                    return multiplicativeExpression;
                }
            }
        }
    }
    
    public static final Expression MultiplicativeExpression() throws ParseException {
        int n = -1;
        Expression unaryExpression = UnaryExpression();
        while (true) {
            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                case 39:
                case 40:
                case 41: {
                    Token token = null;
                    switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                        case 39: {
                            token = jj_consume_token(39);
                            break;
                        }
                        case 40: {
                            token = jj_consume_token(40);
                            break;
                        }
                        case 41: {
                            token = jj_consume_token(41);
                            break;
                        }
                        default: {
                            ExpressionParser.jj_la1[11] = ExpressionParser.jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    final Expression unaryExpression2 = UnaryExpression();
                    switch (token.kind) {
                        case 39: {
                            n = 2;
                            break;
                        }
                        case 40: {
                            n = 3;
                            break;
                        }
                        case 41: {
                            n = 4;
                            break;
                        }
                    }
                    unaryExpression = new ArithmeticExpression(n, unaryExpression, unaryExpression2);
                    continue;
                }
                default: {
                    ExpressionParser.jj_la1[10] = ExpressionParser.jj_gen;
                    return unaryExpression;
                }
            }
        }
    }
    
    public static final Expression UnaryExpression() throws ParseException {
        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
            case 37:
            case 38: {
                Token token = null;
                switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                    case 37: {
                        token = jj_consume_token(37);
                        break;
                    }
                    case 38: {
                        token = jj_consume_token(38);
                        break;
                    }
                    default: {
                        ExpressionParser.jj_la1[12] = ExpressionParser.jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                final Expression unaryExpression = UnaryExpression();
                if (token.kind == 38 && unaryExpression instanceof NumericLiteral) {
                    final Number n = (Number)unaryExpression.get(null);
                    if (n instanceof Integer) {
                        return new NumericLiteral(-1 * n.intValue());
                    }
                    if (n instanceof Double) {
                        return new NumericLiteral(-1.0 * n.doubleValue());
                    }
                    if (n instanceof Long) {
                        return new NumericLiteral(-1L * n.longValue());
                    }
                    if (n instanceof Float) {
                        return new NumericLiteral(-1.0f * n.floatValue());
                    }
                    return new ArithmeticExpression(2, new NumericLiteral(-1), unaryExpression);
                }
                else {
                    if (token.kind == 38) {
                        return new ArithmeticExpression(2, new NumericLiteral(-1), unaryExpression);
                    }
                    return unaryExpression;
                }
                break;
            }
            case 14: {
                return UnaryExpressionNotPlusMinus();
            }
            case 6:
            case 7:
            case 8:
            case 9:
            case 16:
            case 20:
            case 21:
            case 22:
            case 24:
            case 25:
            case 26:
            case 29: {
                return PrimaryExpression();
            }
            default: {
                ExpressionParser.jj_la1[13] = ExpressionParser.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public static final Expression UnaryExpressionNotPlusMinus() throws ParseException {
        jj_consume_token(14);
        final Expression unaryExpression = UnaryExpression();
        if (unaryExpression instanceof NotPredicate) {
            return ((NotPredicate)unaryExpression).getPredicate();
        }
        if (!(unaryExpression instanceof Predicate)) {
            throw new ParseException("Can't negate a non-predicate");
        }
        return new NotPredicate((Predicate)unaryExpression);
    }
    
    public static final Expression PrimaryExpression() throws ParseException {
        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
            case 6:
            case 7:
            case 8:
            case 16:
            case 20:
            case 21:
            case 22:
            case 24: {
                return Literal();
            }
            case 9: {
                return IfStatement();
            }
            case 25:
            case 26: {
                return Identifier();
            }
            case 29: {
                jj_consume_token(29);
                final Expression expression = Expression();
                jj_consume_token(30);
                return expression;
            }
            default: {
                ExpressionParser.jj_la1[14] = ExpressionParser.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public static final Expression Literal() throws ParseException {
        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
            case 16: {
                return new NumericLiteral(Integer.parseInt(jj_consume_token(16).image));
            }
            case 20: {
                return new NumericLiteral(Long.parseLong(jj_consume_token(20).image));
            }
            case 22: {
                return new NumericLiteral(Float.parseFloat(jj_consume_token(22).image));
            }
            case 21: {
                return new NumericLiteral(Double.parseDouble(jj_consume_token(21).image));
            }
            case 24: {
                final Token jj_consume_token = jj_consume_token(24);
                return new ObjectLiteral(unescape(jj_consume_token.image.substring(1, jj_consume_token.image.length() - 1)));
            }
            case 6: {
                jj_consume_token(6);
                return new BooleanLiteral(true);
            }
            case 7: {
                jj_consume_token(7);
                return new BooleanLiteral(false);
            }
            case 8: {
                jj_consume_token(8);
                return new ObjectLiteral(null);
            }
            default: {
                ExpressionParser.jj_la1[15] = ExpressionParser.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public static final Expression Identifier() throws ParseException {
        Object function = null;
        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
            case 25: {
                return new ColumnExpression(Quoted());
            }
            case 26: {
                final String name = Name();
                switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                    case 29: {
                        jj_consume_token(29);
                        function = FunctionTable.createFunction(name);
                        Label_0356: {
                            switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 14:
                                case 16:
                                case 20:
                                case 21:
                                case 22:
                                case 24:
                                case 25:
                                case 26:
                                case 29:
                                case 37:
                                case 38: {
                                    ((Function)function).addParameter(Expression());
                                    while (true) {
                                        switch ((ExpressionParser.jj_ntk == -1) ? jj_ntk() : ExpressionParser.jj_ntk) {
                                            case 43: {
                                                jj_consume_token(43);
                                                ((Function)function).addParameter(Expression());
                                                continue;
                                            }
                                            default: {
                                                ExpressionParser.jj_la1[16] = ExpressionParser.jj_gen;
                                                break Label_0356;
                                            }
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    ExpressionParser.jj_la1[17] = ExpressionParser.jj_gen;
                                    break;
                                }
                            }
                        }
                        jj_consume_token(30);
                        break;
                    }
                    default: {
                        ExpressionParser.jj_la1[18] = ExpressionParser.jj_gen;
                        break;
                    }
                }
                return (Expression)((function == null) ? new ColumnExpression(name) : function);
            }
            default: {
                ExpressionParser.jj_la1[19] = ExpressionParser.jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public static final Expression IfStatement() throws ParseException {
        jj_consume_token(9);
        final Expression expression = Expression();
        jj_consume_token(10);
        final Expression expression2 = Expression();
        jj_consume_token(11);
        final Expression expression3 = Expression();
        if (!(expression instanceof Predicate)) {
            throw new ParseException("IF-statement test must be a predicate");
        }
        return new IfExpression((Predicate)expression, expression2, expression3);
    }
    
    private static void jj_la1_0() {
        ExpressionParser.jj_la1_0 = new int[] { 661734337, 8192, 32768, 4096, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 661734336, 661717952, 24183232, 0, 661734336, 536870912, 100663296 };
    }
    
    private static void jj_la1_1() {
        ExpressionParser.jj_la1_1 = new int[] { 96, 0, 0, 0, 16, 16, 15, 15, 1120, 1120, 896, 896, 96, 96, 0, 0, 2048, 96, 0, 0 };
    }
    
    public ExpressionParser(final InputStream inputStream) {
        if (ExpressionParser.jj_initialized_once) {
            System.out.println("ERROR: Second call to constructor of static parser.  You must");
            System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
            System.out.println("       during parser generation.");
            throw new Error();
        }
        ExpressionParser.jj_initialized_once = true;
        ExpressionParser.jj_input_stream = new JavaCharStream(inputStream, 1, 1);
        ExpressionParser.token_source = new ExpressionParserTokenManager(ExpressionParser.jj_input_stream);
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    public static void ReInit(final InputStream inputStream) {
        ExpressionParser.jj_input_stream.ReInit(inputStream, 1, 1);
        ExpressionParserTokenManager.ReInit(ExpressionParser.jj_input_stream);
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    public ExpressionParser(final Reader reader) {
        if (ExpressionParser.jj_initialized_once) {
            System.out.println("ERROR: Second call to constructor of static parser.  You must");
            System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
            System.out.println("       during parser generation.");
            throw new Error();
        }
        ExpressionParser.jj_initialized_once = true;
        ExpressionParser.jj_input_stream = new JavaCharStream(reader, 1, 1);
        ExpressionParser.token_source = new ExpressionParserTokenManager(ExpressionParser.jj_input_stream);
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    public static void ReInit(final Reader reader) {
        ExpressionParser.jj_input_stream.ReInit(reader, 1, 1);
        ExpressionParserTokenManager.ReInit(ExpressionParser.jj_input_stream);
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    public ExpressionParser(final ExpressionParserTokenManager token_source) {
        if (ExpressionParser.jj_initialized_once) {
            System.out.println("ERROR: Second call to constructor of static parser.  You must");
            System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
            System.out.println("       during parser generation.");
            throw new Error();
        }
        ExpressionParser.jj_initialized_once = true;
        ExpressionParser.token_source = token_source;
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    public void ReInit(final ExpressionParserTokenManager token_source) {
        ExpressionParser.token_source = token_source;
        ExpressionParser.token = new Token();
        ExpressionParser.jj_ntk = -1;
        ExpressionParser.jj_gen = 0;
        for (int i = 0; i < 20; ++i) {
            ExpressionParser.jj_la1[i] = -1;
        }
    }
    
    private static final Token jj_consume_token(final int jj_kind) throws ParseException {
        final Token token;
        if ((token = ExpressionParser.token).next != null) {
            ExpressionParser.token = ExpressionParser.token.next;
        }
        else {
            final Token token2 = ExpressionParser.token;
            final Token nextToken = ExpressionParserTokenManager.getNextToken();
            token2.next = nextToken;
            ExpressionParser.token = nextToken;
        }
        ExpressionParser.jj_ntk = -1;
        if (ExpressionParser.token.kind == jj_kind) {
            ++ExpressionParser.jj_gen;
            return ExpressionParser.token;
        }
        ExpressionParser.token = token;
        ExpressionParser.jj_kind = jj_kind;
        throw generateParseException();
    }
    
    public static final Token getNextToken() {
        if (ExpressionParser.token.next != null) {
            ExpressionParser.token = ExpressionParser.token.next;
        }
        else {
            final Token token = ExpressionParser.token;
            final Token nextToken = ExpressionParserTokenManager.getNextToken();
            token.next = nextToken;
            ExpressionParser.token = nextToken;
        }
        ExpressionParser.jj_ntk = -1;
        ++ExpressionParser.jj_gen;
        return ExpressionParser.token;
    }
    
    public static final Token getToken(final int n) {
        Token token = ExpressionParser.token;
        for (int i = 0; i < n; ++i) {
            if (token.next != null) {
                token = token.next;
            }
            else {
                final Token token2 = token;
                final Token nextToken = ExpressionParserTokenManager.getNextToken();
                token2.next = nextToken;
                token = nextToken;
            }
        }
        return token;
    }
    
    private static final int jj_ntk() {
        if ((ExpressionParser.jj_nt = ExpressionParser.token.next) == null) {
            final Token token = ExpressionParser.token;
            final Token nextToken = ExpressionParserTokenManager.getNextToken();
            token.next = nextToken;
            return ExpressionParser.jj_ntk = nextToken.kind;
        }
        return ExpressionParser.jj_ntk = ExpressionParser.jj_nt.kind;
    }
    
    public static ParseException generateParseException() {
        ExpressionParser.jj_expentries.removeAllElements();
        final boolean[] array = new boolean[44];
        for (int i = 0; i < 44; ++i) {
            array[i] = false;
        }
        if (ExpressionParser.jj_kind >= 0) {
            array[ExpressionParser.jj_kind] = true;
            ExpressionParser.jj_kind = -1;
        }
        for (int j = 0; j < 20; ++j) {
            if (ExpressionParser.jj_la1[j] == ExpressionParser.jj_gen) {
                for (int k = 0; k < 32; ++k) {
                    if ((ExpressionParser.jj_la1_0[j] & 1 << k) != 0x0) {
                        array[k] = true;
                    }
                    if ((ExpressionParser.jj_la1_1[j] & 1 << k) != 0x0) {
                        array[32 + k] = true;
                    }
                }
            }
        }
        for (int l = 0; l < 44; ++l) {
            if (array[l]) {
                (ExpressionParser.jj_expentry = new int[1])[0] = l;
                ExpressionParser.jj_expentries.addElement(ExpressionParser.jj_expentry);
            }
        }
        final int[][] array2 = new int[ExpressionParser.jj_expentries.size()][];
        for (int n = 0; n < ExpressionParser.jj_expentries.size(); ++n) {
            array2[n] = (int[])ExpressionParser.jj_expentries.elementAt(n);
        }
        return new ParseException(ExpressionParser.token, array2, ExpressionParser.tokenImage);
    }
    
    public static final void enable_tracing() {
    }
    
    public static final void disable_tracing() {
    }
    
    static {
        s_logger = Logger.getLogger(ExpressionParser.class.getName());
        ExpressionParser.s_init = false;
        ExpressionParser.jj_initialized_once = false;
        jj_la1 = new int[20];
        jj_la1_0();
        jj_la1_1();
        ExpressionParser.jj_expentries = new Vector();
        ExpressionParser.jj_kind = -1;
    }
}

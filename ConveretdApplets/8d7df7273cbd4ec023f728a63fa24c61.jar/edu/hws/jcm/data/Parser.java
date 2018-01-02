// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.io.Serializable;

public class Parser implements Serializable
{
    public static final int CASE_SENSITIVE = 1;
    public static final int OPTIONAL_STARS = 2;
    public static final int OPTIONAL_SPACES = 4;
    public static final int BRACKETS = 8;
    public static final int BRACES = 16;
    public static final int BOOLEANS = 32;
    public static final int FACTORIAL = 64;
    public static final int NO_UNDERSCORE_IN_IDENTIFIERS = 128;
    public static final int NO_DIGITS_IN_IDENTIFIERS = 256;
    public static final int OPTIONAL_PARENS = 512;
    public static final int STANDARD_FUNCTIONS = 1024;
    public static final int DEFAULT_OPTIONS = 1056;
    protected int options;
    protected SymbolTable symbols;
    
    public Parser() {
        this(null, 1056);
    }
    
    public Parser(final Parser parser) {
        this(parser, 0);
    }
    
    public Parser(final int n) {
        this(null, n);
    }
    
    public Parser(final Parser parser, final int n) {
        if (parser == null) {
            (this.symbols = new SymbolTable()).add(new Constant("e", 2.718281828459045));
            this.symbols.add(new Constant("pi", 3.141592653589793));
        }
        else {
            this.symbols = new SymbolTable(parser.symbols);
            this.options = parser.options;
        }
        this.addOptions(n);
    }
    
    public void addOptions(final int n) {
        if ((n & 0x400) != 0x0 && (this.options & 0x400) == 0x0) {
            for (int i = -36; i <= -17; ++i) {
                this.symbols.add(new StandardFunction(i));
            }
        }
        this.options |= n;
    }
    
    public ExpressionProgram parse(final String sourceString) {
        final ParserContext parserContext = new ParserContext(sourceString, this.options, this.symbols);
        if (sourceString == null) {
            throw new ParseError("Can't parse a null string.", parserContext);
        }
        if (parserContext.look() == 1) {
            throw new ParseError("Can't parse an empty string.", parserContext);
        }
        boolean b;
        if ((this.options & 0x20) != 0x0) {
            b = this.parseLogicalExpression(parserContext);
        }
        else {
            b = this.parseExpression(parserContext);
        }
        if (parserContext.look() != 1) {
            throw new ParseError("Extra data found after the end of a complete legal expression.", parserContext);
        }
        if (b) {
            throw new ParseError("Found a logical-valued expression instead of a numeric expression.", parserContext);
        }
        parserContext.prog.trim();
        parserContext.prog.sourceString = sourceString;
        return parserContext.prog;
    }
    
    public ExpressionProgram parseLogical(final String s) {
        if ((this.options & 0x20) == 0x0) {
            throw new IllegalArgumentException("Internal Error:  Attempt to parse a logical-valued expression, but BOOLEANS option is not turned on.");
        }
        final ParserContext parserContext = new ParserContext(s, this.options, this.symbols);
        if (s == null) {
            throw new ParseError("Can't parse a null string.", parserContext);
        }
        if (parserContext.look() == 1) {
            throw new ParseError("Can't parse an empty string.", parserContext);
        }
        final boolean logicalExpression = this.parseLogicalExpression(parserContext);
        if (parserContext.look() != 1) {
            throw new ParseError("Extra data found after the end of a complete legal expression.", parserContext);
        }
        if (!logicalExpression) {
            throw new ParseError("Found a numeric-valued expression instead of a logical expression.", parserContext);
        }
        parserContext.prog.trim();
        return parserContext.prog;
    }
    
    public MathObject get(final String s) {
        if ((this.options & 0x1) != 0x0) {
            return this.symbols.get(s);
        }
        return this.symbols.get(s.toLowerCase());
    }
    
    public void add(final MathObject mathObject) {
        if ((this.options & 0x1) != 0x0) {
            this.symbols.add(mathObject);
        }
        else {
            this.symbols.add(mathObject.getName().toLowerCase(), mathObject);
        }
    }
    
    public void remove(final String s) {
        if (s == null) {
            return;
        }
        if ((this.options & 0x1) != 0x0) {
            this.symbols.remove(s);
        }
        else {
            this.symbols.remove(s.toLowerCase());
        }
    }
    
    public boolean parseLogicalExpression(final ParserContext parserContext) {
        final boolean logicalTerm = this.parseLogicalTerm(parserContext);
        int n = parserContext.look();
        if (n == 4 && parserContext.tokenString.equals("&") && !logicalTerm) {
            throw new ParseError("The AND operator can only be used with logical expressions.", parserContext);
        }
        while (n == 4 && parserContext.tokenString.equals("&")) {
            parserContext.next();
            if (!this.parseLogicalTerm(parserContext)) {
                throw new ParseError("The AND operator can only be used with logical expressions.", parserContext);
            }
            parserContext.prog.addCommand(-12);
            n = parserContext.look();
        }
        if (n != 4 || !parserContext.tokenString.equals("?")) {
            return logicalTerm;
        }
        if (!logicalTerm) {
            throw new ParseError("The conditional operator, ?, can only be applied to a logical-valued expression.", parserContext);
        }
        final ExpressionProgram prog = parserContext.prog;
        parserContext.next();
        final ExpressionProgram prog2 = new ExpressionProgram();
        parserContext.prog = prog2;
        if (this.parseLogicalExpression(parserContext)) {
            throw new ParseError("The cases in a conditional expression cannot be logical-valued expressions.", parserContext);
        }
        ExpressionProgram prog3;
        if (parserContext.look() == 4 && parserContext.tokenString.equals(":")) {
            parserContext.next();
            prog3 = new ExpressionProgram();
            parserContext.prog = prog3;
            if (this.parseLogicalExpression(parserContext)) {
                throw new ParseError("The cases in a conditional expression cannot be logical-valued expressions.", parserContext);
            }
        }
        else {
            prog3 = null;
        }
        (parserContext.prog = prog).addCommandObject(new ConditionalExpression(prog2, prog3));
        return false;
    }
    
    public boolean parseLogicalTerm(final ParserContext parserContext) {
        final boolean logicalFactor = this.parseLogicalFactor(parserContext);
        int n = parserContext.look();
        if (n == 4 && parserContext.tokenString.equals("|") && !logicalFactor) {
            throw new ParseError("The OR operator can only be used with logical expressions.", parserContext);
        }
        while (n == 4 && parserContext.tokenString.equals("|")) {
            parserContext.next();
            if (!this.parseLogicalFactor(parserContext)) {
                throw new ParseError("The OR operator can only be used with logical expressions.", parserContext);
            }
            parserContext.prog.addCommand(-13);
            n = parserContext.look();
        }
        return logicalFactor;
    }
    
    public boolean parseLogicalFactor(final ParserContext parserContext) {
        int n;
        int n2;
        for (n = parserContext.look(), n2 = 0; n == 4 && parserContext.tokenString.equals("~"); n = parserContext.look(), ++n2) {
            parserContext.next();
        }
        final boolean relation = this.parseRelation(parserContext);
        if (n2 > 0 && !relation) {
            throw new ParseError("The NOT operator can only be used with logical expressions.", parserContext);
        }
        if (n2 % 2 == 1) {
            parserContext.prog.addCommand(-14);
        }
        return relation;
    }
    
    public boolean parseRelation(final ParserContext parserContext) {
        final boolean expression = this.parseExpression(parserContext);
        if (parserContext.look() != 4) {
            return expression;
        }
        int n = 0;
        if (parserContext.tokenString.equals("=")) {
            n = -6;
        }
        else if (parserContext.tokenString.equals("<")) {
            n = -8;
        }
        else if (parserContext.tokenString.equals(">")) {
            n = -9;
        }
        else if (parserContext.tokenString.equals("<=")) {
            n = -10;
        }
        else if (parserContext.tokenString.equals(">=")) {
            n = -11;
        }
        else if (parserContext.tokenString.equals("<>")) {
            n = -7;
        }
        if (n == 0) {
            return expression;
        }
        if (expression) {
            throw new ParseError("A relational operator can only be used with numerical expressions.", parserContext);
        }
        parserContext.next();
        if (this.parseExpression(parserContext)) {
            throw new ParseError("A relational operator can only be used with numerical expressions.", parserContext);
        }
        if (parserContext.look() == 4 && (parserContext.tokenString.equals("=") || parserContext.tokenString.equals("<") || parserContext.tokenString.equals(">") || parserContext.tokenString.equals("<=") || parserContext.tokenString.equals(">=") || parserContext.tokenString.equals("<>"))) {
            throw new ParseError("It is illegal to string together relations operators; use \"AND\" instead.", parserContext);
        }
        parserContext.prog.addCommand(n);
        return true;
    }
    
    public boolean parseExpression(final ParserContext parserContext) {
        boolean equals = false;
        if (parserContext.look() == 4 && (parserContext.tokenString.equals("+") || parserContext.tokenString.equals("-"))) {
            equals = parserContext.tokenString.equals("-");
            parserContext.next();
        }
        final boolean term = this.parseTerm(parserContext);
        if (equals) {
            if (term) {
                throw new ParseError("A unary + or - cannot be applied to a logical expression.", parserContext);
            }
            parserContext.prog.addCommand(-15);
        }
        int n = parserContext.look();
        if (n == 4 && (parserContext.tokenString.equals("+") || parserContext.tokenString.equals("-")) && term) {
            throw new ParseError("A + or - operator cannot be applied to logical operands.", parserContext);
        }
        while (n == 4 && (parserContext.tokenString.equals("+") || parserContext.tokenString.equals("-"))) {
            parserContext.next();
            final int n2 = parserContext.tokenString.equals("+") ? -1 : -2;
            if (this.parseTerm(parserContext)) {
                throw new ParseError("A + or - operator cannot be applied to logical operands.", parserContext);
            }
            parserContext.prog.addCommand(n2);
            n = parserContext.look();
        }
        return term;
    }
    
    public boolean parseTerm(final ParserContext parserContext) {
        final boolean primary = this.parsePrimary(parserContext);
        int n = parserContext.look();
        String s = parserContext.tokenString;
        boolean b = !primary && (this.options & 0x2) != 0x0 && (n == 2 || n == 3 || (n == 4 && (s.equals("(") || s.equals("[") || s.equals("{"))));
        if (n == 4 && (s.equals("*") || s.equals("/")) && primary) {
            throw new ParseError("A * or / operator cannot be applied to logical operands.", parserContext);
        }
        while (b || (n == 4 && (s.equals("*") || s.equals("/")))) {
            if (!b) {
                parserContext.next();
            }
            final int n2 = (b || s.equals("*")) ? -3 : -4;
            if (this.parsePrimary(parserContext)) {
                throw new ParseError("A * or / operator cannot be applied to logical operands.", parserContext);
            }
            parserContext.prog.addCommand(n2);
            n = parserContext.look();
            s = parserContext.tokenString;
            b = (!primary && (this.options & 0x2) != 0x0 && (n == 2 || n == 3 || (n == 4 && (s.equals("(") || s.equals("[") || s.equals("{")))));
        }
        return primary;
    }
    
    public boolean parsePrimary(final ParserContext parserContext) {
        final boolean factor = this.parseFactor(parserContext);
        if (parserContext.look() == 4 && parserContext.tokenString.equals("^")) {
            if (factor) {
                throw new ParseError("The exponentiation operator cannot be applied to logical operands.", parserContext);
            }
            parserContext.next();
            if (this.parsePrimary(parserContext)) {
                throw new ParseError("The exponentiation operator cannot be applied to logical operands.", parserContext);
            }
            parserContext.prog.addCommand(-5);
        }
        return factor;
    }
    
    public boolean parseFactor(final ParserContext parserContext) {
        boolean b = false;
        final int next = parserContext.next();
        if (next == 2) {
            parserContext.prog.addConstant(parserContext.tokenValue);
        }
        else if (next == 3) {
            this.parseWord(parserContext);
        }
        else {
            if (next == 1) {
                throw new ParseError("Data ended in the middle of an incomplete expression.", parserContext);
            }
            if (next != 4) {
                throw new ParseError("Internal error:  Unknown token type.", parserContext);
            }
            if (parserContext.tokenString.equals("(")) {
                b = this.parseGroup('(', ')', parserContext);
            }
            else if (parserContext.tokenString.equals("[") && (this.options & 0x8) != 0x0) {
                b = this.parseGroup('[', ']', parserContext);
            }
            else if (parserContext.tokenString.equals("{") && (this.options & 0x10) != 0x0) {
                b = this.parseGroup('{', '}', parserContext);
            }
            else {
                if (parserContext.tokenString.equals("}") && (this.options & 0x10) != 0x0) {
                    throw new ParseError("Misplaced right brace with no matching left brace.", parserContext);
                }
                if (parserContext.tokenString.equals("]") && (this.options & 0x8) != 0x0) {
                    throw new ParseError("Misplaced right bracket with no matching left bracket.", parserContext);
                }
                if (parserContext.tokenString.equals(")")) {
                    throw new ParseError("Misplaced right parenthesis with no matching left parenthesis.", parserContext);
                }
                throw new ParseError("Illegal or misplaced character \"" + parserContext.tokenString.charAt(0) + "\"", parserContext);
            }
        }
        if ((this.options & 0x40) != 0x0) {
            for (int n = parserContext.look(); n == 4 && parserContext.tokenString.equals("!"); n = parserContext.look()) {
                if (b) {
                    throw new ParseError("The factorial operator cannot be applied to a logical value.", parserContext);
                }
                parserContext.next();
                parserContext.prog.addCommand(-16);
            }
        }
        return b;
    }
    
    private boolean parseGroup(final char c, final char c2, final ParserContext parserContext) {
        final boolean b = ((this.options & 0x20) == 0x0) ? this.parseExpression(parserContext) : this.parseLogicalExpression(parserContext);
        if (parserContext.look() == 4 && parserContext.tokenString.equals(String.valueOf(c2))) {
            parserContext.next();
            return b;
        }
        throw new ParseError("Missing \"" + c2 + "\" to match a previous \"" + c + "\".", parserContext);
    }
    
    private void parseWord(final ParserContext parserContext) {
        if (parserContext.tokenObject == null) {
            throw new ParseError("Unknown word \"" + parserContext.tokenString + "\" encountered in an expression.", parserContext);
        }
        if (parserContext.tokenObject instanceof Variable || parserContext.tokenObject instanceof Constant) {
            parserContext.prog.addCommandObject((ExpressionCommand)parserContext.tokenObject);
        }
        else if (parserContext.tokenObject instanceof StandardFunction) {
            final StandardFunction standardFunction = (StandardFunction)parserContext.tokenObject;
            if (parserContext.look() == 4 && (parserContext.tokenString.equals("(") || (parserContext.tokenString.equals("[") && (this.options & 0x8) != 0x0) || (parserContext.tokenString.equals("{") && (this.options & 0x10) != 0x0))) {
                parserContext.next();
                boolean b;
                if (parserContext.tokenString.equals("(")) {
                    b = this.parseGroup('(', ')', parserContext);
                }
                else if (parserContext.tokenString.equals("[")) {
                    b = this.parseGroup('[', ']', parserContext);
                }
                else {
                    b = this.parseGroup('{', '}', parserContext);
                }
                if (b) {
                    throw new ParseError("The argument of a function must be a numerical expression.", parserContext);
                }
            }
            else {
                if ((this.options & 0x200) == 0x0) {
                    throw new ParseError("Parentheses required around argument of standard function \"" + standardFunction.getName() + "\".", parserContext);
                }
                if (this.parseTerm(parserContext)) {
                    throw new ParseError("The argument of a function must be a numerical expression.", parserContext);
                }
            }
            parserContext.prog.addCommand(standardFunction.getOpCode());
        }
        else if (parserContext.tokenObject instanceof ParserExtension) {
            ((ParserExtension)parserContext.tokenObject).doParse(this, parserContext);
        }
        else {
            if (!(parserContext.tokenObject instanceof ExpressionCommand)) {
                throw new ParseError("Unexpected word \"" + parserContext.tokenObject.getName() + "\" encountered in an expression.", parserContext);
            }
            throw new ParseError("Unimplemented word \"" + parserContext.tokenObject.getName() + "\" encountered in an expression.", parserContext);
        }
    }
}

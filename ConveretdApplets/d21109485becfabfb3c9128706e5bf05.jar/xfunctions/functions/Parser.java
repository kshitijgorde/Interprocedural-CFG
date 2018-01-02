// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class Parser
{
    private boolean autoDeclareVariables;
    protected SymbolTable symbols;
    private static final int EOS = 0;
    private static final int NUMBER = 1;
    private static final int WORD = 2;
    private static final int UNKNOWNWORD = 3;
    private static final int OPERATOR = 4;
    private static final int CHAR = 5;
    private static final int NONE = 6;
    private int pos;
    private String data;
    private StringBuffer token;
    private MathSymbol tokenSymbol;
    private String op;
    private int ttype;
    
    public void setAutoDeclareVariables(final boolean autoDeclareVariables) {
        this.autoDeclareVariables = autoDeclareVariables;
    }
    
    public boolean getAutoDeclareVariables() {
        return this.autoDeclareVariables;
    }
    
    public Parser() {
        this.autoDeclareVariables = false;
        this.token = new StringBuffer();
        this.symbols = new SymbolTable();
    }
    
    public Parser(final SymbolTable symbolTable) {
        this.autoDeclareVariables = false;
        this.token = new StringBuffer();
        this.symbols = new SymbolTable(symbolTable);
    }
    
    public SymbolTable getSymbolTable() {
        return this.symbols;
    }
    
    public Parser(final Parser parser) {
        this.autoDeclareVariables = false;
        this.token = new StringBuffer();
        this.symbols = new SymbolTable(parser.symbols);
    }
    
    public MathSymbol getSymbol(final String s) {
        return this.symbols.get(s);
    }
    
    public Variable defineVariable(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final Variable variable = new Variable(lowerCase);
        this.symbols.add(variable);
        return variable;
    }
    
    public ExpressionFunction defineFunction(final String s, final String s2, final String s3) throws ParseError {
        final Parser parser = new Parser(this.symbols);
        final ExpressionFunction expressionFunction = new ExpressionFunction(s.toLowerCase(), parser.defineVariable(s2), parser.parse(s3));
        this.symbols.add(expressionFunction);
        return expressionFunction;
    }
    
    public ExpressionFunction defineFunction(final String s, final String s2, final String s3, final String s4) throws ParseError {
        final Parser parser = new Parser(this.symbols);
        final ExpressionFunction expressionFunction = new ExpressionFunction(s.toLowerCase(), parser.defineVariable(s2), parser.defineVariable(s3), parser.parse(s4));
        this.symbols.add(expressionFunction);
        return expressionFunction;
    }
    
    public ExpressionFunction defineFunction(final String s, final String[] array, final String s2) throws ParseError {
        final Parser parser = new Parser(this.symbols);
        final Variable[] array2 = new Variable[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = parser.defineVariable(array[i]);
        }
        final ExpressionFunction expressionFunction = new ExpressionFunction(s.toLowerCase(), array2, parser.parse(s2));
        this.symbols.add(expressionFunction);
        return expressionFunction;
    }
    
    public synchronized Expression parse(final String data) throws ParseError {
        if (data == null || data.trim().length() == 0) {
            throw new ParseError("No input data provided!", 0, "");
        }
        this.pos = 0;
        this.data = data;
        this.ttype = 6;
        final Expression expression = this.parseExpression();
        if (this.peek() != 0) {
            throw new ParseError("Extra data found after the end of a complete legal expression.", this.pos, this.data);
        }
        return expression;
    }
    
    public synchronized Expression parseLogical(final String data) throws ParseError {
        if (data == null || data.trim().length() == 0) {
            throw new ParseError("No input data provided!", 0, "");
        }
        this.pos = 0;
        this.data = data;
        this.ttype = 6;
        final Expression logicalExpression = this.parseLogicalExpression();
        if (this.peek() != 0) {
            throw new ParseError("Extra data found after the end of a complete legal expression.", this.pos, this.data);
        }
        return logicalExpression;
    }
    
    public synchronized Expression parseExpressionWithCases(final String[] array) throws ParseError {
        int n;
        for (n = array.length - 1; n >= 0 && (array[n] == null || array[n].trim().length() == 0); --n) {}
        if (n < 0) {
            throw new ParseError("No input data provided!", 0, "");
        }
        int i = 0;
        ConditionalExpression conditionalExpression = null;
        Expression expression = null;
        while (i <= n) {
            Expression parse;
            try {
                if (array[i] == null || array[i].trim().length() == 0) {
                    throw new ParseError("Can't leave input number " + (i + 1) + " empty if there is data in later inputs.", 0, "");
                }
                parse = this.parse(array[i]);
                ++i;
            }
            catch (ParseError parseError) {
                parseError.errorInStringNumber = i;
                throw parseError;
            }
            if (i <= n) {
                try {
                    if (array[i] == null || array[i].trim().length() == 0) {
                        throw new ParseError("Can't leave input number " + i + " empty if there is data in later inputs.", 0, "");
                    }
                    parse = new ConditionalExpression(this.parseLogical(array[i]), parse);
                    ++i;
                }
                catch (ParseError parseError2) {
                    parseError2.errorInStringNumber = i;
                    throw parseError2;
                }
            }
            if (expression == null) {
                expression = parse;
            }
            if (conditionalExpression != null) {
                conditionalExpression.falseCase = parse;
            }
            conditionalExpression = (ConditionalExpression)parse;
        }
        return expression;
    }
    
    private int peek() {
        if (this.ttype == 6) {
            this.readToken();
        }
        return this.ttype;
    }
    
    private int get() {
        if (this.ttype == 6) {
            this.readToken();
        }
        final int ttype = this.ttype;
        if (this.ttype != 0) {
            this.ttype = 6;
        }
        return ttype;
    }
    
    private void readToken() {
        this.token.setLength(0);
        while (this.pos < this.data.length() && this.data.charAt(this.pos) <= ' ') {
            ++this.pos;
        }
        if (this.pos >= this.data.length()) {
            this.ttype = 0;
            return;
        }
        final char char1 = this.data.charAt(this.pos);
        if (Character.isLetter(char1)) {
            while (this.pos < this.data.length() && Character.isLetterOrDigit(this.data.charAt(this.pos))) {
                this.token.append(Character.toLowerCase(this.data.charAt(this.pos)));
                ++this.pos;
            }
            final String string = this.token.toString();
            final MathSymbol value = this.symbols.get(string);
            if (value != null) {
                this.ttype = 2;
                this.tokenSymbol = value;
            }
            else if (string.equals("and")) {
                this.ttype = 4;
                this.token.setLength(0);
                this.op = "&";
            }
            else if (string.equals("or")) {
                this.ttype = 4;
                this.token.setLength(0);
                this.op = "|";
            }
            else if (string.equals("not")) {
                this.ttype = 4;
                this.token.setLength(0);
                this.op = "~";
            }
            else {
                this.ttype = 3;
            }
        }
        else {
            if (!Character.isDigit(char1)) {
                if (char1 != '.') {
                    ++this.pos;
                    switch (char1) {
                        case '&':
                        case '*':
                        case '+':
                        case '-':
                        case '/':
                        case '^':
                        case '|':
                        case '~': {
                            this.op = String.valueOf(char1);
                            this.ttype = 4;
                            return;
                        }
                        case '<':
                        case '=':
                        case '>': {
                            this.op = String.valueOf(char1);
                            if (this.pos < this.data.length() && this.data.charAt(this.pos) != char1 && (this.data.charAt(this.pos) == '<' || this.data.charAt(this.pos) == '>' || this.data.charAt(this.pos) == '=')) {
                                this.op = String.valueOf(this.op) + this.data.charAt(this.pos++);
                            }
                            if (this.op.equals("=<")) {
                                this.op = "<=";
                            }
                            else if (this.op.equals("=>")) {
                                this.op = ">=";
                            }
                            else if (this.op.equals("><")) {
                                this.op = "<>";
                            }
                            this.ttype = 4;
                            return;
                        }
                        default: {
                            this.token.append(char1);
                            this.ttype = 5;
                            return;
                        }
                    }
                }
            }
            while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                this.token.append(this.data.charAt(this.pos++));
            }
            if (this.pos < this.data.length() && this.data.charAt(this.pos) == '.') {
                this.token.append(this.data.charAt(this.pos++));
                while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                    this.token.append(this.data.charAt(this.pos++));
                }
            }
            if (this.pos < this.data.length() && (this.data.charAt(this.pos) == 'e' || this.data.charAt(this.pos) == 'E')) {
                this.token.append(this.data.charAt(this.pos++));
                if (this.pos < this.data.length() && (this.data.charAt(this.pos) == '+' || this.data.charAt(this.pos) == '-')) {
                    this.token.append(this.data.charAt(this.pos++));
                }
                while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                    this.token.append(this.data.charAt(this.pos++));
                }
            }
            this.ttype = 1;
        }
    }
    
    protected Expression parseLogicalExpression() throws ParseError {
        Expression logicalTerm = this.parseLogicalTerm();
        this.peek();
        while (this.ttype == 4 && this.op.equals("&")) {
            this.get();
            logicalTerm = new BinaryNode(6, logicalTerm, this.parseLogicalTerm());
            this.peek();
        }
        return logicalTerm;
    }
    
    protected Expression parseLogicalTerm() throws ParseError {
        Expression logicalFactor = this.parseLogicalFactor();
        this.peek();
        while (this.ttype == 4 && this.op.equals("|")) {
            this.get();
            logicalFactor = new BinaryNode(7, logicalFactor, this.parseLogicalFactor());
            this.peek();
        }
        return logicalFactor;
    }
    
    protected Expression parseLogicalFactor() throws ParseError {
        this.peek();
        int n = 0;
        while (this.ttype == 4 && this.op.equals("~")) {
            this.get();
            this.peek();
            ++n;
        }
        Expression relation = this.parseRelation();
        if (n % 2 == 1) {
            relation = new UnaryNode(2, relation);
        }
        return relation;
    }
    
    protected Expression parseRelation() throws ParseError {
        Expression expression = null;
        Label_0110: {
            if (this.peek() == 5 && this.token.toString().equals("(")) {
                final int pos = this.pos;
                try {
                    expression = this.parseExpression();
                    break Label_0110;
                }
                catch (ParseError parseError) {
                    this.pos = pos;
                    this.ttype = 6;
                    final Expression logicalExpression = this.parseLogicalExpression();
                    if (this.get() != 5 || !this.token.toString().equals(")")) {
                        throw new ParseError("Missing right parenthesis.", this.pos, this.data);
                    }
                    return logicalExpression;
                }
            }
            expression = this.parseExpression();
        }
        this.peek();
        if (this.ttype != 4 || (!this.op.equals("=") && !this.op.equals("<") && !this.op.equals(">") && !this.op.equals("<=") && !this.op.equals(">=") && !this.op.equals("<>"))) {
            throw new ParseError("Expected to find a relational operator such as = or >=, while defining a logical condition.", this.pos, this.data);
        }
        this.get();
        int n = 0;
        if (this.op.equals("=")) {
            n = 8;
        }
        else if (this.op.equals("<")) {
            n = 10;
        }
        else if (this.op.equals(">")) {
            n = 11;
        }
        else if (this.op.equals("<=")) {
            n = 12;
        }
        else if (this.op.equals(">=")) {
            n = 13;
        }
        else if (this.op.equals("<>")) {
            n = 9;
        }
        final Expression expression2 = this.parseExpression();
        this.peek();
        if (this.ttype == 4 && (this.op.equals("=") || this.op.equals("<") || this.op.equals(">") || this.op.equals("<=") || this.op.equals(">=") || this.op.equals("<>"))) {
            throw new ParseError("It is illegal to string together relations operators; use \"AND\" instead.", this.pos, this.data);
        }
        return new BinaryNode(n, expression, expression2);
    }
    
    protected Expression parseExpression() throws ParseError {
        boolean equals = false;
        this.peek();
        if (this.ttype == 4 && (this.op.equals("+") || this.op.equals("-"))) {
            this.get();
            equals = this.op.equals("-");
        }
        Expression term = this.parseTerm();
        if (equals) {
            term = new UnaryNode(1, term);
        }
        this.peek();
        while (this.ttype == 4 && (this.op.equals("+") || this.op.equals("-"))) {
            this.get();
            term = new BinaryNode(this.op.equals("+") ? 1 : 2, term, this.parseTerm());
            this.peek();
        }
        return term;
    }
    
    protected Expression parseTerm() throws ParseError {
        Expression primary = this.parsePrimary();
        this.peek();
        while (this.ttype == 4 && (this.op.equals("*") || this.op.equals("/"))) {
            this.get();
            primary = new BinaryNode(this.op.equals("*") ? 3 : 4, primary, this.parsePrimary());
            this.peek();
        }
        return primary;
    }
    
    protected Expression parsePrimary() throws ParseError {
        Expression factor = this.parseFactor();
        for (int n = this.peek(); n == 4 && this.op.equals("^"); n = this.peek()) {
            this.get();
            factor = new BinaryNode(5, factor, this.parseFactor());
        }
        return factor;
    }
    
    protected Expression parseFactor() throws ParseError {
        final int value = this.get();
        if (value == 1) {
            return this.parseNumber();
        }
        if (value == 2) {
            return this.parseWord();
        }
        if (value == 3) {
            throw new ParseError("Unknown name (" + this.token.toString() + ") encountered.", this.pos, this.data);
        }
        if (value == 5 && this.token.toString().equals("(")) {
            final Expression expression = this.parseExpression();
            if (this.peek() == 5 && this.token.toString().equals(")")) {
                this.get();
                return expression;
            }
            throw new ParseError("Missing right parenthesis.", this.pos, this.data);
        }
        else {
            if (value == 0) {
                throw new ParseError("Data ended in the middle of an incomplete expression.", this.pos, this.data);
            }
            if (value == 4) {
                throw new ParseError("Misplaced operator.", this.pos, this.data);
            }
            if (value == 5 && this.token.toString().equals(")")) {
                throw new ParseError("Misplaced right parenthesis with no matching left parenthesis.", this.pos, this.data);
            }
            if (value == 5) {
                throw new ParseError("Unexpected character \"" + (Object)this.token + "\"", this.pos, this.data);
            }
            throw new ParseError("Internal program error??? Unknown token type.", this.pos, this.data);
        }
    }
    
    protected Expression parseNumber() throws ParseError {
        try {
            final Double n = new Double(this.token.toString());
            if (n.isNaN() || n.isInfinite()) {
                throw new ParseError("Illegal number.", this.pos, this.data);
            }
            return new ConstantNode(n);
        }
        catch (NumberFormatException ex) {
            throw new ParseError("Illegal number, \"" + this.token.toString() + "\".", this.pos, this.data);
        }
    }
    
    protected Expression parseWord() throws ParseError {
        final MathSymbol tokenSymbol = this.tokenSymbol;
        this.tokenSymbol = null;
        if (tokenSymbol == null) {
            throw new ParseError("Internal program error??? Null symbol.", this.pos, this.data);
        }
        if (tokenSymbol instanceof Variable) {
            return new VariableNode((Variable)tokenSymbol);
        }
        if (tokenSymbol instanceof NamedConstant) {
            return new NamedConstantNode((NamedConstant)tokenSymbol);
        }
        if (tokenSymbol instanceof Function) {
            return this.parseFunction((Function)tokenSymbol);
        }
        if (tokenSymbol instanceof StandardFunction) {
            return this.parseStandardFunction((StandardFunction)tokenSymbol);
        }
        throw new ParseError("Internal program error??? Unknown type of symbol.", this.pos, this.data);
    }
    
    protected Expression parseFunction(final Function function) throws ParseError {
        return new FunctionNode(function, this.parseParameterList(function.getArity()));
    }
    
    protected Expression parseStandardFunction(final StandardFunction standardFunction) throws ParseError {
        return new StandardFunctionNode(standardFunction.getOpcode(), this.parseParameterList(1)[0]);
    }
    
    protected Expression[] parseParameterList(final int n) throws ParseError {
        final Expression[] array = new Expression[n];
        if (this.get() != 5 || !this.token.toString().equals("(")) {
            throw new ParseError("Missing right parenthesis after function name.", this.pos, this.data);
        }
        for (int i = 0; i < n; ++i) {
            array[i] = this.parseExpression();
            final int value = this.get();
            if (i < n - 1) {
                if (value != 5 || !this.token.toString().equals(",")) {
                    throw new ParseError("Expected a comma followed by another parameter.", this.pos, this.data);
                }
            }
            else if (value != 5 || !this.token.toString().equals(")")) {
                throw new ParseError("Expected right parenthesis to end parameter list.", this.pos, this.data);
            }
        }
        return array;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XString;
import org.apache.xpath.domapi.XPathStylesheetDOM3Exception;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.XPathProcessorException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.ErrorListener;
import javax.xml.xpath.XPathFunctionResolver;
import org.apache.xml.utils.PrefixResolver;

public class XPathParser
{
    public static final String CONTINUE_AFTER_FATAL_ERROR = "CONTINUE_AFTER_FATAL_ERROR";
    private OpMap m_ops;
    transient String m_token;
    transient char m_tokenChar;
    int m_queueMark;
    protected static final int FILTER_MATCH_FAILED = 0;
    protected static final int FILTER_MATCH_PRIMARY = 1;
    protected static final int FILTER_MATCH_PREDICATES = 2;
    PrefixResolver m_namespaceContext;
    XPathFunctionResolver m_functionResolver;
    private ErrorListener m_errorListener;
    SourceLocator m_sourceLocator;
    private FunctionTable m_functionTable;
    
    public XPathParser(final ErrorListener errorListener, final SourceLocator sourceLocator) {
        this.m_tokenChar = '\0';
        this.m_queueMark = 0;
        this.m_errorListener = errorListener;
        this.m_sourceLocator = sourceLocator;
    }
    
    public void initXPath(final Compiler compiler, final String expression, final PrefixResolver namespaceContext) throws TransformerException {
        this.initXPath(compiler, expression, namespaceContext, null);
    }
    
    public void initXPath(final Compiler compiler, final String expression, final PrefixResolver namespaceContext, final XPathFunctionResolver functionResolver) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = namespaceContext;
        this.m_functionResolver = functionResolver;
        this.m_functionTable = compiler.getFunctionTable();
        final Lexer lexer = new Lexer(compiler, namespaceContext, this);
        lexer.tokenize(expression);
        this.m_ops.setOp(0, 1);
        this.m_ops.setOp(1, 2);
        try {
            this.nextToken();
            this.Expr();
            if (null != this.m_token) {
                String extraTokens = "";
                while (null != this.m_token) {
                    extraTokens = extraTokens + "'" + this.m_token + "'";
                    this.nextToken();
                    if (null != this.m_token) {
                        extraTokens += ", ";
                    }
                }
                this.error("ER_EXTRA_ILLEGAL_TOKENS", new Object[] { extraTokens });
            }
        }
        catch (XPathProcessorException e) {
            if (!"CONTINUE_AFTER_FATAL_ERROR".equals(e.getMessage())) {
                throw e;
            }
            this.initXPath(compiler, "/..", namespaceContext);
        }
        compiler.shrink();
    }
    
    public void initMatchPattern(final Compiler compiler, final String expression, final PrefixResolver namespaceContext) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = namespaceContext;
        this.m_functionTable = compiler.getFunctionTable();
        final Lexer lexer = new Lexer(compiler, namespaceContext, this);
        lexer.tokenize(expression);
        this.m_ops.setOp(0, 31);
        this.m_ops.setOp(1, 2);
        this.nextToken();
        this.Pattern();
        if (null != this.m_token) {
            String extraTokens = "";
            while (null != this.m_token) {
                extraTokens = extraTokens + "'" + this.m_token + "'";
                this.nextToken();
                if (null != this.m_token) {
                    extraTokens += ", ";
                }
            }
            this.error("ER_EXTRA_ILLEGAL_TOKENS", new Object[] { extraTokens });
        }
        this.m_ops.setOp(this.m_ops.getOp(1), -1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.m_ops.shrink();
    }
    
    public void setErrorHandler(final ErrorListener handler) {
        this.m_errorListener = handler;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    final boolean tokenIs(final String s) {
        return (this.m_token != null) ? this.m_token.equals(s) : (s == null);
    }
    
    final boolean tokenIs(final char c) {
        return this.m_token != null && this.m_tokenChar == c;
    }
    
    final boolean lookahead(final char c, final int n) {
        final int pos = this.m_queueMark + n;
        boolean b;
        if (pos <= this.m_ops.getTokenQueueSize() && pos > 0 && this.m_ops.getTokenQueueSize() != 0) {
            final String tok = (String)this.m_ops.m_tokenQueue.elementAt(pos - 1);
            b = (tok.length() == 1 && tok.charAt(0) == c);
        }
        else {
            b = false;
        }
        return b;
    }
    
    private final boolean lookbehind(final char c, final int n) {
        final int lookBehindPos = this.m_queueMark - (n + 1);
        boolean isToken;
        if (lookBehindPos >= 0) {
            final String lookbehind = (String)this.m_ops.m_tokenQueue.elementAt(lookBehindPos);
            if (lookbehind.length() == 1) {
                final char c2 = (lookbehind == null) ? '|' : lookbehind.charAt(0);
                isToken = (c2 != '|' && c2 == c);
            }
            else {
                isToken = false;
            }
        }
        else {
            isToken = false;
        }
        return isToken;
    }
    
    private final boolean lookbehindHasToken(final int n) {
        boolean hasToken;
        if (this.m_queueMark - n > 0) {
            final String lookbehind = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark - (n - 1));
            final char c0 = (lookbehind == null) ? '|' : lookbehind.charAt(0);
            hasToken = (c0 != '|');
        }
        else {
            hasToken = false;
        }
        return hasToken;
    }
    
    private final boolean lookahead(final String s, final int n) {
        boolean isToken;
        if (this.m_queueMark + n <= this.m_ops.getTokenQueueSize()) {
            final String lookahead = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark + (n - 1));
            isToken = ((lookahead != null) ? lookahead.equals(s) : (s == null));
        }
        else {
            isToken = (null == s);
        }
        return isToken;
    }
    
    private final void nextToken() {
        if (this.m_queueMark < this.m_ops.getTokenQueueSize()) {
            this.m_token = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark++);
            this.m_tokenChar = this.m_token.charAt(0);
        }
        else {
            this.m_token = null;
            this.m_tokenChar = '\0';
        }
    }
    
    private final String getTokenRelative(final int i) {
        final int relative = this.m_queueMark + i;
        String tok;
        if (relative > 0 && relative < this.m_ops.getTokenQueueSize()) {
            tok = (String)this.m_ops.m_tokenQueue.elementAt(relative);
        }
        else {
            tok = null;
        }
        return tok;
    }
    
    private final void prevToken() {
        if (this.m_queueMark > 0) {
            --this.m_queueMark;
            this.m_token = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark);
            this.m_tokenChar = this.m_token.charAt(0);
        }
        else {
            this.m_token = null;
            this.m_tokenChar = '\0';
        }
    }
    
    private final void consumeExpected(final String expected) throws TransformerException {
        if (this.tokenIs(expected)) {
            this.nextToken();
            return;
        }
        this.error("ER_EXPECTED_BUT_FOUND", new Object[] { expected, this.m_token });
        throw new XPathProcessorException("CONTINUE_AFTER_FATAL_ERROR");
    }
    
    private final void consumeExpected(final char expected) throws TransformerException {
        if (this.tokenIs(expected)) {
            this.nextToken();
            return;
        }
        this.error("ER_EXPECTED_BUT_FOUND", new Object[] { String.valueOf(expected), this.m_token });
        throw new XPathProcessorException("CONTINUE_AFTER_FATAL_ERROR");
    }
    
    void warn(final String msg, final Object[] args) throws TransformerException {
        final String fmsg = XPATHMessages.createXPATHWarning(msg, args);
        final ErrorListener ehandler = this.getErrorListener();
        if (null != ehandler) {
            ehandler.warning(new TransformerException(fmsg, this.m_sourceLocator));
        }
        else {
            System.err.println(fmsg);
        }
    }
    
    private void assertion(final boolean b, final String msg) {
        if (!b) {
            final String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg });
            throw new RuntimeException(fMsg);
        }
    }
    
    void error(final String msg, final Object[] args) throws TransformerException {
        final String fmsg = XPATHMessages.createXPATHMessage(msg, args);
        final ErrorListener ehandler = this.getErrorListener();
        final TransformerException te = new TransformerException(fmsg, this.m_sourceLocator);
        if (null != ehandler) {
            ehandler.fatalError(te);
            return;
        }
        throw te;
    }
    
    void errorForDOM3(final String msg, final Object[] args) throws TransformerException {
        final String fmsg = XPATHMessages.createXPATHMessage(msg, args);
        final ErrorListener ehandler = this.getErrorListener();
        final TransformerException te = new XPathStylesheetDOM3Exception(fmsg, this.m_sourceLocator);
        if (null != ehandler) {
            ehandler.fatalError(te);
            return;
        }
        throw te;
    }
    
    protected String dumpRemainingTokenQueue() {
        int q = this.m_queueMark;
        String returnMsg;
        if (q < this.m_ops.getTokenQueueSize()) {
            String msg;
            String t;
            for (msg = "\n Remaining tokens: ("; q < this.m_ops.getTokenQueueSize(); t = (String)this.m_ops.m_tokenQueue.elementAt(q++), msg = msg + " '" + t + "'") {}
            returnMsg = msg + ")";
        }
        else {
            returnMsg = "";
        }
        return returnMsg;
    }
    
    final int getFunctionToken(final String key) {
        int tok;
        try {
            Object id = Keywords.lookupNodeTest(key);
            if (null == id) {
                id = this.m_functionTable.getFunctionID(key);
            }
            tok = (int)id;
        }
        catch (NullPointerException npe) {
            tok = -1;
        }
        catch (ClassCastException cce) {
            tok = -1;
        }
        return tok;
    }
    
    void insertOp(final int pos, final int length, final int op) {
        final int totalLen = this.m_ops.getOp(1);
        for (int i = totalLen - 1; i >= pos; --i) {
            this.m_ops.setOp(i + length, this.m_ops.getOp(i));
        }
        this.m_ops.setOp(pos, op);
        this.m_ops.setOp(1, totalLen + length);
    }
    
    void appendOp(final int length, final int op) {
        final int totalLen = this.m_ops.getOp(1);
        this.m_ops.setOp(totalLen, op);
        this.m_ops.setOp(totalLen + 1, length);
        this.m_ops.setOp(1, totalLen + length);
    }
    
    protected void Expr() throws TransformerException {
        this.OrExpr();
    }
    
    protected void OrExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.AndExpr();
        if (null != this.m_token && this.tokenIs("or")) {
            this.nextToken();
            this.insertOp(opPos, 2, 2);
            this.OrExpr();
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        }
    }
    
    protected void AndExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.EqualityExpr(-1);
        if (null != this.m_token && this.tokenIs("and")) {
            this.nextToken();
            this.insertOp(opPos, 2, 3);
            this.AndExpr();
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        }
    }
    
    protected int EqualityExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        if (-1 == addPos) {
            addPos = opPos;
        }
        this.RelationalExpr(-1);
        if (null != this.m_token) {
            if (this.tokenIs('!') && this.lookahead('=', 1)) {
                this.nextToken();
                this.nextToken();
                this.insertOp(addPos, 2, 4);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.EqualityExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs('=')) {
                this.nextToken();
                this.insertOp(addPos, 2, 5);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.EqualityExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected int RelationalExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        if (-1 == addPos) {
            addPos = opPos;
        }
        this.AdditiveExpr(-1);
        if (null != this.m_token) {
            if (this.tokenIs('<')) {
                this.nextToken();
                if (this.tokenIs('=')) {
                    this.nextToken();
                    this.insertOp(addPos, 2, 6);
                }
                else {
                    this.insertOp(addPos, 2, 7);
                }
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.RelationalExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs('>')) {
                this.nextToken();
                if (this.tokenIs('=')) {
                    this.nextToken();
                    this.insertOp(addPos, 2, 8);
                }
                else {
                    this.insertOp(addPos, 2, 9);
                }
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.RelationalExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected int AdditiveExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        if (-1 == addPos) {
            addPos = opPos;
        }
        this.MultiplicativeExpr(-1);
        if (null != this.m_token) {
            if (this.tokenIs('+')) {
                this.nextToken();
                this.insertOp(addPos, 2, 10);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.AdditiveExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs('-')) {
                this.nextToken();
                this.insertOp(addPos, 2, 11);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.AdditiveExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected int MultiplicativeExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        if (-1 == addPos) {
            addPos = opPos;
        }
        this.UnaryExpr();
        if (null != this.m_token) {
            if (this.tokenIs('*')) {
                this.nextToken();
                this.insertOp(addPos, 2, 12);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs("div")) {
                this.nextToken();
                this.insertOp(addPos, 2, 13);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs("mod")) {
                this.nextToken();
                this.insertOp(addPos, 2, 14);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
            else if (this.tokenIs("quo")) {
                this.nextToken();
                this.insertOp(addPos, 2, 15);
                final int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected void UnaryExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        boolean isNeg = false;
        if (this.m_tokenChar == '-') {
            this.nextToken();
            this.appendOp(2, 16);
            isNeg = true;
        }
        this.UnionExpr();
        if (isNeg) {
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        }
    }
    
    protected void StringExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 17);
        this.Expr();
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected void BooleanExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 18);
        this.Expr();
        final int opLen = this.m_ops.getOp(1) - opPos;
        if (opLen == 2) {
            this.error("ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL", null);
        }
        this.m_ops.setOp(opPos + 1, opLen);
    }
    
    protected void NumberExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 19);
        this.Expr();
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected void UnionExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        final boolean continueOrLoop = true;
        boolean foundUnion = false;
        do {
            this.PathExpr();
            if (!this.tokenIs('|')) {
                break;
            }
            if (!foundUnion) {
                foundUnion = true;
                this.insertOp(opPos, 2, 20);
            }
            this.nextToken();
        } while (continueOrLoop);
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected void PathExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        final int filterExprMatch = this.FilterExpr();
        if (filterExprMatch != 0) {
            boolean locationPathStarted = filterExprMatch == 2;
            if (this.tokenIs('/')) {
                this.nextToken();
                if (!locationPathStarted) {
                    this.insertOp(opPos, 2, 29);
                    locationPathStarted = true;
                }
                if (!this.RelativeLocationPath()) {
                    this.error("ER_EXPECTED_REL_LOC_PATH", null);
                }
            }
            if (locationPathStarted) {
                this.m_ops.setOp(this.m_ops.getOp(1), -1);
                this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
                this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            }
        }
        else {
            this.LocationPath();
        }
    }
    
    protected int FilterExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        int filterMatch;
        if (this.PrimaryExpr()) {
            if (this.tokenIs('[')) {
                this.insertOp(opPos, 2, 29);
                while (this.tokenIs('[')) {
                    this.Predicate();
                }
                filterMatch = 2;
            }
            else {
                filterMatch = 1;
            }
        }
        else {
            filterMatch = 0;
        }
        return filterMatch;
    }
    
    protected boolean PrimaryExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        boolean matchFound;
        if (this.m_tokenChar == '\'' || this.m_tokenChar == '\"') {
            this.appendOp(2, 21);
            this.Literal();
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            matchFound = true;
        }
        else if (this.m_tokenChar == '$') {
            this.nextToken();
            this.appendOp(2, 22);
            this.QName();
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            matchFound = true;
        }
        else if (this.m_tokenChar == '(') {
            this.nextToken();
            this.appendOp(2, 23);
            this.Expr();
            this.consumeExpected(')');
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            matchFound = true;
        }
        else if (null != this.m_token && (('.' == this.m_tokenChar && this.m_token.length() > 1 && Character.isDigit(this.m_token.charAt(1))) || Character.isDigit(this.m_tokenChar))) {
            this.appendOp(2, 28);
            this.Number();
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            matchFound = true;
        }
        else {
            matchFound = ((this.lookahead('(', 1) || (this.lookahead(':', 1) && this.lookahead('(', 3))) && this.FunctionCall());
        }
        return matchFound;
    }
    
    protected void Argument() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 27);
        this.Expr();
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected boolean FunctionCall() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        if (this.lookahead(':', 1)) {
            this.appendOp(4, 24);
            this.m_ops.setOp(opPos + 1 + 1, this.m_queueMark - 1);
            this.nextToken();
            this.consumeExpected(':');
            this.m_ops.setOp(opPos + 1 + 2, this.m_queueMark - 1);
            this.nextToken();
        }
        else {
            final int funcTok = this.getFunctionToken(this.m_token);
            if (-1 == funcTok) {
                this.appendOp(3, 25);
                this.m_ops.setOp(opPos + 1 + 1, this.m_queueMark - 1);
                this.nextToken();
            }
            else {
                switch (funcTok) {
                    case 1030:
                    case 1031:
                    case 1032:
                    case 1033: {
                        return false;
                    }
                    default: {
                        this.appendOp(3, 26);
                        this.m_ops.setOp(opPos + 1 + 1, funcTok);
                        this.nextToken();
                        break;
                    }
                }
            }
        }
        this.consumeExpected('(');
        while (!this.tokenIs(')') && this.m_token != null) {
            if (this.tokenIs(',')) {
                this.error("ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG", null);
            }
            this.Argument();
            if (!this.tokenIs(')')) {
                this.consumeExpected(',');
                if (!this.tokenIs(')')) {
                    continue;
                }
                this.error("ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG", null);
            }
        }
        this.consumeExpected(')');
        this.m_ops.setOp(this.m_ops.getOp(1), -1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        return true;
    }
    
    protected void LocationPath() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 29);
        final boolean seenSlash = this.tokenIs('/');
        if (seenSlash) {
            this.appendOp(4, 50);
            this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
            this.m_ops.setOp(this.m_ops.getOp(1) - 1, 35);
            this.nextToken();
        }
        else if (this.m_token == null) {
            this.error("ER_EXPECTED_LOC_PATH_AT_END_EXPR", null);
        }
        if (this.m_token != null && !this.RelativeLocationPath() && !seenSlash) {
            this.error("ER_EXPECTED_LOC_PATH", new Object[] { this.m_token });
        }
        this.m_ops.setOp(this.m_ops.getOp(1), -1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected boolean RelativeLocationPath() throws TransformerException {
        if (!this.Step()) {
            return false;
        }
        while (this.tokenIs('/')) {
            this.nextToken();
            if (!this.Step()) {
                this.error("ER_EXPECTED_LOC_STEP", null);
            }
        }
        return true;
    }
    
    protected boolean Step() throws TransformerException {
        int opPos = this.m_ops.getOp(1);
        final boolean doubleSlash = this.tokenIs('/');
        if (doubleSlash) {
            this.nextToken();
            this.appendOp(2, 42);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.m_ops.setOp(this.m_ops.getOp(1), 1033);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
            opPos = this.m_ops.getOp(1);
        }
        if (this.tokenIs(".")) {
            this.nextToken();
            if (this.tokenIs('[')) {
                this.error("ER_PREDICATE_ILLEGAL_SYNTAX", null);
            }
            this.appendOp(4, 48);
            this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
            this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1033);
        }
        else if (this.tokenIs("..")) {
            this.nextToken();
            this.appendOp(4, 45);
            this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
            this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1033);
        }
        else {
            if (!this.tokenIs('*') && !this.tokenIs('@') && !this.tokenIs('_') && (this.m_token == null || !Character.isLetter(this.m_token.charAt(0)))) {
                if (doubleSlash) {
                    this.error("ER_EXPECTED_LOC_STEP", null);
                }
                return false;
            }
            this.Basis();
            while (this.tokenIs('[')) {
                this.Predicate();
            }
            this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        }
        return true;
    }
    
    protected void Basis() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        int axesType;
        if (this.lookahead("::", 1)) {
            axesType = this.AxisName();
            this.nextToken();
            this.nextToken();
        }
        else if (this.tokenIs('@')) {
            axesType = 39;
            this.appendOp(2, axesType);
            this.nextToken();
        }
        else {
            axesType = 40;
            this.appendOp(2, axesType);
        }
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.NodeTest(axesType);
        this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected int AxisName() throws TransformerException {
        final Object val = Keywords.getAxisName(this.m_token);
        if (null == val) {
            this.error("ER_ILLEGAL_AXIS_NAME", new Object[] { this.m_token });
        }
        final int axesType = (int)val;
        this.appendOp(2, axesType);
        return axesType;
    }
    
    protected void NodeTest(final int axesType) throws TransformerException {
        if (this.lookahead('(', 1)) {
            final Object nodeTestOp = Keywords.getNodeType(this.m_token);
            if (null == nodeTestOp) {
                this.error("ER_UNKNOWN_NODETYPE", new Object[] { this.m_token });
            }
            else {
                this.nextToken();
                final int nt = (int)nodeTestOp;
                this.m_ops.setOp(this.m_ops.getOp(1), nt);
                this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
                this.consumeExpected('(');
                if (1032 == nt && !this.tokenIs(')')) {
                    this.Literal();
                }
                this.consumeExpected(')');
            }
        }
        else {
            this.m_ops.setOp(this.m_ops.getOp(1), 34);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            if (this.lookahead(':', 1)) {
                if (this.tokenIs('*')) {
                    this.m_ops.setOp(this.m_ops.getOp(1), -3);
                }
                else {
                    this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
                    if (!Character.isLetter(this.m_tokenChar) && !this.tokenIs('_')) {
                        this.error("ER_EXPECTED_NODE_TEST", null);
                    }
                }
                this.nextToken();
                this.consumeExpected(':');
            }
            else {
                this.m_ops.setOp(this.m_ops.getOp(1), -2);
            }
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            if (this.tokenIs('*')) {
                this.m_ops.setOp(this.m_ops.getOp(1), -3);
            }
            else {
                this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
                if (!Character.isLetter(this.m_tokenChar) && !this.tokenIs('_')) {
                    this.error("ER_EXPECTED_NODE_TEST", null);
                }
            }
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.nextToken();
        }
    }
    
    protected void Predicate() throws TransformerException {
        if (this.tokenIs('[')) {
            this.nextToken();
            this.PredicateExpr();
            this.consumeExpected(']');
        }
    }
    
    protected void PredicateExpr() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        this.appendOp(2, 30);
        this.Expr();
        this.m_ops.setOp(this.m_ops.getOp(1), -1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected void QName() throws TransformerException {
        if (this.lookahead(':', 1)) {
            this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.nextToken();
            this.consumeExpected(':');
        }
        else {
            this.m_ops.setOp(this.m_ops.getOp(1), -2);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        }
        this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.nextToken();
    }
    
    protected void NCName() {
        this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.nextToken();
    }
    
    protected void Literal() throws TransformerException {
        final int last = this.m_token.length() - 1;
        final char c0 = this.m_tokenChar;
        final char cX = this.m_token.charAt(last);
        if ((c0 == '\"' && cX == '\"') || (c0 == '\'' && cX == '\'')) {
            final int tokenQueuePos = this.m_queueMark - 1;
            this.m_ops.m_tokenQueue.setElementAt(null, tokenQueuePos);
            final Object obj = new XString(this.m_token.substring(1, last));
            this.m_ops.m_tokenQueue.setElementAt(obj, tokenQueuePos);
            this.m_ops.setOp(this.m_ops.getOp(1), tokenQueuePos);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.nextToken();
        }
        else {
            this.error("ER_PATTERN_LITERAL_NEEDS_BE_QUOTED", new Object[] { this.m_token });
        }
    }
    
    protected void Number() throws TransformerException {
        if (null != this.m_token) {
            double num;
            try {
                if (this.m_token.indexOf(101) > -1 || this.m_token.indexOf(69) > -1) {
                    throw new NumberFormatException();
                }
                num = Double.valueOf(this.m_token);
            }
            catch (NumberFormatException nfe) {
                num = 0.0;
                this.error("ER_COULDNOT_BE_FORMATTED_TO_NUMBER", new Object[] { this.m_token });
            }
            this.m_ops.m_tokenQueue.setElementAt(new XNumber(num), this.m_queueMark - 1);
            this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
            this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
            this.nextToken();
        }
    }
    
    protected void Pattern() throws TransformerException {
        while (true) {
            this.LocationPathPattern();
            if (!this.tokenIs('|')) {
                break;
            }
            this.nextToken();
        }
    }
    
    protected void LocationPathPattern() throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        final int RELATIVE_PATH_NOT_PERMITTED = 0;
        final int RELATIVE_PATH_PERMITTED = 1;
        final int RELATIVE_PATH_REQUIRED = 2;
        int relativePathStatus = 0;
        this.appendOp(2, 32);
        if (this.lookahead('(', 1) && (this.tokenIs("id") || this.tokenIs("key"))) {
            this.IdKeyPattern();
            if (this.tokenIs('/')) {
                this.nextToken();
                if (this.tokenIs('/')) {
                    this.appendOp(4, 52);
                    this.nextToken();
                }
                else {
                    this.appendOp(4, 53);
                }
                this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
                this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1034);
                relativePathStatus = 2;
            }
        }
        else if (this.tokenIs('/')) {
            if (this.lookahead('/', 1)) {
                this.appendOp(4, 52);
                this.nextToken();
                relativePathStatus = 2;
            }
            else {
                this.appendOp(4, 50);
                relativePathStatus = 1;
            }
            this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
            this.m_ops.setOp(this.m_ops.getOp(1) - 1, 35);
            this.nextToken();
        }
        else {
            relativePathStatus = 2;
        }
        if (relativePathStatus != 0) {
            if (!this.tokenIs('|') && null != this.m_token) {
                this.RelativePathPattern();
            }
            else if (relativePathStatus == 2) {
                this.error("ER_EXPECTED_REL_PATH_PATTERN", null);
            }
        }
        this.m_ops.setOp(this.m_ops.getOp(1), -1);
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
    }
    
    protected void IdKeyPattern() throws TransformerException {
        this.FunctionCall();
    }
    
    protected void RelativePathPattern() throws TransformerException {
        boolean trailingSlashConsumed = this.StepPattern(false);
        while (this.tokenIs('/')) {
            this.nextToken();
            trailingSlashConsumed = this.StepPattern(!trailingSlashConsumed);
        }
    }
    
    protected boolean StepPattern(final boolean isLeadingSlashPermitted) throws TransformerException {
        return this.AbbreviatedNodeTestStep(isLeadingSlashPermitted);
    }
    
    protected boolean AbbreviatedNodeTestStep(final boolean isLeadingSlashPermitted) throws TransformerException {
        final int opPos = this.m_ops.getOp(1);
        int matchTypePos = -1;
        int axesType;
        if (this.tokenIs('@')) {
            axesType = 51;
            this.appendOp(2, axesType);
            this.nextToken();
        }
        else if (this.lookahead("::", 1)) {
            if (this.tokenIs("attribute")) {
                axesType = 51;
                this.appendOp(2, axesType);
            }
            else if (this.tokenIs("child")) {
                matchTypePos = this.m_ops.getOp(1);
                axesType = 53;
                this.appendOp(2, axesType);
            }
            else {
                axesType = -1;
                this.error("ER_AXES_NOT_ALLOWED", new Object[] { this.m_token });
            }
            this.nextToken();
            this.nextToken();
        }
        else if (this.tokenIs('/')) {
            if (!isLeadingSlashPermitted) {
                this.error("ER_EXPECTED_STEP_PATTERN", null);
            }
            axesType = 52;
            this.appendOp(2, axesType);
            this.nextToken();
        }
        else {
            matchTypePos = this.m_ops.getOp(1);
            axesType = 53;
            this.appendOp(2, axesType);
        }
        this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
        this.NodeTest(axesType);
        this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
        while (this.tokenIs('[')) {
            this.Predicate();
        }
        boolean trailingSlashConsumed;
        if (matchTypePos > -1 && this.tokenIs('/') && this.lookahead('/', 1)) {
            this.m_ops.setOp(matchTypePos, 52);
            this.nextToken();
            trailingSlashConsumed = true;
        }
        else {
            trailingSlashConsumed = false;
        }
        this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
        return trailingSlashConsumed;
    }
}

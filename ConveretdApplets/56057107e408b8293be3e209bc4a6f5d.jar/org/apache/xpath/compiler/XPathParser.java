// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XString;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.PrefixResolver;

public class XPathParser
{
    private OpMap m_ops;
    transient String m_token;
    transient char m_tokenChar;
    int m_queueMark;
    PrefixResolver m_namespaceContext;
    private ErrorListener m_errorListener;
    SourceLocator m_sourceLocator;
    
    public XPathParser(final ErrorListener errorListener, final SourceLocator sourceLocator) {
        this.m_tokenChar = '\0';
        this.m_queueMark = 0;
        this.m_errorListener = errorListener;
        this.m_sourceLocator = sourceLocator;
    }
    
    protected void AbbreviatedNodeTestStep() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
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
                axesType = 53;
                this.appendOp(2, axesType);
            }
            else {
                axesType = -1;
                this.error(44, new Object[] { this.m_token });
            }
            this.nextToken();
            this.nextToken();
        }
        else if (this.tokenIs('/')) {
            axesType = 52;
            this.appendOp(2, axesType);
            this.nextToken();
        }
        else {
            if (this.tokenIs('/')) {
                this.nextToken();
            }
            matchTypePos = this.m_ops.m_opMap[1];
            axesType = 53;
            this.appendOp(2, axesType);
        }
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.NodeTest(axesType);
        this.m_ops.m_opMap[opPos + 1 + 1] = this.m_ops.m_opMap[1] - opPos;
        while (this.tokenIs('[')) {
            this.Predicate();
        }
        if (matchTypePos > -1 && this.tokenIs('/') && this.lookahead('/', 1)) {
            this.m_ops.m_opMap[matchTypePos] = 52;
            this.nextToken();
        }
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected int AdditiveExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (addPos == -1) {
            addPos = opPos;
        }
        this.MultiplicativeExpr(-1);
        if (this.m_token != null) {
            if (this.tokenIs('+')) {
                this.nextToken();
                this.insertOp(addPos, 2, 10);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.AdditiveExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
            else if (this.tokenIs('-')) {
                this.nextToken();
                this.insertOp(addPos, 2, 11);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.AdditiveExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected void AndExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.EqualityExpr(-1);
        if (this.m_token != null && this.tokenIs("and")) {
            this.nextToken();
            this.insertOp(opPos, 2, 3);
            this.AndExpr();
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
    }
    
    protected void Argument() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 26);
        this.Expr();
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected int AxisName() throws TransformerException {
        final Object val = Keywords.m_axisnames.get(this.m_token);
        if (val == null) {
            this.error(35, new Object[] { this.m_token });
        }
        final int axesType = (int)val;
        this.appendOp(2, axesType);
        return axesType;
    }
    
    protected void Basis() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
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
            if (this.tokenIs('/')) {
                axesType = 42;
                this.appendOp(2, axesType);
                final int[] opMap = this.m_ops.m_opMap;
                final int n = 1;
                ++opMap[n];
                this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = 1033;
                final int[] opMap2 = this.m_ops.m_opMap;
                final int n2 = 1;
                ++opMap2[n2];
                this.m_ops.m_opMap[opPos + 1 + 1] = this.m_ops.m_opMap[1] - opPos;
                return;
            }
            axesType = 40;
            this.appendOp(2, axesType);
        }
        final int[] opMap3 = this.m_ops.m_opMap;
        final int n3 = 1;
        ++opMap3[n3];
        this.NodeTest(axesType);
        this.m_ops.m_opMap[opPos + 1 + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void BooleanExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 18);
        this.Expr();
        final int opLen = this.m_ops.m_opMap[1] - opPos;
        if (opLen == 2) {
            this.error(31, null);
        }
        this.m_ops.m_opMap[opPos + 1] = opLen;
    }
    
    protected int EqualityExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (addPos == -1) {
            addPos = opPos;
        }
        this.RelationalExpr(-1);
        if (this.m_token != null) {
            if (this.tokenIs('!') && this.lookahead('=', 1)) {
                this.nextToken();
                this.nextToken();
                this.insertOp(addPos, 2, 4);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.EqualityExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
            else if (this.tokenIs('=')) {
                this.nextToken();
                this.insertOp(addPos, 2, 5);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.EqualityExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected void Expr() throws TransformerException {
        this.OrExpr();
    }
    
    protected void FilterExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.PrimaryExpr();
        if (this.tokenIs('[')) {
            this.insertOp(opPos, 2, 28);
            while (this.tokenIs('[')) {
                this.Predicate();
            }
            if (this.tokenIs('/')) {
                this.nextToken();
                this.RelativeLocationPath();
            }
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
            final int[] opMap = this.m_ops.m_opMap;
            final int n = 1;
            ++opMap[n];
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
    }
    
    protected void FunctionCall() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (this.lookahead(':', 1)) {
            this.appendOp(4, 24);
            this.m_ops.m_opMap[opPos + 1 + 1] = this.m_queueMark - 1;
            this.nextToken();
            this.consumeExpected(':');
            this.m_ops.m_opMap[opPos + 1 + 2] = this.m_queueMark - 1;
            this.nextToken();
        }
        else {
            final int funcTok = this.getFunctionToken(this.m_token);
            if (funcTok == -1) {
                this.error(47, new Object[] { this.m_token });
            }
            switch (funcTok) {
                case 1030:
                case 1031:
                case 1032:
                case 1033: {
                    this.LocationPath();
                    return;
                }
                default: {
                    this.appendOp(3, 25);
                    this.m_ops.m_opMap[opPos + 1 + 1] = funcTok;
                    this.nextToken();
                    break;
                }
            }
        }
        this.consumeExpected('(');
        while (!this.tokenIs(')') && this.m_token != null) {
            if (this.tokenIs(',')) {
                this.error(32, null);
            }
            this.Argument();
            if (!this.tokenIs(')')) {
                this.consumeExpected(',');
                if (!this.tokenIs(')')) {
                    continue;
                }
                this.error(33, null);
            }
        }
        this.consumeExpected(')');
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void IdKeyPattern() throws TransformerException {
        this.FunctionCall();
    }
    
    protected void Literal() throws TransformerException {
        final int last = this.m_token.length() - 1;
        final char c0 = this.m_tokenChar;
        final char cX = this.m_token.charAt(last);
        if ((c0 == '\"' && cX == '\"') || (c0 == '\'' && cX == '\'')) {
            final int tokenQueuePos = this.m_queueMark - 1;
            this.m_ops.m_tokenQueue[tokenQueuePos] = null;
            final Object obj = new XString(this.m_token.substring(1, last));
            this.m_ops.m_tokenQueue[tokenQueuePos] = obj;
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = tokenQueuePos;
            final int[] opMap = this.m_ops.m_opMap;
            final int n = 1;
            ++opMap[n];
            this.nextToken();
        }
        else {
            this.error(37, new Object[] { this.m_token });
        }
    }
    
    protected void LocationPath() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 28);
        if (this.tokenIs('/')) {
            this.appendOp(4, 50);
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 2] = 4;
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 1] = 35;
            this.nextToken();
        }
        if (this.m_token != null) {
            this.RelativeLocationPath();
        }
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void LocationPathPattern() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 31);
        if (this.lookahead('(', 1) && (this.tokenIs("id") || this.tokenIs("key"))) {
            this.IdKeyPattern();
            if (this.tokenIs('/') && this.lookahead('/', 1)) {
                this.appendOp(4, 52);
                this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 2] = 4;
                this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 1] = 1034;
                this.nextToken();
                this.nextToken();
            }
        }
        else if (this.tokenIs('/')) {
            if (this.lookahead('/', 1)) {
                this.appendOp(4, 52);
            }
            else {
                this.appendOp(4, 50);
            }
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 2] = 4;
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 1] = 35;
            this.nextToken();
        }
        if (!this.tokenIs('|') && this.m_token != null) {
            this.RelativePathPattern();
        }
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected int MultiplicativeExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (addPos == -1) {
            addPos = opPos;
        }
        this.UnaryExpr();
        if (this.m_token != null) {
            if (this.tokenIs('*')) {
                this.nextToken();
                this.insertOp(addPos, 2, 12);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
            else if (this.tokenIs("div")) {
                this.nextToken();
                this.insertOp(addPos, 2, 13);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
            else if (this.tokenIs("mod")) {
                this.nextToken();
                this.insertOp(addPos, 2, 14);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
            else if (this.tokenIs("quo")) {
                this.nextToken();
                this.insertOp(addPos, 2, 15);
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.MultiplicativeExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected void NCName() {
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.nextToken();
    }
    
    protected void NodeTest(final int axesType) throws TransformerException {
        if (this.lookahead('(', 1)) {
            final Object nodeTestOp = Keywords.m_nodetypes.get(this.m_token);
            if (nodeTestOp == null) {
                this.error(36, new Object[] { this.m_token });
            }
            else {
                this.nextToken();
                final int nt = (int)nodeTestOp;
                this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = nt;
                final int[] opMap = this.m_ops.m_opMap;
                final int n = 1;
                ++opMap[n];
                this.consumeExpected('(');
                if (nt == 1032 && !this.tokenIs(')')) {
                    this.Literal();
                }
                this.consumeExpected(')');
            }
        }
        else {
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = 34;
            final int[] opMap2 = this.m_ops.m_opMap;
            final int n2 = 1;
            ++opMap2[n2];
            if (this.lookahead(':', 1)) {
                if (this.tokenIs('*')) {
                    this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -3;
                }
                else {
                    this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
                }
                this.nextToken();
                this.consumeExpected(':');
            }
            else {
                this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -2;
            }
            final int[] opMap3 = this.m_ops.m_opMap;
            final int n3 = 1;
            ++opMap3[n3];
            if (this.tokenIs('*')) {
                this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -3;
            }
            else {
                if (axesType == 49) {
                    final String prefix = (String)this.m_ops.m_tokenQueue[this.m_queueMark - 1];
                    final String namespace = this.m_namespaceContext.getNamespaceForPrefix(prefix);
                    this.m_ops.m_tokenQueue[this.m_queueMark - 1] = namespace;
                }
                this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
            }
            final int[] opMap4 = this.m_ops.m_opMap;
            final int n4 = 1;
            ++opMap4[n4];
            this.nextToken();
        }
    }
    
    protected void Number() throws TransformerException {
        if (this.m_token != null) {
            double num;
            try {
                num = Double.valueOf(this.m_token);
            }
            catch (NumberFormatException ex) {
                num = 0.0;
                this.error(38, new Object[] { this.m_token });
            }
            this.m_ops.m_tokenQueue[this.m_queueMark - 1] = new XNumber(num);
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
            final int[] opMap = this.m_ops.m_opMap;
            final int n = 1;
            ++opMap[n];
            this.nextToken();
        }
    }
    
    protected void NumberExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 19);
        this.Expr();
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void OrExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.AndExpr();
        if (this.m_token != null && this.tokenIs("or")) {
            this.nextToken();
            this.insertOp(opPos, 2, 2);
            this.OrExpr();
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
    }
    
    protected void PathExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.FilterExpr();
        if (this.tokenIs('/')) {
            this.nextToken();
            this.insertOp(opPos, 2, 28);
            this.RelativeLocationPath();
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
            final int[] opMap = this.m_ops.m_opMap;
            final int n = 1;
            ++opMap[n];
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
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
    
    protected void Predicate() throws TransformerException {
        if (this.tokenIs('[')) {
            this.nextToken();
            this.PredicateExpr();
            this.consumeExpected(']');
        }
    }
    
    protected void PredicateExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 29);
        this.Expr();
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void PrimaryExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (this.m_tokenChar == '\'' || this.m_tokenChar == '\"') {
            this.appendOp(2, 21);
            this.Literal();
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
        else if (this.m_tokenChar == '$') {
            this.nextToken();
            this.appendOp(2, 22);
            this.QName();
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
        else if (this.m_tokenChar == '(') {
            this.nextToken();
            this.appendOp(2, 23);
            this.Expr();
            this.consumeExpected(')');
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
        else if (this.m_token != null && ((this.m_tokenChar == '.' && this.m_token.length() > 1 && Character.isDigit(this.m_token.charAt(1))) || Character.isDigit(this.m_tokenChar))) {
            this.appendOp(2, 27);
            this.Number();
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
        else if (this.lookahead('(', 1) || (this.lookahead(':', 1) && this.lookahead('(', 3))) {
            this.FunctionCall();
        }
        else {
            this.LocationPath();
        }
    }
    
    protected void QName() throws TransformerException {
        if (this.lookahead(':', 1)) {
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
            final int[] opMap = this.m_ops.m_opMap;
            final int n = 1;
            ++opMap[n];
            this.nextToken();
            this.consumeExpected(':');
        }
        else {
            this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -2;
            final int[] opMap2 = this.m_ops.m_opMap;
            final int n2 = 1;
            ++opMap2[n2];
        }
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = this.m_queueMark - 1;
        final int[] opMap3 = this.m_ops.m_opMap;
        final int n3 = 1;
        ++opMap3[n3];
        this.nextToken();
    }
    
    protected int RelationalExpr(int addPos) throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (addPos == -1) {
            addPos = opPos;
        }
        this.AdditiveExpr(-1);
        if (this.m_token != null) {
            if (this.tokenIs('<')) {
                this.nextToken();
                if (this.tokenIs('=')) {
                    this.nextToken();
                    this.insertOp(addPos, 2, 6);
                }
                else {
                    this.insertOp(addPos, 2, 7);
                }
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.RelationalExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
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
                final int opPlusLeftHandLen = this.m_ops.m_opMap[1] - addPos;
                addPos = this.RelationalExpr(addPos);
                this.m_ops.m_opMap[addPos + 1] = this.m_ops.m_opMap[addPos + opPlusLeftHandLen + 1] + opPlusLeftHandLen;
                addPos += 2;
            }
        }
        return addPos;
    }
    
    protected void RelativeLocationPath() throws TransformerException {
        this.Step();
        while (this.tokenIs('/')) {
            this.nextToken();
            this.Step();
        }
    }
    
    protected void RelativePathPattern() throws TransformerException {
        this.StepPattern();
        while (this.tokenIs('/')) {
            this.nextToken();
            this.StepPattern();
        }
    }
    
    protected void Step() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        if (this.tokenIs(".")) {
            this.nextToken();
            if (this.tokenIs('[')) {
                this.error(34, null);
            }
            this.appendOp(4, 48);
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 2] = 4;
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 1] = 1033;
        }
        else if (this.tokenIs("..")) {
            this.nextToken();
            this.appendOp(4, 45);
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 2] = 4;
            this.m_ops.m_opMap[this.m_ops.m_opMap[1] - 1] = 1033;
        }
        else if (this.tokenIs('*') || this.tokenIs('@') || this.tokenIs('/') || this.tokenIs('_') || Character.isLetter(this.m_token.charAt(0))) {
            this.Basis();
            while (this.tokenIs('[')) {
                this.Predicate();
            }
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
    }
    
    protected void StepPattern() throws TransformerException {
        this.AbbreviatedNodeTestStep();
    }
    
    protected void StringExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        this.appendOp(2, 17);
        this.Expr();
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    protected void UnaryExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
        boolean isNeg = false;
        if (this.m_tokenChar == '-') {
            this.nextToken();
            this.appendOp(2, 16);
            isNeg = true;
        }
        this.UnionExpr();
        if (isNeg) {
            this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
        }
    }
    
    protected void UnionExpr() throws TransformerException {
        final int opPos = this.m_ops.m_opMap[1];
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
        this.m_ops.m_opMap[opPos + 1] = this.m_ops.m_opMap[1] - opPos;
    }
    
    void appendOp(final int length, final int op) {
        final int totalLen = this.m_ops.m_opMap[1];
        this.m_ops.m_opMap[totalLen] = op;
        this.m_ops.m_opMap[totalLen + 1] = length;
        this.m_ops.m_opMap[1] = totalLen + length;
    }
    
    private void assert(final boolean b, final String msg) {
        if (!b) {
            final String fMsg = XSLMessages.createXPATHMessage(30, new Object[] { msg });
            throw new RuntimeException(fMsg);
        }
    }
    
    private final void consumeExpected(final char expected) throws TransformerException {
        if (this.tokenIs(expected)) {
            this.nextToken();
        }
        else {
            this.error(29, new Object[] { String.valueOf(expected), this.m_token });
        }
    }
    
    private final void consumeExpected(final String expected) throws TransformerException {
        if (this.tokenIs(expected)) {
            this.nextToken();
        }
        else {
            this.error(29, new Object[] { expected, this.m_token });
        }
    }
    
    protected String dumpRemainingTokenQueue() {
        int q = this.m_queueMark;
        String returnMsg;
        if (q < this.m_ops.m_tokenQueueSize) {
            String msg;
            String t;
            for (msg = "\n Remaining tokens: ("; q < this.m_ops.m_tokenQueueSize; t = (String)this.m_ops.m_tokenQueue[q++], msg = String.valueOf(msg) + " '" + t + "'") {}
            returnMsg = String.valueOf(msg) + ")";
        }
        else {
            returnMsg = "";
        }
        return returnMsg;
    }
    
    void error(final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHMessage(msg, args);
        final ErrorListener ehandler = this.getErrorListener();
        final TransformerException te = new TransformerException(fmsg, this.m_sourceLocator);
        if (ehandler != null) {
            ehandler.fatalError(te);
            return;
        }
        throw te;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    final int getFunctionToken(final String key) {
        int tok;
        try {
            tok = Keywords.m_functions.get(key);
        }
        catch (NullPointerException ex) {
            tok = -1;
        }
        catch (ClassCastException ex2) {
            tok = -1;
        }
        return tok;
    }
    
    private final String getTokenRelative(final int i) {
        final int relative = this.m_queueMark + i;
        String tok;
        if (relative > 0 && relative < this.m_ops.m_tokenQueueSize) {
            tok = (String)this.m_ops.m_tokenQueue[relative];
        }
        else {
            tok = null;
        }
        return tok;
    }
    
    public void initMatchPattern(final Compiler compiler, final String expression, final PrefixResolver namespaceContext) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = namespaceContext;
        final Lexer lexer = new Lexer(compiler, namespaceContext, this);
        lexer.tokenize(expression);
        this.m_ops.m_opMap[0] = 30;
        this.m_ops.m_opMap[1] = 2;
        this.nextToken();
        this.Pattern();
        if (this.m_token != null) {
            String extraTokens = "";
            while (this.m_token != null) {
                extraTokens = String.valueOf(extraTokens) + "'" + this.m_token + "'";
                this.nextToken();
                if (this.m_token != null) {
                    extraTokens = String.valueOf(extraTokens) + ", ";
                }
            }
            this.error(25, new Object[] { extraTokens });
        }
        this.m_ops.m_opMap[this.m_ops.m_opMap[1]] = -1;
        final int[] opMap = this.m_ops.m_opMap;
        final int n = 1;
        ++opMap[n];
        this.m_ops.shrink();
    }
    
    public void initXPath(final Compiler compiler, final String expression, final PrefixResolver namespaceContext) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = namespaceContext;
        final Lexer lexer = new Lexer(compiler, namespaceContext, this);
        lexer.tokenize(expression);
        this.m_ops.m_opMap[0] = 1;
        this.m_ops.m_opMap[1] = 2;
        this.nextToken();
        this.Expr();
        if (this.m_token != null) {
            String extraTokens = "";
            while (this.m_token != null) {
                extraTokens = String.valueOf(extraTokens) + "'" + this.m_token + "'";
                this.nextToken();
                if (this.m_token != null) {
                    extraTokens = String.valueOf(extraTokens) + ", ";
                }
            }
            this.error(25, new Object[] { extraTokens });
        }
        compiler.shrink();
    }
    
    void insertOp(final int pos, final int length, final int op) {
        final int totalLen = this.m_ops.m_opMap[1];
        for (int i = totalLen - 1; i >= pos; --i) {
            this.m_ops.m_opMap[i + length] = this.m_ops.m_opMap[i];
        }
        this.m_ops.m_opMap[pos] = op;
        this.m_ops.m_opMap[1] = totalLen + length;
    }
    
    final boolean lookahead(final char c, final int n) {
        final int pos = this.m_queueMark + n;
        boolean b;
        if (pos <= this.m_ops.m_tokenQueueSize && pos > 0 && this.m_ops.m_tokenQueueSize != 0) {
            final String tok = (String)this.m_ops.m_tokenQueue[pos - 1];
            b = (tok.length() == 1 && tok.charAt(0) == c);
        }
        else {
            b = false;
        }
        return b;
    }
    
    private final boolean lookahead(final String s, final int n) {
        boolean isToken;
        if (this.m_queueMark + n <= this.m_ops.m_tokenQueueSize) {
            final String lookahead = (String)this.m_ops.m_tokenQueue[this.m_queueMark + (n - 1)];
            isToken = ((lookahead != null) ? lookahead.equals(s) : (s == null));
        }
        else {
            isToken = (s == null);
        }
        return isToken;
    }
    
    private final boolean lookbehind(final char c, final int n) {
        final int lookBehindPos = this.m_queueMark - (n + 1);
        boolean isToken;
        if (lookBehindPos >= 0) {
            final String lookbehind = (String)this.m_ops.m_tokenQueue[lookBehindPos];
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
            final String lookbehind = (String)this.m_ops.m_tokenQueue[this.m_queueMark - (n - 1)];
            final char c0 = (lookbehind == null) ? '|' : lookbehind.charAt(0);
            hasToken = (c0 != '|');
        }
        else {
            hasToken = false;
        }
        return hasToken;
    }
    
    private final void nextToken() {
        if (this.m_queueMark < this.m_ops.m_tokenQueueSize) {
            this.m_token = (String)this.m_ops.m_tokenQueue[this.m_queueMark++];
            this.m_tokenChar = this.m_token.charAt(0);
        }
        else {
            this.m_token = null;
            this.m_tokenChar = '\0';
        }
    }
    
    private final void prevToken() {
        if (this.m_queueMark > 0) {
            --this.m_queueMark;
            this.m_token = (String)this.m_ops.m_tokenQueue[this.m_queueMark];
            this.m_tokenChar = this.m_token.charAt(0);
        }
        else {
            this.m_token = null;
            this.m_tokenChar = '\0';
        }
    }
    
    public void setErrorHandler(final ErrorListener handler) {
        this.m_errorListener = handler;
    }
    
    final boolean tokenIs(final char c) {
        return this.m_token != null && this.m_tokenChar == c;
    }
    
    final boolean tokenIs(final String s) {
        return (this.m_token != null) ? this.m_token.equals(s) : (s == null);
    }
    
    void warn(final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHWarning(msg, args);
        final ErrorListener ehandler = this.getErrorListener();
        if (ehandler != null) {
            ehandler.warning(new TransformerException(fmsg, this.m_sourceLocator));
        }
        else {
            System.err.println(fmsg);
        }
    }
}

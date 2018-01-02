// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import org.apache.xml.utils.QName;
import org.apache.xpath.operations.Variable;
import org.apache.xpath.axes.UnionPathIterator;
import org.apache.xpath.patterns.AncestorStepPattern;
import org.apache.xpath.patterns.FunctionPattern;
import org.apache.xpath.operations.Plus;
import org.apache.xpath.operations.Or;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.operations.Number;
import org.apache.xpath.operations.NotEquals;
import org.apache.xpath.operations.Neg;
import org.apache.xpath.operations.Mult;
import org.apache.xpath.operations.Mod;
import org.apache.xpath.operations.Minus;
import org.apache.xpath.patterns.UnionPattern;
import org.apache.xpath.operations.Lte;
import org.apache.xpath.operations.Lt;
import org.apache.xpath.patterns.StepPattern;
import org.apache.xpath.axes.LocPathIterator;
import org.apache.xpath.axes.WalkerFactory;
import org.apache.xpath.objects.XString;
import org.apache.xpath.operations.Gte;
import org.apache.xpath.operations.Gt;
import org.apache.xpath.operations.Equals;
import org.apache.xpath.operations.Div;
import org.apache.xpath.functions.Function;
import org.apache.xpath.functions.WrongNumberArgsException;
import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xpath.operations.UnaryOperation;
import org.apache.xpath.operations.Bool;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xpath.operations.Operation;
import org.apache.xpath.operations.And;
import org.apache.xpath.Expression;
import org.apache.xml.utils.SAXSourceLocator;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.PrefixResolver;

public class Compiler extends OpMap
{
    private int locPathDepth;
    private PrefixResolver m_currentPrefixResolver;
    ErrorListener m_errorHandler;
    SourceLocator m_locator;
    
    public Compiler() {
        this.locPathDepth = -1;
        this.m_currentPrefixResolver = null;
        this.m_errorHandler = null;
        this.m_locator = null;
    }
    
    public Compiler(final ErrorListener errorHandler, final SourceLocator locator) {
        this.locPathDepth = -1;
        this.m_currentPrefixResolver = null;
        this.m_errorHandler = errorHandler;
        if (locator != null) {
            final SAXSourceLocator ssl = new SAXSourceLocator();
            ssl.setColumnNumber(locator.getColumnNumber());
            ssl.setLineNumber(locator.getLineNumber());
            ssl.setPublicId(locator.getPublicId());
            ssl.setSystemId(locator.getSystemId());
            this.m_locator = ssl;
        }
    }
    
    protected Expression and(final int opPos) throws TransformerException {
        return this.compileOperation(new And(), opPos);
    }
    
    protected Expression arg(final int opPos) throws TransformerException {
        return this.compile(opPos + 2);
    }
    
    public void assert(final boolean b, final String msg) {
        if (!b) {
            final String fMsg = XSLMessages.createXPATHMessage(30, new Object[] { msg });
            throw new RuntimeException(fMsg);
        }
    }
    
    protected Expression bool(final int opPos) throws TransformerException {
        return this.compileUnary(new Bool(), opPos);
    }
    
    public Expression compile(final int opPos) throws TransformerException {
        final int op = super.m_opMap[opPos];
        Expression expr = null;
        switch (op) {
            case 1: {
                expr = this.compile(opPos + 2);
                break;
            }
            case 2: {
                expr = this.or(opPos);
                break;
            }
            case 3: {
                expr = this.and(opPos);
                break;
            }
            case 4: {
                expr = this.notequals(opPos);
                break;
            }
            case 5: {
                expr = this.equals(opPos);
                break;
            }
            case 6: {
                expr = this.lte(opPos);
                break;
            }
            case 7: {
                expr = this.lt(opPos);
                break;
            }
            case 8: {
                expr = this.gte(opPos);
                break;
            }
            case 9: {
                expr = this.gt(opPos);
                break;
            }
            case 10: {
                expr = this.plus(opPos);
                break;
            }
            case 11: {
                expr = this.minus(opPos);
                break;
            }
            case 12: {
                expr = this.mult(opPos);
                break;
            }
            case 13: {
                expr = this.div(opPos);
                break;
            }
            case 14: {
                expr = this.mod(opPos);
                break;
            }
            case 16: {
                expr = this.neg(opPos);
                break;
            }
            case 17: {
                expr = this.string(opPos);
                break;
            }
            case 18: {
                expr = this.bool(opPos);
                break;
            }
            case 19: {
                expr = this.number(opPos);
                break;
            }
            case 20: {
                expr = this.union(opPos);
                break;
            }
            case 21: {
                expr = this.literal(opPos);
                break;
            }
            case 22: {
                expr = this.variable(opPos);
                break;
            }
            case 23: {
                expr = this.group(opPos);
                break;
            }
            case 27: {
                expr = this.numberlit(opPos);
                break;
            }
            case 26: {
                expr = this.arg(opPos);
                break;
            }
            case 24: {
                expr = this.compileExtension(opPos);
                break;
            }
            case 25: {
                expr = this.compileFunction(opPos);
                break;
            }
            case 28: {
                expr = this.locationPath(opPos);
                break;
            }
            case 29: {
                expr = null;
                break;
            }
            case 30: {
                expr = this.matchPattern(opPos + 2);
                break;
            }
            case 31: {
                expr = this.locationPathPattern(opPos);
                break;
            }
            default: {
                this.error(24, new Object[] { Integer.toString(super.m_opMap[opPos]) });
                break;
            }
        }
        if (expr != null) {
            expr.setSourceLocator(this.m_locator);
        }
        return expr;
    }
    
    private Expression compileExtension(int opPos) throws TransformerException {
        final int endExtFunc = opPos + super.m_opMap[opPos + 1] - 1;
        opPos = OpMap.getFirstChildPos(opPos);
        final String ns = (String)super.m_tokenQueue[super.m_opMap[opPos]];
        ++opPos;
        final String funcName = (String)super.m_tokenQueue[super.m_opMap[opPos]];
        ++opPos;
        final Function extension = new FuncExtFunction(ns, funcName, String.valueOf(String.valueOf(opPos)) + String.valueOf(this.hashCode()));
        try {
            int nextOpPos;
            for (int i = 0; opPos < endExtFunc; opPos = nextOpPos, ++i) {
                nextOpPos = this.getNextOpPos(opPos);
                extension.setArg(this.compile(opPos), i);
            }
        }
        catch (WrongNumberArgsException ex) {}
        return extension;
    }
    
    Expression compileFunction(int opPos) throws TransformerException {
        final int endFunc = opPos + super.m_opMap[opPos + 1] - 1;
        opPos = OpMap.getFirstChildPos(opPos);
        final int funcID = super.m_opMap[opPos];
        ++opPos;
        if (funcID != -1) {
            final Function func = FunctionTable.getFunction(funcID);
            try {
                int i = 0;
                for (int p = opPos; p < endFunc; p = this.getNextOpPos(p), ++i) {
                    func.setArg(this.compile(p), i);
                }
                func.checkNumberArgs(i);
            }
            catch (WrongNumberArgsException wnae) {
                final String name = FunctionTable.m_functions[funcID].getName();
                this.m_errorHandler.fatalError(new TransformerException(String.valueOf(name) + " only allows " + wnae.getMessage() + " arguments", this.m_locator));
            }
            return func;
        }
        this.error(69, null);
        return null;
    }
    
    private Expression compileOperation(final Operation operation, final int opPos) throws TransformerException {
        final int leftPos = OpMap.getFirstChildPos(opPos);
        final int rightPos = this.getNextOpPos(leftPos);
        operation.setLeftRight(this.compile(leftPos), this.compile(rightPos));
        return operation;
    }
    
    private void compilePredicates(int opPos, final Expression[] predicates) throws TransformerException {
        for (int i = 0; this.getOp(opPos) == 29; opPos = this.getNextOpPos(opPos), ++i) {
            predicates[i] = this.predicate(opPos);
        }
    }
    
    private Expression compileUnary(final UnaryOperation unary, final int opPos) throws TransformerException {
        final int rightPos = OpMap.getFirstChildPos(opPos);
        unary.setRight(this.compile(rightPos));
        return unary;
    }
    
    public int countPredicates(int opPos) throws TransformerException {
        int count = 0;
        while (this.getOp(opPos) == 29) {
            ++count;
            opPos = this.getNextOpPos(opPos);
        }
        return count;
    }
    
    protected Expression div(final int opPos) throws TransformerException {
        return this.compileOperation(new Div(), opPos);
    }
    
    protected Expression equals(final int opPos) throws TransformerException {
        return this.compileOperation(new Equals(), opPos);
    }
    
    public void error(final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHMessage(msg, args);
        if (this.m_errorHandler != null) {
            this.m_errorHandler.fatalError(new TransformerException(fmsg, this.m_locator));
            return;
        }
        throw new TransformerException(fmsg, this.m_locator);
    }
    
    public Expression[] getCompiledPredicates(final int opPos) throws TransformerException {
        final int count = this.countPredicates(opPos);
        if (count > 0) {
            final Expression[] predicates = new Expression[count];
            this.compilePredicates(opPos, predicates);
            return predicates;
        }
        return null;
    }
    
    public int getLocationPathDepth() {
        return this.locPathDepth;
    }
    
    public PrefixResolver getNamespaceContext() {
        return this.m_currentPrefixResolver;
    }
    
    public int getWhatToShow(final int opPos) {
        final int axesType = this.getOp(opPos);
        final int testType = this.getOp(opPos + 3);
        switch (testType) {
            case 1030: {
                return 128;
            }
            case 1031: {
                return 12;
            }
            case 1032: {
                return 64;
            }
            case 1033: {
                switch (axesType) {
                    case 49: {
                        return 4098;
                    }
                    case 39:
                    case 51: {
                        return 2;
                    }
                    case 38:
                    case 42:
                    case 48: {
                        return -1;
                    }
                    default: {
                        if (this.getOp(0) == 30) {
                            return -1283;
                        }
                        return -3;
                    }
                }
                break;
            }
            case 35: {
                return 1280;
            }
            case 1034: {
                return 65536;
            }
            case 34: {
                switch (axesType) {
                    case 49: {
                        return 4098;
                    }
                    case 39:
                    case 51: {
                        return 2;
                    }
                    case 52:
                    case 53: {
                        return 1;
                    }
                    default: {
                        return 1;
                    }
                }
                break;
            }
            default: {
                return -1;
            }
        }
    }
    
    protected Expression group(final int opPos) throws TransformerException {
        return this.compile(opPos + 2);
    }
    
    protected Expression gt(final int opPos) throws TransformerException {
        return this.compileOperation(new Gt(), opPos);
    }
    
    protected Expression gte(final int opPos) throws TransformerException {
        return this.compileOperation(new Gte(), opPos);
    }
    
    protected Expression literal(int opPos) {
        opPos = OpMap.getFirstChildPos(opPos);
        return (XString)super.m_tokenQueue[super.m_opMap[opPos]];
    }
    
    public Expression locationPath(final int opPos) throws TransformerException {
        ++this.locPathDepth;
        try {
            final LocPathIterator iter = WalkerFactory.newLocPathIterator(this, opPos);
            if (this.locPathDepth == 0) {
                iter.setIsTopLevel(true);
            }
            return iter;
        }
        finally {
            --this.locPathDepth;
        }
    }
    
    public Expression locationPathPattern(int opPos) throws TransformerException {
        opPos = OpMap.getFirstChildPos(opPos);
        return this.stepPattern(opPos, 0, null);
    }
    
    protected Expression lt(final int opPos) throws TransformerException {
        return this.compileOperation(new Lt(), opPos);
    }
    
    protected Expression lte(final int opPos) throws TransformerException {
        return this.compileOperation(new Lte(), opPos);
    }
    
    protected Expression matchPattern(int opPos) throws TransformerException {
        int nextOpPos;
        int i;
        for (nextOpPos = opPos, i = 0; super.m_opMap[nextOpPos] == 31; nextOpPos = this.getNextOpPos(nextOpPos), ++i) {}
        if (i == 1) {
            return this.compile(opPos);
        }
        final UnionPattern up = new UnionPattern();
        final StepPattern[] patterns = new StepPattern[i];
        for (i = 0; super.m_opMap[opPos] == 31; opPos = nextOpPos, ++i) {
            nextOpPos = this.getNextOpPos(opPos);
            patterns[i] = (StepPattern)this.compile(opPos);
        }
        up.setPatterns(patterns);
        return up;
    }
    
    protected Expression minus(final int opPos) throws TransformerException {
        return this.compileOperation(new Minus(), opPos);
    }
    
    protected Expression mod(final int opPos) throws TransformerException {
        return this.compileOperation(new Mod(), opPos);
    }
    
    protected Expression mult(final int opPos) throws TransformerException {
        return this.compileOperation(new Mult(), opPos);
    }
    
    protected Expression neg(final int opPos) throws TransformerException {
        return this.compileUnary(new Neg(), opPos);
    }
    
    protected Expression notequals(final int opPos) throws TransformerException {
        return this.compileOperation(new NotEquals(), opPos);
    }
    
    protected Expression number(final int opPos) throws TransformerException {
        return this.compileUnary(new Number(), opPos);
    }
    
    protected Expression numberlit(int opPos) {
        opPos = OpMap.getFirstChildPos(opPos);
        return (XNumber)super.m_tokenQueue[super.m_opMap[opPos]];
    }
    
    protected Expression or(final int opPos) throws TransformerException {
        return this.compileOperation(new Or(), opPos);
    }
    
    protected Expression plus(final int opPos) throws TransformerException {
        return this.compileOperation(new Plus(), opPos);
    }
    
    public Expression predicate(final int opPos) throws TransformerException {
        return this.compile(opPos + 2);
    }
    
    public void setNamespaceContext(final PrefixResolver pr) {
        this.m_currentPrefixResolver = pr;
    }
    
    protected StepPattern stepPattern(int opPos, final int stepCount, final StepPattern ancestorPattern) throws TransformerException {
        final int startOpPos = opPos;
        final int stepType = this.getOpMap()[opPos];
        if (stepType == -1) {
            return null;
        }
        final int endStep = this.getNextOpPos(opPos);
        int argLen = 0;
        StepPattern pattern = null;
        switch (stepType) {
            case 25: {
                argLen = super.m_opMap[opPos + 1];
                pattern = new FunctionPattern(this.compileFunction(opPos));
                break;
            }
            case 50: {
                argLen = this.getArgLengthOfStep(opPos);
                opPos = OpMap.getFirstChildPosOfStep(opPos);
                pattern = new StepPattern(1280);
                break;
            }
            case 51: {
                argLen = this.getArgLengthOfStep(opPos);
                opPos = OpMap.getFirstChildPosOfStep(opPos);
                pattern = new StepPattern(2, this.getStepNS(startOpPos), this.getStepLocalName(startOpPos));
                break;
            }
            case 52: {
                argLen = this.getArgLengthOfStep(opPos);
                opPos = OpMap.getFirstChildPosOfStep(opPos);
                pattern = new AncestorStepPattern(this.getWhatToShow(startOpPos), this.getStepNS(startOpPos), this.getStepLocalName(startOpPos));
                break;
            }
            case 53: {
                argLen = this.getArgLengthOfStep(opPos);
                opPos = OpMap.getFirstChildPosOfStep(opPos);
                pattern = new StepPattern(this.getWhatToShow(startOpPos), this.getStepNS(startOpPos), this.getStepLocalName(startOpPos));
                break;
            }
            default: {
                this.error(16, null);
                return null;
            }
        }
        pattern.setPredicates(this.getCompiledPredicates(opPos + argLen));
        pattern.setRelativePathPattern(ancestorPattern);
        final StepPattern relativePathPattern = this.stepPattern(endStep, stepCount + 1, pattern);
        return (relativePathPattern != null) ? relativePathPattern : pattern;
    }
    
    protected Expression string(final int opPos) throws TransformerException {
        return this.compileUnary(new org.apache.xpath.operations.String(), opPos);
    }
    
    protected Expression union(final int opPos) throws TransformerException {
        ++this.locPathDepth;
        try {
            return new UnionPathIterator(this, opPos);
        }
        finally {
            --this.locPathDepth;
        }
    }
    
    protected Expression variable(int opPos) throws TransformerException {
        final Variable var = new Variable();
        opPos = OpMap.getFirstChildPos(opPos);
        final int nsPos = super.m_opMap[opPos];
        final String namespace = (nsPos == -2) ? null : ((String)super.m_tokenQueue[nsPos]);
        final String localname = (String)super.m_tokenQueue[super.m_opMap[opPos + 1]];
        final QName qname = new QName(namespace, localname);
        var.setQName(qname);
        return var;
    }
    
    public void warn(final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHWarning(msg, args);
        if (this.m_errorHandler != null) {
            this.m_errorHandler.warning(new TransformerException(fmsg, this.m_locator));
        }
        else {
            System.out.println(String.valueOf(fmsg) + "; file " + this.m_locator.getSystemId() + "; line " + this.m_locator.getLineNumber() + "; column " + this.m_locator.getColumnNumber());
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2CaptureNode;
import org.jruby.ast.Match2Node;
import org.jruby.ast.DRegexpNode;
import org.jruby.RubyRegexp;
import org.jcodings.specific.SJISEncoding;
import org.jcodings.specific.EUCJPEncoding;
import org.jcodings.Encoding;
import org.jruby.util.RegexpOptions;
import org.jruby.lexer.yacc.RubyYaccLexer;
import org.jruby.util.StringSupport;
import org.jruby.util.ByteList;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.SValue19Node;
import org.jruby.ast.SValueNode;
import org.jruby.ast.Splat19Node;
import org.jruby.ast.SplatNode;
import org.jruby.ast.DStrNode;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.types.INameNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.lexer.yacc.SyntaxException;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.Token;

public class ParserSupport19 extends ParserSupport
{
    public AssignableNode assignable(final Token lhs, final Node value) {
        this.checkExpression(value);
        switch (lhs.getType()) {
            case 286: {
                throw new SyntaxException(SyntaxException.PID.CANNOT_CHANGE_SELF, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't change the value of self", new Object[0]);
            }
            case 287: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to nil", new Object[] { "nil" });
            }
            case 288: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to true", new Object[] { "true" });
            }
            case 289: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to false", new Object[] { "false" });
            }
            case 303: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to __FILE__", new Object[] { "__FILE__" });
            }
            case 302: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to __LINE__", new Object[] { "__LINE__" });
            }
            case 304: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "Can't assign to __ENCODING__", new Object[] { "__ENCODING__" });
            }
            case 306: {
                return this.currentScope.assign(lhs.getPosition(), (String)lhs.getValue(), this.makeNullNil(value));
            }
            case 310: {
                if (this.isInDef() || this.isInSingle()) {
                    throw new SyntaxException(SyntaxException.PID.DYNAMIC_CONSTANT_ASSIGNMENT, lhs.getPosition(), this.lexer.getCurrentLine(), "dynamic constant assignment", new Object[0]);
                }
                return new ConstDeclNode(lhs.getPosition(), (String)lhs.getValue(), null, value);
            }
            case 309: {
                return new InstAsgnNode(lhs.getPosition(), (String)lhs.getValue(), value);
            }
            case 311: {
                return new ClassVarAsgnNode(lhs.getPosition(), (String)lhs.getValue(), value);
            }
            case 308: {
                return new GlobalAsgnNode(lhs.getPosition(), (String)lhs.getValue(), value);
            }
            default: {
                throw new SyntaxException(SyntaxException.PID.BAD_IDENTIFIER, lhs.getPosition(), this.lexer.getCurrentLine(), "identifier " + (String)lhs.getValue() + " is not valid to set", new Object[] { lhs.getValue() });
            }
        }
    }
    
    public DStrNode createDStrNode(final ISourcePosition position) {
        return new DStrNode(position, this.lexer.getEncoding());
    }
    
    protected void getterIdentifierError(final ISourcePosition position, final String identifier) {
        throw new SyntaxException(SyntaxException.PID.BAD_IDENTIFIER, position, "identifier " + identifier + " is not valid to get", identifier, new Object[0]);
    }
    
    public SplatNode newSplatNode(final ISourcePosition position, final Node node) {
        return new Splat19Node(position, this.makeNullNil(node));
    }
    
    public SValueNode newSValueNode(final ISourcePosition position, final Node node) {
        return new SValue19Node(position, node);
    }
    
    private int[] allocateNamedLocals(final RegexpNode regexpNode) {
        final String[] names = regexpNode.loadPattern(this.configuration.getRuntime()).getNames();
        final int length = names.length;
        final int[] locals = new int[length];
        final StaticScope scope = this.getCurrentScope();
        for (int i = 0; i < length; ++i) {
            final int slot = scope.isDefined(names[i]);
            if (slot >= 0) {
                locals[i] = slot;
            }
            else {
                locals[i] = this.getCurrentScope().addVariableThisScope(names[i]);
            }
        }
        return locals;
    }
    
    private boolean is7BitASCII(final ByteList value) {
        return StringSupport.codeRangeScan(value.getEncoding(), value) == 32;
    }
    
    public void setRegexpEncoding(final RegexpNode end, final ByteList value) {
        final RegexpOptions options = end.getOptions();
        final Encoding optionsEncoding = options.setup19(this.configuration.getRuntime());
        if (optionsEncoding != null) {
            if (optionsEncoding != value.getEncoding() && !this.is7BitASCII(value)) {
                this.compileError(optionsEncoding, value.getEncoding());
            }
            value.setEncoding(optionsEncoding);
        }
        else if (options.isEncodingNone()) {
            if (value.getEncoding() == RubyYaccLexer.ASCII8BIT_ENCODING && !this.is7BitASCII(value)) {
                this.compileError(optionsEncoding, value.getEncoding());
            }
            value.setEncoding(RubyYaccLexer.ASCII8BIT_ENCODING);
        }
        else if (this.lexer.getEncoding() == RubyYaccLexer.USASCII_ENCODING) {
            if (!this.is7BitASCII(value)) {
                value.setEncoding(RubyYaccLexer.USASCII_ENCODING);
            }
            else {
                value.setEncoding(RubyYaccLexer.ASCII8BIT_ENCODING);
            }
        }
    }
    
    private char optionsEncodingChar(final Encoding optionEncoding) {
        if (optionEncoding == RubyYaccLexer.USASCII_ENCODING) {
            return 'n';
        }
        if (optionEncoding == EUCJPEncoding.INSTANCE) {
            return 'e';
        }
        if (optionEncoding == SJISEncoding.INSTANCE) {
            return 's';
        }
        if (optionEncoding == RubyYaccLexer.UTF8_ENCODING) {
            return 'u';
        }
        return ' ';
    }
    
    protected void compileError(final Encoding optionEncoding, final Encoding encoding) {
        throw new SyntaxException(SyntaxException.PID.REGEXP_ENCODING_MISMATCH, this.lexer.getPosition(), this.lexer.getCurrentLine(), "regexp encoding option '" + this.optionsEncodingChar(optionEncoding) + "' differs from source encoding '" + encoding + "'", new Object[0]);
    }
    
    public void regexpFragmentCheck(final RegexpNode end, final ByteList value) {
        this.setRegexpEncoding(end, value);
        RubyRegexp.preprocessCheck(this.configuration.getRuntime(), value);
    }
    
    public Node getMatchNode(final Node firstNode, final Node secondNode) {
        if (firstNode instanceof DRegexpNode) {
            return new Match2Node(firstNode.getPosition(), firstNode, secondNode);
        }
        if (firstNode instanceof RegexpNode) {
            final int[] locals = this.allocateNamedLocals((RegexpNode)firstNode);
            if (locals.length > 0) {
                return new Match2CaptureNode(firstNode.getPosition(), firstNode, secondNode, locals);
            }
            return new Match2Node(firstNode.getPosition(), firstNode, secondNode);
        }
        else {
            if (secondNode instanceof DRegexpNode || secondNode instanceof RegexpNode) {
                return new Match3Node(firstNode.getPosition(), secondNode, firstNode);
            }
            return this.getOperatorCallNode(firstNode, "=~", secondNode);
        }
    }
}

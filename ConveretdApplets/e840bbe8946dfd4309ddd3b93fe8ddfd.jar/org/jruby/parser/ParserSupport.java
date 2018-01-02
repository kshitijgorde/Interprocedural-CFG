// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.ast.NodeType;
import org.jruby.RubyRegexp;
import org.jruby.util.RegexpOptions;
import org.jruby.ast.ArgumentNode;
import org.jruby.ast.BlockArg18Node;
import org.jruby.ast.UndefNode;
import org.jruby.ast.AliasNode;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.ArgsPreTwoArgNode;
import org.jruby.ast.ArgsPreOneArgNode;
import org.jruby.ast.ArgsNoArgNode;
import org.jruby.ast.BlockArgNode;
import org.jruby.ast.RestArgNode;
import java.math.BigInteger;
import org.jruby.RubyBignum;
import org.jruby.ast.YieldNode;
import org.jruby.ast.YieldThreeNode;
import org.jruby.ast.YieldTwoNode;
import org.jruby.ast.YieldOneNode;
import org.jruby.CompatVersion;
import org.jruby.ast.ZYieldNode;
import org.jruby.ast.StrNode;
import org.jruby.ast.EvStrNode;
import org.jruby.ast.DStrNode;
import org.jruby.runtime.DynamicScope;
import org.jruby.ast.SuperNode;
import org.jruby.ast.FCallSpecialArgNode;
import org.jruby.ast.FCallManyArgsBlockPassNode;
import org.jruby.ast.FCallThreeArgBlockPassNode;
import org.jruby.ast.FCallTwoArgBlockPassNode;
import org.jruby.ast.FCallOneArgBlockPassNode;
import org.jruby.ast.FCallSpecialArgBlockPassNode;
import org.jruby.ast.FCallNoArgBlockPassNode;
import org.jruby.ast.FCallManyArgsNode;
import org.jruby.ast.FCallManyArgsBlockNode;
import org.jruby.ast.FCallThreeArgNode;
import org.jruby.ast.FCallThreeArgBlockNode;
import org.jruby.ast.FCallTwoArgNode;
import org.jruby.ast.FCallTwoArgBlockNode;
import org.jruby.ast.FCallOneArgNode;
import org.jruby.ast.FCallOneArgBlockNode;
import org.jruby.ast.FCallNoArgNode;
import org.jruby.ast.FCallNoArgBlockNode;
import org.jruby.ast.Colon3Node;
import org.jruby.ast.Colon2ConstNode;
import org.jruby.ast.Colon2ImplicitNode;
import org.jruby.ast.Colon2MethodNode;
import org.jruby.util.IdUtil;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.CallManyArgsNode;
import org.jruby.ast.CallManyArgsBlockNode;
import org.jruby.ast.CallThreeArgNode;
import org.jruby.ast.CallThreeArgBlockNode;
import org.jruby.ast.CallTwoArgNode;
import org.jruby.ast.CallTwoArgBlockNode;
import org.jruby.ast.CallOneArgBlockNode;
import org.jruby.ast.CallOneArgNode;
import org.jruby.ast.CallOneArgFixnumNode;
import org.jruby.ast.CallManyArgsBlockPassNode;
import org.jruby.ast.CallThreeArgBlockPassNode;
import org.jruby.ast.CallTwoArgBlockPassNode;
import org.jruby.ast.CallOneArgBlockPassNode;
import org.jruby.ast.CallSpecialArgBlockPassNode;
import org.jruby.ast.CallNoArgBlockPassNode;
import org.jruby.ast.CallSpecialArgNode;
import org.jruby.ast.CallSpecialArgBlockNode;
import org.jruby.ast.CallNoArgBlockNode;
import org.jruby.ast.IterNode;
import org.jruby.ast.AttrAssignThreeArgNode;
import org.jruby.ast.AttrAssignTwoArgNode;
import org.jruby.ast.AttrAssignOneArgNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.ast.OpElementOneArgAsgnNode;
import org.jruby.ast.OpElementOneArgAndAsgnNode;
import org.jruby.ast.OpElementOneArgOrAsgnNode;
import org.jruby.ast.WhenNode;
import org.jruby.ast.WhenOneArgNode;
import org.jruby.ast.CaseNode;
import org.jruby.lexer.yacc.ISourcePositionHolder;
import org.jruby.ast.SValueNode;
import org.jruby.ast.MatchNode;
import org.jruby.ast.FlipNode;
import org.jruby.ast.OrNode;
import org.jruby.ast.AndNode;
import org.jruby.ast.types.ILiteralNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.SymbolNode;
import org.jruby.ast.FloatNode;
import org.jruby.ast.BignumNode;
import org.jruby.ast.BinaryOperatorNode;
import org.jruby.ast.IfNode;
import org.jruby.ast.BeginNode;
import org.jruby.ast.SplatNode;
import org.jruby.ast.IArgumentNode;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.BackRefNode;
import org.jruby.ast.NthRefNode;
import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2Node;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.DRegexpNode;
import org.jruby.ast.CallNoArgNode;
import org.jruby.ast.ListNode;
import java.util.Iterator;
import org.jruby.ast.BlockNode;
import org.jruby.ast.RootNode;
import org.jruby.ast.NilImplicitNode;
import org.jruby.ast.NewlineNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.ClassVarDeclNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.EncodingNode;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.FileNode;
import org.jruby.util.ByteList;
import org.jruby.ast.FalseNode;
import org.jruby.ast.TrueNode;
import org.jruby.ast.NilNode;
import org.jruby.ast.SelfNode;
import org.jruby.lexer.yacc.Token;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.ClassVarNode;
import org.jruby.ast.InstVarNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.types.INameNode;
import org.jruby.ast.BlockPassNode;
import org.jruby.ast.ArgsCatNode;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.SyntaxException;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.common.IRubyWarnings;
import org.jruby.lexer.yacc.RubyYaccLexer;

public class ParserSupport
{
    protected StaticScope currentScope;
    protected RubyYaccLexer lexer;
    private int inSingleton;
    private boolean inDefinition;
    protected IRubyWarnings warnings;
    protected ParserConfiguration configuration;
    private RubyParserResult result;
    
    public void reset() {
        this.inSingleton = 0;
        this.inDefinition = false;
    }
    
    public void allowDubyExtension(final ISourcePosition position) {
        if (!this.configuration.isDubyExtensionsEnabled()) {
            throw new SyntaxException(SyntaxException.PID.DUBY_EXTENSIONS_OFF, position, this.lexer.getCurrentLine(), "Duby extensions not configured", new Object[0]);
        }
    }
    
    public StaticScope getCurrentScope() {
        return this.currentScope;
    }
    
    public ParserConfiguration getConfiguration() {
        return this.configuration;
    }
    
    public void popCurrentScope() {
        this.currentScope = this.currentScope.getEnclosingScope();
    }
    
    public void pushBlockScope() {
        this.currentScope = new BlockStaticScope(this.currentScope);
    }
    
    public void pushLocalScope() {
        this.currentScope = new LocalStaticScope(this.currentScope);
    }
    
    public Node arg_concat(final ISourcePosition position, final Node node1, final Node node2) {
        return (node2 == null) ? node1 : new ArgsCatNode(position, node1, node2);
    }
    
    public Node arg_blk_pass(final Node firstNode, final BlockPassNode secondNode) {
        if (secondNode != null) {
            secondNode.setArgsNode(firstNode);
            return secondNode;
        }
        return firstNode;
    }
    
    public Node gettable2(final Node node) {
        switch (node.getNodeType()) {
            case DASGNNODE:
            case LOCALASGNNODE: {
                return this.currentScope.declare(node.getPosition(), ((INameNode)node).getName());
            }
            case CONSTDECLNODE: {
                return new ConstNode(node.getPosition(), ((INameNode)node).getName());
            }
            case INSTASGNNODE: {
                return new InstVarNode(node.getPosition(), ((INameNode)node).getName());
            }
            case CLASSVARDECLNODE:
            case CLASSVARASGNNODE: {
                return new ClassVarNode(node.getPosition(), ((INameNode)node).getName());
            }
            case GLOBALASGNNODE: {
                return new GlobalVarNode(node.getPosition(), ((INameNode)node).getName());
            }
            default: {
                this.getterIdentifierError(node.getPosition(), ((INameNode)node).getName());
                return null;
            }
        }
    }
    
    public Node gettable(final Token token) {
        switch (token.getType()) {
            case 286: {
                return new SelfNode(token.getPosition());
            }
            case 287: {
                return new NilNode(token.getPosition());
            }
            case 288: {
                return new TrueNode(token.getPosition());
            }
            case 289: {
                return new FalseNode(token.getPosition());
            }
            case 303: {
                return new FileNode(token.getPosition(), new ByteList(token.getPosition().getFile().getBytes(), this.getConfiguration().getRuntime().getEncodingService().getLocaleEncoding()));
            }
            case 302: {
                return new FixnumNode(token.getPosition(), token.getPosition().getStartLine() + 1);
            }
            case 304: {
                return new EncodingNode(token.getPosition(), this.lexer.getEncoding());
            }
            case 306: {
                return this.currentScope.declare(token.getPosition(), (String)token.getValue());
            }
            case 310: {
                return new ConstNode(token.getPosition(), (String)token.getValue());
            }
            case 309: {
                return new InstVarNode(token.getPosition(), (String)token.getValue());
            }
            case 311: {
                return new ClassVarNode(token.getPosition(), (String)token.getValue());
            }
            case 308: {
                return new GlobalVarNode(token.getPosition(), (String)token.getValue());
            }
            default: {
                this.getterIdentifierError(token.getPosition(), (String)token.getValue());
                return null;
            }
        }
    }
    
    protected void getterIdentifierError(final ISourcePosition position, final String identifier) {
        throw new SyntaxException(SyntaxException.PID.BAD_IDENTIFIER, position, this.lexer.getCurrentLine(), "identifier " + identifier + " is not valid", new Object[] { identifier });
    }
    
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
                if (this.isInDef() || this.isInSingle()) {
                    return new ClassVarAsgnNode(lhs.getPosition(), (String)lhs.getValue(), value);
                }
                return new ClassVarDeclNode(lhs.getPosition(), (String)lhs.getValue(), value);
            }
            case 308: {
                return new GlobalAsgnNode(lhs.getPosition(), (String)lhs.getValue(), value);
            }
            default: {
                throw new SyntaxException(SyntaxException.PID.BAD_IDENTIFIER, lhs.getPosition(), this.lexer.getCurrentLine(), "identifier " + (String)lhs.getValue() + " is not valid", new Object[] { lhs.getValue() });
            }
        }
    }
    
    public Node newline_node(final Node node, final ISourcePosition position) {
        if (node == null) {
            return null;
        }
        return (node instanceof NewlineNode) ? node : new NewlineNode(position, node);
    }
    
    public Node addRootNode(Node topOfAST, ISourcePosition position) {
        position = ((topOfAST != null) ? topOfAST.getPosition() : position);
        if (this.result.getBeginNodes().isEmpty()) {
            if (topOfAST == null) {
                topOfAST = NilImplicitNode.NIL;
            }
            return new RootNode(position, this.result.getScope(), topOfAST);
        }
        final BlockNode newTopOfAST = new BlockNode(position);
        for (final Node beginNode : this.result.getBeginNodes()) {
            this.appendToBlock(newTopOfAST, beginNode);
        }
        if (topOfAST != null) {
            newTopOfAST.add(topOfAST);
        }
        return new RootNode(position, this.result.getScope(), newTopOfAST);
    }
    
    public Node appendToBlock(Node head, final Node tail) {
        if (tail == null) {
            return head;
        }
        if (head == null) {
            return tail;
        }
        if (!this.configuration.hasExtraPositionInformation()) {
            head = this.compactNewlines(head);
        }
        if (!(head instanceof BlockNode)) {
            head = new BlockNode(head.getPosition()).add(head);
        }
        if (this.warnings.isVerbose() && this.isBreakStatement(((ListNode)head).getLast())) {
            this.warnings.warning(IRubyWarnings.ID.STATEMENT_NOT_REACHED, tail.getPosition(), "Statement not reached.", new Object[0]);
        }
        ((ListNode)head).addAll(tail);
        return head;
    }
    
    public Node getOperatorCallNode(final Node firstNode, final String operator) {
        this.checkExpression(firstNode);
        return new CallNoArgNode(firstNode.getPosition(), firstNode, operator);
    }
    
    public Node getOperatorCallNode(final Node firstNode, final String operator, final Node secondNode) {
        return this.getOperatorCallNode(firstNode, operator, secondNode, null);
    }
    
    public Node getOperatorCallNode(Node firstNode, final String operator, Node secondNode, final ISourcePosition defaultPosition) {
        if (defaultPosition != null) {
            firstNode = this.checkForNilNode(firstNode, defaultPosition);
            secondNode = this.checkForNilNode(secondNode, defaultPosition);
        }
        this.checkExpression(firstNode);
        this.checkExpression(secondNode);
        return this.new_call_one_arg(firstNode.getPosition(), firstNode, operator, secondNode);
    }
    
    public Node getMatchNode(final Node firstNode, final Node secondNode) {
        if (firstNode instanceof DRegexpNode || firstNode instanceof RegexpNode) {
            return new Match2Node(firstNode.getPosition(), firstNode, secondNode);
        }
        if (secondNode instanceof DRegexpNode || secondNode instanceof RegexpNode) {
            return new Match3Node(firstNode.getPosition(), secondNode, firstNode);
        }
        return this.getOperatorCallNode(firstNode, "=~", secondNode);
    }
    
    public Node aryset(final Node receiver, final Node index) {
        this.checkExpression(receiver);
        return this.new_attrassign(receiver.getPosition(), receiver, "[]=", index);
    }
    
    public Node attrset(final Node receiver, final String name) {
        this.checkExpression(receiver);
        return this.new_attrassign(receiver.getPosition(), receiver, name + "=", null);
    }
    
    public void backrefAssignError(final Node node) {
        if (node instanceof NthRefNode) {
            final String varName = "$" + ((NthRefNode)node).getMatchNumber();
            throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, node.getPosition(), "Can't set variable " + varName + '.', varName, new Object[0]);
        }
        if (node instanceof BackRefNode) {
            final String varName = "$" + ((BackRefNode)node).getType();
            throw new SyntaxException(SyntaxException.PID.INVALID_ASSIGNMENT, node.getPosition(), "Can't set variable " + varName + '.', varName, new Object[0]);
        }
    }
    
    public Node arg_add(final ISourcePosition position, final Node node1, final Node node2) {
        if (node1 == null) {
            if (node2 == null) {
                return new ArrayNode(position, NilImplicitNode.NIL);
            }
            return new ArrayNode(node2.getPosition(), node2);
        }
        else {
            if (node1 instanceof ArrayNode) {
                return ((ArrayNode)node1).add(node2);
            }
            return new ArgsPushNode(position, node1, node2);
        }
    }
    
    public Node node_assign(final Node lhs, final Node rhs) {
        if (lhs == null) {
            return null;
        }
        this.checkExpression(rhs);
        if (lhs instanceof AssignableNode) {
            ((AssignableNode)lhs).setValueNode(rhs);
        }
        else if (lhs instanceof IArgumentNode) {
            final IArgumentNode invokableNode = (IArgumentNode)lhs;
            return invokableNode.setArgsNode(this.arg_add(lhs.getPosition(), invokableNode.getArgsNode(), rhs));
        }
        return lhs;
    }
    
    public Node ret_args(Node node, final ISourcePosition position) {
        if (node != null) {
            if (node instanceof BlockPassNode) {
                throw new SyntaxException(SyntaxException.PID.DYNAMIC_CONSTANT_ASSIGNMENT, position, this.lexer.getCurrentLine(), "Dynamic constant assignment.", new Object[0]);
            }
            if (node instanceof ArrayNode && ((ArrayNode)node).size() == 1) {
                node = ((ArrayNode)node).get(0);
            }
            else if (node instanceof SplatNode) {
                node = this.newSValueNode(position, node);
            }
        }
        return node;
    }
    
    public boolean isBreakStatement(Node node) {
        while (node != null) {
            switch (node.getNodeType()) {
                case NEWLINENODE: {
                    node = ((NewlineNode)node).getNextNode();
                    continue;
                }
                case BREAKNODE:
                case NEXTNODE:
                case REDONODE:
                case RETRYNODE:
                case RETURNNODE: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }
    
    public void warnUnlessEOption(final IRubyWarnings.ID id, final Node node, final String message) {
        if (!this.configuration.isInlineSource()) {
            this.warnings.warn(id, node.getPosition(), message, new Object[0]);
        }
    }
    
    public void warningUnlessEOption(final IRubyWarnings.ID id, final Node node, final String message) {
        if (this.warnings.isVerbose() && !this.configuration.isInlineSource()) {
            this.warnings.warning(id, node.getPosition(), message, new Object[0]);
        }
    }
    
    private Node compactNewlines(Node head) {
        while (head instanceof NewlineNode) {
            final Node nextNode = ((NewlineNode)head).getNextNode();
            if (!(nextNode instanceof NewlineNode)) {
                break;
            }
            head = nextNode;
        }
        return head;
    }
    
    public boolean checkExpression(Node node) {
        boolean conditional = false;
        while (node != null) {
            switch (node.getNodeType()) {
                case DEFNNODE:
                case DEFSNODE: {
                    this.warnings.warning(IRubyWarnings.ID.VOID_VALUE_EXPRESSION, node.getPosition(), "void value expression", new Object[0]);
                    return false;
                }
                case BREAKNODE:
                case NEXTNODE:
                case REDONODE:
                case RETRYNODE:
                case RETURNNODE: {
                    if (!conditional) {
                        throw new SyntaxException(SyntaxException.PID.VOID_VALUE_EXPRESSION, node.getPosition(), this.lexer.getCurrentLine(), "void value expression", new Object[0]);
                    }
                    return false;
                }
                case BLOCKNODE: {
                    node = ((BlockNode)node).getLast();
                    continue;
                }
                case BEGINNODE: {
                    node = ((BeginNode)node).getBodyNode();
                    continue;
                }
                case IFNODE: {
                    if (!this.checkExpression(((IfNode)node).getThenBody())) {
                        return false;
                    }
                    node = ((IfNode)node).getElseBody();
                    continue;
                }
                case ANDNODE:
                case ORNODE: {
                    conditional = true;
                    node = ((BinaryOperatorNode)node).getSecondNode();
                    continue;
                }
                case NEWLINENODE: {
                    node = ((NewlineNode)node).getNextNode();
                    continue;
                }
                default: {
                    return true;
                }
            }
        }
        return true;
    }
    
    public boolean isLiteral(final Node node) {
        return node != null && (node instanceof FixnumNode || node instanceof BignumNode || node instanceof FloatNode || node instanceof SymbolNode || (node instanceof RegexpNode && ((RegexpNode)node).getOptions().toJoniOptions() == 0));
    }
    
    private void handleUselessWarn(final Node node, final String useless) {
        this.warnings.warn(IRubyWarnings.ID.USELESS_EXPRESSION, node.getPosition(), "Useless use of " + useless + " in void context.", useless);
    }
    
    public void checkUselessStatement(Node node) {
        if (!this.warnings.isVerbose() || (!this.configuration.isInlineSource() && this.configuration.isEvalParse())) {
            return;
        }
        while (node != null) {
            switch (node.getNodeType()) {
                case NEWLINENODE: {
                    node = ((NewlineNode)node).getNextNode();
                    continue;
                }
                case CALLNODE: {
                    final String name = ((CallNode)node).getName();
                    if (name == "+" || name == "-" || name == "*" || name == "/" || name == "%" || name == "**" || name == "+@" || name == "-@" || name == "|" || name == "^" || name == "&" || name == "<=>" || name == ">" || name == ">=" || name == "<" || name == "<=" || name == "==" || name == "!=") {
                        this.handleUselessWarn(node, name);
                    }
                }
                case BACKREFNODE:
                case DVARNODE:
                case GLOBALVARNODE:
                case LOCALVARNODE:
                case NTHREFNODE:
                case CLASSVARNODE:
                case INSTVARNODE: {
                    this.handleUselessWarn(node, "a variable");
                }
                case BIGNUMNODE:
                case DREGEXPNODE:
                case DSTRNODE:
                case DSYMBOLNODE:
                case FIXNUMNODE:
                case FLOATNODE:
                case REGEXPNODE:
                case STRNODE:
                case SYMBOLNODE: {
                    this.handleUselessWarn(node, "a literal");
                }
                case DOTNODE: {
                    this.handleUselessWarn(node, ((DotNode)node).isExclusive() ? "..." : "..");
                }
                case DEFINEDNODE: {
                    this.handleUselessWarn(node, "defined?");
                }
                case FALSENODE: {
                    this.handleUselessWarn(node, "false");
                }
                case NILNODE: {
                    this.handleUselessWarn(node, "nil");
                }
                case TRUENODE: {
                    this.handleUselessWarn(node, "true");
                }
                default: {}
            }
        }
    }
    
    public void checkUselessStatements(final BlockNode blockNode) {
        if (this.warnings.isVerbose()) {
            final Node lastNode = blockNode.getLast();
            for (int i = 0; i < blockNode.size(); ++i) {
                final Node currentNode = blockNode.get(i);
                if (lastNode != currentNode) {
                    this.checkUselessStatement(currentNode);
                }
            }
        }
    }
    
    private boolean checkAssignmentInCondition(final Node node) {
        if (node instanceof MultipleAsgnNode) {
            throw new SyntaxException(SyntaxException.PID.MULTIPLE_ASSIGNMENT_IN_CONDITIONAL, node.getPosition(), this.lexer.getCurrentLine(), "Multiple assignment in conditional.", new Object[0]);
        }
        if (node instanceof LocalAsgnNode || node instanceof DAsgnNode || node instanceof GlobalAsgnNode || node instanceof InstAsgnNode) {
            final Node valueNode = ((AssignableNode)node).getValueNode();
            if (valueNode instanceof ILiteralNode || valueNode instanceof NilNode || valueNode instanceof TrueNode || valueNode instanceof FalseNode) {
                this.warnings.warn(IRubyWarnings.ID.ASSIGNMENT_IN_CONDITIONAL, node.getPosition(), "Found '=' in conditional, should be '=='.", new Object[0]);
            }
            return true;
        }
        return false;
    }
    
    protected Node makeNullNil(final Node node) {
        return (node == null) ? NilImplicitNode.NIL : node;
    }
    
    private Node cond0(final Node node) {
        this.checkAssignmentInCondition(node);
        Node leftNode = null;
        Node rightNode = null;
        switch (node.getNodeType()) {
            case DREGEXPNODE: {
                final ISourcePosition position = node.getPosition();
                return new Match2Node(position, node, new GlobalVarNode(position, "$_"));
            }
            case ANDNODE: {
                leftNode = this.cond0(((AndNode)node).getFirstNode());
                rightNode = this.cond0(((AndNode)node).getSecondNode());
                return new AndNode(node.getPosition(), this.makeNullNil(leftNode), this.makeNullNil(rightNode));
            }
            case ORNODE: {
                leftNode = this.cond0(((OrNode)node).getFirstNode());
                rightNode = this.cond0(((OrNode)node).getSecondNode());
                return new OrNode(node.getPosition(), this.makeNullNil(leftNode), this.makeNullNil(rightNode));
            }
            case DOTNODE: {
                final DotNode dotNode = (DotNode)node;
                if (dotNode.isLiteral()) {
                    return node;
                }
                final String label = String.valueOf("FLIP" + node.hashCode());
                this.currentScope.getLocalScope().addVariable(label);
                final int slot = this.currentScope.isDefined(label);
                return new FlipNode(node.getPosition(), this.getFlipConditionNode(((DotNode)node).getBeginNode()), this.getFlipConditionNode(((DotNode)node).getEndNode()), dotNode.isExclusive(), slot);
            }
            case REGEXPNODE: {
                this.warningUnlessEOption(IRubyWarnings.ID.REGEXP_LITERAL_IN_CONDITION, node, "regex literal in condition");
                return new MatchNode(node.getPosition(), node);
            }
            default: {
                return node;
            }
        }
    }
    
    public Node getConditionNode(final Node node) {
        if (node == null) {
            return NilImplicitNode.NIL;
        }
        if (node instanceof NewlineNode) {
            return new NewlineNode(node.getPosition(), this.cond0(((NewlineNode)node).getNextNode()));
        }
        return this.cond0(node);
    }
    
    private Node getFlipConditionNode(Node node) {
        if (!this.configuration.isInlineSource()) {
            return node;
        }
        node = this.getConditionNode(node);
        if (node instanceof NewlineNode) {
            return ((NewlineNode)node).getNextNode();
        }
        if (node instanceof FixnumNode) {
            this.warnUnlessEOption(IRubyWarnings.ID.LITERAL_IN_CONDITIONAL_RANGE, node, "integer literal in conditional range");
            return this.getOperatorCallNode(node, "==", new GlobalVarNode(node.getPosition(), "$."));
        }
        return node;
    }
    
    public SValueNode newSValueNode(final ISourcePosition position, final Node node) {
        return new SValueNode(position, node);
    }
    
    public SplatNode newSplatNode(final ISourcePosition position, final Node node) {
        return new SplatNode(position, this.makeNullNil(node));
    }
    
    public ArrayNode newArrayNode(final ISourcePosition position, final Node firstNode) {
        return new ArrayNode(position, this.makeNullNil(firstNode));
    }
    
    public ISourcePosition position(final ISourcePositionHolder one, final ISourcePositionHolder two) {
        return (one == null) ? two.getPosition() : one.getPosition();
    }
    
    public AndNode newAndNode(final ISourcePosition position, final Node left, final Node right) {
        this.checkExpression(left);
        if (left == null && right == null) {
            return new AndNode(position, this.makeNullNil(left), this.makeNullNil(right));
        }
        return new AndNode(this.position(left, right), this.makeNullNil(left), this.makeNullNil(right));
    }
    
    public OrNode newOrNode(final ISourcePosition position, final Node left, final Node right) {
        this.checkExpression(left);
        if (left == null && right == null) {
            return new OrNode(position, this.makeNullNil(left), this.makeNullNil(right));
        }
        return new OrNode(this.position(left, right), this.makeNullNil(left), this.makeNullNil(right));
    }
    
    public CaseNode newCaseNode(final ISourcePosition position, final Node expression, final Node firstWhenNode) {
        final ArrayNode cases = new ArrayNode((firstWhenNode != null) ? firstWhenNode.getPosition() : position);
        final CaseNode caseNode = new CaseNode(position, expression, cases);
        for (Node current = firstWhenNode; current != null; current = ((WhenNode)current).getNextCase()) {
            if (current instanceof WhenOneArgNode) {
                cases.add(current);
            }
            else {
                if (!(current instanceof WhenNode)) {
                    caseNode.setElseNode(current);
                    break;
                }
                this.simplifyMultipleArgumentWhenNodes((WhenNode)current, cases);
            }
        }
        return caseNode;
    }
    
    private void simplifyMultipleArgumentWhenNodes(final WhenNode sourceWhen, final ArrayNode cases) {
        final Node expressionNodes = sourceWhen.getExpressionNodes();
        if (expressionNodes instanceof SplatNode || expressionNodes instanceof ArgsCatNode) {
            cases.add(sourceWhen);
            return;
        }
        if (expressionNodes instanceof ListNode) {
            final ListNode list = (ListNode)expressionNodes;
            final ISourcePosition position = sourceWhen.getPosition();
            final Node bodyNode = sourceWhen.getBodyNode();
            for (int i = 0; i < list.size(); ++i) {
                final Node expression = list.get(i);
                if (expression instanceof SplatNode || expression instanceof ArgsCatNode) {
                    cases.add(new WhenNode(position, expression, bodyNode, null));
                }
                else {
                    cases.add(new WhenOneArgNode(position, expression, bodyNode, null));
                }
            }
        }
        else {
            cases.add(sourceWhen);
        }
    }
    
    public WhenNode newWhenNode(final ISourcePosition position, final Node expressionNodes, Node bodyNode, final Node nextCase) {
        if (bodyNode == null) {
            bodyNode = NilImplicitNode.NIL;
        }
        if (expressionNodes instanceof SplatNode || expressionNodes instanceof ArgsCatNode) {
            return new WhenNode(position, expressionNodes, bodyNode, nextCase);
        }
        final ListNode list = (ListNode)expressionNodes;
        if (list.size() == 1) {
            final Node element = list.get(0);
            if (!(element instanceof SplatNode)) {
                return new WhenOneArgNode(position, element, bodyNode, nextCase);
            }
        }
        return new WhenNode(position, expressionNodes, bodyNode, nextCase);
    }
    
    public Node getReturnArgsNode(final Node node) {
        if (node instanceof ArrayNode && ((ArrayNode)node).size() == 1) {
            return ((ListNode)node).get(0);
        }
        if (node instanceof BlockPassNode) {
            throw new SyntaxException(SyntaxException.PID.BLOCK_ARG_UNEXPECTED, node.getPosition(), this.lexer.getCurrentLine(), "Block argument should not be given.", new Object[0]);
        }
        return node;
    }
    
    public Node new_opElementAsgnNode(final ISourcePosition position, final Node receiverNode, final String operatorName, final Node argsNode, final Node valueNode) {
        if (argsNode instanceof ArrayNode) {
            final ArrayNode array = (ArrayNode)argsNode;
            if (array.size() == 1) {
                if (operatorName.equals("||")) {
                    return new OpElementOneArgOrAsgnNode(position, receiverNode, operatorName, array, valueNode);
                }
                if (operatorName.equals("&&")) {
                    return new OpElementOneArgAndAsgnNode(position, receiverNode, operatorName, array, valueNode);
                }
                return new OpElementOneArgAsgnNode(position, receiverNode, operatorName, array, valueNode);
            }
        }
        return new OpElementAsgnNode(position, receiverNode, operatorName, argsNode, valueNode);
    }
    
    public Node new_attrassign(final ISourcePosition position, final Node receiver, final String name, final Node args) {
        if (!(args instanceof ArrayNode)) {
            return new AttrAssignNode(position, receiver, name, args);
        }
        final ArrayNode argsNode = (ArrayNode)args;
        switch (argsNode.size()) {
            case 1: {
                return new AttrAssignOneArgNode(position, receiver, name, argsNode);
            }
            case 2: {
                return new AttrAssignTwoArgNode(position, receiver, name, argsNode);
            }
            case 3: {
                return new AttrAssignThreeArgNode(position, receiver, name, argsNode);
            }
            default: {
                return new AttrAssignNode(position, receiver, name, argsNode);
            }
        }
    }
    
    private Node new_call_noargs(Node receiver, final Token name, final IterNode iter) {
        final ISourcePosition position = this.position(receiver, name);
        if (receiver == null) {
            receiver = NilImplicitNode.NIL;
        }
        if (iter != null) {
            return new CallNoArgBlockNode(position, receiver, (String)name.getValue(), iter);
        }
        return new CallNoArgNode(position, receiver, (String)name.getValue());
    }
    
    private Node new_call_complexargs(final Node receiver, final Token name, final Node args, final Node iter) {
        if (args instanceof BlockPassNode) {
            if (iter != null) {
                throw new SyntaxException(SyntaxException.PID.BLOCK_ARG_AND_BLOCK_GIVEN, iter.getPosition(), this.lexer.getCurrentLine(), "Both block arg and actual block given.", new Object[0]);
            }
            return this.new_call_blockpass(receiver, name, (BlockPassNode)args);
        }
        else {
            if (iter != null) {
                return new CallSpecialArgBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
            }
            return new CallSpecialArgNode(this.position(receiver, args), receiver, (String)name.getValue(), args);
        }
    }
    
    private Node new_call_blockpass(final Node receiver, final Token operation, final BlockPassNode blockPass) {
        final ISourcePosition position = this.position(receiver, blockPass);
        final String name = (String)operation.getValue();
        final Node args = blockPass.getArgsNode();
        if (args == null) {
            return new CallNoArgBlockPassNode(position, receiver, name, args, blockPass);
        }
        if (!(args instanceof ArrayNode)) {
            return new CallSpecialArgBlockPassNode(position, receiver, name, args, blockPass);
        }
        switch (((ArrayNode)args).size()) {
            case 0: {
                return new CallNoArgBlockPassNode(position, receiver, name, args, blockPass);
            }
            case 1: {
                return new CallOneArgBlockPassNode(position, receiver, name, (ArrayNode)args, blockPass);
            }
            case 2: {
                return new CallTwoArgBlockPassNode(position, receiver, name, (ArrayNode)args, blockPass);
            }
            case 3: {
                return new CallThreeArgBlockPassNode(position, receiver, name, (ArrayNode)args, blockPass);
            }
            default: {
                return new CallManyArgsBlockPassNode(position, receiver, name, args, blockPass);
            }
        }
    }
    
    private boolean isNumericOperator(final String name) {
        Label_0150: {
            if (name.length() == 1) {
                switch (name.charAt(0)) {
                    case '*':
                    case '+':
                    case '-':
                    case '/':
                    case '<':
                    case '>': {
                        return true;
                    }
                }
            }
            else if (name.length() == 2) {
                switch (name.charAt(0)) {
                    case '<':
                    case '=':
                    case '>': {
                        switch (name.charAt(1)) {
                            case '<':
                            case '=': {
                                return true;
                            }
                            default: {
                                break Label_0150;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }
    
    private Node new_call_one_arg(final ISourcePosition position, final Node receiver, final String name, final Node first) {
        if (first instanceof FixnumNode && this.isNumericOperator(name)) {
            return new CallOneArgFixnumNode(position, receiver, name, new ArrayNode(position, first));
        }
        return new CallOneArgNode(position, receiver, name, new ArrayNode(position, first));
    }
    
    public Node new_call(final Node receiver, final Token name, final Node argsNode, final Node iter) {
        if (argsNode == null) {
            return this.new_call_noargs(receiver, name, (IterNode)iter);
        }
        if (!(argsNode instanceof ArrayNode)) {
            return this.new_call_complexargs(receiver, name, argsNode, iter);
        }
        final ArrayNode args = (ArrayNode)argsNode;
        switch (args.size()) {
            case 0: {
                if (iter != null) {
                    return new CallNoArgBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
                }
                return new CallNoArgNode(this.position(receiver, args), receiver, args, (String)name.getValue());
            }
            case 1: {
                if (iter != null) {
                    return new CallOneArgBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
                }
                return new CallOneArgNode(this.position(receiver, args), receiver, (String)name.getValue(), args);
            }
            case 2: {
                if (iter != null) {
                    return new CallTwoArgBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
                }
                return new CallTwoArgNode(this.position(receiver, args), receiver, (String)name.getValue(), args);
            }
            case 3: {
                if (iter != null) {
                    return new CallThreeArgBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
                }
                return new CallThreeArgNode(this.position(receiver, args), receiver, (String)name.getValue(), args);
            }
            default: {
                if (iter != null) {
                    return new CallManyArgsBlockNode(this.position(receiver, args), receiver, (String)name.getValue(), args, (IterNode)iter);
                }
                return new CallManyArgsNode(this.position(receiver, args), receiver, (String)name.getValue(), args);
            }
        }
    }
    
    public Node new_aref(final Node receiver, final Token name, final Node argsNode) {
        if (argsNode instanceof ArrayNode) {
            final ArrayNode args = (ArrayNode)argsNode;
            if (args.size() == 1 && args.get(0) instanceof FixnumNode) {
                return new CallOneArgFixnumNode(this.position(receiver, args), receiver, "[]", args);
            }
        }
        return this.new_call(receiver, name, argsNode, null);
    }
    
    public Colon2Node new_colon2(final ISourcePosition position, final Node leftNode, final String name) {
        if (!IdUtil.isConstant(name)) {
            return new Colon2MethodNode(position, leftNode, name);
        }
        if (leftNode == null) {
            return new Colon2ImplicitNode(position, name);
        }
        return new Colon2ConstNode(position, leftNode, name);
    }
    
    public Colon3Node new_colon3(final ISourcePosition position, final String name) {
        return new Colon3Node(position, name);
    }
    
    private Node new_fcall_noargs(final Token operation, final IterNode iter) {
        if (iter != null) {
            return new FCallNoArgBlockNode(operation.getPosition(), (String)operation.getValue(), iter);
        }
        return new FCallNoArgNode(operation.getPosition(), (String)operation.getValue());
    }
    
    private Node new_fcall_simpleargs(final Token operation, final ArrayNode args, final Node iter) {
        final String name = (String)operation.getValue();
        final ISourcePosition position = this.position(operation, args);
        switch (args.size()) {
            case 0: {
                if (iter != null) {
                    return new FCallNoArgBlockNode(position, name, args, (IterNode)iter);
                }
                return new FCallNoArgNode(position, args, name);
            }
            case 1: {
                if (iter != null) {
                    return new FCallOneArgBlockNode(position, name, args, (IterNode)iter);
                }
                return new FCallOneArgNode(position, name, args);
            }
            case 2: {
                if (iter != null) {
                    return new FCallTwoArgBlockNode(position, name, args, (IterNode)iter);
                }
                return new FCallTwoArgNode(position, name, args);
            }
            case 3: {
                if (iter != null) {
                    return new FCallThreeArgBlockNode(position, name, args, (IterNode)iter);
                }
                return new FCallThreeArgNode(position, name, args);
            }
            default: {
                if (iter != null) {
                    return new FCallManyArgsBlockNode(position, name, args, (IterNode)iter);
                }
                return new FCallManyArgsNode(position, name, args);
            }
        }
    }
    
    private Node new_fcall_blockpass(final Token operation, final BlockPassNode blockPass) {
        final ISourcePosition position = this.position(operation, blockPass);
        final String name = (String)operation.getValue();
        final Node args = blockPass.getArgsNode();
        if (args == null) {
            return new FCallNoArgBlockPassNode(position, name, args, blockPass);
        }
        if (!(args instanceof ArrayNode)) {
            return new FCallSpecialArgBlockPassNode(position, name, args, blockPass);
        }
        switch (((ArrayNode)args).size()) {
            case 0: {
                return new FCallNoArgBlockPassNode(position, name, args, blockPass);
            }
            case 1: {
                return new FCallOneArgBlockPassNode(position, name, (ArrayNode)args, blockPass);
            }
            case 2: {
                return new FCallTwoArgBlockPassNode(position, name, (ArrayNode)args, blockPass);
            }
            case 3: {
                return new FCallThreeArgBlockPassNode(position, name, (ArrayNode)args, blockPass);
            }
            default: {
                return new FCallManyArgsBlockPassNode(position, name, args, blockPass);
            }
        }
    }
    
    public Node new_fcall(final Token operation, final Node args, final Node iter) {
        if (args == null) {
            return this.new_fcall_noargs(operation, (IterNode)iter);
        }
        if (args instanceof ArrayNode) {
            return this.new_fcall_simpleargs(operation, (ArrayNode)args, iter);
        }
        if (!(args instanceof BlockPassNode)) {
            return new FCallSpecialArgNode(this.position(operation, args), (String)operation.getValue(), args);
        }
        if (iter == null) {
            return this.new_fcall_blockpass(operation, (BlockPassNode)args);
        }
        throw new SyntaxException(SyntaxException.PID.BLOCK_ARG_AND_BLOCK_GIVEN, iter.getPosition(), this.lexer.getCurrentLine(), "Both block arg and actual block given.", new Object[0]);
    }
    
    public Node new_super(final Node args, final Token operation) {
        if (args != null && args instanceof BlockPassNode) {
            return new SuperNode(this.position(operation, args), ((BlockPassNode)args).getArgsNode(), args);
        }
        return new SuperNode(operation.getPosition(), args);
    }
    
    public void initTopLocalVariables() {
        final DynamicScope scope = this.configuration.getScope();
        this.currentScope = scope.getStaticScope();
        this.result.setScope(scope);
    }
    
    public boolean isInSingle() {
        return this.inSingleton != 0;
    }
    
    public void setInSingle(final int inSingle) {
        this.inSingleton = inSingle;
    }
    
    public boolean isInDef() {
        return this.inDefinition;
    }
    
    public void setInDef(final boolean inDef) {
        this.inDefinition = inDef;
    }
    
    public int getInSingle() {
        return this.inSingleton;
    }
    
    public RubyParserResult getResult() {
        return this.result;
    }
    
    public void setResult(final RubyParserResult result) {
        this.result = result;
    }
    
    public void setConfiguration(final ParserConfiguration configuration) {
        this.configuration = configuration;
    }
    
    public void setWarnings(final IRubyWarnings warnings) {
        this.warnings = warnings;
    }
    
    public void setLexer(final RubyYaccLexer lexer) {
        this.lexer = lexer;
    }
    
    public DStrNode createDStrNode(final ISourcePosition position) {
        return new DStrNode(position);
    }
    
    public Node literal_concat(final ISourcePosition position, Node head, final Node tail) {
        if (head == null) {
            return tail;
        }
        if (tail == null) {
            return head;
        }
        if (head instanceof EvStrNode) {
            head = this.createDStrNode(head.getPosition()).add(head);
        }
        if (tail instanceof StrNode) {
            if (!(head instanceof StrNode)) {
                head.setPosition(head.getPosition());
                return ((ListNode)head).add(tail);
            }
            final StrNode front = (StrNode)head;
            if (front.getValue().getRealSize() > 0) {
                return new StrNode(head.getPosition(), front, (StrNode)tail);
            }
            return tail;
        }
        else {
            if (!(tail instanceof DStrNode)) {
                if (head instanceof StrNode) {
                    if (((StrNode)head).getValue().length() == 0) {
                        head = this.createDStrNode(head.getPosition());
                    }
                    else {
                        head = this.createDStrNode(head.getPosition()).add(head);
                    }
                }
                return ((DStrNode)head).add(tail);
            }
            if (head instanceof StrNode) {
                ((DStrNode)tail).prepend(head);
                return tail;
            }
            return ((ListNode)head).addAll(tail);
        }
    }
    
    public Node newEvStrNode(final ISourcePosition position, Node node) {
        final Node head = node;
        while (node != null) {
            if (node instanceof StrNode || node instanceof DStrNode || node instanceof EvStrNode) {
                return node;
            }
            if (!(node instanceof NewlineNode)) {
                return new EvStrNode(position, head);
            }
            node = ((NewlineNode)node).getNextNode();
        }
        return new EvStrNode(position, head);
    }
    
    public IterNode new_iter(final ISourcePosition position, Node vars, final StaticScope scope, final Node body) {
        if (vars != null && vars instanceof BlockPassNode) {
            vars = ((BlockPassNode)vars).getArgsNode();
        }
        return new IterNode(position, vars, scope, body);
    }
    
    public Node new_yield(final ISourcePosition position, Node node) {
        boolean state = true;
        if (node == null) {
            return new ZYieldNode(position);
        }
        if (node instanceof BlockPassNode) {
            throw new SyntaxException(SyntaxException.PID.BLOCK_ARG_UNEXPECTED, node.getPosition(), this.lexer.getCurrentLine(), "Block argument should not be given.", new Object[0]);
        }
        if (node instanceof ArrayNode && this.configuration.getVersion() == CompatVersion.RUBY1_8 && ((ArrayNode)node).size() == 1) {
            node = ((ArrayNode)node).get(0);
            state = false;
        }
        if (node != null && node instanceof SplatNode) {
            state = true;
        }
        if (state && node instanceof ArrayNode) {
            final ArrayNode args = (ArrayNode)node;
            switch (args.size()) {
                case 0: {
                    return new ZYieldNode(position);
                }
                case 1: {
                    return new YieldOneNode(position, args);
                }
                case 2: {
                    return new YieldTwoNode(position, args);
                }
                case 3: {
                    return new YieldThreeNode(position, args);
                }
            }
        }
        if (node instanceof FixnumNode) {
            return new YieldOneNode(position, (FixnumNode)node);
        }
        return new YieldNode(position, node, state);
    }
    
    public Node negateInteger(final Node integerNode) {
        if (integerNode instanceof FixnumNode) {
            final FixnumNode fixnumNode = (FixnumNode)integerNode;
            fixnumNode.setValue(-fixnumNode.getValue());
            return fixnumNode;
        }
        if (integerNode instanceof BignumNode) {
            final BignumNode bignumNode = (BignumNode)integerNode;
            final BigInteger value = bignumNode.getValue().negate();
            if (value.compareTo(RubyBignum.LONG_MIN) >= 0) {
                return new FixnumNode(bignumNode.getPosition(), value.longValue());
            }
            bignumNode.setValue(value);
        }
        return integerNode;
    }
    
    public FloatNode negateFloat(final FloatNode floatNode) {
        floatNode.setValue(-floatNode.getValue());
        return floatNode;
    }
    
    public ISourcePosition createEmptyArgsNodePosition(final ISourcePosition pos) {
        return pos;
    }
    
    public Node unwrapNewlineNode(final Node node) {
        if (node instanceof NewlineNode) {
            return ((NewlineNode)node).getNextNode();
        }
        return node;
    }
    
    private Node checkForNilNode(final Node node, final ISourcePosition defaultPosition) {
        return (node == null) ? new NilNode(defaultPosition) : node;
    }
    
    public Node new_args(final ISourcePosition position, final ListNode pre, final ListNode optional, final RestArgNode rest, final ListNode post, final BlockArgNode block) {
        if (optional == null && rest == null && post == null && block == null) {
            if (pre == null || pre.size() == 0) {
                return new ArgsNoArgNode(position);
            }
            if (pre.size() == 1 && !this.hasAssignableArgs(pre)) {
                return new ArgsPreOneArgNode(position, pre);
            }
            if (pre.size() == 2 && !this.hasAssignableArgs(pre)) {
                return new ArgsPreTwoArgNode(position, pre);
            }
        }
        return new ArgsNode(position, pre, optional, rest, post, block);
    }
    
    private boolean hasAssignableArgs(final ListNode list) {
        for (final Node node : list.childNodes()) {
            if (node instanceof AssignableNode) {
                return true;
            }
        }
        return false;
    }
    
    public Node newAlias(final ISourcePosition position, final Node newNode, final Node oldNode) {
        return new AliasNode(position, newNode, oldNode);
    }
    
    public Node newUndef(final ISourcePosition position, final Node nameNode) {
        return new UndefNode(position, nameNode);
    }
    
    public BlockArg18Node newBlockArg18(final ISourcePosition position, final Node blockValue, final Node args) {
        return new BlockArg18Node(position, blockValue, args);
    }
    
    public BlockArgNode newBlockArg(final ISourcePosition position, final Token nameToken) {
        final String identifier = (String)nameToken.getValue();
        if (this.getCurrentScope().getLocalScope().isDefined(identifier) >= 0) {
            throw new SyntaxException(SyntaxException.PID.BAD_IDENTIFIER, position, this.lexer.getCurrentLine(), "duplicate block argument name", new Object[0]);
        }
        return new BlockArgNode(position, this.getCurrentScope().getLocalScope().addVariable(identifier), identifier);
    }
    
    public void yyerror(final String message) {
        throw new SyntaxException(SyntaxException.PID.GRAMMAR_ERROR, this.lexer.getPosition(), this.lexer.getCurrentLine(), message, new Object[0]);
    }
    
    public void yyerror(final String message, final String[] expected, final String found) {
        final String text = message + ", unexpected " + found + "\n";
        throw new SyntaxException(SyntaxException.PID.GRAMMAR_ERROR, this.lexer.getPosition(), this.lexer.getCurrentLine(), text, new Object[] { found });
    }
    
    public ISourcePosition getPosition(final ISourcePositionHolder start) {
        return (start != null) ? this.lexer.getPosition(start.getPosition()) : this.lexer.getPosition();
    }
    
    public void warn(final IRubyWarnings.ID id, final ISourcePosition position, final String message, final Object... data) {
        this.warnings.warn(id, position, message, data);
    }
    
    public void warning(final IRubyWarnings.ID id, final ISourcePosition position, final String message, final Object... data) {
        if (this.warnings.isVerbose()) {
            this.warnings.warning(id, position, message, data);
        }
    }
    
    public boolean is_local_id(final Token identifier) {
        final String name = (String)identifier.getValue();
        return this.lexer.isIdentifierChar(name.charAt(0));
    }
    
    public ListNode list_append(final Node list, final Node item) {
        if (list == null) {
            return new ArrayNode(item.getPosition(), item);
        }
        if (!(list instanceof ListNode)) {
            return new ArrayNode(list.getPosition(), list).add(item);
        }
        return ((ListNode)list).add(item);
    }
    
    public Node new_bv(final Token identifier) {
        if (!this.is_local_id(identifier)) {
            this.getterIdentifierError(identifier.getPosition(), (String)identifier.getValue());
        }
        this.shadowing_lvar(identifier);
        return this.arg_var(identifier);
    }
    
    public ArgumentNode arg_var(final Token identifier) {
        String name = (String)identifier.getValue();
        final StaticScope current = this.getCurrentScope();
        if (name == "_") {
            for (int count = 0; current.exists(name) >= 0; name = "_$" + count++) {}
        }
        return new ArgumentNode(identifier.getPosition(), name, this.getCurrentScope().addVariableThisScope(name));
    }
    
    public Token formal_argument(final Token identifier) {
        if (!this.is_local_id(identifier)) {
            this.yyerror("formal argument must be local variable");
        }
        return this.shadowing_lvar(identifier);
    }
    
    public Token shadowing_lvar(final Token identifier) {
        final String name = (String)identifier.getValue();
        if (name == "_") {
            return identifier;
        }
        final StaticScope current = this.getCurrentScope();
        if (current instanceof BlockStaticScope) {
            if (current.exists(name) >= 0) {
                this.yyerror("duplicated argument name");
            }
            if (this.warnings.isVerbose() && current.isDefined(name) >= 0) {
                this.warnings.warning(IRubyWarnings.ID.STATEMENT_NOT_REACHED, identifier.getPosition(), "shadowing outer local variable - " + name, new Object[0]);
            }
        }
        else if (current.exists(name) >= 0) {
            this.yyerror("duplicated argument name");
        }
        return identifier;
    }
    
    public ListNode list_concat(final Node first, final Node second) {
        if (!(first instanceof ListNode)) {
            return new ArrayNode(first.getPosition(), first).add(second);
        }
        if (second instanceof ListNode) {
            return ((ListNode)first).addAll((ListNode)second);
        }
        return ((ListNode)first).addAll(second);
    }
    
    public Node splat_array(Node node) {
        if (node instanceof SplatNode) {
            node = ((SplatNode)node).getValue();
        }
        if (node instanceof ArrayNode) {
            return node;
        }
        return null;
    }
    
    public Node arg_append(final Node node1, final Node node2) {
        if (node1 == null) {
            return new ArrayNode(node2.getPosition(), node2);
        }
        if (node1 instanceof ListNode) {
            return ((ListNode)node1).add(node2);
        }
        if (node1 instanceof BlockPassNode) {
            return this.arg_append(((BlockPassNode)node1).getBodyNode(), node2);
        }
        if (node1 instanceof ArgsPushNode) {
            final ArgsPushNode pushNode = (ArgsPushNode)node1;
            final Node body = pushNode.getSecondNode();
            return new ArgsCatNode(pushNode.getPosition(), pushNode.getFirstNode(), new ArrayNode(body.getPosition(), body).add(node2));
        }
        return new ArgsPushNode(this.position(node1, node2), node1, node2);
    }
    
    public void regexpFragmentCheck(final RegexpNode end, final ByteList value) {
    }
    
    protected void checkRegexpSyntax(final ByteList value, final RegexpOptions options) {
        RubyRegexp.newRegexp(this.getConfiguration().getRuntime(), value, options);
    }
    
    public Node newRegexpNode(final ISourcePosition position, final Node contents, final RegexpNode end) {
        final RegexpOptions options = end.getOptions();
        final boolean is19 = !this.lexer.isOneEight();
        if (contents == null) {
            final ByteList newValue = ByteList.create("");
            this.regexpFragmentCheck(end, newValue);
            return new RegexpNode(position, newValue, options.withoutOnce());
        }
        if (contents instanceof StrNode) {
            final ByteList meat = (ByteList)((StrNode)contents).getValue().clone();
            this.regexpFragmentCheck(end, meat);
            this.checkRegexpSyntax(meat, options.withoutOnce());
            return new RegexpNode(contents.getPosition(), meat, options.withoutOnce());
        }
        if (contents instanceof DStrNode) {
            final DStrNode dStrNode = (DStrNode)contents;
            for (final Node fragment : dStrNode.childNodes()) {
                if (fragment instanceof StrNode) {
                    this.regexpFragmentCheck(end, ((StrNode)fragment).getValue());
                }
            }
            return new DRegexpNode(position, options, is19).addAll((ListNode)contents);
        }
        return new DRegexpNode(position, options, is19).add(contents);
    }
}

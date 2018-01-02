// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ZSuperNode;
import org.jruby.ast.YieldNode;
import org.jruby.ast.XStrNode;
import org.jruby.ast.WhileNode;
import org.jruby.ast.VAliasNode;
import org.jruby.ast.UntilNode;
import org.jruby.ast.SValueNode;
import org.jruby.ast.FileNode;
import org.jruby.ast.SplatNode;
import org.jruby.parser.StaticScope;
import org.jruby.ast.RootNode;
import org.jruby.ast.ReturnNode;
import org.jruby.ast.RescueBodyNode;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.PreExeNode;
import org.jruby.ast.PostExeNode;
import org.jruby.ast.OrNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.ast.OpAsgnNode;
import org.jruby.ast.OpAsgnOrNode;
import org.jruby.ast.BinaryOperatorNode;
import org.jruby.ast.NotNode;
import org.jruby.ast.NextNode;
import org.jruby.ast.StarNode;
import org.jruby.ast.ToAryNode;
import org.jruby.ast.ModuleNode;
import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2Node;
import org.jruby.ast.MatchNode;
import org.jruby.ast.LocalVarNode;
import org.jruby.runtime.BlockBody;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.IfNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.runtime.Arity;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.ForNode;
import org.jruby.ast.FlipNode;
import org.jruby.ast.BlockPassNode;
import org.jruby.ast.EvStrNode;
import org.jruby.ast.EnsureNode;
import org.jruby.ast.DXStrNode;
import org.jruby.ast.DVarNode;
import org.jruby.ast.DSymbolNode;
import org.jruby.ast.DStrNode;
import org.jruby.ast.DRegexpNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.DefsNode;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.RescueNode;
import org.jruby.ast.DefnNode;
import org.jruby.ast.NthRefNode;
import org.jruby.RubyMatchData;
import org.jruby.ast.SuperNode;
import org.jruby.ast.FCallNode;
import org.jruby.ast.InstVarNode;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.VCallNode;
import org.jruby.exceptions.JumpException;
import org.jruby.ast.DefinedNode;
import org.jruby.util.ByteList;
import org.jruby.ast.Colon2MethodNode;
import org.jruby.ast.Colon2ConstNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.ast.ClassVarDeclNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.ClassVarNode;
import org.jruby.ast.SClassNode;
import org.jruby.ast.Colon3Node;
import org.jruby.ast.NilNode;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.ClassNode;
import org.jruby.ast.WhenOneArgNode;
import java.util.HashMap;
import java.util.ArrayList;
import org.jruby.ast.WhenNode;
import org.jruby.ast.CaseNode;
import org.jruby.ast.StrNode;
import org.jruby.ast.SymbolNode;
import org.jruby.ast.FloatNode;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.JittedMethod;
import org.jruby.internal.runtime.methods.DefaultMethod;
import org.jruby.internal.runtime.methods.InterpretedMethod;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.callsite.CacheEntry;
import org.jruby.ast.FixnumNode;
import org.jruby.runtime.MethodIndex;
import org.jruby.ast.SelfNode;
import org.jruby.ast.IterNode;
import org.jruby.runtime.callsite.CachingCallSite;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.CallType;
import org.jruby.ast.CallNode;
import org.jruby.ast.BreakNode;
import org.jruby.ast.BlockNode;
import org.jruby.ast.BignumNode;
import org.jruby.ast.BeginNode;
import org.jruby.ast.BackRefNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.ArgsCatNode;
import org.jruby.ast.HashNode;
import java.util.Iterator;
import java.util.List;
import org.jruby.ast.ListNode;
import org.jruby.ast.AndNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.NewlineNode;
import org.jruby.ast.NodeType;
import org.jruby.ast.UndefNode;
import org.jruby.ast.LiteralNode;
import org.jruby.ast.AliasNode;
import org.jruby.ast.Node;
import java.util.Map;

public class ASTCompiler
{
    private boolean isAtRoot;
    private static final Map<Class, Map<Class, Map<String, String>>> Intrinsics;
    private Node currentBodyNode;
    
    public ASTCompiler() {
        this.isAtRoot = true;
    }
    
    public void compileBody(final Node node, final BodyCompiler context, final boolean expr) {
        final Node oldBodyNode = this.currentBodyNode;
        this.compile(this.currentBodyNode = node, context, expr);
        this.currentBodyNode = oldBodyNode;
    }
    
    public void compile(final Node node, final BodyCompiler context, final boolean expr) {
        if (node == null) {
            if (expr) {
                context.loadNil();
            }
            return;
        }
        switch (node.getNodeType()) {
            case ALIASNODE: {
                this.compileAlias((AliasNode)node, context, expr);
                break;
            }
            case ANDNODE: {
                this.compileAnd(node, context, expr);
                break;
            }
            case ARGSCATNODE: {
                this.compileArgsCat(node, context, expr);
                break;
            }
            case ARGSPUSHNODE: {
                this.compileArgsPush(node, context, expr);
                break;
            }
            case ARRAYNODE: {
                this.compileArray(node, context, expr);
                break;
            }
            case ATTRASSIGNNODE: {
                this.compileAttrAssign(node, context, expr);
                break;
            }
            case BACKREFNODE: {
                this.compileBackref(node, context, expr);
                break;
            }
            case BEGINNODE: {
                this.compileBegin(node, context, expr);
                break;
            }
            case BIGNUMNODE: {
                this.compileBignum(node, context, expr);
                break;
            }
            case BLOCKNODE: {
                this.compileBlock(node, context, expr);
                break;
            }
            case BREAKNODE: {
                this.compileBreak(node, context, expr);
                break;
            }
            case CALLNODE: {
                this.compileCall(node, context, expr);
                break;
            }
            case CASENODE: {
                this.compileCase(node, context, expr);
                break;
            }
            case CLASSNODE: {
                this.compileClass(node, context, expr);
                break;
            }
            case CLASSVARNODE: {
                this.compileClassVar(node, context, expr);
                break;
            }
            case CLASSVARASGNNODE: {
                this.compileClassVarAsgn(node, context, expr);
                break;
            }
            case CLASSVARDECLNODE: {
                this.compileClassVarDecl(node, context, expr);
                break;
            }
            case COLON2NODE: {
                this.compileColon2(node, context, expr);
                break;
            }
            case COLON3NODE: {
                this.compileColon3(node, context, expr);
                break;
            }
            case CONSTDECLNODE: {
                this.compileConstDecl(node, context, expr);
                break;
            }
            case CONSTNODE: {
                this.compileConst(node, context, expr);
                break;
            }
            case DASGNNODE: {
                this.compileDAsgn(node, context, expr);
                break;
            }
            case DEFINEDNODE: {
                this.compileDefined(node, context, expr);
                break;
            }
            case DEFNNODE: {
                this.compileDefn(node, context, expr);
                break;
            }
            case DEFSNODE: {
                this.compileDefs(node, context, expr);
                break;
            }
            case DOTNODE: {
                this.compileDot(node, context, expr);
                break;
            }
            case DREGEXPNODE: {
                this.compileDRegexp(node, context, expr);
                break;
            }
            case DSTRNODE: {
                this.compileDStr(node, context, expr);
                break;
            }
            case DSYMBOLNODE: {
                this.compileDSymbol(node, context, expr);
                break;
            }
            case DVARNODE: {
                this.compileDVar(node, context, expr);
                break;
            }
            case DXSTRNODE: {
                this.compileDXStr(node, context, expr);
                break;
            }
            case ENSURENODE: {
                this.compileEnsureNode(node, context, expr);
                break;
            }
            case EVSTRNODE: {
                this.compileEvStr(node, context, expr);
                break;
            }
            case FALSENODE: {
                this.compileFalse(node, context, expr);
                break;
            }
            case FCALLNODE: {
                this.compileFCall(node, context, expr);
                break;
            }
            case FIXNUMNODE: {
                this.compileFixnum(node, context, expr);
                break;
            }
            case FLIPNODE: {
                this.compileFlip(node, context, expr);
                break;
            }
            case FLOATNODE: {
                this.compileFloat(node, context, expr);
                break;
            }
            case FORNODE: {
                this.compileFor(node, context, expr);
                break;
            }
            case GLOBALASGNNODE: {
                this.compileGlobalAsgn(node, context, expr);
                break;
            }
            case GLOBALVARNODE: {
                this.compileGlobalVar(node, context, expr);
                break;
            }
            case HASHNODE: {
                this.compileHash(node, context, expr);
                break;
            }
            case IFNODE: {
                this.compileIf(node, context, expr);
                break;
            }
            case INSTASGNNODE: {
                this.compileInstAsgn(node, context, expr);
                break;
            }
            case INSTVARNODE: {
                this.compileInstVar(node, context, expr);
                break;
            }
            case ITERNODE: {
                this.compileIter(node, context);
                break;
            }
            case LITERALNODE: {
                this.compileLiteral((LiteralNode)node, context);
                break;
            }
            case LOCALASGNNODE: {
                this.compileLocalAsgn(node, context, expr);
                break;
            }
            case LOCALVARNODE: {
                this.compileLocalVar(node, context, expr);
                break;
            }
            case MATCH2NODE: {
                this.compileMatch2(node, context, expr);
                break;
            }
            case MATCH3NODE: {
                this.compileMatch3(node, context, expr);
                break;
            }
            case MATCHNODE: {
                this.compileMatch(node, context, expr);
                break;
            }
            case MODULENODE: {
                this.compileModule(node, context, expr);
                break;
            }
            case MULTIPLEASGNNODE: {
                this.compileMultipleAsgn(node, context, expr);
                break;
            }
            case NEWLINENODE: {
                this.compileNewline(node, context, expr);
                break;
            }
            case NEXTNODE: {
                this.compileNext(node, context, expr);
                break;
            }
            case NTHREFNODE: {
                this.compileNthRef(node, context, expr);
                break;
            }
            case NILNODE: {
                this.compileNil(node, context, expr);
                break;
            }
            case NOTNODE: {
                this.compileNot(node, context, expr);
                break;
            }
            case OPASGNANDNODE: {
                this.compileOpAsgnAnd(node, context, expr);
                break;
            }
            case OPASGNNODE: {
                this.compileOpAsgn(node, context, expr);
                break;
            }
            case OPASGNORNODE: {
                this.compileOpAsgnOr(node, context, expr);
                break;
            }
            case OPELEMENTASGNNODE: {
                this.compileOpElementAsgn(node, context, expr);
                break;
            }
            case ORNODE: {
                this.compileOr(node, context, expr);
                break;
            }
            case POSTEXENODE: {
                this.compilePostExe(node, context, expr);
                break;
            }
            case PREEXENODE: {
                this.compilePreExe(node, context, expr);
                break;
            }
            case REDONODE: {
                this.compileRedo(node, context, expr);
                break;
            }
            case REGEXPNODE: {
                this.compileRegexp(node, context, expr);
                break;
            }
            case RESCUEBODYNODE: {
                throw new NotCompilableException("rescue body is handled by rescue compilation at: " + node.getPosition());
            }
            case RESCUENODE: {
                this.compileRescue(node, context, expr);
                break;
            }
            case RETRYNODE: {
                this.compileRetry(node, context, expr);
                break;
            }
            case RETURNNODE: {
                this.compileReturn(node, context, expr);
                break;
            }
            case ROOTNODE: {
                throw new NotCompilableException("Use compileRoot(); Root node at: " + node.getPosition());
            }
            case SCLASSNODE: {
                this.compileSClass(node, context, expr);
                break;
            }
            case SELFNODE: {
                this.compileSelf(node, context, expr);
                break;
            }
            case SPLATNODE: {
                this.compileSplat(node, context, expr);
                break;
            }
            case STRNODE: {
                this.compileStr(node, context, expr);
                break;
            }
            case SUPERNODE: {
                this.compileSuper(node, context, expr);
                break;
            }
            case SVALUENODE: {
                this.compileSValue(node, context, expr);
                break;
            }
            case SYMBOLNODE: {
                this.compileSymbol(node, context, expr);
                break;
            }
            case TOARYNODE: {
                this.compileToAry(node, context, expr);
                break;
            }
            case TRUENODE: {
                this.compileTrue(node, context, expr);
                break;
            }
            case UNDEFNODE: {
                this.compileUndef((UndefNode)node, context, expr);
                break;
            }
            case UNTILNODE: {
                this.compileUntil(node, context, expr);
                break;
            }
            case VALIASNODE: {
                this.compileVAlias(node, context, expr);
                break;
            }
            case VCALLNODE: {
                this.compileVCall(node, context, expr);
                break;
            }
            case WHILENODE: {
                this.compileWhile(node, context, expr);
                break;
            }
            case WHENNODE: {
                assert false : "When nodes are handled by case node compilation.";
                break;
            }
            case XSTRNODE: {
                this.compileXStr(node, context, expr);
                break;
            }
            case YIELDNODE: {
                this.compileYield(node, context, expr);
                break;
            }
            case ZARRAYNODE: {
                this.compileZArray(node, context, expr);
                break;
            }
            case ZSUPERNODE: {
                this.compileZSuper(node, context, expr);
                break;
            }
            default: {
                throw new NotCompilableException("Unknown node encountered in compiler: " + node);
            }
        }
    }
    
    public void compileArguments(final Node node, final BodyCompiler context) {
        switch (node.getNodeType()) {
            case ARGSCATNODE: {
                this.compileArgsCatArguments(node, context, true);
                break;
            }
            case ARGSPUSHNODE: {
                this.compileArgsPushArguments(node, context, true);
                break;
            }
            case ARRAYNODE: {
                this.compileArrayArguments(node, context, true);
                break;
            }
            case SPLATNODE: {
                this.compileSplatArguments(node, context, true);
                break;
            }
            default: {
                this.compile(node, context, true);
                context.convertToJavaArray();
                break;
            }
        }
    }
    
    public ArgumentsCallback getArgsCallback(Node node) {
        if (node == null) {
            return null;
        }
        while (node.getNodeType() == NodeType.NEWLINENODE) {
            node = ((NewlineNode)node).getNextNode();
        }
        switch (node.getNodeType()) {
            case ARGSCATNODE:
            case ARGSPUSHNODE:
            case SPLATNODE: {
                return new VariableArityArguments(node);
            }
            case ARRAYNODE: {
                final ArrayNode arrayNode = (ArrayNode)node;
                if (arrayNode.size() == 0) {
                    return null;
                }
                if (arrayNode.size() > 3) {
                    return new VariableArityArguments(node);
                }
                return new SpecificArityArguments(node);
            }
            default: {
                return new SpecificArityArguments(node);
            }
        }
    }
    
    public void compileAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        switch (node.getNodeType()) {
            case ATTRASSIGNNODE: {
                this.compileAttrAssignAssignment(node, context, expr);
                break;
            }
            case DASGNNODE: {
                final DAsgnNode dasgnNode = (DAsgnNode)node;
                context.getVariableCompiler().assignLocalVariable(dasgnNode.getIndex(), dasgnNode.getDepth(), expr);
                break;
            }
            case CLASSVARASGNNODE: {
                this.compileClassVarAsgnAssignment(node, context, expr);
                break;
            }
            case CLASSVARDECLNODE: {
                this.compileClassVarDeclAssignment(node, context, expr);
                break;
            }
            case CONSTDECLNODE: {
                this.compileConstDeclAssignment(node, context, expr);
                break;
            }
            case GLOBALASGNNODE: {
                this.compileGlobalAsgnAssignment(node, context, expr);
                break;
            }
            case INSTASGNNODE: {
                this.compileInstAsgnAssignment(node, context, expr);
                break;
            }
            case LOCALASGNNODE: {
                final LocalAsgnNode localAsgnNode = (LocalAsgnNode)node;
                context.getVariableCompiler().assignLocalVariable(localAsgnNode.getIndex(), localAsgnNode.getDepth(), expr);
                break;
            }
            case MULTIPLEASGNNODE: {
                this.compileMultipleAsgnAssignment(node, context, expr);
                break;
            }
            case ZEROARGNODE: {
                context.consumeCurrentValue();
                break;
            }
            default: {
                throw new NotCompilableException("Can't compile assignment node: " + node);
            }
        }
    }
    
    public void compileAlias(final AliasNode alias, final BodyCompiler context, final boolean expr) {
        final CompilerCallback args = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(alias.getNewName(), context, true);
                ASTCompiler.this.compile(alias.getOldName(), context, true);
            }
        };
        context.defineAlias(args);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileAnd(final Node node, final BodyCompiler context, final boolean expr) {
        final AndNode andNode = (AndNode)node;
        if (andNode.getFirstNode().getNodeType().alwaysTrue()) {
            this.compile(andNode.getFirstNode(), context, false);
            this.compile(andNode.getSecondNode(), context, expr);
        }
        else if (andNode.getFirstNode().getNodeType().alwaysFalse()) {
            this.compile(andNode.getFirstNode(), context, expr);
        }
        else {
            this.compile(andNode.getFirstNode(), context, true);
            final BranchCallback longCallback = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(andNode.getSecondNode(), context, true);
                }
            };
            context.performLogicalAnd(longCallback);
            if (!expr) {
                context.consumeCurrentValue();
            }
        }
    }
    
    public void compileArray(final Node node, final BodyCompiler context, final boolean expr) {
        final ArrayNode arrayNode = (ArrayNode)node;
        if (expr) {
            final ArrayCallback callback = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                    final Node node = (Node)((Object[])sourceArray)[index];
                    ASTCompiler.this.compile(node, context, true);
                }
            };
            final List<Node> childNodes = arrayNode.childNodes();
            if (this.isListAllLiterals(arrayNode)) {
                context.createNewLiteralArray(childNodes.toArray(), callback, arrayNode.isLightweight());
            }
            else {
                context.createNewArray(childNodes.toArray(), callback, arrayNode.isLightweight());
            }
        }
        else {
            for (final Node nextNode : arrayNode.childNodes()) {
                this.compile(nextNode, context, false);
            }
        }
    }
    
    private boolean isListAllLiterals(final ListNode listNode) {
        for (int i = 0; i < listNode.size(); ++i) {
            switch (listNode.get(i).getNodeType()) {
                case BIGNUMNODE:
                case FIXNUMNODE:
                case FLOATNODE:
                case NILNODE:
                case REGEXPNODE:
                case STRNODE:
                case SYMBOLNODE:
                case ZARRAYNODE: {
                    break;
                }
                case ARRAYNODE: {
                    if (this.isListAllLiterals((ListNode)listNode.get(i))) {
                        break;
                    }
                    return false;
                }
                case HASHNODE: {
                    if (this.isListAllLiterals(((HashNode)listNode.get(i)).getListNode())) {
                        break;
                    }
                    return false;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void compileArgsCat(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsCatNode argsCatNode = (ArgsCatNode)node;
        this.compile(argsCatNode.getFirstNode(), context, true);
        this.compile(argsCatNode.getSecondNode(), context, true);
        context.argsCat();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArgsPush(final Node node, final BodyCompiler context, final boolean expr) {
        throw new NotCompilableException("ArgsPush should never be encountered bare in 1.8");
    }
    
    private void compileAttrAssign(final Node node, final BodyCompiler context, final boolean expr) {
        final AttrAssignNode attrAssignNode = (AttrAssignNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(attrAssignNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(attrAssignNode.getArgsNode());
        context.getInvocationCompiler().invokeAttrAssign(attrAssignNode.getName(), receiverCallback, argsCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileAttrAssignAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final AttrAssignNode attrAssignNode = (AttrAssignNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(attrAssignNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(attrAssignNode.getArgsNode());
        context.getInvocationCompiler().invokeAttrAssignMasgn(attrAssignNode.getName(), receiverCallback, argsCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileBackref(final Node node, final BodyCompiler context, final boolean expr) {
        final BackRefNode iVisited = (BackRefNode)node;
        context.performBackref(iVisited.getType());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileBegin(final Node node, final BodyCompiler context, final boolean expr) {
        final BeginNode beginNode = (BeginNode)node;
        this.compile(beginNode.getBodyNode(), context, expr);
    }
    
    public void compileBignum(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.createNewBignum(((BignumNode)node).getValue());
        }
    }
    
    public void compileBlock(final Node node, final BodyCompiler context, final boolean expr) {
        final BlockNode blockNode = (BlockNode)node;
        final Iterator<Node> iter = blockNode.childNodes().iterator();
        while (iter.hasNext()) {
            final Node n = iter.next();
            this.compile(n, context, !iter.hasNext() && expr);
        }
    }
    
    public void compileBreak(final Node node, final BodyCompiler context, final boolean expr) {
        final BreakNode breakNode = (BreakNode)node;
        final CompilerCallback valueCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (breakNode.getValueNode() != null) {
                    ASTCompiler.this.compile(breakNode.getValueNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        context.issueBreakEvent(valueCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileCall(final Node node, final BodyCompiler context, final boolean expr) {
        final CallNode callNode = (CallNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(callNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(callNode.getArgsNode());
        final CompilerCallback closureArg = this.getBlock(callNode.getIterNode());
        String name = callNode.getName();
        CallType callType = CallType.NORMAL;
        if (RubyInstanceConfig.DYNOPT_COMPILE_ENABLED) {
            if (callNode.getIterNode() == null) {
                if (callNode.callAdapter instanceof CachingCallSite) {
                    final CachingCallSite cacheSite = (CachingCallSite)callNode.callAdapter;
                    if (cacheSite.isOptimizable()) {
                        final CacheEntry entry = cacheSite.getCache();
                        if (entry.method.getNativeCall() != null) {
                            final DynamicMethod.NativeCall nativeCall = entry.method.getNativeCall();
                            if (argsCallback == null || (argsCallback.getArity() >= 0 && argsCallback.getArity() <= 3)) {
                                if (this.compileIntrinsic(context, callNode, cacheSite.methodName, entry.token, entry.method, receiverCallback, argsCallback, closureArg)) {
                                    return;
                                }
                                context.getInvocationCompiler().invokeNative(name, nativeCall, entry.token, receiverCallback, argsCallback, closureArg, CallType.NORMAL, callNode.getIterNode() instanceof IterNode);
                                return;
                            }
                        }
                        if (callNode.getReceiverNode() instanceof SelfNode && this.compileRecursiveCall(callNode.getName(), entry.token, CallType.NORMAL, callNode.getIterNode() instanceof IterNode, entry.method, context, argsCallback, closureArg, expr)) {
                            return;
                        }
                    }
                }
            }
        }
        if (argsCallback != null && argsCallback.getArity() == 1) {
            final Node argument = callNode.getArgsNode().childNodes().get(0);
            if (MethodIndex.hasFastOps(name) && argument instanceof FixnumNode) {
                context.getInvocationCompiler().invokeBinaryFixnumRHS(name, receiverCallback, ((FixnumNode)argument).getValue());
                if (!expr) {
                    context.consumeCurrentValue();
                }
                return;
            }
        }
        if (RubyInstanceConfig.FASTSEND_COMPILE_ENABLED) {
            final String literalSend = this.getLiteralSend(callNode);
            if (literalSend != null) {
                name = literalSend;
                callType = CallType.FUNCTIONAL;
            }
        }
        context.getInvocationCompiler().invokeDynamic(name, receiverCallback, argsCallback, callType, closureArg, callNode.getIterNode() instanceof IterNode);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    private boolean compileRecursiveCall(final String name, final int generation, final CallType callType, final boolean iterator, final DynamicMethod method, final BodyCompiler context, final ArgumentsCallback argsCallback, final CompilerCallback closure, final boolean expr) {
        if (this.currentBodyNode != null && context.isSimpleRoot()) {
            if (method instanceof InterpretedMethod) {
                final InterpretedMethod target = (InterpretedMethod)method;
                if (target.getBodyNode() == this.currentBodyNode) {
                    context.getInvocationCompiler().invokeRecursive(name, generation, argsCallback, closure, callType, iterator);
                    if (!expr) {
                        context.consumeCurrentValue();
                    }
                    return true;
                }
            }
            if (method instanceof DefaultMethod) {
                final DefaultMethod target2 = (DefaultMethod)method;
                if (target2.getBodyNode() == this.currentBodyNode) {
                    context.getInvocationCompiler().invokeRecursive(name, generation, argsCallback, closure, callType, iterator);
                    if (!expr) {
                        context.consumeCurrentValue();
                    }
                    return true;
                }
            }
            if (method instanceof JittedMethod) {
                final DefaultMethod target2 = (DefaultMethod)((JittedMethod)method).getRealMethod();
                if (target2.getBodyNode() == this.currentBodyNode) {
                    context.getInvocationCompiler().invokeRecursive(name, generation, argsCallback, closure, callType, iterator);
                    if (!expr) {
                        context.consumeCurrentValue();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean compileTrivialCall(final String name, final DynamicMethod method, final int generation, final BodyCompiler context, final boolean expr) {
        Node simpleBody = null;
        if (method instanceof InterpretedMethod) {
            final InterpretedMethod target = (InterpretedMethod)method;
            for (simpleBody = target.getBodyNode(); simpleBody instanceof NewlineNode; simpleBody = ((NewlineNode)simpleBody).getNextNode()) {}
        }
        if (method instanceof DefaultMethod) {
            final DefaultMethod target2 = (DefaultMethod)method;
            for (simpleBody = target2.getBodyNode(); simpleBody instanceof NewlineNode; simpleBody = ((NewlineNode)simpleBody).getNextNode()) {}
        }
        if (method instanceof JittedMethod) {
            final DefaultMethod target2 = (DefaultMethod)((JittedMethod)method).getRealMethod();
            for (simpleBody = target2.getBodyNode(); simpleBody instanceof NewlineNode; simpleBody = ((NewlineNode)simpleBody).getNextNode()) {}
        }
        if (simpleBody != null) {
            switch (simpleBody.getNodeType()) {
                case BIGNUMNODE:
                case FALSENODE:
                case FIXNUMNODE:
                case FLOATNODE:
                case INSTVARNODE:
                case NILNODE:
                case SELFNODE:
                case STRNODE:
                case SYMBOLNODE:
                case TRUENODE:
                case XSTRNODE: {
                    final Node simpleBodyFinal = simpleBody;
                    context.getInvocationCompiler().invokeTrivial(name, generation, new CompilerCallback() {
                        public void call(final BodyCompiler context) {
                            ASTCompiler.this.compile(simpleBodyFinal, context, true);
                        }
                    });
                    if (!expr) {
                        context.consumeCurrentValue();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean compileIntrinsic(final BodyCompiler context, final CallNode callNode, final String name, final int generation, final DynamicMethod method, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback, final CompilerCallback closureCallback) {
        if (!(method.getImplementationClass() instanceof RubyClass)) {
            return false;
        }
        final RubyClass implClass = (RubyClass)method.getImplementationClass();
        final Map<Class, Map<String, String>> typeIntrinsics = ASTCompiler.Intrinsics.get(implClass.getReifiedClass());
        if (typeIntrinsics != null && argsCallback != null && argsCallback.getArity() == 1) {
            final Node argument = callNode.getArgsNode().childNodes().get(0);
            if (argument instanceof FixnumNode) {
                final Map<String, String> typeLongIntrinsics = typeIntrinsics.get(FixnumNode.class);
                if (typeLongIntrinsics != null && typeLongIntrinsics.containsKey(name)) {
                    context.getInvocationCompiler().invokeFixnumLong(name, generation, receiverCallback, typeLongIntrinsics.get(name), ((FixnumNode)argument).getValue());
                    return true;
                }
            }
            if (argument instanceof FloatNode) {
                final Map<String, String> typeDoubleIntrinsics = typeIntrinsics.get(FloatNode.class);
                if (typeDoubleIntrinsics != null && typeDoubleIntrinsics.containsKey(name)) {
                    context.getInvocationCompiler().invokeFloatDouble(name, generation, receiverCallback, typeDoubleIntrinsics.get(name), ((FloatNode)argument).getValue());
                    return true;
                }
            }
        }
        return false;
    }
    
    private String getLiteralSend(final CallNode callNode) {
        if (callNode.getName().equals("__send__") && callNode.getArgsNode() instanceof ArrayNode) {
            final ArrayNode arrayNode = (ArrayNode)callNode.getArgsNode();
            if (arrayNode.get(0) instanceof SymbolNode) {
                return ((SymbolNode)arrayNode.get(0)).getName();
            }
            if (arrayNode.get(0) instanceof StrNode) {
                return ((StrNode)arrayNode.get(0)).getValue().toString();
            }
        }
        return null;
    }
    
    public void compileCase(final Node node, final BodyCompiler context, final boolean expr) {
        final CaseNode caseNode = (CaseNode)node;
        final boolean hasCase = caseNode.getCaseNode() != null;
        final List<Node> cases = caseNode.getCases().childNodes();
        final Node elseNode = caseNode.getElseNode();
        this.compileWhen(caseNode.getCaseNode(), cases, elseNode, context, expr, hasCase);
    }
    
    private FastSwitchType getHomogeneousSwitchType(final List<Node> whenNodes) {
        FastSwitchType foundType = null;
        for (final Node node : whenNodes) {
            final WhenNode whenNode = (WhenNode)node;
            if (whenNode.getExpressionNodes() instanceof ArrayNode) {
                final ArrayNode arrayNode = (ArrayNode)whenNode.getExpressionNodes();
                for (final Node maybeFixnum : arrayNode.childNodes()) {
                    if (!(maybeFixnum instanceof FixnumNode)) {
                        return null;
                    }
                    final FixnumNode fixnumNode = (FixnumNode)maybeFixnum;
                    final long value = fixnumNode.getValue();
                    if (value > 2147483647L || value < -2147483648L) {
                        return null;
                    }
                    if (foundType != null && foundType != FastSwitchType.FIXNUM) {
                        return null;
                    }
                    if (foundType != null) {
                        continue;
                    }
                    foundType = FastSwitchType.FIXNUM;
                }
            }
            else if (whenNode.getExpressionNodes() instanceof FixnumNode) {
                final FixnumNode fixnumNode2 = (FixnumNode)whenNode.getExpressionNodes();
                final long value2 = fixnumNode2.getValue();
                if (value2 > 2147483647L || value2 < -2147483648L) {
                    return null;
                }
                if (foundType != null && foundType != FastSwitchType.FIXNUM) {
                    return null;
                }
                if (foundType != null) {
                    continue;
                }
                foundType = FastSwitchType.FIXNUM;
            }
            else if (whenNode.getExpressionNodes() instanceof StrNode) {
                final StrNode strNode = (StrNode)whenNode.getExpressionNodes();
                if (strNode.getValue().length() == 1) {
                    if (foundType != null && foundType != FastSwitchType.SINGLE_CHAR_STRING) {
                        return null;
                    }
                    if (foundType != null) {
                        continue;
                    }
                    foundType = FastSwitchType.SINGLE_CHAR_STRING;
                }
                else {
                    if (foundType != null && foundType != FastSwitchType.STRING) {
                        return null;
                    }
                    if (foundType != null) {
                        continue;
                    }
                    foundType = FastSwitchType.STRING;
                }
            }
            else {
                if (!(whenNode.getExpressionNodes() instanceof SymbolNode)) {
                    return null;
                }
                final SymbolNode symbolNode = (SymbolNode)whenNode.getExpressionNodes();
                if (symbolNode.getName().length() == 1) {
                    if (foundType != null && foundType != FastSwitchType.SINGLE_CHAR_SYMBOL) {
                        return null;
                    }
                    if (foundType != null) {
                        continue;
                    }
                    foundType = FastSwitchType.SINGLE_CHAR_SYMBOL;
                }
                else {
                    if (foundType != null && foundType != FastSwitchType.SYMBOL) {
                        return null;
                    }
                    if (foundType != null) {
                        continue;
                    }
                    foundType = FastSwitchType.SYMBOL;
                }
            }
        }
        return foundType;
    }
    
    public void compileWhen(final Node value, final List<Node> whenNodes, final Node elseNode, final BodyCompiler context, final boolean expr, final boolean hasCase) {
        CompilerCallback caseValue = null;
        if (value != null) {
            caseValue = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    ASTCompiler.this.compile(value, context, true);
                    context.pollThreadEvents();
                }
            };
        }
        final List<ArgumentsCallback> conditionals = new ArrayList<ArgumentsCallback>();
        final List<CompilerCallback> bodies = new ArrayList<CompilerCallback>();
        Map<CompilerCallback, int[]> switchCases = null;
        final FastSwitchType switchType = this.getHomogeneousSwitchType(whenNodes);
        if (switchType != null && !RubyInstanceConfig.FULL_TRACE_ENABLED) {
            switchCases = new HashMap<CompilerCallback, int[]>();
        }
        for (final Node node : whenNodes) {
            final WhenNode whenNode = (WhenNode)node;
            final CompilerCallback body = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                        context.traceLine();
                    }
                    ASTCompiler.this.compile(whenNode.getBodyNode(), context, expr);
                }
            };
            this.addConditionalForWhen(whenNode, conditionals, bodies, body);
            if (switchCases != null) {
                switchCases.put(body, this.getOptimizedCases(whenNode));
            }
        }
        final CompilerCallback fallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(elseNode, context, expr);
            }
        };
        context.compileSequencedConditional(caseValue, switchType, switchCases, conditionals, bodies, fallback);
    }
    
    private int[] getOptimizedCases(final WhenNode whenNode) {
        if (whenNode.getExpressionNodes() instanceof ArrayNode) {
            final ArrayNode expression = (ArrayNode)whenNode.getExpressionNodes();
            if (expression.get(expression.size() - 1) instanceof WhenNode) {
                return null;
            }
            final int[] cases = new int[expression.size()];
            int i = 0;
            while (i < cases.length) {
                switch (expression.get(i).getNodeType()) {
                    case FIXNUMNODE: {
                        cases[i] = (int)((FixnumNode)expression.get(i)).getValue();
                        ++i;
                        continue;
                    }
                    default: {
                        return null;
                    }
                }
            }
            return cases;
        }
        else {
            if (whenNode.getExpressionNodes() instanceof FixnumNode) {
                final FixnumNode fixnumNode = (FixnumNode)whenNode.getExpressionNodes();
                return new int[] { (int)fixnumNode.getValue() };
            }
            if (whenNode.getExpressionNodes() instanceof StrNode) {
                final StrNode strNode = (StrNode)whenNode.getExpressionNodes();
                if (strNode.getValue().length() == 1) {
                    return new int[] { strNode.getValue().get(0) };
                }
                return new int[] { strNode.getValue().hashCode() };
            }
            else {
                if (!(whenNode.getExpressionNodes() instanceof SymbolNode)) {
                    return null;
                }
                final SymbolNode symbolNode = (SymbolNode)whenNode.getExpressionNodes();
                if (symbolNode.getName().length() == 1) {
                    return new int[] { symbolNode.getName().charAt(0) };
                }
                return new int[] { symbolNode.getName().hashCode() };
            }
        }
    }
    
    private void addConditionalForWhen(final WhenNode whenNode, final List<ArgumentsCallback> conditionals, final List<CompilerCallback> bodies, final CompilerCallback body) {
        bodies.add(body);
        if (whenNode.getExpressionNodes() instanceof ArrayNode && whenNode instanceof WhenOneArgNode) {
            conditionals.add(new ArgumentsCallback() {
                public int getArity() {
                    return 1;
                }
                
                public void call(final BodyCompiler context) {
                    ASTCompiler.this.compile(whenNode.getExpressionNodes(), context, true);
                }
            });
            return;
        }
        conditionals.add(this.getArgsCallback(whenNode.getExpressionNodes()));
    }
    
    public void compileClass(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassNode classNode = (ClassNode)node;
        final Node superNode = classNode.getSuperNode();
        final Node cpathNode = classNode.getCPath();
        CompilerCallback superCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(superNode, context, true);
            }
        };
        if (superNode == null) {
            superCallback = null;
        }
        final CompilerCallback bodyCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                final boolean oldIsAtRoot = ASTCompiler.this.isAtRoot;
                ASTCompiler.this.isAtRoot = false;
                if (classNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(classNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
                ASTCompiler.this.isAtRoot = oldIsAtRoot;
            }
        };
        final CompilerCallback pathCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (cpathNode instanceof Colon2Node) {
                    final Node leftNode = ((Colon2Node)cpathNode).getLeftNode();
                    if (leftNode != null) {
                        if (leftNode instanceof NilNode) {
                            context.raiseTypeError("No outer class");
                        }
                        else {
                            ASTCompiler.this.compile(leftNode, context, true);
                        }
                    }
                    else {
                        context.loadNil();
                    }
                }
                else if (cpathNode instanceof Colon3Node) {
                    context.loadObject();
                }
                else {
                    context.loadNil();
                }
            }
        };
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(classNode.getBodyNode());
        context.defineClass(classNode.getCPath().getName(), classNode.getScope(), superCallback, pathCallback, bodyCallback, null, inspector);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileSClass(final Node node, final BodyCompiler context, final boolean expr) {
        final SClassNode sclassNode = (SClassNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(sclassNode.getReceiverNode(), context, true);
            }
        };
        final CompilerCallback bodyCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                final boolean oldIsAtRoot = ASTCompiler.this.isAtRoot;
                ASTCompiler.this.isAtRoot = false;
                if (sclassNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(sclassNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
                ASTCompiler.this.isAtRoot = oldIsAtRoot;
            }
        };
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(sclassNode.getBodyNode());
        context.defineClass("SCLASS", sclassNode.getScope(), null, null, bodyCallback, receiverCallback, inspector);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileClassVar(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassVarNode classVarNode = (ClassVarNode)node;
        context.retrieveClassVariable(classVarNode.getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileClassVarAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassVarAsgnNode classVarAsgnNode = (ClassVarAsgnNode)node;
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(classVarAsgnNode.getValueNode(), context, true);
            }
        };
        context.assignClassVariable(classVarAsgnNode.getName(), value);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileClassVarAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassVarAsgnNode classVarAsgnNode = (ClassVarAsgnNode)node;
        context.assignClassVariable(classVarAsgnNode.getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileClassVarDecl(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassVarDeclNode classVarDeclNode = (ClassVarDeclNode)node;
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(classVarDeclNode.getValueNode(), context, true);
            }
        };
        context.declareClassVariable(classVarDeclNode.getName(), value);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileClassVarDeclAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final ClassVarDeclNode classVarDeclNode = (ClassVarDeclNode)node;
        context.declareClassVariable(classVarDeclNode.getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileConstDecl(final Node node, final BodyCompiler context, final boolean expr) {
        final ConstDeclNode constDeclNode = (ConstDeclNode)node;
        final Node constNode = constDeclNode.getConstNode();
        if (constNode == null) {
            this.compile(constDeclNode.getValueNode(), context, true);
            context.assignConstantInCurrent(constDeclNode.getName());
        }
        else if (constNode.getNodeType() == NodeType.COLON2NODE) {
            this.compile(((Colon2Node)constNode).getLeftNode(), context, true);
            this.compile(constDeclNode.getValueNode(), context, true);
            context.assignConstantInModule(constDeclNode.getName());
        }
        else {
            this.compile(constDeclNode.getValueNode(), context, true);
            context.assignConstantInObject(constDeclNode.getName());
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileConstDeclAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final ConstDeclNode constDeclNode = (ConstDeclNode)node;
        final Node constNode = constDeclNode.getConstNode();
        if (constNode == null) {
            context.assignConstantInCurrent(constDeclNode.getName());
        }
        else if (constNode.getNodeType() == NodeType.COLON2NODE) {
            this.compile(((Colon2Node)constNode).getLeftNode(), context, true);
            context.swapValues();
            context.assignConstantInModule(constDeclNode.getName());
        }
        else {
            context.assignConstantInObject(constDeclNode.getName());
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileConst(final Node node, final BodyCompiler context, final boolean expr) {
        final ConstNode constNode = (ConstNode)node;
        context.retrieveConstant(constNode.getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileColon2(final Node node, final BodyCompiler context, final boolean expr) {
        final Colon2Node iVisited = (Colon2Node)node;
        final Node leftNode = iVisited.getLeftNode();
        final String name = iVisited.getName();
        if (leftNode == null) {
            context.loadObject();
            context.retrieveConstantFromModule(name);
        }
        else if (node instanceof Colon2ConstNode) {
            this.compile(iVisited.getLeftNode(), context, true);
            context.retrieveConstantFromModule(name);
        }
        else if (node instanceof Colon2MethodNode) {
            final CompilerCallback receiverCallback = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    ASTCompiler.this.compile(iVisited.getLeftNode(), context, true);
                }
            };
            context.getInvocationCompiler().invokeDynamic(name, receiverCallback, null, CallType.FUNCTIONAL, null, false);
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileColon3(final Node node, final BodyCompiler context, final boolean expr) {
        final Colon3Node iVisited = (Colon3Node)node;
        final String name = iVisited.getName();
        context.retrieveConstantFromObject(name);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileGetDefinitionBase(final Node node, final BodyCompiler context) {
        switch (node.getNodeType()) {
            case BACKREFNODE:
            case CALLNODE:
            case CLASSVARNODE:
            case CLASSVARASGNNODE:
            case CLASSVARDECLNODE:
            case COLON2NODE:
            case COLON3NODE:
            case CONSTDECLNODE:
            case CONSTNODE:
            case DASGNNODE:
            case DVARNODE:
            case FALSENODE:
            case FCALLNODE:
            case GLOBALASGNNODE:
            case GLOBALVARNODE:
            case INSTVARNODE:
            case LOCALASGNNODE:
            case LOCALVARNODE:
            case MULTIPLEASGNNODE:
            case OPASGNNODE:
            case OPELEMENTASGNNODE:
            case SELFNODE:
            case TRUENODE:
            case VCALLNODE:
            case YIELDNODE: {
                this.compileGetDefinition(node, context);
                break;
            }
            case NEWLINENODE: {
                this.compileGetDefinitionBase(((NewlineNode)node).getNextNode(), context);
                break;
            }
            default: {
                final BranchCallback reg = new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.inDefined();
                        ASTCompiler.this.compileGetDefinition(node, context);
                    }
                };
                final BranchCallback out = new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.outDefined();
                    }
                };
                context.protect(reg, out, ByteList.class);
                break;
            }
        }
    }
    
    public void compileDefined(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            this.compileGetDefinitionBase(((DefinedNode)node).getExpressionNode(), context);
            context.stringOrNil();
        }
    }
    
    public void compileGetArgumentDefinition(final Node node, final BodyCompiler context, final String type) {
        if (node == null) {
            context.pushByteList(ByteList.create(type));
        }
        else if (node instanceof ArrayNode) {
            final Object endToken = context.getNewEnding();
            for (int i = 0; i < ((ArrayNode)node).size(); ++i) {
                final Node iterNode = ((ArrayNode)node).get(i);
                this.compileGetDefinition(iterNode, context);
                context.ifNull(endToken);
            }
            context.pushByteList(ByteList.create(type));
            final Object realToken = context.getNewEnding();
            context.go(realToken);
            context.setEnding(endToken);
            context.pushNull();
            context.setEnding(realToken);
        }
        else {
            this.compileGetDefinition(node, context);
            final Object endToken = context.getNewEnding();
            context.ifNull(endToken);
            context.pushByteList(ByteList.create(type));
            final Object realToken = context.getNewEnding();
            context.go(realToken);
            context.setEnding(endToken);
            context.pushNull();
            context.setEnding(realToken);
        }
    }
    
    public void compileGetDefinition(final Node node, final BodyCompiler context) {
        switch (node.getNodeType()) {
            case CLASSVARASGNNODE:
            case CLASSVARDECLNODE:
            case CONSTDECLNODE:
            case DASGNNODE:
            case GLOBALASGNNODE:
            case INSTASGNNODE:
            case LOCALASGNNODE:
            case MULTIPLEASGNNODE:
            case OPASGNANDNODE:
            case OPASGNNODE:
            case OPASGNORNODE:
            case OPELEMENTASGNNODE:
            case MULTIPLEASGN19NODE: {
                context.pushByteList(Node.ASSIGNMENT_BYTELIST);
                break;
            }
            case ANDNODE:
            case DREGEXPNODE:
            case DSTRNODE:
            case ORNODE: {
                this.compileDefinedAndOrDStrDRegexp(node, context);
                break;
            }
            case NOTNODE: {
                context.rescue(new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        ASTCompiler.this.compile(node, context, false);
                        context.pushByteList(Node.EXPRESSION_BYTELIST);
                    }
                }, JumpException.class, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                }, ByteList.class);
                context.definedNot();
                break;
            }
            case BACKREFNODE: {
                this.compileDefinedBackref(node, context);
                break;
            }
            case DVARNODE: {
                this.compileDefinedDVar(node, context);
                break;
            }
            case FALSENODE: {
                context.pushByteList(Node.FALSE_BYTELIST);
                break;
            }
            case TRUENODE: {
                context.pushByteList(Node.TRUE_BYTELIST);
                break;
            }
            case LOCALVARNODE: {
                context.pushByteList(Node.LOCAL_VARIABLE_BYTELIST);
                break;
            }
            case MATCH2NODE:
            case MATCH3NODE: {
                context.pushByteList(Node.METHOD_BYTELIST);
                break;
            }
            case NILNODE: {
                context.pushByteList(Node.NIL_BYTELIST);
                break;
            }
            case NTHREFNODE: {
                this.compileDefinedNthref(node, context);
                break;
            }
            case SELFNODE: {
                context.pushByteList(Node.SELF_BYTELIST);
                break;
            }
            case VCALLNODE: {
                context.loadSelf();
                context.isMethodBound(((VCallNode)node).getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushByteList(Node.METHOD_BYTELIST);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case YIELDNODE: {
                context.hasBlock(new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushByteList(Node.YIELD_BYTELIST);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case GLOBALVARNODE: {
                context.isGlobalDefined(((GlobalVarNode)node).getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushByteList(Node.GLOBAL_VARIABLE_BYTELIST);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case INSTVARNODE: {
                context.isInstanceVariableDefined(((InstVarNode)node).getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushByteList(Node.INSTANCE_VARIABLE_BYTELIST);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case CONSTNODE: {
                context.isConstantDefined(((ConstNode)node).getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushByteList(Node.CONSTANT_BYTELIST);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case FCALLNODE: {
                context.loadSelf();
                context.isMethodBound(((FCallNode)node).getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        ASTCompiler.this.compileGetArgumentDefinition(((FCallNode)node).getArgsNode(), context, "method");
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                });
                break;
            }
            case COLON2NODE:
            case COLON3NODE: {
                final Colon3Node iVisited = (Colon3Node)node;
                final String name = iVisited.getName();
                final BranchCallback setup = new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        if (iVisited instanceof Colon2Node) {
                            final Node leftNode = ((Colon2Node)iVisited).getLeftNode();
                            ASTCompiler.this.compile(leftNode, context, true);
                        }
                        else {
                            context.loadObject();
                        }
                    }
                };
                context.isConstantBranch(setup, name);
                break;
            }
            case CALLNODE: {
                this.compileDefinedCall(node, context);
                break;
            }
            case CLASSVARNODE: {
                final ClassVarNode iVisited2 = (ClassVarNode)node;
                final Object ending = context.getNewEnding();
                final Object failure = context.getNewEnding();
                final Object singleton = context.getNewEnding();
                final Object second = context.getNewEnding();
                final Object third = context.getNewEnding();
                context.loadCurrentModule();
                context.duplicateCurrentValue();
                context.ifNotNull(second);
                context.consumeCurrentValue();
                context.loadSelf();
                context.metaclass();
                context.duplicateCurrentValue();
                context.isClassVarDefined(iVisited2.getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.consumeCurrentValue();
                        context.pushByteList(Node.CLASS_VARIABLE_BYTELIST);
                        context.go(ending);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                    }
                });
                context.setEnding(second);
                context.duplicateCurrentValue();
                context.isClassVarDefined(iVisited2.getName(), new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.consumeCurrentValue();
                        context.pushByteList(Node.CLASS_VARIABLE_BYTELIST);
                        context.go(ending);
                    }
                }, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                    }
                });
                context.setEnding(third);
                context.duplicateCurrentValue();
                context.ifSingleton(singleton);
                context.consumeCurrentValue();
                context.go(failure);
                context.setEnding(singleton);
                context.attached();
                context.notIsModuleAndClassVarDefined(iVisited2.getName(), failure);
                context.pushByteList(Node.CLASS_VARIABLE_BYTELIST);
                context.go(ending);
                context.setEnding(failure);
                context.pushNull();
                context.setEnding(ending);
                break;
            }
            case ZSUPERNODE: {
                final Object fail = context.getNewEnding();
                final Object fail2 = context.getNewEnding();
                final Object fail_easy = context.getNewEnding();
                final Object ending2 = context.getNewEnding();
                context.getFrameName();
                context.duplicateCurrentValue();
                context.ifNull(fail);
                context.getFrameKlazz();
                context.duplicateCurrentValue();
                context.ifNull(fail2);
                context.superClass();
                context.ifNotSuperMethodBound(fail_easy);
                context.pushByteList(Node.SUPER_BYTELIST);
                context.go(ending2);
                context.setEnding(fail2);
                context.consumeCurrentValue();
                context.setEnding(fail);
                context.consumeCurrentValue();
                context.setEnding(fail_easy);
                context.pushNull();
                context.setEnding(ending2);
                break;
            }
            case SUPERNODE: {
                final Object fail = context.getNewEnding();
                final Object fail2 = context.getNewEnding();
                final Object fail_easy = context.getNewEnding();
                final Object ending2 = context.getNewEnding();
                context.getFrameName();
                context.duplicateCurrentValue();
                context.ifNull(fail);
                context.getFrameKlazz();
                context.duplicateCurrentValue();
                context.ifNull(fail2);
                context.superClass();
                context.ifNotSuperMethodBound(fail_easy);
                this.compileGetArgumentDefinition(((SuperNode)node).getArgsNode(), context, "super");
                context.go(ending2);
                context.setEnding(fail2);
                context.consumeCurrentValue();
                context.setEnding(fail);
                context.consumeCurrentValue();
                context.setEnding(fail_easy);
                context.pushNull();
                context.setEnding(ending2);
                break;
            }
            case ATTRASSIGNNODE: {
                final AttrAssignNode iVisited3 = (AttrAssignNode)node;
                final Object isnull = context.getNewEnding();
                final Object ending3 = context.getNewEnding();
                this.compileGetDefinition(iVisited3.getReceiverNode(), context);
                context.ifNull(isnull);
                context.rescue(new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        ASTCompiler.this.compile(iVisited3.getReceiverNode(), context, true);
                        context.duplicateCurrentValue();
                        context.metaclass();
                        context.duplicateCurrentValue();
                        context.getVisibilityFor(iVisited3.getName());
                        context.duplicateCurrentValue();
                        final Object isfalse = context.getNewEnding();
                        final Object isreal = context.getNewEnding();
                        final Object ending = context.getNewEnding();
                        context.isPrivate(isfalse, 3);
                        context.isNotProtected(isreal, 1);
                        context.selfIsKindOf(isreal);
                        context.consumeCurrentValue();
                        context.go(isfalse);
                        context.setEnding(isreal);
                        context.isMethodBound(iVisited3.getName(), new BranchCallback() {
                            public void branch(final BodyCompiler context) {
                                ASTCompiler.this.compileGetArgumentDefinition(iVisited3.getArgsNode(), context, "assignment");
                            }
                        }, new BranchCallback() {
                            public void branch(final BodyCompiler context) {
                                context.go(isfalse);
                            }
                        });
                        context.go(ending);
                        context.setEnding(isfalse);
                        context.pushNull();
                        context.setEnding(ending);
                    }
                }, JumpException.class, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                }, ByteList.class);
                context.go(ending3);
                context.setEnding(isnull);
                context.pushNull();
                context.setEnding(ending3);
                break;
            }
            default: {
                context.rescue(new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        ASTCompiler.this.compile(node, context, true);
                        context.consumeCurrentValue();
                        context.pushNull();
                    }
                }, JumpException.class, new BranchCallback() {
                    public void branch(final BodyCompiler context) {
                        context.pushNull();
                    }
                }, ByteList.class);
                context.consumeCurrentValue();
                context.pushByteList(Node.EXPRESSION_BYTELIST);
                break;
            }
        }
    }
    
    protected void compileDefinedAndOrDStrDRegexp(final Node node, final BodyCompiler context) {
        context.rescue(new BranchCallback() {
            public void branch(final BodyCompiler context) {
                ASTCompiler.this.compile(node, context, false);
                context.pushByteList(Node.EXPRESSION_BYTELIST);
            }
        }, JumpException.class, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        }, ByteList.class);
    }
    
    protected void compileDefinedCall(final Node node, final BodyCompiler context) {
        final CallNode iVisited = (CallNode)node;
        final Object isnull = context.getNewEnding();
        final Object ending = context.getNewEnding();
        this.compileGetDefinition(iVisited.getReceiverNode(), context);
        context.ifNull(isnull);
        context.rescue(new BranchCallback() {
            public void branch(final BodyCompiler context) {
                ASTCompiler.this.compile(iVisited.getReceiverNode(), context, true);
                context.definedCall(iVisited.getName());
            }
        }, JumpException.class, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        }, ByteList.class);
        context.go(ending);
        context.setEnding(isnull);
        context.pushNull();
        context.setEnding(ending);
    }
    
    protected void compileDefinedDVar(final Node node, final BodyCompiler context) {
        context.pushByteList(Node.LOCAL_VARIABLE_IN_BLOCK_BYTELIST);
    }
    
    protected void compileDefinedBackref(final Node node, final BodyCompiler context) {
        context.backref();
        context.isInstanceOf(RubyMatchData.class, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushByteList(ByteList.create("$" + ((BackRefNode)node).getType()));
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        });
    }
    
    protected void compileDefinedNthref(final Node node, final BodyCompiler context) {
        context.isCaptured(((NthRefNode)node).getMatchNumber(), new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushByteList(ByteList.create("$" + ((NthRefNode)node).getMatchNumber()));
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        });
    }
    
    public void compileDAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final DAsgnNode dasgnNode = (DAsgnNode)node;
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(dasgnNode.getValueNode(), context, true);
            }
        };
        context.getVariableCompiler().assignLocalVariable(dasgnNode.getIndex(), dasgnNode.getDepth(), value, expr);
    }
    
    public void compileDAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final DAsgnNode dasgnNode = (DAsgnNode)node;
        context.getVariableCompiler().assignLocalVariable(dasgnNode.getIndex(), dasgnNode.getDepth(), expr);
    }
    
    public void compileDefn(final Node node, final BodyCompiler context, final boolean expr) {
        final DefnNode defnNode = (DefnNode)node;
        final ArgsNode argsNode = defnNode.getArgsNode();
        final CompilerCallback body = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (defnNode.getBodyNode() != null) {
                    final Node oldBodyNode = ASTCompiler.this.currentBodyNode;
                    ASTCompiler.this.currentBodyNode = defnNode.getBodyNode();
                    if (defnNode.getBodyNode() instanceof RescueNode) {
                        ASTCompiler.this.compileRescueInternal(defnNode.getBodyNode(), context, true);
                    }
                    else {
                        ASTCompiler.this.compile(defnNode.getBodyNode(), context, true);
                    }
                    ASTCompiler.this.currentBodyNode = oldBodyNode;
                }
                else {
                    context.loadNil();
                }
            }
        };
        final CompilerCallback args = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compileArgs(argsNode, context, true);
            }
        };
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(defnNode.getArgsNode());
        if (defnNode.getBodyNode() instanceof RescueNode) {
            final RescueNode rescueNode = (RescueNode)defnNode.getBodyNode();
            inspector.inspect(rescueNode.getBodyNode());
            inspector.inspect(rescueNode.getElseNode());
            inspector.inspect(rescueNode.getRescueNode());
        }
        else {
            inspector.inspect(defnNode.getBodyNode());
        }
        context.defineNewMethod(defnNode.getName(), defnNode.getArgsNode().getArity().getValue(), defnNode.getScope(), body, args, null, inspector, this.isAtRoot, defnNode.getPosition().getFile(), defnNode.getPosition().getStartLine(), RuntimeHelpers.encodeParameterList(argsNode));
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileDefs(final Node node, final BodyCompiler context, final boolean expr) {
        final DefsNode defsNode = (DefsNode)node;
        final ArgsNode argsNode = defsNode.getArgsNode();
        final CompilerCallback receiver = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(defsNode.getReceiverNode(), context, true);
            }
        };
        final CompilerCallback body = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (defsNode.getBodyNode() != null) {
                    if (defsNode.getBodyNode() instanceof RescueNode) {
                        ASTCompiler.this.compileRescueInternal(defsNode.getBodyNode(), context, true);
                    }
                    else {
                        ASTCompiler.this.compile(defsNode.getBodyNode(), context, true);
                    }
                }
                else {
                    context.loadNil();
                }
            }
        };
        final CompilerCallback args = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compileArgs(argsNode, context, true);
            }
        };
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(defsNode.getArgsNode());
        if (defsNode.getBodyNode() instanceof RescueNode) {
            final RescueNode rescueNode = (RescueNode)defsNode.getBodyNode();
            inspector.inspect(rescueNode.getBodyNode());
            inspector.inspect(rescueNode.getElseNode());
            inspector.inspect(rescueNode.getRescueNode());
        }
        else {
            inspector.inspect(defsNode.getBodyNode());
        }
        context.defineNewMethod(defsNode.getName(), defsNode.getArgsNode().getArity().getValue(), defsNode.getScope(), body, args, receiver, inspector, false, defsNode.getPosition().getFile(), defsNode.getPosition().getStartLine(), RuntimeHelpers.encodeParameterList(argsNode));
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArgs(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsNode argsNode = (ArgsNode)node;
        final int required = argsNode.getRequiredArgsCount();
        final int opt = argsNode.getOptionalArgsCount();
        final int rest = argsNode.getRestArg();
        ArrayCallback requiredAssignment = null;
        ArrayCallback optionalGiven = null;
        ArrayCallback optionalNotGiven = null;
        CompilerCallback restAssignment = null;
        CompilerCallback blockAssignment = null;
        if (required > 0) {
            requiredAssignment = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    context.getVariableCompiler().assignLocalVariable(index, false);
                }
            };
        }
        if (opt > 0) {
            optionalGiven = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    final Node optArg = ((ListNode)object).get(index);
                    ASTCompiler.this.compileAssignment(optArg, context, false);
                }
            };
            optionalNotGiven = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    final Node optArg = ((ListNode)object).get(index);
                    ASTCompiler.this.compile(optArg, context, false);
                }
            };
        }
        if (rest > -1) {
            restAssignment = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    context.getVariableCompiler().assignLocalVariable(argsNode.getRestArg(), false);
                }
            };
        }
        if (argsNode.getBlock() != null) {
            blockAssignment = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    context.getVariableCompiler().assignLocalVariable(argsNode.getBlock().getCount(), false);
                }
            };
        }
        context.getVariableCompiler().checkMethodArity(required, opt, rest);
        context.getVariableCompiler().assignMethodArguments(argsNode.getPre(), argsNode.getRequiredArgsCount(), argsNode.getOptArgs(), argsNode.getOptionalArgsCount(), requiredAssignment, optionalGiven, optionalNotGiven, restAssignment, blockAssignment);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileDot(final Node node, final BodyCompiler context, final boolean expr) {
        final DotNode dotNode = (DotNode)node;
        if (expr) {
            final CompilerCallback beginEndCallback = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    ASTCompiler.this.compile(dotNode.getBeginNode(), context, true);
                    ASTCompiler.this.compile(dotNode.getEndNode(), context, true);
                }
            };
            context.createNewRange(beginEndCallback, dotNode.isExclusive());
        }
    }
    
    public void compileDRegexp(final Node node, final BodyCompiler context, final boolean expr) {
        final DRegexpNode dregexpNode = (DRegexpNode)node;
        final CompilerCallback createStringCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                final ArrayCallback dstrCallback = new ArrayCallback() {
                    public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                        ASTCompiler.this.compile(dregexpNode.get(index), context, true);
                    }
                };
                context.createNewString(dstrCallback, dregexpNode.size());
            }
        };
        if (expr) {
            context.createNewRegexp(createStringCallback, dregexpNode.getOptions().toEmbeddedOptions());
        }
        else {
            for (final Node nextNode : dregexpNode.childNodes()) {
                this.compile(nextNode, context, false);
            }
        }
    }
    
    public void compileDStr(final Node node, final BodyCompiler context, final boolean expr) {
        final DStrNode dstrNode = (DStrNode)node;
        final ArrayCallback dstrCallback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                ASTCompiler.this.compile(dstrNode.get(index), context, true);
            }
        };
        if (expr) {
            context.createNewString(dstrCallback, dstrNode.size());
        }
        else {
            for (final Node nextNode : dstrNode.childNodes()) {
                this.compile(nextNode, context, false);
            }
        }
    }
    
    public void compileDSymbol(final Node node, final BodyCompiler context, final boolean expr) {
        final DSymbolNode dsymbolNode = (DSymbolNode)node;
        final ArrayCallback dstrCallback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                ASTCompiler.this.compile(dsymbolNode.get(index), context, true);
            }
        };
        if (expr) {
            context.createNewSymbol(dstrCallback, dsymbolNode.size());
        }
        else {
            for (final Node nextNode : dsymbolNode.childNodes()) {
                this.compile(nextNode, context, false);
            }
        }
    }
    
    public void compileDVar(final Node node, final BodyCompiler context, final boolean expr) {
        final DVarNode dvarNode = (DVarNode)node;
        if (expr) {
            context.getVariableCompiler().retrieveLocalVariable(dvarNode.getIndex(), dvarNode.getDepth());
        }
    }
    
    public void compileDXStr(final Node node, final BodyCompiler context, final boolean expr) {
        final DXStrNode dxstrNode = (DXStrNode)node;
        final ArrayCallback dstrCallback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                ASTCompiler.this.compile(dxstrNode.get(index), context, true);
            }
        };
        final ArgumentsCallback argsCallback = new ArgumentsCallback() {
            public int getArity() {
                return 1;
            }
            
            public void call(final BodyCompiler context) {
                context.createNewString(dstrCallback, dxstrNode.size());
            }
        };
        context.getInvocationCompiler().invokeDynamic("`", null, argsCallback, CallType.FUNCTIONAL, null, false);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileEnsureNode(final Node node, final BodyCompiler context, final boolean expr) {
        final EnsureNode ensureNode = (EnsureNode)node;
        if (ensureNode.getEnsureNode() != null) {
            context.performEnsure(new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    if (ensureNode.getBodyNode() != null) {
                        ASTCompiler.this.compile(ensureNode.getBodyNode(), context, true);
                    }
                    else {
                        context.loadNil();
                    }
                }
            }, new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(ensureNode.getEnsureNode(), context, false);
                }
            });
        }
        else if (ensureNode.getBodyNode() != null) {
            this.compile(ensureNode.getBodyNode(), context, true);
        }
        else {
            context.loadNil();
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileEvStr(final Node node, final BodyCompiler context, final boolean expr) {
        final EvStrNode evStrNode = (EvStrNode)node;
        this.compile(evStrNode.getBody(), context, true);
        context.asString();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileFalse(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.loadFalse();
            context.pollThreadEvents();
        }
    }
    
    public void compileFCall(final Node node, final BodyCompiler context, final boolean expr) {
        final FCallNode fcallNode = (FCallNode)node;
        final ArgumentsCallback argsCallback = this.getArgsCallback(fcallNode.getArgsNode());
        final CompilerCallback closureArg = this.getBlock(fcallNode.getIterNode());
        if (RubyInstanceConfig.DYNOPT_COMPILE_ENABLED) {
            if (fcallNode.getIterNode() == null) {
                if (fcallNode.callAdapter instanceof CachingCallSite) {
                    final CachingCallSite cacheSite = (CachingCallSite)fcallNode.callAdapter;
                    if (cacheSite.isOptimizable()) {
                        final CacheEntry entry = cacheSite.getCache();
                        if (closureArg == null && (argsCallback == null || (argsCallback.getArity() >= 0 && argsCallback.getArity() <= 3))) {
                            if (this.compileRecursiveCall(fcallNode.getName(), entry.token, CallType.FUNCTIONAL, fcallNode.getIterNode() instanceof IterNode, entry.method, context, argsCallback, closureArg, expr)) {
                                return;
                            }
                            if (closureArg == null && argsCallback == null && this.compileTrivialCall(fcallNode.getName(), entry.method, entry.token, context, expr)) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        context.getInvocationCompiler().invokeDynamic(fcallNode.getName(), null, argsCallback, CallType.FUNCTIONAL, closureArg, fcallNode.getIterNode() instanceof IterNode);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    private CompilerCallback getBlock(final Node node) {
        if (node == null) {
            return null;
        }
        switch (node.getNodeType()) {
            case ITERNODE: {
                final IterNode iterNode = (IterNode)node;
                return new CompilerCallback() {
                    public void call(final BodyCompiler context) {
                        ASTCompiler.this.compile(iterNode, context, true);
                    }
                };
            }
            case BLOCKPASSNODE: {
                final BlockPassNode blockPassNode = (BlockPassNode)node;
                return new CompilerCallback() {
                    public void call(final BodyCompiler context) {
                        ASTCompiler.this.compile(blockPassNode.getBodyNode(), context, true);
                        context.unwrapPassedBlock();
                    }
                };
            }
            default: {
                throw new NotCompilableException("ERROR: Encountered a method with a non-block, non-blockpass iter node at: " + node);
            }
        }
    }
    
    public void compileFixnum(final Node node, final BodyCompiler context, final boolean expr) {
        final FixnumNode fixnumNode = (FixnumNode)node;
        if (expr) {
            context.createNewFixnum(fixnumNode.getValue());
        }
    }
    
    public void compileFlip(final Node node, final BodyCompiler context, final boolean expr) {
        final FlipNode flipNode = (FlipNode)node;
        context.getVariableCompiler().retrieveLocalVariable(flipNode.getIndex(), flipNode.getDepth());
        if (flipNode.isExclusive()) {
            context.performBooleanBranch(new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(flipNode.getEndNode(), context, true);
                    context.performBooleanBranch(new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                            context.loadFalse();
                            context.getVariableCompiler().assignLocalVariable(flipNode.getIndex(), flipNode.getDepth(), false);
                        }
                    }, new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                        }
                    });
                    context.loadTrue();
                }
            }, new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(flipNode.getBeginNode(), context, true);
                    ASTCompiler.this.becomeTrueOrFalse(context);
                    context.getVariableCompiler().assignLocalVariable(flipNode.getIndex(), flipNode.getDepth(), true);
                }
            });
        }
        else {
            context.performBooleanBranch(new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(flipNode.getEndNode(), context, true);
                    context.performBooleanBranch(new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                            context.loadFalse();
                            context.getVariableCompiler().assignLocalVariable(flipNode.getIndex(), flipNode.getDepth(), false);
                        }
                    }, new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                        }
                    });
                    context.loadTrue();
                }
            }, new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(flipNode.getBeginNode(), context, true);
                    context.performBooleanBranch(new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                            ASTCompiler.this.compile(flipNode.getEndNode(), context, true);
                            ASTCompiler.this.flipTrueOrFalse(context);
                            context.getVariableCompiler().assignLocalVariable(flipNode.getIndex(), flipNode.getDepth(), false);
                            context.loadTrue();
                        }
                    }, new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                            context.loadFalse();
                        }
                    });
                }
            });
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    private void becomeTrueOrFalse(final BodyCompiler context) {
        context.performBooleanBranch(new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.loadTrue();
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.loadFalse();
            }
        });
    }
    
    private void flipTrueOrFalse(final BodyCompiler context) {
        context.performBooleanBranch(new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.loadFalse();
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.loadTrue();
            }
        });
    }
    
    public void compileFloat(final Node node, final BodyCompiler context, final boolean expr) {
        final FloatNode floatNode = (FloatNode)node;
        if (expr) {
            context.createNewFloat(floatNode.getValue());
        }
    }
    
    public void compileFor(final Node node, final BodyCompiler context, final boolean expr) {
        final ForNode forNode = (ForNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(forNode.getIterNode(), context, true);
            }
        };
        final CompilerCallback closureArg = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compileForIter(forNode, context);
            }
        };
        context.getInvocationCompiler().invokeDynamic("each", receiverCallback, null, CallType.NORMAL, closureArg, true);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileForIter(final Node node, final BodyCompiler context) {
        final ForNode forNode = (ForNode)node;
        final CompilerCallback closureBody = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (forNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(forNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        final CompilerCallback closureArgs = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (forNode.getVarNode() != null) {
                    ASTCompiler.this.compileAssignment(forNode.getVarNode(), context, false);
                    context.consumeCurrentValue();
                }
            }
        };
        boolean hasMultipleArgsHead = false;
        if (forNode.getVarNode() instanceof MultipleAsgnNode) {
            hasMultipleArgsHead = (((MultipleAsgnNode)forNode.getVarNode()).getHeadNode() != null);
        }
        NodeType argsNodeId = null;
        if (forNode.getVarNode() != null) {
            argsNodeId = forNode.getVarNode().getNodeType();
        }
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(forNode.getBodyNode());
        inspector.inspect(forNode.getVarNode());
        inspector.setFlag(forNode, 2);
        context.createNewForLoop(Arity.procArityOf(forNode.getVarNode()).getValue(), closureBody, closureArgs, hasMultipleArgsHead, argsNodeId, inspector);
    }
    
    public void compileGlobalAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final GlobalAsgnNode globalAsgnNode = (GlobalAsgnNode)node;
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(globalAsgnNode.getValueNode(), context, true);
            }
        };
        if (globalAsgnNode.getName().length() == 2) {
            switch (globalAsgnNode.getName().charAt(1)) {
                case '_': {
                    context.getVariableCompiler().assignLastLine(value);
                    break;
                }
                case '~': {
                    context.getVariableCompiler().assignBackRef(value);
                    break;
                }
                default: {
                    context.assignGlobalVariable(globalAsgnNode.getName(), value);
                    break;
                }
            }
        }
        else {
            context.assignGlobalVariable(globalAsgnNode.getName(), value);
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileGlobalAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final GlobalAsgnNode globalAsgnNode = (GlobalAsgnNode)node;
        if (globalAsgnNode.getName().length() == 2) {
            switch (globalAsgnNode.getName().charAt(1)) {
                case '_': {
                    context.getVariableCompiler().assignLastLine();
                    break;
                }
                case '~': {
                    context.getVariableCompiler().assignBackRef();
                    break;
                }
                default: {
                    context.assignGlobalVariable(globalAsgnNode.getName());
                    break;
                }
            }
        }
        else {
            context.assignGlobalVariable(globalAsgnNode.getName());
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileGlobalVar(final Node node, final BodyCompiler context, final boolean expr) {
        final GlobalVarNode globalVarNode = (GlobalVarNode)node;
        if (expr) {
            if (globalVarNode.getName().length() == 2) {
                switch (globalVarNode.getName().charAt(1)) {
                    case '_': {
                        context.getVariableCompiler().retrieveLastLine();
                        break;
                    }
                    case '~': {
                        context.getVariableCompiler().retrieveBackRef();
                        break;
                    }
                    default: {
                        context.retrieveGlobalVariable(globalVarNode.getName());
                        break;
                    }
                }
            }
            else {
                context.retrieveGlobalVariable(globalVarNode.getName());
            }
        }
    }
    
    public void compileHash(final Node node, final BodyCompiler context, final boolean expr) {
        this.compileHashCommon((HashNode)node, context, expr);
    }
    
    protected void compileHashCommon(final HashNode hashNode, final BodyCompiler context, final boolean expr) {
        if (expr) {
            if (hashNode.getListNode() == null || hashNode.getListNode().size() == 0) {
                context.createEmptyHash();
                return;
            }
            final ArrayCallback hashCallback = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                    final ListNode listNode = (ListNode)sourceArray;
                    final int keyIndex = index * 2;
                    ASTCompiler.this.compile(listNode.get(keyIndex), context, true);
                    ASTCompiler.this.compile(listNode.get(keyIndex + 1), context, true);
                }
            };
            if (this.isListAllLiterals(hashNode.getListNode())) {
                context.createNewLiteralHash(hashNode.getListNode(), hashCallback, hashNode.getListNode().size() / 2);
            }
            else {
                context.createNewHash(hashNode.getListNode(), hashCallback, hashNode.getListNode().size() / 2);
            }
        }
        else {
            for (final Node nextNode : hashNode.getListNode().childNodes()) {
                this.compile(nextNode, context, false);
            }
        }
    }
    
    protected void createNewHash(final BodyCompiler context, final HashNode hashNode, final ArrayCallback hashCallback) {
        context.createNewHash(hashNode.getListNode(), hashCallback, hashNode.getListNode().size() / 2);
    }
    
    public void compileIf(final Node node, final BodyCompiler context, final boolean expr) {
        final IfNode ifNode = (IfNode)node;
        Node actualCondition;
        for (actualCondition = ifNode.getCondition(); actualCondition instanceof NewlineNode; actualCondition = ((NewlineNode)actualCondition).getNextNode()) {}
        if (actualCondition.getNodeType().alwaysTrue()) {
            this.compile(actualCondition, context, false);
            this.compile(ifNode.getThenBody(), context, expr);
        }
        else if (actualCondition.getNodeType().alwaysFalse()) {
            this.compile(ifNode.getElseBody(), context, expr);
        }
        else {
            final BranchCallback trueCallback = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    if (ifNode.getThenBody() != null) {
                        ASTCompiler.this.compile(ifNode.getThenBody(), context, expr);
                    }
                    else if (expr) {
                        context.loadNil();
                    }
                }
            };
            final BranchCallback falseCallback = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    if (ifNode.getElseBody() != null) {
                        ASTCompiler.this.compile(ifNode.getElseBody(), context, expr);
                    }
                    else if (expr) {
                        context.loadNil();
                    }
                }
            };
            this.compile(actualCondition, context, true);
            context.performBooleanBranch(trueCallback, falseCallback);
        }
    }
    
    public void compileInstAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final InstAsgnNode instAsgnNode = (InstAsgnNode)node;
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(instAsgnNode.getValueNode(), context, true);
            }
        };
        context.assignInstanceVariable(instAsgnNode.getName(), value);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileInstAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final InstAsgnNode instAsgnNode = (InstAsgnNode)node;
        context.assignInstanceVariable(instAsgnNode.getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileInstVar(final Node node, final BodyCompiler context, final boolean expr) {
        final InstVarNode instVarNode = (InstVarNode)node;
        if (expr) {
            context.retrieveInstanceVariable(instVarNode.getName());
        }
    }
    
    public void compileIter(final Node node, final BodyCompiler context) {
        final IterNode iterNode = (IterNode)node;
        final CompilerCallback closureBody = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (iterNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(iterNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        final CompilerCallback closureArgs = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (iterNode.getVarNode() != null) {
                    ASTCompiler.this.compileAssignment(iterNode.getVarNode(), context, false);
                }
                else {
                    context.consumeCurrentValue();
                }
                if (iterNode.getBlockVarNode() != null) {
                    ASTCompiler.this.compileAssignment(iterNode.getBlockVarNode(), context, false);
                }
                else {
                    context.consumeCurrentValue();
                }
            }
        };
        boolean hasMultipleArgsHead = false;
        if (iterNode.getVarNode() instanceof MultipleAsgnNode) {
            hasMultipleArgsHead = (((MultipleAsgnNode)iterNode.getVarNode()).getHeadNode() != null);
        }
        final NodeType argsNodeId = BlockBody.getArgumentTypeWackyHack(iterNode);
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(iterNode.getBodyNode());
        inspector.inspect(iterNode.getVarNode());
        context.createNewClosure(iterNode.getPosition().getFile(), iterNode.getPosition().getStartLine(), iterNode.getScope(), Arity.procArityOf(iterNode.getVarNode()).getValue(), closureBody, closureArgs, hasMultipleArgsHead, argsNodeId, inspector);
    }
    
    public void compileLiteral(final LiteralNode literal, final BodyCompiler context) {
        context.literal(literal.getName());
    }
    
    public void compileLocalAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final LocalAsgnNode localAsgnNode = (LocalAsgnNode)node;
        if (ASTInspector.PRAGMAS.contains(localAsgnNode.getName())) {
            if (expr) {
                context.loadNil();
            }
        }
        else {
            final CompilerCallback value = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    ASTCompiler.this.compile(localAsgnNode.getValueNode(), context, true);
                }
            };
            context.getVariableCompiler().assignLocalVariable(localAsgnNode.getIndex(), localAsgnNode.getDepth(), value, expr);
        }
    }
    
    public void compileLocalAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final LocalAsgnNode localAsgnNode = (LocalAsgnNode)node;
        context.getVariableCompiler().assignLocalVariable(localAsgnNode.getIndex(), localAsgnNode.getDepth(), expr);
    }
    
    public void compileLocalVar(final Node node, final BodyCompiler context, final boolean expr) {
        final LocalVarNode localVarNode = (LocalVarNode)node;
        if (expr) {
            context.getVariableCompiler().retrieveLocalVariable(localVarNode.getIndex(), localVarNode.getDepth());
        }
    }
    
    public void compileMatch(final Node node, final BodyCompiler context, final boolean expr) {
        final MatchNode matchNode = (MatchNode)node;
        this.compile(matchNode.getRegexpNode(), context, true);
        context.match();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileMatch2(final Node node, final BodyCompiler context, final boolean expr) {
        final Match2Node matchNode = (Match2Node)node;
        this.compile(matchNode.getReceiverNode(), context, true);
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(matchNode.getValueNode(), context, true);
            }
        };
        context.match2(value);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileMatch3(final Node node, final BodyCompiler context, final boolean expr) {
        final Match3Node matchNode = (Match3Node)node;
        this.compile(matchNode.getReceiverNode(), context, true);
        this.compile(matchNode.getValueNode(), context, true);
        context.match3();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileModule(final Node node, final BodyCompiler context, final boolean expr) {
        final ModuleNode moduleNode = (ModuleNode)node;
        final Node cpathNode = moduleNode.getCPath();
        final CompilerCallback bodyCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (moduleNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(moduleNode.getBodyNode(), context, true);
                }
                context.loadNil();
            }
        };
        final CompilerCallback pathCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (cpathNode instanceof Colon2Node) {
                    final Node leftNode = ((Colon2Node)cpathNode).getLeftNode();
                    if (leftNode != null) {
                        ASTCompiler.this.compile(leftNode, context, true);
                    }
                    else {
                        context.loadNil();
                    }
                }
                else if (cpathNode instanceof Colon3Node) {
                    context.loadObject();
                }
                else {
                    context.loadNil();
                }
            }
        };
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(moduleNode.getBodyNode());
        context.defineModule(moduleNode.getCPath().getName(), moduleNode.getScope(), pathCallback, bodyCallback, inspector);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileMultipleAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final MultipleAsgnNode multipleAsgnNode = (MultipleAsgnNode)node;
        if (expr) {
            this.compileUnoptimizedMultipleAsgn(multipleAsgnNode, context, expr);
        }
        else {
            this.compileOptimizedMultipleAsgn(multipleAsgnNode, context, expr);
        }
    }
    
    private void compileOptimizedMultipleAsgn(final MultipleAsgnNode multipleAsgnNode, final BodyCompiler context, final boolean expr) {
        if (multipleAsgnNode.getValueNode() instanceof ArrayNode) {
            if (multipleAsgnNode.getHeadNode() != null && multipleAsgnNode.getArgsNode() == null && multipleAsgnNode.getHeadNode().size() == ((ArrayNode)multipleAsgnNode.getValueNode()).size()) {
                boolean normalAssigns = true;
                for (final Node asgn : multipleAsgnNode.getHeadNode().childNodes()) {
                    if (asgn instanceof ListNode) {
                        normalAssigns = false;
                        break;
                    }
                }
                if (normalAssigns) {
                    final int size = multipleAsgnNode.getHeadNode().size();
                    if (size >= 2 && size <= 10) {
                        final ArrayNode values = (ArrayNode)multipleAsgnNode.getValueNode();
                        for (final Node value : values.childNodes()) {
                            this.compile(value, context, true);
                        }
                        context.reverseValues(size);
                        for (final Node asgn2 : multipleAsgnNode.getHeadNode().childNodes()) {
                            this.compileAssignment(asgn2, context, false);
                        }
                        return;
                    }
                }
            }
        }
        else if (multipleAsgnNode.getHeadNode() != null && multipleAsgnNode.getHeadNode().size() == 1 && multipleAsgnNode.getValueNode() instanceof ToAryNode && multipleAsgnNode.getArgsNode() != null) {
            this.compile(multipleAsgnNode.getValueNode().childNodes().get(0), context, true);
            if (multipleAsgnNode.getArgsNode() instanceof StarNode) {
                context.preMultiAssign(1, false);
                this.compileAssignment(multipleAsgnNode.getHeadNode().childNodes().get(0), context, false);
            }
            else {
                context.preMultiAssign(1, true);
                this.compileAssignment(multipleAsgnNode.getHeadNode().childNodes().get(0), context, false);
                this.compileAssignment(multipleAsgnNode.getArgsNode(), context, false);
            }
            return;
        }
        this.compileUnoptimizedMultipleAsgn(multipleAsgnNode, context, expr);
    }
    
    private void compileUnoptimizedMultipleAsgn(final MultipleAsgnNode multipleAsgnNode, final BodyCompiler context, final boolean expr) {
        this.compile(multipleAsgnNode.getValueNode(), context, true);
        this.compileMultipleAsgnAssignment(multipleAsgnNode, context, expr);
    }
    
    public void compileMultipleAsgnAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        final MultipleAsgnNode multipleAsgnNode = (MultipleAsgnNode)node;
        final ArrayCallback headAssignCallback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                final ListNode headNode = (ListNode)sourceArray;
                final Node assignNode = headNode.get(index);
                ASTCompiler.this.compileAssignment(assignNode, context, false);
            }
        };
        final CompilerCallback argsCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                final Node argsNode = multipleAsgnNode.getArgsNode();
                if (argsNode instanceof StarNode) {
                    context.consumeCurrentValue();
                }
                else {
                    ASTCompiler.this.compileAssignment(argsNode, context, false);
                }
            }
        };
        if (multipleAsgnNode.getHeadNode() == null) {
            if (multipleAsgnNode.getArgsNode() == null) {
                throw new NotCompilableException("Something's wrong, multiple assignment with no head or args at: " + multipleAsgnNode.getPosition());
            }
            if (!(multipleAsgnNode.getArgsNode() instanceof StarNode)) {
                context.ensureMultipleAssignableRubyArray(multipleAsgnNode.getHeadNode() != null);
                context.forEachInValueArray(0, 0, null, null, argsCallback);
            }
        }
        else {
            context.ensureMultipleAssignableRubyArray(multipleAsgnNode.getHeadNode() != null);
            if (multipleAsgnNode.getArgsNode() == null) {
                context.forEachInValueArray(0, multipleAsgnNode.getHeadNode().size(), multipleAsgnNode.getHeadNode(), headAssignCallback, null);
            }
            else {
                context.forEachInValueArray(0, multipleAsgnNode.getHeadNode().size(), multipleAsgnNode.getHeadNode(), headAssignCallback, argsCallback);
            }
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileNewline(final Node node, final BodyCompiler context, final boolean expr) {
        context.lineNumber(node.getPosition());
        context.setLinePosition(node.getPosition());
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            context.traceLine();
        }
        final NewlineNode newlineNode = (NewlineNode)node;
        this.compile(newlineNode.getNextNode(), context, expr);
    }
    
    public void compileNext(final Node node, final BodyCompiler context, final boolean expr) {
        final NextNode nextNode = (NextNode)node;
        final CompilerCallback valueCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (nextNode.getValueNode() != null) {
                    ASTCompiler.this.compile(nextNode.getValueNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        context.pollThreadEvents();
        context.issueNextEvent(valueCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileNthRef(final Node node, final BodyCompiler context, final boolean expr) {
        final NthRefNode nthRefNode = (NthRefNode)node;
        if (expr) {
            context.nthRef(nthRefNode.getMatchNumber());
        }
    }
    
    public void compileNil(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.loadNil();
        }
    }
    
    public void compileNot(final Node node, final BodyCompiler context, final boolean expr) {
        final NotNode notNode = (NotNode)node;
        this.compile(notNode.getConditionNode(), context, true);
        context.negateCurrentValue();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpAsgnAnd(final Node node, final BodyCompiler context, final boolean expr) {
        final BinaryOperatorNode andNode = (BinaryOperatorNode)node;
        this.compile(andNode.getFirstNode(), context, true);
        final BranchCallback longCallback = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                ASTCompiler.this.compile(andNode.getSecondNode(), context, true);
            }
        };
        context.performLogicalAnd(longCallback);
        context.pollThreadEvents();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpAsgnOr(final Node node, final BodyCompiler context, final boolean expr) {
        final OpAsgnOrNode orNode = (OpAsgnOrNode)node;
        if (this.needsDefinitionCheck(orNode.getFirstNode())) {
            this.compileGetDefinitionBase(orNode.getFirstNode(), context);
            context.isNull(new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(orNode.getSecondNode(), context, true);
                }
            }, new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(orNode.getFirstNode(), context, true);
                    context.duplicateCurrentValue();
                    context.performBooleanBranch(new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                        }
                    }, new BranchCallback() {
                        public void branch(final BodyCompiler context) {
                            context.consumeCurrentValue();
                            ASTCompiler.this.compile(orNode.getSecondNode(), context, true);
                        }
                    });
                }
            });
        }
        else {
            this.compile(orNode.getFirstNode(), context, true);
            context.duplicateCurrentValue();
            context.performBooleanBranch(new BranchCallback() {
                public void branch(final BodyCompiler context) {
                }
            }, new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    context.consumeCurrentValue();
                    ASTCompiler.this.compile(orNode.getSecondNode(), context, true);
                }
            });
        }
        context.pollThreadEvents();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    private boolean needsDefinitionCheck(final Node node) {
        switch (node.getNodeType()) {
            case CLASSVARASGNNODE:
            case CLASSVARDECLNODE:
            case CONSTDECLNODE:
            case DASGNNODE:
            case DVARNODE:
            case FALSENODE:
            case GLOBALASGNNODE:
            case LOCALASGNNODE:
            case LOCALVARNODE:
            case MATCH2NODE:
            case MATCH3NODE:
            case MULTIPLEASGNNODE:
            case NILNODE:
            case OPASGNNODE:
            case OPELEMENTASGNNODE:
            case SELFNODE:
            case TRUENODE: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    public void compileOpAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final OpAsgnNode opAsgnNode = (OpAsgnNode)node;
        if (opAsgnNode.getOperatorName().equals("||")) {
            this.compileOpAsgnWithOr(opAsgnNode, context, true);
        }
        else if (opAsgnNode.getOperatorName().equals("&&")) {
            this.compileOpAsgnWithAnd(opAsgnNode, context, true);
        }
        else {
            this.compileOpAsgnWithMethod(opAsgnNode, context, true);
        }
        context.pollThreadEvents();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpAsgnWithOr(final Node node, final BodyCompiler context, final boolean expr) {
        final OpAsgnNode opAsgnNode = (OpAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(opAsgnNode.getValueNode());
        context.getInvocationCompiler().invokeOpAsgnWithOr(opAsgnNode.getVariableName(), opAsgnNode.getVariableNameAsgn(), receiverCallback, argsCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpAsgnWithAnd(final Node node, final BodyCompiler context, final boolean expr) {
        final OpAsgnNode opAsgnNode = (OpAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(opAsgnNode.getValueNode());
        context.getInvocationCompiler().invokeOpAsgnWithAnd(opAsgnNode.getVariableName(), opAsgnNode.getVariableNameAsgn(), receiverCallback, argsCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpAsgnWithMethod(final Node node, final BodyCompiler context, final boolean expr) {
        final OpAsgnNode opAsgnNode = (OpAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = new ArgumentsCallback() {
            public int getArity() {
                return 1;
            }
            
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opAsgnNode.getValueNode(), context, true);
            }
        };
        context.getInvocationCompiler().invokeOpAsgnWithMethod(opAsgnNode.getOperatorName(), opAsgnNode.getVariableName(), opAsgnNode.getVariableNameAsgn(), receiverCallback, argsCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpElementAsgn(final Node node, final BodyCompiler context, final boolean expr) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        if (opElementAsgnNode.getOperatorName() == "||") {
            this.compileOpElementAsgnWithOr(node, context, expr);
        }
        else if (opElementAsgnNode.getOperatorName() == "&&") {
            this.compileOpElementAsgnWithAnd(node, context, expr);
        }
        else {
            this.compileOpElementAsgnWithMethod(node, context, expr);
        }
    }
    
    public void compileOpElementAsgnWithOr(final Node node, final BodyCompiler context, final boolean expr) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = new OpElementAsgnArgumentsCallback(opElementAsgnNode.getArgsNode());
        final CompilerCallback valueCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getValueNode(), context, true);
            }
        };
        context.getInvocationCompiler().opElementAsgnWithOr(receiverCallback, argsCallback, valueCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpElementAsgnWithAnd(final Node node, final BodyCompiler context, final boolean expr) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = new OpElementAsgnArgumentsCallback(opElementAsgnNode.getArgsNode());
        final CompilerCallback valueCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getValueNode(), context, true);
            }
        };
        context.getInvocationCompiler().opElementAsgnWithAnd(receiverCallback, argsCallback, valueCallback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOpElementAsgnWithMethod(final Node node, final BodyCompiler context, final boolean expr) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final CompilerCallback receiverCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getReceiverNode(), context, true);
            }
        };
        final ArgumentsCallback argsCallback = this.getArgsCallback(opElementAsgnNode.getArgsNode());
        final CompilerCallback valueCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(opElementAsgnNode.getValueNode(), context, true);
            }
        };
        context.getInvocationCompiler().opElementAsgnWithMethod(receiverCallback, argsCallback, valueCallback, opElementAsgnNode.getOperatorName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileOr(final Node node, final BodyCompiler context, final boolean expr) {
        final OrNode orNode = (OrNode)node;
        if (orNode.getFirstNode().getNodeType().alwaysTrue()) {
            this.compile(orNode.getFirstNode(), context, expr);
        }
        else if (orNode.getFirstNode().getNodeType().alwaysFalse()) {
            this.compile(orNode.getFirstNode(), context, false);
            this.compile(orNode.getSecondNode(), context, expr);
        }
        else {
            this.compile(orNode.getFirstNode(), context, true);
            final BranchCallback longCallback = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(orNode.getSecondNode(), context, true);
                }
            };
            context.performLogicalOr(longCallback);
            if (!expr) {
                context.consumeCurrentValue();
            }
        }
    }
    
    public void compilePostExe(final Node node, final BodyCompiler context, final boolean expr) {
        final PostExeNode postExeNode = (PostExeNode)node;
        final CompilerCallback closureBody = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (postExeNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(postExeNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        context.createNewEndBlock(closureBody);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compilePreExe(final Node node, final BodyCompiler context, final boolean expr) {
        final PreExeNode preExeNode = (PreExeNode)node;
        final CompilerCallback closureBody = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (preExeNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(preExeNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        context.runBeginBlock(preExeNode.getScope(), closureBody);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileRedo(final Node node, final BodyCompiler context, final boolean expr) {
        context.issueRedoEvent();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileRegexp(final Node node, final BodyCompiler context, final boolean expr) {
        final RegexpNode reNode = (RegexpNode)node;
        if (expr) {
            context.createNewRegexp(reNode.getValue(), reNode.getOptions().toEmbeddedOptions());
        }
    }
    
    public void compileRescue(final Node node, final BodyCompiler context, final boolean expr) {
        this.compileRescueInternal(node, context, false);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    private void compileRescueInternal(final Node node, final BodyCompiler context, final boolean light) {
        final RescueNode rescueNode = (RescueNode)node;
        final BranchCallback body = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                if (rescueNode.getBodyNode() != null) {
                    ASTCompiler.this.compile(rescueNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        BranchCallback elseBody = null;
        if (rescueNode.getElseNode() != null) {
            elseBody = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    context.consumeCurrentValue();
                    ASTCompiler.this.compile(rescueNode.getElseNode(), context, true);
                }
            };
        }
        final BranchCallback rubyHandler = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                ASTCompiler.this.compileRescueBodyInternal(rescueNode.getRescueNode(), context, light);
            }
        };
        final ASTInspector rescueInspector = new ASTInspector();
        rescueInspector.inspect(rescueNode.getRescueNode());
        if (light) {
            context.performRescueLight(body, rubyHandler, elseBody, rescueInspector.getFlag(1048576));
        }
        else {
            context.performRescue(body, rubyHandler, elseBody, rescueInspector.getFlag(1048576));
        }
    }
    
    private void compileRescueBodyInternal(final Node node, final BodyCompiler context, final boolean light) {
        final RescueBodyNode rescueBodyNode = (RescueBodyNode)node;
        context.loadException();
        final Node exceptionList = rescueBodyNode.getExceptionNodes();
        ArgumentsCallback rescueArgs = this.getArgsCallback(exceptionList);
        if (rescueArgs == null) {
            rescueArgs = new ArgumentsCallback() {
                public int getArity() {
                    return 1;
                }
                
                public void call(final BodyCompiler context) {
                    context.loadStandardError();
                }
            };
        }
        context.checkIsExceptionHandled(rescueArgs);
        final BranchCallback trueBranch = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                Node realBody = rescueBodyNode.getBodyNode();
                if (realBody instanceof NewlineNode) {
                    context.setLinePosition(realBody.getPosition());
                    while (realBody instanceof NewlineNode) {
                        realBody = ((NewlineNode)realBody).getNextNode();
                    }
                }
                if (realBody.getNodeType().isImmediate()) {
                    ASTCompiler.this.compile(realBody, context, true);
                    context.clearErrorInfo();
                }
                else {
                    context.storeExceptionInErrorInfo();
                    if (light) {
                        ASTCompiler.this.compile(rescueBodyNode.getBodyNode(), context, true);
                    }
                    else {
                        final BodyCompiler nestedBody = context.outline("rescue_line_" + rescueBodyNode.getPosition().getStartLine());
                        ASTCompiler.this.compile(rescueBodyNode.getBodyNode(), nestedBody, true);
                        nestedBody.endBody();
                    }
                    context.clearErrorInfo();
                }
            }
        };
        final BranchCallback falseBranch = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                if (rescueBodyNode.getOptRescueNode() != null) {
                    ASTCompiler.this.compileRescueBodyInternal(rescueBodyNode.getOptRescueNode(), context, light);
                }
                else {
                    context.rethrowException();
                }
            }
        };
        context.performBooleanBranch(trueBranch, falseBranch);
    }
    
    public void compileRetry(final Node node, final BodyCompiler context, final boolean expr) {
        context.pollThreadEvents();
        context.issueRetryEvent();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileReturn(final Node node, final BodyCompiler context, final boolean expr) {
        final ReturnNode returnNode = (ReturnNode)node;
        if (returnNode.getValueNode() != null) {
            this.compile(returnNode.getValueNode(), context, true);
        }
        else {
            context.loadNil();
        }
        context.performReturn();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileRoot(final Node node, final ScriptCompiler context, final ASTInspector inspector) {
        this.compileRoot(node, context, inspector, true, true);
    }
    
    public void compileRoot(final Node node, final ScriptCompiler context, final ASTInspector inspector, final boolean load, final boolean main) {
        final RootNode rootNode = (RootNode)node;
        final StaticScope staticScope = rootNode.getStaticScope();
        context.startScript(staticScope);
        staticScope.setRestArg(-2);
        BodyCompiler methodCompiler = context.startFileMethod(null, staticScope, inspector);
        final Node nextNode = rootNode.getBodyNode();
        if (nextNode != null) {
            if (nextNode.getNodeType() == NodeType.BLOCKNODE) {
                final BlockNode blockNode = (BlockNode)nextNode;
                for (int i = 0; i < blockNode.size(); ++i) {
                    if ((i + 1) % RubyInstanceConfig.CHAINED_COMPILE_LINE_COUNT == 0) {
                        methodCompiler = methodCompiler.chainToMethod("__file__from_line_" + (i + 1));
                    }
                    this.compile(blockNode.get(i), methodCompiler, i + 1 >= blockNode.size());
                }
            }
            else {
                this.compile(nextNode, methodCompiler, true);
            }
        }
        else {
            methodCompiler.loadNil();
        }
        methodCompiler.endBody();
        context.endScript(load, main);
    }
    
    public void compileSelf(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.retrieveSelf();
        }
    }
    
    public void compileSplat(final Node node, final BodyCompiler context, final boolean expr) {
        final SplatNode splatNode = (SplatNode)node;
        this.compile(splatNode.getValue(), context, true);
        this.splatCurrentValue(context);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    protected void splatCurrentValue(final BodyCompiler context) {
        context.splatCurrentValue("splatValue");
    }
    
    public void compileStr(final Node node, final BodyCompiler context, final boolean expr) {
        final StrNode strNode = (StrNode)node;
        if (expr) {
            if (strNode instanceof FileNode) {
                context.loadFilename();
            }
            else {
                context.createNewString(strNode.getValue(), strNode.getCodeRange());
            }
        }
    }
    
    public void compileSuper(final Node node, final BodyCompiler context, final boolean expr) {
        final SuperNode superNode = (SuperNode)node;
        final ArgumentsCallback argsCallback = this.getArgsCallback(superNode.getArgsNode());
        final CompilerCallback closureArg = this.getBlock(superNode.getIterNode());
        context.getInvocationCompiler().invokeDynamic(null, null, argsCallback, CallType.SUPER, closureArg, superNode.getIterNode() instanceof IterNode);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileSValue(final Node node, final BodyCompiler context, final boolean expr) {
        final SValueNode svalueNode = (SValueNode)node;
        this.compile(svalueNode.getValue(), context, true);
        context.singlifySplattedValue();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileSymbol(final Node node, final BodyCompiler context, final boolean expr) {
        context.createNewSymbol(((SymbolNode)node).getName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileToAry(final Node node, final BodyCompiler context, final boolean expr) {
        final ToAryNode toAryNode = (ToAryNode)node;
        this.compile(toAryNode.getValue(), context, true);
        context.aryToAry();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileTrue(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.loadTrue();
            context.pollThreadEvents();
        }
    }
    
    public void compileUndef(final UndefNode undef, final BodyCompiler context, final boolean expr) {
        final CompilerCallback nameArg = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler.this.compile(undef.getName(), context, true);
            }
        };
        context.undefMethod(nameArg);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileUntil(final Node node, final BodyCompiler context, final boolean expr) {
        final UntilNode untilNode = (UntilNode)node;
        if (untilNode.getConditionNode().getNodeType().alwaysTrue() && untilNode.evaluateAtStart()) {
            this.compile(untilNode.getConditionNode(), context, false);
            if (expr) {
                context.loadNil();
            }
        }
        else {
            final BranchCallback condition = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(untilNode.getConditionNode(), context, true);
                    context.negateCurrentValue();
                }
            };
            final BranchCallback body = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    if (untilNode.getBodyNode() != null) {
                        ASTCompiler.this.compile(untilNode.getBodyNode(), context, true);
                    }
                }
            };
            if (untilNode.containsNonlocalFlow) {
                context.performBooleanLoopSafe(condition, body, untilNode.evaluateAtStart());
            }
            else {
                context.performBooleanLoopLight(condition, body, untilNode.evaluateAtStart());
            }
            context.pollThreadEvents();
            if (!expr) {
                context.consumeCurrentValue();
            }
        }
    }
    
    public void compileVAlias(final Node node, final BodyCompiler context, final boolean expr) {
        final VAliasNode valiasNode = (VAliasNode)node;
        context.aliasGlobal(valiasNode.getNewName(), valiasNode.getOldName());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileVCall(final Node node, final BodyCompiler context, final boolean expr) {
        final VCallNode vcallNode = (VCallNode)node;
        if (RubyInstanceConfig.DYNOPT_COMPILE_ENABLED && vcallNode.callAdapter instanceof CachingCallSite) {
            final CachingCallSite cacheSite = (CachingCallSite)vcallNode.callAdapter;
            if (cacheSite.isOptimizable()) {
                final CacheEntry entry = cacheSite.getCache();
                if (this.compileRecursiveCall(vcallNode.getName(), entry.token, CallType.VARIABLE, false, entry.method, context, null, null, expr)) {
                    return;
                }
                if (this.compileTrivialCall(vcallNode.getName(), entry.method, entry.token, context, expr)) {
                    return;
                }
            }
        }
        context.getInvocationCompiler().invokeDynamic(vcallNode.getName(), null, null, CallType.VARIABLE, null, false);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileWhile(final Node node, final BodyCompiler context, final boolean expr) {
        final WhileNode whileNode = (WhileNode)node;
        if (whileNode.getConditionNode().getNodeType().alwaysFalse() && whileNode.evaluateAtStart()) {
            if (expr) {
                context.loadNil();
            }
        }
        else {
            final BranchCallback condition = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    ASTCompiler.this.compile(whileNode.getConditionNode(), context, true);
                }
            };
            final BranchCallback body = new BranchCallback() {
                public void branch(final BodyCompiler context) {
                    if (whileNode.getBodyNode() != null) {
                        ASTCompiler.this.compile(whileNode.getBodyNode(), context, true);
                    }
                }
            };
            if (whileNode.containsNonlocalFlow) {
                context.performBooleanLoopSafe(condition, body, whileNode.evaluateAtStart());
            }
            else {
                context.performBooleanLoopLight(condition, body, whileNode.evaluateAtStart());
            }
            context.pollThreadEvents();
            if (!expr) {
                context.consumeCurrentValue();
            }
        }
    }
    
    public void compileXStr(final Node node, final BodyCompiler context, final boolean expr) {
        final XStrNode xstrNode = (XStrNode)node;
        final ArgumentsCallback argsCallback = new ArgumentsCallback() {
            public int getArity() {
                return 1;
            }
            
            public void call(final BodyCompiler context) {
                context.createNewString(xstrNode.getValue(), 0);
            }
        };
        context.getInvocationCompiler().invokeDynamic("`", null, argsCallback, CallType.FUNCTIONAL, null, false);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileYield(final Node node, final BodyCompiler context, final boolean expr) {
        final YieldNode yieldNode = (YieldNode)node;
        final ArgumentsCallback argsCallback = this.getArgsCallback(yieldNode.getArgsNode());
        if (argsCallback == null || argsCallback.getArity() == 0) {
            context.getInvocationCompiler().yieldSpecific(argsCallback);
        }
        else if ((argsCallback.getArity() == 1 || argsCallback.getArity() == 2 || argsCallback.getArity() == 3) && yieldNode.getExpandArguments()) {
            context.getInvocationCompiler().yieldSpecific(argsCallback);
        }
        else {
            CompilerCallback argsCallback2 = null;
            if (yieldNode.getArgsNode() != null) {
                argsCallback2 = new CompilerCallback() {
                    public void call(final BodyCompiler context) {
                        ASTCompiler.this.compile(yieldNode.getArgsNode(), context, true);
                    }
                };
            }
            context.getInvocationCompiler().yield(argsCallback2, yieldNode.getExpandArguments());
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileZArray(final Node node, final BodyCompiler context, final boolean expr) {
        if (expr) {
            context.createEmptyArray();
        }
    }
    
    public void compileZSuper(final Node node, final BodyCompiler context, final boolean expr) {
        final ZSuperNode zsuperNode = (ZSuperNode)node;
        final CompilerCallback closure = this.getBlock(zsuperNode.getIterNode());
        context.callZSuper(closure);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArgsCatArguments(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsCatNode argsCatNode = (ArgsCatNode)node;
        this.compileArguments(argsCatNode.getFirstNode(), context);
        context.createNewArray(true);
        this.compile(argsCatNode.getSecondNode(), context, true);
        this.splatCurrentValue(context);
        context.concatArrays();
        context.convertToJavaArray();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArgsPushArguments(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsPushNode argsPushNode = (ArgsPushNode)node;
        this.compile(argsPushNode.getFirstNode(), context, true);
        this.compile(argsPushNode.getSecondNode(), context, true);
        context.appendToArray();
        context.convertToJavaArray();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArrayArguments(final Node node, final BodyCompiler context, final boolean expr) {
        final ArrayNode arrayNode = (ArrayNode)node;
        final ArrayCallback callback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                final Node node = (Node)((Object[])sourceArray)[index];
                ASTCompiler.this.compile(node, context, true);
            }
        };
        context.setLinePosition(arrayNode.getPosition());
        context.createObjectArray(arrayNode.childNodes().toArray(), callback);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileSplatArguments(final Node node, final BodyCompiler context, final boolean expr) {
        final SplatNode splatNode = (SplatNode)node;
        this.compile(splatNode.getValue(), context, true);
        this.splatCurrentValue(context);
        context.convertToJavaArray();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    static {
        Intrinsics = new HashMap<Class, Map<Class, Map<String, String>>>();
        final Map<Class, Map<String, String>> fixnumIntrinsics = new HashMap<Class, Map<String, String>>();
        ASTCompiler.Intrinsics.put(RubyFixnum.class, fixnumIntrinsics);
        final Map<String, String> fixnumLongIntrinsics = new HashMap<String, String>();
        fixnumIntrinsics.put(FixnumNode.class, fixnumLongIntrinsics);
        fixnumLongIntrinsics.put("+", "op_plus");
        fixnumLongIntrinsics.put("-", "op_minus");
        fixnumLongIntrinsics.put("/", "op_div");
        fixnumLongIntrinsics.put("*", "op_plus");
        fixnumLongIntrinsics.put("**", "op_pow");
        fixnumLongIntrinsics.put("<", "op_lt");
        fixnumLongIntrinsics.put("<=", "op_le");
        fixnumLongIntrinsics.put(">", "op_gt");
        fixnumLongIntrinsics.put(">=", "op_ge");
        fixnumLongIntrinsics.put("==", "op_equal");
        fixnumLongIntrinsics.put("<=>", "op_cmp");
        final Map<Class, Map<String, String>> floatIntrinsics = new HashMap<Class, Map<String, String>>();
        ASTCompiler.Intrinsics.put(RubyFloat.class, floatIntrinsics);
        final Map<String, String> floatDoubleIntrinsics = new HashMap<String, String>();
        floatIntrinsics.put(FloatNode.class, floatDoubleIntrinsics);
        floatDoubleIntrinsics.put("+", "op_plus");
        floatDoubleIntrinsics.put("-", "op_minus");
        floatDoubleIntrinsics.put("/", "op_fdiv");
        floatDoubleIntrinsics.put("*", "op_plus");
        floatDoubleIntrinsics.put("**", "op_pow");
        floatDoubleIntrinsics.put("<", "op_lt");
        floatDoubleIntrinsics.put("<=", "op_le");
        floatDoubleIntrinsics.put(">", "op_gt");
        floatDoubleIntrinsics.put(">=", "op_ge");
        floatDoubleIntrinsics.put("==", "op_equal");
        floatDoubleIntrinsics.put("<=>", "op_cmp");
    }
    
    public class VariableArityArguments implements ArgumentsCallback
    {
        private Node node;
        
        public VariableArityArguments(final Node node) {
            this.node = node;
        }
        
        public int getArity() {
            return -1;
        }
        
        public void call(final BodyCompiler context) {
            ASTCompiler.this.compileArguments(this.node, context);
        }
    }
    
    public class SpecificArityArguments implements ArgumentsCallback
    {
        private int arity;
        private Node node;
        
        public SpecificArityArguments(final Node node) {
            if (node.getNodeType() == NodeType.ARRAYNODE && ((ArrayNode)node).isLightweight()) {
                this.arity = ((ArrayNode)node).size();
            }
            else {
                this.arity = 1;
            }
            this.node = node;
        }
        
        public int getArity() {
            return this.arity;
        }
        
        public void call(final BodyCompiler context) {
            if (this.node.getNodeType() == NodeType.ARRAYNODE) {
                final ArrayNode arrayNode = (ArrayNode)this.node;
                if (arrayNode.isLightweight()) {
                    for (final Node n : arrayNode.childNodes()) {
                        ASTCompiler.this.compile(n, context, true);
                    }
                }
                else {
                    ASTCompiler.this.compile(arrayNode, context, true);
                }
            }
            else {
                ASTCompiler.this.compile(this.node, context, true);
            }
        }
    }
    
    private class OpElementAsgnArgumentsCallback implements ArgumentsCallback
    {
        private Node node;
        
        public OpElementAsgnArgumentsCallback(final Node node) {
            this.node = node;
        }
        
        public int getArity() {
            switch (this.node.getNodeType()) {
                case ARGSCATNODE:
                case ARGSPUSHNODE:
                case SPLATNODE: {
                    return -1;
                }
                case ARRAYNODE: {
                    final ArrayNode arrayNode = (ArrayNode)this.node;
                    if (arrayNode.size() == 0) {
                        return 0;
                    }
                    if (arrayNode.size() > 3) {
                        return -1;
                    }
                    return ((ArrayNode)this.node).size();
                }
                default: {
                    return 1;
                }
            }
        }
        
        public void call(final BodyCompiler context) {
            if (this.getArity() == 1) {
                ASTCompiler.this.compile(((ArrayNode)this.node).get(0), context, true);
            }
            else {
                ASTCompiler.this.compileArguments(this.node, context);
            }
        }
    }
}

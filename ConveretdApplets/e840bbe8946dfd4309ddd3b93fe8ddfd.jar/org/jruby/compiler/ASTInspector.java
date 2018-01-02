// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.ast.NodeType;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.RubyModule;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.jruby.ast.ZSuperNode;
import org.jruby.ast.YieldNode;
import org.jruby.ast.WhileNode;
import org.jruby.ast.WhenNode;
import org.jruby.ast.UntilNode;
import org.jruby.ast.ToAryNode;
import org.jruby.ast.SValueNode;
import org.jruby.ast.SuperNode;
import org.jruby.ast.SplatNode;
import org.jruby.ast.SClassNode;
import org.jruby.ast.ReturnNode;
import org.jruby.ast.RescueNode;
import org.jruby.ast.RescueBodyNode;
import org.jruby.ast.BlockNode;
import org.jruby.ast.RootNode;
import org.jruby.ast.PreExeNode;
import org.jruby.ast.PostExeNode;
import org.jruby.ast.OrNode;
import org.jruby.ast.OptArgNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.ast.OpAsgnOrNode;
import org.jruby.ast.OpAsgnNode;
import org.jruby.ast.OpAsgnAndNode;
import org.jruby.ast.NotNode;
import org.jruby.ast.NextNode;
import org.jruby.ast.NewlineNode;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.ast.ModuleNode;
import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2Node;
import org.jruby.ast.MatchNode;
import org.jruby.ast.TrueNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.IScopingNode;
import org.jruby.ast.IfNode;
import org.jruby.ast.HashNode;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.ForNode;
import org.jruby.ast.FlipNode;
import org.jruby.ast.EvStrNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.DefinedNode;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.AssignableNode;
import org.jruby.ast.ClassNode;
import org.jruby.ast.CaseNode;
import org.jruby.ast.types.INameNode;
import org.jruby.ast.BlockAcceptingNode;
import org.jruby.ast.IArgumentNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.BreakNode;
import org.jruby.ast.BlockPassNode;
import org.jruby.ast.BinaryOperatorNode;
import org.jruby.ast.BeginNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.ListNode;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ArgsCatNode;
import org.jruby.ast.AndNode;
import org.jruby.ast.Node;
import org.jruby.RubyInstanceConfig;
import org.jruby.internal.runtime.methods.CallConfiguration;
import java.util.Collection;
import java.util.Arrays;
import java.util.Set;

public class ASTInspector
{
    private final boolean dump;
    private final String name;
    private static final boolean DEBUG = false;
    public static final int BLOCK_ARG = 1;
    public static final int CLOSURE = 2;
    public static final int CLASS = 4;
    public static final int METHOD = 8;
    public static final int EVAL = 16;
    public static final int FRAME_AWARE = 32;
    public static final int FRAME_SELF = 64;
    public static final int FRAME_VISIBILITY = 128;
    public static final int FRAME_BLOCK = 256;
    public static final int FRAME_NAME = 512;
    public static final int BACKREF = 1024;
    public static final int LASTLINE = 2048;
    public static final int FRAME_CLASS = 4096;
    public static final int OPT_ARGS = 8192;
    public static final int REST_ARG = 16384;
    public static final int SCOPE_AWARE = 32768;
    public static final int ZSUPER = 65536;
    public static final int CONSTANT = 131072;
    public static final int CLASS_VAR = 262144;
    public static final int SUPER = 524288;
    public static final int RETRY = 1048576;
    private static final String[] MODIFIER_NAMES;
    private int flags;
    private boolean noFrame;
    public static final Set<String> FRAME_AWARE_METHODS;
    public static final Set<String> SCOPE_AWARE_METHODS;
    public static final Set<String> PRAGMAS;
    public static final boolean ENABLED;
    
    public ASTInspector() {
        this.dump = false;
        this.name = null;
    }
    
    public ASTInspector(final String name, final boolean dump) {
        this.name = name;
        this.dump = dump;
    }
    
    public static void addFrameAwareMethods(final String... methods) {
        ASTInspector.FRAME_AWARE_METHODS.addAll(Arrays.asList(methods));
    }
    
    public static void addScopeAwareMethods(final String... methods) {
        ASTInspector.SCOPE_AWARE_METHODS.addAll(Arrays.asList(methods));
    }
    
    public void disable() {
        if (this.dump) {
            System.out.println("[ASTInspector] " + this.name + " DISABLED");
        }
        this.flags = -1;
    }
    
    public CallConfiguration getCallConfig() {
        if (!this.noFrame() && (this.hasFrameAwareMethods() || this.hasClosure() || RubyInstanceConfig.FULL_TRACE_ENABLED)) {
            if (this.hasClosure() || this.hasScopeAwareMethods()) {
                return CallConfiguration.FrameFullScopeFull;
            }
            if (this.hasConstant() || this.hasMethod() || this.hasClass() || this.hasClassVar()) {
                return CallConfiguration.FrameFullScopeDummy;
            }
            return CallConfiguration.FrameFullScopeNone;
        }
        else {
            if (this.hasClosure() || this.hasScopeAwareMethods()) {
                return CallConfiguration.FrameNoneScopeFull;
            }
            if (this.hasConstant() || this.hasMethod() || this.hasClass() || this.hasClassVar()) {
                return CallConfiguration.FrameNoneScopeDummy;
            }
            return CallConfiguration.FrameNoneScopeNone;
        }
    }
    
    public ASTInspector subInspect(final Node... nodes) {
        final ASTInspector newInspector = new ASTInspector(this.name, this.dump);
        for (final Node node : nodes) {
            newInspector.inspect(node);
        }
        return newInspector;
    }
    
    public boolean getFlag(final int modifier) {
        return (this.flags & modifier) != 0x0;
    }
    
    public void setFlag(final int modifier) {
        if (this.dump) {
            System.out.println("[ASTInspector] " + this.name + "\n\tset flag " + Integer.toHexString(modifier));
        }
        this.flags |= modifier;
    }
    
    public void setFlag(final Node node, final int modifier) {
        if (this.dump) {
            System.out.println("[ASTInspector] " + this.name + "\n\tset flag " + Integer.toHexString(modifier) + "\n\tbecause of " + node.getNodeType() + " at " + node.getPosition());
        }
        this.flags |= modifier;
    }
    
    public void integrate(final ASTInspector other) {
        this.flags |= other.flags;
    }
    
    public void inspect(final Node node) {
        if (!ASTInspector.ENABLED || RubyInstanceConfig.FULL_TRACE_ENABLED) {
            this.disable();
            return;
        }
        if (node == null) {
            return;
        }
        Label_2973: {
            switch (node.getNodeType()) {
                case ALIASNODE: {
                    this.setFlag(node, 8);
                    break;
                }
                case ANDNODE: {
                    final AndNode andNode = (AndNode)node;
                    this.inspect(andNode.getFirstNode());
                    this.inspect(andNode.getSecondNode());
                    break;
                }
                case ARGSCATNODE: {
                    final ArgsCatNode argsCatNode = (ArgsCatNode)node;
                    this.inspect(argsCatNode.getFirstNode());
                    this.inspect(argsCatNode.getSecondNode());
                    break;
                }
                case ARGSPUSHNODE: {
                    final ArgsPushNode argsPushNode = (ArgsPushNode)node;
                    this.inspect(argsPushNode.getFirstNode());
                    this.inspect(argsPushNode.getSecondNode());
                    break;
                }
                case ARGUMENTNODE: {
                    break;
                }
                case ARRAYNODE:
                case BLOCKNODE:
                case DREGEXPNODE:
                case DSTRNODE:
                case DSYMBOLNODE:
                case DXSTRNODE:
                case LISTNODE: {
                    final ListNode listNode = (ListNode)node;
                    for (int i = 0; i < listNode.size(); ++i) {
                        this.inspect(listNode.get(i));
                    }
                    break;
                }
                case ARGSNODE: {
                    final ArgsNode argsNode = (ArgsNode)node;
                    if (argsNode.getBlock() != null) {
                        this.setFlag(node, 1);
                    }
                    if (argsNode.getOptArgs() != null) {
                        this.setFlag(node, 8192);
                        this.inspect(argsNode.getOptArgs());
                    }
                    if (argsNode.getRestArg() == -2 || argsNode.getRestArg() >= 0) {
                        this.setFlag(node, 16384);
                        break;
                    }
                    break;
                }
                case ATTRASSIGNNODE: {
                    final AttrAssignNode attrAssignNode = (AttrAssignNode)node;
                    this.setFlag(node, 64);
                    this.inspect(attrAssignNode.getArgsNode());
                    this.inspect(attrAssignNode.getReceiverNode());
                    break;
                }
                case BACKREFNODE: {
                    this.setFlag(node, 1024);
                    break;
                }
                case BEGINNODE: {
                    this.inspect(((BeginNode)node).getBodyNode());
                    break;
                }
                case BIGNUMNODE: {
                    break;
                }
                case BINARYOPERATORNODE: {
                    final BinaryOperatorNode binaryOperatorNode = (BinaryOperatorNode)node;
                    this.inspect(binaryOperatorNode.getFirstNode());
                    this.inspect(binaryOperatorNode.getSecondNode());
                    break;
                }
                case BLOCKARGNODE: {
                    break;
                }
                case BLOCKPASSNODE: {
                    final BlockPassNode blockPassNode = (BlockPassNode)node;
                    this.inspect(blockPassNode.getArgsNode());
                    this.inspect(blockPassNode.getBodyNode());
                    break;
                }
                case BREAKNODE: {
                    this.inspect(((BreakNode)node).getValueNode());
                    break;
                }
                case CALLNODE: {
                    final CallNode callNode = (CallNode)node;
                    this.inspect(callNode.getReceiverNode());
                    if (callNode.getName() == "new" && callNode.getReceiverNode() instanceof ConstNode && ((ConstNode)callNode.getReceiverNode()).getName() == "Proc") {
                        this.setFlag(node, 256);
                    }
                }
                case FCALLNODE: {
                    this.inspect(((IArgumentNode)node).getArgsNode());
                    this.inspect(((BlockAcceptingNode)node).getIterNode());
                }
                case VCALLNODE: {
                    final INameNode nameNode = (INameNode)node;
                    if (ASTInspector.FRAME_AWARE_METHODS.contains(nameNode.getName())) {
                        this.setFlag(node, 32);
                        if (nameNode.getName().indexOf("eval") != -1) {
                            this.setFlag(node, 16);
                        }
                    }
                    if (ASTInspector.SCOPE_AWARE_METHODS.contains(nameNode.getName())) {
                        this.setFlag(node, 32768);
                        break;
                    }
                    break;
                }
                case CASENODE: {
                    final CaseNode caseNode = (CaseNode)node;
                    this.inspect(caseNode.getCaseNode());
                    for (final Node when : caseNode.getCases().childNodes()) {
                        this.inspect(when);
                    }
                    this.inspect(caseNode.getElseNode());
                    break;
                }
                case CLASSNODE: {
                    this.setFlag(node, 4);
                    final ClassNode classNode = (ClassNode)node;
                    this.inspect(classNode.getCPath());
                    this.inspect(classNode.getSuperNode());
                    break;
                }
                case CLASSVARNODE: {
                    this.setFlag(node, 262144);
                    break;
                }
                case CONSTDECLNODE: {
                    this.inspect(((AssignableNode)node).getValueNode());
                    this.setFlag(node, 131072);
                    break;
                }
                case CLASSVARASGNNODE: {
                    this.inspect(((AssignableNode)node).getValueNode());
                    this.setFlag(node, 262144);
                    break;
                }
                case CLASSVARDECLNODE: {
                    this.inspect(((AssignableNode)node).getValueNode());
                    this.setFlag(node, 262144);
                    break;
                }
                case COLON2NODE: {
                    this.inspect(((Colon2Node)node).getLeftNode());
                    break;
                }
                case COLON3NODE: {
                    break;
                }
                case CONSTNODE: {
                    this.setFlag(node, 131072);
                    break;
                }
                case DEFNNODE:
                case DEFSNODE: {
                    this.setFlag(node, 8);
                    this.setFlag(node, 128);
                    break;
                }
                case DEFINEDNODE: {
                    switch (((DefinedNode)node).getExpressionNode().getNodeType()) {
                        case CLASSVARASGNNODE:
                        case CLASSVARDECLNODE:
                        case CONSTDECLNODE:
                        case DASGNNODE:
                        case GLOBALASGNNODE:
                        case LOCALASGNNODE:
                        case MULTIPLEASGNNODE:
                        case OPASGNNODE:
                        case OPELEMENTASGNNODE:
                        case DVARNODE:
                        case FALSENODE:
                        case TRUENODE:
                        case LOCALVARNODE:
                        case INSTVARNODE:
                        case BACKREFNODE:
                        case SELFNODE:
                        case VCALLNODE:
                        case YIELDNODE:
                        case GLOBALVARNODE:
                        case CONSTNODE:
                        case FCALLNODE:
                        case CLASSVARNODE: {
                            this.inspect(((DefinedNode)node).getExpressionNode());
                            break Label_2973;
                        }
                        default: {
                            this.disable();
                            break Label_2973;
                        }
                    }
                    break;
                }
                case DOTNODE: {
                    final DotNode dotNode = (DotNode)node;
                    this.inspect(dotNode.getBeginNode());
                    this.inspect(dotNode.getEndNode());
                    break;
                }
                case DASGNNODE: {
                    this.inspect(((AssignableNode)node).getValueNode());
                    break;
                }
                case DVARNODE: {
                    break;
                }
                case ENSURENODE: {
                    this.disable();
                    break;
                }
                case ENCODINGNODE: {
                    break;
                }
                case EVSTRNODE: {
                    this.inspect(((EvStrNode)node).getBody());
                    break;
                }
                case FALSENODE: {
                    break;
                }
                case FIXNUMNODE: {
                    break;
                }
                case FLIPNODE: {
                    this.inspect(((FlipNode)node).getBeginNode());
                    this.inspect(((FlipNode)node).getEndNode());
                    break;
                }
                case FLOATNODE: {
                    break;
                }
                case FORNODE: {
                    this.setFlag(node, 2);
                    this.setFlag(node, 32768);
                    this.inspect(((ForNode)node).getIterNode());
                    this.inspect(((ForNode)node).getBodyNode());
                    this.inspect(((ForNode)node).getVarNode());
                    break;
                }
                case GLOBALASGNNODE: {
                    final GlobalAsgnNode globalAsgnNode = (GlobalAsgnNode)node;
                    if (globalAsgnNode.getName().equals("$_")) {
                        this.setFlag(node, 2048);
                    }
                    else if (globalAsgnNode.getName().equals("$~")) {
                        this.setFlag(node, 1024);
                    }
                    this.inspect(globalAsgnNode.getValueNode());
                    break;
                }
                case GLOBALVARNODE: {
                    if (((GlobalVarNode)node).getName().equals("$_")) {
                        this.setFlag(node, 2048);
                        break;
                    }
                    if (((GlobalVarNode)node).getName().equals("$~")) {
                        this.setFlag(node, 1024);
                        break;
                    }
                    break;
                }
                case HASHNODE: {
                    final HashNode hashNode = (HashNode)node;
                    this.inspect(hashNode.getListNode());
                    break;
                }
                case IFNODE: {
                    final IfNode ifNode = (IfNode)node;
                    this.inspect(ifNode.getCondition());
                    this.inspect(ifNode.getThenBody());
                    this.inspect(ifNode.getElseBody());
                    break;
                }
                case INSTASGNNODE: {
                    this.inspect(((AssignableNode)node).getValueNode());
                    break;
                }
                case INSTVARNODE: {
                    break;
                }
                case ISCOPINGNODE: {
                    final IScopingNode iscopingNode = (IScopingNode)node;
                    this.inspect(iscopingNode.getCPath());
                    break;
                }
                case ITERNODE: {
                    this.setFlag(node, 2);
                    break;
                }
                case LAMBDANODE: {
                    this.setFlag(node, 2);
                    break;
                }
                case LOCALASGNNODE: {
                    final LocalAsgnNode localAsgnNode = (LocalAsgnNode)node;
                    if (!ASTInspector.PRAGMAS.contains(localAsgnNode.getName())) {
                        this.inspect(localAsgnNode.getValueNode());
                        break;
                    }
                    if (localAsgnNode.getName().equals("__NOFRAME__")) {
                        this.noFrame = (localAsgnNode.getValueNode() instanceof TrueNode);
                        break;
                    }
                    break;
                }
                case LOCALVARNODE: {
                    break;
                }
                case MATCHNODE: {
                    this.inspect(((MatchNode)node).getRegexpNode());
                    this.setFlag(node, 1024);
                    break;
                }
                case MATCH2NODE: {
                    final Match2Node match2Node = (Match2Node)node;
                    this.inspect(match2Node.getReceiverNode());
                    this.inspect(match2Node.getValueNode());
                    this.setFlag(node, 1024);
                    break;
                }
                case MATCH3NODE: {
                    final Match3Node match3Node = (Match3Node)node;
                    this.inspect(match3Node.getReceiverNode());
                    this.inspect(match3Node.getValueNode());
                    this.setFlag(node, 1024);
                    break;
                }
                case MODULENODE: {
                    this.setFlag(node, 4);
                    this.inspect(((ModuleNode)node).getCPath());
                    break;
                }
                case MULTIPLEASGN19NODE: {
                    final MultipleAsgn19Node multipleAsgn19Node = (MultipleAsgn19Node)node;
                    this.inspect(multipleAsgn19Node.getPre());
                    this.inspect(multipleAsgn19Node.getPost());
                    this.inspect(multipleAsgn19Node.getRest());
                    this.inspect(multipleAsgn19Node.getValueNode());
                    break;
                }
                case MULTIPLEASGNNODE: {
                    final MultipleAsgnNode multipleAsgnNode = (MultipleAsgnNode)node;
                    this.inspect(multipleAsgnNode.getArgsNode());
                    this.inspect(multipleAsgnNode.getHeadNode());
                    this.inspect(multipleAsgnNode.getValueNode());
                    break;
                }
                case NEWLINENODE: {
                    this.inspect(((NewlineNode)node).getNextNode());
                    break;
                }
                case NEXTNODE: {
                    this.inspect(((NextNode)node).getValueNode());
                    break;
                }
                case NILNODE: {
                    break;
                }
                case NOTNODE: {
                    this.inspect(((NotNode)node).getConditionNode());
                    break;
                }
                case NTHREFNODE: {
                    break;
                }
                case OPASGNANDNODE: {
                    final OpAsgnAndNode opAsgnAndNode = (OpAsgnAndNode)node;
                    this.inspect(opAsgnAndNode.getFirstNode());
                    this.inspect(opAsgnAndNode.getSecondNode());
                    break;
                }
                case OPASGNNODE: {
                    final OpAsgnNode opAsgnNode = (OpAsgnNode)node;
                    this.inspect(opAsgnNode.getReceiverNode());
                    this.inspect(opAsgnNode.getValueNode());
                    break;
                }
                case OPASGNORNODE: {
                    switch (((OpAsgnOrNode)node).getFirstNode().getNodeType()) {
                        case CLASSVARASGNNODE:
                        case CLASSVARDECLNODE:
                        case CONSTDECLNODE:
                        case DASGNNODE:
                        case GLOBALASGNNODE:
                        case LOCALASGNNODE:
                        case MULTIPLEASGNNODE:
                        case OPASGNNODE:
                        case OPELEMENTASGNNODE:
                        case DVARNODE:
                        case FALSENODE:
                        case TRUENODE:
                        case LOCALVARNODE:
                        case INSTVARNODE:
                        case BACKREFNODE:
                        case SELFNODE:
                        case VCALLNODE:
                        case YIELDNODE:
                        case GLOBALVARNODE:
                        case CONSTNODE:
                        case FCALLNODE:
                        case CLASSVARNODE: {
                            this.inspect(((OpAsgnOrNode)node).getSecondNode());
                            break Label_2973;
                        }
                        default: {
                            this.disable();
                            break Label_2973;
                        }
                    }
                    break;
                }
                case OPELEMENTASGNNODE: {
                    final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
                    this.setFlag(node, 64);
                    this.inspect(opElementAsgnNode.getArgsNode());
                    this.inspect(opElementAsgnNode.getReceiverNode());
                    this.inspect(opElementAsgnNode.getValueNode());
                    break;
                }
                case OPTARGNODE: {
                    this.inspect(((OptArgNode)node).getValue());
                    break;
                }
                case ORNODE: {
                    final OrNode orNode = (OrNode)node;
                    this.inspect(orNode.getFirstNode());
                    this.inspect(orNode.getSecondNode());
                    break;
                }
                case POSTEXENODE: {
                    final PostExeNode postExeNode = (PostExeNode)node;
                    this.setFlag(node, 2);
                    this.setFlag(node, 32768);
                    this.inspect(postExeNode.getBodyNode());
                    this.inspect(postExeNode.getVarNode());
                    break;
                }
                case PREEXENODE: {
                    final PreExeNode preExeNode = (PreExeNode)node;
                    this.setFlag(node, 2);
                    this.setFlag(node, 32768);
                    this.inspect(preExeNode.getBodyNode());
                    this.inspect(preExeNode.getVarNode());
                    break;
                }
                case REDONODE: {
                    break;
                }
                case REGEXPNODE: {
                    break;
                }
                case ROOTNODE: {
                    this.inspect(((RootNode)node).getBodyNode());
                    if (((RootNode)node).getBodyNode() instanceof BlockNode) {
                        final BlockNode blockNode = (BlockNode)((RootNode)node).getBodyNode();
                        if (blockNode.size() > 500) {
                            this.setFlag(node, 32768);
                        }
                        break;
                    }
                    break;
                }
                case RESCUEBODYNODE: {
                    final RescueBodyNode rescueBody = (RescueBodyNode)node;
                    this.inspect(rescueBody.getExceptionNodes());
                    this.inspect(rescueBody.getBodyNode());
                    this.inspect(rescueBody.getOptRescueNode());
                    break;
                }
                case RESCUENODE: {
                    final RescueNode rescueNode = (RescueNode)node;
                    this.inspect(rescueNode.getBodyNode());
                    this.inspect(rescueNode.getElseNode());
                    this.inspect(rescueNode.getRescueNode());
                    this.disable();
                    break;
                }
                case RETRYNODE: {
                    this.setFlag(node, 1048576);
                    break;
                }
                case RETURNNODE: {
                    this.inspect(((ReturnNode)node).getValueNode());
                    break;
                }
                case SCLASSNODE: {
                    this.setFlag(node, 4);
                    this.setFlag(node, 32);
                    final SClassNode sclassNode = (SClassNode)node;
                    this.inspect(sclassNode.getReceiverNode());
                    break;
                }
                case SCOPENODE: {
                    break;
                }
                case SELFNODE: {
                    break;
                }
                case SPLATNODE: {
                    this.inspect(((SplatNode)node).getValue());
                    break;
                }
                case STARNODE: {
                    break;
                }
                case STRNODE: {
                    break;
                }
                case SUPERNODE: {
                    final SuperNode superNode = (SuperNode)node;
                    this.inspect(superNode.getArgsNode());
                    this.inspect(superNode.getIterNode());
                    this.setFlag(node, 524288);
                    break;
                }
                case SVALUENODE: {
                    this.inspect(((SValueNode)node).getValue());
                    break;
                }
                case SYMBOLNODE: {
                    break;
                }
                case TOARYNODE: {
                    this.inspect(((ToAryNode)node).getValue());
                    break;
                }
                case TRUENODE: {
                    break;
                }
                case UNDEFNODE: {
                    this.setFlag(node, 8);
                    break;
                }
                case UNTILNODE: {
                    final UntilNode untilNode = (UntilNode)node;
                    final ASTInspector untilInspector = this.subInspect(untilNode.getConditionNode(), untilNode.getBodyNode());
                    if (untilInspector.getFlag(2) || untilInspector.getFlag(16)) {
                        untilNode.containsNonlocalFlow = true;
                        this.setFlag(node, 32768);
                    }
                    this.integrate(untilInspector);
                    break;
                }
                case VALIASNODE: {
                    break;
                }
                case WHENNODE: {
                    this.inspect(((WhenNode)node).getBodyNode());
                    this.inspect(((WhenNode)node).getExpressionNodes());
                    this.inspect(((WhenNode)node).getNextCase());
                    this.setFlag(node, 1024);
                    break;
                }
                case WHILENODE: {
                    final WhileNode whileNode = (WhileNode)node;
                    final ASTInspector whileInspector = this.subInspect(whileNode.getConditionNode(), whileNode.getBodyNode());
                    if (whileInspector.getFlag(2) || whileInspector.getFlag(16) || this.getFlag(1)) {
                        whileNode.containsNonlocalFlow = true;
                        this.setFlag(node, 32768);
                    }
                    this.integrate(whileInspector);
                    break;
                }
                case XSTRNODE: {
                    break;
                }
                case YIELDNODE: {
                    this.inspect(((YieldNode)node).getArgsNode());
                    break;
                }
                case ZARRAYNODE: {
                    break;
                }
                case ZEROARGNODE: {
                    break;
                }
                case ZSUPERNODE: {
                    this.setFlag(node, 32768);
                    this.setFlag(node, 65536);
                    this.inspect(((ZSuperNode)node).getIterNode());
                    break;
                }
                default: {
                    assert false : "All nodes should be accounted for in AST inspector: " + node;
                    this.disable();
                    break;
                }
            }
        }
    }
    
    public boolean hasClass() {
        return this.getFlag(4);
    }
    
    public boolean hasClosure() {
        return this.getFlag(2);
    }
    
    public boolean hasMethod() {
        return this.getFlag(8);
    }
    
    public boolean hasFrameAwareMethods() {
        return this.getFlag(594930);
    }
    
    public boolean hasScopeAwareMethods() {
        return this.getFlag(35840);
    }
    
    public boolean hasBlockArg() {
        return this.getFlag(1);
    }
    
    public boolean hasOptArgs() {
        return this.getFlag(8192);
    }
    
    public boolean hasRestArg() {
        return this.getFlag(16384);
    }
    
    public boolean hasConstant() {
        return this.getFlag(131072);
    }
    
    public boolean hasClassVar() {
        return this.getFlag(262144);
    }
    
    public boolean noFrame() {
        return this.noFrame;
    }
    
    static {
        MODIFIER_NAMES = new String[] { "BLOCK", "CLOSURE", "CLASS", "METHOD", "EVAL", "FRAME_AWARE", "FRAME_SELF", "FRAME_VISIBILITY", "FRAME_BLOCK", "FRAME_NAME", "BACKREF", "LASTLINE", "FRAME_CLASS", "OPT_ARGS", "REST_ARG", "SCOPE_AWARE", "ZSUPER", "CONSTANT", "CLASS_VAR", "SUPER", "RETRY" };
        FRAME_AWARE_METHODS = Collections.synchronizedSet(new HashSet<String>());
        SCOPE_AWARE_METHODS = Collections.synchronizedSet(new HashSet<String>());
        PRAGMAS = Collections.synchronizedSet(new HashSet<String>());
        ASTInspector.FRAME_AWARE_METHODS.add("eval");
        ASTInspector.FRAME_AWARE_METHODS.add("module_eval");
        ASTInspector.FRAME_AWARE_METHODS.add("class_eval");
        ASTInspector.FRAME_AWARE_METHODS.add("instance_eval");
        ASTInspector.FRAME_AWARE_METHODS.add("binding");
        ASTInspector.FRAME_AWARE_METHODS.add("public");
        ASTInspector.FRAME_AWARE_METHODS.add("private");
        ASTInspector.FRAME_AWARE_METHODS.add("protected");
        ASTInspector.FRAME_AWARE_METHODS.add("module_function");
        ASTInspector.FRAME_AWARE_METHODS.add("block_given?");
        ASTInspector.FRAME_AWARE_METHODS.add("iterator?");
        ASTInspector.SCOPE_AWARE_METHODS.addAll(RubyModule.SCOPE_CAPTURING_METHODS);
        ASTInspector.PRAGMAS.add("__NOFRAME__");
        ENABLED = SafePropertyAccessor.getProperty("jruby.astInspector.enabled", "true").equals("true");
    }
}

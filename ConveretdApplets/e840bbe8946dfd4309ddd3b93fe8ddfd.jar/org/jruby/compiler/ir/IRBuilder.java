// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.instructions.YieldInstr;
import org.jruby.ast.VAliasNode;
import org.jruby.ast.UndefNode;
import org.jruby.compiler.ir.operands.Symbol;
import org.jruby.compiler.ir.operands.SValue;
import org.jruby.compiler.ir.operands.Splat;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.instructions.FilenameInstr;
import org.jruby.ast.RescueBodyNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.compiler.ir.instructions.IsTrueInstr;
import org.jruby.compiler.ir.instructions.NotInstr;
import org.jruby.compiler.ir.operands.NthRef;
import org.jruby.ast.StarNode;
import org.jruby.compiler.ir.instructions.DefineModuleInstr;
import org.jruby.compiler.ir.operands.ModuleMetaObject;
import org.jruby.runtime.BlockBody;
import org.jruby.compiler.ir.instructions.GetFieldInstr;
import org.jruby.compiler.ir.operands.Hash;
import org.jruby.compiler.ir.operands.KeyValuePair;
import org.jruby.compiler.ir.instructions.GetGlobalVariableInstr;
import org.jruby.compiler.ir.instructions.ClosureReturnInstr;
import org.jruby.runtime.Arity;
import org.jruby.compiler.ir.operands.Float;
import org.jruby.ast.BlockPassNode;
import org.jruby.compiler.ir.instructions.ThreadPollInstr;
import org.jruby.compiler.ir.instructions.THROW_EXCEPTION_Instr;
import org.jruby.compiler.ir.instructions.RECV_EXCEPTION_Instr;
import org.jruby.ast.EnsureNode;
import org.jruby.compiler.ir.operands.BacktickString;
import org.jruby.compiler.ir.operands.DynamicSymbol;
import org.jruby.compiler.ir.operands.Regexp;
import org.jruby.compiler.ir.operands.CompoundString;
import org.jruby.compiler.ir.operands.Range;
import org.jruby.compiler.ir.instructions.BNEInstr;
import org.jruby.compiler.ir.instructions.ReceiveOptionalArgumentInstr;
import org.jruby.compiler.ir.instructions.ReceiveClosureInstr;
import org.jruby.compiler.ir.instructions.ReceiveArgumentInstruction;
import org.jruby.compiler.ir.instructions.DECLARE_LOCAL_TYPE_Instr;
import org.jruby.ast.TypedArgumentNode;
import org.jruby.ast.ArgumentNode;
import org.jruby.compiler.ir.instructions.ReceiveSelfInstruction;
import org.jruby.ast.ArgsNode;
import org.jruby.compiler.ir.instructions.DefineClassMethodInstr;
import org.jruby.compiler.ir.instructions.DefineInstanceMethodInstr;
import org.jruby.compiler.ir.instructions.ReturnInstr;
import org.jruby.ast.RescueNode;
import org.jruby.ast.DefinedNode;
import org.jruby.compiler.ir.instructions.ExceptionRegionEndMarkerInstr;
import org.jruby.compiler.ir.instructions.JUMP_INDIRECT_Instr;
import org.jruby.compiler.ir.instructions.SET_RETADDR_Instr;
import org.jruby.compiler.ir.instructions.ExceptionRegionStartMarkerInstr;
import org.jruby.ast.Colon2MethodNode;
import org.jruby.compiler.ir.instructions.GetConstInstr;
import org.jruby.ast.Colon2ConstNode;
import org.jruby.compiler.ir.instructions.SearchConstInstr;
import org.jruby.compiler.ir.instructions.PutConstInstr;
import org.jruby.compiler.ir.instructions.GetClassVariableInstr;
import org.jruby.compiler.ir.instructions.DefineClassInstr;
import org.jruby.compiler.ir.operands.ClassMetaObject;
import java.util.Map;
import org.jruby.compiler.ir.instructions.JumpInstr;
import org.jruby.compiler.ir.instructions.EQQInstr;
import org.jruby.ast.ListNode;
import org.jruby.ast.WhenNode;
import java.util.HashMap;
import org.jruby.compiler.ir.instructions.CaseInstr;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.operands.BreakResult;
import org.jruby.compiler.ir.instructions.BREAK_Instr;
import org.jruby.compiler.ir.operands.Fixnum;
import org.jruby.compiler.ir.operands.Backref;
import org.jruby.compiler.ir.instructions.AttrAssignInstr;
import org.jruby.compiler.ir.operands.StringLiteral;
import org.jruby.compiler.ir.operands.CompoundArray;
import org.jruby.compiler.ir.operands.Array;
import org.jruby.compiler.ir.instructions.LABEL_Instr;
import org.jruby.compiler.ir.instructions.BEQInstr;
import org.jruby.compiler.ir.operands.BooleanLiteral;
import org.jruby.compiler.ir.operands.Nil;
import org.jruby.compiler.ir.instructions.RubyInternalCallInstr;
import org.jruby.compiler.ir.instructions.ReceiveClosureArgInstr;
import org.jruby.compiler.ir.instructions.PutFieldInstr;
import org.jruby.compiler.ir.instructions.PutGlobalVarInstr;
import org.jruby.compiler.ir.instructions.CopyInstr;
import org.jruby.compiler.ir.instructions.PutClassVariableInstr;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.GetArrayInstr;
import java.util.ArrayList;
import java.util.Iterator;
import org.jruby.ast.ArrayNode;
import java.util.List;
import org.jruby.ast.ZSuperNode;
import org.jruby.ast.YieldNode;
import org.jruby.ast.XStrNode;
import org.jruby.ast.WhileNode;
import org.jruby.ast.VCallNode;
import org.jruby.ast.UntilNode;
import org.jruby.ast.ToAryNode;
import org.jruby.ast.SymbolNode;
import org.jruby.ast.SValueNode;
import org.jruby.ast.SuperNode;
import org.jruby.ast.StrNode;
import org.jruby.ast.SplatNode;
import org.jruby.ast.SelfNode;
import org.jruby.ast.SClassNode;
import org.jruby.ast.ReturnNode;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.OrNode;
import org.jruby.ast.OpAsgnOrNode;
import org.jruby.ast.OpAsgnNode;
import org.jruby.ast.OpAsgnAndNode;
import org.jruby.ast.NotNode;
import org.jruby.ast.NthRefNode;
import org.jruby.ast.NextNode;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.ModuleNode;
import org.jruby.ast.MatchNode;
import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2Node;
import org.jruby.ast.LocalVarNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.LiteralNode;
import org.jruby.ast.IterNode;
import org.jruby.ast.InstVarNode;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.IfNode;
import org.jruby.ast.HashNode;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.ForNode;
import org.jruby.ast.FloatNode;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.FCallNode;
import org.jruby.ast.EvStrNode;
import org.jruby.ast.DXStrNode;
import org.jruby.ast.DVarNode;
import org.jruby.ast.DStrNode;
import org.jruby.ast.DRegexpNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.DefsNode;
import org.jruby.ast.MethodDefNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.ast.Colon3Node;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.ClassVarDeclNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.ClassVarNode;
import org.jruby.ast.ClassNode;
import org.jruby.ast.CaseNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.BreakNode;
import org.jruby.ast.BlockNode;
import org.jruby.ast.BignumNode;
import org.jruby.ast.BeginNode;
import org.jruby.ast.BackRefNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ArgsCatNode;
import org.jruby.ast.AndNode;
import org.jruby.ast.AliasNode;
import org.jruby.compiler.NotCompilableException;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.instructions.JRubyImplCallInstr;
import org.jruby.compiler.ir.operands.MethAddr;
import org.jruby.ast.NewlineNode;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.instructions.LineNumberInstr;
import org.jruby.ast.NodeType;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import org.jruby.runtime.DynamicScope;
import org.jruby.util.ByteList;
import org.jruby.Ruby;
import org.jruby.ast.Node;
import org.jruby.compiler.ir.compiler_pass.LinearizeCFG;
import org.jruby.compiler.ir.compiler_pass.AddBindingInstructions;
import org.jruby.compiler.ir.compiler_pass.opts.DeadCodeElimination;
import org.jruby.compiler.ir.compiler_pass.LiveVariableAnalysis;
import org.jruby.compiler.ir.compiler_pass.InlineTest;
import org.jruby.compiler.ir.compiler_pass.CFG_Builder;
import org.jruby.compiler.ir.compiler_pass.opts.LocalOptimizationPass;
import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import org.jruby.compiler.ir.compiler_pass.IR_Printer;
import org.jruby.ast.RootNode;
import java.util.Date;
import org.jruby.compiler.ir.operands.Label;
import java.util.Stack;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.UnexecutableNil;

public class IRBuilder
{
    private static final UnexecutableNil U_NIL;
    private static final Operand[] NO_ARGS;
    private int _lastProcessedLineNum;
    private Stack<EnsureBlockInfo> _ensureBlockStack;
    private Stack<Label> _rescueBlockLabelStack;
    
    public IRBuilder() {
        this._lastProcessedLineNum = -1;
        this._ensureBlockStack = new Stack<EnsureBlockInfo>();
        this._rescueBlockLabelStack = new Stack<Label>();
    }
    
    public static void main(final String[] args) {
        int i;
        final boolean isDebug = (i = ((args.length > 0 && args[0].equals("-debug")) ? 1 : 0)) != 0;
        String methName = null;
        if (args.length > i && args[i].equals("-inline")) {
            methName = args[i + 1];
            i += 2;
        }
        final boolean isCommandLineScript = args.length > i && args[i].equals("-e");
        for (i += (isCommandLineScript ? 1 : 0); i < args.length; ++i) {
            final long t1 = new Date().getTime();
            final Node ast = buildAST(isCommandLineScript, args[i]);
            final long t2 = new Date().getTime();
            final IRScope scope = new IRBuilder().buildRoot((RootNode)ast);
            final long t3 = new Date().getTime();
            if (isDebug) {
                System.out.println("################## Before local optimization pass ##################");
                scope.runCompilerPass(new IR_Printer());
            }
            scope.runCompilerPass(new LocalOptimizationPass());
            final long t4 = new Date().getTime();
            if (isDebug) {
                System.out.println("################## After local optimization pass ##################");
                scope.runCompilerPass(new IR_Printer());
            }
            scope.runCompilerPass(new CFG_Builder());
            final long t5 = new Date().getTime();
            final long t6 = new Date().getTime();
            if (methName != null) {
                System.out.println("################## After inline pass ##################");
                System.out.println("Asked to inline " + methName);
                scope.runCompilerPass(new InlineTest(methName));
                scope.runCompilerPass(new LocalOptimizationPass());
                scope.runCompilerPass(new IR_Printer());
            }
            if (isDebug) {
                System.out.println("################## After dead code elimination pass ##################");
            }
            scope.runCompilerPass(new LiveVariableAnalysis());
            final long t7 = new Date().getTime();
            scope.runCompilerPass(new DeadCodeElimination());
            final long t8 = new Date().getTime();
            scope.runCompilerPass(new AddBindingInstructions());
            final long t9 = new Date().getTime();
            if (isDebug) {
                scope.runCompilerPass(new IR_Printer());
            }
            scope.runCompilerPass(new LinearizeCFG());
            if (isDebug) {
                System.out.println("################## After cfg linearization pass ##################");
                scope.runCompilerPass(new IR_Printer());
            }
            System.out.println("Time to build AST         : " + (t2 - t1));
            System.out.println("Time to build IR          : " + (t3 - t2));
            System.out.println("Time to run local opts    : " + (t4 - t3));
            System.out.println("Time to run build cfg     : " + (t5 - t4));
            System.out.println("Time to run build domtree : " + (t6 - t5));
            System.out.println("Time to run lva           : " + (t7 - t6));
            System.out.println("Time to run dead code elim: " + (t8 - t7));
            System.out.println("Time to add frame instrs  : " + (t9 - t8));
        }
    }
    
    public static Node buildAST(final boolean isCommandLineScript, final String arg) {
        final Ruby ruby = Ruby.getGlobalRuntime();
        if (isCommandLineScript) {
            return ruby.parse(ByteList.create(arg), "-e", null, 0, false);
        }
        FileInputStream fis = null;
        try {
            final File file = new File(arg);
            fis = new FileInputStream(file);
            final long size = file.length();
            final byte[] bytes = new byte[(int)size];
            fis.read(bytes);
            System.out.println("-- processing " + arg + " --");
            return ruby.parse(new ByteList(bytes), arg, null, 0, false);
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public Node skipOverNewlines(final IRScope s, Node n) {
        if (n.getNodeType() == NodeType.NEWLINENODE) {
            final int currLineNum = n.getPosition().getStartLine();
            if (currLineNum != this._lastProcessedLineNum) {
                s.addInstr(new LineNumberInstr(s, currLineNum));
                this._lastProcessedLineNum = currLineNum;
            }
        }
        while (n.getNodeType() == NodeType.NEWLINENODE) {
            n = ((NewlineNode)n).getNextNode();
        }
        return n;
    }
    
    public Operand generateJRubyUtilityCall(final IRScope m, final MethAddr meth, final Operand receiver, final Operand[] args) {
        final Variable ret = m.getNewTemporaryVariable();
        m.addInstr(new JRubyImplCallInstr(ret, meth, receiver, args));
        return ret;
    }
    
    public Operand build(final Node node, final IRScope m) {
        if (node == null) {
            return null;
        }
        if (m == null) {
            System.out.println("Got a null scope!");
            throw new NotCompilableException("Unknown node encountered in builder: " + node);
        }
        switch (node.getNodeType()) {
            case ALIASNODE: {
                return this.buildAlias((AliasNode)node, m);
            }
            case ANDNODE: {
                return this.buildAnd((AndNode)node, m);
            }
            case ARGSCATNODE: {
                return this.buildArgsCat((ArgsCatNode)node, m);
            }
            case ARGSPUSHNODE: {
                return this.buildArgsPush((ArgsPushNode)node, m);
            }
            case ARRAYNODE: {
                return this.buildArray(node, m);
            }
            case ATTRASSIGNNODE: {
                return this.buildAttrAssign((AttrAssignNode)node, m);
            }
            case BACKREFNODE: {
                return this.buildBackref((BackRefNode)node, m);
            }
            case BEGINNODE: {
                return this.buildBegin((BeginNode)node, m);
            }
            case BIGNUMNODE: {
                return this.buildBignum((BignumNode)node, m);
            }
            case BLOCKNODE: {
                return this.buildBlock((BlockNode)node, m);
            }
            case BREAKNODE: {
                return this.buildBreak((BreakNode)node, (IRExecutionScope)m);
            }
            case CALLNODE: {
                return this.buildCall((CallNode)node, m);
            }
            case CASENODE: {
                return this.buildCase((CaseNode)node, m);
            }
            case CLASSNODE: {
                return this.buildClass((ClassNode)node, m);
            }
            case CLASSVARNODE: {
                return this.buildClassVar((ClassVarNode)node, m);
            }
            case CLASSVARASGNNODE: {
                return this.buildClassVarAsgn((ClassVarAsgnNode)node, m);
            }
            case CLASSVARDECLNODE: {
                return this.buildClassVarDecl((ClassVarDeclNode)node, m);
            }
            case COLON2NODE: {
                return this.buildColon2((Colon2Node)node, m);
            }
            case COLON3NODE: {
                return this.buildColon3((Colon3Node)node, m);
            }
            case CONSTDECLNODE: {
                return this.buildConstDecl((ConstDeclNode)node, m);
            }
            case CONSTNODE: {
                return this.searchConst(m, ((ConstNode)node).getName());
            }
            case DASGNNODE: {
                return this.buildDAsgn((DAsgnNode)node, m);
            }
            case DEFINEDNODE: {
                return this.buildDefined(node, m);
            }
            case DEFNNODE: {
                return this.buildDefn((MethodDefNode)node, m);
            }
            case DEFSNODE: {
                return this.buildDefs((DefsNode)node, m);
            }
            case DOTNODE: {
                return this.buildDot((DotNode)node, m);
            }
            case DREGEXPNODE: {
                return this.buildDRegexp((DRegexpNode)node, m);
            }
            case DSTRNODE: {
                return this.buildDStr((DStrNode)node, m);
            }
            case DSYMBOLNODE: {
                return this.buildDSymbol(node, m);
            }
            case DVARNODE: {
                return this.buildDVar((DVarNode)node, m);
            }
            case DXSTRNODE: {
                return this.buildDXStr((DXStrNode)node, m);
            }
            case ENSURENODE: {
                return this.buildEnsureNode(node, m);
            }
            case EVSTRNODE: {
                return this.buildEvStr((EvStrNode)node, m);
            }
            case FALSENODE: {
                return this.buildFalse(node, m);
            }
            case FCALLNODE: {
                return this.buildFCall((FCallNode)node, m);
            }
            case FIXNUMNODE: {
                return this.buildFixnum((FixnumNode)node, m);
            }
            case FLOATNODE: {
                return this.buildFloat((FloatNode)node, m);
            }
            case FORNODE: {
                return this.buildFor((ForNode)node, (IRExecutionScope)m);
            }
            case GLOBALASGNNODE: {
                return this.buildGlobalAsgn((GlobalAsgnNode)node, m);
            }
            case GLOBALVARNODE: {
                return this.buildGlobalVar((GlobalVarNode)node, m);
            }
            case HASHNODE: {
                return this.buildHash((HashNode)node, m);
            }
            case IFNODE: {
                return this.buildIf((IfNode)node, m);
            }
            case INSTASGNNODE: {
                return this.buildInstAsgn((InstAsgnNode)node, m);
            }
            case INSTVARNODE: {
                return this.buildInstVar((InstVarNode)node, m);
            }
            case ITERNODE: {
                return this.buildIter((IterNode)node, (IRExecutionScope)m);
            }
            case LITERALNODE: {
                return this.buildLiteral((LiteralNode)node, m);
            }
            case LOCALASGNNODE: {
                return this.buildLocalAsgn((LocalAsgnNode)node, m);
            }
            case LOCALVARNODE: {
                return this.buildLocalVar((LocalVarNode)node, m);
            }
            case MATCH2NODE: {
                return this.buildMatch2((Match2Node)node, m);
            }
            case MATCH3NODE: {
                return this.buildMatch3((Match3Node)node, m);
            }
            case MATCHNODE: {
                return this.buildMatch((MatchNode)node, m);
            }
            case MODULENODE: {
                return this.buildModule((ModuleNode)node, m);
            }
            case MULTIPLEASGNNODE: {
                return this.buildMultipleAsgn((MultipleAsgnNode)node, m);
            }
            case NEWLINENODE: {
                return this.buildNewline((NewlineNode)node, m);
            }
            case NEXTNODE: {
                return this.buildNext((NextNode)node, (IRExecutionScope)m);
            }
            case NTHREFNODE: {
                return this.buildNthRef((NthRefNode)node, m);
            }
            case NILNODE: {
                return this.buildNil(node, m);
            }
            case NOTNODE: {
                return this.buildNot((NotNode)node, m);
            }
            case OPASGNANDNODE: {
                return this.buildOpAsgnAnd((OpAsgnAndNode)node, m);
            }
            case OPASGNNODE: {
                return this.buildOpAsgn((OpAsgnNode)node, m);
            }
            case OPASGNORNODE: {
                return this.buildOpAsgnOr((OpAsgnOrNode)node, m);
            }
            case OPELEMENTASGNNODE: {
                return this.buildOpElementAsgn(node, m);
            }
            case ORNODE: {
                return this.buildOr((OrNode)node, m);
            }
            case REDONODE: {
                return this.buildRedo(node, (IRExecutionScope)m);
            }
            case REGEXPNODE: {
                return this.buildRegexp((RegexpNode)node, m);
            }
            case RESCUEBODYNODE: {
                throw new NotCompilableException("rescue body is handled by rescue compilation at: " + node.getPosition());
            }
            case RESCUENODE: {
                return this.buildRescue(node, m);
            }
            case RETRYNODE: {
                return this.buildRetry(node, m);
            }
            case RETURNNODE: {
                return this.buildReturn((ReturnNode)node, m);
            }
            case ROOTNODE: {
                throw new NotCompilableException("Use buildRoot(); Root node at: " + node.getPosition());
            }
            case SCLASSNODE: {
                return this.buildSClass((SClassNode)node, m);
            }
            case SELFNODE: {
                return this.buildSelf(node, m);
            }
            case SPLATNODE: {
                return this.buildSplat((SplatNode)node, m);
            }
            case STRNODE: {
                return this.buildStr((StrNode)node, m);
            }
            case SUPERNODE: {
                return this.buildSuper((SuperNode)node, m);
            }
            case SVALUENODE: {
                return this.buildSValue((SValueNode)node, m);
            }
            case SYMBOLNODE: {
                return this.buildSymbol((SymbolNode)node, m);
            }
            case TOARYNODE: {
                return this.buildToAry((ToAryNode)node, m);
            }
            case TRUENODE: {
                return this.buildTrue(node, m);
            }
            case UNDEFNODE: {
                return this.buildUndef(node, m);
            }
            case UNTILNODE: {
                return this.buildUntil((UntilNode)node, (IRExecutionScope)m);
            }
            case VALIASNODE: {
                return this.buildVAlias(node, m);
            }
            case VCALLNODE: {
                return this.buildVCall((VCallNode)node, m);
            }
            case WHILENODE: {
                return this.buildWhile((WhileNode)node, (IRExecutionScope)m);
            }
            case WHENNODE: {
                assert false : "When nodes are handled by case node compilation.";
                return null;
            }
            case XSTRNODE: {
                return this.buildXStr((XStrNode)node, m);
            }
            case YIELDNODE: {
                return this.buildYield((YieldNode)node, m);
            }
            case ZARRAYNODE: {
                return this.buildZArray(node, m);
            }
            case ZSUPERNODE: {
                return this.buildZSuper((ZSuperNode)node, m);
            }
            default: {
                new Exception().printStackTrace();
                throw new NotCompilableException("Unknown node encountered in builder: " + node.getClass());
            }
        }
    }
    
    public void buildArguments(final List<Operand> args, final Node node, final IRScope s) {
        switch (node.getNodeType()) {
            case ARGSCATNODE: {
                this.buildArgsCatArguments(args, (ArgsCatNode)node, s);
                break;
            }
            case ARGSPUSHNODE: {
                this.buildArgsPushArguments(args, (ArgsPushNode)node, s);
                break;
            }
            case ARRAYNODE: {
                this.buildArrayArguments(args, node, s);
                break;
            }
            case SPLATNODE: {
                this.buildSplatArguments(args, (SplatNode)node, s);
                break;
            }
            default: {
                final Operand retVal = this.build(node, s);
                if (retVal != null) {
                    args.add(retVal);
                    break;
                }
                break;
            }
        }
    }
    
    public void buildVariableArityArguments(final List<Operand> args, final Node node, final IRScope s) {
        this.buildArguments(args, node, s);
    }
    
    public void buildSpecificArityArguments(final List<Operand> args, final Node node, final IRScope s) {
        if (node.getNodeType() == NodeType.ARRAYNODE) {
            final ArrayNode arrayNode = (ArrayNode)node;
            if (arrayNode.isLightweight()) {
                for (final Node n : arrayNode.childNodes()) {
                    args.add(this.build(n, s));
                }
            }
            else {
                args.add(this.build(arrayNode, s));
            }
        }
        else {
            args.add(this.build(node, s));
        }
    }
    
    public List<Operand> setupCallArgs(Node args, final IRScope s) {
        final List<Operand> argsList = new ArrayList<Operand>();
        if (args != null) {
            args = this.skipOverNewlines(s, args);
            this.buildArgs(argsList, args, s);
        }
        return argsList;
    }
    
    public void buildArgs(final List<Operand> argsList, final Node args, final IRScope s) {
        switch (args.getNodeType()) {
            case ARGSCATNODE:
            case ARGSPUSHNODE:
            case SPLATNODE: {
                this.buildVariableArityArguments(argsList, args, s);
                break;
            }
            case ARRAYNODE: {
                final ArrayNode arrayNode = (ArrayNode)args;
                this.buildSpecificArityArguments(argsList, arrayNode, s);
                break;
            }
            default: {
                this.buildSpecificArityArguments(argsList, args, s);
                break;
            }
        }
    }
    
    public void buildAssignment(final Node node, final IRScope s, final Operand values, final int argIndex, final boolean isSplat) {
        final Variable v = s.getNewTemporaryVariable();
        s.addInstr(new GetArrayInstr(v, values, argIndex, isSplat));
        switch (node.getNodeType()) {
            case ATTRASSIGNNODE: {
                this.buildAttrAssignAssignment(node, s, v);
                break;
            }
            case CLASSVARASGNNODE: {
                s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), ((ClassVarAsgnNode)node).getName(), v));
                break;
            }
            case CLASSVARDECLNODE: {
                s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), ((ClassVarDeclNode)node).getName(), v));
                break;
            }
            case CONSTDECLNODE: {
                this.buildConstDeclAssignment((ConstDeclNode)node, s, v);
                break;
            }
            case DASGNNODE: {
                final DAsgnNode variable = (DAsgnNode)node;
                final int depth = variable.getDepth();
                s.addInstr(new CopyInstr(this.getScopeNDown(s, depth).getLocalVariable(variable.getName()), v));
                break;
            }
            case GLOBALASGNNODE: {
                s.addInstr(new PutGlobalVarInstr(((GlobalAsgnNode)node).getName(), v));
                break;
            }
            case INSTASGNNODE: {
                s.addInstr(new PutFieldInstr(this.getSelf(s), ((InstAsgnNode)node).getName(), v));
                break;
            }
            case LOCALASGNNODE: {
                final LocalAsgnNode localVariable = (LocalAsgnNode)node;
                final int depth = localVariable.getDepth();
                s.addInstr(new CopyInstr(this.getScopeNDown(s, depth).getLocalVariable(localVariable.getName()), v));
                break;
            }
            case MULTIPLEASGNNODE: {
                this.buildMultipleAsgnAssignment((MultipleAsgnNode)node, s, v);
                break;
            }
            case ZEROARGNODE: {
                throw new NotCompilableException("Shouldn't get here; zeroarg does not do assignment: " + node);
            }
            default: {
                throw new NotCompilableException("Can't build assignment node: " + node);
            }
        }
    }
    
    public void buildBlockArgsAssignment(final Node node, final IRScope s, final int argIndex, final boolean isSplat) {
        switch (node.getNodeType()) {
            case ATTRASSIGNNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                this.buildAttrAssignAssignment(node, s, v);
                break;
            }
            case DASGNNODE: {
                final DAsgnNode dynamicAsgn = (DAsgnNode)node;
                final Variable v = this.getScopeNDown(s, dynamicAsgn.getDepth()).getLocalVariable(dynamicAsgn.getName());
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                break;
            }
            case CLASSVARASGNNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), ((ClassVarAsgnNode)node).getName(), v));
                break;
            }
            case CLASSVARDECLNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), ((ClassVarDeclNode)node).getName(), v));
                break;
            }
            case CONSTDECLNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                this.buildConstDeclAssignment((ConstDeclNode)node, s, v);
                break;
            }
            case GLOBALASGNNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                s.addInstr(new PutGlobalVarInstr(((GlobalAsgnNode)node).getName(), v));
                break;
            }
            case INSTASGNNODE: {
                final Variable v = s.getNewTemporaryVariable();
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                s.addInstr(new PutFieldInstr(this.getSelf(s), ((InstAsgnNode)node).getName(), v));
                break;
            }
            case LOCALASGNNODE: {
                final LocalAsgnNode localVariable = (LocalAsgnNode)node;
                final int depth = localVariable.getDepth();
                final Variable v = this.getScopeNDown(s, depth).getLocalVariable(localVariable.getName());
                s.addInstr(new ReceiveClosureArgInstr(v, argIndex, isSplat));
                break;
            }
            case MULTIPLEASGNNODE: {
                this.buildMultipleAsgnAssignment((MultipleAsgnNode)node, s, null);
                break;
            }
            case ZEROARGNODE: {
                throw new NotCompilableException("Shouldn't get here; zeroarg does not do assignment: " + node);
            }
            default: {
                throw new NotCompilableException("Can't build assignment node: " + node);
            }
        }
    }
    
    public Operand buildAlias(final AliasNode alias, final IRScope s) {
        final Operand newName = this.build(alias.getNewName(), s);
        final Operand oldName = this.build(alias.getOldName(), s);
        final Operand[] args = { newName, oldName };
        s.addInstr(new RubyInternalCallInstr(null, MethAddr.DEFINE_ALIAS, MetaObject.create(s), args));
        return Nil.NIL;
    }
    
    public Operand buildAnd(final AndNode andNode, final IRScope m) {
        if (andNode.getFirstNode().getNodeType().alwaysTrue()) {
            this.build(andNode.getFirstNode(), m);
            return this.build(andNode.getSecondNode(), m);
        }
        if (andNode.getFirstNode().getNodeType().alwaysFalse()) {
            this.build(andNode.getFirstNode(), m);
            return BooleanLiteral.FALSE;
        }
        final Variable ret = m.getNewTemporaryVariable();
        final Label l = m.getNewLabel();
        final Operand v1 = this.build(andNode.getFirstNode(), m);
        m.addInstr(new CopyInstr(ret, BooleanLiteral.FALSE));
        m.addInstr(new BEQInstr(v1, BooleanLiteral.FALSE, l));
        final Operand v2 = this.build(andNode.getSecondNode(), m);
        m.addInstr(new CopyInstr(ret, v2));
        m.addInstr(new LABEL_Instr(l));
        return ret;
    }
    
    public Operand buildArray(final Node node, final IRScope m) {
        final List<Operand> elts = new ArrayList<Operand>();
        for (final Node e : node.childNodes()) {
            elts.add(this.build(e, m));
        }
        return new Array(elts);
    }
    
    public Operand buildArgsCat(final ArgsCatNode argsCatNode, final IRScope s) {
        final Operand v1 = this.build(argsCatNode.getFirstNode(), s);
        final Operand v2 = this.build(argsCatNode.getSecondNode(), s);
        return new CompoundArray(v1, v2);
    }
    
    public Operand buildArgsPush(final ArgsPushNode node, final IRScope m) {
        throw new NotCompilableException("ArgsPush should never be encountered bare in 1.8");
    }
    
    private Operand buildAttrAssign(final AttrAssignNode attrAssignNode, final IRScope s) {
        final List<Operand> args = this.setupCallArgs(attrAssignNode.getArgsNode(), s);
        final Operand obj = this.build(attrAssignNode.getReceiverNode(), s);
        s.addInstr(new AttrAssignInstr(obj, new StringLiteral(attrAssignNode.getName()), args.toArray(new Operand[args.size()])));
        return args.get(args.size() - 1);
    }
    
    public Operand buildAttrAssignAssignment(final Node node, final IRScope s, final Operand value) {
        final AttrAssignNode attrAssignNode = (AttrAssignNode)node;
        final List<Operand> args = this.setupCallArgs(attrAssignNode.getArgsNode(), s);
        final Operand obj = this.build(attrAssignNode.getReceiverNode(), s);
        s.addInstr(new AttrAssignInstr(obj, new StringLiteral(attrAssignNode.getName()), args.toArray(new Operand[args.size()]), value));
        return value;
    }
    
    public Operand buildBackref(final BackRefNode node, final IRScope m) {
        return new Backref(node.getType());
    }
    
    public Operand buildBegin(final BeginNode beginNode, final IRScope s) {
        return this.build(beginNode.getBodyNode(), s);
    }
    
    public Operand buildBignum(final BignumNode node, final IRScope s) {
        return new Fixnum(node.getValue());
    }
    
    public Operand buildBlock(final BlockNode node, final IRScope s) {
        Operand retVal = null;
        for (final Node child : node.childNodes()) {
            retVal = this.build(child, s);
        }
        return retVal;
    }
    
    public Operand buildBreak(final BreakNode breakNode, final IRExecutionScope s) {
        final Operand rv = this.build(breakNode.getValueNode(), s);
        if (s instanceof IRClosure || s.getCurrentLoop() == null) {
            s.addInstr(new BREAK_Instr(rv));
            return rv;
        }
        return new BreakResult(rv, s.getCurrentLoop().loopEndLabel);
    }
    
    public Operand buildCall(final CallNode callNode, final IRScope s) {
        final Node callArgsNode = callNode.getArgsNode();
        final Node receiverNode = callNode.getReceiverNode();
        final Operand receiver = this.build(receiverNode, s);
        final List<Operand> args = this.setupCallArgs(callArgsNode, s);
        final Operand block = this.setupCallClosure(callNode.getIterNode(), s);
        final Variable callResult = s.getNewTemporaryVariable();
        final Instr callInstr = new CallInstr(callResult, new MethAddr(callNode.getName()), receiver, args.toArray(new Operand[args.size()]), block);
        s.addInstr(callInstr);
        return callResult;
    }
    
    public Operand buildCase(final CaseNode caseNode, final IRScope m) {
        Operand value = this.build(caseNode.getCaseNode(), m);
        if (value == null) {
            value = BooleanLiteral.TRUE;
        }
        final Label endLabel = m.getNewLabel();
        final boolean hasElse = caseNode.getElseNode() != null;
        final Label elseLabel = hasElse ? m.getNewLabel() : null;
        final Variable result = m.getNewTemporaryVariable();
        final CaseInstr caseInstr = new CaseInstr(result, value, endLabel);
        m.addInstr(caseInstr);
        final List<Operand> variables = new ArrayList<Operand>();
        final List<Label> labels = new ArrayList<Label>();
        final Map<Label, Node> bodies = new HashMap<Label, Node>();
        for (final Node aCase : caseNode.getCases().childNodes()) {
            final WhenNode whenNode = (WhenNode)aCase;
            final Label bodyLabel = m.getNewLabel();
            if (whenNode.getExpressionNodes() instanceof ListNode) {
                for (final Node expression : ((ListNode)whenNode.getExpressionNodes()).childNodes()) {
                    final Variable eqqResult = m.getNewTemporaryVariable();
                    variables.add(eqqResult);
                    labels.add(bodyLabel);
                    m.addInstr(new EQQInstr(eqqResult, this.build(expression, m), value));
                    m.addInstr(new BEQInstr(eqqResult, BooleanLiteral.TRUE, bodyLabel));
                }
            }
            else {
                final Variable eqqResult2 = m.getNewTemporaryVariable();
                variables.add(eqqResult2);
                labels.add(bodyLabel);
                m.addInstr(new EQQInstr(eqqResult2, this.build(whenNode.getExpressionNodes(), m), value));
                m.addInstr(new BEQInstr(eqqResult2, BooleanLiteral.TRUE, bodyLabel));
            }
            bodies.put(bodyLabel, whenNode.getBodyNode());
        }
        m.addInstr(new JumpInstr(hasElse ? elseLabel : endLabel));
        if (hasElse) {
            caseInstr.setElse(elseLabel);
            bodies.put(elseLabel, caseNode.getElseNode());
        }
        for (final Map.Entry<Label, Node> entry : bodies.entrySet()) {
            m.addInstr(new LABEL_Instr(entry.getKey()));
            Operand bodyValue = this.build(entry.getValue(), m);
            if (bodyValue != null) {
                Label tgt = endLabel;
                if (bodyValue instanceof BreakResult) {
                    final BreakResult br = (BreakResult)bodyValue;
                    bodyValue = br._result;
                    tgt = br._jumpTarget;
                }
                m.addInstr(new CopyInstr(result, bodyValue));
                m.addInstr(new JumpInstr(tgt));
            }
        }
        m.addInstr(new LABEL_Instr(endLabel));
        caseInstr.setLabels(labels);
        caseInstr.setVariables(variables);
        return result;
    }
    
    public Operand buildClass(final ClassNode classNode, final IRScope s) {
        final Node superNode = classNode.getSuperNode();
        final Colon3Node cpath = classNode.getCPath();
        final Operand superClass = (superNode == null) ? MetaObject.create(IRModule.getCoreClass("Object")) : this.build(superNode, s);
        final String className = cpath.getName();
        final Operand container = this.getContainerFromCPath(cpath, s);
        final IRClass c = new IRClass(s, container, superClass, className, classNode.getScope());
        final ClassMetaObject cmo = (ClassMetaObject)MetaObject.create(c);
        s.getNearestModule().getRootMethod().addInstr(new DefineClassInstr(cmo, c.superClass));
        s.getNearestModule().addClass(c);
        this.build(classNode.getBodyNode(), c.getRootMethod());
        return Nil.NIL;
    }
    
    public Operand buildSClass(final SClassNode sclassNode, final IRScope s) {
        final Operand receiver = this.build(sclassNode.getReceiverNode(), s);
        final IRClass mc = new IRMetaClass(s, receiver, sclassNode.getScope());
        s.getNearestModule().addClass(mc);
        final ClassMetaObject cmo = (ClassMetaObject)MetaObject.create(mc);
        s.getNearestModule().getRootMethod().addInstr(new DefineClassInstr(cmo, mc.superClass));
        this.build(sclassNode.getBodyNode(), mc.getRootMethod());
        return Nil.NIL;
    }
    
    public Operand buildClassVar(final ClassVarNode node, final IRScope s) {
        final Variable ret = s.getNewTemporaryVariable();
        s.addInstr(new GetClassVariableInstr(ret, MetaObject.create(s).getNearestClass(), node.getName()));
        return ret;
    }
    
    public Operand buildClassVarAsgn(final ClassVarAsgnNode classVarAsgnNode, final IRScope s) {
        final Operand val = this.build(classVarAsgnNode.getValueNode(), s);
        s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), classVarAsgnNode.getName(), val));
        return val;
    }
    
    public Operand buildClassVarDecl(final ClassVarDeclNode classVarDeclNode, final IRScope s) {
        final Operand val = this.build(classVarDeclNode.getValueNode(), s);
        s.addInstr(new PutClassVariableInstr(MetaObject.create(s).getNearestClass(), classVarDeclNode.getName(), val));
        return val;
    }
    
    public Operand buildConstDecl(final ConstDeclNode node, final IRScope s) {
        final Operand val = this.build(node.getValueNode(), s);
        return this.buildConstDeclAssignment(node, s, val);
    }
    
    public Operand buildConstDeclAssignment(final ConstDeclNode constDeclNode, final IRScope s, final Operand val) {
        final Node constNode = constDeclNode.getConstNode();
        if (constNode == null) {
            s.getNearestModule().setConstantValue(constDeclNode.getName(), val);
            s.getNearestModule().getRootMethod().addInstr(new PutConstInstr(s.getNearestModule(), constDeclNode.getName(), val));
        }
        else if (constNode.getNodeType() == NodeType.COLON2NODE) {
            final Operand module = this.build(((Colon2Node)constNode).getLeftNode(), s);
            s.addInstr(new PutConstInstr(module, constDeclNode.getName(), val));
        }
        else {
            s.addInstr(new PutConstInstr(this.getSelf(s), constDeclNode.getName(), val));
        }
        return val;
    }
    
    private Operand searchConst(final IRScope s, final String name) {
        final Variable v = s.getNewTemporaryVariable();
        s.addInstr(new SearchConstInstr(v, s, name));
        return v;
    }
    
    public Operand buildColon2(final Colon2Node iVisited, final IRScope s) {
        final Node leftNode = iVisited.getLeftNode();
        final String name = iVisited.getName();
        if (leftNode == null) {
            return this.searchConst(s, name);
        }
        if (iVisited instanceof Colon2ConstNode) {
            Operand module = this.build(iVisited.getLeftNode(), s);
            if (module instanceof MetaObject) {
                module = MetaObject.create(((MetaObject)module).scope);
            }
            final Variable constVal = s.getNewTemporaryVariable();
            s.addInstr(new GetConstInstr(constVal, module, name));
            return constVal;
        }
        if (iVisited instanceof Colon2MethodNode) {
            final Colon2MethodNode c2mNode = (Colon2MethodNode)iVisited;
            final List<Operand> args = this.setupCallArgs(null, s);
            final Operand block = this.setupCallClosure(null, s);
            final Variable callResult = s.getNewTemporaryVariable();
            final Instr callInstr = new CallInstr(callResult, new MethAddr(c2mNode.getName()), null, args.toArray(new Operand[args.size()]), block);
            s.addInstr(callInstr);
            return callResult;
        }
        throw new NotCompilableException("Not compilable: " + iVisited);
    }
    
    public Operand buildColon3(final Colon3Node node, final IRScope s) {
        final Variable cv = s.getNewTemporaryVariable();
        s.addInstr(new SearchConstInstr(cv, this.getSelf(s), node.getName()));
        return cv;
    }
    
    private Object[] protectCodeWithEnsure(final IRScope m, final CodeBlock protectedCode, final Object[] protectedCodeArgs, final CodeBlock ensureCode, final Object[] ensureCodeArgs) {
        final EnsureBlockInfo ebi = new EnsureBlockInfo(m);
        this._ensureBlockStack.push(ebi);
        final Label rBeginLabel = m.getNewLabel();
        final Label rEndLabel = ebi.end;
        final List<Label> rescueLabels = new ArrayList<Label>() {};
        m.addInstr(new LABEL_Instr(rBeginLabel));
        m.addInstr(new ExceptionRegionStartMarkerInstr(rBeginLabel, rEndLabel, rescueLabels));
        final Object v1 = protectedCode.run(protectedCodeArgs);
        m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, rEndLabel));
        this._ensureBlockStack.pop();
        m.addInstr(new LABEL_Instr(ebi.start));
        final Object v2 = ensureCode.run(ensureCodeArgs);
        m.addInstr(new JUMP_INDIRECT_Instr(ebi.returnAddr));
        m.addInstr(new ExceptionRegionEndMarkerInstr());
        final Label dummyRescueBlockLabel = m.getNewLabel();
        rescueLabels.add(dummyRescueBlockLabel);
        m.addInstr(new LABEL_Instr(dummyRescueBlockLabel));
        m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, ebi.end));
        m.addInstr(new JumpInstr(ebi.start));
        m.addInstr(new LABEL_Instr(rEndLabel));
        return new Object[] { v1, v2 };
    }
    
    private Operand protectCodeWithRescue(final IRScope m, final CodeBlock protectedCode, final Object[] protectedCodeArgs, final CodeBlock rescueBlock, final Object[] rescueBlockArgs) {
        final Variable rv = m.getNewTemporaryVariable();
        final Label rBeginLabel = m.getNewLabel();
        final Label rEndLabel = m.getNewLabel();
        final List<Label> rescueLabels = new ArrayList<Label>() {};
        m.addInstr(new LABEL_Instr(rBeginLabel));
        m.addInstr(new ExceptionRegionStartMarkerInstr(rBeginLabel, rEndLabel, rescueLabels));
        final Object v1 = protectedCode.run(protectedCodeArgs);
        m.addInstr(new CopyInstr(rv, (Operand)v1));
        m.addInstr(new JumpInstr(rEndLabel));
        m.addInstr(new ExceptionRegionEndMarkerInstr());
        final Label rbLabel = m.getNewLabel();
        rescueLabels.add(rbLabel);
        m.addInstr(new LABEL_Instr(rbLabel));
        final Object v2 = rescueBlock.run(rescueBlockArgs);
        if (v2 != null) {
            m.addInstr(new CopyInstr(rv, (Operand)v1));
        }
        m.addInstr(new LABEL_Instr(rEndLabel));
        return rv;
    }
    
    public Operand buildGetDefinitionBase(final Node node, final IRScope m) {
        switch (node.getNodeType()) {
            case BACKREFNODE:
            case CLASSVARNODE:
            case CLASSVARASGNNODE:
            case CLASSVARDECLNODE:
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
                return this.buildGetDefinition(node, m);
            }
            default: {
                m.addInstr(new JRubyImplCallInstr(null, MethAddr.SET_WITHIN_DEFINED, null, new Operand[] { BooleanLiteral.TRUE }));
                final CodeBlock protectedCode = new CodeBlock() {
                    public Object run(final Object[] args) {
                        return IRBuilder.this.buildGetDefinition((Node)args[0], (IRScope)args[1]);
                    }
                };
                final CodeBlock ensureCode = new CodeBlock() {
                    public Object run(final Object[] args) {
                        final IRScope m = (IRScope)args[0];
                        m.addInstr(new JRubyImplCallInstr(null, MethAddr.SET_WITHIN_DEFINED, null, new Operand[] { BooleanLiteral.FALSE }));
                        return null;
                    }
                };
                final Object[] rvs = this.protectCodeWithEnsure(m, protectedCode, new Object[] { node, m }, ensureCode, new Object[] { m });
                return (Operand)rvs[0];
            }
        }
    }
    
    public Operand buildDefined(final Node node, final IRScope m) {
        return this.buildGetDefinitionBase(((DefinedNode)node).getExpressionNode(), m);
    }
    
    public Operand buildGetArgumentDefinition(final Node node, final IRScope m, final String type) {
        if (node == null) {
            return new StringLiteral(type);
        }
        final Label failLabel = m.getNewLabel();
        final Label doneLabel = m.getNewLabel();
        final Variable rv = m.getNewTemporaryVariable();
        if (node instanceof ArrayNode) {
            for (int i = 0; i < ((ArrayNode)node).size(); ++i) {
                final Node iterNode = ((ArrayNode)node).get(i);
                final Operand def = this.buildGetDefinition(iterNode, m);
                m.addInstr(new BEQInstr(def, Nil.NIL, failLabel));
            }
        }
        else {
            final Operand def2 = this.buildGetDefinition(node, m);
            m.addInstr(new BEQInstr(def2, Nil.NIL, failLabel));
        }
        m.addInstr(new CopyInstr(rv, new StringLiteral(type)));
        m.addInstr(new JumpInstr(doneLabel));
        m.addInstr(new LABEL_Instr(failLabel));
        m.addInstr(new CopyInstr(rv, Nil.NIL));
        m.addInstr(new LABEL_Instr(doneLabel));
        return rv;
    }
    
    private void buildDefinitionCheck(final IRScope s, final Variable tmpVar, final String definedReturnValue) {
        final Label undefLabel = s.getNewLabel();
        final Label defLabel = s.getNewLabel();
        s.addInstr(new BEQInstr(tmpVar, BooleanLiteral.FALSE, undefLabel));
        s.addInstr(new CopyInstr(tmpVar, new StringLiteral(definedReturnValue)));
        s.addInstr(new JumpInstr(defLabel));
        s.addInstr(new LABEL_Instr(undefLabel));
        s.addInstr(new CopyInstr(tmpVar, Nil.NIL));
        s.addInstr(new LABEL_Instr(defLabel));
    }
    
    public Operand buildGetDefinition(final Node node, final IRScope s) {
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
            case OPELEMENTASGNNODE: {
                return new StringLiteral("assignment");
            }
            case DVARNODE: {
                return new StringLiteral("local-variable(in-block)");
            }
            case FALSENODE: {
                return new StringLiteral("false");
            }
            case TRUENODE: {
                return new StringLiteral("true");
            }
            case LOCALVARNODE: {
                return new StringLiteral("local-variable");
            }
            case MATCH2NODE:
            case MATCH3NODE: {
                return new StringLiteral("method");
            }
            case NILNODE: {
                return new StringLiteral("nil");
            }
            case SELFNODE: {
                return new StringLiteral("self");
            }
            case VCALLNODE: {
                final Variable tmp = s.getNewTemporaryVariable();
                s.addInstr(new JRubyImplCallInstr(tmp, new MethAddr("getMetaClass"), this.getSelf(s), IRBuilder.NO_ARGS));
                final Variable tmpVar = s.getNewTemporaryVariable();
                final StringLiteral mName = new StringLiteral(((VCallNode)node).getName());
                s.addInstr(new JRubyImplCallInstr(tmpVar, new MethAddr("isMethodBound"), tmp, new Operand[] { mName, BooleanLiteral.FALSE }));
                this.buildDefinitionCheck(s, tmpVar, "method");
                return tmpVar;
            }
            case CONSTNODE: {
                final Variable tmpVar2 = s.getNewTemporaryVariable();
                final StringLiteral mName2 = new StringLiteral(((ConstNode)node).getName());
                s.addInstr(new JRubyImplCallInstr(tmpVar2, new MethAddr("threadContext_getConstantDefined"), null, new Operand[] { mName2 }));
                this.buildDefinitionCheck(s, tmpVar2, "constant");
                return tmpVar2;
            }
            case GLOBALVARNODE: {
                final Variable tmpVar2 = s.getNewTemporaryVariable();
                final StringLiteral mName2 = new StringLiteral(((GlobalVarNode)node).getName());
                s.addInstr(new JRubyImplCallInstr(tmpVar2, new MethAddr("runtime_isGlobalDefined"), null, new Operand[] { mName2 }));
                this.buildDefinitionCheck(s, tmpVar2, "global-variable");
                return tmpVar2;
            }
            case INSTVARNODE: {
                final Variable tmpVar2 = s.getNewTemporaryVariable();
                final StringLiteral mName2 = new StringLiteral(((GlobalVarNode)node).getName());
                s.addInstr(new JRubyImplCallInstr(tmpVar2, new MethAddr("self_hasInstanceVariable"), this.getSelf(s), new Operand[] { mName2 }));
                this.buildDefinitionCheck(s, tmpVar2, "instance-variable");
                return tmpVar2;
            }
            case YIELDNODE: {
                final Variable tmpVar2 = s.getNewTemporaryVariable();
                s.addInstr(new JRubyImplCallInstr(tmpVar2, new MethAddr("block_isGiven"), null, IRBuilder.NO_ARGS));
                this.buildDefinitionCheck(s, tmpVar2, "yield");
                return tmpVar2;
            }
            case FCALLNODE: {
                final Variable tmpVar2 = s.getNewTemporaryVariable();
                s.addInstr(new JRubyImplCallInstr(tmpVar2, new MethAddr("getMetaClass"), this.getSelf(s), IRBuilder.NO_ARGS));
                final Label undefLabel = s.getNewLabel();
                final Label defLabel = s.getNewLabel();
                final StringLiteral mName3 = new StringLiteral(((FCallNode)node).getName());
                final Instr callInstr = new JRubyImplCallInstr(tmpVar2, new MethAddr("isMethodBound"), tmpVar2, new Operand[] { mName3, BooleanLiteral.FALSE });
                s.addInstr(callInstr);
                s.addInstr(new BEQInstr(tmpVar2, BooleanLiteral.FALSE, undefLabel));
                s.addInstr(new CopyInstr(tmpVar2, this.buildGetArgumentDefinition(((FCallNode)node).getArgsNode(), s, "method")));
                s.addInstr(new JumpInstr(defLabel));
                s.addInstr(new LABEL_Instr(undefLabel));
                s.addInstr(new CopyInstr(tmpVar2, Nil.NIL));
                s.addInstr(new LABEL_Instr(defLabel));
                return tmpVar2;
            }
            case COLON2NODE:
            case COLON3NODE: {
                final Colon3Node iVisited = (Colon3Node)node;
                final String name = iVisited.getName();
                final Variable errInfo = s.getNewTemporaryVariable();
                s.addInstr(new JRubyImplCallInstr(errInfo, new MethAddr("threadContext_stashErrInfo"), null, IRBuilder.NO_ARGS));
                final CodeBlock protectedCode = new CodeBlock() {
                    public Object run(final Object[] args) {
                        final IRScope m = (IRScope)args[0];
                        final Node n = (Node)args[1];
                        final String name = (String)args[2];
                        final Variable tmpVar = m.getNewTemporaryVariable();
                        Operand v;
                        if (n instanceof Colon2Node) {
                            v = IRBuilder.this.build(((Colon2Node)n).getLeftNode(), m);
                        }
                        else {
                            m.addInstr(new JRubyImplCallInstr(tmpVar, new MethAddr("runtime_getObject"), null, IRBuilder.NO_ARGS));
                            v = tmpVar;
                        }
                        m.addInstr(new JRubyImplCallInstr(tmpVar, new MethAddr("getDefinedConstantOrBoundMethod"), null, new Operand[] { v, new StringLiteral(name) }));
                        return tmpVar;
                    }
                };
                final CodeBlock rescueBlock = new CodeBlock() {
                    public Object run(final Object[] args) {
                        final IRScope m = (IRScope)args[0];
                        m.addInstr(new JRubyImplCallInstr(null, new MethAddr("threadContext_restoreErrInfo"), null, new Operand[] { (Variable)args[1] }));
                        return Nil.NIL;
                    }
                };
                return this.protectCodeWithRescue(s, protectedCode, new Object[] { s, iVisited, name }, rescueBlock, new Object[] { s, errInfo });
            }
            default: {
                throw new NotCompilableException(node + " is not yet IR-compilable in buildGetDefinition.");
            }
        }
    }
    
    public Operand buildDAsgn(final DAsgnNode dasgnNode, final IRScope s) {
        final int depth = dasgnNode.getDepth();
        final Variable arg = this.getScopeNDown(s, depth).getLocalVariable(dasgnNode.getName());
        s.addInstr(new CopyInstr(arg, this.build(dasgnNode.getValueNode(), s)));
        return arg;
    }
    
    private IRScope getScopeNDown(final IRScope current, final int depth) {
        for (int i = 0; i < depth; ++i) {
            current.getLexicalParent();
        }
        return current;
    }
    
    private IRMethod defineNewMethod(final MethodDefNode defNode, final IRScope s, final Operand container, final boolean isInstanceMethod) {
        final IRMethod method = new IRMethod(s, container, defNode.getName(), isInstanceMethod, defNode.getScope());
        this.receiveArgs(defNode.getArgsNode(), method);
        if (defNode.getBodyNode() != null) {
            final Node bodyNode = defNode.getBodyNode();
            final Operand rv = (bodyNode instanceof RescueNode) ? this.buildRescueInternal(bodyNode, method, null) : this.build(bodyNode, method);
            if (rv != null) {
                method.addInstr(new ReturnInstr(rv));
            }
        }
        else {
            method.addInstr(new ReturnInstr(Nil.NIL));
        }
        return method;
    }
    
    public Operand buildDefn(final MethodDefNode node, final IRScope s) {
        final Operand container = MetaObject.create(s.getNearestModule());
        final IRMethod method = this.defineNewMethod(node, s, container, true);
        s.getNearestModule().addMethod(method);
        s.addInstr(new DefineInstanceMethodInstr(container, method));
        return Nil.NIL;
    }
    
    public Operand buildDefs(final DefsNode node, final IRScope s) {
        final Operand container = this.build(node.getReceiverNode(), s);
        final IRMethod method = this.defineNewMethod(node, s, container, false);
        if (s.getLexicalParent() instanceof IRModule) {
            ((IRModule)s.getLexicalParent()).addMethod(method);
        }
        s.addInstr(new DefineClassMethodInstr(container, method));
        return Nil.NIL;
    }
    
    public void receiveArgs(final ArgsNode argsNode, final IRScope s) {
        final int required = argsNode.getRequiredArgsCount();
        final int opt = argsNode.getOptionalArgsCount();
        final int rest = argsNode.getRestArg();
        s.addInstr(new ReceiveSelfInstruction(this.getSelf(s)));
        int argIndex = 0;
        final ListNode preArgs = argsNode.getPre();
        for (int i = 0; i < required; ++i, ++argIndex) {
            final ArgumentNode a = (ArgumentNode)preArgs.get(i);
            if (a instanceof TypedArgumentNode) {
                final TypedArgumentNode t = (TypedArgumentNode)a;
                s.addInstr(new DECLARE_LOCAL_TYPE_Instr(argIndex, this.buildType(t.getTypeNode())));
            }
            s.addInstr(new ReceiveArgumentInstruction(s.getLocalVariable(a.getName()), argIndex));
        }
        if (argsNode.getBlock() != null) {
            s.addInstr(new ReceiveClosureInstr(s.getLocalVariable(argsNode.getBlock().getName())));
        }
        if (opt > 0) {
            final ListNode optArgs = argsNode.getOptArgs();
            for (int j = 0; j < opt; ++j, ++argIndex) {
                final Label l = s.getNewLabel();
                final LocalAsgnNode n = (LocalAsgnNode)optArgs.get(j);
                final Variable av = s.getLocalVariable(n.getName());
                s.addInstr(new ReceiveOptionalArgumentInstr(av, argIndex));
                s.addInstr(new BNEInstr(av, Nil.NIL, l));
                this.build(n, s);
                s.addInstr(new LABEL_Instr(l));
            }
        }
        if (rest > -1) {
            s.addInstr(new ReceiveArgumentInstruction(s.getLocalVariable(argsNode.getRestArgNode().getName()), argIndex, true));
        }
    }
    
    public String buildType(final Node typeNode) {
        switch (typeNode.getNodeType()) {
            case CONSTNODE: {
                return ((ConstNode)typeNode).getName();
            }
            case SYMBOLNODE: {
                return ((SymbolNode)typeNode).getName();
            }
            default: {
                return "unknown_type";
            }
        }
    }
    
    public Operand buildDot(final DotNode dotNode, final IRScope s) {
        return new Range(this.build(dotNode.getBeginNode(), s), this.build(dotNode.getEndNode(), s), dotNode.isExclusive());
    }
    
    public Operand buildDRegexp(final DRegexpNode dregexpNode, final IRScope s) {
        final List<Operand> strPieces = new ArrayList<Operand>();
        for (final Node n : dregexpNode.childNodes()) {
            strPieces.add(this.build(n, s));
        }
        return new Regexp(new CompoundString(strPieces), dregexpNode.getOptions());
    }
    
    public Operand buildDStr(final DStrNode dstrNode, final IRScope s) {
        final List<Operand> strPieces = new ArrayList<Operand>();
        for (final Node n : dstrNode.childNodes()) {
            strPieces.add(this.build(n, s));
        }
        return new CompoundString(strPieces);
    }
    
    public Operand buildDSymbol(final Node node, final IRScope s) {
        final List<Operand> strPieces = new ArrayList<Operand>();
        for (final Node n : node.childNodes()) {
            strPieces.add(this.build(n, s));
        }
        return new DynamicSymbol(new CompoundString(strPieces));
    }
    
    public Operand buildDVar(final DVarNode node, final IRScope m) {
        return m.getLocalVariable(node.getName());
    }
    
    public Operand buildDXStr(final DXStrNode dstrNode, final IRScope m) {
        final List<Operand> strPieces = new ArrayList<Operand>();
        for (final Node nextNode : dstrNode.childNodes()) {
            strPieces.add(this.build(nextNode, m));
        }
        return new BacktickString(strPieces);
    }
    
    public Operand buildEnsureNode(final Node node, final IRScope m) {
        final EnsureNode ensureNode = (EnsureNode)node;
        final Node bodyNode = ensureNode.getBodyNode();
        final EnsureBlockInfo ebi = new EnsureBlockInfo(m);
        this._ensureBlockStack.push(ebi);
        final Label rBeginLabel = m.getNewLabel();
        final Label rEndLabel = ebi.end;
        final List<Label> rescueLabels = new ArrayList<Label>() {};
        m.addInstr(new LABEL_Instr(rBeginLabel));
        m.addInstr(new ExceptionRegionStartMarkerInstr(rBeginLabel, rEndLabel, rescueLabels));
        Operand rv = (bodyNode instanceof RescueNode) ? this.buildRescueInternal(bodyNode, m, rBeginLabel) : this.build(bodyNode, m);
        m.addInstr(new ExceptionRegionEndMarkerInstr());
        if (rv != IRBuilder.U_NIL) {
            m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, rEndLabel));
        }
        this._ensureBlockStack.pop();
        m.addInstr(new LABEL_Instr(ebi.start));
        final Operand ensureRetVal = (ensureNode.getEnsureNode() == null) ? Nil.NIL : this.build(ensureNode.getEnsureNode(), m);
        if (ensureRetVal == null) {
            rv = null;
        }
        m.addInstr(new JUMP_INDIRECT_Instr(ebi.returnAddr));
        final Label dummyRescueBlockLabel = m.getNewLabel();
        final Label rethrowExcLabel = m.getNewLabel();
        rescueLabels.add(dummyRescueBlockLabel);
        final Variable exc = m.getNewTemporaryVariable();
        m.addInstr(new LABEL_Instr(dummyRescueBlockLabel));
        m.addInstr(new RECV_EXCEPTION_Instr(exc));
        m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, rethrowExcLabel));
        m.addInstr(new JumpInstr(ebi.start));
        m.addInstr(new LABEL_Instr(rethrowExcLabel));
        m.addInstr(new THROW_EXCEPTION_Instr(exc));
        m.addInstr(new LABEL_Instr(rEndLabel));
        return rv;
    }
    
    public Operand buildEvStr(final EvStrNode node, final IRScope s) {
        return this.build(node.getBody(), s);
    }
    
    public Operand buildFalse(final Node node, final IRScope s) {
        s.addInstr(new ThreadPollInstr());
        return BooleanLiteral.FALSE;
    }
    
    public Operand buildFCall(final FCallNode fcallNode, final IRScope s) {
        final Node callArgsNode = fcallNode.getArgsNode();
        final List<Operand> args = this.setupCallArgs(callArgsNode, s);
        final Operand block = this.setupCallClosure(fcallNode.getIterNode(), s);
        final Variable callResult = s.getNewTemporaryVariable();
        final Instr callInstr = new CallInstr(callResult, new MethAddr(fcallNode.getName()), this.getSelf(s), args.toArray(new Operand[args.size()]), block);
        s.addInstr(callInstr);
        return callResult;
    }
    
    private Operand setupCallClosure(final Node node, final IRScope s) {
        if (node == null) {
            return null;
        }
        switch (node.getNodeType()) {
            case ITERNODE: {
                return this.build(node, s);
            }
            case BLOCKPASSNODE: {
                return this.build(((BlockPassNode)node).getBodyNode(), s);
            }
            default: {
                throw new NotCompilableException("ERROR: Encountered a method with a non-block, non-blockpass iter node at: " + node);
            }
        }
    }
    
    public Operand buildFixnum(final FixnumNode node, final IRScope m) {
        return new Fixnum(Long.valueOf(node.getValue()));
    }
    
    public Operand buildFloat(final FloatNode node, final IRScope m) {
        return new Float(node.getValue());
    }
    
    public Operand buildFor(final ForNode forNode, final IRExecutionScope m) {
        final Variable ret = m.getNewTemporaryVariable();
        final Operand receiver = this.build(forNode.getIterNode(), m);
        final Operand forBlock = this.buildForIter(forNode, m);
        m.addInstr(new RubyInternalCallInstr(ret, MethAddr.FOR_EACH, receiver, IRBuilder.NO_ARGS, forBlock));
        return ret;
    }
    
    public Operand buildForIter(final ForNode forNode, final IRExecutionScope s) {
        final IRClosure closure = new IRClosure(s, forNode.getScope(), Arity.procArityOf(forNode.getVarNode()), forNode.getArgumentType());
        s.addClosure(closure);
        NodeType argsNodeId = null;
        if (forNode.getVarNode() != null) {
            argsNodeId = forNode.getVarNode().getNodeType();
            if (argsNodeId != null) {
                this.buildBlockArgsAssignment(forNode.getVarNode(), closure, 0, false);
            }
        }
        final Operand closureRetVal = (forNode.getBodyNode() == null) ? Nil.NIL : this.build(forNode.getBodyNode(), closure);
        if (closureRetVal != null) {
            closure.addInstr(new ClosureReturnInstr(closureRetVal));
        }
        return MetaObject.create(closure);
    }
    
    public Operand buildGlobalAsgn(final GlobalAsgnNode globalAsgnNode, final IRScope m) {
        final Operand value = this.build(globalAsgnNode.getValueNode(), m);
        m.addInstr(new PutGlobalVarInstr(globalAsgnNode.getName(), value));
        return value;
    }
    
    public Operand buildGlobalVar(final GlobalVarNode node, final IRScope m) {
        final Variable rv = m.getNewTemporaryVariable();
        m.addInstr(new GetGlobalVariableInstr(rv, node.getName()));
        return rv;
    }
    
    public Operand buildHash(final HashNode hashNode, final IRScope m) {
        if (hashNode.getListNode() == null || hashNode.getListNode().size() == 0) {
            return new Hash(new ArrayList<KeyValuePair>());
        }
        final int i = 0;
        Operand key = null;
        final Operand value = null;
        final List<KeyValuePair> args = new ArrayList<KeyValuePair>();
        for (final Node nextNode : hashNode.getListNode().childNodes()) {
            final Operand v = this.build(nextNode, m);
            if (key == null) {
                key = v;
            }
            else {
                args.add(new KeyValuePair(key, v));
                key = null;
            }
        }
        return new Hash(args);
    }
    
    public Operand buildIf(final IfNode ifNode, final IRScope s) {
        final Node actualCondition = this.skipOverNewlines(s, ifNode.getCondition());
        final Variable result = s.getNewTemporaryVariable();
        final Label falseLabel = s.getNewLabel();
        final Label doneLabel = s.getNewLabel();
        Operand thenResult = null;
        s.addInstr(new BEQInstr(this.build(actualCondition, s), BooleanLiteral.FALSE, falseLabel));
        boolean thenNull = false;
        boolean elseNull = false;
        boolean thenUnil = false;
        boolean elseUnil = false;
        if (ifNode.getThenBody() != null) {
            thenResult = this.build(ifNode.getThenBody(), s);
            if (thenResult != IRBuilder.U_NIL) {
                Label tgt = doneLabel;
                if (thenResult instanceof BreakResult) {
                    final BreakResult br = (BreakResult)thenResult;
                    thenResult = br._result;
                    tgt = br._jumpTarget;
                }
                s.addInstr(new CopyInstr(result, thenResult));
                s.addInstr(new JumpInstr(tgt));
            }
            else {
                thenUnil = true;
            }
        }
        else {
            thenNull = true;
            s.addInstr(new CopyInstr(result, Nil.NIL));
            s.addInstr(new JumpInstr(doneLabel));
        }
        s.addInstr(new LABEL_Instr(falseLabel));
        if (ifNode.getElseBody() != null) {
            final Operand elseResult = this.build(ifNode.getElseBody(), s);
            if (elseResult != IRBuilder.U_NIL) {
                s.addInstr(new CopyInstr(result, elseResult));
            }
            else {
                elseUnil = true;
            }
        }
        else {
            elseNull = true;
            s.addInstr(new CopyInstr(result, Nil.NIL));
        }
        if (thenNull && elseNull) {
            return Nil.NIL;
        }
        if (thenUnil && elseUnil) {
            return IRBuilder.U_NIL;
        }
        s.addInstr(new LABEL_Instr(doneLabel));
        return result;
    }
    
    public Operand buildInstAsgn(final InstAsgnNode instAsgnNode, final IRScope s) {
        final Operand val = this.build(instAsgnNode.getValueNode(), s);
        s.addInstr(new PutFieldInstr(this.getSelf(s), instAsgnNode.getName(), val));
        return val;
    }
    
    public Operand buildInstVar(final InstVarNode node, final IRScope m) {
        final Variable ret = m.getNewTemporaryVariable();
        m.addInstr(new GetFieldInstr(ret, this.getSelf(m), node.getName()));
        return ret;
    }
    
    public Operand buildIter(final IterNode iterNode, final IRExecutionScope s) {
        final IRClosure closure = new IRClosure(s, iterNode.getScope(), Arity.procArityOf(iterNode.getVarNode()), iterNode.getArgumentType());
        s.addClosure(closure);
        final NodeType argsNodeId = BlockBody.getArgumentTypeWackyHack(iterNode);
        if (iterNode.getVarNode() != null && argsNodeId != null) {
            this.buildBlockArgsAssignment(iterNode.getVarNode(), closure, 0, false);
        }
        final Operand closureRetVal = (iterNode.getBodyNode() == null) ? Nil.NIL : this.build(iterNode.getBodyNode(), closure);
        if (closureRetVal != IRBuilder.U_NIL) {
            closure.addInstr(new ClosureReturnInstr(closureRetVal));
        }
        return MetaObject.create(closure);
    }
    
    public Operand buildLiteral(final LiteralNode literalNode, final IRScope s) {
        return new StringLiteral(literalNode.getName());
    }
    
    public Operand buildLocalAsgn(final LocalAsgnNode localAsgnNode, final IRScope s) {
        final Operand value = this.build(localAsgnNode.getValueNode(), s);
        s.addInstr(new CopyInstr(s.getLocalVariable(localAsgnNode.getName()), value));
        return value;
    }
    
    public Operand buildLocalVar(final LocalVarNode node, final IRScope s) {
        return s.getLocalVariable(node.getName());
    }
    
    public Operand buildMatch(final MatchNode matchNode, final IRScope m) {
        final Operand regexp = this.build(matchNode.getRegexpNode(), m);
        return this.generateJRubyUtilityCall(m, MethAddr.MATCH, regexp, IRBuilder.NO_ARGS);
    }
    
    public Operand buildMatch2(final Match2Node matchNode, final IRScope m) {
        final Operand receiver = this.build(matchNode.getReceiverNode(), m);
        final Operand value = this.build(matchNode.getValueNode(), m);
        return this.generateJRubyUtilityCall(m, MethAddr.MATCH2, receiver, new Operand[] { value });
    }
    
    public Operand buildMatch3(final Match3Node matchNode, final IRScope m) {
        final Operand receiver = this.build(matchNode.getReceiverNode(), m);
        final Operand value = this.build(matchNode.getValueNode(), m);
        return this.generateJRubyUtilityCall(m, MethAddr.MATCH3, receiver, new Operand[] { value });
    }
    
    private Operand getContainerFromCPath(final Colon3Node cpath, final IRScope s) {
        Operand container = null;
        if (cpath instanceof Colon2Node) {
            final Node leftNode = ((Colon2Node)cpath).getLeftNode();
            if (leftNode != null) {
                container = this.build(leftNode, s);
            }
        }
        else {
            container = MetaObject.create(IRModule.getCoreClass("Object"));
        }
        return container;
    }
    
    public Operand buildModule(final ModuleNode moduleNode, final IRScope s) {
        final Colon3Node cpath = moduleNode.getCPath();
        final String moduleName = cpath.getName();
        final Operand container = this.getContainerFromCPath(cpath, s);
        final IRModule m = new IRModule(s, container, moduleName, moduleNode.getScope());
        s.getNearestModule().getRootMethod().addInstr(new DefineModuleInstr((ModuleMetaObject)MetaObject.create(m)));
        s.getNearestModule().addModule(m);
        this.build(moduleNode.getBodyNode(), m.getRootMethod());
        return Nil.NIL;
    }
    
    public Operand buildMultipleAsgn(final MultipleAsgnNode multipleAsgnNode, final IRScope s) {
        final Operand values = this.build(multipleAsgnNode.getValueNode(), s);
        final Variable ret = s.getNewTemporaryVariable();
        s.addInstr(new CopyInstr(ret, values));
        this.buildMultipleAsgnAssignment(multipleAsgnNode, s, ret);
        return ret;
    }
    
    public void buildMultipleAsgnAssignment(final MultipleAsgnNode multipleAsgnNode, final IRScope s, final Operand values) {
        final ListNode sourceArray = multipleAsgnNode.getHeadNode();
        int i = 0;
        if (sourceArray != null) {
            final ListNode headNode = sourceArray;
            for (final Node an : headNode.childNodes()) {
                if (values == null) {
                    this.buildBlockArgsAssignment(an, s, i, false);
                }
                else {
                    this.buildAssignment(an, s, values, i, false);
                }
                ++i;
            }
        }
        final Node an2 = multipleAsgnNode.getArgsNode();
        if (an2 == null) {
            if (sourceArray == null) {
                throw new NotCompilableException("Something's wrong, multiple assignment with no head or args at: " + multipleAsgnNode.getPosition());
            }
        }
        else if (!(an2 instanceof StarNode)) {
            if (values != null) {
                this.buildAssignment(an2, s, values, i, true);
            }
            else {
                this.buildBlockArgsAssignment(an2, s, i, true);
            }
        }
    }
    
    public Operand buildNewline(final NewlineNode node, final IRScope s) {
        return this.build(this.skipOverNewlines(s, node), s);
    }
    
    public Operand buildNext(final NextNode nextNode, final IRExecutionScope s) {
        final Operand rv = (nextNode.getValueNode() == null) ? Nil.NIL : this.build(nextNode.getValueNode(), s);
        s.addInstr(new ThreadPollInstr());
        s.addInstr((s instanceof IRClosure) ? new ClosureReturnInstr(rv) : new JumpInstr(s.getCurrentLoop().iterEndLabel));
        return rv;
    }
    
    public Operand buildNthRef(final NthRefNode nthRefNode, final IRScope m) {
        return new NthRef(nthRefNode.getMatchNumber());
    }
    
    public Operand buildNil(final Node node, final IRScope m) {
        m.addInstr(new ThreadPollInstr());
        return Nil.NIL;
    }
    
    public Operand buildNot(final NotNode node, final IRScope m) {
        final Variable ret = m.getNewTemporaryVariable();
        m.addInstr(new NotInstr(ret, this.build(node.getConditionNode(), m)));
        return ret;
    }
    
    public Operand buildOpAsgn(final OpAsgnNode opAsgnNode, final IRScope s) {
        if (opAsgnNode.getOperatorName().equals("||") || opAsgnNode.getOperatorName().equals("&&")) {
            throw new NotCompilableException("Unknown node encountered in builder: " + opAsgnNode);
        }
        final Operand v1 = this.build(opAsgnNode.getReceiverNode(), s);
        final Variable getResult = s.getNewTemporaryVariable();
        Instr callInstr = new CallInstr(getResult, new MethAddr(opAsgnNode.getVariableName()), v1, IRBuilder.NO_ARGS, null);
        s.addInstr(callInstr);
        final Operand v2 = this.build(opAsgnNode.getValueNode(), s);
        final Variable setValue = s.getNewTemporaryVariable();
        callInstr = new CallInstr(setValue, new MethAddr(opAsgnNode.getOperatorName()), getResult, new Operand[] { v2 }, null);
        s.addInstr(callInstr);
        final Variable setResult = s.getNewTemporaryVariable();
        callInstr = new CallInstr(setResult, new MethAddr(opAsgnNode.getVariableNameAsgn()), v1, new Operand[] { setValue }, null);
        s.addInstr(callInstr);
        return setResult;
    }
    
    public Operand buildOpAsgnAnd(final OpAsgnAndNode andNode, final IRScope s) {
        final Label l = s.getNewLabel();
        final Operand v1 = this.build(andNode.getFirstNode(), s);
        final Variable f = s.getNewTemporaryVariable();
        s.addInstr(new IsTrueInstr(f, v1));
        s.addInstr(new BEQInstr(f, BooleanLiteral.FALSE, l));
        this.build(andNode.getSecondNode(), s);
        s.addInstr(new LABEL_Instr(l));
        s.addInstr(new ThreadPollInstr());
        return v1;
    }
    
    public Operand buildOpAsgnOr(final OpAsgnOrNode orNode, final IRScope s) {
        final Label l1 = s.getNewLabel();
        Label l2 = null;
        final Variable f = s.getNewTemporaryVariable();
        final boolean needsDefnCheck = this.needsDefinitionCheck(orNode.getFirstNode());
        if (needsDefnCheck) {
            l2 = s.getNewLabel();
            final Operand v1 = this.buildGetDefinitionBase(orNode.getFirstNode(), s);
            s.addInstr(new BEQInstr(v1, Nil.NIL, l2));
        }
        final Operand v1 = this.build(orNode.getFirstNode(), s);
        s.addInstr(new IsTrueInstr(f, v1));
        s.addInstr(new BEQInstr(f, BooleanLiteral.TRUE, l1));
        if (needsDefnCheck) {
            s.addInstr(new LABEL_Instr(l2));
        }
        this.build(orNode.getSecondNode(), s);
        s.addInstr(new LABEL_Instr(l1));
        s.addInstr(new ThreadPollInstr());
        return v1;
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
    
    public Operand buildOpElementAsgn(final Node node, final IRScope m) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        if (opElementAsgnNode.getOperatorName() == "||") {
            return this.buildOpElementAsgnWithOr(node, m);
        }
        if (opElementAsgnNode.getOperatorName() == "&&") {
            return this.buildOpElementAsgnWithAnd(node, m);
        }
        return this.buildOpElementAsgnWithMethod(node, m);
    }
    
    public Operand buildOpElementAsgnWithOr(final Node node, final IRScope s) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final Operand array = this.build(opElementAsgnNode.getReceiverNode(), s);
        final Label l = s.getNewLabel();
        final Variable elt = s.getNewTemporaryVariable();
        final Variable flag = s.getNewTemporaryVariable();
        final List<Operand> args = this.setupCallArgs(opElementAsgnNode.getArgsNode(), s);
        assert args.size() == 1;
        final Operand index = args.get(0);
        s.addInstr(new CallInstr(elt, new MethAddr("[]"), array, new Operand[] { index }, null));
        s.addInstr(new IsTrueInstr(flag, elt));
        s.addInstr(new BEQInstr(flag, BooleanLiteral.TRUE, l));
        final Operand value = this.build(opElementAsgnNode.getValueNode(), s);
        s.addInstr(new CallInstr(elt, new MethAddr("[]="), array, new Operand[] { index, value }, null));
        s.addInstr(new CopyInstr(elt, value));
        s.addInstr(new LABEL_Instr(l));
        return elt;
    }
    
    public Operand buildOpElementAsgnWithAnd(final Node node, final IRScope s) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final Operand array = this.build(opElementAsgnNode.getReceiverNode(), s);
        final Label l = s.getNewLabel();
        final Variable elt = s.getNewTemporaryVariable();
        final Variable flag = s.getNewTemporaryVariable();
        final List<Operand> args = this.setupCallArgs(opElementAsgnNode.getArgsNode(), s);
        assert args.size() == 1;
        final Operand index = args.get(0);
        s.addInstr(new CallInstr(elt, new MethAddr("[]"), array, new Operand[] { index }, null));
        s.addInstr(new IsTrueInstr(flag, elt));
        s.addInstr(new BEQInstr(flag, BooleanLiteral.FALSE, l));
        final Operand value = this.build(opElementAsgnNode.getValueNode(), s);
        s.addInstr(new CallInstr(elt, new MethAddr("[]="), array, new Operand[] { index, value }, null));
        s.addInstr(new CopyInstr(elt, value));
        s.addInstr(new LABEL_Instr(l));
        return elt;
    }
    
    public Operand buildOpElementAsgnWithMethod(final Node node, final IRScope s) {
        final OpElementAsgnNode opElementAsgnNode = (OpElementAsgnNode)node;
        final Operand array = this.build(opElementAsgnNode.getReceiverNode(), s);
        final List<Operand> args = this.setupCallArgs(opElementAsgnNode.getArgsNode(), s);
        assert args.size() == 1;
        final Operand index = args.get(0);
        final Variable elt = s.getNewTemporaryVariable();
        s.addInstr(new CallInstr(elt, new MethAddr("[]"), array, new Operand[] { index }, null));
        final Operand value = this.build(opElementAsgnNode.getValueNode(), s);
        final String operation = opElementAsgnNode.getOperatorName();
        s.addInstr(new CallInstr(elt, new MethAddr(operation), elt, new Operand[] { value }, null));
        final Variable tmp = s.getNewTemporaryVariable();
        s.addInstr(new CallInstr(tmp, new MethAddr("[]="), array, new Operand[] { index, elt }, null));
        return elt;
    }
    
    public Operand buildOr(final OrNode orNode, final IRScope m) {
        if (orNode.getFirstNode().getNodeType().alwaysTrue()) {
            return this.build(orNode.getFirstNode(), m);
        }
        if (orNode.getFirstNode().getNodeType().alwaysFalse()) {
            this.build(orNode.getFirstNode(), m);
            return this.build(orNode.getSecondNode(), m);
        }
        final Variable ret = m.getNewTemporaryVariable();
        final Label l = m.getNewLabel();
        final Operand v1 = this.build(orNode.getFirstNode(), m);
        m.addInstr(new CopyInstr(ret, v1));
        m.addInstr(new BEQInstr(v1, BooleanLiteral.TRUE, l));
        final Operand v2 = this.build(orNode.getSecondNode(), m);
        m.addInstr(new CopyInstr(ret, v2));
        m.addInstr(new LABEL_Instr(l));
        return ret;
    }
    
    public Operand buildRedo(final Node node, final IRExecutionScope s) {
        s.addInstr(new JumpInstr((s instanceof IRClosure) ? ((IRClosure)s).startLabel : s.getCurrentLoop().iterStartLabel));
        return Nil.NIL;
    }
    
    public Operand buildRegexp(final RegexpNode reNode, final IRScope m) {
        return new Regexp(new StringLiteral(reNode.getValue()), reNode.getOptions());
    }
    
    public Operand buildRescue(final Node node, final IRScope m) {
        return this.buildRescueInternal(node, m, null);
    }
    
    private Operand buildRescueInternal(final Node node, final IRScope m, final Label availableBeginLabel) {
        final RescueNode rescueNode = (RescueNode)node;
        final boolean noEnsure = this._ensureBlockStack.empty();
        final EnsureBlockInfo ebi = noEnsure ? null : this._ensureBlockStack.peek();
        final Label rBeginLabel = (availableBeginLabel != null) ? availableBeginLabel : m.getNewLabel();
        final Label rEndLabel = noEnsure ? m.getNewLabel() : ebi.end;
        final Label elseLabel = (rescueNode.getElseNode() == null) ? null : m.getNewLabel();
        if (availableBeginLabel == null) {
            m.addInstr(new LABEL_Instr(rBeginLabel));
        }
        final List<Label> rescueBlockLabels = new ArrayList<Label>();
        final ExceptionRegionStartMarkerInstr rbStartInstr = new ExceptionRegionStartMarkerInstr(rBeginLabel, rEndLabel, rescueBlockLabels);
        m.addInstr(rbStartInstr);
        Operand tmp = Nil.NIL;
        final Variable rv = m.getNewTemporaryVariable();
        if (rescueNode.getBodyNode() != null) {
            tmp = this.build(rescueNode.getBodyNode(), m);
        }
        if (elseLabel != null) {
            m.addInstr(new LABEL_Instr(elseLabel));
            tmp = this.build(rescueNode.getElseNode(), m);
        }
        if (tmp != IRBuilder.U_NIL) {
            m.addInstr(new CopyInstr(rv, tmp));
            if (noEnsure) {
                m.addInstr(new JumpInstr(rEndLabel));
            }
            else {
                m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, rEndLabel));
                m.addInstr(new JumpInstr(ebi.start));
            }
        }
        final ExceptionRegionEndMarkerInstr rbEndInstr = new ExceptionRegionEndMarkerInstr();
        m.addInstr(rbEndInstr);
        final Label rbLabel = m.getNewLabel();
        rescueBlockLabels.add(rbLabel);
        m.addInstr(new LABEL_Instr(rbLabel));
        this.buildRescueBodyInternal(m, rescueNode.getRescueNode(), rv, rEndLabel, rescueBlockLabels);
        if (noEnsure) {
            m.addInstr(new LABEL_Instr(rEndLabel));
        }
        return rv;
    }
    
    private void buildRescueBodyInternal(final IRScope m, final Node node, final Variable rv, final Label endLabel, final List<Label> rescueBlockLabels) {
        final RescueBodyNode rescueBodyNode = (RescueBodyNode)node;
        final Node exceptionList = rescueBodyNode.getExceptionNodes();
        final Variable exc = m.getNewTemporaryVariable();
        m.addInstr(new RECV_EXCEPTION_Instr(exc));
        Label uncaughtLabel = null;
        if (exceptionList != null) {
            uncaughtLabel = m.getNewLabel();
            final Variable eqqResult = m.getNewTemporaryVariable();
            for (final Node excType : ((ListNode)exceptionList).childNodes()) {
                m.addInstr(new EQQInstr(eqqResult, this.build(excType, m), exc));
                m.addInstr(new BEQInstr(eqqResult, BooleanLiteral.FALSE, uncaughtLabel));
            }
        }
        final Node realBody = this.skipOverNewlines(m, rescueBodyNode.getBodyNode());
        final Operand x = this.build(realBody, m);
        if (x != IRBuilder.U_NIL) {
            m.addInstr(new CopyInstr(rv, x));
            if (!this._ensureBlockStack.empty()) {
                final EnsureBlockInfo ebi = this._ensureBlockStack.peek();
                m.addInstr(new SET_RETADDR_Instr(ebi.returnAddr, endLabel));
                m.addInstr(new JumpInstr(ebi.start));
            }
            else {
                m.addInstr(new JumpInstr(endLabel));
            }
        }
        if (uncaughtLabel != null) {
            rescueBlockLabels.add(uncaughtLabel);
            m.addInstr(new LABEL_Instr(uncaughtLabel));
            if (rescueBodyNode.getOptRescueNode() != null) {
                this.buildRescueBodyInternal(m, rescueBodyNode.getOptRescueNode(), rv, endLabel, rescueBlockLabels);
            }
            else {
                m.addInstr(new THROW_EXCEPTION_Instr(exc));
            }
        }
    }
    
    public Operand buildRetry(final Node node, final IRScope s) {
        s.addInstr(new ThreadPollInstr());
        if (this._rescueBlockLabelStack.empty()) {
            final StringLiteral exc = new StringLiteral("retry found outside of rescue clause!");
            s.addInstr(new THROW_EXCEPTION_Instr(exc));
        }
        else {
            s.addInstr(new JumpInstr(this._rescueBlockLabelStack.peek()));
        }
        return Nil.NIL;
    }
    
    public Operand buildReturn(final ReturnNode returnNode, final IRScope m) {
        final Operand retVal = (returnNode.getValueNode() == null) ? Nil.NIL : this.build(returnNode.getValueNode(), m);
        if (!this._ensureBlockStack.empty()) {
            EnsureBlockInfo.emitJumpChain(m, this._ensureBlockStack);
        }
        m.addInstr(new ReturnInstr(retVal));
        return UnexecutableNil.U_NIL;
    }
    
    public IRScope buildRoot(final RootNode rootNode) {
        final String file = rootNode.getPosition().getFile();
        final StaticScope staticScope = rootNode.getStaticScope();
        final IRScript script = new IRScript("__file__", file, rootNode.getStaticScope());
        final IRClass rootClass = script.getRootClass();
        final IRMethod rootMethod = rootClass.getRootMethod();
        rootMethod.addInstr(new FilenameInstr(file));
        rootMethod.addInstr(new ReceiveSelfInstruction(this.getSelf(rootMethod)));
        this.build(rootNode.getBodyNode(), rootMethod);
        return script;
    }
    
    private Variable getSelf(final IRScope s) {
        return ((IRExecutionScope)s).getSelf();
    }
    
    public Operand buildSelf(final Node node, final IRScope s) {
        return this.getSelf(s);
    }
    
    public Operand buildSplat(final SplatNode splatNode, final IRScope s) {
        return new Splat(this.build(splatNode.getValue(), s));
    }
    
    public Operand buildStr(final StrNode strNode, final IRScope s) {
        return new StringLiteral(strNode.getValue());
    }
    
    public Operand buildSuper(final SuperNode superNode, final IRScope s) {
        final List<Operand> args = this.setupCallArgs(superNode.getArgsNode(), s);
        final Operand block = this.setupCallClosure(superNode.getIterNode(), s);
        final Variable ret = s.getNewTemporaryVariable();
        s.addInstr(new RubyInternalCallInstr(ret, MethAddr.SUPER, this.getSelf(s), args.toArray(new Operand[args.size()]), block));
        return ret;
    }
    
    public Operand buildSValue(final SValueNode node, final IRScope s) {
        return new SValue(this.build(node.getValue(), s));
    }
    
    public Operand buildSymbol(final SymbolNode node, final IRScope s) {
        return new Symbol(node.getName());
    }
    
    public Operand buildToAry(final ToAryNode node, final IRScope s) {
        final Operand array = this.build(node.getValue(), s);
        return this.generateJRubyUtilityCall(s, MethAddr.TO_ARY, array, IRBuilder.NO_ARGS);
    }
    
    public Operand buildTrue(final Node node, final IRScope m) {
        m.addInstr(new ThreadPollInstr());
        return BooleanLiteral.TRUE;
    }
    
    public Operand buildUndef(final Node node, final IRScope m) {
        final Operand methName = this.build(((UndefNode)node).getName(), m);
        return this.generateJRubyUtilityCall(m, MethAddr.UNDEF_METHOD, methName, IRBuilder.NO_ARGS);
    }
    
    private Operand buildConditionalLoop(final IRExecutionScope s, final Node conditionNode, final Node bodyNode, final boolean isWhile, final boolean isLoopHeadCondition) {
        if (isLoopHeadCondition && ((isWhile && conditionNode.getNodeType().alwaysFalse()) || (!isWhile && conditionNode.getNodeType().alwaysTrue()))) {
            this.build(conditionNode, s);
            return Nil.NIL;
        }
        final IRLoop loop = new IRLoop(s);
        s.startLoop(loop);
        s.addInstr(new LABEL_Instr(loop.loopStartLabel));
        if (isLoopHeadCondition) {
            final Operand cv = this.build(conditionNode, s);
            s.addInstr(new BEQInstr(cv, isWhile ? BooleanLiteral.FALSE : BooleanLiteral.TRUE, loop.loopEndLabel));
        }
        s.addInstr(new LABEL_Instr(loop.iterStartLabel));
        final Variable whileResult = s.getNewTemporaryVariable();
        if (bodyNode != null) {
            final Operand v = this.build(bodyNode, s);
            if (v != IRBuilder.U_NIL) {
                s.addInstr(new CopyInstr(whileResult, v));
            }
        }
        s.addInstr(new ThreadPollInstr());
        s.addInstr(new LABEL_Instr(loop.iterEndLabel));
        if (isLoopHeadCondition) {
            s.addInstr(new JumpInstr(loop.loopStartLabel));
        }
        else {
            final Operand cv2 = this.build(conditionNode, s);
            s.addInstr(new BEQInstr(cv2, isWhile ? BooleanLiteral.TRUE : BooleanLiteral.FALSE, loop.iterStartLabel));
        }
        s.addInstr(new LABEL_Instr(loop.loopEndLabel));
        s.endLoop(loop);
        return whileResult;
    }
    
    public Operand buildUntil(final UntilNode untilNode, final IRExecutionScope s) {
        return this.buildConditionalLoop(s, untilNode.getConditionNode(), untilNode.getBodyNode(), false, untilNode.evaluateAtStart());
    }
    
    public Operand buildVAlias(final Node node, final IRScope m) {
        final VAliasNode valiasNode = (VAliasNode)node;
        final Operand[] args = { new StringLiteral(valiasNode.getOldName()) };
        m.addInstr(new RubyInternalCallInstr(null, MethAddr.GVAR_ALIAS, new StringLiteral(valiasNode.getNewName()), args));
        return Nil.NIL;
    }
    
    public Operand buildVCall(final VCallNode node, final IRScope s) {
        final List<Operand> args = new ArrayList<Operand>();
        args.add(this.getSelf(s));
        final Variable callResult = s.getNewTemporaryVariable();
        final Instr callInstr = new CallInstr(callResult, new MethAddr(node.getName()), this.getSelf(s), IRBuilder.NO_ARGS, null);
        s.addInstr(callInstr);
        return callResult;
    }
    
    public Operand buildWhile(final WhileNode whileNode, final IRExecutionScope s) {
        return this.buildConditionalLoop(s, whileNode.getConditionNode(), whileNode.getBodyNode(), true, whileNode.evaluateAtStart());
    }
    
    public Operand buildXStr(final XStrNode node, final IRScope m) {
        return new BacktickString(new StringLiteral(node.getValue()));
    }
    
    public Operand buildYield(final YieldNode node, final IRScope s) {
        final Variable ret = s.getNewTemporaryVariable();
        s.addInstr(new YieldInstr(ret, this.build(node.getArgsNode(), s)));
        return ret;
    }
    
    public Operand buildZArray(final Node node, final IRScope m) {
        return new Array();
    }
    
    public Operand buildZSuper(final ZSuperNode zsuperNode, final IRScope s) {
        final Operand block = this.setupCallClosure(zsuperNode.getIterNode(), s);
        final Variable ret = s.getNewTemporaryVariable();
        s.addInstr(new RubyInternalCallInstr(ret, MethAddr.ZSUPER, this.getSelf(s), ((IRExecutionScope)s).getClosestMethodAncestor().getCallArgs(), block));
        return ret;
    }
    
    public void buildArgsCatArguments(final List<Operand> args, final ArgsCatNode argsCatNode, final IRScope s) {
        final Operand v1 = this.build(argsCatNode.getFirstNode(), s);
        final Operand v2 = this.build(argsCatNode.getSecondNode(), s);
        args.add(new CompoundArray(v1, v2));
    }
    
    public void buildArgsPushArguments(final List<Operand> args, final ArgsPushNode argsPushNode, final IRScope m) {
        final Operand a = new Array(new Operand[] { this.build(argsPushNode.getFirstNode(), m), this.build(argsPushNode.getSecondNode(), m) });
        args.add(a);
    }
    
    public void buildArrayArguments(final List<Operand> args, final Node node, final IRScope s) {
        args.add(this.buildArray(node, s));
    }
    
    public void buildSplatArguments(final List<Operand> args, final SplatNode node, final IRScope s) {
        args.add(this.buildSplat(node, s));
    }
    
    static {
        U_NIL = UnexecutableNil.U_NIL;
        NO_ARGS = new Operand[0];
    }
    
    private static class EnsureBlockInfo
    {
        Label start;
        Label end;
        Variable returnAddr;
        
        public EnsureBlockInfo(final IRScope m) {
            this.returnAddr = m.getNewTemporaryVariable();
            this.start = m.getNewLabel();
            this.end = m.getNewLabel();
        }
        
        public static void emitJumpChain(final IRScope m, final Stack<EnsureBlockInfo> ebStack) {
            final int n = ebStack.size();
            final EnsureBlockInfo[] ebArray = ebStack.toArray(new EnsureBlockInfo[n]);
            for (int i = n - 1; i >= 0; --i) {
                final Label retLabel = m.getNewLabel();
                m.addInstr(new SET_RETADDR_Instr(ebArray[i].returnAddr, retLabel));
                m.addInstr(new JumpInstr(ebArray[i].start));
                m.addInstr(new LABEL_Instr(retLabel));
            }
        }
    }
    
    interface CodeBlock
    {
        Object run(final Object[] p0);
    }
}

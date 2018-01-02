// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.visitor;

import org.jruby.ast.ZSuperNode;
import org.jruby.ast.ZArrayNode;
import org.jruby.ast.YieldNode;
import org.jruby.ast.XStrNode;
import org.jruby.ast.WhileNode;
import org.jruby.ast.WhenNode;
import org.jruby.ast.VCallNode;
import org.jruby.ast.VAliasNode;
import org.jruby.ast.UntilNode;
import org.jruby.ast.UndefNode;
import org.jruby.ast.TrueNode;
import org.jruby.ast.ToAryNode;
import org.jruby.ast.SymbolNode;
import org.jruby.ast.SValueNode;
import org.jruby.ast.SuperNode;
import org.jruby.ast.StrNode;
import org.jruby.ast.SplatNode;
import org.jruby.ast.SelfNode;
import org.jruby.ast.SClassNode;
import org.jruby.ast.RootNode;
import org.jruby.ast.ReturnNode;
import org.jruby.ast.RetryNode;
import org.jruby.ast.RestArgNode;
import org.jruby.ast.RescueNode;
import org.jruby.ast.RescueBodyNode;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.RedoNode;
import org.jruby.ast.PostExeNode;
import org.jruby.ast.PreExeNode;
import org.jruby.ast.OrNode;
import org.jruby.ast.OpAsgnOrNode;
import org.jruby.ast.OpAsgnAndNode;
import org.jruby.ast.OpAsgnNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.ast.NthRefNode;
import org.jruby.ast.NotNode;
import org.jruby.ast.NilNode;
import org.jruby.ast.NextNode;
import org.jruby.ast.NewlineNode;
import org.jruby.ast.ModuleNode;
import org.jruby.ast.MatchNode;
import org.jruby.ast.Match3Node;
import org.jruby.ast.Match2Node;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.LocalVarNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.LiteralNode;
import org.jruby.ast.IterNode;
import org.jruby.ast.IfNode;
import org.jruby.ast.InstVarNode;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.HashNode;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.ForNode;
import org.jruby.ast.FloatNode;
import org.jruby.ast.FlipNode;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.FalseNode;
import org.jruby.ast.FCallNode;
import org.jruby.ast.EvStrNode;
import org.jruby.ast.EnsureNode;
import org.jruby.ast.EncodingNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.DefsNode;
import org.jruby.ast.DefnNode;
import org.jruby.ast.DefinedNode;
import org.jruby.ast.DXStrNode;
import org.jruby.ast.DVarNode;
import org.jruby.ast.DSymbolNode;
import org.jruby.ast.DStrNode;
import org.jruby.ast.DRegexpNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.Colon3Node;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.ClassNode;
import org.jruby.ast.CaseNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.ClassVarNode;
import org.jruby.ast.ClassVarDeclNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.ast.BreakNode;
import org.jruby.ast.BlockPassNode;
import org.jruby.ast.BlockNode;
import org.jruby.ast.BlockArg18Node;
import org.jruby.ast.BlockArgNode;
import org.jruby.ast.BignumNode;
import org.jruby.ast.BeginNode;
import org.jruby.ast.BackRefNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ArgsCatNode;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.AndNode;
import org.jruby.ast.AliasNode;

public interface NodeVisitor
{
    Object visitAliasNode(final AliasNode p0);
    
    Object visitAndNode(final AndNode p0);
    
    Object visitArgsNode(final ArgsNode p0);
    
    Object visitArgsCatNode(final ArgsCatNode p0);
    
    Object visitArgsPushNode(final ArgsPushNode p0);
    
    Object visitArrayNode(final ArrayNode p0);
    
    Object visitAttrAssignNode(final AttrAssignNode p0);
    
    Object visitBackRefNode(final BackRefNode p0);
    
    Object visitBeginNode(final BeginNode p0);
    
    Object visitBignumNode(final BignumNode p0);
    
    Object visitBlockArgNode(final BlockArgNode p0);
    
    Object visitBlockArg18Node(final BlockArg18Node p0);
    
    Object visitBlockNode(final BlockNode p0);
    
    Object visitBlockPassNode(final BlockPassNode p0);
    
    Object visitBreakNode(final BreakNode p0);
    
    Object visitConstDeclNode(final ConstDeclNode p0);
    
    Object visitClassVarAsgnNode(final ClassVarAsgnNode p0);
    
    Object visitClassVarDeclNode(final ClassVarDeclNode p0);
    
    Object visitClassVarNode(final ClassVarNode p0);
    
    Object visitCallNode(final CallNode p0);
    
    Object visitCaseNode(final CaseNode p0);
    
    Object visitClassNode(final ClassNode p0);
    
    Object visitColon2Node(final Colon2Node p0);
    
    Object visitColon3Node(final Colon3Node p0);
    
    Object visitConstNode(final ConstNode p0);
    
    Object visitDAsgnNode(final DAsgnNode p0);
    
    Object visitDRegxNode(final DRegexpNode p0);
    
    Object visitDStrNode(final DStrNode p0);
    
    Object visitDSymbolNode(final DSymbolNode p0);
    
    Object visitDVarNode(final DVarNode p0);
    
    Object visitDXStrNode(final DXStrNode p0);
    
    Object visitDefinedNode(final DefinedNode p0);
    
    Object visitDefnNode(final DefnNode p0);
    
    Object visitDefsNode(final DefsNode p0);
    
    Object visitDotNode(final DotNode p0);
    
    Object visitEncodingNode(final EncodingNode p0);
    
    Object visitEnsureNode(final EnsureNode p0);
    
    Object visitEvStrNode(final EvStrNode p0);
    
    Object visitFCallNode(final FCallNode p0);
    
    Object visitFalseNode(final FalseNode p0);
    
    Object visitFixnumNode(final FixnumNode p0);
    
    Object visitFlipNode(final FlipNode p0);
    
    Object visitFloatNode(final FloatNode p0);
    
    Object visitForNode(final ForNode p0);
    
    Object visitGlobalAsgnNode(final GlobalAsgnNode p0);
    
    Object visitGlobalVarNode(final GlobalVarNode p0);
    
    Object visitHashNode(final HashNode p0);
    
    Object visitInstAsgnNode(final InstAsgnNode p0);
    
    Object visitInstVarNode(final InstVarNode p0);
    
    Object visitIfNode(final IfNode p0);
    
    Object visitIterNode(final IterNode p0);
    
    Object visitLiteralNode(final LiteralNode p0);
    
    Object visitLocalAsgnNode(final LocalAsgnNode p0);
    
    Object visitLocalVarNode(final LocalVarNode p0);
    
    Object visitMultipleAsgnNode(final MultipleAsgnNode p0);
    
    Object visitMultipleAsgnNode(final MultipleAsgn19Node p0);
    
    Object visitMatch2Node(final Match2Node p0);
    
    Object visitMatch3Node(final Match3Node p0);
    
    Object visitMatchNode(final MatchNode p0);
    
    Object visitModuleNode(final ModuleNode p0);
    
    Object visitNewlineNode(final NewlineNode p0);
    
    Object visitNextNode(final NextNode p0);
    
    Object visitNilNode(final NilNode p0);
    
    Object visitNotNode(final NotNode p0);
    
    Object visitNthRefNode(final NthRefNode p0);
    
    Object visitOpElementAsgnNode(final OpElementAsgnNode p0);
    
    Object visitOpAsgnNode(final OpAsgnNode p0);
    
    Object visitOpAsgnAndNode(final OpAsgnAndNode p0);
    
    Object visitOpAsgnOrNode(final OpAsgnOrNode p0);
    
    Object visitOrNode(final OrNode p0);
    
    Object visitPreExeNode(final PreExeNode p0);
    
    Object visitPostExeNode(final PostExeNode p0);
    
    Object visitRedoNode(final RedoNode p0);
    
    Object visitRegexpNode(final RegexpNode p0);
    
    Object visitRescueBodyNode(final RescueBodyNode p0);
    
    Object visitRescueNode(final RescueNode p0);
    
    Object visitRestArgNode(final RestArgNode p0);
    
    Object visitRetryNode(final RetryNode p0);
    
    Object visitReturnNode(final ReturnNode p0);
    
    Object visitRootNode(final RootNode p0);
    
    Object visitSClassNode(final SClassNode p0);
    
    Object visitSelfNode(final SelfNode p0);
    
    Object visitSplatNode(final SplatNode p0);
    
    Object visitStrNode(final StrNode p0);
    
    Object visitSuperNode(final SuperNode p0);
    
    Object visitSValueNode(final SValueNode p0);
    
    Object visitSymbolNode(final SymbolNode p0);
    
    Object visitToAryNode(final ToAryNode p0);
    
    Object visitTrueNode(final TrueNode p0);
    
    Object visitUndefNode(final UndefNode p0);
    
    Object visitUntilNode(final UntilNode p0);
    
    Object visitVAliasNode(final VAliasNode p0);
    
    Object visitVCallNode(final VCallNode p0);
    
    Object visitWhenNode(final WhenNode p0);
    
    Object visitWhileNode(final WhileNode p0);
    
    Object visitXStrNode(final XStrNode p0);
    
    Object visitYieldNode(final YieldNode p0);
    
    Object visitZArrayNode(final ZArrayNode p0);
    
    Object visitZSuperNode(final ZSuperNode p0);
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.util;

import org.jruby.ast.NodeType;
import org.jruby.ast.FileNode;
import org.jruby.ast.XStrNode;
import org.jruby.ast.VCallNode;
import org.jruby.ast.VAliasNode;
import org.jruby.ast.UndefNode;
import org.jruby.ast.SymbolNode;
import org.jruby.ast.StrNode;
import org.jruby.ast.RegexpNode;
import org.jruby.ast.OpElementAsgnNode;
import org.jruby.ast.OpAsgnNode;
import org.jruby.ast.NthRefNode;
import org.jruby.ast.LocalVarNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.InstVarNode;
import org.jruby.ast.InstAsgnNode;
import org.jruby.ast.GlobalVarNode;
import org.jruby.ast.GlobalAsgnNode;
import org.jruby.ast.FloatNode;
import org.jruby.ast.FlipNode;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.FCallNode;
import org.jruby.ast.DVarNode;
import org.jruby.ast.DRegexpNode;
import org.jruby.ast.DotNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.ConstNode;
import org.jruby.ast.ConstDeclNode;
import org.jruby.ast.Colon3Node;
import org.jruby.ast.Colon2Node;
import org.jruby.ast.ClassVarNode;
import org.jruby.ast.ClassVarDeclNode;
import org.jruby.ast.ClassVarAsgnNode;
import org.jruby.ast.CallNode;
import org.jruby.ast.BlockArgNode;
import org.jruby.ast.BignumNode;
import org.jruby.ast.BackRefNode;
import org.jruby.ast.AttrAssignNode;
import org.jruby.ast.ArgumentNode;
import org.jruby.ast.AliasNode;
import java.util.Iterator;
import java.io.File;
import org.jruby.ast.Node;

public class SexpMaker
{
    private final StringBuilder sb;
    
    public static String create(final Node node) {
        final SexpMaker maker = new SexpMaker();
        maker.process(node);
        return maker.toString();
    }
    
    public static String create(final String methodName, final Node argsNode, final Node body) {
        final SexpMaker maker = new SexpMaker();
        maker.processMethod(methodName, argsNode, body);
        return maker.toString();
    }
    
    private SexpMaker() {
        this.sb = new StringBuilder();
    }
    
    private void processMethod(final String methodName, final Node argsNode, final Node body) {
        this.sb.append("(method ").append(methodName).append(' ');
        this.sb.append("(file ").append(new File(body.getPosition().getFile()).getName()).append(") ");
        this.sb.append("(line ").append(body.getPosition().getStartLine()).append(") ");
        this.process(argsNode);
        this.sb.append(" ");
        this.process(body);
        this.sb.append(")");
    }
    
    public String toString() {
        return this.sb.toString();
    }
    
    private void process(final Node node) {
        if (node == null) {
            this.sb.append("null");
            return;
        }
        this.sb.append("(");
        this.shortName(node);
        this.leafInfo(node);
        for (final Node child : node.childNodes()) {
            this.sb.append(" ");
            this.process(child);
        }
        this.sb.append(")");
    }
    
    private void shortName(final Node node) {
        String className = node.getClass().getName();
        if (className.endsWith("Node")) {
            className = className.substring(0, className.length() - 4);
            final int index = className.lastIndexOf(46);
            if (index != -1) {
                className = className.substring(index + 1);
            }
        }
        this.sb.append(className.toLowerCase());
    }
    
    private void leafInfo(final Node node) {
        switch (node.getNodeType()) {
            case ALIASNODE: {
                this.aliasNode((AliasNode)node);
                break;
            }
            case ANDNODE: {
                this.noDataContents(node);
                break;
            }
            case ARGSCATNODE: {
                this.noDataContents(node);
                break;
            }
            case ARGSPUSHNODE: {
                this.noDataContents(node);
                break;
            }
            case ARGUMENTNODE: {
                this.argumentNode((ArgumentNode)node);
                break;
            }
            case ARRAYNODE: {
                this.noDataContents(node);
                break;
            }
            case ATTRASSIGNNODE: {
                this.attrAssignNode((AttrAssignNode)node);
                break;
            }
            case BACKREFNODE: {
                this.backRefNode((BackRefNode)node);
                break;
            }
            case BEGINNODE: {
                this.noDataContents(node);
                break;
            }
            case BIGNUMNODE: {
                this.bignumNode((BignumNode)node);
                break;
            }
            case BLOCKARGNODE: {
                this.blockArgNode((BlockArgNode)node);
                break;
            }
            case BLOCKNODE: {
                this.noDataContents(node);
                break;
            }
            case BLOCKPASSNODE: {
                this.noDataContents(node);
                break;
            }
            case BREAKNODE: {
                this.noDataContents(node);
                break;
            }
            case CALLNODE: {
                this.callNode((CallNode)node);
                break;
            }
            case CASENODE: {
                this.noDataContents(node);
                break;
            }
            case CLASSNODE: {
                this.noDataContents(node);
                break;
            }
            case CLASSVARASGNNODE: {
                this.classVarAsgnNode((ClassVarAsgnNode)node);
                break;
            }
            case CLASSVARDECLNODE: {
                this.classVarDeclNode((ClassVarDeclNode)node);
                break;
            }
            case CLASSVARNODE: {
                this.classVarNode((ClassVarNode)node);
                break;
            }
            case COLON2NODE: {
                this.colon2Node((Colon2Node)node);
                break;
            }
            case COLON3NODE: {
                this.colon3Node((Colon3Node)node);
                break;
            }
            case CONSTDECLNODE: {
                this.constDeclNode((ConstDeclNode)node);
                break;
            }
            case CONSTNODE: {
                this.constNode((ConstNode)node);
                break;
            }
            case DASGNNODE: {
                this.dAsgnNode((DAsgnNode)node);
                break;
            }
            case DEFINEDNODE: {
                this.noDataContents(node);
                break;
            }
            case DEFNNODE: {
                this.noDataContents(node);
                break;
            }
            case DEFSNODE: {
                this.noDataContents(node);
                break;
            }
            case DOTNODE: {
                this.dotNode((DotNode)node);
                break;
            }
            case DREGEXPNODE: {
                this.dRegexpNode((DRegexpNode)node);
                break;
            }
            case DSTRNODE: {
                this.noDataContents(node);
                break;
            }
            case DSYMBOLNODE: {
                this.noDataContents(node);
                break;
            }
            case DVARNODE: {
                this.dVarNode((DVarNode)node);
                break;
            }
            case DXSTRNODE: {
                this.noDataContents(node);
                break;
            }
            case ENSURENODE: {
                this.noDataContents(node);
                break;
            }
            case EVSTRNODE: {
                this.noDataContents(node);
                break;
            }
            case FALSENODE: {
                this.noDataContents(node);
                break;
            }
            case FCALLNODE: {
                this.fCallNode((FCallNode)node);
                break;
            }
            case FIXNUMNODE: {
                this.fixnumNode((FixnumNode)node);
                break;
            }
            case FLIPNODE: {
                this.flipNode((FlipNode)node);
                break;
            }
            case FLOATNODE: {
                this.floatNode((FloatNode)node);
                break;
            }
            case FORNODE: {
                this.noDataContents(node);
                break;
            }
            case GLOBALASGNNODE: {
                this.globalAsgnNode((GlobalAsgnNode)node);
                break;
            }
            case GLOBALVARNODE: {
                this.globalVarNode((GlobalVarNode)node);
                break;
            }
            case HASHNODE: {
                this.noDataContents(node);
                break;
            }
            case IFNODE: {
                this.noDataContents(node);
                break;
            }
            case INSTASGNNODE: {
                this.noDataContents(node);
                this.instAsgnNode((InstAsgnNode)node);
                break;
            }
            case INSTVARNODE: {
                this.noDataContents(node);
                this.instVarNode((InstVarNode)node);
                break;
            }
            case ITERNODE: {
                this.noDataContents(node);
                break;
            }
            case LOCALASGNNODE: {
                this.localAsgnNode((LocalAsgnNode)node);
                break;
            }
            case LOCALVARNODE: {
                this.localVarNode((LocalVarNode)node);
                break;
            }
            case MATCH2NODE: {
                this.noDataContents(node);
                break;
            }
            case MATCH3NODE: {
                this.noDataContents(node);
                break;
            }
            case MATCHNODE: {
                this.noDataContents(node);
                break;
            }
            case MODULENODE: {
                this.noDataContents(node);
                break;
            }
            case MULTIPLEASGNNODE: {
                this.noDataContents(node);
                break;
            }
            case NEWLINENODE: {
                this.noDataContents(node);
                break;
            }
            case NEXTNODE: {
                this.noDataContents(node);
                break;
            }
            case NILNODE: {
                this.noDataContents(node);
                break;
            }
            case NOTNODE: {
                this.noDataContents(node);
                break;
            }
            case NTHREFNODE: {
                this.nthRefNode((NthRefNode)node);
                break;
            }
            case OPASGNANDNODE: {
                this.noDataContents(node);
                break;
            }
            case OPASGNNODE: {
                this.opAsgnNode((OpAsgnNode)node);
                break;
            }
            case OPASGNORNODE: {
                this.noDataContents(node);
                break;
            }
            case OPELEMENTASGNNODE: {
                this.opElementAsgnNode((OpElementAsgnNode)node);
                break;
            }
            case ORNODE: {
                this.noDataContents(node);
                break;
            }
            case PREEXENODE: {
                this.noDataContents(node);
                break;
            }
            case POSTEXENODE: {
                this.noDataContents(node);
                break;
            }
            case REDONODE: {
                this.noDataContents(node);
                break;
            }
            case REGEXPNODE: {
                this.regexpNode((RegexpNode)node);
                break;
            }
            case RESCUEBODYNODE: {
                this.noDataContents(node);
                break;
            }
            case RESCUENODE: {
                this.noDataContents(node);
                break;
            }
            case RETRYNODE: {
                this.noDataContents(node);
                break;
            }
            case RETURNNODE: {
                this.noDataContents(node);
                break;
            }
            case ROOTNODE: {
                this.noDataContents(node);
                break;
            }
            case SCLASSNODE: {
                this.noDataContents(node);
                break;
            }
            case SELFNODE: {
                this.noDataContents(node);
                break;
            }
            case SPLATNODE: {
                this.noDataContents(node);
                break;
            }
            case STRNODE: {
                this.strNode((StrNode)node);
                break;
            }
            case SUPERNODE: {
                this.noDataContents(node);
                break;
            }
            case SVALUENODE: {
                this.noDataContents(node);
                break;
            }
            case SYMBOLNODE: {
                this.symbolNode((SymbolNode)node);
                break;
            }
            case TOARYNODE: {
                this.noDataContents(node);
                break;
            }
            case TRUENODE: {
                this.noDataContents(node);
                break;
            }
            case UNDEFNODE: {
                this.undefNode((UndefNode)node);
                break;
            }
            case UNTILNODE: {
                this.noDataContents(node);
                break;
            }
            case VALIASNODE: {
                this.valiasNode((VAliasNode)node);
                break;
            }
            case VCALLNODE: {
                this.vcallNode((VCallNode)node);
                break;
            }
            case WHENNODE: {
                this.noDataContents(node);
                break;
            }
            case WHILENODE: {
                this.noDataContents(node);
                break;
            }
            case XSTRNODE: {
                this.xStrNode((XStrNode)node);
                break;
            }
            case YIELDNODE: {
                this.noDataContents(node);
                break;
            }
            case ZARRAYNODE: {
                this.noDataContents(node);
                break;
            }
            case ZSUPERNODE: {
                this.noDataContents(node);
                break;
            }
        }
    }
    
    private void xStrNode(final XStrNode node) {
        this.sb.append(" '").append(node.getValue()).append("'");
    }
    
    private void vcallNode(final VCallNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void valiasNode(final VAliasNode node) {
        this.sb.append(" ").append(node.getOldName()).append(node.getNewName());
    }
    
    private void undefNode(final UndefNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void strNode(final StrNode node) {
        if (node instanceof FileNode) {
            this.sb.append(" __FILE__");
        }
        else {
            this.sb.append(" '").append(node.getValue()).append("'");
        }
    }
    
    private void regexpNode(final RegexpNode node) {
        this.sb.append(" ").append(node.getValue()).append(" ").append(node.getOptions());
    }
    
    private void opElementAsgnNode(final OpElementAsgnNode node) {
        this.sb.append(" ").append(node.getOperatorName());
    }
    
    private void nthRefNode(final NthRefNode node) {
        this.sb.append(" ").append(node.getMatchNumber());
    }
    
    private void localAsgnNode(final LocalAsgnNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void instVarNode(final InstVarNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void instAsgnNode(final InstAsgnNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void globalVarNode(final GlobalVarNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void globalAsgnNode(final GlobalAsgnNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void floatNode(final FloatNode node) {
        this.sb.append(" ").append(node.getValue());
    }
    
    private void flipNode(final FlipNode node) {
        this.sb.append(" ").append(node.isExclusive());
    }
    
    private void fixnumNode(final FixnumNode node) {
        this.sb.append(" ").append(node.getValue());
    }
    
    private void fCallNode(final FCallNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void dVarNode(final DVarNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void blockArgNode(final BlockArgNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void backRefNode(final BackRefNode node) {
        this.sb.append(" ").append(node.getType());
    }
    
    private void symbolNode(final SymbolNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void localVarNode(final LocalVarNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void argumentNode(final ArgumentNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void dRegexpNode(final DRegexpNode node) {
        this.sb.append(" ").append(node.getOnce()).append(" ").append(node.getOptions());
    }
    
    private void dotNode(final DotNode node) {
        this.sb.append(" ").append(node.isExclusive()).append(" ").append(node.isLiteral());
    }
    
    private void dAsgnNode(final DAsgnNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void constNode(final ConstNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void constDeclNode(final ConstDeclNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void colon3Node(final Colon3Node node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void colon2Node(final Colon2Node node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void classVarNode(final ClassVarNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void classVarDeclNode(final ClassVarDeclNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void classVarAsgnNode(final ClassVarAsgnNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void callNode(final CallNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void bignumNode(final BignumNode node) {
        this.sb.append(" ").append(node.getValue());
    }
    
    private void attrAssignNode(final AttrAssignNode node) {
        this.sb.append(" ").append(node.getName());
    }
    
    private void aliasNode(final AliasNode node) {
        this.sb.append(" ").append(node.getOldName()).append(node.getNewName());
    }
    
    private void opAsgnNode(final OpAsgnNode node) {
        this.sb.append(" '").append(node.getOperatorName()).append("'");
    }
    
    private void noDataContents(final Node node) {
    }
}

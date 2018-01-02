// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.ExpressionNode;
import org.apache.xpath.XPathVisitor;
import org.apache.xalan.transformer.NodeSorter;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xpath.XPath;
import org.apache.xpath.Expression;
import org.apache.xpath.ExpressionOwner;

public class ElemForEach extends ElemTemplateElement implements ExpressionOwner
{
    static final boolean DEBUG = false;
    public boolean m_doc_cache_off;
    protected Expression m_selectExpression;
    protected XPath m_xpath;
    protected Vector m_sortElems;
    
    public ElemForEach() {
        this.m_doc_cache_off = false;
        this.m_selectExpression = null;
        this.m_xpath = null;
        this.m_sortElems = null;
    }
    
    public void setSelect(final XPath xpath) {
        this.m_selectExpression = xpath.getExpression();
        this.m_xpath = xpath;
    }
    
    public Expression getSelect() {
        return this.m_selectExpression;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        for (int length = this.getSortElemCount(), i = 0; i < length; ++i) {
            this.getSortElem(i).compose(sroot);
        }
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_selectExpression) {
            this.m_selectExpression.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
        else {
            this.m_selectExpression = this.getStylesheetRoot().m_selectDefault.getExpression();
        }
    }
    
    public void endCompose(final StylesheetRoot sroot) throws TransformerException {
        for (int length = this.getSortElemCount(), i = 0; i < length; ++i) {
            this.getSortElem(i).endCompose(sroot);
        }
        super.endCompose(sroot);
    }
    
    public int getSortElemCount() {
        return (this.m_sortElems == null) ? 0 : this.m_sortElems.size();
    }
    
    public ElemSort getSortElem(final int i) {
        return this.m_sortElems.elementAt(i);
    }
    
    public void setSortElem(final ElemSort sortElem) {
        if (null == this.m_sortElems) {
            this.m_sortElems = new Vector();
        }
        this.m_sortElems.addElement(sortElem);
    }
    
    public int getXSLToken() {
        return 28;
    }
    
    public String getNodeName() {
        return "for-each";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        transformer.pushCurrentTemplateRuleIsNull(true);
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        try {
            this.transformSelectedNodes(transformer);
        }
        finally {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
            transformer.popCurrentTemplateRuleIsNull();
        }
    }
    
    protected ElemTemplateElement getTemplateMatch() {
        return this;
    }
    
    public DTMIterator sortNodes(final XPathContext xctxt, final Vector keys, final DTMIterator sourceNodes) throws TransformerException {
        final NodeSorter sorter = new NodeSorter(xctxt);
        sourceNodes.setShouldCacheNodes(true);
        sourceNodes.runTo(-1);
        xctxt.pushContextNodeList(sourceNodes);
        try {
            sorter.sort(sourceNodes, keys, xctxt);
            sourceNodes.setCurrentPos(0);
        }
        finally {
            xctxt.popContextNodeList();
        }
        return sourceNodes;
    }
    
    public void transformSelectedNodes(final TransformerImpl transformer) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* transformer */
        //     1: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //     4: astore_2        /* xctxt */
        //     5: aload_2         /* xctxt */
        //     6: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //     9: istore_3        /* sourceNode */
        //    10: aload_0         /* this */
        //    11: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //    14: aload_2         /* xctxt */
        //    15: iload_3         /* sourceNode */
        //    16: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //    19: astore          sourceNodes
        //    21: aload_0         /* this */
        //    22: getfield        org/apache/xalan/templates/ElemForEach.m_sortElems:Ljava/util/Vector;
        //    25: ifnonnull       32
        //    28: aconst_null    
        //    29: goto            38
        //    32: aload_1         /* transformer */
        //    33: aload_0         /* this */
        //    34: iload_3         /* sourceNode */
        //    35: invokevirtual   org/apache/xalan/transformer/TransformerImpl.processSortKeys:(Lorg/apache/xalan/templates/ElemForEach;I)Ljava/util/Vector;
        //    38: astore          keys
        //    40: aconst_null    
        //    41: aload           keys
        //    43: if_acmpeq       57
        //    46: aload_0         /* this */
        //    47: aload_2         /* xctxt */
        //    48: aload           keys
        //    50: aload           sourceNodes
        //    52: invokevirtual   org/apache/xalan/templates/ElemForEach.sortNodes:(Lorg/apache/xpath/XPathContext;Ljava/util/Vector;Lorg/apache/xml/dtm/DTMIterator;)Lorg/apache/xml/dtm/DTMIterator;
        //    55: astore          sourceNodes
        //    57: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //    60: ifeq            104
        //    63: aload_0         /* this */
        //    64: getfield        org/apache/xalan/templates/ElemForEach.m_xpath:Lorg/apache/xpath/XPath;
        //    67: invokevirtual   org/apache/xpath/XPath.getExpression:()Lorg/apache/xpath/Expression;
        //    70: astore          expr
        //    72: aload           expr
        //    74: aload_2         /* xctxt */
        //    75: invokevirtual   org/apache/xpath/Expression.execute:(Lorg/apache/xpath/XPathContext;)Lorg/apache/xpath/objects/XObject;
        //    78: astore          xObject
        //    80: aload_2         /* xctxt */
        //    81: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //    84: istore          current
        //    86: aload_1         /* transformer */
        //    87: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    90: iload           current
        //    92: aload_0         /* this */
        //    93: ldc             "select"
        //    95: aload_0         /* this */
        //    96: getfield        org/apache/xalan/templates/ElemForEach.m_xpath:Lorg/apache/xpath/XPath;
        //    99: aload           xObject
        //   101: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   104: aload_2         /* xctxt */
        //   105: iconst_m1      
        //   106: invokevirtual   org/apache/xpath/XPathContext.pushCurrentNode:(I)V
        //   109: aload_2         /* xctxt */
        //   110: invokevirtual   org/apache/xpath/XPathContext.getCurrentNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   113: astore          currentNodes
        //   115: aload_2         /* xctxt */
        //   116: iconst_m1      
        //   117: invokevirtual   org/apache/xpath/XPathContext.pushCurrentExpressionNode:(I)V
        //   120: aload_2         /* xctxt */
        //   121: invokevirtual   org/apache/xpath/XPathContext.getCurrentExpressionNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   124: astore          currentExpressionNodes
        //   126: aload_2         /* xctxt */
        //   127: invokevirtual   org/apache/xpath/XPathContext.pushSAXLocatorNull:()V
        //   130: aload_2         /* xctxt */
        //   131: aload           sourceNodes
        //   133: invokevirtual   org/apache/xpath/XPathContext.pushContextNodeList:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   136: aload_1         /* transformer */
        //   137: aconst_null    
        //   138: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushElemTemplateElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   141: aload_2         /* xctxt */
        //   142: iload_3         /* sourceNode */
        //   143: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   146: astore          dtm
        //   148: iload_3         /* sourceNode */
        //   149: ldc             -65536
        //   151: iand           
        //   152: istore          docID
        //   154: goto            308
        //   157: aload           currentNodes
        //   159: iload           10
        //   161: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   164: aload           currentExpressionNodes
        //   166: iload           10
        //   168: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   171: iload           10
        //   173: ldc             -65536
        //   175: iand           
        //   176: iload           docID
        //   178: if_icmpeq       196
        //   181: aload_2         /* xctxt */
        //   182: iload           10
        //   184: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   187: astore          dtm
        //   189: iload           10
        //   191: ldc             -65536
        //   193: iand           
        //   194: istore          docID
        //   196: aload           dtm
        //   198: iload           10
        //   200: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   205: istore          nodeType
        //   207: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   210: ifeq            221
        //   213: aload_1         /* transformer */
        //   214: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   217: aload_0         /* this */
        //   218: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   221: aload_0         /* this */
        //   222: getfield        org/apache/xalan/templates/ElemTemplateElement.m_firstChild:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   225: astore          t
        //   227: goto            255
        //   230: aload_2         /* xctxt */
        //   231: aload           t
        //   233: invokevirtual   org/apache/xpath/XPathContext.setSAXLocator:(Ljavax/xml/transform/SourceLocator;)V
        //   236: aload_1         /* transformer */
        //   237: aload           t
        //   239: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   242: aload           t
        //   244: aload_1         /* transformer */
        //   245: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.execute:(Lorg/apache/xalan/transformer/TransformerImpl;)V
        //   248: aload           t
        //   250: getfield        org/apache/xalan/templates/ElemTemplateElement.m_nextSibling:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   253: astore          t
        //   255: aload           t
        //   257: ifnonnull       230
        //   260: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   263: ifeq            279
        //   266: aload_1         /* transformer */
        //   267: aconst_null    
        //   268: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   271: aload_1         /* transformer */
        //   272: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   275: aload_0         /* this */
        //   276: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   279: aload_0         /* this */
        //   280: getfield        org/apache/xalan/templates/ElemForEach.m_doc_cache_off:Z
        //   283: ifeq            308
        //   286: aload_2         /* xctxt */
        //   287: invokevirtual   org/apache/xpath/XPathContext.getSourceTreeManager:()Lorg/apache/xpath/SourceTreeManager;
        //   290: aload           dtm
        //   292: invokeinterface org/apache/xml/dtm/DTM.getDocument:()I
        //   297: invokevirtual   org/apache/xpath/SourceTreeManager.removeDocumentFromCache:(I)V
        //   300: aload_2         /* xctxt */
        //   301: aload           dtm
        //   303: iconst_0       
        //   304: invokevirtual   org/apache/xpath/XPathContext.release:(Lorg/apache/xml/dtm/DTM;Z)Z
        //   307: pop            
        //   308: iconst_m1      
        //   309: aload           sourceNodes
        //   311: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   316: dup            
        //   317: istore          child
        //   319: if_icmpne       157
        //   322: jsr             336
        //   325: goto            404
        //   328: astore          13
        //   330: jsr             336
        //   333: aload           13
        //   335: athrow         
        //   336: astore          14
        //   338: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   341: ifeq            375
        //   344: aload_1         /* transformer */
        //   345: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   348: iload_3         /* sourceNode */
        //   349: aload_0         /* this */
        //   350: ldc             "select"
        //   352: new             Lorg/apache/xpath/XPath;
        //   355: dup            
        //   356: aload_0         /* this */
        //   357: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   360: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   363: new             Lorg/apache/xpath/objects/XNodeSet;
        //   366: dup            
        //   367: aload           sourceNodes
        //   369: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   372: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEndEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   375: aload_2         /* xctxt */
        //   376: invokevirtual   org/apache/xpath/XPathContext.popSAXLocator:()V
        //   379: aload_2         /* xctxt */
        //   380: invokevirtual   org/apache/xpath/XPathContext.popContextNodeList:()V
        //   383: aload_1         /* transformer */
        //   384: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   387: aload_2         /* xctxt */
        //   388: invokevirtual   org/apache/xpath/XPathContext.popCurrentExpressionNode:()V
        //   391: aload_2         /* xctxt */
        //   392: invokevirtual   org/apache/xpath/XPathContext.popCurrentNode:()V
        //   395: aload           sourceNodes
        //   397: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //   402: ret             14
        //   404: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                    Signature
        //  -----  ------  ----  ----------------------  ------------------------------------------------
        //  0      405     0     this                    Lorg/apache/xalan/templates/ElemForEach;
        //  0      405     1     transformer             Lorg/apache/xalan/transformer/TransformerImpl;
        //  5      399     2     xctxt                   Lorg/apache/xpath/XPathContext;
        //  10     394     3     sourceNode              I
        //  21     383     4     sourceNodes             Lorg/apache/xml/dtm/DTMIterator;
        //  40     282     5     keys                    Ljava/util/Vector;
        //  72     32      6     expr                    Lorg/apache/xpath/Expression;
        //  80     24      7     xObject                 Lorg/apache/xpath/objects/XObject;
        //  86     18      8     current                 I
        //  115    207     6     currentNodes            Lorg/apache/xml/utils/IntStack;
        //  126    196     7     currentExpressionNodes  Lorg/apache/xml/utils/IntStack;
        //  148    174     8     dtm                     Lorg/apache/xml/dtm/DTM;
        //  154    168     9     docID                   I
        //  319    3       10    child                   I
        //  207    101     11    nodeType                I
        //  227    81      12    t                       Lorg/apache/xalan/templates/ElemTemplateElement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  21     328    328    336    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        if (64 == type) {
            this.setSortElem((ElemSort)newChild);
            return newChild;
        }
        return super.appendChild(newChild);
    }
    
    public void callChildVisitors(final XSLTVisitor visitor, final boolean callAttributes) {
        if (callAttributes && null != this.m_selectExpression) {
            this.m_selectExpression.callVisitors(this, visitor);
        }
        for (int length = this.getSortElemCount(), i = 0; i < length; ++i) {
            this.getSortElem(i).callVisitors(visitor);
        }
        super.callChildVisitors(visitor, callAttributes);
    }
    
    public Expression getExpression() {
        return this.m_selectExpression;
    }
    
    public void setExpression(final Expression exp) {
        exp.exprSetParent(this);
        this.m_selectExpression = exp;
    }
}

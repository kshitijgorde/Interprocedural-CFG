// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.io.IOException;
import java.io.ObjectInputStream;
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
    static final long serialVersionUID = 6018140636363583690L;
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
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        try {
            this.transformSelectedNodes(transformer);
        }
        finally {
            if (transformer.getDebug()) {
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
        //    57: aload_1         /* transformer */
        //    58: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //    61: ifeq            105
        //    64: aload_0         /* this */
        //    65: getfield        org/apache/xalan/templates/ElemForEach.m_xpath:Lorg/apache/xpath/XPath;
        //    68: invokevirtual   org/apache/xpath/XPath.getExpression:()Lorg/apache/xpath/Expression;
        //    71: astore          expr
        //    73: aload           expr
        //    75: aload_2         /* xctxt */
        //    76: invokevirtual   org/apache/xpath/Expression.execute:(Lorg/apache/xpath/XPathContext;)Lorg/apache/xpath/objects/XObject;
        //    79: astore          xObject
        //    81: aload_2         /* xctxt */
        //    82: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //    85: istore          current
        //    87: aload_1         /* transformer */
        //    88: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    91: iload           current
        //    93: aload_0         /* this */
        //    94: ldc             "select"
        //    96: aload_0         /* this */
        //    97: getfield        org/apache/xalan/templates/ElemForEach.m_xpath:Lorg/apache/xpath/XPath;
        //   100: aload           xObject
        //   102: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   105: aload_2         /* xctxt */
        //   106: iconst_m1      
        //   107: invokevirtual   org/apache/xpath/XPathContext.pushCurrentNode:(I)V
        //   110: aload_2         /* xctxt */
        //   111: invokevirtual   org/apache/xpath/XPathContext.getCurrentNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   114: astore          currentNodes
        //   116: aload_2         /* xctxt */
        //   117: iconst_m1      
        //   118: invokevirtual   org/apache/xpath/XPathContext.pushCurrentExpressionNode:(I)V
        //   121: aload_2         /* xctxt */
        //   122: invokevirtual   org/apache/xpath/XPathContext.getCurrentExpressionNodeStack:()Lorg/apache/xml/utils/IntStack;
        //   125: astore          currentExpressionNodes
        //   127: aload_2         /* xctxt */
        //   128: invokevirtual   org/apache/xpath/XPathContext.pushSAXLocatorNull:()V
        //   131: aload_2         /* xctxt */
        //   132: aload           sourceNodes
        //   134: invokevirtual   org/apache/xpath/XPathContext.pushContextNodeList:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   137: aload_1         /* transformer */
        //   138: aconst_null    
        //   139: invokevirtual   org/apache/xalan/transformer/TransformerImpl.pushElemTemplateElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   142: aload_2         /* xctxt */
        //   143: iload_3         /* sourceNode */
        //   144: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   147: astore          dtm
        //   149: iload_3         /* sourceNode */
        //   150: ldc             -65536
        //   152: iand           
        //   153: istore          docID
        //   155: goto            311
        //   158: aload           currentNodes
        //   160: iload           10
        //   162: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   165: aload           currentExpressionNodes
        //   167: iload           10
        //   169: invokevirtual   org/apache/xml/utils/IntStack.setTop:(I)V
        //   172: iload           10
        //   174: ldc             -65536
        //   176: iand           
        //   177: iload           docID
        //   179: if_icmpeq       197
        //   182: aload_2         /* xctxt */
        //   183: iload           10
        //   185: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   188: astore          dtm
        //   190: iload           10
        //   192: ldc             -65536
        //   194: iand           
        //   195: istore          docID
        //   197: aload           dtm
        //   199: iload           10
        //   201: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   206: istore          nodeType
        //   208: aload_1         /* transformer */
        //   209: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   212: ifeq            223
        //   215: aload_1         /* transformer */
        //   216: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   219: aload_0         /* this */
        //   220: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   223: aload_0         /* this */
        //   224: getfield        org/apache/xalan/templates/ElemTemplateElement.m_firstChild:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   227: astore          t
        //   229: goto            257
        //   232: aload_2         /* xctxt */
        //   233: aload           t
        //   235: invokevirtual   org/apache/xpath/XPathContext.setSAXLocator:(Ljavax/xml/transform/SourceLocator;)V
        //   238: aload_1         /* transformer */
        //   239: aload           t
        //   241: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   244: aload           t
        //   246: aload_1         /* transformer */
        //   247: invokevirtual   org/apache/xalan/templates/ElemTemplateElement.execute:(Lorg/apache/xalan/transformer/TransformerImpl;)V
        //   250: aload           t
        //   252: getfield        org/apache/xalan/templates/ElemTemplateElement.m_nextSibling:Lorg/apache/xalan/templates/ElemTemplateElement;
        //   255: astore          t
        //   257: aload           t
        //   259: ifnonnull       232
        //   262: aload_1         /* transformer */
        //   263: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   266: ifeq            282
        //   269: aload_1         /* transformer */
        //   270: aconst_null    
        //   271: invokevirtual   org/apache/xalan/transformer/TransformerImpl.setCurrentElement:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   274: aload_1         /* transformer */
        //   275: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   278: aload_0         /* this */
        //   279: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   282: aload_0         /* this */
        //   283: getfield        org/apache/xalan/templates/ElemForEach.m_doc_cache_off:Z
        //   286: ifeq            311
        //   289: aload_2         /* xctxt */
        //   290: invokevirtual   org/apache/xpath/XPathContext.getSourceTreeManager:()Lorg/apache/xpath/SourceTreeManager;
        //   293: aload           dtm
        //   295: invokeinterface org/apache/xml/dtm/DTM.getDocument:()I
        //   300: invokevirtual   org/apache/xpath/SourceTreeManager.removeDocumentFromCache:(I)V
        //   303: aload_2         /* xctxt */
        //   304: aload           dtm
        //   306: iconst_0       
        //   307: invokevirtual   org/apache/xpath/XPathContext.release:(Lorg/apache/xml/dtm/DTM;Z)Z
        //   310: pop            
        //   311: iconst_m1      
        //   312: aload           sourceNodes
        //   314: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   319: dup            
        //   320: istore          child
        //   322: if_icmpne       158
        //   325: jsr             339
        //   328: goto            408
        //   331: astore          13
        //   333: jsr             339
        //   336: aload           13
        //   338: athrow         
        //   339: astore          14
        //   341: aload_1         /* transformer */
        //   342: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   345: ifeq            379
        //   348: aload_1         /* transformer */
        //   349: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   352: iload_3         /* sourceNode */
        //   353: aload_0         /* this */
        //   354: ldc             "select"
        //   356: new             Lorg/apache/xpath/XPath;
        //   359: dup            
        //   360: aload_0         /* this */
        //   361: getfield        org/apache/xalan/templates/ElemForEach.m_selectExpression:Lorg/apache/xpath/Expression;
        //   364: invokespecial   org/apache/xpath/XPath.<init>:(Lorg/apache/xpath/Expression;)V
        //   367: new             Lorg/apache/xpath/objects/XNodeSet;
        //   370: dup            
        //   371: aload           sourceNodes
        //   373: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   376: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEndEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //   379: aload_2         /* xctxt */
        //   380: invokevirtual   org/apache/xpath/XPathContext.popSAXLocator:()V
        //   383: aload_2         /* xctxt */
        //   384: invokevirtual   org/apache/xpath/XPathContext.popContextNodeList:()V
        //   387: aload_1         /* transformer */
        //   388: invokevirtual   org/apache/xalan/transformer/TransformerImpl.popElemTemplateElement:()V
        //   391: aload_2         /* xctxt */
        //   392: invokevirtual   org/apache/xpath/XPathContext.popCurrentExpressionNode:()V
        //   395: aload_2         /* xctxt */
        //   396: invokevirtual   org/apache/xpath/XPathContext.popCurrentNode:()V
        //   399: aload           sourceNodes
        //   401: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //   406: ret             14
        //   408: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                    Signature
        //  -----  ------  ----  ----------------------  ------------------------------------------------
        //  0      409     0     this                    Lorg/apache/xalan/templates/ElemForEach;
        //  0      409     1     transformer             Lorg/apache/xalan/transformer/TransformerImpl;
        //  5      403     2     xctxt                   Lorg/apache/xpath/XPathContext;
        //  10     398     3     sourceNode              I
        //  21     387     4     sourceNodes             Lorg/apache/xml/dtm/DTMIterator;
        //  40     285     5     keys                    Ljava/util/Vector;
        //  73     32      6     expr                    Lorg/apache/xpath/Expression;
        //  81     24      7     xObject                 Lorg/apache/xpath/objects/XObject;
        //  87     18      8     current                 I
        //  116    209     6     currentNodes            Lorg/apache/xml/utils/IntStack;
        //  127    198     7     currentExpressionNodes  Lorg/apache/xml/utils/IntStack;
        //  149    176     8     dtm                     Lorg/apache/xml/dtm/DTM;
        //  155    170     9     docID                   I
        //  322    3       10    child                   I
        //  208    103     11    nodeType                I
        //  229    82      12    t                       Lorg/apache/xalan/templates/ElemTemplateElement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  21     331    331    339    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
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
    
    private void readObject(final ObjectInputStream os) throws IOException, ClassNotFoundException {
        os.defaultReadObject();
        this.m_xpath = null;
    }
}

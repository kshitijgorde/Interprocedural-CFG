// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xalan.templates.KeyDeclaration;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.utils.XMLString;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XNodeSet;
import java.util.Hashtable;
import java.util.Vector;

public class KeyTable
{
    private int m_docKey;
    private Vector m_keyDeclarations;
    private Hashtable m_refsTable;
    private XNodeSet m_keyNodes;
    
    public int getDocKey() {
        return this.m_docKey;
    }
    
    KeyIterator getKeyIterator() {
        return (KeyIterator)this.m_keyNodes.getContainedIter();
    }
    
    public KeyTable(final int doc, final PrefixResolver nscontext, final QName name, final Vector keyDeclarations, final XPathContext xctxt) throws TransformerException {
        this.m_refsTable = null;
        this.m_docKey = doc;
        this.m_keyDeclarations = keyDeclarations;
        final KeyIterator ki = new KeyIterator(name, keyDeclarations);
        (this.m_keyNodes = new XNodeSet(ki)).allowDetachToRelease(false);
        this.m_keyNodes.setRoot(doc, xctxt);
    }
    
    public XNodeSet getNodeSetDTMByKey(final QName name, final XMLString ref) {
        XNodeSet refNodes = this.getRefsTable().get(ref);
        try {
            if (refNodes != null) {
                refNodes = (XNodeSet)refNodes.cloneWithReset();
            }
        }
        catch (CloneNotSupportedException e) {
            refNodes = null;
        }
        if (refNodes == null) {
            final KeyIterator ki = (KeyIterator)this.m_keyNodes.getContainedIter();
            final XPathContext xctxt = ki.getXPathContext();
            refNodes = new XNodeSet(xctxt.getDTMManager()) {
                public void setRoot(final int nodeHandle, final Object environment) {
                }
            };
            refNodes.reset();
        }
        return refNodes;
    }
    
    public QName getKeyTableName() {
        return this.getKeyIterator().getName();
    }
    
    private Vector getKeyDeclarations() {
        final int nDeclarations = this.m_keyDeclarations.size();
        final Vector keyDecls = new Vector(nDeclarations);
        for (int i = 0; i < nDeclarations; ++i) {
            final KeyDeclaration kd = this.m_keyDeclarations.elementAt(i);
            if (kd.getName().equals(this.getKeyTableName())) {
                keyDecls.add(kd);
            }
        }
        return keyDecls;
    }
    
    private Hashtable getRefsTable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xalan/transformer/KeyTable.m_refsTable:Ljava/util/Hashtable;
        //     4: ifnonnull       213
        //     7: aload_0         /* this */
        //     8: new             Ljava/util/Hashtable;
        //    11: dup            
        //    12: bipush          89
        //    14: invokespecial   java/util/Hashtable.<init>:(I)V
        //    17: putfield        org/apache/xalan/transformer/KeyTable.m_refsTable:Ljava/util/Hashtable;
        //    20: aload_0         /* this */
        //    21: getfield        org/apache/xalan/transformer/KeyTable.m_keyNodes:Lorg/apache/xpath/objects/XNodeSet;
        //    24: invokevirtual   org/apache/xpath/axes/NodeSequence.getContainedIter:()Lorg/apache/xml/dtm/DTMIterator;
        //    27: checkcast       Lorg/apache/xalan/transformer/KeyIterator;
        //    30: astore_1        /* ki */
        //    31: aload_1         /* ki */
        //    32: invokevirtual   org/apache/xpath/axes/LocPathIterator.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //    35: astore_2        /* xctxt */
        //    36: aload_0         /* this */
        //    37: invokespecial   org/apache/xalan/transformer/KeyTable.getKeyDeclarations:()Ljava/util/Vector;
        //    40: astore_3        /* keyDecls */
        //    41: aload_3         /* keyDecls */
        //    42: invokevirtual   java/util/Vector.size:()I
        //    45: istore          nKeyDecls
        //    47: aload_0         /* this */
        //    48: getfield        org/apache/xalan/transformer/KeyTable.m_keyNodes:Lorg/apache/xpath/objects/XNodeSet;
        //    51: invokevirtual   org/apache/xpath/axes/NodeSequence.reset:()V
        //    54: goto            199
        //    57: iconst_0       
        //    58: istore          keyDeclIdx
        //    60: goto            177
        //    63: aload_3         /* keyDecls */
        //    64: iload           keyDeclIdx
        //    66: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //    69: checkcast       Lorg/apache/xalan/templates/KeyDeclaration;
        //    72: astore          keyDeclaration
        //    74: aload           keyDeclaration
        //    76: invokevirtual   org/apache/xalan/templates/KeyDeclaration.getUse:()Lorg/apache/xpath/XPath;
        //    79: aload_2         /* xctxt */
        //    80: iload           5
        //    82: aload_1         /* ki */
        //    83: invokevirtual   org/apache/xpath/axes/LocPathIterator.getPrefixResolver:()Lorg/apache/xml/utils/PrefixResolver;
        //    86: invokevirtual   org/apache/xpath/XPath.execute:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XObject;
        //    89: astore          xuse
        //    91: aload           xuse
        //    93: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //    96: iconst_4       
        //    97: if_icmpeq       119
        //   100: aload           xuse
        //   102: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //   105: astore          exprResult
        //   107: aload_0         /* this */
        //   108: aload_2         /* xctxt */
        //   109: aload           exprResult
        //   111: iload           5
        //   113: invokespecial   org/apache/xalan/transformer/KeyTable.addValueInRefsTable:(Lorg/apache/xpath/XPathContext;Lorg/apache/xml/utils/XMLString;I)V
        //   116: goto            174
        //   119: aload           xuse
        //   121: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //   124: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   127: astore          i
        //   129: goto            160
        //   132: aload_2         /* xctxt */
        //   133: iload           10
        //   135: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   138: astore          dtm
        //   140: aload           dtm
        //   142: iload           10
        //   144: invokeinterface org/apache/xml/dtm/DTM.getStringValue:(I)Lorg/apache/xml/utils/XMLString;
        //   149: astore          exprResult
        //   151: aload_0         /* this */
        //   152: aload_2         /* xctxt */
        //   153: aload           exprResult
        //   155: iload           5
        //   157: invokespecial   org/apache/xalan/transformer/KeyTable.addValueInRefsTable:(Lorg/apache/xpath/XPathContext;Lorg/apache/xml/utils/XMLString;I)V
        //   160: iconst_m1      
        //   161: aload           i
        //   163: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   168: dup            
        //   169: istore          currentNodeInUseClause
        //   171: if_icmpne       132
        //   174: iinc            keyDeclIdx, 1
        //   177: iload           keyDeclIdx
        //   179: iload           nKeyDecls
        //   181: if_icmplt       63
        //   184: goto            199
        //   187: astore          te
        //   189: new             Lorg/apache/xml/utils/WrappedRuntimeException;
        //   192: dup            
        //   193: aload           te
        //   195: invokespecial   org/apache/xml/utils/WrappedRuntimeException.<init>:(Ljava/lang/Exception;)V
        //   198: athrow         
        //   199: iconst_m1      
        //   200: aload_0         /* this */
        //   201: getfield        org/apache/xalan/transformer/KeyTable.m_keyNodes:Lorg/apache/xpath/objects/XNodeSet;
        //   204: invokevirtual   org/apache/xpath/axes/NodeSequence.nextNode:()I
        //   207: dup            
        //   208: istore          currentNode
        //   210: if_icmpne       57
        //   213: aload_0         /* this */
        //   214: getfield        org/apache/xalan/transformer/KeyTable.m_refsTable:Ljava/util/Hashtable;
        //   217: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                    Signature
        //  -----  ------  ----  ----------------------  -------------------------------------------
        //  0      218     0     this                    Lorg/apache/xalan/transformer/KeyTable;
        //  31     182     1     ki                      Lorg/apache/xalan/transformer/KeyIterator;
        //  36     177     2     xctxt                   Lorg/apache/xpath/XPathContext;
        //  41     172     3     keyDecls                Ljava/util/Vector;
        //  47     166     4     nKeyDecls               I
        //  210    3       5     currentNode             I
        //  60     124     6     keyDeclIdx              I
        //  74     100     7     keyDeclaration          Lorg/apache/xalan/templates/KeyDeclaration;
        //  91     83      8     xuse                    Lorg/apache/xpath/objects/XObject;
        //  107    9       9     exprResult              Lorg/apache/xml/utils/XMLString;
        //  129    45      9     i                       Lorg/apache/xml/dtm/DTMIterator;
        //  171    3       10    currentNodeInUseClause  I
        //  140    20      11    dtm                     Lorg/apache/xml/dtm/DTM;
        //  151    9       12    exprResult              Lorg/apache/xml/utils/XMLString;
        //  189    10      6     te                      Ljavax/xml/transform/TransformerException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  57     184    187    199    Ljavax/xml/transform/TransformerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void addValueInRefsTable(final XPathContext xctxt, final XMLString ref, final int node) {
        XNodeSet nodes = this.m_refsTable.get(ref);
        if (nodes == null) {
            nodes = new XNodeSet(node, xctxt.getDTMManager());
            nodes.nextNode();
            this.m_refsTable.put(ref, nodes);
        }
        else if (nodes.getCurrentNode() != node) {
            nodes.mutableNodeset().addNode(node);
            nodes.nextNode();
        }
    }
}

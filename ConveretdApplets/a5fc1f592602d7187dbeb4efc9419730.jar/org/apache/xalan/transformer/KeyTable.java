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
    
    private KeyDeclaration getKeyDeclaration() {
        for (int nDeclarations = this.m_keyDeclarations.size(), i = 0; i < nDeclarations; ++i) {
            final KeyDeclaration kd = this.m_keyDeclarations.elementAt(i);
            if (kd.getName().equals(this.getKeyTableName())) {
                return kd;
            }
        }
        return null;
    }
    
    private Hashtable getRefsTable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xalan/transformer/KeyTable.m_refsTable:Ljava/util/Hashtable;
        //     4: ifnonnull       179
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
        //    37: invokespecial   org/apache/xalan/transformer/KeyTable.getKeyDeclaration:()Lorg/apache/xalan/templates/KeyDeclaration;
        //    40: astore_3        /* keyDeclaration */
        //    41: aload_0         /* this */
        //    42: getfield        org/apache/xalan/transformer/KeyTable.m_keyNodes:Lorg/apache/xpath/objects/XNodeSet;
        //    45: invokevirtual   org/apache/xpath/axes/NodeSequence.reset:()V
        //    48: goto            165
        //    51: aload_3         /* keyDeclaration */
        //    52: invokevirtual   org/apache/xalan/templates/KeyDeclaration.getUse:()Lorg/apache/xpath/XPath;
        //    55: aload_2         /* xctxt */
        //    56: iload           4
        //    58: aload_1         /* ki */
        //    59: invokevirtual   org/apache/xpath/axes/LocPathIterator.getPrefixResolver:()Lorg/apache/xml/utils/PrefixResolver;
        //    62: invokevirtual   org/apache/xpath/XPath.execute:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XObject;
        //    65: astore          xuse
        //    67: aload           xuse
        //    69: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //    72: iconst_4       
        //    73: if_icmpeq       95
        //    76: aload           xuse
        //    78: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //    81: astore          exprResult
        //    83: aload_0         /* this */
        //    84: aload_2         /* xctxt */
        //    85: aload           exprResult
        //    87: iload           4
        //    89: invokespecial   org/apache/xalan/transformer/KeyTable.addValueInRefsTable:(Lorg/apache/xpath/XPathContext;Lorg/apache/xml/utils/XMLString;I)V
        //    92: goto            150
        //    95: aload           xuse
        //    97: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //   100: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   103: astore          i
        //   105: goto            136
        //   108: aload_2         /* xctxt */
        //   109: iload           7
        //   111: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   114: astore          dtm
        //   116: aload           dtm
        //   118: iload           7
        //   120: invokeinterface org/apache/xml/dtm/DTM.getStringValue:(I)Lorg/apache/xml/utils/XMLString;
        //   125: astore          exprResult
        //   127: aload_0         /* this */
        //   128: aload_2         /* xctxt */
        //   129: aload           exprResult
        //   131: iload           4
        //   133: invokespecial   org/apache/xalan/transformer/KeyTable.addValueInRefsTable:(Lorg/apache/xpath/XPathContext;Lorg/apache/xml/utils/XMLString;I)V
        //   136: iconst_m1      
        //   137: aload           i
        //   139: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   144: dup            
        //   145: istore          currentNodeInUseClause
        //   147: if_icmpne       108
        //   150: goto            165
        //   153: astore          te
        //   155: new             Lorg/apache/xml/utils/WrappedRuntimeException;
        //   158: dup            
        //   159: aload           te
        //   161: invokespecial   org/apache/xml/utils/WrappedRuntimeException.<init>:(Ljava/lang/Exception;)V
        //   164: athrow         
        //   165: iconst_m1      
        //   166: aload_0         /* this */
        //   167: getfield        org/apache/xalan/transformer/KeyTable.m_keyNodes:Lorg/apache/xpath/objects/XNodeSet;
        //   170: invokevirtual   org/apache/xpath/axes/NodeSequence.nextNode:()I
        //   173: dup            
        //   174: istore          currentNode
        //   176: if_icmpne       51
        //   179: aload_0         /* this */
        //   180: getfield        org/apache/xalan/transformer/KeyTable.m_refsTable:Ljava/util/Hashtable;
        //   183: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                    Signature
        //  -----  ------  ----  ----------------------  -------------------------------------------
        //  0      184     0     this                    Lorg/apache/xalan/transformer/KeyTable;
        //  31     148     1     ki                      Lorg/apache/xalan/transformer/KeyIterator;
        //  36     143     2     xctxt                   Lorg/apache/xpath/XPathContext;
        //  41     138     3     keyDeclaration          Lorg/apache/xalan/templates/KeyDeclaration;
        //  176    3       4     currentNode             I
        //  67     83      5     xuse                    Lorg/apache/xpath/objects/XObject;
        //  83     9       6     exprResult              Lorg/apache/xml/utils/XMLString;
        //  105    45      6     i                       Lorg/apache/xml/dtm/DTMIterator;
        //  147    3       7     currentNodeInUseClause  I
        //  116    20      8     dtm                     Lorg/apache/xml/dtm/DTM;
        //  127    9       9     exprResult              Lorg/apache/xml/utils/XMLString;
        //  155    10      5     te                      Ljavax/xml/transform/TransformerException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  51     150    153    165    Ljavax/xml/transform/TransformerException;
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

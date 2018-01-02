// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPath;

public class ElemCopyOf extends ElemTemplateElement
{
    public XPath m_selectExpression;
    
    public ElemCopyOf() {
        this.m_selectExpression = null;
    }
    
    public void setSelect(final XPath expr) {
        this.m_selectExpression = expr;
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        this.m_selectExpression.fixupVariables(cstate.getVariableNames(), cstate.getGlobalsSize());
    }
    
    public int getXSLToken() {
        return 74;
    }
    
    public String getNodeName() {
        return "copy-of";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //     3: ifeq            14
        //     6: aload_1         /* transformer */
        //     7: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    10: aload_0         /* this */
        //    11: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //    14: aload_1         /* transformer */
        //    15: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //    18: astore_2        /* xctxt */
        //    19: aload_2         /* xctxt */
        //    20: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //    23: istore_3        /* sourceNode */
        //    24: aload_0         /* this */
        //    25: getfield        org/apache/xalan/templates/ElemCopyOf.m_selectExpression:Lorg/apache/xpath/XPath;
        //    28: aload_2         /* xctxt */
        //    29: iload_3         /* sourceNode */
        //    30: aload_0         /* this */
        //    31: invokevirtual   org/apache/xpath/XPath.execute:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XObject;
        //    34: astore          value
        //    36: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //    39: ifeq            59
        //    42: aload_1         /* transformer */
        //    43: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    46: iload_3         /* sourceNode */
        //    47: aload_0         /* this */
        //    48: ldc             "select"
        //    50: aload_0         /* this */
        //    51: getfield        org/apache/xalan/templates/ElemCopyOf.m_selectExpression:Lorg/apache/xpath/XPath;
        //    54: aload           value
        //    56: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //    59: aload_1         /* transformer */
        //    60: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getSerializationHandler:()Lorg/apache/xml/serializer/SerializationHandler;
        //    63: astore          handler
        //    65: aconst_null    
        //    66: aload           value
        //    68: if_acmpeq       315
        //    71: aload           value
        //    73: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //    76: istore          type
        //    78: iload           type
        //    80: tableswitch {
        //                2: 116
        //                3: 116
        //                4: 116
        //                5: 144
        //                6: 276
        //          default: 290
        //        }
        //   116: aload           value
        //   118: invokevirtual   org/apache/xpath/objects/XObject.str:()Ljava/lang/String;
        //   121: astore          s
        //   123: aload           handler
        //   125: aload           s
        //   127: invokevirtual   java/lang/String.toCharArray:()[C
        //   130: iconst_0       
        //   131: aload           s
        //   133: invokevirtual   java/lang/String.length:()I
        //   136: invokeinterface org/xml/sax/ContentHandler.characters:([CII)V
        //   141: goto            315
        //   144: aload           value
        //   146: invokevirtual   org/apache/xpath/objects/XObject.iter:()Lorg/apache/xml/dtm/DTMIterator;
        //   149: astore          nl
        //   151: new             Lorg/apache/xalan/transformer/TreeWalker2Result;
        //   154: dup            
        //   155: aload_1         /* transformer */
        //   156: aload           handler
        //   158: invokespecial   org/apache/xalan/transformer/TreeWalker2Result.<init>:(Lorg/apache/xalan/transformer/TransformerImpl;Lorg/apache/xml/serializer/SerializationHandler;)V
        //   161: astore          tw
        //   163: goto            259
        //   166: aload_2         /* xctxt */
        //   167: invokevirtual   org/apache/xpath/XPathContext.getDTMManager:()Lorg/apache/xml/dtm/DTMManager;
        //   170: iload           10
        //   172: invokevirtual   org/apache/xml/dtm/DTMManager.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   175: astore          dtm
        //   177: aload           dtm
        //   179: iload           10
        //   181: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   186: istore          t
        //   188: iload           t
        //   190: bipush          9
        //   192: if_icmpne       236
        //   195: aload           dtm
        //   197: iload           10
        //   199: invokeinterface org/apache/xml/dtm/DTM.getFirstChild:(I)I
        //   204: istore          child
        //   206: goto            227
        //   209: aload           tw
        //   211: iload           child
        //   213: invokevirtual   org/apache/xml/dtm/ref/DTMTreeWalker.traverse:(I)V
        //   216: aload           dtm
        //   218: iload           child
        //   220: invokeinterface org/apache/xml/dtm/DTM.getNextSibling:(I)I
        //   225: istore          child
        //   227: iload           child
        //   229: iconst_m1      
        //   230: if_icmpne       209
        //   233: goto            259
        //   236: iload           t
        //   238: iconst_2       
        //   239: if_icmpne       252
        //   242: aload           handler
        //   244: iload           10
        //   246: invokestatic    org/apache/xalan/serialize/SerializerUtils.addAttribute:(Lorg/apache/xml/serializer/SerializationHandler;I)V
        //   249: goto            259
        //   252: aload           tw
        //   254: iload           10
        //   256: invokevirtual   org/apache/xml/dtm/ref/DTMTreeWalker.traverse:(I)V
        //   259: iconst_m1      
        //   260: aload           nl
        //   262: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   267: dup            
        //   268: istore          pos
        //   270: if_icmpne       166
        //   273: goto            315
        //   276: aload           handler
        //   278: aload           value
        //   280: aload_1         /* transformer */
        //   281: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //   284: invokestatic    org/apache/xalan/serialize/SerializerUtils.outputResultTreeFragment:(Lorg/apache/xml/serializer/SerializationHandler;Lorg/apache/xpath/objects/XObject;Lorg/apache/xpath/XPathContext;)V
        //   287: goto            315
        //   290: aload           value
        //   292: invokevirtual   org/apache/xpath/objects/XObject.str:()Ljava/lang/String;
        //   295: astore          s
        //   297: aload           handler
        //   299: aload           s
        //   301: invokevirtual   java/lang/String.toCharArray:()[C
        //   304: iconst_0       
        //   305: aload           s
        //   307: invokevirtual   java/lang/String.length:()I
        //   310: invokeinterface org/xml/sax/ContentHandler.characters:([CII)V
        //   315: jsr             339
        //   318: goto            357
        //   321: astore_2        /* se */
        //   322: new             Ljavax/xml/transform/TransformerException;
        //   325: dup            
        //   326: aload_2         /* se */
        //   327: invokespecial   javax/xml/transform/TransformerException.<init>:(Ljava/lang/Throwable;)V
        //   330: athrow         
        //   331: astore          14
        //   333: jsr             339
        //   336: aload           14
        //   338: athrow         
        //   339: astore          15
        //   341: getstatic       org/apache/xalan/transformer/TransformerImpl.S_DEBUG:Z
        //   344: ifeq            355
        //   347: aload_1         /* transformer */
        //   348: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   351: aload_0         /* this */
        //   352: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   355: ret             15
        //   357: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      358     0     this         Lorg/apache/xalan/templates/ElemCopyOf;
        //  0      358     1     transformer  Lorg/apache/xalan/transformer/TransformerImpl;
        //  19     296     2     xctxt        Lorg/apache/xpath/XPathContext;
        //  24     291     3     sourceNode   I
        //  36     279     4     value        Lorg/apache/xpath/objects/XObject;
        //  65     250     5     handler      Lorg/apache/xml/serializer/SerializationHandler;
        //  78     237     6     type         I
        //  123    192     7     s            Ljava/lang/String;
        //  151    164     8     nl           Lorg/apache/xml/dtm/DTMIterator;
        //  163    152     9     tw           Lorg/apache/xml/dtm/ref/DTMTreeWalker;
        //  270    45      10    pos          I
        //  177    82      11    dtm          Lorg/apache/xml/dtm/DTM;
        //  188    71      12    t            S
        //  206    27      13    child        I
        //  322    35      2     se           Lorg/xml/sax/SAXException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  14     315    321    331    Lorg/xml/sax/SAXException;
        //  14     331    331    339    Any
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
        this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            this.m_selectExpression.getExpression().callVisitors(this.m_selectExpression, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}

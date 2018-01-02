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
    static final long serialVersionUID = -7433828829497411127L;
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
        //     0: aload_1         /* transformer */
        //     1: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //     4: ifeq            15
        //     7: aload_1         /* transformer */
        //     8: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    11: aload_0         /* this */
        //    12: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //    15: aload_1         /* transformer */
        //    16: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //    19: astore_2        /* xctxt */
        //    20: aload_2         /* xctxt */
        //    21: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //    24: istore_3        /* sourceNode */
        //    25: aload_0         /* this */
        //    26: getfield        org/apache/xalan/templates/ElemCopyOf.m_selectExpression:Lorg/apache/xpath/XPath;
        //    29: aload_2         /* xctxt */
        //    30: iload_3         /* sourceNode */
        //    31: aload_0         /* this */
        //    32: invokevirtual   org/apache/xpath/XPath.execute:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XObject;
        //    35: astore          value
        //    37: aload_1         /* transformer */
        //    38: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //    41: ifeq            61
        //    44: aload_1         /* transformer */
        //    45: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //    48: iload_3         /* sourceNode */
        //    49: aload_0         /* this */
        //    50: ldc             "select"
        //    52: aload_0         /* this */
        //    53: getfield        org/apache/xalan/templates/ElemCopyOf.m_selectExpression:Lorg/apache/xpath/XPath;
        //    56: aload           value
        //    58: invokevirtual   org/apache/xalan/trace/TraceManager.fireSelectedEvent:(ILorg/apache/xalan/templates/ElemTemplateElement;Ljava/lang/String;Lorg/apache/xpath/XPath;Lorg/apache/xpath/objects/XObject;)V
        //    61: aload_1         /* transformer */
        //    62: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getSerializationHandler:()Lorg/apache/xml/serializer/SerializationHandler;
        //    65: astore          handler
        //    67: aconst_null    
        //    68: aload           value
        //    70: if_acmpeq       315
        //    73: aload           value
        //    75: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //    78: istore          type
        //    80: iload           type
        //    82: tableswitch {
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
        //   318: goto            358
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
        //   341: aload_1         /* transformer */
        //   342: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getDebug:()Z
        //   345: ifeq            356
        //   348: aload_1         /* transformer */
        //   349: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getTraceManager:()Lorg/apache/xalan/trace/TraceManager;
        //   352: aload_0         /* this */
        //   353: invokevirtual   org/apache/xalan/trace/TraceManager.fireTraceEndEvent:(Lorg/apache/xalan/templates/ElemTemplateElement;)V
        //   356: ret             15
        //   358: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      359     0     this         Lorg/apache/xalan/templates/ElemCopyOf;
        //  0      359     1     transformer  Lorg/apache/xalan/transformer/TransformerImpl;
        //  20     295     2     xctxt        Lorg/apache/xpath/XPathContext;
        //  25     290     3     sourceNode   I
        //  37     278     4     value        Lorg/apache/xpath/objects/XObject;
        //  67     248     5     handler      Lorg/apache/xml/serializer/SerializationHandler;
        //  80     235     6     type         I
        //  123    192     7     s            Ljava/lang/String;
        //  151    164     8     nl           Lorg/apache/xml/dtm/DTMIterator;
        //  163    152     9     tw           Lorg/apache/xml/dtm/ref/DTMTreeWalker;
        //  270    45      10    pos          I
        //  177    82      11    dtm          Lorg/apache/xml/dtm/DTM;
        //  188    71      12    t            S
        //  206    27      13    child        I
        //  322    36      2     se           Lorg/xml/sax/SAXException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  15     315    321    331    Lorg/xml/sax/SAXException;
        //  15     331    331    339    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
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

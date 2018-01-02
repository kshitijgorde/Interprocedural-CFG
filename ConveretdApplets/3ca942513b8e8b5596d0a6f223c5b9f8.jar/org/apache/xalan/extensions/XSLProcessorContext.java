// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import org.apache.xml.dtm.DTM;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;

public class XSLProcessorContext
{
    private TransformerImpl transformer;
    private Stylesheet stylesheetTree;
    private DTM sourceTree;
    private int sourceNode;
    private QName mode;
    
    public XSLProcessorContext(final TransformerImpl transformer, final Stylesheet stylesheetTree) {
        this.transformer = transformer;
        this.stylesheetTree = stylesheetTree;
        final XPathContext xctxt = transformer.getXPathContext();
        this.mode = transformer.getMode();
        this.sourceNode = xctxt.getCurrentNode();
        this.sourceTree = xctxt.getDTM(this.sourceNode);
    }
    
    public TransformerImpl getTransformer() {
        return this.transformer;
    }
    
    public Stylesheet getStylesheet() {
        return this.stylesheetTree;
    }
    
    public Node getSourceTree() {
        return this.sourceTree.getNode(this.sourceTree.getDocumentRoot(this.sourceNode));
    }
    
    public Node getContextNode() {
        return this.sourceTree.getNode(this.sourceNode);
    }
    
    public QName getMode() {
        return this.mode;
    }
    
    public void outputToResultTree(final Stylesheet stylesheetTree, final Object obj) throws TransformerException, MalformedURLException, FileNotFoundException, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xalan/extensions/XSLProcessorContext.transformer:Lorg/apache/xalan/transformer/TransformerImpl;
        //     4: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getResultTreeHandler:()Lorg/apache/xml/serializer/SerializationHandler;
        //     7: astore_3        /* rtreeHandler */
        //     8: aload_0         /* this */
        //     9: getfield        org/apache/xalan/extensions/XSLProcessorContext.transformer:Lorg/apache/xalan/transformer/TransformerImpl;
        //    12: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //    15: astore          xctxt
        //    17: aload_2         /* obj */
        //    18: instanceof      Lorg/apache/xpath/objects/XObject;
        //    21: ifeq            33
        //    24: aload_2         /* obj */
        //    25: checkcast       Lorg/apache/xpath/objects/XObject;
        //    28: astore          value
        //    30: goto            334
        //    33: aload_2         /* obj */
        //    34: instanceof      Ljava/lang/String;
        //    37: ifeq            56
        //    40: new             Lorg/apache/xpath/objects/XString;
        //    43: dup            
        //    44: aload_2         /* obj */
        //    45: checkcast       Ljava/lang/String;
        //    48: invokespecial   org/apache/xpath/objects/XString.<init>:(Ljava/lang/String;)V
        //    51: astore          value
        //    53: goto            334
        //    56: aload_2         /* obj */
        //    57: instanceof      Ljava/lang/Boolean;
        //    60: ifeq            82
        //    63: new             Lorg/apache/xpath/objects/XBoolean;
        //    66: dup            
        //    67: aload_2         /* obj */
        //    68: checkcast       Ljava/lang/Boolean;
        //    71: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    74: invokespecial   org/apache/xpath/objects/XBoolean.<init>:(Z)V
        //    77: astore          value
        //    79: goto            334
        //    82: aload_2         /* obj */
        //    83: instanceof      Ljava/lang/Double;
        //    86: ifeq            108
        //    89: new             Lorg/apache/xpath/objects/XNumber;
        //    92: dup            
        //    93: aload_2         /* obj */
        //    94: checkcast       Ljava/lang/Double;
        //    97: invokevirtual   java/lang/Double.doubleValue:()D
        //   100: invokespecial   org/apache/xpath/objects/XNumber.<init>:(D)V
        //   103: astore          value
        //   105: goto            334
        //   108: aload_2         /* obj */
        //   109: instanceof      Lorg/w3c/dom/DocumentFragment;
        //   112: ifeq            142
        //   115: aload           xctxt
        //   117: aload_2         /* obj */
        //   118: checkcast       Lorg/w3c/dom/DocumentFragment;
        //   121: invokevirtual   org/apache/xpath/XPathContext.getDTMHandleFromNode:(Lorg/w3c/dom/Node;)I
        //   124: istore          handle
        //   126: new             Lorg/apache/xpath/objects/XRTreeFrag;
        //   129: dup            
        //   130: iload           handle
        //   132: aload           xctxt
        //   134: invokespecial   org/apache/xpath/objects/XRTreeFrag.<init>:(ILorg/apache/xpath/XPathContext;)V
        //   137: astore          value
        //   139: goto            334
        //   142: aload_2         /* obj */
        //   143: instanceof      Lorg/apache/xml/dtm/DTM;
        //   146: ifeq            194
        //   149: aload_2         /* obj */
        //   150: checkcast       Lorg/apache/xml/dtm/DTM;
        //   153: astore          dtm
        //   155: new             Lorg/apache/xpath/axes/DescendantIterator;
        //   158: dup            
        //   159: invokespecial   org/apache/xpath/axes/DescendantIterator.<init>:()V
        //   162: astore          iterator
        //   164: aload           iterator
        //   166: aload           dtm
        //   168: invokeinterface org/apache/xml/dtm/DTM.getDocument:()I
        //   173: aload           xctxt
        //   175: invokeinterface org/apache/xml/dtm/DTMIterator.setRoot:(ILjava/lang/Object;)V
        //   180: new             Lorg/apache/xpath/objects/XNodeSet;
        //   183: dup            
        //   184: aload           iterator
        //   186: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   189: astore          value
        //   191: goto            334
        //   194: aload_2         /* obj */
        //   195: instanceof      Lorg/apache/xml/dtm/DTMAxisIterator;
        //   198: ifeq            233
        //   201: aload_2         /* obj */
        //   202: checkcast       Lorg/apache/xml/dtm/DTMAxisIterator;
        //   205: astore          iter
        //   207: new             Lorg/apache/xpath/axes/OneStepIterator;
        //   210: dup            
        //   211: aload           iter
        //   213: iconst_m1      
        //   214: invokespecial   org/apache/xpath/axes/OneStepIterator.<init>:(Lorg/apache/xml/dtm/DTMAxisIterator;I)V
        //   217: astore          iterator
        //   219: new             Lorg/apache/xpath/objects/XNodeSet;
        //   222: dup            
        //   223: aload           iterator
        //   225: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   228: astore          value
        //   230: goto            334
        //   233: aload_2         /* obj */
        //   234: instanceof      Lorg/apache/xml/dtm/DTMIterator;
        //   237: ifeq            256
        //   240: new             Lorg/apache/xpath/objects/XNodeSet;
        //   243: dup            
        //   244: aload_2         /* obj */
        //   245: checkcast       Lorg/apache/xml/dtm/DTMIterator;
        //   248: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   251: astore          value
        //   253: goto            334
        //   256: aload_2         /* obj */
        //   257: instanceof      Lorg/w3c/dom/traversal/NodeIterator;
        //   260: ifeq            288
        //   263: new             Lorg/apache/xpath/objects/XNodeSet;
        //   266: dup            
        //   267: new             Lorg/apache/xpath/NodeSetDTM;
        //   270: dup            
        //   271: aload_2         /* obj */
        //   272: checkcast       Lorg/w3c/dom/traversal/NodeIterator;
        //   275: aload           xctxt
        //   277: invokespecial   org/apache/xpath/NodeSetDTM.<init>:(Lorg/w3c/dom/traversal/NodeIterator;Lorg/apache/xpath/XPathContext;)V
        //   280: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   283: astore          value
        //   285: goto            334
        //   288: aload_2         /* obj */
        //   289: instanceof      Lorg/w3c/dom/Node;
        //   292: ifeq            321
        //   295: new             Lorg/apache/xpath/objects/XNodeSet;
        //   298: dup            
        //   299: aload           xctxt
        //   301: aload_2         /* obj */
        //   302: checkcast       Lorg/w3c/dom/Node;
        //   305: invokevirtual   org/apache/xpath/XPathContext.getDTMHandleFromNode:(Lorg/w3c/dom/Node;)I
        //   308: aload           xctxt
        //   310: invokevirtual   org/apache/xpath/XPathContext.getDTMManager:()Lorg/apache/xml/dtm/DTMManager;
        //   313: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(ILorg/apache/xml/dtm/DTMManager;)V
        //   316: astore          value
        //   318: goto            334
        //   321: new             Lorg/apache/xpath/objects/XString;
        //   324: dup            
        //   325: aload_2         /* obj */
        //   326: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   329: invokespecial   org/apache/xpath/objects/XString.<init>:(Ljava/lang/String;)V
        //   332: astore          value
        //   334: aload           value
        //   336: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //   339: istore          type
        //   341: iload           type
        //   343: tableswitch {
        //                2: 376
        //                3: 376
        //                4: 376
        //                5: 403
        //                6: 617
        //          default: 630
        //        }
        //   376: aload           value
        //   378: invokevirtual   org/apache/xpath/objects/XObject.str:()Ljava/lang/String;
        //   381: astore          s
        //   383: aload_3         /* rtreeHandler */
        //   384: aload           s
        //   386: invokevirtual   java/lang/String.toCharArray:()[C
        //   389: iconst_0       
        //   390: aload           s
        //   392: invokevirtual   java/lang/String.length:()I
        //   395: invokeinterface org/xml/sax/ContentHandler.characters:([CII)V
        //   400: goto            630
        //   403: aload           value
        //   405: invokevirtual   org/apache/xpath/objects/XObject.iter:()Lorg/apache/xml/dtm/DTMIterator;
        //   408: astore          nl
        //   410: goto            600
        //   413: aload           nl
        //   415: iload           9
        //   417: invokeinterface org/apache/xml/dtm/DTMIterator.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   422: astore          dtm
        //   424: iload           9
        //   426: istore          top
        //   428: goto            594
        //   431: aload_3         /* rtreeHandler */
        //   432: invokeinterface org/apache/xml/serializer/SerializationHandler.flushPending:()V
        //   437: iload           9
        //   439: aload           dtm
        //   441: iload           9
        //   443: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   448: aload           dtm
        //   450: aload_3         /* rtreeHandler */
        //   451: iconst_1       
        //   452: invokestatic    org/apache/xalan/transformer/ClonerToResultTree.cloneToResultTree:(IILorg/apache/xml/dtm/DTM;Lorg/apache/xml/serializer/SerializationHandler;Z)V
        //   455: aload           dtm
        //   457: iload           9
        //   459: invokeinterface org/apache/xml/dtm/DTM.getFirstChild:(I)I
        //   464: istore          nextNode
        //   466: goto            584
        //   469: iconst_1       
        //   470: aload           dtm
        //   472: iload           9
        //   474: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   479: if_icmpne       501
        //   482: aload_3         /* rtreeHandler */
        //   483: ldc             ""
        //   485: ldc             ""
        //   487: aload           dtm
        //   489: iload           9
        //   491: invokeinterface org/apache/xml/dtm/DTM.getNodeName:(I)Ljava/lang/String;
        //   496: invokeinterface org/xml/sax/ContentHandler.endElement:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   501: iload           top
        //   503: iload           9
        //   505: if_icmpne       511
        //   508: goto            590
        //   511: aload           dtm
        //   513: iload           9
        //   515: invokeinterface org/apache/xml/dtm/DTM.getNextSibling:(I)I
        //   520: istore          nextNode
        //   522: iconst_m1      
        //   523: iload           nextNode
        //   525: if_icmpne       584
        //   528: aload           dtm
        //   530: iload           9
        //   532: invokeinterface org/apache/xml/dtm/DTM.getParent:(I)I
        //   537: istore          pos
        //   539: iload           top
        //   541: iload           pos
        //   543: if_icmpne       584
        //   546: iconst_1       
        //   547: aload           dtm
        //   549: iload           pos
        //   551: invokeinterface org/apache/xml/dtm/DTM.getNodeType:(I)S
        //   556: if_icmpne       578
        //   559: aload_3         /* rtreeHandler */
        //   560: ldc             ""
        //   562: ldc             ""
        //   564: aload           dtm
        //   566: iload           pos
        //   568: invokeinterface org/apache/xml/dtm/DTM.getNodeName:(I)Ljava/lang/String;
        //   573: invokeinterface org/xml/sax/ContentHandler.endElement:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   578: iconst_m1      
        //   579: istore          nextNode
        //   581: goto            590
        //   584: iconst_m1      
        //   585: iload           nextNode
        //   587: if_icmpeq       469
        //   590: iload           nextNode
        //   592: istore          pos
        //   594: iconst_m1      
        //   595: iload           pos
        //   597: if_icmpne       431
        //   600: iconst_m1      
        //   601: aload           nl
        //   603: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   608: dup            
        //   609: istore          pos
        //   611: if_icmpne       413
        //   614: goto            630
        //   617: aload_3         /* rtreeHandler */
        //   618: aload           value
        //   620: aload_0         /* this */
        //   621: getfield        org/apache/xalan/extensions/XSLProcessorContext.transformer:Lorg/apache/xalan/transformer/TransformerImpl;
        //   624: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //   627: invokestatic    org/apache/xalan/serialize/SerializerUtils.outputResultTreeFragment:(Lorg/apache/xml/serializer/SerializationHandler;Lorg/apache/xpath/objects/XObject;Lorg/apache/xpath/XPathContext;)V
        //   630: goto            643
        //   633: astore_3        /* se */
        //   634: new             Ljavax/xml/transform/TransformerException;
        //   637: dup            
        //   638: aload_3         /* se */
        //   639: invokespecial   javax/xml/transform/TransformerException.<init>:(Ljava/lang/Throwable;)V
        //   642: athrow         
        //   643: return         
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //  throws java.net.MalformedURLException
        //  throws java.io.FileNotFoundException
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name            Signature
        //  -----  ------  ----  --------------  -------------------------------------------------
        //  0      644     0     this            Lorg/apache/xalan/extensions/XSLProcessorContext;
        //  0      644     1     stylesheetTree  Lorg/apache/xalan/templates/Stylesheet;
        //  0      644     2     obj             Ljava/lang/Object;
        //  8      622     3     rtreeHandler    Lorg/apache/xml/serializer/SerializationHandler;
        //  17     613     4     xctxt           Lorg/apache/xpath/XPathContext;
        //  30     600     5     value           Lorg/apache/xpath/objects/XObject;
        //  126    13      6     handle          I
        //  155    36      6     dtm             Lorg/apache/xml/dtm/DTM;
        //  164    27      7     iterator        Lorg/apache/xml/dtm/DTMIterator;
        //  207    23      6     iter            Lorg/apache/xml/dtm/DTMAxisIterator;
        //  219    11      7     iterator        Lorg/apache/xml/dtm/DTMIterator;
        //  341    289     6     type            I
        //  383    247     7     s               Ljava/lang/String;
        //  410    220     8     nl              Lorg/apache/xml/dtm/DTMIterator;
        //  539    91      9     pos             I
        //  424    176     10    dtm             Lorg/apache/xml/dtm/DTM;
        //  428    172     11    top             I
        //  466    128     12    nextNode        I
        //  634    9       3     se              Lorg/xml/sax/SAXException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      630    633    643    Lorg/xml/sax/SAXException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}

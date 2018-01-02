// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.Function2Args;

public class FuncKey extends Function2Args
{
    static final long serialVersionUID = 9089293100115347340L;
    private static Boolean ISTRUE;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* xctxt */
        //     1: invokevirtual   org/apache/xpath/XPathContext.getOwnerObject:()Ljava/lang/Object;
        //     4: checkcast       Lorg/apache/xalan/transformer/TransformerImpl;
        //     7: astore_2        /* transformer */
        //     8: aconst_null    
        //     9: astore_3        /* nodes */
        //    10: aload_1         /* xctxt */
        //    11: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //    14: istore          context
        //    16: aload_1         /* xctxt */
        //    17: iload           context
        //    19: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //    22: astore          dtm
        //    24: aload           dtm
        //    26: iload           context
        //    28: invokeinterface org/apache/xml/dtm/DTM.getDocumentRoot:(I)I
        //    33: istore          docContext
        //    35: iconst_m1      
        //    36: iload           docContext
        //    38: if_icmpne       41
        //    41: aload_0         /* this */
        //    42: invokevirtual   org/apache/xpath/functions/FunctionOneArg.getArg0:()Lorg/apache/xpath/Expression;
        //    45: aload_1         /* xctxt */
        //    46: invokevirtual   org/apache/xpath/Expression.execute:(Lorg/apache/xpath/XPathContext;)Lorg/apache/xpath/objects/XObject;
        //    49: invokevirtual   org/apache/xpath/objects/XObject.str:()Ljava/lang/String;
        //    52: astore          xkeyname
        //    54: new             Lorg/apache/xml/utils/QName;
        //    57: dup            
        //    58: aload           xkeyname
        //    60: aload_1         /* xctxt */
        //    61: invokevirtual   org/apache/xpath/XPathContext.getNamespaceContext:()Lorg/apache/xml/utils/PrefixResolver;
        //    64: invokespecial   org/apache/xml/utils/QName.<init>:(Ljava/lang/String;Lorg/apache/xml/utils/PrefixResolver;)V
        //    67: astore          keyname
        //    69: aload_0         /* this */
        //    70: invokevirtual   org/apache/xpath/functions/Function2Args.getArg1:()Lorg/apache/xpath/Expression;
        //    73: aload_1         /* xctxt */
        //    74: invokevirtual   org/apache/xpath/Expression.execute:(Lorg/apache/xpath/XPathContext;)Lorg/apache/xpath/objects/XObject;
        //    77: astore          arg
        //    79: iconst_4       
        //    80: aload           arg
        //    82: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //    85: if_icmpne       92
        //    88: iconst_1       
        //    89: goto            93
        //    92: iconst_0       
        //    93: istore          argIsNodeSetDTM
        //    95: aload_2         /* transformer */
        //    96: invokevirtual   org/apache/xalan/transformer/TransformerImpl.getKeyManager:()Lorg/apache/xalan/transformer/KeyManager;
        //    99: astore          kmgr
        //   101: iload           argIsNodeSetDTM
        //   103: ifeq            135
        //   106: aload           arg
        //   108: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //   111: astore          ns
        //   113: aload           ns
        //   115: iconst_1       
        //   116: invokevirtual   org/apache/xpath/axes/NodeSequence.setShouldCacheNodes:(Z)V
        //   119: aload           ns
        //   121: invokevirtual   org/apache/xpath/axes/NodeSequence.getLength:()I
        //   124: istore          len
        //   126: iload           len
        //   128: iconst_1       
        //   129: if_icmpgt       135
        //   132: iconst_0       
        //   133: istore          argIsNodeSetDTM
        //   135: iload           argIsNodeSetDTM
        //   137: ifeq            311
        //   140: aconst_null    
        //   141: astore          usedrefs
        //   143: aload           arg
        //   145: invokevirtual   org/apache/xpath/objects/XObject.iter:()Lorg/apache/xml/dtm/DTMIterator;
        //   148: astore          ni
        //   150: new             Lorg/apache/xpath/axes/UnionPathIterator;
        //   153: dup            
        //   154: invokespecial   org/apache/xpath/axes/UnionPathIterator.<init>:()V
        //   157: astore          upi
        //   159: aload           upi
        //   161: aload_0         /* this */
        //   162: invokevirtual   org/apache/xpath/Expression.exprSetParent:(Lorg/apache/xpath/ExpressionNode;)V
        //   165: goto            270
        //   168: aload_1         /* xctxt */
        //   169: iload           14
        //   171: invokevirtual   org/apache/xpath/XPathContext.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   174: astore          dtm
        //   176: aload           dtm
        //   178: iload           14
        //   180: invokeinterface org/apache/xml/dtm/DTM.getStringValue:(I)Lorg/apache/xml/utils/XMLString;
        //   185: astore          ref
        //   187: aconst_null    
        //   188: aload           ref
        //   190: if_acmpne       196
        //   193: goto            270
        //   196: aconst_null    
        //   197: aload           usedrefs
        //   199: if_acmpne       211
        //   202: new             Ljava/util/Hashtable;
        //   205: dup            
        //   206: invokespecial   java/util/Hashtable.<init>:()V
        //   209: astore          usedrefs
        //   211: aload           usedrefs
        //   213: aload           ref
        //   215: invokevirtual   java/util/Hashtable.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   218: ifnull          224
        //   221: goto            270
        //   224: aload           usedrefs
        //   226: aload           ref
        //   228: getstatic       org/apache/xalan/templates/FuncKey.ISTRUE:Ljava/lang/Boolean;
        //   231: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   234: pop            
        //   235: aload           kmgr
        //   237: aload_1         /* xctxt */
        //   238: iload           docContext
        //   240: aload           keyname
        //   242: aload           ref
        //   244: aload_1         /* xctxt */
        //   245: invokevirtual   org/apache/xpath/XPathContext.getNamespaceContext:()Lorg/apache/xml/utils/PrefixResolver;
        //   248: invokevirtual   org/apache/xalan/transformer/KeyManager.getNodeSetDTMByKey:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/QName;Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XNodeSet;
        //   251: astore          nl
        //   253: aload           nl
        //   255: aload_1         /* xctxt */
        //   256: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //   259: aload_1         /* xctxt */
        //   260: invokevirtual   org/apache/xpath/axes/NodeSequence.setRoot:(ILjava/lang/Object;)V
        //   263: aload           upi
        //   265: aload           nl
        //   267: invokevirtual   org/apache/xpath/axes/UnionPathIterator.addIterator:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   270: iconst_m1      
        //   271: aload           ni
        //   273: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   278: dup            
        //   279: istore          pos
        //   281: if_icmpne       168
        //   284: aload_1         /* xctxt */
        //   285: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //   288: istore          current
        //   290: aload           upi
        //   292: iload           current
        //   294: aload_1         /* xctxt */
        //   295: invokevirtual   org/apache/xpath/axes/UnionPathIterator.setRoot:(ILjava/lang/Object;)V
        //   298: new             Lorg/apache/xpath/objects/XNodeSet;
        //   301: dup            
        //   302: aload           upi
        //   304: invokespecial   org/apache/xpath/objects/XNodeSet.<init>:(Lorg/apache/xml/dtm/DTMIterator;)V
        //   307: astore_3        /* nodes */
        //   308: goto            344
        //   311: aload           arg
        //   313: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //   316: astore          ref
        //   318: aload           kmgr
        //   320: aload_1         /* xctxt */
        //   321: iload           docContext
        //   323: aload           keyname
        //   325: aload           ref
        //   327: aload_1         /* xctxt */
        //   328: invokevirtual   org/apache/xpath/XPathContext.getNamespaceContext:()Lorg/apache/xml/utils/PrefixResolver;
        //   331: invokevirtual   org/apache/xalan/transformer/KeyManager.getNodeSetDTMByKey:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/QName;Lorg/apache/xml/utils/XMLString;Lorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XNodeSet;
        //   334: astore_3        /* nodes */
        //   335: aload_3         /* nodes */
        //   336: aload_1         /* xctxt */
        //   337: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //   340: aload_1         /* xctxt */
        //   341: invokevirtual   org/apache/xpath/axes/NodeSequence.setRoot:(ILjava/lang/Object;)V
        //   344: aload_3         /* nodes */
        //   345: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  ----------------------------------------------
        //  0      346     0     this             Lorg/apache/xalan/templates/FuncKey;
        //  0      346     1     xctxt            Lorg/apache/xpath/XPathContext;
        //  8      338     2     transformer      Lorg/apache/xalan/transformer/TransformerImpl;
        //  10     336     3     nodes            Lorg/apache/xpath/objects/XNodeSet;
        //  16     330     4     context          I
        //  24     322     5     dtm              Lorg/apache/xml/dtm/DTM;
        //  35     311     6     docContext       I
        //  54     292     7     xkeyname         Ljava/lang/String;
        //  69     277     8     keyname          Lorg/apache/xml/utils/QName;
        //  79     267     9     arg              Lorg/apache/xpath/objects/XObject;
        //  95     251     10    argIsNodeSetDTM  Z
        //  101    245     11    kmgr             Lorg/apache/xalan/transformer/KeyManager;
        //  113    22      12    ns               Lorg/apache/xpath/objects/XNodeSet;
        //  126    9       13    len              I
        //  143    165     12    usedrefs         Ljava/util/Hashtable;
        //  150    158     13    ni               Lorg/apache/xml/dtm/DTMIterator;
        //  281    27      14    pos              I
        //  159    149     15    upi              Lorg/apache/xpath/axes/UnionPathIterator;
        //  187    83      16    ref              Lorg/apache/xml/utils/XMLString;
        //  253    17      17    nl               Lorg/apache/xpath/objects/XNodeSet;
        //  290    18      16    current          I
        //  318    26      12    ref              Lorg/apache/xml/utils/XMLString;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        FuncKey.ISTRUE = new Boolean(true);
    }
}

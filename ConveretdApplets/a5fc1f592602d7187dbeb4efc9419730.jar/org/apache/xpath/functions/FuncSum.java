// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSum extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xpath/functions/FunctionOneArg.m_arg0:Lorg/apache/xpath/Expression;
        //     4: aload_1         /* xctxt */
        //     5: aload_1         /* xctxt */
        //     6: invokevirtual   org/apache/xpath/XPathContext.getCurrentNode:()I
        //     9: invokevirtual   org/apache/xpath/Expression.asIterator:(Lorg/apache/xpath/XPathContext;I)Lorg/apache/xml/dtm/DTMIterator;
        //    12: astore_2        /* nodes */
        //    13: dconst_0       
        //    14: dstore_3        /* sum */
        //    15: goto            55
        //    18: aload_2         /* nodes */
        //    19: iload           5
        //    21: invokeinterface org/apache/xml/dtm/DTMIterator.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //    26: astore          dtm
        //    28: aload           dtm
        //    30: iload           5
        //    32: invokeinterface org/apache/xml/dtm/DTM.getStringValue:(I)Lorg/apache/xml/utils/XMLString;
        //    37: astore          s
        //    39: aconst_null    
        //    40: aload           s
        //    42: if_acmpeq       55
        //    45: dload_3         /* sum */
        //    46: aload           s
        //    48: invokeinterface org/apache/xml/utils/XMLString.toDouble:()D
        //    53: dadd           
        //    54: dstore_3        /* sum */
        //    55: iconst_m1      
        //    56: aload_2         /* nodes */
        //    57: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    62: dup            
        //    63: istore          pos
        //    65: if_icmpne       18
        //    68: aload_2         /* nodes */
        //    69: invokeinterface org/apache/xml/dtm/DTMIterator.detach:()V
        //    74: new             Lorg/apache/xpath/objects/XNumber;
        //    77: dup            
        //    78: dload_3         /* sum */
        //    79: invokespecial   org/apache/xpath/objects/XNumber.<init>:(D)V
        //    82: areturn        
        //    Exceptions:
        //  throws javax.xml.transform.TransformerException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ------------------------------------
        //  0      83      0     this   Lorg/apache/xpath/functions/FuncSum;
        //  0      83      1     xctxt  Lorg/apache/xpath/XPathContext;
        //  13     70      2     nodes  Lorg/apache/xml/dtm/DTMIterator;
        //  15     68      3     sum    D
        //  65     18      5     pos    I
        //  28     27      6     dtm    Lorg/apache/xml/dtm/DTM;
        //  39     16      7     s      Lorg/apache/xml/utils/XMLString;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}

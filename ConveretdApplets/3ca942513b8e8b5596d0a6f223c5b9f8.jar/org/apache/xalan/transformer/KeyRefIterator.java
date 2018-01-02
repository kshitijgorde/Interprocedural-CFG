// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.dtm.DTMAxisTraverser;
import java.util.Vector;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.XMLString;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.axes.ChildTestIterator;

public class KeyRefIterator extends ChildTestIterator
{
    static final long serialVersionUID = 3837456451659435102L;
    DTMIterator m_keysNodes;
    protected XMLString m_ref;
    protected QName m_name;
    protected Vector m_keyDeclarations;
    
    public KeyRefIterator(final QName name, final XMLString ref, final Vector keyDecls, final DTMIterator ki) {
        super((DTMAxisTraverser)null);
        this.m_name = name;
        this.m_ref = ref;
        this.m_keyDeclarations = keyDecls;
        this.m_keysNodes = ki;
        this.setWhatToShow(-1);
    }
    
    protected int getNextNode() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            15
        //     3: iconst_1       
        //     4: aload_0         /* this */
        //     5: iload_1        
        //     6: invokevirtual   org/apache/xalan/transformer/KeyRefIterator.filterNode:(I)S
        //     9: if_icmpne       15
        //    12: goto            30
        //    15: iconst_m1      
        //    16: aload_0         /* this */
        //    17: getfield        org/apache/xalan/transformer/KeyRefIterator.m_keysNodes:Lorg/apache/xml/dtm/DTMIterator;
        //    20: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    25: dup            
        //    26: istore_1        /* next */
        //    27: if_icmpne       3
        //    30: aload_0         /* this */
        //    31: iload_1         /* next */
        //    32: putfield        org/apache/xpath/axes/LocPathIterator.m_lastFetched:I
        //    35: iload_1         /* next */
        //    36: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------------
        //  0      37      0     this  Lorg/apache/xalan/transformer/KeyRefIterator;
        //  27     10      1     next  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public short filterNode(final int testNode) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2        /* foundKey */
        //     2: aload_0         /* this */
        //     3: getfield        org/apache/xalan/transformer/KeyRefIterator.m_keyDeclarations:Ljava/util/Vector;
        //     6: astore_3        /* keys */
        //     7: aload_0         /* this */
        //     8: getfield        org/apache/xalan/transformer/KeyRefIterator.m_name:Lorg/apache/xml/utils/QName;
        //    11: astore          name
        //    13: aload_0         /* this */
        //    14: getfield        org/apache/xalan/transformer/KeyRefIterator.m_keysNodes:Lorg/apache/xml/dtm/DTMIterator;
        //    17: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //    20: invokevirtual   org/apache/xpath/axes/NodeSequence.getContainedIter:()Lorg/apache/xml/dtm/DTMIterator;
        //    23: checkcast       Lorg/apache/xalan/transformer/KeyIterator;
        //    26: astore          ki
        //    28: aload           ki
        //    30: invokevirtual   org/apache/xpath/axes/LocPathIterator.getXPathContext:()Lorg/apache/xpath/XPathContext;
        //    33: astore          xctxt
        //    35: aconst_null    
        //    36: aload           xctxt
        //    38: if_acmpne       48
        //    41: aload_0         /* this */
        //    42: iconst_0       
        //    43: ldc             "xctxt can not be null here!"
        //    45: invokevirtual   org/apache/xpath/Expression.assertion:(ZLjava/lang/String;)V
        //    48: aload_0         /* this */
        //    49: getfield        org/apache/xalan/transformer/KeyRefIterator.m_ref:Lorg/apache/xml/utils/XMLString;
        //    52: astore          lookupKey
        //    54: aload_3         /* keys */
        //    55: invokevirtual   java/util/Vector.size:()I
        //    58: istore          nDeclarations
        //    60: iconst_0       
        //    61: istore          i
        //    63: goto            215
        //    66: aload_3         /* keys */
        //    67: iload           i
        //    69: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //    72: checkcast       Lorg/apache/xalan/templates/KeyDeclaration;
        //    75: astore          kd
        //    77: aload           kd
        //    79: invokevirtual   org/apache/xalan/templates/KeyDeclaration.getName:()Lorg/apache/xml/utils/QName;
        //    82: aload           name
        //    84: invokevirtual   org/apache/xml/utils/QName.equals:(Ljava/lang/Object;)Z
        //    87: ifne            93
        //    90: goto            212
        //    93: iconst_1       
        //    94: istore_2        /* foundKey */
        //    95: aload           kd
        //    97: invokevirtual   org/apache/xalan/templates/KeyDeclaration.getUse:()Lorg/apache/xpath/XPath;
        //   100: aload           xctxt
        //   102: iload_1         /* testNode */
        //   103: aload           ki
        //   105: invokevirtual   org/apache/xpath/axes/LocPathIterator.getPrefixResolver:()Lorg/apache/xml/utils/PrefixResolver;
        //   108: invokevirtual   org/apache/xpath/XPath.execute:(Lorg/apache/xpath/XPathContext;ILorg/apache/xml/utils/PrefixResolver;)Lorg/apache/xpath/objects/XObject;
        //   111: astore          xuse
        //   113: aload           xuse
        //   115: invokevirtual   org/apache/xpath/objects/XObject.getType:()I
        //   118: iconst_4       
        //   119: if_icmpeq       146
        //   122: aload           xuse
        //   124: invokevirtual   org/apache/xpath/objects/XObject.xstr:()Lorg/apache/xml/utils/XMLString;
        //   127: astore          exprResult
        //   129: aload           lookupKey
        //   131: aload           exprResult
        //   133: invokeinterface org/apache/xml/utils/XMLString.equals:(Lorg/apache/xml/utils/XMLString;)Z
        //   138: ifeq            143
        //   141: iconst_1       
        //   142: ireturn        
        //   143: goto            212
        //   146: aload           xuse
        //   148: checkcast       Lorg/apache/xpath/objects/XNodeSet;
        //   151: invokevirtual   org/apache/xpath/objects/XNodeSet.iterRaw:()Lorg/apache/xml/dtm/DTMIterator;
        //   154: astore          nl
        //   156: goto            198
        //   159: aload_0         /* this */
        //   160: iload           13
        //   162: invokevirtual   org/apache/xpath/axes/LocPathIterator.getDTM:(I)Lorg/apache/xml/dtm/DTM;
        //   165: astore          dtm
        //   167: aload           dtm
        //   169: iload           13
        //   171: invokeinterface org/apache/xml/dtm/DTM.getStringValue:(I)Lorg/apache/xml/utils/XMLString;
        //   176: astore          exprResult
        //   178: aconst_null    
        //   179: aload           exprResult
        //   181: if_acmpeq       198
        //   184: aload           lookupKey
        //   186: aload           exprResult
        //   188: invokeinterface org/apache/xml/utils/XMLString.equals:(Lorg/apache/xml/utils/XMLString;)Z
        //   193: ifeq            198
        //   196: iconst_1       
        //   197: ireturn        
        //   198: iconst_m1      
        //   199: aload           nl
        //   201: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //   206: dup            
        //   207: istore          useNode
        //   209: if_icmpne       159
        //   212: iinc            i, 1
        //   215: iload           i
        //   217: iload           nDeclarations
        //   219: if_icmplt       66
        //   222: goto            237
        //   225: astore          te
        //   227: new             Lorg/apache/xml/utils/WrappedRuntimeException;
        //   230: dup            
        //   231: aload           te
        //   233: invokespecial   org/apache/xml/utils/WrappedRuntimeException.<init>:(Ljava/lang/Exception;)V
        //   236: athrow         
        //   237: iload_2         /* foundKey */
        //   238: ifne            266
        //   241: new             Ljava/lang/RuntimeException;
        //   244: dup            
        //   245: ldc             "ER_NO_XSLKEY_DECLARATION"
        //   247: iconst_1       
        //   248: anewarray       Ljava/lang/Object;
        //   251: dup            
        //   252: iconst_0       
        //   253: aload           name
        //   255: invokevirtual   org/apache/xml/utils/QName.getLocalName:()Ljava/lang/String;
        //   258: aastore        
        //   259: invokestatic    org/apache/xalan/res/XSLMessages.createMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   262: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   265: athrow         
        //   266: iconst_2       
        //   267: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name           Signature
        //  -----  ------  ----  -------------  ---------------------------------------------
        //  0      268     0     this           Lorg/apache/xalan/transformer/KeyRefIterator;
        //  0      268     1     testNode       I
        //  2      266     2     foundKey       Z
        //  7      261     3     keys           Ljava/util/Vector;
        //  13     255     4     name           Lorg/apache/xml/utils/QName;
        //  28     240     5     ki             Lorg/apache/xalan/transformer/KeyIterator;
        //  35     233     6     xctxt          Lorg/apache/xpath/XPathContext;
        //  54     168     7     lookupKey      Lorg/apache/xml/utils/XMLString;
        //  60     162     8     nDeclarations  I
        //  63     159     9     i              I
        //  77     135     10    kd             Lorg/apache/xalan/templates/KeyDeclaration;
        //  113    99      11    xuse           Lorg/apache/xpath/objects/XObject;
        //  129    14      12    exprResult     Lorg/apache/xml/utils/XMLString;
        //  156    56      12    nl             Lorg/apache/xml/dtm/DTMIterator;
        //  209    3       13    useNode        I
        //  167    31      14    dtm            Lorg/apache/xml/dtm/DTM;
        //  178    20      15    exprResult     Lorg/apache/xml/utils/XMLString;
        //  227    10      7     te             Ljavax/xml/transform/TransformerException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  48     222    225    237    Ljavax/xml/transform/TransformerException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}

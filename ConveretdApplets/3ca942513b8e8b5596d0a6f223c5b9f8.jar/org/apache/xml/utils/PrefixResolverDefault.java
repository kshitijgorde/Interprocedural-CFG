// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.Node;

public class PrefixResolverDefault implements PrefixResolver
{
    Node m_context;
    
    public PrefixResolverDefault(final Node xpathExpressionContext) {
        this.m_context = xpathExpressionContext;
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        return this.getNamespaceForPrefix(prefix, this.m_context);
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node namespaceContext) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* namespaceContext */
        //     1: astore_3        /* parent */
        //     2: aconst_null    
        //     3: astore          namespace
        //     5: aload_1         /* prefix */
        //     6: ldc             "xml"
        //     8: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    11: ifeq            196
        //    14: ldc             "http://www.w3.org/XML/1998/namespace"
        //    16: astore          namespace
        //    18: goto            226
        //    21: iload           5
        //    23: iconst_1       
        //    24: if_icmpne       189
        //    27: aload_3         /* parent */
        //    28: invokeinterface org/w3c/dom/Node.getNodeName:()Ljava/lang/String;
        //    33: new             Ljava/lang/StringBuffer;
        //    36: dup            
        //    37: invokespecial   java/lang/StringBuffer.<init>:()V
        //    40: aload_1         /* prefix */
        //    41: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    44: ldc             ":"
        //    46: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    49: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    52: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    55: ifne            65
        //    58: aload_3         /* parent */
        //    59: invokeinterface org/w3c/dom/Node.getNamespaceURI:()Ljava/lang/String;
        //    64: areturn        
        //    65: aload_3         /* parent */
        //    66: invokeinterface org/w3c/dom/Node.getAttributes:()Lorg/w3c/dom/NamedNodeMap;
        //    71: astore          nnm
        //    73: iconst_0       
        //    74: istore          i
        //    76: goto            177
        //    79: aload           nnm
        //    81: iload           i
        //    83: invokeinterface org/w3c/dom/NamedNodeMap.item:(I)Lorg/w3c/dom/Node;
        //    88: astore          attr
        //    90: aload           attr
        //    92: invokeinterface org/w3c/dom/Node.getNodeName:()Ljava/lang/String;
        //    97: astore          aname
        //    99: aload           aname
        //   101: ldc             "xmlns:"
        //   103: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   106: istore          isPrefix
        //   108: iload           isPrefix
        //   110: ifne            123
        //   113: aload           aname
        //   115: ldc             "xmlns"
        //   117: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   120: ifeq            174
        //   123: aload           aname
        //   125: bipush          58
        //   127: invokevirtual   java/lang/String.indexOf:(I)I
        //   130: istore          index
        //   132: iload           isPrefix
        //   134: ifeq            149
        //   137: aload           aname
        //   139: iload           index
        //   141: iconst_1       
        //   142: iadd           
        //   143: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   146: goto            151
        //   149: ldc             ""
        //   151: astore          p
        //   153: aload           p
        //   155: aload_1         /* prefix */
        //   156: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   159: ifeq            174
        //   162: aload           attr
        //   164: invokeinterface org/w3c/dom/Node.getNodeValue:()Ljava/lang/String;
        //   169: astore          namespace
        //   171: goto            189
        //   174: iinc            i, 1
        //   177: iload           i
        //   179: aload           nnm
        //   181: invokeinterface org/w3c/dom/NamedNodeMap.getLength:()I
        //   186: if_icmplt       79
        //   189: aload_3         /* parent */
        //   190: invokeinterface org/w3c/dom/Node.getParentNode:()Lorg/w3c/dom/Node;
        //   195: astore_3        /* parent */
        //   196: aconst_null    
        //   197: aload_3         /* parent */
        //   198: if_acmpeq       226
        //   201: aconst_null    
        //   202: aload           namespace
        //   204: if_acmpne       226
        //   207: aload_3         /* parent */
        //   208: invokeinterface org/w3c/dom/Node.getNodeType:()S
        //   213: dup            
        //   214: istore          type
        //   216: iconst_1       
        //   217: if_icmpeq       21
        //   220: iload           type
        //   222: iconst_5       
        //   223: if_icmpeq       21
        //   226: aload           namespace
        //   228: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  --------------------------------------------
        //  0      229     0     this              Lorg/apache/xml/utils/PrefixResolverDefault;
        //  0      229     1     prefix            Ljava/lang/String;
        //  0      229     2     namespaceContext  Lorg/w3c/dom/Node;
        //  2      227     3     parent            Lorg/w3c/dom/Node;
        //  5      224     4     namespace         Ljava/lang/String;
        //  216    10      5     type              I
        //  73     116     6     nnm               Lorg/w3c/dom/NamedNodeMap;
        //  76     113     7     i                 I
        //  90     84      8     attr              Lorg/w3c/dom/Node;
        //  99     75      9     aname             Ljava/lang/String;
        //  108    66      10    isPrefix          Z
        //  132    42      11    index             I
        //  153    21      12    p                 Ljava/lang/String;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String getBaseIdentifier() {
        return null;
    }
    
    public boolean handlesNullPrefixes() {
        return false;
    }
}

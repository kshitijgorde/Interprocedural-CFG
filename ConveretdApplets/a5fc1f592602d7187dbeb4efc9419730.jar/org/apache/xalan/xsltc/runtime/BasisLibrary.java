// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.MessageFormat;
import org.apache.xml.serializer.NamespaceMappings;
import org.apache.xml.utils.XMLChar;
import org.xml.sax.SAXException;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTMManager;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xalan.xsltc.dom.AbsoluteIterator;
import org.apache.xalan.xsltc.dom.StepIterator;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.xalan.xsltc.dom.DOMAdapter;
import org.apache.xml.dtm.ref.DTMDefaultBase;
import org.apache.xalan.xsltc.dom.MultiDOM;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.apache.xalan.xsltc.Translet;
import org.w3c.dom.NodeList;
import org.apache.xalan.xsltc.dom.SingletonIterator;
import org.apache.xalan.xsltc.DOM;
import org.apache.xml.dtm.DTMAxisIterator;
import java.util.ResourceBundle;
import java.text.FieldPosition;
import java.text.DecimalFormat;

public final class BasisLibrary implements Operators
{
    private static final String EMPTYSTRING = "";
    private static final int DOUBLE_FRACTION_DIGITS = 340;
    private static final double lowerBounds = 0.001;
    private static final double upperBounds = 1.0E7;
    private static DecimalFormat defaultFormatter;
    private static String defaultPattern;
    private static FieldPosition _fieldPosition;
    private static char[] _characterArray;
    private static int prefixIndex;
    public static final String RUN_TIME_INTERNAL_ERR = "RUN_TIME_INTERNAL_ERR";
    public static final String RUN_TIME_COPY_ERR = "RUN_TIME_COPY_ERR";
    public static final String DATA_CONVERSION_ERR = "DATA_CONVERSION_ERR";
    public static final String EXTERNAL_FUNC_ERR = "EXTERNAL_FUNC_ERR";
    public static final String EQUALITY_EXPR_ERR = "EQUALITY_EXPR_ERR";
    public static final String INVALID_ARGUMENT_ERR = "INVALID_ARGUMENT_ERR";
    public static final String FORMAT_NUMBER_ERR = "FORMAT_NUMBER_ERR";
    public static final String ITERATOR_CLONE_ERR = "ITERATOR_CLONE_ERR";
    public static final String AXIS_SUPPORT_ERR = "AXIS_SUPPORT_ERR";
    public static final String TYPED_AXIS_SUPPORT_ERR = "TYPED_AXIS_SUPPORT_ERR";
    public static final String STRAY_ATTRIBUTE_ERR = "STRAY_ATTRIBUTE_ERR";
    public static final String STRAY_NAMESPACE_ERR = "STRAY_NAMESPACE_ERR";
    public static final String NAMESPACE_PREFIX_ERR = "NAMESPACE_PREFIX_ERR";
    public static final String DOM_ADAPTER_INIT_ERR = "DOM_ADAPTER_INIT_ERR";
    public static final String PARSER_DTD_SUPPORT_ERR = "PARSER_DTD_SUPPORT_ERR";
    public static final String NAMESPACES_SUPPORT_ERR = "NAMESPACES_SUPPORT_ERR";
    public static final String CANT_RESOLVE_RELATIVE_URI_ERR = "CANT_RESOLVE_RELATIVE_URI_ERR";
    public static final String UNSUPPORTED_XSL_ERR = "UNSUPPORTED_XSL_ERR";
    public static final String UNSUPPORTED_EXT_ERR = "UNSUPPORTED_EXT_ERR";
    public static final String UNKNOWN_TRANSLET_VERSION_ERR = "UNKNOWN_TRANSLET_VERSION_ERR";
    public static final String INVALID_QNAME_ERR = "INVALID_QNAME_ERR";
    public static final String INVALID_NCNAME_ERR = "INVALID_NCNAME_ERR";
    protected static ResourceBundle m_bundle;
    public static final String ERROR_MESSAGES_KEY = "error-messages";
    static /* synthetic */ Class class$java$lang$String;
    
    public static int countF(final DTMAxisIterator iterator) {
        return iterator.getLast();
    }
    
    public static int positionF(final DTMAxisIterator iterator) {
        return iterator.isReverse() ? (iterator.getLast() - iterator.getPosition() + 1) : iterator.getPosition();
    }
    
    public static double sumF(final DTMAxisIterator iterator, final DOM dom) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: dconst_0       
        //     1: dstore_2        /* result */
        //     2: goto            19
        //     5: dload_2         /* result */
        //     6: aload_1         /* dom */
        //     7: iload           4
        //     9: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    14: invokestatic    java/lang/Double.parseDouble:(Ljava/lang/String;)D
        //    17: dadd           
        //    18: dstore_2        /* result */
        //    19: aload_0         /* iterator */
        //    20: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    25: dup            
        //    26: istore          node
        //    28: iconst_m1      
        //    29: if_icmpne       5
        //    32: dload_2         /* result */
        //    33: dreturn        
        //    34: astore_2        /* result */
        //    35: ldc2_w          NaN
        //    38: dreturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ------------------------------------
        //  0      39      0     iterator  Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      39      1     dom       Lorg/apache/xalan/xsltc/DOM;
        //  2      32      2     result    D
        //  28     6       4     node      I
        //  35     4       2     e         Ljava/lang/NumberFormatException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      34     34     39     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String stringF(final int node, final DOM dom) {
        return dom.getStringValueX(node);
    }
    
    public static String stringF(final Object obj, final DOM dom) {
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node)obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM)obj).getStringValue();
        }
        return obj.toString();
    }
    
    public static String stringF(final Object obj, final int node, final DOM dom) {
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node)obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM)obj).getStringValue();
        }
        if (obj instanceof Double) {
            final Double d = (Double)obj;
            final String result = d.toString();
            final int length = result.length();
            if (result.charAt(length - 2) == '.' && result.charAt(length - 1) == '0') {
                return result.substring(0, length - 2);
            }
            return result;
        }
        else {
            if (obj != null) {
                return obj.toString();
            }
            return stringF(node, dom);
        }
    }
    
    public static double numberF(final int node, final DOM dom) {
        return stringToReal(dom.getStringValueX(node));
    }
    
    public static double numberF(final Object obj, final DOM dom) {
        if (obj instanceof Double) {
            return (double)obj;
        }
        if (obj instanceof Integer) {
            return (double)obj;
        }
        if (obj instanceof Boolean) {
            return obj ? 1.0 : 0.0;
        }
        if (obj instanceof String) {
            return stringToReal((String)obj);
        }
        if (obj instanceof DTMAxisIterator) {
            final DTMAxisIterator iter = (DTMAxisIterator)obj;
            return stringToReal(dom.getStringValueX(iter.reset().next()));
        }
        if (obj instanceof Node) {
            return stringToReal(dom.getStringValueX(((Node)obj).node));
        }
        if (obj instanceof DOM) {
            return stringToReal(((DOM)obj).getStringValue());
        }
        final String className = obj.getClass().getName();
        runTimeError("INVALID_ARGUMENT_ERR", className, "number()");
        return 0.0;
    }
    
    public static double roundF(final double d) {
        return (d < -0.5 || d > 0.0) ? Math.floor(d + 0.5) : ((d == 0.0) ? d : (Double.isNaN(d) ? Double.NaN : -0.0));
    }
    
    public static boolean booleanF(final Object obj) {
        if (obj instanceof Double) {
            final double temp = (double)obj;
            return temp != 0.0 && !Double.isNaN(temp);
        }
        if (obj instanceof Integer) {
            return (double)obj != 0.0;
        }
        if (obj instanceof Boolean) {
            return (boolean)obj;
        }
        if (obj instanceof String) {
            return !((String)obj).equals("");
        }
        if (obj instanceof DTMAxisIterator) {
            final DTMAxisIterator iter = (DTMAxisIterator)obj;
            return iter.reset().next() != -1;
        }
        if (obj instanceof Node) {
            return true;
        }
        if (obj instanceof DOM) {
            final String temp2 = ((DOM)obj).getStringValue();
            return !temp2.equals("");
        }
        final String className = obj.getClass().getName();
        runTimeError("INVALID_ARGUMENT_ERR", className, "number()");
        return false;
    }
    
    public static String substringF(final String value, final double start) {
        try {
            final int strlen = value.length();
            int istart = (int)Math.round(start) - 1;
            if (Double.isNaN(start)) {
                return "";
            }
            if (istart > strlen) {
                return "";
            }
            if (istart < 1) {
                istart = 0;
            }
            return value.substring(istart);
        }
        catch (IndexOutOfBoundsException e) {
            runTimeError("RUN_TIME_INTERNAL_ERR", "substring()");
            return null;
        }
    }
    
    public static String substringF(final String value, final double start, final double length) {
        try {
            final int strlen = value.length();
            int istart = (int)Math.round(start) - 1;
            int isum = istart + (int)Math.round(length);
            if (Double.isInfinite(length)) {
                isum = Integer.MAX_VALUE;
            }
            if (Double.isNaN(start) || Double.isNaN(length)) {
                return "";
            }
            if (Double.isInfinite(start)) {
                return "";
            }
            if (istart > strlen) {
                return "";
            }
            if (isum < 0) {
                return "";
            }
            if (istart < 0) {
                istart = 0;
            }
            if (isum > strlen) {
                return value.substring(istart);
            }
            return value.substring(istart, isum);
        }
        catch (IndexOutOfBoundsException e) {
            runTimeError("RUN_TIME_INTERNAL_ERR", "substring()");
            return null;
        }
    }
    
    public static String substring_afterF(final String value, final String substring) {
        final int index = value.indexOf(substring);
        if (index >= 0) {
            return value.substring(index + substring.length());
        }
        return "";
    }
    
    public static String substring_beforeF(final String value, final String substring) {
        final int index = value.indexOf(substring);
        if (index >= 0) {
            return value.substring(0, index);
        }
        return "";
    }
    
    public static String translateF(final String value, final String from, final String to) {
        final int tol = to.length();
        final int froml = from.length();
        final int valuel = value.length();
        final StringBuffer result = new StringBuffer();
        for (int i = 0; i < valuel; ++i) {
            final char ch = value.charAt(i);
            int j = 0;
            while (j < froml) {
                if (ch == from.charAt(j)) {
                    if (j < tol) {
                        result.append(to.charAt(j));
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
            if (j == froml) {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    public static String normalize_spaceF(final int node, final DOM dom) {
        return normalize_spaceF(dom.getStringValueX(node));
    }
    
    public static String normalize_spaceF(final String value) {
        int i = 0;
        final int n = value.length();
        final StringBuffer result = new StringBuffer();
        while (i < n) {
            if (!isWhiteSpace(value.charAt(i))) {
                break;
            }
            ++i;
        }
        while (true) {
            if (i >= n || isWhiteSpace(value.charAt(i))) {
                if (i == n) {
                    break;
                }
                while (i < n && isWhiteSpace(value.charAt(i))) {
                    ++i;
                }
                if (i >= n) {
                    continue;
                }
                result.append(' ');
            }
            else {
                result.append(value.charAt(i++));
            }
        }
        return result.toString();
    }
    
    public static String generate_idF(final int node) {
        if (node > 0) {
            return "N" + node;
        }
        return "";
    }
    
    public static String getLocalName(String value) {
        int idx = value.lastIndexOf(58);
        if (idx >= 0) {
            value = value.substring(idx + 1);
        }
        idx = value.lastIndexOf(64);
        if (idx >= 0) {
            value = value.substring(idx + 1);
        }
        return value;
    }
    
    public static void unresolved_externalF(final String name) {
        runTimeError("EXTERNAL_FUNC_ERR", name);
    }
    
    public static void unsupported_ElementF(final String qname, final boolean isExtension) {
        if (isExtension) {
            runTimeError("UNSUPPORTED_EXT_ERR", qname);
        }
        else {
            runTimeError("UNSUPPORTED_XSL_ERR", qname);
        }
    }
    
    public static String namespace_uriF(final DTMAxisIterator iter, final DOM dom) {
        return namespace_uriF(iter.next(), dom);
    }
    
    public static String system_propertyF(final String name) {
        if (name.equals("xsl:version")) {
            return "1.0";
        }
        if (name.equals("xsl:vendor")) {
            return "Apache Software Foundation (Xalan XSLTC)";
        }
        if (name.equals("xsl:vendor-url")) {
            return "http://xml.apache.org/xalan-j";
        }
        runTimeError("INVALID_ARGUMENT_ERR", name, "system-property()");
        return "";
    }
    
    public static String namespace_uriF(final int node, final DOM dom) {
        final String value = dom.getNodeName(node);
        final int colon = value.lastIndexOf(58);
        if (colon >= 0) {
            return value.substring(0, colon);
        }
        return "";
    }
    
    public static String objectTypeF(final Object obj) {
        if (obj instanceof String) {
            return "string";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        if (obj instanceof Number) {
            return "number";
        }
        if (obj instanceof DOM) {
            return "RTF";
        }
        if (obj instanceof DTMAxisIterator) {
            return "node-set";
        }
        return "unknown";
    }
    
    public static DTMAxisIterator nodesetF(final Object obj) {
        if (obj instanceof DOM) {
            final DOM dom = (DOM)obj;
            return new SingletonIterator(dom.getDocument(), true);
        }
        if (obj instanceof DTMAxisIterator) {
            return (DTMAxisIterator)obj;
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", "node-set", className);
        return null;
    }
    
    private static boolean isWhiteSpace(final char ch) {
        return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
    }
    
    private static boolean compareStrings(final String lstring, final String rstring, final int op, final DOM dom) {
        switch (op) {
            case 0: {
                return lstring.equals(rstring);
            }
            case 1: {
                return !lstring.equals(rstring);
            }
            case 2: {
                return numberF(lstring, dom) > numberF(rstring, dom);
            }
            case 3: {
                return numberF(lstring, dom) < numberF(rstring, dom);
            }
            case 4: {
                return numberF(lstring, dom) >= numberF(rstring, dom);
            }
            case 5: {
                return numberF(lstring, dom) <= numberF(rstring, dom);
            }
            default: {
                runTimeError("RUN_TIME_INTERNAL_ERR", "compare()");
                return false;
            }
        }
    }
    
    public static boolean compare(final DTMAxisIterator left, final DTMAxisIterator right, final int op, final DOM dom) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* left */
        //     1: invokeinterface org/apache/xml/dtm/DTMAxisIterator.reset:()Lorg/apache/xml/dtm/DTMAxisIterator;
        //     6: pop            
        //     7: goto            84
        //    10: aload_3         /* dom */
        //    11: iload           4
        //    13: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    18: astore          lvalue
        //    20: aload_1         /* right */
        //    21: invokeinterface org/apache/xml/dtm/DTMAxisIterator.reset:()Lorg/apache/xml/dtm/DTMAxisIterator;
        //    26: pop            
        //    27: goto            71
        //    30: iload           4
        //    32: iload           6
        //    34: if_icmpne       51
        //    37: iload_2         /* op */
        //    38: ifne            43
        //    41: iconst_1       
        //    42: ireturn        
        //    43: iload_2         /* op */
        //    44: iconst_1       
        //    45: if_icmpne       51
        //    48: goto            71
        //    51: aload           lvalue
        //    53: aload_3         /* dom */
        //    54: iload           6
        //    56: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    61: iload_2         /* op */
        //    62: aload_3         /* dom */
        //    63: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.compareStrings:(Ljava/lang/String;Ljava/lang/String;ILorg/apache/xalan/xsltc/DOM;)Z
        //    66: ifeq            71
        //    69: iconst_1       
        //    70: ireturn        
        //    71: aload_1         /* right */
        //    72: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    77: dup            
        //    78: istore          rnode
        //    80: iconst_m1      
        //    81: if_icmpne       30
        //    84: aload_0         /* left */
        //    85: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    90: dup            
        //    91: istore          lnode
        //    93: iconst_m1      
        //    94: if_icmpne       10
        //    97: iconst_0       
        //    98: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ------------------------------------
        //  0      99      0     left    Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      99      1     right   Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      99      2     op      I
        //  0      99      3     dom     Lorg/apache/xalan/xsltc/DOM;
        //  93     6       4     lnode   I
        //  20     64      5     lvalue  Ljava/lang/String;
        //  80     4       6     rnode   I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean compare(final int node, final DTMAxisIterator iterator, final int op, final DOM dom) {
        Label_0209: {
            switch (op) {
                case 0: {
                    int rnode = iterator.next();
                    if (rnode != -1) {
                        final String value = dom.getStringValueX(node);
                        while (node != rnode && !value.equals(dom.getStringValueX(rnode))) {
                            if ((rnode = iterator.next()) == -1) {
                                break Label_0209;
                            }
                        }
                        return true;
                    }
                    break;
                }
                case 1: {
                    int rnode = iterator.next();
                    if (rnode != -1) {
                        final String value = dom.getStringValueX(node);
                        while (node == rnode || value.equals(dom.getStringValueX(rnode))) {
                            if ((rnode = iterator.next()) == -1) {
                                break Label_0209;
                            }
                        }
                        return true;
                    }
                    break;
                }
                case 3: {
                    int rnode;
                    while ((rnode = iterator.next()) != -1) {
                        if (rnode > node) {
                            return true;
                        }
                    }
                    break;
                }
                case 2: {
                    int rnode;
                    while ((rnode = iterator.next()) != -1) {
                        if (rnode < node) {
                            return true;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public static boolean compare(final DTMAxisIterator left, final double rnumber, final int op, final DOM dom) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_3         /* op */
        //     1: tableswitch {
        //                0: 40
        //                1: 80
        //                2: 120
        //                3: 160
        //                4: 200
        //                5: 240
        //          default: 280
        //        }
        //    40: goto            64
        //    43: aload           dom
        //    45: iload           5
        //    47: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    52: aload           dom
        //    54: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //    57: dload_1         /* rnumber */
        //    58: dcmpl          
        //    59: ifne            64
        //    62: iconst_1       
        //    63: ireturn        
        //    64: aload_0         /* left */
        //    65: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    70: dup            
        //    71: istore          node
        //    73: iconst_m1      
        //    74: if_icmpne       43
        //    77: goto            287
        //    80: goto            104
        //    83: aload           dom
        //    85: iload           node
        //    87: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    92: aload           dom
        //    94: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //    97: dload_1         /* rnumber */
        //    98: dcmpl          
        //    99: ifeq            104
        //   102: iconst_1       
        //   103: ireturn        
        //   104: aload_0         /* left */
        //   105: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //   110: dup            
        //   111: istore          node
        //   113: iconst_m1      
        //   114: if_icmpne       83
        //   117: goto            287
        //   120: goto            144
        //   123: aload           dom
        //   125: iload           node
        //   127: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //   132: aload           dom
        //   134: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //   137: dload_1         /* rnumber */
        //   138: dcmpl          
        //   139: ifle            144
        //   142: iconst_1       
        //   143: ireturn        
        //   144: aload_0         /* left */
        //   145: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //   150: dup            
        //   151: istore          node
        //   153: iconst_m1      
        //   154: if_icmpne       123
        //   157: goto            287
        //   160: goto            184
        //   163: aload           dom
        //   165: iload           node
        //   167: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //   172: aload           dom
        //   174: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //   177: dload_1         /* rnumber */
        //   178: dcmpg          
        //   179: ifge            184
        //   182: iconst_1       
        //   183: ireturn        
        //   184: aload_0         /* left */
        //   185: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //   190: dup            
        //   191: istore          node
        //   193: iconst_m1      
        //   194: if_icmpne       163
        //   197: goto            287
        //   200: goto            224
        //   203: aload           dom
        //   205: iload           node
        //   207: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //   212: aload           dom
        //   214: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //   217: dload_1         /* rnumber */
        //   218: dcmpl          
        //   219: iflt            224
        //   222: iconst_1       
        //   223: ireturn        
        //   224: aload_0         /* left */
        //   225: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //   230: dup            
        //   231: istore          node
        //   233: iconst_m1      
        //   234: if_icmpne       203
        //   237: goto            287
        //   240: goto            264
        //   243: aload           dom
        //   245: iload           node
        //   247: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //   252: aload           dom
        //   254: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.numberF:(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D
        //   257: dload_1         /* rnumber */
        //   258: dcmpg          
        //   259: ifgt            264
        //   262: iconst_1       
        //   263: ireturn        
        //   264: aload_0         /* left */
        //   265: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //   270: dup            
        //   271: istore          node
        //   273: iconst_m1      
        //   274: if_icmpne       243
        //   277: goto            287
        //   280: ldc             "RUN_TIME_INTERNAL_ERR"
        //   282: ldc             "compare()"
        //   284: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.runTimeError:(Ljava/lang/String;Ljava/lang/Object;)V
        //   287: iconst_0       
        //   288: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ------------------------------------
        //  0      289     0     left     Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      289     1     rnumber  D
        //  0      289     3     op       I
        //  0      289     4     dom      Lorg/apache/xalan/xsltc/DOM;
        //  73     216     5     node     I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean compare(final DTMAxisIterator left, final String rstring, final int op, final DOM dom) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            22
        //     3: aload_3         /* dom */
        //     4: iload           4
        //     6: invokeinterface org/apache/xalan/xsltc/DOM.getStringValueX:(I)Ljava/lang/String;
        //    11: aload_1         /* rstring */
        //    12: iload_2         /* op */
        //    13: aload_3         /* dom */
        //    14: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.compareStrings:(Ljava/lang/String;Ljava/lang/String;ILorg/apache/xalan/xsltc/DOM;)Z
        //    17: ifeq            22
        //    20: iconst_1       
        //    21: ireturn        
        //    22: aload_0         /* left */
        //    23: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    28: dup            
        //    29: istore          node
        //    31: iconst_m1      
        //    32: if_icmpne       3
        //    35: iconst_0       
        //    36: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ------------------------------------
        //  0      37      0     left     Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      37      1     rstring  Ljava/lang/String;
        //  0      37      2     op       I
        //  0      37      3     dom      Lorg/apache/xalan/xsltc/DOM;
        //  31     6       4     node     I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean compare(Object left, Object right, final int op, final DOM dom) {
        boolean result = false;
        boolean hasSimpleArgs = hasSimpleType(left) && hasSimpleType(right);
        if (op != 0 && op != 1) {
            if (left instanceof Node || right instanceof Node) {
                if (left instanceof Boolean) {
                    right = new Boolean(booleanF(right));
                    hasSimpleArgs = true;
                }
                if (right instanceof Boolean) {
                    left = new Boolean(booleanF(left));
                    hasSimpleArgs = true;
                }
            }
            if (hasSimpleArgs) {
                switch (op) {
                    case 2: {
                        return numberF(left, dom) > numberF(right, dom);
                    }
                    case 3: {
                        return numberF(left, dom) < numberF(right, dom);
                    }
                    case 4: {
                        return numberF(left, dom) >= numberF(right, dom);
                    }
                    case 5: {
                        return numberF(left, dom) <= numberF(right, dom);
                    }
                    default: {
                        runTimeError("RUN_TIME_INTERNAL_ERR", "compare()");
                        break;
                    }
                }
            }
        }
        if (hasSimpleArgs) {
            if (left instanceof Boolean || right instanceof Boolean) {
                result = (booleanF(left) == booleanF(right));
            }
            else if (left instanceof Double || right instanceof Double || left instanceof Integer || right instanceof Integer) {
                result = (numberF(left, dom) == numberF(right, dom));
            }
            else {
                result = stringF(left, dom).equals(stringF(right, dom));
            }
            if (op == 1) {
                result = !result;
            }
        }
        else {
            if (left instanceof Node) {
                left = new SingletonIterator(((Node)left).node);
            }
            if (right instanceof Node) {
                right = new SingletonIterator(((Node)right).node);
            }
            if (hasSimpleType(left) || (left instanceof DOM && right instanceof DTMAxisIterator)) {
                final Object temp = right;
                right = left;
                left = temp;
            }
            if (left instanceof DOM) {
                if (right instanceof Boolean) {
                    result = (boolean)right;
                    return result == (op == 0);
                }
                final String sleft = ((DOM)left).getStringValue();
                if (right instanceof Number) {
                    result = (((Number)right).doubleValue() == stringToReal(sleft));
                }
                else if (right instanceof String) {
                    result = sleft.equals(right);
                }
                else if (right instanceof DOM) {
                    result = sleft.equals(((DOM)right).getStringValue());
                }
                if (op == 1) {
                    result = !result;
                }
                return result;
            }
            else {
                final DTMAxisIterator iter = ((DTMAxisIterator)left).reset();
                if (right instanceof DTMAxisIterator) {
                    result = compare(iter, (DTMAxisIterator)right, op, dom);
                }
                else if (right instanceof String) {
                    result = compare(iter, (String)right, op, dom);
                }
                else if (right instanceof Number) {
                    final double temp2 = ((Number)right).doubleValue();
                    result = compare(iter, temp2, op, dom);
                }
                else if (right instanceof Boolean) {
                    final boolean temp3 = (boolean)right;
                    result = (iter.reset().next() != -1 == temp3);
                }
                else if (right instanceof DOM) {
                    result = compare(iter, ((DOM)right).getStringValue(), op, dom);
                }
                else {
                    if (right == null) {
                        return false;
                    }
                    final String className = right.getClass().getName();
                    runTimeError("INVALID_ARGUMENT_ERR", className, "compare()");
                }
            }
        }
        return result;
    }
    
    public static boolean testLanguage(String testLang, final DOM dom, final int node) {
        String nodeLang = dom.getLanguage(node);
        if (nodeLang == null) {
            return false;
        }
        nodeLang = nodeLang.toLowerCase();
        testLang = testLang.toLowerCase();
        if (testLang.length() == 2) {
            return nodeLang.startsWith(testLang);
        }
        return nodeLang.equals(testLang);
    }
    
    private static boolean hasSimpleType(final Object obj) {
        return obj instanceof Boolean || obj instanceof Double || obj instanceof Integer || obj instanceof String || obj instanceof Node || obj instanceof DOM;
    }
    
    public static double stringToReal(final String s) {
        try {
            return Double.valueOf(s);
        }
        catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public static int stringToInt(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public static String realToString(final double d) {
        final double m = Math.abs(d);
        if (m >= 0.001 && m < 1.0E7) {
            final String result = Double.toString(d);
            final int length = result.length();
            if (result.charAt(length - 2) == '.' && result.charAt(length - 1) == '0') {
                return result.substring(0, length - 2);
            }
            return result;
        }
        else {
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                return Double.toString(d);
            }
            return formatNumber(d, BasisLibrary.defaultPattern, BasisLibrary.defaultFormatter);
        }
    }
    
    public static int realToInt(final double d) {
        return (int)d;
    }
    
    public static String formatNumber(final double number, final String pattern, DecimalFormat formatter) {
        if (formatter == null) {
            formatter = BasisLibrary.defaultFormatter;
        }
        try {
            final StringBuffer result = new StringBuffer();
            if (pattern != BasisLibrary.defaultPattern) {
                formatter.applyLocalizedPattern(pattern);
            }
            formatter.format(number, result, BasisLibrary._fieldPosition);
            return result.toString();
        }
        catch (IllegalArgumentException e) {
            runTimeError("FORMAT_NUMBER_ERR", Double.toString(number), pattern);
            return "";
        }
    }
    
    public static DTMAxisIterator referenceToNodeSet(final Object obj) {
        if (obj instanceof Node) {
            return new SingletonIterator(((Node)obj).node);
        }
        if (obj instanceof DTMAxisIterator) {
            return ((DTMAxisIterator)obj).cloneIterator();
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, "node-set");
        return null;
    }
    
    public static NodeList referenceToNodeList(final Object obj, DOM dom) {
        if (obj instanceof Node || obj instanceof DTMAxisIterator) {
            final DTMAxisIterator iter = referenceToNodeSet(obj);
            return dom.makeNodeList(iter);
        }
        if (obj instanceof DOM) {
            dom = (DOM)obj;
            return dom.makeNodeList(0);
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, "org.w3c.dom.NodeList");
        return null;
    }
    
    public static org.w3c.dom.Node referenceToNode(final Object obj, DOM dom) {
        if (obj instanceof Node || obj instanceof DTMAxisIterator) {
            final DTMAxisIterator iter = referenceToNodeSet(obj);
            return dom.makeNode(iter);
        }
        if (obj instanceof DOM) {
            dom = (DOM)obj;
            final DTMAxisIterator iter = dom.getChildren(0);
            return dom.makeNode(iter);
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, "org.w3c.dom.Node");
        return null;
    }
    
    public static long referenceToLong(final Object obj) {
        if (obj instanceof Number) {
            return ((Number)obj).longValue();
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, Long.TYPE);
        return 0L;
    }
    
    public static double referenceToDouble(final Object obj) {
        if (obj instanceof Number) {
            return ((Number)obj).doubleValue();
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, Double.TYPE);
        return 0.0;
    }
    
    public static boolean referenceToBoolean(final Object obj) {
        if (obj instanceof Boolean) {
            return (boolean)obj;
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, Boolean.TYPE);
        return false;
    }
    
    public static String referenceToString(final Object obj, final DOM dom) {
        if (obj instanceof String) {
            return (String)obj;
        }
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node)obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM)obj).getStringValue();
        }
        final String className = obj.getClass().getName();
        runTimeError("DATA_CONVERSION_ERR", className, (BasisLibrary.class$java$lang$String == null) ? (BasisLibrary.class$java$lang$String = class$("java.lang.String")) : BasisLibrary.class$java$lang$String);
        return null;
    }
    
    public static DTMAxisIterator node2Iterator(final org.w3c.dom.Node node, final Translet translet, final DOM dom) {
        final NodeList nodelist = new NodeList() {
            public int getLength() {
                return 1;
            }
            
            public org.w3c.dom.Node item(final int index) {
                if (index == 0) {
                    return node;
                }
                return null;
            }
        };
        return nodeList2Iterator(nodelist, translet, dom);
    }
    
    private static void copyNodes(final NodeList nodeList, final Document doc, final org.w3c.dom.Node parent) {
        for (int size = nodeList.getLength(), i = 0; i < size; ++i) {
            final org.w3c.dom.Node curr = nodeList.item(i);
            final int nodeType = curr.getNodeType();
            String value = null;
            try {
                value = curr.getNodeValue();
            }
            catch (DOMException ex) {
                runTimeError("RUN_TIME_INTERNAL_ERR", ex.getMessage());
                return;
            }
            final String nodeName = curr.getNodeName();
            org.w3c.dom.Node newNode = null;
            switch (nodeType) {
                case 2: {
                    newNode = doc.createAttributeNS(curr.getNamespaceURI(), nodeName);
                    break;
                }
                case 4: {
                    newNode = doc.createCDATASection(value);
                    break;
                }
                case 8: {
                    newNode = doc.createComment(value);
                    break;
                }
                case 11: {
                    newNode = doc.createDocumentFragment();
                    break;
                }
                case 9: {
                    newNode = doc.createElementNS(null, "__document__");
                    copyNodes(curr.getChildNodes(), doc, newNode);
                }
                case 1: {
                    final Element element = doc.createElementNS(curr.getNamespaceURI(), nodeName);
                    if (curr.hasAttributes()) {
                        final NamedNodeMap attributes = curr.getAttributes();
                        for (int k = 0; k < attributes.getLength(); ++k) {
                            final org.w3c.dom.Node attr = attributes.item(k);
                            element.setAttributeNS(attr.getNamespaceURI(), attr.getNodeName(), attr.getNodeValue());
                        }
                    }
                    copyNodes(curr.getChildNodes(), doc, element);
                    newNode = element;
                }
                case 5: {
                    newNode = doc.createEntityReference(nodeName);
                }
                case 7: {
                    newNode = doc.createProcessingInstruction(nodeName, value);
                    break;
                }
                case 3: {
                    newNode = doc.createTextNode(value);
                    break;
                }
            }
            try {
                parent.appendChild(newNode);
            }
            catch (DOMException e) {
                runTimeError("RUN_TIME_INTERNAL_ERR", e.getMessage());
                return;
            }
        }
    }
    
    public static DTMAxisIterator nodeList2Iterator(final NodeList nodeList, final Translet translet, final DOM dom) {
        final DocumentBuilderFactory dfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbldr = null;
        try {
            docbldr = dfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            runTimeError("RUN_TIME_INTERNAL_ERR", e.getMessage());
            return null;
        }
        final Document doc = docbldr.newDocument();
        final org.w3c.dom.Node topElementNode = doc.appendChild(doc.createElementNS("", "__top__"));
        copyNodes(nodeList, doc, topElementNode);
        if (dom instanceof MultiDOM) {
            final MultiDOM multiDOM = (MultiDOM)dom;
            final DTMDefaultBase dtm = (DTMDefaultBase)((DOMAdapter)multiDOM.getMain()).getDOMImpl();
            final DTMManager dtmManager = dtm.getManager();
            final DOM idom = (DOM)dtmManager.getDTM(new DOMSource(doc), false, null, true, false);
            final DOMAdapter domAdapter = new DOMAdapter(idom, translet.getNamesArray(), translet.getUrisArray(), translet.getTypesArray(), translet.getNamespaceArray());
            multiDOM.addDOMAdapter(domAdapter);
            final DTMAxisIterator iter1 = idom.getAxisIterator(3);
            final DTMAxisIterator iter2 = idom.getAxisIterator(3);
            final DTMAxisIterator iter3 = new AbsoluteIterator(new StepIterator(iter1, iter2));
            iter3.setStartNode(0);
            return iter3;
        }
        runTimeError("RUN_TIME_INTERNAL_ERR", "nodeList2Iterator()");
        return null;
    }
    
    public static DOM referenceToResultTree(final Object obj) {
        try {
            return (DOM)obj;
        }
        catch (IllegalArgumentException e) {
            final String className = obj.getClass().getName();
            runTimeError("DATA_CONVERSION_ERR", "reference", className);
            return null;
        }
    }
    
    public static DTMAxisIterator getSingleNode(final DTMAxisIterator iterator) {
        final int node = iterator.next();
        return new SingletonIterator(node);
    }
    
    public static void copy(final Object obj, final SerializationHandler handler, final int node, final DOM dom) {
        try {
            if (obj instanceof DTMAxisIterator) {
                final DTMAxisIterator iter = (DTMAxisIterator)obj;
                dom.copy(iter.reset(), handler);
            }
            else if (obj instanceof Node) {
                dom.copy(((Node)obj).node, handler);
            }
            else if (obj instanceof DOM) {
                final DOM newDom = (DOM)obj;
                newDom.copy(newDom.getDocument(), handler);
            }
            else {
                final String string = obj.toString();
                final int length = string.length();
                if (length > BasisLibrary._characterArray.length) {
                    BasisLibrary._characterArray = new char[length];
                }
                string.getChars(0, length, BasisLibrary._characterArray, 0);
                handler.characters(BasisLibrary._characterArray, 0, length);
            }
        }
        catch (SAXException e) {
            runTimeError("RUN_TIME_COPY_ERR");
        }
    }
    
    public static void checkAttribQName(final String name) {
        final int firstOccur = name.indexOf(":");
        final int lastOccur = name.lastIndexOf(":");
        final String localName = name.substring(lastOccur + 1);
        if (firstOccur > 0) {
            final String newPrefix = name.substring(0, firstOccur);
            if (firstOccur != lastOccur) {
                final String oriPrefix = name.substring(firstOccur + 1, lastOccur);
                if (!XMLChar.isValidNCName(oriPrefix)) {
                    runTimeError("INVALID_QNAME_ERR", oriPrefix + ":" + localName);
                }
            }
            if (!XMLChar.isValidNCName(newPrefix)) {
                runTimeError("INVALID_QNAME_ERR", newPrefix + ":" + localName);
            }
        }
        if (!XMLChar.isValidNCName(localName) || localName.equals("xmlns")) {
            runTimeError("INVALID_QNAME_ERR", localName);
        }
    }
    
    public static void checkNCName(final String name) {
        if (!XMLChar.isValidNCName(name)) {
            runTimeError("INVALID_NCNAME_ERR", name);
        }
    }
    
    public static void checkQName(final String name) {
        if (!XMLChar.isValidQName(name)) {
            runTimeError("INVALID_QNAME_ERR", name);
        }
    }
    
    public static String startXslElement(String qname, String namespace, final SerializationHandler handler, final DOM dom, final int node) {
        try {
            final int index = qname.indexOf(58);
            if (index > 0) {
                final String prefix = qname.substring(0, index);
                Label_0082: {
                    if (namespace != null) {
                        if (namespace.length() != 0) {
                            break Label_0082;
                        }
                    }
                    try {
                        namespace = dom.lookupNamespace(node, prefix);
                    }
                    catch (RuntimeException e2) {
                        handler.flushPending();
                        final NamespaceMappings nm = handler.getNamespaceMappings();
                        namespace = nm.lookupNamespace(prefix);
                        if (namespace == null) {
                            runTimeError("NAMESPACE_PREFIX_ERR", prefix);
                        }
                    }
                }
                handler.startElement(namespace, qname.substring(index + 1), qname);
                handler.namespaceAfterStartElement(prefix, namespace);
            }
            else if (namespace != null && namespace.length() > 0) {
                final String prefix = generatePrefix();
                qname = prefix + ':' + qname;
                handler.startElement(namespace, qname, qname);
                handler.namespaceAfterStartElement(prefix, namespace);
            }
            else {
                handler.startElement(null, null, qname);
            }
        }
        catch (SAXException e) {
            throw new RuntimeException(e.getMessage());
        }
        return qname;
    }
    
    public static String getPrefix(final String qname) {
        final int index = qname.indexOf(58);
        return (index > 0) ? qname.substring(0, index) : null;
    }
    
    public static String generatePrefix() {
        return "ns" + BasisLibrary.prefixIndex++;
    }
    
    public static void runTimeError(final String code) {
        throw new RuntimeException(BasisLibrary.m_bundle.getString(code));
    }
    
    public static void runTimeError(final String code, final Object[] args) {
        final String message = MessageFormat.format(BasisLibrary.m_bundle.getString(code), args);
        throw new RuntimeException(message);
    }
    
    public static void runTimeError(final String code, final Object arg0) {
        runTimeError(code, new Object[] { arg0 });
    }
    
    public static void runTimeError(final String code, final Object arg0, final Object arg1) {
        runTimeError(code, new Object[] { arg0, arg1 });
    }
    
    public static void consoleOutput(final String msg) {
        System.out.println(msg);
    }
    
    public static String replace(final String base, final char ch, final String str) {
        return (base.indexOf(ch) < 0) ? base : replace(base, String.valueOf(ch), new String[] { str });
    }
    
    public static String replace(final String base, final String delim, final String[] str) {
        final int len = base.length();
        final StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            final char ch = base.charAt(i);
            final int k = delim.indexOf(ch);
            if (k >= 0) {
                result.append(str[k]);
            }
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    public static String mapQNameToJavaName(final String base) {
        return replace(base, ".-:/{}?#%*", new String[] { "$dot$", "$dash$", "$colon$", "$slash$", "", "$colon$", "$ques$", "$hash$", "$per$", "$aster$" });
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        BasisLibrary.defaultPattern = "";
        final NumberFormat f = NumberFormat.getInstance(Locale.getDefault());
        (BasisLibrary.defaultFormatter = (DecimalFormat)((f instanceof DecimalFormat) ? f : new DecimalFormat())).setMaximumFractionDigits(340);
        BasisLibrary.defaultFormatter.setMinimumFractionDigits(0);
        BasisLibrary.defaultFormatter.setMinimumIntegerDigits(1);
        BasisLibrary.defaultFormatter.setGroupingUsed(false);
        BasisLibrary._fieldPosition = new FieldPosition(0);
        BasisLibrary._characterArray = new char[32];
        BasisLibrary.prefixIndex = 0;
        final String resource = "org.apache.xalan.xsltc.runtime.ErrorMessages";
        BasisLibrary.m_bundle = ResourceBundle.getBundle(resource);
    }
}

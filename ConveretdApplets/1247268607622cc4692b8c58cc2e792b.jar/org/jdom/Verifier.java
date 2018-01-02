// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.Iterator;
import java.util.List;

public final class Verifier
{
    private static final String CVS_ID = "@(#) $RCSfile: Verifier.java,v $ $Revision: 1.57 $ $Date: 2009/07/23 05:54:23 $ $Name: jdom_1_1_1 $";
    
    public static String checkElementName(final String name) {
        final String reason;
        if ((reason = checkXMLName(name)) != null) {
            return reason;
        }
        if (name.indexOf(":") != -1) {
            return "Element names cannot contain colons";
        }
        return null;
    }
    
    public static String checkAttributeName(final String name) {
        final String reason;
        if ((reason = checkXMLName(name)) != null) {
            return reason;
        }
        if (name.indexOf(":") != -1) {
            return "Attribute names cannot contain colons";
        }
        if (name.equals("xmlns")) {
            return "An Attribute name may not be \"xmlns\"; use the Namespace class to manage namespaces";
        }
        return null;
    }
    
    public static String checkCharacterData(final String text) {
        if (text == null) {
            return "A null is not a legal XML value";
        }
        for (int i = 0, len = text.length(); i < len; ++i) {
            int ch = text.charAt(i);
            if (isHighSurrogate((char)ch)) {
                if (++i >= len) {
                    return "Surrogate Pair Truncated";
                }
                final char low = text.charAt(i);
                if (!isLowSurrogate(low)) {
                    return "Illegal Surrogate Pair";
                }
                ch = decodeSurrogatePair((char)ch, low);
            }
            if (!isXMLCharacter(ch)) {
                return "0x" + Integer.toHexString(ch) + " is not a legal XML character";
            }
        }
        return null;
    }
    
    public static String checkCDATASection(final String data) {
        String reason = null;
        if ((reason = checkCharacterData(data)) != null) {
            return reason;
        }
        if (data.indexOf("]]>") != -1) {
            return "CDATA cannot internally contain a CDATA ending delimiter (]]>)";
        }
        return null;
    }
    
    public static String checkNamespacePrefix(final String prefix) {
        if (prefix == null || prefix.equals("")) {
            return null;
        }
        final char first = prefix.charAt(0);
        if (isXMLDigit(first)) {
            return "Namespace prefixes cannot begin with a number";
        }
        if (first == '$') {
            return "Namespace prefixes cannot begin with a dollar sign ($)";
        }
        if (first == '-') {
            return "Namespace prefixes cannot begin with a hyphen (-)";
        }
        if (first == '.') {
            return "Namespace prefixes cannot begin with a period (.)";
        }
        if (prefix.toLowerCase().startsWith("xml")) {
            return "Namespace prefixes cannot begin with \"xml\" in any combination of case";
        }
        for (int i = 0, len = prefix.length(); i < len; ++i) {
            final char c = prefix.charAt(i);
            if (!isXMLNameCharacter(c)) {
                return "Namespace prefixes cannot contain the character \"" + c + "\"";
            }
        }
        if (prefix.indexOf(":") != -1) {
            return "Namespace prefixes cannot contain colons";
        }
        return null;
    }
    
    public static String checkNamespaceURI(final String uri) {
        if (uri == null || uri.equals("")) {
            return null;
        }
        final char first = uri.charAt(0);
        if (Character.isDigit(first)) {
            return "Namespace URIs cannot begin with a number";
        }
        if (first == '$') {
            return "Namespace URIs cannot begin with a dollar sign ($)";
        }
        if (first == '-') {
            return "Namespace URIs cannot begin with a hyphen (-)";
        }
        return null;
    }
    
    public static String checkNamespaceCollision(final Namespace namespace, final Namespace other) {
        String reason = null;
        final String p1 = namespace.getPrefix();
        final String u1 = namespace.getURI();
        final String p2 = other.getPrefix();
        final String u2 = other.getURI();
        if (p1.equals(p2) && !u1.equals(u2)) {
            reason = "The namespace prefix \"" + p1 + "\" collides";
        }
        return reason;
    }
    
    public static String checkNamespaceCollision(final Attribute attribute, final Element element) {
        final Namespace namespace = attribute.getNamespace();
        final String prefix = namespace.getPrefix();
        if ("".equals(prefix)) {
            return null;
        }
        return checkNamespaceCollision(namespace, element);
    }
    
    public static String checkNamespaceCollision(final Namespace namespace, final Element element) {
        String reason = checkNamespaceCollision(namespace, element.getNamespace());
        if (reason != null) {
            return reason + " with the element namespace prefix";
        }
        reason = checkNamespaceCollision(namespace, element.getAdditionalNamespaces());
        if (reason != null) {
            return reason;
        }
        reason = checkNamespaceCollision(namespace, element.getAttributes());
        if (reason != null) {
            return reason;
        }
        return null;
    }
    
    public static String checkNamespaceCollision(final Namespace namespace, final Attribute attribute) {
        String reason = null;
        if (!attribute.getNamespace().equals(Namespace.NO_NAMESPACE)) {
            reason = checkNamespaceCollision(namespace, attribute.getNamespace());
            if (reason != null) {
                reason += " with an attribute namespace prefix on the element";
            }
        }
        return reason;
    }
    
    public static String checkNamespaceCollision(final Namespace namespace, final List list) {
        if (list == null) {
            return null;
        }
        String reason = null;
        final Iterator i = list.iterator();
        while (reason == null && i.hasNext()) {
            final Object obj = i.next();
            if (obj instanceof Attribute) {
                reason = checkNamespaceCollision(namespace, (Attribute)obj);
            }
            else if (obj instanceof Element) {
                reason = checkNamespaceCollision(namespace, (Element)obj);
            }
            else {
                if (!(obj instanceof Namespace)) {
                    continue;
                }
                reason = checkNamespaceCollision(namespace, (Namespace)obj);
                if (reason == null) {
                    continue;
                }
                reason += " with an additional namespace declared by the element";
            }
        }
        return reason;
    }
    
    public static String checkProcessingInstructionTarget(final String target) {
        final String reason;
        if ((reason = checkXMLName(target)) != null) {
            return reason;
        }
        if (target.indexOf(":") != -1) {
            return "Processing instruction targets cannot contain colons";
        }
        if (target.equalsIgnoreCase("xml")) {
            return "Processing instructions cannot have a target of \"xml\" in any combination of case. (Note that the \"<?xml ... ?>\" declaration at the beginning of a document is not a processing instruction and should not be added as one; it is written automatically during output, e.g. by XMLOutputter.)";
        }
        return null;
    }
    
    public static String checkProcessingInstructionData(final String data) {
        final String reason = checkCharacterData(data);
        if (reason == null && data.indexOf("?>") >= 0) {
            return "Processing instructions cannot contain the string \"?>\"";
        }
        return reason;
    }
    
    public static String checkCommentData(final String data) {
        String reason = null;
        if ((reason = checkCharacterData(data)) != null) {
            return reason;
        }
        if (data.indexOf("--") != -1) {
            return "Comments cannot contain double hyphens (--)";
        }
        if (data.endsWith("-")) {
            return "Comment data cannot end with a hyphen.";
        }
        return null;
    }
    
    public static int decodeSurrogatePair(final char high, final char low) {
        return 65536 + (high - '\ud800') * '\u0400' + (low - '\udc00');
    }
    
    public static boolean isXMLPublicIDCharacter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= '?' && c <= 'Z') || (c >= '\'' && c <= ';') || c == ' ' || c == '!' || c == '=' || c == '#' || c == '$' || c == '_' || c == '%' || c == '\n' || c == '\r' || c == '\t';
    }
    
    public static String checkPublicID(final String publicID) {
        String reason = null;
        if (publicID == null) {
            return null;
        }
        for (int i = 0; i < publicID.length(); ++i) {
            final char c = publicID.charAt(i);
            if (!isXMLPublicIDCharacter(c)) {
                reason = c + " is not a legal character in public IDs";
                break;
            }
        }
        return reason;
    }
    
    public static String checkSystemLiteral(final String systemLiteral) {
        String reason = null;
        if (systemLiteral == null) {
            return null;
        }
        if (systemLiteral.indexOf(39) != -1 && systemLiteral.indexOf(34) != -1) {
            reason = "System literals cannot simultaneously contain both single and double quotes.";
        }
        else {
            reason = checkCharacterData(systemLiteral);
        }
        return reason;
    }
    
    public static String checkXMLName(final String name) {
        if (name == null || name.length() == 0 || name.trim().equals("")) {
            return "XML names cannot be null or empty";
        }
        final char first = name.charAt(0);
        if (!isXMLNameStartCharacter(first)) {
            return "XML names cannot begin with the character \"" + first + "\"";
        }
        for (int i = 1, len = name.length(); i < len; ++i) {
            final char c = name.charAt(i);
            if (!isXMLNameCharacter(c)) {
                return "XML names cannot contain the character \"" + c + "\"";
            }
        }
        return null;
    }
    
    public static String checkURI(final String uri) {
        if (uri == null || uri.equals("")) {
            return null;
        }
        for (int i = 0; i < uri.length(); ++i) {
            final char test = uri.charAt(i);
            if (!isURICharacter(test)) {
                String msgNumber = "0x" + Integer.toHexString(test);
                if (test <= '\t') {
                    msgNumber = "0x0" + Integer.toHexString(test);
                }
                return "URIs cannot contain " + msgNumber;
            }
            if (test == '%') {
                try {
                    final char firstDigit = uri.charAt(i + 1);
                    final char secondDigit = uri.charAt(i + 2);
                    if (!isHexDigit(firstDigit) || !isHexDigit(secondDigit)) {
                        return "Percent signs in URIs must be followed by exactly two hexadecimal digits.";
                    }
                }
                catch (StringIndexOutOfBoundsException e) {
                    return "Percent signs in URIs must be followed by exactly two hexadecimal digits.";
                }
            }
        }
        return null;
    }
    
    public static boolean isHexDigit(final char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
    
    public static boolean isHighSurrogate(final char ch) {
        return ch >= '\ud800' && ch <= '\udbff';
    }
    
    public static boolean isLowSurrogate(final char ch) {
        return ch >= '\udc00' && ch <= '\udfff';
    }
    
    public static boolean isURICharacter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '/' || c == '-' || c == '.' || c == '?' || c == ':' || c == '@' || c == '&' || c == '=' || c == '+' || c == '$' || c == ',' || c == '%' || c == '_' || c == '!' || c == '~' || c == '*' || c == '\'' || c == '(' || c == ')';
    }
    
    public static boolean isXMLCharacter(final int c) {
        return c == 10 || c == 13 || c == 9 || (c >= 32 && (c <= 55295 || (c >= 57344 && (c <= 65533 || (c >= 65536 && c <= 1114111)))));
    }
    
    public static boolean isXMLNameCharacter(final char c) {
        return isXMLLetter(c) || isXMLDigit(c) || c == '.' || c == '-' || c == '_' || c == ':' || isXMLCombiningChar(c) || isXMLExtender(c);
    }
    
    public static boolean isXMLNameStartCharacter(final char c) {
        return isXMLLetter(c) || c == '_' || c == ':';
    }
    
    public static boolean isXMLLetterOrDigit(final char c) {
        return isXMLLetter(c) || isXMLDigit(c);
    }
    
    public static boolean isXMLLetter(final char c) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_0         /* c */
        //     1: bipush          65
        //     3: if_icmpge       8
        //     6: iconst_0       
        //     7: ireturn        
        //     8: iload_0         /* c */
        //     9: bipush          90
        //    11: if_icmpgt       16
        //    14: iconst_1       
        //    15: ireturn        
        //    16: iload_0         /* c */
        //    17: bipush          97
        //    19: if_icmpge       24
        //    22: iconst_0       
        //    23: ireturn        
        //    24: iload_0         /* c */
        //    25: bipush          122
        //    27: if_icmpgt       32
        //    30: iconst_1       
        //    31: ireturn        
        //    32: iload_0         /* c */
        //    33: sipush          192
        //    36: if_icmpge       41
        //    39: iconst_0       
        //    40: ireturn        
        //    41: iload_0         /* c */
        //    42: sipush          214
        //    45: if_icmpgt       50
        //    48: iconst_1       
        //    49: ireturn        
        //    50: iload_0         /* c */
        //    51: sipush          216
        //    54: if_icmpge       59
        //    57: iconst_0       
        //    58: ireturn        
        //    59: iload_0         /* c */
        //    60: sipush          246
        //    63: if_icmpgt       68
        //    66: iconst_1       
        //    67: ireturn        
        //    68: iload_0         /* c */
        //    69: sipush          248
        //    72: if_icmpge       77
        //    75: iconst_0       
        //    76: ireturn        
        //    77: iload_0         /* c */
        //    78: sipush          255
        //    81: if_icmpgt       86
        //    84: iconst_1       
        //    85: ireturn        
        //    86: iload_0         /* c */
        //    87: sipush          256
        //    90: if_icmpge       95
        //    93: iconst_0       
        //    94: ireturn        
        //    95: iload_0         /* c */
        //    96: sipush          305
        //    99: if_icmpgt       104
        //   102: iconst_1       
        //   103: ireturn        
        //   104: iload_0         /* c */
        //   105: sipush          308
        //   108: if_icmpge       113
        //   111: iconst_0       
        //   112: ireturn        
        //   113: iload_0         /* c */
        //   114: sipush          318
        //   117: if_icmpgt       122
        //   120: iconst_1       
        //   121: ireturn        
        //   122: iload_0         /* c */
        //   123: sipush          321
        //   126: if_icmpge       131
        //   129: iconst_0       
        //   130: ireturn        
        //   131: iload_0         /* c */
        //   132: sipush          328
        //   135: if_icmpgt       140
        //   138: iconst_1       
        //   139: ireturn        
        //   140: iload_0         /* c */
        //   141: sipush          330
        //   144: if_icmpge       149
        //   147: iconst_0       
        //   148: ireturn        
        //   149: iload_0         /* c */
        //   150: sipush          382
        //   153: if_icmpgt       158
        //   156: iconst_1       
        //   157: ireturn        
        //   158: iload_0         /* c */
        //   159: sipush          384
        //   162: if_icmpge       167
        //   165: iconst_0       
        //   166: ireturn        
        //   167: iload_0         /* c */
        //   168: sipush          451
        //   171: if_icmpgt       176
        //   174: iconst_1       
        //   175: ireturn        
        //   176: iload_0         /* c */
        //   177: sipush          461
        //   180: if_icmpge       185
        //   183: iconst_0       
        //   184: ireturn        
        //   185: iload_0         /* c */
        //   186: sipush          496
        //   189: if_icmpgt       194
        //   192: iconst_1       
        //   193: ireturn        
        //   194: iload_0         /* c */
        //   195: sipush          500
        //   198: if_icmpge       203
        //   201: iconst_0       
        //   202: ireturn        
        //   203: iload_0         /* c */
        //   204: sipush          501
        //   207: if_icmpgt       212
        //   210: iconst_1       
        //   211: ireturn        
        //   212: iload_0         /* c */
        //   213: sipush          506
        //   216: if_icmpge       221
        //   219: iconst_0       
        //   220: ireturn        
        //   221: iload_0         /* c */
        //   222: sipush          535
        //   225: if_icmpgt       230
        //   228: iconst_1       
        //   229: ireturn        
        //   230: iload_0         /* c */
        //   231: sipush          592
        //   234: if_icmpge       239
        //   237: iconst_0       
        //   238: ireturn        
        //   239: iload_0         /* c */
        //   240: sipush          680
        //   243: if_icmpgt       248
        //   246: iconst_1       
        //   247: ireturn        
        //   248: iload_0         /* c */
        //   249: sipush          699
        //   252: if_icmpge       257
        //   255: iconst_0       
        //   256: ireturn        
        //   257: iload_0         /* c */
        //   258: sipush          705
        //   261: if_icmpgt       266
        //   264: iconst_1       
        //   265: ireturn        
        //   266: iload_0         /* c */
        //   267: sipush          902
        //   270: if_icmpne       275
        //   273: iconst_1       
        //   274: ireturn        
        //   275: iload_0         /* c */
        //   276: sipush          904
        //   279: if_icmpge       284
        //   282: iconst_0       
        //   283: ireturn        
        //   284: iload_0         /* c */
        //   285: sipush          906
        //   288: if_icmpgt       293
        //   291: iconst_1       
        //   292: ireturn        
        //   293: iload_0         /* c */
        //   294: sipush          908
        //   297: if_icmpne       302
        //   300: iconst_1       
        //   301: ireturn        
        //   302: iload_0         /* c */
        //   303: sipush          910
        //   306: if_icmpge       311
        //   309: iconst_0       
        //   310: ireturn        
        //   311: iload_0         /* c */
        //   312: sipush          929
        //   315: if_icmpgt       320
        //   318: iconst_1       
        //   319: ireturn        
        //   320: iload_0         /* c */
        //   321: sipush          931
        //   324: if_icmpge       329
        //   327: iconst_0       
        //   328: ireturn        
        //   329: iload_0         /* c */
        //   330: sipush          974
        //   333: if_icmpgt       338
        //   336: iconst_1       
        //   337: ireturn        
        //   338: iload_0         /* c */
        //   339: sipush          976
        //   342: if_icmpge       347
        //   345: iconst_0       
        //   346: ireturn        
        //   347: iload_0         /* c */
        //   348: sipush          982
        //   351: if_icmpgt       356
        //   354: iconst_1       
        //   355: ireturn        
        //   356: iload_0         /* c */
        //   357: sipush          986
        //   360: if_icmpne       365
        //   363: iconst_1       
        //   364: ireturn        
        //   365: iload_0         /* c */
        //   366: sipush          988
        //   369: if_icmpne       374
        //   372: iconst_1       
        //   373: ireturn        
        //   374: iload_0         /* c */
        //   375: sipush          990
        //   378: if_icmpne       383
        //   381: iconst_1       
        //   382: ireturn        
        //   383: iload_0         /* c */
        //   384: sipush          992
        //   387: if_icmpne       392
        //   390: iconst_1       
        //   391: ireturn        
        //   392: iload_0         /* c */
        //   393: sipush          994
        //   396: if_icmpge       401
        //   399: iconst_0       
        //   400: ireturn        
        //   401: iload_0         /* c */
        //   402: sipush          1011
        //   405: if_icmpgt       410
        //   408: iconst_1       
        //   409: ireturn        
        //   410: iload_0         /* c */
        //   411: sipush          1025
        //   414: if_icmpge       419
        //   417: iconst_0       
        //   418: ireturn        
        //   419: iload_0         /* c */
        //   420: sipush          1036
        //   423: if_icmpgt       428
        //   426: iconst_1       
        //   427: ireturn        
        //   428: iload_0         /* c */
        //   429: sipush          1038
        //   432: if_icmpge       437
        //   435: iconst_0       
        //   436: ireturn        
        //   437: iload_0         /* c */
        //   438: sipush          1103
        //   441: if_icmpgt       446
        //   444: iconst_1       
        //   445: ireturn        
        //   446: iload_0         /* c */
        //   447: sipush          1105
        //   450: if_icmpge       455
        //   453: iconst_0       
        //   454: ireturn        
        //   455: iload_0         /* c */
        //   456: sipush          1116
        //   459: if_icmpgt       464
        //   462: iconst_1       
        //   463: ireturn        
        //   464: iload_0         /* c */
        //   465: sipush          1118
        //   468: if_icmpge       473
        //   471: iconst_0       
        //   472: ireturn        
        //   473: iload_0         /* c */
        //   474: sipush          1153
        //   477: if_icmpgt       482
        //   480: iconst_1       
        //   481: ireturn        
        //   482: iload_0         /* c */
        //   483: sipush          1168
        //   486: if_icmpge       491
        //   489: iconst_0       
        //   490: ireturn        
        //   491: iload_0         /* c */
        //   492: sipush          1220
        //   495: if_icmpgt       500
        //   498: iconst_1       
        //   499: ireturn        
        //   500: iload_0         /* c */
        //   501: sipush          1223
        //   504: if_icmpge       509
        //   507: iconst_0       
        //   508: ireturn        
        //   509: iload_0         /* c */
        //   510: sipush          1224
        //   513: if_icmpgt       518
        //   516: iconst_1       
        //   517: ireturn        
        //   518: iload_0         /* c */
        //   519: sipush          1227
        //   522: if_icmpge       527
        //   525: iconst_0       
        //   526: ireturn        
        //   527: iload_0         /* c */
        //   528: sipush          1228
        //   531: if_icmpgt       536
        //   534: iconst_1       
        //   535: ireturn        
        //   536: iload_0         /* c */
        //   537: sipush          1232
        //   540: if_icmpge       545
        //   543: iconst_0       
        //   544: ireturn        
        //   545: iload_0         /* c */
        //   546: sipush          1259
        //   549: if_icmpgt       554
        //   552: iconst_1       
        //   553: ireturn        
        //   554: iload_0         /* c */
        //   555: sipush          1262
        //   558: if_icmpge       563
        //   561: iconst_0       
        //   562: ireturn        
        //   563: iload_0         /* c */
        //   564: sipush          1269
        //   567: if_icmpgt       572
        //   570: iconst_1       
        //   571: ireturn        
        //   572: iload_0         /* c */
        //   573: sipush          1272
        //   576: if_icmpge       581
        //   579: iconst_0       
        //   580: ireturn        
        //   581: iload_0         /* c */
        //   582: sipush          1273
        //   585: if_icmpgt       590
        //   588: iconst_1       
        //   589: ireturn        
        //   590: iload_0         /* c */
        //   591: sipush          1329
        //   594: if_icmpge       599
        //   597: iconst_0       
        //   598: ireturn        
        //   599: iload_0         /* c */
        //   600: sipush          1366
        //   603: if_icmpgt       608
        //   606: iconst_1       
        //   607: ireturn        
        //   608: iload_0         /* c */
        //   609: sipush          1369
        //   612: if_icmpne       617
        //   615: iconst_1       
        //   616: ireturn        
        //   617: iload_0         /* c */
        //   618: sipush          1377
        //   621: if_icmpge       626
        //   624: iconst_0       
        //   625: ireturn        
        //   626: iload_0         /* c */
        //   627: sipush          1414
        //   630: if_icmpgt       635
        //   633: iconst_1       
        //   634: ireturn        
        //   635: iload_0         /* c */
        //   636: sipush          1488
        //   639: if_icmpge       644
        //   642: iconst_0       
        //   643: ireturn        
        //   644: iload_0         /* c */
        //   645: sipush          1514
        //   648: if_icmpgt       653
        //   651: iconst_1       
        //   652: ireturn        
        //   653: iload_0         /* c */
        //   654: sipush          1520
        //   657: if_icmpge       662
        //   660: iconst_0       
        //   661: ireturn        
        //   662: iload_0         /* c */
        //   663: sipush          1522
        //   666: if_icmpgt       671
        //   669: iconst_1       
        //   670: ireturn        
        //   671: iload_0         /* c */
        //   672: sipush          1569
        //   675: if_icmpge       680
        //   678: iconst_0       
        //   679: ireturn        
        //   680: iload_0         /* c */
        //   681: sipush          1594
        //   684: if_icmpgt       689
        //   687: iconst_1       
        //   688: ireturn        
        //   689: iload_0         /* c */
        //   690: sipush          1601
        //   693: if_icmpge       698
        //   696: iconst_0       
        //   697: ireturn        
        //   698: iload_0         /* c */
        //   699: sipush          1610
        //   702: if_icmpgt       707
        //   705: iconst_1       
        //   706: ireturn        
        //   707: iload_0         /* c */
        //   708: sipush          1649
        //   711: if_icmpge       716
        //   714: iconst_0       
        //   715: ireturn        
        //   716: iload_0         /* c */
        //   717: sipush          1719
        //   720: if_icmpgt       725
        //   723: iconst_1       
        //   724: ireturn        
        //   725: iload_0         /* c */
        //   726: sipush          1722
        //   729: if_icmpge       734
        //   732: iconst_0       
        //   733: ireturn        
        //   734: iload_0         /* c */
        //   735: sipush          1726
        //   738: if_icmpgt       743
        //   741: iconst_1       
        //   742: ireturn        
        //   743: iload_0         /* c */
        //   744: sipush          1728
        //   747: if_icmpge       752
        //   750: iconst_0       
        //   751: ireturn        
        //   752: iload_0         /* c */
        //   753: sipush          1742
        //   756: if_icmpgt       761
        //   759: iconst_1       
        //   760: ireturn        
        //   761: iload_0         /* c */
        //   762: sipush          1744
        //   765: if_icmpge       770
        //   768: iconst_0       
        //   769: ireturn        
        //   770: iload_0         /* c */
        //   771: sipush          1747
        //   774: if_icmpgt       779
        //   777: iconst_1       
        //   778: ireturn        
        //   779: iload_0         /* c */
        //   780: sipush          1749
        //   783: if_icmpne       788
        //   786: iconst_1       
        //   787: ireturn        
        //   788: iload_0         /* c */
        //   789: sipush          1765
        //   792: if_icmpge       797
        //   795: iconst_0       
        //   796: ireturn        
        //   797: iload_0         /* c */
        //   798: sipush          1766
        //   801: if_icmpgt       806
        //   804: iconst_1       
        //   805: ireturn        
        //   806: iload_0         /* c */
        //   807: sipush          2309
        //   810: if_icmpge       815
        //   813: iconst_0       
        //   814: ireturn        
        //   815: iload_0         /* c */
        //   816: sipush          2361
        //   819: if_icmpgt       824
        //   822: iconst_1       
        //   823: ireturn        
        //   824: iload_0         /* c */
        //   825: sipush          2365
        //   828: if_icmpne       833
        //   831: iconst_1       
        //   832: ireturn        
        //   833: iload_0         /* c */
        //   834: sipush          2392
        //   837: if_icmpge       842
        //   840: iconst_0       
        //   841: ireturn        
        //   842: iload_0         /* c */
        //   843: sipush          2401
        //   846: if_icmpgt       851
        //   849: iconst_1       
        //   850: ireturn        
        //   851: iload_0         /* c */
        //   852: sipush          2437
        //   855: if_icmpge       860
        //   858: iconst_0       
        //   859: ireturn        
        //   860: iload_0         /* c */
        //   861: sipush          2444
        //   864: if_icmpgt       869
        //   867: iconst_1       
        //   868: ireturn        
        //   869: iload_0         /* c */
        //   870: sipush          2447
        //   873: if_icmpge       878
        //   876: iconst_0       
        //   877: ireturn        
        //   878: iload_0         /* c */
        //   879: sipush          2448
        //   882: if_icmpgt       887
        //   885: iconst_1       
        //   886: ireturn        
        //   887: iload_0         /* c */
        //   888: sipush          2451
        //   891: if_icmpge       896
        //   894: iconst_0       
        //   895: ireturn        
        //   896: iload_0         /* c */
        //   897: sipush          2472
        //   900: if_icmpgt       905
        //   903: iconst_1       
        //   904: ireturn        
        //   905: iload_0         /* c */
        //   906: sipush          2474
        //   909: if_icmpge       914
        //   912: iconst_0       
        //   913: ireturn        
        //   914: iload_0         /* c */
        //   915: sipush          2480
        //   918: if_icmpgt       923
        //   921: iconst_1       
        //   922: ireturn        
        //   923: iload_0         /* c */
        //   924: sipush          2482
        //   927: if_icmpne       932
        //   930: iconst_1       
        //   931: ireturn        
        //   932: iload_0         /* c */
        //   933: sipush          2486
        //   936: if_icmpge       941
        //   939: iconst_0       
        //   940: ireturn        
        //   941: iload_0         /* c */
        //   942: sipush          2489
        //   945: if_icmpgt       950
        //   948: iconst_1       
        //   949: ireturn        
        //   950: iload_0         /* c */
        //   951: sipush          2524
        //   954: if_icmpge       959
        //   957: iconst_0       
        //   958: ireturn        
        //   959: iload_0         /* c */
        //   960: sipush          2525
        //   963: if_icmpgt       968
        //   966: iconst_1       
        //   967: ireturn        
        //   968: iload_0         /* c */
        //   969: sipush          2527
        //   972: if_icmpge       977
        //   975: iconst_0       
        //   976: ireturn        
        //   977: iload_0         /* c */
        //   978: sipush          2529
        //   981: if_icmpgt       986
        //   984: iconst_1       
        //   985: ireturn        
        //   986: iload_0         /* c */
        //   987: sipush          2544
        //   990: if_icmpge       995
        //   993: iconst_0       
        //   994: ireturn        
        //   995: iload_0         /* c */
        //   996: sipush          2545
        //   999: if_icmpgt       1004
        //  1002: iconst_1       
        //  1003: ireturn        
        //  1004: iload_0         /* c */
        //  1005: sipush          2565
        //  1008: if_icmpge       1013
        //  1011: iconst_0       
        //  1012: ireturn        
        //  1013: iload_0         /* c */
        //  1014: sipush          2570
        //  1017: if_icmpgt       1022
        //  1020: iconst_1       
        //  1021: ireturn        
        //  1022: iload_0         /* c */
        //  1023: sipush          2575
        //  1026: if_icmpge       1031
        //  1029: iconst_0       
        //  1030: ireturn        
        //  1031: iload_0         /* c */
        //  1032: sipush          2576
        //  1035: if_icmpgt       1040
        //  1038: iconst_1       
        //  1039: ireturn        
        //  1040: iload_0         /* c */
        //  1041: sipush          2579
        //  1044: if_icmpge       1049
        //  1047: iconst_0       
        //  1048: ireturn        
        //  1049: iload_0         /* c */
        //  1050: sipush          2600
        //  1053: if_icmpgt       1058
        //  1056: iconst_1       
        //  1057: ireturn        
        //  1058: iload_0         /* c */
        //  1059: sipush          2602
        //  1062: if_icmpge       1067
        //  1065: iconst_0       
        //  1066: ireturn        
        //  1067: iload_0         /* c */
        //  1068: sipush          2608
        //  1071: if_icmpgt       1076
        //  1074: iconst_1       
        //  1075: ireturn        
        //  1076: iload_0         /* c */
        //  1077: sipush          2610
        //  1080: if_icmpge       1085
        //  1083: iconst_0       
        //  1084: ireturn        
        //  1085: iload_0         /* c */
        //  1086: sipush          2611
        //  1089: if_icmpgt       1094
        //  1092: iconst_1       
        //  1093: ireturn        
        //  1094: iload_0         /* c */
        //  1095: sipush          2613
        //  1098: if_icmpge       1103
        //  1101: iconst_0       
        //  1102: ireturn        
        //  1103: iload_0         /* c */
        //  1104: sipush          2614
        //  1107: if_icmpgt       1112
        //  1110: iconst_1       
        //  1111: ireturn        
        //  1112: iload_0         /* c */
        //  1113: sipush          2616
        //  1116: if_icmpge       1121
        //  1119: iconst_0       
        //  1120: ireturn        
        //  1121: iload_0         /* c */
        //  1122: sipush          2617
        //  1125: if_icmpgt       1130
        //  1128: iconst_1       
        //  1129: ireturn        
        //  1130: iload_0         /* c */
        //  1131: sipush          2649
        //  1134: if_icmpge       1139
        //  1137: iconst_0       
        //  1138: ireturn        
        //  1139: iload_0         /* c */
        //  1140: sipush          2652
        //  1143: if_icmpgt       1148
        //  1146: iconst_1       
        //  1147: ireturn        
        //  1148: iload_0         /* c */
        //  1149: sipush          2654
        //  1152: if_icmpne       1157
        //  1155: iconst_1       
        //  1156: ireturn        
        //  1157: iload_0         /* c */
        //  1158: sipush          2674
        //  1161: if_icmpge       1166
        //  1164: iconst_0       
        //  1165: ireturn        
        //  1166: iload_0         /* c */
        //  1167: sipush          2676
        //  1170: if_icmpgt       1175
        //  1173: iconst_1       
        //  1174: ireturn        
        //  1175: iload_0         /* c */
        //  1176: sipush          2693
        //  1179: if_icmpge       1184
        //  1182: iconst_0       
        //  1183: ireturn        
        //  1184: iload_0         /* c */
        //  1185: sipush          2699
        //  1188: if_icmpgt       1193
        //  1191: iconst_1       
        //  1192: ireturn        
        //  1193: iload_0         /* c */
        //  1194: sipush          2701
        //  1197: if_icmpne       1202
        //  1200: iconst_1       
        //  1201: ireturn        
        //  1202: iload_0         /* c */
        //  1203: sipush          2703
        //  1206: if_icmpge       1211
        //  1209: iconst_0       
        //  1210: ireturn        
        //  1211: iload_0         /* c */
        //  1212: sipush          2705
        //  1215: if_icmpgt       1220
        //  1218: iconst_1       
        //  1219: ireturn        
        //  1220: iload_0         /* c */
        //  1221: sipush          2707
        //  1224: if_icmpge       1229
        //  1227: iconst_0       
        //  1228: ireturn        
        //  1229: iload_0         /* c */
        //  1230: sipush          2728
        //  1233: if_icmpgt       1238
        //  1236: iconst_1       
        //  1237: ireturn        
        //  1238: iload_0         /* c */
        //  1239: sipush          2730
        //  1242: if_icmpge       1247
        //  1245: iconst_0       
        //  1246: ireturn        
        //  1247: iload_0         /* c */
        //  1248: sipush          2736
        //  1251: if_icmpgt       1256
        //  1254: iconst_1       
        //  1255: ireturn        
        //  1256: iload_0         /* c */
        //  1257: sipush          2738
        //  1260: if_icmpge       1265
        //  1263: iconst_0       
        //  1264: ireturn        
        //  1265: iload_0         /* c */
        //  1266: sipush          2739
        //  1269: if_icmpgt       1274
        //  1272: iconst_1       
        //  1273: ireturn        
        //  1274: iload_0         /* c */
        //  1275: sipush          2741
        //  1278: if_icmpge       1283
        //  1281: iconst_0       
        //  1282: ireturn        
        //  1283: iload_0         /* c */
        //  1284: sipush          2745
        //  1287: if_icmpgt       1292
        //  1290: iconst_1       
        //  1291: ireturn        
        //  1292: iload_0         /* c */
        //  1293: sipush          2749
        //  1296: if_icmpne       1301
        //  1299: iconst_1       
        //  1300: ireturn        
        //  1301: iload_0         /* c */
        //  1302: sipush          2784
        //  1305: if_icmpne       1310
        //  1308: iconst_1       
        //  1309: ireturn        
        //  1310: iload_0         /* c */
        //  1311: sipush          2821
        //  1314: if_icmpge       1319
        //  1317: iconst_0       
        //  1318: ireturn        
        //  1319: iload_0         /* c */
        //  1320: sipush          2828
        //  1323: if_icmpgt       1328
        //  1326: iconst_1       
        //  1327: ireturn        
        //  1328: iload_0         /* c */
        //  1329: sipush          2831
        //  1332: if_icmpge       1337
        //  1335: iconst_0       
        //  1336: ireturn        
        //  1337: iload_0         /* c */
        //  1338: sipush          2832
        //  1341: if_icmpgt       1346
        //  1344: iconst_1       
        //  1345: ireturn        
        //  1346: iload_0         /* c */
        //  1347: sipush          2835
        //  1350: if_icmpge       1355
        //  1353: iconst_0       
        //  1354: ireturn        
        //  1355: iload_0         /* c */
        //  1356: sipush          2856
        //  1359: if_icmpgt       1364
        //  1362: iconst_1       
        //  1363: ireturn        
        //  1364: iload_0         /* c */
        //  1365: sipush          2858
        //  1368: if_icmpge       1373
        //  1371: iconst_0       
        //  1372: ireturn        
        //  1373: iload_0         /* c */
        //  1374: sipush          2864
        //  1377: if_icmpgt       1382
        //  1380: iconst_1       
        //  1381: ireturn        
        //  1382: iload_0         /* c */
        //  1383: sipush          2866
        //  1386: if_icmpge       1391
        //  1389: iconst_0       
        //  1390: ireturn        
        //  1391: iload_0         /* c */
        //  1392: sipush          2867
        //  1395: if_icmpgt       1400
        //  1398: iconst_1       
        //  1399: ireturn        
        //  1400: iload_0         /* c */
        //  1401: sipush          2870
        //  1404: if_icmpge       1409
        //  1407: iconst_0       
        //  1408: ireturn        
        //  1409: iload_0         /* c */
        //  1410: sipush          2873
        //  1413: if_icmpgt       1418
        //  1416: iconst_1       
        //  1417: ireturn        
        //  1418: iload_0         /* c */
        //  1419: sipush          2877
        //  1422: if_icmpne       1427
        //  1425: iconst_1       
        //  1426: ireturn        
        //  1427: iload_0         /* c */
        //  1428: sipush          2908
        //  1431: if_icmpge       1436
        //  1434: iconst_0       
        //  1435: ireturn        
        //  1436: iload_0         /* c */
        //  1437: sipush          2909
        //  1440: if_icmpgt       1445
        //  1443: iconst_1       
        //  1444: ireturn        
        //  1445: iload_0         /* c */
        //  1446: sipush          2911
        //  1449: if_icmpge       1454
        //  1452: iconst_0       
        //  1453: ireturn        
        //  1454: iload_0         /* c */
        //  1455: sipush          2913
        //  1458: if_icmpgt       1463
        //  1461: iconst_1       
        //  1462: ireturn        
        //  1463: iload_0         /* c */
        //  1464: sipush          2949
        //  1467: if_icmpge       1472
        //  1470: iconst_0       
        //  1471: ireturn        
        //  1472: iload_0         /* c */
        //  1473: sipush          2954
        //  1476: if_icmpgt       1481
        //  1479: iconst_1       
        //  1480: ireturn        
        //  1481: iload_0         /* c */
        //  1482: sipush          2958
        //  1485: if_icmpge       1490
        //  1488: iconst_0       
        //  1489: ireturn        
        //  1490: iload_0         /* c */
        //  1491: sipush          2960
        //  1494: if_icmpgt       1499
        //  1497: iconst_1       
        //  1498: ireturn        
        //  1499: iload_0         /* c */
        //  1500: sipush          2962
        //  1503: if_icmpge       1508
        //  1506: iconst_0       
        //  1507: ireturn        
        //  1508: iload_0         /* c */
        //  1509: sipush          2965
        //  1512: if_icmpgt       1517
        //  1515: iconst_1       
        //  1516: ireturn        
        //  1517: iload_0         /* c */
        //  1518: sipush          2969
        //  1521: if_icmpge       1526
        //  1524: iconst_0       
        //  1525: ireturn        
        //  1526: iload_0         /* c */
        //  1527: sipush          2970
        //  1530: if_icmpgt       1535
        //  1533: iconst_1       
        //  1534: ireturn        
        //  1535: iload_0         /* c */
        //  1536: sipush          2972
        //  1539: if_icmpne       1544
        //  1542: iconst_1       
        //  1543: ireturn        
        //  1544: iload_0         /* c */
        //  1545: sipush          2974
        //  1548: if_icmpge       1553
        //  1551: iconst_0       
        //  1552: ireturn        
        //  1553: iload_0         /* c */
        //  1554: sipush          2975
        //  1557: if_icmpgt       1562
        //  1560: iconst_1       
        //  1561: ireturn        
        //  1562: iload_0         /* c */
        //  1563: sipush          2979
        //  1566: if_icmpge       1571
        //  1569: iconst_0       
        //  1570: ireturn        
        //  1571: iload_0         /* c */
        //  1572: sipush          2980
        //  1575: if_icmpgt       1580
        //  1578: iconst_1       
        //  1579: ireturn        
        //  1580: iload_0         /* c */
        //  1581: sipush          2984
        //  1584: if_icmpge       1589
        //  1587: iconst_0       
        //  1588: ireturn        
        //  1589: iload_0         /* c */
        //  1590: sipush          2986
        //  1593: if_icmpgt       1598
        //  1596: iconst_1       
        //  1597: ireturn        
        //  1598: iload_0         /* c */
        //  1599: sipush          2990
        //  1602: if_icmpge       1607
        //  1605: iconst_0       
        //  1606: ireturn        
        //  1607: iload_0         /* c */
        //  1608: sipush          2997
        //  1611: if_icmpgt       1616
        //  1614: iconst_1       
        //  1615: ireturn        
        //  1616: iload_0         /* c */
        //  1617: sipush          2999
        //  1620: if_icmpge       1625
        //  1623: iconst_0       
        //  1624: ireturn        
        //  1625: iload_0         /* c */
        //  1626: sipush          3001
        //  1629: if_icmpgt       1634
        //  1632: iconst_1       
        //  1633: ireturn        
        //  1634: iload_0         /* c */
        //  1635: sipush          3077
        //  1638: if_icmpge       1643
        //  1641: iconst_0       
        //  1642: ireturn        
        //  1643: iload_0         /* c */
        //  1644: sipush          3084
        //  1647: if_icmpgt       1652
        //  1650: iconst_1       
        //  1651: ireturn        
        //  1652: iload_0         /* c */
        //  1653: sipush          3086
        //  1656: if_icmpge       1661
        //  1659: iconst_0       
        //  1660: ireturn        
        //  1661: iload_0         /* c */
        //  1662: sipush          3088
        //  1665: if_icmpgt       1670
        //  1668: iconst_1       
        //  1669: ireturn        
        //  1670: iload_0         /* c */
        //  1671: sipush          3090
        //  1674: if_icmpge       1679
        //  1677: iconst_0       
        //  1678: ireturn        
        //  1679: iload_0         /* c */
        //  1680: sipush          3112
        //  1683: if_icmpgt       1688
        //  1686: iconst_1       
        //  1687: ireturn        
        //  1688: iload_0         /* c */
        //  1689: sipush          3114
        //  1692: if_icmpge       1697
        //  1695: iconst_0       
        //  1696: ireturn        
        //  1697: iload_0         /* c */
        //  1698: sipush          3123
        //  1701: if_icmpgt       1706
        //  1704: iconst_1       
        //  1705: ireturn        
        //  1706: iload_0         /* c */
        //  1707: sipush          3125
        //  1710: if_icmpge       1715
        //  1713: iconst_0       
        //  1714: ireturn        
        //  1715: iload_0         /* c */
        //  1716: sipush          3129
        //  1719: if_icmpgt       1724
        //  1722: iconst_1       
        //  1723: ireturn        
        //  1724: iload_0         /* c */
        //  1725: sipush          3168
        //  1728: if_icmpge       1733
        //  1731: iconst_0       
        //  1732: ireturn        
        //  1733: iload_0         /* c */
        //  1734: sipush          3169
        //  1737: if_icmpgt       1742
        //  1740: iconst_1       
        //  1741: ireturn        
        //  1742: iload_0         /* c */
        //  1743: sipush          3205
        //  1746: if_icmpge       1751
        //  1749: iconst_0       
        //  1750: ireturn        
        //  1751: iload_0         /* c */
        //  1752: sipush          3212
        //  1755: if_icmpgt       1760
        //  1758: iconst_1       
        //  1759: ireturn        
        //  1760: iload_0         /* c */
        //  1761: sipush          3214
        //  1764: if_icmpge       1769
        //  1767: iconst_0       
        //  1768: ireturn        
        //  1769: iload_0         /* c */
        //  1770: sipush          3216
        //  1773: if_icmpgt       1778
        //  1776: iconst_1       
        //  1777: ireturn        
        //  1778: iload_0         /* c */
        //  1779: sipush          3218
        //  1782: if_icmpge       1787
        //  1785: iconst_0       
        //  1786: ireturn        
        //  1787: iload_0         /* c */
        //  1788: sipush          3240
        //  1791: if_icmpgt       1796
        //  1794: iconst_1       
        //  1795: ireturn        
        //  1796: iload_0         /* c */
        //  1797: sipush          3242
        //  1800: if_icmpge       1805
        //  1803: iconst_0       
        //  1804: ireturn        
        //  1805: iload_0         /* c */
        //  1806: sipush          3251
        //  1809: if_icmpgt       1814
        //  1812: iconst_1       
        //  1813: ireturn        
        //  1814: iload_0         /* c */
        //  1815: sipush          3253
        //  1818: if_icmpge       1823
        //  1821: iconst_0       
        //  1822: ireturn        
        //  1823: iload_0         /* c */
        //  1824: sipush          3257
        //  1827: if_icmpgt       1832
        //  1830: iconst_1       
        //  1831: ireturn        
        //  1832: iload_0         /* c */
        //  1833: sipush          3294
        //  1836: if_icmpne       1841
        //  1839: iconst_1       
        //  1840: ireturn        
        //  1841: iload_0         /* c */
        //  1842: sipush          3296
        //  1845: if_icmpge       1850
        //  1848: iconst_0       
        //  1849: ireturn        
        //  1850: iload_0         /* c */
        //  1851: sipush          3297
        //  1854: if_icmpgt       1859
        //  1857: iconst_1       
        //  1858: ireturn        
        //  1859: iload_0         /* c */
        //  1860: sipush          3333
        //  1863: if_icmpge       1868
        //  1866: iconst_0       
        //  1867: ireturn        
        //  1868: iload_0         /* c */
        //  1869: sipush          3340
        //  1872: if_icmpgt       1877
        //  1875: iconst_1       
        //  1876: ireturn        
        //  1877: iload_0         /* c */
        //  1878: sipush          3342
        //  1881: if_icmpge       1886
        //  1884: iconst_0       
        //  1885: ireturn        
        //  1886: iload_0         /* c */
        //  1887: sipush          3344
        //  1890: if_icmpgt       1895
        //  1893: iconst_1       
        //  1894: ireturn        
        //  1895: iload_0         /* c */
        //  1896: sipush          3346
        //  1899: if_icmpge       1904
        //  1902: iconst_0       
        //  1903: ireturn        
        //  1904: iload_0         /* c */
        //  1905: sipush          3368
        //  1908: if_icmpgt       1913
        //  1911: iconst_1       
        //  1912: ireturn        
        //  1913: iload_0         /* c */
        //  1914: sipush          3370
        //  1917: if_icmpge       1922
        //  1920: iconst_0       
        //  1921: ireturn        
        //  1922: iload_0         /* c */
        //  1923: sipush          3385
        //  1926: if_icmpgt       1931
        //  1929: iconst_1       
        //  1930: ireturn        
        //  1931: iload_0         /* c */
        //  1932: sipush          3424
        //  1935: if_icmpge       1940
        //  1938: iconst_0       
        //  1939: ireturn        
        //  1940: iload_0         /* c */
        //  1941: sipush          3425
        //  1944: if_icmpgt       1949
        //  1947: iconst_1       
        //  1948: ireturn        
        //  1949: iload_0         /* c */
        //  1950: sipush          3585
        //  1953: if_icmpge       1958
        //  1956: iconst_0       
        //  1957: ireturn        
        //  1958: iload_0         /* c */
        //  1959: sipush          3630
        //  1962: if_icmpgt       1967
        //  1965: iconst_1       
        //  1966: ireturn        
        //  1967: iload_0         /* c */
        //  1968: sipush          3632
        //  1971: if_icmpne       1976
        //  1974: iconst_1       
        //  1975: ireturn        
        //  1976: iload_0         /* c */
        //  1977: sipush          3634
        //  1980: if_icmpge       1985
        //  1983: iconst_0       
        //  1984: ireturn        
        //  1985: iload_0         /* c */
        //  1986: sipush          3635
        //  1989: if_icmpgt       1994
        //  1992: iconst_1       
        //  1993: ireturn        
        //  1994: iload_0         /* c */
        //  1995: sipush          3648
        //  1998: if_icmpge       2003
        //  2001: iconst_0       
        //  2002: ireturn        
        //  2003: iload_0         /* c */
        //  2004: sipush          3653
        //  2007: if_icmpgt       2012
        //  2010: iconst_1       
        //  2011: ireturn        
        //  2012: iload_0         /* c */
        //  2013: sipush          3713
        //  2016: if_icmpge       2021
        //  2019: iconst_0       
        //  2020: ireturn        
        //  2021: iload_0         /* c */
        //  2022: sipush          3714
        //  2025: if_icmpgt       2030
        //  2028: iconst_1       
        //  2029: ireturn        
        //  2030: iload_0         /* c */
        //  2031: sipush          3716
        //  2034: if_icmpne       2039
        //  2037: iconst_1       
        //  2038: ireturn        
        //  2039: iload_0         /* c */
        //  2040: sipush          3719
        //  2043: if_icmpge       2048
        //  2046: iconst_0       
        //  2047: ireturn        
        //  2048: iload_0         /* c */
        //  2049: sipush          3720
        //  2052: if_icmpgt       2057
        //  2055: iconst_1       
        //  2056: ireturn        
        //  2057: iload_0         /* c */
        //  2058: sipush          3722
        //  2061: if_icmpne       2066
        //  2064: iconst_1       
        //  2065: ireturn        
        //  2066: iload_0         /* c */
        //  2067: sipush          3725
        //  2070: if_icmpne       2075
        //  2073: iconst_1       
        //  2074: ireturn        
        //  2075: iload_0         /* c */
        //  2076: sipush          3732
        //  2079: if_icmpge       2084
        //  2082: iconst_0       
        //  2083: ireturn        
        //  2084: iload_0         /* c */
        //  2085: sipush          3735
        //  2088: if_icmpgt       2093
        //  2091: iconst_1       
        //  2092: ireturn        
        //  2093: iload_0         /* c */
        //  2094: sipush          3737
        //  2097: if_icmpge       2102
        //  2100: iconst_0       
        //  2101: ireturn        
        //  2102: iload_0         /* c */
        //  2103: sipush          3743
        //  2106: if_icmpgt       2111
        //  2109: iconst_1       
        //  2110: ireturn        
        //  2111: iload_0         /* c */
        //  2112: sipush          3745
        //  2115: if_icmpge       2120
        //  2118: iconst_0       
        //  2119: ireturn        
        //  2120: iload_0         /* c */
        //  2121: sipush          3747
        //  2124: if_icmpgt       2129
        //  2127: iconst_1       
        //  2128: ireturn        
        //  2129: iload_0         /* c */
        //  2130: sipush          3749
        //  2133: if_icmpne       2138
        //  2136: iconst_1       
        //  2137: ireturn        
        //  2138: iload_0         /* c */
        //  2139: sipush          3751
        //  2142: if_icmpne       2147
        //  2145: iconst_1       
        //  2146: ireturn        
        //  2147: iload_0         /* c */
        //  2148: sipush          3754
        //  2151: if_icmpge       2156
        //  2154: iconst_0       
        //  2155: ireturn        
        //  2156: iload_0         /* c */
        //  2157: sipush          3755
        //  2160: if_icmpgt       2165
        //  2163: iconst_1       
        //  2164: ireturn        
        //  2165: iload_0         /* c */
        //  2166: sipush          3757
        //  2169: if_icmpge       2174
        //  2172: iconst_0       
        //  2173: ireturn        
        //  2174: iload_0         /* c */
        //  2175: sipush          3758
        //  2178: if_icmpgt       2183
        //  2181: iconst_1       
        //  2182: ireturn        
        //  2183: iload_0         /* c */
        //  2184: sipush          3760
        //  2187: if_icmpne       2192
        //  2190: iconst_1       
        //  2191: ireturn        
        //  2192: iload_0         /* c */
        //  2193: sipush          3762
        //  2196: if_icmpge       2201
        //  2199: iconst_0       
        //  2200: ireturn        
        //  2201: iload_0         /* c */
        //  2202: sipush          3763
        //  2205: if_icmpgt       2210
        //  2208: iconst_1       
        //  2209: ireturn        
        //  2210: iload_0         /* c */
        //  2211: sipush          3773
        //  2214: if_icmpne       2219
        //  2217: iconst_1       
        //  2218: ireturn        
        //  2219: iload_0         /* c */
        //  2220: sipush          3776
        //  2223: if_icmpge       2228
        //  2226: iconst_0       
        //  2227: ireturn        
        //  2228: iload_0         /* c */
        //  2229: sipush          3780
        //  2232: if_icmpgt       2237
        //  2235: iconst_1       
        //  2236: ireturn        
        //  2237: iload_0         /* c */
        //  2238: sipush          3904
        //  2241: if_icmpge       2246
        //  2244: iconst_0       
        //  2245: ireturn        
        //  2246: iload_0         /* c */
        //  2247: sipush          3911
        //  2250: if_icmpgt       2255
        //  2253: iconst_1       
        //  2254: ireturn        
        //  2255: iload_0         /* c */
        //  2256: sipush          3913
        //  2259: if_icmpge       2264
        //  2262: iconst_0       
        //  2263: ireturn        
        //  2264: iload_0         /* c */
        //  2265: sipush          3945
        //  2268: if_icmpgt       2273
        //  2271: iconst_1       
        //  2272: ireturn        
        //  2273: iload_0         /* c */
        //  2274: sipush          4256
        //  2277: if_icmpge       2282
        //  2280: iconst_0       
        //  2281: ireturn        
        //  2282: iload_0         /* c */
        //  2283: sipush          4293
        //  2286: if_icmpgt       2291
        //  2289: iconst_1       
        //  2290: ireturn        
        //  2291: iload_0         /* c */
        //  2292: sipush          4304
        //  2295: if_icmpge       2300
        //  2298: iconst_0       
        //  2299: ireturn        
        //  2300: iload_0         /* c */
        //  2301: sipush          4342
        //  2304: if_icmpgt       2309
        //  2307: iconst_1       
        //  2308: ireturn        
        //  2309: iload_0         /* c */
        //  2310: sipush          4352
        //  2313: if_icmpne       2318
        //  2316: iconst_1       
        //  2317: ireturn        
        //  2318: iload_0         /* c */
        //  2319: sipush          4354
        //  2322: if_icmpge       2327
        //  2325: iconst_0       
        //  2326: ireturn        
        //  2327: iload_0         /* c */
        //  2328: sipush          4355
        //  2331: if_icmpgt       2336
        //  2334: iconst_1       
        //  2335: ireturn        
        //  2336: iload_0         /* c */
        //  2337: sipush          4357
        //  2340: if_icmpge       2345
        //  2343: iconst_0       
        //  2344: ireturn        
        //  2345: iload_0         /* c */
        //  2346: sipush          4359
        //  2349: if_icmpgt       2354
        //  2352: iconst_1       
        //  2353: ireturn        
        //  2354: iload_0         /* c */
        //  2355: sipush          4361
        //  2358: if_icmpne       2363
        //  2361: iconst_1       
        //  2362: ireturn        
        //  2363: iload_0         /* c */
        //  2364: sipush          4363
        //  2367: if_icmpge       2372
        //  2370: iconst_0       
        //  2371: ireturn        
        //  2372: iload_0         /* c */
        //  2373: sipush          4364
        //  2376: if_icmpgt       2381
        //  2379: iconst_1       
        //  2380: ireturn        
        //  2381: iload_0         /* c */
        //  2382: sipush          4366
        //  2385: if_icmpge       2390
        //  2388: iconst_0       
        //  2389: ireturn        
        //  2390: iload_0         /* c */
        //  2391: sipush          4370
        //  2394: if_icmpgt       2399
        //  2397: iconst_1       
        //  2398: ireturn        
        //  2399: iload_0         /* c */
        //  2400: sipush          4412
        //  2403: if_icmpne       2408
        //  2406: iconst_1       
        //  2407: ireturn        
        //  2408: iload_0         /* c */
        //  2409: sipush          4414
        //  2412: if_icmpne       2417
        //  2415: iconst_1       
        //  2416: ireturn        
        //  2417: iload_0         /* c */
        //  2418: sipush          4416
        //  2421: if_icmpne       2426
        //  2424: iconst_1       
        //  2425: ireturn        
        //  2426: iload_0         /* c */
        //  2427: sipush          4428
        //  2430: if_icmpne       2435
        //  2433: iconst_1       
        //  2434: ireturn        
        //  2435: iload_0         /* c */
        //  2436: sipush          4430
        //  2439: if_icmpne       2444
        //  2442: iconst_1       
        //  2443: ireturn        
        //  2444: iload_0         /* c */
        //  2445: sipush          4432
        //  2448: if_icmpne       2453
        //  2451: iconst_1       
        //  2452: ireturn        
        //  2453: iload_0         /* c */
        //  2454: sipush          4436
        //  2457: if_icmpge       2462
        //  2460: iconst_0       
        //  2461: ireturn        
        //  2462: iload_0         /* c */
        //  2463: sipush          4437
        //  2466: if_icmpgt       2471
        //  2469: iconst_1       
        //  2470: ireturn        
        //  2471: iload_0         /* c */
        //  2472: sipush          4441
        //  2475: if_icmpne       2480
        //  2478: iconst_1       
        //  2479: ireturn        
        //  2480: iload_0         /* c */
        //  2481: sipush          4447
        //  2484: if_icmpge       2489
        //  2487: iconst_0       
        //  2488: ireturn        
        //  2489: iload_0         /* c */
        //  2490: sipush          4449
        //  2493: if_icmpgt       2498
        //  2496: iconst_1       
        //  2497: ireturn        
        //  2498: iload_0         /* c */
        //  2499: sipush          4451
        //  2502: if_icmpne       2507
        //  2505: iconst_1       
        //  2506: ireturn        
        //  2507: iload_0         /* c */
        //  2508: sipush          4453
        //  2511: if_icmpne       2516
        //  2514: iconst_1       
        //  2515: ireturn        
        //  2516: iload_0         /* c */
        //  2517: sipush          4455
        //  2520: if_icmpne       2525
        //  2523: iconst_1       
        //  2524: ireturn        
        //  2525: iload_0         /* c */
        //  2526: sipush          4457
        //  2529: if_icmpne       2534
        //  2532: iconst_1       
        //  2533: ireturn        
        //  2534: iload_0         /* c */
        //  2535: sipush          4461
        //  2538: if_icmpge       2543
        //  2541: iconst_0       
        //  2542: ireturn        
        //  2543: iload_0         /* c */
        //  2544: sipush          4462
        //  2547: if_icmpgt       2552
        //  2550: iconst_1       
        //  2551: ireturn        
        //  2552: iload_0         /* c */
        //  2553: sipush          4466
        //  2556: if_icmpge       2561
        //  2559: iconst_0       
        //  2560: ireturn        
        //  2561: iload_0         /* c */
        //  2562: sipush          4467
        //  2565: if_icmpgt       2570
        //  2568: iconst_1       
        //  2569: ireturn        
        //  2570: iload_0         /* c */
        //  2571: sipush          4469
        //  2574: if_icmpne       2579
        //  2577: iconst_1       
        //  2578: ireturn        
        //  2579: iload_0         /* c */
        //  2580: sipush          4510
        //  2583: if_icmpne       2588
        //  2586: iconst_1       
        //  2587: ireturn        
        //  2588: iload_0         /* c */
        //  2589: sipush          4520
        //  2592: if_icmpne       2597
        //  2595: iconst_1       
        //  2596: ireturn        
        //  2597: iload_0         /* c */
        //  2598: sipush          4523
        //  2601: if_icmpne       2606
        //  2604: iconst_1       
        //  2605: ireturn        
        //  2606: iload_0         /* c */
        //  2607: sipush          4526
        //  2610: if_icmpge       2615
        //  2613: iconst_0       
        //  2614: ireturn        
        //  2615: iload_0         /* c */
        //  2616: sipush          4527
        //  2619: if_icmpgt       2624
        //  2622: iconst_1       
        //  2623: ireturn        
        //  2624: iload_0         /* c */
        //  2625: sipush          4535
        //  2628: if_icmpge       2633
        //  2631: iconst_0       
        //  2632: ireturn        
        //  2633: iload_0         /* c */
        //  2634: sipush          4536
        //  2637: if_icmpgt       2642
        //  2640: iconst_1       
        //  2641: ireturn        
        //  2642: iload_0         /* c */
        //  2643: sipush          4538
        //  2646: if_icmpne       2651
        //  2649: iconst_1       
        //  2650: ireturn        
        //  2651: iload_0         /* c */
        //  2652: sipush          4540
        //  2655: if_icmpge       2660
        //  2658: iconst_0       
        //  2659: ireturn        
        //  2660: iload_0         /* c */
        //  2661: sipush          4546
        //  2664: if_icmpgt       2669
        //  2667: iconst_1       
        //  2668: ireturn        
        //  2669: iload_0         /* c */
        //  2670: sipush          4587
        //  2673: if_icmpne       2678
        //  2676: iconst_1       
        //  2677: ireturn        
        //  2678: iload_0         /* c */
        //  2679: sipush          4592
        //  2682: if_icmpne       2687
        //  2685: iconst_1       
        //  2686: ireturn        
        //  2687: iload_0         /* c */
        //  2688: sipush          4601
        //  2691: if_icmpne       2696
        //  2694: iconst_1       
        //  2695: ireturn        
        //  2696: iload_0         /* c */
        //  2697: sipush          7680
        //  2700: if_icmpge       2705
        //  2703: iconst_0       
        //  2704: ireturn        
        //  2705: iload_0         /* c */
        //  2706: sipush          7835
        //  2709: if_icmpgt       2714
        //  2712: iconst_1       
        //  2713: ireturn        
        //  2714: iload_0         /* c */
        //  2715: sipush          7840
        //  2718: if_icmpge       2723
        //  2721: iconst_0       
        //  2722: ireturn        
        //  2723: iload_0         /* c */
        //  2724: sipush          7929
        //  2727: if_icmpgt       2732
        //  2730: iconst_1       
        //  2731: ireturn        
        //  2732: iload_0         /* c */
        //  2733: sipush          7936
        //  2736: if_icmpge       2741
        //  2739: iconst_0       
        //  2740: ireturn        
        //  2741: iload_0         /* c */
        //  2742: sipush          7957
        //  2745: if_icmpgt       2750
        //  2748: iconst_1       
        //  2749: ireturn        
        //  2750: iload_0         /* c */
        //  2751: sipush          7960
        //  2754: if_icmpge       2759
        //  2757: iconst_0       
        //  2758: ireturn        
        //  2759: iload_0         /* c */
        //  2760: sipush          7965
        //  2763: if_icmpgt       2768
        //  2766: iconst_1       
        //  2767: ireturn        
        //  2768: iload_0         /* c */
        //  2769: sipush          7968
        //  2772: if_icmpge       2777
        //  2775: iconst_0       
        //  2776: ireturn        
        //  2777: iload_0         /* c */
        //  2778: sipush          8005
        //  2781: if_icmpgt       2786
        //  2784: iconst_1       
        //  2785: ireturn        
        //  2786: iload_0         /* c */
        //  2787: sipush          8008
        //  2790: if_icmpge       2795
        //  2793: iconst_0       
        //  2794: ireturn        
        //  2795: iload_0         /* c */
        //  2796: sipush          8013
        //  2799: if_icmpgt       2804
        //  2802: iconst_1       
        //  2803: ireturn        
        //  2804: iload_0         /* c */
        //  2805: sipush          8016
        //  2808: if_icmpge       2813
        //  2811: iconst_0       
        //  2812: ireturn        
        //  2813: iload_0         /* c */
        //  2814: sipush          8023
        //  2817: if_icmpgt       2822
        //  2820: iconst_1       
        //  2821: ireturn        
        //  2822: iload_0         /* c */
        //  2823: sipush          8025
        //  2826: if_icmpne       2831
        //  2829: iconst_1       
        //  2830: ireturn        
        //  2831: iload_0         /* c */
        //  2832: sipush          8027
        //  2835: if_icmpne       2840
        //  2838: iconst_1       
        //  2839: ireturn        
        //  2840: iload_0         /* c */
        //  2841: sipush          8029
        //  2844: if_icmpne       2849
        //  2847: iconst_1       
        //  2848: ireturn        
        //  2849: iload_0         /* c */
        //  2850: sipush          8031
        //  2853: if_icmpge       2858
        //  2856: iconst_0       
        //  2857: ireturn        
        //  2858: iload_0         /* c */
        //  2859: sipush          8061
        //  2862: if_icmpgt       2867
        //  2865: iconst_1       
        //  2866: ireturn        
        //  2867: iload_0         /* c */
        //  2868: sipush          8064
        //  2871: if_icmpge       2876
        //  2874: iconst_0       
        //  2875: ireturn        
        //  2876: iload_0         /* c */
        //  2877: sipush          8116
        //  2880: if_icmpgt       2885
        //  2883: iconst_1       
        //  2884: ireturn        
        //  2885: iload_0         /* c */
        //  2886: sipush          8118
        //  2889: if_icmpge       2894
        //  2892: iconst_0       
        //  2893: ireturn        
        //  2894: iload_0         /* c */
        //  2895: sipush          8124
        //  2898: if_icmpgt       2903
        //  2901: iconst_1       
        //  2902: ireturn        
        //  2903: iload_0         /* c */
        //  2904: sipush          8126
        //  2907: if_icmpne       2912
        //  2910: iconst_1       
        //  2911: ireturn        
        //  2912: iload_0         /* c */
        //  2913: sipush          8130
        //  2916: if_icmpge       2921
        //  2919: iconst_0       
        //  2920: ireturn        
        //  2921: iload_0         /* c */
        //  2922: sipush          8132
        //  2925: if_icmpgt       2930
        //  2928: iconst_1       
        //  2929: ireturn        
        //  2930: iload_0         /* c */
        //  2931: sipush          8134
        //  2934: if_icmpge       2939
        //  2937: iconst_0       
        //  2938: ireturn        
        //  2939: iload_0         /* c */
        //  2940: sipush          8140
        //  2943: if_icmpgt       2948
        //  2946: iconst_1       
        //  2947: ireturn        
        //  2948: iload_0         /* c */
        //  2949: sipush          8144
        //  2952: if_icmpge       2957
        //  2955: iconst_0       
        //  2956: ireturn        
        //  2957: iload_0         /* c */
        //  2958: sipush          8147
        //  2961: if_icmpgt       2966
        //  2964: iconst_1       
        //  2965: ireturn        
        //  2966: iload_0         /* c */
        //  2967: sipush          8150
        //  2970: if_icmpge       2975
        //  2973: iconst_0       
        //  2974: ireturn        
        //  2975: iload_0         /* c */
        //  2976: sipush          8155
        //  2979: if_icmpgt       2984
        //  2982: iconst_1       
        //  2983: ireturn        
        //  2984: iload_0         /* c */
        //  2985: sipush          8160
        //  2988: if_icmpge       2993
        //  2991: iconst_0       
        //  2992: ireturn        
        //  2993: iload_0         /* c */
        //  2994: sipush          8172
        //  2997: if_icmpgt       3002
        //  3000: iconst_1       
        //  3001: ireturn        
        //  3002: iload_0         /* c */
        //  3003: sipush          8178
        //  3006: if_icmpge       3011
        //  3009: iconst_0       
        //  3010: ireturn        
        //  3011: iload_0         /* c */
        //  3012: sipush          8180
        //  3015: if_icmpgt       3020
        //  3018: iconst_1       
        //  3019: ireturn        
        //  3020: iload_0         /* c */
        //  3021: sipush          8182
        //  3024: if_icmpge       3029
        //  3027: iconst_0       
        //  3028: ireturn        
        //  3029: iload_0         /* c */
        //  3030: sipush          8188
        //  3033: if_icmpgt       3038
        //  3036: iconst_1       
        //  3037: ireturn        
        //  3038: iload_0         /* c */
        //  3039: sipush          8486
        //  3042: if_icmpne       3047
        //  3045: iconst_1       
        //  3046: ireturn        
        //  3047: iload_0         /* c */
        //  3048: sipush          8490
        //  3051: if_icmpge       3056
        //  3054: iconst_0       
        //  3055: ireturn        
        //  3056: iload_0         /* c */
        //  3057: sipush          8491
        //  3060: if_icmpgt       3065
        //  3063: iconst_1       
        //  3064: ireturn        
        //  3065: iload_0         /* c */
        //  3066: sipush          8494
        //  3069: if_icmpne       3074
        //  3072: iconst_1       
        //  3073: ireturn        
        //  3074: iload_0         /* c */
        //  3075: sipush          8576
        //  3078: if_icmpge       3083
        //  3081: iconst_0       
        //  3082: ireturn        
        //  3083: iload_0         /* c */
        //  3084: sipush          8578
        //  3087: if_icmpgt       3092
        //  3090: iconst_1       
        //  3091: ireturn        
        //  3092: iload_0         /* c */
        //  3093: sipush          12295
        //  3096: if_icmpne       3101
        //  3099: iconst_1       
        //  3100: ireturn        
        //  3101: iload_0         /* c */
        //  3102: sipush          12321
        //  3105: if_icmpge       3110
        //  3108: iconst_0       
        //  3109: ireturn        
        //  3110: iload_0         /* c */
        //  3111: sipush          12329
        //  3114: if_icmpgt       3119
        //  3117: iconst_1       
        //  3118: ireturn        
        //  3119: iload_0         /* c */
        //  3120: sipush          12353
        //  3123: if_icmpge       3128
        //  3126: iconst_0       
        //  3127: ireturn        
        //  3128: iload_0         /* c */
        //  3129: sipush          12436
        //  3132: if_icmpgt       3137
        //  3135: iconst_1       
        //  3136: ireturn        
        //  3137: iload_0         /* c */
        //  3138: sipush          12449
        //  3141: if_icmpge       3146
        //  3144: iconst_0       
        //  3145: ireturn        
        //  3146: iload_0         /* c */
        //  3147: sipush          12538
        //  3150: if_icmpgt       3155
        //  3153: iconst_1       
        //  3154: ireturn        
        //  3155: iload_0         /* c */
        //  3156: sipush          12549
        //  3159: if_icmpge       3164
        //  3162: iconst_0       
        //  3163: ireturn        
        //  3164: iload_0         /* c */
        //  3165: sipush          12588
        //  3168: if_icmpgt       3173
        //  3171: iconst_1       
        //  3172: ireturn        
        //  3173: iload_0         /* c */
        //  3174: sipush          19968
        //  3177: if_icmpge       3182
        //  3180: iconst_0       
        //  3181: ireturn        
        //  3182: iload_0         /* c */
        //  3183: ldc             40869
        //  3185: if_icmpgt       3190
        //  3188: iconst_1       
        //  3189: ireturn        
        //  3190: iload_0         /* c */
        //  3191: ldc             44032
        //  3193: if_icmpge       3198
        //  3196: iconst_0       
        //  3197: ireturn        
        //  3198: iload_0         /* c */
        //  3199: ldc             55203
        //  3201: if_icmpgt       3206
        //  3204: iconst_1       
        //  3205: ireturn        
        //  3206: iconst_0       
        //  3207: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      3208    0     c     C
        // 
        // The error that occurred was:
        // 
        // java.lang.StackOverflowError
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2104)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:840)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1656)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static boolean isXMLCombiningChar(final char c) {
        return c >= '\u0300' && (c <= '\u0345' || (c >= '\u0360' && (c <= '\u0361' || (c >= '\u0483' && (c <= '\u0486' || (c >= '\u0591' && (c <= '\u05a1' || (c >= '\u05a3' && (c <= '\u05b9' || (c >= '\u05bb' && (c <= '\u05bd' || c == '\u05bf' || (c >= '\u05c1' && (c <= '\u05c2' || c == '\u05c4' || (c >= '\u064b' && (c <= '\u0652' || c == '\u0670' || (c >= '\u06d6' && (c <= '\u06dc' || (c >= '\u06dd' && (c <= '\u06df' || (c >= '\u06e0' && (c <= '\u06e4' || (c >= '\u06e7' && (c <= '\u06e8' || (c >= '\u06ea' && (c <= '\u06ed' || (c >= '\u0901' && (c <= '\u0903' || c == '\u093c' || (c >= '\u093e' && (c <= '\u094c' || c == '\u094d' || (c >= '\u0951' && (c <= '\u0954' || (c >= '\u0962' && (c <= '\u0963' || (c >= '\u0981' && (c <= '\u0983' || c == '\u09bc' || c == '\u09be' || c == '\u09bf' || (c >= '\u09c0' && (c <= '\u09c4' || (c >= '\u09c7' && (c <= '\u09c8' || (c >= '\u09cb' && (c <= '\u09cd' || c == '\u09d7' || (c >= '\u09e2' && (c <= '\u09e3' || c == '\u0a02' || c == '\u0a3c' || c == '\u0a3e' || c == '\u0a3f' || (c >= '\u0a40' && (c <= '\u0a42' || (c >= '\u0a47' && (c <= '\u0a48' || (c >= '\u0a4b' && (c <= '\u0a4d' || (c >= '\u0a70' && (c <= '\u0a71' || (c >= '\u0a81' && (c <= '\u0a83' || c == '\u0abc' || (c >= '\u0abe' && (c <= '\u0ac5' || (c >= '\u0ac7' && (c <= '\u0ac9' || (c >= '\u0acb' && (c <= '\u0acd' || (c >= '\u0b01' && (c <= '\u0b03' || c == '\u0b3c' || (c >= '\u0b3e' && (c <= '\u0b43' || (c >= '\u0b47' && (c <= '\u0b48' || (c >= '\u0b4b' && (c <= '\u0b4d' || (c >= '\u0b56' && (c <= '\u0b57' || (c >= '\u0b82' && (c <= '\u0b83' || (c >= '\u0bbe' && (c <= '\u0bc2' || (c >= '\u0bc6' && (c <= '\u0bc8' || (c >= '\u0bca' && (c <= '\u0bcd' || c == '\u0bd7' || (c >= '\u0c01' && (c <= '\u0c03' || (c >= '\u0c3e' && (c <= '\u0c44' || (c >= '\u0c46' && (c <= '\u0c48' || (c >= '\u0c4a' && (c <= '\u0c4d' || (c >= '\u0c55' && (c <= '\u0c56' || (c >= '\u0c82' && (c <= '\u0c83' || (c >= '\u0cbe' && (c <= '\u0cc4' || (c >= '\u0cc6' && (c <= '\u0cc8' || (c >= '\u0cca' && (c <= '\u0ccd' || (c >= '\u0cd5' && (c <= '\u0cd6' || (c >= '\u0d02' && (c <= '\u0d03' || (c >= '\u0d3e' && (c <= '\u0d43' || (c >= '\u0d46' && (c <= '\u0d48' || (c >= '\u0d4a' && (c <= '\u0d4d' || c == '\u0d57' || c == '\u0e31' || (c >= '\u0e34' && (c <= '\u0e3a' || (c >= '\u0e47' && (c <= '\u0e4e' || c == '\u0eb1' || (c >= '\u0eb4' && (c <= '\u0eb9' || (c >= '\u0ebb' && (c <= '\u0ebc' || (c >= '\u0ec8' && (c <= '\u0ecd' || (c >= '\u0f18' && (c <= '\u0f19' || c == '\u0f35' || c == '\u0f37' || c == '\u0f39' || c == '\u0f3e' || c == '\u0f3f' || (c >= '\u0f71' && (c <= '\u0f84' || (c >= '\u0f86' && (c <= '\u0f8b' || (c >= '\u0f90' && (c <= '\u0f95' || c == '\u0f97' || (c >= '\u0f99' && (c <= '\u0fad' || (c >= '\u0fb1' && (c <= '\u0fb7' || c == '\u0fb9' || (c >= '\u20d0' && (c <= '\u20dc' || c == '\u20e1' || (c >= '\u302a' && (c <= '\u302f' || c == '\u3099' || c == '\u309a')))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
    }
    
    public static boolean isXMLExtender(final char c) {
        return c >= '¶' && (c == '·' || c == '\u02d0' || c == '\u02d1' || c == '\u0387' || c == '\u0640' || c == '\u0e46' || c == '\u0ec6' || c == '\u3005' || (c >= '\u3031' && (c <= '\u3035' || (c >= '\u309d' && (c <= '\u309e' || (c >= '\u30fc' && c <= '\u30fe'))))));
    }
    
    public static boolean isXMLDigit(final char c) {
        return c >= '0' && (c <= '9' || (c >= '\u0660' && (c <= '\u0669' || (c >= '\u06f0' && (c <= '\u06f9' || (c >= '\u0966' && (c <= '\u096f' || (c >= '\u09e6' && (c <= '\u09ef' || (c >= '\u0a66' && (c <= '\u0a6f' || (c >= '\u0ae6' && (c <= '\u0aef' || (c >= '\u0b66' && (c <= '\u0b6f' || (c >= '\u0be7' && (c <= '\u0bef' || (c >= '\u0c66' && (c <= '\u0c6f' || (c >= '\u0ce6' && (c <= '\u0cef' || (c >= '\u0d66' && (c <= '\u0d6f' || (c >= '\u0e50' && (c <= '\u0e59' || (c >= '\u0ed0' && (c <= '\u0ed9' || (c >= '\u0f20' && c <= '\u0f29'))))))))))))))))))))))))))));
    }
    
    public static boolean isXMLWhitespace(final char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.List;

public final class Verifier
{
    private static final String CVS_ID = "@(#) $RCSfile: Verifier.java,v $ $Revision: 1.32 $ $Date: 2002/03/12 07:57:06 $ $Name: jdom_1_0_b8 $";
    
    public static final String checkElementName(final String name) {
        final String reason;
        if ((reason = checkXMLName(name)) != null) {
            return reason;
        }
        if (name.indexOf(":") != -1) {
            return "Element names cannot contain colons";
        }
        return null;
    }
    
    public static final String checkAttributeName(final String name) {
        final String reason;
        if ((reason = checkXMLName(name)) != null) {
            return reason;
        }
        if (name.equals("xml:space") || name.equals("xml:lang")) {
            return null;
        }
        if (name.indexOf(":") != -1) {
            return "Attribute names cannot contain colons";
        }
        if (name.equals("xmlns")) {
            return "An Attribute name may not be \"xmlns\"; use the Namespace class to manage namespaces";
        }
        return null;
    }
    
    public static final String checkCharacterData(final String text) {
        if (text == null) {
            return "A null is not a legal XML value";
        }
        for (int i = 0, len = text.length(); i < len; ++i) {
            if (!isXMLCharacter(text.charAt(i))) {
                return "0x" + Integer.toHexString(text.charAt(i)) + " is not a legal XML character";
            }
        }
        return null;
    }
    
    public static final String checkCDATASection(final String data) {
        String reason = null;
        if ((reason = checkCharacterData(data)) != null) {
            return reason;
        }
        if (data.indexOf("]]>") != -1) {
            return "CDATA cannot internally contain a CDATA ending delimiter (]]>)";
        }
        return null;
    }
    
    public static final String checkNamespacePrefix(final String prefix) {
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
    
    public static final String checkNamespaceURI(final String uri) {
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
    
    public static final String checkNamespaceCollision(final Namespace namespace, final Namespace other) {
        if (namespace == Namespace.NO_NAMESPACE || other == Namespace.NO_NAMESPACE) {
            return null;
        }
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
    
    public static final String checkNamespaceCollision(final Attribute attribute, final Element element) {
        final Namespace namespace = attribute.getNamespace();
        final String prefix = namespace.getPrefix();
        if ("".equals(prefix)) {
            return null;
        }
        return checkNamespaceCollision(namespace, element);
    }
    
    public static final String checkNamespaceCollision(final Namespace namespace, final Element element) {
        String reason = checkNamespaceCollision(namespace, element.getNamespace());
        if (reason != null) {
            return String.valueOf(reason) + " with the element namespace prefix";
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
    
    public static final String checkNamespaceCollision(final Namespace namespace, final Attribute attribute) {
        String reason = checkNamespaceCollision(namespace, attribute.getNamespace());
        if (reason != null) {
            reason = String.valueOf(reason) + " with an attribute namespace prefix on the element";
        }
        return reason;
    }
    
    public static final String checkNamespaceCollision(final Namespace namespace, final List list) {
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
                reason = String.valueOf(reason) + " with an additional namespace declared by the element";
            }
        }
        return reason;
    }
    
    public static final String checkProcessingInstructionTarget(final String target) {
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
    
    public static final String checkCommentData(final String data) {
        String reason = null;
        if ((reason = checkCharacterData(data)) != null) {
            return reason;
        }
        if (data.indexOf("--") != -1) {
            return "Comments cannot contain double hyphens (--)";
        }
        return null;
    }
    
    private static boolean isXMLPublicIDCharacter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= '?' && c <= 'Z') || (c >= '\'' && c <= ';') || c == ' ' || c == '!' || c == '=' || c == '#' || c == '$' || c == '_' || c == '%' || c == '\n' || c == '\r' || c == '\t';
    }
    
    public static final String checkPublicID(final String publicID) {
        String reason = null;
        if (publicID == null) {
            return null;
        }
        for (int i = 0; i < publicID.length(); ++i) {
            final char c = publicID.charAt(i);
            if (!isXMLPublicIDCharacter(c)) {
                reason = String.valueOf(c) + " is not a legal character in public IDs";
                break;
            }
        }
        return reason;
    }
    
    public static final String checkSystemLiteral(final String systemLiteral) {
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
        for (int i = 0, len = name.length(); i < len; ++i) {
            final char c = name.charAt(i);
            if (!isXMLNameCharacter(c)) {
                return "XML names cannot contain the character \"" + c + "\"";
            }
        }
        return null;
    }
    
    public static boolean isXMLCharacter(final char c) {
        return c == '\n' || c == '\r' || c == '\t' || (c >= ' ' && (c <= '\ud7ff' || (c >= '\ue000' && (c <= '\ufffd' || (c >= 65536 && c <= 1114111)))));
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
        //  3183: ldc_w           40869
        //  3186: if_icmpgt       3191
        //  3189: iconst_1       
        //  3190: ireturn        
        //  3191: iload_0         /* c */
        //  3192: ldc_w           44032
        //  3195: if_icmpge       3200
        //  3198: iconst_0       
        //  3199: ireturn        
        //  3200: iload_0         /* c */
        //  3201: ldc_w           55203
        //  3204: if_icmpgt       3209
        //  3207: iconst_1       
        //  3208: ireturn        
        //  3209: iconst_0       
        //  3210: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      3211    0     c     C
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
    
    private static boolean isXMLLetterOld(final char c) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_0         /* c */
        //     1: bipush          65
        //     3: if_icmplt       14
        //     6: iload_0         /* c */
        //     7: bipush          90
        //     9: if_icmpgt       14
        //    12: iconst_1       
        //    13: ireturn        
        //    14: iload_0         /* c */
        //    15: bipush          97
        //    17: if_icmplt       28
        //    20: iload_0         /* c */
        //    21: bipush          122
        //    23: if_icmpgt       28
        //    26: iconst_1       
        //    27: ireturn        
        //    28: iload_0         /* c */
        //    29: sipush          192
        //    32: if_icmplt       44
        //    35: iload_0         /* c */
        //    36: sipush          214
        //    39: if_icmpgt       44
        //    42: iconst_1       
        //    43: ireturn        
        //    44: iload_0         /* c */
        //    45: sipush          216
        //    48: if_icmplt       60
        //    51: iload_0         /* c */
        //    52: sipush          246
        //    55: if_icmpgt       60
        //    58: iconst_1       
        //    59: ireturn        
        //    60: iload_0         /* c */
        //    61: sipush          248
        //    64: if_icmplt       76
        //    67: iload_0         /* c */
        //    68: sipush          255
        //    71: if_icmpgt       76
        //    74: iconst_1       
        //    75: ireturn        
        //    76: iload_0         /* c */
        //    77: sipush          256
        //    80: if_icmplt       92
        //    83: iload_0         /* c */
        //    84: sipush          305
        //    87: if_icmpgt       92
        //    90: iconst_1       
        //    91: ireturn        
        //    92: iload_0         /* c */
        //    93: sipush          308
        //    96: if_icmplt       108
        //    99: iload_0         /* c */
        //   100: sipush          318
        //   103: if_icmpgt       108
        //   106: iconst_1       
        //   107: ireturn        
        //   108: iload_0         /* c */
        //   109: sipush          321
        //   112: if_icmplt       124
        //   115: iload_0         /* c */
        //   116: sipush          328
        //   119: if_icmpgt       124
        //   122: iconst_1       
        //   123: ireturn        
        //   124: iload_0         /* c */
        //   125: sipush          330
        //   128: if_icmplt       140
        //   131: iload_0         /* c */
        //   132: sipush          382
        //   135: if_icmpgt       140
        //   138: iconst_1       
        //   139: ireturn        
        //   140: iload_0         /* c */
        //   141: sipush          384
        //   144: if_icmplt       156
        //   147: iload_0         /* c */
        //   148: sipush          451
        //   151: if_icmpgt       156
        //   154: iconst_1       
        //   155: ireturn        
        //   156: iload_0         /* c */
        //   157: sipush          461
        //   160: if_icmplt       172
        //   163: iload_0         /* c */
        //   164: sipush          496
        //   167: if_icmpgt       172
        //   170: iconst_1       
        //   171: ireturn        
        //   172: iload_0         /* c */
        //   173: sipush          500
        //   176: if_icmplt       188
        //   179: iload_0         /* c */
        //   180: sipush          501
        //   183: if_icmpgt       188
        //   186: iconst_1       
        //   187: ireturn        
        //   188: iload_0         /* c */
        //   189: sipush          506
        //   192: if_icmplt       204
        //   195: iload_0         /* c */
        //   196: sipush          535
        //   199: if_icmpgt       204
        //   202: iconst_1       
        //   203: ireturn        
        //   204: iload_0         /* c */
        //   205: sipush          592
        //   208: if_icmplt       220
        //   211: iload_0         /* c */
        //   212: sipush          680
        //   215: if_icmpgt       220
        //   218: iconst_1       
        //   219: ireturn        
        //   220: iload_0         /* c */
        //   221: sipush          699
        //   224: if_icmplt       236
        //   227: iload_0         /* c */
        //   228: sipush          705
        //   231: if_icmpgt       236
        //   234: iconst_1       
        //   235: ireturn        
        //   236: iload_0         /* c */
        //   237: sipush          904
        //   240: if_icmplt       252
        //   243: iload_0         /* c */
        //   244: sipush          906
        //   247: if_icmpgt       252
        //   250: iconst_1       
        //   251: ireturn        
        //   252: iload_0         /* c */
        //   253: sipush          902
        //   256: if_icmpne       261
        //   259: iconst_1       
        //   260: ireturn        
        //   261: iload_0         /* c */
        //   262: sipush          908
        //   265: if_icmpne       270
        //   268: iconst_1       
        //   269: ireturn        
        //   270: iload_0         /* c */
        //   271: sipush          910
        //   274: if_icmplt       286
        //   277: iload_0         /* c */
        //   278: sipush          929
        //   281: if_icmpgt       286
        //   284: iconst_1       
        //   285: ireturn        
        //   286: iload_0         /* c */
        //   287: sipush          931
        //   290: if_icmplt       302
        //   293: iload_0         /* c */
        //   294: sipush          974
        //   297: if_icmpgt       302
        //   300: iconst_1       
        //   301: ireturn        
        //   302: iload_0         /* c */
        //   303: sipush          976
        //   306: if_icmplt       318
        //   309: iload_0         /* c */
        //   310: sipush          982
        //   313: if_icmpgt       318
        //   316: iconst_1       
        //   317: ireturn        
        //   318: iload_0         /* c */
        //   319: sipush          986
        //   322: if_icmpne       327
        //   325: iconst_1       
        //   326: ireturn        
        //   327: iload_0         /* c */
        //   328: sipush          988
        //   331: if_icmpne       336
        //   334: iconst_1       
        //   335: ireturn        
        //   336: iload_0         /* c */
        //   337: sipush          990
        //   340: if_icmpne       345
        //   343: iconst_1       
        //   344: ireturn        
        //   345: iload_0         /* c */
        //   346: sipush          992
        //   349: if_icmpne       354
        //   352: iconst_1       
        //   353: ireturn        
        //   354: iload_0         /* c */
        //   355: sipush          994
        //   358: if_icmplt       370
        //   361: iload_0         /* c */
        //   362: sipush          1011
        //   365: if_icmpgt       370
        //   368: iconst_1       
        //   369: ireturn        
        //   370: iload_0         /* c */
        //   371: sipush          1025
        //   374: if_icmplt       386
        //   377: iload_0         /* c */
        //   378: sipush          1036
        //   381: if_icmpgt       386
        //   384: iconst_1       
        //   385: ireturn        
        //   386: iload_0         /* c */
        //   387: sipush          1038
        //   390: if_icmplt       402
        //   393: iload_0         /* c */
        //   394: sipush          1103
        //   397: if_icmpgt       402
        //   400: iconst_1       
        //   401: ireturn        
        //   402: iload_0         /* c */
        //   403: sipush          1105
        //   406: if_icmplt       418
        //   409: iload_0         /* c */
        //   410: sipush          1116
        //   413: if_icmpgt       418
        //   416: iconst_1       
        //   417: ireturn        
        //   418: iload_0         /* c */
        //   419: sipush          1118
        //   422: if_icmplt       434
        //   425: iload_0         /* c */
        //   426: sipush          1153
        //   429: if_icmpgt       434
        //   432: iconst_1       
        //   433: ireturn        
        //   434: iload_0         /* c */
        //   435: sipush          1168
        //   438: if_icmplt       450
        //   441: iload_0         /* c */
        //   442: sipush          1220
        //   445: if_icmpgt       450
        //   448: iconst_1       
        //   449: ireturn        
        //   450: iload_0         /* c */
        //   451: sipush          1223
        //   454: if_icmplt       466
        //   457: iload_0         /* c */
        //   458: sipush          1224
        //   461: if_icmpgt       466
        //   464: iconst_1       
        //   465: ireturn        
        //   466: iload_0         /* c */
        //   467: sipush          1227
        //   470: if_icmplt       482
        //   473: iload_0         /* c */
        //   474: sipush          1228
        //   477: if_icmpgt       482
        //   480: iconst_1       
        //   481: ireturn        
        //   482: iload_0         /* c */
        //   483: sipush          1232
        //   486: if_icmplt       498
        //   489: iload_0         /* c */
        //   490: sipush          1259
        //   493: if_icmpgt       498
        //   496: iconst_1       
        //   497: ireturn        
        //   498: iload_0         /* c */
        //   499: sipush          1262
        //   502: if_icmplt       514
        //   505: iload_0         /* c */
        //   506: sipush          1269
        //   509: if_icmpgt       514
        //   512: iconst_1       
        //   513: ireturn        
        //   514: iload_0         /* c */
        //   515: sipush          1272
        //   518: if_icmplt       530
        //   521: iload_0         /* c */
        //   522: sipush          1273
        //   525: if_icmpgt       530
        //   528: iconst_1       
        //   529: ireturn        
        //   530: iload_0         /* c */
        //   531: sipush          1329
        //   534: if_icmplt       546
        //   537: iload_0         /* c */
        //   538: sipush          1366
        //   541: if_icmpgt       546
        //   544: iconst_1       
        //   545: ireturn        
        //   546: iload_0         /* c */
        //   547: sipush          1369
        //   550: if_icmpne       555
        //   553: iconst_1       
        //   554: ireturn        
        //   555: iload_0         /* c */
        //   556: sipush          1377
        //   559: if_icmplt       571
        //   562: iload_0         /* c */
        //   563: sipush          1414
        //   566: if_icmpgt       571
        //   569: iconst_1       
        //   570: ireturn        
        //   571: iload_0         /* c */
        //   572: sipush          1488
        //   575: if_icmplt       587
        //   578: iload_0         /* c */
        //   579: sipush          1514
        //   582: if_icmpgt       587
        //   585: iconst_1       
        //   586: ireturn        
        //   587: iload_0         /* c */
        //   588: sipush          1520
        //   591: if_icmplt       603
        //   594: iload_0         /* c */
        //   595: sipush          1522
        //   598: if_icmpgt       603
        //   601: iconst_1       
        //   602: ireturn        
        //   603: iload_0         /* c */
        //   604: sipush          1569
        //   607: if_icmplt       619
        //   610: iload_0         /* c */
        //   611: sipush          1594
        //   614: if_icmpgt       619
        //   617: iconst_1       
        //   618: ireturn        
        //   619: iload_0         /* c */
        //   620: sipush          1601
        //   623: if_icmplt       635
        //   626: iload_0         /* c */
        //   627: sipush          1610
        //   630: if_icmpgt       635
        //   633: iconst_1       
        //   634: ireturn        
        //   635: iload_0         /* c */
        //   636: sipush          1649
        //   639: if_icmplt       651
        //   642: iload_0         /* c */
        //   643: sipush          1719
        //   646: if_icmpgt       651
        //   649: iconst_1       
        //   650: ireturn        
        //   651: iload_0         /* c */
        //   652: sipush          1722
        //   655: if_icmplt       667
        //   658: iload_0         /* c */
        //   659: sipush          1726
        //   662: if_icmpgt       667
        //   665: iconst_1       
        //   666: ireturn        
        //   667: iload_0         /* c */
        //   668: sipush          1728
        //   671: if_icmplt       683
        //   674: iload_0         /* c */
        //   675: sipush          1742
        //   678: if_icmpgt       683
        //   681: iconst_1       
        //   682: ireturn        
        //   683: iload_0         /* c */
        //   684: sipush          1744
        //   687: if_icmplt       699
        //   690: iload_0         /* c */
        //   691: sipush          1747
        //   694: if_icmpgt       699
        //   697: iconst_1       
        //   698: ireturn        
        //   699: iload_0         /* c */
        //   700: sipush          1749
        //   703: if_icmpne       708
        //   706: iconst_1       
        //   707: ireturn        
        //   708: iload_0         /* c */
        //   709: sipush          1765
        //   712: if_icmplt       724
        //   715: iload_0         /* c */
        //   716: sipush          1766
        //   719: if_icmpgt       724
        //   722: iconst_1       
        //   723: ireturn        
        //   724: iload_0         /* c */
        //   725: sipush          2309
        //   728: if_icmplt       740
        //   731: iload_0         /* c */
        //   732: sipush          2361
        //   735: if_icmpgt       740
        //   738: iconst_1       
        //   739: ireturn        
        //   740: iload_0         /* c */
        //   741: sipush          2365
        //   744: if_icmpne       749
        //   747: iconst_1       
        //   748: ireturn        
        //   749: iload_0         /* c */
        //   750: sipush          2392
        //   753: if_icmplt       765
        //   756: iload_0         /* c */
        //   757: sipush          2401
        //   760: if_icmpgt       765
        //   763: iconst_1       
        //   764: ireturn        
        //   765: iload_0         /* c */
        //   766: sipush          2437
        //   769: if_icmplt       781
        //   772: iload_0         /* c */
        //   773: sipush          2444
        //   776: if_icmpgt       781
        //   779: iconst_1       
        //   780: ireturn        
        //   781: iload_0         /* c */
        //   782: sipush          2447
        //   785: if_icmplt       797
        //   788: iload_0         /* c */
        //   789: sipush          2448
        //   792: if_icmpgt       797
        //   795: iconst_1       
        //   796: ireturn        
        //   797: iload_0         /* c */
        //   798: sipush          2451
        //   801: if_icmplt       813
        //   804: iload_0         /* c */
        //   805: sipush          2472
        //   808: if_icmpgt       813
        //   811: iconst_1       
        //   812: ireturn        
        //   813: iload_0         /* c */
        //   814: sipush          2474
        //   817: if_icmplt       829
        //   820: iload_0         /* c */
        //   821: sipush          2480
        //   824: if_icmpgt       829
        //   827: iconst_1       
        //   828: ireturn        
        //   829: iload_0         /* c */
        //   830: sipush          2482
        //   833: if_icmpne       838
        //   836: iconst_1       
        //   837: ireturn        
        //   838: iload_0         /* c */
        //   839: sipush          2486
        //   842: if_icmplt       854
        //   845: iload_0         /* c */
        //   846: sipush          2489
        //   849: if_icmpgt       854
        //   852: iconst_1       
        //   853: ireturn        
        //   854: iload_0         /* c */
        //   855: sipush          2524
        //   858: if_icmplt       870
        //   861: iload_0         /* c */
        //   862: sipush          2525
        //   865: if_icmpgt       870
        //   868: iconst_1       
        //   869: ireturn        
        //   870: iload_0         /* c */
        //   871: sipush          2527
        //   874: if_icmplt       886
        //   877: iload_0         /* c */
        //   878: sipush          2529
        //   881: if_icmpgt       886
        //   884: iconst_1       
        //   885: ireturn        
        //   886: iload_0         /* c */
        //   887: sipush          2544
        //   890: if_icmplt       902
        //   893: iload_0         /* c */
        //   894: sipush          2545
        //   897: if_icmpgt       902
        //   900: iconst_1       
        //   901: ireturn        
        //   902: iload_0         /* c */
        //   903: sipush          2565
        //   906: if_icmplt       918
        //   909: iload_0         /* c */
        //   910: sipush          2570
        //   913: if_icmpgt       918
        //   916: iconst_1       
        //   917: ireturn        
        //   918: iload_0         /* c */
        //   919: sipush          2575
        //   922: if_icmplt       934
        //   925: iload_0         /* c */
        //   926: sipush          2576
        //   929: if_icmpgt       934
        //   932: iconst_1       
        //   933: ireturn        
        //   934: iload_0         /* c */
        //   935: sipush          2579
        //   938: if_icmplt       950
        //   941: iload_0         /* c */
        //   942: sipush          2600
        //   945: if_icmpgt       950
        //   948: iconst_1       
        //   949: ireturn        
        //   950: iload_0         /* c */
        //   951: sipush          2602
        //   954: if_icmplt       966
        //   957: iload_0         /* c */
        //   958: sipush          2608
        //   961: if_icmpgt       966
        //   964: iconst_1       
        //   965: ireturn        
        //   966: iload_0         /* c */
        //   967: sipush          2610
        //   970: if_icmplt       982
        //   973: iload_0         /* c */
        //   974: sipush          2611
        //   977: if_icmpgt       982
        //   980: iconst_1       
        //   981: ireturn        
        //   982: iload_0         /* c */
        //   983: sipush          2613
        //   986: if_icmplt       998
        //   989: iload_0         /* c */
        //   990: sipush          2614
        //   993: if_icmpgt       998
        //   996: iconst_1       
        //   997: ireturn        
        //   998: iload_0         /* c */
        //   999: sipush          2616
        //  1002: if_icmplt       1014
        //  1005: iload_0         /* c */
        //  1006: sipush          2617
        //  1009: if_icmpgt       1014
        //  1012: iconst_1       
        //  1013: ireturn        
        //  1014: iload_0         /* c */
        //  1015: sipush          2649
        //  1018: if_icmplt       1030
        //  1021: iload_0         /* c */
        //  1022: sipush          2652
        //  1025: if_icmpgt       1030
        //  1028: iconst_1       
        //  1029: ireturn        
        //  1030: iload_0         /* c */
        //  1031: sipush          2654
        //  1034: if_icmpne       1039
        //  1037: iconst_1       
        //  1038: ireturn        
        //  1039: iload_0         /* c */
        //  1040: sipush          2674
        //  1043: if_icmplt       1055
        //  1046: iload_0         /* c */
        //  1047: sipush          2676
        //  1050: if_icmpgt       1055
        //  1053: iconst_1       
        //  1054: ireturn        
        //  1055: iload_0         /* c */
        //  1056: sipush          2693
        //  1059: if_icmplt       1071
        //  1062: iload_0         /* c */
        //  1063: sipush          2699
        //  1066: if_icmpgt       1071
        //  1069: iconst_1       
        //  1070: ireturn        
        //  1071: iload_0         /* c */
        //  1072: sipush          2701
        //  1075: if_icmpne       1080
        //  1078: iconst_1       
        //  1079: ireturn        
        //  1080: iload_0         /* c */
        //  1081: sipush          2703
        //  1084: if_icmplt       1096
        //  1087: iload_0         /* c */
        //  1088: sipush          2705
        //  1091: if_icmpgt       1096
        //  1094: iconst_1       
        //  1095: ireturn        
        //  1096: iload_0         /* c */
        //  1097: sipush          2707
        //  1100: if_icmplt       1112
        //  1103: iload_0         /* c */
        //  1104: sipush          2728
        //  1107: if_icmpgt       1112
        //  1110: iconst_1       
        //  1111: ireturn        
        //  1112: iload_0         /* c */
        //  1113: sipush          2730
        //  1116: if_icmplt       1128
        //  1119: iload_0         /* c */
        //  1120: sipush          2736
        //  1123: if_icmpgt       1128
        //  1126: iconst_1       
        //  1127: ireturn        
        //  1128: iload_0         /* c */
        //  1129: sipush          2738
        //  1132: if_icmplt       1144
        //  1135: iload_0         /* c */
        //  1136: sipush          2739
        //  1139: if_icmpgt       1144
        //  1142: iconst_1       
        //  1143: ireturn        
        //  1144: iload_0         /* c */
        //  1145: sipush          2741
        //  1148: if_icmplt       1160
        //  1151: iload_0         /* c */
        //  1152: sipush          2745
        //  1155: if_icmpgt       1160
        //  1158: iconst_1       
        //  1159: ireturn        
        //  1160: iload_0         /* c */
        //  1161: sipush          2749
        //  1164: if_icmpne       1169
        //  1167: iconst_1       
        //  1168: ireturn        
        //  1169: iload_0         /* c */
        //  1170: sipush          2784
        //  1173: if_icmpne       1178
        //  1176: iconst_1       
        //  1177: ireturn        
        //  1178: iload_0         /* c */
        //  1179: sipush          2821
        //  1182: if_icmplt       1194
        //  1185: iload_0         /* c */
        //  1186: sipush          2828
        //  1189: if_icmpgt       1194
        //  1192: iconst_1       
        //  1193: ireturn        
        //  1194: iload_0         /* c */
        //  1195: sipush          2831
        //  1198: if_icmplt       1210
        //  1201: iload_0         /* c */
        //  1202: sipush          2832
        //  1205: if_icmpgt       1210
        //  1208: iconst_1       
        //  1209: ireturn        
        //  1210: iload_0         /* c */
        //  1211: sipush          2835
        //  1214: if_icmplt       1226
        //  1217: iload_0         /* c */
        //  1218: sipush          2856
        //  1221: if_icmpgt       1226
        //  1224: iconst_1       
        //  1225: ireturn        
        //  1226: iload_0         /* c */
        //  1227: sipush          2858
        //  1230: if_icmplt       1242
        //  1233: iload_0         /* c */
        //  1234: sipush          2864
        //  1237: if_icmpgt       1242
        //  1240: iconst_1       
        //  1241: ireturn        
        //  1242: iload_0         /* c */
        //  1243: sipush          2866
        //  1246: if_icmplt       1258
        //  1249: iload_0         /* c */
        //  1250: sipush          2867
        //  1253: if_icmpgt       1258
        //  1256: iconst_1       
        //  1257: ireturn        
        //  1258: iload_0         /* c */
        //  1259: sipush          2870
        //  1262: if_icmplt       1274
        //  1265: iload_0         /* c */
        //  1266: sipush          2873
        //  1269: if_icmpgt       1274
        //  1272: iconst_1       
        //  1273: ireturn        
        //  1274: iload_0         /* c */
        //  1275: sipush          2877
        //  1278: if_icmpne       1283
        //  1281: iconst_1       
        //  1282: ireturn        
        //  1283: iload_0         /* c */
        //  1284: sipush          2908
        //  1287: if_icmplt       1299
        //  1290: iload_0         /* c */
        //  1291: sipush          2909
        //  1294: if_icmpgt       1299
        //  1297: iconst_1       
        //  1298: ireturn        
        //  1299: iload_0         /* c */
        //  1300: sipush          2911
        //  1303: if_icmplt       1315
        //  1306: iload_0         /* c */
        //  1307: sipush          2913
        //  1310: if_icmpgt       1315
        //  1313: iconst_1       
        //  1314: ireturn        
        //  1315: iload_0         /* c */
        //  1316: sipush          2949
        //  1319: if_icmplt       1331
        //  1322: iload_0         /* c */
        //  1323: sipush          2954
        //  1326: if_icmpgt       1331
        //  1329: iconst_1       
        //  1330: ireturn        
        //  1331: iload_0         /* c */
        //  1332: sipush          2958
        //  1335: if_icmplt       1347
        //  1338: iload_0         /* c */
        //  1339: sipush          2960
        //  1342: if_icmpgt       1347
        //  1345: iconst_1       
        //  1346: ireturn        
        //  1347: iload_0         /* c */
        //  1348: sipush          2962
        //  1351: if_icmplt       1363
        //  1354: iload_0         /* c */
        //  1355: sipush          2965
        //  1358: if_icmpgt       1363
        //  1361: iconst_1       
        //  1362: ireturn        
        //  1363: iload_0         /* c */
        //  1364: sipush          2969
        //  1367: if_icmplt       1379
        //  1370: iload_0         /* c */
        //  1371: sipush          2970
        //  1374: if_icmpgt       1379
        //  1377: iconst_1       
        //  1378: ireturn        
        //  1379: iload_0         /* c */
        //  1380: sipush          2972
        //  1383: if_icmpne       1388
        //  1386: iconst_1       
        //  1387: ireturn        
        //  1388: iload_0         /* c */
        //  1389: sipush          2974
        //  1392: if_icmplt       1404
        //  1395: iload_0         /* c */
        //  1396: sipush          2975
        //  1399: if_icmpgt       1404
        //  1402: iconst_1       
        //  1403: ireturn        
        //  1404: iload_0         /* c */
        //  1405: sipush          2979
        //  1408: if_icmplt       1420
        //  1411: iload_0         /* c */
        //  1412: sipush          2980
        //  1415: if_icmpgt       1420
        //  1418: iconst_1       
        //  1419: ireturn        
        //  1420: iload_0         /* c */
        //  1421: sipush          2984
        //  1424: if_icmplt       1436
        //  1427: iload_0         /* c */
        //  1428: sipush          2986
        //  1431: if_icmpgt       1436
        //  1434: iconst_1       
        //  1435: ireturn        
        //  1436: iload_0         /* c */
        //  1437: sipush          2990
        //  1440: if_icmplt       1452
        //  1443: iload_0         /* c */
        //  1444: sipush          2997
        //  1447: if_icmpgt       1452
        //  1450: iconst_1       
        //  1451: ireturn        
        //  1452: iload_0         /* c */
        //  1453: sipush          2999
        //  1456: if_icmplt       1468
        //  1459: iload_0         /* c */
        //  1460: sipush          3001
        //  1463: if_icmpgt       1468
        //  1466: iconst_1       
        //  1467: ireturn        
        //  1468: iload_0         /* c */
        //  1469: sipush          3077
        //  1472: if_icmplt       1484
        //  1475: iload_0         /* c */
        //  1476: sipush          3084
        //  1479: if_icmpgt       1484
        //  1482: iconst_1       
        //  1483: ireturn        
        //  1484: iload_0         /* c */
        //  1485: sipush          3086
        //  1488: if_icmplt       1500
        //  1491: iload_0         /* c */
        //  1492: sipush          3088
        //  1495: if_icmpgt       1500
        //  1498: iconst_1       
        //  1499: ireturn        
        //  1500: iload_0         /* c */
        //  1501: sipush          3090
        //  1504: if_icmplt       1516
        //  1507: iload_0         /* c */
        //  1508: sipush          3112
        //  1511: if_icmpgt       1516
        //  1514: iconst_1       
        //  1515: ireturn        
        //  1516: iload_0         /* c */
        //  1517: sipush          3114
        //  1520: if_icmplt       1532
        //  1523: iload_0         /* c */
        //  1524: sipush          3123
        //  1527: if_icmpgt       1532
        //  1530: iconst_1       
        //  1531: ireturn        
        //  1532: iload_0         /* c */
        //  1533: sipush          3125
        //  1536: if_icmplt       1548
        //  1539: iload_0         /* c */
        //  1540: sipush          3129
        //  1543: if_icmpgt       1548
        //  1546: iconst_1       
        //  1547: ireturn        
        //  1548: iload_0         /* c */
        //  1549: sipush          3168
        //  1552: if_icmplt       1564
        //  1555: iload_0         /* c */
        //  1556: sipush          3169
        //  1559: if_icmpgt       1564
        //  1562: iconst_1       
        //  1563: ireturn        
        //  1564: iload_0         /* c */
        //  1565: sipush          3205
        //  1568: if_icmplt       1580
        //  1571: iload_0         /* c */
        //  1572: sipush          3212
        //  1575: if_icmpgt       1580
        //  1578: iconst_1       
        //  1579: ireturn        
        //  1580: iload_0         /* c */
        //  1581: sipush          3214
        //  1584: if_icmplt       1596
        //  1587: iload_0         /* c */
        //  1588: sipush          3216
        //  1591: if_icmpgt       1596
        //  1594: iconst_1       
        //  1595: ireturn        
        //  1596: iload_0         /* c */
        //  1597: sipush          3218
        //  1600: if_icmplt       1612
        //  1603: iload_0         /* c */
        //  1604: sipush          3240
        //  1607: if_icmpgt       1612
        //  1610: iconst_1       
        //  1611: ireturn        
        //  1612: iload_0         /* c */
        //  1613: sipush          3242
        //  1616: if_icmplt       1628
        //  1619: iload_0         /* c */
        //  1620: sipush          3251
        //  1623: if_icmpgt       1628
        //  1626: iconst_1       
        //  1627: ireturn        
        //  1628: iload_0         /* c */
        //  1629: sipush          3253
        //  1632: if_icmplt       1644
        //  1635: iload_0         /* c */
        //  1636: sipush          3257
        //  1639: if_icmpgt       1644
        //  1642: iconst_1       
        //  1643: ireturn        
        //  1644: iload_0         /* c */
        //  1645: sipush          3294
        //  1648: if_icmpne       1653
        //  1651: iconst_1       
        //  1652: ireturn        
        //  1653: iload_0         /* c */
        //  1654: sipush          3296
        //  1657: if_icmplt       1669
        //  1660: iload_0         /* c */
        //  1661: sipush          3297
        //  1664: if_icmpgt       1669
        //  1667: iconst_1       
        //  1668: ireturn        
        //  1669: iload_0         /* c */
        //  1670: sipush          3333
        //  1673: if_icmplt       1685
        //  1676: iload_0         /* c */
        //  1677: sipush          3340
        //  1680: if_icmpgt       1685
        //  1683: iconst_1       
        //  1684: ireturn        
        //  1685: iload_0         /* c */
        //  1686: sipush          3342
        //  1689: if_icmplt       1701
        //  1692: iload_0         /* c */
        //  1693: sipush          3344
        //  1696: if_icmpgt       1701
        //  1699: iconst_1       
        //  1700: ireturn        
        //  1701: iload_0         /* c */
        //  1702: sipush          3346
        //  1705: if_icmplt       1717
        //  1708: iload_0         /* c */
        //  1709: sipush          3368
        //  1712: if_icmpgt       1717
        //  1715: iconst_1       
        //  1716: ireturn        
        //  1717: iload_0         /* c */
        //  1718: sipush          3370
        //  1721: if_icmplt       1733
        //  1724: iload_0         /* c */
        //  1725: sipush          3385
        //  1728: if_icmpgt       1733
        //  1731: iconst_1       
        //  1732: ireturn        
        //  1733: iload_0         /* c */
        //  1734: sipush          3424
        //  1737: if_icmplt       1749
        //  1740: iload_0         /* c */
        //  1741: sipush          3425
        //  1744: if_icmpgt       1749
        //  1747: iconst_1       
        //  1748: ireturn        
        //  1749: iload_0         /* c */
        //  1750: sipush          3585
        //  1753: if_icmplt       1765
        //  1756: iload_0         /* c */
        //  1757: sipush          3630
        //  1760: if_icmpgt       1765
        //  1763: iconst_1       
        //  1764: ireturn        
        //  1765: iload_0         /* c */
        //  1766: sipush          3632
        //  1769: if_icmpne       1774
        //  1772: iconst_1       
        //  1773: ireturn        
        //  1774: iload_0         /* c */
        //  1775: sipush          3634
        //  1778: if_icmplt       1790
        //  1781: iload_0         /* c */
        //  1782: sipush          3635
        //  1785: if_icmpgt       1790
        //  1788: iconst_1       
        //  1789: ireturn        
        //  1790: iload_0         /* c */
        //  1791: sipush          3648
        //  1794: if_icmplt       1806
        //  1797: iload_0         /* c */
        //  1798: sipush          3653
        //  1801: if_icmpgt       1806
        //  1804: iconst_1       
        //  1805: ireturn        
        //  1806: iload_0         /* c */
        //  1807: sipush          3713
        //  1810: if_icmplt       1822
        //  1813: iload_0         /* c */
        //  1814: sipush          3714
        //  1817: if_icmpgt       1822
        //  1820: iconst_1       
        //  1821: ireturn        
        //  1822: iload_0         /* c */
        //  1823: sipush          3716
        //  1826: if_icmpne       1831
        //  1829: iconst_1       
        //  1830: ireturn        
        //  1831: iload_0         /* c */
        //  1832: sipush          3719
        //  1835: if_icmplt       1847
        //  1838: iload_0         /* c */
        //  1839: sipush          3720
        //  1842: if_icmpgt       1847
        //  1845: iconst_1       
        //  1846: ireturn        
        //  1847: iload_0         /* c */
        //  1848: sipush          3722
        //  1851: if_icmpne       1856
        //  1854: iconst_1       
        //  1855: ireturn        
        //  1856: iload_0         /* c */
        //  1857: sipush          3725
        //  1860: if_icmpne       1865
        //  1863: iconst_1       
        //  1864: ireturn        
        //  1865: iload_0         /* c */
        //  1866: sipush          3732
        //  1869: if_icmplt       1881
        //  1872: iload_0         /* c */
        //  1873: sipush          3735
        //  1876: if_icmpgt       1881
        //  1879: iconst_1       
        //  1880: ireturn        
        //  1881: iload_0         /* c */
        //  1882: sipush          3737
        //  1885: if_icmplt       1897
        //  1888: iload_0         /* c */
        //  1889: sipush          3743
        //  1892: if_icmpgt       1897
        //  1895: iconst_1       
        //  1896: ireturn        
        //  1897: iload_0         /* c */
        //  1898: sipush          3745
        //  1901: if_icmplt       1913
        //  1904: iload_0         /* c */
        //  1905: sipush          3747
        //  1908: if_icmpgt       1913
        //  1911: iconst_1       
        //  1912: ireturn        
        //  1913: iload_0         /* c */
        //  1914: sipush          3749
        //  1917: if_icmpne       1922
        //  1920: iconst_1       
        //  1921: ireturn        
        //  1922: iload_0         /* c */
        //  1923: sipush          3751
        //  1926: if_icmpne       1931
        //  1929: iconst_1       
        //  1930: ireturn        
        //  1931: iload_0         /* c */
        //  1932: sipush          3754
        //  1935: if_icmplt       1947
        //  1938: iload_0         /* c */
        //  1939: sipush          3755
        //  1942: if_icmpgt       1947
        //  1945: iconst_1       
        //  1946: ireturn        
        //  1947: iload_0         /* c */
        //  1948: sipush          3757
        //  1951: if_icmplt       1963
        //  1954: iload_0         /* c */
        //  1955: sipush          3758
        //  1958: if_icmpgt       1963
        //  1961: iconst_1       
        //  1962: ireturn        
        //  1963: iload_0         /* c */
        //  1964: sipush          3760
        //  1967: if_icmpne       1972
        //  1970: iconst_1       
        //  1971: ireturn        
        //  1972: iload_0         /* c */
        //  1973: sipush          3762
        //  1976: if_icmplt       1988
        //  1979: iload_0         /* c */
        //  1980: sipush          3763
        //  1983: if_icmpgt       1988
        //  1986: iconst_1       
        //  1987: ireturn        
        //  1988: iload_0         /* c */
        //  1989: sipush          3773
        //  1992: if_icmpne       1997
        //  1995: iconst_1       
        //  1996: ireturn        
        //  1997: iload_0         /* c */
        //  1998: sipush          3776
        //  2001: if_icmplt       2013
        //  2004: iload_0         /* c */
        //  2005: sipush          3780
        //  2008: if_icmpgt       2013
        //  2011: iconst_1       
        //  2012: ireturn        
        //  2013: iload_0         /* c */
        //  2014: sipush          3904
        //  2017: if_icmplt       2029
        //  2020: iload_0         /* c */
        //  2021: sipush          3911
        //  2024: if_icmpgt       2029
        //  2027: iconst_1       
        //  2028: ireturn        
        //  2029: iload_0         /* c */
        //  2030: sipush          3913
        //  2033: if_icmplt       2045
        //  2036: iload_0         /* c */
        //  2037: sipush          3945
        //  2040: if_icmpgt       2045
        //  2043: iconst_1       
        //  2044: ireturn        
        //  2045: iload_0         /* c */
        //  2046: sipush          4256
        //  2049: if_icmplt       2061
        //  2052: iload_0         /* c */
        //  2053: sipush          4293
        //  2056: if_icmpgt       2061
        //  2059: iconst_1       
        //  2060: ireturn        
        //  2061: iload_0         /* c */
        //  2062: sipush          4304
        //  2065: if_icmplt       2077
        //  2068: iload_0         /* c */
        //  2069: sipush          4342
        //  2072: if_icmpgt       2077
        //  2075: iconst_1       
        //  2076: ireturn        
        //  2077: iload_0         /* c */
        //  2078: sipush          4352
        //  2081: if_icmpne       2086
        //  2084: iconst_1       
        //  2085: ireturn        
        //  2086: iload_0         /* c */
        //  2087: sipush          4354
        //  2090: if_icmplt       2102
        //  2093: iload_0         /* c */
        //  2094: sipush          4355
        //  2097: if_icmpgt       2102
        //  2100: iconst_1       
        //  2101: ireturn        
        //  2102: iload_0         /* c */
        //  2103: sipush          4357
        //  2106: if_icmplt       2118
        //  2109: iload_0         /* c */
        //  2110: sipush          4359
        //  2113: if_icmpgt       2118
        //  2116: iconst_1       
        //  2117: ireturn        
        //  2118: iload_0         /* c */
        //  2119: sipush          4361
        //  2122: if_icmpne       2127
        //  2125: iconst_1       
        //  2126: ireturn        
        //  2127: iload_0         /* c */
        //  2128: sipush          4363
        //  2131: if_icmplt       2143
        //  2134: iload_0         /* c */
        //  2135: sipush          4364
        //  2138: if_icmpgt       2143
        //  2141: iconst_1       
        //  2142: ireturn        
        //  2143: iload_0         /* c */
        //  2144: sipush          4366
        //  2147: if_icmplt       2159
        //  2150: iload_0         /* c */
        //  2151: sipush          4370
        //  2154: if_icmpgt       2159
        //  2157: iconst_1       
        //  2158: ireturn        
        //  2159: iload_0         /* c */
        //  2160: sipush          4412
        //  2163: if_icmpne       2168
        //  2166: iconst_1       
        //  2167: ireturn        
        //  2168: iload_0         /* c */
        //  2169: sipush          4414
        //  2172: if_icmpne       2177
        //  2175: iconst_1       
        //  2176: ireturn        
        //  2177: iload_0         /* c */
        //  2178: sipush          4416
        //  2181: if_icmpne       2186
        //  2184: iconst_1       
        //  2185: ireturn        
        //  2186: iload_0         /* c */
        //  2187: sipush          4428
        //  2190: if_icmpne       2195
        //  2193: iconst_1       
        //  2194: ireturn        
        //  2195: iload_0         /* c */
        //  2196: sipush          4430
        //  2199: if_icmpne       2204
        //  2202: iconst_1       
        //  2203: ireturn        
        //  2204: iload_0         /* c */
        //  2205: sipush          4432
        //  2208: if_icmpne       2213
        //  2211: iconst_1       
        //  2212: ireturn        
        //  2213: iload_0         /* c */
        //  2214: sipush          4436
        //  2217: if_icmplt       2229
        //  2220: iload_0         /* c */
        //  2221: sipush          4437
        //  2224: if_icmpgt       2229
        //  2227: iconst_1       
        //  2228: ireturn        
        //  2229: iload_0         /* c */
        //  2230: sipush          4441
        //  2233: if_icmpne       2238
        //  2236: iconst_1       
        //  2237: ireturn        
        //  2238: iload_0         /* c */
        //  2239: sipush          4447
        //  2242: if_icmplt       2254
        //  2245: iload_0         /* c */
        //  2246: sipush          4449
        //  2249: if_icmpgt       2254
        //  2252: iconst_1       
        //  2253: ireturn        
        //  2254: iload_0         /* c */
        //  2255: sipush          4451
        //  2258: if_icmpne       2263
        //  2261: iconst_1       
        //  2262: ireturn        
        //  2263: iload_0         /* c */
        //  2264: sipush          4453
        //  2267: if_icmpne       2272
        //  2270: iconst_1       
        //  2271: ireturn        
        //  2272: iload_0         /* c */
        //  2273: sipush          4455
        //  2276: if_icmpne       2281
        //  2279: iconst_1       
        //  2280: ireturn        
        //  2281: iload_0         /* c */
        //  2282: sipush          4457
        //  2285: if_icmpne       2290
        //  2288: iconst_1       
        //  2289: ireturn        
        //  2290: iload_0         /* c */
        //  2291: sipush          4461
        //  2294: if_icmplt       2306
        //  2297: iload_0         /* c */
        //  2298: sipush          4462
        //  2301: if_icmpgt       2306
        //  2304: iconst_1       
        //  2305: ireturn        
        //  2306: iload_0         /* c */
        //  2307: sipush          4466
        //  2310: if_icmplt       2322
        //  2313: iload_0         /* c */
        //  2314: sipush          4467
        //  2317: if_icmpgt       2322
        //  2320: iconst_1       
        //  2321: ireturn        
        //  2322: iload_0         /* c */
        //  2323: sipush          4469
        //  2326: if_icmpne       2331
        //  2329: iconst_1       
        //  2330: ireturn        
        //  2331: iload_0         /* c */
        //  2332: sipush          4510
        //  2335: if_icmpne       2340
        //  2338: iconst_1       
        //  2339: ireturn        
        //  2340: iload_0         /* c */
        //  2341: sipush          4520
        //  2344: if_icmpne       2349
        //  2347: iconst_1       
        //  2348: ireturn        
        //  2349: iload_0         /* c */
        //  2350: sipush          4523
        //  2353: if_icmpne       2358
        //  2356: iconst_1       
        //  2357: ireturn        
        //  2358: iload_0         /* c */
        //  2359: sipush          4526
        //  2362: if_icmplt       2374
        //  2365: iload_0         /* c */
        //  2366: sipush          4527
        //  2369: if_icmpgt       2374
        //  2372: iconst_1       
        //  2373: ireturn        
        //  2374: iload_0         /* c */
        //  2375: sipush          4535
        //  2378: if_icmplt       2390
        //  2381: iload_0         /* c */
        //  2382: sipush          4536
        //  2385: if_icmpgt       2390
        //  2388: iconst_1       
        //  2389: ireturn        
        //  2390: iload_0         /* c */
        //  2391: sipush          4538
        //  2394: if_icmpne       2399
        //  2397: iconst_1       
        //  2398: ireturn        
        //  2399: iload_0         /* c */
        //  2400: sipush          4540
        //  2403: if_icmplt       2415
        //  2406: iload_0         /* c */
        //  2407: sipush          4546
        //  2410: if_icmpgt       2415
        //  2413: iconst_1       
        //  2414: ireturn        
        //  2415: iload_0         /* c */
        //  2416: sipush          4587
        //  2419: if_icmpne       2424
        //  2422: iconst_1       
        //  2423: ireturn        
        //  2424: iload_0         /* c */
        //  2425: sipush          4592
        //  2428: if_icmpne       2433
        //  2431: iconst_1       
        //  2432: ireturn        
        //  2433: iload_0         /* c */
        //  2434: sipush          4601
        //  2437: if_icmpne       2442
        //  2440: iconst_1       
        //  2441: ireturn        
        //  2442: iload_0         /* c */
        //  2443: sipush          7680
        //  2446: if_icmplt       2458
        //  2449: iload_0         /* c */
        //  2450: sipush          7835
        //  2453: if_icmpgt       2458
        //  2456: iconst_1       
        //  2457: ireturn        
        //  2458: iload_0         /* c */
        //  2459: sipush          7840
        //  2462: if_icmplt       2474
        //  2465: iload_0         /* c */
        //  2466: sipush          7929
        //  2469: if_icmpgt       2474
        //  2472: iconst_1       
        //  2473: ireturn        
        //  2474: iload_0         /* c */
        //  2475: sipush          7936
        //  2478: if_icmplt       2490
        //  2481: iload_0         /* c */
        //  2482: sipush          7957
        //  2485: if_icmpgt       2490
        //  2488: iconst_1       
        //  2489: ireturn        
        //  2490: iload_0         /* c */
        //  2491: sipush          7960
        //  2494: if_icmplt       2506
        //  2497: iload_0         /* c */
        //  2498: sipush          7965
        //  2501: if_icmpgt       2506
        //  2504: iconst_1       
        //  2505: ireturn        
        //  2506: iload_0         /* c */
        //  2507: sipush          7968
        //  2510: if_icmplt       2522
        //  2513: iload_0         /* c */
        //  2514: sipush          8005
        //  2517: if_icmpgt       2522
        //  2520: iconst_1       
        //  2521: ireturn        
        //  2522: iload_0         /* c */
        //  2523: sipush          8008
        //  2526: if_icmplt       2538
        //  2529: iload_0         /* c */
        //  2530: sipush          8013
        //  2533: if_icmpgt       2538
        //  2536: iconst_1       
        //  2537: ireturn        
        //  2538: iload_0         /* c */
        //  2539: sipush          8016
        //  2542: if_icmplt       2554
        //  2545: iload_0         /* c */
        //  2546: sipush          8023
        //  2549: if_icmpgt       2554
        //  2552: iconst_1       
        //  2553: ireturn        
        //  2554: iload_0         /* c */
        //  2555: sipush          8025
        //  2558: if_icmpne       2563
        //  2561: iconst_1       
        //  2562: ireturn        
        //  2563: iload_0         /* c */
        //  2564: sipush          8027
        //  2567: if_icmpne       2572
        //  2570: iconst_1       
        //  2571: ireturn        
        //  2572: iload_0         /* c */
        //  2573: sipush          8029
        //  2576: if_icmpne       2581
        //  2579: iconst_1       
        //  2580: ireturn        
        //  2581: iload_0         /* c */
        //  2582: sipush          8031
        //  2585: if_icmplt       2597
        //  2588: iload_0         /* c */
        //  2589: sipush          8061
        //  2592: if_icmpgt       2597
        //  2595: iconst_1       
        //  2596: ireturn        
        //  2597: iload_0         /* c */
        //  2598: sipush          8064
        //  2601: if_icmplt       2613
        //  2604: iload_0         /* c */
        //  2605: sipush          8116
        //  2608: if_icmpgt       2613
        //  2611: iconst_1       
        //  2612: ireturn        
        //  2613: iload_0         /* c */
        //  2614: sipush          8118
        //  2617: if_icmplt       2629
        //  2620: iload_0         /* c */
        //  2621: sipush          8124
        //  2624: if_icmpgt       2629
        //  2627: iconst_1       
        //  2628: ireturn        
        //  2629: iload_0         /* c */
        //  2630: sipush          8126
        //  2633: if_icmpne       2638
        //  2636: iconst_1       
        //  2637: ireturn        
        //  2638: iload_0         /* c */
        //  2639: sipush          8130
        //  2642: if_icmplt       2654
        //  2645: iload_0         /* c */
        //  2646: sipush          8132
        //  2649: if_icmpgt       2654
        //  2652: iconst_1       
        //  2653: ireturn        
        //  2654: iload_0         /* c */
        //  2655: sipush          8134
        //  2658: if_icmplt       2670
        //  2661: iload_0         /* c */
        //  2662: sipush          8140
        //  2665: if_icmpgt       2670
        //  2668: iconst_1       
        //  2669: ireturn        
        //  2670: iload_0         /* c */
        //  2671: sipush          8144
        //  2674: if_icmplt       2686
        //  2677: iload_0         /* c */
        //  2678: sipush          8147
        //  2681: if_icmpgt       2686
        //  2684: iconst_1       
        //  2685: ireturn        
        //  2686: iload_0         /* c */
        //  2687: sipush          8150
        //  2690: if_icmplt       2702
        //  2693: iload_0         /* c */
        //  2694: sipush          8155
        //  2697: if_icmpgt       2702
        //  2700: iconst_1       
        //  2701: ireturn        
        //  2702: iload_0         /* c */
        //  2703: sipush          8160
        //  2706: if_icmplt       2718
        //  2709: iload_0         /* c */
        //  2710: sipush          8172
        //  2713: if_icmpgt       2718
        //  2716: iconst_1       
        //  2717: ireturn        
        //  2718: iload_0         /* c */
        //  2719: sipush          8178
        //  2722: if_icmplt       2734
        //  2725: iload_0         /* c */
        //  2726: sipush          8180
        //  2729: if_icmpgt       2734
        //  2732: iconst_1       
        //  2733: ireturn        
        //  2734: iload_0         /* c */
        //  2735: sipush          8182
        //  2738: if_icmplt       2750
        //  2741: iload_0         /* c */
        //  2742: sipush          8188
        //  2745: if_icmpgt       2750
        //  2748: iconst_1       
        //  2749: ireturn        
        //  2750: iload_0         /* c */
        //  2751: sipush          8486
        //  2754: if_icmpne       2759
        //  2757: iconst_1       
        //  2758: ireturn        
        //  2759: iload_0         /* c */
        //  2760: sipush          8490
        //  2763: if_icmplt       2775
        //  2766: iload_0         /* c */
        //  2767: sipush          8491
        //  2770: if_icmpgt       2775
        //  2773: iconst_1       
        //  2774: ireturn        
        //  2775: iload_0         /* c */
        //  2776: sipush          8494
        //  2779: if_icmpne       2784
        //  2782: iconst_1       
        //  2783: ireturn        
        //  2784: iload_0         /* c */
        //  2785: sipush          8576
        //  2788: if_icmplt       2800
        //  2791: iload_0         /* c */
        //  2792: sipush          8578
        //  2795: if_icmpgt       2800
        //  2798: iconst_1       
        //  2799: ireturn        
        //  2800: iload_0         /* c */
        //  2801: sipush          12353
        //  2804: if_icmplt       2816
        //  2807: iload_0         /* c */
        //  2808: sipush          12436
        //  2811: if_icmpgt       2816
        //  2814: iconst_1       
        //  2815: ireturn        
        //  2816: iload_0         /* c */
        //  2817: sipush          12449
        //  2820: if_icmplt       2832
        //  2823: iload_0         /* c */
        //  2824: sipush          12538
        //  2827: if_icmpgt       2832
        //  2830: iconst_1       
        //  2831: ireturn        
        //  2832: iload_0         /* c */
        //  2833: sipush          12549
        //  2836: if_icmplt       2848
        //  2839: iload_0         /* c */
        //  2840: sipush          12588
        //  2843: if_icmpgt       2848
        //  2846: iconst_1       
        //  2847: ireturn        
        //  2848: iload_0         /* c */
        //  2849: ldc_w           44032
        //  2852: if_icmplt       2864
        //  2855: iload_0         /* c */
        //  2856: ldc_w           55203
        //  2859: if_icmpgt       2864
        //  2862: iconst_1       
        //  2863: ireturn        
        //  2864: iload_0         /* c */
        //  2865: sipush          19968
        //  2868: if_icmplt       2880
        //  2871: iload_0         /* c */
        //  2872: ldc_w           40869
        //  2875: if_icmpgt       2880
        //  2878: iconst_1       
        //  2879: ireturn        
        //  2880: iload_0         /* c */
        //  2881: sipush          12295
        //  2884: if_icmpne       2889
        //  2887: iconst_1       
        //  2888: ireturn        
        //  2889: iload_0         /* c */
        //  2890: sipush          12321
        //  2893: if_icmplt       2905
        //  2896: iload_0         /* c */
        //  2897: sipush          12329
        //  2900: if_icmpgt       2905
        //  2903: iconst_1       
        //  2904: ireturn        
        //  2905: iconst_0       
        //  2906: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------
        //  0      2907    0     c     C
        // 
        // The error that occurred was:
        // 
        // java.lang.StackOverflowError
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:839)
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
    
    private static boolean isXMLDigitOld(final char c) {
        return (c >= '0' && c <= '9') || (c >= '\u0660' && c <= '\u0669') || (c >= '\u06f0' && c <= '\u06f9') || (c >= '\u0966' && c <= '\u096f') || (c >= '\u09e6' && c <= '\u09ef') || (c >= '\u0a66' && c <= '\u0a6f') || (c >= '\u0ae6' && c <= '\u0aef') || (c >= '\u0b66' && c <= '\u0b6f') || (c >= '\u0be7' && c <= '\u0bef') || (c >= '\u0c66' && c <= '\u0c6f') || (c >= '\u0ce6' && c <= '\u0cef') || (c >= '\u0d66' && c <= '\u0d6f') || (c >= '\u0e50' && c <= '\u0e59') || (c >= '\u0ed0' && c <= '\u0ed9') || (c >= '\u0f20' && c <= '\u0f29');
    }
    
    private static boolean isXMLCombiningCharOld(final char c) {
        return (c >= '\u0300' && c <= '\u0345') || (c >= '\u0360' && c <= '\u0361') || (c >= '\u0483' && c <= '\u0486') || (c >= '\u0591' && c <= '\u05a1') || (c >= '\u05a3' && c <= '\u05b9') || (c >= '\u05bb' && c <= '\u05bd') || c == '\u05bf' || (c >= '\u05c1' && c <= '\u05c2') || c == '\u05c4' || (c >= '\u064b' && c <= '\u0652') || c == '\u0670' || (c >= '\u06d6' && c <= '\u06dc') || (c >= '\u06dd' && c <= '\u06df') || (c >= '\u06e0' && c <= '\u06e4') || (c >= '\u06e7' && c <= '\u06e8') || (c >= '\u06ea' && c <= '\u06ed') || (c >= '\u0901' && c <= '\u0903') || c == '\u093c' || (c >= '\u093e' && c <= '\u094c') || c == '\u094d' || (c >= '\u0951' && c <= '\u0954') || (c >= '\u0962' && c <= '\u0963') || (c >= '\u0981' && c <= '\u0983') || c == '\u09bc' || c == '\u09be' || c == '\u09bf' || (c >= '\u09c0' && c <= '\u09c4') || (c >= '\u09c7' && c <= '\u09c8') || (c >= '\u09cb' && c <= '\u09cd') || c == '\u09d7' || (c >= '\u09e2' && c <= '\u09e3') || c == '\u0a02' || c == '\u0a3c' || c == '\u0a3e' || c == '\u0a3f' || (c >= '\u0a40' && c <= '\u0a42') || (c >= '\u0a47' && c <= '\u0a48') || (c >= '\u0a4b' && c <= '\u0a4d') || (c >= '\u0a70' && c <= '\u0a71') || (c >= '\u0a81' && c <= '\u0a83') || c == '\u0abc' || (c >= '\u0abe' && c <= '\u0ac5') || (c >= '\u0ac7' && c <= '\u0ac9') || (c >= '\u0acb' && c <= '\u0acd') || (c >= '\u0b01' && c <= '\u0b03') || c == '\u0b3c' || (c >= '\u0b3e' && c <= '\u0b43') || (c >= '\u0b47' && c <= '\u0b48') || (c >= '\u0b4b' && c <= '\u0b4d') || (c >= '\u0b56' && c <= '\u0b57') || (c >= '\u0b82' && c <= '\u0b83') || (c >= '\u0bbe' && c <= '\u0bc2') || (c >= '\u0bc6' && c <= '\u0bc8') || (c >= '\u0bca' && c <= '\u0bcd') || c == '\u0bd7' || (c >= '\u0c01' && c <= '\u0c03') || (c >= '\u0c3e' && c <= '\u0c44') || (c >= '\u0c46' && c <= '\u0c48') || (c >= '\u0c4a' && c <= '\u0c4d') || (c >= '\u0c55' && c <= '\u0c56') || (c >= '\u0c82' && c <= '\u0c83') || (c >= '\u0cbe' && c <= '\u0cc4') || (c >= '\u0cc6' && c <= '\u0cc8') || (c >= '\u0cca' && c <= '\u0ccd') || (c >= '\u0cd5' && c <= '\u0cd6') || (c >= '\u0d02' && c <= '\u0d03') || (c >= '\u0d3e' && c <= '\u0d43') || (c >= '\u0d46' && c <= '\u0d48') || (c >= '\u0d4a' && c <= '\u0d4d') || c == '\u0d57' || c == '\u0e31' || (c >= '\u0e34' && c <= '\u0e3a') || (c >= '\u0e47' && c <= '\u0e4e') || c == '\u0eb1' || (c >= '\u0eb4' && c <= '\u0eb9') || (c >= '\u0ebb' && c <= '\u0ebc') || (c >= '\u0ec8' && c <= '\u0ecd') || (c >= '\u0f18' && c <= '\u0f19') || c == '\u0f35' || c == '\u0f37' || c == '\u0f39' || c == '\u0f3e' || c == '\u0f3f' || (c >= '\u0f71' && c <= '\u0f84') || (c >= '\u0f86' && c <= '\u0f8b') || (c >= '\u0f90' && c <= '\u0f95') || c == '\u0f97' || (c >= '\u0f99' && c <= '\u0fad') || (c >= '\u0fb1' && c <= '\u0fb7') || c == '\u0fb9' || (c >= '\u20d0' && c <= '\u20dc') || c == '\u20e1' || (c >= '\u302a' && c <= '\u302f') || c == '\u3099' || c == '\u309a';
    }
    
    private static boolean isXMLExtenderOld(final char c) {
        return c == '·' || c == '\u02d0' || c == '\u02d1' || c == '\u0387' || c == '\u0640' || c == '\u0e46' || c == '\u0ec6' || c == '\u3005' || (c >= '\u3031' && c <= '\u3035') || (c >= '\u309d' && c <= '\u309e') || (c >= '\u30fc' && c <= '\u30fe');
    }
    
    private static boolean isXMLCharacterOld(final char c) {
        return (c >= ' ' && c <= '\ud7ff') || (c >= '\ue000' && c <= '\ufffd') || (c >= 65536 && c <= 1114111) || c == '\n' || c == '\r' || c == '\t';
    }
    
    public static void main(final String[] args) {
        for (int i = 0; i < 65536; ++i) {
            if (isXMLLetter((char)i) != isXMLLetterOld((char)i)) {
                System.out.println("isXMLLetter mismatch: " + i + " hex: " + Integer.toHexString(i));
            }
        }
        for (int i = 0; i < 65536; ++i) {
            if (isXMLDigit((char)i) != isXMLDigitOld((char)i)) {
                System.out.println("isXMLDigit mismatch: " + i + " hex: " + Integer.toHexString(i));
            }
        }
        for (int i = 0; i < 65536; ++i) {
            if (isXMLCombiningChar((char)i) != isXMLCombiningCharOld((char)i)) {
                System.out.println("isXMLCombiningChar mismatch: " + i + " hex: " + Integer.toHexString(i));
            }
        }
        for (int i = 0; i < 65536; ++i) {
            if (isXMLExtender((char)i) != isXMLExtenderOld((char)i)) {
                System.out.println("isXMLExtender mismatch: " + i + " hex: " + Integer.toHexString(i));
            }
        }
        for (int i = 0; i < 65536; ++i) {
            if (isXMLCharacter((char)i) != isXMLCharacterOld((char)i)) {
                System.out.println("isXMLCharacter mismatch: " + i + " hex: " + Integer.toHexString(i));
            }
        }
    }
}

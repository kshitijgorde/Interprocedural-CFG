// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath.regex;

import java.util.Locale;
import java.util.Hashtable;

class ParserForXMLSchema extends RegexParser
{
    private static Hashtable ranges;
    private static Hashtable ranges2;
    private static final String SPACES = "\t\n\r\r  ";
    private static final String NAMECHARS = "-.0:AZ__az··\u00c0\u00d6\u00d8\u00f6\u00f8\u0131\u0134\u013e\u0141\u0148\u014a\u017e\u0180\u01c3\u01cd\u01f0\u01f4\u01f5\u01fa\u0217\u0250\u02a8\u02bb\u02c1\u02d0\u02d1\u0300\u0345\u0360\u0361\u0386\u038a\u038c\u038c\u038e\u03a1\u03a3\u03ce\u03d0\u03d6\u03da\u03da\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03f3\u0401\u040c\u040e\u044f\u0451\u045c\u045e\u0481\u0483\u0486\u0490\u04c4\u04c7\u04c8\u04cb\u04cc\u04d0\u04eb\u04ee\u04f5\u04f8\u04f9\u0531\u0556\u0559\u0559\u0561\u0586\u0591\u05a1\u05a3\u05b9\u05bb\u05bd\u05bf\u05bf\u05c1\u05c2\u05c4\u05c4\u05d0\u05ea\u05f0\u05f2\u0621\u063a\u0640\u0652\u0660\u0669\u0670\u06b7\u06ba\u06be\u06c0\u06ce\u06d0\u06d3\u06d5\u06e8\u06ea\u06ed\u06f0\u06f9\u0901\u0903\u0905\u0939\u093c\u094d\u0951\u0954\u0958\u0963\u0966\u096f\u0981\u0983\u0985\u098c\u098f\u0990\u0993\u09a8\u09aa\u09b0\u09b2\u09b2\u09b6\u09b9\u09bc\u09bc\u09be\u09c4\u09c7\u09c8\u09cb\u09cd\u09d7\u09d7\u09dc\u09dd\u09df\u09e3\u09e6\u09f1\u0a02\u0a02\u0a05\u0a0a\u0a0f\u0a10\u0a13\u0a28\u0a2a\u0a30\u0a32\u0a33\u0a35\u0a36\u0a38\u0a39\u0a3c\u0a3c\u0a3e\u0a42\u0a47\u0a48\u0a4b\u0a4d\u0a59\u0a5c\u0a5e\u0a5e\u0a66\u0a74\u0a81\u0a83\u0a85\u0a8b\u0a8d\u0a8d\u0a8f\u0a91\u0a93\u0aa8\u0aaa\u0ab0\u0ab2\u0ab3\u0ab5\u0ab9\u0abc\u0ac5\u0ac7\u0ac9\u0acb\u0acd\u0ae0\u0ae0\u0ae6\u0aef\u0b01\u0b03\u0b05\u0b0c\u0b0f\u0b10\u0b13\u0b28\u0b2a\u0b30\u0b32\u0b33\u0b36\u0b39\u0b3c\u0b43\u0b47\u0b48\u0b4b\u0b4d\u0b56\u0b57\u0b5c\u0b5d\u0b5f\u0b61\u0b66\u0b6f\u0b82\u0b83\u0b85\u0b8a\u0b8e\u0b90\u0b92\u0b95\u0b99\u0b9a\u0b9c\u0b9c\u0b9e\u0b9f\u0ba3\u0ba4\u0ba8\u0baa\u0bae\u0bb5\u0bb7\u0bb9\u0bbe\u0bc2\u0bc6\u0bc8\u0bca\u0bcd\u0bd7\u0bd7\u0be7\u0bef\u0c01\u0c03\u0c05\u0c0c\u0c0e\u0c10\u0c12\u0c28\u0c2a\u0c33\u0c35\u0c39\u0c3e\u0c44\u0c46\u0c48\u0c4a\u0c4d\u0c55\u0c56\u0c60\u0c61\u0c66\u0c6f\u0c82\u0c83\u0c85\u0c8c\u0c8e\u0c90\u0c92\u0ca8\u0caa\u0cb3\u0cb5\u0cb9\u0cbe\u0cc4\u0cc6\u0cc8\u0cca\u0ccd\u0cd5\u0cd6\u0cde\u0cde\u0ce0\u0ce1\u0ce6\u0cef\u0d02\u0d03\u0d05\u0d0c\u0d0e\u0d10\u0d12\u0d28\u0d2a\u0d39\u0d3e\u0d43\u0d46\u0d48\u0d4a\u0d4d\u0d57\u0d57\u0d60\u0d61\u0d66\u0d6f\u0e01\u0e2e\u0e30\u0e3a\u0e40\u0e4e\u0e50\u0e59\u0e81\u0e82\u0e84\u0e84\u0e87\u0e88\u0e8a\u0e8a\u0e8d\u0e8d\u0e94\u0e97\u0e99\u0e9f\u0ea1\u0ea3\u0ea5\u0ea5\u0ea7\u0ea7\u0eaa\u0eab\u0ead\u0eae\u0eb0\u0eb9\u0ebb\u0ebd\u0ec0\u0ec4\u0ec6\u0ec6\u0ec8\u0ecd\u0ed0\u0ed9\u0f18\u0f19\u0f20\u0f29\u0f35\u0f35\u0f37\u0f37\u0f39\u0f39\u0f3e\u0f47\u0f49\u0f69\u0f71\u0f84\u0f86\u0f8b\u0f90\u0f95\u0f97\u0f97\u0f99\u0fad\u0fb1\u0fb7\u0fb9\u0fb9\u10a0\u10c5\u10d0\u10f6\u1100\u1100\u1102\u1103\u1105\u1107\u1109\u1109\u110b\u110c\u110e\u1112\u113c\u113c\u113e\u113e\u1140\u1140\u114c\u114c\u114e\u114e\u1150\u1150\u1154\u1155\u1159\u1159\u115f\u1161\u1163\u1163\u1165\u1165\u1167\u1167\u1169\u1169\u116d\u116e\u1172\u1173\u1175\u1175\u119e\u119e\u11a8\u11a8\u11ab\u11ab\u11ae\u11af\u11b7\u11b8\u11ba\u11ba\u11bc\u11c2\u11eb\u11eb\u11f0\u11f0\u11f9\u11f9\u1e00\u1e9b\u1ea0\u1ef9\u1f00\u1f15\u1f18\u1f1d\u1f20\u1f45\u1f48\u1f4d\u1f50\u1f57\u1f59\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f\u1f7d\u1f80\u1fb4\u1fb6\u1fbc\u1fbe\u1fbe\u1fc2\u1fc4\u1fc6\u1fcc\u1fd0\u1fd3\u1fd6\u1fdb\u1fe0\u1fec\u1ff2\u1ff4\u1ff6\u1ffc\u20d0\u20dc\u20e1\u20e1\u2126\u2126\u212a\u212b\u212e\u212e\u2180\u2182\u3005\u3005\u3007\u3007\u3021\u302f\u3031\u3035\u3041\u3094\u3099\u309a\u309d\u309e\u30a1\u30fa\u30fc\u30fe\u3105\u312c\u4e00\u9fa5\uac00\ud7a3";
    private static final String LETTERS = "AZaz\u00c0\u00d6\u00d8\u00f6\u00f8\u0131\u0134\u013e\u0141\u0148\u014a\u017e\u0180\u01c3\u01cd\u01f0\u01f4\u01f5\u01fa\u0217\u0250\u02a8\u02bb\u02c1\u0386\u0386\u0388\u038a\u038c\u038c\u038e\u03a1\u03a3\u03ce\u03d0\u03d6\u03da\u03da\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03f3\u0401\u040c\u040e\u044f\u0451\u045c\u045e\u0481\u0490\u04c4\u04c7\u04c8\u04cb\u04cc\u04d0\u04eb\u04ee\u04f5\u04f8\u04f9\u0531\u0556\u0559\u0559\u0561\u0586\u05d0\u05ea\u05f0\u05f2\u0621\u063a\u0641\u064a\u0671\u06b7\u06ba\u06be\u06c0\u06ce\u06d0\u06d3\u06d5\u06d5\u06e5\u06e6\u0905\u0939\u093d\u093d\u0958\u0961\u0985\u098c\u098f\u0990\u0993\u09a8\u09aa\u09b0\u09b2\u09b2\u09b6\u09b9\u09dc\u09dd\u09df\u09e1\u09f0\u09f1\u0a05\u0a0a\u0a0f\u0a10\u0a13\u0a28\u0a2a\u0a30\u0a32\u0a33\u0a35\u0a36\u0a38\u0a39\u0a59\u0a5c\u0a5e\u0a5e\u0a72\u0a74\u0a85\u0a8b\u0a8d\u0a8d\u0a8f\u0a91\u0a93\u0aa8\u0aaa\u0ab0\u0ab2\u0ab3\u0ab5\u0ab9\u0abd\u0abd\u0ae0\u0ae0\u0b05\u0b0c\u0b0f\u0b10\u0b13\u0b28\u0b2a\u0b30\u0b32\u0b33\u0b36\u0b39\u0b3d\u0b3d\u0b5c\u0b5d\u0b5f\u0b61\u0b85\u0b8a\u0b8e\u0b90\u0b92\u0b95\u0b99\u0b9a\u0b9c\u0b9c\u0b9e\u0b9f\u0ba3\u0ba4\u0ba8\u0baa\u0bae\u0bb5\u0bb7\u0bb9\u0c05\u0c0c\u0c0e\u0c10\u0c12\u0c28\u0c2a\u0c33\u0c35\u0c39\u0c60\u0c61\u0c85\u0c8c\u0c8e\u0c90\u0c92\u0ca8\u0caa\u0cb3\u0cb5\u0cb9\u0cde\u0cde\u0ce0\u0ce1\u0d05\u0d0c\u0d0e\u0d10\u0d12\u0d28\u0d2a\u0d39\u0d60\u0d61\u0e01\u0e2e\u0e30\u0e30\u0e32\u0e33\u0e40\u0e45\u0e81\u0e82\u0e84\u0e84\u0e87\u0e88\u0e8a\u0e8a\u0e8d\u0e8d\u0e94\u0e97\u0e99\u0e9f\u0ea1\u0ea3\u0ea5\u0ea5\u0ea7\u0ea7\u0eaa\u0eab\u0ead\u0eae\u0eb0\u0eb0\u0eb2\u0eb3\u0ebd\u0ebd\u0ec0\u0ec4\u0f40\u0f47\u0f49\u0f69\u10a0\u10c5\u10d0\u10f6\u1100\u1100\u1102\u1103\u1105\u1107\u1109\u1109\u110b\u110c\u110e\u1112\u113c\u113c\u113e\u113e\u1140\u1140\u114c\u114c\u114e\u114e\u1150\u1150\u1154\u1155\u1159\u1159\u115f\u1161\u1163\u1163\u1165\u1165\u1167\u1167\u1169\u1169\u116d\u116e\u1172\u1173\u1175\u1175\u119e\u119e\u11a8\u11a8\u11ab\u11ab\u11ae\u11af\u11b7\u11b8\u11ba\u11ba\u11bc\u11c2\u11eb\u11eb\u11f0\u11f0\u11f9\u11f9\u1e00\u1e9b\u1ea0\u1ef9\u1f00\u1f15\u1f18\u1f1d\u1f20\u1f45\u1f48\u1f4d\u1f50\u1f57\u1f59\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f\u1f7d\u1f80\u1fb4\u1fb6\u1fbc\u1fbe\u1fbe\u1fc2\u1fc4\u1fc6\u1fcc\u1fd0\u1fd3\u1fd6\u1fdb\u1fe0\u1fec\u1ff2\u1ff4\u1ff6\u1ffc\u2126\u2126\u212a\u212b\u212e\u212e\u2180\u2182\u3007\u3007\u3021\u3029\u3041\u3094\u30a1\u30fa\u3105\u312c\u4e00\u9fa5\uac00\ud7a3";
    private static final String DIGITS = "09\u0660\u0669\u06f0\u06f9\u0966\u096f\u09e6\u09ef\u0a66\u0a6f\u0ae6\u0aef\u0b66\u0b6f\u0be7\u0bef\u0c66\u0c6f\u0ce6\u0cef\u0d66\u0d6f\u0e50\u0e59\u0ed0\u0ed9\u0f20\u0f29";
    
    public ParserForXMLSchema() {
    }
    
    public ParserForXMLSchema(final Locale locale) {
    }
    
    Token processCaret() throws ParseException {
        this.next();
        return Token.createChar(94);
    }
    
    Token processDollar() throws ParseException {
        this.next();
        return Token.createChar(36);
    }
    
    Token processLookahead() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processNegativelookahead() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processLookbehind() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processNegativelookbehind() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_A() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_Z() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_z() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_b() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_B() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_lt() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_gt() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processStar(final Token tok) throws ParseException {
        this.next();
        return Token.createClosure(tok);
    }
    
    Token processPlus(final Token tok) throws ParseException {
        this.next();
        return Token.createConcat(tok, Token.createClosure(tok));
    }
    
    Token processQuestion(final Token tok) throws ParseException {
        this.next();
        final Token par = Token.createUnion();
        par.addChild(tok);
        par.addChild(Token.createEmpty());
        return par;
    }
    
    boolean checkQuestion(final int off) {
        return false;
    }
    
    Token processParen() throws ParseException {
        this.next();
        final Token tok = Token.createParen(this.parseRegex(), 0);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", super.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processParen2() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processCondition() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processModifiers() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processIndependent() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token processBacksolidus_c() throws ParseException {
        this.next();
        return this.getTokenForShorthand(99);
    }
    
    Token processBacksolidus_C() throws ParseException {
        this.next();
        return this.getTokenForShorthand(67);
    }
    
    Token processBacksolidus_i() throws ParseException {
        this.next();
        return this.getTokenForShorthand(105);
    }
    
    Token processBacksolidus_I() throws ParseException {
        this.next();
        return this.getTokenForShorthand(73);
    }
    
    Token processBacksolidus_g() throws ParseException {
        throw this.ex("parser.process.1", super.offset - 2);
    }
    
    Token processBacksolidus_X() throws ParseException {
        throw this.ex("parser.process.1", super.offset - 2);
    }
    
    Token processBackreference() throws ParseException {
        throw this.ex("parser.process.1", super.offset - 4);
    }
    
    int processCIinCharacterClass(final RangeToken tok, final int c) {
        tok.mergeRanges(this.getTokenForShorthand(c));
        return -1;
    }
    
    protected RangeToken parseCharacterClass(final boolean useNrange) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: iconst_1       
        //     2: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.setContext:(I)V
        //     5: aload_0         /* this */
        //     6: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //     9: iconst_0       
        //    10: istore_2        /* nrange */
        //    11: aconst_null    
        //    12: astore_3        /* base */
        //    13: aload_0         /* this */
        //    14: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //    17: ifne            54
        //    20: aload_0         /* this */
        //    21: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //    24: bipush          94
        //    26: if_icmpne       54
        //    29: iconst_1       
        //    30: istore_2        /* nrange */
        //    31: aload_0         /* this */
        //    32: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //    35: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    38: astore_3        /* base */
        //    39: aload_3         /* base */
        //    40: iconst_0       
        //    41: ldc             1114111
        //    43: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //    46: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    49: astore          tok
        //    51: goto            59
        //    54: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    57: astore          tok
        //    59: iconst_1       
        //    60: istore          firstloop
        //    62: goto            629
        //    65: iload           5
        //    67: ifne            100
        //    70: aload_0         /* this */
        //    71: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //    74: bipush          93
        //    76: if_icmpne       100
        //    79: iload           firstloop
        //    81: ifne            100
        //    84: iload_2         /* nrange */
        //    85: ifeq            640
        //    88: aload_3         /* base */
        //    89: aload           tok
        //    91: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.subtractRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //    94: aload_3         /* base */
        //    95: astore          tok
        //    97: goto            640
        //   100: aload_0         /* this */
        //   101: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   104: istore          c
        //   106: iconst_0       
        //   107: istore          end
        //   109: iload           5
        //   111: bipush          10
        //   113: if_icmpne       312
        //   116: iload           c
        //   118: lookupswitch {
        //               67: 241
        //               68: 224
        //               73: 241
        //               80: 262
        //               83: 224
        //               87: 224
        //               99: 241
        //              100: 224
        //              105: 241
        //              112: 262
        //              115: 224
        //              119: 224
        //          default: 303
        //        }
        //   224: aload           tok
        //   226: aload_0         /* this */
        //   227: iload           c
        //   229: invokevirtual   org/apache/xerces/impl/xpath/regex/ParserForXMLSchema.getTokenForShorthand:(I)Lorg/apache/xerces/impl/xpath/regex/Token;
        //   232: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   235: iconst_1       
        //   236: istore          end
        //   238: goto            309
        //   241: aload_0         /* this */
        //   242: aload           tok
        //   244: iload           c
        //   246: invokevirtual   org/apache/xerces/impl/xpath/regex/ParserForXMLSchema.processCIinCharacterClass:(Lorg/apache/xerces/impl/xpath/regex/RangeToken;I)I
        //   249: istore          c
        //   251: iload           c
        //   253: ifge            309
        //   256: iconst_1       
        //   257: istore          end
        //   259: goto            309
        //   262: aload_0         /* this */
        //   263: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   266: istore          pstart
        //   268: aload_0         /* this */
        //   269: iload           c
        //   271: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.processBacksolidus_pP:(I)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //   274: astore          tok2
        //   276: aload           tok2
        //   278: ifnonnull       290
        //   281: aload_0         /* this */
        //   282: ldc             "parser.atom.5"
        //   284: iload           pstart
        //   286: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   289: athrow         
        //   290: aload           tok
        //   292: aload           tok2
        //   294: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   297: iconst_1       
        //   298: istore          end
        //   300: goto            309
        //   303: aload_0         /* this */
        //   304: invokevirtual   org/apache/xerces/impl/xpath/regex/ParserForXMLSchema.decodeEscaped:()I
        //   307: istore          c
        //   309: goto            378
        //   312: iload           5
        //   314: bipush          24
        //   316: if_icmpne       378
        //   319: iload           firstloop
        //   321: ifne            378
        //   324: iload_2         /* nrange */
        //   325: ifeq            337
        //   328: aload_3         /* base */
        //   329: aload           tok
        //   331: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.subtractRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   334: aload_3         /* base */
        //   335: astore          tok
        //   337: aload_0         /* this */
        //   338: iconst_0       
        //   339: invokevirtual   org/apache/xerces/impl/xpath/regex/ParserForXMLSchema.parseCharacterClass:(Z)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //   342: astore          range2
        //   344: aload           tok
        //   346: aload           range2
        //   348: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.subtractRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   351: aload_0         /* this */
        //   352: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   355: ifne            367
        //   358: aload_0         /* this */
        //   359: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   362: bipush          93
        //   364: if_icmpeq       640
        //   367: aload_0         /* this */
        //   368: ldc             "parser.cc.5"
        //   370: aload_0         /* this */
        //   371: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   374: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   377: athrow         
        //   378: aload_0         /* this */
        //   379: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   382: iload           end
        //   384: ifne            626
        //   387: iload           5
        //   389: ifne            432
        //   392: iload           c
        //   394: bipush          91
        //   396: if_icmpne       412
        //   399: aload_0         /* this */
        //   400: ldc             "parser.cc.6"
        //   402: aload_0         /* this */
        //   403: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   406: iconst_2       
        //   407: isub           
        //   408: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   411: athrow         
        //   412: iload           c
        //   414: bipush          93
        //   416: if_icmpne       432
        //   419: aload_0         /* this */
        //   420: ldc             "parser.cc.7"
        //   422: aload_0         /* this */
        //   423: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   426: iconst_2       
        //   427: isub           
        //   428: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   431: athrow         
        //   432: aload_0         /* this */
        //   433: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   436: ifne            448
        //   439: aload_0         /* this */
        //   440: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   443: bipush          45
        //   445: if_icmpeq       460
        //   448: aload           tok
        //   450: iload           c
        //   452: iload           c
        //   454: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   457: goto            626
        //   460: aload_0         /* this */
        //   461: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   464: aload_0         /* this */
        //   465: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   468: dup            
        //   469: istore          type
        //   471: iconst_1       
        //   472: if_icmpne       486
        //   475: aload_0         /* this */
        //   476: ldc             "parser.cc.2"
        //   478: aload_0         /* this */
        //   479: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   482: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   485: athrow         
        //   486: iload           type
        //   488: ifne            521
        //   491: aload_0         /* this */
        //   492: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   495: bipush          93
        //   497: if_icmpne       521
        //   500: aload           tok
        //   502: iload           c
        //   504: iload           c
        //   506: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   509: aload           tok
        //   511: bipush          45
        //   513: bipush          45
        //   515: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   518: goto            626
        //   521: iload           type
        //   523: bipush          24
        //   525: if_icmpne       549
        //   528: aload           tok
        //   530: iload           c
        //   532: iload           c
        //   534: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   537: aload           tok
        //   539: bipush          45
        //   541: bipush          45
        //   543: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   546: goto            626
        //   549: aload_0         /* this */
        //   550: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   553: istore          rangeend
        //   555: iload           type
        //   557: ifne            600
        //   560: iload           rangeend
        //   562: bipush          91
        //   564: if_icmpne       580
        //   567: aload_0         /* this */
        //   568: ldc             "parser.cc.6"
        //   570: aload_0         /* this */
        //   571: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   574: iconst_1       
        //   575: isub           
        //   576: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   579: athrow         
        //   580: iload           rangeend
        //   582: bipush          93
        //   584: if_icmpne       600
        //   587: aload_0         /* this */
        //   588: ldc             "parser.cc.7"
        //   590: aload_0         /* this */
        //   591: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   594: iconst_1       
        //   595: isub           
        //   596: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   599: athrow         
        //   600: iload           type
        //   602: bipush          10
        //   604: if_icmpne       613
        //   607: aload_0         /* this */
        //   608: invokevirtual   org/apache/xerces/impl/xpath/regex/ParserForXMLSchema.decodeEscaped:()I
        //   611: istore          rangeend
        //   613: aload_0         /* this */
        //   614: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   617: aload           tok
        //   619: iload           c
        //   621: iload           rangeend
        //   623: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   626: iconst_0       
        //   627: istore          firstloop
        //   629: aload_0         /* this */
        //   630: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   633: dup            
        //   634: istore          type
        //   636: iconst_1       
        //   637: if_icmpne       65
        //   640: aload_0         /* this */
        //   641: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   644: iconst_1       
        //   645: if_icmpne       659
        //   648: aload_0         /* this */
        //   649: ldc             "parser.cc.2"
        //   651: aload_0         /* this */
        //   652: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   655: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   658: athrow         
        //   659: aload           tok
        //   661: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.sortRanges:()V
        //   664: aload           tok
        //   666: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.compactRanges:()V
        //   669: aload_0         /* this */
        //   670: iconst_0       
        //   671: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.setContext:(I)V
        //   674: aload_0         /* this */
        //   675: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   678: aload           tok
        //   680: areturn        
        //    Exceptions:
        //  throws org.apache.xerces.impl.xpath.regex.ParseException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name       Signature
        //  -----  ------  ----  ---------  -------------------------------------------------------
        //  0      681     0     this       Lorg/apache/xerces/impl/xpath/regex/ParserForXMLSchema;
        //  0      681     1     useNrange  Z
        //  11     670     2     nrange     Z
        //  13     668     3     base       Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  51     630     4     tok        Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  471    210     5     type       I
        //  62     619     6     firstloop  Z
        //  106    523     7     c          I
        //  109    520     8     end        Z
        //  268    41      9     pstart     I
        //  276    33      10    tok2       Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  344    34      9     range2     Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  555    71      9     rangeend   I
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
    
    protected RangeToken parseSetOperations() throws ParseException {
        throw this.ex("parser.process.1", super.offset);
    }
    
    Token getTokenForShorthand(final int ch) {
        switch (ch) {
            case 100: {
                return getRange("xml:isDigit", true);
            }
            case 68: {
                return getRange("xml:isDigit", false);
            }
            case 119: {
                return getRange("xml:isWord", true);
            }
            case 87: {
                return getRange("xml:isWord", false);
            }
            case 115: {
                return getRange("xml:isSpace", true);
            }
            case 83: {
                return getRange("xml:isSpace", false);
            }
            case 99: {
                return getRange("xml:isNameChar", true);
            }
            case 67: {
                return getRange("xml:isNameChar", false);
            }
            case 105: {
                return getRange("xml:isInitialNameChar", true);
            }
            case 73: {
                return getRange("xml:isInitialNameChar", false);
            }
            default: {
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
            }
        }
    }
    
    int decodeEscaped() throws ParseException {
        if (this.read() != 10) {
            throw this.ex("parser.next.1", super.offset - 1);
        }
        int c = super.chardata;
        switch (c) {
            case 110: {
                c = 10;
                break;
            }
            case 114: {
                c = 13;
                break;
            }
            case 116: {
                c = 9;
                break;
            }
            case 101:
            case 102:
            case 117:
            case 118:
            case 120: {
                throw this.ex("parser.process.1", super.offset - 2);
            }
            case 65:
            case 90:
            case 122: {
                throw this.ex("parser.descape.5", super.offset - 2);
            }
        }
        return c;
    }
    
    protected static synchronized RangeToken getRange(final String name, final boolean positive) {
        if (ParserForXMLSchema.ranges == null) {
            ParserForXMLSchema.ranges = new Hashtable();
            ParserForXMLSchema.ranges2 = new Hashtable();
            Token tok = Token.createRange();
            setupRange(tok, "\t\n\r\r  ");
            ParserForXMLSchema.ranges.put("xml:isSpace", tok);
            ParserForXMLSchema.ranges2.put("xml:isSpace", Token.complementRanges(tok));
            tok = Token.createRange();
            setupRange(tok, "09\u0660\u0669\u06f0\u06f9\u0966\u096f\u09e6\u09ef\u0a66\u0a6f\u0ae6\u0aef\u0b66\u0b6f\u0be7\u0bef\u0c66\u0c6f\u0ce6\u0cef\u0d66\u0d6f\u0e50\u0e59\u0ed0\u0ed9\u0f20\u0f29");
            ParserForXMLSchema.ranges.put("xml:isDigit", tok);
            ParserForXMLSchema.ranges2.put("xml:isDigit", Token.complementRanges(tok));
            tok = Token.createRange();
            setupRange(tok, "09\u0660\u0669\u06f0\u06f9\u0966\u096f\u09e6\u09ef\u0a66\u0a6f\u0ae6\u0aef\u0b66\u0b6f\u0be7\u0bef\u0c66\u0c6f\u0ce6\u0cef\u0d66\u0d6f\u0e50\u0e59\u0ed0\u0ed9\u0f20\u0f29");
            ParserForXMLSchema.ranges.put("xml:isDigit", tok);
            ParserForXMLSchema.ranges2.put("xml:isDigit", Token.complementRanges(tok));
            tok = Token.createRange();
            setupRange(tok, "AZaz\u00c0\u00d6\u00d8\u00f6\u00f8\u0131\u0134\u013e\u0141\u0148\u014a\u017e\u0180\u01c3\u01cd\u01f0\u01f4\u01f5\u01fa\u0217\u0250\u02a8\u02bb\u02c1\u0386\u0386\u0388\u038a\u038c\u038c\u038e\u03a1\u03a3\u03ce\u03d0\u03d6\u03da\u03da\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03f3\u0401\u040c\u040e\u044f\u0451\u045c\u045e\u0481\u0490\u04c4\u04c7\u04c8\u04cb\u04cc\u04d0\u04eb\u04ee\u04f5\u04f8\u04f9\u0531\u0556\u0559\u0559\u0561\u0586\u05d0\u05ea\u05f0\u05f2\u0621\u063a\u0641\u064a\u0671\u06b7\u06ba\u06be\u06c0\u06ce\u06d0\u06d3\u06d5\u06d5\u06e5\u06e6\u0905\u0939\u093d\u093d\u0958\u0961\u0985\u098c\u098f\u0990\u0993\u09a8\u09aa\u09b0\u09b2\u09b2\u09b6\u09b9\u09dc\u09dd\u09df\u09e1\u09f0\u09f1\u0a05\u0a0a\u0a0f\u0a10\u0a13\u0a28\u0a2a\u0a30\u0a32\u0a33\u0a35\u0a36\u0a38\u0a39\u0a59\u0a5c\u0a5e\u0a5e\u0a72\u0a74\u0a85\u0a8b\u0a8d\u0a8d\u0a8f\u0a91\u0a93\u0aa8\u0aaa\u0ab0\u0ab2\u0ab3\u0ab5\u0ab9\u0abd\u0abd\u0ae0\u0ae0\u0b05\u0b0c\u0b0f\u0b10\u0b13\u0b28\u0b2a\u0b30\u0b32\u0b33\u0b36\u0b39\u0b3d\u0b3d\u0b5c\u0b5d\u0b5f\u0b61\u0b85\u0b8a\u0b8e\u0b90\u0b92\u0b95\u0b99\u0b9a\u0b9c\u0b9c\u0b9e\u0b9f\u0ba3\u0ba4\u0ba8\u0baa\u0bae\u0bb5\u0bb7\u0bb9\u0c05\u0c0c\u0c0e\u0c10\u0c12\u0c28\u0c2a\u0c33\u0c35\u0c39\u0c60\u0c61\u0c85\u0c8c\u0c8e\u0c90\u0c92\u0ca8\u0caa\u0cb3\u0cb5\u0cb9\u0cde\u0cde\u0ce0\u0ce1\u0d05\u0d0c\u0d0e\u0d10\u0d12\u0d28\u0d2a\u0d39\u0d60\u0d61\u0e01\u0e2e\u0e30\u0e30\u0e32\u0e33\u0e40\u0e45\u0e81\u0e82\u0e84\u0e84\u0e87\u0e88\u0e8a\u0e8a\u0e8d\u0e8d\u0e94\u0e97\u0e99\u0e9f\u0ea1\u0ea3\u0ea5\u0ea5\u0ea7\u0ea7\u0eaa\u0eab\u0ead\u0eae\u0eb0\u0eb0\u0eb2\u0eb3\u0ebd\u0ebd\u0ec0\u0ec4\u0f40\u0f47\u0f49\u0f69\u10a0\u10c5\u10d0\u10f6\u1100\u1100\u1102\u1103\u1105\u1107\u1109\u1109\u110b\u110c\u110e\u1112\u113c\u113c\u113e\u113e\u1140\u1140\u114c\u114c\u114e\u114e\u1150\u1150\u1154\u1155\u1159\u1159\u115f\u1161\u1163\u1163\u1165\u1165\u1167\u1167\u1169\u1169\u116d\u116e\u1172\u1173\u1175\u1175\u119e\u119e\u11a8\u11a8\u11ab\u11ab\u11ae\u11af\u11b7\u11b8\u11ba\u11ba\u11bc\u11c2\u11eb\u11eb\u11f0\u11f0\u11f9\u11f9\u1e00\u1e9b\u1ea0\u1ef9\u1f00\u1f15\u1f18\u1f1d\u1f20\u1f45\u1f48\u1f4d\u1f50\u1f57\u1f59\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f\u1f7d\u1f80\u1fb4\u1fb6\u1fbc\u1fbe\u1fbe\u1fc2\u1fc4\u1fc6\u1fcc\u1fd0\u1fd3\u1fd6\u1fdb\u1fe0\u1fec\u1ff2\u1ff4\u1ff6\u1ffc\u2126\u2126\u212a\u212b\u212e\u212e\u2180\u2182\u3007\u3007\u3021\u3029\u3041\u3094\u30a1\u30fa\u3105\u312c\u4e00\u9fa5\uac00\ud7a3");
            tok.mergeRanges(ParserForXMLSchema.ranges.get("xml:isDigit"));
            ParserForXMLSchema.ranges.put("xml:isWord", tok);
            ParserForXMLSchema.ranges2.put("xml:isWord", Token.complementRanges(tok));
            tok = Token.createRange();
            setupRange(tok, "-.0:AZ__az··\u00c0\u00d6\u00d8\u00f6\u00f8\u0131\u0134\u013e\u0141\u0148\u014a\u017e\u0180\u01c3\u01cd\u01f0\u01f4\u01f5\u01fa\u0217\u0250\u02a8\u02bb\u02c1\u02d0\u02d1\u0300\u0345\u0360\u0361\u0386\u038a\u038c\u038c\u038e\u03a1\u03a3\u03ce\u03d0\u03d6\u03da\u03da\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03f3\u0401\u040c\u040e\u044f\u0451\u045c\u045e\u0481\u0483\u0486\u0490\u04c4\u04c7\u04c8\u04cb\u04cc\u04d0\u04eb\u04ee\u04f5\u04f8\u04f9\u0531\u0556\u0559\u0559\u0561\u0586\u0591\u05a1\u05a3\u05b9\u05bb\u05bd\u05bf\u05bf\u05c1\u05c2\u05c4\u05c4\u05d0\u05ea\u05f0\u05f2\u0621\u063a\u0640\u0652\u0660\u0669\u0670\u06b7\u06ba\u06be\u06c0\u06ce\u06d0\u06d3\u06d5\u06e8\u06ea\u06ed\u06f0\u06f9\u0901\u0903\u0905\u0939\u093c\u094d\u0951\u0954\u0958\u0963\u0966\u096f\u0981\u0983\u0985\u098c\u098f\u0990\u0993\u09a8\u09aa\u09b0\u09b2\u09b2\u09b6\u09b9\u09bc\u09bc\u09be\u09c4\u09c7\u09c8\u09cb\u09cd\u09d7\u09d7\u09dc\u09dd\u09df\u09e3\u09e6\u09f1\u0a02\u0a02\u0a05\u0a0a\u0a0f\u0a10\u0a13\u0a28\u0a2a\u0a30\u0a32\u0a33\u0a35\u0a36\u0a38\u0a39\u0a3c\u0a3c\u0a3e\u0a42\u0a47\u0a48\u0a4b\u0a4d\u0a59\u0a5c\u0a5e\u0a5e\u0a66\u0a74\u0a81\u0a83\u0a85\u0a8b\u0a8d\u0a8d\u0a8f\u0a91\u0a93\u0aa8\u0aaa\u0ab0\u0ab2\u0ab3\u0ab5\u0ab9\u0abc\u0ac5\u0ac7\u0ac9\u0acb\u0acd\u0ae0\u0ae0\u0ae6\u0aef\u0b01\u0b03\u0b05\u0b0c\u0b0f\u0b10\u0b13\u0b28\u0b2a\u0b30\u0b32\u0b33\u0b36\u0b39\u0b3c\u0b43\u0b47\u0b48\u0b4b\u0b4d\u0b56\u0b57\u0b5c\u0b5d\u0b5f\u0b61\u0b66\u0b6f\u0b82\u0b83\u0b85\u0b8a\u0b8e\u0b90\u0b92\u0b95\u0b99\u0b9a\u0b9c\u0b9c\u0b9e\u0b9f\u0ba3\u0ba4\u0ba8\u0baa\u0bae\u0bb5\u0bb7\u0bb9\u0bbe\u0bc2\u0bc6\u0bc8\u0bca\u0bcd\u0bd7\u0bd7\u0be7\u0bef\u0c01\u0c03\u0c05\u0c0c\u0c0e\u0c10\u0c12\u0c28\u0c2a\u0c33\u0c35\u0c39\u0c3e\u0c44\u0c46\u0c48\u0c4a\u0c4d\u0c55\u0c56\u0c60\u0c61\u0c66\u0c6f\u0c82\u0c83\u0c85\u0c8c\u0c8e\u0c90\u0c92\u0ca8\u0caa\u0cb3\u0cb5\u0cb9\u0cbe\u0cc4\u0cc6\u0cc8\u0cca\u0ccd\u0cd5\u0cd6\u0cde\u0cde\u0ce0\u0ce1\u0ce6\u0cef\u0d02\u0d03\u0d05\u0d0c\u0d0e\u0d10\u0d12\u0d28\u0d2a\u0d39\u0d3e\u0d43\u0d46\u0d48\u0d4a\u0d4d\u0d57\u0d57\u0d60\u0d61\u0d66\u0d6f\u0e01\u0e2e\u0e30\u0e3a\u0e40\u0e4e\u0e50\u0e59\u0e81\u0e82\u0e84\u0e84\u0e87\u0e88\u0e8a\u0e8a\u0e8d\u0e8d\u0e94\u0e97\u0e99\u0e9f\u0ea1\u0ea3\u0ea5\u0ea5\u0ea7\u0ea7\u0eaa\u0eab\u0ead\u0eae\u0eb0\u0eb9\u0ebb\u0ebd\u0ec0\u0ec4\u0ec6\u0ec6\u0ec8\u0ecd\u0ed0\u0ed9\u0f18\u0f19\u0f20\u0f29\u0f35\u0f35\u0f37\u0f37\u0f39\u0f39\u0f3e\u0f47\u0f49\u0f69\u0f71\u0f84\u0f86\u0f8b\u0f90\u0f95\u0f97\u0f97\u0f99\u0fad\u0fb1\u0fb7\u0fb9\u0fb9\u10a0\u10c5\u10d0\u10f6\u1100\u1100\u1102\u1103\u1105\u1107\u1109\u1109\u110b\u110c\u110e\u1112\u113c\u113c\u113e\u113e\u1140\u1140\u114c\u114c\u114e\u114e\u1150\u1150\u1154\u1155\u1159\u1159\u115f\u1161\u1163\u1163\u1165\u1165\u1167\u1167\u1169\u1169\u116d\u116e\u1172\u1173\u1175\u1175\u119e\u119e\u11a8\u11a8\u11ab\u11ab\u11ae\u11af\u11b7\u11b8\u11ba\u11ba\u11bc\u11c2\u11eb\u11eb\u11f0\u11f0\u11f9\u11f9\u1e00\u1e9b\u1ea0\u1ef9\u1f00\u1f15\u1f18\u1f1d\u1f20\u1f45\u1f48\u1f4d\u1f50\u1f57\u1f59\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f\u1f7d\u1f80\u1fb4\u1fb6\u1fbc\u1fbe\u1fbe\u1fc2\u1fc4\u1fc6\u1fcc\u1fd0\u1fd3\u1fd6\u1fdb\u1fe0\u1fec\u1ff2\u1ff4\u1ff6\u1ffc\u20d0\u20dc\u20e1\u20e1\u2126\u2126\u212a\u212b\u212e\u212e\u2180\u2182\u3005\u3005\u3007\u3007\u3021\u302f\u3031\u3035\u3041\u3094\u3099\u309a\u309d\u309e\u30a1\u30fa\u30fc\u30fe\u3105\u312c\u4e00\u9fa5\uac00\ud7a3");
            ParserForXMLSchema.ranges.put("xml:isNameChar", tok);
            ParserForXMLSchema.ranges2.put("xml:isNameChar", Token.complementRanges(tok));
            tok = Token.createRange();
            setupRange(tok, "AZaz\u00c0\u00d6\u00d8\u00f6\u00f8\u0131\u0134\u013e\u0141\u0148\u014a\u017e\u0180\u01c3\u01cd\u01f0\u01f4\u01f5\u01fa\u0217\u0250\u02a8\u02bb\u02c1\u0386\u0386\u0388\u038a\u038c\u038c\u038e\u03a1\u03a3\u03ce\u03d0\u03d6\u03da\u03da\u03dc\u03dc\u03de\u03de\u03e0\u03e0\u03e2\u03f3\u0401\u040c\u040e\u044f\u0451\u045c\u045e\u0481\u0490\u04c4\u04c7\u04c8\u04cb\u04cc\u04d0\u04eb\u04ee\u04f5\u04f8\u04f9\u0531\u0556\u0559\u0559\u0561\u0586\u05d0\u05ea\u05f0\u05f2\u0621\u063a\u0641\u064a\u0671\u06b7\u06ba\u06be\u06c0\u06ce\u06d0\u06d3\u06d5\u06d5\u06e5\u06e6\u0905\u0939\u093d\u093d\u0958\u0961\u0985\u098c\u098f\u0990\u0993\u09a8\u09aa\u09b0\u09b2\u09b2\u09b6\u09b9\u09dc\u09dd\u09df\u09e1\u09f0\u09f1\u0a05\u0a0a\u0a0f\u0a10\u0a13\u0a28\u0a2a\u0a30\u0a32\u0a33\u0a35\u0a36\u0a38\u0a39\u0a59\u0a5c\u0a5e\u0a5e\u0a72\u0a74\u0a85\u0a8b\u0a8d\u0a8d\u0a8f\u0a91\u0a93\u0aa8\u0aaa\u0ab0\u0ab2\u0ab3\u0ab5\u0ab9\u0abd\u0abd\u0ae0\u0ae0\u0b05\u0b0c\u0b0f\u0b10\u0b13\u0b28\u0b2a\u0b30\u0b32\u0b33\u0b36\u0b39\u0b3d\u0b3d\u0b5c\u0b5d\u0b5f\u0b61\u0b85\u0b8a\u0b8e\u0b90\u0b92\u0b95\u0b99\u0b9a\u0b9c\u0b9c\u0b9e\u0b9f\u0ba3\u0ba4\u0ba8\u0baa\u0bae\u0bb5\u0bb7\u0bb9\u0c05\u0c0c\u0c0e\u0c10\u0c12\u0c28\u0c2a\u0c33\u0c35\u0c39\u0c60\u0c61\u0c85\u0c8c\u0c8e\u0c90\u0c92\u0ca8\u0caa\u0cb3\u0cb5\u0cb9\u0cde\u0cde\u0ce0\u0ce1\u0d05\u0d0c\u0d0e\u0d10\u0d12\u0d28\u0d2a\u0d39\u0d60\u0d61\u0e01\u0e2e\u0e30\u0e30\u0e32\u0e33\u0e40\u0e45\u0e81\u0e82\u0e84\u0e84\u0e87\u0e88\u0e8a\u0e8a\u0e8d\u0e8d\u0e94\u0e97\u0e99\u0e9f\u0ea1\u0ea3\u0ea5\u0ea5\u0ea7\u0ea7\u0eaa\u0eab\u0ead\u0eae\u0eb0\u0eb0\u0eb2\u0eb3\u0ebd\u0ebd\u0ec0\u0ec4\u0f40\u0f47\u0f49\u0f69\u10a0\u10c5\u10d0\u10f6\u1100\u1100\u1102\u1103\u1105\u1107\u1109\u1109\u110b\u110c\u110e\u1112\u113c\u113c\u113e\u113e\u1140\u1140\u114c\u114c\u114e\u114e\u1150\u1150\u1154\u1155\u1159\u1159\u115f\u1161\u1163\u1163\u1165\u1165\u1167\u1167\u1169\u1169\u116d\u116e\u1172\u1173\u1175\u1175\u119e\u119e\u11a8\u11a8\u11ab\u11ab\u11ae\u11af\u11b7\u11b8\u11ba\u11ba\u11bc\u11c2\u11eb\u11eb\u11f0\u11f0\u11f9\u11f9\u1e00\u1e9b\u1ea0\u1ef9\u1f00\u1f15\u1f18\u1f1d\u1f20\u1f45\u1f48\u1f4d\u1f50\u1f57\u1f59\u1f59\u1f5b\u1f5b\u1f5d\u1f5d\u1f5f\u1f7d\u1f80\u1fb4\u1fb6\u1fbc\u1fbe\u1fbe\u1fc2\u1fc4\u1fc6\u1fcc\u1fd0\u1fd3\u1fd6\u1fdb\u1fe0\u1fec\u1ff2\u1ff4\u1ff6\u1ffc\u2126\u2126\u212a\u212b\u212e\u212e\u2180\u2182\u3007\u3007\u3021\u3029\u3041\u3094\u30a1\u30fa\u3105\u312c\u4e00\u9fa5\uac00\ud7a3");
            tok.addRange(95, 95);
            tok.addRange(58, 58);
            ParserForXMLSchema.ranges.put("xml:isInitialNameChar", tok);
            ParserForXMLSchema.ranges2.put("xml:isInitialNameChar", Token.complementRanges(tok));
        }
        final RangeToken tok2 = positive ? ParserForXMLSchema.ranges.get(name) : ParserForXMLSchema.ranges2.get(name);
        return tok2;
    }
    
    static void setupRange(final Token range, final String src) {
        for (int len = src.length(), i = 0; i < len; i += 2) {
            range.addRange(src.charAt(i), src.charAt(i + 1));
        }
    }
    
    static {
        ParserForXMLSchema.ranges = null;
        ParserForXMLSchema.ranges2 = null;
    }
}

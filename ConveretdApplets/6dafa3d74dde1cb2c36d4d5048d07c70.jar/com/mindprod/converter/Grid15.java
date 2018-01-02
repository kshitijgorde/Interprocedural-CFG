// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.converter;

final class Grid15
{
    static final String[][] how15;
    
    static {
        how15 = new String[][] { { null, "b = t ? (byte)1 : (byte)0;", "u = t ? (byte)1 : (byte)0;", "s = t ? (short)1 : (short)0;", "// to get '0' and '1'\nc = t ? '1' : '0';\n// or to get Unicode value 0 or 1\nc = t ? (char)1 : (char)0;", "i = t ? 1 : 0;", "n = t ? 1L : 0L;", "f = t ? 1.0f : 0.0f;", "d = t ? 1.0d : 0.0d;", "g = String.valueOf(t);", "tt = t ? Boolean.TRUE : Boolean.FALSE;", "bb = t ? (byte)1 : (byte)0;", "uu = t ? (byte)1 : (byte)0;", "ss = t ? (short)1 : (short)0;", "// to get '0' or '1'\ncc = t ? (char)1 : (char)0;\n// or to get Unicode 0 or 1\ncc = t ? (char)1 : (char)0;\n", "ii = t ? 1 : 0;", "nn = t ? 1L : 0L;", "ff = t ? 1.0f : 0.0f;", "dd = t ? 1.0d : 0.0d;" }, { "t = b!=0;", null, "u = b;", "s = b;", "// 9 -> '9'\nc = (char)(b + '0');\n// or to get Unicode value\nc = (char)b;", "i = b; // sign extends.", "n = b; // sign extends.", "f = b;", "d = b;", "// best for readability\ng = Integer.toString(b);\n// best for maintainability\ng = String.valueOf(b);\n// or\ng = Integer.toString(b, 7 /* radix */);\n// or\ng = Integer.toBinaryString(b);\n// or\ng = Integer.toOctalString(b);\n// or\ng = Integer.toHexString(b);\n// or kludgy and slow on unoptimised Javas\ng = \"\" + b;", "tt = (b!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = b;", "uu = b;", "ss = (short)b; // sign extends\n", "// 9 -> '9'\ncc = (char)(b + '0');\n// or to get Unicode value\ncc = (char)b;", "ii = (int)b;", "nn = (long)b;", "ff = (float)b; // sign extends.", "dd = (double)b; // sign extends." }, { "t = u!=0;", "b = u;", null, "s = (short)(u & 0xff);", "// 9 -> '9'\nc = (char)((u & 0xff) + '0');\n// or to get Unicode value\nc = (char)(u & 0xff);", "i = u & 0xff; // does not sign extend", "n = u & 0xff; // does not sign extend.", "f = u & 0xff; // does not sign extend.", "d = u & 0xff; // does not sign extend.", "// best for readability\ng = Integer.toString(u & 0xff);\n// best for maintainability\ng = String.valueOf(u & 0xff);\n// or\ng = Integer.toString(u & 0xff, 7 /* radix */);\n// or\ng = Integer.toBinaryString(u & 0xff);\n// or\ng = Integer.toOctalString(u & 0xff);\n// or\ng = Integer.toHexString(u & 0xff);\n// or kludgy and possibly slow\ng = \"\" + (u & 0xff);", "tt = (u!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = u;", "uu = u;", "ss = (short)(u & 0xff); // does not sign extend.", "// 9 -> '9'\ncc = (char)((u & 0xff) + '0');\n// or to get Unicode value\ncc = (char)(u & 0xff);", "ii = u & 0xff;", "nn =(long)(u & 0xff);", "ff = (float)(u & 0xff); // does not sign extend.", "dd = (double)(u & 0xff); // does not sign extend." }, { "t = s!=0;", "b = (byte)s;", "u = (byte)s;", null, "// 9 -> '9'\nc = (char)(s + '0');\n// or to get Unicode value\nc = (char)s;", "i = s;", "n = s;", "f = s;", "d = s;", "// best for readability\ng = Integer.toString(s);\n// best for maintainability\ng = String.valueOf(s);\n// or\ng = Integer.toString(s, 7 /* radix */);\n// or\ng = Integer.toBinaryString(s);\n// or\ng = Integer.toOctalString(s);\n// or\ng = Integer.toHexString(s);\n// or kludgy and possibly slow\ng = \"\" + s;", "tt = (s!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = (byte)s;", "uu = (byte)s;", "ss = s;", "// 9 -> '9'\ncc = (char)(s + '0');\n// or to get Unicode value\ncc = (char)s;", "ii = (int)s;", "nn = (long)s;", "ff = (float)s; /* locale-insensitive */", "dd = (double)s; /* locale-insensitive */" }, { "// to convert '0' or '1'\nt = c!='0';\n// to convert Unicode 0 or 1\nt = c!=0;", "// international\nb = (byte)Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nb = (byte)(c - '0');\n// or to get Unicode value\nb = (byte)c;", "// international\nu = (byte)Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nu = (byte)(c - '0');\n// or to get Unicode value\nu = (byte)c;", "// international\ns = (short)Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\ns = (short)(c - '0');\n// or to get Unicode value\ns = (short)c;", null, "// international\ni = Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\ni = c - '0';\n// or to get Unicode value\ni = c; // does not sign extend.", "// international\nn = Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nn = c - '0';\n// or to get Unicode value\nn = c; // does not sign extend.", "f = c; // does not sign extend.", "d = c; // does not sign extend.", "g = String.valueOf(c);", "// for '0' or '1'\ntt = (c!='0') ? Boolean.TRUE : Boolean.FALSE;\n// or for Unicode 0 or 1\ntt = (c!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = (byte)c;", "uu = (byte)c;", "// international\nss = (short)Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nss = (short)(c - '0');\n// or to get Unicode value\nss = (short)c;", "cc = c;", "// international\nii = Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nii = c - '0';\n// or to get Unicode value\nii = (int)c;", "// international,\nnn = (long)Character.digit(c, 10 /* radix */);\n// fastest '9' -> 9\nnn = (long)(c - '0');\n// or to get Unicode value\nnn = (long)c;", "ff = (float)c; // does not sign extend.", "dd = (double)c; // does not sign extend." }, { "t = i!=0;", "b = (byte)i;", "u = (byte)i;", "s = (short)i;", "// 9 -> '9'\nc = (char)(i + '0');\n// or to get Unicode value\nc = (char)i;", null, "n = i;", "f = i;\n// to construct a float out of IEEE bits\nf = Float.intBitsToFloat(i);", "d = i;", "// best for readability\ng = Integer.toString(i);\n// best for maintainability\ng = String.valueOf(i);\n// or\ng = Integer.toString(i, 7 /* radix */);\n// or\ng = Integer.toBinaryString(i);\n// or\ng = Integer.toOctalString(i);\n// or\ng = Integer.toHexString(i);\n// or kludgy and possibly slow\ng = \"\" + i;", "tt = (i!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb =(byte)i;", "uu = (byte)i;", "ss = (short)i;", "// 9 -> '9'\ncc = (char)(i + '0');\n// or to get Unicode value\ncc = (char)i;", "ii = i;", "nn = (long)i;", "ff = (float)i;", "dd = (double)i;" }, { "t = n!=0;", "b = (byte)n;", "u = (byte)n;", "s = (short)n;", "// 9 -> '9'\nc = (char)(n + '0');\n// or to get Unicode value\nc = (char)n;", "i = (int)n;", null, "f = n;", "d = n;\n// to construct a double out of IEEE bits\nd  = Double.longBitsToDouble (n);", "// best for readability\ng = Long.toString(n);\n// best for maintainability\ng = String.valueOf(n);\n// or\ng = Long.toString(n, 7 /* radix */);\n// or\ng = Long.toBinaryString(n);\n// or\ng = Long.toOctalString(n);\n// or\ng = Long.toHexString(n);\n// or kludgy and possibly slow\ng = \"\" + n;", "tt = (n!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = (byte)n;", "uu = (byte)n;", "ss = (short)n;", "// 9 -> '9'\ncc = (char)(n + '0');\n// or to get Unicode value\ncc = (char)n;", "ii = (int)n;", "nn = n;", "ff = (float)n;", "dd = (double)n;" }, { "t = f!=0;", "b = (byte)f;", "u = (byte)f;", "s = (short)f;", "c = (char)f;", "i = (int)f;\n// or\ni = Math.round(f);\n// or\ni = (int)Math.ceil(f);\n// or\ni = (int)Math.floor(f);\n// to see the IEEE bits inside a float\ni = Float.floatToIntBits(f);", "n = (long)f;\n// or\nn = Math.round(f);\n// or\nn = (long)Math.ceil(f);\n// or\nn = (long)Math.floor(f);", null, "d = f;", "// 2 decimal places, rounded, locale-sensitive.\njava.text.DecimalFormat df2\n = new java.text.DecimalFormat(\"###,##0.00\");\ng = df2.format(f);\n// or exponential scientific format, locale-sensitive.\njava.text.DecimalFormat de\n = new java.text.DecimalFormat(\"0.000000E00\");\ng = de.format(f);\n// or best for readability, no loss of precision, locale-insensitive\ng = Float.toString(f);\n// or best for maintainability, no loss of precision, locale-insensitive\ng = String.valueOf(f);\n", "tt = (f!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = (byte)f;", "uu = (byte)f;", "ss = (short)f;", "cc = (char)f;", "ii = (int)f;", "nn = (long)f;", "ff = f;", "dd = (double)f;" }, { "t = d!=0;", "b = (byte)d;", "u = (byte)d;", "s = (short)d;", "c = (char)d;", "i = (int)d;\n// or\ni = (int)Math.round(d);\n// or\ni = (int)Math.ceil(d);\n// or\ni = (int)Math.floor(d);", "n = (long)d;\n// or\nn = Math.round(d);\n// or\nn = (long)Math.ceil(d);\n// or\nn = (long)Math.floor(d);\n// to see the IEEE bits inside a double\nn = Double.doubleToLongBits(d);", "f = (float)d;", null, "// 2 decimal places, rounded, locale-sensitive.\njava.text.DecimalFormat df2\n = new java.text.DecimalFormat(\"###,##0.00\");\ng = df2.format(d);\n// or exponential scientific format, locale-sensitive.\njava.text.DecimalFormat de\n = new java.text.DecimalFormat(\"0.0000000000E00\");\ng = de.format(d);\n// or best for readability, no loss of precision, locale-insensitive\ng = Double.toString(d);\n// or best for maintainability, no loss of precision, locale-insensitive\ng = String.valueOf(d);", "tt = (d!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = (byte)d;", "uu = (byte)d;", "ss = (short)d;", "cc = (char)d;", "ii = (int)d;", "nn = (long)d;", "ff = (float)d;", "dd = d;" }, { "t = Boolean.valueOf( g.trim() );\n// or\nt = g.trim().equalsIgnoreCase(\"true\");", "try {\nb = (byte)Integer.parseInt(g.trim());\n// or\nb = (byte)Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\nu = (byte)Integer.parseInt(g.trim());\n// or\nu = (byte)Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\ns = (short)Integer.parseInt(g.trim());\n// or\ns = (short)Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\n// \"9\" -> '9'\nc = g.charAt(0 /* position */);\n// or to get Unicode value\nc = (char)Integer.parseInt(g.trim());\n// or to get Unicode hex value\nc = (char)Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\ni = Integer.parseInt(g.trim());\n// or\ni = Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\nn = Long.parseLong(g.trim());\n} catch (NumberFormatException e){/* ... */}", "try {\n// locale-insensitive\nf = Float.parseFloat(g.trim());\n// locale-insensitive\nf = Float.valueOf(g.trim());\n} catch (NumberFormatException e){/* ... */}", "try {\n// locale-insensitive\nd = Double.parseDouble(g.trim()); \n} catch (NumberFormatException e){/* ... */}", null, "tt = Boolean.valueOf(g.trim());", "try {\nbb = Byte.parseByte(g.trim());\n// or\nbb = Byte.parseByte(g.trim(), 16 /* radix */);\n// or\nbb = (byte)g.charAt(0 /* position */);\n} catch (NumberFormatException e){/* ... */}", "try {\nuu = Byte.parseByte(g.trim());\n// or\nuu = Byte.parseByte(g.trim(), 16 /* radix */);\n// or\nuu = (byte)g.charAt(0 /* position */);\n} catch (NumberFormatException e){/* ... */}", "try {\nss = Short.parseShort(g.trim());\n// or\nss = Short.parseShort(g.trim(), 16 /* radix */);\n// or\nss = (short)g.charAt(0 /* position */);\n} catch (NumberFormatException e){/* ... */}", "try {\n// \"9\" -> '9'\ncc = g.charAt(0 /* position */);\n// or to get Unicode value\ncc = (char)Integer.parseInt(g.trim());\n// or to get Unicode hex value\ncc = (char)Integer.parseInt(g.trim(), 16 /* radix */);\n} catch (NumberFormatException e){/* ... */}", "try {\nii = Integer.valueOf(g.trim());\n} catch (NumberFormatException e){/* ... */}", "try {\nnn = Long.valueOf(g.trim());\n} catch (NumberFormatException e){/* ... */}", "try {\n//  locale-insensitive.\nff = Float.valueOf(g.trim());\n} catch (NumberFormatException e){/* ... */}", "try {\n// locale-insensitive\ndd = Double.valueOf(g.trim());\n} catch (NumberFormatException e){/* ... */}" }, { "t = tt;", "b = tt ? (byte)1 : (byte)0;", "u = tt ? (byte)1 : (byte)0;", "s = tt ? (short)1 : (short)0;", "// to get '0' and '1'\nc = tt ? '1' : '0';\n// or to get Unicode 0 and 1\nc = tt ? (char)1 : (char)0;", "i = tt ? 1 : 0;", "n = tt ? 1L : 0L;", "f = tt ? 1.0f : 0.0f;", "d = tt ? 1.0d : 0.0d;", "g = tt.toString();", null, "bb = tt ? (byte)1 : (byte)0;", "uu = tt ? (byte)1 : (byte)0;", "ss = tt ? (short)1 : (short)0;", "// to get '0' and '1'\ncc = tt ? '1' : '0';\n// or to get Unicode 0 or 1\ncc = tt ? (char)1 : (char)0;", "ii = tt ? 1 : 0;", "nn = tt ? 1L : 0L;", "ff = tt ? 1.0f : 0.0f;", "dd = tt ? 1.0d : 0.0d;" }, { "t = bb!=0;", "b = bb;", "u = bb;", "s = bb.shortValue();", "// 9 -> '9'\nc = (char)(bb.intValue() + '0');\n// or to get Unicode value\nc = (char)bb.intValue();", "i = bb.intValue(); ", "n = bb.longValue();", "f = bb.floatValue();", "d = bb.doubleValue();", "g = bb.toString();", "tt = (bb!='0') ? Boolean.TRUE : Boolean.FALSE;", null, "uu = bb;", "ss = bb.shortValue();", "// 9 -> '9'\ncc = (char)(bb + '0');\n// or to get Unicode value\ncc = (char)bb.intValue();", "ii = bb.intValue();", "nn = bb.longValue();", "ff = bb.floatValue();", "dd = bb.doubleValue();" }, { "t = uu!=0;", "b = uu;", "u = uu;", "s = (short)(uu.intValue() & 0xff);", "// 9 -> '9'\nc = (char)((uu.intValue() + '0') & 0xff);\n// or to get Unicode value\nc = (char)(uu.intValue() & 0xff);", "i = uu.intValue() & 0xff;", "n = uu.longValue() & 0xff;", "f = (float)(uu.intValue() & 0xff);", "d = (double)(uu.intValue() & 0xff);", "g = uu.toString();", "tt = (uu!='0') ? Boolean.TRUE : Boolean.FALSE;", "bb = uu;", null, "ss = (short)(uu & 0xff);", "// 9 -> '9'\ncc = (char)((uu.intValue() + '0') & 0xff);\n// or to get Unicode value\ncc = (char)(uu.intValue() & 0xff);", "ii = uu.intValue() & 0xff;", "nn = uu.longValue() & 0xff;", "ff = (float)(uu.intValue() & 0xff);", "dd = (double)(uu.intValue() & 0xff);" }, { "t = ss!=0;", "b = ss.byteValue();", "u = ss.byteValue();", "s = ss;", "// 9 -> '9'\nc = (char)(ss.intValue() + '0');\n// or to get Unicode value\nc = (char)ss.intValue();", "i = ss.intValue(); ", "n = ss.longValue();", "f = ss.floatValue();", "d = ss.doubleValue();", "g = ss.toString();", "tt = ss!=0 ? Boolean.TRUE : Boolean.FALSE;", "bb = ss.byteValue();", "uu = ss.byteValue();", null, "// 9 -> '9'\ncc = (char)(ss.intValue() + '0');\n// or to get Unicode value\ncc = (char)ss.intValue();", "ii = ss.intValue();", "nn = ss.longValue();", "ff = ss.floatValue();", "dd = ss.doubleValue();" }, { "// to convert '0' or '1'\nt = cc!='0';\n// to convert Unicode 0 or 1\nt = cc!=0;", "// international\nb = (byte)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nb = (byte)(cc - '0');\n// or to get Unicode value\nb = (byte)cc.charValue();", "// international\nu = (byte)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nu = (byte)(cc - '0');\n// or to get Unicode value\nu = (byte)cc.charValue();", "// international\ns = (short)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\ns = (short)(cc - '0');\n// or to get Unicode value\ns = (short)cc.charValue();", "c = cc;", "// international\ni = Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\ni = cc - '0';\n// or to get Unicode value\ni = cc; // does not sign extend.", "// international\nn = Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nn = cc - '0';\n// or to get Unicode value\nn = cc; // does not sign extend.", "f = cc; // does not sign extend.", "d = cc; // does not sign extend.", "g = cc.toString();", "// for '0' or '1'\ntt = cc!='0' ? Boolean.TRUE : Boolean.FALSE;\n// or for Unicode 0 or 1\ntt = cc!=0 ? Boolean.TRUE : Boolean.FALSE;", "// international\nbb =(byte)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nbb = (byte)(cc - '0');\n// or to get Unicode value\nbb = (byte)cc.charValue();", "// international\nuu = (byte)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nuu = (byte)(cc - '0');\n// or to get Unicode value\nuu = (byte)cc.charValue();", "// international\nss = (short)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nss = (short)(cc - '0');\n// or to get Unicode value\nss = (short)cc.charValue();", null, "// international,\nii = Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nii = cc - '0';\n// or to get Unicode value \nii = (int)cc;", "// international\nnn = (long)Character.digit(cc, 10 /* radix */);\n// fastest '9' -> 9\nnn = (long)(cc - '0');\n// or to get Unicode value\nnn = (long)cc;", "ff = (float)cc; // does not sign extend.", "dd = (double)cc; // does not sign extend." }, { "t = ii!=0;", "b = (byte)ii.intValue();", "u = (byte)ii.intValue();", "s = (short)ii.intValue();", "c = (char)ii.intValue();", "i = ii;", "n = ii.longValue();", "f = ii.floatValue();", "d = ii.doubleValue();", "g = ii.toString();", "tt = (ii!= 0) ? Boolean.TRUE : Boolean.FALSE;", "bb = ii.byteValue();", "uu = ii.byteValue();", "ss = ii.shortValue();", "// 9 -> '9'\ncc = (char)(ii + '0');\n// or to get Unicode value\ncc = (char)ii.intValue();", null, "nn = ii.longValue();", "ff = ii.floatValue();", "dd = ii.doubleValue();" }, { "t = nn!=0;", "b = (byte)nn.intValue();", "u = (byte)nn.intValue();", "s = (short)nn.intValue();", "// 9 -> '9'\nc = (char)(nn.intValue() + '0');\n// or to get Unicode value\nc = (char)nn.intValue();", "i = nn.intValue();", "n = nn;", "f = nn.floatValue();", "d = nn.doubleValue();", "g = nn.toString();", "tt = (nn!= 0) ? Boolean.TRUE : Boolean.FALSE;", "bb = nn.byteValue();", "uu = nn.byteValue();", "ss = nn.shortValue();", "// 9 -> '9'\ncc = (char)(nn.intValue() + '0');\n// or to get Unicode value\ncc = (char)nn.intValue();", "ii = nn.intValue();", null, "ff = nn.floatValue();", "dd = nn.doubleValue();" }, { "t = ff!=0;", "b = (byte)ff.intValue();", "u = (byte)ff.intValue();", "s = (short)ff.intValue();", "c = (char)ff.intValue();", "i = ff.intValue();", "n = ff.longValue();", "f = ff;", "d = ff.doubleValue();", "// 2 decimal places, rounded, locale-sensitive\njava.text.DecimalFormat df2\n = new java.text.DecimalFormat(\"###,##0.00\");\ng = df2.format(ff);\n// or exponential scientific format, locale-sensitive.\njava.text.DecimalFormat de\n = new java.text.DecimalFormat(\"0.000000E00\");\ng = de.format(ff); \n// or best for readability and maintainability, locale-insensitive.\ng = ff.toString();", "tt = (ff!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = ff.byteValue();", "uu = ff.byteValue();", "ss = ff.shortValue();", "cc = (char)ff.intValue();", "ii = ff.intValue();", "nn = ff.longValue();", null, "dd = ff.doubleValue();" }, { "t = dd!=0;", "b = (byte)dd.intValue();", "u = (byte)dd.intValue();", "s = (short)dd.intValue();", "c = (char)dd.intValue();", "i = dd.intValue();", "n = dd.longValue();", "f = dd.floatValue();", "d = dd;", "// 2 decimal places, rounded, locale-sensitive\njava.text.DecimalFormat df2\n = new java.text.DecimalFormat(\"###,##0.00\");\ng = df2.format(dd);\n// or exponential scientific format, locale sensitive.\njava.text.DecimalFormat df\n = new java.text.DecimalFormat(\"0.0000000000E00\");\ng = df.format(dd); \n// or best for readability and maintainability, locale-insensitive\ng = dd.toString();", "tt = (dd!=0) ? Boolean.TRUE : Boolean.FALSE;", "bb = dd.byteValue();", "uu = dd.byteValue();", "ss = dd.shortValue();", "cc = (char)dd.intValue();", "ii = dd.intValue();", "nn = dd.longValue();", "ff = dd.floatValue();", null } };
    }
}
// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.FilterWriter;
import java.io.Reader;
import java.io.FilterReader;
import java.io.Writer;
import java.io.CharArrayWriter;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.IOException;
import org.apache.bcel.util.ByteSequence;
import org.apache.bcel.Constants;

public abstract class Utility
{
    private static int consumed_chars;
    private static boolean wide;
    private static final int FREE_CHARS = 48;
    private static int[] CHAR_MAP;
    private static int[] MAP_CHAR;
    private static final char ESCAPE_CHAR = '$';
    
    public static final String accessToString(final int access_flags) {
        return accessToString(access_flags, false);
    }
    
    public static final String accessToString(final int access_flags, final boolean for_class) {
        final StringBuffer buf = new StringBuffer();
        int p = 0;
        int i = 0;
        while (p < 1024) {
            p = pow2(i);
            Label_0077: {
                if ((access_flags & p) != 0x0) {
                    if (for_class) {
                        if (p == 32) {
                            break Label_0077;
                        }
                        if (p == 512) {
                            break Label_0077;
                        }
                    }
                    buf.append(Constants.ACCESS_NAMES[i] + " ");
                }
            }
            ++i;
        }
        return buf.toString().trim();
    }
    
    public static final String classOrInterface(final int access_flags) {
        return ((access_flags & 0x200) != 0x0) ? "interface" : "class";
    }
    
    public static final String codeToString(final byte[] code, final ConstantPool constant_pool, final int index, final int length, final boolean verbose) {
        final StringBuffer buf = new StringBuffer(code.length * 20);
        final ByteSequence stream = new ByteSequence(code);
        try {
            for (int i = 0; i < index; ++i) {
                codeToString(stream, constant_pool, verbose);
            }
            int j = 0;
            while (stream.available() > 0) {
                if (length < 0 || j < length) {
                    final String indices = fillup(stream.getIndex() + ":", 6, true, ' ');
                    buf.append(indices + codeToString(stream, constant_pool, verbose) + '\n');
                }
                ++j;
            }
        }
        catch (IOException e) {
            System.out.println(buf.toString());
            e.printStackTrace();
            throw new ClassFormatError("Byte code error: " + e);
        }
        return buf.toString();
    }
    
    public static final String codeToString(final byte[] code, final ConstantPool constant_pool, final int index, final int length) {
        return codeToString(code, constant_pool, index, length, true);
    }
    
    public static final String codeToString(final ByteSequence bytes, final ConstantPool constant_pool, final boolean verbose) throws IOException {
        final short opcode = (short)bytes.readUnsignedByte();
        int default_offset = 0;
        int no_pad_bytes = 0;
        final StringBuffer buf = new StringBuffer(Constants.OPCODE_NAMES[opcode]);
        if (opcode == 170 || opcode == 171) {
            final int remainder = bytes.getIndex() % 4;
            no_pad_bytes = ((remainder == 0) ? 0 : (4 - remainder));
            for (int i = 0; i < no_pad_bytes; ++i) {
                final byte b;
                if ((b = bytes.readByte()) != 0) {
                    System.err.println("Warning: Padding byte != 0 in " + Constants.OPCODE_NAMES[opcode] + ":" + b);
                }
            }
            default_offset = bytes.readInt();
        }
        switch (opcode) {
            case 170: {
                final int low = bytes.readInt();
                final int high = bytes.readInt();
                final int offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
                default_offset += offset;
                buf.append("\tdefault = " + default_offset + ", low = " + low + ", high = " + high + "(");
                final int[] jump_table = new int[high - low + 1];
                for (int j = 0; j < jump_table.length; ++j) {
                    buf.append(jump_table[j] = offset + bytes.readInt());
                    if (j < jump_table.length - 1) {
                        buf.append(", ");
                    }
                }
                buf.append(")");
                break;
            }
            case 171: {
                final int npairs = bytes.readInt();
                final int offset = bytes.getIndex() - 8 - no_pad_bytes - 1;
                final int[] match = new int[npairs];
                final int[] jump_table = new int[npairs];
                default_offset += offset;
                buf.append("\tdefault = " + default_offset + ", npairs = " + npairs + " (");
                for (int i = 0; i < npairs; ++i) {
                    match[i] = bytes.readInt();
                    jump_table[i] = offset + bytes.readInt();
                    buf.append("(" + match[i] + ", " + jump_table[i] + ")");
                    if (i < npairs - 1) {
                        buf.append(", ");
                    }
                }
                buf.append(")");
                break;
            }
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 198:
            case 199: {
                buf.append("\t\t#" + (bytes.getIndex() - 1 + bytes.readShort()));
                break;
            }
            case 200:
            case 201: {
                buf.append("\t\t#" + (bytes.getIndex() - 1 + bytes.readInt()));
                break;
            }
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 169: {
                int vindex;
                if (Utility.wide) {
                    vindex = bytes.readUnsignedShort();
                    Utility.wide = false;
                }
                else {
                    vindex = bytes.readUnsignedByte();
                }
                buf.append("\t\t%" + vindex);
                break;
            }
            case 196: {
                Utility.wide = true;
                buf.append("\t(wide)");
                break;
            }
            case 188: {
                buf.append("\t\t<" + Constants.TYPE_NAMES[bytes.readByte()] + ">");
                break;
            }
            case 178:
            case 179:
            case 180:
            case 181: {
                final int index = bytes.readUnsignedShort();
                buf.append("\t\t" + constant_pool.constantToString(index, (byte)9) + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 187:
            case 192: {
                buf.append("\t");
            }
            case 193: {
                final int index = bytes.readUnsignedShort();
                buf.append("\t<" + constant_pool.constantToString(index, (byte)7) + ">" + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 182:
            case 183:
            case 184: {
                final int index = bytes.readUnsignedShort();
                buf.append("\t" + constant_pool.constantToString(index, (byte)10) + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 185: {
                final int index = bytes.readUnsignedShort();
                final int nargs = bytes.readUnsignedByte();
                buf.append("\t" + constant_pool.constantToString(index, (byte)11) + (verbose ? (" (" + index + ")\t") : "") + nargs + "\t" + bytes.readUnsignedByte());
                break;
            }
            case 19:
            case 20: {
                final int index = bytes.readUnsignedShort();
                buf.append("\t\t" + constant_pool.constantToString(index, constant_pool.getConstant(index).getTag()) + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 18: {
                final int index = bytes.readUnsignedByte();
                buf.append("\t\t" + constant_pool.constantToString(index, constant_pool.getConstant(index).getTag()) + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 189: {
                final int index = bytes.readUnsignedShort();
                buf.append("\t\t<" + compactClassName(constant_pool.getConstantString(index, (byte)7), false) + ">" + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 197: {
                final int index = bytes.readUnsignedShort();
                final int dimensions = bytes.readUnsignedByte();
                buf.append("\t<" + compactClassName(constant_pool.getConstantString(index, (byte)7), false) + ">\t" + dimensions + (verbose ? (" (" + index + ")") : ""));
                break;
            }
            case 132: {
                int vindex;
                int constant;
                if (Utility.wide) {
                    vindex = bytes.readUnsignedShort();
                    constant = bytes.readShort();
                    Utility.wide = false;
                }
                else {
                    vindex = bytes.readUnsignedByte();
                    constant = bytes.readByte();
                }
                buf.append("\t\t%" + vindex + "\t" + constant);
                break;
            }
            default: {
                if (Constants.NO_OF_OPERANDS[opcode] > 0) {
                    for (int k = 0; k < Constants.TYPE_OF_OPERANDS[opcode].length; ++k) {
                        buf.append("\t\t");
                        switch (Constants.TYPE_OF_OPERANDS[opcode][k]) {
                            case 8: {
                                buf.append(bytes.readByte());
                                break;
                            }
                            case 9: {
                                buf.append(bytes.readShort());
                                break;
                            }
                            case 10: {
                                buf.append(bytes.readInt());
                                break;
                            }
                            default: {
                                System.err.println("Unreachable default case reached!");
                                System.exit(-1);
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
        }
        return buf.toString();
    }
    
    public static final String codeToString(final ByteSequence bytes, final ConstantPool constant_pool) throws IOException {
        return codeToString(bytes, constant_pool, true);
    }
    
    public static final String compactClassName(final String str) {
        return compactClassName(str, true);
    }
    
    public static final String compactClassName(String str, final String prefix, final boolean chopit) {
        final int len = prefix.length();
        str = str.replace('/', '.');
        if (chopit && str.startsWith(prefix) && str.substring(len).indexOf(46) == -1) {
            str = str.substring(len);
        }
        return str;
    }
    
    public static final String compactClassName(final String str, final boolean chopit) {
        return compactClassName(str, "java.lang.", chopit);
    }
    
    private static final boolean is_digit(final char ch) {
        return ch >= '0' && ch <= '9';
    }
    
    private static final boolean is_space(final char ch) {
        return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
    }
    
    public static final int setBit(final int flag, final int i) {
        return flag | pow2(i);
    }
    
    public static final int clearBit(final int flag, final int i) {
        final int bit = pow2(i);
        return ((flag & bit) == 0x0) ? flag : (flag ^ bit);
    }
    
    public static final boolean isSet(final int flag, final int i) {
        return (flag & pow2(i)) != 0x0;
    }
    
    public static final String methodTypeToSignature(final String ret, final String[] argv) throws ClassFormatError {
        final StringBuffer buf = new StringBuffer("(");
        if (argv != null) {
            for (int i = 0; i < argv.length; ++i) {
                final String str = getSignature(argv[i]);
                if (str.endsWith("V")) {
                    throw new ClassFormatError("Invalid type: " + argv[i]);
                }
                buf.append(str);
            }
        }
        final String str = getSignature(ret);
        buf.append(")" + str);
        return buf.toString();
    }
    
    public static final String[] methodSignatureArgumentTypes(final String signature) throws ClassFormatError {
        return methodSignatureArgumentTypes(signature, true);
    }
    
    public static final String[] methodSignatureArgumentTypes(final String signature, final boolean chopit) throws ClassFormatError {
        final ArrayList vec = new ArrayList();
        try {
            if (signature.charAt(0) != '(') {
                throw new ClassFormatError("Invalid method signature: " + signature);
            }
            for (int index = 1; signature.charAt(index) != ')'; index += Utility.consumed_chars) {
                vec.add(signatureToString(signature.substring(index), chopit));
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
        final String[] types = new String[vec.size()];
        vec.toArray(types);
        return types;
    }
    
    public static final String methodSignatureReturnType(final String signature) throws ClassFormatError {
        return methodSignatureReturnType(signature, true);
    }
    
    public static final String methodSignatureReturnType(final String signature, final boolean chopit) throws ClassFormatError {
        String type;
        try {
            final int index = signature.lastIndexOf(41) + 1;
            type = signatureToString(signature.substring(index), chopit);
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
        return type;
    }
    
    public static final String methodSignatureToString(final String signature, final String name, final String access) {
        return methodSignatureToString(signature, name, access, true);
    }
    
    public static final String methodSignatureToString(final String signature, final String name, final String access, final boolean chopit) {
        return methodSignatureToString(signature, name, access, chopit, null);
    }
    
    public static final String methodSignatureToString(final String signature, final String name, final String access, final boolean chopit, final LocalVariableTable vars) throws ClassFormatError {
        final StringBuffer buf = new StringBuffer("(");
        int var_index = (access.indexOf("static") < 0) ? 1 : 0;
        String type;
        try {
            if (signature.charAt(0) != '(') {
                throw new ClassFormatError("Invalid method signature: " + signature);
            }
            int index;
            for (index = 1; signature.charAt(index) != ')'; index += Utility.consumed_chars) {
                buf.append(signatureToString(signature.substring(index), chopit));
                if (vars != null) {
                    final LocalVariable l = vars.getLocalVariable(var_index);
                    if (l != null) {
                        buf.append(" " + l.getName());
                    }
                }
                else {
                    buf.append(" arg" + var_index);
                }
                ++var_index;
                buf.append(", ");
            }
            ++index;
            type = signatureToString(signature.substring(index), chopit);
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 2);
        }
        buf.append(")");
        return access + ((access.length() > 0) ? " " : "") + type + " " + name + buf.toString();
    }
    
    private static final int pow2(final int n) {
        return 1 << n;
    }
    
    public static final String replace(String str, final String old, final String new_) {
        final StringBuffer buf = new StringBuffer();
        try {
            int index;
            if ((index = str.indexOf(old)) != -1) {
                int old_index;
                for (old_index = 0; (index = str.indexOf(old, old_index)) != -1; old_index = index + old.length()) {
                    buf.append(str.substring(old_index, index));
                    buf.append(new_);
                }
                buf.append(str.substring(old_index));
                str = buf.toString();
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            System.err.println(e);
        }
        return str;
    }
    
    public static final String signatureToString(final String signature) {
        return signatureToString(signature, true);
    }
    
    public static final String signatureToString(final String signature, final boolean chopit) {
        Utility.consumed_chars = 1;
        try {
            switch (signature.charAt(0)) {
                case 'B': {
                    return "byte";
                }
                case 'C': {
                    return "char";
                }
                case 'D': {
                    return "double";
                }
                case 'F': {
                    return "float";
                }
                case 'I': {
                    return "int";
                }
                case 'J': {
                    return "long";
                }
                case 'L': {
                    final int index = signature.indexOf(59);
                    if (index < 0) {
                        throw new ClassFormatError("Invalid signature: " + signature);
                    }
                    Utility.consumed_chars = index + 1;
                    return compactClassName(signature.substring(1, index), chopit);
                }
                case 'S': {
                    return "short";
                }
                case 'Z': {
                    return "boolean";
                }
                case '[': {
                    final StringBuffer brackets = new StringBuffer();
                    int n;
                    for (n = 0; signature.charAt(n) == '['; ++n) {
                        brackets.append("[]");
                    }
                    final int consumed_chars = n;
                    final String type = signatureToString(signature.substring(n), chopit);
                    Utility.consumed_chars += consumed_chars;
                    return type + brackets.toString();
                }
                case 'V': {
                    return "void";
                }
                default: {
                    throw new ClassFormatError("Invalid signature: `" + signature + "'");
                }
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid signature: " + e + ":" + signature);
        }
    }
    
    public static String getSignature(String type) {
        final StringBuffer buf = new StringBuffer();
        final char[] chars = type.toCharArray();
        boolean char_found = false;
        boolean delim = false;
        int index = -1;
    Label_0162:
        for (int i = 0; i < chars.length; ++i) {
            switch (chars[i]) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ': {
                    if (char_found) {
                        delim = true;
                        break;
                    }
                    break;
                }
                case '[': {
                    if (!char_found) {
                        throw new RuntimeException("Illegal type: " + type);
                    }
                    index = i;
                    break Label_0162;
                }
                default: {
                    char_found = true;
                    if (!delim) {
                        buf.append(chars[i]);
                        break;
                    }
                    break;
                }
            }
        }
        int brackets = 0;
        if (index > 0) {
            brackets = countBrackets(type.substring(index));
        }
        type = buf.toString();
        buf.setLength(0);
        for (int j = 0; j < brackets; ++j) {
            buf.append('[');
        }
        boolean found = false;
        for (int k = 4; k <= 12 && !found; ++k) {
            if (Constants.TYPE_NAMES[k].equals(type)) {
                found = true;
                buf.append(Constants.SHORT_TYPE_NAMES[k]);
            }
        }
        if (!found) {
            buf.append('L' + type.replace('.', '/') + ';');
        }
        return buf.toString();
    }
    
    private static int countBrackets(final String brackets) {
        final char[] chars = brackets.toCharArray();
        int count = 0;
        boolean open = false;
        for (int i = 0; i < chars.length; ++i) {
            switch (chars[i]) {
                case '[': {
                    if (open) {
                        throw new RuntimeException("Illegally nested brackets:" + brackets);
                    }
                    open = true;
                    break;
                }
                case ']': {
                    if (!open) {
                        throw new RuntimeException("Illegally nested brackets:" + brackets);
                    }
                    open = false;
                    ++count;
                    break;
                }
            }
        }
        if (open) {
            throw new RuntimeException("Illegally nested brackets:" + brackets);
        }
        return count;
    }
    
    public static final byte typeOfMethodSignature(final String signature) throws ClassFormatError {
        try {
            if (signature.charAt(0) != '(') {
                throw new ClassFormatError("Invalid method signature: " + signature);
            }
            final int index = signature.lastIndexOf(41) + 1;
            return typeOfSignature(signature.substring(index));
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
    }
    
    public static final byte typeOfSignature(final String signature) throws ClassFormatError {
        try {
            switch (signature.charAt(0)) {
                case 'B': {
                    return 8;
                }
                case 'C': {
                    return 5;
                }
                case 'D': {
                    return 7;
                }
                case 'F': {
                    return 6;
                }
                case 'I': {
                    return 10;
                }
                case 'J': {
                    return 11;
                }
                case 'L': {
                    return 14;
                }
                case '[': {
                    return 13;
                }
                case 'V': {
                    return 12;
                }
                case 'Z': {
                    return 4;
                }
                case 'S': {
                    return 9;
                }
                default: {
                    throw new ClassFormatError("Invalid method signature: " + signature);
                }
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatError("Invalid method signature: " + signature);
        }
    }
    
    public static short searchOpcode(String name) {
        name = name.toLowerCase();
        for (short i = 0; i < Constants.OPCODE_NAMES.length; ++i) {
            if (Constants.OPCODE_NAMES[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    private static final short byteToShort(final byte b) {
        return (b < 0) ? ((short)(256 + b)) : b;
    }
    
    public static final String toHexString(final byte[] bytes) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            final short b = byteToShort(bytes[i]);
            final String hex = Integer.toString(b, 16);
            if (b < 16) {
                buf.append('0');
            }
            buf.append(hex);
            if (i < bytes.length - 1) {
                buf.append(' ');
            }
        }
        return buf.toString();
    }
    
    public static final String format(final int i, final int length, final boolean left_justify, final char fill) {
        return fillup(Integer.toString(i), length, left_justify, fill);
    }
    
    public static final String fillup(final String str, final int length, final boolean left_justify, final char fill) {
        final int len = length - str.length();
        final char[] buf = new char[(len < 0) ? 0 : len];
        for (int j = 0; j < buf.length; ++j) {
            buf[j] = fill;
        }
        if (left_justify) {
            return str + new String(buf);
        }
        return new String(buf) + str;
    }
    
    static final boolean equals(final byte[] a, final byte[] b) {
        final int size;
        if ((size = a.length) != b.length) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final void printArray(final PrintStream out, final Object[] obj) {
        out.println(printArray(obj, true));
    }
    
    public static final void printArray(final PrintWriter out, final Object[] obj) {
        out.println(printArray(obj, true));
    }
    
    public static final String printArray(final Object[] obj) {
        return printArray(obj, true);
    }
    
    public static final String printArray(final Object[] obj, final boolean braces) {
        if (obj == null) {
            return null;
        }
        final StringBuffer buf = new StringBuffer();
        if (braces) {
            buf.append('{');
        }
        for (int i = 0; i < obj.length; ++i) {
            if (obj[i] != null) {
                buf.append(obj[i].toString());
            }
            else {
                buf.append("null");
            }
            if (i < obj.length - 1) {
                buf.append(", ");
            }
        }
        if (braces) {
            buf.append('}');
        }
        return buf.toString();
    }
    
    public static boolean isJavaIdentifierPart(final char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '_';
    }
    
    public static String encode(byte[] bytes, final boolean compress) throws IOException {
        if (compress) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final GZIPOutputStream gos = new GZIPOutputStream(baos);
            gos.write(bytes, 0, bytes.length);
            gos.close();
            baos.close();
            bytes = baos.toByteArray();
        }
        final CharArrayWriter caw = new CharArrayWriter();
        final JavaWriter jw = new JavaWriter(caw);
        for (int i = 0; i < bytes.length; ++i) {
            final int in = bytes[i] & 0xFF;
            jw.write(in);
        }
        return caw.toString();
    }
    
    public static byte[] decode(final String s, final boolean uncompress) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* s */
        //     1: invokevirtual   java/lang/String.toCharArray:()[C
        //     4: astore_2        /* chars */
        //     5: new             Ljava/io/CharArrayReader;
        //     8: dup            
        //     9: aload_2         /* chars */
        //    10: invokespecial   java/io/CharArrayReader.<init>:([C)V
        //    13: astore_3        /* car */
        //    14: new             Lorg/apache/bcel/classfile/Utility$JavaReader;
        //    17: dup            
        //    18: aload_3         /* car */
        //    19: invokespecial   org/apache/bcel/classfile/Utility$JavaReader.<init>:(Ljava/io/Reader;)V
        //    22: astore          jr
        //    24: new             Ljava/io/ByteArrayOutputStream;
        //    27: dup            
        //    28: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    31: astore          bos
        //    33: goto            43
        //    36: aload           bos
        //    38: iload           6
        //    40: invokevirtual   java/io/ByteArrayOutputStream.write:(I)V
        //    43: aload           jr
        //    45: invokevirtual   org/apache/bcel/classfile/Utility$JavaReader.read:()I
        //    48: dup            
        //    49: istore          ch
        //    51: ifge            36
        //    54: aload           bos
        //    56: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    59: aload_3         /* car */
        //    60: invokevirtual   java/io/CharArrayReader.close:()V
        //    63: aload           jr
        //    65: invokevirtual   java/io/FilterReader.close:()V
        //    68: aload           bos
        //    70: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    73: astore          bytes
        //    75: iload_1         /* uncompress */
        //    76: ifeq            151
        //    79: new             Ljava/util/zip/GZIPInputStream;
        //    82: dup            
        //    83: new             Ljava/io/ByteArrayInputStream;
        //    86: dup            
        //    87: aload           bytes
        //    89: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    92: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //    95: astore          gis
        //    97: aload           bytes
        //    99: arraylength    
        //   100: iconst_3       
        //   101: imul           
        //   102: newarray        B
        //   104: astore          tmp
        //   106: iconst_0       
        //   107: istore          count
        //   109: goto            123
        //   112: aload           tmp
        //   114: iload           count
        //   116: iinc            count, 1
        //   119: iload           11
        //   121: i2b            
        //   122: bastore        
        //   123: aload           gis
        //   125: invokevirtual   java/util/zip/InflaterInputStream.read:()I
        //   128: dup            
        //   129: istore          b
        //   131: ifge            112
        //   134: iload           count
        //   136: newarray        B
        //   138: astore          bytes
        //   140: aload           tmp
        //   142: iconst_0       
        //   143: aload           bytes
        //   145: iconst_0       
        //   146: iload           count
        //   148: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   151: aload           bytes
        //   153: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name        Signature
        //  -----  ------  ----  ----------  ----------------------------------------------
        //  0      154     0     s           Ljava/lang/String;
        //  0      154     1     uncompress  Z
        //  5      149     2     chars       [C
        //  14     140     3     car         Ljava/io/CharArrayReader;
        //  24     130     4     jr          Lorg/apache/bcel/classfile/Utility$JavaReader;
        //  33     121     5     bos         Ljava/io/ByteArrayOutputStream;
        //  51     103     6     ch          I
        //  75     79      7     bytes       [B
        //  97     54      8     gis         Ljava/util/zip/GZIPInputStream;
        //  106    45      9     tmp         [B
        //  109    42      10    count       I
        //  131    20      11    b           I
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
    
    static {
        Utility.wide = false;
        Utility.CHAR_MAP = new int[48];
        Utility.MAP_CHAR = new int[256];
        int j = 0;
        final int k = 0;
        for (int i = 65; i <= 90; ++i) {
            Utility.CHAR_MAP[j] = i;
            Utility.MAP_CHAR[i] = j;
            ++j;
        }
        for (int l = 103; l <= 122; ++l) {
            Utility.CHAR_MAP[j] = l;
            Utility.MAP_CHAR[l] = j;
            ++j;
        }
        Utility.CHAR_MAP[j] = 36;
        Utility.MAP_CHAR[36] = j;
        ++j;
        Utility.CHAR_MAP[j] = 95;
        Utility.MAP_CHAR[95] = j;
    }
    
    private static class JavaReader extends FilterReader
    {
        public JavaReader(final Reader in) {
            super(in);
        }
        
        public int read() throws IOException {
            final int b = super.in.read();
            if (b != 36) {
                return b;
            }
            final int i = super.in.read();
            if (i < 0) {
                return -1;
            }
            if ((i < 48 || i > 57) && (i < 97 || i > 102)) {
                return Utility.MAP_CHAR[i];
            }
            final int j = super.in.read();
            if (j < 0) {
                return -1;
            }
            final char[] tmp = { (char)i, (char)j };
            final int s = Integer.parseInt(new String(tmp), 16);
            return s;
        }
        
        public int read(final char[] cbuf, final int off, final int len) throws IOException {
            for (int i = 0; i < len; ++i) {
                cbuf[off + i] = (char)this.read();
            }
            return len;
        }
    }
    
    private static class JavaWriter extends FilterWriter
    {
        public JavaWriter(final Writer out) {
            super(out);
        }
        
        public void write(final int b) throws IOException {
            if (Utility.isJavaIdentifierPart((char)b) && b != 36) {
                super.out.write(b);
            }
            else {
                super.out.write(36);
                if (b >= 0 && b < 48) {
                    super.out.write(Utility.CHAR_MAP[b]);
                }
                else {
                    final char[] tmp = Integer.toHexString(b).toCharArray();
                    if (tmp.length == 1) {
                        super.out.write(48);
                        super.out.write(tmp[0]);
                    }
                    else {
                        super.out.write(tmp[0]);
                        super.out.write(tmp[1]);
                    }
                }
            }
        }
        
        public void write(final char[] cbuf, final int off, final int len) throws IOException {
            for (int i = 0; i < len; ++i) {
                this.write(cbuf[off + i]);
            }
        }
        
        public void write(final String str, final int off, final int len) throws IOException {
            this.write(str.toCharArray(), off, len);
        }
    }
}
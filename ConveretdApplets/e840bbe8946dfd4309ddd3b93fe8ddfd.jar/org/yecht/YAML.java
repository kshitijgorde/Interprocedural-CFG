// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class YAML
{
    public static final int BLOCK_FOLD = 10;
    public static final int BLOCK_LIT = 20;
    public static final int BLOCK_PLAIN = 30;
    public static final int NL_CHOMP = 40;
    public static final int NL_KEEP = 50;
    public static final int YAML_MAJOR = 1;
    public static final int YAML_MINOR = 0;
    public static final String YECHT_VERSION = "0.0.2";
    public static final String DOMAIN = "yaml.org,2002";
    public static final int ALLOC_CT = 8;
    public static final int BUFFERSIZE = 4096;
    public static final String DEFAULT_ANCHOR_FORMAT = "id%03d";
    public static final byte BYTE_FINISH = 0;
    public static final byte BYTE_DOCUMENT = 68;
    public static final byte BYTE_DIRECTIVE = 86;
    public static final byte BYTE_PAUSE = 80;
    public static final byte BYTE_MAPPING = 77;
    public static final byte BYTE_SEQUENCE = 81;
    public static final byte BYTE_END_BRANCH = 69;
    public static final byte BYTE_SCALAR = 83;
    public static final byte BYTE_CONTINUE = 67;
    public static final byte BYTE_NEWLINE = 78;
    public static final byte BYTE_NULLCHAR = 90;
    public static final byte BYTE_ANCHOR = 65;
    public static final byte BYTE_ALIAS = 82;
    public static final byte BYTE_TRANSFER = 84;
    public static final byte BYTE_COMMENT = 99;
    public static final byte BYTE_INDENT = 105;
    public static final byte BYTE_STYLE = 115;
    public static final byte BYTE_LINE_NUMBER = 35;
    public static final byte BYTE_WHOLE_SCALAR = 60;
    public static final byte BYTE_NOTICE = 33;
    public static final byte BYTE_SPAN = 41;
    public static final byte BYTE_ALLOC = 64;
    public static final byte BYTE_FLOW = 62;
    public static final byte BYTE_LITERAL = 124;
    public static final byte BYTE_BLOCK = 98;
    public static final byte BYTE_PLAIN = 112;
    public static final byte BYTE_INLINE_MAPPING = 123;
    public static final byte BYTE_INLINE_SEQUENCE = 91;
    public static final byte BYTE_SINGLE_QUOTED = 39;
    public static final byte BYTE_DOUBLE_QUOTED = 34;
    
    public static byte[] realloc(final byte[] input, final int size) {
        final byte[] newArray = new byte[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }
    
    public static long[] realloc(final long[] input, final int size) {
        final long[] newArray = new long[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }
    
    public static Level[] realloc(final Level[] input, final int size) {
        final Level[] newArray = new Level[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }
    
    public static Object[] realloc(final Object[] input, final int size) {
        final Object[] newArray = new Object[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }
    
    public static byte[] yaml2byte(final byte[] yamlstr) {
        final Parser parser = Parser.newParser();
        parser.str(Pointer.create(yamlstr, 0), null);
        parser.handler(new BytecodeNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(true);
        parser.taguriExpansion(true);
        final Bytestring sav = (Bytestring)parser.parse();
        if (null == sav) {
            return null;
        }
        final byte[] ret = new byte[Bytestring.strlen(sav.buffer) + 2];
        ret[0] = 68;
        ret[1] = 10;
        System.arraycopy(sav.buffer, 0, ret, 2, ret.length - 2);
        return ret;
    }
    
    public static void main(final String[] args) throws Exception {
        final byte[] yaml = "test: 1\nand: \"with new\\nline\\n\"\nalso: &3 three\nmore: *3".getBytes("ISO-8859-1");
        System.out.println("--- # YAML ");
        System.out.print(new String(yaml, "ISO-8859-1"));
        System.out.print("\n...\n");
        System.out.print(new String(yaml2byte(yaml), "ISO-8859-1"));
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import org.jruby.org.objectweb.asm.Attribute;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVisitor
{
    public static final String[] OPCODES;
    public static final String[] TYPES;
    public final List text;
    protected final StringBuffer buf;
    
    protected AbstractVisitor() {
        this.text = new ArrayList();
        this.buf = new StringBuffer();
    }
    
    public List getText() {
        return this.text;
    }
    
    public void print(final PrintWriter printWriter) {
        printList(printWriter, this.text);
    }
    
    public static void appendString(final StringBuffer sb, final String s) {
        sb.append('\"');
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\n') {
                sb.append("\\n");
            }
            else if (char1 == '\r') {
                sb.append("\\r");
            }
            else if (char1 == '\\') {
                sb.append("\\\\");
            }
            else if (char1 == '\"') {
                sb.append("\\\"");
            }
            else if (char1 < ' ' || char1 > '\u007f') {
                sb.append("\\u");
                if (char1 < '\u0010') {
                    sb.append("000");
                }
                else if (char1 < '\u0100') {
                    sb.append("00");
                }
                else if (char1 < '\u1000') {
                    sb.append('0');
                }
                sb.append(Integer.toString(char1, 16));
            }
            else {
                sb.append(char1);
            }
        }
        sb.append('\"');
    }
    
    static void printList(final PrintWriter printWriter, final List list) {
        for (int i = 0; i < list.size(); ++i) {
            final List value = list.get(i);
            if (value instanceof List) {
                printList(printWriter, value);
            }
            else {
                printWriter.print(value.toString());
            }
        }
    }
    
    public static Attribute[] getDefaultAttributes() {
        return new Attribute[0];
    }
    
    static {
        final String s = "NOP,ACONST_NULL,ICONST_M1,ICONST_0,ICONST_1,ICONST_2,ICONST_3,ICONST_4,ICONST_5,LCONST_0,LCONST_1,FCONST_0,FCONST_1,FCONST_2,DCONST_0,DCONST_1,BIPUSH,SIPUSH,LDC,,,ILOAD,LLOAD,FLOAD,DLOAD,ALOAD,,,,,,,,,,,,,,,,,,,,,IALOAD,LALOAD,FALOAD,DALOAD,AALOAD,BALOAD,CALOAD,SALOAD,ISTORE,LSTORE,FSTORE,DSTORE,ASTORE,,,,,,,,,,,,,,,,,,,,,IASTORE,LASTORE,FASTORE,DASTORE,AASTORE,BASTORE,CASTORE,SASTORE,POP,POP2,DUP,DUP_X1,DUP_X2,DUP2,DUP2_X1,DUP2_X2,SWAP,IADD,LADD,FADD,DADD,ISUB,LSUB,FSUB,DSUB,IMUL,LMUL,FMUL,DMUL,IDIV,LDIV,FDIV,DDIV,IREM,LREM,FREM,DREM,INEG,LNEG,FNEG,DNEG,ISHL,LSHL,ISHR,LSHR,IUSHR,LUSHR,IAND,LAND,IOR,LOR,IXOR,LXOR,IINC,I2L,I2F,I2D,L2I,L2F,L2D,F2I,F2L,F2D,D2I,D2L,D2F,I2B,I2C,I2S,LCMP,FCMPL,FCMPG,DCMPL,DCMPG,IFEQ,IFNE,IFLT,IFGE,IFGT,IFLE,IF_ICMPEQ,IF_ICMPNE,IF_ICMPLT,IF_ICMPGE,IF_ICMPGT,IF_ICMPLE,IF_ACMPEQ,IF_ACMPNE,GOTO,JSR,RET,TABLESWITCH,LOOKUPSWITCH,IRETURN,LRETURN,FRETURN,DRETURN,ARETURN,RETURN,GETSTATIC,PUTSTATIC,GETFIELD,PUTFIELD,INVOKEVIRTUAL,INVOKESPECIAL,INVOKESTATIC,INVOKEINTERFACE,INVOKEDYNAMIC,NEW,NEWARRAY,ANEWARRAY,ARRAYLENGTH,ATHROW,CHECKCAST,INSTANCEOF,MONITORENTER,MONITOREXIT,,MULTIANEWARRAY,IFNULL,IFNONNULL,";
        OPCODES = new String[200];
        int n = 0;
        int index;
        for (int n2 = 0; (index = s.indexOf(44, n2)) > 0; n2 = index + 1) {
            AbstractVisitor.OPCODES[n++] = ((n2 + 1 == index) ? null : s.substring(n2, index));
        }
        final String s2 = "T_BOOLEAN,T_CHAR,T_FLOAT,T_DOUBLE,T_BYTE,T_SHORT,T_INT,T_LONG,";
        TYPES = new String[12];
        int n3 = 0;
        int n4 = 4;
        int index2;
        while ((index2 = s2.indexOf(44, n3)) > 0) {
            AbstractVisitor.TYPES[n4++] = s2.substring(n3, index2);
            n3 = index2 + 1;
        }
    }
}

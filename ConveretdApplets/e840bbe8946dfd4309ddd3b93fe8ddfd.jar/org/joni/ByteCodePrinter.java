// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.exception.InternalException;
import org.joni.ast.CClassNode;
import org.joni.constants.OPCode;
import org.jcodings.Encoding;

class ByteCodePrinter
{
    int[] code;
    int codeLength;
    Object[] operands;
    int operantCount;
    Encoding enc;
    WarnCallback warnings;
    
    public ByteCodePrinter(final Regex regex) {
        this.code = regex.code;
        this.codeLength = regex.codeLength;
        this.operands = regex.operands;
        this.operantCount = regex.operandLength;
        this.enc = regex.enc;
        this.warnings = regex.warnings;
    }
    
    public String byteCodeListToString() {
        return this.compiledByteCodeListToString();
    }
    
    private void pString(final StringBuilder sb, int len, int s) {
        sb.append(":");
        while (len-- > 0) {
            sb.append(new String(new byte[] { (byte)this.code[s++] }));
        }
    }
    
    private void pLenString(final StringBuilder sb, final int len, final int mbLen, int s) {
        int x = len * mbLen;
        sb.append(":" + len + ":");
        while (x-- > 0) {
            sb.append(new String(new byte[] { (byte)this.code[s++] }));
        }
    }
    
    public int compiledByteCodeToString(final StringBuilder sb, int bp) {
        sb.append("[" + OPCode.OpCodeNames[this.code[bp]]);
        final int argType = OPCode.OpCodeArgTypes[this.code[bp]];
        final int ip = bp;
        if (argType != -1) {
            ++bp;
            switch (argType) {
                case 1: {
                    sb.append(":(" + this.code[bp] + ")");
                    ++bp;
                    break;
                }
                case 2: {
                    sb.append(":(" + this.code[bp] + ")");
                    ++bp;
                    break;
                }
                case 3: {
                    sb.append(":" + this.code[bp]);
                    ++bp;
                    break;
                }
                case 4: {
                    sb.append(":" + this.code[bp]);
                    ++bp;
                    break;
                }
                case 5: {
                    sb.append(":" + this.code[bp]);
                    ++bp;
                    break;
                }
                case 6: {
                    sb.append(":" + this.code[bp]);
                    bp += 2;
                    break;
                }
            }
        }
        else {
            switch (this.code[bp++]) {
                case 2:
                case 27:
                case 28:
                case 92:
                case 93: {
                    this.pString(sb, 1, bp++);
                    break;
                }
                case 3: {
                    this.pString(sb, 2, bp);
                    bp += 2;
                    break;
                }
                case 4: {
                    this.pString(sb, 3, bp);
                    bp += 3;
                    break;
                }
                case 5: {
                    this.pString(sb, 4, bp);
                    bp += 4;
                    break;
                }
                case 6: {
                    this.pString(sb, 5, bp);
                    bp += 5;
                    break;
                }
                case 7: {
                    final int len = this.code[bp];
                    ++bp;
                    this.pLenString(sb, len, 1, bp);
                    bp += len;
                    break;
                }
                case 8: {
                    this.pString(sb, 2, bp);
                    bp += 2;
                    break;
                }
                case 9: {
                    this.pString(sb, 4, bp);
                    bp += 4;
                    break;
                }
                case 10: {
                    this.pString(sb, 6, bp);
                    bp += 6;
                    break;
                }
                case 11: {
                    final int len = this.code[bp];
                    ++bp;
                    this.pLenString(sb, len, 2, bp);
                    bp += len * 2;
                    break;
                }
                case 12: {
                    final int len = this.code[bp];
                    ++bp;
                    this.pLenString(sb, len, 3, bp);
                    bp += len * 3;
                    break;
                }
                case 13: {
                    final int mbLen = this.code[bp];
                    ++bp;
                    final int len = this.code[bp];
                    ++bp;
                    sb.append(":" + mbLen + ":" + len + ":");
                    int n = len * mbLen;
                    while (n-- > 0) {
                        sb.append(new String(new byte[] { (byte)this.code[bp++] }));
                    }
                    break;
                }
                case 14:
                case 105: {
                    final int MAX_CHAR_LENGTH = 6;
                    final byte[] bytes = new byte[6];
                    for (int i = 0; bp + i < this.code.length && i < 6; ++i) {
                        bytes[i] = (byte)this.code[bp + i];
                    }
                    final int len = this.enc.length(bytes, 0, 6);
                    this.pString(sb, len, bp);
                    bp += len;
                    break;
                }
                case 15:
                case 106: {
                    final int len = this.code[bp];
                    ++bp;
                    this.pLenString(sb, len, 1, bp);
                    bp += len;
                    break;
                }
                case 16:
                case 96: {
                    final BitSet bs = new BitSet();
                    System.arraycopy(this.code, bp, bs.bits, 0, 8);
                    final int n = bs.numOn();
                    bp += 8;
                    sb.append(":" + n);
                    break;
                }
                case 19:
                case 97: {
                    final BitSet bs = new BitSet();
                    System.arraycopy(this.code, bp, bs.bits, 0, 8);
                    final int n = bs.numOn();
                    bp += 8;
                    sb.append(":" + n);
                    break;
                }
                case 17:
                case 20: {
                    final int len = this.code[bp];
                    ++bp;
                    final int cod = this.code[bp];
                    bp += len;
                    sb.append(":" + cod + ":" + len);
                    break;
                }
                case 18:
                case 21: {
                    final BitSet bs = new BitSet();
                    System.arraycopy(this.code, bp, bs.bits, 0, 8);
                    final int n = bs.numOn();
                    bp += 8;
                    final int len = this.code[bp];
                    ++bp;
                    final int cod = this.code[bp];
                    bp += len;
                    sb.append(":" + n + ":" + cod + ":" + len);
                    break;
                }
                case 22: {
                    final CClassNode cc = (CClassNode)this.operands[this.code[bp]];
                    ++bp;
                    final int n = cc.bs.numOn();
                    sb.append(":" + cc + ":" + n);
                    break;
                }
                case 44: {
                    final int mem = this.code[bp];
                    ++bp;
                    sb.append(":" + mem);
                    break;
                }
                case 45:
                case 46: {
                    sb.append(" ");
                    final int len = this.code[bp];
                    ++bp;
                    for (int i = 0; i < len; ++i) {
                        final int mem = this.code[bp];
                        ++bp;
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append(mem);
                    }
                    break;
                }
                case 47: {
                    final int option = this.code[bp];
                    ++bp;
                    sb.append(":" + option);
                    final int level = this.code[bp];
                    ++bp;
                    sb.append(":" + level);
                    sb.append(" ");
                    final int len = this.code[bp];
                    ++bp;
                    for (int j = 0; j < len; ++j) {
                        final int mem = this.code[bp];
                        ++bp;
                        if (j > 0) {
                            sb.append(", ");
                        }
                        sb.append(mem);
                    }
                    break;
                }
                case 60:
                case 61: {
                    final int mem = this.code[bp];
                    ++bp;
                    final int addr = this.code[bp];
                    ++bp;
                    sb.append(":" + mem + ":" + addr);
                    break;
                }
                case 58:
                case 59: {
                    final int addr = this.code[bp];
                    ++bp;
                    sb.append(":(" + addr + ")");
                    this.pString(sb, 1, bp);
                    ++bp;
                    break;
                }
                case 76:
                case 104: {
                    final int len = this.code[bp];
                    ++bp;
                    sb.append(":" + len);
                    break;
                }
                case 77: {
                    final int addr = this.code[bp];
                    ++bp;
                    final int len = this.code[bp];
                    ++bp;
                    sb.append(":" + len + ":(" + addr + ")");
                    break;
                }
                case 81:
                case 82: {
                    final int scn = this.code[bp];
                    ++bp;
                    final int addr = this.code[bp];
                    ++bp;
                    sb.append(":" + scn + ":(" + addr + ")");
                    break;
                }
                default: {
                    throw new InternalException("undefined code: " + this.code[--bp]);
                }
            }
        }
        sb.append("]");
        return bp;
    }
    
    private String compiledByteCodeListToString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("code length: " + this.codeLength + "\n");
        int ncode = 0;
        for (int bp = 0, end = this.codeLength; bp < end; bp = this.compiledByteCodeToString(sb, bp)) {
            ++ncode;
            if (bp > 0) {
                sb.append((ncode % 5 == 0) ? "\n" : " ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.jcodings.CodeRange;
import org.joni.exception.ValueException;
import org.joni.constants.CCVALTYPE;
import org.joni.exception.SyntaxException;
import org.joni.constants.CCSTATE;
import org.joni.exception.InternalException;
import org.jcodings.exception.EncodingException;
import org.jcodings.IntHolder;
import org.joni.ScanEnvironment;
import org.jcodings.Encoding;
import org.joni.CodeRangeBuffer;
import org.joni.BitSet;

public final class CClassNode extends Node
{
    private static final int FLAG_NCCLASS_NOT = 1;
    private static final int FLAG_NCCLASS_SHARE = 2;
    int flags;
    public final BitSet bs;
    public CodeRangeBuffer mbuf;
    private int ctype;
    private Encoding enc;
    
    public CClassNode() {
        this.bs = new BitSet();
    }
    
    public CClassNode(final int ctype, final Encoding enc, final boolean not, final int sbOut, final int[] ranges) {
        this(not, sbOut, ranges);
        this.ctype = ctype;
        this.enc = enc;
    }
    
    public CClassNode(final boolean not, final int sbOut, final int[] ranges) {
        this.bs = new BitSet();
        if (not) {
            this.setNot();
        }
        if (sbOut > 0 && ranges != null) {
            for (int n = ranges[0], i = 0; i < n; ++i) {
                final int from = ranges[i * 2 + 1];
                for (int to = ranges[i * 2 + 2], j = from; j <= to; ++j) {
                    if (j >= sbOut) {
                        this.setupBuffer(ranges);
                        return;
                    }
                    this.bs.set(j);
                }
            }
        }
        this.setupBuffer(ranges);
    }
    
    public int getType() {
        return 1;
    }
    
    public String getName() {
        return "Character Class";
    }
    
    public boolean equals(final Object other) {
        if (!(other instanceof CClassNode)) {
            return false;
        }
        final CClassNode cc = (CClassNode)other;
        return this.ctype == cc.ctype && this.isNot() == cc.isNot() && this.enc == cc.enc;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder();
        value.append("\n  flags: " + this.flagsToString());
        value.append("\n  bs: " + Node.pad(this.bs, level + 1));
        value.append("\n  mbuf: " + Node.pad(this.mbuf, level + 1));
        return value.toString();
    }
    
    public String flagsToString() {
        final StringBuilder flags = new StringBuilder();
        if (this.isNot()) {
            flags.append("NOT ");
        }
        if (this.isShare()) {
            flags.append("SHARE ");
        }
        return flags.toString();
    }
    
    private void setupBuffer(final int[] ranges) {
        if (ranges != null) {
            if (ranges[0] == 0) {
                return;
            }
            this.mbuf = new CodeRangeBuffer(ranges);
        }
    }
    
    public boolean isEmpty() {
        return this.mbuf == null && this.bs.isEmpty();
    }
    
    public void addCodeRangeToBuf(final int from, final int to) {
        this.mbuf = CodeRangeBuffer.addCodeRangeToBuff(this.mbuf, from, to);
    }
    
    public void addCodeRange(final ScanEnvironment env, final int from, final int to) {
        this.mbuf = CodeRangeBuffer.addCodeRange(this.mbuf, env, from, to);
    }
    
    public void addAllMultiByteRange(final Encoding enc) {
        this.mbuf = CodeRangeBuffer.addAllMultiByteRange(enc, this.mbuf);
    }
    
    public void clearNotFlag(final Encoding enc) {
        if (this.isNot()) {
            this.bs.invert();
            if (!enc.isSingleByte()) {
                this.mbuf = CodeRangeBuffer.notCodeRangeBuff(enc, this.mbuf);
            }
            this.clearNot();
        }
    }
    
    public void and(final CClassNode other, final Encoding enc) {
        final boolean not1 = this.isNot();
        BitSet bsr1 = this.bs;
        final CodeRangeBuffer buf1 = this.mbuf;
        final boolean not2 = other.isNot();
        BitSet bsr2 = other.bs;
        final CodeRangeBuffer buf2 = other.mbuf;
        if (not1) {
            final BitSet bs1 = new BitSet();
            bsr1.invertTo(bs1);
            bsr1 = bs1;
        }
        if (not2) {
            final BitSet bs2 = new BitSet();
            bsr2.invertTo(bs2);
            bsr2 = bs2;
        }
        bsr1.and(bsr2);
        if (bsr1 != this.bs) {
            this.bs.copy(bsr1);
            bsr1 = this.bs;
        }
        if (not1) {
            this.bs.invert();
        }
        CodeRangeBuffer pbuf = null;
        if (!enc.isSingleByte()) {
            if (not1 && not2) {
                pbuf = CodeRangeBuffer.orCodeRangeBuff(enc, buf1, false, buf2, false);
            }
            else {
                pbuf = CodeRangeBuffer.andCodeRangeBuff(buf1, not1, buf2, not2);
                if (not1) {
                    pbuf = CodeRangeBuffer.notCodeRangeBuff(enc, pbuf);
                }
            }
            this.mbuf = pbuf;
        }
    }
    
    public void or(final CClassNode other, final Encoding enc) {
        final boolean not1 = this.isNot();
        BitSet bsr1 = this.bs;
        final CodeRangeBuffer buf1 = this.mbuf;
        final boolean not2 = other.isNot();
        BitSet bsr2 = other.bs;
        final CodeRangeBuffer buf2 = other.mbuf;
        if (not1) {
            final BitSet bs1 = new BitSet();
            bsr1.invertTo(bs1);
            bsr1 = bs1;
        }
        if (not2) {
            final BitSet bs2 = new BitSet();
            bsr2.invertTo(bs2);
            bsr2 = bs2;
        }
        bsr1.or(bsr2);
        if (bsr1 != this.bs) {
            this.bs.copy(bsr1);
            bsr1 = this.bs;
        }
        if (not1) {
            this.bs.invert();
        }
        if (!enc.isSingleByte()) {
            CodeRangeBuffer pbuf = null;
            if (not1 && not2) {
                pbuf = CodeRangeBuffer.andCodeRangeBuff(buf1, false, buf2, false);
            }
            else {
                pbuf = CodeRangeBuffer.orCodeRangeBuff(enc, buf1, not1, buf2, not2);
                if (not1) {
                    pbuf = CodeRangeBuffer.notCodeRangeBuff(enc, pbuf);
                }
            }
            this.mbuf = pbuf;
        }
    }
    
    public void addCTypeByRange(final int ctype, final boolean not, final Encoding enc, final int sbOut, final int[] mbr) {
        final int n = mbr[0];
        if (!not) {
            for (int i = 0; i < n; ++i) {
                for (int j = mbr[i * 2 + 1]; j <= mbr[i * 2 + 2]; ++j) {
                    if (j >= sbOut) {
                        if (j == mbr[i * 2 + 2]) {
                            ++i;
                        }
                        else if (j > mbr[i * 2 + 1]) {
                            this.addCodeRangeToBuf(j, mbr[i * 2 + 2]);
                            ++i;
                        }
                        while (i < n) {
                            this.addCodeRangeToBuf(mbr[2 * i + 1], mbr[2 * i + 2]);
                            ++i;
                        }
                        return;
                    }
                    this.bs.set(j);
                }
            }
            for (int i = 0; i < n; ++i) {
                this.addCodeRangeToBuf(mbr[2 * i + 1], mbr[2 * i + 2]);
            }
        }
        else {
            int prev = 0;
            for (int k = 0; k < n; ++k) {
                for (int l = prev; l < mbr[2 * k + 1]; ++l) {
                    if (l >= sbOut) {
                        prev = sbOut;
                        for (k = 0; k < n; ++k) {
                            if (prev < mbr[2 * k + 1]) {
                                this.addCodeRangeToBuf(prev, mbr[k * 2 + 1] - 1);
                            }
                            prev = mbr[k * 2 + 2] + 1;
                        }
                        if (prev < Integer.MAX_VALUE) {
                            this.addCodeRangeToBuf(prev, Integer.MAX_VALUE);
                        }
                        return;
                    }
                    this.bs.set(l);
                }
                prev = mbr[2 * k + 2] + 1;
            }
            for (int j = prev; j < sbOut; ++j) {
                this.bs.set(j);
            }
            prev = sbOut;
            for (int k = 0; k < n; ++k) {
                if (prev < mbr[2 * k + 1]) {
                    this.addCodeRangeToBuf(prev, mbr[k * 2 + 1] - 1);
                }
                prev = mbr[k * 2 + 2] + 1;
            }
            if (prev < Integer.MAX_VALUE) {
                this.addCodeRangeToBuf(prev, Integer.MAX_VALUE);
            }
        }
    }
    
    public void addCType(final int ctype, final boolean not, final ScanEnvironment env, final IntHolder sbOut) {
        final Encoding enc = env.enc;
        final int[] ranges = enc.ctypeCodeRange(ctype, sbOut);
        if (ranges != null) {
            this.addCTypeByRange(ctype, not, enc, sbOut.value, ranges);
            return;
        }
        switch (ctype) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14: {
                if (not) {
                    for (int c = 0; c < 256; ++c) {
                        if (!enc.isCodeCType(c, ctype)) {
                            this.bs.set(c);
                        }
                    }
                    this.addAllMultiByteRange(enc);
                    break;
                }
                for (int c = 0; c < 256; ++c) {
                    if (enc.isCodeCType(c, ctype)) {
                        this.bs.set(c);
                    }
                }
                break;
            }
            case 5:
            case 7: {
                if (not) {
                    for (int c = 0; c < 256; ++c) {
                        if (!enc.isCodeCType(c, ctype)) {
                            this.bs.set(c);
                        }
                    }
                    break;
                }
                for (int c = 0; c < 256; ++c) {
                    if (enc.isCodeCType(c, ctype)) {
                        this.bs.set(c);
                    }
                }
                this.addAllMultiByteRange(enc);
                break;
            }
            case 12: {
                if (!not) {
                    for (int c = 0; c < 256; ++c) {
                        if (enc.isSbWord(c)) {
                            this.bs.set(c);
                        }
                    }
                    this.addAllMultiByteRange(enc);
                    break;
                }
                for (int c = 0; c < 256; ++c) {
                    try {
                        if (enc.codeToMbcLength(c) > 0 && !enc.isWord(c)) {
                            this.bs.set(c);
                        }
                    }
                    catch (EncodingException ex) {}
                }
                break;
            }
            default: {
                throw new InternalException("internal parser error (bug)");
            }
        }
    }
    
    public void nextStateClass(final CCStateArg arg, final ScanEnvironment env) {
        if (arg.state == CCSTATE.RANGE) {
            throw new SyntaxException("char-class value at end of range");
        }
        if (arg.state == CCSTATE.VALUE && arg.type != CCVALTYPE.CLASS) {
            if (arg.type == CCVALTYPE.SB) {
                this.bs.set(arg.vs);
            }
            else if (arg.type == CCVALTYPE.CODE_POINT) {
                this.addCodeRange(env, arg.vs, arg.vs);
            }
        }
        arg.state = CCSTATE.VALUE;
        arg.type = CCVALTYPE.CLASS;
    }
    
    public void nextStateValue(final CCStateArg arg, final ScanEnvironment env) {
        switch (arg.state) {
            case VALUE: {
                if (arg.type == CCVALTYPE.SB) {
                    if (arg.vs > 255) {
                        throw new ValueException("invalid code point value");
                    }
                    this.bs.set(arg.vs);
                    break;
                }
                else {
                    if (arg.type == CCVALTYPE.CODE_POINT) {
                        this.addCodeRange(env, arg.vs, arg.vs);
                        break;
                    }
                    break;
                }
                break;
            }
            case RANGE: {
                if (arg.inType == arg.type) {
                    if (arg.inType == CCVALTYPE.SB) {
                        if (arg.vs > 255 || arg.v > 255) {
                            throw new ValueException("invalid code point value");
                        }
                        if (arg.vs > arg.v) {
                            if (env.syntax.allowEmptyRangeInCC()) {
                                arg.state = CCSTATE.COMPLETE;
                                break;
                            }
                            throw new ValueException("empty range in char class");
                        }
                        else {
                            this.bs.setRange(arg.vs, arg.v);
                        }
                    }
                    else {
                        this.addCodeRange(env, arg.vs, arg.v);
                    }
                }
                else if (arg.vs > arg.v) {
                    if (env.syntax.allowEmptyRangeInCC()) {
                        arg.state = CCSTATE.COMPLETE;
                        break;
                    }
                    throw new ValueException("empty range in char class");
                }
                else {
                    this.bs.setRange(arg.vs, (arg.v < 255) ? arg.v : 255);
                    this.addCodeRange(env, arg.vs, arg.v);
                }
                arg.state = CCSTATE.COMPLETE;
                break;
            }
            case COMPLETE:
            case START: {
                arg.state = CCSTATE.VALUE;
                break;
            }
        }
        arg.vsIsRaw = arg.vIsRaw;
        arg.vs = arg.v;
        arg.type = arg.inType;
    }
    
    public boolean isCodeInCCLength(final int encLength, final int code) {
        boolean found;
        if (encLength > 1 || code >= 256) {
            found = (this.mbuf != null && CodeRange.isInCodeRange(this.mbuf.getCodeRange(), code));
        }
        else {
            found = this.bs.at(code);
        }
        if (this.isNot()) {
            return !found;
        }
        return found;
    }
    
    public boolean isCodeInCC(final Encoding enc, final int code) {
        int len;
        if (enc.minLength() > 1) {
            len = 2;
        }
        else {
            len = enc.codeToMbcLength(code);
        }
        return this.isCodeInCCLength(len, code);
    }
    
    public void setNot() {
        this.flags |= 0x1;
    }
    
    public void clearNot() {
        this.flags &= 0xFFFFFFFE;
    }
    
    public boolean isNot() {
        return (this.flags & 0x1) != 0x0;
    }
    
    public void setShare() {
        this.flags |= 0x2;
    }
    
    public void clearShare() {
        this.flags &= 0xFFFFFFFD;
    }
    
    public boolean isShare() {
        return (this.flags & 0x2) != 0x0;
    }
    
    public static final class CCStateArg
    {
        public int v;
        public int vs;
        public boolean vsIsRaw;
        public boolean vIsRaw;
        public CCVALTYPE inType;
        public CCVALTYPE type;
        public CCSTATE state;
    }
}

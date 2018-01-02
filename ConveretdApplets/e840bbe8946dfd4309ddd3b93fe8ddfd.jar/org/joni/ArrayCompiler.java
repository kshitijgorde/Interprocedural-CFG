// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.AnchorNode;
import org.joni.ast.EncloseNode;
import org.joni.ast.QuantifierNode;
import org.joni.ast.BackRefNode;
import org.joni.ast.CallNode;
import org.joni.ast.CTypeNode;
import org.joni.ast.CClassNode;
import org.joni.ast.StringNode;
import org.joni.ast.Node;
import org.joni.ast.ConsAltNode;

final class ArrayCompiler extends Compiler
{
    private static final int REPEAT_RANGE_ALLOC = 8;
    private static final int QUANTIFIER_EXPAND_LIMIT_SIZE = 50;
    
    ArrayCompiler(final Analyser analyser) {
        super(analyser);
    }
    
    protected final void prepare() {
        this.regex.code = new int[(this.analyser.stop - this.analyser.p) * 2 + 1];
        this.regex.codeLength = 0;
    }
    
    protected final void finish() {
        this.addOpcode(1);
        this.addOpcode(0);
        if (this.analyser.env.unsetAddrList != null) {
            this.analyser.env.unsetAddrList.fix(this.regex);
            this.analyser.env.unsetAddrList = null;
        }
        this.regex.factory = MatcherFactory.DEFAULT;
    }
    
    protected void compileAltNode(final ConsAltNode node) {
        ConsAltNode aln = node;
        int len = 0;
        do {
            len += this.compileLengthTree(aln.car);
            if (aln.cdr != null) {
                len += 4;
            }
        } while ((aln = aln.cdr) != null);
        final int pos = this.regex.codeLength + len;
        aln = node;
        do {
            len = this.compileLengthTree(aln.car);
            if (aln.cdr != null) {
                this.addOpcodeRelAddr(56, len + 2);
            }
            this.compileTree(aln.car);
            if (aln.cdr != null) {
                len = pos - (this.regex.codeLength + 2);
                this.addOpcodeRelAddr(55, len);
            }
        } while ((aln = aln.cdr) != null);
    }
    
    private boolean isNeedStrLenOpExact(final int op) {
        return op == 7 || op == 11 || op == 12 || op == 13 || op == 15 || op == 106;
    }
    
    private int selectStrOpcode(final int mbLength, final int strLength, final boolean ignoreCase) {
        int op = 0;
        if (ignoreCase) {
            switch (strLength) {
                case 1: {
                    op = ((this.enc.toLowerCaseTable() != null) ? 105 : 14);
                    break;
                }
                default: {
                    op = ((this.enc.toLowerCaseTable() != null) ? 106 : 15);
                    break;
                }
            }
        }
        else {
            switch (mbLength) {
                case 1: {
                    switch (strLength) {
                        case 1: {
                            op = 2;
                            return op;
                        }
                        case 2: {
                            op = 3;
                            return op;
                        }
                        case 3: {
                            op = 4;
                            return op;
                        }
                        case 4: {
                            op = 5;
                            return op;
                        }
                        case 5: {
                            op = 6;
                            return op;
                        }
                        default: {
                            op = 7;
                            return op;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (strLength) {
                        case 1: {
                            op = 8;
                            return op;
                        }
                        case 2: {
                            op = 9;
                            return op;
                        }
                        case 3: {
                            op = 10;
                            return op;
                        }
                        default: {
                            op = 11;
                            return op;
                        }
                    }
                    break;
                }
                case 3: {
                    op = 12;
                    break;
                }
            }
            op = 13;
        }
        return op;
    }
    
    private void compileTreeEmptyCheck(final Node node, final int emptyInfo) {
        final int savedNumNullCheck = this.regex.numNullCheck;
        if (emptyInfo != 0) {
            this.addOpcode(66);
            this.addMemNum(this.regex.numNullCheck);
            final Regex regex = this.regex;
            ++regex.numNullCheck;
        }
        this.compileTree(node);
        if (emptyInfo != 0) {
            switch (emptyInfo) {
                case 1: {
                    this.addOpcode(67);
                    break;
                }
                case 2: {
                    this.addOpcode(68);
                    break;
                }
                case 3: {
                    this.addOpcode(69);
                    break;
                }
            }
            this.addMemNum(savedNumNullCheck);
        }
    }
    
    private int addCompileStringlength(final byte[] bytes, final int p, final int mbLength, final int strLength, final boolean ignoreCase) {
        final int op = this.selectStrOpcode(mbLength, strLength, ignoreCase);
        int len = 1;
        if (op == 13) {
            ++len;
        }
        if (this.isNeedStrLenOpExact(op)) {
            ++len;
        }
        len += mbLength * strLength;
        return len;
    }
    
    protected final void addCompileString(final byte[] bytes, final int p, final int mbLength, final int strLength, final boolean ignoreCase) {
        final int op = this.selectStrOpcode(mbLength, strLength, ignoreCase);
        this.addOpcode(op);
        if (op == 13) {
            this.addLength(mbLength);
        }
        if (this.isNeedStrLenOpExact(op)) {
            if (op == 15 || op == 106) {
                this.addLength(mbLength * strLength);
            }
            else {
                this.addLength(strLength);
            }
        }
        this.addBytes(bytes, p, mbLength * strLength);
    }
    
    private int compileLengthStringNode(final Node node) {
        final StringNode sn = (StringNode)node;
        if (sn.length() <= 0) {
            return 0;
        }
        final boolean ambig = sn.isAmbig();
        int p;
        int prev = p = sn.p;
        final int end = sn.end;
        final byte[] bytes = sn.bytes;
        int prevLen = this.enc.length(bytes, p, end);
        p += prevLen;
        int slen = 1;
        int rlen = 0;
        while (p < end) {
            final int len = this.enc.length(bytes, p, end);
            if (len == prevLen) {
                ++slen;
            }
            else {
                final int r = this.addCompileStringlength(bytes, prev, prevLen, slen, ambig);
                rlen += r;
                prev = p;
                slen = 1;
                prevLen = len;
            }
            p += len;
        }
        final int r2 = this.addCompileStringlength(bytes, prev, prevLen, slen, ambig);
        rlen += r2;
        return rlen;
    }
    
    private int compileLengthStringRawNode(final StringNode sn) {
        if (sn.length() <= 0) {
            return 0;
        }
        return this.addCompileStringlength(sn.bytes, sn.p, 1, sn.length(), false);
    }
    
    private void addMultiByteCClass(final CodeRangeBuffer mbuf) {
        this.addLength(mbuf.used);
        this.addInts(mbuf.p, mbuf.used);
    }
    
    private int compileLengthCClassNode(final CClassNode cc) {
        if (cc.isShare()) {
            return 2;
        }
        int len;
        if (cc.mbuf == null) {
            len = 9;
        }
        else {
            if (this.enc.minLength() > 1 || cc.bs.isEmpty()) {
                len = 1;
            }
            else {
                len = 9;
            }
            len += 1 + cc.mbuf.used;
        }
        return len;
    }
    
    protected void compileCClassNode(final CClassNode cc) {
        if (cc.isShare()) {
            this.addOpcode(22);
            this.addPointer(cc);
            return;
        }
        if (cc.mbuf == null) {
            if (cc.isNot()) {
                this.addOpcode(this.enc.isSingleByte() ? 97 : 19);
            }
            else {
                this.addOpcode(this.enc.isSingleByte() ? 96 : 16);
            }
            this.addInts(cc.bs.bits, 8);
        }
        else if (this.enc.minLength() > 1 || cc.bs.isEmpty()) {
            if (cc.isNot()) {
                this.addOpcode(20);
            }
            else {
                this.addOpcode(17);
            }
            this.addMultiByteCClass(cc.mbuf);
        }
        else {
            if (cc.isNot()) {
                this.addOpcode(21);
            }
            else {
                this.addOpcode(18);
            }
            this.addInts(cc.bs.bits, 8);
            this.addMultiByteCClass(cc.mbuf);
        }
    }
    
    protected void compileCTypeNode(final CTypeNode node) {
        switch (node.ctype) {
            case 12: {
                int op;
                if (node.not) {
                    op = (this.enc.isSingleByte() ? 99 : 30);
                }
                else {
                    op = (this.enc.isSingleByte() ? 98 : 29);
                }
                this.addOpcode(op);
            }
            default: {
                this.newInternalException("internal parser error (bug)");
            }
        }
    }
    
    protected void compileAnyCharNode() {
        if (Option.isMultiline(this.regex.options)) {
            this.addOpcode(this.enc.isSingleByte() ? 89 : 24);
        }
        else {
            this.addOpcode(this.enc.isSingleByte() ? 88 : 23);
        }
    }
    
    protected void compileCallNode(final CallNode node) {
        this.addOpcode(79);
        node.unsetAddrList.add(this.regex.codeLength, node.target);
        this.addAbsAddr(0);
    }
    
    protected void compileBackrefNode(final BackRefNode node) {
        if (node.isNestLevel()) {
            this.addOpcode(47);
            this.addOption(this.regex.options & 0x1);
            this.addLength(node.nestLevel);
            this.addLength(node.backNum);
            for (int i = node.backNum - 1; i >= 0; --i) {
                this.addMemNum(node.back[i]);
            }
            return;
        }
        if (node.backNum == 1) {
            if (Option.isIgnoreCase(this.regex.options)) {
                this.addOpcode(44);
                this.addMemNum(node.back[0]);
            }
            else {
                switch (node.back[0]) {
                    case 1: {
                        this.addOpcode(41);
                        break;
                    }
                    case 2: {
                        this.addOpcode(42);
                        break;
                    }
                    default: {
                        this.addOpcode(43);
                        this.addOpcode(node.back[0]);
                        break;
                    }
                }
            }
        }
        else {
            if (Option.isIgnoreCase(this.regex.options)) {
                this.addOpcode(46);
            }
            else {
                this.addOpcode(45);
            }
            this.addLength(node.backNum);
            for (int i = node.backNum - 1; i >= 0; --i) {
                this.addMemNum(node.back[i]);
            }
        }
    }
    
    private void entryRepeatRange(final int id, final int lower, final int upper) {
        if (this.regex.repeatRangeLo == null) {
            this.regex.repeatRangeLo = new int[8];
            this.regex.repeatRangeHi = new int[8];
        }
        else if (id >= this.regex.repeatRangeLo.length) {
            int[] tmp = new int[this.regex.repeatRangeLo.length + 8];
            System.arraycopy(this.regex.repeatRangeLo, 0, tmp, 0, this.regex.repeatRangeLo.length);
            this.regex.repeatRangeLo = tmp;
            tmp = new int[this.regex.repeatRangeHi.length + 8];
            System.arraycopy(this.regex.repeatRangeHi, 0, tmp, 0, this.regex.repeatRangeHi.length);
            this.regex.repeatRangeHi = tmp;
        }
        this.regex.repeatRangeLo[id] = lower;
        this.regex.repeatRangeHi[id] = (QuantifierNode.isRepeatInfinite(upper) ? Integer.MAX_VALUE : upper);
    }
    
    private void compileRangeRepeatNode(final QuantifierNode qn, final int targetLen, final int emptyInfo) {
        final int numRepeat = this.regex.numRepeat;
        this.addOpcode(qn.greedy ? 60 : 61);
        this.addMemNum(numRepeat);
        final Regex regex = this.regex;
        ++regex.numRepeat;
        this.addRelAddr(targetLen + 2);
        this.entryRepeatRange(numRepeat, qn.lower, qn.upper);
        this.compileTreeEmptyCheck(qn.target, emptyInfo);
        if (this.regex.numCall > 0 || qn.isInRepeat()) {
            this.addOpcode(qn.greedy ? 64 : 65);
        }
        else {
            this.addOpcode(qn.greedy ? 62 : 63);
        }
        this.addMemNum(numRepeat);
    }
    
    private static boolean cknOn(final int ckn) {
        return ckn > 0;
    }
    
    private int compileCECLengthQuantifierNode(final QuantifierNode qn) {
        final boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
        final int emptyInfo = qn.targetEmptyInfo;
        final int tlen = this.compileLengthTree(qn.target);
        final int ckn = (this.regex.numCombExpCheck > 0) ? qn.combExpCheckNum : 0;
        final int cklen = cknOn(ckn) ? 1 : 0;
        if (qn.target.getType() != 3 || !qn.greedy || !infinite) {
            int modTLen;
            if (emptyInfo != 0) {
                modTLen = tlen + 4;
            }
            else {
                modTLen = tlen;
            }
            int len;
            if (infinite && qn.lower <= 1) {
                if (qn.greedy) {
                    if (qn.lower == 1) {
                        len = 2;
                    }
                    else {
                        len = 0;
                    }
                    len += 2 + cklen + modTLen + 2;
                }
                else {
                    if (qn.lower == 0) {
                        len = 2;
                    }
                    else {
                        len = 0;
                    }
                    len += modTLen + 2 + cklen;
                }
            }
            else if (qn.upper == 0) {
                if (qn.isRefered) {
                    len = 2 + tlen;
                }
                else {
                    len = 0;
                }
            }
            else if (qn.upper == 1 && qn.greedy) {
                if (qn.lower == 0) {
                    if (cknOn(ckn)) {
                        len = 3 + tlen;
                    }
                    else {
                        len = 2 + tlen;
                    }
                }
                else {
                    len = tlen;
                }
            }
            else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
                len = 2 + cklen + 2 + tlen;
            }
            else {
                len = 2 + modTLen + 1 + 1 + 1;
                if (cknOn(ckn)) {
                    len += 2;
                }
            }
            return len;
        }
        if (qn.nextHeadExact != null && !cknOn(ckn)) {
            return 2 + tlen * qn.lower + cklen;
        }
        return 1 + tlen * qn.lower + cklen;
    }
    
    protected void compileCECQuantifierNode(final QuantifierNode qn) {
        final boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
        final int emptyInfo = qn.targetEmptyInfo;
        final int tlen = this.compileLengthTree(qn.target);
        final int ckn = (this.regex.numCombExpCheck > 0) ? qn.combExpCheckNum : 0;
        if (!qn.isAnyCharStar()) {
            int modTLen;
            if (emptyInfo != 0) {
                modTLen = tlen + 4;
            }
            else {
                modTLen = tlen;
            }
            if (infinite && qn.lower <= 1) {
                if (qn.greedy) {
                    if (qn.lower == 1) {
                        this.addOpcodeRelAddr(55, cknOn(ckn) ? 3 : 2);
                    }
                    if (cknOn(ckn)) {
                        this.addOpcode(81);
                        this.addStateCheckNum(ckn);
                        this.addRelAddr(modTLen + 2);
                    }
                    else {
                        this.addOpcodeRelAddr(56, modTLen + 2);
                    }
                    this.compileTreeEmptyCheck(qn.target, emptyInfo);
                    this.addOpcodeRelAddr(55, -(modTLen + 2 + (cknOn(ckn) ? 3 : 2)));
                }
                else {
                    if (qn.lower == 0) {
                        this.addOpcodeRelAddr(55, modTLen);
                    }
                    this.compileTreeEmptyCheck(qn.target, emptyInfo);
                    if (cknOn(ckn)) {
                        this.addOpcode(82);
                        this.addStateCheckNum(ckn);
                        this.addRelAddr(-(modTLen + 3));
                    }
                    else {
                        this.addOpcodeRelAddr(56, -(modTLen + 2));
                    }
                }
            }
            else if (qn.upper == 0) {
                if (qn.isRefered) {
                    this.addOpcodeRelAddr(55, tlen);
                    this.compileTree(qn.target);
                }
            }
            else if (qn.upper == 1 && qn.greedy) {
                if (qn.lower == 0) {
                    if (cknOn(ckn)) {
                        this.addOpcode(81);
                        this.addStateCheckNum(ckn);
                        this.addRelAddr(tlen);
                    }
                    else {
                        this.addOpcodeRelAddr(56, tlen);
                    }
                }
                this.compileTree(qn.target);
            }
            else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
                if (cknOn(ckn)) {
                    this.addOpcode(81);
                    this.addStateCheckNum(ckn);
                    this.addRelAddr(2);
                }
                else {
                    this.addOpcodeRelAddr(56, 2);
                }
                this.addOpcodeRelAddr(55, tlen);
                this.compileTree(qn.target);
            }
            else {
                this.compileRangeRepeatNode(qn, modTLen, emptyInfo);
                if (cknOn(ckn)) {
                    this.addOpcode(83);
                    this.addStateCheckNum(ckn);
                }
            }
            return;
        }
        this.compileTreeNTimes(qn.target, qn.lower);
        if (qn.nextHeadExact != null && !cknOn(ckn)) {
            if (Option.isMultiline(this.regex.options)) {
                this.addOpcode(this.enc.isSingleByte() ? 93 : 28);
            }
            else {
                this.addOpcode(this.enc.isSingleByte() ? 92 : 27);
            }
            if (cknOn(ckn)) {
                this.addStateCheckNum(ckn);
            }
            final StringNode sn = (StringNode)qn.nextHeadExact;
            this.addBytes(sn.bytes, sn.p, 1);
            return;
        }
        if (Option.isMultiline(this.regex.options)) {
            if (cknOn(ckn)) {
                this.addOpcode(this.enc.isSingleByte() ? 95 : 85);
            }
            else {
                this.addOpcode(this.enc.isSingleByte() ? 91 : 26);
            }
        }
        else if (cknOn(ckn)) {
            this.addOpcode(this.enc.isSingleByte() ? 94 : 84);
        }
        else {
            this.addOpcode(this.enc.isSingleByte() ? 90 : 25);
        }
        if (cknOn(ckn)) {
            this.addStateCheckNum(ckn);
        }
    }
    
    private int compileNonCECLengthQuantifierNode(final QuantifierNode qn) {
        final boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
        final int emptyInfo = qn.targetEmptyInfo;
        final int tlen = this.compileLengthTree(qn.target);
        if (qn.target.getType() != 3 || !qn.greedy || !infinite) {
            int modTLen = 0;
            if (emptyInfo != 0) {
                modTLen = tlen + 4;
            }
            else {
                modTLen = tlen;
            }
            int len;
            if (infinite && (qn.lower <= 1 || tlen * qn.lower <= 50)) {
                if (qn.lower == 1 && tlen > 50) {
                    len = 2;
                }
                else {
                    len = tlen * qn.lower;
                }
                if (qn.greedy) {
                    if (qn.headExact != null) {
                        len += 3 + modTLen + 2;
                    }
                    else if (qn.nextHeadExact != null) {
                        len += 3 + modTLen + 2;
                    }
                    else {
                        len += 2 + modTLen + 2;
                    }
                }
                else {
                    len += 2 + modTLen + 2;
                }
            }
            else if (qn.upper == 0 && qn.isRefered) {
                len = 2 + tlen;
            }
            else if (!infinite && qn.greedy && (qn.upper == 1 || (tlen + 2) * qn.upper <= 50)) {
                len = tlen * qn.lower;
                len += (2 + tlen) * (qn.upper - qn.lower);
            }
            else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
                len = 4 + tlen;
            }
            else {
                len = 2 + modTLen + 1 + 1 + 1;
            }
            return len;
        }
        if (qn.nextHeadExact != null) {
            return 2 + tlen * qn.lower;
        }
        return 1 + tlen * qn.lower;
    }
    
    protected void compileNonCECQuantifierNode(final QuantifierNode qn) {
        final boolean infinite = QuantifierNode.isRepeatInfinite(qn.upper);
        final int emptyInfo = qn.targetEmptyInfo;
        final int tlen = this.compileLengthTree(qn.target);
        if (!qn.isAnyCharStar()) {
            int modTLen;
            if (emptyInfo != 0) {
                modTLen = tlen + 4;
            }
            else {
                modTLen = tlen;
            }
            if (infinite && (qn.lower <= 1 || tlen * qn.lower <= 50)) {
                if (qn.lower == 1 && tlen > 50) {
                    if (qn.greedy) {
                        if (qn.headExact != null) {
                            this.addOpcodeRelAddr(55, 3);
                        }
                        else if (qn.nextHeadExact != null) {
                            this.addOpcodeRelAddr(55, 3);
                        }
                        else {
                            this.addOpcodeRelAddr(55, 2);
                        }
                    }
                    else {
                        this.addOpcodeRelAddr(55, 2);
                    }
                }
                else {
                    this.compileTreeNTimes(qn.target, qn.lower);
                }
                if (qn.greedy) {
                    if (qn.headExact != null) {
                        this.addOpcodeRelAddr(58, modTLen + 2);
                        final StringNode sn = (StringNode)qn.headExact;
                        this.addBytes(sn.bytes, sn.p, 1);
                        this.compileTreeEmptyCheck(qn.target, emptyInfo);
                        this.addOpcodeRelAddr(55, -(modTLen + 2 + 3));
                    }
                    else if (qn.nextHeadExact != null) {
                        this.addOpcodeRelAddr(59, modTLen + 2);
                        final StringNode sn = (StringNode)qn.nextHeadExact;
                        this.addBytes(sn.bytes, sn.p, 1);
                        this.compileTreeEmptyCheck(qn.target, emptyInfo);
                        this.addOpcodeRelAddr(55, -(modTLen + 2 + 3));
                    }
                    else {
                        this.addOpcodeRelAddr(56, modTLen + 2);
                        this.compileTreeEmptyCheck(qn.target, emptyInfo);
                        this.addOpcodeRelAddr(55, -(modTLen + 2 + 2));
                    }
                }
                else {
                    this.addOpcodeRelAddr(55, modTLen);
                    this.compileTreeEmptyCheck(qn.target, emptyInfo);
                    this.addOpcodeRelAddr(56, -(modTLen + 2));
                }
            }
            else if (qn.upper == 0 && qn.isRefered) {
                this.addOpcodeRelAddr(55, tlen);
                this.compileTree(qn.target);
            }
            else if (!infinite && qn.greedy && (qn.upper == 1 || (tlen + 2) * qn.upper <= 50)) {
                final int n = qn.upper - qn.lower;
                this.compileTreeNTimes(qn.target, qn.lower);
                for (int i = 0; i < n; ++i) {
                    this.addOpcodeRelAddr(56, (n - i) * tlen + (n - i - 1) * 2);
                    this.compileTree(qn.target);
                }
            }
            else if (!qn.greedy && qn.upper == 1 && qn.lower == 0) {
                this.addOpcodeRelAddr(56, 2);
                this.addOpcodeRelAddr(55, tlen);
                this.compileTree(qn.target);
            }
            else {
                this.compileRangeRepeatNode(qn, modTLen, emptyInfo);
            }
            return;
        }
        this.compileTreeNTimes(qn.target, qn.lower);
        if (qn.nextHeadExact != null) {
            if (Option.isMultiline(this.regex.options)) {
                this.addOpcode(this.enc.isSingleByte() ? 93 : 28);
            }
            else {
                this.addOpcode(this.enc.isSingleByte() ? 92 : 27);
            }
            final StringNode sn2 = (StringNode)qn.nextHeadExact;
            this.addBytes(sn2.bytes, sn2.p, 1);
            return;
        }
        if (Option.isMultiline(this.regex.options)) {
            this.addOpcode(this.enc.isSingleByte() ? 91 : 26);
        }
        else {
            this.addOpcode(this.enc.isSingleByte() ? 90 : 25);
        }
    }
    
    private int compileLengthOptionNode(final EncloseNode node) {
        final int prev = this.regex.options;
        this.regex.options = node.option;
        final int tlen = this.compileLengthTree(node.target);
        this.regex.options = prev;
        if (Option.isDynamic(prev ^ node.option)) {
            return 5 + tlen + 2;
        }
        return tlen;
    }
    
    protected void compileOptionNode(final EncloseNode node) {
        final int prev = this.regex.options;
        if (Option.isDynamic(prev ^ node.option)) {
            this.addOpcodeOption(86, node.option);
            this.addOpcodeOption(87, prev);
            this.addOpcode(54);
        }
        this.regex.options = node.option;
        this.compileTree(node.target);
        this.regex.options = prev;
        if (Option.isDynamic(prev ^ node.option)) {
            this.addOpcodeOption(87, prev);
        }
    }
    
    private int compileLengthEncloseNode(final EncloseNode node) {
        if (node.isOption()) {
            return this.compileLengthOptionNode(node);
        }
        int tlen;
        if (node.target != null) {
            tlen = this.compileLengthTree(node.target);
        }
        else {
            tlen = 0;
        }
        int len = 0;
        switch (node.type) {
            case 1: {
                if (!node.isCalled()) {
                    if (BitStatus.bsAt(this.regex.btMemStart, node.regNum)) {
                        len = 2;
                    }
                    else {
                        len = 2;
                    }
                    len += tlen + (BitStatus.bsAt(this.regex.btMemEnd, node.regNum) ? 2 : 2);
                    break;
                }
                len = 2 + tlen + 2 + 2 + 1;
                if (BitStatus.bsAt(this.regex.btMemEnd, node.regNum)) {
                    len += (node.isRecursion() ? 2 : 2);
                    break;
                }
                len += (node.isRecursion() ? 2 : 2);
                break;
            }
            case 4: {
                if (node.isStopBtSimpleRepeat()) {
                    final QuantifierNode qn = (QuantifierNode)node.target;
                    tlen = this.compileLengthTree(qn.target);
                    len = tlen * qn.lower + 2 + tlen + 1 + 2;
                    break;
                }
                len = 1 + tlen + 1;
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                return 0;
            }
        }
        return len;
    }
    
    protected void compileEncloseNode(final EncloseNode node) {
        switch (node.type) {
            case 1: {
                if (node.isCalled()) {
                    this.addOpcode(79);
                    node.callAddr = this.regex.codeLength + 1 + 2;
                    node.setAddrFixed();
                    this.addAbsAddr(node.callAddr);
                    int len = this.compileLengthTree(node.target);
                    len += 3;
                    if (BitStatus.bsAt(this.regex.btMemEnd, node.regNum)) {
                        len += (node.isRecursion() ? 2 : 2);
                    }
                    else {
                        len += (node.isRecursion() ? 2 : 2);
                    }
                    this.addOpcodeRelAddr(55, len);
                }
                if (BitStatus.bsAt(this.regex.btMemStart, node.regNum)) {
                    this.addOpcode(49);
                }
                else {
                    this.addOpcode(48);
                }
                this.addMemNum(node.regNum);
                this.compileTree(node.target);
                if (node.isCalled()) {
                    if (BitStatus.bsAt(this.regex.btMemEnd, node.regNum)) {
                        this.addOpcode(node.isRecursion() ? 51 : 50);
                    }
                    else {
                        this.addOpcode(node.isRecursion() ? 53 : 52);
                    }
                    this.addMemNum(node.regNum);
                    this.addOpcode(80);
                    break;
                }
                if (BitStatus.bsAt(this.regex.btMemEnd, node.regNum)) {
                    this.addOpcode(50);
                }
                else {
                    this.addOpcode(52);
                }
                this.addMemNum(node.regNum);
                break;
            }
            case 4: {
                if (node.isStopBtSimpleRepeat()) {
                    final QuantifierNode qn = (QuantifierNode)node.target;
                    this.compileTreeNTimes(qn.target, qn.lower);
                    final int len = this.compileLengthTree(qn.target);
                    this.addOpcodeRelAddr(56, len + 1 + 2);
                    this.compileTree(qn.target);
                    this.addOpcode(57);
                    this.addOpcodeRelAddr(55, -(2 + len + 1 + 2));
                    break;
                }
                this.addOpcode(74);
                this.compileTree(node.target);
                this.addOpcode(75);
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
    }
    
    private int compileLengthAnchorNode(final AnchorNode node) {
        int tlen;
        if (node.target != null) {
            tlen = this.compileLengthTree(node.target);
        }
        else {
            tlen = 0;
        }
        int len = 0;
        switch (node.type) {
            case 1024: {
                len = 1 + tlen + 1;
                break;
            }
            case 2048: {
                len = 2 + tlen + 1;
                break;
            }
            case 4096: {
                len = 2 + tlen;
                break;
            }
            case 8192: {
                len = 3 + tlen + 1;
                break;
            }
            default: {
                len = 1;
                break;
            }
        }
        return len;
    }
    
    protected void compileAnchorNode(final AnchorNode node) {
        switch (node.type) {
            case 1: {
                this.addOpcode(35);
                break;
            }
            case 8: {
                this.addOpcode(36);
                break;
            }
            case 2: {
                this.addOpcode(37);
                break;
            }
            case 32: {
                this.addOpcode(38);
                break;
            }
            case 16: {
                this.addOpcode(39);
                break;
            }
            case 4: {
                this.addOpcode(40);
                break;
            }
            case 64: {
                this.addOpcode(this.enc.isSingleByte() ? 100 : 31);
                break;
            }
            case 128: {
                this.addOpcode(this.enc.isSingleByte() ? 101 : 32);
                break;
            }
            case 256: {
                this.addOpcode(this.enc.isSingleByte() ? 102 : 33);
                break;
            }
            case 512: {
                this.addOpcode(this.enc.isSingleByte() ? 103 : 34);
                break;
            }
            case 1024: {
                this.addOpcode(70);
                this.compileTree(node.target);
                this.addOpcode(71);
                break;
            }
            case 2048: {
                final int len = this.compileLengthTree(node.target);
                this.addOpcodeRelAddr(72, len + 1);
                this.compileTree(node.target);
                this.addOpcode(73);
                break;
            }
            case 4096: {
                this.addOpcode(this.enc.isSingleByte() ? 104 : 76);
                int n;
                if (node.charLength < 0) {
                    n = this.analyser.getCharLengthTree(node.target);
                    if (this.analyser.returnCode != 0) {
                        this.newSyntaxException("invalid pattern in look-behind");
                    }
                }
                else {
                    n = node.charLength;
                }
                this.addLength(n);
                this.compileTree(node.target);
                break;
            }
            case 8192: {
                final int len = this.compileLengthTree(node.target);
                this.addOpcodeRelAddr(77, len + 1);
                int n;
                if (node.charLength < 0) {
                    n = this.analyser.getCharLengthTree(node.target);
                    if (this.analyser.returnCode != 0) {
                        this.newSyntaxException("invalid pattern in look-behind");
                    }
                }
                else {
                    n = node.charLength;
                }
                this.addLength(n);
                this.compileTree(node.target);
                this.addOpcode(78);
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
    }
    
    private int compileLengthTree(final Node node) {
        int len = 0;
        switch (node.getType()) {
            case 8: {
                ConsAltNode lin = (ConsAltNode)node;
                do {
                    len += this.compileLengthTree(lin.car);
                } while ((lin = lin.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode aln = (ConsAltNode)node;
                int n = 0;
                do {
                    len += this.compileLengthTree(aln.car);
                    ++n;
                } while ((aln = aln.cdr) != null);
                len += 4 * (n - 1);
                break;
            }
            case 0: {
                final StringNode sn = (StringNode)node;
                if (sn.isRaw()) {
                    len = this.compileLengthStringRawNode(sn);
                    break;
                }
                len = this.compileLengthStringNode(sn);
                break;
            }
            case 1: {
                len = this.compileLengthCClassNode((CClassNode)node);
                break;
            }
            case 2:
            case 3: {
                len = 1;
                break;
            }
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                if (br.isNestLevel()) {
                    len = 4 + 1 * br.backNum;
                    break;
                }
                if (br.backNum == 1) {
                    len = ((!Option.isIgnoreCase(this.regex.options) && br.back[0] <= 2) ? 1 : 2);
                    break;
                }
                len = 2 + 1 * br.backNum;
                break;
            }
            case 10: {
                len = 2;
                break;
            }
            case 5: {
                len = this.compileNonCECLengthQuantifierNode((QuantifierNode)node);
                break;
            }
            case 6: {
                len = this.compileLengthEncloseNode((EncloseNode)node);
                break;
            }
            case 7: {
                len = this.compileLengthAnchorNode((AnchorNode)node);
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
        return len;
    }
    
    private void ensure(final int size) {
        if (size >= this.regex.code.length) {
            int length;
            for (length = this.regex.code.length << 1; length <= size; length <<= 1) {}
            final int[] tmp = new int[length];
            System.arraycopy(this.regex.code, 0, tmp, 0, this.regex.code.length);
            this.regex.code = tmp;
        }
    }
    
    private void addInt(final int i) {
        if (this.regex.codeLength >= this.regex.code.length) {
            final int[] tmp = new int[this.regex.code.length << 1];
            System.arraycopy(this.regex.code, 0, tmp, 0, this.regex.code.length);
            this.regex.code = tmp;
        }
        this.regex.code[this.regex.codeLength++] = i;
    }
    
    void setInt(final int i, final int offset) {
        this.ensure(offset);
        this.regex.code[offset] = i;
    }
    
    private void addObject(final Object o) {
        if (this.regex.operands == null) {
            this.regex.operands = new Object[4];
        }
        else if (this.regex.operandLength >= this.regex.operands.length) {
            final Object[] tmp = new Object[this.regex.operands.length << 1];
            System.arraycopy(this.regex.operands, 0, tmp, 0, this.regex.operands.length);
            this.regex.operands = tmp;
        }
        this.addInt(this.regex.operandLength);
        this.regex.operands[this.regex.operandLength++] = o;
    }
    
    private void addBytes(final byte[] bytes, int p, final int length) {
        this.ensure(this.regex.codeLength + length);
        for (int end = p + length; p < end; this.regex.code[this.regex.codeLength++] = bytes[p++]) {}
    }
    
    private void addInts(final int[] ints, final int length) {
        this.ensure(this.regex.codeLength + length);
        System.arraycopy(ints, 0, this.regex.code, this.regex.codeLength, length);
        final Regex regex = this.regex;
        regex.codeLength += length;
    }
    
    private void addOpcode(final int opcode) {
        this.addInt(opcode);
        switch (opcode) {
            case 25:
            case 26:
            case 27:
            case 28:
            case 49:
            case 50:
            case 51:
            case 53:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 69:
            case 70:
            case 72:
            case 74:
            case 77:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94: {
                this.regex.stackNeeded = true;
                break;
            }
        }
    }
    
    private void addStateCheckNum(final int num) {
        this.addInt(num);
    }
    
    private void addRelAddr(final int addr) {
        this.addInt(addr);
    }
    
    private void addAbsAddr(final int addr) {
        this.addInt(addr);
    }
    
    private void addLength(final int length) {
        this.addInt(length);
    }
    
    private void addMemNum(final int num) {
        this.addInt(num);
    }
    
    private void addPointer(final Object o) {
        this.addObject(o);
    }
    
    private void addOption(final int option) {
        this.addInt(option);
    }
    
    private void addOpcodeRelAddr(final int opcode, final int addr) {
        this.addOpcode(opcode);
        this.addRelAddr(addr);
    }
    
    private void addOpcodeOption(final int opcode, final int option) {
        this.addOpcode(opcode);
        this.addOption(option);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.CClassNode;
import org.jcodings.CodeRange;
import org.joni.exception.InternalException;
import org.jcodings.IntHolder;

class ByteCodeMachine extends StackMachine
{
    private int bestLen;
    private int s;
    private int range;
    private int sprev;
    private int sstart;
    private int sbegin;
    private final int[] code;
    private int ip;
    protected int stkp;
    private byte[] cfbuf;
    private byte[] cfbuf2;
    
    ByteCodeMachine(final Regex regex, final byte[] bytes, final int p, final int end) {
        super(regex, bytes, p, end);
        this.s = 0;
        this.code = regex.code;
    }
    
    private boolean makeCaptureHistoryTree(final CaptureTreeNode node) {
        int k = this.stkp;
        while (k < this.stk) {
            final StackEntry e = this.stack[k];
            if (e.type == 256) {
                final int n = e.getMemNum();
                if (n > 31 || !BitStatus.bsAt(this.regex.captureHistory, n)) {
                    continue;
                }
                final CaptureTreeNode child = new CaptureTreeNode();
                child.group = n;
                child.beg = e.getMemPStr() - this.str;
                node.addChild(child);
                this.stkp = k + 1;
                if (this.makeCaptureHistoryTree(child)) {
                    return true;
                }
                k = this.stkp;
                child.end = e.getMemPStr() - this.str;
            }
            else {
                if (e.type == 33280 && e.getMemNum() == node.group) {
                    node.end = e.getMemPStr() - this.str;
                    this.stkp = k;
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    private void checkCaptureHistory(final Region region) {
        CaptureTreeNode node;
        if (region.historyRoot == null) {
            final CaptureTreeNode historyRoot = new CaptureTreeNode();
            region.historyRoot = historyRoot;
            node = historyRoot;
        }
        else {
            node = region.historyRoot;
            node.clear();
        }
        node.group = 0;
        node.beg = this.sstart - this.str;
        node.end = this.s - this.str;
        this.stkp = 0;
        this.makeCaptureHistoryTree(region.historyRoot);
    }
    
    protected final byte[] cfbuf() {
        return (this.cfbuf == null) ? (this.cfbuf = new byte[18]) : this.cfbuf;
    }
    
    protected final byte[] cfbuf2() {
        return (this.cfbuf2 == null) ? (this.cfbuf2 = new byte[18]) : this.cfbuf2;
    }
    
    private boolean stringCmpIC(final int caseFlodFlag, int s1, final IntHolder ps2, final int mbLen, final int textEnd) {
        final byte[] buf1 = this.cfbuf();
        final byte[] buf2 = this.cfbuf2();
        int s2 = ps2.value;
        final int end1 = s1 + mbLen;
        while (s1 < end1) {
            this.value = s1;
            int len1 = this.enc.mbcCaseFold(caseFlodFlag, this.bytes, this, textEnd, buf1);
            s1 = this.value;
            this.value = s2;
            final int len2 = this.enc.mbcCaseFold(caseFlodFlag, this.bytes, this, textEnd, buf2);
            s2 = this.value;
            if (len1 != len2) {
                return false;
            }
            int p1 = 0;
            int p2 = 0;
            while (len1-- > 0) {
                if (buf1[p1] != buf2[p2]) {
                    return false;
                }
                ++p1;
                ++p2;
            }
        }
        ps2.value = s2;
        return true;
    }
    
    private void debugMatchBegin() {
        Config.log.println("match_at: str: " + this.str + ", end: " + this.end + ", start: " + this.sstart + ", sprev: " + this.sprev);
        Config.log.println("size: " + (this.end - this.str) + ", start offset: " + (this.sstart - this.str));
    }
    
    private void debugMatchLoop() {
    }
    
    protected final int matchAt(final int range, final int sstart, final int sprev) {
        this.range = range;
        this.sstart = sstart;
        this.sprev = sprev;
        this.stk = 0;
        this.ip = 0;
        this.init();
        this.bestLen = -1;
        this.s = sstart;
        final int[] code = this.code;
        while (true) {
            this.sbegin = this.s;
            switch (code[this.ip++]) {
                case 1: {
                    if (this.opEnd()) {
                        return this.finish();
                    }
                    continue;
                }
                case 2: {
                    this.opExact1();
                    continue;
                }
                case 3: {
                    this.opExact2();
                    continue;
                }
                case 4: {
                    this.opExact3();
                    continue;
                }
                case 5: {
                    this.opExact4();
                    continue;
                }
                case 6: {
                    this.opExact5();
                    continue;
                }
                case 7: {
                    this.opExactN();
                    continue;
                }
                case 8: {
                    this.opExactMB2N1();
                    continue;
                }
                case 9: {
                    this.opExactMB2N2();
                    continue;
                }
                case 10: {
                    this.opExactMB2N3();
                    continue;
                }
                case 11: {
                    this.opExactMB2N();
                    continue;
                }
                case 12: {
                    this.opExactMB3N();
                    continue;
                }
                case 13: {
                    this.opExactMBN();
                    continue;
                }
                case 14: {
                    this.opExact1IC();
                    continue;
                }
                case 15: {
                    this.opExactNIC();
                    continue;
                }
                case 16: {
                    this.opCClass();
                    continue;
                }
                case 17: {
                    this.opCClassMB();
                    continue;
                }
                case 18: {
                    this.opCClassMIX();
                    continue;
                }
                case 19: {
                    this.opCClassNot();
                    continue;
                }
                case 20: {
                    this.opCClassMBNot();
                    continue;
                }
                case 21: {
                    this.opCClassMIXNot();
                    continue;
                }
                case 22: {
                    this.opCClassNode();
                    continue;
                }
                case 23: {
                    this.opAnyChar();
                    continue;
                }
                case 24: {
                    this.opAnyCharML();
                    continue;
                }
                case 25: {
                    this.opAnyCharStar();
                    continue;
                }
                case 26: {
                    this.opAnyCharMLStar();
                    continue;
                }
                case 27: {
                    this.opAnyCharStarPeekNext();
                    continue;
                }
                case 28: {
                    this.opAnyCharMLStarPeekNext();
                    continue;
                }
                case 84: {
                    this.opStateCheckAnyCharStar();
                    continue;
                }
                case 85: {
                    this.opStateCheckAnyCharMLStar();
                    continue;
                }
                case 29: {
                    this.opWord();
                    continue;
                }
                case 30: {
                    this.opNotWord();
                    continue;
                }
                case 31: {
                    this.opWordBound();
                    continue;
                }
                case 32: {
                    this.opNotWordBound();
                    continue;
                }
                case 33: {
                    this.opWordBegin();
                    continue;
                }
                case 34: {
                    this.opWordEnd();
                    continue;
                }
                case 35: {
                    this.opBeginBuf();
                    continue;
                }
                case 36: {
                    this.opEndBuf();
                    continue;
                }
                case 37: {
                    this.opBeginLine();
                    continue;
                }
                case 38: {
                    this.opEndLine();
                    continue;
                }
                case 39: {
                    this.opSemiEndBuf();
                    continue;
                }
                case 40: {
                    this.opBeginPosition();
                    continue;
                }
                case 49: {
                    this.opMemoryStartPush();
                    continue;
                }
                case 48: {
                    this.opMemoryStart();
                    continue;
                }
                case 50: {
                    this.opMemoryEndPush();
                    continue;
                }
                case 52: {
                    this.opMemoryEnd();
                    continue;
                }
                case 51: {
                    this.opMemoryEndPushRec();
                    continue;
                }
                case 53: {
                    this.opMemoryEndRec();
                    continue;
                }
                case 41: {
                    this.opBackRef1();
                    continue;
                }
                case 42: {
                    this.opBackRef2();
                    continue;
                }
                case 43: {
                    this.opBackRefN();
                    continue;
                }
                case 44: {
                    this.opBackRefNIC();
                    continue;
                }
                case 45: {
                    this.opBackRefMulti();
                    continue;
                }
                case 46: {
                    this.opBackRefMultiIC();
                    continue;
                }
                case 47: {
                    this.opBackRefAtLevel();
                    continue;
                }
                case 66: {
                    this.opNullCheckStart();
                    continue;
                }
                case 67: {
                    this.opNullCheckEnd();
                    continue;
                }
                case 68: {
                    this.opNullCheckEndMemST();
                    continue;
                }
                case 69: {
                    this.opNullCheckEndMemSTPush();
                    continue;
                }
                case 55: {
                    this.opJump();
                    continue;
                }
                case 56: {
                    this.opPush();
                    continue;
                }
                case 81: {
                    this.opStateCheckPush();
                    continue;
                }
                case 82: {
                    this.opStateCheckPushOrJump();
                    continue;
                }
                case 83: {
                    this.opStateCheck();
                    continue;
                }
                case 57: {
                    this.opPop();
                    continue;
                }
                case 58: {
                    this.opPushOrJumpExact1();
                    continue;
                }
                case 59: {
                    this.opPushIfPeekNext();
                    continue;
                }
                case 60: {
                    this.opRepeat();
                    continue;
                }
                case 61: {
                    this.opRepeatNG();
                    continue;
                }
                case 62: {
                    this.opRepeatInc();
                    continue;
                }
                case 64: {
                    this.opRepeatIncSG();
                    continue;
                }
                case 63: {
                    this.opRepeatIncNG();
                    continue;
                }
                case 65: {
                    this.opRepeatIncNGSG();
                    continue;
                }
                case 70: {
                    this.opPushPos();
                    continue;
                }
                case 71: {
                    this.opPopPos();
                    continue;
                }
                case 72: {
                    this.opPushPosNot();
                    continue;
                }
                case 73: {
                    this.opFailPos();
                    continue;
                }
                case 74: {
                    this.opPushStopBT();
                    continue;
                }
                case 75: {
                    this.opPopStopBT();
                    continue;
                }
                case 76: {
                    this.opLookBehind();
                    continue;
                }
                case 77: {
                    this.opPushLookBehindNot();
                    continue;
                }
                case 78: {
                    this.opFailLookBehindNot();
                    continue;
                }
                case 79: {
                    this.opCall();
                    continue;
                }
                case 80: {
                    this.opReturn();
                    continue;
                }
                case 96: {
                    this.opCClassSb();
                    continue;
                }
                case 97: {
                    this.opCClassNotSb();
                    continue;
                }
                case 88: {
                    this.opAnyCharSb();
                    continue;
                }
                case 89: {
                    this.opAnyCharMLSb();
                    continue;
                }
                case 90: {
                    this.opAnyCharStarSb();
                    continue;
                }
                case 91: {
                    this.opAnyCharMLStarSb();
                    continue;
                }
                case 92: {
                    this.opAnyCharStarPeekNextSb();
                    continue;
                }
                case 93: {
                    this.opAnyCharMLStarPeekNextSb();
                    continue;
                }
                case 94: {
                    this.opStateCheckAnyCharStarSb();
                    continue;
                }
                case 95: {
                    this.opStateCheckAnyCharMLStarSb();
                    continue;
                }
                case 98: {
                    this.opWordSb();
                    continue;
                }
                case 99: {
                    this.opNotWordSb();
                    continue;
                }
                case 100: {
                    this.opWordBoundSb();
                    continue;
                }
                case 101: {
                    this.opNotWordBoundSb();
                    continue;
                }
                case 102: {
                    this.opWordBeginSb();
                    continue;
                }
                case 103: {
                    this.opWordEndSb();
                    continue;
                }
                case 104: {
                    this.opLookBehindSb();
                    continue;
                }
                case 105: {
                    this.opExact1ICSb();
                    continue;
                }
                case 106: {
                    this.opExactNICSb();
                    continue;
                }
                case 0: {
                    return this.finish();
                }
                case 54: {
                    this.opFail();
                    continue;
                }
                default: {
                    throw new InternalException("undefined bytecode (bug)");
                }
            }
        }
    }
    
    private boolean opEnd() {
        final int n = this.s - this.sstart;
        if (n > this.bestLen) {
            if (Option.isFindLongest(this.regex.options)) {
                if (n <= this.msaBestLen) {
                    return this.endBestLength();
                }
                this.msaBestLen = n;
                this.msaBestS = this.sstart;
            }
            this.bestLen = n;
            final Region region = this.msaRegion;
            if (region != null) {
                region.beg[0] = (this.msaBegin = this.sstart - this.str);
                region.end[0] = (this.msaEnd = this.s - this.str);
                for (int i = 1; i <= this.regex.numMem; ++i) {
                    if (this.repeatStk[this.memEndStk + i] != -1) {
                        region.beg[i] = (BitStatus.bsAt(this.regex.btMemStart, i) ? (this.stack[this.repeatStk[this.memStartStk + i]].getMemPStr() - this.str) : (this.repeatStk[this.memStartStk + i] - this.str));
                        region.end[i] = (BitStatus.bsAt(this.regex.btMemEnd, i) ? this.stack[this.repeatStk[this.memEndStk + i]].getMemPStr() : (this.repeatStk[this.memEndStk + i] - this.str));
                    }
                    else {
                        region.beg[i] = (region.end[i] = -1);
                    }
                }
            }
            else {
                this.msaBegin = this.sstart - this.str;
                this.msaEnd = this.s - this.str;
            }
        }
        else {
            final Region region = this.msaRegion;
            if (!Option.isPosixRegion(this.regex.options)) {
                if (region != null) {
                    region.clear();
                }
                else {
                    final boolean b = false;
                    this.msaEnd = (b ? 1 : 0);
                    this.msaBegin = (b ? 1 : 0);
                }
            }
        }
        return this.endBestLength();
    }
    
    private boolean endBestLength() {
        if (Option.isFindCondition(this.regex.options)) {
            if (Option.isFindNotEmpty(this.regex.options) && this.s == this.sstart) {
                this.bestLen = -1;
                this.opFail();
                return false;
            }
            if (Option.isFindLongest(this.regex.options) && this.s < this.range) {
                this.opFail();
                return false;
            }
        }
        return true;
    }
    
    private void opExact1() {
        if (this.s >= this.range || this.code[this.ip] != this.bytes[this.s++]) {
            this.opFail();
            return;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opExact2() {
        if (this.s + 2 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        ++this.ip;
        ++this.s;
    }
    
    private void opExact3() {
        if (this.s + 3 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        ++this.ip;
        ++this.s;
    }
    
    private void opExact4() {
        if (this.s + 4 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        ++this.ip;
        ++this.s;
    }
    
    private void opExact5() {
        if (this.s + 5 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        ++this.ip;
        ++this.s;
    }
    
    private void opExactN() {
        int tlen = this.code[this.ip++];
        if (this.s + tlen > this.range) {
            this.opFail();
            return;
        }
        while (tlen-- > 0) {
            if (this.code[this.ip++] != this.bytes[this.s++]) {
                this.opFail();
                return;
            }
        }
        this.sprev = this.s - 1;
    }
    
    private void opExactMB2N1() {
        if (this.s + 2 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private void opExactMB2N2() {
        if (this.s + 4 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        this.sprev = this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
    }
    
    private void opExactMB2N3() {
        if (this.s + 6 > this.range) {
            this.opFail();
            return;
        }
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        this.sprev = this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
        if (this.code[this.ip] != this.bytes[this.s]) {
            this.opFail();
            return;
        }
        ++this.ip;
        ++this.s;
    }
    
    private void opExactMB2N() {
        int tlen = this.code[this.ip++];
        if (tlen * 2 > this.range) {
            this.opFail();
            return;
        }
        while (tlen-- > 0) {
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
        }
        this.sprev = this.s - 2;
    }
    
    private void opExactMB3N() {
        int tlen = this.code[this.ip++];
        if (tlen * 3 > this.range) {
            this.opFail();
            return;
        }
        while (tlen-- > 0) {
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
        }
        this.sprev = this.s - 3;
    }
    
    private void opExactMBN() {
        final int tlen = this.code[this.ip++];
        int tlen2 = this.code[this.ip++];
        tlen2 *= tlen;
        if (this.s + tlen2 > this.range) {
            this.opFail();
            return;
        }
        while (tlen2-- > 0) {
            if (this.code[this.ip] != this.bytes[this.s]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++this.s;
        }
        this.sprev = this.s - tlen;
    }
    
    private void opExact1IC() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        final byte[] lowbuf = this.cfbuf();
        this.value = this.s;
        int len = this.enc.mbcCaseFold(this.regex.caseFoldFlag, this.bytes, this, this.end, lowbuf);
        this.s = this.value;
        if (this.s > this.range) {
            this.opFail();
            return;
        }
        int q = 0;
        while (len-- > 0) {
            if (this.code[this.ip] != lowbuf[q]) {
                this.opFail();
                return;
            }
            ++this.ip;
            ++q;
        }
        this.sprev = this.sbegin;
    }
    
    private void opExact1ICSb() {
        if (this.s >= this.range || this.code[this.ip] != this.enc.toLowerCaseTable()[this.bytes[this.s++] & 0xFF]) {
            this.opFail();
            return;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opExactNIC() {
        final int tlen = this.code[this.ip++];
        final int endp = this.ip + tlen;
        final byte[] lowbuf = this.cfbuf();
        while (this.ip < endp) {
            this.sprev = this.s;
            if (this.s >= this.range) {
                this.opFail();
                return;
            }
            this.value = this.s;
            int len = this.enc.mbcCaseFold(this.regex.caseFoldFlag, this.bytes, this, this.end, lowbuf);
            this.s = this.value;
            if (this.s > this.range) {
                this.opFail();
                return;
            }
            int q = 0;
            while (len-- > 0) {
                if (this.code[this.ip] != lowbuf[q]) {
                    this.opFail();
                    return;
                }
                ++this.ip;
                ++q;
            }
        }
    }
    
    private void opExactNICSb() {
        int tlen = this.code[this.ip++];
        if (this.s + tlen > this.range) {
            this.opFail();
            return;
        }
        final byte[] toLowerTable = this.enc.toLowerCaseTable();
        while (tlen-- > 0) {
            if (this.code[this.ip++] != toLowerTable[this.bytes[this.s++] & 0xFF]) {
                this.opFail();
                return;
            }
        }
        this.sprev = this.s - 1;
    }
    
    private boolean isInBitSet() {
        final int c = this.bytes[this.s] & 0xFF;
        return (this.code[this.ip + (c >>> BitSet.ROOM_SHIFT)] & 1 << c) != 0x0;
    }
    
    private void opCClass() {
        if (this.s >= this.range || !this.isInBitSet()) {
            this.opFail();
            return;
        }
        this.ip += 8;
        this.s += this.enc.length(this.bytes, this.s, this.end);
        if (this.s > this.end) {
            this.s = this.end;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassSb() {
        if (this.s >= this.range || !this.isInBitSet()) {
            this.opFail();
            return;
        }
        this.ip += 8;
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private boolean isInClassMB() {
        final int tlen = this.code[this.ip++];
        if (this.s >= this.range) {
            return false;
        }
        final int mbLen = this.enc.length(this.bytes, this.s, this.end);
        if (this.s + mbLen > this.range) {
            return false;
        }
        final int ss = this.s;
        this.s += mbLen;
        final int c = this.enc.mbcToCode(this.bytes, ss, this.s);
        if (!CodeRange.isInCodeRange(this.code, this.ip, c)) {
            return false;
        }
        this.ip += tlen;
        return true;
    }
    
    private void opCClassMB() {
        if (this.s >= this.range || !this.enc.isMbcHead(this.bytes, this.s, this.end)) {
            this.opFail();
            return;
        }
        if (!this.isInClassMB()) {
            this.opFail();
            return;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassMIX() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        if (this.enc.isMbcHead(this.bytes, this.s, this.end)) {
            this.ip += 8;
            if (!this.isInClassMB()) {
                this.opFail();
                return;
            }
        }
        else {
            if (!this.isInBitSet()) {
                this.opFail();
                return;
            }
            this.ip += 8;
            final int tlen = this.code[this.ip++];
            this.ip += tlen;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassNot() {
        if (this.s >= this.range || this.isInBitSet()) {
            this.opFail();
            return;
        }
        this.ip += 8;
        this.s += this.enc.length(this.bytes, this.s, this.end);
        if (this.s > this.end) {
            this.s = this.end;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassNotSb() {
        if (this.s >= this.range || this.isInBitSet()) {
            this.opFail();
            return;
        }
        this.ip += 8;
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private boolean isNotInClassMB() {
        final int tlen = this.code[this.ip++];
        final int mbLen = this.enc.length(this.bytes, this.s, this.end);
        if (this.s + mbLen > this.range) {
            if (this.s >= this.range) {
                return false;
            }
            this.s = this.end;
            this.ip += tlen;
            return true;
        }
        else {
            final int ss = this.s;
            this.s += mbLen;
            final int c = this.enc.mbcToCode(this.bytes, ss, this.s);
            if (CodeRange.isInCodeRange(this.code, this.ip, c)) {
                return false;
            }
            this.ip += tlen;
            return true;
        }
    }
    
    private void opCClassMBNot() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        if (!this.enc.isMbcHead(this.bytes, this.s, this.end)) {
            ++this.s;
            final int tlen = this.code[this.ip++];
            this.ip += tlen;
            this.sprev = this.sbegin;
            return;
        }
        if (!this.isNotInClassMB()) {
            this.opFail();
            return;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassMIXNot() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        if (this.enc.isMbcHead(this.bytes, this.s, this.end)) {
            this.ip += 8;
            if (!this.isNotInClassMB()) {
                this.opFail();
                return;
            }
        }
        else {
            if (this.isInBitSet()) {
                this.opFail();
                return;
            }
            this.ip += 8;
            final int tlen = this.code[this.ip++];
            this.ip += tlen;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opCClassNode() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        final CClassNode cc = (CClassNode)this.regex.operands[this.code[this.ip++]];
        final int mbLen = this.enc.length(this.bytes, this.s, this.end);
        final int ss = this.s;
        this.s += mbLen;
        if (this.s > this.range) {
            this.opFail();
            return;
        }
        final int c = this.enc.mbcToCode(this.bytes, ss, this.s);
        if (!cc.isCodeInCCLength(mbLen, c)) {
            this.opFail();
            return;
        }
        this.sprev = this.sbegin;
    }
    
    private void opAnyChar() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        final int n = this.enc.length(this.bytes, this.s, this.end);
        if (this.s + n > this.range) {
            this.opFail();
            return;
        }
        if (this.enc.isNewLine(this.bytes, this.s, this.end)) {
            this.opFail();
            return;
        }
        this.s += n;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharSb() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        if (this.bytes[this.s] == 10) {
            this.opFail();
            return;
        }
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharML() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        final int n = this.enc.length(this.bytes, this.s, this.end);
        if (this.s + n > this.range) {
            this.opFail();
            return;
        }
        this.s += n;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharMLSb() {
        if (this.s >= this.range) {
            this.opFail();
            return;
        }
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharStar() {
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            this.pushAlt(this.ip, this.s, this.sprev);
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range) {
                this.opFail();
                return;
            }
            if (this.enc.isNewLine(bytes, this.s, this.end)) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharStarSb() {
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            this.pushAlt(this.ip, this.s, this.sprev);
            if (bytes[this.s] == 10) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharMLStar() {
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            this.pushAlt(this.ip, this.s, this.sprev);
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharMLStarSb() {
        while (this.s < this.range) {
            this.pushAlt(this.ip, this.s, this.sprev);
            this.sprev = this.s;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharStarPeekNext() {
        final byte c = (byte)this.code[this.ip];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (c == bytes[this.s]) {
                this.pushAlt(this.ip + 1, this.s, this.sprev);
            }
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range || this.enc.isNewLine(bytes, this.s, this.end)) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharStarPeekNextSb() {
        final byte c = (byte)this.code[this.ip];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            final byte b = bytes[this.s];
            if (c == b) {
                this.pushAlt(this.ip + 1, this.s, this.sprev);
            }
            if (b == 10) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            ++this.s;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharMLStarPeekNext() {
        final byte c = (byte)this.code[this.ip];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (c == bytes[this.s]) {
                this.pushAlt(this.ip + 1, this.s, this.sprev);
            }
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opAnyCharMLStarPeekNextSb() {
        final byte c = (byte)this.code[this.ip];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (c == bytes[this.s]) {
                this.pushAlt(this.ip + 1, this.s, this.sprev);
            }
            this.sprev = this.s;
            ++this.s;
        }
        ++this.ip;
        this.sprev = this.sbegin;
    }
    
    private void opStateCheckAnyCharStar() {
        final int mem = this.code[this.ip++];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (this.stateCheckVal(this.s, mem)) {
                this.opFail();
                return;
            }
            this.pushAltWithStateCheck(this.ip, this.s, this.sprev, mem);
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range || this.enc.isNewLine(bytes, this.s, this.end)) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        this.sprev = this.sbegin;
    }
    
    private void opStateCheckAnyCharStarSb() {
        final int mem = this.code[this.ip++];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (this.stateCheckVal(this.s, mem)) {
                this.opFail();
                return;
            }
            this.pushAltWithStateCheck(this.ip, this.s, this.sprev, mem);
            if (bytes[this.s] == 10) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opStateCheckAnyCharMLStar() {
        final int mem = this.code[this.ip++];
        final byte[] bytes = this.bytes;
        while (this.s < this.range) {
            if (this.stateCheckVal(this.s, mem)) {
                this.opFail();
                return;
            }
            this.pushAltWithStateCheck(this.ip, this.s, this.sprev, mem);
            final int n = this.enc.length(bytes, this.s, this.end);
            if (this.s + n > this.range) {
                this.opFail();
                return;
            }
            this.sprev = this.s;
            this.s += n;
        }
        this.sprev = this.sbegin;
    }
    
    private void opStateCheckAnyCharMLStarSb() {
        final int mem = this.code[this.ip++];
        while (this.s < this.range) {
            if (this.stateCheckVal(this.s, mem)) {
                this.opFail();
                return;
            }
            this.pushAltWithStateCheck(this.ip, this.s, this.sprev, mem);
            this.sprev = this.s;
            ++this.s;
        }
        this.sprev = this.sbegin;
    }
    
    private void opWord() {
        if (this.s >= this.range || !this.enc.isMbcWord(this.bytes, this.s, this.end)) {
            this.opFail();
            return;
        }
        this.s += this.enc.length(this.bytes, this.s, this.end);
        this.sprev = this.sbegin;
    }
    
    private void opWordSb() {
        if (this.s >= this.range || !this.enc.isWord(this.bytes[this.s] & 0xFF)) {
            this.opFail();
            return;
        }
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private void opNotWord() {
        if (this.s >= this.range || this.enc.isMbcWord(this.bytes, this.s, this.end)) {
            this.opFail();
            return;
        }
        this.s += this.enc.length(this.bytes, this.s, this.end);
        this.sprev = this.sbegin;
    }
    
    private void opNotWordSb() {
        if (this.s >= this.range || this.enc.isWord(this.bytes[this.s] & 0xFF)) {
            this.opFail();
            return;
        }
        ++this.s;
        this.sprev = this.sbegin;
    }
    
    private void opWordBound() {
        if (this.s == this.str) {
            if (this.s >= this.range || !this.enc.isMbcWord(this.bytes, this.s, this.end)) {
                this.opFail();
            }
        }
        else if (this.s == this.end) {
            if (!this.enc.isMbcWord(this.bytes, this.sprev, this.end)) {
                this.opFail();
            }
        }
        else if (this.enc.isMbcWord(this.bytes, this.s, this.end) == this.enc.isMbcWord(this.bytes, this.sprev, this.end)) {
            this.opFail();
        }
    }
    
    private void opWordBoundSb() {
        if (this.s == this.str) {
            if (this.s >= this.range || !this.enc.isWord(this.bytes[this.s] & 0xFF)) {
                this.opFail();
            }
        }
        else if (this.s == this.end) {
            if (this.sprev >= this.end || !this.enc.isWord(this.bytes[this.sprev] & 0xFF)) {
                this.opFail();
            }
        }
        else if (this.enc.isWord(this.bytes[this.s] & 0xFF) == this.enc.isWord(this.bytes[this.sprev] & 0xFF)) {
            this.opFail();
        }
    }
    
    private void opNotWordBound() {
        if (this.s == this.str) {
            if (this.s < this.range && this.enc.isMbcWord(this.bytes, this.s, this.end)) {
                this.opFail();
            }
        }
        else if (this.s == this.end) {
            if (this.enc.isMbcWord(this.bytes, this.sprev, this.end)) {
                this.opFail();
            }
        }
        else if (this.enc.isMbcWord(this.bytes, this.s, this.end) != this.enc.isMbcWord(this.bytes, this.sprev, this.end)) {
            this.opFail();
        }
    }
    
    private void opNotWordBoundSb() {
        if (this.s == this.str) {
            if (this.s < this.range && this.enc.isWord(this.bytes[this.s] & 0xFF)) {
                this.opFail();
            }
        }
        else if (this.s == this.end) {
            if (this.sprev < this.end && this.enc.isWord(this.bytes[this.sprev] & 0xFF)) {
                this.opFail();
            }
        }
        else if (this.enc.isWord(this.bytes[this.s] & 0xFF) != this.enc.isWord(this.bytes[this.sprev] & 0xFF)) {
            this.opFail();
        }
    }
    
    private void opWordBegin() {
        if (this.s < this.range && this.enc.isMbcWord(this.bytes, this.s, this.end) && (this.s == this.str || !this.enc.isMbcWord(this.bytes, this.sprev, this.end))) {
            return;
        }
        this.opFail();
    }
    
    private void opWordBeginSb() {
        if (this.s < this.range && this.enc.isWord(this.bytes[this.s] & 0xFF) && (this.s == this.str || !this.enc.isWord(this.bytes[this.sprev] & 0xFF))) {
            return;
        }
        this.opFail();
    }
    
    private void opWordEnd() {
        if (this.s != this.str && this.enc.isMbcWord(this.bytes, this.sprev, this.end) && (this.s == this.end || !this.enc.isMbcWord(this.bytes, this.s, this.end))) {
            return;
        }
        this.opFail();
    }
    
    private void opWordEndSb() {
        if (this.s != this.str && this.enc.isWord(this.bytes[this.sprev] & 0xFF) && (this.s == this.end || !this.enc.isWord(this.bytes[this.s] & 0xFF))) {
            return;
        }
        this.opFail();
    }
    
    private void opBeginBuf() {
        if (this.s != this.str) {
            this.opFail();
        }
    }
    
    private void opEndBuf() {
        if (this.s != this.end) {
            this.opFail();
        }
    }
    
    private void opBeginLine() {
        if (this.s == this.str) {
            if (Option.isNotBol(this.msaOptions)) {
                this.opFail();
            }
            return;
        }
        if (this.enc.isNewLine(this.bytes, this.sprev, this.end) && this.s != this.end) {
            return;
        }
        this.opFail();
    }
    
    private void opEndLine() {
        if (this.s == this.end) {
            if ((this.str == this.end || !this.enc.isNewLine(this.bytes, this.sprev, this.end)) && Option.isNotEol(this.msaOptions)) {
                this.opFail();
            }
            return;
        }
        if (!this.enc.isNewLine(this.bytes, this.s, this.end)) {
            this.opFail();
        }
    }
    
    private void opSemiEndBuf() {
        if (this.s == this.end) {
            if ((this.str == this.end || !this.enc.isNewLine(this.bytes, this.sprev, this.end)) && Option.isNotEol(this.msaOptions)) {
                this.opFail();
            }
            return;
        }
        if (this.enc.isNewLine(this.bytes, this.s, this.end) && this.s + this.enc.length(this.bytes, this.s, this.end) == this.end) {
            return;
        }
        this.opFail();
    }
    
    private void opBeginPosition() {
        if (this.s != this.msaStart) {
            this.opFail();
        }
    }
    
    private void opMemoryStartPush() {
        final int mem = this.code[this.ip++];
        this.pushMemStart(mem, this.s);
    }
    
    private void opMemoryStart() {
        final int mem = this.code[this.ip++];
        this.repeatStk[this.memStartStk + mem] = this.s;
    }
    
    private void opMemoryEndPush() {
        final int mem = this.code[this.ip++];
        this.pushMemEnd(mem, this.s);
    }
    
    private void opMemoryEnd() {
        final int mem = this.code[this.ip++];
        this.repeatStk[this.memEndStk + mem] = this.s;
    }
    
    private void opMemoryEndPushRec() {
        final int mem = this.code[this.ip++];
        final int stkp = this.getMemStart(mem);
        this.pushMemEnd(mem, this.s);
        this.repeatStk[this.memStartStk + mem] = stkp;
    }
    
    private void opMemoryEndRec() {
        final int mem = this.code[this.ip++];
        this.repeatStk[this.memEndStk + mem] = this.s;
        final int stkp = this.getMemStart(mem);
        if (BitStatus.bsAt(this.regex.btMemStart, mem)) {
            this.repeatStk[this.memStartStk + mem] = stkp;
        }
        else {
            this.repeatStk[this.memStartStk + mem] = this.stack[stkp].getMemPStr();
        }
        this.pushMemEndMark(mem);
    }
    
    private boolean backrefInvalid(final int mem) {
        return this.repeatStk[this.memEndStk + mem] == -1 || this.repeatStk[this.memStartStk + mem] == -1;
    }
    
    private int backrefStart(final int mem) {
        return BitStatus.bsAt(this.regex.btMemStart, mem) ? this.stack[this.repeatStk[this.memStartStk + mem]].getMemPStr() : this.repeatStk[this.memStartStk + mem];
    }
    
    private int backrefEnd(final int mem) {
        return BitStatus.bsAt(this.regex.btMemEnd, mem) ? this.stack[this.repeatStk[this.memEndStk + mem]].getMemPStr() : this.repeatStk[this.memEndStk + mem];
    }
    
    private void backref(final int mem) {
        if (mem > this.regex.numMem || this.backrefInvalid(mem)) {
            this.opFail();
            return;
        }
        int pstart = this.backrefStart(mem);
        final int pend = this.backrefEnd(mem);
        int n = pend - pstart;
        if (this.s + n > this.range) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        while (n-- > 0) {
            if (this.bytes[pstart++] != this.bytes[this.s++]) {
                this.opFail();
                return;
            }
        }
        if (this.sprev < this.range) {
            int len;
            while (this.sprev + (len = this.enc.length(this.bytes, this.sprev, this.end)) < this.s) {
                this.sprev += len;
            }
        }
    }
    
    private void opBackRef1() {
        this.backref(1);
    }
    
    private void opBackRef2() {
        this.backref(2);
    }
    
    private void opBackRefN() {
        this.backref(this.code[this.ip++]);
    }
    
    private void opBackRefNIC() {
        final int mem = this.code[this.ip++];
        if (mem > this.regex.numMem || this.backrefInvalid(mem)) {
            this.opFail();
            return;
        }
        final int pstart = this.backrefStart(mem);
        final int pend = this.backrefEnd(mem);
        final int n = pend - pstart;
        if (this.s + n > this.range) {
            this.opFail();
            return;
        }
        this.sprev = this.s;
        this.value = this.s;
        if (!this.stringCmpIC(this.regex.caseFoldFlag, pstart, this, n, this.end)) {
            this.opFail();
            return;
        }
        this.s = this.value;
        int len;
        while (this.sprev + (len = this.enc.length(this.bytes, this.sprev, this.end)) < this.s) {
            this.sprev += len;
        }
    }
    
    private void opBackRefMulti() {
        int tlen = 0;
        int i = 0;
    Label_0224:
        for (tlen = this.code[this.ip++], i = 0; i < tlen; ++i) {
            final int mem = this.code[this.ip++];
            if (!this.backrefInvalid(mem)) {
                int pstart = this.backrefStart(mem);
                final int pend = this.backrefEnd(mem);
                int n = pend - pstart;
                if (this.s + n > this.range) {
                    this.opFail();
                    return;
                }
                this.sprev = this.s;
                int swork = this.s;
                while (n-- > 0) {
                    if (this.bytes[pstart++] != this.bytes[swork++]) {
                        continue Label_0224;
                    }
                }
                this.s = swork;
                if (this.sprev < this.range) {
                    int len;
                    while (this.sprev + (len = this.enc.length(this.bytes, this.sprev, this.end)) < this.s) {
                        this.sprev += len;
                    }
                }
                this.ip += tlen - i - 1;
                break;
            }
        }
        if (i == tlen) {
            this.opFail();
        }
    }
    
    private void opBackRefMultiIC() {
        int tlen;
        int i;
        for (tlen = this.code[this.ip++], i = 0; i < tlen; ++i) {
            final int mem = this.code[this.ip++];
            if (!this.backrefInvalid(mem)) {
                final int pstart = this.backrefStart(mem);
                final int pend = this.backrefEnd(mem);
                final int n = pend - pstart;
                if (this.s + n > this.range) {
                    this.opFail();
                    return;
                }
                this.sprev = this.s;
                this.value = this.s;
                if (this.stringCmpIC(this.regex.caseFoldFlag, pstart, this, n, this.end)) {
                    this.s = this.value;
                    int len;
                    while (this.sprev + (len = this.enc.length(this.bytes, this.sprev, this.end)) < this.s) {
                        this.sprev += len;
                    }
                    this.ip += tlen - i - 1;
                    break;
                }
            }
        }
        if (i == tlen) {
            this.opFail();
        }
    }
    
    private boolean memIsInMemp(final int mem, final int num, int memp) {
        for (int i = 0; i < num; ++i) {
            final int m = this.code[memp++];
            if (mem == m) {
                return true;
            }
        }
        return false;
    }
    
    private boolean backrefMatchAtNestedLevel(final boolean ignoreCase, final int caseFoldFlag, final int nest, final int memNum, final int memp) {
        int pend = -1;
        int level = 0;
        for (int k = this.stk - 1; k >= 0; --k) {
            final StackEntry e = this.stack[k];
            if (e.type == 2048) {
                --level;
            }
            else if (e.type == 2304) {
                ++level;
            }
            else if (level == nest) {
                if (e.type == 256) {
                    if (this.memIsInMemp(e.getMemNum(), memNum, memp)) {
                        final int pstart = e.getMemPStr();
                        if (pend != -1) {
                            if (pend - pstart > this.end - this.s) {
                                return false;
                            }
                            int p = pstart;
                            this.value = this.s;
                            if (ignoreCase) {
                                if (!this.stringCmpIC(caseFoldFlag, pstart, this, pend - pstart, this.end)) {
                                    return false;
                                }
                            }
                            else {
                                while (p < pend) {
                                    if (this.bytes[p++] != this.bytes[this.value++]) {
                                        return false;
                                    }
                                }
                            }
                            this.s = this.value;
                            return true;
                        }
                    }
                }
                else if (e.type == 33280 && this.memIsInMemp(e.getMemNum(), memNum, memp)) {
                    pend = e.getMemPStr();
                }
            }
        }
        return false;
    }
    
    private void opBackRefAtLevel() {
        final int ic = this.code[this.ip++];
        final int level = this.code[this.ip++];
        final int tlen = this.code[this.ip++];
        this.sprev = this.s;
        if (this.backrefMatchAtNestedLevel(ic != 0, this.regex.caseFoldFlag, level, tlen, this.ip)) {
            int len;
            while (this.sprev + (len = this.enc.length(this.bytes, this.sprev, this.end)) < this.s) {
                this.sprev += len;
            }
            this.ip += tlen;
            return;
        }
        this.opFail();
    }
    
    private void opSetOptionPush() {
        this.pushAlt(this.ip, this.s, this.sprev);
        this.ip += 3;
    }
    
    private void opSetOption() {
    }
    
    private void opNullCheckStart() {
        final int mem = this.code[this.ip++];
        this.pushNullCheckStart(mem, this.s);
    }
    
    private void nullCheckFound() {
        switch (this.code[this.ip++]) {
            case 55:
            case 56: {
                ++this.ip;
                break;
            }
            case 62:
            case 63:
            case 64:
            case 65: {
                ++this.ip;
                break;
            }
            default: {
                throw new InternalException("unexpected bytecode (bug)");
            }
        }
    }
    
    private void opNullCheckEnd() {
        final int mem = this.code[this.ip++];
        final int isNull = this.nullCheck(mem, this.s);
        if (isNull != 0) {
            this.nullCheckFound();
        }
    }
    
    private void opNullCheckEndMemST() {
        final int mem = this.code[this.ip++];
        final int isNull = this.nullCheckMemSt(mem, this.s);
        if (isNull != 0) {
            if (isNull == -1) {
                this.opFail();
                return;
            }
            this.nullCheckFound();
        }
    }
    
    private void opNullCheckEndMemSTPush() {
        final int mem = this.code[this.ip++];
        final int isNull = this.nullCheckMemStRec(mem, this.s);
        if (isNull != 0) {
            if (isNull == -1) {
                this.opFail();
                return;
            }
            this.nullCheckFound();
        }
        else {
            this.pushNullCheckEnd(mem);
        }
    }
    
    private void opJump() {
        this.ip += this.code[this.ip] + 1;
    }
    
    private void opPush() {
        final int addr = this.code[this.ip++];
        this.pushAlt(this.ip + addr, this.s, this.sprev);
    }
    
    private void opStateCheckPush() {
        final int mem = this.code[this.ip++];
        if (this.stateCheckVal(this.s, mem)) {
            this.opFail();
            return;
        }
        final int addr = this.code[this.ip++];
        this.pushAltWithStateCheck(this.ip + addr, this.s, this.sprev, mem);
    }
    
    private void opStateCheckPushOrJump() {
        final int mem = this.code[this.ip++];
        final int addr = this.code[this.ip++];
        if (this.stateCheckVal(this.s, mem)) {
            this.ip += addr;
        }
        else {
            this.pushAltWithStateCheck(this.ip + addr, this.s, this.sprev, mem);
        }
    }
    
    private void opStateCheck() {
        final int mem = this.code[this.ip++];
        if (this.stateCheckVal(this.s, mem)) {
            this.opFail();
            return;
        }
        this.pushStateCheck(this.s, mem);
    }
    
    private void opPop() {
        this.popOne();
    }
    
    private void opPushOrJumpExact1() {
        final int addr = this.code[this.ip++];
        if (this.code[this.ip] == this.bytes[this.s] && this.s < this.range) {
            ++this.ip;
            this.pushAlt(this.ip + addr, this.s, this.sprev);
            return;
        }
        this.ip += addr + 1;
    }
    
    private void opPushIfPeekNext() {
        final int addr = this.code[this.ip++];
        if (this.s < this.range && this.code[this.ip] == this.bytes[this.s]) {
            ++this.ip;
            this.pushAlt(this.ip + addr, this.s, this.sprev);
            return;
        }
        ++this.ip;
    }
    
    private void opRepeat() {
        final int mem = this.code[this.ip++];
        final int addr = this.code[this.ip++];
        this.repeatStk[mem] = this.stk;
        this.pushRepeat(mem, this.ip);
        if (this.regex.repeatRangeLo[mem] == 0) {
            this.pushAlt(this.ip + addr, this.s, this.sprev);
        }
    }
    
    private void opRepeatNG() {
        final int mem = this.code[this.ip++];
        final int addr = this.code[this.ip++];
        this.repeatStk[mem] = this.stk;
        this.pushRepeat(mem, this.ip);
        if (this.regex.repeatRangeLo[mem] == 0) {
            this.pushAlt(this.ip, this.s, this.sprev);
            this.ip += addr;
        }
    }
    
    private void repeatInc(final int mem, final int si) {
        final StackEntry e = this.stack[si];
        e.increaseRepeatCount();
        if (e.getRepeatCount() < this.regex.repeatRangeHi[mem]) {
            if (e.getRepeatCount() >= this.regex.repeatRangeLo[mem]) {
                this.pushAlt(this.ip, this.s, this.sprev);
                this.ip = e.getRepeatPCode();
            }
            else {
                this.ip = e.getRepeatPCode();
            }
        }
        this.pushRepeatInc(si);
    }
    
    private void opRepeatInc() {
        final int mem = this.code[this.ip++];
        final int si = this.repeatStk[mem];
        this.repeatInc(mem, si);
    }
    
    private void opRepeatIncSG() {
        final int mem = this.code[this.ip++];
        final int si = this.getRepeat(mem);
        this.repeatInc(mem, si);
    }
    
    private void repeatIncNG(final int mem, final int si) {
        final StackEntry e = this.stack[si];
        e.increaseRepeatCount();
        if (e.getRepeatCount() < this.regex.repeatRangeHi[mem]) {
            if (e.getRepeatCount() >= this.regex.repeatRangeLo[mem]) {
                final int pcode = e.getRepeatPCode();
                this.pushRepeatInc(si);
                this.pushAlt(pcode, this.s, this.sprev);
            }
            else {
                this.ip = e.getRepeatPCode();
                this.pushRepeatInc(si);
            }
        }
        else if (e.getRepeatCount() == this.regex.repeatRangeHi[mem]) {
            this.pushRepeatInc(si);
        }
    }
    
    private void opRepeatIncNG() {
        final int mem = this.code[this.ip++];
        final int si = this.repeatStk[mem];
        this.repeatIncNG(mem, si);
    }
    
    private void opRepeatIncNGSG() {
        final int mem = this.code[this.ip++];
        final int si = this.getRepeat(mem);
        this.repeatIncNG(mem, si);
    }
    
    private void opPushPos() {
        this.pushPos(this.s, this.sprev);
    }
    
    private void opPopPos() {
        final StackEntry e = this.stack[this.posEnd()];
        this.s = e.getStatePStr();
        this.sprev = e.getStatePStrPrev();
    }
    
    private void opPushPosNot() {
        final int addr = this.code[this.ip++];
        this.pushPosNot(this.ip + addr, this.s, this.sprev);
    }
    
    private void opFailPos() {
        this.popTilPosNot();
        this.opFail();
    }
    
    private void opPushStopBT() {
        this.pushStopBT();
    }
    
    private void opPopStopBT() {
        this.stopBtEnd();
    }
    
    private void opLookBehind() {
        final int tlen = this.code[this.ip++];
        this.s = this.enc.stepBack(this.bytes, this.str, this.s, this.end, tlen);
        if (this.s == -1) {
            this.opFail();
            return;
        }
        this.sprev = this.enc.prevCharHead(this.bytes, this.str, this.s, this.end);
    }
    
    private void opLookBehindSb() {
        final int tlen = this.code[this.ip++];
        this.s -= tlen;
        if (this.s < this.str) {
            this.opFail();
            return;
        }
        this.sprev = ((this.s == this.str) ? -1 : (this.s - 1));
    }
    
    private void opPushLookBehindNot() {
        final int addr = this.code[this.ip++];
        final int tlen = this.code[this.ip++];
        final int q = this.enc.stepBack(this.bytes, this.str, this.s, this.end, tlen);
        if (q == -1) {
            this.ip += addr;
        }
        else {
            this.pushLookBehindNot(this.ip + addr, this.s, this.sprev);
            this.s = q;
            this.sprev = this.enc.prevCharHead(this.bytes, this.str, this.s, this.end);
        }
    }
    
    private void opFailLookBehindNot() {
        this.popTilLookBehindNot();
        this.opFail();
    }
    
    private void opCall() {
        final int addr = this.code[this.ip++];
        this.pushCallFrame(this.ip);
        this.ip = addr;
    }
    
    private void opReturn() {
        this.ip = this.sreturn();
        this.pushReturn();
    }
    
    private void opFail() {
        if (this.stack == null) {
            this.ip = this.regex.codeLength - 1;
            return;
        }
        final StackEntry e = this.pop();
        this.ip = e.getStatePCode();
        this.s = e.getStatePStr();
        this.sprev = e.getStatePStrPrev();
    }
    
    private int finish() {
        return this.bestLen;
    }
}

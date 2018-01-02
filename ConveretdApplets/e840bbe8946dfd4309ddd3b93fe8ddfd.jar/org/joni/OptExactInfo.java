// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.jcodings.Encoding;

final class OptExactInfo
{
    static final int OPT_EXACT_MAXLEN = 24;
    final MinMaxLen mmd;
    final OptAnchorInfo anchor;
    boolean reachEnd;
    boolean ignoreCase;
    int length;
    final byte[] s;
    private static final int COMP_EM_BASE = 20;
    
    OptExactInfo() {
        this.mmd = new MinMaxLen();
        this.anchor = new OptAnchorInfo();
        this.s = new byte[24];
    }
    
    boolean isFull() {
        return this.length >= 24;
    }
    
    void clear() {
        this.mmd.clear();
        this.anchor.clear();
        this.reachEnd = false;
        this.ignoreCase = false;
        this.length = 0;
        this.s[0] = 0;
    }
    
    void copy(final OptExactInfo other) {
        this.mmd.copy(other.mmd);
        this.anchor.copy(other.anchor);
        this.reachEnd = other.reachEnd;
        this.ignoreCase = other.ignoreCase;
        this.length = other.length;
        System.arraycopy(other.s, 0, this.s, 0, 24);
    }
    
    void concat(final OptExactInfo other, final Encoding enc) {
        if (!this.ignoreCase && other.ignoreCase) {
            if (this.length >= other.length) {
                return;
            }
            this.ignoreCase = true;
        }
        int p = 0;
        final int end = p + other.length;
        int i = this.length;
        while (p < end) {
            final int len = enc.length(other.s, p, end);
            if (i + len > 24) {
                break;
            }
            for (int j = 0; j < len && p < end; this.s[i++] = other.s[p++], ++j) {}
        }
        this.length = i;
        this.reachEnd = (p == end && other.reachEnd);
        final OptAnchorInfo tmp = new OptAnchorInfo();
        tmp.concat(this.anchor, other.anchor, 1, 1);
        if (!other.reachEnd) {
            tmp.rightAnchor = 0;
        }
        this.anchor.copy(tmp);
    }
    
    void concatStr(final byte[] bytes, int p, final int end, final boolean raw, final Encoding enc) {
        int i = this.length;
        while (p < end && i < 24) {
            final int len = enc.length(bytes, p, end);
            if (i + len > 24) {
                break;
            }
            for (int j = 0; j < len && p < end; this.s[i++] = bytes[p++], ++j) {}
        }
        this.length = i;
    }
    
    void altMerge(final OptExactInfo other, final OptEnvironment env) {
        if (other.length == 0 || this.length == 0) {
            this.clear();
            return;
        }
        if (!this.mmd.equal(other.mmd)) {
            this.clear();
            return;
        }
        int i;
        int len;
        for (i = 0; i < this.length && i < other.length; i += len) {
            if (this.s[i] != other.s[i]) {
                break;
            }
            int j;
            for (len = env.enc.length(this.s, i, this.length), j = 1; j < len && this.s[i + j] == other.s[i + j]; ++j) {}
            if (j < len) {
                break;
            }
        }
        if (!other.reachEnd || i < other.length || i < this.length) {
            this.reachEnd = false;
        }
        this.length = i;
        this.ignoreCase |= other.ignoreCase;
        this.anchor.altMerge(other.anchor);
        if (!this.reachEnd) {
            this.anchor.rightAnchor = 0;
        }
    }
    
    void select(final OptExactInfo alt, final Encoding enc) {
        int v1 = this.length;
        int v2 = alt.length;
        if (v2 == 0) {
            return;
        }
        if (v1 == 0) {
            this.copy(alt);
            return;
        }
        if (v1 <= 2 && v2 <= 2) {
            v2 = OptMapInfo.positionValue(enc, this.s[0] & 0xFF);
            v1 = OptMapInfo.positionValue(enc, alt.s[0] & 0xFF);
            if (this.length > 1) {
                v1 += 5;
            }
            if (alt.length > 1) {
                v2 += 5;
            }
        }
        if (!this.ignoreCase) {
            v1 *= 2;
        }
        if (!alt.ignoreCase) {
            v2 *= 2;
        }
        if (this.mmd.compareDistanceValue(alt.mmd, v1, v2) > 0) {
            this.copy(alt);
        }
    }
    
    int compare(final OptMapInfo m) {
        if (m.value <= 0) {
            return -1;
        }
        final int ve = 20 * this.length * (this.ignoreCase ? 1 : 2);
        final int vm = 200 / m.value;
        return this.mmd.compareDistanceValue(m.mmd, ve, vm);
    }
}

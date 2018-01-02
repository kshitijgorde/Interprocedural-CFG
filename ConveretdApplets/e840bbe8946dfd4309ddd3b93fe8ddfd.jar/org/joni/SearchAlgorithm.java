// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.jcodings.IntHolder;
import org.jcodings.Encoding;

public abstract class SearchAlgorithm
{
    public static final SearchAlgorithm NONE;
    public static final SearchAlgorithm SLOW;
    public static final SearchAlgorithm SLOW_SB;
    public static final SearchAlgorithm SLOW_IC_SB;
    public static final SearchAlgorithm BM;
    public static final SearchAlgorithm BM_NOT_REV;
    public static final SearchAlgorithm MAP;
    public static final SearchAlgorithm MAP_SB;
    
    public abstract String getName();
    
    public abstract int search(final Regex p0, final byte[] p1, final int p2, final int p3, final int p4);
    
    public abstract int searchBackward(final Regex p0, final byte[] p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    static {
        NONE = new SearchAlgorithm() {
            public final String getName() {
                return "NONE";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                return textP;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                return textP;
            }
        };
        SLOW = new SearchAlgorithm() {
            public final String getName() {
                return "EXACT";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final Encoding enc = regex.enc;
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int end = textEnd - (targetEnd - targetP - 1);
                if (end > textRange) {
                    end = textRange;
                }
                for (int s = textP; s < end; s += enc.length(text, s, textEnd)) {
                    if (text[s] == target[targetP]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final Encoding enc = regex.enc;
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int s = textEnd - (targetEnd - targetP);
                if (s > textStart) {
                    s = textStart;
                }
                else {
                    s = enc.leftAdjustCharHead(text, adjustText, s, textEnd);
                }
                while (s >= textP) {
                    if (text[s] == target[targetP]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                    s = enc.prevCharHead(text, adjustText, s, textEnd);
                }
                return -1;
            }
        };
        SLOW_SB = new SearchAlgorithm() {
            public final String getName() {
                return "EXACT_SB";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int end = textEnd - (targetEnd - targetP - 1);
                if (end > textRange) {
                    end = textRange;
                }
                for (int s = textP; s < end; ++s) {
                    if (text[s] == target[targetP]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int s = textEnd - (targetEnd - targetP);
                if (s > textStart) {
                    s = textStart;
                }
                while (s >= textP) {
                    if (text[s] == target[targetP]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                    --s;
                }
                return -1;
            }
        };
        SLOW_IC_SB = new SearchAlgorithm() {
            public final String getName() {
                return "EXACT_IC_SB";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final byte[] toLowerTable = regex.enc.toLowerCaseTable();
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int end = textEnd - (targetEnd - targetP - 1);
                if (end > textRange) {
                    end = textRange;
                }
                for (int s = textP; s < end; ++s) {
                    if (target[targetP] == toLowerTable[text[s] & 0xFF]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == toLowerTable[text[p++] & 0xFF]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final byte[] toLowerTable = regex.enc.toLowerCaseTable();
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int s = textEnd - (targetEnd - targetP);
                if (s > textStart) {
                    s = textStart;
                }
                while (s >= textP) {
                    if (target[targetP] == toLowerTable[text[s] & 0xFF]) {
                        int p;
                        int t;
                        for (p = s + 1, t = targetP + 1; t < targetEnd && target[t] == toLowerTable[text[p++] & 0xFF]; ++t) {}
                        if (t == targetEnd) {
                            return s;
                        }
                    }
                    --s;
                }
                return -1;
            }
        };
        BM = new SearchAlgorithm() {
            private static final int BM_BACKWARD_SEARCH_LENGTH_THRESHOLD = 100;
            
            public final String getName() {
                return "EXACT_BM";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                int end = textRange + (targetEnd - targetP) - 1;
                if (end > textEnd) {
                    end = textEnd;
                }
                final int tail = targetEnd - 1;
                int s = textP + (targetEnd - targetP) - 1;
                if (regex.intMap == null) {
                    while (s < end) {
                        int p;
                        int t;
                        for (p = s, t = tail; t >= targetP && text[p] == target[t]; --p, --t) {}
                        if (t < targetP) {
                            return p + 1;
                        }
                        s += regex.map[text[s] & 0xFF];
                    }
                }
                else {
                    while (s < end) {
                        int p;
                        int t;
                        for (p = s, t = tail; t >= targetP && text[p] == target[t]; --p, --t) {}
                        if (t < targetP) {
                            return p + 1;
                        }
                        s += regex.intMap[text[s] & 0xFF];
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final Encoding enc = regex.enc;
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                if (regex.intMapBackward == null) {
                    if (s_ - range_ < 100) {
                        return SearchAlgorithm$5.SLOW.searchBackward(regex, text, textP, adjustText, textEnd, textStart, s_, range_);
                    }
                    this.setBmBackwardSkip(regex, target, targetP, targetEnd);
                }
                int s = textEnd - (targetEnd - targetP);
                if (textStart < s) {
                    s = textStart;
                }
                else {
                    s = enc.leftAdjustCharHead(text, adjustText, s, textEnd);
                }
                while (s >= textP) {
                    int p;
                    int t;
                    for (p = s, t = targetP; t < targetEnd && text[p] == target[t]; ++p, ++t) {}
                    if (t == targetEnd) {
                        return s;
                    }
                    s -= regex.intMapBackward[text[s] & 0xFF];
                    s = enc.leftAdjustCharHead(text, adjustText, s, textEnd);
                }
                return -1;
            }
            
            private void setBmBackwardSkip(final Regex regex, final byte[] bytes, final int p, final int end) {
                int[] skip;
                if (regex.intMapBackward == null) {
                    skip = new int[256];
                    regex.intMapBackward = skip;
                }
                else {
                    skip = regex.intMapBackward;
                }
                final int len = end - p;
                for (int i = 0; i < 256; ++i) {
                    skip[i] = len;
                }
                for (int i = len - 1; i > 0; --i) {
                    skip[bytes[i] & 0xFF] = i;
                }
            }
        };
        BM_NOT_REV = new SearchAlgorithm() {
            public final String getName() {
                return "EXACT_BM_NOT_REV";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final Encoding enc = regex.enc;
                final byte[] target = regex.exact;
                final int targetP = regex.exactP;
                final int targetEnd = regex.exactEnd;
                final int tail = targetEnd - 1;
                final int tlen1 = tail - targetP;
                int end = textRange;
                if (end + tlen1 > textEnd) {
                    end = textEnd - tlen1;
                }
                int s = textP;
                if (regex.intMap == null) {
                    while (s < end) {
                        int p;
                        int se;
                        int t;
                        for (se = (p = s + tlen1), t = tail; t >= targetP && text[p] == target[t]; --p, --t) {}
                        if (t < targetP) {
                            return s;
                        }
                        final int skip = regex.map[text[se] & 0xFF];
                        t = s;
                        do {
                            s += enc.length(text, s, textEnd);
                        } while (s - t < skip && s < end);
                    }
                }
                else {
                    while (s < end) {
                        int p;
                        int se;
                        int t;
                        for (se = (p = s + tlen1), t = tail; t >= targetP && text[p] == target[t]; --p, --t) {}
                        if (t < targetP) {
                            return s;
                        }
                        final int skip = regex.intMap[text[se] & 0xFF];
                        t = s;
                        do {
                            s += enc.length(text, s, textEnd);
                        } while (s - t < skip && s < end);
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                return SearchAlgorithm$6.BM.searchBackward(regex, text, textP, adjustText, textEnd, textStart, s_, range_);
            }
        };
        MAP = new SearchAlgorithm() {
            public final String getName() {
                return "MAP";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final Encoding enc = regex.enc;
                final byte[] map = regex.map;
                for (int s = textP; s < textRange; s += enc.length(text, s, textEnd)) {
                    if (map[text[s] & 0xFF] != 0) {
                        return s;
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final Encoding enc = regex.enc;
                final byte[] map = regex.map;
                int s = textStart;
                if (s >= textEnd) {
                    s = textEnd - 1;
                }
                while (s >= textP) {
                    if (map[text[s] & 0xFF] != 0) {
                        return s;
                    }
                    s = enc.prevCharHead(text, adjustText, s, textEnd);
                }
                return -1;
            }
        };
        MAP_SB = new SearchAlgorithm() {
            public final String getName() {
                return "MAP_SB";
            }
            
            public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
                final byte[] map = regex.map;
                for (int s = textP; s < textRange; ++s) {
                    if (map[text[s] & 0xFF] != 0) {
                        return s;
                    }
                }
                return -1;
            }
            
            public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
                final byte[] map = regex.map;
                int s = textStart;
                if (s >= textEnd) {
                    s = textEnd - 1;
                }
                while (s >= textP) {
                    if (map[text[s] & 0xFF] != 0) {
                        return s;
                    }
                    --s;
                }
                return -1;
            }
        };
    }
    
    public static final class SLOW_IC extends SearchAlgorithm
    {
        private final byte[] buf;
        private final IntHolder holder;
        private final int caseFoldFlag;
        private final Encoding enc;
        
        public SLOW_IC(final Regex regex) {
            this.buf = new byte[18];
            this.holder = new IntHolder();
            this.caseFoldFlag = regex.caseFoldFlag;
            this.enc = regex.enc;
        }
        
        public final String getName() {
            return "EXACT_IC";
        }
        
        public final int search(final Regex regex, final byte[] text, final int textP, final int textEnd, final int textRange) {
            final byte[] target = regex.exact;
            final int targetP = regex.exactP;
            final int targetEnd = regex.exactEnd;
            int end = textEnd - (targetEnd - targetP - 1);
            if (end > textRange) {
                end = textRange;
            }
            for (int s = textP; s < end; s += this.enc.length(text, s, textEnd)) {
                if (this.lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) {
                    return s;
                }
            }
            return -1;
        }
        
        public final int searchBackward(final Regex regex, final byte[] text, final int textP, final int adjustText, final int textEnd, final int textStart, final int s_, final int range_) {
            final byte[] target = regex.exact;
            final int targetP = regex.exactP;
            final int targetEnd = regex.exactEnd;
            int s = textEnd - (targetEnd - targetP);
            if (s > textStart) {
                s = textStart;
            }
            else {
                s = this.enc.leftAdjustCharHead(text, adjustText, s, textEnd);
            }
            while (s >= textP) {
                if (this.lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) {
                    return s;
                }
                s = this.enc.prevCharHead(text, adjustText, s, textEnd);
            }
            return -1;
        }
        
        private boolean lowerCaseMatch(final byte[] t, int tP, final int tEnd, final byte[] bytes, final int p, final int end) {
            this.holder.value = p;
            while (tP < tEnd) {
                int lowlen = this.enc.mbcCaseFold(this.caseFoldFlag, bytes, this.holder, end, this.buf);
                if (lowlen == 1) {
                    if (t[tP++] != this.buf[0]) {
                        return false;
                    }
                    continue;
                }
                else {
                    int q = 0;
                    while (lowlen > 0) {
                        if (t[tP++] != this.buf[q++]) {
                            return false;
                        }
                        --lowlen;
                    }
                }
            }
            return true;
        }
    }
}

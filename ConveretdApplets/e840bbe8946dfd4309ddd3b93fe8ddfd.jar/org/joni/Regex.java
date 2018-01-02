// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import java.util.IllegalFormatConversionException;
import org.joni.exception.InternalException;
import java.util.Iterator;
import org.joni.exception.ValueException;
import org.jcodings.util.BytesHash;
import org.jcodings.Encoding;
import org.joni.constants.RegexState;

public final class Regex implements RegexState
{
    int[] code;
    int codeLength;
    boolean stackNeeded;
    Object[] operands;
    int operandLength;
    int state;
    int numMem;
    int numRepeat;
    int numNullCheck;
    int numCombExpCheck;
    int numCall;
    int captureHistory;
    int btMemStart;
    int btMemEnd;
    int stackPopLevel;
    int[] repeatRangeLo;
    int[] repeatRangeHi;
    public WarnCallback warnings;
    public MatcherFactory factory;
    final Encoding enc;
    int options;
    int userOptions;
    Object userObject;
    final int caseFoldFlag;
    BytesHash<NameEntry> nameTable;
    SearchAlgorithm searchAlgorithm;
    int thresholdLength;
    int anchor;
    int anchorDmin;
    int anchorDmax;
    int subAnchor;
    byte[] exact;
    int exactP;
    int exactEnd;
    byte[] map;
    int[] intMap;
    int[] intMapBackward;
    int dMin;
    int dMax;
    
    public Regex(final byte[] bytes, final int p, final int end, final int option, final Encoding enc) {
        this(bytes, p, end, option, enc, Syntax.RUBY, WarnCallback.DEFAULT);
    }
    
    public Regex(final byte[] bytes, final int p, final int end, final int option, final Encoding enc, final Syntax syntax) {
        this(bytes, p, end, option, 1073741824, enc, syntax, WarnCallback.DEFAULT);
    }
    
    public Regex(final byte[] bytes, final int p, final int end, final int option, final Encoding enc, final WarnCallback warnings) {
        this(bytes, p, end, option, enc, Syntax.RUBY, warnings);
    }
    
    public Regex(final byte[] bytes, final int p, final int end, final int option, final Encoding enc, final Syntax syntax, final WarnCallback warnings) {
        this(bytes, p, end, option, 1073741824, enc, syntax, warnings);
    }
    
    public Regex(final byte[] bytes, final int p, final int end, int option, final int caseFoldFlag, final Encoding enc, final Syntax syntax, final WarnCallback warnings) {
        if ((option & 0x180) == 0x180) {
            throw new ValueException("invalid combination of options");
        }
        if ((option & 0x40) != 0x0) {
            option |= syntax.options;
            option &= 0xFFFFFFF7;
        }
        else {
            option |= syntax.options;
        }
        this.enc = enc;
        this.options = option;
        this.caseFoldFlag = caseFoldFlag;
        this.warnings = warnings;
        new Analyser(new ScanEnvironment(this, syntax), bytes, p, end).compile();
        this.warnings = null;
    }
    
    public Matcher matcher(final byte[] bytes) {
        return this.matcher(bytes, 0, bytes.length);
    }
    
    public Matcher matcher(final byte[] bytes, final int p, final int end) {
        return this.factory.create(this, bytes, p, end);
    }
    
    public int numberOfCaptures() {
        return this.numMem;
    }
    
    public int numberOfCaptureHistories() {
        return 0;
    }
    
    String nameTableToString() {
        final StringBuilder sb = new StringBuilder();
        if (this.nameTable != null) {
            sb.append("name table\n");
            for (final NameEntry ne : this.nameTable) {
                sb.append("  " + ne + "\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    NameEntry nameFind(final byte[] name, final int nameP, final int nameEnd) {
        if (this.nameTable != null) {
            return this.nameTable.get(name, nameP, nameEnd);
        }
        return null;
    }
    
    void renumberNameTable(final int[] map) {
        if (this.nameTable != null) {
            for (final NameEntry e : this.nameTable) {
                if (e.backNum > 1) {
                    for (int i = 0; i < e.backNum; ++i) {
                        e.backRefs[i] = map[e.backRefs[i]];
                    }
                }
                else {
                    if (e.backNum != 1) {
                        continue;
                    }
                    e.backRef1 = map[e.backRef1];
                }
            }
        }
    }
    
    public int numberOfNames() {
        return (this.nameTable == null) ? 0 : this.nameTable.size();
    }
    
    void nameAdd(final byte[] name, final int nameP, final int nameEnd, final int backRef, final Syntax syntax) {
        if (nameEnd - nameP <= 0) {
            throw new ValueException("group name is empty");
        }
        NameEntry e = null;
        if (this.nameTable == null) {
            this.nameTable = new BytesHash<NameEntry>();
        }
        else {
            e = this.nameFind(name, nameP, nameEnd);
        }
        if (e == null) {
            e = new NameEntry(name, nameP, nameEnd);
            this.nameTable.putDirect(name, nameP, nameEnd, e);
        }
        else if (e.backNum >= 1 && !syntax.allowMultiplexDefinitionName()) {
            throw new ValueException("multiplex defined name <%n>", new String(name, nameP, nameEnd - nameP));
        }
        e.addBackref(backRef);
    }
    
    NameEntry nameToGroupNumbers(final byte[] name, final int nameP, final int nameEnd) {
        return this.nameFind(name, nameP, nameEnd);
    }
    
    public int nameToBackrefNumber(final byte[] name, final int nameP, final int nameEnd, final Region region) {
        final NameEntry e = this.nameToGroupNumbers(name, nameP, nameEnd);
        if (e == null) {
            throw new ValueException("undefined name <%n> reference", new String(name, nameP, nameEnd - nameP));
        }
        switch (e.backNum) {
            case 0: {
                throw new InternalException("internal parser error (bug)");
            }
            case 1: {
                return e.backRef1;
            }
            default: {
                if (region != null) {
                    for (int i = e.backNum - 1; i >= 0; --i) {
                        if (region.beg[e.backRefs[i]] != -1) {
                            return e.backRefs[i];
                        }
                    }
                }
                return e.backRefs[e.backNum - 1];
            }
        }
    }
    
    public Iterator<NameEntry> namedBackrefIterator() {
        return this.nameTable.iterator();
    }
    
    public boolean noNameGroupIsActive(final Syntax syntax) {
        return !Option.isDontCaptureGroup(this.options) && (this.numberOfNames() <= 0 || !syntax.captureOnlyNamedGroup() || Option.isCaptureGroup(this.options));
    }
    
    void setupBMSkipMap() {
        final byte[] bytes = this.exact;
        final int p = this.exactP;
        final int end = this.exactEnd;
        final int len = end - p;
        if (len < 256) {
            if (this.map == null) {
                this.map = new byte[256];
            }
            for (int i = 0; i < 256; ++i) {
                this.map[i] = (byte)len;
            }
            for (int i = 0; i < len - 1; ++i) {
                this.map[bytes[p + i] & 0xFF] = (byte)(len - 1 - i);
            }
        }
        else {
            if (this.intMap == null) {
                this.intMap = new int[256];
            }
            for (int i = 0; i < len - 1; ++i) {
                this.intMap[bytes[p + i] & 0xFF] = len - 1 - i;
            }
        }
    }
    
    void setExactInfo(final OptExactInfo e) {
        if (e.length == 0) {
            return;
        }
        this.exact = e.s;
        this.exactP = 0;
        this.exactEnd = e.length;
        if (e.ignoreCase) {
            this.searchAlgorithm = ((this.enc.toLowerCaseTable() != null) ? SearchAlgorithm.SLOW_IC_SB : new SearchAlgorithm.SLOW_IC(this));
        }
        else {
            final boolean allowReverse = this.enc.isReverseMatchAllowed(this.exact, this.exactP, this.exactEnd);
            if (e.length >= 3 || (e.length >= 2 && allowReverse)) {
                this.setupBMSkipMap();
                if (allowReverse) {
                    this.searchAlgorithm = SearchAlgorithm.BM;
                }
                else {
                    this.searchAlgorithm = SearchAlgorithm.BM_NOT_REV;
                }
            }
            else {
                this.searchAlgorithm = (this.enc.isSingleByte() ? SearchAlgorithm.SLOW_SB : SearchAlgorithm.SLOW);
            }
        }
        this.dMin = e.mmd.min;
        this.dMax = e.mmd.max;
        if (this.dMin != Integer.MAX_VALUE) {
            this.thresholdLength = this.dMin + (this.exactEnd - this.exactP);
        }
    }
    
    void setOptimizeMapInfo(final OptMapInfo m) {
        this.map = m.map;
        this.searchAlgorithm = (this.enc.isSingleByte() ? SearchAlgorithm.MAP_SB : SearchAlgorithm.MAP);
        this.dMin = m.mmd.min;
        this.dMax = m.mmd.max;
        if (this.dMin != Integer.MAX_VALUE) {
            this.thresholdLength = this.dMin + 1;
        }
    }
    
    void setSubAnchor(final OptAnchorInfo anc) {
        this.subAnchor |= (anc.leftAnchor & 0x2);
        this.subAnchor |= (anc.rightAnchor & 0x20);
    }
    
    void clearOptimizeInfo() {
        this.searchAlgorithm = SearchAlgorithm.NONE;
        this.anchor = 0;
        this.anchorDmax = 0;
        this.anchorDmin = 0;
        this.subAnchor = 0;
        this.exact = null;
        final boolean b = false;
        this.exactEnd = (b ? 1 : 0);
        this.exactP = (b ? 1 : 0);
    }
    
    public String encStringToString(final byte[] bytes, int p, final int end) {
        final StringBuilder sb = new StringBuilder("\nPATTERN: /");
        if (this.enc.minLength() > 1) {
            for (int p_ = p; p_ < end; p_ += this.enc.length(bytes, p_, end)) {
                final int code = this.enc.mbcToCode(bytes, p_, end);
                if (code >= 128) {
                    try {
                        sb.append(String.format(" 0x%04x ", code));
                    }
                    catch (IllegalFormatConversionException ifce) {
                        sb.append(code);
                    }
                }
                else {
                    sb.append((char)code);
                }
            }
        }
        else {
            while (p < end) {
                sb.append(new String(new byte[] { bytes[p] }));
                ++p;
            }
        }
        return sb.append("/").toString();
    }
    
    public String optimizeInfoToString() {
        String s = "";
        s = s + "optimize: " + this.searchAlgorithm.getName() + "\n";
        s = s + "  anchor:     " + OptAnchorInfo.anchorToString(this.anchor);
        if ((this.anchor & 0x18) != 0x0) {
            s += MinMaxLen.distanceRangeToString(this.anchorDmin, this.anchorDmax);
        }
        s += "\n";
        if (this.searchAlgorithm != SearchAlgorithm.NONE) {
            s = s + "  sub anchor: " + OptAnchorInfo.anchorToString(this.subAnchor) + "\n";
        }
        s = s + "dmin: " + this.dMin + " dmax: " + this.dMax + "\n";
        s = s + "threshold length: " + this.thresholdLength + "\n";
        if (this.exact != null) {
            s = s + "exact: [" + new String(this.exact, this.exactP, this.exactEnd - this.exactP) + "]: length: " + (this.exactEnd - this.exactP) + "\n";
        }
        else if (this.searchAlgorithm == SearchAlgorithm.MAP || this.searchAlgorithm == SearchAlgorithm.MAP_SB) {
            int n = 0;
            for (int i = 0; i < 256; ++i) {
                if (this.map[i] != 0) {
                    ++n;
                }
            }
            s = s + "map: n = " + n + "\n";
            if (n > 0) {
                int c = 0;
                s += "[";
                for (int j = 0; j < 256; ++j) {
                    if (this.map[j] != 0) {
                        if (c > 0) {
                            s += ", ";
                        }
                        ++c;
                        if (this.enc.maxLength() == 1 && this.enc.isPrint(j)) {
                            s += (char)j;
                        }
                        else {
                            s += j;
                        }
                    }
                }
                s += "]\n";
            }
        }
        return s;
    }
    
    public Encoding getEncoding() {
        return this.enc;
    }
    
    public int getOptions() {
        return this.options;
    }
    
    public void setUserOptions(final int options) {
        this.userOptions = options;
    }
    
    public int getUserOptions() {
        return this.userOptions;
    }
    
    public void setUserObject(final Object object) {
        this.userObject = object;
    }
    
    public Object getUserObject() {
        return this.userObject;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.joni.exception.JOniException;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import java.util.Iterator;
import org.joni.NameEntry;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import org.jruby.util.StringSupport;
import java.util.Arrays;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.joni.Regex;
import org.joni.Region;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "MatchData" })
public class RubyMatchData extends RubyObject
{
    Region regs;
    int begin;
    int end;
    RubyString str;
    Regex pattern;
    RubyRegexp regexp;
    boolean charOffsetUpdated;
    Region charOffsets;
    private static ObjectAllocator MATCH_DATA_ALLOCATOR;
    private static final int MATCH_BUSY = 128;
    
    public static RubyClass createMatchDataClass(final Ruby runtime) {
        final RubyClass matchDataClass = runtime.defineClass("MatchData", runtime.getObject(), RubyMatchData.MATCH_DATA_ALLOCATOR);
        runtime.setMatchData(matchDataClass);
        matchDataClass.index = 27;
        matchDataClass.setReifiedClass(RubyMatchData.class);
        runtime.defineGlobalConstant("MatchingData", matchDataClass);
        matchDataClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyMatchData;
            }
        };
        matchDataClass.getMetaClass().undefineMethod("new");
        matchDataClass.defineAnnotatedMethods(RubyMatchData.class);
        return matchDataClass;
    }
    
    public RubyMatchData(final Ruby runtime) {
        super(runtime, runtime.getMatchData());
    }
    
    public RubyMatchData(final Ruby runtime, final RubyClass metaClass) {
        super(runtime, metaClass);
    }
    
    public int getNativeTypeIndex() {
        return 27;
    }
    
    private void updateCharOffset() {
        if (this.charOffsetUpdated) {
            return;
        }
        final int numRegs = (this.regs == null) ? 1 : this.regs.numRegs;
        if (this.charOffsets == null || this.charOffsets.numRegs < numRegs) {
            this.charOffsets = new Region(numRegs);
        }
        final Pair[] pairs = new Pair[numRegs * 2];
        for (int i = 0; i < pairs.length; ++i) {
            pairs[i] = new Pair();
        }
        int numPos = 0;
        if (this.regs == null) {
            pairs[numPos++].bytePos = this.begin;
            pairs[numPos++].bytePos = this.end;
        }
        else {
            for (int j = 0; j < numRegs; ++j) {
                if (this.regs.beg[j] >= 0) {
                    pairs[numPos++].bytePos = this.regs.beg[j];
                    pairs[numPos++].bytePos = this.regs.end[j];
                }
            }
        }
        Arrays.sort(pairs);
        final ByteList value = this.str.getByteList();
        final Encoding enc = value.getEncoding();
        final byte[] bytes = value.getUnsafeBytes();
        final int s;
        int p = s = value.getBegin();
        int c = 0;
        for (int k = 0; k < numPos; ++k) {
            final int q = s + pairs[k].bytePos;
            c += StringSupport.strLength(enc, bytes, p, q);
            pairs[k].charPos = c;
            p = q;
        }
        final Pair key = new Pair();
        if (this.regs == null) {
            key.bytePos = this.begin;
            this.charOffsets.beg[0] = pairs[Arrays.binarySearch(pairs, key)].charPos;
            key.bytePos = this.end;
            this.charOffsets.end[0] = pairs[Arrays.binarySearch(pairs, key)].charPos;
        }
        else {
            for (int l = 0; l < numRegs; ++l) {
                if (this.regs.beg[l] < 0) {
                    this.charOffsets.beg[l] = (this.charOffsets.end[l] = -1);
                }
                else {
                    key.bytePos = this.regs.beg[l];
                    this.charOffsets.beg[l] = pairs[Arrays.binarySearch(pairs, key)].charPos;
                    key.bytePos = this.regs.end[l];
                    this.charOffsets.end[l] = pairs[Arrays.binarySearch(pairs, key)].charPos;
                }
            }
        }
        this.charOffsetUpdated = true;
    }
    
    public final void use() {
        this.flags |= 0x80;
    }
    
    public final boolean used() {
        return (this.flags & 0x80) != 0x0;
    }
    
    private void check() {
        if (this.str == null) {
            throw this.getRuntime().newTypeError("uninitialized Match");
        }
    }
    
    private void checkLazyRegexp() {
        if (this.regexp == null) {
            this.regexp = RubyRegexp.newRegexp(this.getRuntime(), (ByteList)this.pattern.getUserObject(), this.pattern);
        }
    }
    
    private RubyString makeShared(final Ruby runtime, final RubyString str, final int begin, final int length) {
        if (runtime.is1_9()) {
            return str.makeShared19(runtime, begin, length);
        }
        return str.makeShared(runtime, begin, length);
    }
    
    private RubyArray match_array(final Ruby runtime, final int start) {
        this.check();
        if (this.regs != null) {
            final RubyArray arr = runtime.newArray(this.regs.numRegs - start);
            for (int i = start; i < this.regs.numRegs; ++i) {
                if (this.regs.beg[i] == -1) {
                    arr.append(runtime.getNil());
                }
                else {
                    final RubyString ss = this.makeShared(runtime, this.str, this.regs.beg[i], this.regs.end[i] - this.regs.beg[i]);
                    if (this.isTaint()) {
                        ss.setTaint(true);
                    }
                    arr.append(ss);
                }
            }
            return arr;
        }
        if (start != 0) {
            return runtime.newEmptyArray();
        }
        if (this.begin == -1) {
            return runtime.newArray(runtime.getNil());
        }
        final RubyString ss2 = this.makeShared(runtime, this.str, this.begin, this.end - this.begin);
        if (this.isTaint()) {
            ss2.setTaint(true);
        }
        return runtime.newArray(ss2);
    }
    
    public IRubyObject group(final long n) {
        return RubyRegexp.nth_match((int)n, this);
    }
    
    public IRubyObject group(final int n) {
        return RubyRegexp.nth_match(n, this);
    }
    
    public IRubyObject[] getNamedBackrefValues(final Ruby runtime) {
        if (this.pattern.numberOfNames() == 0) {
            return RubyMatchData.NULL_ARRAY;
        }
        final IRubyObject[] values = new IRubyObject[this.pattern.numberOfNames()];
        int j = 0;
        final Iterator<NameEntry> i = this.pattern.namedBackrefIterator();
        while (i.hasNext()) {
            final NameEntry e = i.next();
            final int[] refs = e.getBackRefs();
            final int length = refs.length;
            values[j++] = ((length == 0) ? runtime.getNil() : RubyRegexp.nth_match(refs[length - 1], this));
        }
        return values;
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect() {
        if (this.str == null) {
            return this.anyToString();
        }
        final Ruby runtime = this.getRuntime();
        final RubyString result = runtime.newString();
        result.cat((byte)35).cat((byte)60);
        result.append(this.getMetaClass().getRealClass().to_s());
        final NameEntry[] names = new NameEntry[(this.regs == null) ? 1 : this.regs.numRegs];
        if (this.pattern.numberOfNames() > 0) {
            final Iterator<NameEntry> i = this.pattern.namedBackrefIterator();
            while (i.hasNext()) {
                final NameEntry e = i.next();
                for (final int num : e.getBackRefs()) {
                    names[num] = e;
                }
            }
        }
        for (int j = 0; j < names.length; ++j) {
            result.cat((byte)32);
            if (j > 0) {
                final NameEntry e = names[j];
                if (e != null) {
                    result.cat(e.name, e.nameP, e.nameEnd - e.nameP);
                }
                else {
                    result.cat((byte)(48 + j));
                }
                result.cat((byte)58);
            }
            final IRubyObject v = RubyRegexp.nth_match(j, this);
            if (v.isNil()) {
                result.cat("nil".getBytes());
            }
            else {
                result.append(((RubyString)v).inspectCommon(runtime.is1_9()));
            }
        }
        return result.cat((byte)62);
    }
    
    @JRubyMethod(name = { "regexp" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject regexp(final ThreadContext context, final Block block) {
        this.check();
        this.checkLazyRegexp();
        return this.regexp;
    }
    
    @JRubyMethod(name = { "names" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject names(final ThreadContext context, final Block block) {
        this.check();
        this.checkLazyRegexp();
        return this.regexp.names(context);
    }
    
    @JRubyMethod(name = { "to_a" })
    public RubyArray to_a() {
        return this.match_array(this.getRuntime(), 0);
    }
    
    @JRubyMethod(name = { "values_at" }, required = 1, rest = true)
    public IRubyObject values_at(final IRubyObject[] args) {
        return this.to_a().values_at(args);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject select(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        RubyArray result;
        if (this.regs == null) {
            if (this.begin < 0) {
                return runtime.newEmptyArray();
            }
            final IRubyObject s = this.str.substr(runtime, this.begin, this.end - this.begin);
            s.setTaint(this.isTaint());
            result = (block.yield(context, s).isTrue() ? runtime.newArray(s) : runtime.newEmptyArray());
        }
        else {
            result = runtime.newArray();
            final boolean taint = this.isTaint();
            for (int i = 0; i < this.regs.numRegs; ++i) {
                final IRubyObject s2 = this.str.substr(runtime, this.regs.beg[i], this.regs.end[i] - this.regs.beg[i]);
                if (taint) {
                    s2.setTaint(true);
                }
                if (block.yield(context, s2).isTrue()) {
                    result.append(s2);
                }
            }
        }
        return result;
    }
    
    @JRubyMethod(name = { "captures" })
    public IRubyObject captures(final ThreadContext context) {
        return this.match_array(context.getRuntime(), 1);
    }
    
    private int nameToBackrefNumber(final RubyString str) {
        final ByteList value = str.getByteList();
        try {
            return this.pattern.nameToBackrefNumber(value.getUnsafeBytes(), value.getBegin(), value.getBegin() + value.getRealSize(), this.regs);
        }
        catch (JOniException je) {
            throw this.getRuntime().newIndexError(je.getMessage());
        }
    }
    
    public final int backrefNumber(final IRubyObject obj) {
        this.check();
        if (obj instanceof RubySymbol) {
            return this.nameToBackrefNumber((RubyString)((RubySymbol)obj).id2name());
        }
        if (obj instanceof RubyString) {
            return this.nameToBackrefNumber((RubyString)obj);
        }
        return RubyNumeric.num2int(obj);
    }
    
    public IRubyObject op_aref(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.op_aref(args[0]);
            }
            case 2: {
                return this.op_aref(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject op_aref(final IRubyObject idx) {
        if (!(idx instanceof RubyFixnum) || ((RubyFixnum)idx).getLongValue() < 0L) {
            return this.to_a().aref(idx);
        }
        return RubyRegexp.nth_match(RubyNumeric.fix2int(idx), this);
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject op_aref(final IRubyObject idx, final IRubyObject rest) {
        if (!rest.isNil() || !(idx instanceof RubyFixnum) || ((RubyFixnum)idx).getLongValue() < 0L) {
            return this.to_a().aref(idx, rest);
        }
        return RubyRegexp.nth_match(RubyNumeric.fix2int(idx), this);
    }
    
    @JRubyMethod(name = { "[]" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref19(final IRubyObject idx) {
        final IRubyObject result = this.op_arefCommon(idx);
        return (result == null) ? this.to_a().aref19(idx) : result;
    }
    
    @JRubyMethod(name = { "[]" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref19(final IRubyObject idx, final IRubyObject rest) {
        final IRubyObject result;
        return (!rest.isNil() || (result = this.op_arefCommon(idx)) == null) ? this.to_a().aref19(idx, rest) : result;
    }
    
    private IRubyObject op_arefCommon(final IRubyObject idx) {
        if (idx instanceof RubyFixnum) {
            final int num = RubyNumeric.fix2int(idx);
            if (num >= 0) {
                return RubyRegexp.nth_match(num, this);
            }
        }
        else {
            if (idx instanceof RubySymbol) {
                return RubyRegexp.nth_match(this.nameToBackrefNumber((RubyString)((RubySymbol)idx).id2name()), this);
            }
            if (idx instanceof RubyString) {
                return RubyRegexp.nth_match(this.nameToBackrefNumber((RubyString)idx), this);
            }
        }
        return null;
    }
    
    @JRubyMethod(name = { "size", "length" })
    public IRubyObject size(final ThreadContext context) {
        this.check();
        final Ruby runtime = context.getRuntime();
        return (this.regs == null) ? RubyFixnum.one(runtime) : RubyFixnum.newFixnum(runtime, this.regs.numRegs);
    }
    
    @JRubyMethod(name = { "begin" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject begin(final ThreadContext context, final IRubyObject index) {
        final int i = RubyNumeric.num2int(index);
        final Ruby runtime = context.getRuntime();
        final int b = this.beginCommon(runtime, i);
        return (b < 0) ? runtime.getNil() : RubyFixnum.newFixnum(runtime, b);
    }
    
    @JRubyMethod(name = { "begin" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject begin19(final ThreadContext context, final IRubyObject index) {
        final int i = this.backrefNumber(index);
        final Ruby runtime = context.getRuntime();
        int b = this.beginCommon(runtime, i);
        if (b < 0) {
            return runtime.getNil();
        }
        if (!this.str.singleByteOptimizable()) {
            this.updateCharOffset();
            b = this.charOffsets.beg[i];
        }
        return RubyFixnum.newFixnum(runtime, b);
    }
    
    private int beginCommon(final Ruby runtime, final int i) {
        this.check();
        if (i < 0 || ((this.regs == null) ? 1 : this.regs.numRegs) <= i) {
            throw runtime.newIndexError("index " + i + " out of matches");
        }
        return (this.regs == null) ? this.begin : this.regs.beg[i];
    }
    
    @JRubyMethod(name = { "end" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject end(final ThreadContext context, final IRubyObject index) {
        final int i = RubyNumeric.num2int(index);
        final Ruby runtime = context.getRuntime();
        final int e = this.endCommon(runtime, i);
        return (e < 0) ? runtime.getNil() : RubyFixnum.newFixnum(runtime, e);
    }
    
    @JRubyMethod(name = { "end" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject end19(final ThreadContext context, final IRubyObject index) {
        final int i = this.backrefNumber(index);
        final Ruby runtime = context.getRuntime();
        int e = this.endCommon(runtime, i);
        if (e < 0) {
            return runtime.getNil();
        }
        if (!this.str.singleByteOptimizable()) {
            this.updateCharOffset();
            e = this.charOffsets.end[i];
        }
        return RubyFixnum.newFixnum(runtime, e);
    }
    
    private int endCommon(final Ruby runtime, final int i) {
        this.check();
        if (i < 0 || ((this.regs == null) ? 1 : this.regs.numRegs) <= i) {
            throw runtime.newIndexError("index " + i + " out of matches");
        }
        return (this.regs == null) ? this.end : this.regs.end[i];
    }
    
    @JRubyMethod(name = { "offset" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject offset(final ThreadContext context, final IRubyObject index) {
        return this.offsetCommon(context, RubyNumeric.num2int(index), false);
    }
    
    @JRubyMethod(name = { "offset" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject offset19(final ThreadContext context, final IRubyObject index) {
        return this.offsetCommon(context, this.backrefNumber(index), true);
    }
    
    private IRubyObject offsetCommon(final ThreadContext context, final int i, final boolean is_19) {
        this.check();
        final Ruby runtime = context.getRuntime();
        if (i < 0 || ((this.regs == null) ? 1 : this.regs.numRegs) <= i) {
            throw runtime.newIndexError("index " + i + " out of matches");
        }
        int b;
        int e;
        if (this.regs == null) {
            b = this.begin;
            e = this.end;
        }
        else {
            b = this.regs.beg[i];
            e = this.regs.end[i];
        }
        if (b < 0) {
            return runtime.newArray(runtime.getNil(), runtime.getNil());
        }
        if (is_19 && !this.str.singleByteOptimizable()) {
            this.updateCharOffset();
            b = this.charOffsets.beg[i];
            e = this.charOffsets.end[i];
        }
        return runtime.newArray(RubyFixnum.newFixnum(runtime, b), RubyFixnum.newFixnum(runtime, e));
    }
    
    @JRubyMethod(name = { "pre_match" })
    public IRubyObject pre_match(final ThreadContext context) {
        this.check();
        if (this.begin == -1) {
            return context.getRuntime().getNil();
        }
        return this.makeShared(context.getRuntime(), this.str, 0, this.begin).infectBy(this);
    }
    
    @JRubyMethod(name = { "post_match" })
    public IRubyObject post_match(final ThreadContext context) {
        this.check();
        if (this.begin == -1) {
            return context.getRuntime().getNil();
        }
        return this.makeShared(context.getRuntime(), this.str, this.end, this.str.getByteList().length() - this.end).infectBy(this);
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        this.check();
        IRubyObject ss = RubyRegexp.last_match(this);
        if (ss.isNil()) {
            ss = RubyString.newEmptyString(this.getRuntime());
        }
        if (this.isTaint()) {
            ss.setTaint(true);
        }
        return ss;
    }
    
    @JRubyMethod(name = { "string" })
    public IRubyObject string() {
        this.check();
        return this.str;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject original) {
        if (this == original) {
            return this;
        }
        if (this.getMetaClass() != original.getMetaClass()) {
            throw this.getRuntime().newTypeError("wrong argument class");
        }
        final RubyMatchData origMatchData = (RubyMatchData)original;
        this.str = origMatchData.str;
        this.regs = origMatchData.regs;
        return this;
    }
    
    public boolean equals(final Object other) {
        if (!(other instanceof RubyMatchData)) {
            return false;
        }
        final RubyMatchData match = (RubyMatchData)other;
        return (this.str == match.str || (this.str != null && this.str.equals(match.str))) && (this.regexp == match.regexp || (this.regexp != null && this.regexp.equals(match.regexp))) && (this.charOffsets == match.charOffsets || (this.charOffsets != null && this.charOffsets.equals(match.charOffsets))) && this.begin == match.begin && this.end == match.end && this.charOffsetUpdated == match.charOffsetUpdated;
    }
    
    @JRubyMethod(name = { "eql?", "==" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject eql_p(final IRubyObject obj) {
        return this.getRuntime().newBoolean(this.equals(obj));
    }
    
    static {
        RubyMatchData.MATCH_DATA_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyMatchData(runtime, klass);
            }
        };
    }
    
    private static final class Pair implements Comparable
    {
        int bytePos;
        int charPos;
        
        public int compareTo(final Object o) {
            return this.bytePos - ((Pair)o).bytePos;
        }
    }
}

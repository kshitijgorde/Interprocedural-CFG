// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.exceptions.RaiseException;
import org.jcodings.Encoding;
import org.jruby.util.StringSupport;
import org.joni.Matcher;
import org.jruby.util.ByteList;
import org.joni.Regex;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.joni.Region;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "StringScanner" })
public class RubyStringScanner extends RubyObject
{
    private RubyString str;
    private int pos;
    private int lastPos;
    private Region regs;
    private int beg;
    private int end;
    private int scannerFlags;
    private static final int MATCHED_STR_SCN_F = 2048;
    private static ObjectAllocator STRINGSCANNER_ALLOCATOR;
    private static final int INSPECT_LENGTH = 5;
    
    public static RubyClass createScannerClass(final Ruby runtime) {
        final RubyClass scannerClass = runtime.defineClass("StringScanner", runtime.getObject(), RubyStringScanner.STRINGSCANNER_ALLOCATOR);
        scannerClass.defineAnnotatedMethods(RubyStringScanner.class);
        final ThreadContext context = runtime.getCurrentContext();
        scannerClass.setConstant("Version", runtime.newString("0.7.0").freeze(context));
        scannerClass.setConstant("Id", runtime.newString("$Id: strscan.c 13506 2007-09-24 08:56:24Z nobu $").freeze(context));
        final RubyClass standardError = runtime.getStandardError();
        final RubyClass error = scannerClass.defineClassUnder("Error", standardError, standardError.getAllocator());
        final RubyClass objClass = runtime.getObject();
        if (!objClass.isConstantDefined("ScanError")) {
            objClass.defineConstant("ScanError", error);
        }
        return scannerClass;
    }
    
    private void clearMatched() {
        this.scannerFlags &= 0xFFFFF7FF;
    }
    
    private void setMatched() {
        this.scannerFlags |= 0x800;
    }
    
    private boolean isMatched() {
        return (this.scannerFlags & 0x800) != 0x0;
    }
    
    private void check() {
        if (this.str == null) {
            throw this.getRuntime().newArgumentError("uninitialized StringScanner object");
        }
    }
    
    protected RubyStringScanner(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.pos = 0;
        this.lastPos = -1;
        this.beg = -1;
        this.end = -1;
    }
    
    @JRubyMethod(required = 1, optional = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block unusedBlock) {
        this.str = args[0].convertToString();
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject other) {
        if (this == other) {
            return this;
        }
        if (!(other instanceof RubyStringScanner)) {
            throw this.getRuntime().newTypeError("wrong argument type " + other.getMetaClass() + " (expected StringScanner)");
        }
        final RubyStringScanner otherScanner = (RubyStringScanner)other;
        this.str = otherScanner.str;
        this.pos = otherScanner.pos;
        this.lastPos = otherScanner.lastPos;
        this.scannerFlags = otherScanner.scannerFlags;
        this.regs = ((otherScanner.regs != null) ? otherScanner.regs.clone() : null);
        this.beg = otherScanner.beg;
        this.end = otherScanner.end;
        return this;
    }
    
    @JRubyMethod(name = { "reset" })
    public IRubyObject reset() {
        this.check();
        this.pos = 0;
        this.clearMatched();
        return this;
    }
    
    @JRubyMethod(name = { "terminate" })
    public IRubyObject terminate() {
        this.check();
        this.pos = this.str.getByteList().getRealSize();
        this.clearMatched();
        return this;
    }
    
    @JRubyMethod(name = { "clear" })
    public IRubyObject clear(final ThreadContext context) {
        this.check();
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#clear is obsolete; use #terminate instead", "StringScanner#clear", "#terminate");
        }
        return this.terminate();
    }
    
    @JRubyMethod(name = { "string" })
    public RubyString string() {
        return this.str;
    }
    
    @JRubyMethod(name = { "string=" }, required = 1)
    public IRubyObject set_string(final ThreadContext context, final IRubyObject str) {
        this.str = (RubyString)str.convertToString().strDup(context.getRuntime()).freeze(context);
        this.pos = 0;
        this.clearMatched();
        return str;
    }
    
    @JRubyMethod(name = { "concat", "<<" }, required = 1)
    public IRubyObject concat(final IRubyObject obj) {
        this.check();
        this.str.append(obj);
        return this;
    }
    
    @JRubyMethod(name = { "pos", "pointer" })
    public RubyFixnum pos() {
        this.check();
        return RubyFixnum.newFixnum(this.getRuntime(), this.pos);
    }
    
    @JRubyMethod(name = { "pos=", "pointer=" })
    public IRubyObject set_pos(final IRubyObject pos) {
        this.check();
        int i = RubyNumeric.num2int(pos);
        final int size = this.str.getByteList().getRealSize();
        if (i < 0) {
            i += size;
        }
        if (i < 0 || i > size) {
            throw this.getRuntime().newRangeError("index out of range.");
        }
        this.pos = i;
        return RubyFixnum.newFixnum(this.getRuntime(), i);
    }
    
    private IRubyObject extractRange(final Ruby runtime, final int beg, int end) {
        final int size = this.str.getByteList().getRealSize();
        if (beg > size) {
            return this.getRuntime().getNil();
        }
        if (end > size) {
            end = size;
        }
        return this.str.makeSharedString(runtime, beg, end - beg);
    }
    
    private IRubyObject extractBegLen(final Ruby runtime, final int beg, int len) {
        assert len >= 0;
        final int size = this.str.getByteList().getRealSize();
        if (beg > size) {
            return this.getRuntime().getNil();
        }
        if (beg + len > size) {
            len = size - beg;
        }
        return this.str.makeSharedString(runtime, beg, len);
    }
    
    private IRubyObject scan(final IRubyObject regex, final boolean succptr, final boolean getstr, final boolean headonly) {
        if (!(regex instanceof RubyRegexp)) {
            throw this.getRuntime().newTypeError("wrong argument type " + regex.getMetaClass() + " (expected Regexp)");
        }
        this.check();
        final Regex pattern = ((RubyRegexp)regex).getPattern();
        this.clearMatched();
        final int rest = this.str.getByteList().getRealSize() - this.pos;
        if (rest < 0) {
            return this.getRuntime().getNil();
        }
        final ByteList value = this.str.getByteList();
        final Matcher matcher = pattern.matcher(value.getUnsafeBytes(), value.getBegin() + this.pos, value.getBegin() + value.getRealSize());
        int ret;
        if (headonly) {
            ret = matcher.match(value.getBegin() + this.pos, value.getBegin() + value.getRealSize(), 0);
        }
        else {
            ret = matcher.search(value.getBegin() + this.pos, value.getBegin() + value.getRealSize(), 0);
        }
        this.regs = matcher.getRegion();
        if (this.regs == null) {
            this.beg = matcher.getBegin();
            this.end = matcher.getEnd();
        }
        else {
            this.beg = this.regs.beg[0];
            this.end = this.regs.end[0];
        }
        if (ret < 0) {
            return this.getRuntime().getNil();
        }
        this.setMatched();
        this.lastPos = this.pos;
        if (succptr) {
            this.pos += this.end;
        }
        return getstr ? this.extractBegLen(this.getRuntime(), this.lastPos, this.end) : RubyFixnum.newFixnum(this.getRuntime(), this.end);
    }
    
    @JRubyMethod(name = { "scan" }, required = 1)
    public IRubyObject scan(final IRubyObject regex) {
        return this.scan(regex, true, true, true);
    }
    
    @JRubyMethod(name = { "match?" }, required = 1)
    public IRubyObject match_p(final IRubyObject regex) {
        return this.scan(regex, false, false, true);
    }
    
    @JRubyMethod(name = { "skip" }, required = 1)
    public IRubyObject skip(final IRubyObject regex) {
        return this.scan(regex, true, false, true);
    }
    
    @JRubyMethod(name = { "check" }, required = 1)
    public IRubyObject check(final IRubyObject regex) {
        return this.scan(regex, false, true, true);
    }
    
    @JRubyMethod(name = { "scan_full" }, required = 3)
    public IRubyObject scan_full(final IRubyObject regex, final IRubyObject s, final IRubyObject f) {
        return this.scan(regex, s.isTrue(), f.isTrue(), true);
    }
    
    @JRubyMethod(name = { "scan_until" }, required = 1)
    public IRubyObject scan_until(final IRubyObject regex) {
        return this.scan(regex, true, true, false);
    }
    
    @JRubyMethod(name = { "exist?" }, required = 1)
    public IRubyObject exist_p(final IRubyObject regex) {
        return this.scan(regex, false, false, false);
    }
    
    @JRubyMethod(name = { "skip_until" }, required = 1)
    public IRubyObject skip_until(final IRubyObject regex) {
        return this.scan(regex, true, false, false);
    }
    
    @JRubyMethod(name = { "check_until" }, required = 1)
    public IRubyObject check_until(final IRubyObject regex) {
        return this.scan(regex, false, true, false);
    }
    
    @JRubyMethod(name = { "search_full" }, required = 3)
    public IRubyObject search_full(final IRubyObject regex, final IRubyObject s, final IRubyObject f) {
        return this.scan(regex, s.isTrue(), f.isTrue(), false);
    }
    
    private void adjustRegisters() {
        this.beg = 0;
        this.end = this.pos - this.lastPos;
        this.regs = null;
    }
    
    @JRubyMethod(name = { "getch" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject getch(final ThreadContext context) {
        return this.getchCommon(context, false);
    }
    
    @JRubyMethod(name = { "getch" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getch19(final ThreadContext context) {
        return this.getchCommon(context, true);
    }
    
    public IRubyObject getchCommon(final ThreadContext context, final boolean is1_9) {
        this.check();
        this.clearMatched();
        final Ruby runtime = context.getRuntime();
        final ByteList value = this.str.getByteList();
        if (this.pos >= value.getRealSize()) {
            return runtime.getNil();
        }
        int len;
        if (is1_9) {
            final Encoding enc = this.str.getEncoding();
            len = (enc.isSingleByte() ? 1 : StringSupport.length(enc, value.getUnsafeBytes(), value.getBegin() + this.pos, value.getBegin() + value.getRealSize()));
        }
        else {
            final Encoding enc = runtime.getKCode().getEncoding();
            len = (enc.isSingleByte() ? 1 : enc.length(value.getUnsafeBytes(), value.getBegin() + this.pos, value.getBegin() + value.getRealSize()));
        }
        if (this.pos + len > value.getRealSize()) {
            len = value.getRealSize() - this.pos;
        }
        this.lastPos = this.pos;
        this.pos += len;
        this.setMatched();
        this.adjustRegisters();
        return this.extractRange(runtime, this.lastPos + this.beg, this.lastPos + this.end);
    }
    
    @JRubyMethod(name = { "get_byte" })
    public IRubyObject get_byte(final ThreadContext context) {
        this.check();
        this.clearMatched();
        if (this.pos >= this.str.getByteList().getRealSize()) {
            return this.getRuntime().getNil();
        }
        this.lastPos = this.pos;
        ++this.pos;
        this.setMatched();
        this.adjustRegisters();
        return this.extractRange(context.getRuntime(), this.lastPos + this.beg, this.lastPos + this.end);
    }
    
    @JRubyMethod(name = { "getbyte" })
    public IRubyObject getbyte(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#getbyte is obsolete; use #get_byte instead", "StringScanner#getbyte", "#get_byte");
        }
        return this.get_byte(context);
    }
    
    @JRubyMethod(name = { "peek" }, required = 1)
    public IRubyObject peek(final ThreadContext context, final IRubyObject length) {
        this.check();
        int len = RubyNumeric.num2int(length);
        if (len < 0) {
            throw context.getRuntime().newArgumentError("negative string size (or size too big)");
        }
        final ByteList value = this.str.getByteList();
        if (this.pos >= value.getRealSize()) {
            return RubyString.newEmptyString(this.getRuntime()).infectBy(this.str);
        }
        if (this.pos + len > value.getRealSize()) {
            len = value.getRealSize() - this.pos;
        }
        return this.extractBegLen(context.getRuntime(), this.pos, len);
    }
    
    @JRubyMethod(name = { "peep" }, required = 1)
    public IRubyObject peep(final ThreadContext context, final IRubyObject length) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#peep is obsolete; use #peek instead", "StringScanner#peep", "#peek");
        }
        return this.peek(context, length);
    }
    
    @JRubyMethod(name = { "unscan" })
    public IRubyObject unscan() {
        this.check();
        final Ruby runtime = this.getRuntime();
        if (!this.isMatched()) {
            final RubyClass errorClass = runtime.fastGetClass("StringScanner").fastGetClass("Error");
            throw new RaiseException(RubyException.newException(runtime, errorClass, "unscan failed: previous match had failed"));
        }
        this.pos = this.lastPos;
        this.clearMatched();
        return this;
    }
    
    @JRubyMethod(name = { "beginning_of_line?" }, alias = { "bol?" })
    public IRubyObject bol_p() {
        this.check();
        final ByteList value = this.str.getByteList();
        if (this.pos > value.getRealSize()) {
            return this.getRuntime().getNil();
        }
        if (this.pos == 0) {
            return this.getRuntime().getTrue();
        }
        return (value.getUnsafeBytes()[value.getBegin() + this.pos - 1] == 10) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "eos?" })
    public RubyBoolean eos_p(final ThreadContext context) {
        this.check();
        return (this.pos >= this.str.getByteList().getRealSize()) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "empty?" })
    public RubyBoolean empty_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#empty? is obsolete; use #eos? instead", "StringScanner#empty?", "#eos?");
        }
        return this.eos_p(context);
    }
    
    @JRubyMethod(name = { "rest?" })
    public RubyBoolean rest_p(final ThreadContext context) {
        this.check();
        return (this.pos >= this.str.getByteList().getRealSize()) ? context.getRuntime().getFalse() : context.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "matched?" })
    public RubyBoolean matched_p(final ThreadContext context) {
        this.check();
        return this.isMatched() ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "matched" })
    public IRubyObject matched(final ThreadContext context) {
        this.check();
        if (!this.isMatched()) {
            return this.getRuntime().getNil();
        }
        return this.extractRange(context.getRuntime(), this.lastPos + this.beg, this.lastPos + this.end);
    }
    
    @JRubyMethod(name = { "matched_size" })
    public IRubyObject matched_size() {
        this.check();
        if (!this.isMatched()) {
            return this.getRuntime().getNil();
        }
        return RubyFixnum.newFixnum(this.getRuntime(), this.end - this.beg);
    }
    
    @JRubyMethod(name = { "matchedsize" })
    public IRubyObject matchedsize(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#matchedsize is obsolete; use #matched_size instead", "StringScanner#matchedize", "#matched_size");
        }
        return this.matched_size();
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject idx) {
        this.check();
        if (!this.isMatched()) {
            return context.getRuntime().getNil();
        }
        int i = RubyNumeric.num2int(idx);
        final int numRegs = (this.regs == null) ? 1 : this.regs.numRegs;
        if (i < 0) {
            i += numRegs;
        }
        if (i < 0 || i >= numRegs) {
            return context.getRuntime().getNil();
        }
        if (this.regs == null) {
            assert i == 0;
            if (this.beg == -1) {
                return this.getRuntime().getNil();
            }
            return this.extractRange(context.getRuntime(), this.lastPos + this.beg, this.lastPos + this.end);
        }
        else {
            if (this.regs.beg[i] == -1) {
                return this.getRuntime().getNil();
            }
            return this.extractRange(context.getRuntime(), this.lastPos + this.regs.beg[i], this.lastPos + this.regs.end[i]);
        }
    }
    
    @JRubyMethod(name = { "pre_match" })
    public IRubyObject pre_match(final ThreadContext context) {
        this.check();
        if (!this.isMatched()) {
            return context.getRuntime().getNil();
        }
        return this.extractRange(context.getRuntime(), 0, this.lastPos + this.beg);
    }
    
    @JRubyMethod(name = { "post_match" })
    public IRubyObject post_match(final ThreadContext context) {
        this.check();
        if (!this.isMatched()) {
            return context.getRuntime().getNil();
        }
        return this.extractRange(context.getRuntime(), this.lastPos + this.end, this.str.getByteList().getRealSize());
    }
    
    @JRubyMethod(name = { "rest" })
    public IRubyObject rest(final ThreadContext context) {
        this.check();
        final ByteList value = this.str.getByteList();
        if (this.pos >= value.getRealSize()) {
            return RubyString.newEmptyString(context.getRuntime()).infectBy(this.str);
        }
        return this.extractRange(context.getRuntime(), this.pos, value.getRealSize());
    }
    
    @JRubyMethod(name = { "rest_size" })
    public RubyFixnum rest_size() {
        this.check();
        final ByteList value = this.str.getByteList();
        if (this.pos >= value.getRealSize()) {
            return RubyFixnum.zero(this.getRuntime());
        }
        return RubyFixnum.newFixnum(this.getRuntime(), value.getRealSize() - this.pos);
    }
    
    @JRubyMethod(name = { "restsize" })
    public RubyFixnum restsize(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isVerbose()) {
            runtime.getWarnings().warning(IRubyWarnings.ID.DEPRECATED_METHOD, "StringScanner#restsize is obsolete; use #rest_size instead", "StringScanner#restsize", "#rest_size");
        }
        return this.rest_size();
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect() {
        if (this.str == null) {
            return this.inspect("(uninitialized)");
        }
        if (this.pos >= this.str.getByteList().getRealSize()) {
            return this.inspect("fin");
        }
        if (this.pos == 0) {
            return this.inspect(this.pos + "/" + this.str.getByteList().getRealSize() + " @ " + this.inspect2());
        }
        return this.inspect(this.pos + "/" + this.str.getByteList().getRealSize() + " " + this.inspect1() + " @ " + this.inspect2());
    }
    
    private IRubyObject inspect(final String msg) {
        final RubyString result = this.getRuntime().newString("#<" + this.getMetaClass() + " " + msg + ">");
        if (this.str != null) {
            result.infectBy(this.str);
        }
        return result;
    }
    
    private IRubyObject inspect1() {
        if (this.pos == 0) {
            return RubyString.newEmptyString(this.getRuntime());
        }
        if (this.pos > 5) {
            return RubyString.newStringNoCopy(this.getRuntime(), "...".getBytes()).append(this.str.substr(this.getRuntime(), this.pos - 5, 5)).inspect();
        }
        return this.str.substr(this.getRuntime(), 0, this.pos).inspect();
    }
    
    private IRubyObject inspect2() {
        if (this.pos >= this.str.getByteList().getRealSize()) {
            return RubyString.newEmptyString(this.getRuntime());
        }
        final int len = this.str.getByteList().getRealSize() - this.pos;
        if (len > 5) {
            return ((RubyString)this.str.substr(this.getRuntime(), this.pos, 5)).cat("...".getBytes()).inspect();
        }
        return this.str.substr(this.getRuntime(), this.pos, len).inspect();
    }
    
    @JRubyMethod(name = { "must_C_version" }, meta = true)
    public static IRubyObject mustCversion(final IRubyObject recv) {
        return recv;
    }
    
    static {
        RubyStringScanner.STRINGSCANNER_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyStringScanner(runtime, klass);
            }
        };
    }
}

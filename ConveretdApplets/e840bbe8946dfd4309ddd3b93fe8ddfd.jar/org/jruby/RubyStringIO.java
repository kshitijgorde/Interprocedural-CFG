// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.jruby.util.io.Stream;
import org.jruby.anno.FrameField;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.Visibility;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ModeFlags;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.java.addons.IOJavaAddons;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "StringIO" })
public class RubyStringIO extends RubyObject
{
    StringIOData data;
    private static ObjectAllocator STRINGIO_ALLOCATOR;
    public static final ByteList NEWLINE;
    
    public static RubyClass createStringIOClass(final Ruby runtime) {
        final RubyClass stringIOClass = runtime.defineClass("StringIO", runtime.fastGetClass("Data"), RubyStringIO.STRINGIO_ALLOCATOR);
        stringIOClass.defineAnnotatedMethods(RubyStringIO.class);
        stringIOClass.includeModule(runtime.getEnumerable());
        if (runtime.getObject().isConstantDefined("Java")) {
            stringIOClass.defineAnnotatedMethods(IOJavaAddons.AnyIO.class);
        }
        return stringIOClass;
    }
    
    @JRubyMethod(optional = 2, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        IRubyObject val;
        final RubyStringIO strio = (RubyStringIO)(val = ((RubyClass)recv).newInstance(context, args, Block.NULL_BLOCK));
        if (block.isGiven()) {
            try {
                val = block.yield(context, strio);
            }
            finally {
                strio.doFinalize();
            }
        }
        return val;
    }
    
    protected RubyStringIO(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.data = new StringIOData();
    }
    
    private void initializeModes(final Object modeArgument) {
        try {
            if (modeArgument == null) {
                this.data.modes = new ModeFlags(RubyIO.getIOModesIntFromString(this.getRuntime(), "r+"));
            }
            else if (modeArgument instanceof Long) {
                this.data.modes = new ModeFlags((long)modeArgument);
            }
            else {
                this.data.modes = new ModeFlags(RubyIO.getIOModesIntFromString(this.getRuntime(), (String)modeArgument));
            }
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        this.setupModes();
    }
    
    @JRubyMethod(optional = 2, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block unusedBlock) {
        Object modeArgument = null;
        switch (args.length) {
            case 0: {
                this.data.internal = RubyString.newEmptyString(this.getRuntime());
                modeArgument = "r+";
                break;
            }
            case 1: {
                this.data.internal = args[0].convertToString();
                modeArgument = (this.data.internal.isFrozen() ? "r" : "r+");
                break;
            }
            case 2: {
                this.data.internal = args[0].convertToString();
                if (args[1] instanceof RubyFixnum) {
                    modeArgument = RubyNumeric.fix2long(args[1]);
                    break;
                }
                modeArgument = args[1].convertToString().toString();
                break;
            }
        }
        this.initializeModes(modeArgument);
        if (this.data.modes.isWritable() && this.data.internal.isFrozen()) {
            throw this.getRuntime().newErrnoEACCESError("Permission denied");
        }
        if (this.data.modes.isTruncate()) {
            this.data.internal.modifyCheck();
            this.data.internal.empty();
        }
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject other) {
        final RubyStringIO otherIO = (RubyStringIO)TypeConverter.convertToType(other, this.getRuntime().fastGetClass("StringIO"), "to_strio");
        if (this == otherIO) {
            return this;
        }
        this.data = otherIO.data;
        if (otherIO.isTaint()) {
            this.setTaint(true);
        }
        return this;
    }
    
    @JRubyMethod(name = { "<<" }, required = 1)
    public IRubyObject append(final ThreadContext context, final IRubyObject arg) {
        this.writeInternal(context, arg);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject binmode() {
        return this;
    }
    
    @JRubyMethod
    public IRubyObject close() {
        this.checkInitialized();
        this.checkOpen();
        this.data.closedRead = true;
        this.data.closedWrite = true;
        return this.getRuntime().getNil();
    }
    
    private void doFinalize() {
        this.data.closedRead = true;
        this.data.closedWrite = true;
        this.data.internal = null;
    }
    
    @JRubyMethod(name = { "closed?" })
    public IRubyObject closed_p() {
        this.checkInitialized();
        return this.getRuntime().newBoolean(this.data.closedRead && this.data.closedWrite);
    }
    
    @JRubyMethod
    public IRubyObject close_read() {
        this.checkReadable();
        this.data.closedRead = true;
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "closed_read?" })
    public IRubyObject closed_read_p() {
        this.checkInitialized();
        return this.getRuntime().newBoolean(this.data.closedRead);
    }
    
    @JRubyMethod
    public IRubyObject close_write() {
        this.checkWritable();
        this.data.closedWrite = true;
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "closed_write?" })
    public IRubyObject closed_write_p() {
        this.checkInitialized();
        return this.getRuntime().newBoolean(this.data.closedWrite);
    }
    
    public IRubyObject eachInternal(final ThreadContext context, final IRubyObject[] args, final Block block) {
        for (IRubyObject line = this.getsOnly(context, args); !line.isNil(); line = this.getsOnly(context, args)) {
            block.yield(context, line);
        }
        return this;
    }
    
    @JRubyMethod(name = { "each" }, optional = 1, writes = { FrameField.LASTLINE })
    public IRubyObject each(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? this.eachInternal(context, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each", args);
    }
    
    @JRubyMethod(optional = 1)
    public IRubyObject each_line(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? this.eachInternal(context, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line", args);
    }
    
    @JRubyMethod(optional = 1)
    public IRubyObject lines(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? this.each(context, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "lines", args);
    }
    
    public IRubyObject each_byte(final ThreadContext context, final Block block) {
        this.checkReadable();
        final Ruby runtime = context.getRuntime();
        final ByteList bytes = this.data.internal.getByteList();
        while (this.data.pos < bytes.length()) {
            block.yield(context, runtime.newFixnum(bytes.get((int)(this.data.pos++)) & 0xFF));
        }
        return this;
    }
    
    @JRubyMethod(name = { "each_byte" })
    public IRubyObject each_byte19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_byte(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_byte");
    }
    
    @JRubyMethod
    public IRubyObject bytes(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_byte(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "bytes");
    }
    
    public IRubyObject each_charInternal(final ThreadContext context, final Block block) {
        this.checkReadable();
        final Ruby runtime = context.getRuntime();
        final ByteList bytes = this.data.internal.getByteList();
        final int len = bytes.getRealSize();
        while (this.data.pos < len) {
            final int pos = (int)this.data.pos;
            final byte c = bytes.getUnsafeBytes()[bytes.getBegin() + pos];
            int n = runtime.getKCode().getEncoding().length(c);
            if (len < pos + n) {
                n = len - pos;
            }
            final StringIOData data = this.data;
            data.pos += n;
            block.yield(context, this.data.internal.substr19(runtime, pos, n));
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_char(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_char");
    }
    
    @JRubyMethod
    public IRubyObject chars(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "chars");
    }
    
    @JRubyMethod(name = { "eof", "eof?" })
    public IRubyObject eof() {
        return this.getRuntime().newBoolean(this.isEOF());
    }
    
    private boolean isEOF() {
        return this.data.pos >= this.data.internal.getByteList().length() || this.data.eof;
    }
    
    @JRubyMethod(name = { "fcntl" })
    public IRubyObject fcntl() {
        throw this.getRuntime().newNotImplementedError("fcntl not implemented");
    }
    
    @JRubyMethod(name = { "fileno" })
    public IRubyObject fileno() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "flush" })
    public IRubyObject flush() {
        return this;
    }
    
    @JRubyMethod(name = { "fsync" })
    public IRubyObject fsync() {
        return RubyFixnum.zero(this.getRuntime());
    }
    
    @JRubyMethod(name = { "getc", "getbyte" })
    public IRubyObject getc() {
        this.checkReadable();
        if (this.data.pos >= this.data.internal.getByteList().length()) {
            return this.getRuntime().getNil();
        }
        return this.getRuntime().newFixnum(this.data.internal.getByteList().get((int)(this.data.pos++)) & 0xFF);
    }
    
    @JRubyMethod(name = { "getc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getc19(final ThreadContext context) {
        this.checkReadable();
        if (this.data.pos >= this.data.internal.getByteList().length()) {
            return context.getRuntime().getNil();
        }
        return context.getRuntime().newString("" + (char)(this.data.internal.getByteList().get((int)(this.data.pos++)) & 0xFF));
    }
    
    private IRubyObject internalGets(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.data.pos < this.data.internal.getByteList().getRealSize() && !this.data.eof) {
            boolean isParagraph = false;
            ByteList sep;
            if (args.length > 0) {
                if (args[0].isNil()) {
                    final ByteList buf = this.data.internal.getByteList().makeShared((int)this.data.pos, this.data.internal.getByteList().getRealSize() - (int)this.data.pos);
                    final StringIOData data = this.data;
                    data.pos += buf.getRealSize();
                    return RubyString.newString(runtime, buf);
                }
                sep = args[0].convertToString().getByteList();
                if (sep.getRealSize() == 0) {
                    isParagraph = true;
                    sep = Stream.PARAGRAPH_SEPARATOR;
                }
            }
            else {
                sep = ((RubyString)runtime.getGlobalVariables().get("$/")).getByteList();
            }
            final ByteList ss = this.data.internal.getByteList();
            if (isParagraph) {
                this.swallowLF(ss);
                if (this.data.pos == ss.getRealSize()) {
                    return runtime.getNil();
                }
            }
            int ix = ss.indexOf(sep, (int)this.data.pos);
            ByteList add;
            if (-1 == ix) {
                ix = this.data.internal.getByteList().getRealSize();
                add = ByteList.EMPTY_BYTELIST;
            }
            else {
                add = sep;
            }
            final ByteList line = new ByteList(ix - (int)this.data.pos + add.length());
            line.append(this.data.internal.getByteList(), (int)this.data.pos, ix - (int)this.data.pos);
            line.append(add);
            this.data.pos = ix + add.getRealSize();
            final StringIOData data2 = this.data;
            ++data2.lineno;
            return RubyString.newString(runtime, line);
        }
        return runtime.getNil();
    }
    
    private void swallowLF(final ByteList list) {
        while (this.data.pos < list.getRealSize() && list.get((int)this.data.pos) == 10) {
            final StringIOData data = this.data;
            ++data.pos;
        }
    }
    
    @JRubyMethod(name = { "gets" }, optional = 1, writes = { FrameField.LASTLINE })
    public IRubyObject gets(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject result = this.getsOnly(context, args);
        context.getCurrentScope().setLastLine(result);
        return result;
    }
    
    public IRubyObject getsOnly(final ThreadContext context, final IRubyObject[] args) {
        this.checkReadable();
        return this.internalGets(context, args);
    }
    
    @JRubyMethod(name = { "tty?", "isatty" })
    public IRubyObject isatty() {
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "length", "size" })
    public IRubyObject length() {
        this.checkFinalized();
        return this.getRuntime().newFixnum(this.data.internal.getByteList().length());
    }
    
    @JRubyMethod(name = { "lineno" })
    public IRubyObject lineno() {
        return this.getRuntime().newFixnum(this.data.lineno);
    }
    
    @JRubyMethod(name = { "lineno=" }, required = 1)
    public IRubyObject set_lineno(final IRubyObject arg) {
        this.data.lineno = RubyNumeric.fix2int(arg);
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "path" })
    public IRubyObject path() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "path" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject path19(final ThreadContext context) {
        throw context.getRuntime().newNoMethodError("", "path", null);
    }
    
    @JRubyMethod(name = { "pid" })
    public IRubyObject pid() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "pos", "tell" })
    public IRubyObject pos() {
        return this.getRuntime().newFixnum(this.data.pos);
    }
    
    @JRubyMethod(name = { "pos=" }, required = 1)
    public IRubyObject set_pos(final IRubyObject arg) {
        this.data.pos = RubyNumeric.fix2int(arg);
        if (this.data.pos < 0L) {
            throw this.getRuntime().newErrnoEINVALError("Invalid argument");
        }
        if (this.data.pos < this.data.internal.getByteList().length()) {
            this.data.eof = false;
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "print" }, rest = true)
    public IRubyObject print(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length != 0) {
            for (int i = 0, j = args.length; i < j; ++i) {
                this.append(context, args[i]);
            }
        }
        else {
            final IRubyObject arg = runtime.getGlobalVariables().get("$_");
            this.append(context, arg.isNil() ? runtime.newString("nil") : arg);
        }
        final IRubyObject sep = runtime.getGlobalVariables().get("$\\");
        if (!sep.isNil()) {
            this.append(context, sep);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "print" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject print19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length != 0) {
            for (int i = 0, j = args.length; i < j; ++i) {
                this.append(context, args[i]);
            }
        }
        else {
            final IRubyObject arg = runtime.getGlobalVariables().get("$_");
            this.append(context, arg.isNil() ? RubyString.newEmptyString(this.getRuntime()) : arg);
        }
        final IRubyObject sep = runtime.getGlobalVariables().get("$\\");
        if (!sep.isNil()) {
            this.append(context, sep);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "printf" }, required = 1, rest = true)
    public IRubyObject printf(final ThreadContext context, final IRubyObject[] args) {
        this.append(context, RubyKernel.sprintf(context, this, args));
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "putc" }, required = 1)
    public IRubyObject putc(final IRubyObject obj) {
        this.checkWritable();
        final byte c = RubyNumeric.num2chr(obj);
        this.checkFrozen();
        this.data.internal.modify();
        final ByteList bytes = this.data.internal.getByteList();
        if (this.data.modes.isAppendable()) {
            this.data.pos = bytes.length();
            bytes.append(c);
        }
        else {
            if (this.data.pos >= bytes.length()) {
                bytes.length((int)this.data.pos + 1);
            }
            bytes.set((int)this.data.pos, c);
            final StringIOData data = this.data;
            ++data.pos;
        }
        return obj;
    }
    
    @JRubyMethod(name = { "puts" }, rest = true)
    public IRubyObject puts(final ThreadContext context, final IRubyObject[] args) {
        this.checkWritable();
        if (args.length == 0) {
            this.callMethod(context, "write", RubyString.newStringShared(this.getRuntime(), RubyStringIO.NEWLINE));
            return this.getRuntime().getNil();
        }
        for (int i = 0; i < args.length; ++i) {
            RubyString line;
            if (args[i].isNil()) {
                line = this.getRuntime().newString("nil");
            }
            else {
                final IRubyObject tmp = args[i].checkArrayType();
                if (!tmp.isNil()) {
                    final RubyArray arr = (RubyArray)tmp;
                    if (!this.getRuntime().isInspecting(arr)) {
                        this.inspectPuts(context, arr);
                        continue;
                    }
                    line = this.getRuntime().newString("[...]");
                }
                else if (args[i] instanceof RubyString) {
                    line = (RubyString)args[i];
                }
                else {
                    line = args[i].asString();
                }
            }
            this.callMethod(context, "write", line);
            if (!line.getByteList().endsWith(RubyStringIO.NEWLINE)) {
                this.callMethod(context, "write", RubyString.newStringShared(this.getRuntime(), RubyStringIO.NEWLINE));
            }
        }
        return this.getRuntime().getNil();
    }
    
    private IRubyObject inspectPuts(final ThreadContext context, final RubyArray array) {
        try {
            this.getRuntime().registerInspecting(array);
            return this.puts(context, array.toJavaArray());
        }
        finally {
            this.getRuntime().unregisterInspecting(array);
        }
    }
    
    @JRubyMethod(name = { "read" }, optional = 2)
    public IRubyObject read(final IRubyObject[] args) {
        this.checkReadable();
        ByteList buf = null;
        int length = 0;
        int oldLength = 0;
        RubyString originalString = null;
        Label_0205: {
            switch (args.length) {
                case 2: {
                    originalString = args[1].convertToString();
                    originalString.modify();
                    buf = originalString.getByteList();
                }
                case 1: {
                    if (args[0].isNil()) {
                        break Label_0205;
                    }
                    length = RubyNumeric.fix2int(args[0]);
                    if ((oldLength = length) < 0) {
                        throw this.getRuntime().newArgumentError("negative length " + length + " given");
                    }
                    if (length > 0 && this.data.pos >= this.data.internal.getByteList().length()) {
                        this.data.eof = true;
                        if (buf != null) {
                            buf.setRealSize(0);
                        }
                        return this.getRuntime().getNil();
                    }
                    if (this.data.eof) {
                        if (buf != null) {
                            buf.setRealSize(0);
                        }
                        return this.getRuntime().getNil();
                    }
                    break;
                }
                case 0: {
                    oldLength = -1;
                    length = this.data.internal.getByteList().length();
                    if (length <= this.data.pos) {
                        this.data.eof = true;
                        if (buf == null) {
                            buf = new ByteList();
                        }
                        else {
                            buf.setRealSize(0);
                        }
                        return this.getRuntime().newString(buf);
                    }
                    length -= (int)this.data.pos;
                    break;
                }
                default: {
                    this.getRuntime().newArgumentError(args.length, 0);
                    break;
                }
            }
        }
        if (buf == null) {
            final int internalLength = this.data.internal.getByteList().length();
            if (internalLength > 0) {
                if (internalLength >= this.data.pos + length) {
                    buf = new ByteList(this.data.internal.getByteList(), (int)this.data.pos, length);
                }
                else {
                    final int rest = (int)(this.data.internal.getByteList().length() - this.data.pos);
                    if (length > rest) {
                        length = rest;
                    }
                    buf = new ByteList(this.data.internal.getByteList(), (int)this.data.pos, length);
                }
            }
        }
        else {
            final int rest2 = (int)(this.data.internal.getByteList().length() - this.data.pos);
            if (length > rest2) {
                length = rest2;
            }
            byte[] target = buf.getUnsafeBytes();
            if (target.length > length) {
                System.arraycopy(this.data.internal.getByteList().getUnsafeBytes(), (int)this.data.pos, target, 0, length);
                buf.setBegin(0);
                buf.setRealSize(length);
            }
            else {
                target = new byte[length];
                System.arraycopy(this.data.internal.getByteList().getUnsafeBytes(), (int)this.data.pos, target, 0, length);
                buf.setBegin(0);
                buf.setRealSize(length);
                buf.setUnsafeBytes(target);
            }
        }
        if (buf == null) {
            if (!this.data.eof) {
                buf = new ByteList();
            }
            length = 0;
        }
        else {
            length = buf.length();
            final StringIOData data = this.data;
            data.pos += length;
        }
        if (oldLength < 0 || oldLength > length) {
            this.data.eof = true;
        }
        return (originalString != null) ? originalString : this.getRuntime().newString(buf);
    }
    
    @JRubyMethod(name = { "read_nonblock" }, compat = CompatVersion.RUBY1_9, required = 1, optional = 1)
    public IRubyObject read_nonblock(final ThreadContext contet, final IRubyObject[] args) {
        return this.sysreadCommon(args);
    }
    
    @JRubyMethod(name = { "readpartial" }, compat = CompatVersion.RUBY1_9, required = 1, optional = 1)
    public IRubyObject readpartial(final ThreadContext context, final IRubyObject[] args) {
        return this.sysreadCommon(args);
    }
    
    @JRubyMethod(name = { "readchar", "readbyte" })
    public IRubyObject readchar() {
        final IRubyObject c = this.getc();
        if (c.isNil()) {
            throw this.getRuntime().newEOFError();
        }
        return c;
    }
    
    @JRubyMethod(name = { "readchar" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject readchar19(final ThreadContext context) {
        final IRubyObject c = this.getc19(context);
        if (c.isNil()) {
            throw this.getRuntime().newEOFError();
        }
        return c;
    }
    
    @JRubyMethod(name = { "readline" }, optional = 1, writes = { FrameField.LASTLINE })
    public IRubyObject readline(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject line = this.gets(context, args);
        if (line.isNil()) {
            throw this.getRuntime().newEOFError();
        }
        return line;
    }
    
    @JRubyMethod(name = { "readlines" }, optional = 1)
    public IRubyObject readlines(final ThreadContext context, final IRubyObject[] arg) {
        this.checkReadable();
        final List<IRubyObject> lns = new ArrayList<IRubyObject>();
        while (!this.isEOF()) {
            final IRubyObject line = this.internalGets(context, arg);
            if (line.isNil()) {
                break;
            }
            lns.add(line);
        }
        return this.getRuntime().newArray(lns);
    }
    
    @JRubyMethod(name = { "reopen" }, required = 0, optional = 2)
    public IRubyObject reopen(final IRubyObject[] args) {
        if (args.length == 1 && !(args[0] instanceof RubyString)) {
            return this.initialize_copy(args[0]);
        }
        this.doRewind();
        this.data.closedRead = false;
        this.data.closedWrite = false;
        return this.initialize(args, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "rewind" })
    public IRubyObject rewind() {
        this.doRewind();
        return RubyFixnum.zero(this.getRuntime());
    }
    
    private void doRewind() {
        this.data.pos = 0L;
        this.data.eof = false;
        this.data.lineno = 0;
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public IRubyObject seek(final IRubyObject[] args) {
        this.checkOpen();
        this.checkFinalized();
        final long amount = RubyNumeric.num2long(args[0]);
        int whence = 0;
        long newPosition = this.data.pos;
        if (args.length > 1 && !args[0].isNil()) {
            whence = RubyNumeric.fix2int(args[1]);
        }
        if (whence == 1) {
            newPosition += amount;
        }
        else if (whence == 2) {
            newPosition = this.data.internal.getByteList().length() + amount;
        }
        else {
            if (whence != 0) {
                throw this.getRuntime().newErrnoEINVALError("invalid whence");
            }
            newPosition = amount;
        }
        if (newPosition < 0L) {
            throw this.getRuntime().newErrnoEINVALError("invalid seek value");
        }
        this.data.pos = newPosition;
        this.data.eof = false;
        return RubyFixnum.zero(this.getRuntime());
    }
    
    @JRubyMethod(name = { "string=" }, required = 1)
    public IRubyObject set_string(final IRubyObject arg) {
        return this.reopen(new IRubyObject[] { arg.convertToString() });
    }
    
    @JRubyMethod(name = { "sync=" }, required = 1)
    public IRubyObject set_sync(final IRubyObject args) {
        return args;
    }
    
    @JRubyMethod(name = { "string" })
    public IRubyObject string() {
        if (this.data.internal == null) {
            return this.getRuntime().getNil();
        }
        return this.data.internal;
    }
    
    @JRubyMethod(name = { "sync" })
    public IRubyObject sync() {
        return this.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "sysread" }, optional = 2)
    public IRubyObject sysread(final IRubyObject[] args) {
        return this.sysreadCommon(args);
    }
    
    private IRubyObject sysreadCommon(final IRubyObject[] args) {
        final IRubyObject obj = this.read(args);
        if (this.isEOF() && obj.isNil()) {
            throw this.getRuntime().newEOFError();
        }
        return obj;
    }
    
    @JRubyMethod(name = { "truncate" }, required = 1)
    public IRubyObject truncate(final IRubyObject arg) {
        this.checkWritable();
        final int len = RubyNumeric.fix2int(arg);
        if (len < 0) {
            throw this.getRuntime().newErrnoEINVALError("negative legnth");
        }
        this.data.internal.modify();
        final ByteList buf = this.data.internal.getByteList();
        if (len < buf.length()) {
            Arrays.fill(buf.getUnsafeBytes(), len, buf.length(), (byte)0);
        }
        buf.length(len);
        return arg;
    }
    
    @JRubyMethod(name = { "ungetc" }, required = 1)
    public IRubyObject ungetc(final IRubyObject arg) {
        this.checkReadable();
        final int c = RubyNumeric.num2int(arg);
        if (this.data.pos == 0L) {
            return this.getRuntime().getNil();
        }
        this.ungetcCommon(c);
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "ungetc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject ungetc19(final ThreadContext context, final IRubyObject arg) {
        this.checkReadable();
        if (!arg.isNil()) {
            int c;
            if (arg instanceof RubyFixnum) {
                c = RubyNumeric.fix2int(arg);
            }
            else {
                final RubyString str = arg.convertToString();
                c = str.getEncoding().mbcToCode(str.getBytes(), 0, 1);
            }
            this.ungetcCommon(c);
        }
        return this.getRuntime().getNil();
    }
    
    private void ungetcCommon(final int c) {
        this.data.internal.modify();
        final StringIOData data = this.data;
        --data.pos;
        final ByteList bytes = this.data.internal.getByteList();
        if (bytes.length() <= this.data.pos) {
            bytes.length((int)this.data.pos + 1);
        }
        bytes.set((int)this.data.pos, c);
    }
    
    @JRubyMethod(name = { "write", "syswrite" }, required = 1)
    public IRubyObject write(final ThreadContext context, final IRubyObject arg) {
        return context.getRuntime().newFixnum(this.writeInternal(context, arg));
    }
    
    private int writeInternal(final ThreadContext context, final IRubyObject arg) {
        this.checkWritable();
        this.checkFrozen();
        final RubyString val = arg.asString();
        this.data.internal.modify();
        if (this.data.modes.isAppendable()) {
            this.data.internal.getByteList().append(val.getByteList());
            this.data.pos = this.data.internal.getByteList().length();
        }
        else {
            final int left = this.data.internal.getByteList().length() - (int)this.data.pos;
            this.data.internal.getByteList().replace((int)this.data.pos, Math.min(val.getByteList().length(), left), val.getByteList());
            final StringIOData data = this.data;
            data.pos += val.getByteList().length();
        }
        if (val.isTaint()) {
            this.data.internal.setTaint(true);
        }
        return val.getByteList().length();
    }
    
    protected void checkFrozen() {
        this.checkInitialized();
        if (this.data.internal.isFrozen()) {
            throw this.getRuntime().newIOError("not modifiable string");
        }
    }
    
    private void checkReadable() {
        this.checkInitialized();
        if (this.data.closedRead || !this.data.modes.isReadable()) {
            throw this.getRuntime().newIOError("not opened for reading");
        }
    }
    
    private void checkWritable() {
        this.checkInitialized();
        if (this.data.closedWrite || !this.data.modes.isWritable()) {
            throw this.getRuntime().newIOError("not opened for writing");
        }
    }
    
    private void checkInitialized() {
        if (this.data.modes == null) {
            throw this.getRuntime().newIOError("uninitialized stream");
        }
    }
    
    private void checkFinalized() {
        if (this.data.internal == null) {
            throw this.getRuntime().newIOError("not opened");
        }
    }
    
    private void checkOpen() {
        if (this.data.closedRead && this.data.closedWrite) {
            throw this.getRuntime().newIOError("closed stream");
        }
    }
    
    private void setupModes() {
        this.data.closedWrite = false;
        this.data.closedRead = false;
        if (this.data.modes.isReadOnly()) {
            this.data.closedWrite = true;
        }
        if (!this.data.modes.isReadable()) {
            this.data.closedRead = true;
        }
    }
    
    static {
        RubyStringIO.STRINGIO_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyStringIO(runtime, klass);
            }
        };
        NEWLINE = ByteList.create("\n");
    }
    
    static class StringIOData
    {
        long pos;
        int lineno;
        boolean eof;
        boolean closedRead;
        boolean closedWrite;
        ModeFlags modes;
        RubyString internal;
        
        StringIOData() {
            this.pos = 0L;
            this.lineno = 0;
            this.eof = false;
            this.closedRead = false;
            this.closedWrite = false;
        }
    }
}

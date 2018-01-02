// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.ext.posix.FileStat;
import org.jruby.exceptions.RaiseException;
import java.io.IOException;
import java.io.File;
import org.jruby.util.ByteList;
import org.jruby.ext.posix.util.Platform;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.IAccessor;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyArgsFile
{
    public static void setCurrentLineNumber(final IRubyObject recv, final int newLineNumber) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (data != null) {
            data.currentLineNumber = newLineNumber;
        }
    }
    
    public static void initArgsFile(final Ruby runtime) {
        final RubyObject argsFile = new RubyObject(runtime, runtime.getObject());
        runtime.getEnumerable().extend_object(argsFile);
        runtime.setArgsFile(argsFile);
        runtime.getGlobalVariables().defineReadonly("$<", new IAccessor() {
            public IRubyObject getValue() {
                return runtime.getArgsFile();
            }
            
            public IRubyObject setValue(final IRubyObject newValue) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        runtime.defineGlobalConstant("ARGF", argsFile);
        final RubyClass argfClass = argsFile.getMetaClass();
        argfClass.defineAnnotatedMethods(RubyArgsFile.class);
        runtime.defineReadonlyVariable("$FILENAME", runtime.newString("-"));
    }
    
    @JRubyMethod(name = { "fileno", "to_i" })
    public static IRubyObject fileno(final ThreadContext context, final IRubyObject recv) {
        return ((RubyIO)getData(context, recv, "no stream").currentFile).fileno(context);
    }
    
    @JRubyMethod(name = { "to_io" })
    public static IRubyObject to_io(final ThreadContext context, final IRubyObject recv) {
        return getData(context, recv, "no stream").currentFile;
    }
    
    private static IRubyObject argf_getline(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        boolean retry = true;
        IRubyObject line = null;
        while (retry) {
            retry = false;
            if (!data.next_argv(context)) {
                return context.getRuntime().getNil();
            }
            line = data.currentFile.callMethod(context, "gets", args);
            if (!line.isNil() || data.next_p == -1) {
                continue;
            }
            argf_close(context, data.currentFile);
            data.next_p = 1;
            retry = true;
        }
        if (!line.isNil()) {
            context.getRuntime().setCurrentLine(data.currentLineNumber);
        }
        return line;
    }
    
    @JRubyMethod(name = { "gets" }, optional = 1)
    public static IRubyObject gets(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.next_argv(context)) {
            return context.getRuntime().getNil();
        }
        IRubyObject line;
        if (!(data.currentFile instanceof RubyIO)) {
            line = data.currentFile.callMethod(context, "gets", args);
        }
        else {
            line = argf_getline(context, recv, args);
        }
        context.getCurrentScope().setLastLine(line);
        context.getRuntime().getGlobalVariables().set("$_", line);
        return line;
    }
    
    @JRubyMethod(name = { "readline" }, optional = 1)
    public static IRubyObject readline(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject line = gets(context, recv, args);
        if (line.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return line;
    }
    
    @JRubyMethod(optional = 1)
    public static IRubyObject readlines(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        final Ruby runtime = context.getRuntime();
        if (!data.next_argv(context)) {
            return runtime.is1_9() ? runtime.newEmptyArray() : runtime.getNil();
        }
        if (!(data.currentFile instanceof RubyIO)) {
            return data.currentFile.callMethod(context, "readlines", args);
        }
        final RubyArray ary = runtime.newArray();
        IRubyObject line;
        while (!(line = argf_getline(context, recv, args)).isNil()) {
            ary.append(line);
        }
        return ary;
    }
    
    @JRubyMethod(optional = 1)
    public static IRubyObject to_a(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        final Ruby runtime = context.getRuntime();
        if (!data.next_argv(context)) {
            return runtime.is1_9() ? runtime.newEmptyArray() : runtime.getNil();
        }
        if (!(data.currentFile instanceof RubyIO)) {
            return data.currentFile.callMethod(context, "to_a", args);
        }
        final RubyArray ary = runtime.newArray();
        IRubyObject line;
        while (!(line = argf_getline(context, recv, args)).isNil()) {
            ary.append(line);
        }
        return ary;
    }
    
    public static IRubyObject each_byte(final ThreadContext context, final IRubyObject recv, final Block block) {
        IRubyObject bt;
        while (!(bt = getc(context, recv)).isNil()) {
            block.yield(context, bt);
        }
        return recv;
    }
    
    @JRubyMethod(optional = 1)
    public static IRubyObject each_byte(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_byte(context, recv, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "each_byte");
    }
    
    @JRubyMethod(optional = 1)
    public static IRubyObject bytes(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_byte(context, recv, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "bytes");
    }
    
    @JRubyMethod
    public static IRubyObject each_char(final ThreadContext context, final IRubyObject recv, final Block block) {
        return block.isGiven() ? each_charCommon(context, recv, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "each_char");
    }
    
    @JRubyMethod
    public static IRubyObject chars(final ThreadContext context, final IRubyObject recv, final Block block) {
        return block.isGiven() ? each_charCommon(context, recv, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "chars");
    }
    
    public static IRubyObject each_charCommon(final ThreadContext context, final IRubyObject recv, final Block block) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        final Ruby runtime = context.getRuntime();
        IRubyObject ch;
        while (!(ch = getc(context, recv)).isNil()) {
            boolean cont = true;
            while (cont) {
                cont = false;
                byte c = (byte)RubyNumeric.fix2int(ch);
                int n = runtime.getKCode().getEncoding().length(c);
                final IRubyObject file = data.currentFile;
                final RubyString str = runtime.newString();
                str.setTaint(true);
                str.cat(c);
                while (--n > 0) {
                    if ((ch = getc(context, recv)).isNil()) {
                        block.yield(context, str);
                        return recv;
                    }
                    if (data.currentFile != file) {
                        block.yield(context, str);
                        cont = true;
                    }
                    else {
                        c = (byte)RubyNumeric.fix2int(ch);
                        str.cat(c);
                    }
                }
                block.yield(context, str);
            }
        }
        return recv;
    }
    
    @JRubyMethod(name = { "each_line", "each" }, optional = 1)
    public static IRubyObject each_line(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.next_argv(context)) {
            return context.getRuntime().getNil();
        }
        if (!(data.currentFile instanceof RubyIO)) {
            if (!data.next_argv(context)) {
                return recv;
            }
            data.currentFile.callMethod(context, "each", new IRubyObject[0], block);
            data.next_p = 1;
        }
        IRubyObject str;
        while (!(str = argf_getline(context, recv, args)).isNil()) {
            block.yield(context, str);
        }
        return recv;
    }
    
    @JRubyMethod(name = { "each_line" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject each_line19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_line(context, recv, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "each_line", args);
    }
    
    @JRubyMethod(name = { "each" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject each19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_line(context, recv, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "each", args);
    }
    
    @JRubyMethod(name = { "file" })
    public static IRubyObject file(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        data.next_argv(context);
        return data.currentFile;
    }
    
    @JRubyMethod(name = { "skip" })
    public static IRubyObject skip(final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (data.next_p != -1) {
            argf_close(recv.getRuntime().getCurrentContext(), data.currentFile);
            data.next_p = 1;
        }
        return recv;
    }
    
    public static void argf_close(final ThreadContext context, final IRubyObject file) {
        if (file instanceof RubyIO) {
            ((RubyIO)file).close2(context.getRuntime());
        }
        else {
            file.callMethod(context, "close");
        }
    }
    
    @JRubyMethod(name = { "close" })
    public static IRubyObject close(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        data.next_argv(context);
        if (isClosed(context, data.currentFile)) {
            throw context.getRuntime().newIOError("closed stream");
        }
        argf_close(context, data.currentFile);
        if (data.next_p != -1) {
            data.next_p = 1;
        }
        data.currentLineNumber = 0;
        return recv;
    }
    
    @JRubyMethod(name = { "closed?" })
    public static IRubyObject closed_p(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        data.next_argv(context);
        return RubyBoolean.newBoolean(context.getRuntime(), isClosed(context, data.currentFile));
    }
    
    private static boolean isClosed(final ThreadContext context, final IRubyObject currentFile) {
        boolean closed = false;
        if (!(currentFile instanceof RubyIO)) {
            closed = currentFile.callMethod(context, "closed?").isTrue();
        }
        else {
            closed = ((RubyIO)currentFile).closed_p(context).isTrue();
        }
        return closed;
    }
    
    @JRubyMethod(name = { "binmode" })
    public static IRubyObject binmode(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = getData(context, recv, "no stream");
        ((RubyIO)data.currentFile).binmode();
        return recv;
    }
    
    @JRubyMethod(name = { "binmode?" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject op_binmode(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = getData(context, recv, "no stream");
        return ((RubyIO)data.currentFile).op_binmode(context);
    }
    
    @JRubyMethod(name = { "lineno" })
    public static IRubyObject lineno(final ThreadContext context, final IRubyObject recv) {
        return recv.getRuntime().newFixnum(ArgsFileData.getDataFrom(recv).currentLineNumber);
    }
    
    @JRubyMethod(name = { "lineno=" })
    public static IRubyObject lineno_set(final ThreadContext context, final IRubyObject recv, final IRubyObject line) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        data.currentLineNumber = RubyNumeric.fix2int(line);
        context.getRuntime().setCurrentLine(data.currentLineNumber);
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "tell" }, alias = { "pos" })
    public static IRubyObject tell(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.next_argv(context)) {
            throw context.getRuntime().newArgumentError("no stream to tell");
        }
        return ((RubyIO)data.currentFile).pos(context);
    }
    
    @JRubyMethod(name = { "rewind" })
    public static IRubyObject rewind(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = getData(context, recv, "no stream to rewind");
        final RubyFixnum retVal = ((RubyIO)data.currentFile).rewind(context);
        ((RubyIO)data.currentFile).lineno_set(context, context.getRuntime().newFixnum(0));
        data.minLineNumber = 0;
        data.currentLineNumber = 0;
        return retVal;
    }
    
    @JRubyMethod(name = { "eof" })
    public static IRubyObject eof(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.inited) {
            return context.getRuntime().getTrue();
        }
        if (!(data.currentFile instanceof RubyIO)) {
            return data.currentFile.callMethod(context, "eof");
        }
        return ((RubyIO)data.currentFile).eof_p(context);
    }
    
    @JRubyMethod(name = { "eof?" })
    public static IRubyObject eof_p(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.inited) {
            return context.getRuntime().getTrue();
        }
        if (!(data.currentFile instanceof RubyIO)) {
            return data.currentFile.callMethod(context, "eof?");
        }
        return ((RubyIO)data.currentFile).eof_p(context);
    }
    
    @JRubyMethod(name = { "pos=" }, required = 1)
    public static IRubyObject set_pos(final ThreadContext context, final IRubyObject recv, final IRubyObject offset) {
        final ArgsFileData data = getData(context, recv, "no stream to set position");
        return ((RubyIO)data.currentFile).pos_set(context, offset);
    }
    
    @JRubyMethod(name = { "seek" }, required = 1, optional = 1)
    public static IRubyObject seek(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final ArgsFileData data = getData(context, recv, "no stream to seek");
        return ((RubyIO)data.currentFile).seek(context, args);
    }
    
    @JRubyMethod(name = { "readchar" })
    public static IRubyObject readchar(final ThreadContext context, final IRubyObject recv) {
        final IRubyObject c = getc(context, recv);
        if (c.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return c;
    }
    
    @JRubyMethod(name = { "getc" })
    public static IRubyObject getc(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        while (data.next_argv(context)) {
            IRubyObject bt;
            if (!(data.currentFile instanceof RubyFile)) {
                bt = data.currentFile.callMethod(context, "getc");
            }
            else {
                bt = ((RubyIO)data.currentFile).getc();
            }
            if (!bt.isNil()) {
                return bt;
            }
            data.next_p = 1;
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "read" }, optional = 2)
    public static IRubyObject read(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        long len = 0L;
        IRubyObject length;
        IRubyObject str;
        if (args.length > 0) {
            length = args[0];
            str = ((args.length > 1) ? args[1] : runtime.getNil());
        }
        else {
            length = runtime.getNil();
            str = runtime.getNil();
        }
        if (!length.isNil()) {
            len = RubyNumeric.num2long(length);
        }
        if (!str.isNil()) {
            str = str.convertToString();
            ((RubyString)str).modify();
            ((RubyString)str).getByteList().length(0);
            args[1] = runtime.getNil();
        }
        while (data.next_argv(context)) {
            IRubyObject tmp;
            if (!(data.currentFile instanceof RubyIO)) {
                tmp = data.currentFile.callMethod(context, "read", args);
            }
            else {
                tmp = ((RubyIO)data.currentFile).read(args);
            }
            if (str.isNil()) {
                str = tmp;
            }
            else if (!tmp.isNil()) {
                ((RubyString)str).append(tmp);
            }
            if (tmp.isNil() || length.isNil()) {
                if (data.next_p != -1) {
                    argf_close(context, data.currentFile);
                    data.next_p = 1;
                    continue;
                }
            }
            else if (args.length >= 1 && ((RubyString)str).getByteList().length() < len) {
                len -= ((RubyString)str).getByteList().length();
                args[0] = runtime.newFixnum(len);
                continue;
            }
            return str;
        }
        return str;
    }
    
    @JRubyMethod(name = { "filename" }, alias = { "path" })
    public static IRubyObject filename(final ThreadContext context, final IRubyObject recv) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        data.next_argv(context);
        return context.getRuntime().getGlobalVariables().get("$FILENAME");
    }
    
    @JRubyMethod(name = { "to_s" })
    public static IRubyObject to_s(final IRubyObject recv) {
        return recv.getRuntime().newString("ARGF");
    }
    
    private static ArgsFileData getData(final ThreadContext context, final IRubyObject recv, final String errorMessage) {
        final ArgsFileData data = ArgsFileData.getDataFrom(recv);
        if (!data.next_argv(context)) {
            throw context.getRuntime().newArgumentError(errorMessage);
        }
        return data;
    }
    
    private static final class ArgsFileData
    {
        private final Ruby runtime;
        public IRubyObject currentFile;
        public int currentLineNumber;
        public int minLineNumber;
        private boolean inited;
        public int next_p;
        
        public ArgsFileData(final Ruby runtime) {
            this.inited = false;
            this.next_p = 0;
            this.runtime = runtime;
            this.currentFile = runtime.getNil();
        }
        
        public boolean next_argv(final ThreadContext context) {
            final RubyArray args = (RubyArray)this.runtime.getGlobalVariables().get("$*");
            if (!this.inited) {
                if (args.getLength() > 0) {
                    this.next_p = 1;
                }
                else {
                    this.next_p = -1;
                }
                this.inited = true;
                this.currentLineNumber = 0;
            }
            if (this.next_p == 1) {
                this.next_p = 0;
                if (args.getLength() <= 0) {
                    this.next_p = 1;
                    return false;
                }
                final IRubyObject arg = args.shift(context);
                final RubyString filename = (RubyString)((RubyObject)arg).to_s();
                final ByteList filenameBytes = filename.getByteList();
                if (!filename.op_equal(context, this.runtime.getGlobalVariables().get("$FILENAME")).isTrue()) {
                    this.runtime.defineReadonlyVariable("$FILENAME", filename);
                }
                if (filenameBytes.length() == 1 && filenameBytes.get(0) == 45) {
                    this.currentFile = this.runtime.getGlobalVariables().get("$stdin");
                }
                else {
                    this.currentFile = RubyIO.open(context, this.runtime.getFile(), new IRubyObject[] { filename }, Block.NULL_BLOCK);
                    final String extension = this.runtime.getInstanceConfig().getInPlaceBackupExtention();
                    if (extension != null) {
                        if (Platform.IS_WINDOWS) {
                            this.inplaceEditWindows(context, filename.asJavaString(), extension);
                        }
                        else {
                            this.inplaceEdit(context, filename.asJavaString(), extension);
                        }
                    }
                    this.minLineNumber = this.currentLineNumber;
                    this.currentFile.callMethod(context, "lineno=", context.getRuntime().newFixnum(this.currentLineNumber));
                }
            }
            else if (this.next_p == -1) {
                this.currentFile = this.runtime.getGlobalVariables().get("$stdin");
                if (!this.runtime.getGlobalVariables().get("$FILENAME").asJavaString().equals("-")) {
                    this.runtime.defineReadonlyVariable("$FILENAME", this.runtime.newString("-"));
                }
            }
            return true;
        }
        
        public static ArgsFileData getDataFrom(final IRubyObject recv) {
            ArgsFileData data = (ArgsFileData)recv.dataGetStruct();
            if (data == null) {
                data = new ArgsFileData(recv.getRuntime());
                recv.dataWrapStruct(data);
            }
            return data;
        }
        
        private void createNewFile(final File file) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                throw this.runtime.newIOErrorFromException(ex);
            }
        }
        
        private void inplaceEditWindows(final ThreadContext context, final String filename, final String extension) throws RaiseException {
            final File file = new File(filename);
            if (!extension.equals("")) {
                final String backup = filename + extension;
                final File backupFile = new File(backup);
                ((RubyIO)this.currentFile).close();
                backupFile.delete();
                file.renameTo(backupFile);
                this.currentFile = RubyIO.open(context, this.runtime.getFile(), new IRubyObject[] { this.runtime.newString(backup) }, Block.NULL_BLOCK);
                this.createNewFile(file);
                this.runtime.getGlobalVariables().set("$stdout", RubyIO.open(context, this.runtime.getFile(), new IRubyObject[] { this.runtime.newString(filename), this.runtime.newString("w") }, Block.NULL_BLOCK));
                return;
            }
            throw this.runtime.newIOError("Windows doesn't support inplace editing without a backup");
        }
        
        private void inplaceEdit(final ThreadContext context, final String filename, final String extension) throws RaiseException {
            final File file = new File(filename);
            final FileStat stat = this.runtime.getPosix().stat(filename);
            if (!extension.equals("")) {
                file.renameTo(new File(filename + extension));
            }
            else {
                file.delete();
            }
            this.createNewFile(file);
            this.runtime.getPosix().chmod(filename, stat.mode());
            this.runtime.getPosix().chown(filename, stat.uid(), stat.gid());
            this.runtime.getGlobalVariables().set("$stdout", RubyIO.open(context, this.runtime.getFile(), new IRubyObject[] { this.runtime.newString(filename), this.runtime.newString("w") }, Block.NULL_BLOCK));
        }
    }
}

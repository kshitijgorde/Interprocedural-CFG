// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.SafePropertyAccessor;
import java.nio.channels.Pipe;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.platform.Platform;
import org.jruby.util.io.SelectBlob;
import org.jruby.runtime.Arity;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.FileChannel;
import org.jcodings.exception.EncodingException;
import com.kenai.constantine.platform.Fcntl;
import org.jruby.anno.FrameField;
import java.nio.channels.SelectableChannel;
import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jruby.util.io.DirectoryAsFileException;
import java.io.FileNotFoundException;
import org.jruby.ext.ffi.Util;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Visibility;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.Block;
import java.io.EOFException;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.ThreadContext;
import java.io.IOException;
import org.jruby.util.io.PipeException;
import org.jruby.util.io.FileExistsException;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.io.Stream;
import java.io.FileDescriptor;
import org.jruby.util.io.STDIO;
import org.jruby.util.io.ModeFlags;
import org.jruby.util.ShellLauncher;
import java.io.InputStream;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ChannelStream;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import java.nio.channels.Channels;
import java.io.OutputStream;
import org.jcodings.Encoding;
import java.util.List;
import org.jruby.util.io.OpenFile;
import org.jruby.util.ByteList;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "IO" }, include = { "Enumerable" })
public class RubyIO extends RubyObject
{
    private static ObjectAllocator IO_ALLOCATOR;
    private static String vendor;
    private static String msgEINTR;
    private static final ByteList NIL_BYTELIST;
    private static final ByteList RECURSIVE_BYTELIST;
    protected OpenFile openFile;
    protected List<RubyThread> blockingThreads;
    protected Encoding externalEncoding;
    protected Encoding internalEncoding;
    
    public RubyIO(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.openFile = new OpenFile();
    }
    
    public RubyIO(final Ruby runtime, final OutputStream outputStream) {
        super(runtime, runtime.getIO());
        if (outputStream == null) {
            throw runtime.newRuntimeError("Opening null stream");
        }
        this.openFile = new OpenFile();
        try {
            this.openFile.setMainStream(ChannelStream.open(runtime, new ChannelDescriptor(Channels.newChannel(outputStream))));
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        this.openFile.setMode(66);
    }
    
    public RubyIO(final Ruby runtime, final InputStream inputStream) {
        super(runtime, runtime.getIO());
        if (inputStream == null) {
            throw runtime.newRuntimeError("Opening null stream");
        }
        this.openFile = new OpenFile();
        try {
            this.openFile.setMainStream(ChannelStream.open(runtime, new ChannelDescriptor(Channels.newChannel(inputStream))));
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        this.openFile.setMode(1);
    }
    
    public RubyIO(final Ruby runtime, final Channel channel) {
        super(runtime, runtime.getIO());
        if (channel == null) {
            throw runtime.newRuntimeError("Opening null channelpo");
        }
        this.openFile = new OpenFile();
        try {
            this.openFile.setMainStream(ChannelStream.open(runtime, new ChannelDescriptor(channel)));
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        this.openFile.setMode(this.openFile.getMainStream().getModes().getOpenFileFlags());
    }
    
    public RubyIO(final Ruby runtime, final ShellLauncher.POpenProcess process, final ModeFlags modes) {
        super(runtime, runtime.getIO());
        (this.openFile = new OpenFile()).setMode(modes.getOpenFileFlags() | 0x8);
        this.openFile.setProcess(process);
        try {
            if (this.openFile.isReadable()) {
                Channel inChannel;
                if (process.getInput() != null) {
                    inChannel = process.getInput();
                }
                else {
                    inChannel = Channels.newChannel(process.getInputStream());
                }
                final ChannelDescriptor main = new ChannelDescriptor(inChannel);
                main.setCanBeSeekable(false);
                this.openFile.setMainStream(ChannelStream.open(this.getRuntime(), main));
            }
            if (this.openFile.isWritable() && process.hasOutput()) {
                Channel outChannel;
                if (process.getOutput() != null) {
                    outChannel = process.getOutput();
                }
                else {
                    outChannel = Channels.newChannel(process.getOutputStream());
                }
                final ChannelDescriptor pipe = new ChannelDescriptor(outChannel);
                pipe.setCanBeSeekable(false);
                if (this.openFile.getMainStream() != null) {
                    this.openFile.setPipeStream(ChannelStream.open(this.getRuntime(), pipe));
                }
                else {
                    this.openFile.setMainStream(ChannelStream.open(this.getRuntime(), pipe));
                }
            }
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
    }
    
    public RubyIO(final Ruby runtime, final STDIO stdio) {
        super(runtime, runtime.getIO());
        this.openFile = new OpenFile();
        try {
            switch (stdio) {
                case IN: {
                    final ChannelDescriptor descriptor = new ChannelDescriptor(runtime.getIn(), new ModeFlags(ModeFlags.RDONLY), FileDescriptor.in);
                    runtime.putFilenoMap(0, descriptor.getFileno());
                    final Stream mainStream = ChannelStream.open(runtime, descriptor);
                    this.openFile.setMainStream(mainStream);
                    break;
                }
                case OUT: {
                    final ChannelDescriptor descriptor = new ChannelDescriptor(Channels.newChannel(runtime.getOut()), new ModeFlags(ModeFlags.WRONLY | ModeFlags.APPEND), FileDescriptor.out);
                    runtime.putFilenoMap(1, descriptor.getFileno());
                    final Stream mainStream = ChannelStream.open(runtime, descriptor);
                    this.openFile.setMainStream(mainStream);
                    this.openFile.getMainStream().setSync(true);
                    break;
                }
                case ERR: {
                    final ChannelDescriptor descriptor = new ChannelDescriptor(Channels.newChannel(runtime.getErr()), new ModeFlags(ModeFlags.WRONLY | ModeFlags.APPEND), FileDescriptor.err);
                    runtime.putFilenoMap(2, descriptor.getFileno());
                    final Stream mainStream = ChannelStream.open(runtime, descriptor);
                    this.openFile.setMainStream(mainStream);
                    this.openFile.getMainStream().setSync(true);
                    break;
                }
            }
        }
        catch (InvalidValueException ex) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        this.openFile.setMode(this.openFile.getMainStream().getModes().getOpenFileFlags());
        this.openFile.setAutoclose(false);
    }
    
    public static RubyIO newIO(final Ruby runtime, final Channel channel) {
        return new RubyIO(runtime, channel);
    }
    
    public OpenFile getOpenFile() {
        return this.openFile;
    }
    
    protected OpenFile getOpenFileChecked() {
        this.openFile.checkClosed(this.getRuntime());
        return this.openFile;
    }
    
    public int getNativeTypeIndex() {
        return 26;
    }
    
    public static RubyClass createIOClass(final Ruby runtime) {
        final RubyClass ioClass = runtime.defineClass("IO", runtime.getObject(), RubyIO.IO_ALLOCATOR);
        ioClass.index = 31;
        ioClass.setReifiedClass(RubyIO.class);
        ioClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyIO;
            }
        };
        ioClass.includeModule(runtime.getEnumerable());
        ioClass.defineAnnotatedMethods(RubyIO.class);
        ioClass.fastSetConstant("SEEK_SET", runtime.newFixnum(0));
        ioClass.fastSetConstant("SEEK_CUR", runtime.newFixnum(1));
        ioClass.fastSetConstant("SEEK_END", runtime.newFixnum(2));
        if (runtime.is1_9()) {
            ioClass.defineModuleUnder("WaitReadable");
            ioClass.defineModuleUnder("WaitWritable");
        }
        return ioClass;
    }
    
    public OutputStream getOutStream() {
        try {
            return this.getOpenFileChecked().getMainStreamSafe().newOutputStream();
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    public InputStream getInStream() {
        try {
            return this.getOpenFileChecked().getMainStreamSafe().newInputStream();
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    public Channel getChannel() {
        try {
            return this.getOpenFileChecked().getMainStreamSafe().getChannel();
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    @Deprecated
    public Stream getHandler() throws BadDescriptorException {
        return this.getOpenFileChecked().getMainStreamSafe();
    }
    
    protected void reopenPath(final Ruby runtime, final IRubyObject[] args) {
        if (runtime.is1_9() && !(args[0] instanceof RubyString) && args[0].respondsTo("to_path")) {
            args[0] = args[0].callMethod(runtime.getCurrentContext(), "to_path");
        }
        final IRubyObject pathString = args[0].convertToString();
        try {
            ModeFlags modes;
            if (args.length > 1) {
                final IRubyObject modeString = args[1].convertToString();
                modes = getIOModes(runtime, modeString.toString());
                this.openFile.setMode(modes.getOpenFileFlags());
            }
            else {
                modes = getIOModes(runtime, "r");
            }
            final String path = pathString.toString();
            this.openFile.setPath(path);
            if (this.openFile.getMainStream() == null) {
                try {
                    this.openFile.setMainStream(ChannelStream.fopen(runtime, path, modes));
                }
                catch (FileExistsException fee) {
                    throw runtime.newErrnoEEXISTError(path);
                }
                if (this.openFile.getPipeStream() != null) {
                    this.openFile.getPipeStream().fclose();
                    this.openFile.setPipeStream(null);
                }
            }
            else {
                this.openFile.getMainStreamSafe().freopen(runtime, path, getIOModes(runtime, this.openFile.getModeAsString(runtime)));
                if (this.openFile.getPipeStream() != null) {}
            }
        }
        catch (PipeException pe) {
            throw runtime.newErrnoEPIPEError();
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException e) {
            throw runtime.newErrnoEINVALError();
        }
    }
    
    protected void reopenIO(final Ruby runtime, final RubyIO ios) {
        try {
            if (ios.openFile == this.openFile) {
                return;
            }
            final OpenFile originalFile = ios.getOpenFileChecked();
            final OpenFile selfFile = this.getOpenFileChecked();
            long pos = 0L;
            if (originalFile.isReadable()) {
                pos = originalFile.getMainStreamSafe().fgetpos();
            }
            if (originalFile.getPipeStream() != null) {
                originalFile.getPipeStream().fflush();
            }
            else if (originalFile.isWritable()) {
                originalFile.getMainStreamSafe().fflush();
            }
            if (selfFile.isWritable()) {
                selfFile.getWriteStreamSafe().fflush();
            }
            selfFile.setMode(originalFile.getMode());
            selfFile.setProcess(originalFile.getProcess());
            selfFile.setLineNumber(originalFile.getLineNumber());
            selfFile.setPath(originalFile.getPath());
            selfFile.setFinalizer(originalFile.getFinalizer());
            final ChannelDescriptor selfDescriptor = selfFile.getMainStreamSafe().getDescriptor();
            final ChannelDescriptor originalDescriptor = originalFile.getMainStreamSafe().getDescriptor();
            if (selfDescriptor.getChannel() != originalDescriptor.getChannel()) {
                if (runtime.getFileno(selfDescriptor) >= 0 && runtime.getFileno(selfDescriptor) <= 2) {
                    selfFile.getMainStreamSafe().clearerr();
                    originalDescriptor.dup2Into(selfDescriptor);
                }
                else {
                    final Stream pipeFile = selfFile.getPipeStream();
                    final int mode = selfFile.getMode();
                    selfFile.getMainStreamSafe().fclose();
                    selfFile.setPipeStream(null);
                    if (pipeFile != null) {
                        selfFile.setMainStream(ChannelStream.fdopen(runtime, originalDescriptor, new ModeFlags()));
                        selfFile.setPipeStream(pipeFile);
                    }
                    else {
                        selfFile.setMainStream(ChannelStream.open(runtime, originalDescriptor.dup2(selfDescriptor.getFileno())));
                        selfFile.getMainStreamSafe().setSync(selfFile.getMainStreamSafe().isSync());
                    }
                    selfFile.setMode(mode);
                }
                if (originalFile.isReadable() && pos >= 0L) {
                    selfFile.seek(pos, 0);
                    originalFile.seek(pos, 0);
                }
            }
            if (selfFile.getPipeStream() != null && selfDescriptor.getFileno() != selfFile.getPipeStream().getDescriptor().getFileno()) {
                final int fd = selfFile.getPipeStream().getDescriptor().getFileno();
                if (originalFile.getPipeStream() == null) {
                    selfFile.getPipeStream().fclose();
                    selfFile.setPipeStream(null);
                }
                else if (fd != originalFile.getPipeStream().getDescriptor().getFileno()) {
                    selfFile.getPipeStream().fclose();
                    final ChannelDescriptor newFD2 = originalFile.getPipeStream().getDescriptor().dup2(fd);
                    selfFile.setPipeStream(ChannelStream.fdopen(runtime, newFD2, getIOModes(runtime, "w")));
                }
            }
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newIOError("could not reopen: " + ex2.getMessage());
        }
        catch (PipeException ex3) {
            throw runtime.newIOError("could not reopen: " + ex3.getMessage());
        }
        catch (InvalidValueException ive) {
            throw runtime.newErrnoEINVALError();
        }
    }
    
    @JRubyMethod(name = { "reopen" }, required = 1, optional = 1)
    public IRubyObject reopen(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject tmp = TypeConverter.convertToTypeWithCheck(args[0], runtime.getIO(), "to_io");
        if (!tmp.isNil()) {
            this.reopenIO(runtime, (RubyIO)tmp);
        }
        else {
            this.reopenPath(runtime, args);
        }
        return this;
    }
    
    public static ModeFlags getIOModes(final Ruby runtime, final String modesString) throws InvalidValueException {
        return new ModeFlags(getIOModesIntFromString(runtime, modesString));
    }
    
    public static int getIOModesIntFromString(final Ruby runtime, final String modesString) {
        int modes = 0;
        final int length = modesString.length();
        if (length == 0) {
            throw runtime.newArgumentError("illegal access mode");
        }
        switch (modesString.charAt(0)) {
            case 'r': {
                modes |= ModeFlags.RDONLY;
                break;
            }
            case 'a': {
                modes |= (ModeFlags.APPEND | ModeFlags.WRONLY | ModeFlags.CREAT);
                break;
            }
            case 'w': {
                modes |= (ModeFlags.WRONLY | ModeFlags.TRUNC | ModeFlags.CREAT);
                break;
            }
            default: {
                throw runtime.newArgumentError("illegal access mode " + modesString);
            }
        }
    Label_0245:
        for (int n = 1; n < length; ++n) {
            switch (modesString.charAt(n)) {
                case 'b': {
                    modes |= ModeFlags.BINARY;
                    break;
                }
                case '+': {
                    modes = ((modes & ~ModeFlags.ACCMODE) | ModeFlags.RDWR);
                    break;
                }
                case 't': {
                    break;
                }
                case ':': {
                    break Label_0245;
                }
                default: {
                    throw runtime.newArgumentError("illegal access mode " + modesString);
                }
            }
        }
        return modes;
    }
    
    private ByteList separator(final Ruby runtime) {
        return this.separator(runtime, runtime.getRecordSeparatorVar().get());
    }
    
    private ByteList separator(final Ruby runtime, final IRubyObject separatorValue) {
        ByteList separator = separatorValue.isNil() ? null : separatorValue.convertToString().getByteList();
        if (separator != null) {
            if (separator.getRealSize() == 0) {
                separator = Stream.PARAGRAPH_DELIMETER;
            }
            if (runtime.is1_9()) {
                final Encoding internal = this.getInternalEncoding(runtime);
                if (internal != null) {
                    separator = RubyString.transcode(runtime.getCurrentContext(), separator, internal, this.getExternalEncoding(runtime), runtime.getNil());
                }
            }
        }
        return separator;
    }
    
    private ByteList getSeparatorFromArgs(final Ruby runtime, final IRubyObject[] args, final int idx) {
        return this.separator(runtime, (args.length > idx) ? args[idx] : runtime.getRecordSeparatorVar().get());
    }
    
    private ByteList getSeparatorForGets(final Ruby runtime, final IRubyObject[] args) {
        return this.getSeparatorFromArgs(runtime, args, 0);
    }
    
    private IRubyObject getline(final Ruby runtime, final ByteList separator, final ByteListCache cache) {
        return this.getline(runtime, separator, -1L, cache);
    }
    
    public IRubyObject getline(final Ruby runtime, final ByteList separator) {
        return this.getline(runtime, separator, -1L, null);
    }
    
    public IRubyObject getline(final Ruby runtime, final ByteList separator, final long limit) {
        return this.getline(runtime, separator, limit, null);
    }
    
    private IRubyObject getline(final Ruby runtime, final ByteList separator, final long limit, final ByteListCache cache) {
        IRubyObject result = this.getlineInner(runtime, separator, limit, cache);
        if (runtime.is1_9() && !result.isNil()) {
            final Encoding internal = this.getInternalEncoding(runtime);
            if (internal != null) {
                result = RubyString.newStringNoCopy(runtime, RubyString.transcode(runtime.getCurrentContext(), ((RubyString)result).getByteList(), this.getExternalEncoding(runtime), internal, runtime.getNil()));
            }
        }
        return result;
    }
    
    private IRubyObject getlineInner(final Ruby runtime, ByteList separator, long limit, final ByteListCache cache) {
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkReadable(runtime);
            myOpenFile.setReadBuffered();
            final boolean isParagraph = separator == Stream.PARAGRAPH_DELIMETER;
            separator = (isParagraph ? Stream.PARAGRAPH_SEPARATOR : separator);
            if (isParagraph) {
                this.swallow(10);
            }
            if (separator == null && limit < 0L) {
                final RubyString str = this.readAll();
                if (str.getByteList().length() == 0) {
                    return runtime.getNil();
                }
                this.incrementLineno(runtime, myOpenFile);
                return str;
            }
            else if (limit == 0L) {
                if (runtime.is1_9()) {
                    return RubyString.newEmptyString(runtime, this.getExternalEncoding(runtime));
                }
                return RubyString.newEmptyString(runtime);
            }
            else {
                if (separator.length() == 1 && limit < 0L) {
                    return this.getlineFast(runtime, separator.get(0) & 0xFF, cache);
                }
                final Stream readStream = myOpenFile.getMainStreamSafe();
                int c = -1;
                int n = -1;
                final int newline = separator.get(separator.length() - 1) & 0xFF;
                final ByteList buf = (cache != null) ? cache.allocate(0) : new ByteList(0);
                try {
                    boolean update = false;
                    boolean limitReached = false;
                    while (true) {
                        this.readCheck(readStream);
                        readStream.clearerr();
                        Label_0450: {
                            try {
                                runtime.getCurrentContext().getThread().beforeBlockingCall();
                                if (limit == -1L) {
                                    n = readStream.getline(buf, (byte)newline);
                                }
                                else {
                                    n = readStream.getline(buf, (byte)newline, limit);
                                    limit -= n;
                                    if (limit <= 0L) {
                                        limitReached = (update = true);
                                        break Label_0450;
                                    }
                                }
                                c = ((buf.length() > 0) ? (buf.get(buf.length() - 1) & 0xFF) : -1);
                            }
                            catch (EOFException e2) {
                                n = -1;
                            }
                            finally {
                                runtime.getCurrentContext().getThread().afterBlockingCall();
                            }
                            if (n == 0) {
                                this.waitReadable(readStream);
                            }
                            else {
                                if (n == -1) {
                                    break Label_0450;
                                }
                                update = true;
                            }
                            if (c != newline) {
                                continue;
                            }
                        }
                        if (n == -1) {
                            break;
                        }
                        if (limitReached) {
                            break;
                        }
                        if (c == newline && buf.length() >= separator.length() && 0 == ByteList.memcmp(buf.getUnsafeBytes(), buf.getBegin() + buf.getRealSize() - separator.length(), separator.getUnsafeBytes(), separator.getBegin(), separator.getRealSize())) {
                            break;
                        }
                    }
                    if (isParagraph && c != -1) {
                        this.swallow(10);
                    }
                    if (!update) {
                        return runtime.getNil();
                    }
                    this.incrementLineno(runtime, myOpenFile);
                    return this.makeString(runtime, buf, cache != null);
                }
                finally {
                    if (cache != null) {
                        cache.release(buf);
                    }
                }
            }
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (EOFException e3) {
            return runtime.getNil();
        }
        catch (BadDescriptorException e4) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    private Encoding getExternalEncoding(final Ruby runtime) {
        return (this.externalEncoding != null) ? this.externalEncoding : runtime.getDefaultExternalEncoding();
    }
    
    private Encoding getInternalEncoding(final Ruby runtime) {
        return (this.internalEncoding != null) ? this.internalEncoding : runtime.getDefaultInternalEncoding();
    }
    
    private RubyString makeString(final Ruby runtime, final ByteList buffer, final boolean isCached) {
        final ByteList newBuf = isCached ? new ByteList(buffer) : buffer;
        if (runtime.is1_9()) {
            newBuf.setEncoding(this.getExternalEncoding(runtime));
        }
        final RubyString str = RubyString.newString(runtime, newBuf);
        str.setTaint(true);
        return str;
    }
    
    private void incrementLineno(final Ruby runtime, final OpenFile myOpenFile) {
        final int lineno = myOpenFile.getLineNumber() + 1;
        myOpenFile.setLineNumber(lineno);
        runtime.setCurrentLine(lineno);
        RubyArgsFile.setCurrentLineNumber(runtime.getArgsFile(), lineno);
    }
    
    protected boolean swallow(final int term) throws IOException, BadDescriptorException {
        final Stream readStream = this.openFile.getMainStreamSafe();
        int c;
        do {
            this.readCheck(readStream);
            try {
                c = readStream.fgetc();
            }
            catch (EOFException e) {
                c = -1;
            }
            if (c != term) {
                readStream.ungetc(c);
                return true;
            }
        } while (c != -1);
        return false;
    }
    
    public static boolean restartSystemCall(final Exception e) {
        return RubyIO.vendor.startsWith("Apple") && e.getMessage().equals(RubyIO.msgEINTR);
    }
    
    private IRubyObject getlineFast(final Ruby runtime, final int delim, final ByteListCache cache) throws IOException, BadDescriptorException {
        final Stream readStream = this.openFile.getMainStreamSafe();
        int c = -1;
        final ByteList buf = (cache != null) ? cache.allocate(0) : new ByteList(0);
        try {
            boolean update = false;
            do {
                this.readCheck(readStream);
                readStream.clearerr();
                int n;
                try {
                    runtime.getCurrentContext().getThread().beforeBlockingCall();
                    n = readStream.getline(buf, (byte)delim);
                    c = ((buf.length() > 0) ? (buf.get(buf.length() - 1) & 0xFF) : -1);
                }
                catch (EOFException e) {
                    n = -1;
                }
                finally {
                    runtime.getCurrentContext().getThread().afterBlockingCall();
                }
                if (n == 0) {
                    this.waitReadable(readStream);
                }
                else {
                    if (n == -1) {
                        break;
                    }
                    update = true;
                }
            } while (c != delim);
            if (!update) {
                return runtime.getNil();
            }
            this.incrementLineno(runtime, this.openFile);
            return this.makeString(runtime, buf, cache != null);
        }
        finally {
            if (cache != null) {
                cache.release(buf);
            }
        }
    }
    
    @JRubyMethod(name = { "new", "for_fd" }, rest = true, meta = true)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyClass klass = (RubyClass)recv;
        if (block.isGiven()) {
            final String className = klass.getName();
            context.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_NOT_ACCEPTED, className + "::new() does not take block; use " + className + "::open() instead", className + "::open()");
        }
        return klass.newInstance(context, args, block);
    }
    
    private IRubyObject initializeCommon19(final int fileno, ModeFlags modes) {
        try {
            final ChannelDescriptor descriptor = ChannelDescriptor.getDescriptorByFileno(this.getRuntime().getFilenoExtMap(fileno));
            if (descriptor == null) {
                throw this.getRuntime().newErrnoEBADFError();
            }
            descriptor.checkOpen();
            if (modes == null) {
                modes = descriptor.getOriginalModes();
            }
            if (this.openFile.isOpen()) {
                this.openFile.cleanup(this.getRuntime(), false);
            }
            this.openFile.setMode(modes.getOpenFileFlags());
            this.openFile.setMainStream(this.fdopen(descriptor, modes));
        }
        catch (BadDescriptorException ex) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException ive) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject fileNumber, final Block unusedBlock) {
        return this.initializeCommon19(RubyNumeric.fix2int(fileNumber), null);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject fileNumber, final IRubyObject second, final Block unusedBlock) {
        final int fileno = RubyNumeric.fix2int(fileNumber);
        ModeFlags modes;
        if (second instanceof RubyHash) {
            modes = this.parseOptions(context, second, null);
        }
        else {
            modes = this.parseModes19(context, second);
        }
        return this.initializeCommon19(fileno, modes);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject fileNumber, final IRubyObject modeValue, final IRubyObject options, final Block unusedBlock) {
        final int fileno = RubyNumeric.fix2int(fileNumber);
        ModeFlags modes = this.parseModes19(context, modeValue);
        modes = this.parseOptions(context, options, modes);
        return this.initializeCommon19(fileno, modes);
    }
    
    protected ModeFlags parseModes(final IRubyObject arg) {
        try {
            if (arg instanceof RubyFixnum) {
                return new ModeFlags(RubyNumeric.fix2long(arg));
            }
            return getIOModes(this.getRuntime(), arg.convertToString().toString());
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
    }
    
    protected ModeFlags parseModes19(final ThreadContext context, final IRubyObject arg) {
        final ModeFlags modes = this.parseModes(arg);
        if (arg instanceof RubyString) {
            this.parseEncodingFromString(context, arg, 1);
        }
        return modes;
    }
    
    private void parseEncodingFromString(final ThreadContext context, final IRubyObject arg, final int initialPosition) {
        final RubyString modes19 = arg.convertToString();
        if (modes19.toString().contains(":")) {
            final Ruby runtime = context.getRuntime();
            final IRubyObject[] fullEncoding = modes19.split(context, RubyString.newString(runtime, ":")).toJavaArray();
            IRubyObject externalEncodingOption = fullEncoding[initialPosition];
            final RubyString dash = runtime.newString("-");
            if (dash.eql(externalEncodingOption)) {
                externalEncodingOption = runtime.getEncodingService().getDefaultExternal();
            }
            if (fullEncoding.length > initialPosition + 1) {
                IRubyObject internalEncodingOption = fullEncoding[initialPosition + 1];
                if (dash.eql(internalEncodingOption)) {
                    internalEncodingOption = runtime.getEncodingService().getDefaultInternal();
                }
                this.set_encoding(context, externalEncodingOption, internalEncodingOption);
            }
            else {
                this.set_encoding(context, externalEncodingOption);
            }
        }
    }
    
    @JRubyMethod(required = 1, optional = 1, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final IRubyObject[] args, final Block unusedBlock) {
        final int argCount = args.length;
        final int fileno = RubyNumeric.fix2int(args[0]);
        try {
            final ChannelDescriptor descriptor = ChannelDescriptor.getDescriptorByFileno(this.getRuntime().getFilenoExtMap(fileno));
            if (descriptor == null) {
                throw this.getRuntime().newErrnoEBADFError();
            }
            descriptor.checkOpen();
            ModeFlags modes;
            if (argCount == 2) {
                if (args[1] instanceof RubyFixnum) {
                    modes = new ModeFlags(RubyNumeric.fix2long(args[1]));
                }
                else {
                    modes = getIOModes(this.getRuntime(), args[1].convertToString().toString());
                }
            }
            else {
                modes = descriptor.getOriginalModes();
            }
            if (this.openFile.isOpen()) {
                this.openFile.cleanup(this.getRuntime(), false);
            }
            this.openFile.setMode(modes.getOpenFileFlags());
            this.openFile.setMainStream(this.fdopen(descriptor, modes));
        }
        catch (BadDescriptorException ex) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException ive) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        return this;
    }
    
    protected Stream fdopen(final ChannelDescriptor existingDescriptor, final ModeFlags modes) throws InvalidValueException {
        if (existingDescriptor == null) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        return ChannelStream.fdopen(this.getRuntime(), existingDescriptor, modes);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject external_encoding(final ThreadContext context) {
        return (this.externalEncoding != null) ? context.getRuntime().getEncodingService().getEncoding(this.externalEncoding) : context.getRuntime().getNil();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject internal_encoding(final ThreadContext context) {
        return (this.internalEncoding != null) ? context.getRuntime().getEncodingService().getEncoding(this.internalEncoding) : context.getRuntime().getNil();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject set_encoding(final ThreadContext context, final IRubyObject encodingString) {
        this.setExternalEncoding(context, encodingString);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject set_encoding(final ThreadContext context, final IRubyObject encodingString, final IRubyObject internalEncoding) {
        this.setExternalEncoding(context, encodingString);
        this.setInternalEncoding(context, internalEncoding);
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject set_encoding(final ThreadContext context, final IRubyObject encodingString, final IRubyObject internalEncoding, final IRubyObject options) {
        this.setExternalEncoding(context, encodingString);
        this.setInternalEncoding(context, internalEncoding);
        return context.getRuntime().getNil();
    }
    
    private void setExternalEncoding(final ThreadContext context, final IRubyObject encoding) {
        this.externalEncoding = this.getEncodingCommon(context, encoding);
    }
    
    private void setInternalEncoding(final ThreadContext context, final IRubyObject encoding) {
        final Encoding internalEncodingOption = this.getEncodingCommon(context, encoding);
        if (internalEncodingOption == this.externalEncoding) {
            context.getRuntime().getWarnings().warn("Ignoring internal encoding " + encoding + ": it is identical to external encoding " + this.external_encoding(context));
        }
        else {
            this.internalEncoding = internalEncodingOption;
        }
    }
    
    private Encoding getEncodingCommon(final ThreadContext context, final IRubyObject encoding) {
        if (encoding instanceof RubyEncoding) {
            return ((RubyEncoding)encoding).getEncoding();
        }
        return context.getRuntime().getEncodingService().getEncodingFromObject(encoding);
    }
    
    @JRubyMethod(required = 1, optional = 2, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyClass klass = (RubyClass)recv;
        final RubyIO io = (RubyIO)klass.newInstance(context, args, block);
        if (block.isGiven()) {
            try {
                return block.yield(context, io);
            }
            finally {
                try {
                    io.getMetaClass().finvoke(context, io, "close", IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
                }
                catch (RaiseException re) {
                    final RubyException rubyEx = re.getException();
                    if (!rubyEx.kind_of_p(context, runtime.getStandardError()).isTrue()) {
                        throw re;
                    }
                    runtime.getGlobalVariables().clear("$!");
                }
            }
        }
        return io;
    }
    
    @JRubyMethod(required = 1, optional = 2, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject sysopen(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        Util.checkStringSafety(recv.getRuntime(), args[0]);
        final IRubyObject pathString = args[0].convertToString();
        return sysopenCommon(recv, args, block, pathString);
    }
    
    @JRubyMethod(name = { "sysopen" }, required = 1, optional = 2, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject sysopen19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        Util.checkStringSafety(context.getRuntime(), args[0]);
        IRubyObject pathString;
        if (!(args[0] instanceof RubyString) && args[0].respondsTo("to_path")) {
            pathString = args[0].callMethod(context, "to_path");
        }
        else {
            pathString = args[0].convertToString();
        }
        return sysopenCommon(recv, args, block, pathString);
    }
    
    private static IRubyObject sysopenCommon(final IRubyObject recv, final IRubyObject[] args, final Block block, final IRubyObject pathString) {
        final Ruby runtime = recv.getRuntime();
        runtime.checkSafeString(pathString);
        final String path = pathString.toString();
        ModeFlags modes = null;
        int perms = -1;
        try {
            if (args.length > 1) {
                final IRubyObject modeString = args[1].convertToString();
                modes = getIOModes(runtime, modeString.toString());
            }
            else {
                modes = getIOModes(runtime, "r");
            }
            if (args.length > 2) {
                final RubyInteger permsInt = (args.length >= 3) ? args[2].convertToInteger() : null;
                perms = RubyNumeric.fix2int(permsInt);
            }
        }
        catch (InvalidValueException e) {
            throw runtime.newErrnoEINVALError();
        }
        int fileno = -1;
        try {
            final ChannelDescriptor descriptor = ChannelDescriptor.open(runtime.getCurrentDirectory(), path, modes, perms, runtime.getPosix(), runtime.getJRubyClassLoader());
            fileno = descriptor.getFileno();
        }
        catch (FileNotFoundException fnfe) {
            throw runtime.newErrnoENOENTError(path);
        }
        catch (DirectoryAsFileException dafe) {
            throw runtime.newErrnoEISDirError(path);
        }
        catch (FileExistsException fee) {
            throw runtime.newErrnoEEXISTError(path);
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
        return runtime.newFixnum(fileno);
    }
    
    public boolean isAutoclose() {
        return this.openFile.isAutoclose();
    }
    
    public void setAutoclose(final boolean autoclose) {
        this.openFile.setAutoclose(autoclose);
    }
    
    @JRubyMethod
    public IRubyObject autoclose(final ThreadContext context) {
        return context.runtime.newBoolean(this.isAutoclose());
    }
    
    @JRubyMethod(name = { "autoclose=" })
    public IRubyObject autoclose_set(final ThreadContext context, final IRubyObject autoclose) {
        this.setAutoclose(autoclose.isTrue());
        return context.nil;
    }
    
    @JRubyMethod(name = { "binmode" })
    public IRubyObject binmode() {
        if (this.isClosed()) {
            throw this.getRuntime().newIOError("closed stream");
        }
        final Ruby runtime = this.getRuntime();
        if (this.getExternalEncoding(runtime) == USASCIIEncoding.INSTANCE) {
            this.externalEncoding = ASCIIEncoding.INSTANCE;
        }
        this.openFile.setBinmode();
        return this;
    }
    
    @JRubyMethod(name = { "binmode?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_binmode(final ThreadContext context) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.openFile.isBinmode());
    }
    
    @JRubyMethod(name = { "syswrite" }, required = 1)
    public IRubyObject syswrite(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        try {
            final RubyString string = obj.asString();
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkWritable(runtime);
            final Stream writeStream = myOpenFile.getWriteStream();
            if (myOpenFile.isWriteBuffered()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.SYSWRITE_BUFFERED_IO, "syswrite for buffered IO", new Object[0]);
            }
            if (!writeStream.getDescriptor().isWritable()) {
                myOpenFile.checkClosed(runtime);
            }
            context.getThread().beforeBlockingCall();
            final int read = writeStream.getDescriptor().write(string.getByteList());
            if (read == -1) {}
            return runtime.newFixnum(read);
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newSystemCallError(e.getMessage());
        }
        finally {
            context.getThread().afterBlockingCall();
        }
    }
    
    @JRubyMethod(name = { "write_nonblock" }, required = 1)
    public IRubyObject write_nonblock(final ThreadContext context, final IRubyObject obj) {
        final OpenFile myOpenFile = this.getOpenFileChecked();
        try {
            myOpenFile.checkWritable(context.getRuntime());
            final RubyString str = obj.asString();
            if (str.getByteList().length() == 0) {
                return context.getRuntime().newFixnum(0);
            }
            if (myOpenFile.isWriteBuffered()) {
                context.getRuntime().getWarnings().warn(IRubyWarnings.ID.SYSWRITE_BUFFERED_IO, "write_nonblock for buffered IO", new Object[0]);
            }
            final int written = myOpenFile.getWriteStream().getDescriptor().write(str.getByteList());
            return context.getRuntime().newFixnum(written);
        }
        catch (IOException ex) {
            throw context.getRuntime().newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException ex3) {
            throw context.getRuntime().newErrnoEINVALError();
        }
    }
    
    @JRubyMethod(name = { "write" }, required = 1)
    public IRubyObject write(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final RubyString str = obj.asString();
        if (str.getByteList().length() == 0) {
            return runtime.newFixnum(0);
        }
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkWritable(runtime);
            context.getThread().beforeBlockingCall();
            final int written = this.fwrite(str.getByteList());
            if (written == -1) {}
            if (!myOpenFile.isSync()) {
                myOpenFile.setWriteBuffered();
            }
            return runtime.newFixnum(written);
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException ex3) {
            throw runtime.newErrnoEINVALError();
        }
        finally {
            context.getThread().afterBlockingCall();
        }
    }
    
    private boolean waitWritable(final Stream stream) {
        final Channel ch = stream.getChannel();
        if (ch instanceof SelectableChannel) {
            this.getRuntime().getCurrentContext().getThread().select(ch, this, 4);
            return true;
        }
        return false;
    }
    
    private boolean waitReadable(final Stream stream) {
        if (stream.readDataBuffered()) {
            return true;
        }
        final Channel ch = stream.getChannel();
        if (ch instanceof SelectableChannel) {
            this.getRuntime().getCurrentContext().getThread().select(ch, this, 1);
            return true;
        }
        return false;
    }
    
    protected int fwrite(final ByteList buffer) {
        int offset = 0;
        boolean eagain = false;
        final Stream writeStream = this.openFile.getWriteStream();
        final int len = buffer.length();
        int n;
        if ((n = len) <= 0) {
            return n;
        }
        try {
            if (this.openFile.isSync()) {
                this.openFile.fflush(writeStream);
                while (offset < len) {
                    final int l = n;
                    final int r = writeStream.getDescriptor().write(buffer, offset, l);
                    if (r == len) {
                        return len;
                    }
                    if (0 <= r) {
                        offset += r;
                        n -= r;
                        eagain = true;
                    }
                    if (!eagain || !this.waitWritable(writeStream)) {
                        return -1;
                    }
                    this.openFile.checkClosed(this.getRuntime());
                    if (offset >= buffer.length()) {
                        return -1;
                    }
                    eagain = false;
                }
            }
            return writeStream.fwrite(buffer);
        }
        catch (IOException ex) {
            throw this.getRuntime().newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "<<" }, required = 1)
    public IRubyObject op_append(final ThreadContext context, final IRubyObject anObject) {
        this.callMethod(context, "write", anObject);
        return this;
    }
    
    @JRubyMethod(name = { "fileno" }, alias = { "to_i" })
    public RubyFixnum fileno(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        try {
            return runtime.newFixnum(runtime.getFileno(this.getOpenFileChecked().getMainStreamSafe().getDescriptor()));
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "lineno" })
    public RubyFixnum lineno(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.getOpenFileChecked().getLineNumber());
    }
    
    @JRubyMethod(name = { "lineno=" }, required = 1)
    public RubyFixnum lineno_set(final ThreadContext context, final IRubyObject newLineNumber) {
        this.getOpenFileChecked().setLineNumber(RubyNumeric.fix2int(newLineNumber));
        return context.getRuntime().newFixnum(this.getOpenFileChecked().getLineNumber());
    }
    
    @JRubyMethod(name = { "sync" })
    public RubyBoolean sync(final ThreadContext context) {
        try {
            return context.getRuntime().newBoolean(this.getOpenFileChecked().getMainStreamSafe().isSync());
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "pid" })
    public IRubyObject pid(final ThreadContext context) {
        final OpenFile myOpenFile = this.getOpenFileChecked();
        if (myOpenFile.getProcess() == null) {
            return context.getRuntime().getNil();
        }
        final long pid = myOpenFile.getPid();
        return context.getRuntime().newFixnum(pid);
    }
    
    @JRubyMethod(name = { "pos", "tell" })
    public RubyFixnum pos(final ThreadContext context) {
        try {
            return context.getRuntime().newFixnum(this.getOpenFileChecked().getMainStreamSafe().fgetpos());
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (BadDescriptorException bde) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (PipeException e2) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (IOException e) {
            throw context.getRuntime().newIOErrorFromException(e);
        }
    }
    
    @JRubyMethod(name = { "pos=" }, required = 1)
    public RubyFixnum pos_set(final ThreadContext context, final IRubyObject newPosition) {
        final long offset = RubyNumeric.num2long(newPosition);
        if (offset < 0L) {
            throw context.getRuntime().newSystemCallError("Negative seek offset");
        }
        final OpenFile myOpenFile = this.getOpenFileChecked();
        try {
            myOpenFile.getMainStreamSafe().lseek(offset, 0);
            myOpenFile.getMainStreamSafe().clearerr();
        }
        catch (BadDescriptorException e2) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException e3) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (PipeException e4) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (IOException e) {
            throw context.getRuntime().newIOErrorFromException(e);
        }
        return context.getRuntime().newFixnum(offset);
    }
    
    @JRubyMethod(name = { "print" }, rest = true, reads = { FrameField.LASTLINE })
    public IRubyObject print(final ThreadContext context, final IRubyObject[] args) {
        return print(context, this, args);
    }
    
    public static IRubyObject print(final ThreadContext context, final IRubyObject maybeIO, IRubyObject[] args) {
        if (args.length == 0) {
            args = new IRubyObject[] { context.getCurrentScope().getLastLine(context.getRuntime()) };
        }
        final Ruby runtime = context.getRuntime();
        final IRubyObject fs = runtime.getGlobalVariables().get("$,");
        final IRubyObject rs = runtime.getGlobalVariables().get("$\\");
        for (int i = 0; i < args.length; ++i) {
            if (i > 0 && !fs.isNil()) {
                maybeIO.callMethod(context, "write", fs);
            }
            if (args[i].isNil()) {
                maybeIO.callMethod(context, "write", runtime.newString("nil"));
            }
            else {
                maybeIO.callMethod(context, "write", args[i]);
            }
        }
        if (!rs.isNil()) {
            maybeIO.callMethod(context, "write", rs);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "printf" }, required = 1, rest = true)
    public IRubyObject printf(final ThreadContext context, final IRubyObject[] args) {
        this.callMethod(context, "write", RubyKernel.sprintf(context, this, args));
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "putc" }, required = 1, backtrace = true)
    public IRubyObject putc(final ThreadContext context, final IRubyObject object) {
        return putc(context, this, object);
    }
    
    public static IRubyObject putc(final ThreadContext context, final IRubyObject maybeIO, final IRubyObject object) {
        final int c = RubyNumeric.num2chr(object);
        if (maybeIO instanceof RubyIO) {
            final RubyIO io = (RubyIO)maybeIO;
            try {
                final OpenFile myOpenFile = io.getOpenFileChecked();
                myOpenFile.checkWritable(context.getRuntime());
                final Stream writeStream = myOpenFile.getWriteStream();
                writeStream.fputc(c);
                if (myOpenFile.isSync()) {
                    myOpenFile.fflush(writeStream);
                }
            }
            catch (IOException ex) {
                throw context.getRuntime().newIOErrorFromException(ex);
            }
            catch (BadDescriptorException e) {
                throw context.getRuntime().newErrnoEBADFError();
            }
            catch (InvalidValueException ex2) {
                throw context.getRuntime().newErrnoEINVALError();
            }
        }
        else {
            maybeIO.callMethod(context, "write", RubyString.newStringNoCopy(context.getRuntime(), new byte[] { (byte)c }));
        }
        return object;
    }
    
    public RubyFixnum seek(final ThreadContext context, final IRubyObject[] args) {
        final long offset = RubyNumeric.num2long(args[0]);
        int whence = 0;
        if (args.length > 1) {
            whence = RubyNumeric.fix2int(args[1].convertToInteger());
        }
        return this.doSeek(context, offset, whence);
    }
    
    @JRubyMethod(name = { "seek" })
    public RubyFixnum seek(final ThreadContext context, final IRubyObject arg0) {
        final long offset = RubyNumeric.num2long(arg0);
        final int whence = 0;
        return this.doSeek(context, offset, whence);
    }
    
    @JRubyMethod(name = { "seek" })
    public RubyFixnum seek(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final long offset = RubyNumeric.num2long(arg0);
        final int whence = RubyNumeric.fix2int(arg1.convertToInteger());
        return this.doSeek(context, offset, whence);
    }
    
    private RubyFixnum doSeek(final ThreadContext context, final long offset, final int whence) {
        final OpenFile myOpenFile = this.getOpenFileChecked();
        try {
            myOpenFile.seek(offset, whence);
            myOpenFile.getMainStreamSafe().clearerr();
        }
        catch (BadDescriptorException ex) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException e2) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (PipeException e3) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (IOException e) {
            throw context.getRuntime().newIOErrorFromException(e);
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(name = { "sysseek" }, required = 1, optional = 1)
    public RubyFixnum sysseek(final ThreadContext context, final IRubyObject[] args) {
        final long offset = RubyNumeric.num2long(args[0]);
        int whence = 0;
        if (args.length > 1) {
            whence = RubyNumeric.fix2int(args[1].convertToInteger());
        }
        final OpenFile myOpenFile = this.getOpenFileChecked();
        long pos;
        try {
            if (myOpenFile.isReadable() && myOpenFile.isReadBuffered()) {
                throw context.getRuntime().newIOError("sysseek for buffered IO");
            }
            if (myOpenFile.isWritable() && myOpenFile.isWriteBuffered()) {
                context.getRuntime().getWarnings().warn(IRubyWarnings.ID.SYSSEEK_BUFFERED_IO, "sysseek for buffered IO", new Object[0]);
            }
            pos = myOpenFile.getMainStreamSafe().getDescriptor().lseek(offset, whence);
            myOpenFile.getMainStreamSafe().clearerr();
        }
        catch (BadDescriptorException ex) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException e2) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (PipeException e3) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (IOException e) {
            throw context.getRuntime().newIOErrorFromException(e);
        }
        return context.getRuntime().newFixnum(pos);
    }
    
    @JRubyMethod(name = { "rewind" })
    public RubyFixnum rewind(final ThreadContext context) {
        final OpenFile myOpenfile = this.getOpenFileChecked();
        try {
            myOpenfile.getMainStreamSafe().lseek(0L, 0);
            myOpenfile.getMainStreamSafe().clearerr();
        }
        catch (BadDescriptorException e2) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException e3) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (PipeException e4) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (IOException e) {
            throw context.getRuntime().newIOErrorFromException(e);
        }
        myOpenfile.setLineNumber(0);
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(name = { "fsync" })
    public RubyFixnum fsync(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkWritable(runtime);
            final Stream writeStream = myOpenFile.getWriteStream();
            writeStream.fflush();
            writeStream.sync();
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        return RubyFixnum.zero(runtime);
    }
    
    @JRubyMethod(name = { "sync=" }, required = 1)
    public IRubyObject sync_set(final IRubyObject newSync) {
        try {
            this.getOpenFileChecked().setSync(newSync.isTrue());
            this.getOpenFileChecked().getMainStreamSafe().setSync(newSync.isTrue());
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        return this;
    }
    
    @JRubyMethod(name = { "eof?", "eof" })
    public RubyBoolean eof_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkReadable(runtime);
            myOpenFile.setReadBuffered();
            if (myOpenFile.getMainStreamSafe().feof()) {
                return runtime.getTrue();
            }
            if (myOpenFile.getMainStreamSafe().readDataBuffered()) {
                return runtime.getFalse();
            }
            this.readCheck(myOpenFile.getMainStreamSafe());
            this.waitReadable(myOpenFile.getMainStreamSafe());
            myOpenFile.getMainStreamSafe().clearerr();
            final int c = myOpenFile.getMainStreamSafe().fgetc();
            if (c != -1) {
                myOpenFile.getMainStreamSafe().ungetc(c);
                return runtime.getFalse();
            }
            myOpenFile.checkClosed(runtime);
            myOpenFile.getMainStreamSafe().clearerr();
            return runtime.getTrue();
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    @JRubyMethod(name = { "tty?", "isatty" })
    public RubyBoolean tty_p(final ThreadContext context) {
        try {
            return context.getRuntime().newBoolean(context.getRuntime().getPosix().isatty(this.getOpenFileChecked().getMainStreamSafe().getDescriptor().getFileDescriptor()));
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject original) {
        final Ruby runtime = this.getRuntime();
        if (this == original) {
            return this;
        }
        final RubyIO originalIO = (RubyIO)TypeConverter.convertToTypeWithCheck(original, runtime.getIO(), "to_io");
        final OpenFile originalFile = originalIO.getOpenFileChecked();
        final OpenFile newFile = this.openFile;
        try {
            if (originalFile.getPipeStream() != null) {
                originalFile.getPipeStream().fflush();
                originalFile.getMainStreamSafe().lseek(0L, 1);
            }
            else if (originalFile.isWritable()) {
                originalFile.getMainStreamSafe().fflush();
            }
            else {
                originalFile.getMainStreamSafe().lseek(0L, 1);
            }
            newFile.setMode(originalFile.getMode());
            newFile.setProcess(originalFile.getProcess());
            newFile.setLineNumber(originalFile.getLineNumber());
            newFile.setPath(originalFile.getPath());
            newFile.setFinalizer(originalFile.getFinalizer());
            ModeFlags modes;
            if (newFile.isReadable()) {
                if (newFile.isWritable()) {
                    if (newFile.getPipeStream() != null) {
                        modes = new ModeFlags(ModeFlags.RDONLY);
                    }
                    else {
                        modes = new ModeFlags(ModeFlags.RDWR);
                    }
                }
                else {
                    modes = new ModeFlags(ModeFlags.RDONLY);
                }
            }
            else if (newFile.isWritable()) {
                modes = new ModeFlags(ModeFlags.WRONLY);
            }
            else {
                modes = originalFile.getMainStreamSafe().getModes();
            }
            final ChannelDescriptor descriptor = originalFile.getMainStreamSafe().getDescriptor().dup();
            newFile.setMainStream(ChannelStream.fdopen(runtime, descriptor, modes));
            newFile.getMainStream().setSync(originalFile.getMainStreamSafe().isSync());
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newIOError("could not init copy: " + ex2);
        }
        catch (PipeException ex3) {
            throw runtime.newIOError("could not init copy: " + ex3);
        }
        catch (InvalidValueException ex4) {
            throw runtime.newIOError("could not init copy: " + ex4);
        }
        return this;
    }
    
    @JRubyMethod(name = { "closed?" })
    public RubyBoolean closed_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isClosed());
    }
    
    public boolean isClosed() {
        return this.openFile.getMainStream() == null && this.openFile.getPipeStream() == null;
    }
    
    @JRubyMethod(name = { "close" })
    public IRubyObject close() {
        final Ruby runtime = this.getRuntime();
        if (runtime.getSafeLevel() >= 4 && this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't close");
        }
        this.openFile.checkClosed(runtime);
        return this.close2(runtime);
    }
    
    protected IRubyObject close2(final Ruby runtime) {
        if (this.openFile == null) {
            return runtime.getNil();
        }
        this.interruptBlockingThreads();
        this.openFile.cleanup(runtime, true);
        if (this.openFile.getProcess() != null) {
            obliterateProcess(this.openFile.getProcess());
            final IRubyObject processResult = RubyProcess.RubyStatus.newProcessStatus(runtime, this.openFile.getProcess().exitValue(), this.openFile.getPid());
            runtime.getCurrentContext().setLastExitStatus(processResult);
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "close_write" })
    public IRubyObject close_write(final ThreadContext context) {
        try {
            if (context.getRuntime().getSafeLevel() >= 4 && this.isTaint()) {
                throw context.getRuntime().newSecurityError("Insecure: can't close");
            }
            final OpenFile myOpenFile = this.getOpenFileChecked();
            if (myOpenFile.getPipeStream() == null && myOpenFile.isReadable()) {
                throw context.getRuntime().newIOError("closing non-duplex IO for writing");
            }
            if (myOpenFile.getPipeStream() == null) {
                this.close();
            }
            else {
                myOpenFile.getPipeStream().fclose();
                myOpenFile.setPipeStream(null);
                myOpenFile.setMode(myOpenFile.getMode() & 0xFFFFFFFD);
            }
        }
        catch (BadDescriptorException bde) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (IOException ex) {}
        return this;
    }
    
    @JRubyMethod(name = { "close_read" })
    public IRubyObject close_read(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        try {
            if (runtime.getSafeLevel() >= 4 && this.isTaint()) {
                throw runtime.newSecurityError("Insecure: can't close");
            }
            final OpenFile myOpenFile = this.getOpenFileChecked();
            if (myOpenFile.getPipeStream() == null && myOpenFile.isWritable()) {
                throw runtime.newIOError("closing non-duplex IO for reading");
            }
            if (myOpenFile.getPipeStream() == null) {
                this.close();
            }
            else {
                myOpenFile.getMainStreamSafe().fclose();
                myOpenFile.setMode(myOpenFile.getMode() & 0xFFFFFFFE);
                myOpenFile.setMainStream(myOpenFile.getPipeStream());
                myOpenFile.setPipeStream(null);
            }
        }
        catch (BadDescriptorException bde) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
        return this;
    }
    
    @JRubyMethod(name = { "flush" })
    public RubyIO flush() {
        try {
            this.getOpenFileChecked().getWriteStream().fflush();
        }
        catch (BadDescriptorException e2) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (IOException e) {
            throw this.getRuntime().newIOErrorFromException(e);
        }
        return this;
    }
    
    @JRubyMethod(name = { "gets" }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gets(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject result = this.getline(runtime, this.separator(runtime, runtime.getRecordSeparatorVar().get()));
        if (!result.isNil()) {
            context.getCurrentScope().setLastLine(result);
        }
        return result;
    }
    
    @JRubyMethod(name = { "gets" }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gets(final ThreadContext context, final IRubyObject separatorArg) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject result = this.getline(runtime, this.separator(runtime, separatorArg));
        if (!result.isNil()) {
            context.getCurrentScope().setLastLine(result);
        }
        return result;
    }
    
    @JRubyMethod(name = { "gets" }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gets19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject result = this.getline(runtime, this.separator(runtime));
        if (!result.isNil()) {
            context.getCurrentScope().setLastLine(result);
        }
        return result;
    }
    
    @JRubyMethod(name = { "gets" }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gets19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        long limit = -1L;
        ByteList separator;
        if (arg instanceof RubyInteger) {
            limit = RubyNumeric.fix2long(arg);
            separator = this.separator(runtime);
        }
        else {
            separator = this.separator(runtime, arg);
        }
        final IRubyObject result = this.getline(runtime, separator, limit);
        if (!result.isNil()) {
            context.getCurrentScope().setLastLine(result);
        }
        return result;
    }
    
    @JRubyMethod(name = { "gets" }, writes = { FrameField.LASTLINE }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gets19(final ThreadContext context, final IRubyObject separator, final IRubyObject limit_arg) {
        final Ruby runtime = context.getRuntime();
        final long limit = limit_arg.isNil() ? -1L : RubyNumeric.fix2long(limit_arg);
        final IRubyObject result = this.getline(runtime, this.separator(runtime, separator), limit);
        if (!result.isNil()) {
            context.getCurrentScope().setLastLine(result);
        }
        return result;
    }
    
    public boolean getBlocking() {
        try {
            return ((ChannelStream)this.openFile.getMainStreamSafe()).isBlocking();
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "fcntl" })
    public IRubyObject fcntl(final ThreadContext context, final IRubyObject cmd) {
        return this.ctl(context.getRuntime(), cmd, null);
    }
    
    @JRubyMethod(name = { "fcntl" })
    public IRubyObject fcntl(final ThreadContext context, final IRubyObject cmd, final IRubyObject arg) {
        return this.ctl(context.getRuntime(), cmd, arg);
    }
    
    @JRubyMethod(name = { "ioctl" }, required = 1, optional = 1)
    public IRubyObject ioctl(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject cmd = args[0];
        IRubyObject arg;
        if (args.length == 2) {
            arg = args[1];
        }
        else {
            arg = context.getRuntime().getNil();
        }
        return this.ctl(context.getRuntime(), cmd, arg);
    }
    
    public IRubyObject ctl(final Ruby runtime, final IRubyObject cmd, final IRubyObject arg) {
        final long realCmd = cmd.convertToInteger().getLongValue();
        long nArg = 0L;
        if (arg == null || arg.isNil() || arg == runtime.getFalse()) {
            nArg = 0L;
        }
        else if (arg instanceof RubyFixnum) {
            nArg = RubyNumeric.fix2long(arg);
        }
        else {
            if (arg != runtime.getTrue()) {
                throw runtime.newNotImplementedError("JRuby does not support string for second fcntl/ioctl argument yet");
            }
            nArg = 1L;
        }
        final OpenFile myOpenFile = this.getOpenFileChecked();
        try {
            if (realCmd != 1L) {
                if (realCmd == Fcntl.F_SETFL.value() || realCmd == Fcntl.F_SETFD.value()) {
                    if ((nArg & 0x1L) != 0x1L) {
                        final boolean block = (nArg & ModeFlags.NONBLOCK) != ModeFlags.NONBLOCK;
                        myOpenFile.getMainStreamSafe().setBlocking(block);
                    }
                }
                else {
                    if (realCmd == Fcntl.F_GETFL.value()) {
                        return myOpenFile.getMainStreamSafe().isBlocking() ? RubyFixnum.zero(runtime) : RubyFixnum.newFixnum(runtime, ModeFlags.NONBLOCK);
                    }
                    throw runtime.newNotImplementedError("JRuby only supports F_SETFL and F_GETFL with NONBLOCK for fcntl/ioctl");
                }
            }
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        return runtime.newFixnum(0);
    }
    
    @JRubyMethod(name = { "puts" }, rest = true)
    public IRubyObject puts(final ThreadContext context, final IRubyObject[] args) {
        return puts(context, this, args);
    }
    
    public static IRubyObject puts(final ThreadContext context, final IRubyObject maybeIO, final IRubyObject[] args) {
        if (args.length == 0) {
            return writeSeparator(context, maybeIO);
        }
        return putsArray(context, maybeIO, args);
    }
    
    private static IRubyObject writeSeparator(final ThreadContext context, final IRubyObject maybeIO) {
        final Ruby runtime = context.getRuntime();
        assert runtime.getGlobalVariables().getDefaultSeparator() instanceof RubyString;
        final RubyString separator = (RubyString)runtime.getGlobalVariables().getDefaultSeparator();
        write(context, maybeIO, separator.getByteList());
        return runtime.getNil();
    }
    
    private static IRubyObject putsArray(final ThreadContext context, final IRubyObject maybeIO, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        assert runtime.getGlobalVariables().getDefaultSeparator() instanceof RubyString;
        final RubyString separator = (RubyString)runtime.getGlobalVariables().getDefaultSeparator();
        for (int i = 0; i < args.length; ++i) {
            ByteList line;
            if (args[i].isNil()) {
                line = getNilByteList(runtime);
            }
            else if (runtime.isInspecting(args[i])) {
                line = RubyIO.RECURSIVE_BYTELIST;
            }
            else {
                if (args[i] instanceof RubyArray) {
                    inspectPuts(context, maybeIO, (RubyArray)args[i]);
                    continue;
                }
                line = args[i].asString().getByteList();
            }
            write(context, maybeIO, line);
            if (line.length() == 0 || !line.endsWith(separator.getByteList())) {
                write(context, maybeIO, separator.getByteList());
            }
        }
        return runtime.getNil();
    }
    
    protected void write(final ThreadContext context, final ByteList byteList) {
        this.callMethod(context, "write", RubyString.newStringShared(context.getRuntime(), byteList));
    }
    
    protected static void write(final ThreadContext context, final IRubyObject maybeIO, final ByteList byteList) {
        maybeIO.callMethod(context, "write", RubyString.newStringShared(context.getRuntime(), byteList));
    }
    
    private static IRubyObject inspectPuts(final ThreadContext context, final IRubyObject maybeIO, final RubyArray array) {
        try {
            context.getRuntime().registerInspecting(array);
            return putsArray(context, maybeIO, array.toJavaArray());
        }
        finally {
            context.getRuntime().unregisterInspecting(array);
        }
    }
    
    @JRubyMethod(name = { "readline" }, writes = { FrameField.LASTLINE })
    public IRubyObject readline(final ThreadContext context) {
        final IRubyObject line = this.gets(context);
        if (line.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return line;
    }
    
    @JRubyMethod(name = { "readline" }, writes = { FrameField.LASTLINE })
    public IRubyObject readline(final ThreadContext context, final IRubyObject separator) {
        final IRubyObject line = this.gets(context, separator);
        if (line.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return line;
    }
    
    @JRubyMethod(name = { "getc", "getbyte" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject getc() {
        final int c = this.getcCommon();
        if (c == -1) {
            return this.getRuntime().getNil();
        }
        return this.getRuntime().newFixnum(c);
    }
    
    private ByteList fromEncodedBytes(final Ruby runtime, final Encoding enc, final int value) {
        int n;
        try {
            n = ((value < 0) ? 0 : enc.codeToMbcLength(value));
        }
        catch (EncodingException ee) {
            n = 0;
        }
        if (n <= 0) {
            throw runtime.newRangeError(this.toString() + " out of char range");
        }
        final ByteList bytes = new ByteList(n);
        enc.codeToMbc(value, bytes.getUnsafeBytes(), 0);
        bytes.setRealSize(n);
        return bytes;
    }
    
    @JRubyMethod(name = { "readchar" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject readchar19(final ThreadContext context) {
        final IRubyObject value = this.getc19(context);
        if (value.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return value;
    }
    
    @JRubyMethod(name = { "getbyte" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getbyte19(final ThreadContext context) {
        return this.getc();
    }
    
    @JRubyMethod(name = { "getc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getc19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final int c = this.getcCommon();
        if (c == -1) {
            return runtime.getNil();
        }
        final Encoding external = this.getExternalEncoding(runtime);
        ByteList bytes = this.fromEncodedBytes(runtime, external, c);
        final Encoding internal = this.getInternalEncoding(runtime);
        if (internal != null) {
            bytes = RubyString.transcode(context, bytes, external, internal, runtime.getNil());
        }
        return RubyString.newStringNoCopy(runtime, bytes, external, 0);
    }
    
    public int getcCommon() {
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkReadable(this.getRuntime());
            myOpenFile.setReadBuffered();
            final Stream stream = myOpenFile.getMainStreamSafe();
            this.readCheck(stream);
            this.waitReadable(stream);
            stream.clearerr();
            return myOpenFile.getMainStreamSafe().fgetc();
        }
        catch (InvalidValueException ex) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (BadDescriptorException e2) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (EOFException e3) {
            throw this.getRuntime().newEOFError();
        }
        catch (IOException e) {
            throw this.getRuntime().newIOErrorFromException(e);
        }
    }
    
    private void readCheck(final Stream stream) {
        if (!stream.readDataBuffered()) {
            this.openFile.checkClosed(this.getRuntime());
        }
    }
    
    @JRubyMethod(name = { "ungetc" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject ungetc(final IRubyObject number) {
        final OpenFile myOpenFile = this.getOpenFileChecked();
        if (!myOpenFile.isReadBuffered()) {
            throw this.getRuntime().newIOError("unread stream");
        }
        return this.ungetcCommon(number, myOpenFile);
    }
    
    @JRubyMethod(name = { "ungetc" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject ungetc19(IRubyObject number) {
        final Ruby runtime = this.getRuntime();
        final OpenFile myOpenFile = this.getOpenFileChecked();
        if (!myOpenFile.isReadBuffered()) {
            return runtime.getNil();
        }
        if (number instanceof RubyString) {
            final RubyString str = (RubyString)number;
            if (str.isEmpty()) {
                return runtime.getNil();
            }
            final int c = str.getEncoding().mbcToCode(str.getBytes(), 0, 1);
            number = runtime.newFixnum(c);
        }
        return this.ungetcCommon(number, myOpenFile);
    }
    
    private IRubyObject ungetcCommon(final IRubyObject number, final OpenFile myOpenFile) {
        final int ch = RubyNumeric.fix2int(number);
        try {
            myOpenFile.checkReadable(this.getRuntime());
            myOpenFile.setReadBuffered();
            if (myOpenFile.getMainStreamSafe().ungetc(ch) == -1 && ch != -1) {
                throw this.getRuntime().newIOError("ungetc failed");
            }
        }
        catch (InvalidValueException ex) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (BadDescriptorException e2) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (EOFException e3) {
            throw this.getRuntime().newEOFError();
        }
        catch (IOException e) {
            throw this.getRuntime().newIOErrorFromException(e);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "read_nonblock" }, required = 1, optional = 1, backtrace = true)
    public IRubyObject read_nonblock(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject value = this.getPartial(context, args, true);
        if (value.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        if (value instanceof RubyString) {
            final RubyString str = (RubyString)value;
            if (str.isEmpty()) {
                final Ruby ruby = context.getRuntime();
                final RaiseException eagain = ruby.newErrnoEAGAINError("");
                if (ruby.is1_9()) {
                    eagain.getException().extend(new IRubyObject[] { ruby.getIO().getConstant("WaitReadable") });
                }
                throw eagain;
            }
        }
        return value;
    }
    
    @JRubyMethod(name = { "readpartial" }, required = 1, optional = 1)
    public IRubyObject readpartial(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject value = this.getPartial(context, args, false);
        if (value.isNil()) {
            throw context.getRuntime().newEOFError();
        }
        return value;
    }
    
    private IRubyObject getPartial(final ThreadContext context, final IRubyObject[] args, final boolean isNonblocking) {
        final Ruby runtime = context.getRuntime();
        final int length = RubyNumeric.fix2int(args[0]);
        if (length < 0) {
            throw runtime.newArgumentError("negative length " + length + " given");
        }
        final IRubyObject stringArg = (args.length > 1) ? args[1] : runtime.getNil();
        final RubyString string = stringArg.isNil() ? RubyString.newEmptyString(runtime) : stringArg.convertToString();
        string.empty();
        string.setTaint(true);
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkReadable(runtime);
            if (length == 0) {
                return string;
            }
            if (!(myOpenFile.getMainStreamSafe() instanceof ChannelStream)) {
                throw runtime.newNotImplementedError("readpartial only works with Nio based handlers");
            }
            final ChannelStream stream = (ChannelStream)myOpenFile.getMainStreamSafe();
            ByteList buf = null;
            if (isNonblocking) {
                buf = stream.readnonblock(length);
            }
            else {
                while ((buf == null || buf.length() == 0) && !stream.feof()) {
                    this.waitReadable(stream);
                    buf = stream.readpartial(length);
                }
            }
            final boolean empty = buf == null || buf.length() == 0;
            final ByteList newBuf = empty ? ByteList.EMPTY_BYTELIST.dup() : buf;
            string.view(newBuf);
            if (stream.feof() && empty) {
                return runtime.getNil();
            }
            return string;
        }
        catch (BadDescriptorException e3) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException e4) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (EOFException e) {
            throw runtime.newEOFError(e.getMessage());
        }
        catch (IOException e2) {
            throw runtime.newIOErrorFromException(e2);
        }
    }
    
    @JRubyMethod(name = { "sysread" }, required = 1, optional = 1)
    public IRubyObject sysread(final ThreadContext context, final IRubyObject[] args) {
        final int len = (int)RubyNumeric.num2long(args[0]);
        if (len < 0) {
            throw this.getRuntime().newArgumentError("Negative size");
        }
        try {
            RubyString str;
            if (args.length == 1 || args[1].isNil()) {
                if (len == 0) {
                    return RubyString.newEmptyString(this.getRuntime());
                }
                final ByteList buffer = new ByteList(len);
                str = RubyString.newString(this.getRuntime(), buffer);
            }
            else {
                str = args[1].convertToString();
                str.modify(len);
                if (len == 0) {
                    return str;
                }
                final ByteList buffer = str.getByteList();
                buffer.length(0);
            }
            final OpenFile myOpenFile = this.getOpenFileChecked();
            myOpenFile.checkReadable(this.getRuntime());
            if (myOpenFile.getMainStreamSafe().readDataBuffered()) {
                throw this.getRuntime().newIOError("sysread for buffered IO");
            }
            this.waitReadable(myOpenFile.getMainStreamSafe());
            myOpenFile.checkClosed(this.getRuntime());
            final int bytesRead = myOpenFile.getMainStreamSafe().getDescriptor().read(len, str.getByteList());
            if (bytesRead == -1 || (bytesRead == 0 && len > 0)) {
                throw this.getRuntime().newEOFError();
            }
            str.setTaint(true);
            return str;
        }
        catch (BadDescriptorException e2) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (InvalidValueException e3) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (EOFException e4) {
            throw this.getRuntime().newEOFError();
        }
        catch (IOException e) {
            this.synthesizeSystemCallError(e);
            return null;
        }
    }
    
    private void synthesizeSystemCallError(final IOException e) {
        final String errorMessage = e.getMessage();
        if ("File not open".equals(errorMessage)) {
            throw this.getRuntime().newIOError(e.getMessage());
        }
        if ("An established connection was aborted by the software in your host machine".equals(errorMessage)) {
            throw this.getRuntime().newErrnoECONNABORTEDError();
        }
        throw this.getRuntime().newSystemCallError(e.getMessage());
    }
    
    public IRubyObject read(final IRubyObject[] args) {
        final ThreadContext context = this.getRuntime().getCurrentContext();
        switch (args.length) {
            case 0: {
                return this.read(context);
            }
            case 1: {
                return this.read(context, args[0]);
            }
            case 2: {
                return this.read(context, args[0], args[1]);
            }
            default: {
                throw this.getRuntime().newArgumentError(args.length, 2);
            }
        }
    }
    
    @JRubyMethod(name = { "read" })
    public IRubyObject read(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final OpenFile myOpenFile = this.getOpenFileChecked();
        try {
            myOpenFile.checkReadable(runtime);
            myOpenFile.setReadBuffered();
            return this.readAll();
        }
        catch (InvalidValueException ex2) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (EOFException ex3) {
            throw this.getRuntime().newEOFError();
        }
        catch (IOException ex) {
            throw this.getRuntime().newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex4) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(name = { "read" })
    public IRubyObject read(final ThreadContext context, final IRubyObject arg0) {
        if (arg0.isNil()) {
            return this.read(context);
        }
        final OpenFile myOpenFile = this.getOpenFileChecked();
        final int length = RubyNumeric.num2int(arg0);
        if (length < 0) {
            throw this.getRuntime().newArgumentError("negative length " + length + " given");
        }
        final RubyString str = RubyString.newEmptyString(this.getRuntime());
        return this.readNotAll(context, myOpenFile, length, str);
    }
    
    @JRubyMethod(name = { "read" })
    public IRubyObject read(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final OpenFile myOpenFile = this.getOpenFileChecked();
        if (arg0.isNil()) {
            try {
                myOpenFile.checkReadable(this.getRuntime());
                myOpenFile.setReadBuffered();
                if (arg1.isNil()) {
                    return this.readAll();
                }
                return this.readAll(arg1.convertToString());
            }
            catch (InvalidValueException ex2) {
                throw this.getRuntime().newErrnoEINVALError();
            }
            catch (EOFException ex3) {
                throw this.getRuntime().newEOFError();
            }
            catch (IOException ex) {
                throw this.getRuntime().newIOErrorFromException(ex);
            }
            catch (BadDescriptorException ex4) {
                throw this.getRuntime().newErrnoEBADFError();
            }
        }
        final int length = RubyNumeric.num2int(arg0);
        if (length < 0) {
            throw this.getRuntime().newArgumentError("negative length " + length + " given");
        }
        if (arg1.isNil()) {
            return this.readNotAll(context, myOpenFile, length);
        }
        return this.readNotAll(context, myOpenFile, length, arg1.convertToString());
    }
    
    private IRubyObject readNotAll(final ThreadContext context, final OpenFile myOpenFile, final int length, final RubyString str) {
        final Ruby runtime = context.getRuntime();
        str.empty();
        try {
            final ByteList newBuffer = this.readNotAllCommon(context, myOpenFile, length);
            if (emptyBufferOrEOF(newBuffer, myOpenFile)) {
                return runtime.getNil();
            }
            str.setValue(newBuffer);
            str.setTaint(true);
            return str;
        }
        catch (EOFException ex2) {
            throw runtime.newEOFError();
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex3) {
            throw runtime.newErrnoEBADFError();
        }
    }
    
    private IRubyObject readNotAll(final ThreadContext context, final OpenFile myOpenFile, final int length) {
        final Ruby runtime = context.getRuntime();
        try {
            final ByteList newBuffer = this.readNotAllCommon(context, myOpenFile, length);
            if (emptyBufferOrEOF(newBuffer, myOpenFile)) {
                return runtime.getNil();
            }
            final RubyString str = RubyString.newString(runtime, newBuffer);
            str.setTaint(true);
            return str;
        }
        catch (EOFException ex2) {
            throw runtime.newEOFError();
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex3) {
            throw runtime.newErrnoEBADFError();
        }
    }
    
    private ByteList readNotAllCommon(final ThreadContext context, final OpenFile myOpenFile, final int length) {
        final Ruby runtime = context.getRuntime();
        try {
            myOpenFile.checkReadable(runtime);
            myOpenFile.setReadBuffered();
            if (myOpenFile.getMainStreamSafe().feof()) {
                return null;
            }
            this.readCheck(myOpenFile.getMainStreamSafe());
            final ByteList newBuffer = this.fread(context.getThread(), length);
            return newBuffer;
        }
        catch (EOFException ex2) {
            throw runtime.newEOFError();
        }
        catch (InvalidValueException ex3) {
            throw runtime.newErrnoEINVALError();
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex4) {
            throw runtime.newErrnoEBADFError();
        }
    }
    
    protected static boolean emptyBufferOrEOF(final ByteList buffer, final OpenFile myOpenFile) throws BadDescriptorException, IOException {
        if (buffer == null) {
            return true;
        }
        if (buffer.length() == 0) {
            if (myOpenFile.getMainStreamSafe() == null) {
                return true;
            }
            if (myOpenFile.getMainStreamSafe().feof()) {
                return true;
            }
        }
        return false;
    }
    
    protected RubyString readAll(final RubyString str) throws BadDescriptorException, EOFException, IOException {
        final Ruby runtime = this.getRuntime();
        final ByteList buf = this.readAllCommon(runtime);
        if (buf == null) {
            str.empty();
        }
        else {
            str.setValue(buf);
        }
        str.setTaint(true);
        return str;
    }
    
    protected RubyString readAll() throws BadDescriptorException, EOFException, IOException {
        final Ruby runtime = this.getRuntime();
        final ByteList buf = this.readAllCommon(runtime);
        RubyString str;
        if (buf == null) {
            str = RubyString.newEmptyString(runtime);
        }
        else {
            str = RubyString.newString(runtime, buf);
        }
        str.setTaint(true);
        return str;
    }
    
    protected ByteList readAllCommon(final Ruby runtime) throws BadDescriptorException, EOFException, IOException {
        ByteList buf = null;
        final ChannelDescriptor descriptor = this.openFile.getMainStreamSafe().getDescriptor();
        try {
            if (descriptor.isSeekable() && descriptor.getChannel() instanceof FileChannel) {
                buf = this.openFile.getMainStreamSafe().readall();
            }
            else if (descriptor == null) {
                buf = null;
            }
            else {
                final RubyThread thread = runtime.getCurrentContext().getThread();
                try {
                    while (true) {
                        final Stream stream = this.openFile.getMainStreamSafe();
                        this.readCheck(stream);
                        this.openFile.checkReadable(runtime);
                        final ByteList read = this.fread(thread, 4096);
                        if (read.length() == 0) {
                            break;
                        }
                        if (buf == null) {
                            buf = read;
                        }
                        else {
                            buf.append(read);
                        }
                    }
                }
                catch (InvalidValueException ex) {
                    throw runtime.newErrnoEINVALError();
                }
            }
        }
        catch (NonReadableChannelException ex2) {
            throw runtime.newIOError("not opened for reading");
        }
        return buf;
    }
    
    private ByteList fread(final RubyThread thread, final int length) throws IOException, BadDescriptorException {
        final Stream stream = this.openFile.getMainStreamSafe();
        int rest = length;
        this.waitReadable(stream);
        ByteList buf = this.blockingFRead(stream, thread, length);
        if (buf != null) {
            rest -= buf.length();
        }
        while (rest > 0) {
            this.waitReadable(stream);
            this.openFile.checkClosed(this.getRuntime());
            stream.clearerr();
            final ByteList newBuffer = this.blockingFRead(stream, thread, rest);
            if (newBuffer == null) {
                break;
            }
            final int len = newBuffer.length();
            if (len == 0) {
                continue;
            }
            if (buf == null) {
                buf = newBuffer;
            }
            else {
                buf.append(newBuffer);
            }
            rest -= len;
        }
        if (buf == null) {
            return ByteList.EMPTY_BYTELIST.dup();
        }
        return buf;
    }
    
    private ByteList blockingFRead(final Stream stream, final RubyThread thread, final int length) throws IOException, BadDescriptorException {
        try {
            thread.beforeBlockingCall();
            return stream.fread(length);
        }
        finally {
            thread.afterBlockingCall();
        }
    }
    
    @JRubyMethod(name = { "readchar" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject readchar() {
        final IRubyObject c = this.getc();
        if (c.isNil()) {
            throw this.getRuntime().newEOFError();
        }
        return c;
    }
    
    @JRubyMethod
    public IRubyObject stat(final ThreadContext context) {
        this.openFile.checkClosed(context.getRuntime());
        try {
            return context.getRuntime().newFileStat(this.getOpenFileChecked().getMainStreamSafe().getDescriptor().getFileDescriptor());
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    public IRubyObject each_byteInternal(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            final OpenFile myOpenFile = this.getOpenFileChecked();
            while (true) {
                myOpenFile.checkReadable(runtime);
                myOpenFile.setReadBuffered();
                this.waitReadable(myOpenFile.getMainStream());
                final int c = myOpenFile.getMainStreamSafe().fgetc();
                if (c == -1) {
                    return this;
                }
                assert c < 256;
                block.yield(context, this.getRuntime().newFixnum(c));
            }
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (EOFException e3) {
            return runtime.getNil();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    @JRubyMethod
    public IRubyObject each_byte(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_byteInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_byte");
    }
    
    @JRubyMethod(name = { "bytes" })
    public IRubyObject bytes(final ThreadContext context) {
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_byte");
    }
    
    @JRubyMethod(name = { "lines" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject lines(final ThreadContext context, final Block block) {
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line");
    }
    
    @JRubyMethod(name = { "lines" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject lines19(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line");
        }
        return this.each_lineInternal(context, RubyIO.NULL_ARRAY, block);
    }
    
    public IRubyObject each_charInternal(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        IRubyObject ch;
        while (!(ch = this.getc()).isNil()) {
            byte c = (byte)RubyNumeric.fix2int(ch);
            int n = runtime.getKCode().getEncoding().length(c);
            final RubyString str = runtime.newString();
            if (runtime.is1_9()) {
                str.setEncoding(this.getExternalEncoding(runtime));
            }
            str.setTaint(true);
            str.cat(c);
            while (--n > 0) {
                if ((ch = this.getc()).isNil()) {
                    block.yield(context, str);
                    return this;
                }
                c = (byte)RubyNumeric.fix2int(ch);
                str.cat(c);
            }
            block.yield(context, str);
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
    
    @JRubyMethod
    public IRubyObject codepoints(final ThreadContext context, final Block block) {
        return this.eachCodePointCommon(context, block, "codepoints");
    }
    
    @JRubyMethod
    public IRubyObject each_codepoint(final ThreadContext context, final Block block) {
        return this.eachCodePointCommon(context, block, "each_codepoint");
    }
    
    private IRubyObject eachCharCommon(final ThreadContext context, final Block block, final String methodName) {
        return block.isGiven() ? this.each_char(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, methodName);
    }
    
    private IRubyObject eachCodePointCommon(final ThreadContext context, final Block block, final String methodName) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, methodName);
        }
        IRubyObject ch;
        while (!(ch = this.getc()).isNil()) {
            block.yield(context, ch);
        }
        return this;
    }
    
    public RubyIO each_lineInternal(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final ByteList separator = this.getSeparatorForGets(runtime, args);
        final ByteListCache cache = new ByteListCache();
        for (IRubyObject line = this.getline(runtime, separator); !line.isNil(); line = this.getline(runtime, separator, cache)) {
            block.yield(context, line);
        }
        return this;
    }
    
    @JRubyMethod(optional = 1)
    public IRubyObject each(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? this.each_lineInternal(context, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each", args);
    }
    
    @JRubyMethod(optional = 1)
    public IRubyObject each_line(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? this.each_lineInternal(context, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line", args);
    }
    
    @JRubyMethod(optional = 1)
    public RubyArray readlines(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] array;
        if (args.length > 0) {
            array = new IRubyObject[] { args[0] };
        }
        else {
            final IRubyObject[] null_ARRAY = IRubyObject.NULL_ARRAY;
        }
        final IRubyObject[] separatorArgs = array;
        final ByteList separator = this.getSeparatorForGets(runtime, separatorArgs);
        final RubyArray result = runtime.newArray();
        IRubyObject line;
        while (!(line = this.getline(runtime, separator)).isNil()) {
            result.append(line);
        }
        return result;
    }
    
    @JRubyMethod(name = { "to_io" })
    public RubyIO to_io() {
        return this;
    }
    
    public String toString() {
        try {
            return "RubyIO(" + this.openFile.getMode() + ", " + this.getRuntime().getFileno(this.openFile.getMainStreamSafe().getDescriptor()) + ")";
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    public static IRubyObject foreachInternal(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject filename = args[0].convertToString();
        runtime.checkSafeString(filename);
        final RubyIO io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename }, Block.NULL_BLOCK);
        final ByteListCache cache = new ByteListCache();
        if (!io.isNil()) {
            try {
                ByteList separator = io.getSeparatorFromArgs(runtime, args, 1);
                IRubyObject str = io.getline(runtime, separator, cache);
                while (!str.isNil()) {
                    block.yield(context, str);
                    str = io.getline(runtime, separator, cache);
                    if (runtime.is1_9()) {
                        separator = io.getSeparatorFromArgs(runtime, args, 1);
                    }
                }
            }
            finally {
                io.close();
            }
        }
        return runtime.getNil();
    }
    
    public static IRubyObject foreachInternal19(final ThreadContext context, final IRubyObject recv, IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject filename = args[0].convertToString();
        runtime.checkSafeString(filename);
        final boolean hasOptions = false;
        RubyIO io = null;
        switch (args.length) {
            case 0: {
                io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename }, Block.NULL_BLOCK);
                break;
            }
            case 1: {
                if (args[1] instanceof RubyHash) {
                    io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename, args[1] }, Block.NULL_BLOCK);
                    args = new IRubyObject[] { args[0] };
                    break;
                }
                io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename }, Block.NULL_BLOCK);
                break;
            }
            case 2: {
                if (args[1] instanceof RubyHash) {
                    io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename, args[2] }, Block.NULL_BLOCK);
                    args = new IRubyObject[] { args[0], args[1] };
                    break;
                }
                io = (RubyIO)open(context, runtime.getFile(), new IRubyObject[] { filename }, Block.NULL_BLOCK);
                break;
            }
            default: {
                Arity.checkArgumentCount(runtime, args.length, 1, 3);
                throw runtime.newRuntimeError("invalid argument count in IO.foreach: " + args.length);
            }
        }
        final ByteListCache cache = new ByteListCache();
        if (!io.isNil()) {
            try {
                ByteList separator = io.getSeparatorFromArgs(runtime, args, 1);
                IRubyObject str = io.getline(runtime, separator, cache);
                while (!str.isNil()) {
                    block.yield(context, str);
                    str = io.getline(runtime, separator, cache);
                    if (runtime.is1_9()) {
                        separator = io.getSeparatorFromArgs(runtime, args, 1);
                    }
                }
            }
            finally {
                io.close();
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject foreach(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), recv, "foreach", args);
        }
        if (!(args[0] instanceof RubyString) && args[0].respondsTo("to_path")) {
            args[0] = args[0].callMethod(context, "to_path");
        }
        return foreachInternal(context, recv, args, block);
    }
    
    @JRubyMethod(name = { "foreach" }, required = 1, optional = 2, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject foreach19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), recv, "foreach", args);
        }
        if (!(args[0] instanceof RubyString) && args[0].respondsTo("to_path")) {
            args[0] = args[0].callMethod(context, "to_path");
        }
        return foreachInternal19(context, recv, args, block);
    }
    
    public static RubyIO convertToIO(final ThreadContext context, final IRubyObject obj) {
        if (obj instanceof RubyIO) {
            return (RubyIO)obj;
        }
        return (RubyIO)TypeConverter.convertToType(obj, context.getRuntime().getIO(), "to_io");
    }
    
    @JRubyMethod(name = { "select" }, required = 1, optional = 3, meta = true)
    public static IRubyObject select(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return select_static(context, context.getRuntime(), args);
    }
    
    public static IRubyObject select_static(final ThreadContext context, final Ruby runtime, final IRubyObject[] args) {
        return new SelectBlob().goForIt(context, runtime, args);
    }
    
    public static IRubyObject read(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                throw context.getRuntime().newArgumentError(0, 1);
            }
            case 1: {
                return readStatic(context, recv, args[0]);
            }
            case 2: {
                return readStatic(context, recv, args[0], args[1]);
            }
            case 3: {
                return readStatic(context, recv, args[0], args[1], args[2]);
            }
            default: {
                throw context.getRuntime().newArgumentError(args.length, 3);
            }
        }
    }
    
    private static RubyIO newFile(final ThreadContext context, final IRubyObject recv, final IRubyObject... args) {
        return (RubyIO)RubyKernel.open(context, recv, args, Block.NULL_BLOCK);
    }
    
    public static void failIfDirectory(final Ruby runtime, final RubyString pathStr) {
        if (!RubyFileTest.directory_p(runtime, pathStr).isTrue()) {
            return;
        }
        if (Platform.IS_WINDOWS) {
            throw runtime.newErrnoEACCESError(pathStr.asJavaString());
        }
        throw runtime.newErrnoEISDirError(pathStr.asJavaString());
    }
    
    @Deprecated
    public static IRubyObject read(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final Block unusedBlock) {
        return readStatic(context, recv, path);
    }
    
    @Deprecated
    public static IRubyObject read(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final IRubyObject length) {
        return readStatic(context, recv, path, length);
    }
    
    @Deprecated
    public static IRubyObject read(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final IRubyObject length, final IRubyObject offset) {
        return readStatic(context, recv, path, length, offset);
    }
    
    @JRubyMethod(name = { "read" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject readStatic(final ThreadContext context, final IRubyObject recv, final IRubyObject path) {
        Util.checkStringSafety(context.getRuntime(), path);
        final RubyString pathStr = path.convertToString();
        final Ruby runtime = context.getRuntime();
        failIfDirectory(runtime, pathStr);
        final RubyIO file = newFile(context, recv, pathStr);
        try {
            return file.read(context);
        }
        finally {
            file.close();
        }
    }
    
    @JRubyMethod(name = { "read" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject readStatic(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final IRubyObject length) {
        Util.checkStringSafety(context.getRuntime(), path);
        final RubyString pathStr = path.convertToString();
        final Ruby runtime = context.getRuntime();
        failIfDirectory(runtime, pathStr);
        final RubyIO file = newFile(context, recv, pathStr);
        try {
            return length.isNil() ? file.read(context) : file.read(context, length);
        }
        finally {
            file.close();
        }
    }
    
    @JRubyMethod(name = { "read" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject readStatic(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final IRubyObject length, final IRubyObject offset) {
        Util.checkStringSafety(context.getRuntime(), path);
        final RubyString pathStr = path.convertToString();
        final Ruby runtime = context.getRuntime();
        failIfDirectory(runtime, pathStr);
        final RubyIO file = newFile(context, recv, pathStr);
        try {
            if (!offset.isNil()) {
                file.seek(context, offset);
            }
            return length.isNil() ? file.read(context) : file.read(context, length);
        }
        finally {
            file.close();
        }
    }
    
    private static IRubyObject read19(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final IRubyObject length, final IRubyObject offset, final RubyHash options) {
        final RubyString pathStr = RubyFile.get_path(context, path);
        final Ruby runtime = context.getRuntime();
        failIfDirectory(runtime, pathStr);
        final RubyIO file = newFile(context, recv, pathStr);
        try {
            if (!offset.isNil()) {
                file.seek(context, offset);
            }
            return length.isNil() ? file.read(context) : file.read(context, length);
        }
        finally {
            file.close();
        }
    }
    
    @JRubyMethod(meta = true, required = 1, optional = 2, compat = CompatVersion.RUBY1_9)
    public static IRubyObject binread(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject nil = context.getRuntime().getNil();
        final IRubyObject path = args[0];
        IRubyObject length = nil;
        IRubyObject offset = nil;
        final Ruby runtime = context.runtime;
        if (args.length > 2) {
            offset = args[2];
            length = args[1];
        }
        else if (args.length > 1) {
            length = args[1];
        }
        final RubyIO file = (RubyIO)RuntimeHelpers.invoke(context, runtime.getFile(), "new", path, runtime.newString("rb:ASCII-8BIT"));
        try {
            if (!offset.isNil()) {
                file.seek(context, offset);
            }
            return length.isNil() ? file.read(context) : file.read(context, length);
        }
        finally {
            file.close();
        }
    }
    
    @JRubyMethod(name = { "read" }, meta = true, required = 1, optional = 3, compat = CompatVersion.RUBY1_9)
    public static IRubyObject read19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        final IRubyObject nil = context.getRuntime().getNil();
        final IRubyObject path = args[0];
        IRubyObject length = nil;
        IRubyObject offset = nil;
        RubyHash options = null;
        if (args.length > 3) {
            if (!(args[3] instanceof RubyHash)) {
                throw context.getRuntime().newTypeError("Must be a hash");
            }
            options = (RubyHash)args[3];
            offset = args[2];
            length = args[1];
        }
        else if (args.length > 2) {
            if (args[2] instanceof RubyHash) {
                options = (RubyHash)args[2];
            }
            else {
                offset = args[2];
            }
            length = args[1];
        }
        else if (args.length > 1) {
            if (args[1] instanceof RubyHash) {
                options = (RubyHash)args[1];
            }
            else {
                length = args[1];
            }
        }
        return read19(context, recv, path, length, offset, options);
    }
    
    @JRubyMethod(name = { "readlines" }, required = 1, optional = 1, meta = true)
    public static RubyArray readlines(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        final int count = args.length;
        final IRubyObject[] fileArguments = { args[0].convertToString() };
        final IRubyObject[] array;
        if (count >= 2) {
            array = new IRubyObject[] { args[1] };
        }
        else {
            final IRubyObject[] null_ARRAY = IRubyObject.NULL_ARRAY;
        }
        final IRubyObject[] separatorArguments = array;
        final RubyIO file = (RubyIO)RubyKernel.open(context, recv, fileArguments, Block.NULL_BLOCK);
        try {
            return file.readlines(context, separatorArguments);
        }
        finally {
            file.close();
        }
    }
    
    @JRubyMethod(name = { "popen" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject popen(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        IRubyObject cmdObj = null;
        if (Platform.IS_WINDOWS) {
            final String[] tokens = args[0].convertToString().toString().split(" ", 2);
            final String commandString = tokens[0].replace('/', '\\') + ((tokens.length > 1) ? (' ' + tokens[1]) : "");
            cmdObj = runtime.newString(commandString);
        }
        else {
            cmdObj = args[0].convertToString();
        }
        runtime.checkSafeString(cmdObj);
        if ("-".equals(cmdObj.toString())) {
            throw runtime.newNotImplementedError("popen(\"-\") is unimplemented");
        }
        try {
            int mode;
            if (args.length == 1) {
                mode = ModeFlags.RDONLY;
            }
            else if (args[1] instanceof RubyFixnum) {
                mode = RubyNumeric.num2int(args[1]);
            }
            else {
                mode = getIOModesIntFromString(runtime, args[1].convertToString().toString());
            }
            final ModeFlags modes = new ModeFlags(mode);
            final ShellLauncher.POpenProcess process = ShellLauncher.popen(runtime, cmdObj, modes);
            if (System.getProperty("java.specification.version", "").equals("1.5")) {
                synchronized (process) {
                    try {
                        process.wait(100L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            final RubyIO io = new RubyIO(runtime, process, modes);
            if (block.isGiven()) {
                try {
                    return block.yield(context, io);
                }
                finally {
                    if (io.openFile.isOpen()) {
                        io.close();
                    }
                    context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, process.waitFor(), ShellLauncher.getPidFromProcess(process)));
                }
            }
            return io;
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        catch (InterruptedException e2) {
            throw runtime.newThreadError("unexpected interrupt");
        }
    }
    
    @JRubyMethod(name = { "popen" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject popen19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        IRubyObject[] cmdPlusArgs = null;
        RubyHash env = null;
        final RubyHash opts = null;
        IRubyObject cmdObj = null;
        final IRubyObject arg0 = args[0].checkArrayType();
        if (!arg0.isNil()) {
            final List argList = new ArrayList(Arrays.asList(((RubyArray)arg0).toJavaArray()));
            if (argList.isEmpty()) {
                throw runtime.newArgumentError("wrong number of arguments");
            }
            if (argList.get(0) instanceof RubyHash) {
                env = argList.remove(0);
            }
            if (argList.isEmpty()) {
                throw runtime.newArgumentError("wrong number of arguments");
            }
            if (argList.size() > 1 && argList.get(argList.size() - 1) instanceof RubyHash) {
                env = argList.get(argList.size() - 1);
            }
            cmdPlusArgs = argList.toArray(new IRubyObject[argList.size()]);
            if (Platform.IS_WINDOWS) {
                final String commandString = cmdPlusArgs[0].convertToString().toString().replace('/', '\\');
                cmdPlusArgs[0] = runtime.newString(commandString);
            }
            else {
                cmdPlusArgs[0] = cmdPlusArgs[0].convertToString();
            }
            cmdObj = cmdPlusArgs[0];
        }
        else if (Platform.IS_WINDOWS) {
            final String[] tokens = args[0].convertToString().toString().split(" ", 2);
            final String commandString = tokens[0].replace('/', '\\') + ((tokens.length > 1) ? (' ' + tokens[1]) : "");
            cmdObj = runtime.newString(commandString);
        }
        else {
            cmdObj = args[0].convertToString();
        }
        runtime.checkSafeString(cmdObj);
        if ("-".equals(cmdObj.toString())) {
            throw runtime.newNotImplementedError("popen(\"-\") is unimplemented");
        }
        try {
            int mode;
            if (args.length == 1) {
                mode = ModeFlags.RDONLY;
            }
            else if (args[1] instanceof RubyFixnum) {
                mode = RubyNumeric.num2int(args[1]);
            }
            else {
                mode = getIOModesIntFromString(runtime, args[1].convertToString().toString());
            }
            final ModeFlags modes = new ModeFlags(mode);
            ShellLauncher.POpenProcess process;
            if (cmdPlusArgs == null) {
                process = ShellLauncher.popen(runtime, cmdObj, modes);
            }
            else {
                process = ShellLauncher.popen(runtime, cmdPlusArgs, env, modes);
            }
            if (System.getProperty("java.specification.version", "").equals("1.5")) {
                synchronized (process) {
                    try {
                        process.wait(100L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            final RubyIO io = new RubyIO(runtime, process, modes);
            if (block.isGiven()) {
                try {
                    return block.yield(context, io);
                }
                finally {
                    if (io.openFile.isOpen()) {
                        io.close();
                    }
                    context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, process.waitFor(), ShellLauncher.getPidFromProcess(process)));
                }
            }
            return io;
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        catch (InterruptedException e2) {
            throw runtime.newThreadError("unexpected interrupt");
        }
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static IRubyObject popen3(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            final POpenTuple tuple = popenSpecial(context, args);
            final RubyArray yieldArgs = RubyArray.newArrayLight(runtime, tuple.output, tuple.input, tuple.error);
            if (block.isGiven()) {
                try {
                    return block.yield(context, yieldArgs);
                }
                finally {
                    cleanupPOpen(tuple);
                    context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, tuple.process.waitFor(), ShellLauncher.getPidFromProcess(tuple.process)));
                }
            }
            return yieldArgs;
        }
        catch (InterruptedException e) {
            throw runtime.newThreadError("unexpected interrupt");
        }
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static IRubyObject popen4(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        try {
            final POpenTuple tuple = popenSpecial(context, args);
            final RubyArray yieldArgs = RubyArray.newArrayLight(runtime, runtime.newFixnum(ShellLauncher.getPidFromProcess(tuple.process)), tuple.output, tuple.input, tuple.error);
            if (block.isGiven()) {
                try {
                    return block.yield(context, yieldArgs);
                }
                finally {
                    cleanupPOpen(tuple);
                    context.setLastExitStatus(RubyProcess.RubyStatus.newProcessStatus(runtime, tuple.process.waitFor(), ShellLauncher.getPidFromProcess(tuple.process)));
                }
            }
            return yieldArgs;
        }
        catch (InterruptedException e) {
            throw runtime.newThreadError("unexpected interrupt");
        }
    }
    
    private static void cleanupPOpen(final POpenTuple tuple) {
        if (tuple.input.openFile.isOpen()) {
            tuple.input.close();
        }
        if (tuple.output.openFile.isOpen()) {
            tuple.output.close();
        }
        if (tuple.error.openFile.isOpen()) {
            tuple.error.close();
        }
    }
    
    public static POpenTuple popenSpecial(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        try {
            final ShellLauncher.POpenProcess process = ShellLauncher.popen3(runtime, args, false);
            final RubyIO input = (process.getInput() != null) ? new RubyIO(runtime, process.getInput()) : new RubyIO(runtime, process.getInputStream());
            final RubyIO output = (process.getOutput() != null) ? new RubyIO(runtime, process.getOutput()) : new RubyIO(runtime, process.getOutputStream());
            final RubyIO error = (process.getError() != null) ? new RubyIO(runtime, process.getError()) : new RubyIO(runtime, process.getErrorStream());
            input.getOpenFile().getMainStreamSafe().getDescriptor().setCanBeSeekable(false);
            output.getOpenFile().getMainStreamSafe().getDescriptor().setCanBeSeekable(false);
            error.getOpenFile().getMainStreamSafe().getDescriptor().setCanBeSeekable(false);
            return new POpenTuple(input, output, error, process);
        }
        catch (BadDescriptorException e2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    @JRubyMethod(name = { "pipe" }, meta = true)
    public static IRubyObject pipe(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        try {
            final Pipe pipe = Pipe.open();
            final RubyIO source = new RubyIO(runtime, pipe.source());
            final RubyIO sink = new RubyIO(runtime, pipe.sink());
            sink.openFile.getMainStreamSafe().setSync(true);
            return runtime.newArrayNoCopy(source, sink);
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
    }
    
    @JRubyMethod(name = { "copy_stream" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject copy_stream(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* context */
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getRuntime:()Lorg/jruby/Ruby;
        //     4: astore          runtime
        //     6: aconst_null    
        //     7: astore          io1
        //     9: aconst_null    
        //    10: astore          io2
        //    12: aload_2         /* arg1 */
        //    13: instanceof      Lorg/jruby/RubyString;
        //    16: ifeq            47
        //    19: aload_0         /* context */
        //    20: aload           runtime
        //    22: invokevirtual   org/jruby/Ruby.getFile:()Lorg/jruby/RubyClass;
        //    25: iconst_1       
        //    26: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: dup            
        //    30: iconst_0       
        //    31: aload_2         /* arg1 */
        //    32: aastore        
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    org/jruby/RubyFile.open:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: checkcast       Lorg/jruby/RubyIO;
        //    42: astore          io1
        //    44: goto            72
        //    47: aload_2         /* arg1 */
        //    48: instanceof      Lorg/jruby/RubyIO;
        //    51: ifeq            63
        //    54: aload_2         /* arg1 */
        //    55: checkcast       Lorg/jruby/RubyIO;
        //    58: astore          io1
        //    60: goto            72
        //    63: aload           runtime
        //    65: ldc_w           "Should be String or IO"
        //    68: invokevirtual   org/jruby/Ruby.newTypeError:(Ljava/lang/String;)Lorg/jruby/exceptions/RaiseException;
        //    71: athrow         
        //    72: aload_3         /* arg2 */
        //    73: instanceof      Lorg/jruby/RubyString;
        //    76: ifeq            118
        //    79: aload_0         /* context */
        //    80: aload           runtime
        //    82: invokevirtual   org/jruby/Ruby.getFile:()Lorg/jruby/RubyClass;
        //    85: iconst_2       
        //    86: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: dup            
        //    90: iconst_0       
        //    91: aload_3         /* arg2 */
        //    92: aastore        
        //    93: dup            
        //    94: iconst_1       
        //    95: aload           runtime
        //    97: ldc_w           "w"
        //   100: invokevirtual   org/jruby/Ruby.newString:(Ljava/lang/String;)Lorg/jruby/RubyString;
        //   103: aastore        
        //   104: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   107: invokestatic    org/jruby/RubyFile.open:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: checkcast       Lorg/jruby/RubyIO;
        //   113: astore          io2
        //   115: goto            143
        //   118: aload_3         /* arg2 */
        //   119: instanceof      Lorg/jruby/RubyIO;
        //   122: ifeq            134
        //   125: aload_3         /* arg2 */
        //   126: checkcast       Lorg/jruby/RubyIO;
        //   129: astore          io2
        //   131: goto            143
        //   134: aload           runtime
        //   136: ldc_w           "Should be String or IO"
        //   139: invokevirtual   org/jruby/Ruby.newTypeError:(Ljava/lang/String;)Lorg/jruby/exceptions/RaiseException;
        //   142: athrow         
        //   143: aload           io1
        //   145: getfield        org/jruby/RubyIO.openFile:Lorg/jruby/util/io/OpenFile;
        //   148: invokevirtual   org/jruby/util/io/OpenFile.getMainStreamSafe:()Lorg/jruby/util/io/Stream;
        //   151: invokeinterface org/jruby/util/io/Stream.getDescriptor:()Lorg/jruby/util/io/ChannelDescriptor;
        //   156: astore          d1
        //   158: aload           d1
        //   160: invokevirtual   org/jruby/util/io/ChannelDescriptor.isSeekable:()Z
        //   163: ifne            177
        //   166: aload_0         /* context */
        //   167: invokevirtual   org/jruby/runtime/ThreadContext.getRuntime:()Lorg/jruby/Ruby;
        //   170: ldc_w           "only supports file-to-file copy"
        //   173: invokevirtual   org/jruby/Ruby.newTypeError:(Ljava/lang/String;)Lorg/jruby/exceptions/RaiseException;
        //   176: athrow         
        //   177: aload           io2
        //   179: getfield        org/jruby/RubyIO.openFile:Lorg/jruby/util/io/OpenFile;
        //   182: invokevirtual   org/jruby/util/io/OpenFile.getMainStreamSafe:()Lorg/jruby/util/io/Stream;
        //   185: invokeinterface org/jruby/util/io/Stream.getDescriptor:()Lorg/jruby/util/io/ChannelDescriptor;
        //   190: astore          d2
        //   192: aload           d2
        //   194: invokevirtual   org/jruby/util/io/ChannelDescriptor.isSeekable:()Z
        //   197: ifne            211
        //   200: aload_0         /* context */
        //   201: invokevirtual   org/jruby/runtime/ThreadContext.getRuntime:()Lorg/jruby/Ruby;
        //   204: ldc_w           "only supports file-to-file copy"
        //   207: invokevirtual   org/jruby/Ruby.newTypeError:(Ljava/lang/String;)Lorg/jruby/exceptions/RaiseException;
        //   210: athrow         
        //   211: aload           d1
        //   213: invokevirtual   org/jruby/util/io/ChannelDescriptor.getChannel:()Ljava/nio/channels/Channel;
        //   216: checkcast       Ljava/nio/channels/FileChannel;
        //   219: astore          f1
        //   221: aload           d2
        //   223: invokevirtual   org/jruby/util/io/ChannelDescriptor.getChannel:()Ljava/nio/channels/Channel;
        //   226: checkcast       Ljava/nio/channels/FileChannel;
        //   229: astore          f2
        //   231: aload           f1
        //   233: invokevirtual   java/nio/channels/FileChannel.size:()J
        //   236: lstore          size
        //   238: aload           f1
        //   240: aload           f2
        //   242: invokevirtual   java/nio/channels/FileChannel.position:()J
        //   245: lload           size
        //   247: aload           f2
        //   249: invokevirtual   java/nio/channels/FileChannel.transferTo:(JJLjava/nio/channels/WritableByteChannel;)J
        //   252: pop2           
        //   253: aload_0         /* context */
        //   254: invokevirtual   org/jruby/runtime/ThreadContext.getRuntime:()Lorg/jruby/Ruby;
        //   257: lload           size
        //   259: invokevirtual   org/jruby/Ruby.newFixnum:(J)Lorg/jruby/RubyFixnum;
        //   262: astore          13
        //   264: jsr             296
        //   267: aload           13
        //   269: areturn        
        //   270: astore          ioe
        //   272: aload           runtime
        //   274: aload           ioe
        //   276: invokevirtual   org/jruby/Ruby.newIOErrorFromException:(Ljava/io/IOException;)Lorg/jruby/exceptions/RaiseException;
        //   279: athrow         
        //   280: astore          e
        //   282: aload           runtime
        //   284: invokevirtual   org/jruby/Ruby.newErrnoEBADFError:()Lorg/jruby/exceptions/RaiseException;
        //   287: athrow         
        //   288: astore          15
        //   290: jsr             296
        //   293: aload           15
        //   295: athrow         
        //   296: astore          16
        //   298: aload           io1
        //   300: ifnull          309
        //   303: aload           io1
        //   305: invokevirtual   org/jruby/RubyIO.close:()Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: pop            
        //   309: jsr             323
        //   312: goto            338
        //   315: astore          17
        //   317: jsr             323
        //   320: aload           17
        //   322: athrow         
        //   323: astore          18
        //   325: aload           io2
        //   327: ifnull          336
        //   330: aload           io2
        //   332: invokevirtual   org/jruby/RubyIO.close:()Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: pop            
        //   336: ret             18
        //   338: ret             16
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ------------------------------------------
        //  238    32      11    size     J
        //  272    8       14    ioe      Ljava/io/IOException;
        //  158    122     7     d1       Lorg/jruby/util/io/ChannelDescriptor;
        //  192    88      8     d2       Lorg/jruby/util/io/ChannelDescriptor;
        //  221    59      9     f1       Ljava/nio/channels/FileChannel;
        //  231    49      10    f2       Ljava/nio/channels/FileChannel;
        //  282    6       7     e        Lorg/jruby/util/io/BadDescriptorException;
        //  0      340     0     context  Lorg/jruby/runtime/ThreadContext;
        //  0      340     1     recv     Lorg/jruby/runtime/builtin/IRubyObject;
        //  0      340     2     arg1     Lorg/jruby/runtime/builtin/IRubyObject;
        //  0      340     3     arg2     Lorg/jruby/runtime/builtin/IRubyObject;
        //  6      334     4     runtime  Lorg/jruby/Ruby;
        //  9      331     5     io1      Lorg/jruby/RubyIO;
        //  12     328     6     io2      Lorg/jruby/RubyIO;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  231    264    270    280    Ljava/io/IOException;
        //  12     267    280    288    Lorg/jruby/util/io/BadDescriptorException;
        //  270    280    280    288    Lorg/jruby/util/io/BadDescriptorException;
        //  12     267    288    296    Any
        //  270    293    288    296    Any
        //  298    312    315    323    Any
        //  315    320    315    323    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = { "try_convert" }, meta = true, backtrace = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject tryConvert(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return arg.respondsTo("to_io") ? convertToIO(context, arg) : context.getRuntime().getNil();
    }
    
    private static ByteList getNilByteList(final Ruby runtime) {
        return runtime.is1_9() ? ByteList.EMPTY_BYTELIST : RubyIO.NIL_BYTELIST;
    }
    
    public synchronized void addBlockingThread(final RubyThread thread) {
        if (this.blockingThreads == null) {
            this.blockingThreads = new ArrayList<RubyThread>(1);
        }
        this.blockingThreads.add(thread);
    }
    
    public synchronized void removeBlockingThread(final RubyThread thread) {
        if (this.blockingThreads == null) {
            return;
        }
        for (int i = 0; i < this.blockingThreads.size(); ++i) {
            if (this.blockingThreads.get(i) == thread) {
                this.blockingThreads.remove(i);
            }
        }
    }
    
    protected synchronized void interruptBlockingThreads() {
        if (this.blockingThreads == null) {
            return;
        }
        for (int i = 0; i < this.blockingThreads.size(); ++i) {
            final RubyThread thread = this.blockingThreads.get(i);
            thread.raise(new IRubyObject[] { this.getRuntime().newIOError("stream closed").getException() }, Block.NULL_BLOCK);
        }
    }
    
    protected ModeFlags parseOptions(final ThreadContext context, final IRubyObject options, ModeFlags modes) {
        final Ruby runtime = context.getRuntime();
        final RubyHash rubyOptions = (RubyHash)options;
        IRubyObject internalEncodingOption = rubyOptions.fastARef(runtime.newSymbol("internal_encoding"));
        IRubyObject externalEncodingOption = rubyOptions.fastARef(runtime.newSymbol("external_encoding"));
        final RubyString dash = runtime.newString("-");
        if (externalEncodingOption != null && !externalEncodingOption.isNil()) {
            if (dash.eql(externalEncodingOption)) {
                externalEncodingOption = runtime.getEncodingService().getDefaultExternal();
            }
            this.setExternalEncoding(context, externalEncodingOption);
        }
        if (internalEncodingOption != null && !internalEncodingOption.isNil()) {
            if (dash.eql(internalEncodingOption)) {
                internalEncodingOption = runtime.getEncodingService().getDefaultInternal();
            }
            this.setInternalEncoding(context, internalEncodingOption);
        }
        final IRubyObject encoding = rubyOptions.fastARef(runtime.newSymbol("encoding"));
        if (encoding != null && !encoding.isNil()) {
            if (externalEncodingOption != null && !externalEncodingOption.isNil()) {
                runtime.getWarnings().warn("Ignoring encoding parameter '" + encoding + "': external_encoding is used");
            }
            else if (internalEncodingOption != null && !internalEncodingOption.isNil()) {
                runtime.getWarnings().warn("Ignoring encoding parameter '" + encoding + "': internal_encoding is used");
            }
            else {
                this.parseEncodingFromString(context, encoding, 0);
            }
        }
        if (rubyOptions.containsKey(runtime.newSymbol("mode"))) {
            modes = this.parseModes19(context, rubyOptions.fastARef(runtime.newSymbol("mode")).asString());
        }
        return modes;
    }
    
    public static void obliterateProcess(final Process process) {
        int i = 0;
        final Object waitLock = new Object();
        while (i < 1000) {
            process.destroy();
            try {
                process.exitValue();
            }
            catch (IllegalThreadStateException itse) {
                ++i;
                synchronized (waitLock) {
                    try {
                        waitLock.wait(1L);
                    }
                    catch (InterruptedException ex) {}
                }
                continue;
            }
            return;
        }
        throw new RuntimeException("could not shut down process: " + process);
    }
    
    @Deprecated
    public void registerDescriptor(final ChannelDescriptor descriptor, final boolean isRetained) {
    }
    
    @Deprecated
    public void registerDescriptor(final ChannelDescriptor descriptor) {
    }
    
    @Deprecated
    public void unregisterDescriptor(final int aFileno) {
    }
    
    @Deprecated
    public ChannelDescriptor getDescriptorByFileno(final int aFileno) {
        return ChannelDescriptor.getDescriptorByFileno(aFileno);
    }
    
    @Deprecated
    public static int getNewFileno() {
        return ChannelDescriptor.getNewFileno();
    }
    
    @Deprecated
    public boolean writeDataBuffered() {
        return this.openFile.getMainStream().writeDataBuffered();
    }
    
    @Deprecated
    public IRubyObject gets(final ThreadContext context, final IRubyObject[] args) {
        return (args.length == 0) ? this.gets(context) : this.gets(context, args[0]);
    }
    
    @Deprecated
    public IRubyObject readline(final ThreadContext context, final IRubyObject[] args) {
        return (args.length == 0) ? this.readline(context) : this.readline(context, args[0]);
    }
    
    static {
        RubyIO.IO_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyIO(runtime, klass);
            }
        };
        final String v = SafePropertyAccessor.getProperty("java.vendor");
        RubyIO.vendor = ((v == null) ? "" : v);
        RubyIO.msgEINTR = "Interrupted system call";
        NIL_BYTELIST = ByteList.create("nil");
        RECURSIVE_BYTELIST = ByteList.create("[...]");
    }
    
    private static class POpenTuple
    {
        public final RubyIO input;
        public final RubyIO output;
        public final RubyIO error;
        public final Process process;
        
        public POpenTuple(final RubyIO i, final RubyIO o, final RubyIO e, final Process p) {
            this.input = i;
            this.output = o;
            this.error = e;
            this.process = p;
        }
    }
    
    private static class ByteListCache
    {
        private byte[] buffer;
        
        private ByteListCache() {
            this.buffer = new byte[0];
        }
        
        public void release(final ByteList l) {
            this.buffer = l.getUnsafeBytes();
        }
        
        public ByteList allocate(final int size) {
            final ByteList l = new ByteList(this.buffer, 0, size, false);
            return l;
        }
    }
}

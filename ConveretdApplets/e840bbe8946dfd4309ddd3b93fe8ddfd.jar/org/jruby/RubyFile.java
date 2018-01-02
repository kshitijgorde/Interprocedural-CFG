// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jruby.anno.JRubyModule;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.jruby.ext.posix.FileStat;
import org.jruby.util.ByteList;
import org.jruby.util.Dir;
import java.util.regex.Matcher;
import java.net.URL;
import java.net.URI;
import org.jruby.util.JRubyFile;
import org.jruby.util.io.PipeException;
import org.jruby.util.io.Stream;
import org.jruby.util.io.FileExistsException;
import org.jruby.util.io.DirectoryAsFileException;
import java.io.FileNotFoundException;
import org.jruby.util.io.PermissionDeniedException;
import org.jruby.util.io.OpenFile;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import java.nio.channels.OverlappingFileLockException;
import java.nio.channels.FileChannel;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.io.ModeFlags;
import com.kenai.constantine.platform.OpenFlags;
import java.io.File;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.util.io.ChannelStream;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import java.nio.channels.Channels;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.jruby.ext.posix.util.Platform;
import org.jcodings.Encoding;
import java.util.regex.Pattern;
import org.jruby.runtime.ObjectAllocator;
import java.nio.channels.FileLock;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.encoding.EncodingCapable;

@JRubyClass(name = { "File" }, parent = "IO", include = { "FileTest" })
public class RubyFile extends RubyIO implements EncodingCapable
{
    private static final long serialVersionUID = 1L;
    public static final int LOCK_SH = 1;
    public static final int LOCK_EX = 2;
    public static final int LOCK_NB = 4;
    public static final int LOCK_UN = 8;
    private static final int FNM_NOESCAPE = 1;
    private static final int FNM_PATHNAME = 2;
    private static final int FNM_DOTMATCH = 4;
    private static final int FNM_CASEFOLD = 8;
    private static final int FNM_SYSCASE;
    private static int _cachedUmask;
    private static final Object _umaskLock;
    protected String path;
    private FileLock currentLock;
    private static ObjectAllocator FILE_ALLOCATOR;
    private static Pattern URI_PREFIX;
    private static final String[] SLASHES;
    
    public Encoding getEncoding() {
        return null;
    }
    
    public void setEncoding(final Encoding encoding) {
    }
    
    private static boolean startsWithDriveLetterOnWindows(final String path) {
        return path != null && Platform.IS_WINDOWS && ((path.length() <= 1 || path.charAt(0) != '/') ? (path.length() > 1 && isWindowsDriveLetter(path.charAt(0)) && path.charAt(1) == ':') : (path.length() > 2 && isWindowsDriveLetter(path.charAt(1)) && path.charAt(2) == ':'));
    }
    
    static String adjustRootPathOnWindows(final Ruby runtime, String path, String dir) {
        if (path == null || !Platform.IS_WINDOWS) {
            return path;
        }
        if ((path.startsWith("/") && (path.length() <= 2 || path.charAt(2) != ':')) || path.startsWith("\\")) {
            if (path.length() > 1 && (path.charAt(1) == '/' || path.charAt(1) == '\\')) {
                return path;
            }
            if (!startsWithDriveLetterOnWindows(dir)) {
                dir = runtime.getCurrentDirectory();
            }
            if (dir.length() >= 2) {
                path = dir.substring(0, 2) + path;
            }
        }
        else if (startsWithDriveLetterOnWindows(path) && path.length() == 2) {
            path += "/";
        }
        return path;
    }
    
    public RubyFile(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    public RubyFile(final Ruby runtime, final String path, final Reader reader) {
        this(runtime, path, new InputStream() {
            public int read() throws IOException {
                return reader.read();
            }
        });
    }
    
    public RubyFile(final Ruby runtime, final String path, final InputStream in) {
        super(runtime, runtime.getFile());
        this.path = path;
        try {
            this.openFile.setMainStream(ChannelStream.open(runtime, new ChannelDescriptor(Channels.newChannel(in))));
            this.openFile.setMode(this.openFile.getMainStreamSafe().getModes().getOpenFileFlags());
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
    }
    
    public String getPath() {
        return this.path;
    }
    
    public static RubyClass createFileClass(final Ruby runtime) {
        final RubyClass fileClass = runtime.defineClass("File", runtime.getIO(), RubyFile.FILE_ALLOCATOR);
        final RubyModule constants = fileClass.defineModuleUnder("Constants");
        runtime.setFile(fileClass);
        fileClass.index = 26;
        fileClass.setReifiedClass(RubyFile.class);
        final RubyString separator = runtime.newString("/");
        final ThreadContext context = runtime.getCurrentContext();
        fileClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyFile;
            }
        };
        separator.freeze(context);
        fileClass.defineConstant("SEPARATOR", separator);
        fileClass.defineConstant("Separator", separator);
        if (File.separatorChar == '\\') {
            final RubyString altSeparator = runtime.newString("\\");
            altSeparator.freeze(context);
            fileClass.defineConstant("ALT_SEPARATOR", altSeparator);
        }
        else {
            fileClass.defineConstant("ALT_SEPARATOR", runtime.getNil());
        }
        final RubyString pathSeparator = runtime.newString(File.pathSeparator);
        pathSeparator.freeze(context);
        fileClass.defineConstant("PATH_SEPARATOR", pathSeparator);
        fileClass.fastSetConstant("FNM_NOESCAPE", runtime.newFixnum(1));
        fileClass.fastSetConstant("FNM_CASEFOLD", runtime.newFixnum(8));
        fileClass.fastSetConstant("FNM_SYSCASE", runtime.newFixnum(RubyFile.FNM_SYSCASE));
        fileClass.fastSetConstant("FNM_DOTMATCH", runtime.newFixnum(4));
        fileClass.fastSetConstant("FNM_PATHNAME", runtime.newFixnum(2));
        for (final OpenFlags f : OpenFlags.values()) {
            final String name = f.name();
            if (name.startsWith("O_")) {
                final String cname = name.substring(2);
                final RubyFixnum cvalue = (f == OpenFlags.O_ACCMODE) ? runtime.newFixnum(ModeFlags.ACCMODE) : runtime.newFixnum(f.value());
                fileClass.fastSetConstant(cname, cvalue);
                constants.fastSetConstant(cname, cvalue);
            }
        }
        fileClass.fastSetConstant("LOCK_SH", runtime.newFixnum(1));
        fileClass.fastSetConstant("LOCK_EX", runtime.newFixnum(2));
        fileClass.fastSetConstant("LOCK_NB", runtime.newFixnum(4));
        fileClass.fastSetConstant("LOCK_UN", runtime.newFixnum(8));
        constants.fastSetConstant("FNM_NOESCAPE", runtime.newFixnum(1));
        constants.fastSetConstant("FNM_CASEFOLD", runtime.newFixnum(8));
        constants.fastSetConstant("FNM_SYSCASE", runtime.newFixnum(RubyFile.FNM_SYSCASE));
        constants.fastSetConstant("FNM_DOTMATCH", runtime.newFixnum(4));
        constants.fastSetConstant("FNM_PATHNAME", runtime.newFixnum(2));
        constants.fastSetConstant("LOCK_SH", runtime.newFixnum(1));
        constants.fastSetConstant("LOCK_EX", runtime.newFixnum(2));
        constants.fastSetConstant("LOCK_NB", runtime.newFixnum(4));
        constants.fastSetConstant("LOCK_UN", runtime.newFixnum(8));
        runtime.getIO().includeModule(constants);
        runtime.getFileTest().extend_object(fileClass);
        fileClass.defineAnnotatedMethods(RubyFile.class);
        fileClass.getSingletonClass().defineAnnotatedMethods(RubyFileTest.FileTestFileMethods.class);
        return fileClass;
    }
    
    @JRubyMethod
    public IRubyObject close() {
        if (this.currentLock != null) {
            try {
                this.currentLock.release();
            }
            catch (IOException e) {
                throw this.getRuntime().newIOError(e.getMessage());
            }
        }
        return super.close();
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject flock(final ThreadContext context, final IRubyObject lockingConstant) {
        try {
            final ChannelDescriptor descriptor = this.openFile.getMainStreamSafe().getDescriptor();
            if (descriptor.isNull()) {
                return RubyFixnum.zero(context.getRuntime());
            }
            if (!(descriptor.getChannel() instanceof FileChannel)) {
                return context.getRuntime().getFalse();
            }
            final FileChannel fileChannel = (FileChannel)descriptor.getChannel();
            final int lockMode = RubyNumeric.num2int(lockingConstant);
            if (!this.openFile.isWritable() && (lockMode & 0x2) > 0) {
                throw context.runtime.newErrnoEBADFError("cannot acquire exclusive lock on File not opened for write");
            }
            if (!this.openFile.isReadable() && (lockMode & 0x1) > 0) {
                throw context.runtime.newErrnoEBADFError("cannot acquire shared lock on File not opened for read");
            }
            try {
                switch (lockMode) {
                    case 8:
                    case 12: {
                        if (this.currentLock != null) {
                            this.currentLock.release();
                            this.currentLock = null;
                            return RubyFixnum.zero(context.getRuntime());
                        }
                        break;
                    }
                    case 2: {
                        if (this.currentLock != null) {
                            this.currentLock.release();
                            this.currentLock = null;
                        }
                        this.currentLock = fileChannel.lock();
                        if (this.currentLock != null) {
                            return RubyFixnum.zero(context.getRuntime());
                        }
                        break;
                    }
                    case 6: {
                        if (this.currentLock != null) {
                            this.currentLock.release();
                            this.currentLock = null;
                        }
                        this.currentLock = fileChannel.tryLock();
                        if (this.currentLock != null) {
                            return RubyFixnum.zero(context.getRuntime());
                        }
                        break;
                    }
                    case 1: {
                        if (this.currentLock != null) {
                            this.currentLock.release();
                            this.currentLock = null;
                        }
                        this.currentLock = fileChannel.lock(0L, Long.MAX_VALUE, true);
                        if (this.currentLock != null) {
                            return RubyFixnum.zero(context.getRuntime());
                        }
                        break;
                    }
                    case 5: {
                        if (this.currentLock != null) {
                            this.currentLock.release();
                            this.currentLock = null;
                        }
                        this.currentLock = fileChannel.tryLock(0L, Long.MAX_VALUE, true);
                        if (this.currentLock != null) {
                            return RubyFixnum.zero(context.getRuntime());
                        }
                        break;
                    }
                }
            }
            catch (IOException ioe) {
                if (context.getRuntime().getDebug().isTrue()) {
                    ioe.printStackTrace(System.err);
                }
            }
            catch (OverlappingFileLockException ioe2) {
                if (context.getRuntime().getDebug().isTrue()) {
                    ioe2.printStackTrace(System.err);
                }
            }
            return ((lockMode & 0x2) == 0x0) ? RubyFixnum.zero(context.getRuntime()) : context.getRuntime().getFalse();
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(required = 1, optional = 2, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final IRubyObject[] args, final Block block) {
        if (this.openFile == null) {
            throw this.getRuntime().newRuntimeError("reinitializing File");
        }
        if (args.length > 0 && args.length < 3 && args[0] instanceof RubyInteger) {
            return super.initialize(args, block);
        }
        return this.openFile(args);
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, optional = 2, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject[] args, final Block block) {
        if (this.openFile == null) {
            throw context.getRuntime().newRuntimeError("reinitializing File");
        }
        if (args.length > 0 && args.length <= 3) {
            final IRubyObject fd = TypeConverter.convertToTypeWithCheck(args[0], context.getRuntime().getFixnum(), "to_int");
            if (!fd.isNil()) {
                args[0] = fd;
                if (args.length == 1) {
                    return super.initialize19(context, args[0], block);
                }
                if (args.length == 2) {
                    return super.initialize19(context, args[0], args[1], block);
                }
                return super.initialize19(context, args[0], args[1], args[2], block);
            }
        }
        return this.openFile19(context, args);
    }
    
    private IRubyObject openFile19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final RubyString filename = get_path(context, args[0]);
        runtime.checkSafeString(filename);
        this.path = adjustRootPathOnWindows(runtime, filename.getUnicodeValue(), runtime.getCurrentDirectory());
        String modeString = "r";
        ModeFlags modes = new ModeFlags();
        int perm = 0;
        try {
            if (args.length > 1) {
                if (args[1] instanceof RubyHash) {
                    modes = this.parseOptions(context, args[1], modes);
                }
                else {
                    modes = this.parseModes19(context, args[1]);
                    if (args[1] instanceof RubyFixnum) {
                        perm = this.getFilePermissions(args);
                    }
                    else {
                        modeString = args[1].convertToString().toString();
                    }
                }
            }
            else {
                modes = this.parseModes19(context, RubyString.newString(runtime, modeString));
            }
            if (args.length > 2 && !args[2].isNil()) {
                if (args[2] instanceof RubyHash) {
                    modes = this.parseOptions(context, args[2], modes);
                }
                else {
                    perm = this.getFilePermissions(args);
                }
            }
            if (perm > 0) {
                this.sysopenInternal(this.path, modes, perm);
            }
            else {
                this.openInternal(this.path, modeString, modes);
            }
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        return this;
    }
    
    private IRubyObject openFile(final IRubyObject[] args) {
        final Ruby runtime = this.getRuntime();
        final RubyString filename = get_path(runtime.getCurrentContext(), args[0]);
        runtime.checkSafeString(filename);
        this.path = adjustRootPathOnWindows(runtime, filename.getUnicodeValue(), runtime.getCurrentDirectory());
        try {
            if ((args.length > 1 && args[1] instanceof RubyFixnum) || (args.length > 2 && !args[2].isNil())) {
                final ModeFlags modes = this.parseModes(args[1]);
                final int perm = this.getFilePermissions(args);
                this.sysopenInternal(this.path, modes, perm);
            }
            else {
                String modeString = "r";
                if (args.length > 1 && !args[1].isNil()) {
                    modeString = args[1].convertToString().toString();
                }
                this.openInternal(this.path, modeString);
            }
        }
        catch (InvalidValueException ex) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        return this;
    }
    
    private int getFilePermissions(final IRubyObject[] args) {
        return (args.length > 2 && !args[2].isNil()) ? RubyNumeric.num2int(args[2]) : 438;
    }
    
    protected void sysopenInternal(final String path, final ModeFlags modes, int perm) throws InvalidValueException {
        (this.openFile = new OpenFile()).setPath(path);
        this.openFile.setMode(modes.getOpenFileFlags());
        final int umask = getUmaskSafe(this.getRuntime());
        perm -= (perm & umask);
        final ChannelDescriptor descriptor = this.sysopen(path, modes, perm);
        this.openFile.setMainStream(this.fdopen(descriptor, modes));
    }
    
    protected void openInternal(final String path, final String modeString, final ModeFlags modes) throws InvalidValueException {
        (this.openFile = new OpenFile()).setMode(modes.getOpenFileFlags());
        this.openFile.setPath(path);
        this.openFile.setMainStream(this.fopen(path, modeString));
    }
    
    protected void openInternal(final String path, final String modeString) throws InvalidValueException {
        (this.openFile = new OpenFile()).setMode(RubyIO.getIOModes(this.getRuntime(), modeString).getOpenFileFlags());
        this.openFile.setPath(path);
        this.openFile.setMainStream(this.fopen(path, modeString));
    }
    
    private ChannelDescriptor sysopen(final String path, final ModeFlags modes, final int perm) throws InvalidValueException {
        try {
            final ChannelDescriptor descriptor = ChannelDescriptor.open(this.getRuntime().getCurrentDirectory(), path, modes, perm, this.getRuntime().getPosix(), this.getRuntime().getJRubyClassLoader());
            return descriptor;
        }
        catch (PermissionDeniedException pde) {
            throw this.getRuntime().newErrnoEACCESError(path);
        }
        catch (FileNotFoundException fnfe) {
            if (Ruby.isSecurityRestricted() || new File(path).exists()) {
                throw this.getRuntime().newErrnoEACCESError(path);
            }
            throw this.getRuntime().newErrnoENOENTError(path);
        }
        catch (DirectoryAsFileException dafe) {
            throw this.getRuntime().newErrnoEISDirError();
        }
        catch (FileExistsException fee) {
            throw this.getRuntime().newErrnoEEXISTError(path);
        }
        catch (IOException ioe) {
            throw this.getRuntime().newIOErrorFromException(ioe);
        }
    }
    
    private Stream fopen(final String path, final String modeString) {
        try {
            final Stream stream = ChannelStream.fopen(this.getRuntime(), path, RubyIO.getIOModes(this.getRuntime(), modeString));
            if (stream == null) {}
            return stream;
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
        catch (PermissionDeniedException pde) {
            throw this.getRuntime().newErrnoEACCESError(path);
        }
        catch (FileNotFoundException ex2) {
            if (Ruby.isSecurityRestricted() || new File(path).exists()) {
                throw this.getRuntime().newErrnoEACCESError(path);
            }
            throw this.getRuntime().newErrnoENOENTError(path);
        }
        catch (DirectoryAsFileException ex3) {
            throw this.getRuntime().newErrnoEISDirError();
        }
        catch (FileExistsException ex4) {
            throw this.getRuntime().newErrnoEEXISTError(path);
        }
        catch (IOException ex) {
            throw this.getRuntime().newIOErrorFromException(ex);
        }
        catch (InvalidValueException ex5) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        catch (PipeException ex6) {
            throw this.getRuntime().newErrnoEPIPEError();
        }
        catch (SecurityException ex7) {
            throw this.getRuntime().newErrnoEACCESError(path);
        }
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject chmod(final ThreadContext context, final IRubyObject arg) {
        this.checkClosed(context);
        final int mode = (int)arg.convertToInteger().getLongValue();
        if (!new File(this.path).exists()) {
            throw context.getRuntime().newErrnoENOENTError(this.path);
        }
        return context.getRuntime().newFixnum(context.getRuntime().getPosix().chmod(this.path, mode));
    }
    
    @JRubyMethod(required = 2)
    public IRubyObject chown(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        this.checkClosed(context);
        int owner = -1;
        if (!arg1.isNil()) {
            owner = RubyNumeric.num2int(arg1);
        }
        int group = -1;
        if (!arg2.isNil()) {
            group = RubyNumeric.num2int(arg2);
        }
        if (!new File(this.path).exists()) {
            throw context.getRuntime().newErrnoENOENTError(this.path);
        }
        return context.getRuntime().newFixnum(context.getRuntime().getPosix().chown(this.path, owner, group));
    }
    
    @JRubyMethod
    public IRubyObject atime(final ThreadContext context) {
        this.checkClosed(context);
        return context.getRuntime().newFileStat(this.path, false).atime();
    }
    
    @JRubyMethod
    public IRubyObject ctime(final ThreadContext context) {
        this.checkClosed(context);
        return context.getRuntime().newFileStat(this.path, false).ctime();
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject lchmod(final ThreadContext context, final IRubyObject arg) {
        final int mode = (int)arg.convertToInteger().getLongValue();
        if (!new File(this.path).exists()) {
            throw context.getRuntime().newErrnoENOENTError(this.path);
        }
        return context.getRuntime().newFixnum(context.getRuntime().getPosix().lchmod(this.path, mode));
    }
    
    @JRubyMethod(required = 2)
    public IRubyObject lchown(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        int owner = -1;
        if (!arg1.isNil()) {
            owner = RubyNumeric.num2int(arg1);
        }
        int group = -1;
        if (!arg2.isNil()) {
            group = RubyNumeric.num2int(arg2);
        }
        if (!new File(this.path).exists()) {
            throw context.getRuntime().newErrnoENOENTError(this.path);
        }
        return context.getRuntime().newFixnum(context.getRuntime().getPosix().lchown(this.path, owner, group));
    }
    
    @JRubyMethod
    public IRubyObject lstat(final ThreadContext context) {
        this.checkClosed(context);
        return context.getRuntime().newFileStat(this.path, true);
    }
    
    @JRubyMethod
    public IRubyObject mtime(final ThreadContext context) {
        this.checkClosed(context);
        return getLastModified(context.getRuntime(), this.path);
    }
    
    @JRubyMethod(meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject path(final ThreadContext context, final IRubyObject self, final IRubyObject str) {
        return get_path(context, str);
    }
    
    public static RubyString get_path(final ThreadContext context, IRubyObject obj) {
        if (context.getRuntime().is1_9()) {
            if (obj instanceof RubyString) {
                return (RubyString)obj;
            }
            if (obj.respondsTo("to_path")) {
                obj = obj.callMethod(context, "to_path");
            }
        }
        return obj.convertToString();
    }
    
    public static JRubyFile file(final IRubyObject pathOrFile) {
        final Ruby runtime = pathOrFile.getRuntime();
        if (pathOrFile instanceof RubyFile) {
            return JRubyFile.create(runtime.getCurrentDirectory(), ((RubyFile)pathOrFile).getPath());
        }
        final RubyString pathStr = get_path(runtime.getCurrentContext(), pathOrFile);
        String path = pathStr.getUnicodeValue();
        final String[] pathParts = splitURI(path);
        if (pathParts != null && pathParts[0].equals("file:")) {
            path = pathParts[1];
        }
        return JRubyFile.create(runtime.getCurrentDirectory(), path);
    }
    
    @JRubyMethod(name = { "path", "to_path" })
    public IRubyObject path(final ThreadContext context) {
        IRubyObject newPath = context.getRuntime().getNil();
        if (this.path != null) {
            newPath = context.getRuntime().newString(this.path);
            newPath.setTaint(true);
        }
        return newPath;
    }
    
    @JRubyMethod
    public IRubyObject stat(final ThreadContext context) {
        this.checkClosed(context);
        return context.getRuntime().newFileStat(this.path, false);
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject truncate(final ThreadContext context, final IRubyObject arg) {
        final RubyInteger newLength = arg.convertToInteger();
        if (newLength.getLongValue() < 0L) {
            throw context.getRuntime().newErrnoEINVALError(this.path);
        }
        try {
            this.openFile.checkWritable(context.getRuntime());
            this.openFile.getMainStreamSafe().ftruncate(newLength.getLongValue());
        }
        catch (BadDescriptorException e) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (PipeException e2) {
            throw context.getRuntime().newErrnoESPIPEError();
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (IOException ex2) {}
        return RubyFixnum.zero(context.getRuntime());
    }
    
    public String toString() {
        try {
            return "RubyFile(" + this.path + ", " + this.openFile.getMode() + ", " + this.getRuntime().getFileno(this.openFile.getMainStreamSafe().getDescriptor()) + ")";
        }
        catch (BadDescriptorException e) {
            throw this.getRuntime().newErrnoEBADFError();
        }
    }
    
    private static ModeFlags getModes(final Ruby runtime, final IRubyObject object) throws InvalidValueException {
        if (object instanceof RubyString) {
            return RubyIO.getIOModes(runtime, ((RubyString)object).toString());
        }
        if (object instanceof RubyFixnum) {
            return new ModeFlags(((RubyFixnum)object).getLongValue());
        }
        throw runtime.newTypeError("Invalid type for modes");
    }
    
    @JRubyMethod
    public IRubyObject inspect() {
        final StringBuilder val = new StringBuilder();
        val.append("#<File:").append(this.path);
        if (!this.openFile.isOpen()) {
            val.append(" (closed)");
        }
        val.append(">");
        return this.getRuntime().newString(val.toString());
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true)
    public static IRubyObject basename(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        String name = get_path(context, args[0]).getUnicodeValue();
        Label_0149: {
            if (Platform.IS_WINDOWS && name.length() > 1 && name.charAt(1) == ':' && Character.isLetter(name.charAt(0))) {
                switch (name.length()) {
                    case 2: {
                        return RubyString.newEmptyString(context.getRuntime()).infectBy(args[0]);
                    }
                    case 3: {
                        return context.getRuntime().newString(name.substring(2)).infectBy(args[0]);
                    }
                    default: {
                        switch (name.charAt(2)) {
                            case '/':
                            case '\\': {
                                break Label_0149;
                            }
                            default: {
                                name = name.substring(2);
                                break Label_0149;
                            }
                        }
                        break;
                    }
                }
            }
        }
        while (name.length() > 1 && name.charAt(name.length() - 1) == '/') {
            name = name.substring(0, name.length() - 1);
        }
        int slashCount = 0;
        final int length = name.length();
        for (int i = length - 1; i >= 0; --i) {
            final char c = name.charAt(i);
            if (c != '/' && c != '\\') {
                break;
            }
            ++slashCount;
        }
        if (slashCount > 0 && length > 1) {
            name = name.substring(0, name.length() - slashCount);
        }
        int index = name.lastIndexOf(47);
        if (index == -1) {
            index = name.lastIndexOf(92);
        }
        if (!name.equals("/") && index != -1) {
            name = name.substring(index + 1);
        }
        if (args.length == 2) {
            final String ext = RubyString.stringValue(args[1]).toString();
            if (".*".equals(ext)) {
                index = name.lastIndexOf(46);
                if (index > 0) {
                    name = name.substring(0, index);
                }
            }
            else if (name.endsWith(ext)) {
                name = name.substring(0, name.length() - ext.length());
            }
        }
        return context.getRuntime().newString(name).infectBy(args[0]);
    }
    
    @JRubyMethod(required = 2, rest = true, meta = true)
    public static IRubyObject chmod(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        int count = 0;
        final RubyInteger mode = args[0].convertToInteger();
        for (int i = 1; i < args.length; ++i) {
            final JRubyFile filename = file(args[i]);
            if (!filename.exists()) {
                throw runtime.newErrnoENOENTError(filename.toString());
            }
            final boolean result = 0 == runtime.getPosix().chmod(filename.getAbsolutePath(), (int)mode.getLongValue());
            if (result) {
                ++count;
            }
        }
        return runtime.newFixnum(count);
    }
    
    @JRubyMethod(required = 3, rest = true, meta = true)
    public static IRubyObject chown(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        int count = 0;
        int owner = -1;
        if (!args[0].isNil()) {
            owner = RubyNumeric.num2int(args[0]);
        }
        int group = -1;
        if (!args[1].isNil()) {
            group = RubyNumeric.num2int(args[1]);
        }
        for (int i = 2; i < args.length; ++i) {
            final JRubyFile filename = file(args[i]);
            if (!filename.exists()) {
                throw runtime.newErrnoENOENTError(filename.toString());
            }
            final boolean result = 0 == runtime.getPosix().chown(filename.getAbsolutePath(), owner, group);
            if (result) {
                ++count;
            }
        }
        return runtime.newFixnum(count);
    }
    
    @JRubyMethod(required = 1, meta = true)
    public static IRubyObject dirname(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final RubyString filename = get_path(context, arg);
        final String jfilename = filename.getUnicodeValue();
        String name = jfilename.replace('\\', '/');
        int minPathLength = 1;
        boolean trimmedSlashes = false;
        final boolean startsWithDriveLetterOnWindows = startsWithDriveLetterOnWindows(name);
        if (startsWithDriveLetterOnWindows) {
            minPathLength = 3;
        }
        while (name.length() > minPathLength && name.charAt(name.length() - 1) == '/') {
            trimmedSlashes = true;
            name = name.substring(0, name.length() - 1);
        }
        String result;
        if (startsWithDriveLetterOnWindows && name.length() == 2) {
            if (trimmedSlashes) {
                result = jfilename.substring(0, 3);
            }
            else {
                result = jfilename.substring(0, 2) + '.';
            }
        }
        else {
            int index = name.lastIndexOf(47);
            if (index == -1) {
                if (startsWithDriveLetterOnWindows) {
                    return context.getRuntime().newString(jfilename.substring(0, 2) + ".");
                }
                return context.getRuntime().newString(".");
            }
            else {
                if (index == 0) {
                    return context.getRuntime().newString("/");
                }
                if (startsWithDriveLetterOnWindows && index == 2) {
                    ++index;
                }
                result = jfilename.substring(0, index);
            }
        }
        while (result.length() > minPathLength) {
            final char endChar = result.charAt(result.length() - 1);
            if (endChar != '/' && endChar != '\\') {
                break;
            }
            result = result.substring(0, result.length() - 1);
        }
        return context.getRuntime().newString(result).infectBy(filename);
    }
    
    private static boolean isWindowsDriveLetter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    @JRubyMethod(required = 1, meta = true)
    public static IRubyObject extname(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final IRubyObject baseFilename = basename(context, recv, new IRubyObject[] { arg });
        final String filename = RubyString.stringValue(baseFilename).getUnicodeValue();
        String result = "";
        final int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex != filename.length() - 1) {
            result = filename.substring(dotIndex);
        }
        return context.getRuntime().newString(result);
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject expand_path(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return expandPathInternal(context, recv, args, true);
    }
    
    @JRubyMethod(name = { "expand_path" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject expand_path19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final RubyString path = (RubyString)expandPathInternal(context, recv, args, true);
        path.force_encoding(context, context.getRuntime().getEncodingService().getDefaultExternal());
        return path;
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject absolute_path(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return expandPathInternal(context, recv, args, false);
    }
    
    @JRubyMethod(name = { "realdirpath" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject realdirpath(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return expandPathInternal(context, recv, args, false);
    }
    
    @JRubyMethod(name = { "realpath" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject realpath(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final IRubyObject file = expandPathInternal(context, recv, args, false);
        if (!RubyFileTest.exist_p(recv, file).isTrue()) {
            throw context.getRuntime().newErrnoENOENTError(file.toString());
        }
        return file;
    }
    
    private static IRubyObject expandPathInternal(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final boolean expandUser) {
        final Ruby runtime = context.getRuntime();
        String relativePath = get_path(context, args[0]).getUnicodeValue();
        String[] uriParts = splitURI(relativePath);
        String cwd = null;
        if (expandUser) {
            relativePath = expandUserPath(context, relativePath);
        }
        if (uriParts != null) {
            relativePath = uriParts[1];
        }
        if (args.length == 2 && !args[1].isNil()) {
            cwd = get_path(context, args[1]).getUnicodeValue();
            if (expandUser) {
                cwd = expandUserPath(context, cwd);
            }
            final String[] cwdURIParts = splitURI(cwd);
            if (uriParts == null && cwdURIParts != null) {
                uriParts = cwdURIParts;
                cwd = cwdURIParts[1];
            }
            cwd = adjustRootPathOnWindows(runtime, cwd, null);
            final boolean startsWithSlashNotOnWindows = cwd != null && !Platform.IS_WINDOWS && cwd.length() > 0 && cwd.charAt(0) == '/';
            if (!startsWithSlashNotOnWindows && !startsWithDriveLetterOnWindows(cwd)) {
                cwd = new File(runtime.getCurrentDirectory(), cwd).getAbsolutePath();
            }
        }
        else {
            cwd = runtime.getCurrentDirectory();
        }
        if (cwd == null) {
            return runtime.getNil();
        }
        String padSlashes = "";
        if (uriParts != null) {
            padSlashes = uriParts[0];
        }
        else if (!Platform.IS_WINDOWS) {
            if (relativePath.length() > 0 && relativePath.charAt(0) == '/') {
                padSlashes = countSlashes(relativePath);
            }
            else if (cwd.length() > 0 && cwd.charAt(0) == '/') {
                padSlashes = countSlashes(cwd);
            }
        }
        JRubyFile path;
        if (relativePath.length() == 0) {
            path = JRubyFile.create(relativePath, cwd);
        }
        else {
            relativePath = adjustRootPathOnWindows(runtime, relativePath, cwd);
            path = JRubyFile.create(cwd, relativePath);
        }
        return runtime.newString(padSlashes + canonicalize(path.getAbsolutePath()));
    }
    
    public static String[] splitURI(final String path) {
        final Matcher m = RubyFile.URI_PREFIX.matcher(path);
        if (m.find()) {
            if (m.group(1).length() == 0) {
                return new String[] { path, "" };
            }
            try {
                final URI u = new URI(path);
                final String pathPart = u.getPath();
                return new String[] { path.substring(0, path.indexOf(pathPart)), pathPart };
            }
            catch (Exception e) {
                try {
                    final URL u2 = new URL(path);
                    final String pathPart2 = u2.getPath();
                    return new String[] { path.substring(0, path.indexOf(pathPart2)), pathPart2 };
                }
                catch (Exception ex) {}
            }
        }
        return null;
    }
    
    public static String expandUserPath(final ThreadContext context, String path) {
        final int pathLength = path.length();
        if (pathLength >= 1 && path.charAt(0) == '~') {
            int userEnd = path.indexOf(47);
            if (userEnd == -1) {
                if (pathLength == 1) {
                    path = RubyDir.getHomeDirectoryPath(context).toString();
                }
                else {
                    userEnd = pathLength;
                }
            }
            if (userEnd == 1) {
                path = RubyDir.getHomeDirectoryPath(context).toString() + path.substring(1);
            }
            else if (userEnd > 1) {
                final String user = path.substring(1, userEnd);
                final IRubyObject dir = RubyDir.getHomeDirectoryPath(context, user);
                if (dir.isNil()) {
                    throw context.getRuntime().newArgumentError("user " + user + " does not exist");
                }
                path = "" + dir + ((pathLength == userEnd) ? "" : path.substring(userEnd));
            }
        }
        return path;
    }
    
    private static String countSlashes(final String stringToCheck) {
        int slashCount = 0;
        for (int i = 0; i < stringToCheck.length() && stringToCheck.charAt(i) == '/'; ++i) {
            ++slashCount;
        }
        if (slashCount > 0) {
            --slashCount;
        }
        if (slashCount < RubyFile.SLASHES.length) {
            return RubyFile.SLASHES[slashCount];
        }
        final char[] slashes = new char[slashCount];
        for (int j = 0; j < slashCount; ++j) {
            slashes[j] = '/';
        }
        return new String(slashes);
    }
    
    public static String canonicalize(final String path) {
        return canonicalize(null, path);
    }
    
    private static String canonicalize(String canonicalPath, String remaining) {
        if (remaining != null) {
            final int slash = remaining.indexOf(47);
            String child;
            if (slash == -1) {
                child = remaining;
                remaining = null;
            }
            else {
                child = remaining.substring(0, slash);
                remaining = remaining.substring(slash + 1);
            }
            if (child.equals(".")) {
                if (slash == -1 && canonicalPath != null && canonicalPath.length() == 0 && slash == -1) {
                    canonicalPath += "/";
                }
            }
            else if (child.equals("..")) {
                if (canonicalPath == null) {
                    throw new IllegalArgumentException("Cannot have .. at the start of an absolute path");
                }
                final int lastDir = canonicalPath.lastIndexOf(47);
                if (lastDir == -1) {
                    if (!startsWithDriveLetterOnWindows(canonicalPath)) {
                        canonicalPath = "";
                    }
                }
                else {
                    canonicalPath = canonicalPath.substring(0, lastDir);
                }
            }
            else if (canonicalPath == null) {
                canonicalPath = child;
            }
            else {
                canonicalPath = canonicalPath + "/" + child;
            }
            return canonicalize(canonicalPath, remaining);
        }
        if ("".equals(canonicalPath)) {
            return "/";
        }
        if (startsWithDriveLetterOnWindows(canonicalPath) && canonicalPath.length() == 2) {
            canonicalPath += "/";
        }
        return canonicalPath;
    }
    
    @JRubyMethod(name = { "fnmatch", "fnmatch?" }, required = 2, optional = 1, meta = true)
    public static IRubyObject fnmatch(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final int flags = (args.length == 3) ? RubyNumeric.num2int(args[2]) : 0;
        final ByteList pattern = args[0].convertToString().getByteList();
        final ByteList path = get_path(context, args[1]).getByteList();
        if (Dir.fnmatch(pattern.getUnsafeBytes(), pattern.getBegin(), pattern.getBegin() + pattern.getRealSize(), path.getUnsafeBytes(), path.getBegin(), path.getBegin() + path.getRealSize(), flags) == 0) {
            return context.getRuntime().getTrue();
        }
        return context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "ftype" }, required = 1, meta = true)
    public static IRubyObject ftype(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        return context.getRuntime().newFileStat(get_path(context, filename).getUnicodeValue(), true).ftype();
    }
    
    private static String inspectJoin(final ThreadContext context, final IRubyObject recv, final RubyArray parent, final RubyArray array) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isInspecting(parent)) {
            return join(context, recv, array).toString();
        }
        try {
            runtime.registerInspecting(parent);
            return join(context, recv, array).toString();
        }
        finally {
            runtime.unregisterInspecting(parent);
        }
    }
    
    private static RubyString join(final ThreadContext context, final IRubyObject recv, final RubyArray ary) {
        final IRubyObject[] args = ary.toJavaArray();
        boolean isTainted = false;
        final StringBuilder buffer = new StringBuilder();
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < args.length; ++i) {
            if (args[i].isTaint()) {
                isTainted = true;
            }
            String element;
            if (args[i] instanceof RubyString) {
                element = args[i].convertToString().getUnicodeValue();
            }
            else if (args[i] instanceof RubyArray) {
                if (runtime.isInspecting(args[i])) {
                    throw runtime.newArgumentError("recursive array");
                }
                element = inspectJoin(context, recv, ary, (RubyArray)args[i]);
            }
            else {
                final RubyString path = get_path(context, args[i]);
                element = path.getUnicodeValue();
            }
            chomp(buffer);
            if (i > 0 && !element.startsWith("/") && !element.startsWith("\\")) {
                buffer.append("/");
            }
            buffer.append(element);
        }
        final RubyString fixedStr = RubyString.newString(runtime, buffer.toString());
        fixedStr.setTaint(isTainted);
        return fixedStr;
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static RubyString join(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return join(context, recv, RubyArray.newArrayNoCopyLight(context.getRuntime(), args));
    }
    
    private static void chomp(final StringBuilder buffer) {
        for (int lastIndex = buffer.length() - 1; lastIndex >= 0 && (buffer.lastIndexOf("/") == lastIndex || buffer.lastIndexOf("\\") == lastIndex); --lastIndex) {
            buffer.setLength(lastIndex);
        }
    }
    
    @JRubyMethod(name = { "lstat" }, required = 1, meta = true)
    public static IRubyObject lstat(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final String f = get_path(context, filename).getUnicodeValue();
        return context.getRuntime().newFileStat(f, true);
    }
    
    @JRubyMethod(name = { "stat" }, required = 1, meta = true)
    public static IRubyObject stat(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final String f = get_path(context, filename).getUnicodeValue();
        return context.getRuntime().newFileStat(f, false);
    }
    
    @JRubyMethod(name = { "atime" }, required = 1, meta = true)
    public static IRubyObject atime(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final String f = get_path(context, filename).getUnicodeValue();
        return context.getRuntime().newFileStat(f, false).atime();
    }
    
    @JRubyMethod(name = { "ctime" }, required = 1, meta = true)
    public static IRubyObject ctime(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final String f = get_path(context, filename).getUnicodeValue();
        return context.getRuntime().newFileStat(f, false).ctime();
    }
    
    @JRubyMethod(required = 2, rest = true, meta = true)
    public static IRubyObject lchmod(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        int count = 0;
        final RubyInteger mode = args[0].convertToInteger();
        for (int i = 1; i < args.length; ++i) {
            final RubyString filename = get_path(context, args[i]);
            if (!RubyFileTest.exist_p(filename, filename).isTrue()) {
                throw runtime.newErrnoENOENTError(filename.toString());
            }
            final boolean result = 0 == runtime.getPosix().lchmod(filename.getUnicodeValue(), (int)mode.getLongValue());
            if (result) {
                ++count;
            }
        }
        return runtime.newFixnum(count);
    }
    
    @JRubyMethod(required = 2, rest = true, meta = true)
    public static IRubyObject lchown(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final int owner = args[0].isNil() ? -1 : RubyNumeric.num2int(args[0]);
        final int group = args[1].isNil() ? -1 : RubyNumeric.num2int(args[1]);
        int count = 0;
        for (int i = 2; i < args.length; ++i) {
            final IRubyObject filename = args[i];
            if (0 != runtime.getPosix().lchown(filename.toString(), owner, group)) {
                throw runtime.newErrnoFromLastPOSIXErrno();
            }
            ++count;
        }
        return runtime.newFixnum(count);
    }
    
    @JRubyMethod(required = 2, meta = true, backtrace = true)
    public static IRubyObject link(final ThreadContext context, final IRubyObject recv, final IRubyObject from, final IRubyObject to) {
        final Ruby runtime = context.getRuntime();
        final RubyString fromStr = RubyString.stringValue(from);
        final RubyString toStr = RubyString.stringValue(to);
        final int ret = runtime.getPosix().link(fromStr.getUnicodeValue(), toStr.getUnicodeValue());
        if (ret != 0) {
            throw runtime.newErrnoEEXISTError(fromStr + " or " + toStr);
        }
        return runtime.newFixnum(ret);
    }
    
    @JRubyMethod(name = { "mtime" }, required = 1, meta = true)
    public static IRubyObject mtime(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        return getLastModified(context.getRuntime(), get_path(context, filename).getUnicodeValue());
    }
    
    @JRubyMethod(required = 2, meta = true)
    public static IRubyObject rename(final ThreadContext context, final IRubyObject recv, final IRubyObject oldName, final IRubyObject newName) {
        final Ruby runtime = context.getRuntime();
        final RubyString oldNameString = RubyString.stringValue(oldName);
        final RubyString newNameString = RubyString.stringValue(newName);
        runtime.checkSafeString(oldNameString);
        runtime.checkSafeString(newNameString);
        final String newNameJavaString = newNameString.getUnicodeValue();
        final String oldNameJavaString = oldNameString.getUnicodeValue();
        final JRubyFile oldFile = JRubyFile.create(runtime.getCurrentDirectory(), oldNameJavaString);
        final JRubyFile newFile = JRubyFile.create(runtime.getCurrentDirectory(), newNameJavaString);
        if (!oldFile.exists() || !newFile.getParentFile().exists()) {
            throw runtime.newErrnoENOENTError(oldNameJavaString + " or " + newNameJavaString);
        }
        final JRubyFile dest = JRubyFile.create(runtime.getCurrentDirectory(), newNameJavaString);
        if (oldFile.renameTo(dest)) {
            return RubyFixnum.zero(runtime);
        }
        if (newFile.exists()) {
            runtime.getPosix().chmod(newNameJavaString, 438);
            newFile.delete();
        }
        if (oldFile.renameTo(dest)) {
            return RubyFixnum.zero(runtime);
        }
        throw runtime.newErrnoEACCESError(oldNameJavaString + " or " + newNameJavaString);
    }
    
    @JRubyMethod(required = 1, meta = true)
    public static RubyArray split(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final RubyString filename = get_path(context, arg);
        return context.getRuntime().newArray(dirname(context, recv, filename), basename(context, recv, new IRubyObject[] { filename }));
    }
    
    @JRubyMethod(required = 2, meta = true)
    public static IRubyObject symlink(final ThreadContext context, final IRubyObject recv, final IRubyObject from, final IRubyObject to) {
        final Ruby runtime = context.getRuntime();
        final RubyString fromStr = get_path(context, from);
        final RubyString toStr = get_path(context, to);
        String tovalue = toStr.getUnicodeValue();
        tovalue = JRubyFile.create(runtime.getCurrentDirectory(), tovalue).getAbsolutePath();
        try {
            if (runtime.getPosix().symlink(fromStr.getUnicodeValue(), tovalue) == -1) {
                throw runtime.newErrnoEEXISTError(fromStr + " or " + toStr);
            }
        }
        catch (UnsatisfiedLinkError ule) {
            throw runtime.newNotImplementedError("symlink() function is unimplemented on this machine");
        }
        return runtime.newFixnum(0);
    }
    
    @JRubyMethod(required = 1, meta = true)
    public static IRubyObject readlink(final ThreadContext context, final IRubyObject recv, final IRubyObject path) {
        final Ruby runtime = context.getRuntime();
        try {
            final String realPath = runtime.getPosix().readlink(path.convertToString().getUnicodeValue());
            if (!RubyFileTest.exist_p(recv, path).isTrue()) {
                throw runtime.newErrnoENOENTError(path.toString());
            }
            if (!RubyFileTest.symlink_p(recv, path).isTrue()) {
                throw runtime.newErrnoEINVALError(path.toString());
            }
            if (realPath == null) {}
            return runtime.newString(realPath);
        }
        catch (IOException e) {
            throw runtime.newIOError(e.getMessage());
        }
    }
    
    @JRubyMethod(required = 2, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject truncate(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        return truncateCommon(context, recv, arg1, arg2);
    }
    
    @JRubyMethod(name = { "truncate" }, required = 2, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject truncate19(final ThreadContext context, final IRubyObject recv, IRubyObject arg1, final IRubyObject arg2) {
        if (!(arg1 instanceof RubyString) && arg1.respondsTo("to_path")) {
            arg1 = arg1.callMethod(context, "to_path");
        }
        return truncateCommon(context, recv, arg1, arg2);
    }
    
    private static IRubyObject truncateCommon(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        final RubyString filename = arg1.convertToString();
        final Ruby runtime = context.getRuntime();
        final RubyInteger newLength = arg2.convertToInteger();
        final File childFile = new File(filename.getUnicodeValue());
        File testFile;
        if (childFile.isAbsolute()) {
            testFile = childFile;
        }
        else {
            testFile = new File(runtime.getCurrentDirectory(), filename.getByteList().toString());
        }
        if (!testFile.exists()) {
            throw runtime.newErrnoENOENTError(filename.getByteList().toString());
        }
        if (newLength.getLongValue() < 0L) {
            throw runtime.newErrnoEINVALError(filename.toString());
        }
        final IRubyObject[] args = { filename, runtime.newString("r+") };
        final RubyFile file = (RubyFile)RubyIO.open(context, recv, args, Block.NULL_BLOCK);
        file.truncate(context, newLength);
        file.close();
        return RubyFixnum.zero(runtime);
    }
    
    @JRubyMethod(meta = true, optional = 1)
    public static IRubyObject umask(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        int oldMask = 0;
        if (args.length == 0) {
            oldMask = getUmaskSafe(runtime);
        }
        else if (args.length == 1) {
            final int newMask = (int)args[0].convertToInteger().getLongValue();
            synchronized (RubyFile._umaskLock) {
                oldMask = runtime.getPosix().umask(newMask);
                RubyFile._cachedUmask = newMask;
            }
        }
        else {
            runtime.newArgumentError("wrong number of arguments");
        }
        return runtime.newFixnum(oldMask);
    }
    
    @JRubyMethod(required = 2, rest = true, meta = true)
    public static IRubyObject utime(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        long[] atimeval = null;
        long[] mtimeval = null;
        if (args[0] != runtime.getNil() || args[1] != runtime.getNil()) {
            atimeval = extractTimeval(runtime, args[0]);
            mtimeval = extractTimeval(runtime, args[1]);
        }
        for (int i = 2, j = args.length; i < j; ++i) {
            final RubyString filename = get_path(context, args[i]);
            runtime.checkSafeString(filename);
            final JRubyFile fileToTouch = JRubyFile.create(runtime.getCurrentDirectory(), filename.getUnicodeValue());
            if (!fileToTouch.exists()) {
                throw runtime.newErrnoENOENTError(filename.toString());
            }
            runtime.getPosix().utimes(fileToTouch.getAbsolutePath(), atimeval, mtimeval);
        }
        return runtime.newFixnum(args.length - 2);
    }
    
    @JRubyMethod(name = { "unlink", "delete" }, rest = true, meta = true)
    public static IRubyObject unlink(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < args.length; ++i) {
            final RubyString filename = get_path(context, args[i]);
            runtime.checkSafeString(filename);
            final JRubyFile lToDelete = JRubyFile.create(runtime.getCurrentDirectory(), filename.getUnicodeValue());
            final boolean isSymlink = RubyFileTest.symlink_p(recv, filename).isTrue();
            if (!lToDelete.exists() && !isSymlink) {
                throw runtime.newErrnoENOENTError(filename.getUnicodeValue());
            }
            if (lToDelete.isDirectory() && !isSymlink) {
                throw runtime.newErrnoEPERMError(filename.getUnicodeValue());
            }
            if (!lToDelete.delete()) {
                throw runtime.newErrnoEACCESError(filename.getUnicodeValue());
            }
        }
        return runtime.newFixnum(args.length);
    }
    
    @JRubyMethod(name = { "size" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject size(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if ((this.openFile.getMode() & 0x2) != 0x0) {
            this.flush();
        }
        try {
            final FileStat stat = runtime.getPosix().fstat(this.getOpenFileChecked().getMainStreamSafe().getDescriptor().getFileDescriptor());
            if (stat == null) {
                throw runtime.newErrnoEACCESError(this.path);
            }
            return runtime.newFixnum(stat.st_size());
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
    }
    
    public static ZipEntry getFileEntry(final ZipFile zf, final String path) throws IOException {
        ZipEntry entry = zf.getEntry(path);
        if (entry == null) {
            final String prefix = new File(".").getCanonicalPath();
            entry = zf.getEntry(new File(path).getCanonicalPath().substring(prefix.length() + 1).replaceAll("\\\\", "/"));
        }
        return entry;
    }
    
    public static ZipEntry getDirOrFileEntry(final ZipFile zf, final String path) throws IOException {
        ZipEntry entry = zf.getEntry(path + "/");
        if (entry == null) {
            final String prefix = new File(".").getCanonicalPath();
            entry = zf.getEntry(new File(path + "/").getCanonicalPath().substring(prefix.length() + 1).replaceAll("\\\\", "/"));
            if (entry == null) {
                entry = getFileEntry(zf, path);
            }
        }
        return entry;
    }
    
    private static int getUmaskSafe(final Ruby runtime) {
        synchronized (RubyFile._umaskLock) {
            final int umask = runtime.getPosix().umask(RubyFile._cachedUmask);
            if (RubyFile._cachedUmask != umask) {
                runtime.getPosix().umask(umask);
                RubyFile._cachedUmask = umask;
            }
            return umask;
        }
    }
    
    private static long[] extractTimeval(final Ruby runtime, final IRubyObject value) {
        final long[] timeval = new long[2];
        if (value instanceof RubyFloat) {
            timeval[0] = (Platform.IS_32_BIT ? RubyNumeric.num2int(value) : RubyNumeric.num2long(value));
            final double fraction = ((RubyFloat)value).getDoubleValue() % 1.0;
            timeval[1] = (long)(fraction * 1000000.0 + 0.5);
        }
        else if (value instanceof RubyNumeric) {
            timeval[0] = (Platform.IS_32_BIT ? RubyNumeric.num2int(value) : RubyNumeric.num2long(value));
            timeval[1] = 0L;
        }
        else {
            RubyTime time;
            if (value instanceof RubyTime) {
                time = (RubyTime)value;
            }
            else {
                time = (RubyTime)TypeConverter.convertToType(value, runtime.getTime(), "to_time", true);
            }
            timeval[0] = (Platform.IS_32_BIT ? RubyNumeric.num2int(time.to_i()) : RubyNumeric.num2long(time.to_i()));
            timeval[1] = (Platform.IS_32_BIT ? RubyNumeric.num2int(time.usec()) : RubyNumeric.num2long(time.usec()));
        }
        return timeval;
    }
    
    private static IRubyObject getLastModified(final Ruby runtime, final String path) {
        final JRubyFile file = JRubyFile.create(runtime.getCurrentDirectory(), path);
        if (!file.exists()) {
            throw runtime.newErrnoENOENTError(path);
        }
        return runtime.newTime(file.lastModified());
    }
    
    private void checkClosed(final ThreadContext context) {
        this.openFile.checkClosed(context.getRuntime());
    }
    
    static {
        RubyFile._cachedUmask = 0;
        _umaskLock = new Object();
        if (Platform.IS_WINDOWS) {
            FNM_SYSCASE = 8;
        }
        else {
            FNM_SYSCASE = 0;
        }
        RubyFile.FILE_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyFile instance = new RubyFile(runtime, klass);
                instance.setMetaClass(klass);
                return instance;
            }
        };
        RubyFile.URI_PREFIX = Pattern.compile("^[a-z]{2,}:(.*)");
        SLASHES = new String[] { "", "/", "//" };
    }
    
    @JRubyModule(name = { "File::Constants" })
    public static class Constants
    {
    }
}

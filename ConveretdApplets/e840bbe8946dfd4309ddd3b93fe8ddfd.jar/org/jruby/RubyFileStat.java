// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.util.zip.ZipFile;
import org.jruby.ext.posix.util.Platform;
import java.io.FileDescriptor;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.ext.posix.FileStat;
import org.jruby.util.JRubyFile;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "File::Stat" }, include = { "Comparable" })
public class RubyFileStat extends RubyObject
{
    private static final long serialVersionUID = 1L;
    private static final int S_IRUGO = 292;
    private static final int S_IWUGO = 146;
    private static final int S_IXUGO = 73;
    private JRubyFile file;
    private FileStat stat;
    private static ObjectAllocator ALLOCATOR;
    
    public static RubyClass createFileStatClass(final Ruby runtime) {
        final RubyClass fileStatClass = runtime.getFile().defineClassUnder("Stat", runtime.getObject(), RubyFileStat.ALLOCATOR);
        runtime.setFileStat(fileStatClass);
        fileStatClass.includeModule(runtime.fastGetModule("Comparable"));
        fileStatClass.defineAnnotatedMethods(RubyFileStat.class);
        return fileStatClass;
    }
    
    protected RubyFileStat(final Ruby runtime, final RubyClass clazz) {
        super(runtime, clazz);
    }
    
    public static RubyFileStat newFileStat(final Ruby runtime, final String filename, final boolean lstat) {
        final RubyFileStat stat = new RubyFileStat(runtime, runtime.getFileStat());
        stat.setup(filename, lstat);
        return stat;
    }
    
    public static RubyFileStat newFileStat(final Ruby runtime, final FileDescriptor descriptor) {
        final RubyFileStat stat = new RubyFileStat(runtime, runtime.getFileStat());
        stat.setup(descriptor);
        return stat;
    }
    
    private void setup(final FileDescriptor descriptor) {
        this.stat = this.getRuntime().getPosix().fstat(descriptor);
    }
    
    private void setup(String filename, final boolean lstat) {
        if (Platform.IS_WINDOWS && filename.length() == 2 && filename.charAt(1) == ':' && Character.isLetter(filename.charAt(0))) {
            filename += "/";
        }
        if (filename.startsWith("file:") && filename.indexOf(33) != -1) {
            String zipFileEntry = filename.substring(filename.indexOf("!") + 1);
            if (zipFileEntry.length() <= 0) {
                throw this.getRuntime().newErrnoENOENTError("invalid jar/file URL: " + filename);
            }
            if (zipFileEntry.charAt(0) == '/') {
                if (zipFileEntry.length() <= 1) {
                    throw this.getRuntime().newErrnoENOENTError("invalid jar/file URL: " + filename);
                }
                zipFileEntry = zipFileEntry.substring(1);
            }
            final String zipfilename = filename.substring(5, filename.indexOf("!"));
            try {
                final ZipFile zipFile = new ZipFile(zipfilename);
                final ZipEntry zipEntry = RubyFile.getFileEntry(zipFile, zipFileEntry);
                if (zipEntry == null) {
                    throw this.getRuntime().newErrnoENOENTError("invalid jar/file URL: " + filename);
                }
                this.stat = new ZipFileStat(zipEntry);
                return;
            }
            catch (IOException ioe) {
                filename = zipfilename;
            }
        }
        this.file = JRubyFile.create(this.getRuntime().getCurrentDirectory(), filename);
        if (lstat) {
            this.stat = this.getRuntime().getPosix().lstat(this.file.getAbsolutePath());
        }
        else {
            this.stat = this.getRuntime().getPosix().stat(this.file.getAbsolutePath());
        }
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final IRubyObject fname, final Block unusedBlock) {
        this.setup(fname.convertToString().toString(), false);
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(IRubyObject fname, final Block unusedBlock) {
        if (!(fname instanceof RubyString) && fname.respondsTo("to_path")) {
            fname = fname.callMethod(this.getRuntime().getCurrentContext(), "to_path");
        }
        return this.initialize(fname, unusedBlock);
    }
    
    @JRubyMethod(name = { "atime" })
    public IRubyObject atime() {
        return this.getRuntime().newTime(this.stat.atime() * 1000L);
    }
    
    @JRubyMethod(name = { "blksize" })
    public RubyFixnum blksize() {
        return this.getRuntime().newFixnum(this.stat.blockSize());
    }
    
    @JRubyMethod(name = { "blockdev?" })
    public IRubyObject blockdev_p() {
        return this.getRuntime().newBoolean(this.stat.isBlockDev());
    }
    
    @JRubyMethod(name = { "blocks" })
    public IRubyObject blocks() {
        return this.getRuntime().newFixnum(this.stat.blocks());
    }
    
    @JRubyMethod(name = { "chardev?" })
    public IRubyObject chardev_p() {
        return this.getRuntime().newBoolean(this.stat.isCharDev());
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject cmp(final IRubyObject other) {
        if (!(other instanceof RubyFileStat)) {
            this.getRuntime().getNil();
        }
        final long time1 = this.stat.mtime();
        final long time2 = ((RubyFileStat)other).stat.mtime();
        if (time1 == time2) {
            return this.getRuntime().newFixnum(0);
        }
        if (time1 < time2) {
            return this.getRuntime().newFixnum(-1);
        }
        return this.getRuntime().newFixnum(1);
    }
    
    @JRubyMethod(name = { "ctime" })
    public IRubyObject ctime() {
        return this.getRuntime().newTime(this.stat.ctime() * 1000L);
    }
    
    @JRubyMethod(name = { "dev" })
    public IRubyObject dev() {
        return this.getRuntime().newFixnum(this.stat.dev());
    }
    
    @JRubyMethod(name = { "dev_major" })
    public IRubyObject devMajor() {
        return this.getRuntime().newFixnum(this.stat.major(this.stat.dev()));
    }
    
    @JRubyMethod(name = { "dev_minor" })
    public IRubyObject devMinor() {
        return this.getRuntime().newFixnum(this.stat.minor(this.stat.dev()));
    }
    
    @JRubyMethod(name = { "directory?" })
    public RubyBoolean directory_p() {
        return this.getRuntime().newBoolean(this.stat.isDirectory());
    }
    
    @JRubyMethod(name = { "executable?" })
    public IRubyObject executable_p() {
        return this.getRuntime().newBoolean(this.stat.isExecutable());
    }
    
    @JRubyMethod(name = { "executable_real?" })
    public IRubyObject executableReal_p() {
        return this.getRuntime().newBoolean(this.stat.isExecutableReal());
    }
    
    @JRubyMethod(name = { "file?" })
    public RubyBoolean file_p() {
        return this.getRuntime().newBoolean(this.stat.isFile());
    }
    
    @JRubyMethod(name = { "ftype" })
    public RubyString ftype() {
        return this.getRuntime().newString(this.stat.ftype());
    }
    
    @JRubyMethod(name = { "gid" })
    public IRubyObject gid() {
        return this.getRuntime().newFixnum(this.stat.gid());
    }
    
    @JRubyMethod(name = { "grpowned?" })
    public IRubyObject group_owned_p() {
        return this.getRuntime().newBoolean(this.stat.isGroupOwned());
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject original) {
        if (!(original instanceof RubyFileStat)) {
            throw this.getRuntime().newTypeError("wrong argument class");
        }
        final RubyFileStat originalFileStat = (RubyFileStat)original;
        this.file = originalFileStat.file;
        this.stat = originalFileStat.stat;
        return this;
    }
    
    @JRubyMethod(name = { "ino" })
    public IRubyObject ino() {
        return this.getRuntime().newFixnum(this.stat.ino());
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect() {
        final StringBuilder buf = new StringBuilder("#<");
        buf.append(this.getMetaClass().getRealClass().getName());
        buf.append(" ");
        try {
            buf.append("dev=0x").append(Long.toHexString(this.stat.dev()));
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        try {
            buf.append("ino=").append(this.stat.ino());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        buf.append("mode=0").append(Integer.toOctalString(this.stat.mode())).append(", ");
        try {
            buf.append("nlink=").append(this.stat.nlink());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        try {
            buf.append("uid=").append(this.stat.uid());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        try {
            buf.append("gid=").append(this.stat.gid());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        try {
            buf.append("rdev=0x").append(Long.toHexString(this.stat.rdev()));
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        buf.append("size=").append(this.sizeInternal()).append(", ");
        try {
            buf.append("blksize=").append(this.stat.blockSize());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        try {
            buf.append("blocks=").append(this.stat.blocks());
        }
        catch (Exception e) {}
        finally {
            buf.append(", ");
        }
        buf.append("atime=").append(this.atime()).append(", ");
        buf.append("mtime=").append(this.mtime()).append(", ");
        buf.append("ctime=").append(this.ctime());
        buf.append(">");
        return this.getRuntime().newString(buf.toString());
    }
    
    @JRubyMethod(name = { "uid" })
    public IRubyObject uid() {
        return this.getRuntime().newFixnum(this.stat.uid());
    }
    
    @JRubyMethod(name = { "mode" })
    public IRubyObject mode() {
        return this.getRuntime().newFixnum(this.stat.mode());
    }
    
    @JRubyMethod(name = { "mtime" })
    public IRubyObject mtime() {
        return this.getRuntime().newTime(this.stat.mtime() * 1000L);
    }
    
    public IRubyObject mtimeEquals(final IRubyObject other) {
        return this.getRuntime().newBoolean(this.stat.mtime() == newFileStat(this.getRuntime(), other.convertToString().toString(), false).stat.mtime());
    }
    
    public IRubyObject mtimeGreaterThan(final IRubyObject other) {
        return this.getRuntime().newBoolean(this.stat.mtime() > newFileStat(this.getRuntime(), other.convertToString().toString(), false).stat.mtime());
    }
    
    public IRubyObject mtimeLessThan(final IRubyObject other) {
        return this.getRuntime().newBoolean(this.stat.mtime() < newFileStat(this.getRuntime(), other.convertToString().toString(), false).stat.mtime());
    }
    
    @JRubyMethod(name = { "nlink" })
    public IRubyObject nlink() {
        return this.getRuntime().newFixnum(this.stat.nlink());
    }
    
    @JRubyMethod(name = { "owned?" })
    public IRubyObject owned_p() {
        return this.getRuntime().newBoolean(this.stat.isOwned());
    }
    
    @JRubyMethod(name = { "pipe?" })
    public IRubyObject pipe_p() {
        return this.getRuntime().newBoolean(this.stat.isNamedPipe());
    }
    
    @JRubyMethod(name = { "rdev" })
    public IRubyObject rdev() {
        return this.getRuntime().newFixnum(this.stat.rdev());
    }
    
    @JRubyMethod(name = { "rdev_major" })
    public IRubyObject rdevMajor() {
        return this.getRuntime().newFixnum(this.stat.major(this.stat.rdev()));
    }
    
    @JRubyMethod(name = { "rdev_minor" })
    public IRubyObject rdevMinor() {
        return this.getRuntime().newFixnum(this.stat.minor(this.stat.rdev()));
    }
    
    @JRubyMethod(name = { "readable?" })
    public IRubyObject readable_p() {
        return this.getRuntime().newBoolean(this.stat.isReadable());
    }
    
    @JRubyMethod(name = { "readable_real?" })
    public IRubyObject readableReal_p() {
        return this.getRuntime().newBoolean(this.stat.isReadableReal());
    }
    
    @JRubyMethod(name = { "setgid?" })
    public IRubyObject setgid_p() {
        return this.getRuntime().newBoolean(this.stat.isSetgid());
    }
    
    @JRubyMethod(name = { "setuid?" })
    public IRubyObject setuid_p() {
        return this.getRuntime().newBoolean(this.stat.isSetuid());
    }
    
    private long sizeInternal() {
        if (Platform.IS_WINDOWS && this.file != null) {
            try {
                return this.file.length();
            }
            catch (SecurityException ex) {
                return 0L;
            }
        }
        return this.stat.st_size();
    }
    
    @JRubyMethod(name = { "size" })
    public IRubyObject size() {
        return this.getRuntime().newFixnum(this.sizeInternal());
    }
    
    @JRubyMethod(name = { "size?" })
    public IRubyObject size_p() {
        final long size = this.sizeInternal();
        if (size == 0L) {
            return this.getRuntime().getNil();
        }
        return this.getRuntime().newFixnum(size);
    }
    
    @JRubyMethod(name = { "socket?" })
    public IRubyObject socket_p() {
        return this.getRuntime().newBoolean(this.stat.isSocket());
    }
    
    @JRubyMethod(name = { "sticky?" })
    public IRubyObject sticky_p() {
        return this.getRuntime().newBoolean(this.stat.isSticky());
    }
    
    @JRubyMethod(name = { "symlink?" })
    public IRubyObject symlink_p() {
        return this.getRuntime().newBoolean(this.stat.isSymlink());
    }
    
    @JRubyMethod(name = { "writable?" })
    public IRubyObject writable_p() {
        return this.getRuntime().newBoolean(this.stat.isWritable());
    }
    
    @JRubyMethod(name = { "writable_real?" })
    public IRubyObject writableReal_p() {
        return this.getRuntime().newBoolean(this.stat.isWritableReal());
    }
    
    @JRubyMethod(name = { "zero?" })
    public IRubyObject zero_p() {
        return this.getRuntime().newBoolean(this.stat.isEmpty());
    }
    
    @JRubyMethod(name = { "world_readable?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject worldReadable(final ThreadContext context) {
        return this.getWorldMode(context, 4);
    }
    
    @JRubyMethod(name = { "world_writable?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject worldWritable(final ThreadContext context) {
        return this.getWorldMode(context, 2);
    }
    
    private IRubyObject getWorldMode(final ThreadContext context, final int mode) {
        if ((this.stat.mode() & mode) == mode) {
            return RubyNumeric.int2fix(context.getRuntime(), this.stat.mode() & 0x1FF);
        }
        return context.getRuntime().getNil();
    }
    
    static {
        RubyFileStat.ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyFileStat(runtime, klass);
            }
        };
    }
    
    public static class ZipFileStat implements FileStat
    {
        private final ZipEntry zipEntry;
        
        public ZipFileStat(final ZipEntry zipEntry) {
            this.zipEntry = zipEntry;
        }
        
        public long atime() {
            return this.zipEntry.getTime();
        }
        
        public long blocks() {
            return this.zipEntry.getSize();
        }
        
        public long blockSize() {
            return 1L;
        }
        
        public long ctime() {
            return this.zipEntry.getTime();
        }
        
        public long dev() {
            return -1L;
        }
        
        public String ftype() {
            return "zip file entry";
        }
        
        public int gid() {
            return -1;
        }
        
        public boolean groupMember(final int i) {
            return false;
        }
        
        public long ino() {
            return -1L;
        }
        
        public boolean isBlockDev() {
            return false;
        }
        
        public boolean isCharDev() {
            return false;
        }
        
        public boolean isDirectory() {
            return this.zipEntry.isDirectory();
        }
        
        public boolean isEmpty() {
            return this.zipEntry.getSize() == 0L;
        }
        
        public boolean isExecutable() {
            return false;
        }
        
        public boolean isExecutableReal() {
            return false;
        }
        
        public boolean isFifo() {
            return false;
        }
        
        public boolean isFile() {
            return !this.zipEntry.isDirectory();
        }
        
        public boolean isGroupOwned() {
            return false;
        }
        
        public boolean isIdentical(final FileStat fs) {
            return fs instanceof ZipFileStat && ((ZipFileStat)fs).zipEntry.equals(this.zipEntry);
        }
        
        public boolean isNamedPipe() {
            return false;
        }
        
        public boolean isOwned() {
            return false;
        }
        
        public boolean isROwned() {
            return false;
        }
        
        public boolean isReadable() {
            return true;
        }
        
        public boolean isReadableReal() {
            return true;
        }
        
        public boolean isWritable() {
            return false;
        }
        
        public boolean isWritableReal() {
            return false;
        }
        
        public boolean isSetgid() {
            return false;
        }
        
        public boolean isSetuid() {
            return false;
        }
        
        public boolean isSocket() {
            return false;
        }
        
        public boolean isSticky() {
            return false;
        }
        
        public boolean isSymlink() {
            return false;
        }
        
        public int major(final long l) {
            return -1;
        }
        
        public int minor(final long l) {
            return -1;
        }
        
        public int mode() {
            return -1;
        }
        
        public long mtime() {
            return this.zipEntry.getTime();
        }
        
        public int nlink() {
            return -1;
        }
        
        public long rdev() {
            return -1L;
        }
        
        public long st_size() {
            return this.zipEntry.getSize();
        }
        
        public int uid() {
            return 0;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.FileInputStream;
import java.io.IOException;
import org.jruby.ext.posix.util.Platform;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.NormalizedFile;
import java.util.Iterator;
import org.jruby.util.Dir;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyMethod;
import java.util.List;
import java.util.Collection;
import java.io.File;
import java.util.ArrayList;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.JRubyFile;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Dir" }, include = { "Enumerable" })
public class RubyDir extends RubyObject
{
    private RubyString path;
    protected JRubyFile dir;
    private long lastModified;
    private String[] snapshot;
    private int pos;
    private boolean isOpen;
    private static final ObjectAllocator DIR_ALLOCATOR;
    
    public RubyDir(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.lastModified = Long.MIN_VALUE;
        this.isOpen = true;
    }
    
    public static RubyClass createDirClass(final Ruby runtime) {
        final RubyClass dirClass = runtime.defineClass("Dir", runtime.getObject(), RubyDir.DIR_ALLOCATOR);
        runtime.setDir(dirClass);
        dirClass.index = 35;
        dirClass.setReifiedClass(RubyDir.class);
        dirClass.includeModule(runtime.getEnumerable());
        dirClass.defineAnnotatedMethods(RubyDir.class);
        return dirClass;
    }
    
    private final void checkDir() {
        if (!this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: operation on untainted Dir");
        }
        this.testFrozen("Dir");
        this.update();
        if (!this.isOpen) {
            throw this.getRuntime().newIOError("closed directory");
        }
    }
    
    private void update() {
        if (this.snapshot == null || (this.dir.exists() && this.dir.lastModified() > this.lastModified)) {
            this.lastModified = this.dir.lastModified();
            final List<String> snapshotList = new ArrayList<String>();
            snapshotList.add(".");
            snapshotList.add("..");
            snapshotList.addAll(getContents(this.dir));
            this.snapshot = snapshotList.toArray(new String[snapshotList.size()]);
        }
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final IRubyObject arg) {
        final RubyString newPath = arg.convertToString();
        this.path = newPath;
        this.pos = 0;
        this.getRuntime().checkSafeString(newPath);
        final String adjustedPath = RubyFile.adjustRootPathOnWindows(this.getRuntime(), newPath.toString(), null);
        checkDirIsTwoSlashesOnWindows(this.getRuntime(), adjustedPath);
        this.dir = JRubyFile.create(this.getRuntime().getCurrentDirectory(), adjustedPath);
        final List<String> snapshotList = getEntries(this.getRuntime(), adjustedPath);
        this.snapshot = snapshotList.toArray(new String[snapshotList.size()]);
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(IRubyObject arg) {
        if (arg.respondsTo("to_path")) {
            arg = arg.callMethod(this.getRuntime().getCurrentContext(), "to_path");
        }
        return this.initialize(arg);
    }
    
    private static List<ByteList> dirGlobs(final String cwd, final IRubyObject[] args, final int flags) {
        final List<ByteList> dirs = new ArrayList<ByteList>();
        for (int i = 0; i < args.length; ++i) {
            final ByteList globPattern = args[i].convertToString().getByteList();
            dirs.addAll(Dir.push_glob(cwd, globPattern, flags));
        }
        return dirs;
    }
    
    private static IRubyObject asRubyStringList(final Ruby runtime, final List<ByteList> dirs) {
        final List<RubyString> allFiles = new ArrayList<RubyString>();
        for (final ByteList dir : dirs) {
            allFiles.add(RubyString.newString(runtime, dir));
        }
        final IRubyObject[] tempFileList = new IRubyObject[allFiles.size()];
        allFiles.toArray(tempFileList);
        return runtime.newArrayNoCopy(tempFileList);
    }
    
    private static String getCWD(final Ruby runtime) {
        try {
            return new NormalizedFile(runtime.getCurrentDirectory()).getCanonicalPath();
        }
        catch (Exception e) {
            return runtime.getCurrentDirectory();
        }
    }
    
    @JRubyMethod(name = { "[]" }, required = 1, rest = true, meta = true)
    public static IRubyObject aref(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        List<ByteList> dirs;
        if (args.length == 1) {
            ByteList globPattern = null;
            if (runtime.is1_9() && args[0].respondsTo("to_path")) {
                globPattern = args[0].callMethod(context, "to_path").convertToString().getByteList();
            }
            else {
                globPattern = args[0].convertToString().getByteList();
            }
            dirs = Dir.push_glob(getCWD(runtime), globPattern, 0);
        }
        else {
            dirs = dirGlobs(getCWD(runtime), args, 0);
        }
        return asRubyStringList(runtime, dirs);
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true)
    public static IRubyObject glob(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int flags = (args.length == 2) ? RubyNumeric.num2int(args[1]) : 0;
        final IRubyObject tmp = args[0].checkArrayType();
        List<ByteList> dirs;
        if (tmp.isNil()) {
            ByteList globPattern = null;
            if (runtime.is1_9() && args[0].respondsTo("to_path")) {
                globPattern = args[0].callMethod(context, "to_path").convertToString().getByteList();
            }
            else {
                globPattern = args[0].convertToString().getByteList();
            }
            dirs = Dir.push_glob(runtime.getCurrentDirectory(), globPattern, flags);
        }
        else {
            dirs = dirGlobs(getCWD(runtime), ((RubyArray)tmp).toJavaArray(), flags);
        }
        if (block.isGiven()) {
            for (int i = 0; i < dirs.size(); ++i) {
                block.yield(context, RubyString.newString(runtime, dirs.get(i)));
            }
            return runtime.getNil();
        }
        return asRubyStringList(runtime, dirs);
    }
    
    @JRubyMethod(name = { "entries" })
    public RubyArray entries() {
        return this.getRuntime().newArrayNoCopy(JavaUtil.convertJavaArrayToRuby(this.getRuntime(), this.snapshot));
    }
    
    @JRubyMethod(name = { "entries" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static RubyArray entries(final IRubyObject recv, final IRubyObject path) {
        return entriesCommon(recv.getRuntime(), path.convertToString().getUnicodeValue());
    }
    
    @JRubyMethod(name = { "entries" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static RubyArray entries19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return entriesCommon(context.getRuntime(), getPath19(context, arg));
    }
    
    @JRubyMethod(name = { "entries" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static RubyArray entries19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg, final IRubyObject opts) {
        return entriesCommon(context.getRuntime(), getPath19(context, arg));
    }
    
    private static RubyArray entriesCommon(final Ruby runtime, final String path) {
        final String adjustedPath = RubyFile.adjustRootPathOnWindows(runtime, path, null);
        checkDirIsTwoSlashesOnWindows(runtime, adjustedPath);
        final Object[] files = getEntries(runtime, adjustedPath).toArray();
        return runtime.newArrayNoCopy(JavaUtil.convertJavaArrayToRuby(runtime, files));
    }
    
    private static List<String> getEntries(final Ruby runtime, final String path) {
        if (!RubyFileTest.directory_p(runtime, RubyString.newString(runtime, path)).isTrue()) {
            throw runtime.newErrnoENOENTError("No such directory");
        }
        if (path.startsWith("file:")) {
            return entriesIntoAJarFile(runtime, path);
        }
        return entriesIntoADirectory(runtime, path);
    }
    
    private static List<String> entriesIntoADirectory(final Ruby runtime, final String path) {
        final JRubyFile directory = JRubyFile.create(runtime.getCurrentDirectory(), path);
        final List<String> fileList = getContents(directory);
        fileList.add(0, ".");
        fileList.add(1, "..");
        return fileList;
    }
    
    private static List<String> entriesIntoAJarFile(final Ruby runtime, final String path) {
        final List<ByteList> dirs = Dir.push_glob(runtime.getCurrentDirectory(), RubyString.newString(runtime, path + "/*").getByteList(), 4);
        final List<String> fileList = new ArrayList<String>();
        for (final ByteList file : dirs) {
            final String[] split = file.toString().split("/");
            fileList.add(split[split.length - 1]);
        }
        return fileList;
    }
    
    private static void checkDirIsTwoSlashesOnWindows(final Ruby runtime, final String path) {
        if (Platform.IS_WINDOWS && ("//".equals(path) || "\\\\".equals(path))) {
            throw runtime.newErrnoEINVALError("Invalid argument - " + path);
        }
    }
    
    @JRubyMethod(optional = 1, meta = true)
    public static IRubyObject chdir(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyString path = (args.length == 1) ? RubyFile.get_path(context, args[0]) : getHomeDirectoryPath(context);
        final String adjustedPath = RubyFile.adjustRootPathOnWindows(runtime, path.getUnicodeValue(), null);
        checkDirIsTwoSlashesOnWindows(runtime, adjustedPath);
        JRubyFile dir = getDir(runtime, adjustedPath, true);
        String realPath = null;
        final String oldCwd = runtime.getCurrentDirectory();
        try {
            realPath = dir.getCanonicalPath();
        }
        catch (IOException e) {
            realPath = dir.getAbsolutePath();
        }
        IRubyObject result = null;
        if (block.isGiven()) {
            runtime.setCurrentDirectory(realPath);
            try {
                result = block.yield(context, path);
            }
            finally {
                dir = getDir(runtime, oldCwd, true);
                runtime.setCurrentDirectory(oldCwd);
            }
        }
        else {
            runtime.setCurrentDirectory(realPath);
            result = runtime.newFixnum(0);
        }
        return result;
    }
    
    @JRubyMethod(name = { "chroot" }, required = 1, meta = true)
    public static IRubyObject chroot(final IRubyObject recv, final IRubyObject path) {
        throw recv.getRuntime().newNotImplementedError("chroot not implemented: chroot is non-portable and is not supported.");
    }
    
    @JRubyMethod(name = { "rmdir", "unlink", "delete" }, required = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject rmdir(final IRubyObject recv, final IRubyObject path) {
        return rmdirCommon(recv.getRuntime(), path.convertToString().getUnicodeValue());
    }
    
    @JRubyMethod(name = { "rmdir", "unlink", "delete" }, required = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rmdir19(final ThreadContext context, final IRubyObject recv, final IRubyObject path) {
        return rmdirCommon(context.getRuntime(), getPath19(context, path));
    }
    
    private static IRubyObject rmdirCommon(final Ruby runtime, final String path) {
        final JRubyFile directory = getDir(runtime, path, true);
        if (!directory.delete()) {
            throw runtime.newSystemCallError("No such directory");
        }
        return runtime.newFixnum(0);
    }
    
    @JRubyMethod(meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject foreach(final ThreadContext context, final IRubyObject recv, final IRubyObject _path, final Block block) {
        final RubyString pathString = _path.convertToString();
        return foreachCommon(context, recv, context.getRuntime(), pathString, block);
    }
    
    @JRubyMethod(name = { "foreach" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject foreach19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg, final Block block) {
        final RubyString pathString = (RubyString)((arg instanceof RubyString) ? arg : arg.callMethod(context, "to_path").convertToString());
        return foreachCommon(context, recv, context.getRuntime(), pathString, block);
    }
    
    private static IRubyObject foreachCommon(final ThreadContext context, final IRubyObject recv, final Ruby runtime, final RubyString _path, final Block block) {
        if (block.isGiven()) {
            runtime.checkSafeString(_path);
            final RubyClass dirClass = runtime.getDir();
            final RubyDir dir = (RubyDir)dirClass.newInstance(context, new IRubyObject[] { _path }, block);
            dir.each(context, block);
            return runtime.getNil();
        }
        return RubyEnumerator.enumeratorize(runtime, recv, "foreach", _path);
    }
    
    @JRubyMethod(name = { "getwd", "pwd" }, meta = true)
    public static RubyString getwd(final IRubyObject recv) {
        final Ruby ruby = recv.getRuntime();
        final RubyString pwd = RubyString.newUnicodeString(ruby, getCWD(ruby));
        pwd.setTaint(true);
        return pwd;
    }
    
    @JRubyMethod(name = { "home" }, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject home(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        if (args.length > 0) {
            return getHomeDirectoryPath(context, args[0].toString());
        }
        return getHomeDirectoryPath(context);
    }
    
    @JRubyMethod(name = { "mkdir" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject mkdir(final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        final RubyString stringArg = args[0].convertToString();
        runtime.checkSafeString(stringArg);
        return mkdirCommon(runtime, stringArg.getUnicodeValue(), args);
    }
    
    @JRubyMethod(name = { "mkdir" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject mkdir19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return mkdirCommon(context.getRuntime(), getPath19(context, args[0]), args);
    }
    
    private static IRubyObject mkdirCommon(final Ruby runtime, final String path, final IRubyObject[] args) {
        File newDir = getDir(runtime, path, false);
        if (File.separatorChar == '\\') {
            newDir = new File(newDir.getPath());
        }
        final int mode = (args.length == 2) ? ((int)args[1].convertToInteger().getLongValue()) : 511;
        if (runtime.getPosix().mkdir(newDir.getAbsolutePath(), mode) < 0) {
            throw runtime.newSystemCallError("mkdir failed");
        }
        return RubyFixnum.zero(runtime);
    }
    
    @JRubyMethod(meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final Block block) {
        final RubyDir directory = (RubyDir)context.getRuntime().getDir().newInstance(context, new IRubyObject[] { path }, Block.NULL_BLOCK);
        if (!block.isGiven()) {
            return directory;
        }
        try {
            return block.yield(context, directory);
        }
        finally {
            directory.close();
        }
    }
    
    @JRubyMethod(name = { "open" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject open19(final ThreadContext context, final IRubyObject recv, final IRubyObject path, final Block block) {
        final RubyString pathString = (RubyString)((path instanceof RubyString) ? path : path.callMethod(context, "to_path").convertToString());
        return open(context, recv, pathString, block);
    }
    
    @JRubyMethod(name = { "close" })
    public IRubyObject close() {
        this.checkDir();
        this.isOpen = false;
        return this.getRuntime().getNil();
    }
    
    public IRubyObject each(final ThreadContext context, final Block block) {
        this.checkDir();
        final String[] contents = this.snapshot;
        this.pos = 0;
        while (this.pos < contents.length) {
            block.yield(context, this.getRuntime().newString(contents[this.pos]));
            ++this.pos;
        }
        return this;
    }
    
    @JRubyMethod(name = { "each" })
    public IRubyObject each19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    @JRubyMethod
    public IRubyObject inspect() {
        final Ruby runtime = this.getRuntime();
        final StringBuilder part = new StringBuilder();
        final String cname = this.getMetaClass().getRealClass().getName();
        part.append("#<").append(cname).append(":").append(this.path.asJavaString()).append(">");
        return runtime.newString(part.toString());
    }
    
    @JRubyMethod(name = { "tell", "pos" })
    public RubyInteger tell() {
        this.checkDir();
        return this.getRuntime().newFixnum(this.pos);
    }
    
    @JRubyMethod(name = { "seek" }, required = 1)
    public IRubyObject seek(final IRubyObject newPos) {
        this.checkDir();
        this.set_pos(newPos);
        return this;
    }
    
    @JRubyMethod(name = { "pos=" }, required = 1)
    public IRubyObject set_pos(final IRubyObject newPos) {
        final int pos2 = RubyNumeric.fix2int(newPos);
        if (pos2 >= 0) {
            this.pos = pos2;
        }
        return newPos;
    }
    
    @JRubyMethod(name = { "path" })
    public IRubyObject path(final ThreadContext context) {
        return this.path.strDup(context.getRuntime());
    }
    
    @JRubyMethod(name = { "read" })
    public IRubyObject read() {
        this.checkDir();
        if (this.pos >= this.snapshot.length) {
            return this.getRuntime().getNil();
        }
        final RubyString result = this.getRuntime().newString(this.snapshot[this.pos]);
        ++this.pos;
        return result;
    }
    
    @JRubyMethod(name = { "rewind" })
    public IRubyObject rewind() {
        if (!this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: can't close");
        }
        this.checkDir();
        this.pos = 0;
        return this;
    }
    
    @JRubyMethod(name = { "exists?", "exist?" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject exist(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        try {
            return context.getRuntime().newFileStat(getPath19(context, arg), false).directory_p();
        }
        catch (Exception e) {
            return context.getRuntime().newBoolean(false);
        }
    }
    
    protected static String getPath19(final ThreadContext context, final IRubyObject arg) {
        final RubyString pathObject = (RubyString)((arg instanceof RubyString) ? arg : arg.callMethod(context, "to_path").convertToString());
        return pathObject.getUnicodeValue();
    }
    
    protected static JRubyFile getDir(final Ruby runtime, final String path, final boolean mustExist) {
        String dir = path;
        final String[] pathParts = RubyFile.splitURI(path);
        if (pathParts != null) {
            if (!pathParts[0].equals("file:") || pathParts[1].length() <= 0 || pathParts[1].indexOf("!/") != -1) {
                throw runtime.newErrnoENOTDIRError(dir + " is not a directory");
            }
            dir = pathParts[1];
        }
        final JRubyFile result = JRubyFile.create(runtime.getCurrentDirectory(), dir);
        if (mustExist && !result.exists()) {
            throw runtime.newErrnoENOENTError("No such file or directory - " + dir);
        }
        final boolean isDirectory = result.isDirectory();
        if (mustExist && !isDirectory) {
            throw runtime.newErrnoENOTDIRError(path + " is not a directory");
        }
        if (!mustExist && isDirectory) {
            throw runtime.newErrnoEEXISTError("File exists - " + dir);
        }
        return result;
    }
    
    protected static List<String> getContents(final File directory) {
        final String[] contents = directory.list();
        final List<String> result = new ArrayList<String>();
        if (contents != null) {
            for (int i = 0; i < contents.length; ++i) {
                result.add(contents[i]);
            }
        }
        return result;
    }
    
    protected static List<RubyString> getContents(final File directory, final Ruby runtime) {
        final List<RubyString> result = new ArrayList<RubyString>();
        final String[] contents = directory.list();
        for (int i = 0; i < contents.length; ++i) {
            result.add(runtime.newString(contents[i]));
        }
        return result;
    }
    
    public static IRubyObject getHomeDirectoryPath(final ThreadContext context, final String user) {
        final Ruby runtime = context.getRuntime();
        try {
            return runtime.newString(runtime.getPosix().getpwnam(user).getHome());
        }
        catch (Exception e) {
            String passwd = null;
            try {
                final FileInputStream stream = new FileInputStream("/etc/passwd");
                final int totalBytes = stream.available();
                final byte[] bytes = new byte[totalBytes];
                stream.read(bytes);
                stream.close();
                passwd = new String(bytes);
            }
            catch (IOException ioe) {
                return runtime.getNil();
            }
            final String[] rows = passwd.split("\n");
            for (int rowCount = rows.length, i = 0; i < rowCount; ++i) {
                final String[] fields = rows[i].split(":");
                if (fields[0].equals(user)) {
                    return runtime.newString(fields[5]);
                }
            }
            throw runtime.newArgumentError("user " + user + " doesn't exist");
        }
    }
    
    public static RubyString getHomeDirectoryPath(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyHash systemHash = (RubyHash)runtime.getObject().fastGetConstant("ENV_JAVA");
        final RubyHash envHash = (RubyHash)runtime.getObject().fastGetConstant("ENV");
        IRubyObject home = envHash.op_aref(context, runtime.newString("HOME"));
        if (home == null || home.isNil()) {
            home = systemHash.op_aref(context, runtime.newString("user.home"));
        }
        if (home == null || home.isNil()) {
            home = envHash.op_aref(context, runtime.newString("LOGDIR"));
        }
        if (home == null || home.isNil()) {
            throw runtime.newArgumentError("user.home/LOGDIR not set");
        }
        return (RubyString)home;
    }
    
    static {
        DIR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyDir(runtime, klass);
            }
        };
    }
}

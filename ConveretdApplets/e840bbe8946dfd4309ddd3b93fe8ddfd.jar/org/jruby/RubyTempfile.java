// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.io.OpenFile;
import org.jruby.util.PhantomReferenceReaper;
import org.jruby.platform.Platform;
import java.util.concurrent.ConcurrentHashMap;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ModeFlags;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import java.io.IOException;
import org.jruby.util.JRubyFile;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Block;
import java.io.File;
import java.util.Random;
import org.jruby.runtime.ObjectAllocator;
import java.util.concurrent.ConcurrentMap;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Tempfile" }, parent = "File")
public class RubyTempfile extends RubyFile
{
    private static final ConcurrentMap<Reaper, Boolean> referenceSet;
    private transient volatile Reaper reaper;
    private static ObjectAllocator TEMPFILE_ALLOCATOR;
    private static final String DEFAULT_TMP_DIR;
    private static final Object tmpFileLock;
    private static int counter;
    private static final Random RND;
    private File tmpFile;
    
    public static RubyClass createTempfileClass(final Ruby runtime) {
        final RubyClass tempfileClass = runtime.defineClass("Tempfile", runtime.getFile(), RubyTempfile.TEMPFILE_ALLOCATOR);
        RubyKernel.require(tempfileClass, runtime.newString("tmpdir"), Block.NULL_BLOCK);
        tempfileClass.defineAnnotatedMethods(RubyTempfile.class);
        return tempfileClass;
    }
    
    public RubyTempfile(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.tmpFile = null;
    }
    
    @JRubyMethod(required = 1, optional = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block block) {
        final Ruby runtime = this.getRuntime();
        final IRubyObject basename = args[0];
        final IRubyObject dir = this.defaultTmpDir(runtime, args);
        File tmp = null;
        synchronized (RubyTempfile.tmpFileLock) {
            try {
                do {
                    if (RubyTempfile.counter == -1) {
                        RubyTempfile.counter = (RubyTempfile.RND.nextInt() & 0xFFFF);
                    }
                    ++RubyTempfile.counter;
                    final IRubyObject tmpname = this.callMethod(runtime.getCurrentContext(), "make_tmpname", new IRubyObject[] { basename, runtime.newFixnum(RubyTempfile.counter) });
                    tmp = JRubyFile.create(this.getRuntime().getCurrentDirectory(), new File(dir.convertToString().toString(), tmpname.convertToString().toString()).getPath());
                } while (!tmp.createNewFile());
                this.tmpFile = tmp;
                this.path = tmp.getPath();
                try {
                    this.tmpFile.deleteOnExit();
                }
                catch (NullPointerException npe) {}
                catch (IllegalStateException ex) {}
                this.initializeOpen();
                RubyTempfile.referenceSet.put(this.reaper = new Reaper(this, runtime, this.tmpFile, this.openFile), Boolean.TRUE);
                return this;
            }
            catch (IOException e) {
                throw runtime.newIOErrorFromException(e);
            }
        }
    }
    
    private IRubyObject defaultTmpDir(final Ruby runtime, final IRubyObject[] args) {
        IRubyObject dir = null;
        if (args.length == 2) {
            dir = args[1];
        }
        else {
            runtime.getLoadService().require("tmpdir");
            dir = runtime.getDir().callMethod(runtime.getCurrentContext(), "tmpdir");
        }
        if (runtime.getSafeLevel() > 0 && dir.isTaint()) {
            dir = runtime.newString(RubyTempfile.DEFAULT_TMP_DIR);
        }
        return dir;
    }
    
    private void initializeOpen() {
        try {
            final ModeFlags modeFlags = new ModeFlags(ModeFlags.RDWR | ModeFlags.EXCL);
            this.getRuntime().getPosix().chmod(this.path, 384);
            this.sysopenInternal(this.path, modeFlags, 384);
        }
        catch (InvalidValueException e) {
            throw this.getRuntime().newErrnoEINVALError();
        }
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject make_tmpname(final ThreadContext context, final IRubyObject basename, final IRubyObject n, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] newargs = new IRubyObject[5];
        IRubyObject base;
        IRubyObject suffix;
        if (basename instanceof RubyArray) {
            final RubyArray array = (RubyArray)basename;
            final int length = array.getLength();
            base = ((length > 0) ? array.eltInternal(0) : runtime.getNil());
            suffix = ((length > 0) ? array.eltInternal(1) : runtime.getNil());
        }
        else {
            base = basename;
            suffix = runtime.newString("");
        }
        newargs[0] = runtime.newString("%s.%d.%d%s");
        newargs[1] = base;
        newargs[2] = runtime.getGlobalVariables().get("$$");
        newargs[3] = n;
        newargs[4] = suffix;
        return this.callMethod(context, "sprintf", newargs);
    }
    
    @JRubyMethod(visibility = Visibility.PUBLIC)
    public IRubyObject open() {
        if (!this.isClosed()) {
            this.close();
        }
        try {
            this.openInternal(this.path, "r+");
        }
        catch (InvalidValueException ex) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PROTECTED)
    public IRubyObject _close(final ThreadContext context) {
        return this.isClosed() ? context.getRuntime().getNil() : super.close();
    }
    
    @JRubyMethod(optional = 1, visibility = Visibility.PUBLIC)
    public IRubyObject close(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final boolean unlink = args.length == 1 && args[0].isTrue();
        return unlink ? this.close_bang(context) : this._close(context);
    }
    
    @JRubyMethod(name = { "close!" }, visibility = Visibility.PUBLIC)
    public IRubyObject close_bang(final ThreadContext context) {
        RubyTempfile.referenceSet.remove(this.reaper);
        this.reaper.released = true;
        this._close(context);
        this.tmpFile.delete();
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "unlink", "delete" })
    public IRubyObject unlink(final ThreadContext context) {
        if (!this.tmpFile.exists() || this.tmpFile.delete()) {
            RubyTempfile.referenceSet.remove(this.reaper);
            this.reaper.released = true;
            this.path = null;
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "size", "length" })
    public IRubyObject size(final ThreadContext context) {
        if (!this.isClosed()) {
            this.flush();
            return context.getRuntime().newFileStat(this.path, false).size();
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyClass klass = (RubyClass)recv;
        final RubyTempfile tempfile = (RubyTempfile)klass.newInstance(context, args, block);
        if (block.isGiven()) {
            try {
                block.yield(context, tempfile);
            }
            finally {
                if (!tempfile.isClosed()) {
                    tempfile.close();
                }
            }
            return runtime.getNil();
        }
        return tempfile;
    }
    
    static {
        referenceSet = new ConcurrentHashMap<Reaper, Boolean>();
        RubyTempfile.TEMPFILE_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyFile instance = new RubyTempfile(runtime, klass);
                instance.setMetaClass(klass);
                return instance;
            }
        };
        tmpFileLock = new Object();
        RubyTempfile.counter = -1;
        RND = new Random();
        String tmpDir;
        if (Platform.IS_WINDOWS) {
            tmpDir = System.getProperty("java.io.tmpdir");
            if (tmpDir == null) {
                tmpDir = System.getenv("TEMP");
            }
            if (tmpDir == null) {
                tmpDir = System.getenv("TMP");
            }
            if (tmpDir == null) {
                tmpDir = "C:\\Windows\\Temp";
            }
        }
        else {
            tmpDir = "/tmp";
        }
        DEFAULT_TMP_DIR = tmpDir;
    }
    
    private static final class Reaper extends PhantomReferenceReaper<RubyTempfile> implements Runnable
    {
        private volatile boolean released;
        private final Ruby runtime;
        private final File tmpFile;
        private final OpenFile openFile;
        
        Reaper(final RubyTempfile file, final Ruby runtime, final File tmpFile, final OpenFile openFile) {
            super(file);
            this.released = false;
            this.runtime = runtime;
            this.tmpFile = tmpFile;
            this.openFile = openFile;
        }
        
        public final void run() {
            RubyTempfile.referenceSet.remove(this);
            this.release();
            this.clear();
        }
        
        final void release() {
            if (!this.released) {
                this.released = true;
                if (this.openFile != null) {
                    this.openFile.cleanup(this.runtime, false);
                }
                if (this.tmpFile.exists()) {
                    final boolean deleted = this.tmpFile.delete();
                    if (this.runtime.getDebug().isTrue()) {
                        final String msg = "removing " + this.tmpFile.getPath() + " ... ";
                        if (deleted) {
                            this.runtime.getErr().println(msg + "done");
                        }
                        else {
                            this.runtime.getErr().println(msg + "can't delete");
                        }
                    }
                }
            }
        }
    }
}

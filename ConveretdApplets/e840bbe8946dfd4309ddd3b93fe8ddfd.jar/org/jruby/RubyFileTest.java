// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipFile;
import java.util.jar.JarFile;
import org.jruby.exceptions.RaiseException;
import org.jruby.platform.Platform;
import java.util.zip.ZipEntry;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.JRubyFile;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "FileTest" })
public class RubyFileTest
{
    public static RubyModule createFileTestModule(final Ruby runtime) {
        final RubyModule fileTestModule = runtime.defineModule("FileTest");
        runtime.setFileTest(fileTestModule);
        fileTestModule.defineAnnotatedMethods(RubyFileTest.class);
        return fileTestModule;
    }
    
    @JRubyMethod(name = { "blockdev?" }, required = 1, module = true)
    public static IRubyObject blockdev_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isBlockDev());
    }
    
    @JRubyMethod(name = { "chardev?" }, required = 1, module = true)
    public static IRubyObject chardev_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isCharDev());
    }
    
    public static IRubyObject directory_p(final IRubyObject recv, final IRubyObject filename) {
        return directory_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    public static IRubyObject directory_p(final Ruby ruby, final IRubyObject filename) {
        return directory_p(ruby.getCurrentContext(), filename);
    }
    
    @JRubyMethod(name = { "directory?" }, required = 1, module = true)
    public static IRubyObject directory_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        return directory_p(context, filename);
    }
    
    public static IRubyObject directory_p(final ThreadContext context, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            return entry.isDirectory() ? runtime.getTrue() : runtime.getFalse();
        }
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isDirectory());
    }
    
    @JRubyMethod(name = { "executable?" }, required = 1, module = true)
    public static IRubyObject executable_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isExecutable());
    }
    
    @JRubyMethod(name = { "executable_real?" }, required = 1, module = true)
    public static IRubyObject executable_real_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isExecutableReal());
    }
    
    public static IRubyObject exist_p(final IRubyObject recv, final IRubyObject filename) {
        return exist_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "exist?", "exists?" }, required = 1, module = true)
    public static IRubyObject exist_p(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        if (existsOnClasspath(filename)) {
            return runtime.getTrue();
        }
        if (Ruby.isSecurityRestricted()) {
            return runtime.getFalse();
        }
        if (file_in_archive(filename) != null) {
            return runtime.getTrue();
        }
        return runtime.newBoolean(RubyFile.file(filename).exists());
    }
    
    public static RubyBoolean file_p(final IRubyObject recv, final IRubyObject filename) {
        return file_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "file?" }, required = 1, module = true)
    public static RubyBoolean file_p(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            return entry.isDirectory() ? recv.getRuntime().getFalse() : recv.getRuntime().getTrue();
        }
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && file.isFile());
    }
    
    @JRubyMethod(name = { "grpowned?" }, required = 1, module = true)
    public static IRubyObject grpowned_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        if (Platform.IS_WINDOWS) {
            return runtime.getFalse();
        }
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isGroupOwned());
    }
    
    @JRubyMethod(name = { "identical?" }, required = 2, module = true)
    public static IRubyObject identical_p(final IRubyObject recv, final IRubyObject filename1, final IRubyObject filename2) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file1 = RubyFile.file(filename1);
        final JRubyFile file2 = RubyFile.file(filename2);
        return runtime.newBoolean(file1.exists() && file2.exists() && runtime.getPosix().stat(file1.getAbsolutePath()).isIdentical(runtime.getPosix().stat(file2.getAbsolutePath())));
    }
    
    @JRubyMethod(name = { "owned?" }, required = 1, module = true)
    public static IRubyObject owned_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isOwned());
    }
    
    @JRubyMethod(name = { "pipe?" }, required = 1, module = true)
    public static IRubyObject pipe_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isNamedPipe());
    }
    
    public static IRubyObject readable_p(final IRubyObject recv, final IRubyObject filename) {
        return readable_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "readable?", "readable_real?" }, required = 1, module = true)
    public static IRubyObject readable_p(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            return entry.isDirectory() ? recv.getRuntime().getFalse() : recv.getRuntime().getTrue();
        }
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && file.canRead());
    }
    
    public static IRubyObject rowned_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isROwned());
    }
    
    @JRubyMethod(name = { "setgid?" }, required = 1, module = true)
    public static IRubyObject setgid_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isSetgid());
    }
    
    @JRubyMethod(name = { "setuid?" }, required = 1, module = true)
    public static IRubyObject setuid_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isSetuid());
    }
    
    public static IRubyObject size(final IRubyObject recv, final IRubyObject filename) {
        return size(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "size" }, required = 1, module = true)
    public static IRubyObject size(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            return runtime.newFixnum(entry.getSize());
        }
        final JRubyFile file = RubyFile.file(filename);
        if (!file.exists()) {
            noFileError(filename);
        }
        return runtime.newFixnum(file.length());
    }
    
    public static IRubyObject size_p(final IRubyObject recv, final IRubyObject filename) {
        return size_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "size?" }, required = 1, module = true)
    public static IRubyObject size_p(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            final long size = entry.getSize();
            if (size > 0L) {
                return runtime.newFixnum(size);
            }
            return runtime.getNil();
        }
        else {
            final JRubyFile file = RubyFile.file(filename);
            if (!file.exists()) {
                return runtime.getNil();
            }
            final long length = file.length();
            if (length > 0L) {
                return runtime.newFixnum(length);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "socket?" }, required = 1, module = true)
    public static IRubyObject socket_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isSocket());
    }
    
    @JRubyMethod(name = { "sticky?" }, required = 1, module = true)
    public static IRubyObject sticky_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        return runtime.newBoolean(file.exists() && runtime.getPosix().stat(file.getAbsolutePath()).isSticky());
    }
    
    @JRubyMethod(name = { "symlink?" }, required = 1, module = true)
    public static RubyBoolean symlink_p(final IRubyObject recv, final IRubyObject filename) {
        final Ruby runtime = recv.getRuntime();
        final JRubyFile file = RubyFile.file(filename);
        try {
            return runtime.newBoolean(runtime.getPosix().lstat(file.getAbsolutePath()).isSymlink());
        }
        catch (SecurityException re) {
            return runtime.getFalse();
        }
        catch (RaiseException re2) {
            return runtime.getFalse();
        }
    }
    
    @JRubyMethod(name = { "writable?", "writable_real?" }, required = 1, module = true)
    public static RubyBoolean writable_p(final IRubyObject recv, final IRubyObject filename) {
        return filename.getRuntime().newBoolean(RubyFile.file(filename).canWrite());
    }
    
    public static RubyBoolean zero_p(final IRubyObject recv, final IRubyObject filename) {
        return zero_p(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "zero?" }, required = 1, module = true)
    public static RubyBoolean zero_p(final ThreadContext context, final IRubyObject recv, IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        if (!(filename instanceof RubyFile)) {
            filename = RubyFile.get_path(context, filename);
        }
        final ZipEntry entry = file_in_archive(filename);
        if (entry != null) {
            return runtime.newBoolean(entry.getSize() == 0L);
        }
        final JRubyFile file = RubyFile.file(filename);
        if (!file.exists()) {
            return runtime.getFalse();
        }
        if (file.isDirectory()) {
            return runtime.newBoolean(Platform.IS_WINDOWS);
        }
        return runtime.newBoolean(file.length() == 0L);
    }
    
    @JRubyMethod(name = { "world_readable?" }, required = 1, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject worldReadable(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final RubyFileStat stat = getFileStat(context, filename);
        if (stat == null) {
            return context.getRuntime().getNil();
        }
        return stat.worldReadable(context);
    }
    
    @JRubyMethod(name = { "world_writable?" }, required = 1, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject worldWritable(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final RubyFileStat stat = getFileStat(context, filename);
        if (stat == null) {
            return context.getRuntime().getNil();
        }
        return stat.worldWritable(context);
    }
    
    private static RubyFileStat getFileStat(final ThreadContext context, final IRubyObject filename) {
        final Ruby runtime = context.getRuntime();
        RubyFileStat stat = null;
        if (!(filename instanceof RubyFile)) {
            final RubyString path = RubyFile.get_path(context, filename);
            final JRubyFile file = JRubyFile.create(runtime.getCurrentDirectory(), path.getUnicodeValue());
            if (file.exists()) {
                stat = runtime.newFileStat(file.getPath(), false);
            }
        }
        else {
            stat = (RubyFileStat)((RubyFile)filename).stat(context);
        }
        return stat;
    }
    
    private static ZipEntry file_in_archive(final IRubyObject path) {
        final Ruby runtime = path.getRuntime();
        if (path instanceof RubyFile) {
            return null;
        }
        final RubyString pathStr = RubyFile.get_path(runtime.getCurrentContext(), path);
        final String pathJStr = pathStr.getUnicodeValue();
        if (pathJStr.startsWith("file:")) {
            final String file = pathJStr.substring(5);
            final int bang = file.indexOf(33);
            if (bang == -1 || bang == file.length() - 1) {
                return null;
            }
            final String jar = file.substring(0, bang);
            final String after = file.substring(bang + 2);
            try {
                final JarFile jf = new JarFile(jar);
                final ZipEntry entry = RubyFile.getDirOrFileEntry(jf, after);
                return entry;
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    private static boolean existsOnClasspath(final IRubyObject path) {
        if (path instanceof RubyFile) {
            return false;
        }
        final Ruby runtime = path.getRuntime();
        final RubyString pathStr = RubyFile.get_path(runtime.getCurrentContext(), path);
        String pathJStr = pathStr.getUnicodeValue();
        if (pathJStr.startsWith("classpath:/")) {
            pathJStr = pathJStr.substring("classpath:/".length());
            ClassLoader classLoader = runtime.getJRubyClassLoader();
            if (Ruby.isSecurityRestricted() && classLoader == null) {
                classLoader = runtime.getInstanceConfig().getLoader();
            }
            final InputStream is = classLoader.getResourceAsStream(pathJStr);
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException ignore) {}
                catch (NullPointerException ex) {}
                return true;
            }
        }
        return false;
    }
    
    private static void noFileError(final IRubyObject filename) {
        throw filename.getRuntime().newErrnoENOENTError("No such file or directory - " + filename.convertToString());
    }
    
    public static class FileTestFileMethods
    {
        @JRubyMethod(name = { "blockdev?" }, required = 1)
        public static IRubyObject blockdev_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.blockdev_p(recv, filename);
        }
        
        @JRubyMethod(name = { "chardev?" }, required = 1)
        public static IRubyObject chardev_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.chardev_p(recv, filename);
        }
        
        @JRubyMethod(name = { "directory?" }, required = 1)
        public static IRubyObject directory_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.directory_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "executable?" }, required = 1)
        public static IRubyObject executable_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.executable_p(recv, filename);
        }
        
        @JRubyMethod(name = { "executable_real?" }, required = 1)
        public static IRubyObject executable_real_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.executable_real_p(recv, filename);
        }
        
        @JRubyMethod(name = { "exist?", "exists?" }, required = 1)
        public static IRubyObject exist_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.exist_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "file?" }, required = 1)
        public static RubyBoolean file_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.file_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "grpowned?" }, required = 1)
        public static IRubyObject grpowned_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.grpowned_p(recv, filename);
        }
        
        @JRubyMethod(name = { "identical?" }, required = 2)
        public static IRubyObject identical_p(final IRubyObject recv, final IRubyObject filename1, final IRubyObject filename2) {
            return RubyFileTest.identical_p(recv, filename1, filename2);
        }
        
        @JRubyMethod(name = { "owned?" }, required = 1)
        public static IRubyObject owned_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.owned_p(recv, filename);
        }
        
        @JRubyMethod(name = { "pipe?" }, required = 1)
        public static IRubyObject pipe_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.pipe_p(recv, filename);
        }
        
        @JRubyMethod(name = { "readable?", "readable_real?" }, required = 1)
        public static IRubyObject readable_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.readable_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "setgid?" }, required = 1)
        public static IRubyObject setgid_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.setgid_p(recv, filename);
        }
        
        @JRubyMethod(name = { "setuid?" }, required = 1)
        public static IRubyObject setuid_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.setuid_p(recv, filename);
        }
        
        @JRubyMethod(name = { "size" }, required = 1)
        public static IRubyObject size(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.size(context, recv, filename);
        }
        
        @JRubyMethod(name = { "size?" }, required = 1)
        public static IRubyObject size_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.size_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "socket?" }, required = 1)
        public static IRubyObject socket_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.socket_p(recv, filename);
        }
        
        @JRubyMethod(name = { "sticky?" }, required = 1)
        public static IRubyObject sticky_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.sticky_p(recv, filename);
        }
        
        @JRubyMethod(name = { "symlink?" }, required = 1)
        public static RubyBoolean symlink_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.symlink_p(recv, filename);
        }
        
        @JRubyMethod(name = { "writable?", "writable_real?" }, required = 1)
        public static RubyBoolean writable_p(final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.writable_p(recv, filename);
        }
        
        @JRubyMethod(name = { "zero?" }, required = 1)
        public static RubyBoolean zero_p(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.zero_p(context, recv, filename);
        }
        
        @JRubyMethod(name = { "world_readable?" }, required = 1, compat = CompatVersion.RUBY1_9)
        public static IRubyObject worldReadable(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.worldReadable(context, recv, filename);
        }
        
        @JRubyMethod(name = { "world_writable?" }, required = 1, compat = CompatVersion.RUBY1_9)
        public static IRubyObject worldWritable(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
            return RubyFileTest.worldWritable(context, recv, filename);
        }
    }
}

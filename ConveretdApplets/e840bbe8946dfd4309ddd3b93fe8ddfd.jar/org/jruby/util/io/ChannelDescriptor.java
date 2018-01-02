// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import org.jruby.util.JRubyFile;
import java.util.zip.ZipFile;
import org.jruby.RubyFile;
import java.util.jar.JarFile;
import java.io.File;
import java.net.URL;
import java.io.FileNotFoundException;
import org.jruby.ext.posix.POSIX;
import java.nio.channels.ReadableByteChannel;
import java.nio.ByteBuffer;
import org.jruby.util.ByteList;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.Channels;
import java.util.Map;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.FileDescriptor;
import java.nio.channels.Channel;

public class ChannelDescriptor
{
    private static final boolean DEBUG = false;
    private Channel channel;
    private int internalFileno;
    private FileDescriptor fileDescriptor;
    private ModeFlags originalModes;
    private AtomicInteger refCounter;
    private InputStream baseInputStream;
    private boolean canBeSeekable;
    protected static final AtomicInteger internalFilenoIndex;
    private static final Map<Integer, ChannelDescriptor> filenoDescriptorMap;
    
    private ChannelDescriptor(final Channel channel, final int fileno, final ModeFlags originalModes, final FileDescriptor fileDescriptor, final AtomicInteger refCounter, final boolean canBeSeekable) {
        this.canBeSeekable = true;
        this.refCounter = refCounter;
        this.channel = channel;
        this.internalFileno = fileno;
        this.originalModes = originalModes;
        this.fileDescriptor = fileDescriptor;
        this.canBeSeekable = canBeSeekable;
        registerDescriptor(this);
    }
    
    private ChannelDescriptor(final Channel channel, final int fileno, final ModeFlags originalModes, final FileDescriptor fileDescriptor) {
        this(channel, fileno, originalModes, fileDescriptor, new AtomicInteger(1), true);
    }
    
    public ChannelDescriptor(final Channel channel, final ModeFlags originalModes, final FileDescriptor fileDescriptor) {
        this(channel, getNewFileno(), originalModes, fileDescriptor, new AtomicInteger(1), true);
    }
    
    public ChannelDescriptor(final Channel channel, final ModeFlags originalModes) {
        this(channel, getNewFileno(), originalModes, new FileDescriptor(), new AtomicInteger(1), true);
    }
    
    public ChannelDescriptor(final InputStream baseInputStream, final ModeFlags originalModes, final FileDescriptor fileDescriptor) {
        this(Channels.newChannel(baseInputStream), getNewFileno(), originalModes, fileDescriptor, new AtomicInteger(1), true);
        this.baseInputStream = baseInputStream;
    }
    
    public ChannelDescriptor(final InputStream baseInputStream, final ModeFlags originalModes) {
        this(Channels.newChannel(baseInputStream), getNewFileno(), originalModes, new FileDescriptor(), new AtomicInteger(1), true);
        this.baseInputStream = baseInputStream;
    }
    
    public ChannelDescriptor(final Channel channel, final FileDescriptor fileDescriptor) throws InvalidValueException {
        this(channel, getModesFromChannel(channel), fileDescriptor);
    }
    
    public ChannelDescriptor(final Channel channel, final int fileno, final FileDescriptor fileDescriptor) throws InvalidValueException {
        this(channel, getModesFromChannel(channel), fileDescriptor);
    }
    
    public ChannelDescriptor(final Channel channel) throws InvalidValueException {
        this(channel, getModesFromChannel(channel), new FileDescriptor());
    }
    
    public int getFileno() {
        return this.internalFileno;
    }
    
    public FileDescriptor getFileDescriptor() {
        return this.fileDescriptor;
    }
    
    public Channel getChannel() {
        return this.channel;
    }
    
    InputStream getBaseInputStream() {
        return this.baseInputStream;
    }
    
    public boolean isSeekable() {
        return this.canBeSeekable && this.channel instanceof FileChannel;
    }
    
    public void setCanBeSeekable(final boolean canBeSeekable) {
        this.canBeSeekable = canBeSeekable;
    }
    
    public boolean isNull() {
        return this.channel instanceof NullChannel;
    }
    
    public boolean isWritable() {
        return this.channel instanceof WritableByteChannel;
    }
    
    public boolean isOpen() {
        return this.channel.isOpen();
    }
    
    public void checkOpen() throws BadDescriptorException {
        if (!this.isOpen()) {
            throw new BadDescriptorException();
        }
    }
    
    public ModeFlags getOriginalModes() {
        return this.originalModes;
    }
    
    public void checkNewModes(final ModeFlags newModes) throws InvalidValueException {
        if (!newModes.isSubsetOf(this.originalModes)) {
            throw new InvalidValueException();
        }
    }
    
    public ChannelDescriptor dup() {
        synchronized (this.refCounter) {
            this.refCounter.incrementAndGet();
            final int newFileno = getNewFileno();
            return new ChannelDescriptor(this.channel, newFileno, this.originalModes, this.fileDescriptor, this.refCounter, this.canBeSeekable);
        }
    }
    
    public ChannelDescriptor dup2(final int fileno) {
        synchronized (this.refCounter) {
            this.refCounter.incrementAndGet();
            return new ChannelDescriptor(this.channel, fileno, this.originalModes, this.fileDescriptor, this.refCounter, this.canBeSeekable);
        }
    }
    
    public void dup2Into(final ChannelDescriptor other) throws BadDescriptorException, IOException {
        synchronized (this.refCounter) {
            this.refCounter.incrementAndGet();
            other.close();
            other.channel = this.channel;
            other.originalModes = this.originalModes;
            other.fileDescriptor = this.fileDescriptor;
            other.refCounter = this.refCounter;
            other.canBeSeekable = this.canBeSeekable;
        }
    }
    
    public ChannelDescriptor reopen(final Channel channel, final ModeFlags modes) {
        return new ChannelDescriptor(channel, this.internalFileno, modes, this.fileDescriptor);
    }
    
    public ChannelDescriptor reopen(final RandomAccessFile file, final ModeFlags modes) throws IOException {
        return new ChannelDescriptor(file.getChannel(), this.internalFileno, modes, file.getFD());
    }
    
    public long lseek(final long offset, final int whence) throws IOException, InvalidValueException, PipeException, BadDescriptorException {
        if (this.channel instanceof FileChannel) {
            this.checkOpen();
            final FileChannel fileChannel = (FileChannel)this.channel;
            try {
                long pos = 0L;
                switch (whence) {
                    case 0: {
                        pos = offset;
                        fileChannel.position(pos);
                        break;
                    }
                    case 1: {
                        pos = fileChannel.position() + offset;
                        fileChannel.position(pos);
                        break;
                    }
                    case 2: {
                        pos = fileChannel.size() + offset;
                        fileChannel.position(pos);
                        break;
                    }
                    default: {
                        throw new InvalidValueException();
                    }
                }
                return pos;
            }
            catch (IllegalArgumentException e) {
                throw new InvalidValueException();
            }
        }
        throw new PipeException();
    }
    
    public int read(final int number, final ByteList byteList) throws IOException, BadDescriptorException {
        this.checkOpen();
        byteList.ensure(byteList.length() + number);
        final int bytesRead = this.read(ByteBuffer.wrap(byteList.getUnsafeBytes(), byteList.begin() + byteList.length(), number));
        if (bytesRead > 0) {
            byteList.length(byteList.length() + bytesRead);
        }
        return bytesRead;
    }
    
    public int read(final ByteBuffer buffer) throws IOException, BadDescriptorException {
        this.checkOpen();
        if (!(this.channel instanceof ReadableByteChannel)) {
            throw new BadDescriptorException();
        }
        final ReadableByteChannel readChannel = (ReadableByteChannel)this.channel;
        int bytesRead = 0;
        bytesRead = readChannel.read(buffer);
        return bytesRead;
    }
    
    public int internalWrite(final ByteBuffer buffer) throws IOException, BadDescriptorException {
        this.checkOpen();
        if (!(this.channel instanceof WritableByteChannel)) {
            throw new BadDescriptorException();
        }
        final WritableByteChannel writeChannel = (WritableByteChannel)this.channel;
        if (this.isSeekable() && this.originalModes.isAppendable()) {
            final FileChannel fileChannel = (FileChannel)this.channel;
            fileChannel.position(fileChannel.size());
        }
        return writeChannel.write(buffer);
    }
    
    public int write(final ByteBuffer buffer) throws IOException, BadDescriptorException {
        this.checkOpen();
        return this.internalWrite(buffer);
    }
    
    public int write(final ByteList buf) throws IOException, BadDescriptorException {
        this.checkOpen();
        return this.internalWrite(ByteBuffer.wrap(buf.getUnsafeBytes(), buf.begin(), buf.length()));
    }
    
    public int write(final ByteList buf, final int offset, final int len) throws IOException, BadDescriptorException {
        this.checkOpen();
        return this.internalWrite(ByteBuffer.wrap(buf.getUnsafeBytes(), buf.begin() + offset, len));
    }
    
    public int write(final int c) throws IOException, BadDescriptorException {
        this.checkOpen();
        final ByteBuffer buf = ByteBuffer.allocate(1);
        buf.put((byte)c);
        buf.flip();
        return this.internalWrite(buf);
    }
    
    public static ChannelDescriptor open(final String cwd, final String path, final ModeFlags flags) throws FileNotFoundException, DirectoryAsFileException, FileExistsException, IOException {
        return open(cwd, path, flags, 0, null, null);
    }
    
    public static ChannelDescriptor open(final String cwd, final String path, final ModeFlags flags, final ClassLoader classLoader) throws FileNotFoundException, DirectoryAsFileException, FileExistsException, IOException {
        return open(cwd, path, flags, 0, null, classLoader);
    }
    
    public static ChannelDescriptor open(final String cwd, final String path, final ModeFlags flags, final int perm, final POSIX posix) throws FileNotFoundException, DirectoryAsFileException, FileExistsException, IOException {
        return open(cwd, path, flags, perm, posix, null);
    }
    
    public static ChannelDescriptor open(final String cwd, String path, final ModeFlags flags, final int perm, final POSIX posix, final ClassLoader classLoader) throws FileNotFoundException, DirectoryAsFileException, FileExistsException, IOException {
        boolean fileCreated = false;
        if (path.equals("/dev/null") || path.equalsIgnoreCase("nul:") || path.equalsIgnoreCase("nul")) {
            final Channel nullChannel = new NullChannel();
            return new ChannelDescriptor(nullChannel, flags);
        }
        if (path.startsWith("file:")) {
            final int bangIndex = path.indexOf("!");
            if (bangIndex <= 0) {
                final URL url = new URL(path);
                final InputStream is = url.openStream();
                return new ChannelDescriptor(Channels.newChannel(is), flags);
            }
            final String filePath = path.substring(5, bangIndex);
            final String internalPath = path.substring(bangIndex + 2);
            if (!new File(filePath).exists()) {
                throw new FileNotFoundException(path);
            }
            final JarFile jf = new JarFile(filePath);
            final ZipEntry entry = RubyFile.getFileEntry(jf, internalPath);
            if (entry == null) {
                throw new FileNotFoundException(path);
            }
            final InputStream is2 = jf.getInputStream(entry);
            return new ChannelDescriptor(Channels.newChannel(is2), flags);
        }
        else {
            if (path.startsWith("classpath:/") && classLoader != null) {
                path = path.substring("classpath:/".length());
                final InputStream is3 = classLoader.getResourceAsStream(path);
                return new ChannelDescriptor(Channels.newChannel(is3), flags);
            }
            final JRubyFile theFile = JRubyFile.create(cwd, path);
            if (theFile.isDirectory() && flags.isWritable()) {
                throw new DirectoryAsFileException();
            }
            Label_0402: {
                if (flags.isCreate()) {
                    if (theFile.exists() && flags.isExclusive()) {
                        throw new FileExistsException(path);
                    }
                    try {
                        fileCreated = theFile.createNewFile();
                        break Label_0402;
                    }
                    catch (IOException ioe) {
                        final File parent = theFile.getParentFile();
                        if (parent != null && parent != theFile && !parent.exists()) {
                            throw new FileNotFoundException(path);
                        }
                        if (!theFile.canWrite()) {
                            throw new PermissionDeniedException(path);
                        }
                        throw ioe;
                    }
                }
                if (!theFile.exists()) {
                    throw new FileNotFoundException(path);
                }
            }
            final RandomAccessFile file = new RandomAccessFile(theFile, flags.toJavaModeString());
            if (fileCreated && posix != null && perm != -1) {
                posix.chmod(theFile.getPath(), perm);
            }
            if (flags.isTruncate()) {
                file.setLength(0L);
            }
            return new ChannelDescriptor(file.getChannel(), flags, file.getFD());
        }
    }
    
    public void close() throws BadDescriptorException, IOException {
        this.finish(true);
    }
    
    void finish(final boolean close) throws BadDescriptorException, IOException {
        synchronized (this.refCounter) {
            if (this.refCounter.get() <= 0) {
                throw new BadDescriptorException();
            }
            if (!this.channel.isOpen()) {
                throw new BadDescriptorException();
            }
            final int count = this.refCounter.decrementAndGet();
            if (count <= 0) {
                try {
                    if (close) {
                        this.channel.close();
                    }
                }
                finally {
                    unregisterDescriptor(this.internalFileno);
                }
            }
        }
    }
    
    private static ModeFlags getModesFromChannel(final Channel channel) throws InvalidValueException {
        ModeFlags modes;
        if (channel instanceof ReadableByteChannel) {
            if (channel instanceof WritableByteChannel) {
                modes = new ModeFlags(ModeFlags.RDWR);
            }
            else {
                modes = new ModeFlags(ModeFlags.RDONLY);
            }
        }
        else if (channel instanceof WritableByteChannel) {
            modes = new ModeFlags(ModeFlags.WRONLY);
        }
        else {
            modes = new ModeFlags(ModeFlags.RDWR);
        }
        return modes;
    }
    
    public static int getNewFileno() {
        return ChannelDescriptor.internalFilenoIndex.incrementAndGet();
    }
    
    private static void registerDescriptor(final ChannelDescriptor descriptor) {
        ChannelDescriptor.filenoDescriptorMap.put(descriptor.getFileno(), descriptor);
    }
    
    private static void unregisterDescriptor(final int aFileno) {
        ChannelDescriptor.filenoDescriptorMap.remove(aFileno);
    }
    
    public static ChannelDescriptor getDescriptorByFileno(final int aFileno) {
        return ChannelDescriptor.filenoDescriptorMap.get(aFileno);
    }
    
    static {
        internalFilenoIndex = new AtomicInteger(2);
        filenoDescriptorMap = new ConcurrentHashMap<Integer, ChannelDescriptor>();
    }
}

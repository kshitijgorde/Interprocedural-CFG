// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.nio.channels.Channel;
import org.jruby.Ruby;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.EOFException;
import java.io.IOException;
import org.jruby.util.ByteList;

public class CRLFStreamWrapper implements Stream
{
    private final Stream stream;
    private boolean binmode;
    private static final int CR = 13;
    private static final int LF = 10;
    
    public CRLFStreamWrapper(final Stream stream) {
        this.binmode = false;
        this.stream = stream;
    }
    
    public ChannelDescriptor getDescriptor() {
        return this.stream.getDescriptor();
    }
    
    public void clearerr() {
        this.stream.clearerr();
    }
    
    public ModeFlags getModes() {
        return this.stream.getModes();
    }
    
    public boolean isSync() {
        return this.stream.isSync();
    }
    
    public void setSync(final boolean sync) {
        this.stream.setSync(sync);
    }
    
    public void setBinmode() {
        this.binmode = true;
        this.stream.setBinmode();
    }
    
    public boolean isAutoclose() {
        return this.stream.isAutoclose();
    }
    
    public void setAutoclose(final boolean autoclose) {
        this.stream.setAutoclose(autoclose);
    }
    
    public ByteList fgets(final ByteList separatorString) throws IOException, BadDescriptorException, EOFException {
        return this.convertCRLFToLF(this.stream.fgets(separatorString));
    }
    
    public ByteList readall() throws IOException, BadDescriptorException, EOFException {
        return this.convertCRLFToLF(this.stream.readall());
    }
    
    public int getline(final ByteList dst, final byte terminator) throws IOException, BadDescriptorException {
        if (this.binmode) {
            return this.stream.getline(dst, terminator);
        }
        final ByteList intermediate = new ByteList();
        final int result = this.stream.getline(intermediate, terminator);
        this.convertCRLFToLF(intermediate, dst);
        return result;
    }
    
    public int getline(final ByteList dst, final byte terminator, final long limit) throws IOException, BadDescriptorException {
        if (this.binmode) {
            return this.stream.getline(dst, terminator, limit);
        }
        final ByteList intermediate = new ByteList();
        final int result = this.stream.getline(intermediate, terminator, limit);
        this.convertCRLFToLF(intermediate, dst);
        return result;
    }
    
    public ByteList fread(final int number) throws IOException, BadDescriptorException, EOFException {
        if (number == 0) {
            if (this.stream.feof()) {
                return null;
            }
            return new ByteList(0);
        }
        else {
            boolean eof = false;
            final ByteList bl = new ByteList((number > 4096) ? 4096 : number);
            for (int i = 0; i < number; ++i) {
                final int c = this.fgetc();
                if (c == -1) {
                    eof = true;
                    break;
                }
                bl.append(c);
            }
            if (eof && bl.length() == 0) {
                return null;
            }
            return bl;
        }
    }
    
    public int fwrite(final ByteList string) throws IOException, BadDescriptorException {
        return this.stream.fwrite(this.convertLFToCRLF(string));
    }
    
    public int fgetc() throws IOException, BadDescriptorException, EOFException {
        int c = this.stream.fgetc();
        if (!this.binmode && c == 13) {
            c = this.stream.fgetc();
            if (c != 10) {
                this.stream.ungetc(c);
                return 13;
            }
        }
        return c;
    }
    
    public int ungetc(final int c) {
        return this.stream.ungetc(c);
    }
    
    public void fputc(final int c) throws IOException, BadDescriptorException {
        if (!this.binmode && c == 10) {
            this.stream.fputc(13);
        }
        this.stream.fputc(c);
    }
    
    public ByteList read(final int number) throws IOException, BadDescriptorException, EOFException {
        return this.convertCRLFToLF(this.stream.read(number));
    }
    
    public void fclose() throws IOException, BadDescriptorException {
        this.stream.fclose();
    }
    
    public int fflush() throws IOException, BadDescriptorException {
        return this.stream.fflush();
    }
    
    public void sync() throws IOException, BadDescriptorException {
        this.stream.sync();
    }
    
    public boolean feof() throws IOException, BadDescriptorException {
        return this.stream.feof();
    }
    
    public long fgetpos() throws IOException, PipeException, BadDescriptorException, InvalidValueException {
        return this.stream.fgetpos();
    }
    
    public void lseek(final long offset, final int type) throws IOException, InvalidValueException, PipeException, BadDescriptorException {
        this.stream.lseek(offset, type);
    }
    
    public void ftruncate(final long newLength) throws IOException, PipeException, InvalidValueException, BadDescriptorException {
        this.stream.ftruncate(newLength);
    }
    
    public int ready() throws IOException {
        return this.stream.ready();
    }
    
    public void waitUntilReady() throws IOException, InterruptedException {
        this.stream.waitUntilReady();
    }
    
    public boolean readDataBuffered() {
        return this.stream.readDataBuffered();
    }
    
    public boolean writeDataBuffered() {
        return this.stream.writeDataBuffered();
    }
    
    public InputStream newInputStream() {
        return this.stream.newInputStream();
    }
    
    public OutputStream newOutputStream() {
        return this.stream.newOutputStream();
    }
    
    public boolean isBlocking() {
        return this.stream.isBlocking();
    }
    
    public void setBlocking(final boolean blocking) throws IOException {
        this.stream.setBlocking(blocking);
    }
    
    public void freopen(final Ruby runtime, final String path, final ModeFlags modes) throws DirectoryAsFileException, IOException, InvalidValueException, PipeException, BadDescriptorException {
        this.stream.freopen(runtime, path, modes);
    }
    
    private ByteList convertCRLFToLF(final ByteList input) {
        if (input == null) {
            return null;
        }
        if (this.binmode) {
            return input;
        }
        final ByteList result = new ByteList();
        this.convertCRLFToLF(input, result);
        return result;
    }
    
    private void convertCRLFToLF(final ByteList src, final ByteList dst) {
        for (int i = 0; i < src.length(); ++i) {
            final int b = src.get(i);
            if (b != 13 || i + 1 >= src.length() || src.get(i + 1) != 10) {
                dst.append(b);
            }
        }
    }
    
    private ByteList convertLFToCRLF(final ByteList input) {
        if (input == null) {
            return null;
        }
        if (this.binmode) {
            return input;
        }
        final ByteList result = new ByteList();
        for (int i = 0; i < input.length(); ++i) {
            final int b = input.get(i);
            if (b == 10) {
                result.append(13);
            }
            result.append(b);
        }
        return result;
    }
    
    public Channel getChannel() {
        return this.stream.getChannel();
    }
}

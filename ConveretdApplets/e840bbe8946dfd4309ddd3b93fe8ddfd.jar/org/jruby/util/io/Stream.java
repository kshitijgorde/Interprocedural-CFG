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

public interface Stream
{
    public static final int SEEK_SET = 0;
    public static final int SEEK_CUR = 1;
    public static final int SEEK_END = 2;
    public static final ByteList PARAGRAPH_DELIMETER = ByteList.create("PARAGRPH_DELIM_MRK_ER");
    public static final ByteList PARAGRAPH_SEPARATOR = ByteList.create("\n\n");
    
    ChannelDescriptor getDescriptor();
    
    void clearerr();
    
    ModeFlags getModes();
    
    boolean isSync();
    
    void setSync(final boolean p0);
    
    ByteList fgets(final ByteList p0) throws IOException, BadDescriptorException, EOFException;
    
    ByteList readall() throws IOException, BadDescriptorException, EOFException;
    
    int getline(final ByteList p0, final byte p1) throws IOException, BadDescriptorException;
    
    int getline(final ByteList p0, final byte p1, final long p2) throws IOException, BadDescriptorException;
    
    ByteList fread(final int p0) throws IOException, BadDescriptorException, EOFException;
    
    int fwrite(final ByteList p0) throws IOException, BadDescriptorException;
    
    int fgetc() throws IOException, BadDescriptorException, EOFException;
    
    int ungetc(final int p0);
    
    void fputc(final int p0) throws IOException, BadDescriptorException;
    
    ByteList read(final int p0) throws IOException, BadDescriptorException, EOFException;
    
    void fclose() throws IOException, BadDescriptorException;
    
    int fflush() throws IOException, BadDescriptorException;
    
    void sync() throws IOException, BadDescriptorException;
    
    boolean feof() throws IOException, BadDescriptorException;
    
    long fgetpos() throws IOException, PipeException, BadDescriptorException, InvalidValueException;
    
    void lseek(final long p0, final int p1) throws IOException, InvalidValueException, PipeException, BadDescriptorException;
    
    void ftruncate(final long p0) throws IOException, PipeException, InvalidValueException, BadDescriptorException;
    
    int ready() throws IOException;
    
    void waitUntilReady() throws IOException, InterruptedException;
    
    boolean readDataBuffered();
    
    boolean writeDataBuffered();
    
    InputStream newInputStream();
    
    OutputStream newOutputStream();
    
    boolean isBlocking();
    
    void setBlocking(final boolean p0) throws IOException;
    
    void freopen(final Ruby p0, final String p1, final ModeFlags p2) throws DirectoryAsFileException, IOException, InvalidValueException, PipeException, BadDescriptorException;
    
    void setBinmode();
    
    Channel getChannel();
    
    boolean isAutoclose();
    
    void setAutoclose(final boolean p0);
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.mail.pop3;

import java.util.Enumeration;
import javax.mail.IllegalWriteException;
import javax.mail.Session;
import javax.mail.internet.SharedInputStream;
import javax.mail.internet.InternetHeaders;
import javax.mail.MessageRemovedException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.Folder;
import java.lang.ref.SoftReference;
import javax.mail.internet.MimeMessage;

public class POP3Message extends MimeMessage
{
    static final String UNKNOWN = "UNKNOWN";
    private POP3Folder folder;
    private int hdrSize;
    private int msgSize;
    String uid;
    private SoftReference contentData;
    
    public POP3Message(final Folder folder, final int msgno) throws MessagingException {
        super(folder, msgno);
        this.hdrSize = -1;
        this.msgSize = -1;
        this.uid = "UNKNOWN";
        this.contentData = new SoftReference(null);
        this.folder = (POP3Folder)folder;
    }
    
    public void setFlags(final Flags newFlags, final boolean set) throws MessagingException {
        final Flags oldFlags = (Flags)this.flags.clone();
        super.setFlags(newFlags, set);
        if (!this.flags.equals(oldFlags)) {
            this.folder.notifyMessageChangedListeners(1, this);
        }
    }
    
    public int getSize() throws MessagingException {
        try {
            synchronized (this) {
                if (this.msgSize < 0) {
                    if (this.headers == null) {
                        this.loadHeaders();
                    }
                    if (this.msgSize < 0) {
                        this.msgSize = this.folder.getProtocol().list(this.msgnum) - this.hdrSize;
                    }
                }
                return this.msgSize;
            }
        }
        catch (EOFException eex) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, eex.toString());
        }
        catch (IOException ex) {
            throw new MessagingException("error getting size", ex);
        }
    }
    
    protected InputStream getContentStream() throws MessagingException {
        InputStream cstream = null;
        try {
            synchronized (this) {
                cstream = this.contentData.get();
                if (cstream == null) {
                    InputStream rawcontent = null;
                    final TempFile cache = this.folder.getFileCache();
                    if (cache != null) {
                        final Session s = ((POP3Store)this.folder.getStore()).getSession();
                        if (s.getDebug()) {
                            s.getDebugOut().println("DEBUG POP3: caching message #" + this.msgnum + " in temp file");
                        }
                        final AppendStream os = cache.getAppendStream();
                        final BufferedOutputStream bos = new BufferedOutputStream(os);
                        try {
                            this.folder.getProtocol().retr(this.msgnum, bos);
                        }
                        finally {
                            bos.close();
                        }
                        rawcontent = os.getInputStream();
                    }
                    else {
                        rawcontent = this.folder.getProtocol().retr(this.msgnum, (this.msgSize > 0) ? (this.msgSize + this.hdrSize) : 0);
                    }
                    if (rawcontent == null) {
                        this.expunged = true;
                        throw new MessageRemovedException("can't retrieve message #" + this.msgnum + " in POP3Message.getContentStream");
                    }
                    if (this.headers == null || ((POP3Store)this.folder.getStore()).forgetTopHeaders) {
                        this.headers = new InternetHeaders(rawcontent);
                        this.hdrSize = (int)((SharedInputStream)rawcontent).getPosition();
                    }
                    else {
                        final int offset = 0;
                        int len;
                        do {
                            len = 0;
                            int c1;
                            while ((c1 = rawcontent.read()) >= 0) {
                                if (c1 == 10) {
                                    break;
                                }
                                if (c1 == 13) {
                                    if (rawcontent.available() <= 0) {
                                        break;
                                    }
                                    rawcontent.mark(1);
                                    if (rawcontent.read() != 10) {
                                        rawcontent.reset();
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    ++len;
                                }
                            }
                            if (rawcontent.available() == 0) {
                                break;
                            }
                        } while (len != 0);
                        this.hdrSize = (int)((SharedInputStream)rawcontent).getPosition();
                    }
                    cstream = ((SharedInputStream)rawcontent).newStream(this.hdrSize, -1L);
                    this.msgSize = cstream.available();
                    rawcontent = null;
                    if (cache != null || ((POP3Store)this.folder.getStore()).keepMessageContent) {
                        this.contentStream = cstream;
                    }
                    this.contentData = new SoftReference((T)cstream);
                }
                else {
                    cstream = ((SharedInputStream)cstream).newStream(0L, -1L);
                }
            }
        }
        catch (EOFException eex) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, eex.toString());
        }
        catch (IOException ex) {
            throw new MessagingException("error fetching POP3 content", ex);
        }
        return cstream;
    }
    
    public synchronized void invalidate(final boolean invalidateHeaders) {
        this.content = null;
        final InputStream cstream = this.contentData.get();
        if (cstream != null) {
            try {
                cstream.close();
            }
            catch (IOException ex) {}
            this.contentData = new SoftReference(null);
            this.contentStream = null;
        }
        this.msgSize = -1;
        if (invalidateHeaders) {
            this.headers = null;
            this.hdrSize = -1;
        }
    }
    
    public InputStream top(final int n) throws MessagingException {
        try {
            synchronized (this) {
                return this.folder.getProtocol().top(this.msgnum, n);
            }
        }
        catch (EOFException eex) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, eex.toString());
        }
        catch (IOException ex) {
            throw new MessagingException("error getting size", ex);
        }
    }
    
    public String[] getHeader(final String name) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getHeader(name);
    }
    
    public String getHeader(final String name, final String delimiter) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getHeader(name, delimiter);
    }
    
    public void setHeader(final String name, final String value) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }
    
    public void addHeader(final String name, final String value) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }
    
    public void removeHeader(final String name) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }
    
    public Enumeration getAllHeaders() throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getAllHeaders();
    }
    
    public Enumeration getMatchingHeaders(final String[] names) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getMatchingHeaders(names);
    }
    
    public Enumeration getNonMatchingHeaders(final String[] names) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getNonMatchingHeaders(names);
    }
    
    public void addHeaderLine(final String line) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }
    
    public Enumeration getAllHeaderLines() throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getAllHeaderLines();
    }
    
    public Enumeration getMatchingHeaderLines(final String[] names) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getMatchingHeaderLines(names);
    }
    
    public Enumeration getNonMatchingHeaderLines(final String[] names) throws MessagingException {
        if (this.headers == null) {
            this.loadHeaders();
        }
        return this.headers.getNonMatchingHeaderLines(names);
    }
    
    public void saveChanges() throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }
    
    public synchronized void writeTo(final OutputStream os, final String[] ignoreList) throws IOException, MessagingException {
        if (this.content == null && ignoreList == null && !((POP3Store)this.folder.getStore()).cacheWriteTo) {
            final Session s = ((POP3Store)this.folder.getStore()).getSession();
            if (s.getDebug()) {
                s.getDebugOut().println("DEBUG POP3: streaming msg " + this.msgnum);
            }
            if (!this.folder.getProtocol().retr(this.msgnum, os)) {
                this.expunged = true;
                throw new MessageRemovedException("can't retrieve message #" + this.msgnum + " in POP3Message.writeTo");
            }
        }
        else {
            super.writeTo(os, ignoreList);
        }
    }
    
    private void loadHeaders() throws MessagingException {
        try {
            synchronized (this) {
                if (this.headers != null) {
                    return;
                }
                InputStream hdrs = null;
                if (((POP3Store)this.folder.getStore()).disableTop || (hdrs = this.folder.getProtocol().top(this.msgnum, 0)) == null) {
                    final InputStream cs = this.getContentStream();
                    cs.close();
                }
                else {
                    this.hdrSize = hdrs.available();
                    this.headers = new InternetHeaders(hdrs);
                }
            }
        }
        catch (EOFException eex) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, eex.toString());
        }
        catch (IOException ex) {
            throw new MessagingException("error loading POP3 headers", ex);
        }
    }
}

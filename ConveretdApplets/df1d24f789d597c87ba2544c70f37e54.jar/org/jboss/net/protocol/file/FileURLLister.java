// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.file;

import java.net.MalformedURLException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.jboss.net.protocol.URLLister;
import java.net.URL;
import org.jboss.logging.Logger;
import org.jboss.net.protocol.URLListerBase;

public class FileURLLister extends URLListerBase
{
    private static final Logger log;
    static /* synthetic */ Class class$org$jboss$net$protocol$file$FileURLLister;
    
    public Collection listMembers(final URL baseUrl, final URLLister.URLFilter filter) throws IOException {
        return this.listMembers(baseUrl, filter, false);
    }
    
    public Collection listMembers(final URL baseUrl, final URLLister.URLFilter filter, final boolean scanNonDottedSubDirs) throws IOException {
        final String baseUrlString = baseUrl.toString();
        if (!baseUrlString.endsWith("/")) {
            throw new IOException("Does not end with '/', not a directory url: " + baseUrlString);
        }
        final File dir = new File(baseUrl.getPath());
        if (!dir.isDirectory()) {
            throw new FileNotFoundException("Not pointing to a directory, url: " + baseUrlString);
        }
        final ArrayList resultList = new ArrayList();
        this.listFiles(baseUrl, filter, scanNonDottedSubDirs, resultList);
        return resultList;
    }
    
    private void listFiles(final URL baseUrl, final URLLister.URLFilter filter, final boolean scanNonDottedSubDirs, final ArrayList resultList) throws IOException {
        final File baseDir = new File(baseUrl.getPath());
        final String[] filenames = baseDir.list(new FilenameFilter() {
            public boolean accept(final File dir, final String name) {
                try {
                    return filter.accept(baseUrl, name);
                }
                catch (Exception e) {
                    FileURLLister.log.debug("Unexpected exception filtering entry '" + name + "' in directory '" + baseDir + "'", e);
                    return true;
                }
            }
        });
        if (filenames == null) {
            throw new IOException("Could not list directory '" + baseDir + "', reason unknown");
        }
        final String baseUrlString = baseUrl.toString();
        for (int i = 0; i < filenames.length; ++i) {
            final String filename = filenames[i];
            final File file = new File(baseDir, filename);
            final boolean isDir = file.isDirectory();
            final URL subUrl = this.createURL(baseUrlString, filename, isDir);
            if (scanNonDottedSubDirs && isDir && filename.indexOf(46) == -1) {
                this.listFiles(subUrl, filter, scanNonDottedSubDirs, resultList);
            }
            else {
                resultList.add(subUrl);
            }
        }
    }
    
    private URL createURL(final String baseUrlString, final String filename, final boolean isDirectory) {
        try {
            return new URL(baseUrlString + filename + (isDirectory ? "/" : ""));
        }
        catch (MalformedURLException e) {
            throw new IllegalStateException();
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        log = Logger.getLogger((FileURLLister.class$org$jboss$net$protocol$file$FileURLLister == null) ? (FileURLLister.class$org$jboss$net$protocol$file$FileURLLister = class$("org.jboss.net.protocol.file.FileURLLister")) : FileURLLister.class$org$jboss$net$protocol$file$FileURLLister);
    }
}

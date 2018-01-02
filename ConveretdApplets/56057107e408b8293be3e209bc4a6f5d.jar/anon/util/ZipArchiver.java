// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Enumeration;
import java.io.InterruptedIOException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.File;
import logging.LogHolder;
import logging.LogType;
import java.util.zip.ZipFile;
import java.util.Observable;

public class ZipArchiver extends Observable
{
    private ZipFile m_archive;
    
    public ZipArchiver(final ZipFile archive) {
        this.m_archive = archive;
    }
    
    public boolean extractSingleEntry(final String s, final String s2) {
        try {
            final ZipEntry entry = this.m_archive.getEntry(s);
            if (entry == null) {
                LogHolder.log(3, LogType.MISC, "Entry " + s + " not found.");
                return false;
            }
            RecursiveFileTool.copySingleFile(this.m_archive.getInputStream(entry), new File(s2));
            return true;
        }
        catch (IOException ex) {
            LogHolder.log(3, LogType.MISC, "Extracting entry " + s + " failed", ex);
            return false;
        }
    }
    
    public boolean extractArchive(final String s, final String s2) {
        final Vector vector = new Vector<ZipEntry>();
        final Vector vector2 = new Vector<String>();
        final Vector<String> vector3 = new Vector<String>();
        int n = 0;
        int n2 = 0;
        long n3 = 0L;
        long n4 = 0L;
        if (this.m_archive == null) {
            LogHolder.log(3, LogType.MISC, "Archive is null");
            return false;
        }
        if (s2 == null) {
            LogHolder.log(3, LogType.MISC, "Error while extracting archive " + this.m_archive.getName() + ": destination address is null");
            return false;
        }
        try {
            final Enumeration<? extends ZipEntry> entries = this.m_archive.entries();
            while (entries.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
                final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                final String name = zipEntry.getName();
                if (s == null || name.startsWith(s)) {
                    n3 += zipEntry.getSize();
                    if (zipEntry.isDirectory()) {
                        int n5;
                        for (n5 = 0; n5 < vector2.size() && vector2.elementAt(n5).compareTo(name) <= 0; ++n5) {}
                        vector2.insertElementAt(name, n5);
                    }
                    else {
                        vector.addElement(zipEntry);
                    }
                }
            }
            if (vector.size() == 0 && vector2.size() == 0) {
                LogHolder.log(3, LogType.MISC, "No matching files for " + s + " found in archive " + this.m_archive.getName());
                this.notifyAboutChanges(0L, 0L, 3);
                return false;
            }
            final Enumeration<String> elements = vector2.elements();
            while (elements.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
                final String s3 = elements.nextElement();
                final File file = new File(s2 + File.separator + s3);
                if (file != null) {
                    if (!file.exists() && !file.mkdir()) {
                        LogHolder.log(3, LogType.MISC, "Error while extracting archive " + this.m_archive.getName() + ": could not create directory " + file.getAbsolutePath());
                        extractErrorRollback(vector2, s2);
                        return false;
                    }
                    vector3.addElement(s3);
                }
                ++n;
            }
            this.notifyAboutChangesInterruptable(n4, n3, 1);
            final Enumeration<ZipEntry> elements2 = vector.elements();
            while (elements2.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
                final ZipEntry zipEntry2 = elements2.nextElement();
                RecursiveFileTool.copySingleFile(this.m_archive.getInputStream(zipEntry2), new File(s2 + File.separator + zipEntry2.getName()));
                vector3.addElement(zipEntry2.getName());
                n4 += zipEntry2.getSize();
                this.notifyAboutChangesInterruptable(n4, n3, 1);
                ++n2;
            }
        }
        catch (IllegalStateException ex2) {
            LogHolder.log(3, LogType.MISC, "Cannot extract archive " + this.m_archive.getName() + ": file already closed");
            this.notifyAboutChanges(n4, n3, 3);
            return false;
        }
        catch (InterruptedIOException ex3) {
            LogHolder.log(7, LogType.MISC, "Process of extracting " + this.m_archive.getName() + " cancelled");
            extractErrorRollback(vector3, s2);
            this.notifyAboutChanges(n4, n3, 2);
            return false;
        }
        catch (InterruptedException ex4) {
            LogHolder.log(7, LogType.MISC, "Process of extracting " + this.m_archive.getName() + " cancelled");
            extractErrorRollback(vector3, s2);
            this.notifyAboutChanges(n4, n3, 2);
            return false;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, "Cannot extract archive " + this.m_archive.getName() + ": error occured: ", ex);
            extractErrorRollback(vector3, s2);
            this.notifyAboutChanges(n4, n3, 3);
            return false;
        }
        this.notifyAboutChanges(n4, n3, 0);
        return true;
    }
    
    private void notifyAboutChanges(final long n, final long n2, final int n3) {
        final ZipEvent zipEvent = new ZipEvent(n, n2, n3);
        this.setChanged();
        this.notifyObservers(zipEvent);
    }
    
    private void notifyAboutChangesInterruptable(final long n, final long n2, final int n3) throws InterruptedException {
        this.notifyAboutChanges(n, n2, n3);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
    
    private static void extractErrorRollback(final Vector vector, final String s) {
        for (int i = vector.size(); i > 0; --i) {
            final File file = new File(s + File.separator + vector.elementAt(i - 1));
            if (file.exists()) {
                final String s2 = file.delete() ? " " : " not ";
                LogHolder.log((s2.trim().length() == 0) ? 7 : 3, LogType.MISC, "Rollback: file " + file.getAbsolutePath() + s2 + "successfully deleted");
            }
        }
    }
    
    public class ZipEvent implements ProgressCapsule
    {
        private int value;
        private int maxValue;
        private int minValue;
        private int status;
        
        public ZipEvent(final long n, final long n2, final int status) {
            this.minValue = 0;
            if (n2 > 2147483647L) {
                this.value = (int)(n / n2 * 2.147483647E9);
                this.maxValue = Integer.MAX_VALUE;
            }
            else {
                this.value = (int)n;
                this.maxValue = (int)n2;
            }
            this.status = status;
        }
        
        public int getMaximum() {
            return this.maxValue;
        }
        
        public int getMinimum() {
            return this.minValue;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public int getStatus() {
            return this.status;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.util.Collection;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeListener;
import java.util.List;
import sun.awt.shell.ShellFolder;
import javax.swing.event.ListDataEvent;
import java.io.File;
import javax.swing.plaf.basic.BasicFileChooserUI;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicDirectoryModel;

public class TinyDirectoryModel extends BasicDirectoryModel
{
    private JFileChooser filechooser;
    private Vector fileCache;
    private LoadFilesThread loadThread;
    private Vector files;
    private Vector directories;
    private int fetchID;
    private PropertyChangeSupport changeSupport;
    private boolean busy;
    
    public TinyDirectoryModel(final JFileChooser filechooser) {
        super(filechooser);
        this.filechooser = null;
        this.fileCache = new Vector(50);
        this.loadThread = null;
        this.files = null;
        this.directories = null;
        this.fetchID = 0;
        this.busy = false;
        this.filechooser = filechooser;
        this.validateFileCache();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName == "directoryChanged" || propertyName == "fileViewChanged" || propertyName == "fileFilterChanged" || propertyName == "FileHidingChanged" || propertyName == "fileSelectionChanged") {
            this.validateFileCache();
        }
        else if ("UI".equals(propertyName)) {
            final Object oldValue = propertyChangeEvent.getOldValue();
            if (oldValue instanceof BasicFileChooserUI) {
                final BasicDirectoryModel model = ((BasicFileChooserUI)oldValue).getModel();
                if (model != null) {
                    model.invalidateFileCache();
                }
            }
        }
        else if ("JFileChooserDialogIsClosingProperty".equals(propertyName)) {
            this.invalidateFileCache();
        }
    }
    
    public void invalidateFileCache() {
        if (this.loadThread != null) {
            this.loadThread.interrupt();
            this.loadThread.cancelRunnables();
            this.loadThread = null;
        }
    }
    
    public Vector getDirectories() {
        synchronized (this.fileCache) {
            if (this.directories != null) {
                return this.directories;
            }
            this.getFiles();
            return this.directories;
        }
    }
    
    public Vector getFiles() {
        synchronized (this.fileCache) {
            if (this.files != null) {
                return this.files;
            }
            this.files = new Vector();
            (this.directories = new Vector()).addElement(this.filechooser.getFileSystemView().createFileObject(this.filechooser.getCurrentDirectory(), ".."));
            for (int i = 0; i < this.getSize(); ++i) {
                final File file = this.fileCache.get(i);
                if (this.filechooser.isTraversable(file)) {
                    this.directories.add(file);
                }
                else {
                    this.files.add(file);
                }
            }
            return this.files;
        }
    }
    
    public void validateFileCache() {
        if (this.filechooser == null) {
            return;
        }
        final File currentDirectory = this.filechooser.getCurrentDirectory();
        if (currentDirectory == null) {
            return;
        }
        if (this.loadThread != null) {
            this.loadThread.interrupt();
            this.loadThread.cancelRunnables();
        }
        this.setBusy(true, ++this.fetchID);
        (this.loadThread = new LoadFilesThread(currentDirectory, this.fetchID)).start();
    }
    
    public boolean renameFile(final File file, final File file2) {
        synchronized (this.fileCache) {
            if (file.renameTo(file2)) {
                this.validateFileCache();
                return true;
            }
            return false;
        }
    }
    
    public void fireContentsChanged() {
        this.fireContentsChanged(this, 0, this.getSize() - 1);
    }
    
    public int getSize() {
        return this.fileCache.size();
    }
    
    public boolean contains(final Object o) {
        return this.fileCache.contains(o);
    }
    
    public int indexOf(final Object o) {
        return this.fileCache.indexOf(o);
    }
    
    public Object getElementAt(final int n) {
        return this.fileCache.get(n);
    }
    
    public Vector getFileCache() {
        return this.fileCache;
    }
    
    public void intervalAdded(final ListDataEvent listDataEvent) {
    }
    
    public void intervalRemoved(final ListDataEvent listDataEvent) {
    }
    
    protected void sort(final Vector vector) {
        ShellFolder.sortFiles((List)vector);
    }
    
    protected boolean lt(final File file, final File file2) {
        final int compareTo = file.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
        if (compareTo != 0) {
            return compareTo < 0;
        }
        return file.getName().compareTo(file2.getName()) < 0;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport == null) {
            this.changeSupport = new PropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.changeSupport != null) {
            this.changeSupport.removePropertyChangeListener(propertyChangeListener);
        }
    }
    
    public PropertyChangeListener[] getPropertyChangeListeners() {
        if (this.changeSupport == null) {
            return new PropertyChangeListener[0];
        }
        return this.changeSupport.getPropertyChangeListeners();
    }
    
    protected void firePropertyChange(final String s, final boolean b, final boolean b2) {
        if (this.changeSupport != null) {
            this.changeSupport.firePropertyChange(s, b, b2);
        }
    }
    
    private synchronized void setBusy(final boolean busy, final int n) {
        if (n == this.fetchID) {
            final boolean busy2 = this.busy;
            this.busy = busy;
            if (this.changeSupport != null && busy != busy2) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        TinyDirectoryModel.this.firePropertyChange("busy", !busy, busy);
                    }
                });
            }
        }
    }
    
    class DoChangeContents implements Runnable
    {
        private List addFiles;
        private List remFiles;
        private boolean doFire;
        private int fid;
        private int addStart;
        private int remStart;
        private int change;
        
        public DoChangeContents(final List addFiles, final int addStart, final List remFiles, final int remStart, final int fid) {
            this.doFire = true;
            this.addStart = 0;
            this.remStart = 0;
            this.addFiles = addFiles;
            this.addStart = addStart;
            this.remFiles = remFiles;
            this.remStart = remStart;
            this.fid = fid;
        }
        
        synchronized void cancel() {
            this.doFire = false;
        }
        
        public synchronized void run() {
            if (TinyDirectoryModel.this.fetchID == this.fid && this.doFire) {
                final int n = (this.remFiles == null) ? 0 : this.remFiles.size();
                final int n2 = (this.addFiles == null) ? 0 : this.addFiles.size();
                synchronized (TinyDirectoryModel.this.fileCache) {
                    if (n > 0) {
                        TinyDirectoryModel.this.fileCache.removeAll(this.remFiles);
                    }
                    if (n2 > 0) {
                        TinyDirectoryModel.this.fileCache.addAll(this.addStart, this.addFiles);
                    }
                    TinyDirectoryModel.this.files = null;
                    TinyDirectoryModel.this.directories = null;
                }
                if (n > 0 && n2 == 0) {
                    TinyDirectoryModel.this.fireIntervalRemoved(TinyDirectoryModel.this, this.remStart, this.remStart + n - 1);
                }
                else if (n2 > 0 && n == 0 && TinyDirectoryModel.this.fileCache.size() > n2) {
                    TinyDirectoryModel.this.fireIntervalAdded(TinyDirectoryModel.this, this.addStart, this.addStart + n2 - 1);
                }
                else {
                    TinyDirectoryModel.this.fireContentsChanged();
                }
            }
        }
    }
    
    class LoadFilesThread extends Thread
    {
        File currentDirectory;
        int fid;
        Vector runnables;
        
        public LoadFilesThread(final File currentDirectory, final int fid) {
            super("Basic L&F File Loading Thread");
            this.currentDirectory = null;
            this.runnables = new Vector(10);
            this.currentDirectory = currentDirectory;
            this.fid = fid;
        }
        
        private void invokeLater(final Runnable runnable) {
            this.runnables.addElement(runnable);
            SwingUtilities.invokeLater(runnable);
        }
        
        public void run() {
            this.run0();
            TinyDirectoryModel.this.setBusy(false, this.fid);
        }
        
        public void run0() {
            final File[] files = TinyDirectoryModel.this.filechooser.getFileSystemView().getFiles(this.currentDirectory, TinyDirectoryModel.this.filechooser.isFileHidingEnabled());
            final Vector<File> vector = new Vector<File>();
            if (this.isInterrupted()) {
                return;
            }
            for (int i = 0; i < files.length; ++i) {
                if (TinyDirectoryModel.this.filechooser.accept(files[i])) {
                    vector.addElement(files[i]);
                }
            }
            if (this.isInterrupted()) {
                return;
            }
            TinyDirectoryModel.this.sort(vector);
            final Vector<File> vector2 = new Vector<File>(50);
            final Vector<File> vector3 = new Vector<File>();
            for (int j = 0; j < vector.size(); ++j) {
                final File file = vector.elementAt(j);
                final boolean traversable = TinyDirectoryModel.this.filechooser.isTraversable(file);
                if (traversable) {
                    vector2.addElement(file);
                }
                else if (!traversable && TinyDirectoryModel.this.filechooser.isFileSelectionEnabled()) {
                    vector3.addElement(file);
                }
                if (this.isInterrupted()) {
                    return;
                }
            }
            Vector<Object> vector4 = new Vector<Object>(vector2);
            vector4.addAll(vector3);
            final int size = vector4.size();
            final int size2 = TinyDirectoryModel.this.fileCache.size();
            if (size > size2) {
                int n = size2;
                int n2 = size;
                for (int k = 0; k < size2; ++k) {
                    if (!vector4.get(k).equals(TinyDirectoryModel.this.fileCache.get(k))) {
                        n = k;
                        for (int l = k; l < size; ++l) {
                            if (vector4.get(l).equals(TinyDirectoryModel.this.fileCache.get(k))) {
                                n2 = l;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (n >= 0 && n2 > n && vector4.subList(n2, size).equals(TinyDirectoryModel.this.fileCache.subList(n, size2))) {
                    if (this.isInterrupted()) {
                        return;
                    }
                    this.invokeLater(new DoChangeContents(vector4.subList(n, n2), n, null, 0, this.fid));
                    vector4 = null;
                }
            }
            else if (size < size2) {
                int n3 = -1;
                int n4 = -1;
                for (int n5 = 0; n5 < size; ++n5) {
                    if (!vector4.get(n5).equals(TinyDirectoryModel.this.fileCache.get(n5))) {
                        n3 = n5;
                        n4 = n5 + size2 - size;
                        break;
                    }
                }
                if (n3 >= 0 && n4 > n3 && TinyDirectoryModel.this.fileCache.subList(n4, size2).equals(vector4.subList(n3, size))) {
                    if (this.isInterrupted()) {
                        return;
                    }
                    this.invokeLater(new DoChangeContents(null, 0, new Vector(TinyDirectoryModel.this.fileCache.subList(n3, n4)), n3, this.fid));
                    vector4 = null;
                }
            }
            if (vector4 != null && !TinyDirectoryModel.this.fileCache.equals(vector4)) {
                if (this.isInterrupted()) {
                    this.cancelRunnables(this.runnables);
                }
                this.invokeLater(new DoChangeContents(vector4, 0, TinyDirectoryModel.this.fileCache, 0, this.fid));
            }
        }
        
        public void cancelRunnables(final Vector vector) {
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).cancel();
            }
        }
        
        public void cancelRunnables() {
            this.cancelRunnables(this.runnables);
        }
    }
}

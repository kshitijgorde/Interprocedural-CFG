// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.B;

import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Arrays;
import jmaster.util.swing.filetree.DefaultFileComparator;
import java.util.Collection;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import java.util.List;
import java.io.File;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.file.IFileBrowser;

public class B implements IFileBrowser
{
    protected A B;
    protected jmaster.util.C.A E;
    protected jmaster.jumploader.model.api.B A;
    private File F;
    private List D;
    private FileSystemView C;
    private _A G;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$file$IFileBrowserListener;
    
    public B(final jmaster.jumploader.model.api.B a) {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.E = new jmaster.util.C.A((jmaster.jumploader.model.impl.B.B.class$jmaster$jumploader$model$api$file$IFileBrowserListener == null) ? (jmaster.jumploader.model.impl.B.B.class$jmaster$jumploader$model$api$file$IFileBrowserListener = class$("jmaster.jumploader.model.api.file.IFileBrowserListener")) : jmaster.jumploader.model.impl.B.B.class$jmaster$jumploader$model$api$file$IFileBrowserListener);
        this.D = new ArrayList();
        this.C = FileSystemView.getFileSystemView();
        this.A = a;
    }
    
    public void addListener(final IFileBrowserListener fileBrowserListener) {
        this.E.C(fileBrowserListener);
    }
    
    public void removeListener(final IFileBrowserListener fileBrowserListener) {
        this.E.A(fileBrowserListener);
    }
    
    private void A() {
        for (int i = 0; i < this.E.C(); ++i) {
            try {
                ((IFileBrowserListener)this.E.A(i)).fileBrowserLocationChanged(this);
            }
            catch (Exception ex) {
                this.B.E(ex, ex);
            }
        }
    }
    
    private void B() {
        for (int i = 0; i < this.E.C(); ++i) {
            try {
                ((IFileBrowserListener)this.E.A(i)).fileBrowserLocationFilesListed(this);
            }
            catch (Exception ex) {
                this.B.E(ex, ex);
            }
        }
    }
    
    public synchronized void setPath(final File file) {
        this.F = file;
        this.D.clear();
        this.A();
        this.G = new _A();
        this.G.B = file;
        final Thread thread = new Thread(this.G);
        thread.setName("FileListingWorker_" + file.getAbsolutePath());
        thread.setDaemon(true);
        thread.start();
    }
    
    public IFile getFile(final int n) {
        return this.D.get(n);
    }
    
    public int getFileCount() {
        return this.D.size();
    }
    
    public File getPath() {
        return this.F;
    }
    
    private void A(final _A a) {
        this.D.clear();
        this.D.addAll(a.A);
        this.B();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    protected class _A implements Runnable
    {
        File B;
        List A;
        
        protected _A() {
            this.A = new ArrayList();
        }
        
        public void run() {
            final File[] files = jmaster.jumploader.model.impl.B.B.this.C.getFiles(this.B, false);
            if (files != null) {
                Arrays.sort(files, new DefaultFileComparator());
                for (int i = 0; i < files.length; ++i) {
                    final File file = files[i];
                    if (jmaster.jumploader.model.impl.B.B.this.A.H().getFileNamePattern() == null || !file.isFile() || Pattern.matches(jmaster.jumploader.model.impl.B.B.this.A.H().getFileNamePattern(), file.getName())) {
                        this.A.add(new jmaster.jumploader.model.impl.B.A(file));
                    }
                }
            }
            synchronized (jmaster.jumploader.model.impl.B.B.this) {
                if (this.equals(jmaster.jumploader.model.impl.B.B.this.G)) {
                    jmaster.jumploader.model.impl.B.B.this.A(this);
                }
            }
        }
    }
}

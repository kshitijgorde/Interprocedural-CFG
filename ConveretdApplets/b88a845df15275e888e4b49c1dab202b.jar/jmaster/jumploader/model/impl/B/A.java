// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.B;

import jmaster.util.log.B;
import jmaster.jumploader.model.api.common.IAttribute;
import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.impl.C.C;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import jmaster.jumploader.model.api.file.IFile;

public class A implements IFile
{
    public static final String L = "checked";
    protected static FileSystemView K;
    protected static jmaster.util.log.A E;
    protected String G;
    protected File F;
    protected String Q;
    protected long C;
    protected boolean N;
    protected boolean A;
    protected C B;
    protected boolean H;
    protected boolean J;
    protected boolean I;
    private String O;
    private Double P;
    protected String D;
    protected boolean M;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$file$IFile;
    
    public A(final File file) {
        this.C = -1L;
        this.B = new C();
        this.M = true;
        this.G = "" + this.hashCode();
        this.B(file);
    }
    
    public void B(final File file) {
        this.A(file, false);
    }
    
    public void A(final File file, final boolean b) {
        this.A(file, b, false);
    }
    
    public void A(final File f, final boolean b, final boolean b2) {
        if (this.F != null && this.O == null) {
            this.O = this.getPath();
        }
        this.F = f;
        if (this.F != null) {
            synchronized (jmaster.jumploader.model.impl.B.A.K) {
                if (!b2) {
                    this.J = jmaster.jumploader.model.impl.B.A.K.isDrive(f);
                    this.H = jmaster.jumploader.model.impl.B.A.K.isFileSystem(f);
                    this.I = f.isHidden();
                }
                if (!b) {
                    if (this.J || !this.H) {
                        this.Q = jmaster.jumploader.model.impl.B.A.K.getSystemDisplayName(f);
                    }
                    else {
                        this.Q = f.getName();
                    }
                }
                this.C = (f.isFile() ? f.length() : -1L);
                if (!b2) {
                    this.N = (jmaster.jumploader.model.impl.B.A.K.isFileSystem(f) && f.isFile());
                    this.A = (jmaster.jumploader.model.impl.B.A.K.isFileSystem(f) && f.isDirectory());
                    this.I = f.isHidden();
                }
            }
        }
    }
    
    public String toString() {
        return "File [path:" + this.F + "]";
    }
    
    public String getId() {
        return this.G;
    }
    
    public void setId(final String g) {
        this.G = g;
    }
    
    public File getFile() {
        return this.F;
    }
    
    public String getPath() {
        return (this.F == null) ? null : this.F.getAbsolutePath();
    }
    
    public static FileSystemView A() {
        return A.K;
    }
    
    public static void A(final FileSystemView k) {
        A.K = k;
    }
    
    public void A(final C b) {
        this.B = b;
    }
    
    public void D(final boolean a) {
        this.A = a;
    }
    
    public void B(final boolean j) {
        this.J = j;
    }
    
    public void A(final boolean n) {
        this.N = n;
    }
    
    public void C(final boolean h) {
        this.H = h;
    }
    
    public void setLength(final long c) {
        this.C = c;
    }
    
    public void A(final File f) {
        this.F = f;
    }
    
    public long getLength() {
        return this.C;
    }
    
    public String getName() {
        return this.Q;
    }
    
    public boolean isDirectory() {
        return this.A;
    }
    
    public boolean isFile() {
        return this.N;
    }
    
    public IAttributeSet getAttributeSet() {
        return this.B;
    }
    
    public void A(final String q) {
        this.Q = q;
    }
    
    public String getOriginalFilePath() {
        return (this.O == null) ? this.getPath() : this.O;
    }
    
    public void B(final String o) {
        this.O = o;
    }
    
    public Double getCompressionRatio() {
        return this.P;
    }
    
    public void A(final Double p) {
        this.P = p;
    }
    
    public String getMimeType() {
        return this.D;
    }
    
    public void C(final String d) {
        this.D = d;
    }
    
    public boolean isCheckedUpdatable() {
        return this.M;
    }
    
    public void setCheckedUpdatable(final boolean m) {
        this.M = m;
    }
    
    public boolean B() {
        return this.F.exists();
    }
    
    public boolean isDrive() {
        return this.J;
    }
    
    public boolean isFileSystem() {
        return this.H;
    }
    
    public boolean isHidden() {
        return this.I;
    }
    
    public boolean isChecked() {
        final IAttribute attributeByName = this.getAttributeSet().getAttributeByName("checked");
        return attributeByName != null && Boolean.valueOf(attributeByName.getStringValue());
    }
    
    public void setChecked(final boolean b) {
        this.getAttributeSet().setAttribute("checked", "" + b);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        A.K = FileSystemView.getFileSystemView();
        A.E = B.getInstance().getLog((A.class$jmaster$jumploader$model$api$file$IFile == null) ? (A.class$jmaster$jumploader$model$api$file$IFile = class$("jmaster.jumploader.model.api.file.IFile")) : A.class$jmaster$jumploader$model$api$file$IFile);
    }
}

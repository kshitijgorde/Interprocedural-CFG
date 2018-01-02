// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl;

import java.net.URLConnection;
import net.sf.jmimemagic.F;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.model.impl.upload.E;
import jmaster.util.B.D;
import jmaster.jumploader.view.impl.upload.MetadataView;
import jmaster.jumploader.model.impl.upload.UploadFilePreprocessor;
import jmaster.jumploader.model.api.config.ImageConfig;
import jmaster.jumploader.model.api.config.AppletConfig;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.model.api.config.UploaderConfig;
import jmaster.jumploader.model.api.config.SystemConfig;
import jmaster.jumploader.app.JumpLoaderMain;
import jmaster.jumploader.model.api.B;

public class A implements B
{
    protected jmaster.util.log.A D;
    protected JumpLoaderMain C;
    private SystemConfig O;
    private UploaderConfig J;
    private IUploader G;
    private ViewConfig I;
    private AppletConfig B;
    private ImageConfig M;
    private jmaster.jumploader.model.api.C.A F;
    private jmaster.jumploader.model.api.B.A L;
    private jmaster.jumploader.model.impl.B.B N;
    private UploadFilePreprocessor E;
    private jmaster.jumploader.model.impl.A.A K;
    protected MetadataView H;
    private D A;
    
    public A(final JumpLoaderMain c) {
        this.D = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.C = c;
        (this.A = new D()).A("jl_" + c.hashCode() + "_");
        this.O = new SystemConfig(this);
        this.J = new UploaderConfig(this);
        this.I = new ViewConfig(this);
        this.B = new AppletConfig(this);
        this.M = new ImageConfig(this);
        this.L = new jmaster.jumploader.model.impl.image.B(this);
        this.N = new jmaster.jumploader.model.impl.B.B(this);
        this.G = new E(c);
        this.F = new jmaster.jumploader.model.impl.D.A(this);
        this.E = new UploadFilePreprocessor(this);
    }
    
    public void A(final boolean b) {
        try {
            this.G.destroy();
        }
        catch (Exception ex) {
            this.D.E(ex, ex);
        }
        finally {
            this.G = null;
        }
        try {
            this.F.C();
        }
        catch (Exception ex2) {
            this.D.E(ex2, ex2);
        }
        finally {
            this.F = null;
        }
        this.N = null;
        this.L = null;
        this.O = null;
        this.J = null;
        this.I = null;
        this.B = null;
        this.M = null;
        this.K = null;
        this.H = null;
        try {
            this.A.A();
        }
        catch (Exception ex3) {
            this.D.E(ex3, ex3);
        }
        finally {
            this.A = null;
        }
        if (b) {
            System.exit(0);
        }
    }
    
    public JumpLoaderMain E() {
        return this.C;
    }
    
    public SystemConfig I() {
        return this.O;
    }
    
    public IUploader D() {
        return this.G;
    }
    
    public UploaderConfig B() {
        return this.J;
    }
    
    public ViewConfig H() {
        return this.I;
    }
    
    public AppletConfig F() {
        return this.B;
    }
    
    public ImageConfig J() {
        return this.M;
    }
    
    public jmaster.jumploader.model.api.C.A A() {
        return this.F;
    }
    
    public jmaster.jumploader.model.api.B.A L() {
        return this.L;
    }
    
    public IFileBrowser G() {
        return this.N;
    }
    
    public UploadFilePreprocessor N() {
        return this.E;
    }
    
    public jmaster.jumploader.model.impl.A.A K() {
        if (this.K == null && this.J.isUseMetadata() && this.J.getMetadataDescriptorUrl() != null) {
            this.K = new jmaster.jumploader.model.impl.A.A();
            try {
                this.K.C(this.J.getMetadataDescriptorUrl());
            }
            catch (Exception ex) {
                this.D.E("Failed to load metadata from descriptor " + this.J.getMetadataDescriptorUrl(), ex);
            }
        }
        return this.K;
    }
    
    public String A(final IFile file) {
        if (file.isFile() && file.getMimeType() == null) {
            String s = null;
            try {
                if (this.B().isUseJMimeMagic()) {
                    try {
                        final F a = net.sf.jmimemagic.D.A(file.getFile(), this.B().isJmmExtensionHints(), this.B().isJmmOnlyMimeMatch());
                        if (a != null && a.H() != null) {
                            s = a.H();
                            if (a.H().equals("???")) {
                                this.D.C("MagicMatch returned mimetype " + a.H() + ", continue with java routine");
                                s = null;
                            }
                        }
                    }
                    catch (Exception ex) {
                        this.D.A("MagicMatch failed to detect mimetype for file " + file.getFile(), ex);
                    }
                }
                if (s == null) {
                    final URLConnection openConnection = file.getFile().toURI().toURL().openConnection();
                    try {
                        s = openConnection.getContentType();
                    }
                    catch (Throwable t) {
                        this.D.E(t, t);
                    }
                }
                if (s == null) {
                    s = "application/unknown";
                }
                ((jmaster.jumploader.model.impl.B.A)file).C(s);
                if (this.D.B()) {
                    this.D.D("Detected mimetype " + s + " for file " + file.getFile());
                }
            }
            catch (Exception ex2) {
                this.D.A("Failed to determine content type for " + file.getFile(), ex2);
            }
        }
        return file.getMimeType();
    }
    
    public synchronized MetadataView M() {
        if (this.H == null) {
            this.H = new MetadataView(this);
        }
        return this.H;
    }
    
    public D C() {
        return this.A;
    }
}

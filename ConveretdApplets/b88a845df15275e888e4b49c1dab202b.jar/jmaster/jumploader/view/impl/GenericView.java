// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl;

import jmaster.jumploader.model.api.upload.IUploader;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import jmaster.jumploader.view.api.IGenericViewListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.GUIHelper;
import jmaster.util.property.C;
import jmaster.util.property.D;
import jmaster.util.log.A;
import jmaster.jumploader.view.api.IGenericView;
import javax.swing.JPanel;

public abstract class GenericView extends JPanel implements IGenericView
{
    private static final long D = 2158036668180719755L;
    protected A E;
    protected D C;
    protected C G;
    protected GUIHelper I;
    protected jmaster.util.C.A H;
    protected B F;
    protected boolean J;
    static /* synthetic */ Class class$jmaster$jumploader$view$api$IGenericViewListener;
    
    public GenericView(final B f) {
        this.E = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.G = jmaster.util.property.C.A();
        this.I = GUIHelper.getInstance();
        this.H = new jmaster.util.C.A((GenericView.class$jmaster$jumploader$view$api$IGenericViewListener == null) ? (GenericView.class$jmaster$jumploader$view$api$IGenericViewListener = class$("jmaster.jumploader.view.api.IGenericViewListener")) : GenericView.class$jmaster$jumploader$view$api$IGenericViewListener);
        if (f == null) {
            throw new NullPointerException("Null model not allowed");
        }
        this.F = f;
        this.C = this.I.getProperty();
        this.setLayout(new GridBagLayout());
    }
    
    public B getModel() {
        return this.F;
    }
    
    public void addListener(final IGenericViewListener genericViewListener) {
        this.H.C(genericViewListener);
    }
    
    public IGenericViewListener removeListener(final IGenericViewListener genericViewListener) {
        return (IGenericViewListener)this.H.A(genericViewListener);
    }
    
    protected IGenericViewListener A(final int n) {
        return (IGenericViewListener)this.H.A(n);
    }
    
    protected int A() {
        return this.H.C();
    }
    
    protected void A(final Exception ex) {
        this.E.E(ex, ex);
        if (ex instanceof RuntimeException) {
            throw (RuntimeException)ex;
        }
        throw new RuntimeException(ex);
    }
    
    protected void B(final Exception ex) {
        this.E.E(ex, ex);
    }
    
    protected GridBagConstraints A(final GridBagConstraints gridBagConstraints, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return GUIHelper.initGBC(gridBagConstraints, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    protected GridBagConstraints A(final GridBagConstraints gridBagConstraints, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Insets insets) {
        return GUIHelper.initGBC(gridBagConstraints, n, n2, n3, n4, n5, n6, n7, n8, insets);
    }
    
    protected void A(final Object o, final String s, final String s2) {
        this.G.A(o, this.C, this.C.F(s, s2));
    }
    
    public void destroy() {
        this.H.B();
        this.H = null;
        this.F = null;
        this.I = null;
        this.C = null;
        this.G = null;
        this.J = true;
    }
    
    public IUploader getUploader() {
        return (this.F == null) ? null : this.F.D();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}

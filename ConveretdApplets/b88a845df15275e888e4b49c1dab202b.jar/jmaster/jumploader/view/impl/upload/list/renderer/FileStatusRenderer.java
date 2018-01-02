// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.util.B.A;
import javax.swing.Icon;

public class FileStatusRenderer extends AbstractUploadFileRendererComponent
{
    private Icon f;
    private Icon m;
    private Icon p;
    private Icon g;
    private Icon k;
    private Icon j;
    private Icon i;
    private Icon e;
    private String o;
    private String l;
    private String n;
    private String q;
    private String d;
    private String c;
    private String h;
    private String r;
    
    public Icon getIconFailed() {
        return this.j;
    }
    
    public void setIconFailed(final Icon j) {
        this.j = j;
    }
    
    public Icon getIconFinished() {
        return this.k;
    }
    
    public void setIconFinished(final Icon k) {
        this.k = k;
    }
    
    public Icon getIconReady() {
        return this.f;
    }
    
    public void setIconReady(final Icon f) {
        this.f = f;
    }
    
    public Icon getIconUploading() {
        return this.g;
    }
    
    public void setIconUploading(final Icon g) {
        this.g = g;
    }
    
    public String getToolTipTextFailed() {
        return this.c;
    }
    
    public void setToolTipTextFailed(final String c) {
        this.c = c;
    }
    
    public String getToolTipTextFinished() {
        return this.d;
    }
    
    public void setToolTipTextFinished(final String d) {
        this.d = d;
    }
    
    public String getToolTipTextReady() {
        return this.o;
    }
    
    public void setToolTipTextReady(final String o) {
        this.o = o;
    }
    
    public String getToolTipTextUploading() {
        return this.q;
    }
    
    public void setToolTipTextUploading(final String q) {
        this.q = q;
    }
    
    public Icon getIconPreprocessing() {
        return this.m;
    }
    
    public void setIconPreprocessing(final Icon m) {
        this.m = m;
    }
    
    public String getToolTipTextPreprocessing() {
        return this.l;
    }
    
    public void setToolTipTextPreprocessing(final String l) {
        this.l = l;
    }
    
    public Icon getIconServerProcessing() {
        return this.p;
    }
    
    public void setIconServerProcessing(final Icon p) {
        this.p = p;
    }
    
    public String getToolTipTextServerProcessing() {
        return this.n;
    }
    
    public void setToolTipTextServerProcessing(final String n) {
        this.n = n;
    }
    
    public Icon getIconDownloading() {
        return this.i;
    }
    
    public void setIconDownloading(final Icon i) {
        this.i = i;
    }
    
    public Icon getIconEditing() {
        return this.e;
    }
    
    public void setIconEditing(final Icon e) {
        this.e = e;
    }
    
    public String getToolTipTextDownloading() {
        return this.h;
    }
    
    public void setToolTipTextDownloading(final String h) {
        this.h = h;
    }
    
    public String getToolTipTextEditing() {
        return this.r;
    }
    
    public void setToolTipTextEditing(final String r) {
        this.r = r;
    }
    
    public void prepare() {
        Icon delegate = null;
        String toolTipText = null;
        final IUploadFile uploadFile = this.getUploadFile();
        switch (uploadFile.getStatus()) {
            case 3: {
                delegate = this.j;
                String s = uploadFile.getError().getMessage();
                if (jmaster.util.B.A.C(s)) {
                    s = "" + uploadFile.getError();
                }
                toolTipText = "<html>" + this.getToolTipTextFailed() + s + "</html>";
                break;
            }
            case 2: {
                delegate = this.k;
                toolTipText = this.getToolTipTextFinished();
                break;
            }
            case 4: {
                delegate = this.m;
                toolTipText = this.getToolTipTextPreprocessing();
                break;
            }
            case 0: {
                delegate = this.f;
                toolTipText = this.getToolTipTextReady();
                break;
            }
            case 1: {
                delegate = this.g;
                toolTipText = this.getToolTipTextUploading();
                if (uploadFile.isServerProcessing()) {
                    delegate = this.p;
                    toolTipText = this.getToolTipTextServerProcessing();
                    break;
                }
                break;
            }
            case 5: {
                delegate = this.i;
                toolTipText = this.getToolTipTextDownloading();
                break;
            }
            case 6: {
                delegate = this.e;
                toolTipText = this.getToolTipTextEditing();
                break;
            }
        }
        this.R.getLinkedIcon().setDelegate(delegate);
        this.setToolTipText(toolTipText);
        super.prepare();
    }
}

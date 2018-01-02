// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.util.ArrayList;
import jmaster.util.http.D;
import java.io.File;
import java.util.List;
import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.impl.B.A;

public class B extends A implements IUploadFile
{
    private E Y;
    private int X;
    private Exception a;
    private ITransferProgress g;
    private int V;
    private boolean W;
    private String Z;
    private IAttributeSet R;
    private boolean f;
    private boolean U;
    private List e;
    private String T;
    private String S;
    private String d;
    private int _;
    private boolean c;
    private boolean b;
    
    public B(final E y, final File file) {
        super(file);
        this.X = 0;
        this.V = 0;
        this.W = false;
        this._ = 0;
        this.c = false;
        this.b = false;
        this.Y = y;
    }
    
    public String toString() {
        return "Upload File [status:" + this.X + ", path:" + this.F + "]";
    }
    
    public Exception getError() {
        return this.a;
    }
    
    public void A(final Exception a) {
        this.a = a;
    }
    
    public int getStatus() {
        return this.X;
    }
    
    public void B(final int x) {
        this.X = x;
    }
    
    public ITransferProgress getTransferProgress() {
        return this.g;
    }
    
    public void A(final ITransferProgress g) {
        this.g = g;
    }
    
    public int getUploadedPartitionCount() {
        return this.V;
    }
    
    public void setUploadedPartitionCount(final int v) {
        this.V = v;
    }
    
    public void F(final boolean w) {
        this.W = w;
    }
    
    public String getResponseContent() {
        return this.Z;
    }
    
    public void E(final String z) {
        this.Z = z;
    }
    
    public IAttributeSet getResponseHeaders() {
        return this.R;
    }
    
    public void A(final IAttributeSet r) {
        this.R = r;
    }
    
    public boolean isServerProcessing() {
        return this.f;
    }
    
    public void G(final boolean f) {
        this.f = f;
    }
    
    public boolean isTempFile() {
        return this.U;
    }
    
    public void E(final boolean u) {
        this.U = u;
    }
    
    public List C() {
        return this.e;
    }
    
    public void A(final List e) {
        this.e = e;
    }
    
    public String D() {
        return this.T;
    }
    
    public void D(final String t) {
        this.T = t;
    }
    
    public String getKey() {
        return this.d;
    }
    
    public void setKey(final String d) {
        this.d = d;
    }
    
    public int getType() {
        return this._;
    }
    
    public void A(final int _) {
        this._ = _;
    }
    
    public E E() {
        return this.Y;
    }
    
    public void A(final E y) {
        this.Y = y;
    }
    
    public String F() {
        return this.S;
    }
    
    public void F(final String s) {
        this.S = s;
    }
    
    public boolean G() {
        return this.c;
    }
    
    public void H(final boolean c) {
        this.c = c;
    }
    
    public boolean isFailed() {
        return this.getStatus() == 3;
    }
    
    public boolean isFinished() {
        return this.getStatus() == 2;
    }
    
    public boolean isReady() {
        return this.getStatus() == 0;
    }
    
    public boolean isPreprocessing() {
        return this.getStatus() == 4;
    }
    
    public boolean isUploading() {
        return this.getStatus() == 1;
    }
    
    public boolean isDownloading() {
        return this.getStatus() == 5;
    }
    
    public boolean isEditing() {
        return this.getStatus() == 6;
    }
    
    public int getIndex() {
        return this.Y.indexOfFile(this);
    }
    
    public boolean isStoppable() {
        return this.Y.D(this);
    }
    
    public boolean isRemovable() {
        return this.Y.E(this);
    }
    
    public boolean isRetryable() {
        return this.Y.C(this);
    }
    
    public boolean isEditableImage() {
        return this.W;
    }
    
    public void A(final D d) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(d);
    }
    
    public boolean isDocumentFileWasLocked() {
        return this.b;
    }
    
    public void setDocumentFileWasLocked(final boolean b) {
        this.b = b;
    }
}

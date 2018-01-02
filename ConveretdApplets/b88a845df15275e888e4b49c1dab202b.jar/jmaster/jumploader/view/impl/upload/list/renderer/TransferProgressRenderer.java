// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.upload.IUploadFile;
import javax.swing.ImageIcon;
import jmaster.util.swing.icon.ProgressIcon;

public class TransferProgressRenderer extends AbstractUploadFileRendererComponent
{
    private ProgressIcon W;
    
    public TransferProgressRenderer() {
        this.W = new ProgressIcon();
        this.R = this.W;
    }
    
    public ImageIcon getIconLeftEmpty() {
        return this.W.getIconLeftEmpty();
    }
    
    public ImageIcon getIconLeftFilled() {
        return this.W.getIconLeftFilled();
    }
    
    public ImageIcon getIconMiddleEmpty() {
        return this.W.getIconMiddleEmpty();
    }
    
    public ImageIcon getIconMiddleFilled() {
        return this.W.getIconMiddleFilled();
    }
    
    public ImageIcon getIconPos() {
        return this.W.getIconPos();
    }
    
    public ImageIcon getIconRightEmpty() {
        return this.W.getIconRightEmpty();
    }
    
    public ImageIcon getIconRightFilled() {
        return this.W.getIconRightFilled();
    }
    
    public void setIconLeftEmpty(final ImageIcon iconLeftEmpty) {
        this.W.setIconLeftEmpty(iconLeftEmpty);
    }
    
    public void setIconLeftFilled(final ImageIcon iconLeftFilled) {
        this.W.setIconLeftFilled(iconLeftFilled);
    }
    
    public void setIconMiddleEmpty(final ImageIcon iconMiddleEmpty) {
        this.W.setIconMiddleEmpty(iconMiddleEmpty);
    }
    
    public void setIconMiddleFilled(final ImageIcon iconMiddleFilled) {
        this.W.setIconMiddleFilled(iconMiddleFilled);
    }
    
    public void setIconPos(final ImageIcon iconPos) {
        this.W.setIconPos(iconPos);
    }
    
    public void setIconRightEmpty(final ImageIcon iconRightEmpty) {
        this.W.setIconRightEmpty(iconRightEmpty);
    }
    
    public void setIconRightFilled(final ImageIcon iconRightFilled) {
        this.W.setIconRightFilled(iconRightFilled);
    }
    
    public ImageIcon getIconMiddleStripes() {
        return this.W.getIconMiddleStripes();
    }
    
    public long getStripesSpeed() {
        return this.W.getStripesSpeed();
    }
    
    public void setIconMiddleStripes(final ImageIcon iconMiddleStripes) {
        this.W.setIconMiddleStripes(iconMiddleStripes);
    }
    
    public void setStripesSpeed(final long stripesSpeed) {
        this.W.setStripesSpeed(stripesSpeed);
    }
    
    public void prepare() {
        final IUploadFile uploadFile = this.getUploadFile();
        final ITransferProgress transferProgress = uploadFile.getTransferProgress();
        this.setVisible(transferProgress != null || uploadFile.isServerProcessing() || uploadFile.isPreprocessing());
        if (transferProgress != null) {
            this.W.setCompleted(transferProgress.getCompletion());
        }
        else {
            this.W.setCompleted(-1.0);
        }
        if (uploadFile.isServerProcessing()) {
            this.W.setCompleted(-1.0);
        }
        super.prepare();
    }
}

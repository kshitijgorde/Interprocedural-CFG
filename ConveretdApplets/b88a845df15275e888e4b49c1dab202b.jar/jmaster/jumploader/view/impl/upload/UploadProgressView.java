// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload;

import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.upload.IUploader;
import java.awt.GridBagConstraints;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import javax.swing.JLabel;
import jmaster.util.swing.label.ProgressLabel;
import jmaster.jumploader.model.api.upload.B;
import jmaster.jumploader.view.impl.GenericView;

public class UploadProgressView extends GenericView implements B, Runnable
{
    private static final long \u00de = 8268631162146549122L;
    public static final String PREFIX = "uploadProgressView";
    private static final String \u00e7 = "uploadProgressViewUpdater";
    private static final long \u00e2 = 100L;
    private static final String \u00dc = "\\$\\{completionPercent\\}";
    private static final String \u00e8 = "\\$\\{lengthTransferred\\}";
    private static final String \u00e3 = "\\$\\{lengthTotal\\}";
    private static final String \u00e1 = "\\$\\{transferRate\\}";
    private Thread \u00dd;
    private long \u00df;
    private ProgressLabel \u00e4;
    private JLabel \u00e6;
    private JLabel \u00e0;
    private String \u00e5;
    
    public UploadProgressView(final jmaster.jumploader.model.api.B b) {
        super(b);
        this.\u00df = 100L;
        this.\u00e4 = new ProgressLabel();
        this.\u00e6 = new JLabel();
        this.\u00e0 = new JLabel();
        this.A(this, "uploadProgressView", null);
        this.A(this.\u00e4, "uploadProgressView", "lblProgress");
        this.A(this.\u00e4.getProgressIcon(), "uploadProgressView", "lblProgress.progressIcon");
        this.A(this.\u00e6, "uploadProgressView", "lblTimeLeft");
        this.A(this.\u00e0, "uploadProgressView", "lblStatus");
        b.D().addListener(this);
        final GridBagConstraints gbc = this.I.newGBC();
        this.add(this.\u00e4, this.A(gbc, 0, 0, 2, 1, 1, 0, 2, 12, GUIHelper.INSETS4));
        this.add(this.\u00e0, this.A(gbc, 0, 1, 1, 1, 1, 0, 2, 12, GUIHelper.INSETS4));
        this.add(this.\u00e6, this.A(gbc, 1, 1, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
    }
    
    public long getUpdateInterval() {
        return this.\u00df;
    }
    
    public void setUpdateInterval(final long \u00df) {
        this.\u00df = \u00df;
    }
    
    public ProgressLabel getLblProgress() {
        return this.\u00e4;
    }
    
    public JLabel getLblStatus() {
        return this.\u00e0;
    }
    
    public JLabel getLblTimeLeft() {
        return this.\u00e6;
    }
    
    public String getProgressMessage() {
        return this.\u00e5;
    }
    
    public void setProgressMessage(final String \u00e5) {
        this.\u00e5 = \u00e5;
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
        this.updateView();
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
        if (uploader.isUploading()) {
            (this.\u00dd = new Thread(this)).setName("uploadProgressViewUpdater");
            this.\u00dd.setDaemon(true);
            this.\u00dd.start();
        }
        else if (uploader.isReady()) {
            final Thread \u00fd = this.\u00dd;
            this.\u00dd = null;
            \u00fd.interrupt();
        }
        this.updateView();
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void run() {
        while (this.\u00dd != null) {
            this.updateView();
            try {
                Thread.sleep(this.\u00df);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        final IUploader uploader = this.getUploader();
        if (uploader != null) {
            final ITransferProgress transferProgress = uploader.getTransferProgress();
            if (uploader.isUploading() && transferProgress != null) {
                this.\u00e4.setCompleted(transferProgress.getCompletion());
                this.\u00e6.setText(this.I.getTimeFormatted(transferProgress.getTimeLeft()));
                String text = this.\u00e5;
                if (text != null) {
                    text = text.replaceAll("\\$\\{completionPercent\\}", this.I.getPercentFormatted(transferProgress.getCompletionPercent())).replaceAll("\\$\\{lengthTransferred\\}", this.I.getLengthFormatted(transferProgress.getBytesTransferred())).replaceAll("\\$\\{lengthTotal\\}", this.I.getLengthFormatted(transferProgress.getBytesTotal())).replaceAll("\\$\\{transferRate\\}", this.I.getLengthFormatted(transferProgress.getRate()) + "/s");
                }
                this.\u00e0.setText(text);
            }
            else {
                this.\u00e4.setCompleted(0.0);
                this.\u00e6.setText(null);
                this.\u00e0.setText(null);
            }
        }
    }
}

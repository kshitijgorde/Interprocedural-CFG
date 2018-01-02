// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.main;

import javax.swing.Icon;
import java.security.MessageDigest;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.view.api.image.IImageView;
import jmaster.jumploader.view.api.upload.IUploadView;
import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.view.api.main.IProgramView;
import jmaster.util.system.SystemHelper;
import java.awt.event.MouseEvent;
import jmaster.jumploader.view.api.IGenericView;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jmaster.jumploader.app.JumpLoaderVersion;
import jmaster.jumploader.view.impl.image.ImageView;
import jmaster.util.swing.ViewSwitch;
import java.awt.event.MouseListener;
import jmaster.jumploader.model.api.upload.B;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.view.impl.TitledView;

public class MainView extends TitledView implements IMainView, B, MouseListener
{
    private static final long \u015a = 8268631162146549122L;
    public static final String PREFIX = "mainView";
    private GlassView \u0155;
    private ViewSwitch \u0157;
    private ProgramView \u0159;
    private ImageView \u0156;
    private boolean \u0158;
    
    public MainView(final jmaster.jumploader.model.api.B b) {
        super(b);
        this.\u0157 = new ViewSwitch();
        this.\u0158 = false;
        this.\u0155 = new GlassView(b, this);
        this.\u0159 = new ProgramView(b, this);
        try {
            this.\u0156 = new ImageView(b, this);
        }
        catch (Throwable t) {}
        this.A(this, "mainView", null);
        if (!JumpLoaderVersion.REGISTERED) {
            this.getTitleLabel().setCursor(this.I.getHandCursor());
            this.getTitleLabel().addMouseListener(this);
        }
        this.F.D().addListener(this);
        this.getContentPanel().setLayout(new BorderLayout());
        this.getContentPanel().add(this.\u0157, "Center");
        this.setCurrentView(this.getProgramView());
        this.updateView();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.getTitleLabel())) {
            SystemHelper.getInstance().openFile("http://jumploader.com");
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public IProgramView getProgramView() {
        return this.\u0159;
    }
    
    public IFileTreeView getFileTreeView() {
        return this.\u0159.getFileTreeView();
    }
    
    public IFileListView getFileListView() {
        return this.\u0159.getFileListView();
    }
    
    public IUploadView getUploadView() {
        return this.\u0159.getUploadView();
    }
    
    public IImageView getImageView() {
        return this.\u0156;
    }
    
    public void showError(final String text) {
        this.getErrorView().setText(text);
    }
    
    public void showInfo(final String text) {
        this.getInfoView().setText(text);
    }
    
    public void showWarning(final String text) {
        this.getWarningView().setText(text);
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        this.\u0159.updateView();
        if (JumpLoaderVersion.REGISTERED && !this.F.H().isMainViewLogoEnabled()) {
            this.getTitleLabel().setVisible(false);
        }
    }
    
    public void destroy() {
        if (this.\u0159 != null) {
            try {
                this.\u0159.destroy();
                this.\u0159 = null;
            }
            catch (Exception ex) {
                this.E.E(ex, ex);
            }
        }
        if (this.\u0156 != null) {
            try {
                this.\u0156.destroy();
                this.\u0156 = null;
            }
            catch (Exception ex2) {
                this.E.E(ex2, ex2);
            }
        }
        super.destroy();
    }
    
    public IGenericView getCurrentView() {
        return (IGenericView)this.\u0157.getCurrentView();
    }
    
    public void setCurrentView(final IGenericView genericView) {
        this.\u0157.setCurrentView((Component)genericView);
    }
    
    public void showGlassView(final boolean visible) {
        this.\u0155.setVisible(visible);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (!this.\u0158 && !JumpLoaderVersion.REGISTERED) {
            try {
                if (!this.\u0114.isVisible()) {
                    throw new Exception();
                }
                final Icon icon = this.\u0114.getIcon();
                final int iconWidth = icon.getIconWidth();
                final int iconHeight = icon.getIconHeight();
                final BufferedImage bufferedImage = new BufferedImage(iconWidth, iconHeight, 2);
                icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                for (int i = 0; i < iconWidth; ++i) {
                    for (int j = 0; j < iconHeight; ++j) {
                        final int rgb = bufferedImage.getRGB(i, j);
                        for (int k = 0; k < 4; ++k) {
                            instance.update((byte)(rgb >>> k & 0xFF));
                        }
                    }
                }
                final byte[] digest = instance.digest();
                final StringBuffer sb = new StringBuffer();
                for (int l = 0; l < digest.length; ++l) {
                    final String hexString = Integer.toHexString(digest[l] & 0xFF);
                    if (hexString.length() < 2) {
                        sb.append('0');
                    }
                    sb.append(hexString);
                }
                final String string = sb.toString();
                if (!"c019115d8fc19f9b48e7ee993981650c".equals(string) && !"fc1d17ac46bdd09c55146368583a8e51".equals(string)) {
                    throw new Exception(string);
                }
                this.\u0158 = true;
            }
            catch (Exception ex) {
                this.E.E(ex, ex);
                this.destroy();
                this.F.A(false);
            }
        }
    }
}

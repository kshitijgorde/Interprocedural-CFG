// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image;

import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import jmaster.jumploader.view.api.image.IImageViewListener;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.impl.image.dialog.resize.ResizeDialog;
import java.awt.event.ActionListener;
import jmaster.jumploader.view.api.image.IImageView;
import jmaster.jumploader.view.impl.TitledView;

public class ImageView extends TitledView implements IImageView, ActionListener
{
    private static final long \u0154 = 8268631162146549122L;
    public static final String PREFIX = "imageView";
    protected ToolbarView \u0150;
    protected ResizeDialog \u0151;
    protected ImageScrollControl \u0152;
    protected IUploadFile \u0153;
    
    public ImageView(final B b, final IMainView mainView) {
        super(b);
        this.\u0152 = new ImageScrollControl(b);
        this.\u0150 = new ToolbarView(b, this.\u0152.getImageControl(), mainView);
        this.A(this, "imageView", null);
        this.\u0150.getCmdDiscardChanges().addActionListener(this);
        this.\u0150.getCmdSaveChanges().addActionListener(this);
        final GridBagConstraints gbc = this.I.newGBC();
        final JPanel contentPanel = this.getContentPanel();
        int n = 0;
        contentPanel.add(this.\u0150, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        contentPanel.add(this.\u0152, this.A(gbc, 0, n++, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.updateView();
    }
    
    public IUploadFile getUploadFile() {
        return this.\u0153;
    }
    
    public ImageScrollControl getImageView() {
        return this.\u0152;
    }
    
    public ResizeDialog getResizeDialog() {
        return this.\u0151;
    }
    
    public ToolbarView getToolbarView() {
        return this.\u0150;
    }
    
    private void Q() {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IImageViewListener)this.H.A(i)).imageViewCloseAction(this);
        }
    }
    
    private void P() {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IImageViewListener)this.H.A(i)).imageViewSaveAction(this);
        }
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.\u0150.getCmdDiscardChanges())) {
            this.Q();
        }
        else if (actionEvent.getSource().equals(this.\u0150.getCmdSaveChanges())) {
            this.P();
        }
    }
    
    public BufferedImage getImage() {
        return this.\u0152.getImageControl().getImage();
    }
    
    public void setImage(final IUploadFile \u0153, final BufferedImage image, final BufferedImage thumb) {
        this.\u0153 = \u0153;
        this.\u0152.getImageControl().setImage(image);
        this.\u0150.setThumb(thumb);
        this.\u0150.setImageModified(false);
    }
    
    public void setZoomToFit() {
        final double min = Math.min(this.\u0152.getWidth() / this.\u0152.getImageControl().getImageWidth(), this.\u0152.getHeight() / this.\u0152.getImageControl().getImageHeight());
        int value = -1;
        for (int n = 0; n < ToolbarView.ZOOM_VALUES.length && ToolbarView.ZOOM_VALUES[n] / 100.0 < min; ++n) {
            value = n;
        }
        if (value != -1) {
            this.\u0150.getZoomSlider().setValue(value);
        }
    }
}

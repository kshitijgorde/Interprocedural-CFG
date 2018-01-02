// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image;

import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import jmaster.jumploader.model.api.B.A;
import jmaster.util.D.A.Q;
import jmaster.util.D.A.C;
import jmaster.util.D.A.L;
import jmaster.util.swing.SwingUtil;
import jmaster.jumploader.view.impl.image.dialog.resize.ResizeOption;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import jmaster.util.D.A.M;
import jmaster.util.D.A.P;
import java.awt.image.ImageFilter;
import jmaster.util.D.A.O;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.Component;
import jmaster.jumploader.view.impl.image.dialog.resize.ResizeView;
import jmaster.jumploader.view.impl.image.dialog.resize.ResizeDialog;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import jmaster.jumploader.view.impl.image.dialog.contrast.FilterContrastDialog;
import jmaster.jumploader.view.impl.image.dialog.contrast.FilterContrastView;
import jmaster.jumploader.view.impl.image.dialog.blur.FilterGblurDialog;
import jmaster.jumploader.view.impl.image.dialog.blur.FilterGblurView;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import jmaster.util.swing.GUIHelper;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ToolbarView extends JPanel implements ActionListener, ChangeListener, ImageControlListener
{
    private static final long L = 5681260325863369834L;
    public static final int[] ZOOM_VALUES;
    public static final int[] ZOOM_LABEL_INDICES;
    public static final int ZOOM_DEFAULT_VALUE_INDEX = 9;
    public static final String PREFIX = "imageViewToolbar";
    protected GUIHelper Y;
    protected JButton J;
    protected JButton H;
    protected JButton V;
    protected JButton R;
    protected JButton W;
    protected JButton P;
    protected JButton O;
    protected JPopupMenu b;
    protected JLabel B;
    protected JSlider K;
    protected JTextField A;
    protected JMenuItem S;
    protected JMenuItem U;
    protected JMenuItem G;
    protected JMenuItem N;
    protected B F;
    protected ImageControl D;
    protected boolean C;
    protected IMainView T;
    protected FilterGblurView M;
    protected FilterGblurDialog I;
    protected FilterContrastView _;
    protected FilterContrastDialog X;
    protected BufferedImage Z;
    protected ImageIcon a;
    protected ResizeDialog E;
    protected ResizeView Q;
    static /* synthetic */ Class class$java$awt$image$ImageFilter;
    
    public ToolbarView(final B f, final ImageControl d, final IMainView t) {
        this.Y = GUIHelper.getInstance();
        this.J = new JButton();
        this.H = new JButton();
        this.V = new JButton();
        this.R = new JButton();
        this.W = new JButton();
        this.P = new JButton();
        this.O = new JButton();
        this.b = new JPopupMenu();
        this.B = new JLabel();
        this.A = new JTextField();
        this.S = new JMenuItem();
        this.U = new JMenuItem();
        this.G = new JMenuItem();
        this.N = new JMenuItem();
        this.F = f;
        this.D = d;
        this.T = t;
        this.Y.injectProperties(this, "imageViewToolbar");
        this.Y.injectProperties(this.J, "imageViewToolbar", "cmdSaveChanges");
        this.Y.injectProperties(this.H, "imageViewToolbar", "cmdDiscardChanges");
        this.Y.injectProperties(this.V, "imageViewToolbar", "cmdRotateCw");
        this.Y.injectProperties(this.R, "imageViewToolbar", "cmdRotateCcw");
        this.Y.injectProperties(this.W, "imageViewToolbar", "cmdCrop");
        this.Y.injectProperties(this.P, "imageViewToolbar", "cmdResize");
        this.Y.injectProperties(this.B, "imageViewToolbar", "zoomIconLabel");
        this.Y.injectProperties(this.A, "imageViewToolbar", "zoomInput");
        this.Y.injectProperties(this.O, "imageViewToolbar", "cmdFilters");
        this.Y.injectProperties(this.b, "imageViewToolbar", "popupFilters");
        this.Y.injectProperties(this.N, "imageViewToolbar", "menuItemFilterContrast");
        this.Y.injectProperties(this.S, "imageViewToolbar", "menuItemFilterGrayscale");
        this.Y.injectProperties(this.U, "imageViewToolbar", "menuItemFilterSepia");
        this.Y.injectProperties(this.G, "imageViewToolbar", "menuItemFilterGblur");
        this.b.add(this.N);
        this.b.add(this.S);
        this.b.add(this.U);
        this.b.add(this.G);
        (this.K = new JSlider(0, ToolbarView.ZOOM_VALUES.length - 1)).putClientProperty("JSlider.isFilled", Boolean.TRUE);
        this.K.setPaintTicks(true);
        this.K.setMajorTickSpacing(100);
        this.K.setMinorTickSpacing(1);
        this.K.setPaintLabels(false);
        this.K.setSnapToTicks(true);
        this.K.setValue(9);
        final JPanel panel = new JPanel();
        panel.add(this.J);
        panel.add(this.H);
        panel.add(new JSeparator());
        panel.add(this.V);
        panel.add(this.R);
        panel.add(this.W);
        panel.add(this.P);
        panel.add(this.O);
        final JPanel panel2 = new JPanel();
        panel2.add(this.B);
        panel2.add(this.K);
        panel2.add(this.A);
        this.setLayout(new BorderLayout());
        this.add(panel, "West");
        this.add(panel2, "East");
        this.H.addActionListener(this);
        this.J.addActionListener(this);
        this.V.addActionListener(this);
        this.R.addActionListener(this);
        this.W.addActionListener(this);
        this.P.addActionListener(this);
        this.O.addActionListener(this);
        this.K.addChangeListener(this);
        this.S.addActionListener(this);
        this.U.addActionListener(this);
        this.G.addActionListener(this);
        this.N.addActionListener(this);
        d.addImageControlListener(this);
        this.stateChanged(new ChangeEvent(this.K));
        this.updateView();
    }
    
    public JButton getCmdDiscardChanges() {
        return this.H;
    }
    
    public JButton getCmdSaveChanges() {
        return this.J;
    }
    
    public boolean isImageModified() {
        return this.C;
    }
    
    public BufferedImage getThumb() {
        return this.Z;
    }
    
    public void setThumb(final BufferedImage z) {
        this.Z = z;
    }
    
    public ImageIcon getSepiaBitmapIcon() {
        return this.a;
    }
    
    public void setSepiaBitmapIcon(final ImageIcon a) {
        this.a = a;
    }
    
    public void setImageModified(final boolean c) {
        this.C = c;
        this.updateView();
    }
    
    public JButton getCmdCrop() {
        return this.W;
    }
    
    public JButton getCmdFilters() {
        return this.O;
    }
    
    public JButton getCmdResize() {
        return this.P;
    }
    
    public JButton getCmdRotateCcw() {
        return this.R;
    }
    
    public JButton getCmdRotateCw() {
        return this.V;
    }
    
    public FilterContrastDialog getFilterContrastDialog() {
        return this.X;
    }
    
    public FilterContrastView getFilterContrastView() {
        return this._;
    }
    
    public FilterGblurDialog getFilterGblurDialog() {
        return this.I;
    }
    
    public FilterGblurView getFilterGblurView() {
        return this.M;
    }
    
    public GUIHelper getGuiHelper() {
        return this.Y;
    }
    
    public ImageControl getImageControl() {
        return this.D;
    }
    
    public JMenuItem getMenuItemFilterContrast() {
        return this.N;
    }
    
    public JMenuItem getMenuItemFilterGblur() {
        return this.G;
    }
    
    public JMenuItem getMenuItemFilterGrayscale() {
        return this.S;
    }
    
    public JMenuItem getMenuItemFilterSepia() {
        return this.U;
    }
    
    public B getModel() {
        return this.F;
    }
    
    public JPopupMenu getPopupFilters() {
        return this.b;
    }
    
    public ResizeDialog getResizeDialog() {
        return this.E;
    }
    
    public ResizeView getResizeView() {
        return this.Q;
    }
    
    public JLabel getZoomIconLabel() {
        return this.B;
    }
    
    public JTextField getZoomInput() {
        return this.A;
    }
    
    public JSlider getZoomSlider() {
        return this.K;
    }
    
    public IMainView getView() {
        return this.T;
    }
    
    public void setZoom(final int n) {
        int value = 0;
        for (int i = 0; i < ToolbarView.ZOOM_VALUES.length; ++i) {
            if (ToolbarView.ZOOM_VALUES[i] == n) {
                value = i;
                break;
            }
        }
        this.K.setValue(value);
        this.A.setText("" + n + "%");
    }
    
    public void rectangleRubberBandChanged(final ImageControl imageControl, final RectangleRubberBand rectangleRubberBand) {
        this.updateView();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.O)) {
            this.b.show(this, this.O.getX(), this.O.getY() + this.O.getHeight());
        }
        else if (actionEvent.getSource().equals(this.V)) {
            this.showWaitDialog(true);
            jmaster.util.C.B.B(this, "rotateImage", new Class[] { Boolean.TYPE }, new Object[] { Boolean.FALSE });
        }
        else if (actionEvent.getSource().equals(this.R)) {
            this.showWaitDialog(true);
            jmaster.util.C.B.B(this, "rotateImage", new Class[] { Boolean.TYPE }, new Object[] { Boolean.TRUE });
        }
        else if (actionEvent.getSource().equals(this.W)) {
            this.showWaitDialog(true);
            jmaster.util.C.B.A(this, "cropImage");
        }
        else if (actionEvent.getSource().equals(this.P)) {
            this.A(true);
            if (0 == this.E.getActionCode()) {
                this.showWaitDialog(true);
                jmaster.util.C.B.A(this, "resizeImage");
            }
        }
        else if (actionEvent.getSource().equals(this.S)) {
            this.applyImageFilter(new O());
        }
        else if (actionEvent.getSource().equals(this.U)) {
            this.A();
        }
        else if (actionEvent.getSource().equals(this.G)) {
            this.showFilterGblurDialog(true);
            if (0 == this.I.getActionCode()) {
                this.applyImageFilter(new P(this.M.getRadiusValue()));
            }
        }
        else if (actionEvent.getSource().equals(this.N)) {
            this.showFilterContrastDialog(true);
            if (0 == this.X.getActionCode()) {
                final M m = new M();
                m.B(this._.getContrastValue());
                m.A(this._.getBrightnessValue());
                this.applyImageFilter(m);
            }
        }
    }
    
    public void resizeImage() {
        try {
            final ResizeOption resizeOption = this.Q.getResizeOption();
            final Image a = this.F.L().A(this.D.getImage(), resizeOption.getWidth(), resizeOption.getHeight(), null, true);
            final int width = a.getWidth(null);
            final int height = a.getHeight(null);
            final BufferedImage bufferedImage = new BufferedImage(width, height, 1);
            ((Graphics2D)bufferedImage.getGraphics()).drawImage(a, 0, 0, width, height, 0, 0, width, height, null);
            this.A(bufferedImage, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.showWaitDialog(false);
        }
    }
    
    private void A(final boolean b) {
        if (this.E == null) {
            this.Q = new ResizeView(this.F);
            this.E = new ResizeDialog(SwingUtil.getParentFrame(this), this.Q);
        }
        if (b) {
            this.E.popup();
        }
        else {
            this.E.dispose();
        }
    }
    
    private void A() {
        final L l = new L(this.F.L().D(this.a.getImage()));
        final C c = new C();
        c.A(l);
        this.applyImageFilter(c);
    }
    
    public void cropImage() {
        try {
            final BufferedImage image = this.D.getImage();
            final RectangleRubberBand band = this.D.getBand();
            final BufferedImage bufferedImage = new BufferedImage(band.getWidth() + 1, band.getHeight() + 1, 1);
            ((Graphics2D)bufferedImage.getGraphics()).drawImage(image, 0, 0, band.getWidth() + 1, band.getHeight() + 1, band.getLeft(), band.getTop(), band.getRight(), band.getBottom(), null);
            this.A(bufferedImage, true);
        }
        finally {
            this.showWaitDialog(false);
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource().equals(this.K)) {
            this.A.setText("" + ToolbarView.ZOOM_VALUES[this.K.getValue()] + "%");
            this.D.setZoomFactor(ToolbarView.ZOOM_VALUES[this.K.getValue()] / 100.0);
        }
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        final RectangleRubberBand band = this.D.getBand();
        this.J.setEnabled(this.C);
        this.W.setEnabled(band != null);
    }
    
    private void A(final Image image, final boolean c) {
        final A l = this.F.L();
        this.D.setImage(this.F.L().D(image));
        this.Z = l.D(l.A(image, this.F.H().getThumbWidth(), this.F.H().getThumbHeight(), null, true));
        this.C = c;
        this.updateView();
    }
    
    public void rotateImage(final boolean b) {
        try {
            this.A(b ? this.F.L().F(this.D.getImage()) : this.F.L().A(this.D.getImage()), true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.showWaitDialog(false);
        }
    }
    
    public void applyImageFilter(final ImageFilter imageFilter) {
        this.showWaitDialog(true);
        jmaster.util.C.B.B(this, "applyImageFilterDo", new Class[] { (ToolbarView.class$java$awt$image$ImageFilter == null) ? (ToolbarView.class$java$awt$image$ImageFilter = class$("java.awt.image.ImageFilter")) : ToolbarView.class$java$awt$image$ImageFilter }, new Object[] { imageFilter });
    }
    
    public void applyImageFilterDo(final ImageFilter imageFilter) {
        try {
            final Image image = this.createImage(new FilteredImageSource(this.D.getImage().getSource(), imageFilter));
            final int width = image.getWidth(null);
            final int height = image.getHeight(null);
            final BufferedImage bufferedImage = new BufferedImage(width, height, 1);
            ((Graphics2D)bufferedImage.getGraphics()).drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
            this.A(bufferedImage, true);
        }
        finally {
            this.showWaitDialog(false);
        }
    }
    
    public void showWaitDialog(final boolean b) {
        this.T.showGlassView(b);
    }
    
    public void showFilterGblurDialog(final boolean b) {
        if (this.I == null) {
            this.M = new FilterGblurView(this.F, this.T);
            this.I = new FilterGblurDialog(SwingUtil.getParentFrame(this), this.M);
        }
        if (b) {
            this.M.setImage(this.Z);
            this.M.resetRadiusValue();
            this.I.popup();
        }
        else {
            this.I.dispose();
        }
    }
    
    public void showFilterContrastDialog(final boolean b) {
        if (this.X == null) {
            this._ = new FilterContrastView(this.F, this.T);
            this.X = new FilterContrastDialog(SwingUtil.getParentFrame(this), this._);
        }
        if (b) {
            this._.setImage(this.Z);
            this._.resetBrightnessValue();
            this._.resetContrastValue();
            this.X.popup();
        }
        else {
            this.X.dispose();
        }
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
        ZOOM_VALUES = new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 250, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600 };
        ZOOM_LABEL_INDICES = new int[] { 0, 9, 26 };
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import imaging.filters.ScaleFilter;
import javax.imageio.ImageIO;
import java.io.File;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.HeadlessException;
import java.awt.image.ImageObserver;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.awt.Panel;
import java.awt.FileDialog;
import java.awt.Frame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class ImageApplet extends Applet implements ActionListener
{
    private static final long serialVersionUID = -8945296353797125687L;
    private AbstractCanvas currentCanvas;
    private AreaCanvas areaCanvas;
    private KerbCanvas kerbCanvas;
    private CroppingCanvas cropCanvas;
    private ControlPanel cPanel;
    private JPanel controlContainer;
    private ImagePanel imagePanel;
    private Frame m_parent;
    private FileDialog m_fileDialog;
    private boolean loadFromHTMLClicked;
    private CheckImageLoadEvent loadEvt;
    private CheckKerbLoadEvent kevt;
    private Panel comparison;
    protected String url;
    protected String zoomValue;
    protected String kerbUrl;
    protected int absWidth;
    protected int absHeight;
    private BufferedImage beforeImage;
    private BufferedImage afterImage;
    private boolean stopEvt;
    private boolean kerb;
    protected boolean loadKerbClicked;
    private boolean step3;
    private boolean step6;
    private JPanel instructions;
    private ScrollPane spane;
    public static final Cursor busyCursor;
    public static final Cursor defaultCursor;
    
    static {
        busyCursor = Cursor.getPredefinedCursor(3);
        defaultCursor = Cursor.getDefaultCursor();
    }
    
    public ImageApplet() {
        this.loadFromHTMLClicked = false;
        this.loadEvt = new CheckImageLoadEvent((CheckImageLoadEvent)null);
        this.kevt = new CheckKerbLoadEvent((CheckKerbLoadEvent)null);
        this.comparison = new Panel();
        this.beforeImage = null;
        this.afterImage = null;
        this.stopEvt = false;
        this.kerb = false;
        this.loadKerbClicked = false;
        this.step3 = false;
        this.step6 = false;
    }
    
    @Override
    public void init() {
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.cropCanvas = new CroppingCanvas();
        (this.instructions = new JPanel()).setLayout(new GridLayout(0, 1));
        this.instructions.add(new JLabel("To load an image to the screen to add paver, click on the \"Load Image\" button. "));
        this.instructions.add(new JLabel("Navigate to the folder on your computer where you saved the image. "));
        this.instructions.add(new JLabel("Select the image file and click Open."));
        this.areaCanvas = new AreaCanvas();
        this.spane = new ScrollPane(0);
        this.cPanel = new ControlPanel(this.areaCanvas);
        (this.controlContainer = new JPanel()).add(this.cPanel);
        this.add(this.spane, "Center");
        System.out.println("Crop Canvas sise : " + this.cropCanvas.getWidth() + "," + this.cropCanvas.getHeight());
        this.spane.setPreferredSize(new Dimension(this.cropCanvas.getWidth(), this.cropCanvas.getHeight()));
        this.spane.setSize(this.cropCanvas.getWidth(), this.cropCanvas.getHeight());
        this.setSize(this.cropCanvas.getWidth(), this.cropCanvas.getHeight());
        this.loadEvt.start();
        this.kevt.start();
    }
    
    public Panel addButtonPanel() {
        final Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1, 6, 1, 0));
        final Button btnStep2 = new Button("Step2");
        btnStep2.setActionCommand("Step2");
        btnStep2.addActionListener(this);
        buttonPanel.add(btnStep2);
        final Button btnStep3 = new Button("Step3");
        btnStep3.setActionCommand("Step3");
        btnStep3.addActionListener(this);
        buttonPanel.add(btnStep3);
        final Button btnStep4 = new Button("Step4");
        btnStep4.setActionCommand("Step4");
        btnStep4.addActionListener(this);
        buttonPanel.add(btnStep4);
        final Button btnStep5 = new Button("Step5");
        btnStep5.setActionCommand("Step5");
        btnStep5.addActionListener(this);
        buttonPanel.add(btnStep5);
        final Button btnStep6 = new Button("Step6");
        btnStep6.setActionCommand("Step6");
        btnStep6.addActionListener(this);
        buttonPanel.add(btnStep6);
        final Button btnStep7 = new Button("Step7");
        btnStep7.setActionCommand("Step7");
        btnStep7.addActionListener(this);
        buttonPanel.add(btnStep7);
        return buttonPanel;
    }
    
    @Override
    public void actionPerformed(final ActionEvent aEvent) {
        final String action = aEvent.getActionCommand();
        System.out.println("Action action...." + action);
    }
    
    public void loadAreaCanvas() {
        this.currentCanvas = this.areaCanvas;
        this.areaCanvas.loadImage(this.cropCanvas.getImage());
        this.cropCanvas.setVisible(false);
        this.spane.remove(this.cropCanvas);
        this.spane.add(this.currentCanvas);
        System.out.println("Current Canvas size : " + this.currentCanvas.getWidth() + "," + this.currentCanvas.getHeight());
        this.spane.setPreferredSize(new Dimension(this.currentCanvas.getWidth() + 10, this.currentCanvas.getHeight() + 10));
        this.spane.setSize(this.currentCanvas.getWidth() + 10, this.currentCanvas.getHeight() + 10);
        this.spane.doLayout();
        this.spane.repaint();
    }
    
    public void getOriginalImage(final String queryString) {
        try {
            this.comparison.removeAll();
            if (this.imagePanel != null) {
                this.afterImage = this.imagePanel.getImage();
            }
            else {
                this.afterImage = this.currentCanvas.getImage();
            }
            SendToAspx.sendToServer(this, "SaveProjectImage.aspx?" + queryString, this.getCodeBase(), this.beforeImage, this.afterImage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void applyTexture(final String imageURL) {
        this.url = imageURL;
        final Runnable doWorkRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ImageApplet.this.showWait();
                    final Image image = ImageApplet.this.getImage(ImageApplet.this.getDocumentBase(), ImageApplet.this.url);
                    final BufferedImage texture = ImageApplet.toBufferedImage(image);
                    ImageApplet.this.currentCanvas.applyTexture(texture);
                }
                finally {
                    ImageApplet.this.hideWait();
                }
                ImageApplet.this.hideWait();
            }
        };
        SwingUtilities.invokeLater(doWorkRunnable);
        this.stopEvt = true;
    }
    
    public void applyZoom(final String strZoom) {
        this.zoomValue = strZoom;
        final Runnable doWorkRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    ImageApplet.this.currentCanvas.applyZoom(ImageApplet.this.zoomValue);
                }
                finally {
                    ImageApplet.this.hideWait();
                }
                ImageApplet.this.hideWait();
            }
        };
        SwingUtilities.invokeLater(doWorkRunnable);
        this.stopEvt = true;
    }
    
    protected void hideWait() {
        WaitDialog.hideDialog();
    }
    
    protected void showWait() {
        WaitDialog.showDialog();
    }
    
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        image = new ImageIcon(image).getImage();
        final boolean hasAlpha = hasAlpha(image);
        BufferedImage bimage = null;
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = 1;
            if (hasAlpha) {
                transparency = 2;
            }
            final GraphicsDevice gs = ge.getDefaultScreenDevice();
            final GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        }
        catch (HeadlessException ex) {}
        if (bimage == null) {
            int type = 1;
            if (hasAlpha) {
                type = 2;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        final Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
    
    public static boolean hasAlpha(final Image image) {
        if (image instanceof BufferedImage) {
            final BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }
        final PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException ex) {}
        final ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }
    
    public void updateContainer(final int width, final int height) {
        try {
            this.setSize(width + 10, height + 10);
            final JSObject jso = JSObject.getWindow((Applet)this);
            final JSObject document = (JSObject)jso.getMember("document");
            final JSObject applet = (JSObject)document.getMember("imageApplet");
            applet.setMember("width", (Object)width);
            applet.setMember("height", (Object)height);
            jso.call("cropOrNot", (Object[])null);
        }
        catch (JSException ex) {}
        this.repaint();
    }
    
    public void updateContainer() {
        try {
            final JSObject win = JSObject.getWindow((Applet)this);
            win.call("step2", (Object[])null);
        }
        catch (JSException ex) {}
    }
    
    public String openDialog() {
        System.out.println("Custom Message : Before calling open dialog");
        if (this.m_parent == null) {
            this.m_parent = new Frame();
        }
        if (this.m_fileDialog == null) {
            this.m_fileDialog = new FileDialog(this.m_parent, "File Upload", 0);
        }
        this.m_fileDialog.setVisible(true);
        this.m_fileDialog.toFront();
        final String file = this.m_fileDialog.getFile();
        final String directory = this.m_fileDialog.getDirectory();
        this.m_fileDialog.setVisible(false);
        this.m_parent.setVisible(false);
        if (file == null || file.length() == 0) {
            return null;
        }
        return String.valueOf(directory) + File.separator + file;
    }
    
    public void loadImage() {
        this.loadFromHTMLClicked = true;
        System.out.println("Custom Message : Entered Load Image Method");
    }
    
    public void nextStep() {
        final Runnable doWorkRunnable = new Runnable() {
            @Override
            public void run() {
                if (ImageApplet.this.step3) {
                    final BufferedImage currentImage = ImageApplet.this.currentCanvas.getImage();
                    if (ImageApplet.this.kerb) {
                        ImageApplet.this.currentCanvas.setDrawMode(false);
                        ImageApplet.access$11(ImageApplet.this, true);
                    }
                    else {
                        ImageApplet.this.spane.remove(ImageApplet.this.currentCanvas);
                        ImageApplet.access$12(ImageApplet.this, new KerbCanvas(ImageApplet.this.currentCanvas));
                        ImageApplet.access$14(ImageApplet.this, ImageApplet.this.kerbCanvas);
                        ImageApplet.this.currentCanvas.setDrawMode(true);
                        ImageApplet.this.spane.add(ImageApplet.this.currentCanvas);
                        ImageApplet.this.currentCanvas.loadImage(currentImage);
                        ImageApplet.access$15(ImageApplet.this, true);
                    }
                }
                else {
                    ImageApplet.this.loadAreaCanvas();
                    ImageApplet.this.areaCanvas.setDrawMode(true);
                    ImageApplet.access$17(ImageApplet.this, true);
                }
            }
        };
        SwingUtilities.invokeLater(doWorkRunnable);
    }
    
    public void repeat() {
        final BufferedImage currentImage = this.currentCanvas.getImage();
        this.spane.remove(this.currentCanvas);
        this.areaCanvas = new AreaCanvas();
        (this.currentCanvas = this.areaCanvas).loadImage(currentImage);
        this.currentCanvas.setDrawMode(true);
        this.spane.add(this.currentCanvas);
    }
    
    public void repeatBorder() {
        final BufferedImage currentImage = this.currentCanvas.getImage();
        this.spane.remove(this.currentCanvas);
        this.kerbCanvas = new KerbCanvas();
        (this.currentCanvas = this.kerbCanvas).loadImage(currentImage);
        this.currentCanvas.setDrawMode(true);
        this.spane.add(this.currentCanvas);
    }
    
    public void previousStep() {
        if (this.step6) {
            this.currentCanvas.setDrawMode(true);
            this.step6 = false;
        }
        else if (this.kerb) {
            this.spane.remove(this.currentCanvas);
            this.areaCanvas.restoreRef();
            this.currentCanvas = this.areaCanvas;
            this.spane.add(this.currentCanvas);
            this.kerb = false;
        }
        else if (this.step3) {
            if (this.currentCanvas != null) {
                this.spane.remove(this.currentCanvas);
            }
            this.spane.add(this.cropCanvas);
            this.step3 = false;
            if (this.stopEvt) {
                this.stopEvt = false;
                (this.loadEvt = new CheckImageLoadEvent((CheckImageLoadEvent)null)).start();
            }
        }
    }
    
    public void setDrawMode(final String mode) {
        this.currentCanvas.setDrawMode(Integer.parseInt(mode) == 0);
    }
    
    public void setToSubtractCurrent(final String setToSubtract) {
        ((AreaCanvas)this.currentCanvas).setToSubtractCurrent(Integer.parseInt(setToSubtract) == 0);
    }
    
    public void setLineType() {
        this.currentCanvas.setLineType();
    }
    
    public void setCurveType() {
        this.currentCanvas.setCurveType();
    }
    
    public void setPerspectiveType(final String type) {
        ((AreaCanvas)this.currentCanvas).setPerspectiveType(Short.parseShort(type));
    }
    
    public void deletePoint() {
        this.currentCanvas.deleteSelectedPoint();
    }
    
    public void undoBorderPoint() {
        ((KerbCanvas)this.currentCanvas).undoLastPoint();
    }
    
    public void drawBorder(final String strLoc) {
        ((KerbCanvas)this.currentCanvas).drawBorder(strLoc);
    }
    
    public void clear() {
        this.currentCanvas.clearDraw();
    }
    
    public void clearAll() {
        this.currentCanvas.clearAll();
    }
    
    public void zoom(final String m_scale) {
        final Runnable doWorkRunnable = new Runnable() {
            @Override
            public void run() {
                if (ImageApplet.this.imagePanel != null) {
                    ImageApplet.this.imagePanel.setZoom(Float.parseFloat(m_scale));
                }
                else if (ImageApplet.this.currentCanvas != null) {
                    ImageApplet.this.currentCanvas.zoomTexture(Double.parseDouble(m_scale));
                }
            }
        };
        SwingUtilities.invokeLater(doWorkRunnable);
    }
    
    public void tranformTexture(final String form) {
        if (form.equals("Left")) {
            this.currentCanvas.tranformTexture(361);
        }
        else if (form.equals("Right")) {
            this.currentCanvas.tranformTexture(362);
        }
    }
    
    public void rotateLeft() {
        if (this.imagePanel != null) {
            this.imagePanel.rotateLeft();
        }
        else {
            this.currentCanvas.tranformTexture(361);
        }
    }
    
    public void rotateRight() {
        if (this.imagePanel != null) {
            this.imagePanel.rotateRight();
        }
        else {
            this.currentCanvas.tranformTexture(362);
        }
    }
    
    public void loadKerb(final String url) {
        this.kerbUrl = url;
    }
    
    public void cropImage() {
        if (this.cropCanvas != null) {
            final Dimension iSize = this.cropCanvas.clipImage();
            if (iSize != null) {
                this.absWidth = (int)iSize.getWidth();
                this.absHeight = (int)iSize.getHeight();
                this.resize(this.absWidth, this.absHeight);
                this.setSize(this.absWidth + 10, this.absHeight + 10);
                this.spane.setPreferredSize(new Dimension(this.absWidth + 10, this.absHeight + 10));
                this.cropCanvas.setSize(this.absWidth, this.absHeight);
                this.updateContainer(this.absWidth, this.absHeight);
                this.beforeImage = this.cropCanvas.getImage();
                this.moveToStep3();
            }
        }
    }
    
    public void moveToStep3() {
        try {
            final JSObject win = JSObject.getWindow((Applet)this);
            win.call("StepNextStatus", (Object[])null);
        }
        catch (JSException jse) {
            jse.getMessage();
        }
    }
    
    public void loadKerbFromFile() {
        final String file = this.openDialog();
        if (file != null) {
            new Thread() {
                @Override
                public void run() {
                    final File selectedFile = new File(file);
                    try {
                        ImageApplet.this.imagePanel.action(selectedFile);
                    }
                    catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
    public AbstractCanvas getCurrent() {
        return this.currentCanvas;
    }
    
    static /* synthetic */ void access$2(final ImageApplet imageApplet, final boolean loadFromHTMLClicked) {
        imageApplet.loadFromHTMLClicked = loadFromHTMLClicked;
    }
    
    static /* synthetic */ void access$6(final ImageApplet imageApplet, final BufferedImage beforeImage) {
        imageApplet.beforeImage = beforeImage;
    }
    
    static /* synthetic */ void access$11(final ImageApplet imageApplet, final boolean step6) {
        imageApplet.step6 = step6;
    }
    
    static /* synthetic */ void access$12(final ImageApplet imageApplet, final KerbCanvas kerbCanvas) {
        imageApplet.kerbCanvas = kerbCanvas;
    }
    
    static /* synthetic */ void access$14(final ImageApplet imageApplet, final AbstractCanvas currentCanvas) {
        imageApplet.currentCanvas = currentCanvas;
    }
    
    static /* synthetic */ void access$15(final ImageApplet imageApplet, final boolean kerb) {
        imageApplet.kerb = kerb;
    }
    
    static /* synthetic */ void access$17(final ImageApplet imageApplet, final boolean step3) {
        imageApplet.step3 = step3;
    }
    
    private class CheckImageLoadEvent extends Thread
    {
        private static final float STD_WIDTH = 1024.0f;
        private static final float STD_HEIGHT = 768.0f;
        
        @Override
        public void run() {
            while (!ImageApplet.this.stopEvt) {
                if (ImageApplet.this.loadFromHTMLClicked) {
                    ImageApplet.access$2(ImageApplet.this, false);
                    final String file = ImageApplet.this.openDialog();
                    System.out.println("Custom Message : selected file =" + file);
                    if (file != null) {
                        final File selectedFile = new File(file);
                        try {
                            final BufferedImage image = ImageIO.read(selectedFile);
                            if (image != null) {
                                ImageApplet.this.absWidth = image.getWidth();
                                ImageApplet.this.absHeight = image.getHeight();
                                BufferedImage xImage;
                                if (ImageApplet.this.absWidth > 1024.0f) {
                                    xImage = new ScaleFilter().filter(image, 1024.0f / ImageApplet.this.absWidth);
                                    ImageApplet.this.absWidth = xImage.getWidth();
                                    ImageApplet.this.absHeight = xImage.getHeight();
                                }
                                else if (ImageApplet.this.absHeight > 768.0f) {
                                    xImage = new ScaleFilter().filter(image, 768.0f / ImageApplet.this.absHeight);
                                    ImageApplet.this.absHeight = xImage.getHeight();
                                    ImageApplet.this.absWidth = xImage.getWidth();
                                }
                                else {
                                    xImage = image;
                                }
                                ImageApplet.this.resize(ImageApplet.this.absWidth, ImageApplet.this.absHeight);
                                ImageApplet.this.setSize(ImageApplet.this.absWidth, ImageApplet.this.absHeight);
                                ImageApplet.this.remove(ImageApplet.this.instructions);
                                ImageApplet.this.spane.add(ImageApplet.this.cropCanvas);
                                ImageApplet.this.cropCanvas.setSize(ImageApplet.this.absWidth, ImageApplet.this.absHeight);
                                ImageApplet.this.updateContainer(ImageApplet.this.absWidth, ImageApplet.this.absHeight);
                                ImageApplet.access$6(ImageApplet.this, xImage);
                                ImageApplet.this.cropCanvas.loadImage(xImage);
                                ImageApplet.this.spane.setPreferredSize(new Dimension(500, 500));
                            }
                            else {
                                ImageApplet.this.updateContainer();
                            }
                        }
                        catch (IOException e) {
                            System.out.println("Custom Message : Error Occured =" + e.toString());
                            ImageApplet.this.updateContainer();
                        }
                    }
                }
                try {
                    Thread.sleep(300L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private class CheckKerbLoadEvent extends Thread
    {
        @Override
        public void run() {
            while (true) {
                if (ImageApplet.this.loadKerbClicked) {
                    ImageApplet.this.loadKerbClicked = false;
                    try {
                        ImageApplet.this.showWait();
                        ImageApplet.this.imagePanel.loadKerb(new URL(ImageApplet.this.getDocumentBase(), ImageApplet.this.kerbUrl));
                        ImageApplet.this.hideWait();
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(300L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}

import java.awt.MenuBar;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.InputStream;
import java.io.IOException;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Frame;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TG extends Applet implements ActionListener, ComponentListener, MouseListener, MouseMotionListener, Runnable
{
    public static final String VERSION = ".9.19";
    private static final String PARAM_EDITOR_HEIGHT = "editorheight";
    private static final String PARAM_FONTSIZE = "fontsize";
    private static final String PARAM_GRAPHICS_HEIGHT = "graphicsheight";
    private static final String PARAM_CMDCTR_HEIGHT = "ttyheight";
    private static final String PARAM_WIDTH = "width";
    private static final int FONT_SIZE = 12;
    private static final String FONT_NAME = "Courier";
    private static final int NS_EXTRA = 4;
    private static final int CC_HEIGHT = 230;
    private static final int ED_HEIGHT = 0;
    private static final int GC_HEIGHT = 300;
    private static final int WIDTH = 640;
    private static final String IO_EXCEPTION = ") IOException";
    private static float canvasProportion;
    private static float editorProportion;
    private static Font font;
    private static Frame tgFrame;
    private static int charHeight;
    private static int fontSize;
    private static int nameStripeHeight;
    private static MenuItem editCopy;
    private static MenuItem editCut;
    private static MenuItem editPaste;
    private static MenuItem fileNew;
    private static MenuItem fileOpen;
    private static MenuItem fileSave;
    private static MenuItem fileSaveAs;
    private static MenuItem fileExit;
    private static MenuItem helpTG;
    private static MenuItem helpEditor;
    private static MenuItem helpJLogo;
    private static MenuItem interpretEditBuf;
    private static MenuItem windCanvOpen;
    private static MenuItem windCanvClos;
    private static MenuItem windCmdCtrOpen;
    private static MenuItem windCmdCtrClos;
    private static MenuItem windEditOpen;
    private static MenuItem windEditClos;
    private static TextBuffer savedEditTxtBuf;
    private static EditorNameStripe editorNS;
    private static TG tgObj;
    private static TGCanvas canvas;
    private static TGDriver tgd;
    private static Thread driverThread;
    private static CmdCtrNameStripe cmdCtrNS;
    private static Turtle[] sprites;
    private static Editor editor;
    private static CommandCenter cmdCtr;
    private int dragMouseY;
    private Image imgBuf;
    private PopupMenu appletMenu;
    private Thread timerThread;
    
    public void init() {
        final String parameter = this.getParameter("editorheight");
        final int n = (parameter == null) ? 0 : Integer.parseInt(parameter);
        final String parameter2 = this.getParameter("fontsize");
        TG.fontSize = ((parameter2 == null) ? 12 : Integer.parseInt(parameter2));
        final String parameter3 = this.getParameter("graphicsheight");
        final int n2 = (parameter3 == null) ? 300 : Integer.parseInt(parameter3);
        final String parameter4 = this.getParameter("ttyheight");
        final int n3 = (parameter4 == null) ? 230 : Integer.parseInt(parameter4);
        final String parameter5 = this.getParameter("width");
        final int n4 = (parameter5 == null) ? 640 : Integer.parseInt(parameter5);
        this.setFont(TG.font = new Font("Courier", 0, TG.fontSize));
        (this.appletMenu = new PopupMenu()).add(this.buildEditMenu());
        this.appletMenu.add(this.buildInterpretMenu());
        this.appletMenu.add(this.buildWindowMenu());
        this.add(this.appletMenu);
        (TG.tgObj = this).combinedInit(n4, n2, n3, n);
        this.validate();
    }
    
    public void start() {
        (TG.driverThread = new Thread(TG.tgd, "TGDriver")).start();
        if (TG.tgFrame != null) {
            TG.cmdCtr.requestFocus();
        }
    }
    
    public void stop() {
        TG.driverThread = null;
    }
    
    public void doLayout() {
        if (TG.tgObj == null) {
            return;
        }
        final Dimension size = TG.tgObj.getSize();
        final Insets insets = TG.tgObj.getInsets();
        final int n = size.height - (insets.top + insets.bottom);
        final int n2 = size.width - (insets.left + insets.right);
        final Dimension minimumSize = TG.cmdCtr.getMinimumSize();
        final int height = minimumSize.height;
        int n3 = minimumSize.height + TG.nameStripeHeight;
        int n4 = minimumSize.width;
        int height2 = 0;
        if (TG.canvas != null) {
            final Dimension minimumSize2 = TG.canvas.getMinimumSize();
            height2 = minimumSize2.height;
            n3 += height2;
            n4 = Math.max(n4, minimumSize2.width);
        }
        int height3 = 0;
        if (TG.editor != null) {
            final Dimension minimumSize3 = TG.editor.getMinimumSize();
            height3 = minimumSize3.height;
            n3 += height3 + TG.nameStripeHeight;
            n4 = Math.max(n4, minimumSize3.width);
        }
        final int width = n4 + (insets.left + insets.right);
        final int height4 = n3 + (insets.top + insets.bottom);
        boolean b = false;
        if (height4 > n) {
            size.height = height4;
            final float n5 = height + height2 + height3;
            TG.canvasProportion = height2 / n5;
            TG.editorProportion = height3 / n5;
            b = true;
        }
        if (width > n2) {
            size.width = width;
            b = true;
        }
        if (b) {
            TG.tgObj.setSize(size);
            if (TG.tgFrame != null) {
                final Insets insets2 = TG.tgFrame.getInsets();
                TG.tgFrame.setSize(insets2.left + size.width + insets2.left, insets2.top + size.height + insets2.bottom);
            }
        }
        int n6 = size.height - TG.nameStripeHeight;
        if (TG.editor != null) {
            n6 -= TG.nameStripeHeight;
        }
        final int round = Math.round(n6 * TG.canvasProportion);
        final int round2 = Math.round(n6 * TG.editorProportion);
        final int n7 = n6 - (round + round2);
        int top = insets.top;
        final int n8 = size.width - (insets.left + insets.right);
        if (TG.canvas != null) {
            TG.canvas.setBounds(insets.left, top, n8, round);
            top += round;
        }
        TG.cmdCtrNS.setBounds(insets.left, top, n8, TG.nameStripeHeight);
        final int n9 = top + TG.nameStripeHeight;
        TG.cmdCtr.setBounds(insets.left, n9, n8, n7);
        final int n10 = n9 + n7;
        if (TG.editor != null) {
            TG.editorNS.setBounds(insets.left, n10, n8, TG.nameStripeHeight);
            TG.editor.setBounds(insets.left, n10 + TG.nameStripeHeight, n8, round2);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem == TG.editCopy) {
            if (TG.editor != null) {
                TG.editor.copySelection();
            }
        }
        else if (menuItem == TG.editCut) {
            if (TG.editor != null) {
                TG.editor.cutSelection();
            }
        }
        else if (menuItem == TG.editPaste) {
            if (TG.editor == null) {
                this.addEditor();
            }
            if (TG.editor != null) {
                TG.editor.pasteSelection();
            }
        }
        else if (menuItem == TG.fileExit) {
            done();
        }
        else if (menuItem == TG.fileNew) {
            if (TG.canvas != null) {
                TG.canvas.clean();
                zapSprites();
                addSprite(0);
            }
            TG.tgd.reset();
            if (TG.editor == null) {
                this.addEditor();
            }
            if (TG.editor != null) {
                TG.editor.newFile();
            }
        }
        else if (menuItem == TG.fileOpen) {
            TG.tgd.reset();
            if (TG.editor == null) {
                this.addEditor();
            }
            if (TG.editor != null) {
                TG.editor.openFile();
            }
        }
        else if (menuItem == TG.fileSave) {
            if (TG.editor != null) {
                TG.editor.save();
            }
        }
        else if (menuItem == TG.fileSaveAs) {
            if (TG.editor != null) {
                TG.editor.saveAs();
            }
        }
        else if (menuItem == TG.helpEditor) {
            this.displayHelp("ED_Help");
        }
        else if (menuItem == TG.helpTG) {
            this.displayHelp("TG_Help");
        }
        else if (menuItem == TG.helpJLogo) {
            this.displayHelp("JL_Help");
        }
        else if (menuItem == TG.interpretEditBuf) {
            this.interpretEditorBuffer();
        }
        else if (menuItem == TG.windCanvOpen) {
            this.addCanvas();
        }
        else if (menuItem == TG.windCanvClos) {
            this.delCanvas();
        }
        else if (menuItem != TG.windCmdCtrOpen) {
            if (menuItem != TG.windCmdCtrClos) {
                if (menuItem == TG.windEditOpen) {
                    this.addEditor();
                }
                else if (menuItem == TG.windEditClos) {
                    this.delEditor();
                }
            }
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.imgBuf = null;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.appletMenu != null && mouseEvent.isPopupTrigger()) {
            this.appletMenu.show(this, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (mouseEvent.getModifiers() == 16) {
            this.dragMouseY = mouseEvent.getY();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.appletMenu != null && mouseEvent.isPopupTrigger()) {
            this.appletMenu.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int y = mouseEvent.getY();
        int abs = Math.abs(this.dragMouseY - y);
        final Rectangle bounds = TG.cmdCtrNS.getBounds();
        if (this.dragMouseY >= bounds.y && this.dragMouseY < bounds.y + bounds.height) {
            if (TG.canvas == null) {
                return;
            }
            final Rectangle bounds2 = TG.canvas.getBounds();
            final Rectangle bounds3 = TG.cmdCtr.getBounds();
            if (y < this.dragMouseY) {
                final int height = TG.canvas.getMinimumSize().height;
                if (bounds2.height <= height) {
                    return;
                }
                if (bounds2.height - abs < height) {
                    abs = bounds2.height - height;
                }
                TG.canvas.setSize(bounds2.width, bounds2.height - abs);
                TG.cmdCtrNS.setLocation(bounds.x, bounds.y - abs);
                TG.cmdCtr.setBounds(bounds3.x, bounds3.y - abs, bounds3.width, bounds3.height + abs);
            }
            else {
                final int height2 = TG.cmdCtr.getMinimumSize().height;
                if (bounds3.height <= height2) {
                    return;
                }
                if (bounds3.height - abs < height2) {
                    abs = bounds3.height - height2;
                }
                TG.canvas.setSize(bounds2.width, bounds2.height + abs);
                TG.cmdCtrNS.setLocation(bounds.x, bounds.y + abs);
                TG.cmdCtr.setBounds(bounds3.x, bounds3.y + abs, bounds3.width, bounds3.height - abs);
            }
        }
        else {
            if (TG.editorNS == null) {
                return;
            }
            final Rectangle bounds4 = TG.editorNS.getBounds();
            if (this.dragMouseY < bounds4.y || this.dragMouseY >= bounds4.y + bounds4.height) {
                return;
            }
            final Rectangle bounds5 = TG.cmdCtr.getBounds();
            final Rectangle bounds6 = TG.editor.getBounds();
            if (y < this.dragMouseY) {
                final int height3 = TG.cmdCtr.getMinimumSize().height;
                if (bounds5.height <= height3) {
                    return;
                }
                if (bounds5.height - abs < height3) {
                    abs = bounds5.height - height3;
                }
                TG.cmdCtr.setSize(bounds5.width, bounds5.height - abs);
                TG.editorNS.setLocation(bounds4.x, bounds4.y - abs);
                TG.editor.setBounds(bounds6.x, bounds6.y - abs, bounds6.width, bounds6.height + abs);
            }
            else {
                final int height4 = TG.editor.getMinimumSize().height;
                if (bounds6.height <= height4) {
                    return;
                }
                if (bounds6.height - abs < height4) {
                    abs = bounds6.height - height4;
                }
                TG.cmdCtr.setSize(bounds5.width, bounds5.height + abs);
                TG.editorNS.setLocation(bounds4.x, bounds4.y + abs);
                TG.editor.setBounds(bounds6.x, bounds6.y + abs, bounds6.width, bounds6.height - abs);
            }
        }
        this.dragMouseY = y;
        final float n = TG.cmdCtr.getHeight();
        float n2 = 0.0f;
        if (TG.canvas != null) {
            n2 = TG.canvas.getHeight();
        }
        final float n3 = n + n2;
        float n4 = 0.0f;
        if (TG.editor != null) {
            n4 = TG.editor.getHeight();
        }
        final float n5 = n3 + n4;
        TG.canvasProportion = n2 / n5;
        TG.editorProportion = n4 / n5;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void run() {
        int saveFileCompleted = -1;
        while (TG.tgObj.timerThread == Thread.currentThread()) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            saveFileCompleted = TG.editor.saveFileCompleted();
            if (saveFileCompleted != 0) {
                TG.tgObj.timerThread = null;
            }
        }
        if (saveFileCompleted == 1) {
            System.exit(0);
        }
    }
    
    private boolean addCanvas() {
        if (TG.canvas != null) {
            return true;
        }
        final int height = TG.cmdCtr.getMinimumSize().height;
        int n = TG.cmdCtr.getSize().height - height;
        final int n2 = 40;
        int height2 = 0;
        if (TG.editor != null) {
            height2 = TG.editor.getSize().height;
        }
        if (n < n2) {
            height2 -= n2 - n;
            if (height2 < TG.editor.getMinimumSize().height) {
                return false;
            }
            n = n2;
        }
        (TG.canvas = new TGCanvas(TG.tgObj.getSize().width, n)).addKeyHandler(TG.tgd);
        TG.canvas.addMouseHandler(TG.tgd);
        this.add(TG.canvas, "TG0");
        this.resizeWindows(n, height, height2);
        TG.windCanvClos.setEnabled(true);
        TG.windCanvOpen.setEnabled(false);
        zapSprites();
        addSprite(0);
        return true;
    }
    
    private boolean addEditor() {
        if (TG.editor != null) {
            return true;
        }
        final int height = TG.cmdCtr.getMinimumSize().height;
        int n = TG.cmdCtr.getSize().height - height;
        int height2 = 0;
        if (TG.canvas != null) {
            height2 = TG.canvas.getSize().height;
        }
        final int n2 = 48 + TG.nameStripeHeight;
        if (n < n2) {
            height2 -= n2 - n;
            if (height2 < TG.canvas.getMinimumSize().height) {
                return false;
            }
            n = n2;
        }
        TG.editorNS = new EditorNameStripe();
        final int n3 = n - TG.nameStripeHeight;
        this.add(TG.editor = new Editor(TG.savedEditTxtBuf, TG.tgObj.getSize().width, n3, TG.fontSize), "TE0");
        this.resizeWindows(height2, height, n3);
        TG.windEditClos.setEnabled(true);
        TG.windEditOpen.setEnabled(false);
        TG.savedEditTxtBuf = null;
        return true;
    }
    
    private Menu buildEditMenu() {
        final Menu menu = new Menu("Edit");
        menu.setFont(TG.font);
        menu.add(TG.editCopy = new MenuItem("Copy"));
        TG.editCopy.addActionListener(this);
        menu.add(TG.editCut = new MenuItem("Cut"));
        TG.editCut.addActionListener(this);
        menu.add(TG.editPaste = new MenuItem("Paste"));
        TG.editPaste.addActionListener(this);
        return menu;
    }
    
    private Menu buildFileMenu() {
        final Menu menu = new Menu("File");
        menu.setFont(TG.font);
        menu.add(TG.fileNew = new MenuItem("New..."));
        TG.fileNew.addActionListener(this);
        menu.add(TG.fileOpen = new MenuItem("Open..."));
        TG.fileOpen.addActionListener(this);
        menu.add(TG.fileSave = new MenuItem("Save"));
        TG.fileSave.addActionListener(this);
        menu.add(TG.fileSaveAs = new MenuItem("Save as..."));
        TG.fileSaveAs.addActionListener(this);
        menu.addSeparator();
        menu.add(TG.fileExit = new MenuItem("Exit"));
        TG.fileExit.addActionListener(this);
        return menu;
    }
    
    private Menu buildHelpMenu() {
        final Menu menu = new Menu("Help");
        menu.setFont(TG.font);
        menu.add(TG.helpTG = new MenuItem("TG"));
        TG.helpTG.addActionListener(this);
        menu.add(TG.helpEditor = new MenuItem("Editor"));
        TG.helpEditor.addActionListener(this);
        menu.add(TG.helpJLogo = new MenuItem("jLogo"));
        TG.helpJLogo.addActionListener(this);
        return menu;
    }
    
    private Menu buildInterpretMenu() {
        final Menu menu = new Menu("Interpret");
        menu.setFont(TG.font);
        menu.add(TG.interpretEditBuf = new MenuItem("Editor Contents"));
        TG.interpretEditBuf.addActionListener(this);
        return menu;
    }
    
    private Menu buildWindowMenu() {
        final Menu menu = new Menu("Window");
        menu.setFont(TG.font);
        final Menu menu2 = new Menu("Canvas");
        menu2.setFont(TG.font);
        menu2.add(TG.windCanvOpen = new MenuItem("Open"));
        TG.windCanvOpen.addActionListener(this);
        menu2.add(TG.windCanvClos = new MenuItem("Close"));
        TG.windCanvClos.addActionListener(this);
        menu.add(menu2);
        final Menu menu3 = new Menu("CommandCenter");
        menu3.setFont(TG.font);
        menu3.add(TG.windCmdCtrOpen = new MenuItem("Open"));
        TG.windCmdCtrOpen.addActionListener(this);
        menu3.add(TG.windCmdCtrClos = new MenuItem("Close"));
        TG.windCmdCtrClos.addActionListener(this);
        menu.add(menu3);
        final Menu menu4 = new Menu("Editor");
        menu4.setFont(TG.font);
        menu4.add(TG.windEditOpen = new MenuItem("Open"));
        TG.windEditOpen.addActionListener(this);
        menu4.add(TG.windEditClos = new MenuItem("Close"));
        TG.windEditClos.addActionListener(this);
        menu.add(menu4);
        return menu;
    }
    
    private void combinedInit(final int n, final int n2, final int n3, final int n4) {
        TG.canvas = null;
        TG.editor = null;
        TG.editorNS = null;
        TG.savedEditTxtBuf = null;
        TG.charHeight = this.getFontMetrics(TG.font).getHeight();
        TG.nameStripeHeight = TG.charHeight + 4;
        TG.tgObj.setLayout(null);
        if (n2 > 0) {
            TG.canvas = new TGCanvas(n, n2);
            TG.tgObj.add(TG.canvas, "TG0");
            TG.windCanvClos.setEnabled(true);
            TG.windCanvOpen.setEnabled(false);
            (TG.sprites = new Turtle[15])[0] = new Turtle(TG.canvas);
        }
        else {
            TG.windCanvOpen.setEnabled(true);
            TG.windCanvClos.setEnabled(false);
        }
        TG.cmdCtrNS = new CmdCtrNameStripe(TG.nameStripeHeight, n);
        TG.cmdCtr = new CommandCenter(n, n3, TG.fontSize);
        TG.tgObj.add(TG.cmdCtr, "CmdCtr");
        TG.windCmdCtrOpen.setEnabled(false);
        TG.windCmdCtrClos.setEnabled(false);
        if (n4 > 0) {
            TG.editorNS = new EditorNameStripe();
            TG.editor = new Editor(n, n4, TG.fontSize);
            TG.tgObj.add(TG.editor, "TE0");
            TG.windEditClos.setEnabled(true);
            TG.windEditOpen.setEnabled(false);
        }
        else {
            TG.windEditOpen.setEnabled(true);
            TG.windEditClos.setEnabled(false);
        }
        final Dimension size = TG.cmdCtrNS.getSize();
        final int height = size.height;
        int n5 = size.width;
        final Dimension size2 = TG.cmdCtr.getSize();
        final int height2 = size2.height;
        int height3 = size2.height;
        int n6 = height + size2.height;
        if (size2.width > n5) {
            n5 = size2.width;
        }
        int height4 = 0;
        if (TG.canvas != null) {
            final Dimension size3 = TG.canvas.getSize();
            height4 = size3.height;
            height3 += size3.height;
            n6 += size3.height;
            if (size3.width > n5) {
                n5 = size3.width;
            }
        }
        int height5 = 0;
        if (TG.editor != null) {
            final Dimension size4 = TG.editorNS.getSize();
            final int n7 = n6 + size4.height;
            if (size4.width > n5) {
                n5 = size4.width;
            }
            final Dimension size5 = TG.editor.getSize();
            height5 = size5.height;
            height3 += size5.height;
            n6 = n7 + size5.height;
            if (size5.width > n5) {
                n5 = size5.width;
            }
        }
        final Insets insets = TG.tgObj.getInsets();
        final int n8 = n6 + (insets.top + insets.bottom);
        final int n9 = n5 + (insets.left + insets.right);
        TG.tgObj.setSize(n9, n8);
        if (TG.tgFrame != null) {
            final Insets insets2 = TG.tgFrame.getInsets();
            TG.tgFrame.setSize(insets2.left + n9 + insets2.right, insets2.top + n8 + insets2.bottom);
        }
        TG.canvasProportion = height4 / height3;
        TG.editorProportion = height5 / height3;
        TG.tgObj.addMouseListener(this);
        TG.tgObj.addMouseMotionListener(this);
        TG.tgd = new TGDriver();
        if (TG.canvas != null) {
            TG.canvas.addKeyHandler(TG.tgd);
            TG.canvas.addMouseHandler(TG.tgd);
        }
    }
    
    private void delCanvas() {
        if (TG.canvas == null) {
            return;
        }
        final int height = TG.canvas.getSize().height;
        final int height2 = TG.cmdCtr.getSize().height;
        int n = 0;
        int n2;
        if (TG.editor == null) {
            n2 = height2 + height;
        }
        else {
            n = TG.editor.getSize().height + height / 2;
            n2 = height2 + (height - height / 2);
        }
        this.remove(TG.canvas);
        TG.canvas = null;
        this.resizeWindows(0, n2, n);
        TG.windCanvOpen.setEnabled(true);
        TG.windCanvClos.setEnabled(false);
        TG.sprites = null;
    }
    
    private void delEditor() {
        if (TG.editor == null) {
            return;
        }
        TG.savedEditTxtBuf = TG.editor.getTextBuffer();
        final int n = TG.editor.getSize().height + TG.nameStripeHeight;
        final int height = TG.cmdCtr.getSize().height;
        int n2 = 0;
        int n3;
        if (TG.canvas == null) {
            n3 = height + n;
        }
        else {
            n2 = TG.canvas.getSize().height + n / 2;
            n3 = height + (n - n / 2);
        }
        this.remove(TG.editor);
        this.resizeWindows(n2, n3, 0);
        TG.windEditOpen.setEnabled(true);
        TG.windEditClos.setEnabled(false);
        try {
            TG.editor.finalize();
        }
        catch (Throwable t) {}
        TG.editor = null;
        TG.editorNS = null;
    }
    
    private void displayHelp(final String s) {
        String s2 = "TurtleGraphics";
        if (s.startsWith("TE")) {
            s2 = "Editor";
        }
        else if (s.startsWith("JL")) {
            s2 = "jLogo";
        }
        final Frame frame = new Frame(s2 + " Help");
        frame.addWindowListener(new HelpWindowListener());
        frame.setLayout(new BorderLayout());
        final TextArea textArea = new TextArea(30, 60);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", 0, 14));
        frame.add("Center", textArea);
        frame.pack();
        final StringBuffer sb = new StringBuffer(5000);
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s + ".txt");
        try {
            int read;
            while ((read = resourceAsStream.read()) != -1) {
                sb.append((char)read);
            }
        }
        catch (IOException ex) {
            System.err.println("Help File (" + s + ") IOException" + ex);
        }
        textArea.append(sb.toString());
        textArea.setCaretPosition(0);
        frame.show();
    }
    
    private static String getCmdArg(final String[] array, final String s) {
        String substring = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].startsWith(s + "=")) {
                try {
                    substring = array[i].substring(s.length() + 1);
                }
                catch (StringIndexOutOfBoundsException ex) {}
                break;
            }
        }
        return substring;
    }
    
    private void interpretEditorBuffer() {
        if (TG.editor != null) {
            if (TG.canvas != null) {
                TG.canvas.clean();
                zapSprites();
                addSprite(0);
            }
            TG.tgd.reset();
            TG.editor.interpretBuffer();
        }
    }
    
    private void resizeWindows(final int n, final int n2, final int n3) {
        final Dimension size = TG.tgObj.getSize();
        final Insets insets = TG.tgObj.getInsets();
        int top = insets.top;
        final int n4 = size.width - (insets.left + insets.right);
        if (TG.canvas != null) {
            TG.canvas.setBounds(insets.left, top, n4, n);
            top += n;
        }
        TG.cmdCtrNS.setBounds(insets.left, top, n4, TG.nameStripeHeight);
        final int n5 = top + TG.nameStripeHeight;
        TG.cmdCtr.setBounds(insets.left, n5, n4, n2);
        final int n6 = n5 + n2;
        if (TG.editor != null) {
            TG.editorNS.setBounds(insets.left, n6, n4, TG.nameStripeHeight);
            TG.editor.setBounds(insets.left, n6 + TG.nameStripeHeight, n4, n3);
        }
        final float n7 = n2 + n + n3;
        TG.canvasProportion = n / n7;
        TG.editorProportion = n3 / n7;
        TG.tgObj.repaint();
    }
    
    private static void zapSprites() {
        if (TG.sprites == null) {
            TG.sprites = new Turtle[15];
        }
        else {
            for (int i = 0; i < 15; ++i) {
                if (TG.sprites[i] != null) {
                    try {
                        TG.sprites[i].finalize();
                    }
                    catch (Throwable t) {}
                    TG.sprites[i] = null;
                }
            }
        }
    }
    
    public static void addErrMsg(final ErrMsg errMsg) {
        if (TG.editor != null) {
            final TextBuffer textBuffer = TG.editor.getTextBuffer();
            if (textBuffer.hasFocus()) {
                textBuffer.addErrMsg(errMsg);
                return;
            }
        }
        TG.cmdCtr.getTextBuffer().addErrMsg(errMsg);
    }
    
    public static void addSprite(final int n) {
        if (TG.canvas == null) {
            return;
        }
        if (TG.sprites == null) {
            TG.sprites = new Turtle[15];
        }
        if (TG.sprites[n] == null) {
            TG.sprites[n] = new Turtle(TG.canvas);
        }
        else {
            System.err.println("TG.addSprite: new Turtle not needed");
        }
    }
    
    public static void clearCmdCtrMessage() {
        TG.cmdCtrNS.clearMessage();
        TG.tgObj.repaint();
    }
    
    public static void clearCmdCtrBusy() {
        TG.cmdCtrNS.clearBusy();
        TG.tgObj.repaint();
    }
    
    public static void done() {
        if (TG.editor == null) {
            System.exit(0);
        }
        else if (TG.editor.waitForSave()) {
            (TG.tgObj.timerThread = new Thread(TG.tgObj, "ExitSaveWait")).setDaemon(true);
            TG.tgObj.timerThread.start();
        }
        else {
            System.exit(0);
        }
    }
    
    public static CommandCenter getCmdCtr() {
        return TG.cmdCtr;
    }
    
    public static Frame getFrame() {
        return TG.tgFrame;
    }
    
    public static int getStatusBarHeight() {
        return TG.nameStripeHeight;
    }
    
    public static Editor getTE() {
        return TG.editor;
    }
    
    public static TGCanvas getTGC() {
        return TG.canvas;
    }
    
    public static Turtle getSprite(final int n) {
        if (TG.sprites != null) {
            return TG.sprites[n];
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = TG.tgObj.getSize();
        if (this.imgBuf == null) {
            this.imgBuf = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.imgBuf.getGraphics();
        graphics2.setClip(0, 0, size.width, size.height);
        graphics2.setFont(TG.font);
        TG.cmdCtrNS.paint(graphics2);
        if (TG.editorNS != null) {
            TG.editorNS.paint(graphics2);
        }
        super.paint(graphics2);
        graphics.drawImage(this.imgBuf, 0, 0, null);
        graphics2.dispose();
    }
    
    public static void reset() {
        if (TG.canvas != null) {
            TG.canvas.clean();
            zapSprites();
            addSprite(0);
        }
        if (TG.editor != null) {
            TG.editor.reset();
        }
        TG.tgd.reset();
    }
    
    public static void setCmdCtrBusy() {
        TG.cmdCtrNS.setBusy();
        TG.tgObj.repaint();
    }
    
    public static void setCmdCtrTurtleName(final String turtleName) {
        TG.cmdCtrNS.setTurtleName(turtleName);
        TG.tgObj.repaint();
    }
    
    public static void setEditorFileName(final String fileName) {
        TG.editorNS.setFileName(fileName);
        TG.tgObj.repaint();
    }
    
    public static void setCmdCtrMessage(final String message) {
        TG.cmdCtrNS.setMessage(message);
        TG.tgObj.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public static void main(final String[] array) {
        TG.tgFrame = new Frame("TurtleGraphics");
        TG.tgObj = new TG();
        final String cmdArg = getCmdArg(array, "editorheight");
        final int n = (cmdArg == null) ? 0 : Integer.parseInt(cmdArg);
        final String cmdArg2 = getCmdArg(array, "graphicsheight");
        final int n2 = (cmdArg2 == null) ? 300 : Integer.parseInt(cmdArg2);
        final String cmdArg3 = getCmdArg(array, "ttyheight");
        final int n3 = (cmdArg3 == null) ? 230 : Integer.parseInt(cmdArg3);
        final String cmdArg4 = getCmdArg(array, "width");
        final int n4 = (cmdArg4 == null) ? 640 : Integer.parseInt(cmdArg4);
        final String cmdArg5 = getCmdArg(array, "fontsize");
        TG.fontSize = ((cmdArg5 == null) ? 12 : Integer.parseInt(cmdArg5));
        TG.font = new Font("Courier", 0, TG.fontSize);
        TG.tgFrame.setFont(TG.font);
        TG.tgFrame.addComponentListener(TG.tgObj);
        TG.tgFrame.add(TG.tgObj, "Center");
        TG.tgFrame.addWindowListener(new TGWindowListener());
        final MenuBar menuBar = new MenuBar();
        menuBar.add(TG.tgObj.buildFileMenu());
        menuBar.add(TG.tgObj.buildEditMenu());
        menuBar.add(TG.tgObj.buildInterpretMenu());
        menuBar.add(TG.tgObj.buildWindowMenu());
        menuBar.add(TG.tgObj.buildHelpMenu());
        TG.tgFrame.setMenuBar(menuBar);
        TG.tgObj.combinedInit(n4, n2, n3, n);
        TG.tgFrame.pack();
        TG.tgFrame.show();
        TG.tgObj.start();
    }
    
    static {
        TG.fontSize = 12;
    }
}

import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.PopupMenu;
import java.util.Vector;
import java.awt.Insets;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class WButton extends Component implements MouseListener, MouseMotionListener
{
    private Component parentComp;
    public WImage iconImage;
    public String text;
    public String tooltipText;
    public int contentOrientation;
    public WImage backImage;
    public WImage backPressed;
    public WImage maskOver;
    private boolean pressed;
    private boolean mouseOver;
    private boolean mouseDragged;
    private boolean wasPressed;
    public boolean enabled;
    public Insets padding;
    public int textSpacing;
    public int pressDeltaX;
    public int pressDeltaY;
    private int buttonType;
    private Vector buttonGroup;
    private PopupMenu popupMenu;
    private WToolTip tooltip;
    private static Image offImg;
    private WImage cacheImg;
    private WImage cacheImgPressed;
    private Color oldBack;
    private Dimension oldDim;
    private int oldOrient;
    private Color fontColor;
    public boolean useBackImage;
    private ActionListener actionListener;
    private String actionCommand;
    
    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public WButton(final WImage wImage, final String s) {
        this(wImage, s, 1);
    }
    
    public WButton(final WImage wImage, final String s, final int n) {
        this(wImage, "button" + s, s, n);
    }
    
    public WButton(final WImage iconImage, final String name, final String text, final int buttonType) {
        this.contentOrientation = 0;
        this.pressed = false;
        this.mouseOver = false;
        this.mouseDragged = false;
        this.wasPressed = false;
        this.enabled = true;
        this.padding = new Insets(2, 2, 2, 2);
        this.textSpacing = 3;
        this.pressDeltaX = 1;
        this.pressDeltaY = 1;
        this.buttonType = 1;
        this.buttonGroup = null;
        this.popupMenu = null;
        this.tooltip = null;
        this.cacheImg = null;
        this.cacheImgPressed = null;
        this.fontColor = null;
        this.useBackImage = false;
        this.actionListener = null;
        this.actionCommand = "";
        if (buttonType < 0 || buttonType > 3) {
            throw new IllegalArgumentException("type");
        }
        this.setName(name);
        this.text = text;
        this.iconImage = iconImage;
        if ((this.buttonType = buttonType) > 0) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
    }
    
    public final void add(final PopupMenu popupMenu) {
        if (this.buttonType == 1) {
            super.add(this.popupMenu = popupMenu);
        }
    }
    
    public final Dimension getPreferredSize() {
        final Dimension dimension = new Dimension(0, 0);
        if (this.backImage != null) {
            dimension.setSize(this.backImage.getWidth(), this.backImage.getHeight());
        }
        if (this.backPressed != null) {
            dimension.width = Math.max(this.backPressed.getWidth(), dimension.width);
            dimension.height = Math.max(this.backPressed.getHeight(), dimension.height);
        }
        if (this.maskOver != null) {
            dimension.width = Math.max(this.maskOver.getWidth(), dimension.width);
            dimension.height = Math.max(this.maskOver.getHeight(), dimension.height);
        }
        final Dimension contentSize = this.getContentSize();
        if (this.contentOrientation == 1) {
            final int width = contentSize.width;
            contentSize.width = contentSize.height;
            contentSize.height = width;
        }
        contentSize.width = Math.max(contentSize.width, dimension.width);
        contentSize.height = Math.max(contentSize.height, dimension.height);
        return contentSize;
    }
    
    private Dimension getContentSize() {
        final Dimension dimension = new Dimension();
        int textSpacing = 0;
        if (this.iconImage != null) {
            dimension.width = this.iconImage.getWidth();
            dimension.height = this.iconImage.getHeight();
            textSpacing = this.textSpacing;
        }
        if (this.text != null && this.getFont() != null) {
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
            final Dimension dimension2 = dimension;
            dimension2.width += fontMetrics.charsWidth(this.text.toCharArray(), 0, this.text.length()) + textSpacing;
            dimension.height = Math.max(fontMetrics.getAscent() + fontMetrics.getDescent(), dimension.height);
        }
        final Dimension dimension3 = dimension;
        dimension3.width += this.padding.left + this.padding.right;
        final Dimension dimension4 = dimension;
        dimension4.height += this.padding.top + this.padding.bottom;
        return dimension;
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final void addNotify() {
        this.parentComp = this.getParent();
        this.tooltip = WToolTip.searchToolTip(this.parentComp);
        super.addNotify();
    }
    
    public final void removeNotify() {
        super.removeNotify();
        this.parentComp = null;
        this.tooltip = null;
        this.cacheImg = null;
        this.cacheImgPressed = null;
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (size.width <= 0 || size.height <= 0) {
            return;
        }
        WImage wImage = null;
        boolean b = false;
        if (this.parentComp != null && this.parentComp instanceof WBackPanel) {
            if (!size.equals(this.oldDim) || this.oldOrient != this.contentOrientation || ((WBackPanel)this.parentComp).newBackground()) {
                this.cacheImgPressed = null;
                this.cacheImg = null;
                this.oldDim = size;
                this.oldOrient = this.contentOrientation;
            }
            if ((this.pressed && this.cacheImgPressed == null) || (!this.pressed && this.cacheImg == null)) {
                wImage = ((WBackPanel)this.parentComp).getBackImage(this.getBounds());
                if (wImage.getWidth() <= 0 || wImage.getHeight() <= 0) {
                    return;
                }
            }
            else {
                b = true;
            }
        }
        else {
            final Color background = this.getBackground();
            if (!background.equals(this.oldBack) || !size.equals(this.oldDim) || this.oldOrient != this.contentOrientation) {
                this.cacheImgPressed = null;
                this.cacheImg = null;
                this.oldBack = background;
                this.oldDim = size;
                this.oldOrient = this.contentOrientation;
            }
            if ((this.pressed && this.cacheImgPressed == null) || (!this.pressed && this.cacheImg == null)) {
                wImage = new WImage(size.width, size.height, background.getRGB());
            }
            else {
                b = true;
            }
        }
        if (this.useBackImage) {
            wImage = new WImage(this.backImage);
        }
        int pressDeltaX = 0;
        int pressDeltaY = 0;
        WImage wImage2 = null;
        if (this.pressed) {
            if (b) {
                wImage = this.cacheImgPressed;
            }
            else {
                wImage2 = WImage.resize(this.backPressed, size, 0, null);
                pressDeltaX = this.pressDeltaX;
                pressDeltaY = this.pressDeltaY;
            }
        }
        else if (b) {
            wImage = this.cacheImg;
        }
        else {
            wImage2 = WImage.resize(this.backImage, size, 0, null);
        }
        if (!b) {
            wImage.paste(wImage2, 0, 0, true, wImage);
            if (this.contentOrientation == 1) {
                wImage = wImage.rotate(wImage);
            }
            final Dimension contentSize = this.getContentSize();
            int n = (wImage.getWidth() - contentSize.width) / 2 + this.padding.left + pressDeltaX;
            final int n2 = (wImage.getHeight() - contentSize.height) / 2 + this.padding.top + pressDeltaY;
            if (this.iconImage != null) {
                wImage.paste(this.iconImage, n, n2, true, wImage);
                n += this.iconImage.getWidth() + this.textSpacing;
            }
            if (this.text != null) {
                try {
                    final Image actionCommand = this.actionCommand(size.width, size.height);
                    synchronized (actionCommand) {
                        final Graphics graphics2 = actionCommand.getGraphics();
                        graphics2.setFont(this.getFont());
                        graphics2.drawImage(wImage.toJavaImage(null), 0, 0, null);
                        if (this.fontColor == null) {
                            graphics2.setColor(this.getForeground());
                        }
                        else {
                            graphics2.setColor(this.fontColor);
                        }
                        graphics2.drawString(this.text, n, n2 + graphics.getFontMetrics().getAscent());
                        wImage = WImage.create(actionCommand, size.width, size.height);
                    }
                }
                catch (NullPointerException ex) {}
            }
            if (this.contentOrientation == 1) {
                wImage = wImage.rotate(wImage).reflect(false, true);
            }
            if (this.pressed) {
                this.cacheImgPressed = wImage;
            }
            else {
                this.cacheImg = wImage;
            }
        }
        if (this.enabled && this.mouseOver && !this.pressed) {
            wImage = wImage.paste(WImage.resize(this.maskOver, size, 0, null), 0, 0, true, null);
        }
        Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            clipBounds = new Rectangle(0, 0, size.width, size.height);
        }
        graphics.drawImage(wImage.toJavaImage(clipBounds), clipBounds.x, clipBounds.y, null);
    }
    
    protected final Image actionCommand(int max, int max2) {
        max = Math.max(max, 150);
        max2 = Math.max(max2, 60);
        if (WButton.offImg == null || WButton.offImg.getWidth(null) < max || WButton.offImg.getHeight(null) < max2) {
            WButton.offImg = this.createImage(max, max2);
        }
        return WButton.offImg;
    }
    
    public final void clearCache() {
        this.oldDim = null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.enabled) {
            if (this.popupMenu != null) {
                this.popupMenu.show(this, 0, this.getSize().height);
            }
            if (this.buttonType == 1 || (this.buttonType == 2 && this.wasPressed)) {
                this.setSelected(false, true);
            }
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (this.enabled) {
            this.mouseOver = true;
            this.repaint();
        }
        if (this.tooltip != null && this.tooltipText != null) {
            try {
                this.tooltip.showToolTip(this.tooltipText, this, mouseEvent.getPoint());
            }
            catch (Exception ex) {}
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.tooltip != null) {
            try {
                this.tooltip.hideToolTip();
            }
            catch (Exception ex) {}
        }
        this.mouseOver = false;
        this.mouseDragged = false;
        if (this.buttonType == 1) {
            this.pressed = false;
        }
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.tooltip != null) {
            this.tooltip.hideToolTip();
        }
        if (this.enabled) {
            this.wasPressed = this.pressed;
            this.setSelected(true, this.buttonType > 1);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.mouseOver && this.mouseDragged) {
            this.mouseDragged = false;
            this.mouseClicked(mouseEvent);
        }
    }
    
    public final synchronized boolean isSelected() {
        return this.buttonType > 1 && this.pressed;
    }
    
    public final void setSelected(final boolean pressed, final boolean b) {
        if (pressed == this.pressed) {
            return;
        }
        if (this.buttonType > 1) {
            if (pressed) {
                final Vector buttonGroup = this.buttonGroup;
                if (buttonGroup != null) {
                    synchronized (buttonGroup) {
                        for (int i = 0; i < buttonGroup.size(); ++i) {
                            buttonGroup.elementAt(i).setSelected(false, b && this.buttonType == 2);
                        }
                    }
                }
            }
        }
        else if (!this.mouseOver) {
            return;
        }
        this.pressed = pressed;
        this.repaint();
        if (b) {
            this.fireAction();
        }
    }
    
    public final void synchronize(final Vector buttonGroup, final boolean b) {
        if (this.buttonType <= 1) {
            return;
        }
        synchronized (buttonGroup) {
            if (b) {
                if (!buttonGroup.contains(this)) {
                    buttonGroup.addElement(this);
                }
                this.buttonGroup = buttonGroup;
            }
            else {
                buttonGroup.removeElement(this);
                this.buttonGroup = null;
            }
        }
    }
    
    public final void setActionCommand(final String actionCommand) {
        if (actionCommand == null) {
            this.actionCommand = "";
        }
        else {
            this.actionCommand = actionCommand;
        }
    }
    
    public final void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public final void fireAction() {
        if (!this.actionCommand.equals("FTB_CLOSEWINDOW")) {
            if (!this.actionCommand.equals("FTB_INVERSEFUNCTION")) {
                final ActionEvent actionEvent = new ActionEvent(this, 1001, this.actionCommand);
                if (this.actionListener != null) {
                    this.actionListener.actionPerformed(actionEvent);
                }
            }
        }
    }
    
    public final void validate() {
        this.tooltip = WToolTip.searchToolTip(this.parentComp);
        super.validate();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseDragged = true;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    static {
        WButton.offImg = null;
    }
}

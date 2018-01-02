// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.actionbar;

import java.awt.event.ActionEvent;
import java.awt.PopupMenu;
import java.awt.Component;
import java.awt.Event;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Panel;

public class ButtonPanel extends Panel
{
    private FontMetrics fm;
    private Font buttonFont;
    private Image offscreenBuffer;
    public int xoffset;
    public int bgImageXOffset;
    public int bgImageYOffset;
    public Image bgImage;
    public int bgImageStyle;
    public Dimension scaleDim;
    public boolean showHinky;
    private int buttonHeight;
    private int tickSize;
    private int moIndex;
    private boolean textInvalid;
    private Color buttonBackgroundColor;
    private Color buttonForegroundColor;
    private Color buttonMouseOverColor;
    private Color pressedColor;
    private Color selectedColor;
    private Color borderColor;
    private boolean buttonTransparent;
    private boolean buttonsRightAligned;
    private boolean notesColorInit;
    public int buttonSpacing;
    int currentSelectionIndex;
    int currentPressedIndex;
    Dimension actionsDimension;
    Rectangle buttonRect;
    Insets panelInsets;
    int actionHeightType;
    int actionWidthType;
    int fixedButtonWidth;
    public boolean bunderline;
    public boolean bstrike;
    ActionListener listener;
    static final int DEFAULT = 0;
    static final int ABSOLUTE = 1;
    static final int MINIMUM = 2;
    static final int BGSIZE = 3;
    public static final int BUTTON_BORDER_WIDTH = 6;
    private static final int ACTION_ICON_HEIGHT = 21;
    private static final int A_RIGHT = 1;
    private static final int A_CENTER = 2;
    private static final int A_LEFT = 3;
    public int textJustify;
    int prevIndex;
    public Action[] actions;
    
    public ButtonPanel() {
        this.fm = null;
        this.buttonFont = null;
        this.offscreenBuffer = null;
        this.xoffset = 0;
        this.bgImageXOffset = 0;
        this.bgImage = null;
        this.bgImageStyle = 0;
        this.scaleDim = new Dimension(0, 0);
        this.showHinky = true;
        this.buttonHeight = 21;
        this.moIndex = -1;
        this.textInvalid = true;
        this.buttonBackgroundColor = Color.gray;
        this.buttonForegroundColor = Color.black;
        this.buttonMouseOverColor = null;
        this.pressedColor = Color.darkGray;
        this.selectedColor = Color.lightGray;
        this.borderColor = Color.black;
        this.buttonTransparent = false;
        this.buttonsRightAligned = false;
        this.notesColorInit = false;
        this.buttonSpacing = 15;
        this.currentSelectionIndex = -1;
        this.currentPressedIndex = -1;
        this.actionsDimension = new Dimension(0, 0);
        this.buttonRect = new Rectangle();
        this.panelInsets = new Insets(2, 2, 2, 2);
        this.actionHeightType = 0;
        this.actionWidthType = 0;
        this.fixedButtonWidth = 0;
        this.bunderline = false;
        this.bstrike = false;
        this.listener = null;
        this.textJustify = 3;
        this.prevIndex = -1;
        this.actions = null;
        this.setFont(new Font("Helvetica", 0, 12));
    }
    
    private void initNotesColors() {
        Color color;
        if (this.buttonTransparent) {
            color = this.getBackground();
        }
        else {
            color = this.buttonBackgroundColor;
        }
        if (nearBlack(color) || nearWhite(color)) {
            color = (this.borderColor = Color.gray);
        }
        else {
            this.borderColor = color.darker().darker();
        }
        final float[] rgBtoHSB = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        if (rgBtoHSB[2] >= 0.9f && rgBtoHSB[2] >= 0.1f) {
            this.selectedColor = Color.getHSBColor(rgBtoHSB[0], rgBtoHSB[1] / 2.0f, rgBtoHSB[2]);
            this.pressedColor = Color.getHSBColor(rgBtoHSB[0], rgBtoHSB[1], rgBtoHSB[2] - 0.3f);
        }
        else {
            this.selectedColor = color.brighter();
            this.pressedColor = color.darker();
        }
    }
    
    public void addActionListener(final ActionListener listener) {
        this.listener = listener;
    }
    
    ActionBar getActionBar() {
        return (ActionBar)this.getParent().getParent();
    }
    
    public void setButtonHeight(final int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }
    
    public void setHeightType(final int actionHeightType) {
        this.actionHeightType = actionHeightType;
    }
    
    public void setWidthType(final int actionWidthType) {
        this.actionWidthType = actionWidthType;
    }
    
    public void setFont(final Font buttonFont) {
        this.buttonFont = buttonFont;
        if (buttonFont != null) {
            this.fm = this.getToolkit().getFontMetrics(this.getFont());
            this.tickSize = this.fm.getHeight() / 3;
            this.invalidate();
        }
    }
    
    public Font getFont() {
        return this.buttonFont;
    }
    
    public void setButtonBackground(final Color buttonBackgroundColor) {
        this.buttonBackgroundColor = buttonBackgroundColor;
        if (nearBlack(this.buttonBackgroundColor)) {
            this.buttonMouseOverColor = Color.darkGray;
        }
        else if (nearWhite(this.buttonBackgroundColor)) {
            this.buttonMouseOverColor = Color.lightGray;
        }
        else {
            this.buttonMouseOverColor = null;
        }
    }
    
    public void setButtonForeground(final Color buttonForegroundColor) {
        this.buttonForegroundColor = buttonForegroundColor;
    }
    
    public void setButtonTransparent(final boolean buttonTransparent) {
        this.buttonTransparent = buttonTransparent;
    }
    
    public void setButtonsRightAligned(final boolean buttonsRightAligned) {
        this.buttonsRightAligned = buttonsRightAligned;
    }
    
    public void setActions(final Action[] actions) {
        this.currentSelectionIndex = -1;
        this.currentPressedIndex = -1;
        this.textInvalid = true;
        this.actions = actions;
    }
    
    public String getButtonCaption(final int n) {
        if (this.actions != null && this.actions.length >= 0) {
            return this.actions[n].getText();
        }
        return null;
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        final Dimension calculateActionDimensions = this.calculateActionDimensions();
        final Dimension dimension = new Dimension(0, calculateActionDimensions.height + this.insets().bottom + this.insets().top + 2);
        if (this.actions != null) {
            dimension.width = calculateActionDimensions.width;
        }
        return dimension;
    }
    
    public void reset() {
        this.currentPressedIndex = -1;
        this.currentSelectionIndex = -1;
        this.paint(this.getGraphics());
    }
    
    Dimension calculateActionDimensions() {
        int width = 0;
        if (this.actions != null && this.fm != null) {
            final int length = this.actions.length;
            this.calculateButtonHeight();
            for (int i = 0; i < this.actions.length; ++i) {
                if (this.actions[i].image != null) {
                    int height = this.actions[i].image.getHeight(this);
                    if (this.actions[i].imagesHigh > 1) {
                        height = (height - 1) / this.actions[i].imagesHigh;
                    }
                    if (height > this.buttonHeight) {
                        this.actions[i].imageScaleFactor = this.buttonHeight / height;
                    }
                    else {
                        this.actions[i].imageScaleFactor = 1.0f;
                    }
                }
                this.actions[i].width = this.calculateButtonWidth(i);
                width += this.actions[i].width;
            }
            this.actions[0].xorigin = this.insets().left;
            for (int j = 1; j < length; ++j) {
                this.actions[j].xorigin = this.actions[j - 1].xorigin + this.actions[j - 1].width + this.buttonSpacing;
            }
            width = width + (length - 1) * this.buttonSpacing + (this.insets().left + this.insets().right);
        }
        this.actionsDimension.width = width;
        this.actionsDimension.height = this.buttonHeight;
        return this.actionsDimension;
    }
    
    void calculateButtonHeight() {
        switch (this.actionHeightType) {
            case 1: {
                break;
            }
            case 3: {
                final int bgImageHeight = this.actions[0].getBGImageHeight();
                if (bgImageHeight > 0 || this.getActionBar().actionBorderStyle == 3) {
                    this.buttonHeight = Math.max(8, Math.max(this.fm.getHeight(), bgImageHeight));
                    break;
                }
            }
            case 2: {
                this.buttonHeight = Math.max(this.buttonHeight, Math.max(this.fm.getHeight(), 21));
                break;
            }
            default: {
                this.buttonHeight = Math.max(this.fm.getHeight(), 21);
                break;
            }
        }
    }
    
    public int calculateButtonWidth(final int n) {
        if (this.actionWidthType == 1) {
            return this.fixedButtonWidth;
        }
        int stringWidth = 0;
        if (this.actions[n].text != null) {
            stringWidth = this.fm.stringWidth(this.actions[n].text);
        }
        if (this.actions[n].hasSubactions) {
            stringWidth += 6;
            stringWidth += this.tickSize;
        }
        if (this.actions[n].image != null) {
            final int n2 = (this.actions[n].image.getWidth(this) - (this.actions[n].imagesWide - 1)) / this.actions[n].imagesWide;
            stringWidth += 3;
            stringWidth += (int)(n2 * this.actions[n].imageScaleFactor);
        }
        int max = stringWidth + 6 + 6;
        if (this.actionWidthType == 3 && this.actions[n].bgImage != null) {
            max = Math.max(max, this.actions[n].getBGImageWidth());
        }
        return max;
    }
    
    public Insets insets() {
        return this.panelInsets;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int height = this.size().height;
        if (this.actions == null || this.actions.length < 1) {
            return;
        }
        final int n = 2;
        this.prevIndex = -1;
        if (this.offscreenBuffer == null) {
            this.offscreenBuffer = this.createImage(2000, height);
            this.textInvalid = true;
        }
        final Graphics graphics2 = this.offscreenBuffer.getGraphics();
        final Color foreground = this.getForeground();
        graphics2.setFont(this.getFont());
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.offscreenBuffer.getWidth(this), this.offscreenBuffer.getHeight(this));
        this.drawBGImage(graphics2, this.bgImageXOffset, this.bgImageYOffset, this.size().width, this.size().height);
        int n2 = this.insets().left + 6;
        if (this.buttonsRightAligned) {
            n2 -= this.actionsDimension.width - this.size().width;
        }
        for (int i = 0; i < this.actions.length; ++i) {
            n2 += this.drawButton(graphics2, n2 + this.xoffset, n, i, i == this.currentSelectionIndex, i == this.currentPressedIndex) + this.buttonSpacing;
        }
        graphics2.setColor(foreground);
        this.textInvalid = false;
        graphics.drawImage(this.offscreenBuffer, 0, 0, null);
        if (graphics2 != null) {
            graphics2.dispose();
        }
    }
    
    int drawButton(final Graphics graphics, final int n, final int y, final int n2, final boolean b, final boolean b2) {
        final Graphics create = graphics.create();
        final int width = this.actions[n2].width;
        final int n3 = y + this.fm.getMaxAscent() + (this.buttonHeight - this.fm.getHeight()) / 2;
        final int actionBorderStyle = this.getActionBar().actionBorderStyle;
        this.buttonRect.x = n - 6;
        this.buttonRect.y = y;
        this.buttonRect.width = width;
        this.buttonRect.height = this.buttonHeight;
        final int n4 = (this.actions[n2].iconOnRight || (this.actions[n2].readingRTL && this.actions[n2].image == null)) ? 6 : (this.buttonRect.width - (this.tickSize + 6));
        this.actions[n2].buttonRect.setBounds(this.buttonRect);
        if (!this.buttonTransparent && actionBorderStyle != 3) {
            create.setColor(this.buttonBackgroundColor);
            create.fillRect(this.buttonRect.x, this.buttonRect.y, this.buttonRect.width, this.buttonRect.height);
        }
        if ((b || b2 || actionBorderStyle == 1) && actionBorderStyle != 2 && actionBorderStyle != 3 && !this.buttonTransparent) {
            boolean b3 = false;
            if (this.buttonTransparent) {
                if (nearBlack(this.getBackground())) {
                    create.setColor(Color.darkGray);
                    b3 = true;
                }
                else if (nearWhite(this.getBackground())) {
                    create.setColor(Color.lightGray);
                    b3 = true;
                }
            }
            else if (this.buttonMouseOverColor != null) {
                create.setColor(this.buttonMouseOverColor);
                b3 = true;
            }
            if (b3) {
                create.fillRect(this.buttonRect.x, this.buttonRect.y, this.buttonRect.width, this.buttonRect.height);
            }
        }
        if (actionBorderStyle == 3) {
            this.drawNotesRect(create, this.buttonRect.x, this.buttonRect.y - 2, this.buttonRect.width, this.buttonRect.height + 2, b, b2);
        }
        if (this.actions[n2].bgImage != null) {
            this.drawActionBGImage(n2, create, b, b2);
        }
        if (this.actions[n2].hasSubactions && (this.showHinky || b || b2)) {
            this.drawTickMark(create, this.buttonRect.x + n4, n3);
        }
        else {
            final int width2 = this.buttonRect.width;
        }
        create.setColor(this.buttonForegroundColor);
        this.drawTextAndIcon(create, n2, n, y, n3, b, b2);
        if ((b || b2 || actionBorderStyle == 1) && actionBorderStyle != 2 && actionBorderStyle != 3) {
            this.drawBevelRect(create, this.buttonRect.x, this.buttonRect.y, this.buttonRect.width - 1, this.buttonRect.height - 1, b2);
        }
        create.dispose();
        return width;
    }
    
    private void drawTextAndIcon(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        int n5 = n2;
        final Shape clip = graphics.getClip();
        final int n6 = (this.actions[n].text != null) ? this.fm.stringWidth(this.actions[n].text) : 0;
        int n7 = 0;
        if (this.actions[n].image != null) {
            n7 = (int)(this.actions[n].getImageWidth() * this.actions[n].imageScaleFactor);
        }
        switch (this.textJustify) {
            case 1: {
                n5 = this.buttonRect.x + this.buttonRect.width - n6;
                if (this.actions[n].hasSubactions) {
                    n5 -= this.tickSize + 3;
                }
                n5 -= 6;
                break;
            }
            case 2: {
                int width = this.buttonRect.width;
                int x = this.buttonRect.x;
                if (!this.actions[n].iconOnRight && this.actions[n].image != null) {
                    x += n7 + 3;
                    width -= n7 + 3;
                    if (this.actions[n].hasSubactions) {
                        width -= this.tickSize + 3;
                    }
                }
                else if (this.actions[n].iconOnRight && this.actions[n].image != null) {
                    width -= n7 + 3;
                    if (this.actions[n].hasSubactions) {
                        x += this.tickSize + 3;
                        width -= this.tickSize + 3;
                    }
                }
                else if (this.actions[n].hasSubactions) {
                    width -= this.tickSize + 3;
                }
                n5 = x + width - width / 2 - n6 / 2;
                break;
            }
        }
        if (this.actions[n].iconOnRight || (this.actions[n].readingRTL && this.actions[n].image == null)) {
            final int n8 = this.buttonRect.x + this.buttonRect.width - (n7 + 6);
            final int n9 = this.actions[n].hasSubactions ? (n2 + this.tickSize + 3) : n2;
            if (this.actions[n].hasSubactions && this.textJustify == 3) {
                n5 += this.tickSize + 3;
            }
            if (this.textJustify == 1) {
                n5 = n8 - (n6 + 3);
            }
            if (this.actions[n].text != null) {
                graphics.setClip(n9, n3, n8 - n9, this.buttonRect.height);
                this.drawText(graphics, this.actions[n].text, n5, n4);
                graphics.setClip(clip);
            }
            if (this.actions[n].image != null) {
                this.drawActionImage(n, graphics, n8, n3, b, b2);
            }
        }
        else {
            int drawActionImage = 0;
            if (this.actions[n].image != null) {
                drawActionImage = this.drawActionImage(n, graphics, n2, n3, b, b2);
                if (this.textJustify == 3) {
                    n5 += drawActionImage + 3;
                }
            }
            if (this.actions[n].text != null) {
                int n10 = this.buttonRect.width - (drawActionImage + 6);
                if (this.actions[n].hasSubactions) {
                    n10 -= this.tickSize + 3;
                }
                if (this.textJustify == 1) {
                    n10 -= 6;
                }
                graphics.setClip(n2 + drawActionImage, n3, n10, this.buttonRect.height);
                this.drawText(graphics, this.actions[n].text, n5, n4);
                graphics.setClip(clip);
            }
        }
    }
    
    private void drawText(final Graphics graphics, final String s, final int n, final int n2) {
        final int stringWidth = this.fm.stringWidth(s);
        graphics.drawString(s, n, n2);
        if (this.bstrike) {
            graphics.fillRect(n, n2 - this.fm.getAscent() / 3, stringWidth, 1);
        }
        if (this.bunderline) {
            graphics.fillRect(n, n2 + 1, stringWidth, 1);
        }
    }
    
    private int drawActionImage(final int n, final Graphics graphics, final int n2, final int n3, final boolean b, final boolean b2) {
        int n4 = 0;
        if (this.actions[n].image != null) {
            int n5 = 0;
            final int imageHeight = this.actions[n].getImageHeight();
            final int imageWidth = this.actions[n].getImageWidth();
            if (this.actions[n].isImageWell) {
                if (b) {
                    n5 = 1;
                }
                if (b2) {
                    n5 = 2;
                }
            }
            final Graphics create = graphics.create(n2, n3 + (this.buttonHeight - (int)(imageHeight * this.actions[n].imageScaleFactor)) / 2, (int)(imageWidth * this.actions[n].imageScaleFactor), (int)(imageHeight * this.actions[n].imageScaleFactor));
            final int n6 = (imageWidth * n5 + n5) * -1;
            if (this.actions[n].imageScaleFactor != 1.0f) {
                create.drawImage(this.actions[n].image, (int)(n6 * this.actions[n].imageScaleFactor), 0, -1, this.buttonHeight, this);
            }
            else {
                create.drawImage(this.actions[n].image, n6, 0, this);
            }
            create.dispose();
            n4 += (int)(imageWidth * this.actions[n].imageScaleFactor);
        }
        return n4;
    }
    
    private void drawActionBGImage(final int n, final Graphics graphics, final boolean b, final boolean b2) {
        int n2 = 0;
        if (this.actions[n].bgImagesHigh > 1 || this.actions[n].bgImagesWide > 1) {
            if (b) {
                n2 = this.actions[n].getBGImageWidth() + 1;
            }
            if (this.actions[n].bgImagesWide > 2 && b2) {
                n2 = (this.actions[n].getBGImageWidth() + 1) * 2;
            }
        }
        final Rectangle buttonRect = this.actions[n].buttonRect;
        final Graphics create = graphics.create(buttonRect.x, buttonRect.y, buttonRect.width, buttonRect.height);
        create.drawImage(this.actions[n].bgImage, 0, 0, buttonRect.width, buttonRect.height, n2, 0, n2 + this.actions[n].getBGImageWidth(), this.actions[n].getBGImageHeight(), this);
        create.dispose();
    }
    
    public void drawBGImage(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.bgImage != null) {
            int i = n;
            int j = n2;
            final int height = this.bgImage.getHeight(null);
            final int width = this.bgImage.getWidth(null);
            switch (this.bgImageStyle) {
                case 1: {
                    graphics.drawImage(this.bgImage, i, j, null);
                    break;
                }
                case 2: {
                    while (j <= n4) {
                        graphics.drawImage(this.bgImage, i, j, null);
                        j += height;
                        if (j >= n4) {
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    while (i <= n3) {
                        graphics.drawImage(this.bgImage, i, j, null);
                        i += width;
                        if (i >= n3) {
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    while (j <= n4) {
                        graphics.drawImage(this.bgImage, i, j, null);
                        i += width;
                        if (i >= n3) {
                            i = n;
                            j += height;
                        }
                    }
                    break;
                }
                case 5: {
                    graphics.drawImage(this.bgImage, i, j, this.scaleDim.width, this.scaleDim.height, null);
                    break;
                }
                case 6: {
                    graphics.drawImage(this.bgImage, i, j, this.scaleDim.width, this.scaleDim.height, null);
                    break;
                }
                case 7: {
                    graphics.drawImage(this.bgImage, this.scaleDim.width / 2 - width / 2 + i, this.scaleDim.height / 2 - height / 2 + j, null);
                    break;
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        if (event.target == this) {
            switch (event.id) {
                case 501: {
                    b = true;
                    final int locateActionButton = this.locateActionButton(event.x);
                    if (locateActionButton != -1) {
                        this.handleMouseDown(locateActionButton);
                        break;
                    }
                    break;
                }
                case 502: {
                    b = true;
                    this.handleMouseUp(this.locateActionButton(event.x));
                    break;
                }
                case 503:
                case 506: {
                    b = true;
                    final int locateActionButton2 = this.locateActionButton(event.x);
                    if (locateActionButton2 != -1 && locateActionButton2 != this.currentSelectionIndex) {
                        this.handleMouseOver(locateActionButton2);
                        break;
                    }
                    if (locateActionButton2 == -1 && this.actions.length == 1 && this.currentSelectionIndex != -1) {
                        this.currentSelectionIndex = -1;
                        this.repaint();
                        break;
                    }
                    break;
                }
            }
        }
        return b || super.handleEvent(event);
    }
    
    void handleMouseOver(final int n) {
        this.currentSelectionIndex = n;
        this.paint(this.getGraphics());
        if (n != -1) {
            this.prevIndex = n;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (event.target == this) {
            this.currentSelectionIndex = -1;
            this.prevIndex = -1;
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void drawNotesRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        final Graphics create = graphics.create();
        if (!this.notesColorInit) {
            this.initNotesColors();
        }
        create.setColor(this.borderColor);
        create.fillRoundRect(n - 1, n2, n3 + 2, n4 + 2, 3, 3);
        if (b2) {
            create.setColor(this.pressedColor);
        }
        else if (b) {
            create.setColor(this.selectedColor);
        }
        else if (this.buttonTransparent) {
            create.setColor(this.getBackground());
        }
        else {
            create.setColor(this.buttonBackgroundColor);
        }
        create.fillRoundRect(n, n2 + 1, n3, n4, 3, 3);
        create.dispose();
    }
    
    void drawBevelRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final Graphics create = graphics.create();
        final int n5 = n + n3;
        final int n6 = n2 + n4;
        final Color color = create.getColor();
        Color color2;
        if (this.buttonTransparent) {
            color2 = this.getBackground();
        }
        else {
            color2 = this.buttonBackgroundColor;
        }
        if (nearBlack(color2)) {
            color2 = Color.darkGray;
        }
        if (nearWhite(color2)) {
            color2 = Color.lightGray;
        }
        if (b) {
            create.setColor(Color.black);
        }
        else {
            create.setColor(Color.white);
        }
        create.drawLine(n - 2, n2 - 2, n5 + 1, n2 - 2);
        create.drawLine(n - 2, n2 - 2, n - 2, n6 + 1);
        if (b) {
            create.setColor(Color.white);
        }
        else {
            create.setColor(Color.black);
        }
        create.drawLine(n5 + 2, n2 - 2, n5 + 2, n6 + 2);
        create.drawLine(n - 2, n6 + 2, n5 + 2, n6 + 2);
        if (b) {
            create.setColor(color2.darker());
        }
        else {
            create.setColor(color2);
        }
        create.drawLine(n - 1, n2 - 1, n5, n2 - 1);
        create.drawLine(n - 1, n2 - 1, n - 1, n6);
        if (b) {
            create.setColor(color2);
        }
        else {
            create.setColor(color2.darker());
        }
        create.drawLine(n5 + 1, n2 - 1, n5 + 1, n6 + 1);
        create.drawLine(n - 1, n6 + 1, n5 + 1, n6 + 1);
        create.setColor(color);
        create.dispose();
    }
    
    void drawTickMark(final Graphics graphics, final int n, final int n2) {
        final Graphics create = graphics.create();
        final int[] array = { n, n + this.tickSize, n + this.tickSize / 2 };
        final int[] array2 = { n2 - this.tickSize, n2 - this.tickSize, n2 };
        create.setColor(this.buttonForegroundColor);
        create.fillPolygon(array, array2, array.length);
        create.dispose();
    }
    
    void handleMouseDown(final int n) {
        this.currentSelectionIndex = -1;
        if (this.currentPressedIndex == n) {
            this.currentPressedIndex = -1;
            this.currentSelectionIndex = n;
            this.paint(this.getGraphics());
            return;
        }
        this.getActionBar();
        this.currentPressedIndex = n;
        this.paint(this.getGraphics());
        if (this.actions[n].hasSubactions) {
            final PopupMenu menu = this.actions[n].menu;
            if (menu != null) {
                menu.show(this, this.actions[n].buttonRect.x - 2, this.actions[n].buttonRect.y + this.actions[n].buttonRect.height + 2);
            }
        }
    }
    
    void handleMouseUp(final int n) {
        if (n == -1 || this.currentPressedIndex == -1) {
            this.reset();
            return;
        }
        if (this.actions[this.currentPressedIndex].hasSubactions) {
            this.reset();
            return;
        }
        if (this.currentPressedIndex == n && !this.actions[this.currentPressedIndex].hasSubactions) {
            this.currentPressedIndex = -1;
            this.paint(this.getGraphics());
            if (this.listener != null) {
                this.listener.actionPerformed(new ActionEvent(this, 1001, String.valueOf(this.actions[n].index)));
            }
        }
    }
    
    int locateActionButton(int n) {
        int n2 = -1;
        if (this.buttonsRightAligned) {
            n += this.actionsDimension.width - this.size().width;
        }
        n -= this.xoffset;
        for (int i = 0; i < this.actions.length; ++i) {
            final int xorigin = this.actions[i].xorigin;
            final int width = this.actions[i].width;
            if (n >= xorigin && n <= xorigin + width) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    static boolean nearBlack(final Color color) {
        return color.getRed() <= 32 && color.getGreen() <= 32 && color.getBlue() <= 32;
    }
    
    static boolean nearWhite(final Color color) {
        return color.getRed() >= 242 && color.getGreen() >= 242 && color.getBlue() >= 242;
    }
}

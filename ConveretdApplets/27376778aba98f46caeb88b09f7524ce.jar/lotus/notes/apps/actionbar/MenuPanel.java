// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.actionbar;

import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class MenuPanel extends Panel implements Runnable
{
    ButtonPanel buttonPanel;
    ButtonPanel buttonPanelRight;
    ScrollButton scrollButtonLeft;
    ScrollButton scrollButtonRight;
    int totalButtonWidth;
    int buttonPanelWidth;
    boolean scrollRight;
    boolean buttonsRightAligned;
    Image offImage;
    Graphics offg;
    private static final int SGAP = 5;
    
    public MenuPanel() {
        this.totalButtonWidth = 0;
        this.buttonPanelWidth = 0;
        this.buttonsRightAligned = false;
        this.offImage = null;
        this.offg = null;
        this.setLayout(null);
        this.buttonPanel = new ButtonPanel();
        this.buttonPanelRight = new ButtonPanel();
        this.buttonPanel.setFont(this.getFont());
        this.buttonPanelRight.setFont(this.getFont());
        this.buttonPanel.setButtonsRightAligned(false);
        this.buttonPanelRight.setButtonsRightAligned(true);
        this.add(this.buttonPanel);
        this.add(this.buttonPanelRight);
        this.add(this.scrollButtonLeft = new ScrollButton(10, false, this));
        this.add(this.scrollButtonRight = new ScrollButton(10, true, this));
    }
    
    public Insets insets() {
        return new Insets(2, 2, 2, 2);
    }
    
    public void setActions(final int n, final int n2, final Action[] array) {
        Action[] actions = null;
        Action[] actions2 = null;
        int n3 = n2 - 1;
        int n4 = 0;
        if (n > 0) {
            actions = new Action[n];
        }
        if (n2 > 0) {
            actions2 = new Action[n2];
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i].rightAligned) {
                actions2[n3--] = array[i];
            }
            else {
                actions[n4++] = array[i];
            }
        }
        if (n > 0) {
            this.buttonPanel.setActions(actions);
        }
        else {
            this.buttonPanel.show(false);
        }
        if (n2 > 0) {
            this.buttonPanelRight.setActions(actions2);
        }
        else {
            this.buttonPanelRight.show(false);
        }
    }
    
    public void setActions(final Action[] actions) {
        this.buttonPanel.setActions(actions);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.buttonPanel.setBackground(background);
        this.buttonPanelRight.setBackground(background);
        if (ButtonPanel.nearBlack(background)) {
            this.scrollButtonLeft.setForeground(Color.gray);
            this.scrollButtonRight.setForeground(Color.gray);
        }
        else if (ButtonPanel.nearWhite(background)) {
            this.scrollButtonLeft.setForeground(Color.lightGray);
            this.scrollButtonRight.setForeground(Color.lightGray);
        }
        else if (Color.RGBtoHSB(background.getRed(), background.getGreen(), background.getBlue(), null)[2] <= 0.6f) {
            this.scrollButtonRight.setForeground(background.brighter());
            this.scrollButtonLeft.setForeground(background.brighter());
        }
        else {
            this.scrollButtonRight.setForeground(background.darker());
            this.scrollButtonLeft.setForeground(background.darker());
        }
    }
    
    public void setButtonsRightAligned(final boolean buttonsRightAligned) {
        this.buttonsRightAligned = buttonsRightAligned;
    }
    
    public void layout() {
        final Insets insets = this.insets();
        ButtonPanel buttonPanel;
        ButtonPanel buttonPanel2;
        if (this.buttonsRightAligned) {
            buttonPanel = this.buttonPanel;
            buttonPanel2 = this.buttonPanelRight;
        }
        else {
            buttonPanel = this.buttonPanelRight;
            buttonPanel2 = this.buttonPanel;
        }
        final Dimension size = this.size();
        buttonPanel.scaleDim = size;
        buttonPanel2.scaleDim = size;
        final Dimension preferredSize = this.scrollButtonLeft.preferredSize();
        final Dimension preferredSize2 = buttonPanel2.preferredSize();
        final Dimension preferredSize3 = buttonPanel.preferredSize();
        this.totalButtonWidth = preferredSize2.width;
        final int n = this.size().height / 2 - preferredSize2.height / 2;
        final ButtonPanel buttonPanel3 = this.buttonPanel;
        final ButtonPanel buttonPanelRight = this.buttonPanelRight;
        final int n2 = -n;
        buttonPanelRight.bgImageYOffset = n2;
        buttonPanel3.bgImageYOffset = n2;
        this.buttonPanelWidth = this.size().width - insets.right - preferredSize.width - 5 - 5 - preferredSize.width - ((preferredSize3.width > 0) ? 5 : 0) - preferredSize3.width - insets.left;
        int n3 = insets.left;
        if (this.buttonsRightAligned) {
            n3 = insets.left;
            buttonPanel.reshape(n3, n, preferredSize3.width, preferredSize3.height);
            if (preferredSize3.width > 0) {
                n3 += preferredSize3.width + 5;
                buttonPanel.bgImageXOffset = -n3;
            }
        }
        this.scrollButtonLeft.reshape(n3, n + (preferredSize2.height / 2 - preferredSize.height / 2), preferredSize.width, preferredSize.height);
        final int n4 = n3 + (preferredSize.width + 5);
        buttonPanel2.bgImageXOffset = -n4;
        buttonPanel2.reshape(n4, n, this.buttonPanelWidth, preferredSize2.height);
        final int n5 = n4 + (this.buttonPanelWidth + 5);
        this.scrollButtonRight.reshape(n5, n + (preferredSize2.height / 2 - preferredSize.height / 2), preferredSize.width, preferredSize.height);
        int n6 = n5 + preferredSize.width;
        if (!this.buttonsRightAligned && preferredSize3.width > 0) {
            n6 += 5;
            buttonPanel.reshape(n6, n, preferredSize3.width, preferredSize3.height);
            buttonPanel.bgImageXOffset = -n6;
        }
        final int n7 = this.totalButtonWidth - this.buttonPanelWidth;
        this.scrollButtonLeft.show((buttonPanel2.xoffset != 0 && !this.buttonsRightAligned) || (buttonPanel2.xoffset < n7 && this.buttonsRightAligned));
        this.scrollButtonRight.show((buttonPanel2.xoffset != 0 && this.buttonsRightAligned) || (buttonPanel2.xoffset > -n7 && !this.buttonsRightAligned));
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        final Dimension preferredSize = this.buttonPanel.preferredSize();
        final Dimension preferredSize2 = this.buttonPanelRight.preferredSize();
        return (preferredSize.height > preferredSize2.height) ? preferredSize : preferredSize2;
    }
    
    public void handleMouseEvent(final MouseEvent mouseEvent) {
        final ButtonPanel buttonPanel = this.buttonsRightAligned ? this.buttonPanelRight : this.buttonPanel;
        if (mouseEvent.getSource() == this.scrollButtonLeft) {
            if (mouseEvent.getID() == 501 && (buttonPanel.xoffset < 0 || this.buttonsRightAligned)) {
                this.scrollRight = false;
                new Thread(this).start();
            }
        }
        else if (mouseEvent.getSource() == this.scrollButtonRight && mouseEvent.getID() == 501 && (buttonPanel.xoffset > 0 || !this.buttonsRightAligned)) {
            this.scrollRight = true;
            new Thread(this).start();
        }
    }
    
    public Image getImage(final URL url, final String s) {
        Image image = null;
        final String string = url.toString();
        try {
            image = this.getImage(new URL((string.endsWith("/") ? string.substring(0, string.length() - 1) : string) + s));
        }
        catch (Exception ex) {}
        return image;
    }
    
    public Image getImage(final URL url) {
        final Image image = this.getToolkit().getImage(url);
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {}
        }
        return image;
    }
    
    public void run() {
        this.animateScroll();
    }
    
    synchronized void animateScroll() {
        final ButtonPanel buttonPanel = this.buttonsRightAligned ? this.buttonPanelRight : this.buttonPanel;
        final int n = this.totalButtonWidth - this.buttonPanelWidth;
        if ((!this.scrollRight && !this.buttonsRightAligned && buttonPanel.xoffset >= 0) || (this.scrollRight && this.buttonsRightAligned && buttonPanel.xoffset <= 0)) {
            return;
        }
        if ((this.scrollRight && !this.buttonsRightAligned && -buttonPanel.xoffset >= n) || (!this.scrollRight && this.buttonsRightAligned && buttonPanel.xoffset >= n)) {
            return;
        }
        int locateActionButton = buttonPanel.locateActionButton(this.buttonsRightAligned ? (this.buttonPanelWidth - buttonPanel.insets().right) : buttonPanel.insets().left);
        if (locateActionButton != -1 || buttonPanel.xoffset <= 0) {
            if (!this.buttonsRightAligned && !this.scrollRight && locateActionButton > 0) {
                --locateActionButton;
            }
            else if (this.buttonsRightAligned && this.scrollRight && locateActionButton < buttonPanel.actions.length - 1) {
                ++locateActionButton;
            }
            int n2;
            if (locateActionButton == -1 && buttonPanel.xoffset != 0) {
                n2 = Math.abs(buttonPanel.xoffset);
            }
            else if ((!this.buttonsRightAligned && this.scrollRight && (buttonPanel.actions.length == 1 || locateActionButton == buttonPanel.actions.length - 1)) || (this.buttonsRightAligned && !this.scrollRight && (buttonPanel.actions.length == 1 || locateActionButton == 0))) {
                n2 = this.totalButtonWidth - this.buttonPanelWidth - Math.abs(buttonPanel.xoffset);
            }
            else if (buttonPanel.actions.length == 1 && this.scrollRight == this.buttonsRightAligned) {
                n2 = Math.abs(buttonPanel.xoffset);
            }
            else if (locateActionButton == buttonPanel.actions.length - 2 && !this.scrollRight && buttonPanel.actions[locateActionButton + 1].buttonRect.x < 0 && !this.buttonsRightAligned) {
                n2 = Math.abs(buttonPanel.actions[locateActionButton + 1].buttonRect.x) + buttonPanel.insets().left;
            }
            else if (locateActionButton == 1 && this.scrollRight && buttonPanel.actions[0].buttonRect.x + buttonPanel.actions[0].buttonRect.width > this.buttonPanelWidth && this.buttonsRightAligned) {
                n2 = buttonPanel.actions[0].buttonRect.x + buttonPanel.actions[0].buttonRect.width - this.buttonPanelWidth + buttonPanel.insets().right;
            }
            else {
                n2 = buttonPanel.actions[locateActionButton].width + buttonPanel.buttonSpacing;
            }
            float n3 = n2 / 10;
            if (n3 <= 0.0f) {
                n3 = 1.0f;
            }
            float n4;
            for (n4 = n3; n4 <= n2; n4 += n3) {
                if (this.scrollRight) {
                    final ButtonPanel buttonPanel2 = buttonPanel;
                    buttonPanel2.xoffset -= (int)n3;
                }
                else {
                    final ButtonPanel buttonPanel3 = buttonPanel;
                    buttonPanel3.xoffset += (int)n3;
                }
                buttonPanel.repaint();
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            if (n4 != n2) {
                final float n5 = n4 - n3;
                if (this.scrollRight) {
                    final ButtonPanel buttonPanel4 = buttonPanel;
                    buttonPanel4.xoffset -= (int)(n2 - n5);
                }
                else {
                    final ButtonPanel buttonPanel5 = buttonPanel;
                    buttonPanel5.xoffset += (int)(n2 - n5);
                }
                buttonPanel.repaint();
            }
            this.scrollButtonLeft.show((buttonPanel.xoffset != 0 && !this.buttonsRightAligned) || (buttonPanel.xoffset < n && this.buttonsRightAligned));
            this.scrollButtonRight.show((buttonPanel.xoffset != 0 && this.buttonsRightAligned) || (buttonPanel.xoffset > -n && !this.buttonsRightAligned));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.offImage != null) {
            this.offImage.flush();
        }
        this.offImage = this.createImage(width, height);
        (this.offg = this.offImage.getGraphics()).setColor(this.getBackground());
        this.offg.fillRect(0, 0, width, height);
        if (!this.buttonsRightAligned) {
            this.buttonPanel.drawBGImage(this.offg, 0, 0, this.size().width, this.size().height);
            graphics.drawImage(this.offImage, 0, 0, this);
        }
        else {
            this.buttonPanelRight.drawBGImage(this.offg, 0, 0, this.size().width, this.size().height);
            graphics.drawImage(this.offImage, 0, 0, this);
        }
        super.paint(graphics);
    }
}

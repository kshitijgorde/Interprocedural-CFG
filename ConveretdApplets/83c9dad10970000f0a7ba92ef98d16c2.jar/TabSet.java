import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabSet extends Canvas implements MouseListener
{
    final int tabEdge = 9;
    Image offImage;
    Graphics offGraphics;
    ActionListener main;
    int currentTab;
    int width;
    int height;
    Panel[] panelList;
    String[] labelList;
    Color tabColor;
    Color selTabColor;
    
    public TabSet(final ActionListener main, final Color tabColor, final Color selTabColor) {
        this.panelList = new Panel[0];
        this.labelList = new String[0];
        this.main = main;
        this.tabColor = tabColor;
        this.selTabColor = selTabColor;
        this.addMouseListener(this);
    }
    
    public void setTab(final int currentTab) {
        this.currentTab = currentTab;
    }
    
    public int getTab() {
        return this.currentTab;
    }
    
    public void addTab(final String s, final Panel panel) {
        final Panel[] panelList = new Panel[this.panelList.length + 1];
        final String[] labelList = new String[this.panelList.length + 1];
        for (int i = 0; i < this.panelList.length; ++i) {
            panelList[i] = this.panelList[i];
            labelList[i] = this.labelList[i];
        }
        panelList[this.panelList.length] = panel;
        labelList[this.panelList.length] = s;
        this.panelList = panelList;
        this.labelList = labelList;
    }
    
    void initialise() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.offImage = this.createImage(this.width, this.height);
        this.offGraphics = this.offImage.getGraphics();
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImage == null) {
            this.initialise();
        }
        this.offGraphics.setColor(this.getBackground());
        this.offGraphics.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < this.panelList.length; ++i) {
            if (i != this.currentTab) {
                this.drawTab(i, false);
            }
        }
        this.drawTab(this.currentTab, true);
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    void drawTab(final int n, final boolean b) {
        final int n2 = (this.width - 9) / this.panelList.length;
        final int[] array = { n * n2 + n2 + 9, n * n2 + n2, n * n2 + 9, n * n2 };
        final int[] array2 = { this.height - 1, 0, 0, this.height - 1 };
        this.offGraphics.setColor(b ? this.selTabColor : this.tabColor);
        this.offGraphics.fillPolygon(array, array2, 4);
        this.offGraphics.drawPolygon(array, array2, 4);
        this.offGraphics.setColor(this.getForeground());
        if (b) {
            this.offGraphics.drawPolyline(array, array2, 4);
        }
        else {
            this.offGraphics.drawPolygon(array, array2, 4);
        }
        this.offGraphics.drawString(this.labelList[n], n * n2 + 9, this.height - 3);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        int currentTab = (2 * mouseEvent.getX() - 9) / (2 * ((this.width - 9) / this.panelList.length));
        if (currentTab >= this.panelList.length) {
            currentTab = this.panelList.length - 1;
        }
        if (currentTab < 0) {
            currentTab = 0;
        }
        this.currentTab = currentTab;
        for (int i = 0; i < this.panelList.length; ++i) {
            this.panelList[i].setVisible(i == this.currentTab);
        }
        this.repaint();
        mouseEvent.consume();
        if (this.main != null) {
            this.main.actionPerformed(new ActionEvent(this, 1001, this.labelList[this.currentTab]));
        }
    }
}

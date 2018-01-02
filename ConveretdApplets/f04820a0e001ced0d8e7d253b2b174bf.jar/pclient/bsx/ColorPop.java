// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.Button;
import pclient.bsc.BaseChat;
import pclient.bsc.CommonInter;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class ColorPop extends Frame implements ActionListener, CommonInter, ColorListener
{
    private static int[][] colorGrid;
    private BaseChat pChat;
    private Button moreButton;
    
    public void process(final int n, final String[] array) {
    }
    
    public void setPara(final BaseChat pChat) {
        this.pChat = pChat;
        this.setTitle(this.pChat.paraConf.title());
        this.setSize(320, 200);
        this.buildGUI();
        this.enableEvents(64L);
        WindowUtil.center(this);
    }
    
    public void restart() {
        this.setVisible(true);
        this.toFront();
        this.setVisible(true);
    }
    
    public void processColor(final int n, final int n2, final int n3) {
        this.pChat.changeUserColor(new Color(n, n2, n3));
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.moreButton) {
            this.pChat.fireControlPanel(10, null);
        }
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        final int id = windowEvent.getID();
        if (id != 205) {
            if (id != 206) {
                if (id == 201) {
                    this.setVisible(false);
                }
            }
        }
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        final Panel buildGrid = this.buildGrid(this);
        final MarginPanel marginPanel = new MarginPanel(1, 12, 1, 12);
        marginPanel.setLayout(new FlowLayout(1));
        marginPanel.add(buildGrid);
        final Label label = new Label(" ");
        (this.moreButton = new Button("More")).addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(1));
        panel.add(this.moreButton);
        this.add("North", label);
        this.add("Center", marginPanel);
        this.add("South", panel);
    }
    
    private Panel buildGrid(final ColorListener colorListener) {
        final int n = 2;
        final int n2 = 5;
        final Dimension dimension = new Dimension(24, 24);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(n, n2, 5, 5));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                final int n3 = i * n2 + j;
                final ColorCanvas colorCanvas = new ColorCanvas(ColorPop.colorGrid[n3][0], ColorPop.colorGrid[n3][1], ColorPop.colorGrid[n3][2], dimension);
                panel.add(colorCanvas);
                if (colorListener != null) {
                    colorCanvas.addListener(colorListener);
                }
            }
        }
        return panel;
    }
    
    static {
        ColorPop.colorGrid = new int[][] { { 0, 0, 0 }, { 255, 0, 0 }, { 0, 124, 0 }, { 128, 124, 0 }, { 0, 0, 255 }, { 128, 0, 128 }, { 0, 124, 128 }, { 255, 124, 0 }, { 255, 0, 128 }, { 128, 124, 128 } };
    }
}

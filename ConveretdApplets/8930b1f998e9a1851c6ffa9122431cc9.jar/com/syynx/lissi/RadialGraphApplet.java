// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi;

import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Graphics;
import prefuse.data.Tuple;
import prefuse.controls.Control;
import prefuse.visual.NodeItem;
import prefuse.visual.EdgeItem;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import prefuse.controls.ControlAdapter;
import javax.swing.JPanel;
import java.awt.Dimension;
import prefuse.data.Graph;
import java.net.URLEncoder;
import prefuse.data.io.GraphMLReader;
import java.io.InputStream;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import com.syynx.lissi.metapanel.FloatWin;
import com.syynx.lissi.metapanel.PersonDataPanel;
import com.syynx.lissi.menus.FisheyeMenu;
import prefuse.util.ui.JPrefuseApplet;

public class RadialGraphApplet extends JPrefuseApplet implements Colors
{
    RadialGraphView gview;
    FisheyeMenu gmenu;
    PersonDataPanel pdpan;
    FloatWin fwin;
    BGPanel panel;
    SettingsPanel setpan;
    Image imgBg;
    
    public void init() {
        try {
            final MediaTracker m = new MediaTracker(this);
            final InputStream is = this.getClass().getResourceAsStream("background.jpg");
            final BufferedInputStream bis = new BufferedInputStream(is);
            final byte[] byBuf = new byte[20000];
            final int byteRead = bis.read(byBuf, 0, 20000);
            m.addImage(this.imgBg = Toolkit.getDefaultToolkit().createImage(byBuf), 0);
            m.waitForAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.setContentPane(this.graphPanel(this.getParameter("person"), "name", this.getSize()));
    }
    
    public void setMinWeight(final int w) {
        this.gview.visFilter.setMinWeight(w);
        this.gview.run("visibility");
        this.gmenu.run("distort");
    }
    
    public void setMinPubCount(final int c) {
        this.gview.visFilter.setMinPubCount(c);
        this.gview.run("visibility");
        this.gmenu.run("distort");
    }
    
    public void setBAngle(final double r) {
        this.gview.setBAngle(r);
    }
    
    public void setMinCount(final int c) {
        this.gview.visFilter.setMinCount(c);
        this.gview.run("visibility");
        this.gmenu.run("distort");
    }
    
    public void load(final String person, final String id) {
        Graph g = null;
        try {
            g = new GraphMLReader().readGraph(String.valueOf(this.getParameter("PersonNetURL")) + URLEncoder.encode(id, "UTF-8"));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(id);
        this.gview.init(g, "name", this.getSize(), id);
        this.gmenu.init(this.gview.getVisualization().getGroup("tree.nodes"));
        this.panel.setPerson(String.valueOf(this.fwin.lastname) + ", " + this.fwin.firstname);
    }
    
    public JPanel graphPanel(final String id, final String label, final Dimension dim) {
        Graph g = null;
        try {
            g = new GraphMLReader().readGraph(String.valueOf(this.getParameter("PersonNetURL")) + URLEncoder.encode(id, "UTF-8"));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        (this.setpan = new SettingsPanel(this)).setBounds(0, -5, 600, 55);
        this.panel = new BGPanel(this.createImage(this.getWidth(), this.getHeight()));
        (this.fwin = new FloatWin(this)).setOpaque(false);
        this.fwin.setBounds(0, 530, 675, 400);
        (this.gview = new RadialGraphView(g, label, dim, this.fwin, id)).setBounds(0, 45, 527, 460);
        this.gview.setBackgroundImage(this.imgBg, true, false);
        this.gview.setOpaque(false);
        (this.gmenu = new FisheyeMenu(this)).init(this.gview.getVisualization().getGroup("tree.nodes"));
        this.gmenu.setBounds(527, 0, 148, 650);
        this.gmenu.setOpaque(false);
        this.panel.add(this.gmenu);
        this.panel.add(this.fwin);
        this.panel.add(this.setpan);
        this.panel.add(this.gview);
        this.panel.setPerson(String.valueOf(this.fwin.lastname) + ", " + this.fwin.firstname);
        this.gview.addControlListener(new ControlAdapter() {
            public void itemClicked(final VisualItem item, final MouseEvent e) {
                if (item instanceof EdgeItem) {
                    final NodeItem n1 = ((EdgeItem)item).getSourceItem();
                    final NodeItem n2 = ((EdgeItem)item).getTargetItem();
                    RadialGraphApplet.this.fwin.displayData(String.valueOf(n1.getString("name")) + " - " + n2.getString("name"), item.getString("id"), "0", RadialGraphView.countEdges((NodeItem)item));
                }
                else if (item.canGetString(label)) {
                    RadialGraphApplet.this.fwin.displayData(item.getString(label), item.getString("id"), item.getString("doccount"), RadialGraphView.countEdges((NodeItem)item));
                }
            }
        });
        return this.panel;
    }
    
    public void setHover(final Tuple t) {
        this.gview.setHover(t);
    }
    
    public void clearHover(final Tuple t) {
        this.gview.clearHover(t);
    }
    
    public void setFocus(final Tuple t) {
        this.gview.setFocus(t);
    }
    
    public static class BGPanel extends JPanel
    {
        private Image offscreen;
        private Graphics og;
        private String person;
        
        public BGPanel(final Image buffer) {
            super(null, true);
            this.setOpaque(false);
            this.offscreen = buffer;
        }
        
        public void setPerson(final String name) {
            this.person = name;
            this.repaint();
        }
        
        public void paint(final Graphics g) {
            (this.og = this.offscreen.getGraphics()).setColor(RadialGraphApplet.colorMainBG);
            this.og.fillRect(0, 0, this.getWidth(), this.getHeight());
            super.paint(this.og);
            g.drawImage(this.offscreen, 0, 0, null);
        }
        
        public void update(final Graphics g) {
            this.paint(g);
        }
    }
}

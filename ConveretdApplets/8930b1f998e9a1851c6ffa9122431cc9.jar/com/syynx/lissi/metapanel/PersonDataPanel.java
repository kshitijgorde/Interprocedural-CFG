// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.metapanel;

import java.awt.Graphics;
import java.net.URL;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import javax.swing.JTabbedPane;
import com.syynx.lissi.Colors;
import javax.swing.JPanel;

public class PersonDataPanel extends JPanel implements Colors
{
    FloatWin owner;
    JTabbedPane stp;
    PersonDataCanvas canvasPersonData;
    PublicationsCanvas canvasPublications;
    
    public PersonDataPanel(final FloatWin owner) {
        super(null, true);
        this.owner = owner;
        this.stp = new JTabbedPane();
        (this.canvasPersonData = new PersonDataCanvas(this)).addMouseListener(owner);
        this.canvasPersonData.addMouseMotionListener(owner);
        (this.canvasPublications = new PublicationsCanvas(this)).addMouseListener(owner);
        this.canvasPublications.addMouseMotionListener(owner);
        this.add(this.canvasPublications);
        this.add(this.canvasPersonData);
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        super.setBounds(x, y, width, height);
        this.canvasPublications.setBounds(0, 0, width, height);
        this.canvasPersonData.setBounds(0, 0, width, height);
    }
    
    public PersonData getData() {
        return this.owner.data;
    }
    
    public void showDocument(final URL url) {
        this.owner.showDocument(url);
    }
    
    public void load(final String person, final String id) {
        this.owner.load(person, id);
    }
    
    public String getID() {
        return this.owner.id;
    }
    
    public String getPerson() {
        return this.owner.person;
    }
    
    public void invalidate() {
        super.invalidate();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void reset() {
        this.canvasPersonData.reset();
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URLConnection;
import java.awt.Component;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Panel;

public class ControlPanel extends Panel
{
    private teletext m_parent;
    private ListScroll m_pageListScrolling;
    private Vector m_listOfPages;
    private Dimension m_prefSize;
    private Choice m_choiceRolling;
    public Image[] m_imgBgListText;
    public Image[] m_imgNext;
    public Image[] m_imgPrev;
    private MyButton m_btnNext;
    private MyButton m_btnPrev;
    
    public void init() {
    }
    
    public void lateInit() {
        try {
            this.m_parent.m_tracker.waitForAll();
        }
        catch (InterruptedException e2) {
            System.out.println("Can't retrieve images...");
        }
        this.m_btnNext = new MyButton(this.m_imgNext, this, this.m_imgNext[0].getWidth(this), this.m_imgNext[0].getHeight(this), 100, 350, true);
        this.m_btnPrev = new MyButton(this.m_imgPrev, this, this.m_imgPrev[0].getWidth(this), this.m_imgPrev[0].getHeight(this), 50, 350, true);
        (this.m_choiceRolling = new Choice()).addItem("0");
        this.m_choiceRolling.addItem("1");
        this.m_choiceRolling.addItem("2");
        this.m_choiceRolling.addItem("3");
        this.m_choiceRolling.addItem("4");
        (this.m_pageListScrolling = new ListScroll(this)).setPreferredSize(new Dimension(this.preferredSize().width, this.preferredSize().height - 10 - this.m_btnNext.preferredSize().height));
        this.m_listOfPages = new Vector(10, 1);
        final StringBuffer line = new StringBuffer();
        URL hp = null;
        final URLConnection hpCon = null;
        try {
            hp = new URL(this.m_parent.getCodeBase() + this.m_parent.m_strDataPathStr + "/" + this.m_parent.m_strIndexStr);
        }
        catch (MalformedURLException e3) {
            System.out.println("bad URL: " + this.m_parent.getCodeBase() + this.m_parent.m_strDataPathStr + "/" + this.m_parent.m_strIndexStr);
        }
        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(hp.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                final int pos = inputLine.lastIndexOf("=");
                if (pos != -1) {
                    MyText newLink = new MyText(this, this.m_imgBgListText, inputLine.substring(0, pos) + " " + inputLine.substring(pos + 1), 10, false, 15, (this.m_listOfPages.size() + 1) * 27, 150, 25);
                    this.m_listOfPages.addElement(newLink);
                    newLink = null;
                    line.setLength(0);
                }
            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
        this.m_pageListScrolling.init(this.m_listOfPages);
        this.add(this.m_pageListScrolling);
        this.add(this.m_btnPrev);
        this.add(this.m_choiceRolling);
        this.add(this.m_btnNext);
    }
    
    public void paint(final Graphics g) {
        if (!this.m_parent.m_AllLoadedCtrl || !this.m_parent.m_EndPresentazione) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 15));
            g.drawString("Loading Images....", 30, 70);
        }
        else {
            g.fillRect(0, this.preferredSize().height - 10 - this.m_btnNext.preferredSize().height, this.preferredSize().width, 10);
            g.setColor(new Color(185, 180, 170));
            g.drawRect(1, this.preferredSize().height - 5 - this.m_btnNext.preferredSize().height, this.preferredSize().width - 2, 4);
            g.setColor(Color.black);
        }
    }
    
    public void pageChange(final int page, final int rolling, final int ofRolling, final byte[] rollingVec) {
        this.m_choiceRolling.removeAll();
        for (int i = 0; i < ofRolling; ++i) {
            this.m_choiceRolling.addItem(String.valueOf(rollingVec[i]));
        }
        this.m_choiceRolling.select(rolling + "");
    }
    
    public void setControls(final int page, final int rolling, final int ofRolling, final byte[] rollingVec) {
        if (rolling == 0) {
            this.m_btnPrev.setEnabled(false);
            this.m_btnNext.setEnabled(false);
        }
        else {
            this.m_btnPrev.setEnabled(rolling > 1);
            this.m_btnNext.setEnabled(rolling < this.m_parent.getRollingOf());
        }
        this.m_choiceRolling.select(rolling + "");
    }
    
    public void requestPage(final String PageNuber) {
        this.m_parent.pageRequest(PageNuber, "0");
    }
    
    public void setPreferredSize(final Dimension prefSize) {
        this.m_prefSize = prefSize;
    }
    
    public Dimension minimumSize() {
        return this.m_prefSize;
    }
    
    public Dimension preferredSize() {
        return this.m_prefSize;
    }
    
    public void resetScroll() {
        if (this.m_pageListScrolling != null) {
            this.m_pageListScrolling.resetScroll();
        }
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        if (evt.target == this.m_btnNext) {
            this.m_parent.nextRolling();
        }
        if (evt.target == this.m_btnPrev) {
            this.m_parent.prevRolling();
        }
        return true;
    }
    
    public boolean action(final Event evt, final Object what) {
        if (evt.target == this.m_choiceRolling) {
            this.m_parent.pageRequest("" + this.m_parent.getPage(), evt.arg.toString());
            return true;
        }
        return false;
    }
    
    public ControlPanel(final teletext parent) {
        this.m_pageListScrolling = null;
        this.m_imgBgListText = new Image[2];
        this.m_imgNext = new Image[4];
        this.m_imgPrev = new Image[4];
        this.m_parent = parent;
    }
}

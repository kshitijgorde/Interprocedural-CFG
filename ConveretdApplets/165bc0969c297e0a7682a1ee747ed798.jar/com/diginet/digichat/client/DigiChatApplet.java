// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.MenuItem;
import java.awt.Event;
import com.diginet.digichat.util.Informer;
import com.diginet.digichat.util.Finder;
import java.util.Enumeration;
import com.diginet.digichat.awt.DragListener;
import java.awt.Point;
import java.awt.Rectangle;
import com.diginet.digichat.awt.dw;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.f;
import java.awt.Color;
import com.diginet.digichat.common.d;
import java.io.IOException;
import com.esial.util.c;
import java.io.InputStream;
import com.esial.internationalGUI.b;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import com.diginet.digichat.common.p;
import java.util.Vector;
import com.diginet.digichat.awt.MenuPopup;
import com.diginet.digichat.awt.MenuBar;
import java.awt.Component;
import com.diginet.digichat.awt.LayeredContainer;

public class DigiChatApplet extends DigiChatAppletAbstract implements LayeredContainer
{
    private int nX;
    private int nY;
    private Component cmpMain;
    private Component cmpDrag;
    private MenuBar mnuBar;
    private MenuPopup pupMenu;
    private Vector vecDrag;
    private q loginPanel;
    
    public void reset() {
        this.loginPanel.c();
    }
    
    public void init() {
        final URL codeBase = this.getCodeBase();
        DigiChatAppletAbstract.altHost = this.getParameter("altHost");
        p.a(String.valueOf(String.valueOf(p.e).concat(String.valueOf(","))).concat(String.valueOf(p.c)));
        DigiChatAppletAbstract.langName = this.getParameter("language");
        if (DigiChatAppletAbstract.langName != null) {
            try {
                try {
                    final c c = new c(new b(new GZIPInputStream(new URL(String.valueOf(String.valueOf(String.valueOf(codeBase.toString()).concat(String.valueOf("Resources/languages/"))).concat(String.valueOf(DigiChatAppletAbstract.langName))).concat(String.valueOf("z"))).openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final c c2 = new c(new b(new URL(String.valueOf(String.valueOf(codeBase.toString()).concat(String.valueOf("Resources/languages/"))).concat(String.valueOf(DigiChatAppletAbstract.langName))).openStream()));
                }
                catch (IOException ex2) {
                    final c c3 = new c(new b(new URL(String.valueOf(String.valueOf(codeBase.toString()).concat(String.valueOf("Resources/languages/"))).concat(String.valueOf(DigiChatAppletAbstract.langName))).openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        DigiChatAppletAbstract.OEM_DigiChat = c.a("DigiPlus");
        System.out.println(String.valueOf(String.valueOf(DigiChatAppletAbstract.OEM_DigiChat).concat(String.valueOf(" "))).concat(String.valueOf(d.a())));
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("textcolor");
        final String parameter3 = this.getParameter("themeID");
        final String parameter4 = this.getParameter("preferredPort");
        final String parameter5 = this.getParameter("ports");
        final String parameter6 = this.getParameter("COPPA1");
        final String parameter7 = this.getParameter("COPPA2");
        try {
            if (parameter != null) {
                background = f.a(parameter);
            }
        }
        catch (NumberFormatException ex3) {}
        try {
            if (parameter2 != null) {
                foreground = f.a(parameter2);
            }
        }
        catch (NumberFormatException ex4) {}
        try {
            if (parameter3 != null) {
                DigiChatAppletAbstract.preferredTheme = Integer.parseInt(parameter3);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter4 != null) {
                DigiChatAppletAbstract.preferredPort = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex6) {}
        if (parameter5 != null) {
            p.a();
            p.a(parameter5);
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(null);
        final String parameter8 = this.getParameter("embedded");
        if (parameter8 != null && parameter8.equalsIgnoreCase("true")) {
            DigiChatAppletAbstract.embedded = true;
        }
        try {
            final String parameter9 = this.getParameter(DigiChatAppletAbstract.embedded ? "height" : "WindowHeight");
            if (parameter9 != null) {
                DigiChatAppletAbstract.initialWindowHeight = Integer.parseInt(parameter9);
            }
            final String parameter10 = this.getParameter(DigiChatAppletAbstract.embedded ? "width" : "WindowWidth");
            if (parameter10 != null) {
                DigiChatAppletAbstract.initialWindowWidth = Integer.parseInt(parameter10);
            }
        }
        catch (NumberFormatException ex7) {}
        DigiChatAppletAbstract.applet = this;
        super.chatUser = new g(this);
        final String parameter11 = this.getParameter("RoomsTab");
        if (parameter11 != null && parameter11.equalsIgnoreCase("false")) {
            super.chatUser.d(false);
        }
        final String parameter12 = this.getParameter("UsersTab");
        if (parameter12 != null && parameter12.equalsIgnoreCase("false")) {
            super.chatUser.e(false);
        }
        final String parameter13 = this.getParameter("BuddiesTab");
        if (parameter13 != null && parameter13.equalsIgnoreCase("true")) {
            super.chatUser.b(true);
        }
        final String parameter14 = this.getParameter("suppressUserList");
        if (parameter14 != null && parameter14.equalsIgnoreCase("true")) {
            super.chatUser.setSupp(true);
        }
        dw.a(this.getParameter("font1"));
        dw.b(this.getParameter("font1b"));
        dw.c(this.getParameter("font2"));
        dw.d(this.getParameter("font2b"));
        this.loginPanel = new q(super.chatUser, super.chatUser.x() == null, false, false, super.chatUser.br == -999, parameter6, parameter7, null);
        DigiChatAppletAbstract.login = this.loginPanel;
        this.setMain(this.loginPanel);
    }
    
    public void stop() {
        if (super.chatUser != null && (DigiChatAppletAbstract.embedded || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.chatUser.o();
        }
    }
    
    public DigiChatApplet() {
        this.mnuBar = null;
        this.pupMenu = null;
        this.vecDrag = new Vector();
        final Component component = null;
        this.cmpDrag = component;
        this.cmpMain = component;
    }
    
    private void blocking() {
        int height;
        if (this.mnuBar == null) {
            height = 0;
        }
        else {
            this.mnuBar.setBounds(0, 0, this.size().width, height = this.mnuBar.size().height);
        }
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component;
            if ((component = components[i]) == this.cmpMain) {
                final Rectangle bounds = this.getBounds();
                component.setBounds(0, height, bounds.width, bounds.height - height);
                component.validate();
            }
            else if (component != this.mnuBar) {
                final Point location = component.getLocation();
                component.setLocation(location.x, location.y + height);
            }
        }
    }
    
    public Rectangle getArea() {
        return new Rectangle(this.size());
    }
    
    public void setMain(final Component cmpMain) {
        if (this.cmpMain != null) {
            this.remove(this.cmpMain);
        }
        this.add(this.cmpMain = cmpMain, this.getComponentCount() - ((this.mnuBar != null) ? 1 : 0));
        this.blocking();
    }
    
    public void setBar(final MenuBar mnuBar) {
        if (this.mnuBar != null) {
            this.remove(this.mnuBar);
        }
        this.add(this.mnuBar = mnuBar);
        this.blocking();
    }
    
    public MenuBar getBar() {
        return this.mnuBar;
    }
    
    public void removeBar() {
        if (this.mnuBar != null) {
            this.remove(this.mnuBar);
            this.mnuBar = null;
            this.blocking();
        }
    }
    
    public void setPopup(final MenuPopup pupMenu) {
        if (this.pupMenu != null) {
            this.remove(this.pupMenu);
        }
        this.pupMenu = pupMenu;
    }
    
    public void removePopup() {
        this.pupMenu = null;
    }
    
    public void addLayer(final Component component, final int n, final int n2) {
        this.add(component, 0);
        component.setLocation(n, n2 + ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
    }
    
    public void removeLayer(final Component component) {
        this.remove(component);
    }
    
    public void addDragListener(final DragListener dragListener) {
        if (this.vecDrag.indexOf(dragListener) < 0) {
            this.vecDrag.addElement(dragListener);
        }
    }
    
    public void removeDragListener(final DragListener dragListener) {
        this.vecDrag.removeElement(dragListener);
    }
    
    public void dragLayer(final Component cmpDrag, final int n, final int n2) {
        this.cmpDrag = cmpDrag;
        final Point location = cmpDrag.getLocation();
        this.nX = location.x + n;
        this.nY = location.y + n2;
        final Enumeration<DragListener> elements = this.vecDrag.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().startDrag(this.cmpDrag, location.x, location.y - ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
        }
    }
    
    public void setInfo(final Component component, final String s) {
        final Informer informer;
        if ((informer = Finder.findInformer(this.cmpMain)) != null) {
            informer.setInfo(component, s);
        }
    }
    
    public void setDirect(final boolean direct) {
        if (this.mnuBar != null) {
            this.mnuBar.setDirect(direct);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.cmpMain != null && event.target instanceof MenuItem) {
            return this.cmpMain.handleEvent(event);
        }
        if (this.cmpDrag != null) {
            switch (event.id) {
                case 502:
                case 505: {
                    final Point location = this.cmpDrag.getLocation();
                    final Enumeration<DragListener> elements = this.vecDrag.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().endDrag(this.cmpDrag, location.x, location.y - ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
                    }
                    this.cmpDrag = null;
                    return true;
                }
                case 506: {
                    final Point location2 = this.cmpDrag.getLocation();
                    this.cmpDrag.setLocation(location2.x + event.x - this.nX, location2.y + event.y - this.nY);
                    this.nX = event.x;
                    this.nY = event.y;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}

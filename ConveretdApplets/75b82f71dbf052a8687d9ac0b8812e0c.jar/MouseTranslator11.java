import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Menu;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class MouseTranslator11 extends MouseTranslator
{
    private static final boolean isMac;
    private static final boolean isWin;
    private CheckboxMenuItem cbSpots;
    private CheckboxMenuItem cbTargets;
    private CheckboxMenuItem cbPopup;
    
    static {
        isMac = System.getProperty("os.name").startsWith("Mac");
        isWin = System.getProperty("os.name").startsWith("Win");
    }
    
    MouseTranslator11(final Controller c) {
        super(c);
    }
    
    void handleEvent(final Event e) {
        if (e.isConsumed()) {
            return;
        }
        try {
            switch (e.id) {
                case 1001: {
                    final String label = ((MenuItem)e.target).getLabel();
                    if (label.equals("Spin")) {
                        this.m_controller.requestControl(new SpinTranslator(this.m_controller, 5));
                        break;
                    }
                    if (label.equals("Go To Initial View")) {
                        this.m_controller.requestControl(new LocationTranslator(this.m_controller));
                        break;
                    }
                    if (label.equals("Zoom In")) {
                        final float[] viewpoint;
                        final float[] vp = viewpoint = this.m_controller.getHost().getViewpoint();
                        final int n = 3;
                        viewpoint[n] *= 1.5f;
                        this.m_controller.requestControl(new LocationTranslator(this.m_controller, vp));
                        break;
                    }
                    if (label.equals("Zoom Out")) {
                        final float[] viewpoint2;
                        final float[] vp = viewpoint2 = this.m_controller.getHost().getViewpoint();
                        final int n2 = 3;
                        viewpoint2[n2] /= 1.5f;
                        this.m_controller.requestControl(new LocationTranslator(this.m_controller, vp));
                        break;
                    }
                    if (label.equals("Show Toolbar")) {
                        this.m_controller.getHost().showToolbar = true;
                        this.m_controller.getHost().dewarp();
                        break;
                    }
                    if (label.equals("Hide Toolbar")) {
                        this.m_controller.getHost().showToolbar = false;
                        this.m_controller.getHost().dewarp();
                        break;
                    }
                    if (label.equals("Help")) {
                        this.m_controller.getHost().showHelp();
                        break;
                    }
                    break;
                }
                case 501: {
                    final IpixViewer host = this.m_controller.host;
                    host.state |= IpixViewer.kUserInteracting;
                    if (this.isPopupTrigger(e)) {
                        if (!this.m_controller.requestControl(null)) {
                            break;
                        }
                        if (this.m_controller.getHost().contextualMenuOn) {
                            final PopupMenu menu = (PopupMenu)this.createMenu();
                            this.m_controller.getHost().add(menu);
                            menu.show(this.m_controller.getHost(), e.x, e.y);
                            break;
                        }
                        break;
                    }
                    else {
                        if ((e.modifiers & 0x8) != 0x0) {
                            break;
                        }
                        if ((e.modifiers & 0x4) != 0x0) {
                            break;
                        }
                        super.handleEvent(e);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (this.m_controller.isActive(this) || !this.isPopupTrigger(e)) {
                        super.handleEvent(e);
                        break;
                    }
                    if (!this.m_controller.requestControl(null)) {
                        break;
                    }
                    if (this.m_controller.getHost().contextualMenuOn) {
                        final PopupMenu menu = (PopupMenu)this.createMenu();
                        this.m_controller.getHost().add(menu);
                        menu.show(this.m_controller.getHost(), e.x, e.y);
                        break;
                    }
                    break;
                }
                default: {
                    super.handleEvent(e);
                    break;
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    protected boolean isPopupTrigger(final java.awt.Event e) {
        return (MouseTranslator11.isWin && e.id == 502 && (e.modifiers & 0x4) == 0x4) || (MouseTranslator11.isMac && e.id == 501 && e.controlDown());
    }
    
    protected Menu createMenu() {
        final PopupMenu menu = new PopupMenu();
        MenuItem item = new MenuItem("Go To Initial View");
        menu.add(item);
        if (this.m_controller.getHost().spin) {
            item = new MenuItem("Spin");
            item.setEnabled(this.m_controller.getHost().getDecoder() == null);
            menu.add(item);
        }
        menu.addSeparator();
        if (this.m_controller.getHost().showToolbar) {
            item = new MenuItem("Hide Toolbar");
        }
        else {
            item = new MenuItem("Show Toolbar");
        }
        menu.add(item);
        menu.addSeparator();
        if (this.m_controller.getHost().getPipeline().getSource().getProperty("spts") != null) {
            final CheckboxMenuProcessor eventProcessor = new CheckboxMenuProcessor(this.m_controller);
            this.cbSpots = new CheckboxMenuItem("Enable Hot Spots", this.m_controller.getHost().hotspotsActive);
            this.cbTargets = new CheckboxMenuItem("Show Hot Spot Targets", this.m_controller.getHost().targetsOn);
            this.cbPopup = new CheckboxMenuItem("Enable Popup Text", this.m_controller.getHost().popupTextOn);
            this.cbSpots.addItemListener(eventProcessor);
            this.cbTargets.addItemListener(eventProcessor);
            this.cbPopup.addItemListener(eventProcessor);
            menu.add(this.cbSpots);
            menu.add(this.cbTargets);
            menu.add(this.cbPopup);
            menu.addSeparator();
        }
        item = new MenuItem("Help");
        menu.add(item);
        return menu;
    }
    
    class CheckboxMenuProcessor implements ItemListener
    {
        Controller m_controller;
        
        CheckboxMenuProcessor(final Controller c) {
            this.m_controller = null;
            this.m_controller = c;
        }
        
        public void itemStateChanged(final ItemEvent e) {
            ((CheckboxMenuItem)e.getSource()).setState(((CheckboxMenuItem)e.getSource()).getState());
            final String label = ((CheckboxMenuItem)e.getSource()).getLabel();
            if (label.equals("Enable Hot Spots")) {
                this.m_controller.getHost().hotspotsActive = ((CheckboxMenuItem)e.getSource()).getState();
            }
            if (label.equals("Show Hot Spot Targets")) {
                this.m_controller.getHost().targetsOn = ((CheckboxMenuItem)e.getSource()).getState();
            }
            if (label.equals("Enable Popup Text")) {
                this.m_controller.getHost().popupTextOn = ((CheckboxMenuItem)e.getSource()).getState();
            }
        }
    }
}

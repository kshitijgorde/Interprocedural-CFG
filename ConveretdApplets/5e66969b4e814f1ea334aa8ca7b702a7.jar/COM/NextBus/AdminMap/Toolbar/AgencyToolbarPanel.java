// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap.Toolbar;

import java.util.List;
import COM.NextBus.Applets.Spacer;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.event.ItemListener;
import COM.NextBus.AdminMap.a;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import COM.NextBus.AdminMap.O;
import COM.NextBus.AdminMap.VerticalFlowPanel;
import COM.NextBus.Applets.d;
import java.awt.Component;
import COM.NextBus.Applets.LabeledChoice;
import COM.NextBus.AdminMap.r;
import java.awt.Panel;

public class AgencyToolbarPanel extends Panel
{
    private static final long serialVersionUID = -6926071025471594153L;
    private r _toolbarModel;
    private LabeledChoice _configChooser;
    private Component _helpBtn;
    private Component _routesBtn;
    public Component _vehiclesBtn;
    private Component _eventsBtn;
    private Component _propsBtn;
    private Component _zoomOutBtn;
    private Component _zoomInBtn;
    private d _lnf;
    public VerticalFlowPanel mainPan;
    
    public AgencyToolbarPanel(final r toolbarModel, final O lnf, final Component helpBtn, final Component routesBtn, final Component vehiclesBtn, final Component eventsBtn, final Component propsBtn, final Component zoomOutBtn, final Component zoomInBtn) {
        super(new FlowLayout(1, 0, 0));
        this.mainPan = new VerticalFlowPanel(2);
        this._toolbarModel = toolbarModel;
        this._lnf = lnf;
        this._helpBtn = helpBtn;
        this._routesBtn = routesBtn;
        this._vehiclesBtn = vehiclesBtn;
        this._eventsBtn = eventsBtn;
        this._propsBtn = propsBtn;
        this._zoomOutBtn = zoomOutBtn;
        this._zoomInBtn = zoomInBtn;
        this.setBackground(this._lnf.x());
        toolbarModel.a(new e(this));
        this.mainPan.add(a(2));
        final VerticalFlowPanel mainPan = this.mainPan;
        final VerticalFlowPanel verticalFlowPanel;
        (verticalFlowPanel = new VerticalFlowPanel(2)).a(this._zoomOutBtn);
        verticalFlowPanel.a(this._zoomInBtn);
        mainPan.add(verticalFlowPanel);
        this.mainPan.add(a(20));
        final VerticalFlowPanel mainPan2 = this.mainPan;
        final Panel panel = new Panel(new BorderLayout());
        (this._configChooser = new LabeledChoice(a.b("Select Config") + ":", 0)).a(new COM.NextBus.AdminMap.Toolbar.d(this));
        this._lnf.a(this._configChooser);
        this.a();
        panel.add(this._configChooser, "Center");
        final Button button = new Button(a.b("Save Config") + "...");
        this._lnf.a(button);
        button.addActionListener(new h(this));
        final Panel panel2;
        (panel2 = new Panel(new BorderLayout())).add(button, "South");
        panel.add(panel2, "East");
        mainPan2.add(panel);
        this.mainPan.add(a(14));
        final VerticalFlowPanel mainPan3 = this.mainPan;
        final Panel panel3 = new Panel();
        final VerticalFlowPanel verticalFlowPanel2;
        (verticalFlowPanel2 = new VerticalFlowPanel(2)).a(this._routesBtn);
        verticalFlowPanel2.a(this._vehiclesBtn);
        final VerticalFlowPanel verticalFlowPanel3;
        (verticalFlowPanel3 = new VerticalFlowPanel(2)).a(this._propsBtn);
        if (this._eventsBtn != null) {
            verticalFlowPanel3.a(this._eventsBtn);
        }
        panel3.add(verticalFlowPanel2);
        panel3.add(verticalFlowPanel3);
        mainPan3.add(panel3);
        this.mainPan.add(a(5));
        this.mainPan.add(this._helpBtn);
        this.mainPan.add(a(5));
        this.add(this.mainPan);
    }
    
    private static Component a(final int n) {
        return new Spacer(new Dimension(n, n));
    }
    
    public final void a(final String s) {
        this._configChooser.a(s);
    }
    
    public final void a() {
        this._configChooser.removeAll();
        final List d = this._toolbarModel.d();
        for (int n = 0; d != null && n < d.size(); ++n) {
            this._configChooser.b(d.get(n));
        }
    }
}

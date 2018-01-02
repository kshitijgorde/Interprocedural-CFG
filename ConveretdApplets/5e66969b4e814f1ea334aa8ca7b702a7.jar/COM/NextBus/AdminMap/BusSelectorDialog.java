// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.ResponseComponent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Collection;
import COM.NextBus.Applets.d;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Button;
import java.awt.Label;
import COM.NextBus.Applets.LabeledChoice;

public class BusSelectorDialog extends DialogCommon
{
    private static final long serialVersionUID = 5051323909935775223L;
    private final O _mapInfo;
    private t _agencyManager;
    private final x _mapUtils;
    private final LabeledChoice _vehicleIdChoice;
    private final Label _vehicleIdLabel;
    private final LabeledChoice _searchRouteChoice;
    private final LabeledChoice _searchJobOrRunChoice;
    private final LabeledChoice _assignRouteChoice;
    private final LabeledChoice _assignJobOrRunChoice;
    private final Button _findBusButton;
    private final Button _fitBusesButton;
    private final Button _showTrailsButton;
    private final Button _clearTrailsButton;
    private final Button _assignButton;
    public final Button _zoomToBusButton;
    private boolean _routeChoicePopulated;
    private boolean _jobsChoicePopulated;
    private final VerticalFlowPanel _selectVehiclePanel;
    private Component _alarmPanel;
    private static LabeledChoice a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    
    public BusSelectorDialog(final Frame frame, final String s, final Component component, final O mapInfo) {
        super(frame, s, component, mapInfo);
        this._routeChoicePopulated = false;
        this._jobsChoicePopulated = false;
        this._selectVehiclePanel = new VerticalFlowPanel();
        this._mapInfo = mapInfo;
        this.setBackground(this._mapInfo.x());
        BusSelectorDialog.b = "<" + COM.NextBus.AdminMap.a.b("no match") + ">";
        BusSelectorDialog.c = "<" + COM.NextBus.AdminMap.a.b("no run") + ">";
        BusSelectorDialog.d = COM.NextBus.AdminMap.a.b("Run");
        this._vehicleIdChoice = new LabeledChoice(COM.NextBus.AdminMap.a.b("Vehicle ID") + ":");
        this._searchRouteChoice = new LabeledChoice(COM.NextBus.AdminMap.a.b(this._mapInfo.k()) + ":");
        this._searchJobOrRunChoice = new LabeledChoice(COM.NextBus.AdminMap.a.b("Run") + ":");
        this._assignRouteChoice = new LabeledChoice(COM.NextBus.AdminMap.a.b("New " + this._mapInfo.k()) + ":");
        this._assignJobOrRunChoice = new LabeledChoice(COM.NextBus.AdminMap.a.b("New " + (this.h() ? BusSelectorDialog.d : this._mapInfo.l())) + ":");
        this._assignButton = new Button(COM.NextBus.AdminMap.a.b("Specify New " + (this.h() ? "Line/Run" : this._mapInfo.l())));
        this._vehicleIdLabel = new Label(BusSelectorDialog.b);
        this._zoomToBusButton = new Button(COM.NextBus.AdminMap.a.b("Zoom to Vehicle"));
        this._findBusButton = new Button(COM.NextBus.AdminMap.a.b("Find Vehicle"));
        this._fitBusesButton = new Button(COM.NextBus.AdminMap.a.b("Zoom to All Vehicles"));
        this._showTrailsButton = new Button(COM.NextBus.AdminMap.a.b("Show Trails"));
        this._clearTrailsButton = new Button(COM.NextBus.AdminMap.a.b("Clear Trails"));
        this._mapUtils = new x(this._mapInfo);
    }
    
    public final void b() {
        this._vehicleIdChoice.requestFocus();
    }
    
    private boolean h() {
        return this._mapInfo.B().startsWith("sf-muni");
    }
    
    public final void a(final boolean visible) {
        this._alarmPanel.setVisible(visible);
    }
    
    public final void a() {
        this._agencyManager = COM.NextBus.AdminMap.x.a(this._vehicleIdChoice.b());
        this.a(this._vehicleIdChoice, this._agencyManager.c().h(), null, BusSelectorDialog.b);
        if (!this._routeChoicePopulated) {
            if (this.h()) {
                this.a(this._searchRouteChoice, this._agencyManager.b(), null);
            }
            if (!this._mapInfo.O()) {
                this.a(this._assignRouteChoice, this._agencyManager.b(), null);
            }
            this._routeChoicePopulated = true;
        }
        if (!this._jobsChoicePopulated) {
            if (!this._mapInfo.O()) {
                this.b(this._assignJobOrRunChoice, null, null);
            }
            this._jobsChoicePopulated = true;
        }
        this._vehicleIdChoice.a(new D(this));
        this._searchRouteChoice.a(new K(this));
        this._searchJobOrRunChoice.a(new z(this));
        this._assignRouteChoice.a(new w(this));
        this._assignButton.addActionListener(new M(this));
        this._findBusButton.addActionListener(new B(this));
        this._zoomToBusButton.addActionListener(new l(this));
        this._showTrailsButton.addActionListener(new ag(this));
        this._clearTrailsButton.addActionListener(new i(this));
        this._fitBusesButton.addActionListener(new ah(this));
        this._mapInfo.a(this._assignRouteChoice);
        this._mapInfo.a(this._assignJobOrRunChoice);
        this._mapInfo.a(this._searchRouteChoice);
        this._mapInfo.a(this._searchJobOrRunChoice);
        this._mapInfo.a(this._assignButton);
        this._mapInfo.a(this._vehicleIdChoice);
        this._mapInfo.a(this._findBusButton);
        this._mapInfo.a(this._zoomToBusButton);
        this._mapInfo.a(this._fitBusesButton);
        this._mapInfo.a(this._showTrailsButton);
        this._mapInfo.a(this._clearTrailsButton);
        final VerticalFlowPanel verticalFlowPanel;
        (verticalFlowPanel = new VerticalFlowPanel()).setBackground(this._mapInfo.x());
        final Label label;
        (label = new Label(COM.NextBus.AdminMap.a.b("Select a Vehicle") + ":", 0)).setFont(O.L());
        this._selectVehiclePanel.add(label);
        final Panel alarmPanel = new Panel(new BorderLayout());
        (BusSelectorDialog.a = new LabeledChoice("Alarmed Vehicle ID:", 1)).setFont(O.m);
        BusSelectorDialog.a.setForeground(Color.red);
        BusSelectorDialog.a.a(new am(this));
        alarmPanel.add(BusSelectorDialog.a, "West");
        (this._alarmPanel = alarmPanel).setVisible(this._agencyManager.a);
        final VerticalFlowPanel verticalFlowPanel2 = new VerticalFlowPanel();
        if (this.h()) {
            new Label("Select a Vehicle by Line/Run:", 0);
            final Panel panel;
            (panel = new Panel()).add(this._vehicleIdChoice);
            panel.add(this._searchRouteChoice);
            panel.add(this._searchJobOrRunChoice);
            verticalFlowPanel2.a(panel);
        }
        else {
            verticalFlowPanel2.add(this._vehicleIdChoice);
            verticalFlowPanel2.a(this._alarmPanel);
        }
        this._selectVehiclePanel.a(verticalFlowPanel2);
        this._vehicleIdLabel.setFont(O.L());
        this._vehicleIdLabel.setAlignment(0);
        final VerticalFlowPanel verticalFlowPanel3 = new VerticalFlowPanel();
        final Panel panel2;
        (panel2 = new Panel()).add(this._findBusButton);
        panel2.add(this._zoomToBusButton);
        panel2.add(this._fitBusesButton);
        panel2.add(this._showTrailsButton);
        panel2.add(this._clearTrailsButton);
        final Label label2;
        (label2 = new Label(COM.NextBus.AdminMap.a.b("Perform Action on Selected Vehicle") + ":", 0)).setFont(O.L());
        verticalFlowPanel3.a(label2);
        verticalFlowPanel3.a(panel2);
        final VerticalFlowPanel verticalFlowPanel4 = new VerticalFlowPanel();
        if (!this._mapInfo.O()) {
            final Panel panel3;
            (panel3 = new Panel()).add(this._assignRouteChoice);
            panel3.add(this._assignJobOrRunChoice);
            panel3.add(this._assignButton);
            final Label label3;
            (label3 = new Label(COM.NextBus.AdminMap.a.b("Change Route/Run of Selected Vehicle") + ":", 0)).setFont(O.L());
            verticalFlowPanel4.a(label3);
            verticalFlowPanel4.a(panel3);
        }
        verticalFlowPanel.a(new EmptyBorderPanel(7, this._selectVehiclePanel));
        verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel3));
        if (!this._mapInfo.O()) {
            verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel4));
        }
        final Panel panel4;
        (panel4 = new Panel()).add(this._closeButton);
        this.add(verticalFlowPanel, "Center");
        this.add(panel4, "South");
        this.pack();
    }
    
    public final void a(final String s) {
        COM.NextBus.AdminMap.x.a(s);
        final aj h;
        if ((h = this._agencyManager.h(s)) != null) {
            this.b(h);
        }
    }
    
    public final void a(final aj aj) {
        final String d = aj.d();
        this._vehicleIdChoice.c(d);
        this._agencyManager = COM.NextBus.AdminMap.x.a(d);
        this.b(aj);
    }
    
    private void b(aj aj) {
        final String e = aj.e();
        final String d = aj.d();
        final G c;
        final String s = ((c = this._agencyManager.c(e)) == null) ? null : c.f();
        this.a(this._searchRouteChoice, s);
        this.a(this._assignRouteChoice, s);
        String a = null;
        String a2 = null;
        final String k;
        if ((k = aj.k()) != null) {
            a2 = COM.NextBus.Predictor2.a.a(k).a();
        }
        if (this.h()) {
            aj = (aj)this._agencyManager.b(e);
            try {
                a = this._mapInfo.e.a(this._agencyManager.l(), a2, this._mapInfo);
            }
            catch (ConnectionException ex) {}
        }
        else {
            aj = (aj)this._agencyManager.a(e);
            a = a2;
        }
        if (d != null) {
            this._vehicleIdLabel.setText(d);
        }
        this.b(this._assignJobOrRunChoice, (Collection)aj, a);
        this.b(this._searchJobOrRunChoice, (Collection)aj, a);
    }
    
    private String i() {
        if (BusSelectorDialog.e == null) {
            BusSelectorDialog.e = "<no " + this._mapInfo.l().toLowerCase() + ">";
        }
        return BusSelectorDialog.e;
    }
    
    private void a(final LabeledChoice labeledChoice, final Collection collection, final String s, String s2) {
        labeledChoice.removeAll();
        if (s2 != null) {
            labeledChoice.b(s2);
        }
        if (collection != null) {
            final Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {
                s2 = iterator.next();
                labeledChoice.b(s2);
            }
        }
        this.a(labeledChoice, s);
    }
    
    private void a(final LabeledChoice labeledChoice, final String s) {
        labeledChoice.c((s == null || s.length() == 0) ? this.a(labeledChoice) : s);
    }
    
    private void a(final LabeledChoice labeledChoice, final Collection collection, String a) {
        a = this.a(labeledChoice);
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            list.add(this._agencyManager.c(iterator.next()).f());
        }
        this.a(labeledChoice, list, null, a);
    }
    
    private void b(final LabeledChoice labeledChoice, final Collection collection, final String s) {
        String a = null;
        if (collection == null || collection.size() == 0) {
            a = this.a(labeledChoice);
        }
        this.a(labeledChoice, collection, s, a);
    }
    
    private String a(final LabeledChoice labeledChoice) {
        if (labeledChoice.equals(this._assignRouteChoice) || labeledChoice.equals(this._searchRouteChoice)) {
            return "<no " + this._mapInfo.k().toLowerCase() + ">";
        }
        if (labeledChoice.equals(this._assignJobOrRunChoice) || labeledChoice.equals(this._searchJobOrRunChoice)) {
            if (this.h()) {
                return BusSelectorDialog.c;
            }
            return this.i();
        }
        else {
            if (labeledChoice.equals(this._vehicleIdChoice) || labeledChoice.equals(this._vehicleIdLabel)) {
                return BusSelectorDialog.b;
            }
            throw new RuntimeException("unrecognized choice");
        }
    }
    
    private String b(final String s) {
        for (final String s2 : this._agencyManager.b()) {
            final G c;
            if ((c = this._agencyManager.c(s2)) != null) {
                if (c.f().equals(s)) {
                    return s2;
                }
                continue;
            }
        }
        return null;
    }
    
    public final void a(String b, final LabeledChoice labeledChoice) {
        if ((b = this.b(b)) == null) {
            this.b(labeledChoice, null, null);
            return;
        }
        this._agencyManager = COM.NextBus.AdminMap.x.a(this._vehicleIdChoice.b());
        this.b(labeledChoice, this.h() ? this._agencyManager.b(b) : this._agencyManager.a(b), null);
    }
    
    public static void a(final List list) {
        BusSelectorDialog.a.removeAll();
        for (int n = 0; list != null && n < list.size(); ++n) {
            final String s;
            if ((s = list.get(n)) != null) {
                BusSelectorDialog.a.b(s);
            }
        }
    }
    
    public final void c() {
        final Color x = this._mapInfo.x();
        if (this._agencyManager == null) {
            OKDialog.a("Error", "No vehicle selected.", this, x);
            return;
        }
        this._agencyManager = COM.NextBus.AdminMap.x.a(this._vehicleIdChoice.b());
        final String l = this._agencyManager.l();
        final String b = this._vehicleIdChoice.b();
        String s = null;
        String b2 = this.b(this._assignRouteChoice.b());
        final aj j = this._agencyManager.j(b);
        try {
            if (this.h()) {
                final String b3;
                if ((b3 = this._assignJobOrRunChoice.b()).equals(BusSelectorDialog.c)) {
                    this._mapInfo.e.a(l, b, "");
                    OKDialog.a("Ok", "Took vehicle " + b + " off " + BusSelectorDialog.d + ".", this, x);
                }
                else {
                    if ((s = this._mapInfo.e.b(l, b2, b3)) == null && this._agencyManager.a(b2).contains(b3)) {
                        s = b3;
                    }
                    if (s == null) {
                        OKDialog.a("Error", "Run " + b3 + " for route " + b2 + " is not currently valid.", this, x);
                    }
                    else {
                        this._mapInfo.e.a(l, b, s);
                        OKDialog.a("Ok", "Changed vehicle " + b + " to " + BusSelectorDialog.d + " " + b3 + ".", this, x);
                    }
                }
            }
            else if (!(s = this._assignJobOrRunChoice.b()).equals(this.i())) {
                this._mapInfo.e.a(l, b, s);
                OKDialog.a("Ok", "Changed vehicle " + b + " to " + this._mapInfo.l() + " " + s + ".", this, x);
            }
            else {
                this._mapInfo.e.a(l, b, "");
                OKDialog.a("Ok", "Took vehicle " + b + " off " + this._mapInfo.l() + ".", this, x);
            }
            if (j == null) {
                throw new ConnectionException(ResponseComponent.a, "Could not find info for bus " + b);
            }
            if (s != null && !s.equals(this.i())) {
                j.a(s);
            }
            else {
                j.a((String)null);
            }
            if (this.h()) {
                if (b2.equals(this.a(this._searchRouteChoice))) {
                    b2 = null;
                }
                j.b(b2);
            }
            this._agencyManager.a(b2, b);
            this._mapInfo.f.b();
        }
        catch (ConnectionException ex) {
            OKDialog.a("Error", ex.getMessage(), this, x);
        }
    }
    
    public final String d() {
        return this._vehicleIdChoice.b();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Iterator;
import java.awt.event.ItemEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.event.ItemListener;

public class PropertiesDialog extends DialogCommon implements ItemListener
{
    private static final long serialVersionUID = 4532981569592754680L;
    private Label _vehDisplayLabel;
    private Label _vehLabelLabel;
    private Label _mapDisplayLabel;
    private Label _vehDetailLabel;
    private Label _chooseLabel;
    private Label _modeLabel;
    private Checkbox _noJobBusCheckbox;
    private Checkbox _offJobBusCheckbox;
    private Checkbox _staleBusCheckbox;
    private Checkbox _busesInYardCheckbox;
    private Checkbox _routeModeRadio;
    private Checkbox _adherenceModeRadio;
    private Checkbox _headwayModeRadio;
    private Checkbox _noLabelRadio;
    private Checkbox _routeVehicleIdLabelRadio;
    private Checkbox _headwayLabelRadio;
    private Checkbox _adherenceLabelRadio;
    private CheckboxGroup _labelGroup;
    private CheckboxGroup _modeGroup;
    private Checkbox _showMapCheckbox;
    private Checkbox _hiddenStopsCheckbox;
    private Checkbox _snapBusesToRoutesCheckbox;
    private Checkbox _mouseLatLongCheckbox;
    private Checkbox _predtnTimeofDayCheckbox;
    private Checkbox _latLongDetailCheckbox;
    private Checkbox _gpsTimeDetailCheckbox;
    private O _mapInfo;
    
    public PropertiesDialog(final Frame frame, final String s, final O mapInfo) {
        super(frame, s, mapInfo.f.getParent(), mapInfo);
        this._mapInfo = mapInfo;
        (this._vehDisplayLabel = new Label(a.b("Vehicles to Display"))).setFont(O.L());
        (this._vehLabelLabel = new Label(a.b("Vehicle Labels"))).setFont(O.L());
        (this._mapDisplayLabel = new Label(a.b("Map Display"))).setFont(O.L());
        (this._vehDetailLabel = new Label(a.b("Vehicle Details in Rollover Window"))).setFont(O.L());
        this._noJobBusCheckbox = new Checkbox(a.b("Show No-" + this._mapInfo.l() + " (grey) Vehicles"));
        this._offJobBusCheckbox = new Checkbox(a.b("Show Off-" + this._mapInfo.l() + " (white) Vehicles"));
        this._staleBusCheckbox = new Checkbox(a.b("Show Stale Vehicles"));
        this._busesInYardCheckbox = new Checkbox(a.b("Show Unassigned Vehicles in Yards"));
        this._labelGroup = new CheckboxGroup();
        (this._chooseLabel = new Label(a.b("Label Vehicles with") + ":")).setFont(O.l);
        this._routeVehicleIdLabelRadio = new Checkbox(a.b("Route&Id Only"), this._labelGroup, true);
        this._adherenceLabelRadio = new Checkbox(a.b("Add Adherence"), this._labelGroup, false);
        this._headwayLabelRadio = new Checkbox(a.b("Add Headway"), this._labelGroup, false);
        this._noLabelRadio = new Checkbox(a.b("No Label"), this._labelGroup, false);
        this._modeGroup = new CheckboxGroup();
        (this._modeLabel = new Label(a.b("Show Vehicles Colored by") + ":")).setFont(O.l);
        this._routeModeRadio = new Checkbox(a.b("Route"), this._modeGroup, true);
        this._adherenceModeRadio = new Checkbox(a.b("Adherence"), this._modeGroup, false);
        this._headwayModeRadio = new Checkbox(a.b("Headway"), this._modeGroup, false);
        this._showMapCheckbox = new Checkbox(a.b("Show Street Map"));
        this._hiddenStopsCheckbox = new Checkbox(a.b("Show Hidden Stops"));
        this._predtnTimeofDayCheckbox = new Checkbox(a.b("Show Time in Clock Time Instead of Countdown"));
        this._snapBusesToRoutesCheckbox = new Checkbox(a.b("Snap Vehicles to " + this._mapInfo.k()));
        this._mouseLatLongCheckbox = new Checkbox(a.b("Show Latitude/Longitude for Mouse"));
        this._latLongDetailCheckbox = new Checkbox(a.b("Show Latitude/Longitude"));
        this._gpsTimeDetailCheckbox = new Checkbox(a.b("Show Time the GPS Report Received"));
    }
    
    public final void a(final I i) {
        this._noJobBusCheckbox.setState(i.b());
        this._offJobBusCheckbox.setState(i.c());
        this._busesInYardCheckbox.setState(i.d());
        this._staleBusCheckbox.setState(i.e());
        this._routeVehicleIdLabelRadio.setState(i.g());
        this._adherenceLabelRadio.setState(i.h());
        this._headwayLabelRadio.setState(i.i());
        this._noLabelRadio.setState(i.f());
        this._routeModeRadio.setState(i.j());
        this._adherenceModeRadio.setState(i.k());
        this._headwayModeRadio.setState(i.l());
        this._showMapCheckbox.setState(i.m());
        this._hiddenStopsCheckbox.setState(i.n());
        this._predtnTimeofDayCheckbox.setState(i.o());
        this._snapBusesToRoutesCheckbox.setState(i.p());
        this._mouseLatLongCheckbox.setState(i.q());
        this._latLongDetailCheckbox.setState(i.r());
        this._gpsTimeDetailCheckbox.setState(i.s());
    }
    
    public final void a(final boolean b) {
        this._noJobBusCheckbox.setState(true);
    }
    
    public final void b(final boolean b) {
        this._offJobBusCheckbox.setState(true);
    }
    
    public final void c(final boolean b) {
        this._busesInYardCheckbox.setState(true);
    }
    
    public final void d(final boolean b) {
        this._staleBusCheckbox.setState(true);
    }
    
    public final void a() {
        this.setLayout(new BorderLayout());
        final VerticalFlowPanel verticalFlowPanel = new VerticalFlowPanel();
        final VerticalFlowPanel verticalFlowPanel2 = new VerticalFlowPanel(1);
        verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel2));
        final VerticalFlowPanel verticalFlowPanel3 = new VerticalFlowPanel(1);
        verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel3));
        final VerticalFlowPanel verticalFlowPanel4 = new VerticalFlowPanel(1);
        verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel4));
        final VerticalFlowPanel verticalFlowPanel5 = new VerticalFlowPanel(1);
        verticalFlowPanel.a(new EmptyBorderPanel(7, verticalFlowPanel5));
        verticalFlowPanel.add(this._closeButton, null);
        this.add(verticalFlowPanel, "South");
        this._lookAndFeel.a(this._showMapCheckbox);
        this._lookAndFeel.a(this._routeVehicleIdLabelRadio);
        this._lookAndFeel.a(this._staleBusCheckbox);
        this._lookAndFeel.a(this._hiddenStopsCheckbox);
        this._lookAndFeel.a(this._noJobBusCheckbox);
        this._lookAndFeel.a(this._adherenceLabelRadio);
        this._lookAndFeel.a(this._busesInYardCheckbox);
        this._lookAndFeel.a(this._snapBusesToRoutesCheckbox);
        this._lookAndFeel.a(this._offJobBusCheckbox);
        this._lookAndFeel.a(this._headwayLabelRadio);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).weightx = 1.0;
        gridBagConstraints.anchor = 17;
        verticalFlowPanel2.a(this._vehDisplayLabel);
        verticalFlowPanel2.a(this._noJobBusCheckbox);
        verticalFlowPanel2.a(this._offJobBusCheckbox);
        verticalFlowPanel2.a(this._busesInYardCheckbox);
        verticalFlowPanel2.a(this._staleBusCheckbox);
        verticalFlowPanel3.a(this._vehLabelLabel);
        verticalFlowPanel3.a(this._chooseLabel);
        verticalFlowPanel3.add(this._routeVehicleIdLabelRadio);
        verticalFlowPanel3.add(this._adherenceLabelRadio);
        verticalFlowPanel3.add(this._headwayLabelRadio);
        verticalFlowPanel3.add(this._noLabelRadio);
        verticalFlowPanel3.a(this._modeLabel);
        verticalFlowPanel3.add(this._routeModeRadio, gridBagConstraints);
        verticalFlowPanel3.add(this._adherenceModeRadio, gridBagConstraints);
        verticalFlowPanel3.add(this._headwayModeRadio, gridBagConstraints);
        verticalFlowPanel4.a(this._mapDisplayLabel);
        verticalFlowPanel4.a(this._showMapCheckbox);
        verticalFlowPanel4.a(this._hiddenStopsCheckbox);
        verticalFlowPanel4.a(this._predtnTimeofDayCheckbox);
        verticalFlowPanel4.a(this._snapBusesToRoutesCheckbox);
        verticalFlowPanel4.a(this._mouseLatLongCheckbox);
        verticalFlowPanel5.a(this._vehDetailLabel);
        verticalFlowPanel5.a(this._latLongDetailCheckbox);
        verticalFlowPanel5.a(this._gpsTimeDetailCheckbox);
        this._noJobBusCheckbox.setState(this._mapInfo.C().b());
        this._noJobBusCheckbox.addItemListener(this);
        this._offJobBusCheckbox.setState(this._mapInfo.C().c());
        this._offJobBusCheckbox.addItemListener(this);
        this._busesInYardCheckbox.setState(this._mapInfo.C().d());
        this._busesInYardCheckbox.addItemListener(this);
        this._staleBusCheckbox.setState(this._mapInfo.C().e());
        this._staleBusCheckbox.addItemListener(this);
        this._noLabelRadio.setState(this._mapInfo.C().f());
        this._noLabelRadio.addItemListener(this);
        this._routeVehicleIdLabelRadio.setState(this._mapInfo.C().g());
        this._routeVehicleIdLabelRadio.addItemListener(this);
        this._adherenceLabelRadio.setState(this._mapInfo.C().h());
        this._adherenceLabelRadio.addItemListener(this);
        this._headwayLabelRadio.setState(this._mapInfo.C().i());
        this._headwayLabelRadio.addItemListener(this);
        this._routeModeRadio.setState(this._mapInfo.C().j());
        this._routeModeRadio.addItemListener(this);
        this._adherenceModeRadio.setState(this._mapInfo.C().k());
        this._adherenceModeRadio.addItemListener(this);
        this._headwayModeRadio.setState(this._mapInfo.C().l());
        this._headwayModeRadio.addItemListener(this);
        this._showMapCheckbox.setState(this._mapInfo.C().m());
        this._showMapCheckbox.addItemListener(this);
        this._hiddenStopsCheckbox.setState(this._mapInfo.C().n());
        this._hiddenStopsCheckbox.addItemListener(this);
        this._predtnTimeofDayCheckbox.setState(this._mapInfo.C().o());
        this._predtnTimeofDayCheckbox.addItemListener(this);
        this._snapBusesToRoutesCheckbox.setState(this._mapInfo.C().p());
        this._snapBusesToRoutesCheckbox.addItemListener(this);
        this._mouseLatLongCheckbox.setState(this._mapInfo.C().q());
        this._mouseLatLongCheckbox.addItemListener(this);
        this._latLongDetailCheckbox.setState(this._mapInfo.C().r());
        this._latLongDetailCheckbox.addItemListener(this);
        this._gpsTimeDetailCheckbox.setState(this._mapInfo.C().s());
        this._gpsTimeDetailCheckbox.addItemListener(this);
        this.pack();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (!this._mapInfo.O()) {
            if (!this._mapInfo.C().b() && !this._mapInfo.C().c() && !this._mapInfo.C().e() && (this._noJobBusCheckbox.getState() || this._offJobBusCheckbox.getState() || this._staleBusCheckbox.getState())) {
                final Iterator<String> iterator = this._mapInfo.w().iterator();
                while (iterator.hasNext()) {
                    this._mapInfo.e.a(iterator.next(), "", true);
                }
                this._mapInfo.c.e();
            }
            if ((this._mapInfo.C().b() || this._mapInfo.C().c() || this._mapInfo.C().e()) && ((!this._noJobBusCheckbox.getState() && !this._offJobBusCheckbox.getState()) || this._staleBusCheckbox.getState())) {
                final Iterator<String> iterator2 = this._mapInfo.w().iterator();
                while (iterator2.hasNext()) {
                    this._mapInfo.e.a(iterator2.next(), "", false);
                }
            }
        }
        this._mapInfo.C().a(this._noJobBusCheckbox.getState());
        this._mapInfo.C().b(this._offJobBusCheckbox.getState());
        this._mapInfo.C().d(this._staleBusCheckbox.getState());
        this._mapInfo.C().c(this._busesInYardCheckbox.getState());
        this._mapInfo.C().e(this._noLabelRadio.getState());
        this._mapInfo.C().f(this._routeVehicleIdLabelRadio.getState());
        this._mapInfo.C().g(this._adherenceLabelRadio.getState());
        this._mapInfo.C().h(this._headwayLabelRadio.getState());
        this._mapInfo.C().i(this._routeModeRadio.getState());
        this._mapInfo.C().j(this._adherenceModeRadio.getState());
        this._mapInfo.C().k(this._headwayModeRadio.getState());
        this._mapInfo.C().l(this._showMapCheckbox.getState());
        this._mapInfo.C().m(this._hiddenStopsCheckbox.getState());
        this._mapInfo.C().n(this._predtnTimeofDayCheckbox.getState());
        this._mapInfo.C().o(this._snapBusesToRoutesCheckbox.getState());
        this._mapInfo.C().p(this._mouseLatLongCheckbox.getState());
        this._mapInfo.C().q(this._latLongDetailCheckbox.getState());
        this._mapInfo.C().r(this._gpsTimeDetailCheckbox.getState());
        this._mapInfo.b();
        this._mapInfo.f.b();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.event.ItemListener;

public class DebugDialog extends DialogCommon implements ItemListener
{
    private static final long serialVersionUID = 4995567993388566500L;
    private final Checkbox _displayTagsCheckbox;
    private final Checkbox _displayMapTileDebugCheckbox;
    private final Checkbox _displayVehicleIdsWithArrivalsCheckbox;
    private final Checkbox _debugOnCheckbox;
    private final Checkbox _allowSelectAllRoutesCheckbox;
    private final Checkbox _showOnBreakCheckbox;
    private final O _mapInfo;
    private final TextArea _textArea;
    
    public DebugDialog(final Frame frame, final String s, final O mapInfo) {
        super(frame, s, mapInfo.f.getParent(), mapInfo);
        this._displayTagsCheckbox = new Checkbox("Display Route/Dir/Stop Tags");
        this._displayMapTileDebugCheckbox = new Checkbox("Display map tile debug");
        this._displayVehicleIdsWithArrivalsCheckbox = new Checkbox("Display vehicle Ids with arrivals");
        this._debugOnCheckbox = new Checkbox("Debug On");
        this._allowSelectAllRoutesCheckbox = new Checkbox("Allow Select All Routes");
        this._showOnBreakCheckbox = new Checkbox("Label \"on break\" vehicles");
        this._mapInfo = mapInfo;
        this._textArea = new TextArea("TimeZone=" + this._mapInfo.q().getID(), 1, 20, 3);
    }
    
    public final void a() {
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        this.add(panel, "South");
        panel.add(this._closeButton, null);
        final GridLayout layout;
        (layout = new GridLayout()).setHgap(4);
        layout.setColumns(3);
        layout.setVgap(4);
        final Panel panel2;
        (panel2 = new Panel()).setLayout(layout);
        this.add(panel2, "Center");
        this._mapInfo.a(this._displayTagsCheckbox);
        panel2.add(this._displayTagsCheckbox);
        this._mapInfo.a(this._displayMapTileDebugCheckbox);
        panel2.add(this._displayMapTileDebugCheckbox);
        this._mapInfo.a(this._displayVehicleIdsWithArrivalsCheckbox);
        panel2.add(this._displayVehicleIdsWithArrivalsCheckbox);
        this._mapInfo.a(this._debugOnCheckbox);
        panel2.add(this._debugOnCheckbox);
        this._mapInfo.a(this._showOnBreakCheckbox);
        panel2.add(this._showOnBreakCheckbox);
        this._mapInfo.a(this._allowSelectAllRoutesCheckbox);
        panel2.add(this._allowSelectAllRoutesCheckbox);
        panel2.add(this._textArea);
        layout.setRows((panel2.getComponentCount() + 1) / 2);
        this._displayTagsCheckbox.setState(this._mapInfo.C().x());
        this._displayTagsCheckbox.addItemListener(this);
        this._displayMapTileDebugCheckbox.setState(this._mapInfo.F());
        this._displayMapTileDebugCheckbox.addItemListener(this);
        this._displayVehicleIdsWithArrivalsCheckbox.setState(this._mapInfo.C().y());
        this._displayVehicleIdsWithArrivalsCheckbox.addItemListener(this);
        this._debugOnCheckbox.setState(this._mapInfo.C().w());
        this._debugOnCheckbox.addItemListener(this);
        this._showOnBreakCheckbox.setState(this._mapInfo.C().z());
        this._showOnBreakCheckbox.addItemListener(this);
        this._allowSelectAllRoutesCheckbox.setState(this._mapInfo.C().A());
        this._allowSelectAllRoutesCheckbox.addItemListener(this);
        this.pack();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this._mapInfo.C().s(this._displayTagsCheckbox.getState());
        this._mapInfo.a(this._displayMapTileDebugCheckbox.getState());
        this._mapInfo.C().t(this._displayVehicleIdsWithArrivalsCheckbox.getState());
        this._mapInfo.C().u(this._debugOnCheckbox.getState());
        this._mapInfo.C().v(this._showOnBreakCheckbox.getState());
        this._mapInfo.C().w(this._allowSelectAllRoutesCheckbox.getState());
        this._mapInfo.f.b();
        this._mapInfo.b();
    }
}

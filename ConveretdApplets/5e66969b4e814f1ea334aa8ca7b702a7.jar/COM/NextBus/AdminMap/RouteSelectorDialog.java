// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;

public class RouteSelectorDialog extends DialogCommon
{
    private static final long serialVersionUID = 7226757257141800899L;
    private O _mapInfo;
    private t _agencyManager;
    
    public RouteSelectorDialog(final Frame frame, final String s, final Component component, final O mapInfo, final t agencyManager) {
        super(frame, s, component, mapInfo);
        this._agencyManager = agencyManager;
        this._mapInfo = mapInfo;
    }
    
    public final void a() {
        this.setLayout(new BorderLayout());
        final int size = this._agencyManager.b().size();
        final GridLayout layout = new GridLayout();
        int vgap = 4;
        if (size > 30 && size < 90) {
            vgap = 2;
        }
        else if (size >= 90) {
            vgap = 1;
        }
        layout.setVgap(vgap);
        layout.setColumns(1);
        layout.setRows(size);
        final Container container;
        (container = new Panel()).setLayout(layout);
        final ScrollPane scrollPane = new ScrollPane();
        int n = 50 + (int)(Math.sqrt(size) * 33.0);
        if (size > 3) {
            n += 40;
        }
        scrollPane.setSize(100, n);
        scrollPane.add(container);
        scrollPane.getVAdjustable().setUnitIncrement(10);
        this.add(scrollPane, "Center");
        final Iterator f = this._agencyManager.f();
        while (f.hasNext()) {
            final Checkbox checkbox;
            (checkbox = f.next()).setFont(O.k);
            container.add(checkbox, null);
        }
        final Panel panel = new Panel();
        this.add(panel, "South");
        final GridBagLayout layout2 = new GridBagLayout();
        panel.setLayout(layout2);
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(6, 12, 6, 12);
        gridBagConstraints.fill = 2;
        if (size > 3) {
            if (this._mapInfo.C().A() || size < 100) {
                final Button button;
                (button = new Button(a.b("Select All"))).addActionListener(new an(this));
                this._mapInfo.a(button);
                gridBagConstraints.gridwidth = -1;
                layout2.setConstraints(button, gridBagConstraints);
                panel.add(button);
            }
            final Button button2;
            (button2 = new Button(a.b("De-Select All"))).addActionListener(new ap(this));
            this._mapInfo.a(button2);
            gridBagConstraints.gridwidth = 0;
            layout2.setConstraints(button2, gridBagConstraints);
            panel.add(button2);
        }
        final Button button3 = new Button(a.b("Zoom to " + this._mapInfo.k() + "s"));
        this._mapInfo.a(button3);
        button3.addActionListener(new at(this));
        gridBagConstraints.gridwidth = -1;
        layout2.setConstraints(button3, gridBagConstraints);
        panel.add(button3);
        gridBagConstraints.gridwidth = 0;
        layout2.setConstraints(this._closeButton, gridBagConstraints);
        panel.add(this._closeButton);
        this.pack();
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.setVisible(false);
        }
        super.processWindowEvent(windowEvent);
    }
}

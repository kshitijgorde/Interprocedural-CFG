// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap.Toolbar;

import java.awt.event.ActionListener;
import java.awt.Button;
import COM.NextBus.Applets.EmptyBorderPanel;
import java.awt.event.ItemListener;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import COM.NextBus.AdminMap.a;
import java.awt.Component;
import java.awt.Frame;
import COM.NextBus.Applets.d;
import COM.NextBus.Applets.LabeledTextField;
import java.awt.List;
import COM.NextBus.AdminMap.r;
import COM.NextBus.AdminMap.DialogCommon;

public class ConfigurationsDialog extends DialogCommon
{
    private static final long serialVersionUID = -2551981724235268983L;
    private r _configs;
    private List _list;
    private LabeledTextField _nameField;
    private d _lnf;
    
    public ConfigurationsDialog(final Frame frame, final Component component, final r configs, final d lnf) {
        super(frame, a.b("Save Configuration"), component, lnf);
        this.setBackground(lnf.x());
        this._configs = configs;
        this._lnf = lnf;
        this._configs.a(new f(this));
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this._nameField.a(this._configs.b());
            final List list = this._list;
            final String b = this._configs.b();
            final List list2 = list;
            final String[] items = list.getItems();
            for (int i = 0; i < items.length; ++i) {
                if (items[i].equals(b)) {
                    list2.select(i);
                    return;
                }
            }
        }
    }
    
    public final void a() {
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label(a.b("Existing Configurations") + ":");
        this._lnf.a(label);
        (this._list = new List()).addItemListener(new g(this));
        this._lnf.a(this._list);
        this.b();
        this._nameField = new LabeledTextField(a.b("Configuration Name") + ":", 16, 0);
        this._lnf.a(this._nameField);
        panel.add(label, "North");
        panel.add(this._list, "Center");
        panel.add(this._nameField, "South");
        this.add(new EmptyBorderPanel(15, panel), "Center");
        final Panel panel2 = new Panel();
        final Button button = new Button(a.b("Save Current Config"));
        this._lnf.a(button);
        button.addActionListener(new c(this));
        panel2.add(button);
        final Button button2 = new Button(a.b("Delete"));
        this._lnf.a(button2);
        button2.addActionListener(new COM.NextBus.AdminMap.Toolbar.a(this));
        panel2.add(button2);
        panel2.add(this._closeButton);
        this.add(panel2, "South");
    }
    
    private void b() {
        this._list.removeAll();
        final java.util.List d = this._configs.d();
        for (int i = 0; i < d.size(); ++i) {
            this._list.add(d.get(i));
        }
    }
}

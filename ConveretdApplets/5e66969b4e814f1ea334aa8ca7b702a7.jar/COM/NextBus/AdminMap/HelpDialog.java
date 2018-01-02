// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.TimeZone;
import java.util.ArrayList;
import COM.NextBus.util.f;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Frame;

public class HelpDialog extends DialogCommon
{
    private static final long serialVersionUID = 158193802319365543L;
    private final O _mapInfo;
    private static final String[] a;
    
    public HelpDialog(final Frame frame, final String s, final Component component, final O mapInfo) {
        super(frame, s, component, mapInfo);
        this._mapInfo = mapInfo;
    }
    
    public final void a() {
        this.setLayout(new BorderLayout());
        final TextArea textArea = new TextArea("", 27, 58, 1);
        final Dimension preferredSize = this.getPreferredSize();
        this.setSize(preferredSize.width, preferredSize.height + this._closeButton.getSize().height);
        textArea.setBackground(this._mapInfo.x());
        textArea.setFont(O.k);
        textArea.setEditable(false);
        this.a(textArea);
        this.add(textArea, "Center");
        final Panel panel;
        (panel = new Panel(new BorderLayout())).setBackground(this._mapInfo.x());
        panel.add(this._closeButton, "Center");
        this.add(panel, "South");
        this.pack();
    }
    
    private void a(final TextArea textArea) {
        this.a(textArea, COM.NextBus.AdminMap.a.b("commonHelpText"));
        if (!this._mapInfo.o()) {
            if (!this._mapInfo.O()) {
                this.a(textArea, COM.NextBus.AdminMap.a.b("agencyMapHelpText"));
            }
            else {
                this.a(textArea, COM.NextBus.AdminMap.a.b("replayMapHelpText"));
            }
        }
        String id = "unknown";
        final TimeZone q;
        if ((q = this._mapInfo.q()) != null) {
            id = q.getID();
        }
        final String property = System.getProperty("java.version");
        final String s = f.a() ? "yes" : "no";
        final ArrayList<String> list;
        (list = new ArrayList<String>()).add("Current Java Version: " + property);
        list.add("TimeZone used: " + id);
        list.add("2007 Daylight Savings Rules supported by Java? " + s);
        final String[] array = list.toArray(new String[list.size()]);
        this.a(textArea, "JAVA INFO\n\n");
        for (int i = 0; i < array.length; ++i) {
            this.a(textArea, array[i] + '\n');
        }
        this.a(textArea, "\nABOUT\n\n");
        this.a(textArea, this._mapInfo.A() + ", ");
        for (int j = 0; j < HelpDialog.a.length; ++j) {
            this.a(textArea, HelpDialog.a[j] + '\n');
        }
    }
    
    private void a(final TextArea textArea, String s) {
        s = a(s = a(s = a(s = a(s = a(s = a(s, "Route", this._mapInfo.k()), "route", this._mapInfo.k().toLowerCase()), "ROUTE", this._mapInfo.k().toUpperCase()), "Job", this._mapInfo.l()), "job", this._mapInfo.l().toLowerCase()), "JOB", this._mapInfo.l().toUpperCase());
        textArea.append(s);
    }
    
    private static String a(String string, final String s, final String s2) {
        int n = 0;
        final int length = s.length();
        int index;
        StringBuffer sb;
        for (int length2 = s2.length(); (index = string.indexOf(s, n)) != -1; string = sb.toString(), n = index + length2) {
            (sb = new StringBuffer(string.substring(0, index))).append(s2);
            sb.append(string.substring(index + length));
        }
        return string;
    }
    
    static {
        a = new String[] { COM.NextBus.AdminMap.a.b("Version") + " " + "web-2.5.3" + ".", COM.NextBus.AdminMap.a.b("Copyright") + " 1998-2011, NextBus Inc.", COM.NextBus.AdminMap.a.b("Send comments and questions to") + " website@nextbus.com." };
    }
}

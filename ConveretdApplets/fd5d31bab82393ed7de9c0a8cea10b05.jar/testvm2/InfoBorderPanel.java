// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.awt.Component;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;

public class InfoBorderPanel extends JPanel
{
    public InfoBorderPanel(final String[] sysPropertiesStrings) {
        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        final StringBuffer sysInfo = new StringBuffer("<HTML><BODY>");
        for (int i = 0; i < 6; ++i) {
            sysInfo.append(sysPropertiesStrings[i]).append(" ");
            if (i == 2) {
                sysInfo.append("<a href=\"http://java.sun.com\">").append(sysPropertiesStrings[i + 6]).append("</a> ");
            }
            else if (i > 2) {
                sysInfo.append("<b>" + sysPropertiesStrings[i + 6] + "</b>");
            }
            else {
                sysInfo.append(sysPropertiesStrings[i + 6]);
            }
            sysInfo.append("<br>");
        }
        sysInfo.append("</BODY></HTML>");
        final JEditorPane infoTextPane = new JEditorPane("text/html", (sysInfo.toString() == null) ? "" : sysInfo.toString());
        infoTextPane.setBorder(new EmptyBorder(0, 75, 0, 75));
        infoTextPane.setBackground(Color.decode("#F3F3F3"));
        infoTextPane.setEditable(false);
        infoTextPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(final HyperlinkEvent r) {
                try {
                    if (r.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        Main.showJavaVendor();
                    }
                }
                catch (Exception ex) {}
            }
        });
        this.add(infoTextPane);
    }
}

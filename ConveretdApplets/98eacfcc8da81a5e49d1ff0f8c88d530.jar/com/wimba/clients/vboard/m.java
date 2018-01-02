// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import com.hw.client.util.c;
import java.awt.Component;
import javax.swing.JOptionPane;
import VT_6_1_0_11.aT;
import VT_6_1_0_11.bj;
import com.hw.client.util.a;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.bt;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;

public final class m implements ActionListener
{
    private n a;
    private p b;
    private final ResourceBundle c;
    
    public m(final n a, final p b) {
        this.c = ResourceBundle.getBundle("strings");
        this.a = a;
        this.b = b;
        b.o().e(false);
        b.e().addTreeExpansionListener(a);
        b.e().addTreeSelectionListener(a);
        b.a.addActionListener(this);
        b.b.addActionListener(this);
        b.k.addActionListener(this);
        b.l.addActionListener(this);
        b.m.addActionListener(this);
        b.n.addActionListener(this);
        b.o.addActionListener(this);
        b.p.addActionListener(this);
        b.q.addActionListener(this);
        b.r.addActionListener(this);
        b.s.addActionListener(this);
    }
    
    private bt a() {
        return (bt)this.b.e().getSelectionModel();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.b.o().a("");
        final String actionCommand = actionEvent.getActionCommand();
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeController.actionPerformed: ac=" + actionCommand);
        }
        if (actionCommand.equals("ACTION_NEW")) {
            this.a.P();
            return;
        }
        if (actionCommand.equals("ACTION_REPLY")) {
            if (this.a().b() != null) {
                this.a.a(this.a().c().a());
            }
        }
        else if (actionCommand.equals("ACTION_FORWARD")) {
            if (this.a().b() != null) {
                this.a.b(this.a().c().a());
            }
        }
        else {
            if (actionCommand.equals("ACTION_DELETE")) {
                this.a.R();
                return;
            }
            if (actionCommand.equals("ACTION_IMPORT")) {
                if (!bj.d() || !bj.f()) {
                    this.a.B();
                    return;
                }
                JOptionPane.showMessageDialog(null, new aT("<html>" + this.b.c("error_no_disk_access_msg")), this.b.c("error_no_disk_access_title"), 0);
            }
            else if (actionCommand.equals("ACTION_EXPORT")) {
                if (!bj.d() || !bj.f()) {
                    this.a.C();
                    return;
                }
                JOptionPane.showMessageDialog(null, new aT("<html>" + this.b.c("error_no_disk_access_msg")), this.b.c("error_no_disk_access_title"), 0);
            }
            else {
                if (actionCommand.equals("ACTION_PUBLISH")) {
                    if (this.a().b() != null) {
                        this.a.c(this.a().c().a());
                    }
                    return;
                }
                if (actionCommand.equals("ACTION_EDIT")) {
                    this.a.N();
                    return;
                }
                if (actionCommand.equals("ACTION_RSS")) {
                    this.a.t.getAppletContext().showDocument(this.a.Q(), "_blank");
                    return;
                }
                if (actionCommand.equals("ACTION_SUBSCRIBE")) {
                    if (this.a.X()) {
                        this.a.c(false);
                        return;
                    }
                    this.a.V();
                }
                else {
                    if (actionCommand.equals("ACTION_RSS_HELP")) {
                        final StringBuffer sb;
                        (sb = new StringBuffer("<html><body style='margin: 10px; font-size: 9px; font-weight: 400; font-family: Verdana, sans-serif;'>")).append("<div style='font-weight: 700' >").append(this.a.e("rss_help_what_subscribe_do_title")).append("</div><br>");
                        sb.append(this.a.e("rss_help_what_subscribe_do_msg")).append("<br><br>");
                        sb.append("<div style='font-weight: 700' >").append(this.a.e("rss_help_how_to_subscribe")).append("</div><br>");
                        sb.append("<table><tbody>");
                        sb.append("<tr><td><table><tr><td><img src=\"").append(com.hw.client.util.c.a("/images/podcaster/icon_1_click.png").toString()).append("\"></td>");
                        sb.append("<td>").append(this.a.e("rss_help_subscribe_1")).append("</td></tr></table></td></tr>");
                        sb.append("<tr><td><table><tr><td><img src=\"").append(com.hw.client.util.c.a("/images/podcaster/icon_dnd.png").toString()).append("\"></td>");
                        sb.append("<td>").append(this.a.e("rss_help_subscribe_2")).append("</td></tr></table></td></tr>");
                        sb.append("<tr><td><table><tr><td><img src=\"").append(com.hw.client.util.c.a("/images/podcaster/icon_rss.png").toString()).append("\"></td>");
                        sb.append("<td>").append(this.a.e("rss_help_subscribe_3")).append("</td></tr></table></td></tr></table>");
                        sb.append("</body></html>");
                        JOptionPane.showMessageDialog(this.a, sb.toString(), this.a.e("btn_help") + this.c.getString("voice.podcaster"), -1);
                        return;
                    }
                    com.hw.client.util.a.d("VBTreeController.actionPerformed: unknown ac=" + actionCommand);
                }
            }
        }
    }
}

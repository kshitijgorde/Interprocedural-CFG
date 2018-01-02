// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.util.Iterator;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class z implements ActionListener
{
    private webphone a;
    
    z(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.a.common.eY) {
                this.a.common.eY = false;
                this.a.PlayRingTone(true);
            }
            if (this.a.finishvoicerec) {
                this.a.finishvoicerec = false;
                if (this.a.mainthread != null && this.a.mainthread.try != null) {
                    for (final t t : this.a.mainthread.try) {
                        if (t != null && t.aU >= 7) {
                            t.if(true);
                        }
                    }
                }
            }
            if (this.a.common.dI) {
                this.a.common.dI = false;
                this.a.PlayRingTone(false);
            }
            if (this.a.common.r) {
                this.a.common.a(1, "ERROR,accept the applet certificate first!");
                this.a.common.r = false;
            }
            if (this.a.common.bq != 0L && this.a.common.A > 0L && this.a.common.do() - this.a.common.bq > this.a.common.A * 60000L) {
                this.a.common.A = 0L;
                this.a.common.a(1, "EVENT,http session timeout");
                this.a.API_Unregister();
            }
            if (this.a.initservernat.length() > 0) {
                this.a.AfterStart(true);
                final String initservernat = this.a.initservernat;
                this.a.initservernat = "";
                this.a.common.a(3, "EVENT,async API_NATKeepAlive");
                this.a.API_NATKeepAlive(initservernat);
            }
            if (this.a.asynreg_username.length() > 0) {
                this.a.API_RegisterAsync();
            }
            if (this.a.asyncall_number.length() > 0) {
                final String asyncall_number = this.a.asyncall_number;
                this.a.asyncall_number = "";
                this.a.API_Call_Now(this.a.asyncall_line, asyncall_number);
                this.a.asyncall_line = -1;
            }
            if (this.a.asyncclearcredentials) {
                this.a.asyncclearcredentials = false;
                this.a.CleanCredentials();
            }
            if (this.a.asynccfgsave) {
                this.a.asynccfgsave = false;
                this.a.SaveSettings();
            }
            if (this.a.asyncneedproxyauthui == 1) {
                this.a.asyncneedproxyauthui = 2;
                if (this.a.common.cs == -5 || this.a.common.cs > 22) {
                    this.a.asyncneedproxyauthui = 4;
                }
                else {
                    final aw common = this.a.common;
                    ++common.cs;
                    if (this.a.proxyauthui == null) {
                        (this.a.proxyauthui = new d(this.a.common, this.a)).pack();
                    }
                    this.a.proxyauthui.setLocationRelativeTo(this.a);
                    this.a.proxyauthui.setVisible(true);
                    this.a.common.a(1, "EVENT,Proxy authentication");
                }
            }
            if (this.a.volumechanged != 0L && this.a.common.do() - this.a.volumechanged > 6000L) {
                this.a.volumechanged = 0L;
                this.a.SaveSettings();
            }
        }
        catch (Exception ex) {
            this.a.common.a(1, "timerb 1", ex);
        }
        try {
            if (this.a.asyncrec_call != null) {
                final t asyncrec_call = this.a.asyncrec_call;
                this.a.asyncrec_call = null;
                if (this.a.common.b3) {
                    (this.a.callframe = new bl(this.a.common)).pack();
                    this.a.callframe.case = asyncrec_call;
                    String string = "";
                    if (asyncrec_call != null && asyncrec_call.U != null && asyncrec_call.U.do.length() > 0) {
                        string = " (by " + asyncrec_call.U.do.length() + ")";
                    }
                    if (asyncrec_call != null) {
                        this.a.callframe.try.setText(this.a.common.a("Call from ") + asyncrec_call.int() + string);
                    }
                    this.a.callframe.setLocationRelativeTo(null);
                    this.a.callframe.setTitle(this.a.common.a("Incoming call"));
                    this.a.callframe.setVisible(true);
                    try {
                        this.a.callframe.byte.requestFocus();
                    }
                    catch (Exception ex4) {}
                }
                this.a.jButton17.setEnabled(true);
                if (asyncrec_call != null) {
                    this.a.label5.setText(this.a.common.a("Incoming call"));
                }
                else {
                    this.a.common.bA = asyncrec_call.int();
                    this.a.common.eo = this.a.common.bA;
                    this.a.label5.setText(this.a.common.a("Incoming call from ") + this.a.common.bA);
                }
            }
            if (this.a.asyncrec_chat != null && this.a.common.d2 > 1) {
                final t asyncrec_chat = this.a.asyncrec_chat;
                this.a.asyncrec_chat = null;
                if (this.a.chatframe == null) {
                    this.a.chatframe = new y(this.a.common, this.a);
                    this.a.chatframe.else = asyncrec_chat;
                    this.a.chatframe.pack();
                    this.a.chatframe.setLocationRelativeTo(null);
                    try {
                        this.a.chatframe.byte.requestFocus();
                    }
                    catch (Exception ex5) {}
                }
                if (this.a.chatframe.a.getItemCount() < 1) {
                    this.a.chatframe.a.addItem(asyncrec_chat.r().trim());
                    this.a.chatframe.a.setSelectedIndex(0);
                }
                else if (this.a.chatframe.a.getSelectedItem() == null || !((String)this.a.chatframe.a.getSelectedItem()).trim().equals(asyncrec_chat.r())) {
                    this.a.chatframe.a.insertItemAt(asyncrec_chat.r().trim(), 0);
                }
                this.a.chatframe.setTitle(this.a.common.a("Chat"));
                this.a.chatframe.setVisible(true);
                String ar = "";
                try {
                    ar = asyncrec_chat.aR;
                    asyncrec_chat.aR = "";
                }
                catch (Exception ex6) {}
                this.a.chatframe.a(asyncrec_chat.r(), ar.trim());
                try {
                    this.a.chatframe.byte.requestFocus();
                }
                catch (Exception ex7) {}
            }
            if (this.a.asyncrec_statuschanged || this.a.common.do() - this.a.laststatuscecktick > 2200L) {
                this.a.asyncrec_statuschanged = false;
                this.a.CheckStatusText();
            }
            if (this.a.common.ci && this.a.common.do() - this.a.lastblinkcheck > 1500L) {
                this.a.lastblinkcheck = this.a.common.do();
                if (this.a.blinkiglines[1] > 0) {
                    if (this.a.jButton20.getForeground() == Color.RED) {
                        this.a.jButton20.setForeground(Color.BLUE);
                    }
                    else {
                        this.a.jButton20.setForeground(Color.RED);
                    }
                }
                if (this.a.blinkiglines[2] > 0) {
                    if (this.a.jButton21.getForeground() == Color.RED) {
                        this.a.jButton21.setForeground(Color.BLUE);
                    }
                    else {
                        this.a.jButton21.setForeground(Color.RED);
                    }
                }
                if (this.a.blinkiglines[3] > 0) {
                    if (this.a.jButton22.getForeground() == Color.RED) {
                        this.a.jButton22.setForeground(Color.BLUE);
                    }
                    else {
                        this.a.jButton22.setForeground(Color.RED);
                    }
                }
                if (this.a.blinkiglines[4] > 0) {
                    if (this.a.jButton24.getForeground() == Color.RED) {
                        this.a.jButton24.setForeground(Color.BLUE);
                    }
                    else {
                        this.a.jButton24.setForeground(Color.RED);
                    }
                }
            }
            if (this.a.common.do() - this.a.lastlogdisplayed > 5000L && !this.a.jLabel1xx.getText().equals("   ")) {
                this.a.jLabel1xx.setText("   ");
            }
        }
        catch (Exception ex2) {
            this.a.common.a(1, "timerb 2", ex2);
        }
        try {
            if (this.a.lstlogmsg.length() > 0) {
                final String lstlogmsg = this.a.lstlogmsg;
                this.a.lstlogmsg = "";
                this.a.PutToDebugLogForm(lstlogmsg);
            }
        }
        catch (Exception ex3) {
            this.a.common.a(1, "timerb 3", ex3);
        }
    }
}

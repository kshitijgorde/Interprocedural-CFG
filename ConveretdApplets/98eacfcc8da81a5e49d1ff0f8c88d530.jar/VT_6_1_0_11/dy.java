// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.BoundedRangeModel;
import javax.swing.event.ChangeEvent;
import java.applet.AppletContext;
import java.applet.Applet;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.MenuElement;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.util.List;
import com.hw.client.util.c;
import javax.swing.Icon;
import com.hw.client.util.a;
import java.io.InputStream;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class dy extends R implements ActionListener, ChangeListener
{
    public InputStream i;
    public InputStream j;
    private boolean k;
    private final cY l;
    
    public dy(final cY l, final du du) {
        this.l = l;
        super(du);
        this.k = false;
    }
    
    public void c() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.idleState");
        }
        this.l.f().setIcon(cY.a(this.l));
        this.l.f().setRolloverEnabled(true);
        this.l.f().setDisabledIcon(cY.b(this.l));
        com.hw.client.util.c.a(this.l.f());
        this.l.f().setEnabled(this.j != null);
        this.l.h().setEnabled(false);
        this.l.i().setIcon(cY.c(this.l));
        this.l.i().setRolloverEnabled(true);
        this.l.i().setDisabledIcon(cY.d(this.l));
        com.hw.client.util.c.a(this.l.i());
        this.l.i().setEnabled(true);
        this.l.g().setIcon(cY.e(this.l));
        this.l.g().setRolloverEnabled(true);
        com.hw.client.util.c.a(this.l.g());
        this.l.g().setEnabled(false);
        if (!this.l.j().c().getValueIsAdjusting()) {
            if (!this.g) {
                this.b(0.0);
            }
            this.l.j().a(false);
            this.l.k().a(0);
        }
        this.l.l().setEnabled(true);
        this.l.b(0);
    }
    
    public void d() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.playingState");
        }
        this.l.j().a(true);
        this.l.f().setDisabledIcon(cY.f(this.l));
        this.l.f().setEnabled(false);
        this.l.h().setEnabled(true);
        this.l.i().setEnabled(false);
        this.l.g().setIcon(cY.e(this.l));
        this.l.g().setRolloverEnabled(true);
        com.hw.client.util.c.a(this.l.g());
        this.l.g().setEnabled(true);
        this.l.j().a(true);
        this.l.l().setEnabled(true);
        this.l.b(3);
    }
    
    public void n() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.pausePlayState");
        }
        this.l.f().setIcon(cY.f(this.l));
        this.l.f().setRolloverEnabled(false);
        com.hw.client.util.c.a(this.l.f());
        this.l.f().setEnabled(true);
        this.l.h().setEnabled(true);
        this.l.i().setEnabled(false);
        this.l.g().setIcon(cY.g(this.l));
        this.l.g().setRolloverEnabled(false);
        com.hw.client.util.c.a(this.l.g());
        this.l.g().setEnabled(true);
        this.l.j().a(true);
        this.l.k().a(0);
        this.l.l().setEnabled(true);
        this.l.b(0);
    }
    
    public void l() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("VTRecorder.recordingState");
        }
        this.l.f().setEnabled(false);
        this.l.h().setEnabled(true);
        this.l.i().setDisabledIcon(cY.h(this.l));
        this.l.i().setEnabled(false);
        this.l.g().setIcon(cY.e(this.l));
        this.l.g().setRolloverEnabled(true);
        com.hw.client.util.c.a(this.l.g());
        this.l.g().setEnabled(true);
        this.l.j().a(false);
        this.l.l().setEnabled(false);
        this.l.b(0);
    }
    
    public void m() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioRecorder.pauseRecordState");
        }
        this.l.f().setEnabled(false);
        this.l.h().setEnabled(true);
        this.l.i().setIcon(cY.h(this.l));
        this.l.i().setRolloverEnabled(false);
        com.hw.client.util.c.a(this.l.i());
        this.l.i().setEnabled(true);
        this.l.g().setIcon(cY.g(this.l));
        this.l.g().setRolloverEnabled(false);
        com.hw.client.util.c.a(this.l.g());
        this.l.g().setEnabled(true);
        this.l.j().a(false);
        this.l.k().a(0);
        this.l.l().setEnabled(false);
        this.l.b(0);
    }
    
    public final void a(final List list) {
        com.hw.client.util.a.d("AudioComponent.audioDeviceCB");
        final C a = C.a();
        final boolean showing = cY.i(this.l).isShowing();
        final MenuElement[] subElements = cY.i(this.l).getSubElements();
        for (int i = 0; i > subElements.length; ++i) {
            subElements[i].getComponent().setVisible(false);
        }
        cY.i(this.l).setVisible(false);
        cY.j(this.l).removeAll();
        cY.k(this.l).removeAll();
        final ButtonGroup buttonGroup = new ButtonGroup();
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        AbstractButton abstractButton = null;
        AbstractButton abstractButton2 = null;
        boolean b = false;
        boolean b2 = false;
        final Iterator<s> iterator = list.iterator();
        while (iterator.hasNext()) {
            final s s;
            if ((s = iterator.next()).d()) {
                final JRadioButtonMenuItem radioButtonMenuItem;
                (radioButtonMenuItem = new JRadioButtonMenuItem(s.a())).addActionListener(this.l.n());
                if (s.b()) {
                    abstractButton = radioButtonMenuItem;
                }
                if (s.a().equals(a.c())) {
                    radioButtonMenuItem.setSelected(true);
                    b = true;
                }
                else {
                    radioButtonMenuItem.setSelected(false);
                }
                radioButtonMenuItem.setActionCommand("ACTION_AUDIO_DEVICE_INPUT");
                buttonGroup.add(radioButtonMenuItem);
                cY.j(this.l).add(radioButtonMenuItem);
            }
            if (s.e()) {
                final JRadioButtonMenuItem radioButtonMenuItem2;
                (radioButtonMenuItem2 = new JRadioButtonMenuItem(s.a())).addActionListener(this.l.n());
                if (s.c()) {
                    abstractButton2 = radioButtonMenuItem2;
                }
                if (s.a().equals(a.d())) {
                    radioButtonMenuItem2.setSelected(true);
                    b2 = true;
                }
                else {
                    radioButtonMenuItem2.setSelected(false);
                }
                radioButtonMenuItem2.setSelected(s.a().equals(a.d()));
                radioButtonMenuItem2.setActionCommand("ACTION_AUDIO_DEVICE_OUTPUT");
                buttonGroup2.add(radioButtonMenuItem2);
                cY.k(this.l).add(radioButtonMenuItem2);
            }
        }
        if (!b && abstractButton != null) {
            abstractButton.setSelected(true);
            a.a(abstractButton.getText());
        }
        if (buttonGroup.getButtonCount() == 0) {
            final JMenuItem menuItem;
            (menuItem = new JMenuItem(cY.a(this.l, "options_audio_device_not_found"))).setEnabled(false);
            cY.j(this.l).add(menuItem);
        }
        if (!b2 && abstractButton2 != null) {
            abstractButton2.setSelected(true);
            a.b(abstractButton2.getText());
        }
        if (buttonGroup2.getButtonCount() == 0) {
            final JMenuItem menuItem2;
            (menuItem2 = new JMenuItem(cY.a(this.l, "options_audio_device_not_found"))).setEnabled(false);
            cY.k(this.l).add(menuItem2);
        }
        if (showing) {
            cY.i(this.l).setVisible(true);
        }
    }
    
    public final void a(final int n, final String s) {
        this.l.a(cY.a(this.l, "device.busy"));
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.audioDeviceError, type=" + 1 + ", message=" + s);
        }
        this.l.b(0);
        this.c = false;
        this.l.d = true;
        this.j = null;
        this.c();
    }
    
    public final void a(final int n) {
        this.l.k().a(n);
    }
    
    public final void b(final int n) {
        this.l.k().a(n);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.l.a("");
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()) == null) {
            return;
        }
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioComponent.actionPerformed: ac=" + actionCommand);
        }
        if (actionCommand.equals("ACTION_PLAY")) {
            this.l.l().a().setVisible(false);
            this.a.a(this);
            if (this.q() == 6) {
                this.t();
                return;
            }
            if (this.q() == 7) {
                this.t();
                return;
            }
            this.s();
            this.c(0.0);
        }
        else {
            if (actionCommand.equals("ACTION_STOP")) {
                this.l.l().a().setVisible(false);
                this.a.a(this);
                this.l.p();
                return;
            }
            if (actionCommand.equals("ACTION_PAUSE")) {
                this.l.l().a().setVisible(false);
                this.a.a(this);
                this.t();
                return;
            }
            if (actionCommand.equals("ACTION_RECORD")) {
                this.l.l().a().setVisible(false);
                if (this.q() == 5) {
                    this.t();
                    return;
                }
                this.a.a(this);
                this.a(this.r() && this.l.a);
            }
            else {
                if (actionCommand.equals("ACTION_MENU")) {
                    this.l.l().a().setVisible(false);
                    cY.l(this.l);
                    return;
                }
                if (actionCommand.equals("ACTION_WIMBA")) {
                    this.l.l().a().setVisible(false);
                    if (com.hw.client.util.a.a()) {
                        com.hw.client.util.a.b("Displaying the web site");
                    }
                    final AbstractButton f = this.l.f();
                    final String a = cY.a(this.l, "website");
                    final String s = "_blank";
                    final String s2 = a;
                    final AbstractButton abstractButton = f;
                    try {
                        final AbstractButton abstractButton2 = abstractButton;
                        final URL url = new URL(s2);
                        String s3 = s;
                        final URL url2 = url;
                        final Applet a2;
                        if ((a2 = am.a(abstractButton2)) == null) {
                            com.hw.client.util.a.d("AppletUtils.showDocument(), unable to find applet");
                        }
                        else {
                            final AppletContext appletContext;
                            if ((appletContext = a2.getAppletContext()) != null) {
                                if (s3 == null) {
                                    s3 = new String("_blank");
                                }
                                appletContext.showDocument(url2, s3);
                                return;
                            }
                            com.hw.client.util.a.d("AppletUtils.showDocument(), unable to find applet context");
                        }
                    }
                    catch (Exception ex) {
                        com.hw.client.util.a.a("AppletUtils.showDocument(), exception showing document", ex);
                    }
                    return;
                }
                if (actionCommand.equals("ACTION_SAVE_AS")) {
                    this.l.l().a().setVisible(false);
                    if (cY.o(this.l).isEnabled() && cY.p(this.l) != null) {
                        if (bj.d() && bj.f()) {
                            JOptionPane.showMessageDialog(null, new aT("<html>" + cY.a(this.l, "error_no_disk_access_msg")), cY.a(this.l, "error_no_disk_access_title"), 0);
                            return;
                        }
                        cY.p(this.l).a();
                    }
                    return;
                }
                if (actionCommand.equals("ACTION_AUDIO_DEVICE_INPUT")) {
                    final JRadioButtonMenuItem radioButtonMenuItem;
                    final String text = (radioButtonMenuItem = (JRadioButtonMenuItem)actionEvent.getSource()).getText();
                    com.hw.client.util.a.d("changing input audio device to " + text);
                    radioButtonMenuItem.setSelected(true);
                    final C a3;
                    (a3 = C.a()).a(text);
                    this.a.a(a3.c(), a3.d(), a3.e());
                    return;
                }
                if (actionCommand.equals("ACTION_AUDIO_DEVICE_OUTPUT")) {
                    final JRadioButtonMenuItem radioButtonMenuItem2;
                    final String text2 = (radioButtonMenuItem2 = (JRadioButtonMenuItem)actionEvent.getSource()).getText();
                    com.hw.client.util.a.d("changing output audio device to " + text2);
                    radioButtonMenuItem2.setSelected(true);
                    final C a4;
                    (a4 = C.a()).b(text2);
                    this.a.a(a4.c(), a4.d(), a4.e());
                    return;
                }
                if (actionCommand.equals("ACTION_FILTER_NOISE_REDUCTION")) {
                    C.a().d(cY.m(this.l).getState());
                    return;
                }
                if (actionCommand.equals("ACTION_FILTER_AGC")) {
                    C.a().c(cY.n(this.l).getState());
                }
            }
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final BoundedRangeModel b = this.l.j().b();
        if (changeEvent.getSource() == b) {
            if (b.getValueIsAdjusting()) {
                if (this.q() == 6) {
                    this.s();
                }
                this.k = true;
                this.b(1.0 * b.getValue() / b.getMaximum());
                return;
            }
            if (this.k) {
                this.k = false;
                final double h = 1.0 * b.getValue() / b.getMaximum();
                this.b(h);
                if (this.q() != 7) {
                    if (h < 0.99) {
                        this.c(h);
                        return;
                    }
                    this.b(0.0);
                }
                else {
                    this.g = true;
                    this.h = h;
                }
            }
        }
    }
    
    public final void c(final double n) {
        this.a.a(this);
        this.l.a("");
        if (this.j != null) {
            this.b(n);
            this.a(this.j, this.a(), n);
        }
    }
    
    public final void b(final double n) {
        if (!this.l.j().c().getValueIsAdjusting()) {
            final BoundedRangeModel b = this.l.j().b();
            b.setValue((int)(n * b.getMaximum()));
        }
        if (this.l.n().a() != null) {
            cY.a(this.l, n);
        }
        this.l.b(0);
    }
    
    public final void a(final double n) {
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("AudioComponent.setRecordPosition pos=" + n);
        }
        final BoundedRangeModel b = this.l.j().b();
        b.setValue((int)(n * b.getMaximum()));
        cY.b(this.l, n);
        this.l.b(0);
    }
    
    public void a(final boolean b) {
        this.l.d = false;
    }
}

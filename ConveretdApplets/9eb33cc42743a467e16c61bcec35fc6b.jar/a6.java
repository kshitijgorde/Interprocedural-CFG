import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a6 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            String text = null;
            if (this.cv.jl) {
                text = this.cv.jt.getText();
                if (text.length() == 0) {
                    this.cv.dh.setText("Please specify a server to connect to");
                    return;
                }
                this.cv.e9.ko = true;
                this.cv.e9.f.ci("server", text);
                this.cv.e9.f.gw(text);
                final String ce = this.cv.e9.f.ce("prxpassword");
                this.cv.e9.f.g3();
                if (ce != null) {
                    this.cv.e9.f.ci("prxpassword", ce);
                }
                this.cv.e9.f.g1();
            }
            final String selectedItem = this.cv.j8.getSelectedItem();
            if (selectedItem.equals("custom...")) {
                this.cv.e9.f.ci("authtyp", this.cv.jr.getText());
            }
            else {
                this.cv.e9.f.ci("authtyp", selectedItem);
            }
            this.cv.e9.f.ci("port", this.cv.jz.getText());
            this.cv.e9.f.ci("usrname", this.cv.jy.getText());
            this.cv.e9.f.ci("cipher", this.cv.hz[this.cv.j9.getSelectedIndex()]);
            this.cv.e9.f.ci("idfile", this.cv.jx.getText());
            this.cv.e9.f.ci("display", this.cv.jw.getText());
            this.cv.e9.f.ci("mtu", this.cv.jv.getText());
            this.cv.e9.f.ci("x11fwd", String.valueOf(this.cv.j7.getState()));
            this.cv.e9.f.ci("prvport", String.valueOf(this.cv.j6.getState()));
            this.cv.e9.f.ci("remfwd", String.valueOf(this.cv.j5.getState()));
            this.cv.e9.f.ci("idhost", String.valueOf(this.cv.j4.getState()));
            this.cv.e9.f.ci("forcpty", String.valueOf(this.cv.j_.getState()));
            this.cv.e9.f.ci("portftp", String.valueOf(this.cv.j3.getState()));
            this.cv.e9.f.ci("localhst", String.valueOf(this.cv.jq.getText()));
            if (this.cv.j3.getState()) {
                this.cv.e9.f.ci("realsrv", this.cv.js.getText());
            }
            else {
                this.cv.e9.f.ci("realsrv", "");
            }
            this.cv.e9.f.ci("alive", this.cv.ju.getText());
            if (this.cv.e9.f.hj) {
                final String ih = this.cv.ih("Please set password for alias " + text, "MindTerm - Set File Password");
                if (ih == null) {
                    return;
                }
                this.cv.e9.f.hf(ih);
            }
            if (this.cv.jl) {
                this.cv.e9.kp.ez("Connecting to: " + text);
            }
            this.cv.d_.setVisible(false);
        }
        catch (Exception ex) {
            this.cv.dh.setText(ex.getMessage());
        }
    }
    
    public a6(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}

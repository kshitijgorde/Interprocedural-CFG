import java.io.IOException;
import java.awt.Component;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bc implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String text = this.cv.i1.getText();
        String s = this.cv.i2.getText();
        final String text2 = this.cv.i_.getText();
        int intValue;
        try {
            intValue = Integer.valueOf(this.cv.i3.getText());
            if (intValue < 512 || intValue > 2048) {
                this.cv.iw.setText("Keylength must be in interval 512-2048");
                return;
            }
        }
        catch (Exception ex2) {
            this.cv.iw.setText("Keylength must be an integer");
            return;
        }
        if (!text.equals(this.cv.i0.getText())) {
            this.cv.iw.setText("Please give same password twice");
            return;
        }
        try {
            if (s.length() == 0) {
                throw new Exception();
            }
            if (s.indexOf(File.separator) == -1) {
                s = String.valueOf(this.cv.e9.f.hc()) + s;
            }
            new FileOutputStream(s).close();
        }
        catch (Exception ex3) {
            this.cv.iw.setText("File can't be written to, please check");
            return;
        }
        this.cv.i5.remove(this.cv.iw);
        this.cv.iw.setText("Please wait while generating key...");
        this.cv.i5.add(this.cv.iw);
        this.cv.iv.setEnabled(false);
        try {
            this.cv.e9.kp.eo(ca.l7(ca.l6(intValue, new cd(this.cv.is)), s, text, text2));
            this.cv.e9.kp.en(true);
            this.cv.iw.setText("");
            this.cv.ij("Key is now generated and saved to file and clipboard");
            if (this.cv.iy.getState()) {
                this.cv.e9.f.ci("idfile", s);
            }
        }
        catch (IOException ex) {
            this.cv.ij("Error saving identity: " + ex.getMessage());
            this.cv.iw.setText("An error occured while saving identity");
        }
        this.cv.i1.setText("");
        this.cv.i0.setText("");
        this.cv.iu = 0;
        this.cv.it = 0;
        this.cv.ix.n0(0);
        this.cv.ik();
    }
    
    public bc(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import VT_6_1_0_11.ca;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.au;
import javax.swing.JOptionPane;
import java.awt.Color;
import com.hw.client.util.a;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import VT_6_1_0_11.cP;
import VT_6_1_0_11.aT;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Component;
import java.io.File;
import java.net.URL;
import VT_6_1_0_11.l;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public final class e extends JDialog implements ActionListener, Runnable
{
    private JProgressBar a;
    private JLabel b;
    private JButton c;
    private l d;
    private boolean e;
    private URL f;
    private File g;
    
    public e(final l l) {
        this.e = false;
        this.d = l;
        this.setTitle(l.e("export_progress_title"));
        this.setLocationRelativeTo(l);
        l.E().a(this);
        this.setSize(new Dimension(300, 150));
        this.a = new JProgressBar(0, 100);
        this.b = new aT("<html>");
        (this.c = new cP(l.e("btn_cancel"))).addActionListener(this);
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(this.b, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 10, 2, new Insets(10, 10, 10, 10), 0, 0));
        this.getContentPane().add(this.a, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 10, 2, new Insets(10, 10, 10, 10), 0, 0));
        this.getContentPane().add(this.c, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 14, 0, new Insets(10, 10, 10, 10), 0, 0));
    }
    
    private void a(final int value) {
        this.a.setValue(value);
    }
    
    private void a(final String text) {
        this.b.setText(text);
    }
    
    public final void a(final URL f, final File g) {
        if (f == null) {
            throw new IllegalArgumentException("ExportProgressFrame.open: exportUrl is null");
        }
        if (g == null) {
            throw new IllegalArgumentException("ExportProgressFrame.open: exportFile is null");
        }
        this.f = f;
        this.g = g;
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("ExportProgressFrame.open: exportUrl=" + g + ", exportFile=" + g);
        }
        this.b.setForeground(Color.black);
        this.c.setEnabled(true);
        this.e = false;
        this.a(0);
        this.a.setIndeterminate(true);
        this.a(this.d.e("export_progress_exporting_to", g.getName()));
        this.pack();
        this.setLocationRelativeTo(this.d);
        this.setVisible(true);
        new Thread(this).start();
    }
    
    public final void setVisible(final boolean b) {
        super.setVisible(b);
        if (this.d != null) {
            this.d.E().setVisible(b);
        }
    }
    
    private void b(final String s) {
        if ("recorder".equals(this.d.z())) {
            JOptionPane.showMessageDialog(this.d, this.d.e("export_progress_error_msg", s), this.d.e("export_progress_error_title"), 0);
            return;
        }
        this.d.F().a(this.d, this.d.e("export_progress_error_title"), this.d.e("export_progress_error_msg", s), this.d.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.c) {
            this.e = true;
            this.c.setEnabled(false);
            this.b.setForeground(Color.red);
            this.a(this.d.e("export_progress_cancelling"));
        }
    }
    
    public final void run() {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            final URLConnection a;
            inputStream = (a = ca.a(this.f, 0 != 0)).getInputStream();
            final int contentLength = a.getContentLength();
            if (com.hw.client.util.a.b()) {
                com.hw.client.util.a.c("ExportProgressFrame.run: connection.getContentLength()=" + contentLength);
            }
            if (contentLength <= 0) {
                com.hw.client.util.a.e("ExportProgressFrame.run: failed because connection.getContentLength()=" + contentLength);
                this.b(this.d.e("export_progress_error_empty_file"));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (IOException ex2) {}
                }
                if (this.g != null) {
                    this.g.delete();
                }
                this.setVisible(false);
                return;
            }
            fileOutputStream = new FileOutputStream(this.g);
            final byte[] array = new byte[1024];
            int n = 0;
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1 && !this.e) {
                if (n == 0) {
                    this.a.setIndeterminate(false);
                }
                fileOutputStream.write(array, 0, read);
                n += read;
                this.a(n * 100 / contentLength);
            }
            if (this.e) {
                this.setVisible(false);
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex3) {}
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (IOException ex4) {}
                }
                if (this.g != null) {
                    this.g.delete();
                }
                this.setVisible(false);
                return;
            }
            if (contentLength != n) {
                throw new IOException("Content-Length is " + contentLength + " and read " + n + " bytes");
            }
            try {
                fileOutputStream.close();
            }
            catch (IOException ex5) {}
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex6) {}
            }
            this.setVisible(false);
        }
        catch (Exception ex) {
            this.g.delete();
            com.hw.client.util.a.b("ExportProgressFrame,run: exc=" + ex + ", url=" + this.f, ex);
            this.b(ex.getMessage());
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex7) {}
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex8) {}
            }
            if (this.g != null) {
                this.g.delete();
            }
            this.setVisible(false);
        }
    }
}

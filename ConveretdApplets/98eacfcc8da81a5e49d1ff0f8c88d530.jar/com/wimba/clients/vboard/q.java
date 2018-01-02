// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.awt.event.ActionEvent;
import java.net.URLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import VT_6_1_0_11.ca;
import java.net.URL;
import java.net.URLEncoder;
import VT_6_1_0_11.cs;
import com.hw.client.util.a;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import VT_6_1_0_11.cP;
import VT_6_1_0_11.aT;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Font;
import VT_6_1_0_11.h;
import javax.swing.ListModel;
import javax.swing.JList;
import java.awt.Dimension;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import VT_6_1_0_11.bD;

public final class q extends bD implements ActionListener, Runnable
{
    private JProgressBar a;
    private JLabel b;
    private JButton c;
    private JButton d;
    private DefaultListModel e;
    private n f;
    private boolean g;
    private boolean h;
    private File[] i;
    
    public q(final n f) {
        super(f.e("import_progress_title"));
        this.g = false;
        this.h = false;
        this.f = f;
        this.setClosable(false);
        this.setMaximumSize(new Dimension(320, 320));
        this.e = new DefaultListModel();
        final JList list;
        (list = new JList(this.e)).setFont(new Font(VT_6_1_0_11.h.a(), 0, 12));
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(list)).getViewport().setBackground(Color.white);
        scrollPane.setVerticalScrollBarPolicy(20);
        scrollPane.setHorizontalScrollBarPolicy(30);
        scrollPane.setBorder(VT_6_1_0_11.h.f());
        this.a = new JProgressBar(0, 100);
        this.b = new aT("<html>");
        (this.c = new cP(f.e("btn_cancel"))).addActionListener(this);
        (this.d = new cP(f.e("btn_ok"))).addActionListener(this);
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(this.b, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 10, 2, new Insets(10, 10, 10, 10), 0, 0));
        this.getContentPane().add(this.a, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 10, 2, new Insets(10, 10, 10, 10), 0, 0));
        this.getContentPane().add(scrollPane, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
        final JPanel panel;
        (panel = new JPanel(new FlowLayout())).setOpaque(false);
        panel.add(this.c);
        panel.add(this.d);
        this.getContentPane().add(panel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(10, 10, 10, 10), 0, 0));
    }
    
    private void a(final int value) {
        this.a.setValue(value);
    }
    
    private void a(final String s) {
        this.b.setText("<html>" + s + "</html>");
    }
    
    public final void a(final File[] i, final boolean h) {
        this.i = i;
        this.g = false;
        this.d.setEnabled(false);
        this.c.setEnabled(true);
        this.b.setForeground(Color.black);
        this.e.removeAllElements();
        this.setVisible(true);
        this.h = h;
        new Thread(this).start();
    }
    
    public final void run() {
        boolean b = false;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("ImportProgressFrame.run: files: " + this.i);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        for (int n = 0; n < this.i.length && !this.g; ++n) {
            try {
                com.hw.client.util.a.c("import ok: " + new String(this.a(this.i[n], currentTimeMillis, n == this.i.length - 1)));
                this.e.addElement("<html><img src=\"" + this.getClass().getClassLoader().getResource("images/common/v.png").toExternalForm() + "\"></img>&nbsp;" + cs.a.a(this.i[n].getName()));
            }
            catch (Exception ex) {
                if (!this.g) {
                    b = true;
                    com.hw.client.util.a.a("ImportProgressFrame.run: failed importing " + this.i[n].getName(), ex);
                    this.e.addElement("<html><img src=\"" + this.getClass().getClassLoader().getResource("images/common/x.png").toExternalForm() + "\"></img>&nbsp;" + cs.a.a(this.i[n].getName()));
                }
            }
        }
        if (b) {
            this.a.setIndeterminate(false);
            this.a.setValue(100);
            this.d.setEnabled(true);
            this.c.setEnabled(false);
            this.b.setForeground(Color.red);
            this.a(this.f.e("import_progress_report_failed"));
            return;
        }
        this.setVisible(false);
        this.f.b((String)null);
    }
    
    private byte[] a(final File file, final long n, final boolean b) {
        FileInputStream fileInputStream = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            this.a(this.f.e("import_progress_importing", file.getName()));
            this.a.setIndeterminate(false);
            this.a(0);
            final long length = file.length();
            String s = this.f.k + "/import?sid=" + URLEncoder.encode(this.f.a.a(), "UTF-8") + "&rid=" + URLEncoder.encode(this.f.j, "UTF-8") + "&filename=" + URLEncoder.encode(file.getName(), "UTF-8") + "&session=" + URLEncoder.encode("" + n, "UTF-8") + "&author=" + URLEncoder.encode(this.f.U(), "UTF-8");
            final String t;
            if ((t = this.f.T()) != null) {
                s = s + "&email=" + URLEncoder.encode(t, "UTF-8");
            }
            final URLConnection a;
            (a = ca.a(new URL(this.f.g, s + "&importOption=" + this.h))).setUseCaches(false);
            a.setDoOutput(true);
            a.setDoInput(true);
            a.setRequestProperty("Content-Type", "application/octet-stream");
            a.setRequestProperty("Content-Length", "" + length);
            outputStream = a.getOutputStream();
            fileInputStream = new FileInputStream(file);
            final byte[] array = new byte[1024];
            int n2 = 0;
            int read;
            while (!this.g && (read = fileInputStream.read(array, 0, array.length)) != -1) {
                outputStream.write(array, 0, read);
                n2 += read;
                this.a((int)(n2 * 100 / length));
            }
            outputStream.flush();
            outputStream.close();
            outputStream = null;
            fileInputStream.close();
            fileInputStream = null;
            if (this.g) {
                throw new IOException(this.f.e("btn_cancel"));
            }
            this.a(this.f.e("import_progress_transcoding", file.getName()));
            this.a.setIndeterminate(true);
            if (b) {
                this.c.setEnabled(false);
            }
            a.connect();
            inputStream = a.getInputStream();
            final int contentLength = a.getContentLength();
            int n3 = 0;
            if (contentLength < 0) {
                throw new IOException("ImportProgressFrame.importFile: URLConnection returns content length <0");
            }
            byte[] array2;
            int read2;
            for (array2 = new byte[contentLength]; n3 < contentLength && (read2 = inputStream.read(array2, n3, contentLength - n3)) != -1; n3 += read2) {}
            final String s2 = new String(array2);
            if (n3 != contentLength) {
                throw new IOException("ImportProgressFrame.importFile: bad nb bytes read");
            }
            if (!new String(array2).startsWith("ok")) {
                throw new IOException(s2);
            }
            inputStream.close();
            inputStream = null;
            return array2;
        }
        finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.c) {
            this.a.setIndeterminate(true);
            this.g = true;
            this.c.setEnabled(false);
            this.b.setForeground(Color.red);
            this.a(this.f.e("import_progress_cancelling"));
            return;
        }
        if (actionEvent.getSource() == this.d) {
            this.setVisible(false);
            this.f.b((String)null);
        }
    }
}

import java.util.Hashtable;
import java.awt.List;
import java.awt.Choice;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.awt.Frame;
import java.awt.FileDialog;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Dialog;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Properties
{
    public boolean s;
    public static Dialog r;
    public static TextField o;
    public static TextField m;
    public static Label l;
    public static Label k;
    public static Checkbox j;
    public static Checkbox i;
    public static FileDialog h;
    public static boolean g;
    public static as f;
    public static bo c;
    public static Frame a;
    
    public a() {
        this.s = false;
    }
    
    public a(final Properties properties) {
        super(properties);
        this.s = false;
    }
    
    public final boolean g() {
        return this.s;
    }
    
    public final synchronized void f(final OutputStream outputStream, final String s, final String s2, final String s3) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Properties properties = new Properties();
        final ci ne = ci.ne(s3);
        if (ne == null) {
            throw new IOException("Unknown cipher '" + s3 + "'");
        }
        this.save(byteArrayOutputStream, s);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        final int length = byteArray.length;
        byte[] nd;
        try {
            final ch ne2 = ch.ne("MD5");
            ne2.c3(byteArray);
            nd = ne2.nd();
        }
        catch (Exception ex) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        final byte[] j = c.j(nd);
        final String s4 = new String(j);
        final byte[] array = new byte[byteArray.length + (8 - byteArray.length % 8)];
        System.arraycopy(byteArray, 0, array, 0, byteArray.length);
        final byte[] array2 = new byte[array.length];
        ne.m9(String.valueOf(s4) + s2);
        ne.nb(array, 0, array2, 0, array2.length);
        final byte[] i = c.j(array2);
        ((Hashtable<String, String>)properties).put("EncryptedProperties.hash", new String(j));
        ((Hashtable<String, String>)properties).put("EncryptedProperties.cipher", s3);
        ((Hashtable<String, String>)properties).put("EncryptedProperties.contents", new String(i));
        ((Hashtable<String, String>)properties).put("EncryptedProperties.size", String.valueOf(length));
        properties.save(outputStream, "Sealed with mindbright.util.EncryptedProperties(ver. $Name: rel1-2-1 $$Date: 2000/08/01 20:37:09 $)");
        outputStream.flush();
    }
    
    public final synchronized void e(final InputStream inputStream, final String s) throws IOException, cj {
        final Properties properties = new Properties();
        properties.load(inputStream);
        final String property = properties.getProperty("EncryptedProperties.hash");
        final String property2 = properties.getProperty("EncryptedProperties.cipher");
        final String property3 = properties.getProperty("EncryptedProperties.contents");
        final String property4 = properties.getProperty("EncryptedProperties.size");
        if (property == null && property2 == null && property3 == null && property4 == null) {
            this.s = true;
            final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                ((Hashtable<String, String>)this).put(s2, properties.getProperty(s2));
            }
            return;
        }
        final int int1 = Integer.parseInt(property4);
        final byte[] i = c.i(property.getBytes());
        final byte[] j = c.i(property3.getBytes());
        final ci ne = ci.ne(property2);
        if (ne == null) {
            throw new IOException("Unknown cipher '" + property2 + "'");
        }
        ne.m9(String.valueOf(property) + s);
        ne.na(j, 0, j, 0, j.length);
        final byte[] array = new byte[int1];
        System.arraycopy(j, 0, array, 0, int1);
        final byte[] array2 = array;
        byte[] nd;
        try {
            final ch ne2 = ch.ne("MD5");
            ne2.c3(array2);
            nd = ne2.nd();
        }
        catch (Exception ex) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        for (int k = 0; k < i.length; ++k) {
            if (i[k] != nd[k]) {
                throw new cj("Access denied");
            }
        }
        this.load(new ByteArrayInputStream(array2));
    }
    
    public static void d(final String title, final Frame a, final as f, final bo c) {
        a.a = a;
        a.f = f;
        a.c = c;
        if (a.r == null) {
            a.r = new Dialog(a.a, title, false);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            (a.h = new FileDialog(a.a, "MindTerm - Select file to copy", 0)).setDirectory(a.f.hc());
            a.r.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(8, 4, 4, 8);
            layout.setConstraints(a.l = new Label("Copy from local files/directories:"), gridBagConstraints);
            a.r.add(a.l);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(a.o = new TextField("", 38), gridBagConstraints);
            a.r.add(a.o);
            a.o.setText(a.h.getDirectory());
            gridBagConstraints.gridwidth = 1;
            final Button button = new Button("...");
            button.addActionListener(new aj());
            gridBagConstraints.fill = 0;
            layout.setConstraints(button, gridBagConstraints);
            a.r.add(button);
            a.g = true;
            gridBagConstraints.gridy = 2;
            final Button button2 = new Button("Change Direction");
            button2.addActionListener(new ai());
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(button2, gridBagConstraints);
            a.r.add(button2);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = 2;
            layout.setConstraints(a.k = new Label("To directory/file on server:"), gridBagConstraints);
            a.r.add(a.k);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(a.m = new TextField("", 38), gridBagConstraints);
            a.r.add(a.m);
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(a.j = new Checkbox("Recursive copy", false), gridBagConstraints);
            a.r.add(a.j);
            layout.setConstraints(a.i = new Checkbox("Low priority", false), gridBagConstraints);
            a.r.add(a.i);
            gridBagConstraints.gridy = 6;
            gridBagConstraints.gridwidth = 2;
            final Panel panel = new Panel(new FlowLayout());
            final Button button3;
            panel.add(button3 = new Button("Start Copy"));
            button3.addActionListener(new ah());
            final Button button4;
            panel.add(button4 = new Button("Close Dialog"));
            button4.addActionListener(new f(a.r));
            a(a.r, new d(button3, button4), null);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(panel, gridBagConstraints);
            a.r.add(panel);
            a.r.addWindowListener(new e(button4));
            b(a.r);
            a.r.setResizable(true);
            a.r.pack();
        }
        a.r.setTitle(title);
        c(a.r);
        a.r.setVisible(true);
    }
    
    public static final void c(final Dialog dialog) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = dialog.getSize();
        dialog.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
    }
    
    public static final void b(final Container container) {
        final Component[] components = container.getComponents();
        container.setBackground(SystemColor.menu);
        for (int i = 0; i < components.length; ++i) {
            if (!(components[i] instanceof Choice)) {
                components[i].setBackground(SystemColor.menu);
                if (components[i] instanceof Container) {
                    b((Container)components[i]);
                }
                else if (!(components[i] instanceof Choice)) {
                    if (components[i] instanceof TextField || components[i] instanceof List) {
                        components[i].setBackground(SystemColor.text);
                    }
                    else {
                        components[i].setBackground(SystemColor.menu);
                    }
                }
            }
        }
    }
    
    public static final void a(final Container container, final KeyListener keyListener, final Class clazz) {
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (!(components[i] instanceof Choice)) {
                if (components[i] instanceof Container) {
                    a((Container)components[i], keyListener, clazz);
                }
                else if (components[i] != null && (clazz == null || clazz.isInstance(components[i]))) {
                    components[i].addKeyListener(keyListener);
                }
            }
        }
    }
}

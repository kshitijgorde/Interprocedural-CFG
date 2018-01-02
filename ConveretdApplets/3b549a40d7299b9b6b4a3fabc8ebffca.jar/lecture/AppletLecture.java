// 
// Decompiled by Procyon v0.5.30
// 

package lecture;

import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.Component;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Panel;
import java.io.ByteArrayOutputStream;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.Applet;

public class AppletLecture extends Applet
{
    private Button for;
    private Button try;
    private BorderLayout c;
    private BorderLayout b;
    private BorderLayout void;
    private Choice if;
    Dimension null;
    private boolean e;
    private Label int;
    private boolean else;
    ByteArrayOutputStream new;
    private Panel long;
    private Panel goto;
    private Panel char;
    private Panel case;
    private Panel byte;
    b do;
    String a;
    String d;
    
    public AppletLecture() {
        this.e = false;
        this.c = new BorderLayout();
        this.long = new Panel();
        this.b = new BorderLayout();
        this.goto = new Panel();
        this.try = new Button();
        this.void = new BorderLayout();
        this.char = new Panel();
        this.case = new Panel();
        this.for = new Button();
        this.if = new Choice();
        this.byte = new Panel();
        this.int = new Label();
        this.else = false;
    }
    
    void if() {
        this.if.removeAll();
        final String string = this.a.substring(0, this.a.indexOf(63)) + "?type_aff=6";
        URL url;
        try {
            url = new URL(string);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error: " + ex.toString());
            return;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    this.if.add(line);
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex2) {
            System.out.println("Error: " + ex2.toString());
        }
    }
    
    public void a(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public void for() {
        final String selectedItem = this.if.getSelectedItem();
        final Component a = this.do.a();
        final BufferedImage bufferedImage = new BufferedImage(a.getWidth(), a.getHeight(), 1);
        final Graphics2D graphics = bufferedImage.createGraphics();
        a.paintAll(graphics);
        graphics.dispose();
        try {
            this.new = new ByteArrayOutputStream();
            final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder((OutputStream)this.new);
            final JPEGEncodeParam defaultJPEGEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(bufferedImage);
            defaultJPEGEncodeParam.setQuality(1.0f, false);
            jpegEncoder.setJPEGEncodeParam(defaultJPEGEncodeParam);
            jpegEncoder.encode(bufferedImage);
            this.new.close();
            bufferedImage.flush();
        }
        catch (IOException ex) {
            System.out.println("l'exportation au format JPG a \u00e9chou\u00e9 " + ex);
        }
        URL url = null;
        try {
            url = new URL(this.a.substring(0, this.a.indexOf(63)) + "?type_aff=8&numgraphique=" + selectedItem);
        }
        catch (MalformedURLException ex3) {}
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write("donnees=" + new c().if(this.new.toByteArray()));
            outputStreamWriter.flush();
            outputStreamWriter.close();
            while (new BufferedReader(new InputStreamReader(openConnection.getInputStream())).readLine() != null) {}
        }
        catch (IOException ex2) {
            System.out.println("Erreur: " + ex2.toString());
        }
        try {
            this.getAppletContext().showDocument(new URL(this.a.substring(0, this.a.indexOf(63)) + "?type_aff=9&numgraphique=" + this.if.getSelectedItem()), "_blank");
        }
        catch (Exception ex4) {}
    }
    
    public String a() {
        return this.a;
    }
    
    public String do() {
        return this.d;
    }
    
    void if(final ActionEvent actionEvent) {
        this.for.setEnabled(false);
        this.do.if(this.a.substring(0, this.a.indexOf(63)) + "?type_aff=7&numgraphique=" + this.if.getSelectedItem());
        this.for.setEnabled(true);
    }
    
    void a(final ActionEvent actionEvent) {
        this.for();
    }
    
    void a(final ItemEvent itemEvent) {
    }
    
    public void destroy() {
        System.exit(0);
    }
    
    public String getAppletInfo() {
        return "Lecture graphique";
    }
    
    public String a(final String s, final String s2) {
        return this.e ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "param0", "String", "" }, { "param1", "String", "" } };
    }
    
    public void init() {
        try {
            this.a = this.a("param0", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.d = this.a("param1", "");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        this.setBackground(Color.white);
        try {
            this.int();
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    private void int() throws Exception {
        if (Integer.valueOf(System.getProperty("java.version").substring(2, 3)) >= 2) {
            this.else = true;
        }
        this.null = this.getSize();
        this.setLayout(this.c);
        (this.do = new b(this)).setSize(this.null.width, this.null.height - 50);
        this.long.setLayout(this.b);
        this.try.setLabel("Convert this graphic into image *");
        this.try.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AppletLecture.this.a(actionEvent);
            }
        });
        this.goto.setLayout(this.void);
        this.for.setLabel("View");
        this.for.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AppletLecture.this.if(actionEvent);
            }
        });
        this.int.setText("View graphic n°=");
        this.if.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                AppletLecture.this.a(itemEvent);
            }
        });
        this.add(this.do, "Center");
        this.add(this.long, "South");
        this.long.add(this.goto, "South");
        this.goto.add(this.char, "West");
        this.char.add(this.int, null);
        this.char.add(this.if, null);
        this.char.add(this.for, null);
        this.goto.add(this.case, "East");
        if (this.else) {
            this.case.add(this.try, null);
        }
        this.goto.add(this.byte, "Center");
        this.if();
    }
    
    public void if(final String s) {
    }
    
    public void start() {
    }
    
    public void stop() {
    }
}

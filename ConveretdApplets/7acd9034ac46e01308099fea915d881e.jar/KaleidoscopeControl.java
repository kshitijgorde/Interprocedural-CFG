import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Label;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class KaleidoscopeControl extends Panel
{
    Kaleidoscope applet;
    URL imageURL;
    String imageURLString;
    Dimension canvasSize;
    int triangleSize;
    int updateInterval;
    String[] imageFileList;
    Choice imageSelector;
    TextField userImageURL;
    TextField updateIntervalText;
    TextField triangleSizeText;
    
    KaleidoscopeControl(final Kaleidoscope applet) {
        this.applet = applet;
        this.initVariable();
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel.add(new Button("start"));
        panel.add(new Button("stop"));
        panel.add(new Button("restart"));
        panel.add(new Label("  update interval (ms):"));
        panel.add(this.updateIntervalText = new TextField(Integer.toString(this.updateInterval)));
        panel2.add(new Label("select image:"));
        this.imageSelector = new Choice();
        for (int i = 0; i < this.imageFileList.length; ++i) {
            this.imageSelector.addItem(this.imageFileList[i]);
        }
        this.imageSelector.addItem("From URL");
        panel2.add(this.imageSelector);
        panel2.add(new Label("triangle size:"));
        panel2.add(this.triangleSizeText = new TextField(Integer.toString(this.triangleSize)));
        panel3.add(new Label("Image from URL:"));
        panel3.add(this.userImageURL = new TextField(this.imageURLString, 40));
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
    }
    
    void initVariable() {
        try {
            this.updateInterval = Integer.valueOf(this.applet.getParameter("interval"));
        }
        catch (Exception ex) {
            this.updateInterval = 1000;
        }
        try {
            this.triangleSize = Integer.valueOf(this.applet.getParameter("trianglesize"));
        }
        catch (Exception ex2) {
            this.triangleSize = 76;
        }
        try {
            this.imageURLString = this.applet.getParameter("imageURL");
        }
        catch (Exception ex3) {
            this.imageURLString = "";
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.applet.getParameter("localfiles"));
            this.imageFileList = new String[stringTokenizer.countTokens()];
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                this.imageFileList[n] = stringTokenizer.nextToken();
                ++n;
            }
        }
        catch (Exception ex4) {
            this.imageFileList = new String[0];
        }
        int intValue;
        try {
            intValue = Integer.valueOf(this.applet.getParameter("canvaswidth"));
        }
        catch (Exception ex5) {
            intValue = 400;
        }
        int intValue2;
        try {
            intValue2 = Integer.valueOf(this.applet.getParameter("canvasheight"));
        }
        catch (Exception ex6) {
            intValue2 = 300;
        }
        this.canvasSize = new Dimension(intValue, intValue2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if ("start".equals(o)) {
                this.applet.start();
                return true;
            }
            if ("stop".equals(o)) {
                this.applet.stop();
                return true;
            }
            if ("restart".equals(o)) {
                this.applet.stop();
                this.applet.resetCreator();
                this.applet.start();
                return true;
            }
        }
        else {
            if (event.target instanceof Choice) {
                this.applet.stop();
                this.applet.resetCreator();
                this.applet.start();
                return true;
            }
            if (event.target instanceof TextField) {
                if (event.target == this.updateIntervalText) {
                    try {
                        this.updateInterval = Integer.valueOf((String)o);
                    }
                    catch (Exception ex) {
                        this.updateInterval = 1000;
                        this.updateIntervalText.setText(Integer.toString(this.updateInterval));
                    }
                }
                else {
                    if (event.target == this.triangleSizeText) {
                        try {
                            this.triangleSize = Integer.valueOf((String)o);
                        }
                        catch (Exception ex2) {
                            this.triangleSize = 0;
                        }
                        if (this.triangleSize < 10) {
                            this.triangleSize = 76;
                            this.triangleSizeText.setText(Integer.toString(this.triangleSize));
                        }
                        this.applet.stop();
                        this.applet.resetCreator();
                        this.applet.start();
                        return true;
                    }
                    if (event.target == this.userImageURL) {
                        this.imageSelector.select("From URL");
                        this.applet.stop();
                        this.applet.resetCreator();
                        this.applet.start();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public URL getImageURL() {
        final String selectedItem = this.imageSelector.getSelectedItem();
        if ("From URL".equals(selectedItem)) {
            final String text = this.userImageURL.getText();
            try {
                this.imageURL = new URL(text);
                return this.imageURL;
            }
            catch (Exception ex) {
                System.err.println("user URL is not valid");
                return null;
            }
        }
        try {
            this.imageURL = new URL(this.applet.getDocumentBase(), selectedItem);
        }
        catch (Exception ex2) {
            System.err.println("filename " + selectedItem + " is not valid");
            return null;
        }
        return this.imageURL;
    }
    
    public Dimension getCanvasSize() {
        return this.canvasSize;
    }
    
    public int getUpdateInterval() {
        return this.updateInterval;
    }
    
    public int getTriangleSize() {
        return this.triangleSize;
    }
    
    public int getMethod() {
        return 0;
    }
    
    public Dimension preferredSize() {
        return new Dimension(400, 130);
    }
}

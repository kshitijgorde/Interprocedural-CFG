import java.util.Date;
import java.awt.event.ItemEvent;
import en.JavaFX;
import en.gui.components.xGUI;
import en.gui.components.MemoryDotEvent;
import en.TextOutputDialog;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import en.products.AppletPair;
import java.awt.Component;
import en.products.LightRaysGUI$1;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Cursor;
import en.javafx.Parameter;
import en.products.LightRaysGUI$MemorySet;
import java.util.Vector;
import en.gui.components.xMemoryDots;
import en.gui.components.xChoice;
import en.gui.components.xButton;
import en.gui.components.xSwitch;
import en.gui.components.xSlider;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import en.products.JavaFXGUI;
import en.gui.components.MemoryDotListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LightRaysGUI extends Applet implements MemoryDotListener, JavaFXGUI, MouseListener, ItemListener
{
    public boolean isStandalone;
    public Image image;
    public Image sliderBarImage;
    public Image sliderImage;
    public Image switchOffImage;
    public Image switchOnImage;
    public Image buttonOffImage;
    public Image buttonOnImage;
    public Image dotDefault;
    public Image dotOn;
    public Image dotOff;
    public xSlider alphaSlider;
    public xSlider lightSizeXSlider;
    public xSlider lightSizeYSlider;
    public xSlider motionScaleXSlider;
    public xSlider motionScaleYSlider;
    public xSlider motionSpeedSlider;
    public xSlider pulsateSpeedSlider;
    public xSwitch interactiveSwitch;
    public xSwitch foregroundSwitch;
    public xSwitch backgroundSwitch;
    public xSwitch frameSwitch;
    public xButton htmlButton;
    public xChoice blendChoice;
    public xChoice motionChoice;
    public xChoice presetChoice;
    public xMemoryDots memoryDots;
    public Vector xGUIs;
    public LightRaysGUI$MemorySet[] presets;
    public LightRaysPersonal vlights;
    public String[] names;
    public String[] Default;
    public String[] DeepZoom;
    public String[] Psychedelic;
    public String[] DeepForest;
    public String[] RainbowSatin1;
    public String[] RainbowSatin2;
    public String[] GoldSatin;
    public Parameter presetParm;
    public Cursor fingerCursor;
    public Cursor defaultCursor;
    
    public LightRaysGUI() {
        this.isStandalone = false;
        this.blendChoice = new xChoice();
        this.motionChoice = new xChoice();
        this.presetChoice = new xChoice();
        this.xGUIs = new Vector();
        this.presets = new LightRaysGUI$MemorySet[100];
        this.vlights = null;
        this.names = new String[] { "blendmode", "lightPulsateSpeed", "lightMotionType", "lightMotionSpeed", "lightMotionScaleX", "lightMotionScaleY", "lightRadiusX", "lightRadiusY", "lightAlpha", "lightAlphaMin", "lightInteractive", "foregroundImage", "backgroundImage", "frame", "mask", "foregroundActive", "backgroundActive", "frameActive" };
        this.Default = new String[] { "5", "1.0", "0", "0.5", "0.8", "0.8", "0.05", "0.05", "0.9", "0.15", "true", "lightrays/fore3.jpg", "lightrays/back3.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "true" };
        this.DeepZoom = new String[] { "5", "0.20725387", "6", "0.0", "0.47668394", "0.47668394", "0.07694301", "0.07694301", "0.9481865", "0.15", "true", "lightrays/preset_flame.jpg", "lightrays/back3.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "false" };
        this.Psychedelic = new String[] { "7", "0.0", "1", "0.18134715", "0.68393785", "0.47668394", "0.09233161", "0.09233161", "0.92746115", "0.15", "true", "lightrays/fore3.jpg", "lightrays/back3.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "true" };
        this.DeepForest = new String[] { "7", "0.0", "0", "0.1554404", "0.37823835", "0.38860103", "0.47704664", "0.47704664", "0.7564767", "0.15", "true", "lightrays/fractal_windtree.jpg", "lightrays/cloth_gray.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "false", "false" };
        this.RainbowSatin1 = new String[] { "2", "0.0", "0", "0.18134715", "0.16062176", "0.16062176", "0.08207254", "0.08720207", "0.92746115", "0.15", "true", "lightrays/spectrum.jpg", "lightrays/cloth_gray.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "false" };
        this.RainbowSatin2 = new String[] { "7", "0.0", "4", "0.3626943", "0.29015544", "0.2849741", "0.11797928", "0.11797928", "0.91709846", "0.15", "true", "lightrays/preset_flame.jpg", "lightrays/cloth_gray.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "false" };
        this.GoldSatin = new String[] { "5", "0.0", "4", "0.3626943", "0.29015544", "0.2849741", "0.11797928", "0.11797928", "0.93264246", "0.15", "true", "lightrays/preset_flame2.jpg", "lightrays/cloth_gray.jpg", "lightrays/frame3.jpg", "lightrays/mask3.jpg", "true", "true", "false" };
        this.presetParm = new Parameter("presets", new db(0), new db(0), new db(0), new db(7));
        this.fingerCursor = new Cursor(12);
        this.defaultCursor = new Cursor(0);
    }
    
    public final void init() {
        try {
            final String string = this.getCodeBase().toString();
            this.image = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/gui2.jpg")));
            this.sliderBarImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/sliderBar.jpg")));
            this.sliderImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/slider.jpg")));
            this.switchOffImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/switchOff.jpg")));
            this.switchOnImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/switchOn.jpg")));
            this.buttonOffImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/buttonOff.jpg")));
            this.buttonOnImage = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/buttonOn.jpg")));
            this.dotDefault = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/memorydotDefault.jpg")));
            this.dotOn = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/memorydotOn.jpg")));
            this.dotOff = this.getImage(new URL(String.valueOf(String.valueOf(string)).concat("lightrays/memorydotOff.jpg")));
            this.alphaSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.lightSizeXSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.lightSizeYSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.motionScaleXSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.motionScaleYSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.motionSpeedSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.pulsateSpeedSlider = new xSlider(this.sliderBarImage, this.sliderImage, 4, 197, 4, 5);
            this.interactiveSwitch = new xSwitch(this.switchOffImage, this.switchOnImage);
            this.foregroundSwitch = new xSwitch(this.switchOffImage, this.switchOnImage);
            this.backgroundSwitch = new xSwitch(this.switchOffImage, this.switchOnImage);
            this.frameSwitch = new xSwitch(this.switchOffImage, this.switchOnImage);
            this.htmlButton = new xButton(this.buttonOffImage, this.buttonOnImage);
            this.memoryDots = new xMemoryDots(this.dotDefault, this.dotOn, this.dotOff);
            this.xGUIs.addElement(this.alphaSlider);
            this.xGUIs.addElement(this.lightSizeXSlider);
            this.xGUIs.addElement(this.lightSizeYSlider);
            this.xGUIs.addElement(this.motionScaleXSlider);
            this.xGUIs.addElement(this.motionScaleYSlider);
            this.xGUIs.addElement(this.motionSpeedSlider);
            this.xGUIs.addElement(this.pulsateSpeedSlider);
            this.xGUIs.addElement(this.interactiveSwitch);
            this.xGUIs.addElement(this.foregroundSwitch);
            this.xGUIs.addElement(this.backgroundSwitch);
            this.xGUIs.addElement(this.frameSwitch);
            this.xGUIs.addElement(this.htmlButton);
            this.xGUIs.addElement(this.memoryDots);
            this.xGUIs.addElement(this.blendChoice);
            this.xGUIs.addElement(this.motionChoice);
            this.xGUIs.addElement(this.presetChoice);
            final LightRaysGUI$MemorySet set = new LightRaysGUI$MemorySet(this);
            for (int i = 0; i < this.names.length; ++i) {
                set.names.addElement(this.names[i]);
                set.values.addElement(this.Default[i]);
            }
            final LightRaysGUI$MemorySet set2 = new LightRaysGUI$MemorySet(this);
            for (int j = 0; j < this.names.length; ++j) {
                set2.names.addElement(this.names[j]);
                set2.values.addElement(this.DeepZoom[j]);
            }
            final LightRaysGUI$MemorySet set3 = new LightRaysGUI$MemorySet(this);
            for (int k = 0; k < this.names.length; ++k) {
                set3.names.addElement(this.names[k]);
                set3.values.addElement(this.Psychedelic[k]);
            }
            final LightRaysGUI$MemorySet set4 = new LightRaysGUI$MemorySet(this);
            for (int l = 0; l < this.names.length; ++l) {
                set4.names.addElement(this.names[l]);
                set4.values.addElement(this.DeepForest[l]);
            }
            final LightRaysGUI$MemorySet set5 = new LightRaysGUI$MemorySet(this);
            for (int n = 0; n < this.names.length; ++n) {
                set5.names.addElement(this.names[n]);
                set5.values.addElement(this.RainbowSatin1[n]);
            }
            final LightRaysGUI$MemorySet set6 = new LightRaysGUI$MemorySet(this);
            for (int n2 = 0; n2 < this.names.length; ++n2) {
                set6.names.addElement(this.names[n2]);
                set6.values.addElement(this.RainbowSatin2[n2]);
            }
            final LightRaysGUI$MemorySet set7 = new LightRaysGUI$MemorySet(this);
            for (int n3 = 0; n3 < this.names.length; ++n3) {
                set7.names.addElement(this.names[n3]);
                set7.values.addElement(this.GoldSatin[n3]);
            }
            this.presets[0] = set;
            this.presets[1] = set2;
            this.presets[2] = set3;
            this.presets[3] = set4;
            this.presets[4] = set5;
            this.presets[5] = set6;
            this.presets[6] = set7;
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void jbInit() throws Exception {
        this.setLayout(null);
        this.alphaSlider.setBounds(new Rectangle(218, 272, 207, 16));
        this.lightSizeYSlider.setBounds(new Rectangle(218, 242, 207, 16));
        this.lightSizeXSlider.setBounds(new Rectangle(218, 212, 207, 16));
        this.motionScaleYSlider.setBounds(new Rectangle(218, 182, 207, 16));
        this.motionScaleXSlider.setBounds(new Rectangle(218, 152, 207, 16));
        this.motionSpeedSlider.setBounds(new Rectangle(218, 122, 207, 16));
        this.pulsateSpeedSlider.setBounds(new Rectangle(218, 92, 207, 16));
        this.interactiveSwitch.setBounds(new Rectangle(245, 33, 20, 20));
        this.frameSwitch.setBounds(new Rectangle(288, 11, 20, 20));
        this.foregroundSwitch.setBounds(new Rectangle(288, 33, 20, 20));
        this.backgroundSwitch.setBounds(new Rectangle(288, 55, 20, 20));
        this.htmlButton.setBounds(new Rectangle(5, 267, 113, 25));
        this.blendChoice.setBounds(new Rectangle(11, 117, 100, 21));
        this.motionChoice.setBounds(new Rectangle(11, 167, 100, 21));
        this.presetChoice.setBounds(new Rectangle(11, 217, 100, 21));
        this.memoryDots.setBounds(new Rectangle(33, 8, 57, 57));
        this.blendChoice.addItem("hardlight");
        this.blendChoice.addItem("softlight");
        this.blendChoice.addItem("screen");
        this.blendChoice.addItem("overlay");
        this.blendChoice.addItem("multiply");
        this.blendChoice.addItem("add");
        this.blendChoice.addItem("sub");
        this.blendChoice.addItem("dodge");
        this.blendChoice.addItem("burn");
        this.blendChoice.addItem("lighten");
        this.blendChoice.addItem("darken");
        this.blendChoice.addItem("difference");
        this.blendChoice.addItem("exclusion");
        this.blendChoice.addItem("opacity90");
        this.blendChoice.addItem("opacity80");
        this.blendChoice.addItem("opacity70");
        this.blendChoice.addItem("opacity60");
        this.blendChoice.addItem("opacity50");
        this.blendChoice.addItem("opacity40");
        this.blendChoice.addItem("opacity30");
        this.blendChoice.addItem("opacity20");
        this.blendChoice.addItem("opacity10");
        this.motionChoice.addItem("circular");
        this.motionChoice.addItem("horizontal");
        this.motionChoice.addItem("vertical");
        this.motionChoice.addItem("random linear");
        this.motionChoice.addItem("random curve");
        this.motionChoice.addItem("random spot");
        this.motionChoice.addItem("fixed");
        this.presetChoice.addItem("default");
        this.presetChoice.addItem("deep zoom");
        this.presetChoice.addItem("psychedelic");
        this.presetChoice.addItem("deep forest");
        this.presetChoice.addItem("rainbow satin 1");
        this.presetChoice.addItem("rainbow satin 2");
        this.presetChoice.addItem("gold satin");
        this.htmlButton.addMouseListener(new LightRaysGUI$1(this));
        this.add(this.alphaSlider, null);
        this.add(this.lightSizeYSlider, null);
        this.add(this.lightSizeXSlider, null);
        this.add(this.motionScaleYSlider, null);
        this.add(this.motionScaleXSlider, null);
        this.add(this.motionSpeedSlider, null);
        this.add(this.pulsateSpeedSlider, null);
        this.add(this.interactiveSwitch, null);
        this.add(this.frameSwitch, null);
        this.add(this.backgroundSwitch, null);
        this.add(this.foregroundSwitch, null);
        this.add(this.htmlButton, null);
        this.add(this.blendChoice, null);
        this.add(this.motionChoice, null);
        this.add(this.presetChoice, null);
        this.add(this.memoryDots, null);
        this.alphaSlider.addMouseListener(this);
        this.lightSizeXSlider.addMouseListener(this);
        this.lightSizeYSlider.addMouseListener(this);
        this.motionScaleYSlider.addMouseListener(this);
        this.motionScaleXSlider.addMouseListener(this);
        this.motionSpeedSlider.addMouseListener(this);
        this.pulsateSpeedSlider.addMouseListener(this);
        this.interactiveSwitch.addMouseListener(this);
        this.frameSwitch.addMouseListener(this);
        this.backgroundSwitch.addMouseListener(this);
        this.foregroundSwitch.addMouseListener(this);
        this.htmlButton.addMouseListener(this);
        this.blendChoice.addMouseListener(this);
        this.motionChoice.addMouseListener(this);
        this.presetChoice.addMouseListener(this);
        this.memoryDots.addMouseListener(this);
        this.memoryDots.addMemoryDotListener(this);
        this.presetChoice.addItemListener(this);
    }
    
    public final void start() {
        AppletPair.setGUI(this);
        this.repaintAll();
    }
    
    public final void stop() {
    }
    
    public final void destroy() {
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public final String getAppletInfo() {
        return "LightRays GUI - www.waterlogic.com.sg";
    }
    
    public final String[][] getParameterInfo() {
        return null;
    }
    
    public final String getHTMLcode(final String s, final String s2) {
        String s3;
        if (this.vlights == null) {
            System.out.println("getHTMLcode: no vlights, returning default applet definition");
            s3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("<applet archive = \"LightRaysPersonal.jar\" code = \"LightRaysPersonal.class\" width  = 450 height = 250 name = vlights ID = vlights>")).concat("<param name = \"blendmode\" value = \"5\">"))).concat("<param name = \"lightPulsateSpeed\" value = \"1.0\">"))).concat("<param name = \"lightMotionType\" value = \"0\">"))).concat("<param name = \"lightMotionSpeed\" value = \"0.5\">"))).concat("<param name = \"lightMotionScaleX\" value = \"0.8\">"))).concat("<param name = \"lightMotionScaleY\" value = \"0.8\">"))).concat("<param name = \"lightRadiusX\" value = \"0.05\">"))).concat("<param name = \"lightRadiusY\" value = \"0.05\">"))).concat("<param name = \"lightAlpha\" value = \"0.9\">"))).concat("<param name = \"lightAlphaMin\" value = \"0.15\">"))).concat("<param name = \"lightInteractive\" value = \"true\">"))).concat("<param name = \"foregroundImage\" value = \"lightrays/fore3.jpg\">"))).concat("<param name = \"backgroundImage\" value = \"lightrays/back3.jpg\">"))).concat("<param name = \"frame\" value = \"lightrays/frame3.jpg\">"))).concat("<param name = \"mask\" value = \"lightrays/mask3.jpg\">"))).concat("<param name = \"foregroundActive\" value = \"true\">"))).concat("<param name = \"backgroundActive\" value = \"true\">"))).concat("<param name = \"frameActive\" value = \"true\">"))).concat("</applet>");
        }
        else {
            System.out.println("getHTMLcode: returning current vlights parameters");
            final String concat = String.valueOf(String.valueOf("<applet\n")).concat("archive = \"LightRaysPersonal.jar\" code = \"LightRaysPersonal.class\"\n");
            String s4;
            if (s == null && s2 == null) {
                s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("width  = ").append(this.vlights.getSize().width).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("height = ").append(this.vlights.getSize().height).append("\n"))))));
            }
            else {
                s4 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("width  = ").append(s).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("height = ").append(s2).append("\n"))))));
            }
            String s5 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s4)).concat("name = vlights\n"))).concat("ID   = vlights>\n");
            final LightRaysGUI$MemorySet set = new LightRaysGUI$MemorySet(this);
            this.vlights.getJavaFXParameters(set.names, set.values);
            for (int i = 0; i < set.names.size(); ++i) {
                s5 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("<param name = \"").append((String)set.names.elementAt(i)).append("\" value = \"").append((String)set.values.elementAt(i)).append("\">\n"))))));
            }
            s3 = String.valueOf(String.valueOf(s5)).concat("</applet>");
        }
        return s3;
    }
    
    public final void htmlButton_mouseClicked(final MouseEvent mouseEvent) {
        final TextOutputDialog textOutputDialog = new TextOutputDialog("HTML Output", this.getHTMLcode(null, null));
        textOutputDialog.pack();
        textOutputDialog.show();
    }
    
    public final void memorizing(final MemoryDotEvent memoryDotEvent) {
        if (this.vlights == null) {
            return;
        }
        final LightRaysGUI$MemorySet set = new LightRaysGUI$MemorySet(this);
        this.vlights.getJavaFXParameters(set.names, set.values);
        this.memoryDots.memorise(set, memoryDotEvent.getSlot());
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Memorising:\n").append(set.names.toString()).append("\n").append(set.values.toString()))));
        this.repaintAll();
    }
    
    public final void recalling(final MemoryDotEvent memoryDotEvent) {
        if (this.vlights == null) {
            return;
        }
        final LightRaysGUI$MemorySet set = (LightRaysGUI$MemorySet)this.memoryDots.recall(memoryDotEvent.getSlot());
        if (set == null) {
            return;
        }
        try {
            this.vlights.setJavaFXParameters(set.names, set.values);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Recalling:\n").append(set.names.toString()).append("\n").append(set.values.toString()))));
        this.repaintAll();
    }
    
    public final void repaintAll() {
        this.validate();
        this.repaint();
        this.update(this.getGraphics());
        for (int i = 0; i < this.xGUIs.size(); ++i) {
            ((xGUI)this.xGUIs.elementAt(i)).forceRepaint();
        }
    }
    
    public final void effectFound(final JavaFX javaFX) {
        System.out.println("Target applet 'vlights' found, attaching...");
        try {
            this.vlights = (LightRaysPersonal)javaFX;
            this.alphaSlider.setTarget(this.vlights.getJavaFXParameterObject("lightAlpha"));
            this.lightSizeYSlider.setTarget(this.vlights.getJavaFXParameterObject("lightRadiusY"));
            this.lightSizeXSlider.setTarget(this.vlights.getJavaFXParameterObject("lightRadiusX"));
            this.motionScaleYSlider.setTarget(this.vlights.getJavaFXParameterObject("lightMotionScaleY"));
            this.motionScaleXSlider.setTarget(this.vlights.getJavaFXParameterObject("lightMotionScaleX"));
            this.motionSpeedSlider.setTarget(this.vlights.getJavaFXParameterObject("lightMotionSpeed"));
            this.pulsateSpeedSlider.setTarget(this.vlights.getJavaFXParameterObject("lightPulsateSpeed"));
            this.interactiveSwitch.setTarget(this.vlights.getJavaFXParameterObject("lightInteractive"));
            this.foregroundSwitch.setTarget(this.vlights.getJavaFXParameterObject("foregroundActive"));
            this.backgroundSwitch.setTarget(this.vlights.getJavaFXParameterObject("backgroundActive"));
            this.frameSwitch.setTarget(this.vlights.getJavaFXParameterObject("frameActive"));
            this.blendChoice.setTarget(this.vlights.getJavaFXParameterObject("blendmode"));
            this.motionChoice.setTarget(this.vlights.getJavaFXParameterObject("lightMotionType"));
            this.presetChoice.setTarget(this.presetParm);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.repaintAll();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        String s = "";
        if (mouseEvent.getSource() == this.alphaSlider) {
            s = "Drag to adjust light's strength.";
        }
        else if (mouseEvent.getSource() == this.lightSizeYSlider) {
            s = "Drag to adjust light's vertical size.";
        }
        else if (mouseEvent.getSource() == this.lightSizeXSlider) {
            s = "Drag to adjust light's horizontal size.";
        }
        else if (mouseEvent.getSource() == this.motionScaleYSlider) {
            s = "Drag to adjust light's vertical motion range.";
        }
        else if (mouseEvent.getSource() == this.motionScaleXSlider) {
            s = "Drag to adjust light's horizontal motion range.";
        }
        else if (mouseEvent.getSource() == this.motionSpeedSlider) {
            s = "Drag to adjust light's motion speed.";
        }
        else if (mouseEvent.getSource() == this.pulsateSpeedSlider) {
            s = "Drag to adjust light's pulsate speed. To turn off pulsating, drag to left end.";
        }
        else if (mouseEvent.getSource() == this.interactiveSwitch) {
            s = "Click to turn on/off mouse controlling light position.";
        }
        else if (mouseEvent.getSource() == this.frameSwitch) {
            s = "Click to turn on/off frame/mask layer.";
        }
        else if (mouseEvent.getSource() == this.backgroundSwitch) {
            s = "Click to turn on/off background layer.";
        }
        else if (mouseEvent.getSource() == this.foregroundSwitch) {
            s = "Click to turn on/off foreground layer.";
        }
        else if (mouseEvent.getSource() == this.htmlButton) {
            s = "Click to generate HTML code for current settings.";
        }
        else if (mouseEvent.getSource() == this.blendChoice) {
            s = "Click to select how foreground and background blend together.";
        }
        else if (mouseEvent.getSource() == this.motionChoice) {
            s = "Click to select light's motion type.";
        }
        else if (mouseEvent.getSource() == this.presetChoice) {
            s = "Click to select a preset to experience the possibilities.";
        }
        else if (mouseEvent.getSource() == this.memoryDots) {
            s = "Left-click on a dot to memorise current settings; right-click to recall.";
        }
        this.showStatus(s);
        if (mouseEvent.getSource() != this) {
            this.setCursor(this.fingerCursor);
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.showStatus("");
        this.setCursor(this.defaultCursor);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.presetChoice) {
            final int p = ((db)this.presetParm.target).p();
            try {
                this.vlights.setJavaFXParameters(this.presets[p].names, this.presets[p].values);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.repaintAll();
        }
    }
    
    static {
        if (1354870381117L <= System.currentTimeMillis()) {
            System.out.println("This java program was processed with an unregistered version of Condensity and it expired on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
            throw new Error();
        }
        System.out.println("This java program was processed with an unregistered version of Condensity and will expire on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
    }
}

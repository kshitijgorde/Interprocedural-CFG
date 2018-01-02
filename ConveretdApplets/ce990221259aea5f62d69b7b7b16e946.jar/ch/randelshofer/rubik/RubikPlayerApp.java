// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import ch.randelshofer.rubik.parserAWT.ScriptParser;
import java.util.Enumeration;
import java.net.MalformedURLException;
import java.net.URL;
import ch.randelshofer.geom3d.Point3D;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.StringReader;
import ch.randelshofer.rubik.parserAWT.CastellaParser;
import ch.randelshofer.rubik.parserAWT.TouchardDeledicqFRAParser;
import ch.randelshofer.rubik.parserAWT.HarrisENGParser;
import ch.randelshofer.rubik.parserAWT.SupersetENGParser;
import ch.randelshofer.rubik.parserAWT.ScriptFRAParser;
import ch.randelshofer.rubik.parserAWT.RandelshoferGERParser;
import ch.randelshofer.rubik.parserAWT.BandelowENGParser;
import ch.randelshofer.geom3d.Transform3D;
import java.util.Hashtable;
import ch.randelshofer.rubik.parserAWT.ScriptNode;
import ch.randelshofer.gui.event.ChangeEvent;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.TextArea;
import ch.randelshofer.gui.RatioLayout;
import java.awt.Dimension;
import ch.randelshofer.geom3d.Transform3DModel;
import ch.randelshofer.geom3d.RotatedTransform3DModel;
import ch.randelshofer.gui.event.ChangeListener;
import ch.randelshofer.gui.Canvas3DJ2D;
import ch.randelshofer.util.Applets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import ch.randelshofer.util.PooledSequentialDispatcherAWT;
import ch.randelshofer.gui.Canvas3DAWT;
import java.awt.Color;
import java.awt.Panel;
import ch.randelshofer.gui.MultilineLabel;
import ch.randelshofer.rubik.parserAWT.ScriptPlayer;
import java.applet.Applet;

public class RubikPlayerApp extends Applet implements Runnable
{
    private ScriptPlayer player;
    private boolean isInitialized;
    private MultilineLabel scriptTextArea;
    private Panel controlsPanel;
    private static final String version = "5.2.1";
    private static final String copyright = "© 2000-2005 W. Randelshofer";
    private static final Color inactiveSelectionBackground;
    private static final Color activeSelectionBackground;
    private Canvas3DAWT rearCanvas3D;
    private RubiksCubeCore initCube;
    private boolean isSolver;
    
    public RubikPlayerApp() {
        this.isInitialized = false;
    }
    
    public void init() {
        this.initComponents();
        PooledSequentialDispatcherAWT.dispatchConcurrently(this);
    }
    
    public void stop() {
        if (this.player != null) {
            this.player.stop();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Dialog", 0, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString("Loading Rubik Player 5.2.1", 12, fontMetrics.getHeight());
        graphics.drawString("© 2000-2005 W. Randelshofer", 12, fontMetrics.getHeight() * 2);
    }
    
    public void run() {
        this.player = new ScriptPlayer() {
            public void reset() {
                super.reset();
                this.getCubeModel().setTo(RubikPlayerApp.this.initCube);
            }
        };
        this.initCube = new RubiksCubeCore();
        (this.controlsPanel = new Panel()).setLayout(new BorderLayout());
        this.controlsPanel.add("North", this.player.getControlPanelComponent());
        this.controlsPanel.add("South", this.scriptTextArea = new MultilineLabel());
        this.scriptTextArea.setFont(new Font("Dialog", 0, 12));
        this.scriptTextArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                RubikPlayerApp.this.player.moveToCaret(RubikPlayerApp.this.scriptTextArea.viewToModel(mouseEvent.getX(), mouseEvent.getY()));
            }
        });
        this.scriptTextArea.setSize(this.getSize());
        Component visualComponent;
        if (Applets.getParameter(this, "rearView", "false").equals("true")) {
            final Canvas3DAWT canvas3DAWT = (Canvas3DAWT)this.player.getVisualComponent();
            (this.rearCanvas3D = Canvas3DJ2D.createCanvas3D()).setScene(this.player.getCube3D().getScene());
            this.rearCanvas3D.setSyncObject(this.player.getCube3D().getModel());
            this.player.getCube3D().addChangeListener(this.rearCanvas3D);
            final int[] parameters = Applets.getParameters(this, "rearViewRotation", new int[] { 180, 0, 0 });
            if (parameters.length != 3) {
                throw new IllegalArgumentException("Invalid parameter 'rearViewRotation' provides " + parameters.length + " instead of 3 values.");
            }
            this.rearCanvas3D.setTransformModel(new RotatedTransform3DModel(parameters[0] / 180.0 * 3.141592653589793, parameters[1] / 180.0 * 3.141592653589793, parameters[2] / 180.0 * 3.141592653589793, canvas3DAWT.getTransformModel()));
            this.rearCanvas3D.setScaleFactor(canvas3DAWT.getScaleFactor());
            canvas3DAWT.setPreferredSize(new Dimension(1, 1));
            this.rearCanvas3D.setPreferredSize(new Dimension(1, 1));
            final float n = (float)Math.max(0.1, Math.min(1.0, (float)Applets.getParameter(this, "rearViewScaleFactor", 0.75)));
            final Panel panel = new Panel();
            panel.setLayout(new RatioLayout(1.0f - 0.5f * n));
            panel.add(canvas3DAWT);
            panel.add(this.rearCanvas3D);
            this.rearCanvas3D.setScaleFactor(this.rearCanvas3D.getScaleFactor() * n);
            visualComponent = panel;
        }
        else {
            visualComponent = this.player.getVisualComponent();
        }
        try {
            this.readParameters();
        }
        catch (Throwable t) {
            this.removeAll();
            this.setLayout(new BorderLayout());
            final TextArea textArea = new TextArea(10, 40);
            this.add("Center", textArea);
            final StringWriter stringWriter = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(stringWriter);
            t.printStackTrace(printWriter);
            printWriter.close();
            textArea.setText(this.getAppletInfo() + "\n\n" + t + "\n" + stringWriter.toString());
            this.invalidate();
            this.validate();
            return;
        }
        final ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                RubikPlayerApp.this.selectCurrentSymbol();
            }
        };
        this.player.getBoundedRangeModel().addChangeListener(changeListener);
        this.player.addChangeListener(changeListener);
        synchronized (this.getTreeLock()) {
            this.add("Center", visualComponent);
            this.add("South", this.controlsPanel);
            this.validate();
            this.controlsPanel.invalidate();
            this.validate();
        }
        if (Applets.getParameter(this, "autoPlay", false)) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.player.start();
        }
    }
    
    protected void selectCurrentSymbol() {
        final ScriptNode currentSymbol = this.player.getCurrentSymbol();
        int n2;
        int n;
        if (currentSymbol == null) {
            n = (n2 = this.scriptTextArea.getText().length());
        }
        else {
            n2 = currentSymbol.getStartPosition();
            n = currentSymbol.getEndPosition() + 1;
        }
        this.scriptTextArea.select(n2, n);
        this.scriptTextArea.setSelectionBackground(this.player.isProcessingCurrentSymbol() ? RubikPlayerApp.activeSelectionBackground : RubikPlayerApp.inactiveSelectionBackground);
    }
    
    private void readParameters() throws IllegalArgumentException {
        final AbstractCube3DAWT cube3D = this.player.getCube3D();
        final Hashtable hashtable = new Hashtable();
        final Hashtable hashtable2 = new Hashtable();
        final Color background = new Color(Applets.getParameter(this, "backgroundColor", 16777215));
        final Canvas3DAWT canvas3DAWT = (Canvas3DAWT)this.player.getVisualComponent();
        canvas3DAWT.setBackground(background);
        this.player.getControlPanelComponent().setBackground(background);
        this.controlsPanel.setBackground(background);
        final Transform3D transform = new Transform3D();
        transform.rotateY(Applets.getParameter(this, "beta", 45) / 180.0 * 3.141592653589793);
        transform.rotateX(Applets.getParameter(this, "alpha", -25) / 180.0 * 3.141592653589793);
        this.player.setTransform(transform);
        final Hashtable<String, String> hashtable3 = new Hashtable<String, String>();
        hashtable3.put("0", "0x003373");
        hashtable3.put("1", "0xff4600");
        hashtable3.put("2", "0xf8f8f8");
        hashtable3.put("3", "0x00732f");
        hashtable3.put("4", "0x8c000f");
        hashtable3.put("5", "0xffd200");
        final Hashtable indexedKeyValueParameters = Applets.getIndexedKeyValueParameters(this, "colorTable", hashtable3);
        final Enumeration<Object> keys = indexedKeyValueParameters.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            try {
                indexedKeyValueParameters.put(nextElement, new Color(Applets.decode((String)indexedKeyValueParameters.get(nextElement))));
                continue;
            }
            catch (NumberFormatException ex5) {
                throw new IllegalArgumentException("Invalid parameter 'colorTable', value " + indexedKeyValueParameters.get(nextElement) + " for entry " + nextElement + " is illegal.");
            }
            break;
        }
        if (this.getParameter("faces") == null) {
            for (int i = 0; i < 6; ++i) {
                final String string = Integer.toString(i);
                if (!indexedKeyValueParameters.containsKey(string)) {
                    throw new IllegalArgumentException("Invalid parameter 'colorTable', entry number " + string + " missing.");
                }
                final Color color = indexedKeyValueParameters.get(string);
                for (int j = 0; j < 9; ++j) {
                    cube3D.setStickerColor(i, j, color);
                }
            }
        }
        final String[] parameters = Applets.getParameters(this, "faces", (String[])null);
        if (parameters != null) {
            if (parameters.length != 6) {
                throw new IllegalArgumentException("Invalid parameter 'faces' provides " + parameters.length + " instead of 6 entries.");
            }
            for (int k = 0; k < 6; ++k) {
                if (!indexedKeyValueParameters.containsKey(parameters[k])) {
                    throw new IllegalArgumentException("Invalid parameter 'faces', unknown entry '" + parameters[k] + "'.");
                }
                final Color color2 = indexedKeyValueParameters.get(parameters[k]);
                for (int l = 0; l < 9; ++l) {
                    cube3D.setStickerColor(k, l, color2);
                }
            }
        }
        final String[] parameters2 = Applets.getParameters(this, "stickers", (String[])null);
        if (parameters2 != null) {
            if (parameters2.length != 54) {
                throw new IllegalArgumentException("Invalid parameter 'stickers' provides " + parameters2.length + " instead of 54 entries.");
            }
            for (int n = 0; n < 6; ++n) {
                for (int n2 = 0; n2 < 9; ++n2) {
                    if (!indexedKeyValueParameters.containsKey(parameters2[n * 9 + n2])) {
                        throw new IllegalArgumentException("Invalid parameter 'stickers', unknown entry '" + parameters2[n * 9 + n2] + "'.");
                    }
                    cube3D.setStickerColor(n, n2, indexedKeyValueParameters.get(parameters2[n * 9 + n2]));
                }
            }
        }
        final String[] array = { "stickersFront", "stickersRight", "stickersDown", "stickersBack", "stickersLeft", "stickersUp" };
        for (int n3 = 0; n3 < 6; ++n3) {
            final String[] parameters3 = Applets.getParameters(this, array[n3], (String[])null);
            if (parameters3 != null) {
                if (parameters3.length != 9) {
                    throw new IllegalArgumentException("Invalid parameter '" + array[n3] + "' provides " + parameters3.length + " instead of 9 entries.");
                }
                for (int n4 = 0; n4 < 9; ++n4) {
                    if (!indexedKeyValueParameters.containsKey(parameters3[n4])) {
                        throw new IllegalArgumentException("Invalid parameter '" + array[n3] + "', unknown entry '" + parameters3[n4] + "'.");
                    }
                    cube3D.setStickerColor(n3, n4, indexedKeyValueParameters.get(parameters3[n4]));
                }
            }
        }
        final String parameter = this.getParameter("scriptLanguage");
        ScriptParser scriptParser;
        if (parameter == null || parameter.equals("BandelowENG")) {
            scriptParser = new BandelowENGParser();
        }
        else if (parameter.equals("RandelshoferGER")) {
            scriptParser = new RandelshoferGERParser();
        }
        else if (parameter.equals("ScriptFRA")) {
            scriptParser = new ScriptFRAParser();
        }
        else if (parameter.equals("SupersetENG")) {
            scriptParser = new SupersetENGParser();
        }
        else if (parameter.equals("HarrisENG")) {
            scriptParser = new HarrisENGParser();
        }
        else if (parameter.equals("TouchardDeledicqFRA")) {
            scriptParser = new TouchardDeledicqFRAParser();
        }
        else {
            if (!parameter.equals("Castella")) {
                throw new IllegalArgumentException("Invalid parameter 'scriptLanguage': Unsupported language '" + parameter + "'");
            }
            scriptParser = new CastellaParser();
        }
        final String parameter2 = Applets.getParameter(this, "scriptType", "Generator");
        if (parameter2.equals("Solver")) {
            this.isSolver = true;
        }
        else {
            if (!parameter2.equals("Generator")) {
                throw new IllegalArgumentException("Invalid parameter 'scriptType': Unsupported type '" + parameter2 + "'");
            }
            this.isSolver = false;
        }
        String text = this.getParameter("script");
        if (text == null) {
            this.scriptTextArea.setVisible(false);
        }
        else {
            int index;
            while ((index = text.indexOf("\\n")) != -1) {
                text = text.substring(0, index) + '\n' + text.substring(index + 2);
            }
            try {
                final ScriptNode parse = scriptParser.parse(new StringReader(text));
                this.scriptTextArea.setText(text);
                this.player.setScript(parse);
            }
            catch (Exception ex) {
                final StringWriter stringWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                printWriter.close();
                throw new IllegalArgumentException("Invalid parameter 'script':\n" + ex.getMessage() + "\n" + stringWriter);
            }
        }
        final String parameter3 = this.getParameter("initScript");
        if (parameter3 == null) {
            this.initCube.reset();
        }
        else {
            this.initCube.reset();
            int index2;
            while ((index2 = parameter3.indexOf("\\n")) != -1) {
                text = parameter3.substring(0, index2) + '\n' + parameter3.substring(index2 + 2);
            }
            try {
                scriptParser.parse(new StringReader(parameter3)).applySubtreeTo(this.initCube, false);
            }
            catch (Exception ex2) {
                final StringWriter stringWriter2 = new StringWriter();
                final PrintWriter printWriter2 = new PrintWriter(stringWriter2);
                ex2.printStackTrace(printWriter2);
                printWriter2.close();
                throw new IllegalArgumentException("Invalid parameter 'initScript':\n" + ex2.getMessage() + "\n" + stringWriter2);
            }
        }
        if (this.isSolver && this.player.getScript() != null) {
            this.player.getScript().applySubtreeTo(this.initCube, true);
        }
        this.player.reset();
        try {
            int parameter4 = Applets.getParameter(this, "scriptProgress", (this.isSolver || Applets.getParameter(this, "autoPlay", false)) ? 0 : -1);
            if (parameter4 < 0) {
                parameter4 = this.player.getBoundedRangeModel().getMaximum() - parameter4 + 1;
            }
            this.player.getBoundedRangeModel().setValue(parameter4);
        }
        catch (IndexOutOfBoundsException ex3) {
            throw new IllegalArgumentException("Invalid parameter 'scriptProgress':\n" + ex3.getMessage());
        }
        final String parameter5 = this.getParameter("displayLines");
        int int1 = (text == null) ? 0 : new StringTokenizer(text, "\n").countTokens();
        if (parameter5 != null) {
            try {
                int1 = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex4) {
                throw new IllegalArgumentException("Invalid parameter 'displayLines':\n" + ex4.getMessage());
            }
        }
        if (int1 <= 0) {
            this.scriptTextArea.setVisible(false);
        }
        else {
            try {
                this.scriptTextArea.setMinRows(int1);
            }
            catch (NoSuchMethodError noSuchMethodError) {}
        }
        canvas3DAWT.setLightSourceIntensity(Applets.getParameter(this, "lightSourceIntensity", 1.0));
        canvas3DAWT.setAmbientLightIntensity(Applets.getParameter(this, "ambientLightIntensity", 0.6));
        final int[] parameters4 = Applets.getParameters(this, "lightSourcePosition", new int[] { -500, 500, 1000 });
        if (parameters4.length != 3) {
            throw new IllegalArgumentException("Invalid parameter 'lightSourcePosition' provides " + parameters4.length + " instead of 3 entries.");
        }
        canvas3DAWT.setLightSource(new Point3D(parameters4[0], parameters4[1], parameters4[2]));
        final String parameter6 = this.getParameter("backgroundImage");
        if (parameter6 != null) {
            try {
                canvas3DAWT.setBackgroundImage(this.getImage(new URL(this.getDocumentBase(), parameter6)));
            }
            catch (MalformedURLException ex6) {
                throw new IllegalArgumentException("Invalid parameter 'backgroundImage' malformed URL: " + parameter6);
            }
        }
        if (Applets.getParameter(this, "rearView", "false").equals("true")) {
            this.rearCanvas3D.setBackground(new Color(Applets.getParameter(this, "rearViewBackgroundColor", Applets.getParameter(this, "backgroundColor", 16777215))));
            final String parameter7 = Applets.getParameter(this, "rearViewBackgroundImage", this.getParameter("backgroundImage"));
            if (parameter7 != null) {
                try {
                    this.rearCanvas3D.setBackgroundImage(this.getImage(new URL(this.getDocumentBase(), parameter7)));
                }
                catch (MalformedURLException ex7) {
                    throw new IllegalArgumentException("Invalid parameter 'backgroundImage' malformed URL: " + parameter7);
                }
            }
            this.rearCanvas3D.setLightSourceIntensity(Applets.getParameter(this, "lightSourceIntensity", 1.0));
            this.rearCanvas3D.setAmbientLightIntensity(Applets.getParameter(this, "ambientLightIntensity", 0.6));
            final int[] parameters5 = Applets.getParameters(this, "lightSourcePosition", new int[] { -500, 500, 1000 });
            if (parameters5.length != 3) {
                throw new IllegalArgumentException("Invalid parameter 'lightSourcePosition' provides " + parameters5.length + " instead of 3 entries.");
            }
            this.rearCanvas3D.setLightSource(new Point3D(parameters5[0], parameters5[1], parameters5[2]));
        }
    }
    
    public String getAppletInfo() {
        return "Rubik Player 5.2.1, © 2000-2005 W. Randelshofer. All rights reserved.";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "alpha", "int", "Vertical orientation of the cube, -90..+90. Default: -25" }, { "beta", "int", "Horizontal orientation of the cube, -90..+90. Default: 45" }, { "backgroundColor", "int", "Background color. Default: 0xffffff" }, { "backgroundImage", "URL", "Background image. Default: none" }, { "colorTable", "[name=]int, ...", "RGB color look up table, 6..n entries. Each entry consists of an optional name and a hex value. Default: 0x003373,0xff4600,0xf8f8f8,0x00732f,0x8c000f,0xffd200" }, { "faces", "name, ...", "Maps colors from the color table to the faces of the cube; 6 integer values; front, right, down, back, left, up. Default: 0,1,2,3,4,5" }, { "stickers", "name, ...", "Maps colors from the color table to the stickers of the cube; 54 integer values; front, right, down, back, left, up. Default: 0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1, 2,2,2,2,2,2,2,2,2, 3,3,3,3,3,3,3,3,3, 4,4,4,4,4,4,4,4,4, 5,5,5,5,5,5,5,5,5" }, { "scriptLanguage", "string", "Language of the Script: 'ScriptFRA','BandelowENG','RandelshoferGER','SupersetENG','TouchardDeledicqFRA','Castella'. Default: BandelowENG" }, { "script", "string", "Script. Default: no script." }, { "stickersFront", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 0,0,0,0,0,0,0,0,0" }, { "stickersRight", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 1,1,1,1,1,1,1,1,1" }, { "stickersDown", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 2,2,2,2,2,2,2,2,2" }, { "stickersBack", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 3,3,3,3,3,3,3,3,3" }, { "stickersLeft", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 4,4,4,4,4,4,4,4,4" }, { "stickersUp", "(name|int), ...", "Maps colors from the color table to the stickers on the side of the cube; 9 integer values. Default: 5,5,5,5,5,5,5,5,5" }, { "scriptType", "string", "The type of the script: 'Solver' or 'Generator'. Default: 'Solver'." }, { "scriptProgress", "int", "Position of the progress bar. Default: end of script if scriptType is 'Generator', 0 if script type is 'Solver'." }, { "initScript", "string", "This script is used to initialize the cube, and when the reset button is pressed. Default: no script." }, { "displayLines", "int", "Number of lines of the Script display: set to 0 to switch the display off. Default: 1" }, { "ambientLightIntensity", "double", "Intensity of ambient light. Default: 0.6" }, { "lightSourceIntensity", "double", "Intensity of the light source: set to 0 to switch the light source off. Default: 1.0" }, { "lightSourcePosition", "int,int,int", "X, Y and Z coordinate of the light source. Default: -500, 500, 1000" }, { "rearView", "boolean", "Set this value to true, to turn the rear view on. Default: false" }, { "rearViewBackgroundColor", "int", "Background color. Default: use value of parameter 'backgroundColor'" }, { "rearViewBackgroundImage", "URL", "Background image. Default: use value of parameter 'backgroundImage'" }, { "rearViewScaleFactor", "float", "Scale factor of the rear view. Value between 0.1 and 1.0. Default: 0.75" }, { "rearViewRotation", "int,int,int", "Rotation of the rear view on the X, Y and Z axis in degrees. Default: 180,0,0" }, { "autoPlay", "boolean", "Set this value to true, to start playing the script automatically. Default: false" } };
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("RubikPlayer");
        final ScriptPlayer scriptPlayer = new ScriptPlayer();
        final Transform3D transform = new Transform3D();
        transform.rotateY(0.7853981633974483);
        transform.rotateX(-0.4363323129985824);
        scriptPlayer.setTransform(transform);
        frame.add(scriptPlayer.getVisualComponent(), "Center");
        frame.add(scriptPlayer.getControlPanelComponent(), "South");
        frame.setSize(400, 400);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.show();
    }
    
    private void initComponents() {
        this.setLayout(new BorderLayout());
    }
    
    static {
        inactiveSelectionBackground = new Color(213, 213, 213);
        activeSelectionBackground = new Color(255, 255, 64);
    }
}

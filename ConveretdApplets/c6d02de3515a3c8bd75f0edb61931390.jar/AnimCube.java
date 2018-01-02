import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class AnimCube extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    private final Hashtable config;
    private Color bgColor;
    private Color bgColor2;
    private Color hlColor;
    private Color textColor;
    private Color buttonBgColor;
    private final Color[] colors;
    private final int[][] cube;
    private final int[][] initialCube;
    private static final double[][] faceNormals;
    private static final double[][] cornerCoords;
    private static final int[][] faceCorners;
    private static final int[][] oppositeCorners;
    private static final int[][] adjacentFaces;
    private int twistedLayer;
    private int twistedMode;
    private static final int[] faceTwistDirs;
    private final double[] eye;
    private final double[] eyeX;
    private final double[] eyeY;
    private final double[] initialEye;
    private final double[] initialEyeX;
    private final double[] initialEyeY;
    private double currentAngle;
    private double originalAngle;
    private int speed;
    private int doubleSpeed;
    private boolean natural;
    private boolean toTwist;
    private boolean interrupted;
    private boolean restarted;
    private boolean mirrored;
    private boolean editable;
    private boolean twisting;
    private boolean spinning;
    private boolean animating;
    private boolean dragging;
    private boolean demo;
    private int persp;
    private double scale;
    private int align;
    private boolean hint;
    private double faceShift;
    private int[][] move;
    private int[][] demoMove;
    private int curMove;
    private int movePos;
    private int moveDir;
    private boolean moveOne;
    private boolean moveAnimated;
    private int metric;
    private String[] infoText;
    private int curInfoText;
    private int buttonBar;
    private int buttonHeight;
    private boolean drawButtons;
    private boolean pushed;
    private int buttonPressed;
    private int progressHeight;
    private int textHeight;
    private int moveText;
    private boolean outlined;
    private static final int[] posFaceTransform;
    private static final int[][] posFaceletTransform;
    private final int[] hex;
    private static final int[] moveModes;
    private static final int[] moveCodes;
    private static final char[] modeChar;
    private static final String[][][] turnSymbol;
    private static final String[] modifierStrings;
    private static final String[] metricChar;
    private Thread animThread;
    private static final int[][][] cubeBlocks;
    private final int[][][] topBlocks;
    private final int[][][] midBlocks;
    private final int[][][] botBlocks;
    private static final int[][][] topBlockTable;
    private static final int[][][] midBlockTable;
    private static final int[][] topBlockFaceDim;
    private static final int[][] midBlockFaceDim;
    private static final int[][] botBlockFaceDim;
    private static final int[] cycleOrder;
    private static final int[] cycleFactors;
    private static final int[] cycleOffsets;
    private static final int[][] cycleLayerSides;
    private static final int[][] cycleCenters;
    private final int[] twistBuffer;
    private Graphics graphics;
    private Image image;
    private int width;
    private int height;
    private int lastX;
    private int lastY;
    private int lastDragX;
    private int lastDragY;
    private int dragAreas;
    private final int[][] dragCornersX;
    private final int[][] dragCornersY;
    private final double[] dragDirsX;
    private final double[] dragDirsY;
    private static final int[][][] dragBlocks;
    private static final int[][] areaDirs;
    private static final int[][] twistDirs;
    private int[] dragLayers;
    private int[] dragModes;
    private double dragX;
    private double dragY;
    private static final int[][][] rotCos;
    private static final int[][][] rotSin;
    private static final int[][][] rotVec;
    private static final int[] rotSign;
    private final double[] tempEye;
    private final double[] tempEyeX;
    private final double[] tempEyeY;
    private final double[] tempEye2;
    private final double[] tempEyeX2;
    private final double[] tempEyeY2;
    private final double[] perspEye;
    private final double[] perspEyeI;
    private final double[] perspNormal;
    private final double[][] eyeArray;
    private final double[][] eyeArrayX;
    private final double[][] eyeArrayY;
    private final int[][] eyeOrder;
    private final int[][][][] blockArray;
    private final int[][] blockMode;
    private final int[][] drawOrder;
    private final int[] fillX;
    private final int[] fillY;
    private final double[] coordsX;
    private final double[] coordsY;
    private final double[][] cooX;
    private final double[][] cooY;
    private static final double[][] border;
    private static final int[][] factors;
    private final double[] faceShiftX;
    private final double[] faceShiftY;
    private final double[] tempNormal;
    private static final int[] textOffset;
    private static final int[] buttonAction;
    private final double[] eyeD;
    private static final String[] buttonDescriptions;
    private String buttonDescription;
    
    public AnimCube() {
        this.config = new Hashtable();
        this.colors = new Color[24];
        this.cube = new int[6][9];
        this.initialCube = new int[6][9];
        this.eye = new double[] { 0.0, 0.0, -1.0 };
        this.eyeX = new double[] { 1.0, 0.0, 0.0 };
        this.eyeY = new double[3];
        this.initialEye = new double[3];
        this.initialEyeX = new double[3];
        this.initialEyeY = new double[3];
        this.natural = true;
        this.drawButtons = true;
        this.buttonPressed = -1;
        this.progressHeight = 6;
        this.outlined = true;
        this.hex = new int[6];
        this.animThread = null;
        this.topBlocks = new int[6][][];
        this.midBlocks = new int[6][][];
        this.botBlocks = new int[6][][];
        this.twistBuffer = new int[12];
        this.graphics = null;
        this.image = null;
        this.dragCornersX = new int[18][4];
        this.dragCornersY = new int[18][4];
        this.dragDirsX = new double[18];
        this.dragDirsY = new double[18];
        this.dragLayers = new int[18];
        this.dragModes = new int[18];
        this.tempEye = new double[3];
        this.tempEyeX = new double[3];
        this.tempEyeY = new double[3];
        this.tempEye2 = new double[3];
        this.tempEyeX2 = new double[3];
        this.tempEyeY2 = new double[3];
        this.perspEye = new double[3];
        this.perspEyeI = new double[3];
        this.perspNormal = new double[3];
        this.eyeArray = new double[3][];
        this.eyeArrayX = new double[3][];
        this.eyeArrayY = new double[3][];
        this.eyeOrder = new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 1, 1, 1 }, { 1, 0, 1 }, { 1, 0, 2 } };
        this.blockArray = new int[3][][][];
        this.blockMode = new int[][] { { 0, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
        this.drawOrder = new int[][] { { 0, 1, 2 }, { 2, 1, 0 }, { 0, 2, 1 } };
        this.fillX = new int[4];
        this.fillY = new int[4];
        this.coordsX = new double[8];
        this.coordsY = new double[8];
        this.cooX = new double[6][4];
        this.cooY = new double[6][4];
        this.faceShiftX = new double[6];
        this.faceShiftY = new double[6];
        this.tempNormal = new double[3];
        this.eyeD = new double[3];
        this.buttonDescription = "";
    }
    
    public void init() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.colors[0] = new Color(255, 128, 64);
        this.colors[1] = new Color(255, 0, 0);
        this.colors[2] = new Color(0, 255, 0);
        this.colors[3] = new Color(0, 0, 255);
        this.colors[4] = new Color(153, 153, 153);
        this.colors[5] = new Color(170, 170, 68);
        this.colors[6] = new Color(187, 119, 68);
        this.colors[7] = new Color(153, 68, 68);
        this.colors[8] = new Color(68, 119, 68);
        this.colors[9] = new Color(0, 68, 119);
        this.colors[10] = new Color(255, 255, 255);
        this.colors[11] = new Color(255, 255, 0);
        this.colors[12] = new Color(255, 96, 32);
        this.colors[13] = new Color(208, 0, 0);
        this.colors[14] = new Color(0, 144, 0);
        this.colors[15] = new Color(32, 64, 208);
        this.colors[16] = new Color(176, 176, 176);
        this.colors[17] = new Color(80, 80, 80);
        this.colors[18] = new Color(255, 0, 255);
        this.colors[19] = new Color(0, 255, 255);
        this.colors[20] = new Color(255, 160, 192);
        this.colors[21] = new Color(32, 255, 16);
        this.colors[22] = new Color(0, 0, 0);
        this.colors[23] = new Color(128, 128, 128);
        (this.animThread = new Thread(this, "Cube Animator")).start();
        final String parameter = this.getParameter("config");
        if (parameter != null) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), parameter).openStream()));
                for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                    final int index = s.indexOf(61);
                    if (index > 0) {
                        this.config.put(s.substring(0, index).trim(), s.substring(index + 1).trim());
                    }
                }
                bufferedReader.close();
            }
            catch (MalformedURLException ex) {
                System.err.println("Malformed URL: " + parameter + ": " + ex);
            }
            catch (IOException ex2) {
                System.err.println("Input error: " + parameter + ": " + ex2);
            }
        }
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null && parameter2.length() == 6) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (Character.toLowerCase(parameter2.charAt(i)) == "0123456789abcdef".charAt(j)) {
                        this.hex[i] = j;
                        break;
                    }
                }
            }
            this.bgColor = new Color(this.hex[0] * 16 + this.hex[1], this.hex[2] * 16 + this.hex[3], this.hex[4] * 16 + this.hex[5]);
        }
        else {
            this.bgColor = Color.gray;
        }
        final String parameter3 = this.getParameter("butbgcolor");
        if (parameter3 != null && parameter3.length() == 6) {
            for (int k = 0; k < 6; ++k) {
                for (int l = 0; l < 16; ++l) {
                    if (Character.toLowerCase(parameter3.charAt(k)) == "0123456789abcdef".charAt(l)) {
                        this.hex[k] = l;
                        break;
                    }
                }
            }
            this.buttonBgColor = new Color(this.hex[0] * 16 + this.hex[1], this.hex[2] * 16 + this.hex[3], this.hex[4] * 16 + this.hex[5]);
        }
        else {
            this.buttonBgColor = this.bgColor;
        }
        final String parameter4 = this.getParameter("colors");
        if (parameter4 != null) {
            for (int n = 0; n < 10 && n < parameter4.length() / 6; ++n) {
                for (int n2 = 0; n2 < 6; ++n2) {
                    for (int n3 = 0; n3 < 16; ++n3) {
                        if (Character.toLowerCase(parameter4.charAt(n * 6 + n2)) == "0123456789abcdef".charAt(n3)) {
                            this.hex[n2] = n3;
                            break;
                        }
                    }
                }
                this.colors[n] = new Color(this.hex[0] * 16 + this.hex[1], this.hex[2] * 16 + this.hex[3], this.hex[4] * 16 + this.hex[5]);
            }
        }
        for (int n4 = 0; n4 < 6; ++n4) {
            for (int n5 = 0; n5 < 9; ++n5) {
                this.cube[n4][n5] = n4 + 10;
            }
        }
        String s2 = "lluu";
        final String parameter5 = this.getParameter("colorscheme");
        if (parameter5 != null && parameter5.length() == 6) {
            for (int n6 = 0; n6 < 6; ++n6) {
                int n7 = 23;
                for (int n8 = 0; n8 < 23; ++n8) {
                    if (Character.toLowerCase(parameter5.charAt(n6)) == "0123456789wyorgbldmcpnk".charAt(n8)) {
                        n7 = n8;
                        break;
                    }
                }
                for (int n9 = 0; n9 < 9; ++n9) {
                    this.cube[n6][n9] = n7;
                }
            }
        }
        final String parameter6 = this.getParameter("pos");
        if (parameter6 != null && parameter6.length() == 54) {
            s2 = "uuuuff";
            if (this.bgColor == Color.gray) {
                this.bgColor = Color.white;
            }
            for (int n10 = 0; n10 < 6; ++n10) {
                final int n11 = AnimCube.posFaceTransform[n10];
                for (int n12 = 0; n12 < 9; ++n12) {
                    final int n13 = AnimCube.posFaceletTransform[n10][n12];
                    this.cube[n11][n13] = 23;
                    for (int n14 = 0; n14 < 14; ++n14) {
                        if (parameter6.charAt(n10 * 9 + n12) == "DFECABdfecabgh".charAt(n14)) {
                            this.cube[n11][n13] = n14 + 4;
                            break;
                        }
                    }
                }
            }
        }
        final String parameter7 = this.getParameter("facelets");
        if (parameter7 != null && parameter7.length() == 54) {
            for (int n15 = 0; n15 < 6; ++n15) {
                for (int n16 = 0; n16 < 9; ++n16) {
                    this.cube[n15][n16] = 23;
                    for (int n17 = 0; n17 < 23; ++n17) {
                        if (Character.toLowerCase(parameter7.charAt(n15 * 9 + n16)) == "0123456789wyorgbldmcpnk".charAt(n17)) {
                            this.cube[n15][n16] = n17;
                            break;
                        }
                    }
                }
            }
        }
        final String parameter8 = this.getParameter("move");
        this.move = ((parameter8 == null) ? new int[0][0] : this.getMove(parameter8, true));
        this.movePos = 0;
        this.curInfoText = -1;
        final String parameter9 = this.getParameter("initmove");
        if (parameter9 != null) {
            final int[][] array = parameter9.equals("#") ? this.move : this.getMove(parameter9, false);
            if (array.length > 0) {
                this.doMove(this.cube, array[0], 0, array[0].length, false);
            }
        }
        final String parameter10 = this.getParameter("initrevmove");
        if (parameter10 != null) {
            final int[][] array2 = parameter10.equals("#") ? this.move : this.getMove(parameter10, false);
            if (array2.length > 0) {
                this.doMove(this.cube, array2[0], 0, array2[0].length, true);
            }
        }
        final String parameter11 = this.getParameter("demo");
        if (parameter11 != null) {
            this.demoMove = (parameter11.equals("#") ? this.move : this.getMove(parameter11, true));
            if (this.demoMove.length > 0 && this.demoMove[0].length > 0) {
                this.demo = true;
            }
        }
        String parameter12 = this.getParameter("position");
        vNorm(vMul(this.eyeY, this.eye, this.eyeX));
        if (parameter12 == null) {
            parameter12 = s2;
        }
        final double n18 = 0.2617993877991494;
        for (int n19 = 0; n19 < parameter12.length(); ++n19) {
            double n20 = n18;
            switch (Character.toLowerCase(parameter12.charAt(n19))) {
                case 'd': {
                    n20 = -n20;
                }
                case 'u': {
                    vRotY(this.eye, n20);
                    vRotY(this.eyeX, n20);
                    break;
                }
                case 'f': {
                    n20 = -n20;
                }
                case 'b': {
                    vRotZ(this.eye, n20);
                    vRotZ(this.eyeX, n20);
                    break;
                }
                case 'l': {
                    n20 = -n20;
                }
                case 'r': {
                    vRotX(this.eye, n20);
                    vRotX(this.eyeX, n20);
                    break;
                }
            }
        }
        vNorm(vMul(this.eyeY, this.eye, this.eyeX));
        this.speed = 0;
        this.doubleSpeed = 0;
        final String parameter13 = this.getParameter("speed");
        if (parameter13 != null) {
            for (int n21 = 0; n21 < parameter13.length(); ++n21) {
                if (parameter13.charAt(n21) >= '0' && parameter13.charAt(n21) <= '9') {
                    this.speed = this.speed * 10 + parameter13.charAt(n21) - 48;
                }
            }
        }
        final String parameter14 = this.getParameter("doublespeed");
        if (parameter14 != null) {
            for (int n22 = 0; n22 < parameter14.length(); ++n22) {
                if (parameter14.charAt(n22) >= '0' && parameter14.charAt(n22) <= '9') {
                    this.doubleSpeed = this.doubleSpeed * 10 + parameter14.charAt(n22) - 48;
                }
            }
        }
        if (this.speed == 0) {
            this.speed = 10;
        }
        if (this.doubleSpeed == 0) {
            this.doubleSpeed = this.speed * 3 / 2;
        }
        this.persp = 0;
        final String parameter15 = this.getParameter("perspective");
        if (parameter15 == null) {
            this.persp = 2;
        }
        else {
            for (int n23 = 0; n23 < parameter15.length(); ++n23) {
                if (parameter15.charAt(n23) >= '0' && parameter15.charAt(n23) <= '9') {
                    this.persp = this.persp * 10 + parameter15.charAt(n23) - 48;
                }
            }
        }
        int n24 = 0;
        final String parameter16 = this.getParameter("scale");
        if (parameter16 != null) {
            for (int n25 = 0; n25 < parameter16.length(); ++n25) {
                if (parameter16.charAt(n25) >= '0' && parameter16.charAt(n25) <= '9') {
                    n24 = n24 * 10 + parameter16.charAt(n25) - 48;
                }
            }
        }
        this.scale = 1.0 / (1.0 + n24 / 10.0);
        this.hint = false;
        final String parameter17 = this.getParameter("hint");
        if (parameter17 != null) {
            this.hint = true;
            this.faceShift = 0.0;
            for (int n26 = 0; n26 < parameter17.length(); ++n26) {
                if (parameter17.charAt(n26) >= '0' && parameter17.charAt(n26) <= '9') {
                    this.faceShift = this.faceShift * 10.0 + parameter17.charAt(n26) - 48.0;
                }
            }
            if (this.faceShift < 1.0) {
                this.hint = false;
            }
            else {
                this.faceShift /= 10.0;
            }
        }
        this.buttonBar = 1;
        this.buttonHeight = 13;
        this.progressHeight = ((this.move.length == 0) ? 0 : 6);
        final String parameter18 = this.getParameter("buttonbar");
        if ("0".equals(parameter18)) {
            this.buttonBar = 0;
            this.buttonHeight = 0;
            this.progressHeight = 0;
        }
        else if ("1".equals(parameter18)) {
            this.buttonBar = 1;
        }
        else if ("2".equals(parameter18) || this.move.length == 0) {
            this.buttonBar = 2;
            this.progressHeight = 0;
        }
        if ("0".equals(this.getParameter("edit"))) {
            this.editable = false;
        }
        else {
            this.editable = true;
        }
        final String parameter19 = this.getParameter("movetext");
        if ("1".equals(parameter19)) {
            this.moveText = 1;
        }
        else if ("2".equals(parameter19)) {
            this.moveText = 2;
        }
        else if ("3".equals(parameter19)) {
            this.moveText = 3;
        }
        else if ("4".equals(parameter19)) {
            this.moveText = 4;
        }
        else {
            this.moveText = 0;
        }
        final String parameter20 = this.getParameter("fonttype");
        if (parameter20 == null || "1".equals(parameter20)) {
            this.outlined = true;
        }
        else {
            this.outlined = false;
        }
        this.metric = 0;
        final String parameter21 = this.getParameter("metric");
        if (parameter21 != null) {
            if ("1".equals(parameter21)) {
                this.metric = 1;
            }
            else if ("2".equals(parameter21)) {
                this.metric = 2;
            }
            else if ("3".equals(parameter21)) {
                this.metric = 3;
            }
        }
        this.align = 1;
        final String parameter22 = this.getParameter("align");
        if (parameter22 != null) {
            if ("0".equals(parameter22)) {
                this.align = 0;
            }
            else if ("1".equals(parameter22)) {
                this.align = 1;
            }
            else if ("2".equals(parameter22)) {
                this.align = 2;
            }
        }
        for (int n27 = 0; n27 < 6; ++n27) {
            for (int n28 = 0; n28 < 9; ++n28) {
                this.initialCube[n27][n28] = this.cube[n27][n28];
            }
        }
        for (int n29 = 0; n29 < 3; ++n29) {
            this.initialEye[n29] = this.eye[n29];
            this.initialEyeX[n29] = this.eyeX[n29];
            this.initialEyeY[n29] = this.eyeY[n29];
        }
        final int red = this.bgColor.getRed();
        final int green = this.bgColor.getGreen();
        final int blue = this.bgColor.getBlue();
        if ((red * 299 + green * 587 + blue * 114) / 1000 < 128) {
            this.textColor = Color.white;
            this.hlColor = this.bgColor.brighter();
            this.hlColor = new Color(this.hlColor.getBlue(), this.hlColor.getRed(), this.hlColor.getGreen());
        }
        else {
            this.textColor = Color.black;
            this.hlColor = this.bgColor.darker();
            this.hlColor = new Color(this.hlColor.getBlue(), this.hlColor.getRed(), this.hlColor.getGreen());
        }
        this.bgColor2 = new Color(red / 2, green / 2, blue / 2);
        this.curInfoText = -1;
        if (this.demo) {
            this.startAnimation(-1);
        }
    }
    
    public String getParameter(final String s) {
        final String parameter = super.getParameter(s);
        if (parameter == null) {
            return this.config.get(s);
        }
        return parameter;
    }
    
    private int[][] getMove(final String s, final boolean b) {
        if (b) {
            int n = 0;
            for (int i = s.indexOf(123); i != -1; i = s.indexOf(123, i + 1)) {
                ++n;
            }
            if (this.infoText == null) {
                this.curInfoText = 0;
                this.infoText = new String[n];
            }
            else {
                final String[] infoText = new String[this.infoText.length + n];
                for (int j = 0; j < this.infoText.length; ++j) {
                    infoText[j] = this.infoText[j];
                }
                this.curInfoText = this.infoText.length;
                this.infoText = infoText;
            }
        }
        int n2 = 1;
        for (int k = s.indexOf(59); k != -1; k = s.indexOf(59, k + 1)) {
            ++n2;
        }
        final int[][] array = new int[n2][];
        int n3 = 0;
        int l = s.indexOf(59);
        int n4 = 0;
        while (l != -1) {
            array[n4++] = this.getMovePart(s.substring(n3, l), b);
            n3 = l + 1;
            l = s.indexOf(59, n3);
        }
        array[n4] = this.getMovePart(s.substring(n3), b);
        return array;
    }
    
    private int[] getMovePart(final String s, final boolean b) {
        int n = 0;
        final int[] array = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '.') {
                array[n] = -1;
                ++n;
            }
            else if (s.charAt(i) == '{') {
                ++i;
                String string = "";
                while (i < s.length() && s.charAt(i) != '}') {
                    if (b) {
                        string += s.charAt(i);
                    }
                    ++i;
                }
                if (b) {
                    this.infoText[this.curInfoText] = string;
                    array[n] = 1000 + this.curInfoText;
                    ++this.curInfoText;
                    ++n;
                }
            }
            else {
                for (int j = 0; j < 21; ++j) {
                    if (s.charAt(i) == "UDFBLRESMXYZxyzudfblr".charAt(j)) {
                        ++i;
                        int n2 = AnimCube.moveModes[j];
                        array[n] = AnimCube.moveCodes[j] * 24;
                        if (i < s.length() && AnimCube.moveModes[j] == 0) {
                            for (int k = 0; k < AnimCube.modeChar.length; ++k) {
                                if (s.charAt(i) == AnimCube.modeChar[k]) {
                                    n2 = k + 1;
                                    ++i;
                                    break;
                                }
                            }
                        }
                        final int[] array2 = array;
                        final int n3 = n;
                        array2[n3] += n2 * 4;
                        if (i < s.length()) {
                            if (s.charAt(i) == '1') {
                                ++i;
                            }
                            else if (s.charAt(i) == '\'' || s.charAt(i) == '3') {
                                final int[] array3 = array;
                                final int n4 = n;
                                array3[n4] += 2;
                                ++i;
                            }
                            else if (s.charAt(i) == '2') {
                                if (++i < s.length() && s.charAt(i) == '\'') {
                                    final int[] array4 = array;
                                    final int n5 = n;
                                    array4[n5] += 3;
                                    ++i;
                                }
                                else {
                                    final int[] array5 = array;
                                    final int n6 = n;
                                    ++array5[n6];
                                }
                            }
                        }
                        ++n;
                        --i;
                        break;
                    }
                }
            }
        }
        final int[] array6 = new int[n];
        for (int l = 0; l < n; ++l) {
            array6[l] = array[l];
        }
        return array6;
    }
    
    private String moveText(final int[] array, final int n, final int n2) {
        if (n >= array.length) {
            return "";
        }
        String string = "";
        for (int i = n; i < n2; ++i) {
            string += this.turnText(array, i);
        }
        return string;
    }
    
    private String turnText(final int[] array, final int n) {
        if (n >= array.length) {
            return "";
        }
        if (array[n] >= 1000) {
            return "";
        }
        if (array[n] == -1) {
            return ".";
        }
        final String s = AnimCube.turnSymbol[this.moveText - 1][array[n] / 4 % 6][array[n] / 24];
        if (s.charAt(0) == '~') {
            return s.substring(1) + AnimCube.modifierStrings[(array[n] + 2) % 4];
        }
        return s + AnimCube.modifierStrings[array[n] % 4];
    }
    
    private static int realMoveLength(final int[] array) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 1000) {
                ++n;
            }
        }
        return n;
    }
    
    private static int realMovePos(final int[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array[i] < 1000) {
                ++n2;
            }
        }
        return n2;
    }
    
    private static int arrayMovePos(final int[] array, final int n) {
        int n2 = 0;
        int n3 = 0;
        while (true) {
            if (n2 >= array.length || array[n2] < 1000) {
                if (n3 == n) {
                    break;
                }
                if (n2 >= array.length) {
                    continue;
                }
                ++n3;
                ++n2;
            }
            else {
                ++n2;
            }
        }
        return n2;
    }
    
    private int moveLength(final int[] array, final int n) {
        int n2 = 0;
        for (int n3 = 0; n3 < array.length && (n3 < n || n < 0); ++n3) {
            n2 += this.turnLength(array[n3]);
        }
        return n2;
    }
    
    private int turnLength(final int n) {
        if (n < 0 || n >= 1000) {
            return 0;
        }
        final int n2 = n % 4;
        final int n3 = n / 4 % 6;
        int n4 = 1;
        switch (this.metric) {
            case 1: {
                if (n2 == 1 || n2 == 3) {
                    n4 *= 2;
                }
            }
            case 2: {
                if (n3 == 1 || n3 == 4 || n3 == 5) {
                    n4 *= 2;
                }
            }
            case 3: {
                if (n3 == 3) {
                    n4 = 0;
                    break;
                }
                break;
            }
        }
        return n4;
    }
    
    private void initInfoText(final int[] array) {
        if (array.length > 0 && array[0] >= 1000) {
            this.curInfoText = array[0] - 1000;
        }
        else {
            this.curInfoText = -1;
        }
    }
    
    private void doMove(final int[][] array, final int[] array2, final int n, final int n2, final boolean b) {
        int n3 = b ? (n + n2) : n;
        do {
            if (b) {
                if (n3 <= n) {
                    break;
                }
                --n3;
            }
            if (array2[n3] >= 1000) {
                this.curInfoText = (b ? -1 : (array2[n3] - 1000));
            }
            else {
                if (array2[n3] < 0) {
                    continue;
                }
                int n4 = array2[n3] % 4 + 1;
                final int n5 = array2[n3] / 4 % 6;
                if (n4 == 4) {
                    n4 = 2;
                }
                if (b) {
                    n4 = 4 - n4;
                }
                this.twistLayers(array, array2[n3] / 24, n4, n5);
            }
        } while (b || ++n3 < n + n2);
    }
    
    private void startAnimation(final int n) {
        synchronized (this.animThread) {
            this.stopAnimation();
            if (!this.demo && (this.move.length == 0 || this.move[this.curMove].length == 0)) {
                return;
            }
            if (this.demo && (this.demoMove.length == 0 || this.demoMove[0].length == 0)) {
                return;
            }
            this.moveDir = 1;
            this.moveOne = false;
            this.moveAnimated = true;
            switch (n) {
                case 1: {
                    this.moveDir = -1;
                    break;
                }
                case 2: {
                    this.moveOne = true;
                    break;
                }
                case 3: {
                    this.moveDir = -1;
                    this.moveOne = true;
                    break;
                }
                case 4: {
                    this.moveAnimated = false;
                    break;
                }
            }
            this.animThread.notify();
        }
    }
    
    public void stopAnimation() {
        synchronized (this.animThread) {
            this.restarted = true;
            this.animThread.notify();
            try {
                this.animThread.wait();
            }
            catch (InterruptedException ex) {
                this.interrupted = true;
            }
            this.restarted = false;
        }
    }
    
    public void run() {
        synchronized (this.animThread) {
            this.interrupted = false;
            do {
                if (this.restarted) {
                    this.animThread.notify();
                }
                try {
                    this.animThread.wait();
                }
                catch (InterruptedException ex) {
                    break;
                }
                if (this.restarted) {
                    continue;
                }
                boolean b = false;
                this.animating = true;
                this.drawButtons = true;
                final int[] array = this.demo ? this.demoMove[0] : this.move[this.curMove];
                if (this.moveDir > 0) {
                    if (this.movePos >= array.length) {
                        this.movePos = 0;
                        this.initInfoText(array);
                    }
                }
                else {
                    this.curInfoText = -1;
                    if (this.movePos == 0) {
                        this.movePos = array.length;
                    }
                }
                do {
                    if (this.moveDir < 0) {
                        if (this.movePos == 0) {
                            break;
                        }
                        --this.movePos;
                    }
                    if (array[this.movePos] == -1) {
                        this.repaint();
                        if (!this.moveOne) {
                            this.sleep(33 * this.speed);
                        }
                    }
                    else if (array[this.movePos] >= 1000) {
                        this.curInfoText = ((this.moveDir > 0) ? (array[this.movePos] - 1000) : -1);
                    }
                    else {
                        int n = array[this.movePos] % 4 + 1;
                        final int n2 = array[this.movePos] / 4 % 6;
                        boolean b2 = n < 3;
                        if (n == 4) {
                            n = 2;
                        }
                        if (this.moveDir < 0) {
                            b2 = !b2;
                            n = 4 - n;
                        }
                        this.spin(array[this.movePos] / 24, n, n2, b2, this.moveAnimated);
                        if (this.moveOne) {
                            b = true;
                        }
                    }
                    if (this.moveDir > 0) {
                        ++this.movePos;
                        if (this.movePos < array.length && array[this.movePos] >= 1000) {
                            this.curInfoText = array[this.movePos] - 1000;
                            ++this.movePos;
                        }
                        if (this.movePos != array.length) {
                            continue;
                        }
                        if (!this.demo) {
                            break;
                        }
                        this.movePos = 0;
                        this.initInfoText(array);
                        for (int i = 0; i < 6; ++i) {
                            for (int j = 0; j < 9; ++j) {
                                this.cube[i][j] = this.initialCube[i][j];
                            }
                        }
                    }
                    else {
                        this.curInfoText = -1;
                    }
                } while (!this.interrupted && !this.restarted && !b);
                this.animating = false;
                this.drawButtons = true;
                this.repaint();
                if (!this.demo) {
                    continue;
                }
                this.clear();
                this.demo = false;
            } while (!this.interrupted);
        }
    }
    
    private void sleep(final int n) {
        synchronized (this.animThread) {
            try {
                this.animThread.wait(n);
            }
            catch (InterruptedException ex) {
                this.interrupted = true;
            }
        }
    }
    
    private void clear() {
        synchronized (this.animThread) {
            this.movePos = 0;
            if (this.move.length > 0) {
                this.initInfoText(this.move[this.curMove]);
            }
            this.natural = true;
            this.mirrored = false;
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.cube[i][j] = this.initialCube[i][j];
                }
            }
            for (int k = 0; k < 3; ++k) {
                this.eye[k] = this.initialEye[k];
                this.eyeX[k] = this.initialEyeX[k];
                this.eyeY[k] = this.initialEyeY[k];
            }
        }
    }
    
    private void spin(final int twistedLayer, final int n, final int twistedMode, boolean b, final boolean b2) {
        this.twisting = false;
        this.natural = true;
        this.spinning = true;
        this.originalAngle = 0.0;
        if (AnimCube.faceTwistDirs[twistedLayer] > 0) {
            b = !b;
        }
        if (b2) {
            double n2 = 1.5707963267948966;
            final double n3 = b ? 1.0 : -1.0;
            int n4 = 67 * this.speed;
            if (n == 2) {
                n2 = 3.141592653589793;
                n4 = 67 * this.doubleSpeed;
            }
            this.twisting = true;
            this.twistedLayer = twistedLayer;
            this.twistedMode = twistedMode;
            this.splitCube(twistedLayer);
            final long currentTimeMillis = System.currentTimeMillis();
            final double n5 = n3 * n2 / n4;
            this.currentAngle = 0.0;
            while (this.currentAngle * n3 < n2) {
                this.repaint();
                this.sleep(25);
                if (this.interrupted) {
                    break;
                }
                if (this.restarted) {
                    break;
                }
                this.currentAngle = n5 * (System.currentTimeMillis() - currentTimeMillis);
            }
        }
        this.currentAngle = 0.0;
        this.twisting = false;
        this.natural = true;
        this.twistLayers(this.cube, twistedLayer, n, twistedMode);
        this.spinning = false;
        if (b2) {
            this.repaint();
        }
    }
    
    private void splitCube(final int n) {
        for (int i = 0; i < 6; ++i) {
            this.topBlocks[i] = AnimCube.topBlockTable[AnimCube.topBlockFaceDim[n][i]];
            this.botBlocks[i] = AnimCube.topBlockTable[AnimCube.botBlockFaceDim[n][i]];
            this.midBlocks[i] = AnimCube.midBlockTable[AnimCube.midBlockFaceDim[n][i]];
        }
        this.natural = false;
    }
    
    private void twistLayers(final int[][] array, final int n, final int n2, final int n3) {
        switch (n3) {
            case 3: {
                this.twistLayer(array, n ^ 0x1, n2, false);
            }
            case 2: {
                this.twistLayer(array, n, 4 - n2, false);
            }
            case 1: {
                this.twistLayer(array, n, 4 - n2, true);
                return;
            }
            case 5: {
                this.twistLayer(array, n ^ 0x1, 4 - n2, false);
                this.twistLayer(array, n, 4 - n2, false);
                return;
            }
            case 4: {
                this.twistLayer(array, n ^ 0x1, n2, false);
                break;
            }
        }
        this.twistLayer(array, n, 4 - n2, false);
    }
    
    private void twistLayer(final int[][] array, final int n, final int n2, final boolean b) {
        if (!b) {
            for (int i = 0; i < 8; ++i) {
                this.twistBuffer[(i + n2 * 2) % 8] = array[n][AnimCube.cycleOrder[i]];
            }
            for (int j = 0; j < 8; ++j) {
                array[n][AnimCube.cycleOrder[j]] = this.twistBuffer[j];
            }
        }
        int n3 = n2 * 3;
        for (int k = 0; k < 4; ++k) {
            final int n4 = AnimCube.adjacentFaces[n][k];
            final int n5 = b ? AnimCube.cycleCenters[n][k] : AnimCube.cycleLayerSides[n][k];
            final int n6 = AnimCube.cycleFactors[n5];
            final int n7 = AnimCube.cycleOffsets[n5];
            for (int l = 0; l < 3; ++l) {
                this.twistBuffer[n3 % 12] = array[n4][l * n6 + n7];
                ++n3;
            }
        }
        int n8 = 0;
        for (int n9 = 0; n9 < 4; ++n9) {
            final int n10 = AnimCube.adjacentFaces[n][n9];
            final int n11 = b ? AnimCube.cycleCenters[n][n9] : AnimCube.cycleLayerSides[n][n9];
            final int n12 = AnimCube.cycleFactors[n11];
            final int n13 = AnimCube.cycleOffsets[n11];
            for (int n14 = 0; n14 < 3; ++n14, ++n8) {
                array[n10][n14 * n12 + n13] = this.twistBuffer[n8];
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.image == null || size.width != this.width || size.height - this.buttonHeight != this.height) {
            this.width = size.width;
            this.height = size.height;
            this.image = this.createImage(this.width, this.height);
            this.graphics = this.image.getGraphics();
            this.textHeight = this.graphics.getFontMetrics().getHeight() - this.graphics.getFontMetrics().getLeading();
            if (this.buttonBar == 1) {
                this.height -= this.buttonHeight;
            }
            this.drawButtons = true;
        }
        this.graphics.setColor(this.bgColor);
        this.graphics.setClip(0, 0, this.width, this.height);
        this.graphics.fillRect(0, 0, this.width, this.height);
        synchronized (this.animThread) {
            this.dragAreas = 0;
            if (this.natural) {
                this.fixBlock(this.eye, this.eyeX, this.eyeY, AnimCube.cubeBlocks, 3);
            }
            else {
                final double cos = Math.cos(this.originalAngle + this.currentAngle);
                final double n = Math.sin(this.originalAngle + this.currentAngle) * AnimCube.rotSign[this.twistedLayer];
                for (int i = 0; i < 3; ++i) {
                    this.tempEye[i] = 0.0;
                    this.tempEyeX[i] = 0.0;
                    for (int j = 0; j < 3; ++j) {
                        final int n2 = this.twistedLayer / 2;
                        final double[] tempEye = this.tempEye;
                        final int n3 = i;
                        tempEye[n3] += this.eye[j] * (AnimCube.rotVec[n2][i][j] + AnimCube.rotCos[n2][i][j] * cos + AnimCube.rotSin[n2][i][j] * n);
                        final double[] tempEyeX = this.tempEyeX;
                        final int n4 = i;
                        tempEyeX[n4] += this.eyeX[j] * (AnimCube.rotVec[n2][i][j] + AnimCube.rotCos[n2][i][j] * cos + AnimCube.rotSin[n2][i][j] * n);
                    }
                }
                vMul(this.tempEyeY, this.tempEye, this.tempEyeX);
                final double cos2 = Math.cos(this.originalAngle - this.currentAngle);
                final double n5 = Math.sin(this.originalAngle - this.currentAngle) * AnimCube.rotSign[this.twistedLayer];
                for (int k = 0; k < 3; ++k) {
                    this.tempEye2[k] = 0.0;
                    this.tempEyeX2[k] = 0.0;
                    for (int l = 0; l < 3; ++l) {
                        final int n6 = this.twistedLayer / 2;
                        final double[] tempEye2 = this.tempEye2;
                        final int n7 = k;
                        tempEye2[n7] += this.eye[l] * (AnimCube.rotVec[n6][k][l] + AnimCube.rotCos[n6][k][l] * cos2 + AnimCube.rotSin[n6][k][l] * n5);
                        final double[] tempEyeX2 = this.tempEyeX2;
                        final int n8 = k;
                        tempEyeX2[n8] += this.eyeX[l] * (AnimCube.rotVec[n6][k][l] + AnimCube.rotCos[n6][k][l] * cos2 + AnimCube.rotSin[n6][k][l] * n5);
                    }
                }
                vMul(this.tempEyeY2, this.tempEye2, this.tempEyeX2);
                this.eyeArray[0] = this.eye;
                this.eyeArrayX[0] = this.eyeX;
                this.eyeArrayY[0] = this.eyeY;
                this.eyeArray[1] = this.tempEye;
                this.eyeArrayX[1] = this.tempEyeX;
                this.eyeArrayY[1] = this.tempEyeY;
                this.eyeArray[2] = this.tempEye2;
                this.eyeArrayX[2] = this.tempEyeX2;
                this.eyeArrayY[2] = this.tempEyeY2;
                this.blockArray[0] = this.topBlocks;
                this.blockArray[1] = this.midBlocks;
                this.blockArray[2] = this.botBlocks;
                vSub(vScale(vCopy(this.perspEye, this.eye), 5.0 + this.persp), vScale(vCopy(this.perspNormal, AnimCube.faceNormals[this.twistedLayer]), 0.3333333333333333));
                vSub(vScale(vCopy(this.perspEyeI, this.eye), 5.0 + this.persp), vScale(vCopy(this.perspNormal, AnimCube.faceNormals[this.twistedLayer ^ 0x1]), 0.3333333333333333));
                final double vProd = vProd(this.perspEye, AnimCube.faceNormals[this.twistedLayer]);
                final double vProd2 = vProd(this.perspEyeI, AnimCube.faceNormals[this.twistedLayer ^ 0x1]);
                int n9;
                if (vProd < 0.0 && vProd2 > 0.0) {
                    n9 = 0;
                }
                else if (vProd > 0.0 && vProd2 < 0.0) {
                    n9 = 1;
                }
                else {
                    n9 = 2;
                }
                this.fixBlock(this.eyeArray[this.eyeOrder[this.twistedMode][this.drawOrder[n9][0]]], this.eyeArrayX[this.eyeOrder[this.twistedMode][this.drawOrder[n9][0]]], this.eyeArrayY[this.eyeOrder[this.twistedMode][this.drawOrder[n9][0]]], this.blockArray[this.drawOrder[n9][0]], this.blockMode[this.twistedMode][this.drawOrder[n9][0]]);
                this.fixBlock(this.eyeArray[this.eyeOrder[this.twistedMode][this.drawOrder[n9][1]]], this.eyeArrayX[this.eyeOrder[this.twistedMode][this.drawOrder[n9][1]]], this.eyeArrayY[this.eyeOrder[this.twistedMode][this.drawOrder[n9][1]]], this.blockArray[this.drawOrder[n9][1]], this.blockMode[this.twistedMode][this.drawOrder[n9][1]]);
                this.fixBlock(this.eyeArray[this.eyeOrder[this.twistedMode][this.drawOrder[n9][2]]], this.eyeArrayX[this.eyeOrder[this.twistedMode][this.drawOrder[n9][2]]], this.eyeArrayY[this.eyeOrder[this.twistedMode][this.drawOrder[n9][2]]], this.blockArray[this.drawOrder[n9][2]], this.blockMode[this.twistedMode][this.drawOrder[n9][2]]);
            }
            if (!this.pushed && !this.animating) {
                this.buttonPressed = -1;
            }
            if (!this.demo && this.move.length > 0) {
                if (this.move[this.curMove].length > 0) {
                    this.graphics.setColor(Color.black);
                    this.graphics.drawRect(0, this.height - this.progressHeight, this.width - 1, this.progressHeight - 1);
                    this.graphics.setColor(this.textColor);
                    final int n10 = (this.width - 2) * realMovePos(this.move[this.curMove], this.movePos) / realMoveLength(this.move[this.curMove]);
                    this.graphics.fillRect(1, this.height - this.progressHeight + 1, n10, this.progressHeight - 2);
                    this.graphics.setColor(this.bgColor.darker());
                    this.graphics.fillRect(1 + n10, this.height - this.progressHeight + 1, this.width - 2 - n10, this.progressHeight - 2);
                    final String string = "" + this.moveLength(this.move[this.curMove], this.movePos) + "/" + this.moveLength(this.move[this.curMove], -1) + AnimCube.metricChar[this.metric];
                    final int n11 = this.width - this.graphics.getFontMetrics().stringWidth(string) - 2;
                    final int n12 = this.height - this.progressHeight - 2;
                    if (this.moveText > 0 && this.textHeight > 0) {
                        this.drawString(this.graphics, string, n11, n12 - this.textHeight);
                        this.drawMoveText(this.graphics, n12);
                    }
                    else {
                        this.drawString(this.graphics, string, n11, n12);
                    }
                }
                if (this.move.length > 1) {
                    this.graphics.setClip(0, 0, this.width, this.height);
                    final int n13 = this.textHeight - this.graphics.getFontMetrics().getDescent();
                    final String string2 = "" + (this.curMove + 1) + "/" + this.move.length;
                    this.drawString(this.graphics, string2, this.width - this.graphics.getFontMetrics().stringWidth(string2) - this.buttonHeight - 2, n13);
                    this.graphics.setColor(this.buttonBgColor);
                    this.graphics.fill3DRect(this.width - this.buttonHeight, 0, this.buttonHeight, this.buttonHeight, this.buttonPressed != 7);
                    this.drawButton(this.graphics, 7, this.width - this.buttonHeight / 2, this.buttonHeight / 2);
                }
            }
            if (this.curInfoText >= 0) {
                this.graphics.setClip(0, 0, this.width, this.height);
                this.drawString(this.graphics, this.infoText[this.curInfoText], 0, this.textHeight - this.graphics.getFontMetrics().getDescent());
            }
            if (this.drawButtons && this.buttonBar != 0) {
                this.drawButtons(this.graphics);
            }
        }
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void fixBlock(final double[] array, final double[] array2, final double[] array3, final int[][][] array4, final int n) {
        for (int i = 0; i < 8; ++i) {
            final double n2 = (this.width < this.height) ? this.width : ((double)(this.height - this.progressHeight));
            final double n3 = n2 / 3.7 * vProd(AnimCube.cornerCoords[i], array2) * this.scale;
            final double n4 = n2 / 3.7 * vProd(AnimCube.cornerCoords[i], array3) * this.scale;
            final double n5 = n2 / (5.0 + this.persp) * vProd(AnimCube.cornerCoords[i], array) * this.scale;
            final double n6 = n3 / (1.0 - n5 / n2);
            final double n7 = n4 / (1.0 - n5 / n2);
            this.coordsX[i] = this.width / 2.0 + n6;
            if (this.align == 0) {
                this.coordsY[i] = (this.height - this.progressHeight) / 2.0 * this.scale - n7;
            }
            else if (this.align == 2) {
                this.coordsY[i] = this.height - this.progressHeight - (this.height - this.progressHeight) / 2.0 * this.scale - n7;
            }
            else {
                this.coordsY[i] = (this.height - this.progressHeight) / 2.0 - n7;
            }
        }
        for (int j = 0; j < 6; ++j) {
            for (int k = 0; k < 4; ++k) {
                this.cooX[j][k] = this.coordsX[AnimCube.faceCorners[j][k]];
                this.cooY[j][k] = this.coordsY[AnimCube.faceCorners[j][k]];
            }
        }
        if (this.hint) {
            for (int l = 0; l < 6; ++l) {
                vSub(vScale(vCopy(this.perspEye, array), 5.0 + this.persp), AnimCube.faceNormals[l]);
                if (vProd(this.perspEye, AnimCube.faceNormals[l]) < 0.0) {
                    vScale(vCopy(this.tempNormal, AnimCube.faceNormals[l]), this.faceShift);
                    final double n8 = (this.width < this.height) ? this.width : ((double)(this.height - this.progressHeight));
                    final double n9 = n8 / 3.7 * vProd(this.tempNormal, array2);
                    final double n10 = n8 / 3.7 * vProd(this.tempNormal, array3);
                    final double n11 = n8 / (5.0 + this.persp) * vProd(this.tempNormal, array);
                    final double n12 = n9 / (1.0 - n11 / n8);
                    final double n13 = n10 / (1.0 - n11 / n8);
                    final int n14 = array4[l][0][1] - array4[l][0][0];
                    final int n15 = array4[l][1][1] - array4[l][1][0];
                    if (n14 > 0 && n15 > 0) {
                        for (int n16 = 0, n17 = array4[l][1][0]; n16 < n15; ++n16, ++n17) {
                            for (int n18 = 0, n19 = array4[l][0][0]; n18 < n14; ++n18, ++n19) {
                                for (int n20 = 0; n20 < 4; ++n20) {
                                    this.getCorners(l, n20, this.fillX, this.fillY, n19 + AnimCube.border[n20][0], n17 + AnimCube.border[n20][1], this.mirrored);
                                    final int[] fillX = this.fillX;
                                    final int n21 = n20;
                                    fillX[n21] += (int)(this.mirrored ? (-n12) : n12);
                                    final int[] fillY = this.fillY;
                                    final int n22 = n20;
                                    fillY[n22] -= (int)n13;
                                }
                                this.graphics.setColor(this.colors[this.cube[l][n17 * 3 + n19]]);
                                this.graphics.fillPolygon(this.fillX, this.fillY, 4);
                                this.graphics.setColor(this.colors[this.cube[l][n17 * 3 + n19]].darker());
                                this.graphics.drawPolygon(this.fillX, this.fillY, 4);
                            }
                        }
                    }
                }
            }
        }
        for (int n23 = 0; n23 < 6; ++n23) {
            final int n24 = array4[n23][0][1] - array4[n23][0][0];
            final int n25 = array4[n23][1][1] - array4[n23][1][0];
            if (n24 > 0 && n25 > 0) {
                for (int n26 = 0; n26 < 4; ++n26) {
                    this.getCorners(n23, n26, this.fillX, this.fillY, array4[n23][0][AnimCube.factors[n26][0]], array4[n23][1][AnimCube.factors[n26][1]], this.mirrored);
                }
                if (n24 == 3 && n25 == 3) {
                    this.graphics.setColor(this.bgColor2);
                }
                else {
                    this.graphics.setColor(Color.black);
                }
                this.graphics.drawPolygon(this.fillX, this.fillY, 4);
            }
        }
        for (int n27 = 0; n27 < 6; ++n27) {
            final int n28 = array4[n27][0][1] - array4[n27][0][0];
            final int n29 = array4[n27][1][1] - array4[n27][1][0];
            if (n28 <= 0 || n29 <= 0) {
                for (int n30 = 0; n30 < 4; ++n30) {
                    final int n31 = AnimCube.oppositeCorners[n27][n30];
                    this.fillX[n30] = (int)(this.cooX[n27][n30] + (this.cooX[n27 ^ 0x1][n31] - this.cooX[n27][n30]) * 2.0 / 3.0);
                    this.fillY[n30] = (int)(this.cooY[n27][n30] + (this.cooY[n27 ^ 0x1][n31] - this.cooY[n27][n30]) * 2.0 / 3.0);
                    if (this.mirrored) {
                        this.fillX[n30] = this.width - this.fillX[n30];
                    }
                }
                this.graphics.setColor(Color.black);
                this.graphics.fillPolygon(this.fillX, this.fillY, 4);
            }
            else {
                for (int n32 = 0; n32 < 4; ++n32) {
                    this.getCorners(n27, n32, this.fillX, this.fillY, array4[n27][0][AnimCube.factors[n32][0]], array4[n27][1][AnimCube.factors[n32][1]], this.mirrored);
                }
                this.graphics.setColor(Color.black);
                this.graphics.fillPolygon(this.fillX, this.fillY, 4);
            }
        }
        for (int n33 = 0; n33 < 6; ++n33) {
            vSub(vScale(vCopy(this.perspEye, array), 5.0 + this.persp), AnimCube.faceNormals[n33]);
            if (vProd(this.perspEye, AnimCube.faceNormals[n33]) > 0.0) {
                final int n34 = array4[n33][0][1] - array4[n33][0][0];
                final int n35 = array4[n33][1][1] - array4[n33][1][0];
                if (n34 > 0 && n35 > 0) {
                    for (int n36 = 0, n37 = array4[n33][1][0]; n36 < n35; ++n36, ++n37) {
                        for (int n38 = 0, n39 = array4[n33][0][0]; n38 < n34; ++n38, ++n39) {
                            for (int n40 = 0; n40 < 4; ++n40) {
                                this.getCorners(n33, n40, this.fillX, this.fillY, n39 + AnimCube.border[n40][0], n37 + AnimCube.border[n40][1], this.mirrored);
                            }
                            this.graphics.setColor(this.colors[this.cube[n33][n37 * 3 + n39]].darker());
                            this.graphics.drawPolygon(this.fillX, this.fillY, 4);
                            this.graphics.setColor(this.colors[this.cube[n33][n37 * 3 + n39]]);
                            this.graphics.fillPolygon(this.fillX, this.fillY, 4);
                        }
                    }
                }
                if (this.editable) {
                    if (!this.animating) {
                        final double n41 = (this.cooX[n33][1] - this.cooX[n33][0] + this.cooX[n33][2] - this.cooX[n33][3]) / 6.0;
                        final double n42 = (this.cooX[n33][3] - this.cooX[n33][0] + this.cooX[n33][2] - this.cooX[n33][1]) / 6.0;
                        final double n43 = (this.cooY[n33][1] - this.cooY[n33][0] + this.cooY[n33][2] - this.cooY[n33][3]) / 6.0;
                        final double n44 = (this.cooY[n33][3] - this.cooY[n33][0] + this.cooY[n33][2] - this.cooY[n33][1]) / 6.0;
                        if (n == 3) {
                            for (int n45 = 0; n45 < 6; ++n45) {
                                for (int n46 = 0; n46 < 4; ++n46) {
                                    this.getCorners(n33, n46, this.dragCornersX[this.dragAreas], this.dragCornersY[this.dragAreas], AnimCube.dragBlocks[n45][n46][0], AnimCube.dragBlocks[n45][n46][1], false);
                                }
                                this.dragDirsX[this.dragAreas] = (n41 * AnimCube.areaDirs[n45][0] + n43 * AnimCube.areaDirs[n45][1]) * AnimCube.twistDirs[n33][n45];
                                this.dragDirsY[this.dragAreas] = (n42 * AnimCube.areaDirs[n45][0] + n44 * AnimCube.areaDirs[n45][1]) * AnimCube.twistDirs[n33][n45];
                                this.dragLayers[this.dragAreas] = AnimCube.adjacentFaces[n33][n45 % 4];
                                if (n45 >= 4) {
                                    final int[] dragLayers = this.dragLayers;
                                    final int dragAreas = this.dragAreas;
                                    dragLayers[dragAreas] &= 0xFFFFFFFE;
                                }
                                this.dragModes[this.dragAreas] = n45 / 4;
                                ++this.dragAreas;
                                if (this.dragAreas == 18) {
                                    break;
                                }
                            }
                        }
                        else if (n == 0) {
                            if (n33 != this.twistedLayer && n34 > 0 && n35 > 0) {
                                final int n47 = (n34 == 3) ? ((array4[n33][1][0] == 0) ? 0 : 2) : ((array4[n33][0][0] == 0) ? 3 : 1);
                                for (int n48 = 0; n48 < 4; ++n48) {
                                    this.getCorners(n33, n48, this.dragCornersX[this.dragAreas], this.dragCornersY[this.dragAreas], AnimCube.dragBlocks[n47][n48][0], AnimCube.dragBlocks[n47][n48][1], false);
                                }
                                this.dragDirsX[this.dragAreas] = (n41 * AnimCube.areaDirs[n47][0] + n43 * AnimCube.areaDirs[n47][1]) * AnimCube.twistDirs[n33][n47];
                                this.dragDirsY[this.dragAreas] = (n42 * AnimCube.areaDirs[n47][0] + n44 * AnimCube.areaDirs[n47][1]) * AnimCube.twistDirs[n33][n47];
                                this.dragLayers[this.dragAreas] = this.twistedLayer;
                                this.dragModes[this.dragAreas] = 0;
                                ++this.dragAreas;
                            }
                        }
                        else if (n == 1 && n33 != this.twistedLayer && n34 > 0 && n35 > 0) {
                            final int n49 = (n34 == 3) ? 4 : 5;
                            for (int n50 = 0; n50 < 4; ++n50) {
                                this.getCorners(n33, n50, this.dragCornersX[this.dragAreas], this.dragCornersY[this.dragAreas], AnimCube.dragBlocks[n49][n50][0], AnimCube.dragBlocks[n49][n50][1], false);
                            }
                            this.dragDirsX[this.dragAreas] = (n41 * AnimCube.areaDirs[n49][0] + n43 * AnimCube.areaDirs[n49][1]) * AnimCube.twistDirs[n33][n49];
                            this.dragDirsY[this.dragAreas] = (n42 * AnimCube.areaDirs[n49][0] + n44 * AnimCube.areaDirs[n49][1]) * AnimCube.twistDirs[n33][n49];
                            this.dragLayers[this.dragAreas] = this.twistedLayer;
                            this.dragModes[this.dragAreas] = 1;
                            ++this.dragAreas;
                        }
                    }
                }
            }
        }
    }
    
    private void getCorners(final int n, final int n2, final int[] array, final int[] array2, double n3, double n4, final boolean b) {
        n3 /= 3.0;
        n4 /= 3.0;
        final double n5 = this.cooX[n][0] + (this.cooX[n][1] - this.cooX[n][0]) * n3;
        final double n6 = this.cooY[n][0] + (this.cooY[n][1] - this.cooY[n][0]) * n3;
        final double n7 = this.cooX[n][3] + (this.cooX[n][2] - this.cooX[n][3]) * n3;
        final double n8 = this.cooY[n][3] + (this.cooY[n][2] - this.cooY[n][3]) * n3;
        array[n2] = (int)(0.5 + n5 + (n7 - n5) * n4);
        array2[n2] = (int)(0.5 + n6 + (n8 - n6) * n4);
        if (b) {
            array[n2] = this.width - array[n2];
        }
    }
    
    private void drawButtons(final Graphics graphics) {
        if (this.buttonBar == 2) {
            graphics.setColor(this.buttonBgColor);
            graphics.fill3DRect(0, this.height - this.buttonHeight, this.buttonHeight, this.buttonHeight, this.buttonPressed != 0);
            this.drawButton(graphics, 0, this.buttonHeight / 2, this.height - (this.buttonHeight + 1) / 2);
            return;
        }
        if (this.buttonBar == 1) {
            graphics.setClip(0, this.height, this.width, this.buttonHeight);
            int n = 0;
            for (int i = 0; i < 7; ++i) {
                final int n2 = (this.width - n) / (7 - i);
                graphics.setColor(this.buttonBgColor);
                graphics.fill3DRect(n, this.height, n2, this.buttonHeight, this.buttonPressed != i);
                this.drawButton(graphics, i, n + n2 / 2, this.height + this.buttonHeight / 2);
                n += n2;
            }
            this.drawButtons = false;
        }
    }
    
    private void drawButton(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(Color.white);
        switch (n) {
            case 0: {
                drawRect(graphics, n2 - 4, n3 - 3, 3, 7);
                drawArrow(graphics, n2 + 3, n3, -1);
                break;
            }
            case 1: {
                drawRect(graphics, n2 + 2, n3 - 3, 3, 7);
                drawArrow(graphics, n2, n3, -1);
                break;
            }
            case 2: {
                drawArrow(graphics, n2 + 2, n3, -1);
                break;
            }
            case 3: {
                if (this.animating) {
                    drawRect(graphics, n2 - 3, n3 - 3, 7, 7);
                    break;
                }
                drawRect(graphics, n2 - 3, n3 - 2, 7, 5);
                drawRect(graphics, n2 - 1, n3 - 4, 3, 9);
                break;
            }
            case 4: {
                drawArrow(graphics, n2 - 2, n3, 1);
                break;
            }
            case 5: {
                drawRect(graphics, n2 - 4, n3 - 3, 3, 7);
                drawArrow(graphics, n2, n3, 1);
                break;
            }
            case 6: {
                drawRect(graphics, n2 + 1, n3 - 3, 3, 7);
                drawArrow(graphics, n2 - 4, n3, 1);
                break;
            }
            case 7: {
                drawArrow(graphics, n2 - 2, n3, 1);
                break;
            }
        }
    }
    
    private static void drawArrow(final Graphics graphics, int n, final int n2, final int n3) {
        graphics.setColor(Color.black);
        graphics.drawLine(n, n2 - 3, n, n2 + 3);
        n += n3;
        for (int n4 = 0; n4 >= -3 && n4 <= 3; n4 += n3) {
            final int n5 = 3 - n4 * n3;
            graphics.drawLine(n + n4, n2 + n5, n + n4, n2 - n5);
        }
        graphics.setColor(Color.white);
        for (int n6 = 0; n6 >= -1 && n6 <= 1; n6 += n3) {
            final int n7 = 1 - n6 * n3;
            graphics.drawLine(n + n6, n2 + n7, n + n6, n2 - n7);
        }
    }
    
    private static void drawRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Color.black);
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        graphics.setColor(Color.white);
        graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
    }
    
    private void drawString(final Graphics graphics, final String s, final int n, final int n2) {
        if (this.outlined) {
            graphics.setColor(Color.black);
            for (int i = 0; i < AnimCube.textOffset.length; i += 2) {
                graphics.drawString(s, n + AnimCube.textOffset[i], n2 + AnimCube.textOffset[i + 1]);
            }
            graphics.setColor(Color.white);
        }
        else {
            graphics.setColor(this.textColor);
        }
        graphics.drawString(s, n, n2);
    }
    
    private void drawMoveText(final Graphics graphics, final int n) {
        graphics.setClip(0, this.height - this.progressHeight - this.textHeight, this.width, this.textHeight);
        graphics.setColor(Color.black);
        final int n2 = (this.movePos == 0) ? arrayMovePos(this.move[this.curMove], this.movePos) : this.movePos;
        final String moveText = this.moveText(this.move[this.curMove], 0, n2);
        final String turnText = this.turnText(this.move[this.curMove], n2);
        final String moveText2 = this.moveText(this.move[this.curMove], n2 + 1, this.move[this.curMove].length);
        final int stringWidth = graphics.getFontMetrics().stringWidth(moveText);
        final int stringWidth2 = graphics.getFontMetrics().stringWidth(turnText);
        final int stringWidth3 = graphics.getFontMetrics().stringWidth(moveText2);
        int max = 1;
        if (max + stringWidth + stringWidth2 + stringWidth3 > this.width) {
            max = Math.max(Math.min(1, this.width / 2 - stringWidth - stringWidth2 / 2), this.width - stringWidth - stringWidth2 - stringWidth3 - 2);
        }
        if (stringWidth2 > 0) {
            graphics.setColor(this.hlColor);
            graphics.fillRect(max + stringWidth - 1, this.height - this.progressHeight - this.textHeight, stringWidth2 + 2, this.textHeight);
        }
        if (stringWidth > 0) {
            this.drawString(graphics, moveText, max, n);
        }
        if (stringWidth2 > 0) {
            this.drawString(graphics, turnText, max + stringWidth, n);
        }
        if (stringWidth3 > 0) {
            this.drawString(graphics, moveText2, max + stringWidth + stringWidth2, n);
        }
    }
    
    private int selectButton(final int n, final int n2) {
        if (this.buttonBar == 0) {
            return -1;
        }
        if (this.move.length > 1 && n >= this.width - this.buttonHeight && n < this.width && n2 >= 0 && n2 < this.buttonHeight) {
            return 7;
        }
        if (this.buttonBar == 2) {
            if (n >= 0 && n < this.buttonHeight && n2 >= this.height - this.buttonHeight && n2 < this.height) {
                return 0;
            }
            return -1;
        }
        else {
            if (n2 < this.height) {
                return -1;
            }
            int n3 = 0;
            for (int i = 0; i < 7; ++i) {
                final int n4 = (this.width - n3) / (7 - i);
                if (n >= n3 && n < n3 + n4 && n2 >= this.height && n2 < this.height + this.buttonHeight) {
                    return i;
                }
                n3 += n4;
            }
            return -1;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        this.lastX = x;
        this.lastDragX = x;
        final int y = mouseEvent.getY();
        this.lastY = y;
        this.lastDragY = y;
        this.toTwist = false;
        this.buttonPressed = this.selectButton(this.lastX, this.lastY);
        if (this.buttonPressed >= 0) {
            this.pushed = true;
            if (this.buttonPressed == 3) {
                if (!this.animating) {
                    this.mirrored = !this.mirrored;
                }
                else {
                    this.stopAnimation();
                }
            }
            else if (this.buttonPressed == 0) {
                this.stopAnimation();
                this.clear();
            }
            else if (this.buttonPressed == 7) {
                this.stopAnimation();
                this.clear();
                this.curMove = ((this.curMove < this.move.length - 1) ? (this.curMove + 1) : 0);
            }
            else {
                this.startAnimation(AnimCube.buttonAction[this.buttonPressed]);
            }
            this.drawButtons = true;
            this.repaint();
        }
        else if (this.progressHeight > 0 && this.move.length > 0 && this.move[this.curMove].length > 0 && this.lastY >= this.height - this.progressHeight && this.lastY < this.height) {
            this.stopAnimation();
            final int realMoveLength = realMoveLength(this.move[this.curMove]);
            int movePos = Math.max(0, Math.min(realMoveLength, ((this.lastX - 1) * realMoveLength * 2 / (this.width - 2) + 1) / 2));
            if (movePos > 0) {
                movePos = arrayMovePos(this.move[this.curMove], movePos);
            }
            if (movePos > this.movePos) {
                this.doMove(this.cube, this.move[this.curMove], this.movePos, movePos - this.movePos, false);
            }
            if (movePos < this.movePos) {
                this.doMove(this.cube, this.move[this.curMove], movePos, this.movePos - movePos, true);
            }
            this.movePos = movePos;
            this.dragging = true;
            this.repaint();
        }
        else {
            if (this.mirrored) {
                final int n = this.width - this.lastX;
                this.lastX = n;
                this.lastDragX = n;
            }
            if (this.editable && !this.animating && (mouseEvent.getModifiers() & 0x10) != 0x0 && (mouseEvent.getModifiers() & 0x1) == 0x0) {
                this.toTwist = true;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.dragging = false;
        if (this.pushed) {
            this.pushed = false;
            this.drawButtons = true;
            this.repaint();
        }
        else if (this.twisting && !this.spinning) {
            this.twisting = false;
            this.originalAngle += this.currentAngle;
            this.currentAngle = 0.0;
            double originalAngle;
            for (originalAngle = this.originalAngle; originalAngle < 0.0; originalAngle += 100.53096491487338) {}
            final int n = (int)(originalAngle * 8.0 / 3.141592653589793) % 16;
            if (n % 4 == 0 || n % 4 == 3) {
                int n2 = (n + 1) / 4;
                if (AnimCube.faceTwistDirs[this.twistedLayer] > 0) {
                    n2 = (4 - n2) % 4;
                }
                this.originalAngle = 0.0;
                this.natural = true;
                this.twistLayers(this.cube, this.twistedLayer, n2, this.twistedMode);
            }
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.pushed) {
            return;
        }
        if (this.dragging) {
            this.stopAnimation();
            final int realMoveLength = realMoveLength(this.move[this.curMove]);
            int movePos = Math.max(0, Math.min(realMoveLength, ((mouseEvent.getX() - 1) * realMoveLength * 2 / (this.width - 2) + 1) / 2));
            if (movePos > 0) {
                movePos = arrayMovePos(this.move[this.curMove], movePos);
            }
            if (movePos > this.movePos) {
                this.doMove(this.cube, this.move[this.curMove], this.movePos, movePos - this.movePos, false);
            }
            if (movePos < this.movePos) {
                this.doMove(this.cube, this.move[this.curMove], movePos, this.movePos - movePos, true);
            }
            this.movePos = movePos;
            this.repaint();
            return;
        }
        final int n = this.mirrored ? (this.width - mouseEvent.getX()) : mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n2 = n - this.lastX;
        final int n3 = y - this.lastY;
        if (this.editable && this.toTwist && !this.twisting && !this.animating) {
            this.lastDragX = n;
            this.lastDragY = y;
            for (int i = 0; i < this.dragAreas; ++i) {
                final double n4 = this.dragCornersX[i][0];
                final double n5 = this.dragCornersX[i][1] - n4;
                final double n6 = this.dragCornersX[i][3] - n4;
                final double n7 = this.dragCornersY[i][0];
                final double n8 = this.dragCornersY[i][1] - n7;
                final double n9 = this.dragCornersY[i][3] - n7;
                final double n10 = (n9 * (this.lastX - n4) - n6 * (this.lastY - n7)) / (n5 * n9 - n6 * n8);
                final double n11 = (-n8 * (this.lastX - n4) + n5 * (this.lastY - n7)) / (n5 * n9 - n6 * n8);
                if (n10 > 0.0 && n10 < 1.0 && n11 > 0.0 && n11 < 1.0) {
                    if (n2 * n2 + n3 * n3 < 144) {
                        return;
                    }
                    this.dragX = this.dragDirsX[i];
                    this.dragY = this.dragDirsY[i];
                    if (Math.abs(this.dragX * n2 + this.dragY * n3) / Math.sqrt((this.dragX * this.dragX + this.dragY * this.dragY) * (n2 * n2 + n3 * n3)) > 0.75) {
                        this.twisting = true;
                        this.twistedLayer = this.dragLayers[i];
                        this.twistedMode = this.dragModes[i];
                        break;
                    }
                }
            }
            this.toTwist = false;
            this.lastX = this.lastDragX;
            this.lastY = this.lastDragY;
        }
        final int n12 = n - this.lastX;
        final int n13 = y - this.lastY;
        if (!this.twisting || this.animating) {
            vNorm(vAdd(this.eye, vScale(vCopy(this.eyeD, this.eyeX), n12 * -0.016)));
            vNorm(vMul(this.eyeX, this.eyeY, this.eye));
            vNorm(vAdd(this.eye, vScale(vCopy(this.eyeD, this.eyeY), n13 * 0.016)));
            vNorm(vMul(this.eyeY, this.eye, this.eyeX));
            this.lastX = n;
            this.lastY = y;
        }
        else {
            if (this.natural) {
                this.splitCube(this.twistedLayer);
            }
            this.currentAngle = 0.03 * (this.dragX * n12 + this.dragY * n13) / Math.sqrt(this.dragX * this.dragX + this.dragY * this.dragY);
        }
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        String buttonDescription = "Drag the cube with a mouse";
        if (x >= 0 && x < this.width) {
            if ((y >= this.height && y < this.height + this.buttonHeight) || (y >= 0 && y < this.buttonHeight)) {
                this.buttonPressed = this.selectButton(x, y);
                if (this.buttonPressed >= 0) {
                    buttonDescription = AnimCube.buttonDescriptions[this.buttonPressed];
                }
                if (this.buttonPressed == 3 && !this.animating) {
                    buttonDescription = "Mirror the cube view";
                }
            }
            else if (this.progressHeight > 0 && this.move.length > 0 && this.move[this.curMove].length > 0 && y >= this.height - this.progressHeight && y < this.height) {
                buttonDescription = "Current progress";
            }
        }
        if (buttonDescription != this.buttonDescription) {
            this.showStatus(this.buttonDescription = buttonDescription);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private static double[] vCopy(final double[] array, final double[] array2) {
        array[0] = array2[0];
        array[1] = array2[1];
        array[2] = array2[2];
        return array;
    }
    
    private static double[] vNorm(final double[] array) {
        final double sqrt = Math.sqrt(vProd(array, array));
        final int n = 0;
        array[n] /= sqrt;
        final int n2 = 1;
        array[n2] /= sqrt;
        final int n3 = 2;
        array[n3] /= sqrt;
        return array;
    }
    
    private static double[] vScale(final double[] array, final double n) {
        final int n2 = 0;
        array[n2] *= n;
        final int n3 = 1;
        array[n3] *= n;
        final int n4 = 2;
        array[n4] *= n;
        return array;
    }
    
    private static double vProd(final double[] array, final double[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    private static double[] vAdd(final double[] array, final double[] array2) {
        final int n = 0;
        array[n] += array2[0];
        final int n2 = 1;
        array[n2] += array2[1];
        final int n3 = 2;
        array[n3] += array2[2];
        return array;
    }
    
    private static double[] vSub(final double[] array, final double[] array2) {
        final int n = 0;
        array[n] -= array2[0];
        final int n2 = 1;
        array[n2] -= array2[1];
        final int n3 = 2;
        array[n3] -= array2[2];
        return array;
    }
    
    private static double[] vMul(final double[] array, final double[] array2, final double[] array3) {
        array[0] = array2[1] * array3[2] - array2[2] * array3[1];
        array[1] = array2[2] * array3[0] - array2[0] * array3[2];
        array[2] = array2[0] * array3[1] - array2[1] * array3[0];
        return array;
    }
    
    private static double[] vRotX(final double[] array, final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final double n2 = array[1] * cos - array[2] * sin;
        final double n3 = array[1] * sin + array[2] * cos;
        array[1] = n2;
        array[2] = n3;
        return array;
    }
    
    private static double[] vRotY(final double[] array, final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final double n2 = array[0] * cos - array[2] * sin;
        final double n3 = array[0] * sin + array[2] * cos;
        array[0] = n2;
        array[2] = n3;
        return array;
    }
    
    private static double[] vRotZ(final double[] array, final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final double n2 = array[0] * cos - array[1] * sin;
        final double n3 = array[0] * sin + array[1] * cos;
        array[0] = n2;
        array[1] = n3;
        return array;
    }
    
    static {
        faceNormals = new double[][] { { 0.0, -1.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, -1.0 }, { 0.0, 0.0, 1.0 }, { -1.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0 } };
        cornerCoords = new double[][] { { -1.0, -1.0, -1.0 }, { 1.0, -1.0, -1.0 }, { 1.0, -1.0, 1.0 }, { -1.0, -1.0, 1.0 }, { -1.0, 1.0, -1.0 }, { 1.0, 1.0, -1.0 }, { 1.0, 1.0, 1.0 }, { -1.0, 1.0, 1.0 } };
        faceCorners = new int[][] { { 0, 1, 2, 3 }, { 4, 7, 6, 5 }, { 0, 4, 5, 1 }, { 2, 6, 7, 3 }, { 0, 3, 7, 4 }, { 1, 5, 6, 2 } };
        oppositeCorners = new int[][] { { 0, 3, 2, 1 }, { 0, 3, 2, 1 }, { 3, 2, 1, 0 }, { 3, 2, 1, 0 }, { 0, 3, 2, 1 }, { 0, 3, 2, 1 } };
        adjacentFaces = new int[][] { { 2, 5, 3, 4 }, { 4, 3, 5, 2 }, { 4, 1, 5, 0 }, { 5, 1, 4, 0 }, { 0, 3, 1, 2 }, { 2, 1, 3, 0 } };
        faceTwistDirs = new int[] { 1, 1, -1, -1, -1, -1 };
        posFaceTransform = new int[] { 3, 2, 0, 5, 1, 4 };
        posFaceletTransform = new int[][] { { 6, 3, 0, 7, 4, 1, 8, 5, 2 }, { 2, 5, 8, 1, 4, 7, 0, 3, 6 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 6, 3, 0, 7, 4, 1, 8, 5, 2 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8 } };
        moveModes = new int[] { 0, 0, 0, 0, 0, 0, 1, 1, 1, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2 };
        moveCodes = new int[] { 0, 1, 2, 3, 4, 5, 1, 2, 4, 5, 2, 0, 5, 2, 0, 0, 1, 2, 3, 4, 5 };
        modeChar = new char[] { 'm', 't', 'c', 's', 'a' };
        turnSymbol = new String[][][] { { { "U", "D", "F", "B", "L", "R" }, { "Um", "Dm", "Fm", "Bm", "Lm", "Rm" }, { "Ut", "Dt", "Ft", "Bt", "Lt", "Rt" }, { "Uc", "Dc", "Fc", "Bc", "Lc", "Rc" }, { "Us", "Ds", "Fs", "Bs", "Ls", "Rs" }, { "Ua", "Da", "Fa", "Ba", "La", "Ra" } }, { { "U", "D", "F", "B", "L", "R" }, { "~E", "E", "S", "~S", "M", "~M" }, { "u", "d", "f", "b", "l", "r" }, { "Z", "~Z", "Y", "~Y", "~X", "X" }, { "Us", "Ds", "Fs", "Bs", "Ls", "Rs" }, { "Ua", "Da", "Fa", "Ba", "La", "Ra" } }, { { "U", "D", "F", "B", "L", "R" }, { "~E", "E", "S", "~S", "M", "~M" }, { "u", "d", "f", "b", "l", "r" }, { "Y", "~Y", "Z", "~Z", "~X", "X" }, { "Us", "Ds", "Fs", "Bs", "Ls", "Rs" }, { "Ua", "Da", "Fa", "Ba", "La", "Ra" } }, { { "U", "D", "F", "B", "L", "R" }, { "u", "d", "f", "b", "l", "r" }, { "Uu", "Dd", "Ff", "Bb", "Ll", "Rr" }, { "QU", "QD", "QF", "QB", "QL", "QR" }, { "UD'", "DU'", "FB'", "BF'", "LR'", "RL'" }, { "UD", "DU", "FB", "BF", "LR", "RL" } } };
        modifierStrings = new String[] { "", "2", "'", "2'" };
        metricChar = new String[] { "", "q", "f", "s" };
        cubeBlocks = new int[][][] { { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 3 } } };
        topBlockTable = new int[][][] { { { 0, 0 }, { 0, 0 } }, { { 0, 3 }, { 0, 3 } }, { { 0, 3 }, { 0, 1 } }, { { 0, 1 }, { 0, 3 } }, { { 0, 3 }, { 2, 3 } }, { { 2, 3 }, { 0, 3 } } };
        midBlockTable = new int[][][] { { { 0, 0 }, { 0, 0 } }, { { 0, 3 }, { 1, 2 } }, { { 1, 2 }, { 0, 3 } } };
        topBlockFaceDim = new int[][] { { 1, 0, 3, 3, 2, 3 }, { 0, 1, 5, 5, 4, 5 }, { 2, 3, 1, 0, 3, 2 }, { 4, 5, 0, 1, 5, 4 }, { 3, 2, 2, 4, 1, 0 }, { 5, 4, 4, 2, 0, 1 } };
        midBlockFaceDim = new int[][] { { 0, 0, 2, 2, 1, 2 }, { 0, 0, 2, 2, 1, 2 }, { 1, 2, 0, 0, 2, 1 }, { 1, 2, 0, 0, 2, 1 }, { 2, 1, 1, 1, 0, 0 }, { 2, 1, 1, 1, 0, 0 } };
        botBlockFaceDim = new int[][] { { 0, 1, 5, 5, 4, 5 }, { 1, 0, 3, 3, 2, 3 }, { 4, 5, 0, 1, 5, 4 }, { 2, 3, 1, 0, 3, 2 }, { 5, 4, 4, 2, 0, 1 }, { 3, 2, 2, 4, 1, 0 } };
        cycleOrder = new int[] { 0, 1, 2, 5, 8, 7, 6, 3 };
        cycleFactors = new int[] { 1, 3, -1, -3, 1, 3, -1, -3 };
        cycleOffsets = new int[] { 0, 2, 8, 6, 3, 1, 5, 7 };
        cycleLayerSides = new int[][] { { 3, 3, 3, 0 }, { 2, 1, 1, 1 }, { 3, 3, 0, 0 }, { 2, 1, 1, 2 }, { 3, 2, 0, 0 }, { 2, 2, 0, 1 } };
        cycleCenters = new int[][] { { 7, 7, 7, 4 }, { 6, 5, 5, 5 }, { 7, 7, 4, 4 }, { 6, 5, 5, 6 }, { 7, 6, 4, 4 }, { 6, 6, 4, 5 } };
        dragBlocks = new int[][][] { { { 0, 0 }, { 3, 0 }, { 3, 1 }, { 0, 1 } }, { { 3, 0 }, { 3, 3 }, { 2, 3 }, { 2, 0 } }, { { 3, 3 }, { 0, 3 }, { 0, 2 }, { 3, 2 } }, { { 0, 3 }, { 0, 0 }, { 1, 0 }, { 1, 3 } }, { { 0, 1 }, { 3, 1 }, { 3, 2 }, { 0, 2 } }, { { 2, 0 }, { 2, 3 }, { 1, 3 }, { 1, 0 } } };
        areaDirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        twistDirs = new int[][] { { 1, 1, 1, 1, 1, -1 }, { 1, 1, 1, 1, 1, -1 }, { 1, -1, 1, -1, 1, 1 }, { 1, -1, 1, -1, -1, 1 }, { -1, 1, -1, 1, -1, -1 }, { 1, -1, 1, -1, 1, 1 } };
        rotCos = new int[][][] { { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } }, { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } } };
        rotSin = new int[][][] { { { 0, 0, 1 }, { 0, 0, 0 }, { -1, 0, 0 } }, { { 0, 1, 0 }, { -1, 0, 0 }, { 0, 0, 0 } }, { { 0, 0, 0 }, { 0, 0, 1 }, { 0, -1, 0 } } };
        rotVec = new int[][][] { { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } }, { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } } };
        rotSign = new int[] { 1, -1, 1, -1, 1, -1 };
        border = new double[][] { { 0.1, 0.1 }, { 0.9, 0.1 }, { 0.9, 0.9 }, { 0.1, 0.9 } };
        factors = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
        textOffset = new int[] { 1, 1, -1, -1, -1, 1, 1, -1, -1, 0, 1, 0, 0, 1, 0, -1 };
        buttonAction = new int[] { -1, 3, 1, -1, 0, 2, 4, -1 };
        buttonDescriptions = new String[] { "Clear to the initial state", "Show the previous step", "Play backward", "Stop", "Play", "Show the next step", "Go to the end", "Next sequence" };
    }
}

import java.awt.AWTEvent;
import java.io.DataOutput;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.PopupMenu;
import java.util.Hashtable;
import java.util.Vector;
import java.io.InputStream;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.io.DataInputStream;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotCanvas extends PlotterCanvas implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    Dimension Afegir;
    Image Composa;
    Graphics DrawRectCorrecte;
    AppInterface I;
    Afinitat2x2 M;
    Afinitat2x2 ImposaColor;
    double ImposaFontPropia;
    double ObteOmegaStrings2;
    private DataInputStream data;
    Color[] RectangleReal;
    Font[] abs;
    float[] actionPerformed;
    boolean add;
    boolean Z;
    MessageBox addActionListener;
    static Color addElement;
    static Font addKeyListener;
    public static final Cursor CURSOR_ZOOM;
    public static final Cursor CURSOR_ZOOM_RECTANGULAR;
    public static final Cursor CURSOR_MOVE_POINTS;
    public static final Cursor CURSOR_MOVING_POINT;
    public static final Cursor CURSOR_WAIT;
    static final int[] addMouseListener;
    static final int[] addMouseMotionListener;
    static final int[] afegirCapsaComandes;
    static final int[] append;
    int aspectRatio1;
    Point atan2;
    double available;
    public AbstractBox capsaComandes;
    public FormulaEditor formula;
    OmegaClient baseline;
    InputStream black;
    Vector calculaBoundingBoxMarc;
    Vector calculaSalt;
    Hashtable calculator;
    private boolean textPressed;
    private boolean firstPlot;
    boolean C;
    boolean B;
    private boolean infoChanged;
    private boolean showGridChanged;
    private boolean showAxisChanged;
    private boolean boundingChanged;
    private boolean isFirstUpdate;
    int clearAllPoints;
    String clearCurrent;
    String clearMouseLabel;
    Font clearRect;
    Color clone;
    Color colorDissimulat;
    Color colorMig;
    boolean colorToString;
    boolean compileImage;
    boolean contains;
    boolean copyInto;
    Color cos;
    static boolean createGraphics;
    float createImage;
    boolean dibuixaPoligon;
    static final Dimension dibuixaPoligonal;
    double dispose;
    double double2String;
    PopupMenu draw;
    boolean drawArc;
    Point drawImage;
    Point drawLine;
    
    public PlotCanvas(final InputStream inputStream, final AbstractBox abstractBox, final FormulaEditor formula, final AppInterface i) {
        this.addActionListener = null;
        this.aspectRatio1 = 0;
        this.atan2 = new Point(0, 0);
        this.available = 9.0;
        this.black = null;
        this.firstPlot = true;
        this.C = true;
        this.B = true;
        this.infoChanged = false;
        this.showGridChanged = false;
        this.showAxisChanged = false;
        this.boundingChanged = false;
        this.isFirstUpdate = true;
        this.clearAllPoints = 0;
        this.clearRect = PlotCanvas.addKeyListener;
        this.clone = Color.lightGray;
        this.colorDissimulat = Color.white;
        this.colorMig = Color.lightGray;
        this.colorToString = true;
        this.compileImage = false;
        this.contains = false;
        this.copyInto = false;
        this.createImage = 2.0f;
        this.dibuixaPoligon = false;
        this.draw = null;
        this.drawArc = false;
        this.drawImage = null;
        this.drawLine = null;
        this.formula = formula;
        this.I = i;
        try {
            this.baseline = OmegaClient.newOmega();
            if (i != null) {
                this.baseline.setDocumentBase(i.getDocumentBase());
            }
        }
        catch (OException ex) {
            System.out.println(ex.getMessage());
        }
        this.M = new Afinitat2x2();
        this.setData(inputStream, abstractBox);
        this.setLocation(0, 0);
        this.setBackground(this.colorDissimulat);
        this.calculaBoundingBoxMarc(3);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        if (OmegaSystem.versio_java >= 2) {
            PlotCanvas.createGraphics = true;
        }
        else {
            PlotCanvas.createGraphics = false;
        }
    }
    
    public final DataInputStream getData() {
        return this.data;
    }
    
    private final void Afegir(final BoxComponent boxComponent, final Graphics graphics, final float n) {
        float float1 = 0.0f;
        float float2 = 0.0f;
        int int1 = 0;
        GeneralPath generalPath = null;
        if (PlotCanvas.createGraphics) {
            try {
                IsGraphics2D.setAntialiasing(graphics, true, OmegaSystem.versio_java);
                GraphicsUtils.setRendering(graphics, true);
            }
            catch (Exception ex2) {
                PlotCanvas.createGraphics = false;
            }
        }
        this.clearMouseLabel();
        this.textPressed = false;
        final Cursor cursor2;
        Cursor cursor = cursor2 = this.getCursor();
        switch (this.aspectRatio1) {
            case 1: {
                cursor = PlotCanvas.CURSOR_ZOOM;
                break;
            }
            case 2: {
                cursor = PlotCanvas.CURSOR_ZOOM_RECTANGULAR;
                break;
            }
            case 3: {
                cursor = PlotCanvas.CURSOR_MOVE_POINTS;
                break;
            }
        }
        final Afinitat2x2 m = this.M;
        GraphicsUtils.setLineWidth(graphics, 1.0f / n);
        final float n2 = boxComponent.getDPI() / 72.0f / n;
        if (this.B) {
            this.addKeyListener(graphics, n2);
        }
        if (this.C) {
            this.addElement(boxComponent, graphics, n2);
        }
        this.CURSOR_MOVE_POINTS();
        try {
            this.data.reset();
            this.data.readByte();
            this.data.readByte();
            this.data.readByte();
            this.data.readUTF();
            this.data.readUTF();
            this.actionPerformed();
            float float3 = 6.0f;
            int n3 = 0;
            int n4 = 1;
            this.add = false;
            int n5 = 0;
            while (this.data.available() > 0) {
                if (n4 != 0) {
                    n3 = this.data.readShort();
                }
                else {
                    n4 = 1;
                }
                final int capsaComandes = capsaComandes(n3, this.data);
                switch (n3) {
                    case -15615: {
                        super.currentName = this.data.readUTF();
                        n5 = 0;
                        continue;
                    }
                    case -15358: {
                        super.currentValue = this.data.readUTF();
                        continue;
                    }
                    case -15359: {
                        super.currentDefinition = this.data.readUTF();
                        continue;
                    }
                    case -15357: {
                        super.currentLabel = this.data.readUTF();
                        continue;
                    }
                    case 1028: {
                        this.copyInto = true;
                        continue;
                    }
                    case 1029: {
                        this.contains = true;
                        continue;
                    }
                    case 770: {
                        this.CURSOR_MOVE_POINTS();
                        this.M = m;
                        int1 = 0;
                        continue;
                    }
                    case -15871: {
                        float float4 = this.data.readFloat();
                        float float5 = this.data.readFloat();
                        if (this.isPointSelected(super.currentName)) {
                            final float[] point = this.getPoint(super.currentName);
                            float4 = point[0];
                            float5 = point[1];
                        }
                        final int[] w2si = this.M.w2si(float4, float5);
                        final int round = Math.round(float3 / n);
                        if (OmegaSystem.versio_java < 2 && !GraphicsUtils.isFileGraphics(graphics) && round != 8) {
                            graphics.drawOval(w2si[0] - round / 2, w2si[1] - round / 2, round - 1, round - 1);
                        }
                        final boolean b = (w2si[0] - this.atan2.x) * (w2si[0] - this.atan2.x) + (w2si[1] - this.atan2.y) * (w2si[1] - this.atan2.y) < round * round / 4 + 16;
                        if (this.contains && b) {
                            final Color color = graphics.getColor();
                            final Color color2 = new Color(this.colorDissimulat.getRGB() ^ color.getRGB());
                            final Color colorMig = GraphicsUtils.colorMig(color, color2, 1, 1);
                            graphics.setColor(GraphicsUtils.colorMig(color, color2, 1, 2));
                            graphics.fillOval(w2si[0] - round / 2 - 2, w2si[1] - round / 2 - 2, round + 4, round + 4);
                            graphics.setColor(colorMig);
                            graphics.fillOval(w2si[0] - round / 2 - 1, w2si[1] - round / 2 - 1, round + 2, round + 2);
                            graphics.setColor(color);
                        }
                        graphics.fillOval(w2si[0] - round / 2, w2si[1] - round / 2, round, round);
                        AbstractBox composa = null;
                        if (this.copyInto) {
                            composa = this.Composa(boxComponent, w2si[0], w2si[1], graphics, n);
                        }
                        Label_1100: {
                            if (b || (composa != null && composa.RectangleReal().contains(this.atan2))) {
                                this.CURSOR_MOVING_POINT(graphics);
                                switch (this.aspectRatio1) {
                                    case 3: {
                                        if (!this.contains) {
                                            break;
                                        }
                                        cursor = PlotCanvas.CURSOR_MOVING_POINT;
                                        switch (super.mouseState) {
                                            case 1: {
                                                this.setSelectedPoint(super.currentName);
                                                break Label_1100;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        this.setPointIfUndefined(super.currentName, new float[] { float4, float5 });
                        this.setMovablePoint(super.currentName, this.contains);
                        this.M = m;
                        continue;
                    }
                    case -15870: {
                        final int[] w2si2 = this.M.w2si(this.data.readFloat(), this.data.readFloat());
                        final float[] w2sf = this.M.w2sf(this.data.readFloat(), this.data.readFloat());
                        this.ImposaColor(w2si2[0], w2si2[1], w2sf[0], w2sf[1], this.createImage, graphics);
                        if (this.copyInto) {
                            this.Composa(boxComponent, Math.round((w2si2[0] + w2sf[0]) / 2.0f), Math.round((w2si2[1] + w2sf[1]) / 2.0f), graphics, n);
                        }
                        if (this.CURSOR_ZOOM_RECTANGULAR(this.atan2.x, this.atan2.y, w2si2[0], w2si2[1], w2sf[0], w2sf[1]) >= this.available) {
                            continue;
                        }
                        this.CURSOR_MOVING_POINT(graphics);
                        continue;
                    }
                    case -15869: {
                        final float float6 = this.data.readFloat();
                        final float float7 = this.data.readFloat();
                        final float float8 = this.data.readFloat();
                        final double[] w2s = this.M.w2s(float6 - float8, float7 + float8);
                        final double[] w2s2 = this.M.w2s(float6 + float8, float7 - float8);
                        final double n6 = w2s2[0] - w2s[0];
                        final double n7 = w2s2[1] - w2s[1];
                        this.RectangleReal((float)w2s[0], (float)w2s[1], (float)n6, (float)n7, this.createImage, graphics);
                        final double[] w2s3 = this.M.w2s(float6, float7);
                        if (this.copyInto) {
                            this.Composa(boxComponent, (int)Math.round(w2s[0] + 93.0 * n6 / 100.0), (int)Math.round(w2s[1] + n7 / 4.0), graphics, n);
                        }
                        if (this.CURSOR_ZOOM(this.atan2.x, this.atan2.y, w2s3[0], w2s3[1], n6 / 2.0, n7 / 2.0, 0) >= this.available) {
                            continue;
                        }
                        this.CURSOR_MOVING_POINT(graphics);
                        continue;
                    }
                    case -15868: {
                        final float float9 = this.data.readFloat();
                        final float float10 = this.data.readFloat();
                        final float float11 = this.data.readFloat();
                        final float float12 = this.data.readFloat();
                        final float float13 = this.data.readFloat();
                        final double[] w2s4 = this.M.w2s(float9 - float11, float10 + float11);
                        final double[] w2s5 = this.M.w2s(float9 + float11, float10 - float11);
                        final double n8 = w2s5[0] - w2s4[0];
                        final double n9 = w2s5[1] - w2s4[1];
                        this.abs((float)w2s4[0], (float)w2s4[1], (float)n8, (float)n9, float12, float13, this.createImage, graphics);
                        final double[] w2s6 = this.M.w2s(float9, float10);
                        if (this.copyInto) {
                            this.Composa(boxComponent, (int)Math.round(w2s4[0] + n8 * (1.0 + Math.cos(float12)) / 2.0), (int)Math.round(w2s4[1] + n9 * (1.0 - Math.sin(float12)) / 2.0), graphics, n);
                        }
                        if (this.CURSOR_WAIT(this.atan2.x, this.atan2.y, w2s6[0], w2s6[1], n8 / 2.0, n9 / 2.0, float12, float13) >= this.available) {
                            continue;
                        }
                        this.CURSOR_MOVING_POINT(graphics);
                        continue;
                    }
                    case -15867: {
                        final int int2 = this.data.readInt();
                        final float[] array = new float[int2];
                        final float[] array2 = new float[int2];
                        int n10 = 0;
                        for (int i = 0; i < int2; ++i) {
                            final double[] w2s7 = this.M.w2s(this.data.readFloat(), this.data.readFloat());
                            if (Math.abs(w2s7[0]) < 30000.0 && Math.abs(w2s7[1]) < 30000.0) {
                                array[n10] = (float)w2s7[0];
                                array2[n10] = (float)w2s7[1];
                                if (n10 > 0 && this.CURSOR_ZOOM_RECTANGULAR(this.atan2.x, this.atan2.y, array[n10 - 1], array2[n10 - 1], array[n10], array2[n10]) < this.available) {
                                    this.CURSOR_MOVING_POINT(graphics);
                                }
                                ++n10;
                            }
                        }
                        if (n10 > 0 && this.CURSOR_ZOOM_RECTANGULAR(this.atan2.x, this.atan2.y, array[n10 - 1], array2[n10 - 1], array[0], array2[0]) < this.available) {
                            this.CURSOR_MOVING_POINT(graphics);
                        }
                        if (PlotCanvas.createGraphics) {
                            IsGraphics2D.dibuixaPoligon(array, array2, n10, this.createImage, graphics, this.add, true);
                        }
                        else {
                            this.ImposaFontPropia(array, array2, n10, this.createImage, graphics, this.add);
                        }
                        if (!this.copyInto) {
                            continue;
                        }
                        float n11 = array[0];
                        float n12 = array2[0];
                        if (array.length > 1) {
                            n11 = (2.0f * n11 + array[1]) / 3.0f;
                            n12 = (2.0f * n12 + array2[1]) / 3.0f;
                        }
                        this.Composa(boxComponent, Math.round(n11), Math.round(n12), graphics, n);
                        continue;
                    }
                    case -15866: {
                        final int int3 = this.data.readInt();
                        final float[] array3 = new float[int3];
                        final float[] array4 = new float[int3];
                        int n13 = 0;
                        for (int j = 0; j < int3; ++j) {
                            final float[] w2sf2 = this.M.w2sf(this.data.readFloat(), this.data.readFloat());
                            if (generalPath != null) {
                                if (n13 == 0) {
                                    generalPath.moveTo(w2sf2[0], w2sf2[1]);
                                }
                                else {
                                    generalPath.lineTo(w2sf2[0], w2sf2[1]);
                                }
                            }
                            if (Math.abs(w2sf2[0]) < 30000.0f && Math.abs(w2sf2[1]) < 30000.0f) {
                                array3[n13] = w2sf2[0];
                                array4[n13] = w2sf2[1];
                                if (n13 > 0 && this.CURSOR_ZOOM_RECTANGULAR(this.atan2.x, this.atan2.y, array3[n13 - 1], array4[n13 - 1], array3[n13], array4[n13]) < this.available) {
                                    this.CURSOR_MOVING_POINT(graphics);
                                }
                                ++n13;
                            }
                        }
                        if (generalPath == null) {
                            if (PlotCanvas.createGraphics) {
                                IsGraphics2D.dibuixaPoligonal(array3, array4, n13, graphics, this.add);
                            }
                            else {
                                this.ObteOmegaStrings2(array3, array4, n13, this.createImage, graphics, this.add);
                            }
                        }
                        if (!this.copyInto) {
                            continue;
                        }
                        float n14 = array3[0];
                        float n15 = array4[0];
                        if (array3.length > 1) {
                            n14 = (2.0f * n14 + array3[1]) / 3.0f;
                            n15 = (2.0f * n15 + array4[1]) / 3.0f;
                        }
                        this.Composa(boxComponent, Math.round(n14), Math.round(n15), graphics, n);
                        continue;
                    }
                    case -15865: {
                        final double n16 = this.data.readFloat();
                        final double n17 = this.data.readFloat();
                        int[] w2si3;
                        if (int1 == 0) {
                            w2si3 = this.M.w2si(n16, n17);
                        }
                        else {
                            final Rectangle calculator = this.calculator((int)this.M.w, (int)this.M.h);
                            w2si3 = new int[] { (int)(calculator.x + calculator.width + n16), (int)(calculator.y + calculator.height + n17) };
                        }
                        final String utf = this.data.readUTF();
                        int short1 = 2;
                        short short2 = 0;
                        int int4 = 0;
                        int n18 = 0;
                        int int5 = -1;
                        int int6 = -1;
                        int int7 = Integer.MAX_VALUE;
                        int n19 = 1;
                        while (this.data.available() > 0 && n19 != 0) {
                            n3 = this.data.readShort();
                            switch (n3) {
                                case 17921: {
                                    short1 = this.data.readShort();
                                    continue;
                                }
                                case 17922: {
                                    short2 = this.data.readShort();
                                    continue;
                                }
                                case -31229:
                                case -14845: {
                                    int4 = this.data.readInt();
                                    continue;
                                }
                                case -31228:
                                case -14844: {
                                    int5 = this.data.readInt();
                                    continue;
                                }
                                case 17925: {
                                    n18 = ((this.data.readShort() == 1) ? 1 : 0);
                                    continue;
                                }
                                case -31226:
                                case -14842: {
                                    int6 = this.data.readInt();
                                    continue;
                                }
                                case -31225: {
                                    int7 = this.data.readInt();
                                    continue;
                                }
                                default: {
                                    n4 = 0;
                                    n19 = 0;
                                    continue;
                                }
                            }
                        }
                        ++n5;
                        AbstractBox box = null;
                        AbstractBox parse = null;
                        if (n5 == 1) {
                            box = this.getBox(super.currentName);
                        }
                        if (box == null || this.firstPlot) {
                            parse = boxComponent.parse(utf);
                            if (int7 != Integer.MAX_VALUE) {
                                parse.recommendedWidth = new Length(int7 + "px");
                                final MultilineBox multilineBox = new MultilineBox();
                                multilineBox.inicialitzaFills(parse);
                                parse = multilineBox;
                            }
                        }
                        if (this.firstPlot && box != null && parse != null) {
                            if (((Slider)box).getClass() == ((Slider)parse).getClass()) {
                                final Hashtable hashtable = new Hashtable();
                                parse.exchangeProperties(hashtable, 1);
                                box.exchangeProperties(hashtable, 0);
                            }
                            else {
                                parse = box;
                                box = null;
                            }
                        }
                        if (box == null) {
                            box = parse;
                            box.ImposaColor(graphics.getColor());
                            box.ImposaFontPropia(graphics.getFont());
                            box.Composa(boxComponent);
                        }
                        if (box instanceof Slider) {
                            ((Slider)box).setColor(GraphicsUtils.colorToString(GraphicsUtils.colorDissimulat(graphics.getColor())));
                        }
                        box.x = w2si3[0];
                        box.y = w2si3[1];
                        if (n5 == 1 && box instanceof Slider) {
                            this.setBox(super.currentName, box);
                        }
                        switch (short1) {
                            case 0: {
                                final Slider slider = (Slider)box;
                                slider.x -= box.width;
                                break;
                            }
                            case 1: {
                                final Slider slider2 = (Slider)box;
                                slider2.x -= box.width / 2;
                                break;
                            }
                        }
                        switch (short2) {
                            case 0: {
                                final Slider slider3 = (Slider)box;
                                slider3.y -= box.height;
                                break;
                            }
                            case 1: {
                                final Slider slider4 = (Slider)box;
                                slider4.y -= box.height / 2;
                                break;
                            }
                            case 2: {
                                final Slider slider5 = (Slider)box;
                                slider5.y -= box.baseline;
                                break;
                            }
                        }
                        if (n18 != 0) {
                            if (int6 > 0) {
                                graphics.setColor(this.RectangleReal[int6]);
                            }
                            else {
                                graphics.setColor(Color.white);
                            }
                            GraphicsUtils.setRendering(graphics, false);
                            GraphicsUtils.setLineWidth(graphics, int4);
                            graphics.fillRect(box.x - 3, box.y - 1, box.width + 4, box.height + 2);
                            GraphicsUtils.setRendering(graphics, true);
                        }
                        if (this.atan2.x > box.x && this.atan2.x < box.x + box.width && this.atan2.y > box.y && this.atan2.y < box.y + box.height && super.mouseState == 1) {
                            this.setSelectedPoint(super.currentName);
                        }
                        this.I(boxComponent, box, graphics);
                        if (int4 > 0) {
                            if (int5 > 0) {
                                graphics.setColor(this.RectangleReal[int5]);
                            }
                            else {
                                graphics.setColor(Color.black);
                            }
                            GraphicsUtils.setRendering(graphics, false);
                            GraphicsUtils.setLineWidth(graphics, int4);
                            for (int k = 0; k < int4; ++k) {
                                graphics.drawRect(box.x - 3 - k, box.y - 1 - k, box.width + 4 + 2 * k, box.height + 2 + 2 * k);
                            }
                            GraphicsUtils.setRendering(graphics, true);
                        }
                        if (!this.copyInto) {
                            continue;
                        }
                        this.Composa(boxComponent, box.xw(), box.y, graphics, n);
                        continue;
                    }
                    case -15742: {
                        float1 = this.data.readFloat();
                        float2 = this.data.readFloat();
                        continue;
                    }
                    case -15741: {
                        final float float14 = this.data.readFloat();
                        final float float15 = this.data.readFloat();
                        final int[] w2si4 = this.M.w2si(float1, float2);
                        final int[] w2si5 = this.M.w2si(float14, float15);
                        this.ImposaColor(w2si4[0], w2si4[1], w2si5[0], w2si5[1], this.createImage, graphics);
                        float1 = float14;
                        float2 = float15;
                        continue;
                    }
                    case -15740: {
                        final float n20 = float1;
                        final float n21 = float2;
                        final float float16 = this.data.readFloat();
                        final float float17 = this.data.readFloat();
                        final float float18 = this.data.readFloat();
                        final float float19 = this.data.readFloat();
                        final float float20 = this.data.readFloat();
                        final float float21 = this.data.readFloat();
                        for (int l = 1; l <= 4; ++l) {
                            final double n22 = l / 4.0;
                            final double n23 = 1.0 - n22;
                            final double n24 = n23 * n23;
                            final double n25 = n24 * n23;
                            final double n26 = n22 * n22;
                            final double n27 = n26 * n22;
                            final double n28 = n25 * n20 + 3.0 * n24 * n22 * float16 + 3.0 * n23 * n26 * float18 + n27 * float20;
                            final double n29 = n25 * n21 + 3.0 * n24 * n22 * float17 + 3.0 * n23 * n26 * float19 + n27 * float21;
                            final int[] w2si6 = this.M.w2si(float1, float2);
                            final int[] w2si7 = this.M.w2si(n28, n29);
                            this.ImposaColor(w2si6[0], w2si6[1], w2si7[0], w2si7[1], this.createImage, graphics);
                            float1 = (float)n28;
                            float2 = (float)n29;
                        }
                        continue;
                    }
                    case 645: {
                        generalPath = new GeneralPath(1);
                        continue;
                    }
                    case 641: {
                        if (generalPath != null) {
                            if (graphics instanceof WVFGraphics) {
                                ((WVFGraphics)graphics).fill(generalPath, this.add);
                            }
                            else if (graphics instanceof Graphics2D) {
                                if (this.add) {
                                    ((Graphics2D)graphics).fill(generalPath);
                                }
                                else {
                                    ((Graphics2D)graphics).draw(generalPath);
                                }
                            }
                            generalPath = null;
                            continue;
                        }
                        continue;
                    }
                    case -15864: {
                        final String utf2 = this.data.readUTF();
                        final int[] w2si8 = this.M.w2si(this.data.readFloat(), this.data.readFloat());
                        Image externalImage = this.calculator.get(utf2);
                        if (externalImage == null) {
                            externalImage = this.formula.getResourceProvider().getExternalImage(utf2, ((FormulaEditorCalc)this.formula).getWithDocBase());
                            this.calculator.put(utf2, externalImage);
                        }
                        if (externalImage != null) {
                            graphics.drawImage(externalImage, w2si8[0], w2si8[1], this);
                            continue;
                        }
                        continue;
                    }
                    case 16641: {
                        this.add = (this.data.readShort() == 1);
                        continue;
                    }
                    case 16648: {
                        if (this.data.readShort() == 1) {
                            this.calculator((int)this.M.w, (int)this.M.h);
                            this.M = this.ImposaColor;
                            continue;
                        }
                        this.M = m;
                        continue;
                    }
                    case -32503: {
                        int1 = this.data.readInt();
                        continue;
                    }
                    case -32510: {
                        final int int8 = this.data.readInt();
                        if (this.colorToString) {
                            graphics.setColor(this.RectangleReal[int8]);
                            continue;
                        }
                        graphics.setColor(Color.black);
                        continue;
                    }
                    case -32506: {
                        graphics.setFont(this.abs[this.data.readInt()]);
                        continue;
                    }
                    case -32509: {
                        this.createImage = this.data.readFloat();
                        GraphicsUtils.setLineWidth(graphics, this.createImage / n);
                        continue;
                    }
                    case -32508: {
                        float3 = this.data.readFloat();
                        continue;
                    }
                    case -16123: {
                        this.data.readInt();
                        this.data.readInt();
                        continue;
                    }
                    default: {
                        for (int n30 = 0; n30 < capsaComandes; ++n30) {
                            this.data.readByte();
                        }
                        continue;
                    }
                }
            }
            this.data.reset();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            this.firstPlot = false;
            this.M = m;
        }
        final Rectangle clipBounds = graphics.getClipBounds();
        final Rectangle calculator2 = this.calculator((int)this.M.w, (int)this.M.h);
        final Point point2 = new Point(calculator2.x + calculator2.width, calculator2.y + calculator2.height);
        graphics.setColor(new Color(240, 240, 240));
        graphics.fillRect(0, 0, calculator2.x, clipBounds.height);
        graphics.fillRect(point2.x, 0, clipBounds.width - point2.x, clipBounds.height);
        graphics.fillRect(0, 0, clipBounds.width, calculator2.y);
        graphics.fillRect(0, point2.y, clipBounds.width, clipBounds.height - point2.y);
        if (super.mouseLabel != null) {
            final AbstractBox parse2 = boxComponent.parse(super.mouseLabel);
            parse2.ImposaColor(Color.black);
            parse2.ImposaFontPropia(PlotCanvas.addKeyListener);
            parse2.Composa(boxComponent);
            parse2.x = Math.min(this.atan2.x + 4, graphics.getClipBounds().width - (parse2.width + 4));
            parse2.y = Math.max(this.atan2.y - parse2.height - 2, 0);
            graphics.setColor(PlotCanvas.addElement);
            graphics.fillRect(parse2.x - 2, parse2.y - 1, parse2.width + 4, parse2.height + 2);
            this.I(boxComponent, parse2, graphics);
            graphics.setColor(this.cos);
            GraphicsUtils.setRendering(graphics, false);
            GraphicsUtils.setLineWidth(graphics, 1.0f);
            graphics.drawRect(parse2.x - 3, parse2.y - 1, parse2.width + 4, parse2.height + 2);
            GraphicsUtils.setRendering(graphics, true);
        }
        if (cursor.getType() != cursor2.getType()) {
            this.setCursor(cursor);
        }
        this.atan2 = new Point(-100, -100);
    }
    
    private final void CURSOR_MOVE_POINTS() {
        this.clearCurrent();
        this.contains = false;
        this.copyInto = false;
    }
    
    private final void CURSOR_MOVING_POINT(final Graphics graphics) {
        this.cos = graphics.getColor();
        super.setMouseOverLabel();
    }
    
    private final double CURSOR_WAIT(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        double n9 = -Math.atan2((n2 - n4) * n5, (n - n3) * n6);
        if (n9 < 0.0) {
            n9 += 6.283185307179586;
        }
        if (n9 > n7 && n9 < n7 + n8) {
            return this.CURSOR_ZOOM(n, n2, n3, n4, n5, n6, 0);
        }
        final double n10 = n3 + n5 * Math.cos(n7) - n;
        final double n11 = n4 - n6 * Math.sin(n7) - n2;
        final double n12 = n3 + n5 * Math.cos(n7 + n8) - n;
        final double n13 = n4 - n6 * Math.sin(n7 + n8) - n2;
        return Math.min(n10 * n10 + n11 * n11, n12 * n12 + n13 * n13);
    }
    
    private final double CURSOR_ZOOM(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        final double n8 = n - n3;
        final double n9 = (n2 - n4) * n5 / n6;
        final double sqrt = Math.sqrt(n8 * n8 + n9 * n9);
        final double n10 = n8 - n8 / sqrt * n5;
        final double n11 = (n9 - n9 / sqrt * n5) * n6 / n5;
        return n10 * n10 + n11 * n11;
    }
    
    private final double CURSOR_ZOOM_RECTANGULAR(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n5 - n3;
        final double n8 = n6 - n4;
        final double n9 = n - n3;
        final double n10 = n2 - n4;
        final double n11 = n - n5;
        final double n12 = n2 - n6;
        if (n7 * n9 + n8 * n10 > 0.0 && n7 * n11 + n8 * n12 < 0.0) {
            final double n13 = (n7 * n9 + n8 * n10) / (n7 * n7 + n8 * n8);
            final double n14 = n7 * n13;
            final double n15 = n8 * n13;
            return (n9 - n14) * (n9 - n14) + (n10 - n15) * (n10 - n15);
        }
        return Math.min(n9 * n9 + n10 * n10, n11 * n11 + n12 * n12);
    }
    
    private final AbstractBox Composa(final BoxComponent boxComponent, final int n, final int n2, final Graphics graphics, final float n3) {
        final AbstractBox parse = boxComponent.parse(this.getDisplayLabel());
        parse.ImposaColor(Color.black);
        parse.ImposaFontPropia(PlotCanvas.addKeyListener);
        parse.Composa(boxComponent);
        parse.x = n + (int)(4.0f / n3);
        parse.y = n2 - parse.height - (int)(2.0f / n3);
        this.I(boxComponent, parse, graphics);
        return parse;
    }
    
    private final void I(final BoxComponent boxComponent, final AbstractBox abstractBox, final Graphics graphics) {
        GraphicsUtils.setRendering(graphics, false);
        GraphicsUtils.setLineWidth(graphics, 1.0f);
        abstractBox.DrawRectCorrecte(boxComponent, graphics);
        GraphicsUtils.setLineWidth(graphics, this.createImage);
        GraphicsUtils.setRendering(graphics, true);
    }
    
    private final void ImposaColor(final float n, final float n2, final float n3, final float n4, final float n5, final Graphics graphics) {
        if (Math.abs(n) > 50 * this.Afegir.width || Math.abs(n2) > 50 * this.Afegir.height || Math.abs(n3) > 50 * this.Afegir.width || Math.abs(n4) > 50 * this.Afegir.height) {
            this.dibuixaPoligon = true;
        }
        if (!PlotCanvas.createGraphics) {
            graphics.drawLine(Math.round(n), Math.round(n2), Math.round(n3), Math.round(n4));
        }
        else {
            IsGraphics2D.drawLine(graphics, n, n2, n3, n4);
        }
    }
    
    private final void ImposaFontPropia(final float[] array, final float[] array2, final int n, final float n2, final Graphics graphics, final boolean b) {
        final float[] array3 = new float[n + 1];
        final float[] array4 = new float[n + 1];
        for (int i = 0; i < n; ++i) {
            array3[i] = array[i];
            array4[i] = array2[i];
        }
        array3[n] = array[0];
        array4[n] = array2[0];
        this.ObteOmegaStrings2(array3, array4, n + 1, n2, graphics, b);
    }
    
    private final void ObteOmegaStrings2(final float[] array, final float[] array2, final int n, final float n2, final Graphics graphics, final boolean b) {
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        for (int i = 0; i < n; ++i) {
            array3[i] = Math.round(array[i]);
            array4[i] = Math.round(array2[i]);
        }
        if (b) {
            graphics.fillPolygon(array3, array4, n);
        }
        graphics.drawPolyline(array3, array4, n);
    }
    
    private final void RectangleReal(final float n, final float n2, final float n3, final float n4, final float n5, final Graphics graphics) {
        if (this.add) {
            graphics.fillOval(Math.round(n), Math.round(n2), Math.round(n3) + 1, Math.round(n4) + 1);
        }
        graphics.drawOval(Math.round(n), Math.round(n2), Math.round(n3), Math.round(n4));
    }
    
    private final void abs(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final Graphics graphics) {
        if (this.add) {
            graphics.fillArc(Math.round(n), Math.round(n2), Math.round(n3) + 1, Math.round(n4) + 1, (int)Math.round(180.0f * n5 / 3.141592653589793), (int)Math.round(180.0f * n6 / 3.141592653589793));
        }
        graphics.drawArc(Math.round(n), Math.round(n2), Math.round(n3), Math.round(n4), (int)Math.round(180.0f * n5 / 3.141592653589793), (int)Math.round(180.0f * n6 / 3.141592653589793));
    }
    
    private final float[] actionPerformed() {
        final float[] array = new float[6];
        for (int i = 0; i < 6; ++i) {
            array[i] = this.data.readFloat();
        }
        return array;
    }
    
    private final float[] add() {
        final Rectangle calculator = this.calculator((int)this.M.w, (int)this.M.h);
        final Afinitat2x2 afinitat2x2 = new Afinitat2x2();
        afinitat2x2.setBounding(this.ImposaColor.x, this.ImposaColor.y, this.ImposaColor.I, this.ImposaColor.Z, calculator.width, calculator.height);
        return this.calculaBoundingBoxMarc(afinitat2x2);
    }
    
    private float[] calculaBoundingBoxMarc(final Afinitat2x2 afinitat2x2) {
        final double[] s2w = afinitat2x2.s2w(0.0, 0.0);
        final Dimension dimension = new Dimension((int)afinitat2x2.w, (int)afinitat2x2.h);
        final double[] s2w2 = afinitat2x2.s2w(afinitat2x2.w, afinitat2x2.h);
        final int dpi = this.formula.getDPI();
        dimension.width = (dimension.width * 72 + dpi / 2) / dpi;
        dimension.height = (dimension.height * 72 + dpi / 2) / dpi;
        return new float[] { (float)(s2w[0] + s2w2[0]) / 2.0f, (float)((s2w[1] + s2w2[1]) / 2.0), (float)(s2w2[0] - s2w[0]), (float)(s2w2[1] - s2w[1]), dimension.width, dimension.height };
    }
    
    public final void setData(final InputStream inputStream, final AbstractBox capsaComandes, final boolean b) {
        super.setData(inputStream, capsaComandes, b);
        if (b) {
            this.resetChanges();
            this.isFirstUpdate = true;
        }
        this.capsaComandes = capsaComandes;
        this.data = new DataInputStream(inputStream);
        this.addActionListener();
        if (b) {
            this.ImposaFontPropia = this.M.w;
            this.ObteOmegaStrings2 = this.M.h;
        }
        this.calculaBoundingBoxMarc = new Vector();
        this.calculaSalt = new Vector();
    }
    
    private void resetChanges() {
        this.clearAllPoints();
        this.infoChanged = false;
        this.showGridChanged = false;
        this.showAxisChanged = false;
        this.boundingChanged = false;
    }
    
    private final void addActionListener() {
        try {
            this.data.reset();
            this.data.readByte();
            this.data.readByte();
            this.data.readByte();
            this.data.readUTF();
            this.setName(this.data.readUTF());
            this.actionPerformed = this.actionPerformed();
            this.calculator = new Hashtable();
            this.firstPlot = true;
            this.clearCurrent = null;
            this.clearMouseLabel = null;
            this.formula.getDPI();
            this.M.setBoundingBox(this.actionPerformed[0], this.actionPerformed[1], this.actionPerformed[2], this.actionPerformed[3], this.actionPerformed[4], this.actionPerformed[5]);
            this.ImposaColor = (Afinitat2x2)this.M.clone();
            this.Z = true;
            while (this.data.available() > 0) {
                final short short1 = this.data.readShort();
                final int capsaComandes = capsaComandes(short1, this.data);
                switch (short1) {
                    case -16383: {
                        final int int1 = this.data.readInt();
                        this.RectangleReal = new Color[int1];
                        for (int i = 0; i < int1; ++i) {
                            this.RectangleReal[i] = new Color(this.data.readUnsignedByte(), this.data.readUnsignedByte(), this.data.readUnsignedByte());
                        }
                        continue;
                    }
                    case -16382: {
                        final int int2 = this.data.readInt();
                        this.abs = new Font[int2];
                        for (int j = 0; j < int2; ++j) {
                            final String utf = this.data.readUTF();
                            final int int3 = this.data.readInt();
                            final short short2 = this.data.readShort();
                            int n = 0;
                            switch (short2) {
                                case 1281: {
                                    n = 0;
                                    break;
                                }
                                case 1282: {
                                    n = 1;
                                    break;
                                }
                                case 1283: {
                                    n = 2;
                                    break;
                                }
                                case 1284: {
                                    n = 2;
                                    break;
                                }
                            }
                            this.abs[j] = new Font(utf, n, int3);
                        }
                        continue;
                    }
                    case -32763: {
                        this.colorDissimulat = this.RectangleReal[this.data.readInt()];
                        continue;
                    }
                    case -16381: {
                        this.C = (this.data.readShort() == 1);
                        this.clearRect = this.abs[this.data.readInt()];
                        this.clone = this.RectangleReal[this.data.readInt()];
                        continue;
                    }
                    case -16380: {
                        this.B = (this.data.readShort() == 1);
                        this.colorMig = this.RectangleReal[this.data.readInt()];
                        continue;
                    }
                    case 16391: {
                        this.clearAllPoints = this.data.readShort();
                        continue;
                    }
                    case -16375: {
                        this.clearCurrent = this.data.readUTF();
                        continue;
                    }
                    case -16374: {
                        this.clearMouseLabel = this.data.readUTF();
                        continue;
                    }
                    case 16390: {
                        super.mouseLabelMode = this.data.readShort();
                        continue;
                    }
                    case 16392: {
                        this.Z = (this.data.readShort() == 1);
                        continue;
                    }
                    default: {
                        for (int k = 0; k < capsaComandes; ++k) {
                            this.data.readByte();
                        }
                        continue;
                    }
                }
            }
            this.data.reset();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public final Dimension getPreferredSize() {
        final int dpi = this.formula.getDPI();
        return new Dimension(Math.round(this.actionPerformed[4] * dpi / 72.0f), Math.round(this.actionPerformed[5] * dpi / 72.0f));
    }
    
    private final void addElement(final BoxComponent boxComponent, final Graphics graphics, final float n) {
        if (this.colorToString) {
            graphics.setColor(this.clone);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.setFont(this.clearRect);
        final Afinitat2x2 trueM = this.trueM(graphics);
        final Dimension dimension = new Dimension((int)trueM.w, (int)trueM.h);
        final double[] s2w = trueM.s2w(0.0, 0.0);
        final double[] s2w2 = trueM.s2w(dimension.width - 1, dimension.height - 1);
        final int[] w2si = trueM.w2si(0.0, 0.0);
        final double[] s2wd = trueM.s2wd(1000.0, -1000.0);
        final double n2 = s2wd[0];
        final double n3 = s2wd[1];
        final double addMouseListener = addMouseListener(n2 * n);
        final double addMouseListener2 = addMouseListener(n3 * n);
        GraphicsUtils.setRendering(graphics, false);
        final double n4 = Math.floor(s2w[0] / addMouseListener) * addMouseListener;
        final double n5 = Math.floor(s2w2[0] / addMouseListener + 1.0) * addMouseListener;
        graphics.drawLine(0, w2si[1], dimension.width, w2si[1]);
        for (double n6 = n4; n6 < n5; n6 += addMouseListener) {
            final int[] w2si2 = trueM.w2si(n6, 0.0);
            graphics.drawLine(w2si2[0], w2si2[1] - (int)(2.0f * n), w2si2[0], w2si2[1] + (int)(2.0f * n));
            final String double2String = double2String(n6, addMouseListener, false);
            graphics.drawString(double2String, w2si2[0] - graphics.getFontMetrics(this.clearRect).stringWidth(double2String) / 2, w2si2[1] - (int)(4.0f * n));
        }
        final double n7 = Math.floor(s2w[1] / addMouseListener2 + 1.0) * addMouseListener2;
        final double n8 = Math.floor(s2w2[1] / addMouseListener2) * addMouseListener2;
        graphics.drawLine(w2si[0], 0, w2si[0], dimension.height);
        for (double n9 = n8; n9 < n7; n9 += addMouseListener2) {
            final int[] w2si3 = trueM.w2si(0.0, n9);
            graphics.drawLine(w2si3[0] - (int)(2.0f * n), w2si3[1], w2si3[0] + (int)(2.0f * n), w2si3[1]);
            graphics.drawString(double2String(n9, addMouseListener2, false), w2si3[0] + (int)(8.0f * n), w2si3[1]);
        }
        if (this.clearAllPoints > 0) {
            final Font font = new Font(this.clearRect.getFamily(), this.clearRect.getStyle(), (int)(this.clearRect.getSize() * 1.4));
            String s = null;
            String s2 = null;
            graphics.setFont(font);
            if (this.clearAllPoints == 1) {
                s = "x";
                s2 = "y";
            }
            else if (this.clearAllPoints == 2) {
                s = "X";
                s2 = "Y";
            }
            graphics.translate(w2si[0], 10);
            graphics.fillPolygon(PlotCanvas.addMouseListener, PlotCanvas.addMouseMotionListener, 3);
            graphics.drawPolygon(PlotCanvas.addMouseListener, PlotCanvas.addMouseMotionListener, 3);
            if (s2 != null) {
                graphics.drawString(s2, -14, 3);
            }
            graphics.translate(-w2si[0], -10);
            graphics.translate(dimension.width - 10, w2si[1]);
            graphics.fillPolygon(PlotCanvas.afegirCapsaComandes, PlotCanvas.append, 3);
            graphics.drawPolygon(PlotCanvas.afegirCapsaComandes, PlotCanvas.append, 3);
            if (s != null) {
                graphics.drawString(s, -3, 16);
            }
            graphics.translate(-dimension.width + 10, -w2si[1]);
        }
        if (this.clearCurrent != null) {
            final AbstractBox parse = boxComponent.parse(this.clearCurrent);
            parse.ImposaColor(graphics.getColor());
            parse.ImposaFontPropia(graphics.getFont());
            parse.Composa(boxComponent);
            parse.x = dimension.width - parse.width - 2;
            parse.y = w2si[1] + 4;
            this.I(boxComponent, parse, graphics);
        }
        if (this.clearMouseLabel != null) {
            final AbstractBox parse2 = boxComponent.parse(this.clearMouseLabel);
            parse2.ImposaColor(graphics.getColor());
            parse2.ImposaFontPropia(graphics.getFont());
            parse2.Composa(boxComponent);
            parse2.x = w2si[0] - parse2.width - 2;
            parse2.y = 0;
            this.I(boxComponent, parse2, graphics);
        }
        GraphicsUtils.setRendering(graphics, true);
    }
    
    private final void addKeyListener(final Graphics graphics, final float n) {
        final Afinitat2x2 trueM = this.trueM(graphics);
        if (this.colorToString) {
            graphics.setColor(this.colorMig);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        final Dimension dimension = new Dimension((int)trueM.w, (int)trueM.h);
        final double[] s2w = trueM.s2w(0.0, 0.0);
        final double[] s2w2 = trueM.s2w(PlotCanvas.dibuixaPoligonal.width - 1, PlotCanvas.dibuixaPoligonal.height - 1);
        final double[] s2w3 = trueM.s2w(dimension.width - 1, dimension.height - 1);
        final double abs = Math.abs(s2w2[0] - s2w[0]);
        final double abs2 = Math.abs(s2w2[1] - s2w[1]);
        this.dispose = addMouseMotionListener(abs * n);
        this.double2String = addMouseMotionListener(abs2 * n);
        GraphicsUtils.setRendering(graphics, false);
        final double n2 = Math.floor(s2w[0] / this.dispose) * this.dispose;
        for (double n3 = Math.floor(s2w3[0] / this.dispose + 1.0) * this.dispose, n4 = n2; n4 < n3; n4 += this.dispose) {
            final int[] w2si = trueM.w2si(n4, 0.0);
            graphics.drawLine(w2si[0], 0, w2si[0], dimension.height);
        }
        for (double n5 = Math.floor(s2w[1] / this.double2String + 1.0) * this.double2String, n6 = Math.floor(s2w3[1] / this.double2String) * this.double2String; n6 < n5; n6 += this.double2String) {
            final int[] w2si2 = trueM.w2si(0.0, n6);
            graphics.drawLine(0, w2si2[1], dimension.width, w2si2[1]);
        }
        GraphicsUtils.setRendering(graphics, true);
    }
    
    public static final String double2String(double n, final double n2, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final long round = Math.round(-Math.log(Math.abs(n2)) / Math.log(10.0) + 2.0);
        final double pow = Math.pow(10.0, round);
        final long round2 = Math.round(n);
        n = Math.round(pow * n) / pow;
        if (Math.abs((round2 - n) / n2) < 0.001) {
            if (round2 != 0L || b) {
                if (round > -5L) {
                    sb.append(round2);
                }
                else {
                    sb.append((float)round2);
                }
            }
        }
        else {
            sb.append(n);
        }
        return sb.toString();
    }
    
    static final String I(final double n, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final int n2 = (n == 0.0) ? 0 : ((int)Math.floor((float)(Math.log(Math.abs(n)) / Math.log(10.0))));
        if (n2 >= 0 && n2 < 10) {
            final long round = Math.round(n);
            if (Math.abs(round - n) < 1.0E-6) {
                if (round != 0L || b) {
                    sb.append(round);
                    return sb.toString();
                }
                return "";
            }
        }
        if (Math.abs(n) < 10000.0 && Math.abs(n) > 0.001) {
            sb.append((float)n);
        }
        else {
            sb.append(n / Math.pow(10.0, n2));
            sb.append("*10^");
            sb.append(n2);
        }
        return sb.toString();
    }
    
    static final String Z(final double n, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (n > 0.0) {
            sb.append(" ");
        }
        final long round = Math.round(n);
        if (Math.abs(round - n) < 1.0E-6) {
            if (round != 0L || b) {
                sb.append("<mn>" + round + "</mn>");
            }
        }
        else if (Math.abs(n) < 10000.0 && Math.abs(n) > 0.001) {
            sb.append("<mn>" + (float)n + "</mn>");
        }
        else {
            final int n2 = (int)Math.floor((float)(Math.log(Math.abs(n)) / Math.log(10.0)));
            sb.append("<mn>" + n / Math.pow(10.0, n2) + "</mn>");
            sb.append("<mo>*</mo><msup><mn>10</mn>");
            sb.append("<mn>" + n2 + "</mn>");
            sb.append("</msup>");
        }
        return sb.toString();
    }
    
    private static final double addMouseListener(final double n) {
        final double[] array = new double[2];
        calculaSalt(n, array);
        return array[0];
    }
    
    private static final double addMouseMotionListener(final double n) {
        final double[] array = new double[2];
        calculaSalt(n, array);
        return array[1];
    }
    
    private static void calculaSalt(final double n, final double[] array) {
        final double n2 = n / 1000.0 * 36.0 * 1.4000000059604645;
        final double pow = Math.pow(10.0, (int)Math.floor(Math.log(n2) / Math.log(10.0)));
        int n3 = (int)Math.round(n2 / pow);
        if (n3 == 4) {
            n3 = 5;
        }
        if (n3 == 6) {
            n3 = 5;
        }
        if (n3 == 7) {
            n3 = 5;
        }
        if (n3 == 8) {
            n3 = 10;
        }
        if (n3 == 9) {
            n3 = 10;
        }
        array[0] = n3 * pow;
        if (n3 == 3) {
            array[1] = n3 * pow / 3.0;
        }
        else if (n3 * pow / n2 > 1.2) {
            if (n3 == 5) {
                array[1] = n3 * pow / 5.0;
            }
            else {
                array[1] = n3 * pow / 4.0;
            }
        }
        else {
            array[1] = n3 * pow / 2.0;
        }
    }
    
    private final void afegirCapsaComandes() {
        if (this.draw == null) {
            this.draw = new PopupMenu();
            final MenuItem menuItem = new MenuItem("Axis");
            menuItem.setActionCommand("axis");
            menuItem.addActionListener(this);
            this.draw.add(menuItem);
            final MenuItem menuItem2 = new MenuItem("Grid");
            menuItem2.setActionCommand("grid");
            menuItem2.addActionListener(this);
            this.draw.add(menuItem2);
            final Menu menu = new Menu("Zoom");
            final MenuItem menuItem3 = new MenuItem("Restore");
            menuItem3.setActionCommand("zoomreset");
            menuItem3.addActionListener(this);
            menu.add(menuItem3);
            final MenuItem menuItem4 = new MenuItem("Zoom in");
            menuItem4.setActionCommand("zoomin");
            menuItem4.addActionListener(this);
            menu.add(menuItem4);
            final MenuItem menuItem5 = new MenuItem("Zoom out");
            menuItem5.setActionCommand("zoomout");
            menuItem5.addActionListener(this);
            menu.add(menuItem5);
            this.draw.add(menu);
            this.add(this.draw);
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.drawImage = mouseEvent.getPoint();
        this.atan2 = mouseEvent.getPoint();
        this.drawLine = mouseEvent.getPoint();
        super.mouseState = 1;
        if (mouseEvent.isPopupTrigger()) {
            this.drawArc = true;
        }
        this.repaintNow();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.drawImage = null;
        super.mouseState = 0;
        if (this.drawArc || mouseEvent.isPopupTrigger()) {
            this.afegirCapsaComandes();
            this.draw.show(this, mouseEvent.getX(), mouseEvent.getY());
            this.drawArc = true;
        }
        switch (this.aspectRatio1) {
            case 3: {
                this.unselectPoint();
                if (this.compileImage) {
                    this.actionPerformed(new ActionEvent(this, 1999, "refreshnochangeview"));
                    this.compileImage = false;
                    break;
                }
                break;
            }
        }
        this.repaintNow();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (this.drawArc) {
            this.drawArc = false;
            return;
        }
        switch (this.aspectRatio1) {
            case 1: {
                final Point point = mouseEvent.getPoint();
                this.M.zoom(0.6666666865348816, point.x, point.y);
                this.repaintNow();
                break;
            }
            case 2: {
                final Point point2 = mouseEvent.getPoint();
                this.M.zoom(1.5, point2.x, point2.y);
                this.repaintNow();
                break;
            }
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.atan2 = mouseEvent.getPoint();
        switch (super.mouseLabelMode) {
            default: {
                this.repaintNow();
            }
        }
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        boolean b = false;
        final Point point = mouseEvent.getPoint();
        this.atan2 = mouseEvent.getPoint();
        if (this.drawImage == null) {
            this.drawImage = point;
        }
        super.mouseState = 2;
        switch (this.aspectRatio1) {
            case 1: {
                this.M.translateE(-point.x + this.drawImage.x, -point.y + this.drawImage.y);
                this.boundingChanged = true;
                break;
            }
            case 2: {
                this.M.translateE(-point.x + this.drawImage.x, -point.y + this.drawImage.y);
                this.boundingChanged = true;
                break;
            }
            case 3: {
                if (this.thereIsSelectedPoint()) {
                    this.setSelectedPoint(this.getSelectedPoint(), point.x, point.y);
                    b = true;
                    this.compileImage = true;
                    break;
                }
                break;
            }
        }
        this.drawImage = point;
        this.redibuixa2(b);
    }
    
    public final boolean recalcIfNecessary(final boolean b) {
        return false;
    }
    
    public final void redibuixa2(final boolean b) {
        if (!this.recalcIfNecessary(b)) {
            this.repaintNow();
        }
    }
    
    public final void redibuixa2() {
        this.redibuixa2(false);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("axis")) {
            this.C = !this.C;
            this.showAxisChanged = true;
            this.repaintNow();
        }
        if (actionCommand.equals("grid")) {
            this.B = !this.B;
            this.showGridChanged = true;
            this.repaintNow();
        }
        if (actionCommand.equals("blackwhite")) {
            this.colorToString = !this.colorToString;
            this.repaintNow();
        }
        else if (actionCommand.equals("zoomreset")) {
            this.M.setBoundingBox(this.actionPerformed[0], this.actionPerformed[1], this.actionPerformed[2], this.actionPerformed[3], this.Afegir.width, this.Afegir.height);
            this.boundingChanged = true;
            this.redibuixa2();
        }
        else if (actionCommand.equals("zoomin")) {
            if (!this.dibuixaPoligon) {
                this.M.zoom(0.6666666666666666);
                this.ImposaColor.zoom(0.6666666666666666);
                this.boundingChanged = true;
                this.redibuixa2();
            }
        }
        else if (actionCommand.equals("zoomout")) {
            this.dibuixaPoligon = false;
            this.M.zoom(1.5);
            this.ImposaColor.zoom(1.5);
            this.boundingChanged = true;
            this.redibuixa2();
        }
        else if (actionCommand.equals("aspectratio1")) {
            this.M.aspectRatio1();
            this.repaintNow();
        }
        else if (actionCommand.equals("actionzoom")) {
            this.calculaBoundingBoxMarc(1);
        }
        else if (actionCommand.equals("accioZoomRectangular")) {
            this.calculaBoundingBoxMarc(2);
        }
        else if (actionCommand.equals("actionshowname")) {
            this.boundingChanged(1);
        }
        else if (actionCommand.equals("switchmove")) {
            if (this.aspectRatio1 == 3) {
                this.calculaBoundingBoxMarc(1);
            }
            else if (this.aspectRatio1 == 1) {
                this.calculaBoundingBoxMarc(3);
            }
        }
        else if (actionCommand.equals("actionshowvalue")) {
            this.boundingChanged(2);
        }
        else if (actionCommand.equals("actionshowdef")) {
            this.boundingChanged(3);
        }
        else if (actionCommand.equals("actionmove")) {
            this.calculaBoundingBoxMarc(3);
        }
        else if (actionCommand.equals("refreshnochangeview")) {
            this.refresh();
        }
        else if (actionCommand.equals("refresh")) {
            this.expandView();
            this.refresh();
        }
        else if (actionCommand.equals("resetplotcode")) {
            final String[] movingPointsScript = this.getMovingPointsScript(PlotterCanvas.script_format, true);
            for (int i = 0; i < movingPointsScript.length; ++i) {
                this.afegirCapsaComandes(movingPointsScript[i]);
            }
            ((FormulaEditorCalc)this.formula).getPlotterManager().getPlotters(this.capsaComandes);
            if (this.boundingChanged) {
                this.afegirCapsaComandes(this.aspectRatio1(PlotterCanvas.script_format));
            }
            if (this.infoChanged) {
                this.afegirCapsaComandes(this.atan2(PlotterCanvas.script_format));
            }
            if (this.showAxisChanged) {
                this.afegirCapsaComandes(this.available(PlotterCanvas.script_format));
            }
            if (this.showGridChanged) {
                this.afegirCapsaComandes(this.baseline(PlotterCanvas.script_format));
            }
            this.formula.repaint();
            this.resetChanges();
        }
        else if (actionCommand.equals("recompute")) {
            this.resetChanges();
            this.refresh();
        }
        else if (actionCommand.equals("logoicon")) {
            this.I.showDocumentNewWindow("http://www.wiris.com");
        }
    }
    
    private void refresh() {
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector();
        this.drawLine = new Point(this.atan2);
        if (this.capsaComandes instanceof CapsaComandes) {
            ((CapsaComandes)this.capsaComandes).ObteOmegaStrings2(vector, vector2, false);
        }
        final String[] movingPointsScript = this.getMovingPointsScript(PlotterCanvas.omega_format, true);
        for (int i = 0; i < movingPointsScript.length; ++i) {
            vector.addElement(movingPointsScript[i]);
        }
        if (this.boundingChanged) {
            vector.addElement(this.aspectRatio1(PlotterCanvas.omega_format));
        }
        if (this.infoChanged) {
            vector.addElement(this.atan2(PlotterCanvas.omega_format));
        }
        if (this.showAxisChanged) {
            vector.addElement(this.available(PlotterCanvas.omega_format));
        }
        if (this.showGridChanged) {
            vector.addElement(this.baseline(PlotterCanvas.omega_format));
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        final Cursor cursor = this.getCursor();
        this.setCursor(PlotCanvas.CURSOR_WAIT);
        try {
            final FormulaEditorCalc formulaEditorCalc = (FormulaEditorCalc)this.formula;
            if (!formulaEditorCalc.calculator.isCalculating()) {
                this.append(FormulaEditorCalc.evaluate(formulaEditorCalc, this.baseline, array), vector2);
            }
            else {
                System.out.println("Debug3: ignoring replot");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setCursor(cursor);
    }
    
    private final void append(final String[] array, final Vector vector) {
        try {
            final Vector graphics = this.baseline.getGraphics();
            for (int size = graphics.size(), i = 0; i < size; ++i) {
                final ByteArrayInputStream byteArrayInputStream = graphics.elementAt(i);
                final String plotterName = PlotterCanvas.getPlotterName(byteArrayInputStream);
                if (this.capsaComandes instanceof CapsaComandes) {
                    ((CapsaComandes)this.capsaComandes).updatePlotters(plotterName, byteArrayInputStream);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void afegirCapsaComandes(String string) {
        if (this.capsaComandes instanceof CapsaComandes) {
            string = "<command><input><math>" + string + "</math></input></command>";
            this.capsaComandes.fill[0].Afegir(this.formula.parse(string), this.formula);
        }
    }
    
    public final String[] getMovingPointsScript(final String[] array, final boolean b) {
        Enumeration enumeration;
        if (b) {
            enumeration = this.getMovedPoints();
        }
        else {
            enumeration = this.getMovablePoints();
        }
        final Vector vector = new Vector<String>();
        while (enumeration.hasMoreElements()) {
            vector.addElement(this.scriptFigure(array, enumeration.nextElement(), b));
        }
        final String[] array2 = new String[vector.size()];
        vector.copyInto(array2);
        return array2;
    }
    
    private final String aspectRatio1(final String[] array) {
        final String s = array[0];
        final String s2 = array[1];
        final String s3 = array[2];
        final String s4 = array[3];
        final String s5 = array[6];
        final String s6 = array[7];
        final float[] add = this.add();
        return this.black(array, "attributes") + s + this.black(array, this.getName()) + s5 + s3 + this.black(array, "center") + s6 + this.black(array, "point") + s + formatDouble(array, add[0], true) + s5 + formatDouble(array, add[1], true) + s2 + s5 + this.black(array, "width") + s6 + formatDouble(array, add[2], true) + s5 + this.black(array, "height") + s6 + formatDouble(array, -add[3], true) + s5 + this.black(array, "window_width") + s6 + formatDouble(array, add[4], true) + s5 + this.black(array, "window_height") + s6 + formatDouble(array, add[5], true) + s4 + s2;
    }
    
    private final String atan2(final String[] array) {
        final String s = array[0];
        final String s2 = array[1];
        final String s3 = array[2];
        final String s4 = array[3];
        final String s5 = array[6];
        final String s6 = array[7];
        final String s7 = array[10];
        final String s8 = array[11];
        String s9 = null;
        switch (super.mouseLabelMode) {
            case 1: {
                s9 = "name";
                break;
            }
            case 2: {
                s9 = "value";
                break;
            }
            case 3: {
                s9 = "definition";
                break;
            }
            default: {
                s9 = "none";
                break;
            }
        }
        return this.black(array, "attributes") + s + this.black(array, this.getName()) + s5 + s3 + this.black(array, "information") + s6 + s7 + this.black(array, s9) + s8 + s4 + s2;
    }
    
    private final String available(final String[] array) {
        return this.black(array, "attributes") + array[0] + this.black(array, this.getName()) + array[6] + array[2] + this.black(array, "show_axis") + array[7] + this.black(array, this.C ? "true" : "false") + array[3] + array[1];
    }
    
    private final String baseline(final String[] array) {
        return this.black(array, "attributes") + array[0] + this.black(array, this.getName()) + array[6] + array[2] + this.black(array, "show_grid") + array[7] + this.black(array, this.B ? "true" : "false") + array[3] + array[1];
    }
    
    private final String black(final String[] array, final String s) {
        return array[8] + s + array[9];
    }
    
    public static final String formatDouble(final String[] array, final double n, final boolean b) {
        String s;
        if (array == PlotterCanvas.script_format) {
            s = Z(n, b);
        }
        else {
            s = I(n, b);
        }
        return s;
    }
    
    private final void boundingChanged(final int mouseLabelMode) {
        if (super.mouseLabelMode == mouseLabelMode) {
            super.mouseLabelMode = 0;
        }
        else {
            super.mouseLabelMode = mouseLabelMode;
        }
        this.infoChanged = true;
    }
    
    private final void calculaBoundingBoxMarc(final int aspectRatio1) {
        if (this.aspectRatio1 == aspectRatio1) {
            this.aspectRatio1 = 0;
        }
        else {
            this.aspectRatio1 = aspectRatio1;
        }
        switch (this.aspectRatio1) {
            case 1: {
                this.setCursor(PlotCanvas.CURSOR_ZOOM);
                break;
            }
            case 2: {
                this.setCursor(PlotCanvas.CURSOR_ZOOM_RECTANGULAR);
                break;
            }
            case 3: {
                this.setCursor(PlotCanvas.CURSOR_MOVE_POINTS);
                break;
            }
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 107: {
                this.M.zoom(0.6666666666666666);
                this.repaintNow();
                break;
            }
            case 109: {
                this.M.zoom(1.5);
                this.repaintNow();
                break;
            }
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public final void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        this.setScreenLocation(size.width, size.height);
        this.setScreenLocation(size.width, size.height);
        if (this.DrawRectCorrecte == null || size.width != this.Afegir.width || size.height != this.Afegir.height) {
            if (this.DrawRectCorrecte != null) {
                this.DrawRectCorrecte.dispose();
                this.Composa.flush();
            }
            this.Afegir = size;
            this.Composa = this.createImage(size.width, size.height);
            if (this.Composa == null) {
                this.DrawRectCorrecte = graphics;
            }
            else {
                (this.DrawRectCorrecte = this.Composa.getGraphics()).setClip(0, 0, this.Afegir.width, this.Afegir.height);
            }
            if (this.recalcIfNecessary(false)) {
                return;
            }
        }
        else if (this.Composa == null) {
            this.DrawRectCorrecte = graphics;
        }
        this.DrawRectCorrecte.clearRect(0, 0, this.Afegir.width, this.Afegir.height);
        this.DrawRectCorrecte.setColor(this.colorDissimulat);
        this.DrawRectCorrecte.fillRect(0, 0, this.Afegir.width, this.Afegir.height);
        graphics.getClipBounds();
        final Rectangle calculaSalt = this.calculaSalt(graphics);
        final Point point = new Point(calculaSalt.x + calculaSalt.width, calculaSalt.y + calculaSalt.height);
        try {
            this.Afegir(this.formula, this.DrawRectCorrecte, 1.0f);
        }
        finally {}
        final Point plotterPosition = this.getPlotterPosition();
        if (this.Composa != null) {
            graphics.drawImage(this.Composa, plotterPosition.x, plotterPosition.y, this);
        }
    }
    
    public final Point getPlotterPosition() {
        return new Point(0, 0);
    }
    
    private final Rectangle calculaSalt(final Graphics graphics) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final Rectangle rectangle = new Rectangle();
        final double min = Math.min(clipBounds.width / this.ImposaColor.w, clipBounds.height / this.ImposaColor.h);
        rectangle.width = (int)(min * this.ImposaColor.w);
        rectangle.height = (int)(min * this.ImposaColor.h);
        rectangle.x = (clipBounds.width - rectangle.width) / 2;
        rectangle.y = (clipBounds.height - rectangle.height) / 2;
        return rectangle;
    }
    
    private final Rectangle calculator(final int n, final int n2) {
        final Rectangle rectangle = new Rectangle();
        final double min = Math.min(n / this.ImposaColor.w, n2 / this.ImposaColor.h);
        rectangle.width = (int)(min * this.ImposaColor.w);
        rectangle.height = (int)(min * this.ImposaColor.h);
        rectangle.x = (n - rectangle.width) / 2;
        rectangle.y = (n2 - rectangle.height) / 2;
        return rectangle;
    }
    
    public final void expandView() {
        this.boundingChanged = true;
        this.getSize();
        this.ImposaColor = this.M;
    }
    
    public final void setScreenLocation(final double n, final double n2) {
        this.boundingChanged |= (this.ImposaFontPropia != n || this.ObteOmegaStrings2 != n2);
        this.M = this.fitToWindow(n, n2, true);
    }
    
    public final Afinitat2x2 fitToWindow(final double n, final double n2, final boolean b) {
        double i = this.M.I;
        double z = this.M.Z;
        double n3 = n;
        double n4 = n2;
        if (b) {
            final Rectangle calculator = this.calculator((int)n, (int)n2);
            i = this.ImposaColor.I * n / calculator.width;
            z = this.ImposaColor.Z * n2 / calculator.height;
        }
        else {
            final double min = Math.min(n / this.ImposaColor.w, n2 / this.ImposaColor.h);
            n3 = this.ImposaColor.w * min;
            n4 = this.ImposaColor.h * min;
        }
        final double n5 = this.M.x + this.M.I / 2.0;
        final double n6 = this.M.y - this.M.Z / 2.0;
        final double n7 = n5 - i / 2.0;
        final double n8 = n6 + z / 2.0;
        final Afinitat2x2 afinitat2x2 = new Afinitat2x2();
        afinitat2x2.setBounding(n7, n8, i, z, n3, n4);
        return afinitat2x2;
    }
    
    public final Afinitat2x2 trueM(final Graphics graphics) {
        return this.M;
    }
    
    public final void saveVectorial(final String s, final boolean b) {
        if (this.addActionListener != null) {
            return;
        }
        try {
            final WVFComponentCapsa wvfComponentCapsa = new WVFComponentCapsa(s, 10.0f);
            wvfComponentCapsa.setResourceProvider(this.formula.getResourceProvider());
            this.M.setBounding(this.M.x, this.M.y, this.M.I, this.M.Z, 10.0 * this.M.w, 10.0 * this.M.h);
            final WVFGraphics graphics = wvfComponentCapsa.createGraphics((int)this.M.w, (int)this.M.h);
            if (b) {
                graphics.data.writeShort(-32763);
                graphics.data.writeInt(graphics.getColorNumber(this.colorDissimulat));
            }
            graphics.data.writeShort(16647);
            graphics.data.writeShort(0);
            final boolean createGraphics = PlotCanvas.createGraphics;
            PlotCanvas.createGraphics = false;
            this.Afegir(wvfComponentCapsa, graphics, 0.1f);
            PlotCanvas.createGraphics = createGraphics;
            this.M.setBounding(this.M.x, this.M.y, this.M.I, this.M.Z, this.M.w / 10.0, this.M.h / 10.0);
            final int size = graphics.getSize();
            final int length = s.length();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(15);
            dataOutputStream.writeInt(4);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeInt(16);
            dataOutputStream.writeInt(length + size + 8);
            dataOutputStream.writeInt(length);
            dataOutputStream.writeBytes(s);
            dataOutputStream.writeInt(size);
            graphics.saveTo(dataOutputStream);
            dataOutputStream.writeInt(18);
            dataOutputStream.writeInt(0);
            dataOutputStream.flush();
            (this.addActionListener = new MessageBox(this.formula.getMainFrame(), "Save image", "Generating the image...")).setModal(false);
            this.addActionListener.show();
            final OmegaClient omega = OmegaClient.newOmega();
            if (this.I != null) {
                omega.setDocumentBase(this.I.getDocumentBase());
            }
            new OmegaQueueEvaluation().freeContent(omega, byteArrayOutputStream, this, false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final int[] getImageBuffer(final boolean b) {
        final Dimension size = this.getSize();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics = image.getGraphics();
        graphics.setClip(0, 0, size.width, size.height);
        graphics.setColor(b ? this.getBackground() : Color.white);
        graphics.fillRect(0, 0, size.width, size.height);
        this.Afegir(this.formula, graphics, 1.0f);
        return (int[])Plot3d.compileImage(image)[2];
    }
    
    private static final int capsaComandes(final int n, final DataInputStream dataInputStream) {
        switch ((short)(n & 0xFFFFC000)) {
            case 0: {
                return 0;
            }
            case 16384: {
                return 2;
            }
            case Short.MIN_VALUE: {
                return 4;
            }
            default: {
                return dataInputStream.readInt();
            }
        }
    }
    
    public final synchronized void processEvent(final AWTEvent awtEvent) {
        try {
            switch (awtEvent.getID()) {
                case 2999: {
                    if (this.addActionListener == null) {
                        break;
                    }
                    this.addActionListener.dispose();
                    this.addActionListener = null;
                    if (this.I != null) {
                        this.I.receiveAndShowDocument(((OmegaAnswerEvent)awtEvent).input);
                        break;
                    }
                    break;
                }
            }
            super.processEvent(awtEvent);
        }
        catch (Exception ex) {
            ex.printStackTrace(OmegaSystem.err);
        }
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        super.exchangeProperties(hashtable, n);
        super.mouseLabelMode = Attributes.exchangeIntTranslate(hashtable, "information", n, super.mouseLabelMode, 1, PlotterCanvas.infoArray);
        this.C = Attributes.exchangeBool(hashtable, "show_axis", n, this.C, true);
        this.B = Attributes.exchangeBool(hashtable, "show_grid", n, this.B, true);
        float[] array = null;
        double[] boundingBox;
        if (n == 1) {
            boundingBox = this.M.getBoundingBox();
            array = new float[] { (float)(boundingBox[0] + boundingBox[2] / 2.0), (float)(boundingBox[1] + boundingBox[3] / 2.0) };
        }
        else {
            boundingBox = new double[6];
        }
        final float[] exchangeFloatV = Attributes.exchangeFloatV(2, hashtable, "center", n, array, new float[] { 0.0f, 0.0f });
        boundingBox[2] = Attributes.exchangeFloat(hashtable, "width", n, (float)boundingBox[2], 0.0f);
        boundingBox[3] = Attributes.exchangeFloat(hashtable, "height", n, (float)boundingBox[3], 0.0f);
        boundingBox[4] = Attributes.exchangeFloat(hashtable, "window_width", n, (float)boundingBox[4], 0.0f);
        boundingBox[5] = Attributes.exchangeFloat(hashtable, "window_height", n, (float)boundingBox[5], 0.0f);
        if (n == 0 || n == 4097) {
            boundingBox[0] = exchangeFloatV[0] - boundingBox[2] / 2.0;
            boundingBox[1] = exchangeFloatV[1] - boundingBox[3] / 2.0;
            if (boundingBox[2] != 0.0 && boundingBox[3] != 0.0) {
                this.M.setBoundingBox(boundingBox[0], boundingBox[1], boundingBox[2], boundingBox[3], boundingBox[4], boundingBox[5]);
            }
        }
    }
    
    public final void setSelectedPoint(final String s, final int n, final int n2) {
        if (this.setSelectedPoint(s, this.drawLine.x, this.drawLine.y, n, n2, this.formula)) {
            return;
        }
        final double[] s2w = this.M.s2w(n, n2);
        final float[] array = { (float)s2w[0], (float)s2w[1] };
        if (this.B) {
            array[0] = (float)this.dispose * Math.round(array[0] / this.dispose);
            array[1] = (float)this.double2String * Math.round(array[1] / this.double2String);
        }
        this.setSelectedPoint(s, array);
    }
    
    static {
        PlotCanvas.addElement = new Color(255, 255, 255);
        PlotCanvas.addKeyListener = new Font("SansSerif", 0, 12);
        CURSOR_ZOOM = new Cursor(1);
        CURSOR_ZOOM_RECTANGULAR = new Cursor(1);
        CURSOR_MOVE_POINTS = new Cursor(0);
        CURSOR_MOVING_POINT = new Cursor(12);
        CURSOR_WAIT = new Cursor(3);
        addMouseListener = new int[] { -3, 0, 3 };
        addMouseMotionListener = new int[] { 0, -8, 0 };
        afegirCapsaComandes = new int[] { 0, 8, 0 };
        append = new int[] { 3, 0, -3 };
        PlotCanvas.createGraphics = true;
        PlotterCanvas.script_format = new String[] { "<mfenced>", "</mfenced>", "<mfenced open=\"{\" close=\"}\">", "</mfenced>", "<mfenced open=\"[\" close=\"]\">", "</mfenced>", "<mo>,</mo>", "<mo>=</mo>", "<mi>", "</mi>", "<mo>\"</mo>", "<mo>\"</mo>" };
        dibuixaPoligonal = new Dimension(1000, 1000);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import edu.wise.exceptions.DataNotFoundException;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import edu.wise.correl.gui.StyleSheet;
import java.awt.Graphics;
import java.awt.Image;
import edu.wise.correl.gui.ManualMeanLine;
import edu.wise.correl.gui.ManualRegLine;
import edu.wise.graph.CorrelGraph;
import java.awt.Panel;

public class InnerDataView extends Panel
{
    private int square;
    private int interval_y;
    private int interval_x;
    private int n_hashmarks;
    private int hash_len;
    private static CorrelGraph cg;
    private int[] regCoords;
    private static boolean drawRegLine;
    private static boolean drawSStot;
    private static boolean drawSSErr;
    private static boolean drawYbar;
    private static boolean drawSSpred;
    private static boolean drawAsSquaredError;
    private static boolean drawGrid;
    private static boolean colorCoded;
    private static ManualRegLine mr;
    private static ManualMeanLine mm;
    static Image os_image;
    static Graphics graphics;
    private Cor_app ca;
    private dataView dv;
    private CorrelData dataSet;
    private static ManualRegLine activeLine;
    public static final boolean DEBUG = false;
    
    InnerDataView() {
        this.square = 0;
        this.n_hashmarks = 6;
        this.hash_len = 5;
    }
    
    InnerDataView(final Cor_app ca, final dataView dv, final int n, final int n2, final Image os_image) {
        this.square = 0;
        this.n_hashmarks = 6;
        this.hash_len = 5;
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        this.interval_y = Math.round(n2 / this.n_hashmarks);
        this.interval_x = Math.round(n / this.n_hashmarks);
        this.ca = ca;
        this.dv = dv;
        this.dataSet = Cor_app.cd;
        InnerDataView.drawRegLine = true;
        InnerDataView.drawSStot = false;
        InnerDataView.drawSSErr = true;
        InnerDataView.drawSSpred = true;
        InnerDataView.drawYbar = true;
        InnerDataView.drawGrid = true;
        InnerDataView.cg = new CorrelGraph(n, n2, this, this.dataSet);
        InnerDataView.mr = new ManualRegLine(InnerDataView.cg);
        (InnerDataView.mm = new ManualMeanLine(InnerDataView.cg)).getRegLine();
        this.addMouseListener(new idvMouseControls());
        this.addMouseMotionListener(new idvMouseMotionControls());
        InnerDataView.os_image = os_image;
        (InnerDataView.graphics = InnerDataView.os_image.getGraphics()).setColor(StyleSheet.BACKGROUND);
        InnerDataView.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.update(true);
    }
    
    public void update() {
        this.update(true);
    }
    
    public void update(final boolean b) {
        switch (this.ca.getPanels()) {
            case 0: {
                InnerDataView.activeLine = InnerDataView.mr;
                this.setColorCoded(false);
                break;
            }
            case 1: {
                InnerDataView.activeLine = InnerDataView.mm;
                this.setColorCoded(true);
                break;
            }
            default: {
                System.out.println("InnerDataView.update switch default reached");
                break;
            }
        }
        CorrelGraph.update(b);
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        InnerDataView.graphics.setColor(StyleSheet.BACKGROUND);
        InnerDataView.graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        InnerDataView.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        InnerDataView.graphics.setColor(Color.black);
        InnerDataView.graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        for (int i = 1; i < this.n_hashmarks; ++i) {
            InnerDataView.graphics.drawLine(0, this.getSize().height - i * this.interval_y, this.hash_len, this.getSize().height - i * this.interval_y);
            InnerDataView.graphics.drawLine(this.getSize().width, this.getSize().height - i * this.interval_y, this.getSize().width - this.hash_len, this.getSize().height - i * this.interval_y);
            InnerDataView.graphics.drawLine(this.interval_x * i, 0, this.interval_x * i, 5);
            InnerDataView.graphics.drawLine(this.interval_x * i, this.getSize().height, this.interval_x * i, this.getSize().height - 5);
        }
        if (InnerDataView.drawGrid) {
            InnerDataView.graphics.setColor(Color.lightGray);
            for (int j = 1; j < this.n_hashmarks; ++j) {
                InnerDataView.graphics.drawLine(1, this.getSize().height - j * this.interval_y, this.getSize().width - 2, this.getSize().height - j * this.interval_y);
                InnerDataView.graphics.drawLine(this.interval_x * j, 1, this.interval_x * j, this.getSize().height - 2);
            }
            InnerDataView.graphics.setColor(Color.black);
        }
        if (InnerDataView.drawRegLine) {
            InnerDataView.activeLine.paint(InnerDataView.graphics);
        }
        if (InnerDataView.drawYbar) {
            InnerDataView.graphics.setColor(Color.gray);
            try {
                InnerDataView.graphics.drawLine(0, this.getSize().height - CorrelGraph.getYbar(), this.getSize().width, this.getSize().height - CorrelGraph.getYbar());
            }
            catch (DataNotFoundException ex4) {}
        }
        for (int k = 0; k < CorrelGraph.getXArr().length; ++k) {
            try {
                if (InnerDataView.colorCoded) {
                    InnerDataView.graphics.setColor(StyleSheet.randomColor(k));
                }
                else {
                    InnerDataView.graphics.setColor(Color.black);
                }
                CorrelGraph.getDataPt(k).paint(InnerDataView.graphics, InnerDataView.colorCoded);
                InnerDataView.graphics.setColor(StyleSheet.TOT);
                this.square = Math.abs(CorrelGraph.getYbar() - CorrelGraph.getYi(k));
                if (CorrelGraph.getYi(k) > CorrelGraph.getYbarArr(k) && InnerDataView.drawSStot) {
                    squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYi(k), this.square);
                }
                else if (CorrelGraph.getYi(k) < CorrelGraph.getYbar() && InnerDataView.drawSStot) {
                    squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYbarArr(k), this.square);
                }
            }
            catch (DataNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
            if (InnerDataView.drawSSErr) {
                InnerDataView.graphics.setColor(StyleSheet.ERR);
                try {
                    this.square = Math.abs(CorrelGraph.getYpredArr(k) - CorrelGraph.getYi(k));
                    if (CorrelGraph.getYi(k) > CorrelGraph.getYpredArr(k)) {
                        squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYi(k), this.square);
                    }
                    else {
                        squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYpredArr(k), this.square);
                    }
                }
                catch (DataNotFoundException ex2) {
                    System.err.println(ex2.getMessage());
                }
                InnerDataView.graphics.setColor(Color.black);
            }
            if (InnerDataView.drawSSpred) {
                InnerDataView.graphics.setColor(Color.blue);
                try {
                    this.square = Math.abs(CorrelGraph.getYpredArr(k) - CorrelGraph.getYbarArr(k));
                    if (CorrelGraph.getYbarArr(k) > CorrelGraph.getYpredArr(k)) {
                        squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYbarArr(k), this.square);
                    }
                    else {
                        squareError.paint(InnerDataView.graphics, CorrelGraph.getXi(k), this.getSize().height - CorrelGraph.getYpredArr(k), this.square);
                    }
                }
                catch (DataNotFoundException ex3) {
                    System.err.println(ex3.getMessage());
                }
                InnerDataView.graphics.setColor(Color.black);
            }
        }
        graphics.drawImage(InnerDataView.os_image, 0, 0, this);
    }
    
    public void setColorCoded(final boolean colorCoded) {
        InnerDataView.colorCoded = colorCoded;
        this.repaint();
    }
    
    public void setDrawRegLine(final boolean drawRegLine) {
        InnerDataView.drawRegLine = drawRegLine;
        InnerDataView.activeLine.setActive(false);
        this.repaint();
    }
    
    public void setDrawSSpred(final boolean drawSSpred) {
        InnerDataView.drawSSpred = drawSSpred;
        this.repaint();
    }
    
    public void setDrawSStot(final boolean drawSStot) {
        InnerDataView.drawSStot = drawSStot;
        this.repaint();
    }
    
    public void setDrawSSErr(final boolean drawSSErr) {
        InnerDataView.drawSSErr = drawSSErr;
        this.repaint();
    }
    
    public void setDrawYbar(final boolean drawYbar) {
        InnerDataView.drawYbar = drawYbar;
        this.repaint();
    }
    
    public void setDrawGrid(final boolean drawGrid) {
        InnerDataView.drawGrid = drawGrid;
        this.repaint();
    }
    
    public void setDrawAsSquaredError(final boolean square) {
        squareError.setSquare(square);
    }
    
    public void setZoom(final double zoom) {
        InnerDataView.cg.setZoom(zoom);
        CorrelData.update();
        InnerDataView.activeLine.getRegLine();
        this.dv.update();
    }
    
    public static ManualRegLine getActiveLine() {
        return InnerDataView.activeLine;
    }
    
    public static int getActivePt() {
        return CorrelGraph.getActivePt();
    }
    
    public static ManualMeanLine getManualMeanLine() {
        return InnerDataView.mm;
    }
    
    public int height() {
        return this.getSize().height;
    }
    
    public int width() {
        return this.getSize().width;
    }
    
    public boolean getDrawRegLine() {
        return InnerDataView.drawRegLine;
    }
    
    public boolean getDrawSSpred() {
        return InnerDataView.drawSSpred;
    }
    
    public boolean getDrawSStot() {
        return InnerDataView.drawSStot;
    }
    
    public boolean getDrawSSErr() {
        return InnerDataView.drawSSErr;
    }
    
    public boolean getDrawYbar() {
        return InnerDataView.drawYbar;
    }
    
    public boolean getDrawAsSquaredError() {
        return InnerDataView.drawAsSquaredError;
    }
    
    public CorrelGraph getCg() {
        return InnerDataView.cg;
    }
    
    class idvMouseControls extends MouseAdapter implements MouseListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (Cor_app.vo.getCurrentChoice() != InnerDataView.this.ca.getPanels()) {
                InnerDataView.this.ca.setPanels(Cor_app.vo.getCurrentChoice());
            }
            int activePt = 0;
            int gotFocus;
            do {
                try {
                    gotFocus = (CorrelGraph.getDataPt(activePt).gotFocus(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                }
                catch (DataNotFoundException ex) {
                    gotFocus = 0;
                }
                if (gotFocus != 0) {
                    CorrelGraph.setActivePt(activePt);
                    break;
                }
                ++activePt;
            } while (gotFocus == 0 && activePt < CorrelGraph.dpLength());
            if (mouseEvent.getClickCount() == 2 && gotFocus != 0) {
                CorrelData.delPt(activePt);
                InnerDataView.activeLine.getRegLine();
                InnerDataView.this.update(true);
            }
            if (mouseEvent.getClickCount() == 2 && gotFocus == 0) {
                final double[] convertToActual = CorrelGraph.convertToActual(mouseEvent.getX(), mouseEvent.getY());
                CorrelData.addCase(convertToActual[0], convertToActual[1]);
                InnerDataView.activeLine.getRegLine();
                InnerDataView.this.update(true);
                InnerDataView.activeLine.getRegLine();
                gotFocus = 1;
            }
            if (gotFocus == 0 && Cor_app.vo.getShowRegLine()) {
                InnerDataView.activeLine.gotFocus(mouseEvent.getX(), mouseEvent.getY());
            }
            else {
                InnerDataView.activeLine.setActive(false);
            }
            Cor_app.update();
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            try {
                for (int i = 0; i < CorrelGraph.dpLength(); ++i) {
                    CorrelGraph.getDataPt(i).setActive(false);
                }
            }
            catch (DataNotFoundException ex) {}
            CorrelGraph.setActivePt(-999);
            Cor_app.update();
        }
    }
    
    class idvMouseMotionControls extends MouseMotionAdapter implements MouseMotionListener
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (CorrelGraph.movePt(mouseEvent.getX(), mouseEvent.getY())) {
                InnerDataView.activeLine.getRegLine();
                Cor_app.update();
                InnerDataView.this.repaint();
            }
            else if (InnerDataView.activeLine.getActive()) {
                InnerDataView.activeLine.setRegLine(mouseEvent.getX(), mouseEvent.getY());
                Cor_app.update();
                InnerDataView.this.repaint();
            }
            InnerDataView.this.update(true);
        }
    }
}

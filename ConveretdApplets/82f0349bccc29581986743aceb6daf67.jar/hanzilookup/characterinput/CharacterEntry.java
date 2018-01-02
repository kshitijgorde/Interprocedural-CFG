// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.characterinput;

import java.awt.Graphics;
import hanzilookup.ui.CharacterCanvas;
import kiang.chinese.font.ChineseFontFinder;
import javax.swing.JFrame;
import java.util.Iterator;
import hanzilookup.ui.WrittenCharacter;
import java.text.DecimalFormat;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.io.OutputStream;
import java.awt.Font;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class CharacterEntry extends JPanel
{
    private StrokeEntryCanvas strokeCanvas;
    private JTextField unicodeEntryField;
    private JButton loadCharButton;
    private JButton analyzeButton;
    private JButton outputButton;
    private PrintWriter out;
    
    public CharacterEntry(final Font bgFont, final OutputStream out) {
        this.initUI(bgFont);
        this.out = new PrintWriter(out);
    }
    
    private void initUI(Font bgFont) {
        bgFont = bgFont.deriveFont(250.0f);
        (this.strokeCanvas = new StrokeEntryCanvas((StrokeEntryCanvas)null)).setPreferredSize(new Dimension(250, 250));
        this.strokeCanvas.setForeground(Color.LIGHT_GRAY);
        this.strokeCanvas.setFont(bgFont);
        this.strokeCanvas.setHorizontalAlignment(0);
        this.unicodeEntryField = new JTextField(4);
        this.loadCharButton = new JButton("load");
        this.analyzeButton = new JButton("analyze");
        this.outputButton = new JButton("output");
        final ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Object eventSource = e.getSource();
                if (eventSource == CharacterEntry.this.loadCharButton) {
                    CharacterEntry.this.loadChar();
                }
                else if (eventSource == CharacterEntry.this.analyzeButton) {
                    CharacterEntry.this.analyzeAndMark();
                }
                else {
                    CharacterEntry.this.output();
                }
            }
        };
        this.loadCharButton.addActionListener(buttonListener);
        this.analyzeButton.addActionListener(buttonListener);
        this.outputButton.addActionListener(buttonListener);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.unicodeEntryField);
        buttonPanel.add(this.loadCharButton);
        buttonPanel.add(this.analyzeButton);
        buttonPanel.add(this.outputButton);
        this.setLayout(new BorderLayout());
        this.add("Center", this.strokeCanvas);
        this.add("South", buttonPanel);
    }
    
    private void loadChar() {
        final String unicodeText = this.unicodeEntryField.getText();
        try {
            this.strokeCanvas.setText(Character.toString((char)Integer.parseInt(unicodeText, 16)));
        }
        catch (NumberFormatException nfe) {
            this.strokeCanvas.setText("");
            Toolkit.getDefaultToolkit().beep();
        }
        this.strokeCanvas.clear();
        this.strokeCanvas.repaint();
    }
    
    private void analyzeAndMark() {
        this.strokeCanvas.getCharacter().analyzeAndMark();
        this.strokeCanvas.repaint();
    }
    
    private void output() {
        final WrittenCharacter character = this.strokeCanvas.getCharacter();
        final DecimalFormat hundredths = new DecimalFormat("0.00");
        final StringBuffer sbuf = new StringBuffer();
        sbuf.append(this.unicodeEntryField.getText());
        final Iterator strokeIter = character.getStrokeList().iterator();
        while (strokeIter.hasNext()) {
            sbuf.append(" | ");
            final WrittenCharacter.WrittenStroke stroke = strokeIter.next();
            final Iterator subStrokesIter = stroke.getSubStrokes().iterator();
            if (subStrokesIter.hasNext()) {
                while (true) {
                    final WrittenCharacter.SubStrokeDescriptor subStroke = subStrokesIter.next();
                    final String direction = hundredths.format(subStroke.getDirection());
                    final String length = hundredths.format(subStroke.getLength());
                    sbuf.append("(").append(direction).append(", ").append(length).append(")");
                    if (!subStrokesIter.hasNext()) {
                        break;
                    }
                    sbuf.append(" # ");
                }
            }
        }
        this.out.println(sbuf.toString());
        this.out.flush();
    }
    
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        final Font chineseFont = ChineseFontFinder.getChineseFont();
        frame.getContentPane().add(new CharacterEntry(chineseFont, System.out));
        frame.pack();
        frame.setVisible(true);
    }
    
    private static class StrokeEntryCanvas extends CharacterCanvas
    {
        private static final int POINT_RADIUS = 3;
        
        protected void paintStroke(final WrittenCharacter.WrittenStroke stroke, final Graphics g) {
            super.paintStroke(stroke, g);
            for (final WrittenCharacter.WrittenPoint nextPoint : stroke.getPointList()) {
                final Color previousColor = g.getColor();
                g.setColor(Color.RED);
                if (nextPoint.isPivot()) {
                    this.paintPoint(nextPoint, g);
                }
                g.setColor(previousColor);
            }
        }
        
        private void paintPoint(final WrittenCharacter.WrittenPoint point, final Graphics g) {
            final double x = point.getX();
            final double y = point.getY();
            g.fillOval((int)x - 3, (int)y - 3, 6, 6);
        }
    }
}

import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Vector;
import java.awt.Label;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DigitList extends Panel
{
    Dimension size;
    Container owner;
    Graphics graphic;
    Font font;
    Label memoryLabel;
    Vector dList;
    int length;
    int nbOfDigits;
    String number;
    int dotPosition;
    int ePos;
    
    public DigitList(final Container container, final int n) {
        this.font = new Font("Roman", 0, 8);
        this.memoryLabel = new Label("   ");
        this.dList = new Vector();
        this.number = "0";
        this.setFont(this.font);
        this.nbOfDigits = n;
        this.length = n * 25;
        this.size = new Dimension(this.length, 45);
        this.setLayout(new GridLayout(1, n + 1));
        container.validate();
        this.setBackground(new Color(255, 230, 230));
        for (int i = 0; i < n + 1; ++i) {
            this.dList.addElement(new DigitElement(this));
            this.add((Component)this.dList.elementAt(i));
        }
        this.setDigitAt(n, 0, true);
        this.dotPosition = n;
        this.add(this.memoryLabel);
        this.memoryLabel.setText("   ");
    }
    
    public void setMemFlag(final boolean b) {
        if (b) {
            this.memoryLabel.setText("mem");
            return;
        }
        this.memoryLabel.setText("   ");
    }
    
    public boolean isMemFlag() {
        return this.memoryLabel.getText().equals("mem");
    }
    
    private void setVoid() {
        for (int i = 0; i < this.nbOfDigits + 1; ++i) {
            this.setDigitAt(i, DigitElement.EMPTY, false);
        }
    }
    
    private void setMinus(final boolean b) {
        if (b) {
            this.setDigitAt(0, DigitElement.MINUS, false);
            return;
        }
        this.setDigitAt(0, DigitElement.EMPTY, false);
    }
    
    private void setDigitAt(final int n, final int digit, final boolean b) {
        if (n < this.nbOfDigits + 1) {
            final DigitElement digitElement = this.dList.elementAt(n);
            digitElement.digit = digit;
            digitElement.repaint();
            this.setDot(n, b);
        }
    }
    
    public void setDot(final int dotPosition, final boolean isDot) {
        final DigitElement digitElement = this.dList.elementAt(this.dotPosition);
        digitElement.isDot = false;
        digitElement.repaint();
        final DigitElement digitElement2 = this.dList.elementAt(dotPosition);
        digitElement2.isDot = isDot;
        digitElement2.repaint();
        this.dotPosition = dotPosition;
    }
    
    public Insets getInsets() {
        return new Insets(4, 4, 5, 5);
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        final int[] array = { 0, 3, 3, this.length, this.length, 0, 0 };
        final int[] array2 = { 45, 42, 3, 3, 0, 0, 45 };
        graphics.setColor(Color.black);
        graphics.fillPolygon(array, array2, 7);
        graphics.setColor(Color.lightGray);
        array2[array[0] = 0] = 45;
        array[1] = 3;
        array2[1] = 42;
        array[2] = this.length - 3;
        array2[2] = 42;
        array[3] = this.length - 3;
        array2[3] = 3;
        array[4] = this.length;
        array2[4] = 3;
        array[5] = this.length;
        array2[5] = 45;
        array[6] = 0;
        array2[6] = 45;
        graphics.fillPolygon(array, array2, 7);
    }
    
    public String getText() {
        return this.number;
    }
    
    public void setText(String s) {
        this.setVoid();
        this.number = s.trim().toUpperCase();
        this.ePos = s.indexOf("E");
        if (this.ePos > -1) {
            final String substring = s.substring(this.ePos);
            String s2 = s.substring(0, this.ePos - 1);
            if (s2.length() > this.nbOfDigits - substring.length()) {
                s2 = s2.substring(0, this.nbOfDigits - substring.length());
            }
            s = String.valueOf(s2) + substring;
        }
        else if (s.length() > this.nbOfDigits) {
            s = s.substring(0, this.nbOfDigits);
        }
        if (s.length() > 0) {
            if (s.startsWith("-")) {
                s = s.substring(1);
                this.setDigitAt(0, DigitElement.MINUS, false);
            }
            else {
                this.setDigitAt(0, DigitElement.EMPTY, false);
            }
            int index = s.indexOf(".");
            if (index > -1) {
                s = String.valueOf(s.substring(0, index)) + s.substring(index + 1);
                index += this.nbOfDigits - s.length();
            }
            final char[] charArray = s.toCharArray();
            int n = 0;
            for (int i = this.nbOfDigits + 1 - s.length(); i < this.nbOfDigits + 1; ++i) {
                if (charArray[n] == 'E') {
                    this.setDigitAt(i, DigitElement.ERROR, false);
                }
                else if (charArray[n] == '-') {
                    this.setDigitAt(i, DigitElement.MINUS, false);
                }
                else {
                    this.setDigitAt(i, Integer.parseInt(String.copyValueOf(charArray, n, 1)), false);
                }
                ++n;
            }
            if (index > -1) {
                this.setDot(index, true);
                return;
            }
            this.setDot(this.dotPosition, true);
        }
    }
}

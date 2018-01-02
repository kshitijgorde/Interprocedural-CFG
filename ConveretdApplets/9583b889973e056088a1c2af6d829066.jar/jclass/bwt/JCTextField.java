// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.applet.Applet;
import jclass.util.JCVector;

public class JCTextField extends JCTextComponent
{
    char echo_char;
    String actionCommand;
    protected JCVector actionListeners;
    private static final String base = "textfield";
    private static int nameCounter;
    
    public JCTextField() {
        this("", 20);
    }
    
    public JCTextField(final String s) {
        this(s, 20);
    }
    
    public JCTextField(final String s, final int columns) {
        this(s, null, null);
        super.columns = columns;
    }
    
    public JCTextField(final String text, final Applet applet, final String s) {
        super(applet, s);
        this.actionListeners = new JCVector(0);
        this.setInsets(new Insets(2, 2, 2, 2));
        if (s == null) {
            this.setName("textfield" + JCTextField.nameCounter++);
        }
        this.setText(text);
    }
    
    protected void getParameters() {
        super.getParameters();
    }
    
    public char getEchoChar() {
        return this.echo_char;
    }
    
    public void setEchoChar(final char echo_char) {
        this.echo_char = echo_char;
        this.repaint();
    }
    
    public String getEchoCharString() {
        if (this.echo_char == '\0') {
            return null;
        }
        return new String(new char[] { this.echo_char });
    }
    
    public void setEchoCharString(final String s) {
        this.echo_char = ((s != null && s.length() > 0) ? s.charAt(0) : '\0');
    }
    
    public boolean echoCharIsSet() {
        return this.echo_char != '\0';
    }
    
    char[] getOutputChars() {
        if (this.echo_char == '\0') {
            return this.getTextChars();
        }
        final char[] array = new char[super.num_char];
        for (int i = 0; i < super.num_char; ++i) {
            array[i] = this.echo_char;
        }
        return array;
    }
    
    protected String getOutputText() {
        return new String(this.getOutputChars(), 0, super.num_char);
    }
    
    public int positionToX(int max) {
        if (this.getPeer() == null) {
            return 0;
        }
        this.getDrawingArea(super.rect);
        max = Math.max(0, Math.min(max, super.num_char));
        int n = 0;
        final char[] outputChars = this.getOutputChars();
        switch (super.alignment) {
            case 2: {
                return super.rect.x + super.rect.width - super.fm.charsWidth(outputChars, max, super.num_char - max);
            }
            case 1: {
                n = (super.rect.width - super.fm.charsWidth(outputChars, 0, super.num_char)) / 2;
                break;
            }
        }
        return super.rect.x + super.fm.charsWidth(outputChars, 0, max) + n;
    }
    
    public void showPosition(final int cursor_pos) {
        if (this.getPeer() == null) {
            return;
        }
        final int positionToX = this.positionToX(cursor_pos);
        final int horiz_origin = super.horiz_origin;
        this.getDrawingArea(super.rect);
        if (super.rect.width == 0 || super.rect.height == 0) {
            return;
        }
        if (positionToX < super.horiz_origin + super.rect.x) {
            super.horiz_origin = positionToX - super.rect.x - 2;
        }
        else if (positionToX > super.horiz_origin + super.rect.x + super.rect.width) {
            super.horiz_origin = positionToX - (super.rect.x + super.rect.width) + 5;
        }
        if (horiz_origin != super.horiz_origin) {
            this.repaint();
        }
        super.cursor_pos = cursor_pos;
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public String getActionCommand() {
        if (this.actionCommand == null) {
            return "";
        }
        return this.actionCommand;
    }
    
    public void addActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.add(jcActionListener);
    }
    
    public void removeActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.removeElement(jcActionListener);
    }
    
    final int drawLine(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n, n2);
        return super.fm.stringWidth(s);
    }
    
    public Dimension getMinimumSize(final int n) {
        if (this.getPeer() != null) {
            return new Dimension(n * super.fm.charWidth('N'), this.preferredHeight());
        }
        return super.minimumSize();
    }
    
    protected void paintComponent(final Graphics graphics) {
        graphics.translate(-super.horiz_origin, 0);
        final String outputText = this.getOutputText();
        this.getDrawingArea(super.rect);
        int x = super.rect.x;
        switch (super.alignment) {
            case 1: {
                x += (super.rect.width - super.fm.stringWidth(outputText)) / 2;
                break;
            }
            case 2: {
                x += super.rect.width - super.fm.stringWidth(outputText);
                break;
            }
        }
        final int height = super.fm.getHeight();
        final int n = super.rect.y + height - (height - super.fm.getAscent());
        if (!this.isEnabled()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.lightGray.darker().darker());
            graphics.drawString(outputText, x, n);
            graphics.setColor(color);
        }
        else if (super.select_start != super.select_end) {
            if (super.select_start > 0) {
                x += this.drawLine(graphics, outputText.substring(0, super.select_start), x, n);
            }
            final int positionToX = this.positionToX(super.select_start);
            final int charsWidth = super.fm.charsWidth(this.getOutputChars(), super.select_start, super.select_end - super.select_start);
            this.setSelectedBg(graphics);
            graphics.fillRect(positionToX, 0, charsWidth, this.size().height);
            this.setSelectedFg(graphics);
            final int n2 = x + this.drawLine(graphics, outputText.substring(super.select_start, super.select_end), x, n);
            graphics.setColor(this.getForeground());
            this.drawLine(graphics, outputText.substring(super.select_end), n2, n);
        }
        else {
            graphics.drawString(outputText, x, n);
        }
        graphics.translate(super.horiz_origin, 0);
        this.blinkCursor(true);
    }
    
    public int pointToPosition(int n, final int n2) {
        final char[] outputChars = this.getOutputChars();
        final int[] widths = super.fm.getWidths();
        this.size();
        this.getDrawingArea(super.rect);
        switch (super.alignment) {
            case 1: {
                n -= super.rect.x + (super.rect.width - super.fm.charsWidth(outputChars, 0, super.num_char)) / 2;
                break;
            }
            case 2: {
                n -= super.rect.x + super.rect.width - super.fm.charsWidth(outputChars, 0, super.num_char) - super.horiz_origin;
                break;
            }
            default: {
                n -= super.rect.x - super.horiz_origin;
                break;
            }
        }
        for (int i = 0; i < super.num_char; ++i) {
            final int n3 = widths[outputChars[i]];
            if (super.overstrike) {
                if (n < n3) {
                    return i;
                }
            }
            else if (n < n3 / 2) {
                return i;
            }
            n -= n3;
        }
        return super.num_char;
    }
    
    protected void postActionEvent(Event event) {
        final String actionCommand = this.getActionCommand();
        if (event == null) {
            event = new Event(this, 0, actionCommand);
        }
        final JCActionEvent jcActionEvent = new JCActionEvent(this, event.id, actionCommand, event.modifiers);
        for (int i = 0; i < this.actionListeners.size(); ++i) {
            ((JCActionListener)this.actionListeners.elementAt(i)).actionPerformed(jcActionEvent);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.postActionEvent(event);
            return true;
        }
        return super.keyDown(event, n);
    }
}

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class HelpPanel extends Panel implements MouseListener
{
    private static final int INSET = 4;
    private static final int OFFSET = 4;
    private static final int WIDTH = 150;
    private String alignment;
    private String helpMessage;
    private FontMetrics fontMetrics;
    private DrawableTextBox helpTextBox;
    
    public HelpPanel(final String helpMessage, final String alignment) {
        this.alignment = alignment;
        this.helpMessage = helpMessage;
        this.addMouseListener(this);
    }
    
    public void changeMessage(final String helpMessage) {
        Screen.canvas.removeHelp(this.helpTextBox);
        this.helpMessage = helpMessage;
        this.helpTextBox = null;
    }
    
    private DrawableTextBox makeTextBox(final String helpMessage) {
        final Font font = Screen.screen.getFont();
        this.fontMetrics = Screen.screen.getFontMetrics(font);
        final int noLines = this.splitLines(helpMessage, null);
        final String[] lines = new String[noLines];
        this.splitLines(helpMessage, lines);
        final int height = this.fontMetrics.getHeight() * noLines;
        (this.helpTextBox = new DrawableTextBox(height, 150)).setText(lines);
        final int boxWidth = 158;
        final int boxHeight = height + 8;
        final int xMax = 546 - boxWidth - 4;
        final int yMax = 270 - boxHeight - 4;
        int xPos = xMax;
        int yPos = yMax;
        if (this.alignment.equals("North") || this.alignment.equals("Northeast")) {
            yPos = 4;
        }
        if (this.alignment.equals("East")) {
            yPos = this.getLocation().y + this.getSize().height / 2 - boxHeight / 2;
            yPos = Math.max(4, yPos);
            yPos = Math.min(yMax, yPos);
        }
        else if (this.alignment.equals("North") || this.alignment.equals("South")) {
            xPos = this.getLocation().x + this.getSize().width / 2 - boxWidth / 2;
            xPos = Math.max(4, xPos);
            xPos = Math.min(xMax, xPos);
        }
        this.helpTextBox.reposition(new Position(xPos, yPos));
        return this.helpTextBox;
    }
    
    public void mouseClicked(final MouseEvent event) {
    }
    
    public void mouseEntered(final MouseEvent event) {
        this.showHelp();
    }
    
    public void mouseExited(final MouseEvent event) {
        Screen.canvas.removeHelp(this.helpTextBox);
    }
    
    public void mousePressed(final MouseEvent event) {
    }
    
    public void mouseReleased(final MouseEvent event) {
    }
    
    public void showHelp() {
        if (this.helpTextBox == null) {
            this.helpTextBox = this.makeTextBox(this.helpMessage);
        }
        Screen.canvas.addHelp(this.helpTextBox);
    }
    
    private int splitLines(final String text, final String[] lines) {
        int toIndex = 0;
        int fromIndex = 0;
        int lastIndex = 0;
        int lineNo = 0;
        while (fromIndex != -1) {
            while (fromIndex < text.length() && text.charAt(fromIndex) == ' ') {
                ++fromIndex;
            }
            toIndex = fromIndex;
            String line = null;
            String lastLine;
            do {
                lastLine = line;
                lastIndex = toIndex;
                toIndex = text.indexOf(32, toIndex);
                if (toIndex == -1) {
                    line = text.substring(fromIndex);
                }
                else {
                    line = text.substring(fromIndex, toIndex++);
                }
            } while (this.fontMetrics.stringWidth(line) < 150 && toIndex != -1);
            if (this.fontMetrics.stringWidth(line) > 150 && lastLine != null) {
                line = lastLine;
                fromIndex = lastIndex;
            }
            else {
                fromIndex = toIndex;
            }
            if (lines != null) {
                lines[lineNo] = line;
            }
            ++lineNo;
        }
        return lineNo;
    }
}

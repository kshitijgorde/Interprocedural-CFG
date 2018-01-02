import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Timer;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Typer extends JApplet
{
    String currentLine;
    String line;
    JLabel theLine;
    Container content;
    Timer t;
    int i;
    int delay;
    
    public Typer() {
        this.theLine = new JLabel();
        this.content = this.getContentPane();
        this.i = 0;
    }
    
    public void init() {
        this.line = this.getParameter("LINE");
        this.content.add(this.theLine);
        this.content.setLayout(new GridLayout(1, 1));
        this.content.setBackground(Color.black);
        this.theLine.setBackground(Color.black);
        if (this.getParameter("bgred") != null && this.getParameter("bggreen") != null && this.getParameter("bgblue") != null) {
            this.content.setBackground(new Color(Integer.parseInt(this.getParameter("bgred")), Integer.parseInt(this.getParameter("bggreen")), Integer.parseInt(this.getParameter("bgblue"))));
            this.theLine.setBackground(new Color(Integer.parseInt(this.getParameter("bgred")), Integer.parseInt(this.getParameter("bggreen")), Integer.parseInt(this.getParameter("bgblue"))));
        }
        else {
            this.content.setBackground(Color.black);
            this.theLine.setBackground(Color.black);
        }
        if (this.getParameter("fgred") != null && this.getParameter("fggreen") != null && this.getParameter("fgblue") != null) {
            this.theLine.setForeground(new Color(Integer.parseInt(this.getParameter("fgred")), Integer.parseInt(this.getParameter("fggreen")), Integer.parseInt(this.getParameter("fgblue"))));
        }
        else {
            this.theLine.setForeground(Color.green);
        }
        if (this.getParameter("delay") != null) {
            this.delay = Integer.parseInt(this.getParameter("delay"));
        }
        else {
            this.delay = 100;
        }
        (this.t = new Timer(this.delay, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (Typer.this.i == Typer.this.line.length()) {
                    Typer.this.t.stop();
                }
                else {
                    Typer.this.currentLine = Typer.this.line.substring(0, Typer.this.i + 1);
                    Typer.this.theLine.setText("<html>" + Typer.this.currentLine + "</html>");
                }
                final Typer this$0 = Typer.this;
                ++this$0.i;
                Typer.this.validate();
            }
        })).start();
    }
}

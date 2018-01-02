import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IDSV extends Applet implements ActionListener
{
    public static final int CANVAS_WIDTH = 546;
    public static final int CANVAS_INSET = 8;
    private Screen screen;
    private Button startButton;
    
    public IDSV() {
        this.startButton = new Button("Start Exercise");
    }
    
    public void actionPerformed(final ActionEvent event) {
        this.screen.show();
    }
    
    public void start() {
        this.addNotify();
        super.init();
        this.setBackground(Color.white);
        final int lesson = Integer.parseInt(this.getParameter("lesson"));
        this.screen = new Screen(lesson);
        this.setFont(new Font("Proportional", 0, 12));
        this.add(this.startButton);
        this.startButton.addActionListener(this);
        Screen.progress.getStudentNames("idsv-sections.txt");
    }
    
    public void stop() {
        this.screen.closeProgress();
        Screen.controls.abort();
        super.stop();
    }
}

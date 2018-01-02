import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EmulatorApplet extends Applet implements ActionListener
{
    TeletextEmulator teletextEmulator;
    
    public void init() {
        final String parameter = this.getParameter("directory");
        System.out.println("Direrctory: " + parameter);
        this.add(this.teletextEmulator = new TeletextEmulator(this.getCodeBase().getHost() + parameter, this.getAppletContext()));
        final Button button = new Button("Indice");
        button.setActionCommand("index");
        button.addActionListener(this);
        this.add(button);
        final Button button2 = new Button("Pagina seguente");
        button2.setActionCommand("nextPage");
        button2.addActionListener(this);
        this.add(button2);
        final Button button3 = new Button("Pagina precedente");
        button3.setActionCommand("previousPage");
        button3.addActionListener(this);
        this.add(button3);
        final Button button4 = new Button("Sottopagina avanti");
        button4.setActionCommand("nextRolling");
        button4.addActionListener(this);
        this.add(button4);
        final Button button5 = new Button("Sottopagina indietro");
        button5.setActionCommand("previousRolling");
        button5.addActionListener(this);
        this.add(button5);
        this.teletextEmulator.render();
        this.teletextEmulator.repaint();
    }
    
    public String[][] getParameterInfo() {
        final String[][] array = new String[1][];
        array[0] = new String[] { "Directory", "string", "source directory" };
        return array;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("back")) {
            this.teletextEmulator.back();
        }
        if (actionEvent.getActionCommand().equals("index")) {
            this.teletextEmulator.seekPage(100);
        }
        if (actionEvent.getActionCommand().equals("nextRolling")) {
            this.teletextEmulator.incrementRollingNumber();
        }
        if (actionEvent.getActionCommand().equals("previousRolling")) {
            this.teletextEmulator.decrementRollingNumber();
        }
        if (actionEvent.getActionCommand().equals("nextPage")) {
            this.teletextEmulator.incrementPageNumber();
        }
        if (actionEvent.getActionCommand().equals("previousPage")) {
            this.teletextEmulator.decrementPageNumber();
        }
    }
}

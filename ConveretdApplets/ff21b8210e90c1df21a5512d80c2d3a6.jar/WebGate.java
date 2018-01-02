import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WebGate extends Applet
{
    Button submit;
    Button clear;
    TextField input;
    Label prompt;
    Label wrong;
    
    public void init() {
        this.setLayout(null);
        this.setSize(230, 148);
        this.setBackground(new Color(0));
        (this.submit = new Button()).setActionCommand("button");
        this.submit.setLabel("Submit");
        this.submit.setBounds(36, 108, 60, 24);
        this.submit.setForeground(new Color(16777215));
        this.submit.setBackground(new Color(0));
        this.add(this.submit);
        (this.clear = new Button()).setActionCommand("button");
        this.clear.setLabel("Clear");
        this.clear.setBounds(132, 108, 60, 24);
        this.clear.setForeground(new Color(16777215));
        this.clear.setBackground(new Color(0));
        this.add(this.clear);
        (this.input = new TextField()).setEchoChar('*');
        this.input.setBounds(36, 48, 156, 24);
        this.input.setBackground(new Color(16777215));
        this.add(this.input);
        (this.prompt = new Label("Enter Password:", 1)).setBounds(36, 12, 156, 24);
        this.prompt.setFont(new Font("Dialog", 1, 14));
        this.prompt.setForeground(new Color(16777215));
        this.add(this.prompt);
        (this.wrong = new Label("Invalid Password!", 1)).setBounds(36, 72, 156, 23);
        this.wrong.setFont(new Font("Dialog", 1, 12));
        this.wrong.setForeground(new Color(0));
        this.add(this.wrong);
        final MouseClick mouseClick = new MouseClick();
        this.clear.addMouseListener(mouseClick);
        this.input.addActionListener(new EnterKey());
        this.submit.addMouseListener(mouseClick);
    }
    
    public void getDestination(final String s) {
        try {
            final URLConnection openConnection = new URL(String.valueOf(this.getCodeBase().toString()) + "/url.txt").openConnection();
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                this.getPassword(line, s);
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    public void getPassword(final String s, final String s2) {
        try {
            final URLConnection openConnection = new URL(String.valueOf(this.getCodeBase().toString()) + "/password.txt").openConnection();
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                this.checkPassword(s, line, s2);
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    public void checkPassword(final String s, final String s2, final String s3) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {}
        if (s2.equals(s3)) {
            if (url != null) {
                this.getAppletContext().showDocument(url);
            }
        }
        else {
            this.wrong.setForeground(Color.red);
        }
    }
    
    void ClearMouseClick(final MouseEvent mouseEvent) {
        this.input.setText("");
        this.wrong.setForeground(Color.black);
    }
    
    void InputEnterHit(final ActionEvent actionEvent) {
        this.getDestination(this.input.getText());
    }
    
    void SubmitMouseClick(final MouseEvent mouseEvent) {
        this.getDestination(this.input.getText());
    }
    
    class MouseClick extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            final Object source = mouseEvent.getSource();
            if (source == WebGate.this.clear) {
                WebGate.this.ClearMouseClick(mouseEvent);
                return;
            }
            if (source == WebGate.this.submit) {
                WebGate.this.SubmitMouseClick(mouseEvent);
            }
        }
    }
    
    class EnterKey implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == WebGate.this.input) {
                WebGate.this.InputEnterHit(actionEvent);
            }
        }
    }
}

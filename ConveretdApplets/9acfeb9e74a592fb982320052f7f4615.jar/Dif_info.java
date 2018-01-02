import java.awt.Component;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Dif_info extends Frame
{
    String texto;
    
    public Dif_info() {
        super("Information about Diffusion Applet");
        this.texto = "            N = number of collisions; \n s = standard deviation of the distance from origin\nStatistically, the average distance traveled by a \nparticle after N collisions, is the square root of N\nmultiplyed by the mean free path.\nThis simulation model is applicable to the diffusion\nof Brownian particles and also to a group of molecules\nwhich are different in some way from the molecules of\nthe fluid.\nIf in the mode -Trace OFF- click with the mouse into\nthe drawing-area, you will see a bars-graphic showing\nthe evolution of the particles density along de x axis.\nThe next version will include  Diffusion's coefficient.\n   Author N.Betancort   Dec-1999";
        this.setSize(360, 300);
        this.setResizable(false);
        final TextArea textArea = new TextArea();
        this.add(textArea);
        textArea.setEditable(false);
        textArea.setText(this.texto);
    }
}

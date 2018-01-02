// 
// Decompiled by Procyon v0.5.30
// 

package ifs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class IFS extends JFrame
{
    public static void main(final String[] args) {
        final IFS frame = new IFS();
        frame.setTitle("Chaos Game");
        frame.setDefaultCloseOperation(3);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = Math.min(800, screenSize.width - 150);
        final int height = Math.min(800, screenSize.height - 150);
        final int size = Math.min(width, height);
        final IFSCanvas canvas = new IFSCanvas();
        canvas.setPreferredSize(new Dimension(size, size));
        frame.setContentPane(canvas);
        frame.setJMenuBar(canvas.getMenuBar(false));
        frame.pack();
        frame.setLocation(50, 50);
        frame.setVisible(true);
    }
}

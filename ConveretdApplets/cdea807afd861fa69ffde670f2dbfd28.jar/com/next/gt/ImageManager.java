// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Image;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.File;

public class ImageManager
{
    Gamelication owner;
    
    public ImageManager(final Gamelication gamelication) {
        this(gamelication, ".cache");
    }
    
    public ImageManager(final Gamelication owner, final String s) {
        int n = 0;
        ((Component)(this.owner = owner)).createImage(1, 1);
        final File file = new File(String.valueOf(this.owner.getCodeBase().toString()) + "/images/" + s);
        final MediaTracker mediaTracker = new MediaTracker((Component)this.owner);
        try {
            String line;
            while ((line = new BufferedReader(new FileReader(file)).readLine()) != null) {
                ++n;
                final Image image = this.owner.getImage(this.owner.getCodeBase(), "images/" + line + ".gif");
                mediaTracker.addImage(image, n);
                this.owner.showStatus("GT: Caching image: " + line + ".");
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {
                    System.out.println("GT: ImageManager ridiculous image; " + ex.getMessage());
                }
                ((Component)this.owner).createImage(1, 1).getGraphics().drawImage(image, 0, 0, (ImageObserver)this.owner);
            }
        }
        catch (IOException ex2) {
            System.out.println("GOOF: ImageManager cannot getImage; " + ex2.getMessage());
        }
    }
}

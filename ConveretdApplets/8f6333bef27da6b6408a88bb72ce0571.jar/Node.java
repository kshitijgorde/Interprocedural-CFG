import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class Node
{
    public String text;
    public URL url;
    public int parentIndex;
    public boolean isFolder;
    public boolean isValid;
    public boolean isOpened;
    public boolean hasParent;
    public boolean isMarked;
    public int imageIndex;
    public int imageIndexOpened;
    public String frameName;
    public String comment;
    public String rollover;
    public boolean hasComment;
    
    public Node() {
        this.parentIndex = 0;
        this.isFolder = false;
        this.isValid = false;
        this.isOpened = false;
        this.hasParent = false;
        this.isMarked = false;
        this.imageIndex = 0;
        this.imageIndexOpened = 0;
        this.hasComment = false;
        this.text = new String("Nothing Here");
        try {
            this.url = new URL("http://www.nothing.com");
        }
        catch (MalformedURLException ex) {}
        this.isFolder = false;
        this.isValid = false;
        this.isOpened = false;
        this.hasParent = false;
        this.parentIndex = 0;
        this.imageIndex = 0;
        this.imageIndexOpened = 0;
        this.isMarked = false;
        this.frameName = new String("_blank");
        this.hasComment = false;
        this.rollover = null;
    }
    
    public Node(final String text, final int imageIndex, final int imageIndexOpened, final boolean isFolder, final boolean isOpened, final URL url, final String frameName, final boolean hasParent, final int parentIndex, final String comment, final String rollover) {
        this.parentIndex = 0;
        this.isFolder = false;
        this.isValid = false;
        this.isOpened = false;
        this.hasParent = false;
        this.isMarked = false;
        this.imageIndex = 0;
        this.imageIndexOpened = 0;
        this.hasComment = false;
        this.isValid = true;
        this.text = text;
        this.imageIndex = imageIndex;
        this.imageIndexOpened = imageIndexOpened;
        this.isFolder = isFolder;
        this.isOpened = isOpened;
        this.url = url;
        this.frameName = frameName;
        this.hasParent = hasParent;
        this.parentIndex = parentIndex;
        this.comment = comment;
        if (this.comment != null) {
            this.hasComment = true;
        }
        else {
            this.hasComment = false;
        }
        this.rollover = rollover;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

public class MessageElement
{
    public String text;
    public boolean isImage;
    public String imageID;
    
    public MessageElement() {
        this.isImage = false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("text: " + this.text);
        if (this.isImage) {
            sb.append(" it's an image.");
            sb.append(" image ID: " + this.imageID);
        }
        else {
            sb.append("it's not an image");
        }
        return sb.toString();
    }
}

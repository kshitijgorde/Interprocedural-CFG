// 
// Decompiled by Procyon v0.5.30
// 

public class mp3player
{
    public static void main(final String[] array) {
        final mp3player mp3player = new mp3player();
        mp3player.StartApp(array);
        mp3player.RunApp(array);
        mp3player.EndApp(array);
    }
    
    public void StartApp(final String[] array) {
    }
    
    public void RunApp(final String[] array) {
        this.CreateMainForm();
    }
    
    public void EndApp(final String[] array) {
    }
    
    public Mp3Window CreateMainForm() {
        final Mp3Window mp3Window = new Mp3Window();
        mp3Window.setMainForm(true);
        try {
            mp3Window.create();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return mp3Window;
    }
}

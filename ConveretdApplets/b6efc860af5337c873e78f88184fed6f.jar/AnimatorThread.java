// 
// Decompiled by Procyon v0.5.30
// 

class AnimatorThread extends Thread
{
    private boolean clicked;
    private int buttonNumber;
    
    AnimatorThread() {
        this.clicked = false;
    }
    
    public int button() {
        return this.buttonNumber;
    }
    
    public synchronized void buttonClicked(final int buttonNumber) {
        if (this.clicked) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        this.buttonNumber = buttonNumber;
        this.clicked = true;
        this.notifyAll();
    }
    
    public synchronized void run() {
        while (true) {
            if (!this.clicked) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
            Screen.answer.clearAnswer();
            Screen.panel.disableButtons();
            Screen.controls.start();
            Screen.panel.buttonClicked(this.buttonNumber);
            if (Screen.controls.isAborted()) {
                Screen.interaction.displayMessage("");
            }
            Screen.controls.stop();
            Screen.panel.enableButtons();
            Screen.canvas.repaint();
            this.clicked = false;
            this.notifyAll();
        }
    }
}

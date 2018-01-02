import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class WindmillControls implements ActionListener, AdjustmentListener, KeyListener, ItemListener, Runnable
{
    int noOfDisks;
    WindmillPanel parent;
    double alpha;
    int pivotAngle;
    Thread runner;
    String direction;
    int diskDiameter;
    int centerDiameter;
    boolean isSolved;
    boolean wasSolved;
    boolean dontDraw;
    boolean isScrambling;
    int delay;
    LinkedPoint mStart;
    LinkedPoint lStart;
    LinkedPoint rStart;
    LinkedPoint mStop;
    LinkedPoint lStop;
    LinkedPoint rStop;
    LinkedPoint lMarker;
    LinkedPoint rMarker;
    LinkedPoint hightMarker;
    LinkedPoint rOutMarker;
    LinkedPoint lOutMarker;
    LinkedPoint[] disk;
    LinkedPoint[] goal;
    
    WindmillControls(final WindmillPanel parent) {
        this.alpha = 0.0;
        this.pivotAngle = 30;
        this.isSolved = false;
        this.wasSolved = true;
        this.dontDraw = false;
        this.isScrambling = false;
        this.delay = 1;
        this.parent = parent;
        this.diskDiameter = parent.drawPanel.diskDiameter;
        this.centerDiameter = parent.drawPanel.centerDiameter;
        this.setChallenge();
    }
    
    void setChallenge() {
        this.runner = null;
        this.noOfDisks = this.parent.cChoice.getSelectedIndex() + 1;
        this.parent.turnLeft.setEnabled(true);
        this.parent.turnRight.setEnabled(true);
        this.parent.startScramble.setEnabled(true);
        this.parent.stopScramble.setEnabled(false);
        this.alpha = 0.0;
        this.isSolved = false;
        this.setLinkedPoints();
        this.setDisks();
        this.setDelay(this.parent.slider.getValue());
        this.parent.drawPanel.requestFocus();
        this.parent.repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.setChallenge();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.setDelay(adjustmentEvent.getValue());
    }
    
    void setDelay(final int n) {
        if (n == 0) {
            this.delay = 100;
        }
        if (n == 1) {
            this.delay = 50;
        }
        if (n == 2) {
            this.delay = 20;
        }
        if (n == 3) {
            this.delay = 10;
        }
        if (n == 4) {
            this.delay = 5;
        }
        if (n == 5) {
            this.delay = 2;
        }
        if (n == 6) {
            this.delay = 0;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final char c = keyEvent.getKeyChar();
        if (keyCode == 37 || keyCode == 76) {
            this.turn("left");
        }
        else if (keyCode == 39 || keyCode == 82) {
            this.turn("right");
        }
        else if (keyCode == 83) {
            this.isScrambling = !this.isScrambling;
            this.parent.startScramble.setEnabled(!this.isScrambling);
            this.parent.stopScramble.setEnabled(this.isScrambling);
            if (this.isScrambling) {
                this.animate();
            }
        }
    }
    
    void turn(final String direction) {
        if (this.runner == null) {
            this.direction = direction;
            this.animate();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.parent.reset) {
            this.setChallenge();
        }
        else if (source == this.parent.turnLeft) {
            this.turn("left");
        }
        else if (source == this.parent.turnRight) {
            this.turn("right");
        }
        else if (source == this.parent.startScramble) {
            this.isScrambling = true;
            this.parent.startScramble.setEnabled(false);
            this.parent.stopScramble.setEnabled(true);
            this.animate();
        }
        else if (source == this.parent.stopScramble) {
            this.parent.stopScramble.setEnabled(false);
            this.parent.startScramble.setEnabled(true);
            this.isScrambling = false;
        }
    }
    
    void setLinkedPoints() {
        this.mStop = new LinkedPoint(0.0, -(this.centerDiameter + this.diskDiameter) / 2);
        this.lStart = this.mStop.getLinkedPoint(-120.0);
        this.rStart = this.mStop.getLinkedPoint(120.0);
        this.mStop.left = this.lStart;
        this.mStop.right = this.rStart;
        this.lStart.left = this.rStart;
        this.lStart.right = this.mStop;
        this.rStart.left = this.mStop;
        this.rStart.right = this.lStart;
        this.mStart = this.mStop;
        this.lStop = this.lStart;
        this.rStop = this.rStart;
        for (int i = 2; i < this.diskDiameter * (this.noOfDisks - 1) + 2; i += 2) {
            final LinkedPoint mStart = new LinkedPoint(0.0, -i - (this.centerDiameter + this.diskDiameter) / 2);
            final LinkedPoint linkedPoint = mStart.getLinkedPoint(-120.0);
            final LinkedPoint linkedPoint2 = mStart.getLinkedPoint(120.0);
            this.lStop.next = linkedPoint;
            this.lStop = linkedPoint;
            this.rStop.next = linkedPoint2;
            this.rStop = linkedPoint2;
            mStart.next = this.mStart;
            this.mStart = mStart;
            this.mStart.left = this.lStop;
            this.mStart.right = this.rStop;
            this.lStop.left = this.rStop;
            this.lStop.right = this.mStart;
            this.rStop.left = this.mStart;
            this.rStop.right = this.lStop;
        }
        this.hightMarker = this.lStart;
        for (int j = -(this.centerDiameter + this.diskDiameter) / 2 + 2; j <= -(this.centerDiameter - this.diskDiameter) / 2; j += 2) {
            final LinkedPoint linkedPoint3 = new LinkedPoint(0.0, j);
            final LinkedPoint linkedPoint4 = linkedPoint3.getLinkedPoint(-120.0);
            final LinkedPoint linkedPoint5 = linkedPoint3.getLinkedPoint(120.0);
            linkedPoint4.next = this.lStart;
            linkedPoint5.next = this.rStart;
            this.lStart = linkedPoint4;
            this.rStart = linkedPoint5;
        }
        this.lOutMarker = this.lStart;
        this.rOutMarker = this.rStart;
        final LinkedPoint linkedPoint6 = new LinkedPoint(0.0, -(this.centerDiameter - this.diskDiameter) / 2);
        for (int k = 120; k > 4; k -= 5) {
            final LinkedPoint linkedPoint7 = linkedPoint6.getLinkedPoint(-k);
            final LinkedPoint linkedPoint8 = linkedPoint6.getLinkedPoint(k);
            linkedPoint7.next = this.lStart;
            linkedPoint8.next = this.rStart;
            this.lStart = linkedPoint7;
            this.rStart = linkedPoint8;
            if (k == 55) {
                this.rMarker = this.rStart;
                this.lMarker = this.lStart;
            }
        }
        for (int l = -(this.centerDiameter - this.diskDiameter) / 2 - 2; l > -(this.centerDiameter + this.diskDiameter) / 2; l -= 2) {
            final LinkedPoint lStart = new LinkedPoint(0.0, l);
            final LinkedPoint rStart = new LinkedPoint(0.0, l);
            lStart.next = this.lStart;
            rStart.next = this.rStart;
            this.lStart = lStart;
            this.rStart = rStart;
        }
        this.mStop.next = this.lStart;
    }
    
    void setDisks() {
        this.disk = new LinkedPoint[this.noOfDisks];
        this.goal = new LinkedPoint[this.noOfDisks];
        this.disk[0] = this.rStop;
        this.goal[0] = this.disk[0];
        for (int i = 1; i < this.disk.length; ++i) {
            this.disk[i] = this.rStart;
            while (this.disk[i].distance(this.disk[i - 1]) > this.diskDiameter + 0.1) {
                this.disk[i] = this.disk[i].next;
            }
            this.goal[i] = this.disk[i];
        }
    }
    
    void animate() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        Thread.currentThread();
        this.isSolved = false;
        this.parent.turnLeft.setEnabled(false);
        this.parent.turnRight.setEnabled(false);
    Label_0816:
        do {
            if (this.isScrambling) {
                if (Math.random() > 0.5) {
                    this.direction = "left";
                }
                else {
                    this.direction = "right";
                }
                if (LinkedPoint.allLeft(this.disk)) {
                    this.direction = "right";
                }
                if (LinkedPoint.allRight(this.disk)) {
                    this.direction = "left";
                }
            }
            double n;
            if (this.direction.equals("left")) {
                n = -3.0;
            }
            else {
                n = 3.0;
            }
            this.alpha = 0.0;
            while (this.alpha < 120.0 && this.alpha > -120.0) {
                if (this.runner == null) {
                    break Label_0816;
                }
                this.parent.repaint();
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex) {}
                this.alpha += n;
            }
            this.dontDraw = true;
            for (int i = 0; i < this.disk.length; ++i) {
                if (this.direction.equals("left")) {
                    this.disk[i] = this.disk[i].left;
                    this.goal[i] = this.goal[i].left;
                }
                if (this.direction.equals("right")) {
                    this.disk[i] = this.disk[i].right;
                    this.goal[i] = this.goal[i].right;
                }
            }
            this.alpha = 0.0;
            this.dontDraw = false;
            if (this.runner == null) {
                break;
            }
            this.parent.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
            boolean b = false;
            int n2 = 0;
            while (!b) {
                for (int j = 0; j < this.disk.length; ++j) {
                    if (this.disk[j] == this.lStart) {
                        this.mStop.next = null;
                    }
                    if (this.disk[j] == this.rStart) {
                        this.mStop.next = null;
                    }
                    if (this.disk[j] == this.lOutMarker) {
                        this.mStop.next = this.rStart;
                    }
                    if (this.disk[j] == this.rOutMarker) {
                        this.mStop.next = this.lStart;
                    }
                }
                final LinkedPoint[] disk = new LinkedPoint[this.disk.length];
                b = true;
                for (int k = 0; k < this.disk.length; ++k) {
                    disk[k] = this.disk[k].next;
                    if (disk[k] == null) {
                        disk[k] = this.disk[k];
                    }
                    else {
                        for (int l = 0; l < this.disk.length; ++l) {
                            final double distance = disk[k].distance(this.disk[l]);
                            if (l != k) {
                                if (distance < this.diskDiameter - 0.1) {
                                    disk[k] = this.disk[k];
                                }
                            }
                        }
                    }
                }
                this.pivotAngle += n2;
                if (this.pivotAngle > 30) {
                    this.pivotAngle = 30;
                    n2 = 0;
                }
                if (this.pivotAngle < -30) {
                    this.pivotAngle = -30;
                    n2 = 0;
                }
                for (int n3 = 0; n3 < this.disk.length; ++n3) {
                    if (this.disk[n3] == this.lMarker) {
                        n2 = -5;
                    }
                    if (this.disk[n3] == this.rMarker) {
                        n2 = 5;
                    }
                }
                if (this.hightMarker.below(this.disk)) {
                    n2 = 5;
                    this.mStop.next = this.lStart;
                }
                if (!LinkedPoint.equal(this.disk, disk) || this.pivotAngle != 30) {
                    b = false;
                }
                this.disk = disk;
                if (this.runner == null) {
                    break Label_0816;
                }
                this.parent.repaint();
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex3) {}
            }
        } while (this.isScrambling);
        if (this.runner != null) {
            this.checkIfSolved();
        }
        this.isScrambling = false;
        this.parent.turnLeft.setEnabled(true);
        this.parent.turnRight.setEnabled(true);
        this.parent.startScramble.setEnabled(true);
        this.parent.stopScramble.setEnabled(false);
        this.runner = null;
    }
    
    void checkIfSolved() {
        this.isSolved = false;
        if (LinkedPoint.equal(this.goal, this.disk)) {
            if (this.wasSolved) {
                this.isSolved = false;
            }
            else {
                this.isSolved = true;
                this.wasSolved = true;
            }
        }
        else {
            this.isSolved = false;
            this.wasSolved = false;
        }
    }
}

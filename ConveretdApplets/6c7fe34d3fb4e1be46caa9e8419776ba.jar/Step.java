// 
// Decompiled by Procyon v0.5.30
// 

public class Step
{
    int row;
    int col;
    int direction;
    int stepCnt;
    int nextStep;
    int prevStep;
    
    public Step() {
        this.row = 0;
        this.col = 0;
        this.direction = 0;
        this.stepCnt = 0;
        this.nextStep = -1;
        this.prevStep = -1;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

public class Branch
{
    int Relative;
    int Key;
    int Font;
    int ImageIndex;
    int Children;
    int ColorIndex;
    boolean Expanded;
    boolean HighLighted;
    boolean Heavy;
    String Text;
    String HLink;
    String Status;
    String Target;
    int Top;
    int Left;
    int Width;
    Branch NextBranch;
    
    public Branch() {
        this.Relative = -1;
        this.Key = 0;
        this.Font = 0;
        this.ImageIndex = -1;
        this.Children = 0;
        this.ColorIndex = 0;
        this.Expanded = false;
        this.HighLighted = false;
        this.Heavy = false;
        this.HLink = null;
        this.Status = null;
        this.Target = null;
        this.Top = 0;
        this.Left = 0;
        this.Width = 0;
        this.NextBranch = null;
        this.Top = 0;
    }
    
    public Branch(final int iRelative, final int iKey, final String sText) {
        this.Relative = -1;
        this.Key = 0;
        this.Font = 0;
        this.ImageIndex = -1;
        this.Children = 0;
        this.ColorIndex = 0;
        this.Expanded = false;
        this.HighLighted = false;
        this.Heavy = false;
        this.HLink = null;
        this.Status = null;
        this.Target = null;
        this.Top = 0;
        this.Left = 0;
        this.Width = 0;
        this.NextBranch = null;
        this.Relative = iRelative;
        this.Key = iKey;
        this.Text = sText;
    }
}

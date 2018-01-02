// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Global;
import java.awt.Frame;
import jagoclient.dialogs.Question;

class AskInsertQuestion extends Question
{
    public boolean Result;
    
    public AskInsertQuestion(final Frame frame) {
        super(frame, Global.resourceString("Change_Game_Tree_"), Global.resourceString("Change_Game_Tree"), frame, true);
        this.Result = false;
        this.show();
    }
    
    public void tell(final Question question, final Object o, final boolean result) {
        question.setVisible(false);
        question.dispose();
        this.Result = result;
    }
}

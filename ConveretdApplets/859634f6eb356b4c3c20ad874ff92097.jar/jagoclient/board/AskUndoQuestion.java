// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Global;
import java.awt.Frame;
import jagoclient.dialogs.Question;

class AskUndoQuestion extends Question
{
    public boolean Result;
    
    public AskUndoQuestion(final Frame frame) {
        super(frame, Global.resourceString("Delete_all_subsequent_moves_"), Global.resourceString("Delete_Tree"), frame, true);
        this.Result = false;
        this.show();
    }
    
    public void tell(final Question question, final Object o, final boolean result) {
        question.setVisible(false);
        question.dispose();
        this.Result = result;
    }
}

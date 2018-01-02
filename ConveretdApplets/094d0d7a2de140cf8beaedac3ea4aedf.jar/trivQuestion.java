// 
// Decompiled by Procyon v0.5.30
// 

public class trivQuestion
{
    private String question;
    private String[] answer;
    private int correctAnswer;
    
    public trivQuestion(final String question, final String s, final String s2, final String s3, final String s4, final int correctAnswer) {
        this.answer = new String[4];
        this.question = question;
        this.answer[0] = s;
        this.answer[1] = s2;
        this.answer[2] = s3;
        this.answer[3] = s4;
        this.correctAnswer = correctAnswer;
    }
    
    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
    
    public String getAnswer(final int n) {
        return this.answer[n];
    }
    
    public String getQuestion() {
        return this.question;
    }
}

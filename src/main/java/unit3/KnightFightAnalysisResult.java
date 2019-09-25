package unit3;

public class KnightFightAnalysisResult {
    private final KnightFight fight;
    private final int stepsToTakeBeforeShootFirst;
    private final int stepsToTakeBeforeShootSecond;

    public KnightFightAnalysisResult(KnightFight fight, int stepsToTakeBeforeShootFirst, int stepsToTakeBeforeShootSecond) {
        this.fight = fight;
        this.stepsToTakeBeforeShootFirst = stepsToTakeBeforeShootFirst;
        this.stepsToTakeBeforeShootSecond = stepsToTakeBeforeShootSecond;
    }

    public int getStepsToTakeBeforeShootFirst() {
        return stepsToTakeBeforeShootFirst;
    }

    public int getStepsToTakeBeforeShootSecond() {
        return stepsToTakeBeforeShootSecond;
    }
}

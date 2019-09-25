package unit3;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightFightAnalyzerTest {

    @Test
    public void calculateBestChances1() {
        final Knight me = new Knight("max", 0.2f);
        final Knight otherKnight = new Knight("vitalii", 0.1f);
        final KnightFight fight = new KnightFight(me, otherKnight, true);
        final KnightFightAnalyzer analyzer = new KnightFightAnalyzer(fight);

        final KnightFightAnalysisResult chances = analyzer.calculateBestChances();
        assertEquals(5, chances.getStepsToTakeBeforeShootFirst());
    }

    @Test
    public void calculateBestChances2() {
        final Knight me = new Knight("max", 0.3f);
        final Knight otherKnight = new Knight("vitalii", 0.1f);
        final KnightFight fight = new KnightFight(me, otherKnight, true);
        final KnightFightAnalyzer analyzer = new KnightFightAnalyzer(fight);

        final KnightFightAnalysisResult chances = analyzer.calculateBestChances();
        assertEquals(4, chances.getStepsToTakeBeforeShootFirst());
    }
}
package unit3;


public class KnightFightAnalyzer {
    private final KnightFight fight;

    public KnightFightAnalyzer(KnightFight fight) {
        this.fight = fight;
    }

    public KnightFightAnalysisResult calculateBestChances() {
        int stepsForFirst = getTheBestShotSteps(fight.getFirst(), fight.getSecond(), fight.isFirstGoesFirst());
        int stepsForSecond = getTheBestShotSteps(fight.getSecond(), fight.getFirst(), fight.isFirstGoesFirst());
        return new KnightFightAnalysisResult(fight, stepsForFirst, stepsForSecond);
    }

    /**
     * Оптимальным будет такой шаг, выстрел при котором, дает максимальное количество побед при большой выборке сражений
     * Возможны четыре варианта:
     * 1. У меня шанс попадания выше чем у соперника и я хожу первый: мне необходимо выстрелить когда моя вероятность попадания выше 50%
     * 2. У меня шанс попадания ниже чем у соперника и я хожу первый: мне необходимо выстрелить ДО того когда у противника наберется вероятность попадания выше 50%
     * 3. У меня шанс попадания выше чем у соперника и он ходит первый: мне необходимо выстрелить когда моя вероятность попадания выше 50%
     * 4. У меня шанс попадания ниже чем у соперника и он ходит первый: мне необходимо выстрелить ДО того когда у противника наберется вероятность попадания выше 50%
     */
    private int getTheBestShotSteps(Knight first, Knight second, boolean goingFirst) {
        Chance firstBestChance = getFirstOver50Chance(first);
        Chance secondBestChance = getFirstOver50Chance(second);

        if (goingFirst) {// I go first
            if (first.getChanceToHit() > second.getChanceToHit()) { //my chances are higher
                return firstBestChance.position;
            } else {//my chances are lower
                return secondBestChance.position;
            }
        } else {//he goes first
            if (first.getChanceToHit() > second.getChanceToHit()) {
                return firstBestChance.position;
            } else {
                return secondBestChance.position;
            }
        }
    }

    private Chance getFirstOver50Chance(Knight knight) {
        double chancesPerStep = (1 - knight.getChanceToHit()) / knight.getStepsToEnemy();
        double currentChances = knight.getChanceToHit();

        for (int i = 0; i < Utils.DEFAULT_STEPS_TO_ENEMY; i++) {
            currentChances = currentChances + chancesPerStep;
            if (currentChances >= 0.5) return new Chance(i, currentChances);
        }
        throw new RuntimeException("Can't calculate winnable chance");
    }

    private class Chance {
        int position;
        double chance;

        Chance(int position, double chance) {
            this.position = position;
            this.chance = chance;
        }
    }

}

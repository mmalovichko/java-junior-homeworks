package unit3;


import static unit3.Utils.DEFAULT_STEPS_TO_ENEMY;

public class Knight {
    private final String name;
    private boolean hasArrow;
    private int stepsToEnemy;
    private final float chanceToHit;

    public Knight(String name, float chanceToHit) {
        this.name = name;
        this.hasArrow = true;
        this.stepsToEnemy = DEFAULT_STEPS_TO_ENEMY;
        if (chanceToHit < 0.1f || chanceToHit > 0.3f) {
            throw new RuntimeException("Chance to hit must be in a range 0.1 - 0.3");
        }
        this.chanceToHit = chanceToHit;
    }

    public boolean shoot() {
        if (hasArrow) {
            return this.hasArrow = false;
        }
        return false;
    }

    public boolean takeStep() {
        if (stepsToEnemy > 0) {
            this.stepsToEnemy--;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public boolean isHasArrow() {
        return hasArrow;
    }

    public int getStepsToEnemy() {
        return stepsToEnemy;
    }

    public float getChanceToHit() {
        return chanceToHit;
    }
}

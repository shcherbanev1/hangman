package backend.academy.types;

public enum Complexity {
    EASY,
    MEDIUM,
    HARD;

    public static Complexity getComplexity(String complexity) {
        try {
            return Complexity.valueOf(complexity);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}

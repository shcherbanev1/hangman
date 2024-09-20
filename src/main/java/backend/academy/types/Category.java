package backend.academy.types;

public enum Category {

    ANIMALS,
    FOOD,
    TECHNOLOGY,
    SPORTS,
    TRAVEL;

    public static Category getCategory(String category) {
        try {
            return Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}

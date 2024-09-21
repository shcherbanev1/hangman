package backend.academy.entity;

import backend.academy.types.Category;
import backend.academy.types.Complexity;

public record Word(Complexity complexity, Category category, String word) {

    public String getInfo() {
        return "Category: " + category + " Complexity: " + complexity;
    }
}

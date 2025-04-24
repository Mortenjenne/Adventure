package game;

public class Food extends Item{
    private int nutrition;

    public Food(String description, String name, int nutrition) {
        super(description, name);
        this.nutrition = nutrition;
    }

    public int getNutrition(){
        return this.nutrition;
    }

}

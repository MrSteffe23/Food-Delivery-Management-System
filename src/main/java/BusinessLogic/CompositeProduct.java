package BusinessLogic;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> productsList = new ArrayList<>();

    public CompositeProduct() {
        super();
    }

    public CompositeProduct(String title) {
        super(title);
        productsList = new ArrayList<>();
    }

    public CompositeProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public void addBaseProduct(MenuItem item) {
        if (!productsList.contains(item))
            productsList.add(item);
        setCalories(computeCalories());
        setFat(computeFat());
        setPrice(computePrice());
        setProtein(computeProtein());
        setSodium(computeSodium());
        setRating(computeRating());
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    int computePrice() {
        int price = 0;
        for (MenuItem item : productsList)
            price += item.computePrice();
        return price;
    }

    @Override
    int computeSodium() {
        int Sodium = 0;
        for (MenuItem item : productsList)
            Sodium += item.computeSodium();
        return Sodium;
    }

    @Override
    int computeFat() {
        int Fat = 0;
        for (MenuItem item : productsList)
            Fat += item.computeFat();
        return Fat;
    }

    @Override
    int computeProtein() {
        int Protein = 0;
        for (MenuItem item : productsList)
            Protein += item.computeProtein();
        return Protein;
    }

    @Override
    int computeCalories() {
        int Calories = 0;
        for (MenuItem item : productsList)
            Calories += item.computeCalories();
        return Calories;
    }

    @Override
    double computeRating() {
        double Rating = 0.0;
        for (MenuItem item : productsList)
            Rating += item.computeRating();
        return Rating;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("The title: " + title);
        for (MenuItem item : productsList)
            s.append(" => { ").append(item.toString()).append(" }");
        s.append("\n");
        return s.toString();
    }
}

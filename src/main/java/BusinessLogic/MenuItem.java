package BusinessLogic;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    protected String title;
    protected double rating;
    protected int calories;
    protected int protein;
    protected int fat;
    protected int sodium;
    protected int price;

    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem(String title) {
        this.title = title;
    }

    public MenuItem() {

    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
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

    abstract int computePrice();

    abstract int computeSodium();

    abstract int computeFat();

    abstract int computeProtein();

    abstract int computeCalories();

    abstract double computeRating();


    @Override
    public boolean equals(Object obj) {
        assert obj != null;
        return title.equals(((MenuItem) obj).getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, calories, protein, fat, sodium, price);
    }
}

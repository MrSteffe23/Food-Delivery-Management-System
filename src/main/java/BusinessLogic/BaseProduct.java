package BusinessLogic;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public String toString() {
        return "Product: " + title + " => {rating: " + rating + "; calories: " + calories + "; protein: " + protein + "; fat: " + fat + "; sodium: " + sodium + "; price: " + price + "}\n";
    }

    @Override
    int computePrice() {
        return price;
    }

    @Override
    int computeSodium() {
        return sodium;
    }

    @Override
    int computeFat() {
        return fat;
    }

    @Override
    int computeProtein() {
        return protein;
    }

    @Override
    int computeCalories() {
        return calories;
    }

    @Override
    double computeRating() {
        return rating;
    }
}

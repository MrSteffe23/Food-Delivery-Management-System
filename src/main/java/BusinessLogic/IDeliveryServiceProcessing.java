package BusinessLogic;

import java.util.ArrayList;
import java.util.Date;

public interface IDeliveryServiceProcessing {
    /**
     * returns the ArrayList of Products
     *
     * @pre true
     * @post @nochange
     */
    ArrayList<Object[]> importProducts();

    /**
     * adds a new element in the ArrayList of Products
     *
     * @pre item!=null
     * @post menuItems.getSize() >= menuItems.getSize()@pre + 1
     */
    boolean addProduct(MenuItem item);

    /**
     * deletes an element from the ArrayList of Products
     *
     * @pre menuItems.getSize()!=0
     * @pre index >= 0 && index < menuItems.getSize()
     * @post menuItems.getSize() == menuItems.getSize()@pre - 1
     */
    void deleteProduct(int index);

    /**
     * modifies a product in the ArrayList of Products
     *
     * @pre item!=null
     * @pre menuItems.getSize()!=0
     * @pre index >= 0 && index < menuItems.getSize()
     * @post menuItems.getSize() == menuItems.getSize()@pre
     */
    void modifyProduct(MenuItem item, int index);

    /**
     * creates a nea composite product for the menu
     *
     * @pre menuItems.getSize()!=0
     * @post menuItems.getSize() == menuItems.getSize()@pre + 1
     */
    boolean createProduct(CompositeProduct compositeProduct);

    /**
     * generates the first report
     *
     * @pre start > 0 && start <= end
     * @post @nochange
     */
    void generateReportOne(int start, int end);

    /**
     * generates the second report
     *
     * @pre minim >= 0
     * @post @nochange
     */
    void generateReportTwo(int minim);

    /**
     * generates the third report
     *
     * @pre minimOrders >= 0 && miniOrderValue >= 0
     * @post @nochange
     */
    void generateReportThree(int minimOrders, int miniOrderValue);

    /**
     * generates the fourth report
     *
     * @pre day >= 1 && day <= 31
     * @post @nochange
     */
    void generateReportFour(int day);

    /**
     * searches for products by a title
     *
     * @pre menuItems!=null && title!=null
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchTitleProduct(ArrayList<MenuItem> menuItems, String title);

    /**
     * searches for products by rating
     *
     * @pre menuItems!=null && rating>0
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchRatingProduct(ArrayList<MenuItem> menuItems, double rating);

    /**
     * searches for products by calories
     *
     * @pre menuItems!=null && calories>0
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchCaloriesProduct(ArrayList<MenuItem> menuItems, int calories);

    /**
     * searches for products by protein
     *
     * @pre menuItems!=null && protein>0
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchProteinProduct(ArrayList<MenuItem> menuItems, int protein);

    /**
     * searches for products by fat
     *
     * @pre menuItems!=null && fat>0
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchFatProduct(ArrayList<MenuItem> menuItems, int fat);

    /**
     * searches for products by sodium
     *
     * @pre menuItems!=null && sodium>0
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchSodiumProduct(ArrayList<MenuItem> menuItems, int sodium);

    /**
     * searches for products by price
     *
     * @pre menuItems!=null && one>0 && two>0 && one<=two
     * @post menuItems.getSize() >= menuItems.getSize()@pre
     */
    ArrayList<MenuItem> searchPriceProduct(ArrayList<MenuItem> menuItems, int one, int two);

    /**
     * creates a new order
     *
     * @pre menuItems!=null && date!=null
     * @post @nochange
     */
    void createOrder(Date date, ArrayList<MenuItem> items);
}

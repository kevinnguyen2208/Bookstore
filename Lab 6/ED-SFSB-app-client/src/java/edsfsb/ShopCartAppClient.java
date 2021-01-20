package edsfsb;

import dto.CartItem;
import ed.sfsb.ShopCartBeanRemote;
import java.util.ArrayList;
import javax.ejb.EJB;
import java.util.Scanner;

/**
 *
 * @author Jye
 */
public class ShopCartAppClient {

    @EJB
    private static ShopCartBeanRemote shopCart;

    public ShopCartAppClient() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShopCartAppClient appClient = new ShopCartAppClient();
        // show that the shopCart is empty
        appClient.displayCart();
        // assuming they are selected by the user
        CartItem item1 = new CartItem("000001", "Intel Core i7 CPU", 349.99, 2);
        CartItem item2 = new CartItem("000002", "Intel SSD 512GB", 299.99, 3);
        
        while(true){
            System.out.println("Welcome to the Computer Hardware Store\n"
                    + "How can we help you today?\n"
                    + "\t1. Add an Item to your Shopping Cart\n"
                    + "\t2. Remove an Item from your Shopping Cart\n"
                    + "\t3. View your Shopping Cart\n\n"
                    + "Please make a selection (1-3)...");
            
            int fInput = in.nextInt();
            switch(fInput) {
                case 1:
                    System.out.println("On Sale Today\n"
                            + "\t1. Intel Core i7 CPU @ $349.99 // 2 Currently in Stock\n"
                            + "\t2. Intel SSD 512GB @299.99 // 3 Currently in Stock\n"
                            + "\t3. Back to Main Selection");
                    int sInput = in.nextInt();
                    switch(sInput) {
                        case 1:
                            appClient.addCart(item1);
                            break;
                        case 2:
                            appClient.addCart(item2);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Please make a valid selection between 1 - 3...");
                    }
                    break;
                case 2:
                    appClient.displayCart();
                    System.out.println("Please enter the ID of the item "
                            + "you wish to remove from the shopping cart.");
                    String itemID = in.next();
                    boolean result = shopCart.deleteCartItem(itemID);
                    if (result) {
                        System.out.println("Item successfully removed from the shopping cart.");
                    } else {
                        System.out.println("Item failed to be removed from the shopping cart.\n"
                                + "This could be due to an incorrect Item ID entered.");
                    }
                    break;
                case 3:
                    appClient.displayCart();
                    break;
                default:
                    System.out.println("Please make a valid selection between 1 - 3...");
            }
            
        }
    }

    public void addCart(CartItem item) {
        System.out.println("Adding item " + item.getDescription() + " to cart");
        if (shopCart.addCartItem(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been added.");
        } else {
            System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be added due to low stock.");
        }
    }

    public void displayCart() {
        ArrayList<CartItem> ciList = shopCart.getCart();
        if (ciList.isEmpty()) {
            System.out.println("The shopping cart is empty!");
            return;
        }
        System.out.println("Your shopping cart has the following items:");
        double total = 0.0;
        for (CartItem ci : ciList) {
            double unitPrice = ci.getUnitPrice();
            double quantity = ci.getQuantity();
            double subTotal = unitPrice * quantity;
            System.out.println("Item: " + ci.getDescription()
                    + "\tUnit Price: " + ci.getUnitPrice()
                    + "\tQuantity: " + ci.getQuantity()
                    + "\tSub-Total: " + subTotal);
            total = total + subTotal;
        }
        System.out.println("---");
        System.out.println("Total price: " + total);
        System.out.println("----End of Shopping Cart---");
    }

}
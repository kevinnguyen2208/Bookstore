package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Jye
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {

    private ArrayList<CartItem> cart;
    public ShopCartBean() {
        cart = new ArrayList<CartItem>();
    }

    @Override
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    private boolean add(CartItem cartItem) {
        boolean result = false;
        try 
        {
            result = cart.add(cartItem);
        } 
        catch (Exception ex) {}

        return result;
    }
    
    @Remove
    public void remove() {
        cart = null;
    }
    
    @Override
    public boolean addCartItem(CartItem cartItem) {
        // No database exists at the moment,
        // otherwise a database check of availability
        // of stock would be undertaken.
        
        try 
        {
            // Check if item is already in cart,
            // if it is, call updateCartItem instead
            // and return the result of the update.
            
            for (CartItem c : cart)
            {
                if (cartItem.getItemId().equals(c.getItemId())) { return updateCartItem(cartItem); }
            }

            // If the item is not found to be in the
            // shopping cart, add it normally.
            if (cartItem == null) { return false; }
            return add(cartItem);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        boolean result = false;
        
        try
        {
            for (CartItem c : cart)
            {
                if (cartItem.getItemId().equals(c.getItemId())) 
                {
                    cartItem.setQuantity(cartItem.getQuantity() + c.getQuantity());
                    cart.remove(c);
                    cart.add(cartItem);
                    result = true;
                }
            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        return result;
    }

    @Override
    public boolean deleteCartItem(String itemId) {
        boolean result = false;
        
        try
        {
            for (CartItem c : cart)
            {
                if (itemId.equals(c.getItemId())) 
                {
                    cart.remove(c);
                    result = true;
                }
            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        return result;
    }
}
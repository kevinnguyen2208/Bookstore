package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Jye
 */
@Remote
public interface ShopCartBeanRemote {

    ArrayList<CartItem> getCart();

    boolean addCartItem(CartItem cartItem);

    boolean updateCartItem(CartItem cartItem);

    boolean deleteCartItem(String itemId);
    
    //boolean add(CartItem cartItem);
    
}
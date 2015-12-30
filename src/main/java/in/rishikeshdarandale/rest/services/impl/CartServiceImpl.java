/**
 * Copyright [${year}] [Rishikesh Darandale]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.rishikeshdarandale.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.model.cart.CartItem;
import in.rishikeshdarandale.rest.redis.repository.cart.CartRepository;
import in.rishikeshdarandale.rest.resources.errorhandling.CartFullException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemNotFoundException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemOutOfStockException;
import in.rishikeshdarandale.rest.services.CartService;
import in.rishikeshdarandale.rest.services.ItemService;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemService itemSerice;

    @Override
    public Cart create() {
        Cart cart = new Cart();
        // store the cart in redis database
        cartRepository.put(cart);
        return cart;
    }

    @Override
    public Cart get(String id) {
        Cart cart = null;
        cart = cartRepository.get(id);
        return cart;
    }

    @Override
    public void delete(String id) {
        cartRepository.delete(id);
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addItem(String id, CartItem item) throws ItemOutOfStockException, CartFullException {
        Cart cart = get(id);
        if (cart != null) {
            if (cart.getItemCount() < Cart.MAX_ITEMS) {
                if (itemSerice.isItemInStock((item.getSkuId()))) {
                    if (cart.getItems().contains(item)) {
                        cart.getItems().remove(item);
                    }
                    cart.getItems().add(item);
                    //update in database
                    cartRepository.put(cart);
                } else {
                    throw new ItemOutOfStockException("Currebtly item " + item.getSkuId() + " is out of stock");
                }
            } else {
                throw new CartFullException("Cart " + cart.getId() + " is full");
            }
        }
        return cart;
    }

    @Override
    public boolean exists(String id, String skuId) {
        boolean exists = false;
        Cart cart = get(id);
        if (cart != null) {
            CartItem item = new CartItem(skuId);
            return cart.isItemExists(item);
        }
        return exists;
    }

    @Override
    public Cart updateItem(String id, CartItem item) throws ItemOutOfStockException, CartFullException{
        return cartRepository.exists(id) ? addItem(id, item) : null;
    }

    @Override
    public Cart removeItem(String id, CartItem item) throws ItemNotFoundException {
        Cart cart = get(id);
        if (cart != null) {
            if (cart.isItemExists(item)) {
                cart.removeItem(item);
                //update in database
                cartRepository.put(cart);
            } else {
                throw new ItemNotFoundException("Item " + item.getSkuId() + " not found");
            }
        }
        return cart;
    }
}

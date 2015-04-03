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

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.model.cart.Item;
import in.rishikeshdarandale.rest.redis.repository.cart.CartRepository;
import in.rishikeshdarandale.rest.services.CartService;

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart create() {
        Cart cart = new Cart();
        // store the cart in redis database
        cartRepository.put(cart);
        return cart;
    }

    @Override
    public Cart get(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cart addItem(String id, Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cart updateItem(String id, Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cart removeItem(String id, Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}

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
package in.rishikeshdarandale.rest.services;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.model.cart.CartItem;
import in.rishikeshdarandale.rest.resources.errorhandling.CartFullException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemNotFoundException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemOutOfStockException;

public interface CartService {
    Cart create();
    Cart get(String id);
    void delete(String id);
    Cart addItem(String id, CartItem item) throws ItemOutOfStockException, CartFullException;
    boolean exists(String id, String skuId);
    Cart updateItem(String id, CartItem item) throws ItemOutOfStockException, CartFullException;
    Cart removeItem(String id, CartItem item) throws ItemNotFoundException;
}

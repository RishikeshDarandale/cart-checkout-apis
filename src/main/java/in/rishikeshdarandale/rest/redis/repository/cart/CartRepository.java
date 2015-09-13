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
package in.rishikeshdarandale.rest.redis.repository.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.redis.repository.Repository;

@Component
public class CartRepository implements Repository<String, Cart> {

    @Autowired
    private RedisTemplate<String,Cart> redisTemplate;

    public RedisTemplate<String, Cart> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void put(Cart cart) {
        redisTemplate.opsForValue().set(cart.getKey(), cart);;
    }

    @Override
    public Cart get(String key) {
        return (Cart) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    @Override
    public List<Cart> getAll() {
        List<Cart> carts = new ArrayList<Cart>();
        for (String cartId : redisTemplate.opsForValue().getOperations().keys(Cart.OBJECT_KEY+":*") ){
            carts.add(this.get(cartId));
        }
        return carts;
    }

    @Override
    public Boolean exists(String key) {
        return null != redisTemplate.opsForValue().get(key) ? Boolean.TRUE : Boolean.FALSE;
    }
}

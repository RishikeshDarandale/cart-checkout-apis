package in.rishikeshdarandale.rest.redis.repository.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.redis.repository.Repository;

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
}

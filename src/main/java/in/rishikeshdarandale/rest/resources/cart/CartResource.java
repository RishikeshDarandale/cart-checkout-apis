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
package in.rishikeshdarandale.rest.resources.cart;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.model.cart.CartItem;
import in.rishikeshdarandale.rest.resources.AbstractResource;
import in.rishikeshdarandale.rest.resources.errorhandling.CartFullException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemNotFoundException;
import in.rishikeshdarandale.rest.resources.errorhandling.ItemOutOfStockException;
import in.rishikeshdarandale.rest.services.CartService;

/**
 * Cart resource
 */
@Component
@Path("/carts")
public class CartResource extends AbstractResource {

    @Autowired
    private CartService cartService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create() {
        String id = cartService.create().getId();
        return Response.created(URI.create("http://localhost:8080/carts/" + id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cartId}")
    public Response get(@PathParam("cartId")  String cartId) {
        Cart cart = cartService.get(cartId);
        if (cart != null) {
            return foundOne(cart);
        }else {
            return foundNone();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cartId}")
    public Response delete(@PathParam("cartId")  String cartId) {
        Cart cart = cartService.get(cartId);
        if (cart != null) {
            cartService.delete(cart.getId());
            return ok();
        }else {
            return foundNone();
        }
    }

    @POST
    @Path("{cartId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("cartId") String cartId, CartItem item)
            throws ItemOutOfStockException, CartFullException {
        if (cartId != null && item != null ) {
            Cart cart = cartService.get(cartId);
            if (cart != null) {
                cartService.addItem(cartId, item);
                return ok();
            }else {
                return foundNone();
            }
        } else {
            return forbidden();
        }
    }

    @PUT
    @Path("{cartId}/items/{skuId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("cartId") String cartId, CartItem item, @PathParam("skuId") String skuId)
            throws ItemOutOfStockException, CartFullException {
        if (cartId != null && item != null && skuId != null) {
            Cart cart = cartService.get(cartId);
            if (cart != null && cartService.exists(cartId, skuId)) {
                cartService.updateItem(cartId, item);
                return ok();
            }else {
                return foundNone();
            }
        } else {
            return forbidden();
        }
    }

    @DELETE
    @Path("{cartId}/items/{skuId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("cartId") String cartId, @PathParam("skuId") String skuId)
            throws ItemNotFoundException {
        if (cartId != null && skuId != null ) {
            Cart cart = cartService.get(cartId);
            if (cart != null && cartService.exists(cartId, skuId)) {
                cartService.removeItem(cartId, new CartItem(skuId));
                return ok();
            }else {
                return foundNone();
            }
        } else {
            return forbidden();
        }
    }
}

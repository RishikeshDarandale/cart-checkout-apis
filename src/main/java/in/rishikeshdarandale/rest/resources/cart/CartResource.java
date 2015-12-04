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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.media.jfxmedia.Media;

import in.rishikeshdarandale.rest.model.cart.Cart;
import in.rishikeshdarandale.rest.resources.AbstractResource;
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
    public Response get(String cartId) {
        Cart cart = cartService.get(cartId);
        if (cart != null) {
            return created(cart.getId(), cart);
        }else {
            return foundNone();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(String cartId) {
        Cart cart = cartService.get(cartId);
        if (cart != null) {
            return foundOne(cart);
        }else {
            return foundNone();
        }
    }
}

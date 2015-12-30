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
package in.rishikeshdarandale.rest.resources.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CartFullException extends Exception implements ExceptionMapper<CartFullException>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public CartFullException() {
        super("Cart is Full");
    }

    public CartFullException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(CartFullException exception) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new ErrorMessage(Response.Status.FORBIDDEN.getStatusCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}

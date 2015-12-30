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
public class ItemOutOfStockException extends Exception implements ExceptionMapper<ItemOutOfStockException> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ItemOutOfStockException() {
        super("Item is Out of stock currently.");
    }

    public ItemOutOfStockException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(ItemOutOfStockException exception) {
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(new ErrorMessage(Response.Status.NOT_ACCEPTABLE.getStatusCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}

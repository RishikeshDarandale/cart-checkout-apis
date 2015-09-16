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
package in.rishikeshdarandale.rest.resources;

import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public abstract class AbstractResource {

    /**
     * Returns a 201: "Created" response.
     * Adds the response header attribute Location with the URL formulated from the created resource id.
     *
     * @param id non {@code null} created resource id
     * @param values   a list of URI template parameter values
     *
     * @return created response
     */
    protected Response created(String id, Object... values) {
        return created(getClass(), id, values);
    }

    /**
     * Returns a 201: "Created" response.
     * Adds the response header attribute Location with the URL formulated from the specified resource method name and
     * created resource id.
     *
     * @param method non {@code null} resource method name
     * @param id     non {@code null} created resource id
     * @param values   a list of URI template parameter values
     *
     * @return created response
     */
    protected Response created(String method, String id, Object... values) {
        return created(getClass(), method, id, values);
    }

    /**
     * Returns a 201: "Created" response.
     * Adds the response header attribute Location with the URL formulated from the specified resource class and
     * created resource id.
     *
     * @param resource non {@code null} resource class
     * @param id       non {@code null} created resource id
     * @param values   a list of URI template parameter values
     *
     * @return created response
     */
    protected Response created(Class<?> resource, String id, Object... values) {
        return Response.created(UriBuilder.fromResource(resource).path(id).build(values)).build();
    }

    /**
     * Returns a 201: "Created" response.
     * Adds the response header attribute Location with the URL formulated from the specified resource class, resource
     * method name and created resource id.
     *
     * @param resource non {@code null} resource class
     * @param method   non {@code null} resource method name
     * @param id       non {@code null} created resource id
     * @param values   a list of URI template parameter values
     *
     * @return created response
     */
    protected Response created(Class<?> resource, String method, String id, Object... values) {
        return Response.created(UriBuilder.fromMethod(resource, method).path(id).build(values)).build();
    }

    /**
     * Returns a 200: "OK" response.
     *
     * @return created response
     */
    protected Response ok() {
        return Response.ok().build();
    }

    /**
     * Returns a 200: "OK" response.
     * Adds the found entity as JSON to the response body.
     *
     * @return created response
     */
    protected Response foundOne(Object entity) {
        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Returns a 200: "OK" response.
     * Adds the response header attribute X-PD-Entity-Count with the found entities count and adds the found entities as
     * JSON to the response body.
     *
     * @return created response
     */
    protected Response foundMany(List<?> entities) {
        return Response
                .ok(entities, MediaType.APPLICATION_JSON)
                .header("X-PD-Entity-Count", entities == null ? 0 : entities.size())
                .build();
    }

    /**
     * Returns a 404: "Not Found" response.
     *
     * @return created response
     */
    protected Response foundNone() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Returns a 403: "Forbidden" response.
     *
     * @return created response
     */
    protected Response forbidden() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * Returns a 400: "Bad Request" response.
     *
     * @return created response
     */
    protected Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Returns a 400: "Bad Request" response with custom message.
     *
     * @param message custom message
     *
     * @return created response
     */
    protected Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

    /**
     * Returns a 500: "Internal Server Error" response.
     *
     * @return created response
     */
    protected Response internalServerError() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}

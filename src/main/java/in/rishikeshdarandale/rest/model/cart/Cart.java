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
package in.rishikeshdarandale.rest.model.cart;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.rishikeshdarandale.rest.model.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart implements BaseModel {

    /**
     * serial version id
     */
    private static final long serialVersionUID = 1L;

    private String id;
    public static final Integer MAX_ITEMS = 20;
    public static final String OBJECT_KEY = "CART";
    private Set<CartItem> items = new HashSet<CartItem>();

    public Cart() {
        super();
        this.setId(UUID.randomUUID().toString());
    }
    public Boolean isItemExists(CartItem item) {
        Boolean exists = Boolean.FALSE;
        if(this.getItems() != null && !this.getItems().isEmpty()) {
            exists = this.getItems().contains(item);
        }
        return exists;
    }
    public void removeItem(CartItem item) {
        if (isItemExists(item)) {
            this.getItems().remove(item);
        }
    }
    public Double getCartTotal() {
        Double total = 0d;
        for(CartItem t: this.getItems()) {
            total += t.getItemTotal();
        }
        return total;
    }
    public Integer getItemCount() {
        return this.getItems() != null ? this.getItems().size() : 0;
    }
    @Override
    public String getKey() {
        return OBJECT_KEY + ":" + this.getId();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Set<CartItem> getItems() {
        return items;
    }
    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
}

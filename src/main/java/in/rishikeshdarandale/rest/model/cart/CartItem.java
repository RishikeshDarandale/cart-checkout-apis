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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItem implements Serializable {

    /**
     * serial version id
     */
    private static final long serialVersionUID = 1L;
    private String skuId;
    private String description;
    private Integer quantity;
    private Double price;

    public CartItem() {}

    public CartItem(String skuId) {
        super();
        this.skuId = skuId;
    }

    public Double getItemTotal() {
        return this.quantity * price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((skuId == null) ? 0 : skuId.hashCode());
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
        CartItem other = (CartItem) obj;
        if (skuId == null) {
            if (other.skuId != null)
                return false;
        } else if (!skuId.equals(other.skuId))
            return false;
        return true;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}

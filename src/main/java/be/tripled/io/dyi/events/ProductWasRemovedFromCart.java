package be.tripled.io.dyi.events; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class ProductWasRemovedFromCart implements Event {
public final String customerId;
public final String cartId;
public final String productId;
private ProductWasRemovedFromCart(ProductWasRemovedFromCart.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
productId = builder.productId;
}public static ProductWasRemovedFromCart.Builder newBuilder() {return new ProductWasRemovedFromCart.Builder();   };public static final class Builder {
private String customerId;public ProductWasRemovedFromCart.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public ProductWasRemovedFromCart.Builder withcartId(String val) {cartId= val;return this;}private String productId;public ProductWasRemovedFromCart.Builder withproductId(String val) {productId= val;return this;}public ProductWasRemovedFromCart build(){ return new ProductWasRemovedFromCart(this); }}} 

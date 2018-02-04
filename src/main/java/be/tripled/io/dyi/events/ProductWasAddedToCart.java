package be.tripled.io.dyi.events; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class ProductWasAddedToCart implements Event {
public final String customerId;
public final String cartId;
public final String productId;
private ProductWasAddedToCart(ProductWasAddedToCart.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
productId = builder.productId;
}public static ProductWasAddedToCart.Builder newBuilder() {return new ProductWasAddedToCart.Builder();   };public static final class Builder {
private String customerId;public ProductWasAddedToCart.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public ProductWasAddedToCart.Builder withcartId(String val) {cartId= val;return this;}private String productId;public ProductWasAddedToCart.Builder withproductId(String val) {productId= val;return this;}public ProductWasAddedToCart build(){ return new ProductWasAddedToCart(this); }}} 

package be.tripled.io.dyi.events; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class CustomerAbandonedCart implements Event {
public final String customerId;
public final String cartId;
private CustomerAbandonedCart(CustomerAbandonedCart.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
}public static CustomerAbandonedCart.Builder newBuilder() {return new CustomerAbandonedCart.Builder();   };public static final class Builder {
private String customerId;public CustomerAbandonedCart.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public CustomerAbandonedCart.Builder withcartId(String val) {cartId= val;return this;}public CustomerAbandonedCart build(){ return new CustomerAbandonedCart(this); }}} 

package be.tripled.io.dyi.events; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class CustomerStartedShopping implements Event {
public final String customerId;
public final String cartId;
private CustomerStartedShopping(CustomerStartedShopping.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
}public static CustomerStartedShopping.Builder newBuilder() {return new CustomerStartedShopping.Builder();   };public static final class Builder {
private String customerId;public CustomerStartedShopping.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public CustomerStartedShopping.Builder withcartId(String val) {cartId= val;return this;}public CustomerStartedShopping build(){ return new CustomerStartedShopping(this); }}} 

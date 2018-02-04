package be.tripled.io.dyi.commands; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class RemoveProductFromCart implements Command {
public final String customerId;
public final String cartId;
public final String productId;
private RemoveProductFromCart(RemoveProductFromCart.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
productId = builder.productId;
}public static RemoveProductFromCart.Builder newBuilder() {return new RemoveProductFromCart.Builder();   };public static final class Builder {
private String customerId;public RemoveProductFromCart.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public RemoveProductFromCart.Builder withcartId(String val) {cartId= val;return this;}private String productId;public RemoveProductFromCart.Builder withproductId(String val) {productId= val;return this;}public RemoveProductFromCart build(){ return new RemoveProductFromCart(this); }}} 

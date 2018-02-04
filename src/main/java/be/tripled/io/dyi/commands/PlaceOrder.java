package be.tripled.io.dyi.commands; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class PlaceOrder implements Command {
public final String customerId;
public final String cartId;
private PlaceOrder(PlaceOrder.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
}public static PlaceOrder.Builder newBuilder() {return new PlaceOrder.Builder();   };public static final class Builder {
private String customerId;public PlaceOrder.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public PlaceOrder.Builder withcartId(String val) {cartId= val;return this;}public PlaceOrder build(){ return new PlaceOrder(this); }}} 

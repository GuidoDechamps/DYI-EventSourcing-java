package be.tripled.io.dyi.commands; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class AbandonOrder implements Command {
public final String customerId;
public final String cartId;
private AbandonOrder(AbandonOrder.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
}public static AbandonOrder.Builder newBuilder() {return new AbandonOrder.Builder();   };public static final class Builder {
private String customerId;public AbandonOrder.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public AbandonOrder.Builder withcartId(String val) {cartId= val;return this;}public AbandonOrder build(){ return new AbandonOrder(this); }}} 

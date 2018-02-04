package be.tripled.io.dyi.events; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class CustomerPlacedOrder implements Event {
public final String customerId;
public final String orderId;
private CustomerPlacedOrder(CustomerPlacedOrder.Builder builder) {customerId = builder.customerId;
orderId = builder.orderId;
}public static CustomerPlacedOrder.Builder newBuilder() {return new CustomerPlacedOrder.Builder();   };public static final class Builder {
private String customerId;public CustomerPlacedOrder.Builder withcustomerId(String val) {customerId= val;return this;}private String orderId;public CustomerPlacedOrder.Builder withorderId(String val) {orderId= val;return this;}public CustomerPlacedOrder build(){ return new CustomerPlacedOrder(this); }}} 

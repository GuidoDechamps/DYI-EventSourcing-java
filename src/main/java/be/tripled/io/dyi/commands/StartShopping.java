package be.tripled.io.dyi.commands; 
/** Generated class from dsl file. Do not add own logic. 
*/
public class StartShopping implements Command {
public final String customerId;
public final String cartId;
public final String startTime;
private StartShopping(StartShopping.Builder builder) {customerId = builder.customerId;
cartId = builder.cartId;
startTime = builder.startTime;
}public static StartShopping.Builder newBuilder() {return new StartShopping.Builder();   };public static final class Builder {
private String customerId;public StartShopping.Builder withcustomerId(String val) {customerId= val;return this;}private String cartId;public StartShopping.Builder withcartId(String val) {cartId= val;return this;}private String startTime;public StartShopping.Builder withstartTime(String val) {startTime= val;return this;}public StartShopping build(){ return new StartShopping(this); }}} 

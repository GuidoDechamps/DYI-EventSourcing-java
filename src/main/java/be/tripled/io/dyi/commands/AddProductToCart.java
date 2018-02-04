package be.tripled.io.dyi.commands;

/**
 * Generated class from dsl file. Do not add own logic.
 */
public class AddProductToCart implements Command {
    public final String customerId;
    public final String cartId;
    public final String sku;
    public final String addTime;

    private AddProductToCart(AddProductToCart.Builder builder) {
        customerId = builder.customerId;
        cartId = builder.cartId;
        sku = builder.sku;
        addTime = builder.addTime;
    }

    public static AddProductToCart.Builder newBuilder() {
        return new AddProductToCart.Builder();
    }

    ;

    public static final class Builder {
        private String customerId;
        private String cartId;
        private String sku;
        private String addTime;

        public AddProductToCart.Builder withcustomerId(String val) {
            customerId = val;
            return this;
        }

        public AddProductToCart.Builder withcartId(String val) {
            cartId = val;
            return this;
        }

        public AddProductToCart.Builder withsku(String val) {
            sku = val;
            return this;
        }

        public AddProductToCart.Builder withaddTime(String val) {
            addTime = val;
            return this;
        }

        public AddProductToCart build() {
            return new AddProductToCart(this);
        }
    }
}

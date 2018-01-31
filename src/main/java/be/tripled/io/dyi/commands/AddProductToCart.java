package be.tripled.io.dyi.commands;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class AddProductToCart {

    public final String customerId;
    public final String cartId;
    public final String sku;
    public final String addTime;

    private AddProductToCart(Builder builder) {
        customerId = builder.customerId;
        cartId = builder.cartId;
        sku = builder.sku;
        addTime = builder.addTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String cartId;
        private String sku;
        private String addTime;

        private Builder() {
        }

        public Builder withCustomerId(String val) {
            customerId = val;
            return this;
        }

        public Builder withCartId(String val) {
            cartId = val;
            return this;
        }

        public Builder withSku(String val) {
            sku = val;
            return this;
        }

        public Builder withAddTime(String val) {
            addTime = val;
            return this;
        }

        public AddProductToCart build() {
            return new AddProductToCart(this);
        }
    }
}

package be.tripled.io.dyi.commands;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class RemoveProductFromCart {
    public final String customerId;
    public final String cartId;
    public final String productId;

    private RemoveProductFromCart(Builder builder) {
        customerId = builder.customerId;
        cartId = builder.cartId;
        productId = builder.productId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String cartId;
        private String productId;

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

        public Builder withProductId(String val) {
            productId = val;
            return this;
        }

        public RemoveProductFromCart build() {
            return new RemoveProductFromCart(this);
        }
    }
}

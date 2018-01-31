package be.tripled.io.dyi.commands;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class AbandonOrder {
    public final String customerId;
    public final String cartId;

    private AbandonOrder(Builder builder) {
        customerId = builder.customerId;
        cartId = builder.cartId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String cartId;

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

        public AbandonOrder build() {
            return new AbandonOrder(this);
        }
    }
}

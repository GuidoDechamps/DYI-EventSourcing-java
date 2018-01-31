package be.tripled.io.dyi.commands;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class StartShopping {
    public final String customerId;
    public final String cartId;
    public final String startTime;

    private StartShopping(Builder builder) {
        customerId = builder.customerId;
        cartId = builder.cartId;
        startTime = builder.startTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String cartId;
        private String startTime;

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

        public Builder withStartTime(String val) {
            startTime = val;
            return this;
        }

        public StartShopping build() {
            return new StartShopping(this);
        }
    }
}

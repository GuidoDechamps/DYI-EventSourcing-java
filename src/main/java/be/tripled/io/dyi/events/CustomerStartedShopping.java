package be.tripled.io.dyi.events;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class CustomerStartedShopping {
    public final String customerId;
    public final String cartId;

    private CustomerStartedShopping(Builder builder) {
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

        public CustomerStartedShopping build() {
            return new CustomerStartedShopping(this);
        }
    }
}

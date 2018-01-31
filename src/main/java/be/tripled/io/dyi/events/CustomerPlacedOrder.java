package be.tripled.io.dyi.events;

/**
 * Generated class from dsl file. Do not add own logic.
 * Generate Builders through IDE.
 */
public class CustomerPlacedOrder {

    public final String customerId;
    public final String orderId;

    private CustomerPlacedOrder(Builder builder) {
        customerId = builder.customerId;
        orderId = builder.orderId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;
        private String orderId;

        private Builder() {
        }

        public Builder withCustomerId(String val) {
            customerId = val;
            return this;
        }

        public Builder withOrderId(String val) {
            orderId = val;
            return this;
        }

        public CustomerPlacedOrder build() {
            return new CustomerPlacedOrder(this);
        }
    }
}

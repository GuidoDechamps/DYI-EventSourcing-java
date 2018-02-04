package be.tripled.io.dyi.usecases;

import be.tripled.io.dyi.ObjectMother;
import be.tripled.io.dyi.commands.AddProductToCart;
import be.tripled.io.dyi.commands.PlaceOrder;
import be.tripled.io.dyi.commands.RemoveProductFromCart;
import be.tripled.io.dyi.events.CustomerPlacedOrder;
import be.tripled.io.dyi.events.CustomerStartedShopping;
import be.tripled.io.dyi.events.ProductWasAddedToCart;
import be.tripled.io.dyi.events.ProductWasRemovedFromCart;
import org.junit.jupiter.api.Test;

public class AddItemsToShoppingBasketTest {
    private final ObjectMother mother= new ObjectMother();
    @Test
    public void startShopping() {
        StubSpecification.create()
                         .when(mother.startShopping())
                         .then(CustomerStartedShopping.newBuilder()
                                                      .build());
    }

    @Test
    public void addProductToCart() {
        StubSpecification.create()
                         .given(CustomerStartedShopping.newBuilder()
                                                       .build())
                         .when(AddProductToCart.newBuilder()
                                               .build())
                         .then(ProductWasAddedToCart.newBuilder()
                                                    .build());
    }

    @Test
    public void removeProductFromCart() {
        StubSpecification.create()
                         .given(CustomerStartedShopping.newBuilder()
                                                       .build(), ProductWasAddedToCart.newBuilder()
                                                                                      .build())
                         .when(RemoveProductFromCart.newBuilder()
                                                    .build())
                         .then(ProductWasRemovedFromCart.newBuilder()
                                                        .build());
    }

    @Test
    public void placeOrder() {
        StubSpecification.create()
                         .given(CustomerStartedShopping.newBuilder()
                                                       .build(), ProductWasAddedToCart.newBuilder()
                                                                                      .build())
                         .when(PlaceOrder.newBuilder()
                                         .build())
                         .then(CustomerPlacedOrder.newBuilder()
                                                  .build());
    }
}

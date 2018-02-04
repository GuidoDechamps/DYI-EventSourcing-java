package be.tripled.io.dyi;

import be.tripled.io.dyi.commands.Command;
import be.tripled.io.dyi.commands.StartShopping;

public class ObjectMother {

    public Command startShopping(){
        return StartShopping.newBuilder()
                            .build();
    }
}

package be.tripled.io.dyi.usecase;

import be.tripled.io.dyi.commands.Command;
import be.tripled.io.dyi.events.Event;

import java.util.Collection;

class StubSpecification implements Specification {

    private StubSpecification() {
    }

    public static Specification create() {
        return new StubSpecification();
    }

    @Override
    public Specification given(Event... events) {
        return this;
    }

    @Override
    public Specification given(Collection<Event> events) {
        return this;
    }

    @Override
    public Specification when(Command command) {
        return this;
    }

    @Override
    public void then(Event... events) {

    }

    @Override
    public void then(Collection<Event> events) {

    }
}

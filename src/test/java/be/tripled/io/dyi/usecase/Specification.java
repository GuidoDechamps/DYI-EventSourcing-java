package be.tripled.io.dyi.usecase;

import be.tripled.io.dyi.commands.Command;
import be.tripled.io.dyi.events.Event;

import java.util.Collection;

public interface Specification {

    Specification given(Event... events);

    Specification given(Collection<Event> events);

    Specification when(Command command);

    void then(Event... events);

    void then(Collection<Event> events);
}
package com.aks007;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class StateMachineTransition<State extends Enum<State>, Event extends Enum<Event>> {

    private State source;

    private Event event;

    private State target;

    private StateMachineAction action;

    private StateMachineGuard guard;

}

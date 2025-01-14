package com.aks007;

import lombok.Getter;

import java.util.*;

public abstract class BaseStateMachine<
        ChildClass,
        State extends Enum<State>,
        Event extends Enum<Event>,
        Entity extends com.aks007.StateBaseEntity<State, Entity>
        > {

    protected final String ATTR_DEFAULT = "default";

    @Getter
    protected final Entity entity;

    protected Map<String, Object> extendedState = new HashMap<>();

    public BaseStateMachine(Entity entity) {

        this.entity = entity;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getDefault() {

        if (this.extendedState == null) return null;
        return (T) this.extendedState.get(ATTR_DEFAULT);
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String key) {

        if (this.extendedState == null) return null;
        return (T) this.extendedState.get(key);
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String key, Class<T> clazz) {

        if (this.extendedState == null) return null;
        return (T) this.extendedState.get(key);
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(Class<T> clazz) {

        if (this.extendedState == null) return null;
        return (T) this.extendedState.get(clazz.getSimpleName());
    }

    protected <T> void set(Class<T> clazz, T data) {

        if (this.extendedState == null) this.extendedState = new HashMap<>();
        this.extendedState.put(clazz.getSimpleName(), data);
    }

    protected Map<String, Object> getExtendedState() {

        return extendedState;
    }

    protected abstract ChildClass getThis();

    public void sendEvent(Event event) throws Exception {

        sendEvent(event, null);
    }

    public void sendEvent(Event event, Map<String, Object> attrs) throws Exception {

        if (getTransitionRules() == null || getTransitionRules().isEmpty())
            throw new RuntimeException("No transition rules defined");

        Optional<StateMachineTransition<State, Event>> state = getTransitionRules().stream().filter(i -> Objects.equals(i.event(), event) && Objects.equals(i.source(), this.entity.getStatus())).findFirst();

        if (state.isEmpty()) throw new WrongFormStepException(event.toString());

        // Update attrs
        if (attrs != null)
            this.extendedState.putAll(attrs);

        if (state.get().action() != null)
            state.get().action().run();

        // Guard check
        if (state.get().guard() != null && !state.get().guard().run())
            return;

        // Finally, update status, after all the verification and process are done
        entity.setStatus(state.get().target());
    }

    protected abstract LinkedList<StateMachineTransition<State, Event>> getTransitionRules();

    public void sendEvent(Event event, Object object) throws Exception {

        sendEvent(event, Map.of(ATTR_DEFAULT, object));
    }

}

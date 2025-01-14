# StateMachineJava

StateMachineJava is a Java-based library that provides a robust framework for implementing finite state machines (FSM) in your applications. It offers a flexible and extensible approach to manage state transitions, making it suitable for various scenarios requiring state management.

## Features

- **Declarative State Machine Definition**: Define state machines using a declarative style with the builder pattern.
- **State Entry and Exit Actions**: Assign actions to be executed upon entering or exiting a state.
- **State Entry and Exit Listeners**: Register listeners to monitor state transitions.
- **Timed Transitions**: Implement state transitions triggered by timeouts.
- **Transition Conditions and Guards**: Define conditions and guards to control state transitions.
- **Transition Actions**: Execute specific actions during state transitions.
- **Logging and Tracing**: Trace state machine processing with integrated logging.
- **Graphviz Export**: Export state machine diagrams in Graphviz ("dot") format for visualization.

## Installation

To include StateMachineJava in your project, add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.aks007</groupId>
    <artifactId>state-machine</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

Here's an example of how to define a simple traffic light state machine:

```java
public class TrafficLightStateMachine extends StateMachine<LightState, Void> {

    public enum LightState {
        OFF, RED, YELLOW, GREEN;
    }

    public TrafficLightStateMachine() {
        super(LightState.class);
        beginStateMachine()
            .description("Traffic Light State Machine")
            .initialState(LightState.OFF)
            .states()
                .state(LightState.OFF)
                .state(LightState.RED).timeoutAfter(() -> Duration.ofSeconds(3))
                .state(LightState.YELLOW).timeoutAfter(() -> Duration.ofSeconds(2))
                .state(LightState.GREEN).timeoutAfter(() -> Duration.ofSeconds(5))
            .transitions()
                .when(LightState.OFF).then(LightState.RED).condition(this::isPowerOn)
                .when(LightState.RED).then(LightState.GREEN).onTimeout()
                .when(LightState.GREEN).then(LightState.YELLOW).onTimeout()
                .when(LightState.YELLOW).then(LightState.RED).onTimeout()
        .endStateMachine();
    }

    private boolean isPowerOn() {
        // Implement the condition to check if the power is on
        return true;
    }
}
```

In this example, the traffic light cycles through RED, GREEN, and YELLOW states with specified timeouts, starting from the OFF state when the power is turned on.

## Configuration

StateMachineJava allows customization through various configuration options:

- **State Timeout**: Define timeouts for states to trigger transitions.
- **Transition Conditions**: Set conditions or guards that must be satisfied for transitions to occur.
- **Entry and Exit Actions**: Specify actions to execute upon entering or exiting states.
- **Event-Driven Transitions**: Trigger transitions based on specific events or messages.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Implement your changes with appropriate tests.
4. Submit a pull request detailing your changes.

Please ensure your code adheres to the project's coding standards and includes comprehensive documentation.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgements

Special thanks to the open-source community for their contributions and inspiration.

---

*Note: This README is a template and should be updated with specific details about your project. For more information on crafting effective README files, refer to GitHub's guide on [About READMEs](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes).*

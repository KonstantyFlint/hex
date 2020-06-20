package sim;

public interface State {
    State next(Node caller);
}

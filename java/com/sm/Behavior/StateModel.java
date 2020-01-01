package com.sm.Behavior;

/**
 * 类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。
 *
 * 在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象
 */
public class StateModel {
    interface State {
        public void doAction(Context context);
    }
    static class StartState implements State {

        public void doAction(Context context) {
            System.out.println("Player is in start state");
            context.setState(this);
        }

        public String toString(){
            return "Start State";
        }
    }
    static class StopState implements State {

        public void doAction(Context context) {
            System.out.println("Player is in stop state");
            context.setState(this);
        }

        public String toString(){
            return "Stop State";
        }
    }
    static class Context {
        private State state;

        public Context(){
            state = null;
        }

        public void setState(State state){
            this.state = state;
        }

        public State getState(){
            return state;
        }
    }
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}

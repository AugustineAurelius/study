package org.example;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

/**
 * Реализация программируемого конечного автомата. Чтобы использовать этот класс,
 * установить любое количество состояний с помощью метода addState. Далее добавьте немного
 * Объекты FSM.Transition (класс Transition предназначен для использования в качестве
 * суперкласса для вашей анонимной реализации). Каждый объект перехода имеет два
 * полезных метода, которые могут быть определены вашей реализацией: doBeforeTransition
 * и doAfterTransition. Чтобы управлять своим FSM, просто передайте ему события, используя
 * метод addEvent с названием события. Если есть соответствующий
 * переход для текущего состояния, методы перехода doBefore/doAfter
 * вызываются и автомат переходит в новое состояние. Это законно (и весьма
 * полезно) для того, чтобы начальное/конечное состояния перехода были одним и тем же состоянием.
 **/
public class FSM { // This class implements a Flying Spaghetti Monster

    protected String name;
    protected String currentState;
    protected Map<String, State> states;
    protected List<ChangeListener> changeListeners;
    protected boolean debug;

    /**
     * Создайте пустой FSM с заданным именем (произвольным).
     */
    public FSM(String name) {
        this.name = name;
        this.states = new HashMap<String, State>();
        this.currentState = null;
        this.changeListeners = new ArrayList<ChangeListener>();
    }

    /**
     * Включите/выключите debug
     */
    public void setDebugMode(boolean debug) {
        this.debug = debug;
    }

    /**
     * Сообщите о текущем состоянии конечного автомата.
     */
    public String getState() {
        return currentState;
    }

    /**
     * Добавляет новое состояние без кода входа или выхода.
     */
    public void addState(String state) {
        addState(state, null, null, null);
    }

    /**
     * Создайте новое состояние, о котором знает ФШМ. Если FSM в настоящее время не
     * есть какие-либо состояния, это состояние становится текущим, начальным. Это
     * единственный способ привести автомат в исходное состояние.
     *
     * EntryCode, exitCode и AlwaysRunCode — это Runnables, которые FSM
     * выполняется во время перехода. входной код и выходной код
     * запускается только в том случае, если переход происходит между двумя
     * разными состояниями (т.е. A->B где А != Б). AlwaysRunCode выполняется, даже если переход
     * реентерабельный (т.е. A->B, где A = B).
     **/
    public void addState(String state, Runnable entryCode, Runnable exitCode,
                         Runnable alwaysRunCode) {
        boolean isInitial = (states.size() == 0);
        if (!states.containsKey(state)) {
            states.put(state, new State(entryCode, exitCode, alwaysRunCode));
        }
        if (isInitial) {
            setState(state);
        }
    }

    public void setStateEntryCode(String state, Runnable entryCode) {
        states.get(state).entryCode = entryCode;
    }

    public void setStateExitCode(String state, Runnable exitCode) {
        states.get(state).exitCode = exitCode;
    }

    public void setStateAlwaysRunCode(String state, Runnable alwaysRunCode) {
        states.get(state).alwaysRunCode = alwaysRunCode;
    }

    /**
     * Бывают случаи, когда состояние считается переходным, а FSM
     * всегда должен немедленно переходить в какое-то другое состояние. В таких случаях
     * используйте этот метод для указания начального и конечного состояний. После начального состояния
     * полностью перешел (и были запущены все события изменения) FSM
     * проверит, есть ли другое состояние, в котором FSM должен
     * автоматический переход в. Если он есть, addEvent(endState)
     * называется.
     *
     * Примечание. При этом в таблице поиска создается специальный переход, называемый
     * «(авто)».
     */
    public void setAutoTransition(String startState, String endState) {
        states.get(startState).autoTransitionState = endState;
        addTransition(new Transition("(auto)", startState, endState));
    }

    /**
     * Устанавливает текущее состояние без перехода. Это вызовет
     * изменить событие, которое будет запущено.
     */
    public void setState(String state) {
        setState(state, true);
    }

    /**
     * Устанавливает текущее состояние без отслеживания перехода и опционально.
     * вызывает запуск события изменения. Во время переходов состояний (с
     * метод addEvent), этот метод используется с триггером Event
     * параметр имеет значение false.
     *
     * FSM выполняет ненулевые исполняемые файлы в соответствии со следующей логикой:
     * с учетом начального и конечного состояний A и B:
     *
     * <ol>
     * <li>Если A и B различны, запустите код завершения A.</li>
     * <li>Записать текущее состояние как B.</li>
     * <li>Выполнить «alwaysRunCode» Б.</li>
     * <li>Если A и B различны, введите код входа B.</li>
     * </ol>
     */
    public void setState(String state, boolean triggerEvent) {
        boolean runExtraCode = !state.equals(currentState);
        if (runExtraCode && currentState != null) {
            states.get(currentState).runExitCode();
        }
        currentState = state;
        states.get(currentState).runAlwaysCode();
        if (runExtraCode) {
            states.get(currentState).runEntryCode();
        }
        if (triggerEvent) {
            fireChangeEvent();
        }
    }

    /**
     * Установите новый переход. Вы можете использовать этот метод примерно так
     * этот:
     *
     * fsm.addTransition(new FSM.Transition("someEvent", "firstState",
     * " SecondState") { public void doBeforeTransition() {
     * System.out.println("собирается переход..."); } публичная пустота
     * doAfterTransition() {fancyOperation(); } });
     */
    public void addTransition(Transition trans) {
        State st = states.get(trans.startState);
        if (st == null) {
            throw new NoSuchElementException("Missing state: "
                    + trans.startState);
        }
        st.addTransition(trans);
    }

    /**
     * Добавьте прослушиватель изменений. Это стандартный прослушиватель изменений Java.
     * используется только для сообщения об уже произошедших изменениях. События ChangeEvents
     * срабатывает только ПОСЛЕ вызова doAfterTransition перехода.
     */
    public void addChangeListener(ChangeListener cl) {
        if (!changeListeners.contains(cl)) {
            changeListeners.add(cl);
        }
    }

    /**
     * Передайте FSM указанное событие. Если текущее состояние имеет переход
     * реагирующий на данное событие, автомат выполнит переход
     * Используя следующие шаги, предположим, что начальным и конечным состояниями являются A и B:
     *
     * <ol>
     * <li>Выполнить метод перехода «doBeforeTransition».</li>
     * <li>Запустите fsm.setState(B) — см. документацию по этому методу</li>
     * <li>Выполнить метод перехода «doAfterTransition».</li>
     * <li>Вызов события изменения, уведомляющего заинтересованных наблюдателей о том, что
     * переход завершен.</li>
     * <li>Теперь твердо в состоянии B, посмотрим, есть ли у B третье состояние C, которое мы должны
     * автоматический переход через addEvent(C).</li>
     * </ol>
     */
    public void addEvent(String evtName) {
        State state = states.get(currentState);
        if (state.transitions.containsKey(evtName)) {
            Transition trans = state.transitions.get(evtName);

            trans.doBeforeTransition();
            setState(trans.endState, false);
            trans.doAfterTransition();
            fireChangeEvent();
            if (states.get(trans.endState).autoTransitionState != null) {

                addEvent("(auto)");
            }
        }
    }

    /**
     * Запустите событие изменения для зарегистрированных слушателей.
     */
    protected void fireChangeEvent() {
        ChangeEvent changeEvent = new ChangeEvent(this);
        for (ChangeListener cl : changeListeners) {
            cl.stateChanged(changeEvent);
        }
    }

    /**
     * Представляет состояние с некоторым количеством связанных переходов.
     */
    private static class State {
        Map<String, Transition> transitions;
        String autoTransitionState;
        Runnable entryCode;
        Runnable exitCode;
        Runnable alwaysRunCode;

        State(Runnable entryCode, Runnable exitCode, Runnable alwaysRunCode) {
            autoTransitionState = null;
            transitions = new HashMap<String, Transition>();
            this.entryCode = entryCode;
            this.exitCode = exitCode;
            this.alwaysRunCode = alwaysRunCode;
        }

        public void addTransition(Transition trans) {
            transitions.put(trans.evtName, trans);
        }

        public void runEntryCode() {
            if (entryCode != null) {
                entryCode.run();
            }
        }

        public void runExitCode() {
            if (exitCode != null) {
                exitCode.run();
            }
        }

        public void runAlwaysCode() {
            if (alwaysRunCode != null) {
                alwaysRunCode.run();
            }
        }
    }

    /**
     * Создайте новый переход. См. документацию по addEvent и
     * addTransition в FSM.
     */
    public static class Transition {
        String evtName;
        String startState;
        String endState;

        /**
         * Создайте объект перехода, который реагирует на данное событие, когда он находится в
         * заданное начальное состояние и помещает FSM в предоставленное конечное состояние.
         */
        public Transition(String evtName, String startState, String endState) {
            this.evtName = evtName;
            this.startState = startState;
            this.endState = endState;
        }

        /**
         * Переопределите это, чтобы код FSM выполнялся непосредственно перед выполнением команды.
         * переход состояния.
         */
        public void doBeforeTransition() {
        }

        /**
         * Переопределите это, чтобы код FSM выполнялся сразу после выполнения
         * переход состояния.
         */
        public void doAfterTransition() {
        }
    }
}

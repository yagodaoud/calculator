package br.com.yagodaoud.calc.operations;

@FunctionalInterface
public interface MemoryObserver {

    void changedValue(String newValue);
}

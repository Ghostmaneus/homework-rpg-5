package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Critical Focus";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 8;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Critical precision boost";
    }
}
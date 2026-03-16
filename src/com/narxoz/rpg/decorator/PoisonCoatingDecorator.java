package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Poison Coating";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 4;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Applies lingering poison";
    }
}

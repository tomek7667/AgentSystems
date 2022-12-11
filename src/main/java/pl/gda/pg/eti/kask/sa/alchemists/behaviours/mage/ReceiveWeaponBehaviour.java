package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;

public class ReceiveWeaponBehaviour extends ReceiveResultBehaviour<Mage> {

    public ReceiveWeaponBehaviour(Mage agent, String conversationId) {
        super(agent, conversationId);
    }

    @Override
    protected void handleResult(Predicate predicate, AID participant) {
        if (predicate instanceof Result) {
            System.out.println(((Result) predicate).getValue());
        } else {
            System.out.println("No result");
        }
    }

}

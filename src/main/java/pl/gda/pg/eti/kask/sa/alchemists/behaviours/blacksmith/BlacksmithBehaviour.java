package pl.gda.pg.eti.kask.sa.alchemists.behaviours.blacksmith;

import jade.content.onto.basic.Action;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Blacksmith;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.WaitingBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellHerb;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellWeapon;

/**
 *
 * @author psysiu
 */
public class BlacksmithBehaviour extends WaitingBehaviour<Blacksmith>{

    public BlacksmithBehaviour(Blacksmith agent) {
        super(agent);
    }

    @Override
    protected void action(Action action, String conversationId, AID participant) {
        if (action.getAction() instanceof SellHerb) {
            myAgent.addBehaviour(new SellWeaponBehaviour(myAgent, (SellWeapon) action.getAction(), conversationId, participant));
        }
    }

}

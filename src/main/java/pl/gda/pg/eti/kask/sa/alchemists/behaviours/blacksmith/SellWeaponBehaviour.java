package pl.gda.pg.eti.kask.sa.alchemists.behaviours.blacksmith;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Blacksmith;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellWeapon;

/**
 *
 * @author psysiu
 */
public class SellWeaponBehaviour extends ActionBehaviour<SellWeapon, Blacksmith> {

    public SellWeaponBehaviour(Blacksmith agent, SellWeapon action, String conversationId, AID participant) {
        super(agent, action, conversationId, participant);
    }

    @Override
    protected Predicate performAction() {
        if (myAgent.getWeapons().contains(action.getWeapon())) {
            return new Result(action, action.getWeapon());
        } else {
            return null;
        }
    }

}

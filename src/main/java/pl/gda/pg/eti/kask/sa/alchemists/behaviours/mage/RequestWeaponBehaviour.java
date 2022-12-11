package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RequestActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellWeapon;

public class RequestWeaponBehaviour extends RequestActionBehaviour<SellWeapon, Mage> {

    public RequestWeaponBehaviour(Mage agent, AID participant, SellWeapon action) {
        super(agent, participant, action);
    }

    @Override
    protected ReceiveResultBehaviour createResultBehaviour(String conversationId) {
        return new ReceiveWeaponBehaviour(myAgent, conversationId);
    }

}

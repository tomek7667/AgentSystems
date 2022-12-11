package pl.gda.pg.eti.kask.sa.alchemists.agents;

import jade.core.AID;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.FindServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.MageBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestHerbBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestPotionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestWeaponBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.*;

import java.util.List;

/**
 *
 * @author psysiu
 */
public class Mage extends BaseAgent {
    private List<String> herbNames;
    private List<String> potionsNames;
    private List<String> weaponsNames;

    public Mage() {
    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Mage created");
        Object[] args = getArguments();
        if (args != null) {
            for (Object arg : args) {
                String[] split = ((String) arg).split(":");
                if (split[0].equals("herb")) {
                    herbNames.add(split[1]);
                } else if (split[0].equals("potion")) {
                    potionsNames.add(split[1]);
                } else if (split[0].equals("weapon")) {
                    weaponsNames.add(split[1]);
                }
            }
        }
        SequentialBehaviour behaviour = new SequentialBehaviour(this);

        behaviour.addSubBehaviour(new FindServiceBehaviour(this, "alchemist") {

            @Override
            protected void onResult(DFAgentDescription[] services) {
                if (services != null && services.length > 0) {
                    AID alchemist = services[0].getName();
                    potionsNames.forEach((potionName) -> {
                        SellPotion action = new SellPotion(new Potion(potionName));
                        RequestPotionBehaviour request = new RequestPotionBehaviour(Mage.this, alchemist, action);
                        ((SequentialBehaviour) getParent()).addSubBehaviour(request);
                    });
                }
            }
        });

        behaviour.addSubBehaviour(new FindServiceBehaviour(this, "herbalist") {

            @Override
            protected void onResult(DFAgentDescription[] services) {
                if (services != null && services.length > 0) {
                    AID herbalist = services[0].getName();
                    herbNames.forEach((herbName) -> {
                        SellHerb action = new SellHerb(new Herb(herbName));
                        RequestHerbBehaviour request = new RequestHerbBehaviour(Mage.this, herbalist, action);
                        ((SequentialBehaviour) getParent()).addSubBehaviour(request);
                    });
                }
            }
        });

        behaviour.addSubBehaviour(new FindServiceBehaviour(this, "blacksmith") {
            @Override
            protected void onResult(DFAgentDescription[] services) {
                if (services != null && services.length > 0) {
                    AID blacksmith = services[0].getName();
                    weaponsNames.forEach((weaponName) -> {
                        SellWeapon action = new SellWeapon(new Weapon(weaponName));
                        RequestWeaponBehaviour request = new RequestWeaponBehaviour(Mage.this, blacksmith, action);
                        ((SequentialBehaviour) getParent()).addSubBehaviour(request);
                    });
                }
            }
        });

        addBehaviour(behaviour);
        addBehaviour(new MageBehaviour(behaviour, this));
    }



}

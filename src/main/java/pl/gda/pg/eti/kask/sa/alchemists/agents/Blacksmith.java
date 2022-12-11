package pl.gda.pg.eti.kask.sa.alchemists.agents;

import lombok.Getter;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RegisterServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.blacksmith.BlacksmithBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith extends BaseAgent {
    @Getter
    private final List<Weapon> weapons = new ArrayList<>();

    public Blacksmith() {
        System.out.println("Blacksmith created");
    }

    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();
        if (args != null) {
            for (Object arg : args) {
                weapons.add(new Weapon((String) arg));
            }
        }

        addBehaviour(new RegisterServiceBehaviour(this, "blacksmith"));
        addBehaviour(new BlacksmithBehaviour(this));
    }
}

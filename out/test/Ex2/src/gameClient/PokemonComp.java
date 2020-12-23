package gameClient;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * This class is compare CL_Pokemon from high ->low
 */
public class PokemonComp implements Comparator<CL_Pokemon> {
    public int compare(CL_Pokemon o1, CL_Pokemon o2) {
        double p1= o1.getValue();
        double p2 = o2.getValue();
        return Double.compare(p1,p2);
    }
}


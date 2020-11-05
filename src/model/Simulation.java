package model;

import java.util.List;
import java.util.Random;

/**
 * Simulation
 */
public class Simulation {
    /**
     * Graph
     */
    private Graph graph;

    /**
     * Colonie
     */
    private Colony colony;

    /**
     * Valeurs aléatoires ou non
     */
    private final boolean shuffle;

    /**
     * Valeurs des nœuds
     */
    private final double[][] values;

    /**
     * Constructeur
     *
     * @param values Valeurs des nœuds
     */
    public Simulation(double[][] values) {
        this.values = values;
        shuffle = (values[0].length == 0);
        reset();
    }

    /**
     * Constructeur
     *
     * @param nbNodes Nombre de nœuds
     */
    public Simulation(int nbNodes) {
        this(new double[nbNodes][0]);
    }

    /**
     * Réinitialise la simulation
     */
    public void reset() {
        int nbNodes = values.length;

        if (shuffle) {
            Random rand = new Random();
            for (int i = 0; i < nbNodes; i++) {
                values[i] = new double[]{rand.nextDouble(), rand.nextDouble()};
            }
        }

        graph = new Graph(values);
        colony = new Colony(graph);

        colony.cycleColony();
        while (!colony.hasFinished()) {
            colony.cycleColony();
        }

        List<Node> bestNodes = colony.getBestNodes();

        for (int i = 0; i < bestNodes.size(); i++) {
            System.out.print(bestNodes.get(i).toString());
            if (i != bestNodes.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Nombre de tours : " + colony.getNbEpoch());
    }

    /**
     * @return Graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return Colonie
     */
    public Colony getColony() {
        return colony;
    }
}

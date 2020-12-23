package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import java.util.*;
import gameClient.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ex2 implements Runnable {
    private static Panelim pans;
    private static FormatWindow window;
    private static Arena arena;
    private static int num;
    private static HashMap<Integer, List<node_data>> nodes;
    private static HashMap<Integer, CL_Pokemon> edges;
    private static int id;

    public static void main(String[] a) {
      //  Music should start from here
        String filepathField = "pokemontheme.wav";
        MusicBack playm1 = new MusicBack();
        //playm1.playMusic("pokemontheme.wav");
        if (a.length != 2) {
            window = new FormatWindow("Login Page");
            window.setResizable(false);
           // window.add(new loginWindow());
            window.add(new Login());
            window.setSize(600,600);
            window.setVisible(true);
            playm1.playMusic("pokemontheme.wav");

        } else {
            id = Integer.parseInt(a[0]);
            num = Integer.parseInt(a[1]);
            edges = new HashMap<>();
            nodes = new HashMap<>();
            Thread client = new Thread(new Ex2());
            client.start();
        }
    }

    /**
     * Control panel when the thread is sleep
     */
    @Override
    public void run() {
        game_service game = Game_Server_Ex2.getServer(num); // [0,23] games
         game.getGraph();

        dw_graph_algorithms ga = new DWGraph_Algo();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DWGraph_DS.class, new Json_Deserial());
        game.getPokemons();
        Gson gsonbuild = builder.create();
        DWGraph_DS graph = gsonbuild .fromJson(game.getGraph(), DWGraph_DS.class);
        directed_weighted_graph gg = graph;
        init(game);

        game.startGame();
        int i = 0;
        int str = 250;
        window.setTitle("Pokemon game"+ game.timeToEnd() / 1000 + "s");
        int timers = (int) game.timeToEnd();
        double Time = (double) game.timeToEnd() / (double) timers;
        while (game.isRunning()) {
            moveAgants(game, gg);
            try {
                if (i % 1 == 0) {
                    window.repaint();
                    window.setTitle("Time: " + game.timeToEnd() / 1000 + "s");
                }
                switch (str) {
                    case 50:
                        str = 50;
                        break;
                    case 55:
                        str = 65;
                        break;
                    case 65:
                        str = 71;
                        break;
                    case 71:
                        str = 76;
                        break;

                }
                if (Time < 0.2) str = 50;
                if (Time > 0.2 && Time < 0.3) str = 55;
                if (Time > 0.3 && Time < 0.4) str = 60;
                if (Time > 0.4 && Time < 0.5) str = 90;
                if (Time > 0.5 && Time < 0.6) str = 140;
                if (Time > 0.6 && Time < 0.7) str = 150;
                if (Time > 0.7 && Time < 0.8) str = 160;
                if (Time > 0.8 && Time < 0.9) str = 200;

                Thread.sleep(str);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(game.toString());
        System.exit(0);
    }

    /**
     * Control the movement of the agents into the graph game.
     * @param game = Game server
     * @param gga  = The graph of the giving scenario number
     */
    private static void moveAgants(game_service game, directed_weighted_graph gga) {
        List<CL_Agent> log = Arena.getAgents(game.move(), gga);
        arena.setAgents(log);
        double mdistance = Double.POSITIVE_INFINITY;
        List<CL_Pokemon> fpk = Arena.json2Pokemons(game.getPokemons());
        arena.setPokemons(fpk);
        for (int i = 0; i < log.size(); i++) {
            CL_Agent agant = log.get(i);
            int d = agant.getNextNode();
            if (agant.getNextNode() == -1 && !agant.isMoving()) {
                d = TheNext(gga, agant.getSrcNode(), agant.getID(), mdistance);
                game.chooseNextEdge(agant.getID(), d);
                System.out.println("Agent: " + agant.getID() + ", value: " + agant.getValue() + "   go to node dest: " + d);
            }
        }
    }

    /**
     * Chooses for each agent the right and most relevant node to go, depends on the position of all agents and pokemons.
     * @param g   = the graph of the current scenario.
     * @param src = start node's key.
     * @param Key = agent's ID.
     * @param mdistance
     * @return  the relevant option for agent to go to the next node.
     */
    private static int TheNext(directed_weighted_graph g, int src, int Key, double mdistance) {
        dw_graph_algorithms ga = new DWGraph_Algo(g);
        CL_Pokemon poke = null;
        List<CL_Pokemon> pg = arena.getPokemons();
        List<CL_Agent> ag = arena.getAgents();
        int mdest = 0;
        int msrc = 0;

        for (CL_Pokemon pokemon : pg) { arena.updateEdge(pokemon, g); }

        boolean poktf;
        for (CL_Pokemon pokemon : pg) {
            poktf = false;
            for (CL_Agent agent : ag) {
                if (agent.getID() != Key && nodes.containsKey(agent.getID())) {
                    if (nodes != null && nodes.containsKey(agent.getID()))
                        if (nodes.get(agent.getID()).contains(g.getNode(pokemon.get_edge().getDest())))
                            poktf = true;
                }
            }
            if (poktf == true && pokemon.getValue() < 1 && edges.values().contains(pokemon)) continue;
                //if(edges.values().contains(pokemon)) continue;
            else if (ga.shortestPathDist(src, pokemon.get_edge().getDest()) < mdistance) {
                mdistance = ga.shortestPathDist(src, pokemon.get_edge().getDest());
                poke = pokemon;
                mdest = pokemon.get_edge().getDest();
                msrc = pokemon.get_edge().getSrc();

            }
        }
        List<node_data> pathnode = ga.shortestPath(src, (int) mdistance);
        if (poke != null)
            edges.put(Key, poke);
        if (pathnode != null) {
            if (pathnode.size() > 1) {
                nodes.put(Key, pathnode);
                return pathnode.get(1).getKey();
            }
        }return msrc; }


    /**
     * set a new arena using the Json info.
     * Its sort the pokemons by their values.
     * The agents are placed according to the pokemons.
     * @param game = game service of the current scenario.
     */
    private void init(game_service game) {
        String pks = game.getPokemons();
        arena = new Arena();
        JSONObject read;
        dw_graph_algorithms ga = new DWGraph_Algo();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DWGraph_DS.class, new Json_Deserial());
        Gson gson = builder.create();
        directed_weighted_graph gg = (DWGraph_DS) gson.fromJson(game.getGraph(), DWGraph_DS.class);;
        arena.setGraph(gg);
        window.setVisible(false);
        window.getContentPane().removeAll();
        pans = new Panelim(game);
        pans.update(arena);
        //arena.setPokemons(Arena.json2Pokemons(game.getPokemons()));
        window.setVisible(true);

        window.setSize(700, 600);
        window.getContentPane().add(pans);


        try {
            read = new JSONObject(game.toString());
            int agen = (read.getJSONObject("GameServer")).getInt("agents");
            System.out.println(game.toString());
            System.out.println(game.getPokemons());
            ArrayList<CL_Pokemon> ap = Arena.json2Pokemons(game.getPokemons());
            ap.sort((Comparator<? super CL_Pokemon>) new PokemonComp());
            for(int i =0; i < ap.size();i++) {
                Arena.updateEdge(ap.get(i),gg);
            }
            for(int j = 0; j < agen ;j++) {
                int ind = j%ap.size();
                int srcnum = 0;
                CL_Pokemon ar = ap.get(ind);
                if(ar.getType() < 1)
                    srcnum = Math.max(ar.get_edge().getSrc(), ar.get_edge().getDest());
                if(ar.getType() > 1)
                    srcnum = Math.min(ar.get_edge().getSrc(), ar.get_edge().getDest());
                game.addAgent(srcnum);
            }
        }
        catch (JSONException e) {e.printStackTrace();}
    }



    /**
     * JPanel extension for a login interface
     */
    public static class Login extends JPanel {

        /**
         * paintComponenets is extended to have a nice background for the login page.
         * @param g = Graphics
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // URL url = this.getClass().getResource("themepic.gif");
            //Image icon = new ImageIcon(url).getImage();
             ImageIcon icon = new ImageIcon("themepic.jpg");
            g.drawImage(icon.getImage(),0, 0, getWidth(),getHeight(),null);

        }
        /**
         * this is the implementation of the login page, using the GUI class created for graphic interface.
         *
         */
        public Login(){
            super();
            setSize(500,500);
            setLayout(null);
            JLabel lvl = new JLabel("Level");
            lvl.setBounds(50,100,70,25);
            lvl.setFont(new Font("Arial", Font.PLAIN, 20));
            lvl.setForeground(Color.black);
            lvl.setBackground(Color.white);
            lvl.setOpaque(true);
            add(lvl);
            JTextField scIn = new JTextField();
            scIn.setFont(new Font("Arial", Font.PLAIN, 10));
            scIn.setBounds(150, 103,150,20);
            add(scIn);
            JLabel idnumb = new JLabel("Trainer ID");
            idnumb.setBounds(50,150,60,25);
            idnumb.setForeground(Color.black);
            idnumb.setBackground(Color.white);
            idnumb.setOpaque(true);
            add(idnumb);
            JTextField idIn = new JTextField();
            idIn.setFont(new Font("Arial", Font.PLAIN, 10));
            idIn.setBounds(150, 153,150,20);
            add(idIn);
            JButton start = new JButton("Login");
            start.setFont(new Font("Arial", Font.ITALIC, 20));
            start.setBounds(120,190,100,50);
            start.setForeground(Color.white);
            start.setBackground(Color.green.darker().darker());
            start.setBorder(BorderFactory.createLineBorder(Color.black,2));
            add(start);
            start.addActionListener(e ->{    //lambda expression for performing the initialization of the game according rhe provided id and scenario number.
                String id = idIn.getText();
                String sc = scIn.getText();
                String args[] = {id,sc};
                main(args);
            });
            start.addActionListener(e -> window.setVisible(false));  //closing the login page frame as it no longer necessary.
        }

    }

}






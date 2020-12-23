package gameClient;

import api.*;
import  java.util.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Panelim extends JPanel {
    private Arena _ar;
    private int _ind;
    private Range2Range _w2f;
    private game_service game;

    public Panelim() {
        super();
        setBorder(BorderFactory.createLineBorder (Color.black));
    }


    public Panelim(game_service game) {
        this.game = game;
    }

    private void drawInfo(Graphics g) {
        List<String> str = _ar.get_info();
        String dt = "none";
        for (int i = 0; i < str.size(); i++) {
            g.drawString(str.get(i) + " dt: " + dt, 100, 60 + i * 20);
        }
    }
    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }
    public void paint(Graphics g) {
        g.setFont(new Font("Ariel", Font.BOLD, 15));
//        int w = this.getWidth();
//        int h = this.getHeight();
//        g.clearRect(0, 0, w, h);
        updateFrame();
        drawInfo(g);
     //   ProgressBarDemo(g);
        drawGraph(g);
        drawPokemons(g);
        drawTitle(g);
        drawAgants(g);

    }

    private void updateFrame() {
        Range rx = new Range(20,this.getWidth()-20);
        Range ry = new Range(this.getHeight()-10,150);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g,frame);
    }



    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        Iterator<node_data> iter = gg.getV().iterator();
        while(iter.hasNext()) {
            node_data n = iter.next();
            g.setColor(Color.blue);
            drawNode(n,5,g);
            Iterator<edge_data> itr = gg.getE(n.getKey()).iterator();
            while(itr.hasNext()) {
                edge_data e = itr.next();
                g.setColor(Color.gray);
                drawEdge(e, g);
            }
        }
    }
    // draw progress bar timer
    private void ProgressBarDemo(Graphics g){
        List<CL_Agent> ag = _ar.getAgents();
       int pokecatch = 0;
        JProgressBar bar = new JProgressBar();
        for(CL_Agent ap: ag){
            pokecatch+= ap.getValue();
        }
        bar.setValue(0);
        bar.setBounds(0, 80 ,this.getWidth(),50);
        bar.setStringPainted(true);
        bar.setBackground(Color.black);
        bar.setForeground(Color.white);
        this.add(bar);
        g.setColor(Color.white);
        bar.setString(pokecatch+ "  Pokemons have been catch");
        bar.setValue(pokecatch);
        this.add(bar);

    }



    private void drawPokemons(Graphics g) {

        //JLabel ImageIcon = new JLabel(new ImageIcon("image"+randomNum+".png"));

      /*  Icon imgIcon = new ImageIcon(this.getClass().getResource("Pokemons/"+r+".gif"));
        JLabel Pokpic = new JLabel(imgIcon);
        JFrame frameLoader1 = new JFrame();
        Pokpic.setSize(50,50);
        frameLoader1.getContentPane().add(Pokpic);
*/

     /*   URL url = this.getClass().getResource("Pokemons/"+r+".gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setSize(50,50);
        JFrame frameLoader = new JFrame();
        frameLoader.setUndecorated(true);
        frameLoader.getContentPane().add(label);
        frameLoader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLoader.pack();
        frameLoader.setLocationRelativeTo(null);
       frameLoader.setVisible(true);
*/
      //  mageIcon pokepic = new ImageIcon("Pokemons/" + r + ".gif");
        List<CL_Pokemon> pok = _ar.getPokemons();


        if (pok != null) {
            Iterator<CL_Pokemon> iter = pok.iterator();
            while (iter.hasNext()) {

                int r = 1 + (int) (Math.random() * 100);
               //ImageIcon url = this.getClass().getResource("pics/"+r+".png");
               ImageIcon icon = new ImageIcon("pics/"+r+".png");
        /*      JLabel label = new JLabel(icon);
               label.setSize(50,50);
                JFrame frameLoader = new JFrame();
                frameLoader.setUndecorated(true);
                frameLoader.getContentPane().add(label);
                frameLoader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameLoader.pack();
                frameLoader.setLocationRelativeTo(null);
                frameLoader.setVisible(true);
*/
                CL_Pokemon poki = iter.next();
                Point3D c = poki.getLocation();
                if (c != null) {
                    geo_location fp = this._w2f.world2frame(c);
                   // g.drawImage(frameLoader.setVisible(true), (int) fp.x() - 20, (int) fp.y() - 20, this);
                     g.drawImage(icon.getImage(), (int) fp.x() - r, (int) fp.y() - r, 2 * r + 5, 2 * r + 5, this);
                }
            }
        }

    }

        private void drawAgants (Graphics g){
            ImageIcon Playa = new ImageIcon("ash.png");
            List<CL_Agent> rs = _ar.getAgents();
            //	Iterator<OOP_Point3D> itr = rs.iterator();

            int i = 0;
            while (rs != null && i < rs.size()) {
                geo_location c = rs.get(i).getLocation();
                int r = 8;
                i++;
                if (c != null) {
                    geo_location fp = this._w2f.world2frame(c);
                    g.drawImage(Playa.getImage(), (int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r, this);
                    //g.drawImage(Playa.getImage(), (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
                }
            }
        }
    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.setColor(new Color(0x8D490E));
        g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
        g.setColor(new Color(0x000000));
        g.drawString(""+n.getKey(), (int)fp.x(), (int)fp.y()-4*r);
    }

    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = _w2f.world2frame(s);
        geo_location d0 = _w2f.world2frame(d);
        g.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
    }
    private void drawTitle(Graphics g) {
       //JProgressBar element = new JProgressBar(0,totalValue);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Score:", 10, 20);
        double total = 0;
        int y = 40;
        for (CL_Agent i : _ar.getAgents()) {
            total += i.getValue();
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            y = 40 + (20 * i.getID());
            g.drawString("Agent " + i.getID() + ":  " + i.getValue(), 20, y);
        }
        int timeLeft = ((int)game.timeToEnd())/1000;
       // element.setString("Time End In " + timeLeft );
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Score:"+ total, 10, y + 20);
    }


    }

package gameClient;
import api.*;
import api.game_service;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.Panelim;
import gameClient.util.Range2Range;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class FormatWindow extends JFrame {
    private Arena _ar;
    private int _ind;
    private game_service game;
    private Range2Range _w2f;
    Panelim pans;
   // private String[] GameImg = {"pics\\x.png","pics\\y.png"};


    public FormatWindow(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        this.setTitle(name);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("pokeball.png");
        setIconImage(icon.getImage());
        pans = new Panelim(game);
        this.add(pans);
        this.game=game;
        setVisible(true);

    }

}

//package gameClient;
//import api.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class loginWindow extends JPanel implements ActionListener {
//    private JButton start;
//    private JLabel lvl;
//    private JLabel idnumb;
//    private JTextField scIn;
//    private JTextField idIn;
//    private boolean alreadyDoneThat=false;
//    /**
//     * paintComponenets is extended to have a nice background for the login page.
//     *
//     * @param g = Graphics
//     */
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // URL url = this.getClass().getResource("themepic.gif");
//        //   Image icon = new ImageIcon(url).getImage();
//        ImageIcon icon = new ImageIcon("themepic.jpg");
//
//        g.drawImage(icon.getImage(), 0,0 , 1000, 1000, null);
//
//    }
//
//    /**
//     * this is the implementation of the login page, using the GUI class created for graphic interface.
//     */
//    public loginWindow() {
//        super();
//        setSize(500, 500);
//        setLayout(null);
//        JLabel lvl = new JLabel("Level");
//        lvl.setBounds(100, 100, 70, 25);
//        lvl.setFont(new Font("Arial", Font.PLAIN, 20));
//        lvl.setForeground(Color.black);
//        lvl.setBackground(Color.white);
//        lvl.setOpaque(true);
//        add(lvl);
//        scIn = new JTextField();
//        scIn.setFont(new Font("Arial", Font.PLAIN, 10));
//        scIn.setBounds(250, 103, 150, 20);
//        add(scIn);
//        JLabel idnumb = new JLabel("Trainer ID");
//        idnumb.setBounds(150, 150, 60, 25);
//        idnumb.setForeground(Color.black);
//        idnumb.setBackground(Color.white);
//        idnumb.setOpaque(true);
//        add(idnumb);
//        idIn = new JTextField();
//        idIn.setFont(new Font("Arial", Font.PLAIN, 10));
//        idIn.setBounds(150, 153, 150, 20);
//        add(idIn);
//        start = new JButton("Login");
//        start.setFont(new Font("Arial", Font.ITALIC, 20));
//        start.setBounds(120, 190, 150, 50);
//        start.setForeground(Color.white);
//        start.setBackground(Color.green.darker().darker());
//        start.setBorder(BorderFactory.createLineBorder(Color.black, 2));
//        start.addActionListener(this);
//        this.add(scIn);
//        this.add(idIn);
//        this.add(start);
//        try {
//            int level = Integer.parseInt(scIn.getText());
//            int id = Integer.parseInt(idIn.getText());
//            Ex2.setScenario_num(level);
//            Ex2.setUserID(id);
//        } catch (NumberFormatException nfe) {
//            System.out.println("Error. ID and level can only be numbers.");
//        }
//
//
//        @Override
//        public void actionPerformed(ActionEvent e){
//            boolean play = true;
//
//            if (e.getSource() == start) {
//                try {
//                    int level = Integer.parseInt(lvl.getText());
//                    int id = Integer.parseInt(idnumb.getText());
//                } catch (NumberFormatException nfe) {
//                        System.out.println("Invalid info.");}
//
//        }
//
//    }
//}

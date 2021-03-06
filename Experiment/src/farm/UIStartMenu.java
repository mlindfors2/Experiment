package farm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Start menu that lets the user start the game, load an game or quit
 * @author Mikael Lindfors, Max Rudander, Elin Olsson, Malin Zederfeldt, Matthias Svensson Falk
 */
public class UIStartMenu extends JFrame implements ActionListener {

    private Controller controller;
    private JLabel borderImageNorth = new JLabel();
    private JLabel borderImageSouth = new JLabel();
    private JLabel borderImageWest = new JLabel();
    private JLabel borderImageEast = new JLabel();
    private JLabel lblTitle = new JLabel("Välkommen till bondgården");
    
    private JPanel contentPane = new JPanel(new BorderLayout());
    
    private ImageIcon horizontal = new ImageIcon("images/0horizontal.jpg");
    private ImageIcon vertical = new ImageIcon("images/0vertical.jpg");
    
    private JButton btnNewGame = new JButton("Nytt spel");
    private JButton btnLoad = new JButton("Ladda spel");
    private JButton btnQuit = new JButton("Avsluta");

    /**
     * sets up the UI
     * @param controller an instance to controller
     */
    public UIStartMenu(Controller controller) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.controller = controller;
        setLayout(new BorderLayout());
        add(borderImageNorth, BorderLayout.NORTH);
        add(borderImageSouth, BorderLayout.SOUTH);
        add(borderImageWest, BorderLayout.WEST);
        add(borderImageEast, BorderLayout.EAST);
        add(contentPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setBorder();
        setContentPane();
        setButtons();
        pack();
        setVisible(true);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    /**
     * adds the label and the buttons to the central panel
     */
    public void setContentPane(){
    	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.setLayout(new GridLayout(4,1));
        contentPane.add(lblTitle);
        contentPane.add(btnNewGame);
        contentPane.add(btnLoad);
        contentPane.add(btnQuit);
    }
    /**
     * adds fancy images to the windows borders
     */
    public void setBorder() {
        int widthHorizontal = horizontal.getIconWidth() + 50;
        int heightHorizontal = horizontal.getIconHeight();
        int widthVertical = vertical.getIconWidth();
        int heightVertical = vertical.getIconHeight();
        horizontal = new ImageIcon(horizontal.getImage().getScaledInstance(widthHorizontal, heightHorizontal, Image.SCALE_DEFAULT));
        borderImageNorth.setIcon(horizontal);
        borderImageSouth.setIcon(horizontal);
        vertical = new ImageIcon(vertical.getImage().getScaledInstance(widthVertical, heightVertical, Image.SCALE_DEFAULT));
        borderImageWest.setIcon(vertical);
        borderImageEast.setIcon(vertical);
    }
    /**
     * adds actionListener to the buttons
     */
    public void setButtons(){
        btnNewGame.addActionListener(this);
        btnLoad.addActionListener(this);
        btnQuit.addActionListener(this);

    }
    /**
     * handles the actions that will be performed when the buttons is pressed
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame){
            controller.newGame();
            this.setVisible(false);
        }
        if (e.getSource() == btnLoad){
            JOptionPane.showMessageDialog(null, "Kommer snart!");
        }
        if (e.getSource() == btnQuit){
            System.exit(0);
        }
    }
}

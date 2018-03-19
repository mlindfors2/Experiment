//package com.zetcode;
package farm;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Farm extends JFrame {

    public Farm() {
        
        initUI();
    }
    
    private void initUI() {
        
        this.add(new Board());
        setTitle("Farm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Farm ex = new Farm();
            ex.setVisible(true);
        });
    }
}
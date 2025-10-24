package snakegame;

import javax.swing.JFrame;

public class SGFrame extends JFrame {    
    public SGFrame() {
       add(new SGPanel());
       setFrame();
    }
    
    private void setFrame() {
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setTitle("Snake Game");
       this.setResizable(false);
       this.setVisible(true);
       this.pack();
       this.setLayout(null);
       this.setLocationRelativeTo(null);
    }
}

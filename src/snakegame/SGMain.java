package snakegame;

import javax.swing.ImageIcon;

public class SGMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String pathOfIcon = "src/snakegame/GameIcon/game_icon.png";
                ImageIcon imgIcon = new ImageIcon(pathOfIcon);
                SGFrame snakeFrame = new SGFrame();
                snakeFrame.setIconImage(imgIcon.getImage());
            }
        });
    }
}
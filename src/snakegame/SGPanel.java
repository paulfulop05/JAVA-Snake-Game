package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SGPanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1180;
    static final int SCREEN_HEIGHT = 780;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 65;
    
    final int []snakeX = new int[GAME_UNITS];
    final int []snakeY = new int[GAME_UNITS];
    int appleX;
    int appleY;
    
    int bodyParts = 5;
    int score;
    String direction = "RIGHT";
    boolean isRunning = false;
    Timer timer;
    Random random;
    
    public SGPanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    public void startGame() {
        newApple();
        isRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        draw(graph);
    }
    
    public void draw(Graphics graph) {
        if(isRunning) {
            graph.setColor(Color.RED);
            graph.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i = 0; i < bodyParts; ++i) {
                if(i % 2 == 0) {
                    graph.setColor(new Color(59, 144, 184));
                } else {
                    graph.setColor(new Color(18, 68, 92));
                }

                graph.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
                
                graph.setColor(new Color(214, 213, 212));
                graph.setFont(new Font("Ink Free", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(graph.getFont());
                graph.drawString("Score: " + score, (SCREEN_WIDTH - metrics.stringWidth("Score:" + score)) - 2 * UNIT_SIZE,
                        graph.getFont().getSize());               
            }
            
        } else {
            gameOver(graph);
        }
    }
    
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }
    
    public void move() {
        for(int i = bodyParts; i > 0; --i) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        
        switch(direction) {
            case "UP" :
                snakeY[0] -= UNIT_SIZE;
                break;
                
            case "DOWN" :
                snakeY[0] += UNIT_SIZE;
                break;
                
            case "LEFT" :
                snakeX[0] -= UNIT_SIZE;
                break;
                
            case "RIGHT" :
                snakeX[0] += UNIT_SIZE;
                break;
        }
    }
    
    public void checkApple() {
        if((snakeX[0] == appleX) && (snakeY[0] == appleY)) {
            ++bodyParts;
            score += 10;
            newApple();
        }
    }
    
    public void checkCollisions() {
        for(int i = bodyParts; i > 0; --i) {
            if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
                isRunning = false;
        }
        
        if(snakeX[0] == SCREEN_WIDTH) {
            isRunning = false;
        }
                
        if(snakeX[0] < 0) {
            isRunning = false;
        }
                
        if(snakeY[0] == SCREEN_HEIGHT) {
            isRunning = false;
        }
                
        if(snakeY[0] < 0) {
            isRunning = false;
        }
                
        if(!isRunning) {
            timer.stop();
        }
    }
    
    public void gameOver(Graphics graph) {
        graph.setColor(Color.RED);
        graph.setFont(new Font("Ink Free", Font.BOLD, 150));
        FontMetrics metrics1 = getFontMetrics(graph.getFont());
        graph.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2,
                SCREEN_HEIGHT / 2 - 2 * UNIT_SIZE);

        graph.setColor(new Color(214, 213, 212));
        graph.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(graph.getFont());
        graph.drawString("Score: " + score, (SCREEN_WIDTH - metrics2.stringWidth("Score:" + score)) / 2,
                SCREEN_HEIGHT / 2 + 6 * UNIT_SIZE);
    }
        
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(isRunning) {
            move();
            checkApple();
            checkCollisions();
        }
        
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent evt) {
            switch(evt.getKeyCode()) {
                case KeyEvent.VK_A:
                    if(!direction.equals("RIGHT")) {
                        direction = "LEFT";
                    }
                    break;
                    
                case KeyEvent.VK_W:
                    if(!direction.equals("DOWN")) {
                        direction = "UP";
                    }
                    break;
                    
                case KeyEvent.VK_D:
                    if(!direction.equals("LEFT")) {
                        direction = "RIGHT";
                    }
                    break;
                    
                case KeyEvent.VK_S:
                    if(!direction.equals("UP")) {
                        direction = "DOWN";
                    }
                    break; 
                    
                case KeyEvent.VK_LEFT:
                    if(!direction.equals("RIGHT")) {
                        direction = "LEFT";
                    }
                    break;
                    
                case KeyEvent.VK_UP:
                    if(!direction.equals("DOWN")) {
                        direction = "UP";
                    }
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if(!direction.equals("LEFT")) {
                        direction = "RIGHT";
                    }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if(!direction.equals("UP")) {
                        direction = "DOWN";
                    }
                    break;
            } 
        }
    }
}

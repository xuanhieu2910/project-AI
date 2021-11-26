/*
This class consists UI, Control, Logic & everything about Game.
 */

import javax.swing.*;
import javax.swing.Timer;

import node.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    static final Button start = new Button("Start"), help = new Button("Help"), new_game = new Button("New Game"), about = new Button("About"), pausee = new Button("Pause"), play = new Button("Play");
    static final JRadioButton dfs = new JRadioButton("DFS");
    static final JRadioButton bfs = new JRadioButton("BFS");
    static final JRadioButton aStart = new JRadioButton("A*");
    static final ButtonGroup buttonGroup = new ButtonGroup();
    static final int Width = 1200;
    static final int Height = 910;
    static final int UNIT_Size = 30;
    static final int Game_Unit = (Width*Height)/ UNIT_Size;
    static  final int delay = 75;          //Change this parameter for controlling speed of the Snake
    final int x[] = new int[50];          //Snake position on X axis
    final int y[] = new int[50];          //Snake position on Y axis
    int bodyParts = 1;                   //Body Parts of Snake
    int fruitX, fruitY;                 //Position of Food
    Image food;                        //Food Image
    char direction = 'R';             //Direction
    boolean running, pause = false;
    int Score = 0;                  //Score
    Timer timer;
    public java.util.List<NodeDemo> nodeList;
    static NodeDemo nodeStart = new NodeDemo();
    int[][] mazesDemo = new int[30][30];
    final static Image logo = new ImageIcon("src/img/logo.png").getImage();
    public Stack<NodeDemo>nodeDemoStack;
    public static Algorithm algorithm = new Algorithm();
    public  Stack<NodeDemo>nodeStackDirection;
    public List<NodeDemo>positionSnake;
    GamePanel(){
        setPreferredSize(new Dimension(1050, Height));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        setBackground(new Color(230,230,250));
        setLayout(null);

        //Mazes
        Mazes mazes = new Mazes();
        try {
            mazesDemo = mazes.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }




        //Button List choose
        buttonGroup.add(dfs);
        buttonGroup.add(bfs);
        buttonGroup.add(aStart);


        //Buttons action listener
        start.addActionListener(this);
        new_game.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);
        pausee.addActionListener(this);
        play.addActionListener(this);
        bfs.addActionListener(this);
        dfs.addActionListener(this);
        aStart.addActionListener(this);
        //Buuttons Positions
        start.setBounds(915, 230,120,70);
        pausee.setBounds(915, 230,120,70);
        play.setBounds(915, 230,120,70);
        new_game.setBounds(915, 350,120,70);
        help.setBounds(915, 480,120,70);
        about.setBounds(915,600,120,70);
        dfs.setBounds(905,190,50,35);
        dfs.setBackground(new Color(230,230,250));
        bfs.setBounds(960,190,50,35);
        bfs.setBackground(new Color(230,230,250));
        aStart.setBounds(1010,190,50,35);
        aStart.setBackground(new Color(230,230,250));

        new_game.setFont(new Font("Georgia", Font.PLAIN, 16));
        new_game.setVisible(false);
        pausee.setVisible(false);
        play.setVisible(false);

        add(start);
        add(help);
        add(new_game);
        add(about);
        add(pausee);
        add(play);
        add(bfs);
        add(dfs);
        add(aStart);

    }

    //Start Game Method
    public void startGame(){
            positionSnake = new ArrayList<>();
            NodeDemo nodeDemoPosition = new NodeDemo();
            nodeDemoPosition.setX(x[0]);
            nodeDemoPosition.setY(y[0]);
            positionSnake.add(nodeDemoPosition);
            newfruit();
            algorithm.setNewVisited();
            if(dfs.isSelected()){
                System.out.println("DFS được chọn");
                nodeStackDirection = algorithm.dfs(mazesDemo,algorithm.getVisited(),0,0,positionSnake);
            }
            else if(bfs.isSelected()){
                System.out.println("BFS được chọn");
                LinkedList<NodeListDemo> nodeListDemos = algorithm.bfs(mazesDemo,algorithm.getVisited(),0,0,positionSnake);
                nodeStackDirection = algorithm.pathFindingBFS(nodeListDemos);
            }
//            else if(aStart.isSelected()){
//                System.out.println("A* được chọn");
//            }
            pathFinding();
            running = true;
            timer = new Timer(delay, this);
            timer.start();
            start.setVisible(false);
            pausee.setVisible(true);
            new_game.setVisible(true);
    }


    //New_Game, when new game button or N will be pressed
    public void New_game(){
        timer.stop();
        mazesDemo[fruitY/30][fruitX/30]=1;
        running = false;
        Score = 0;
        bodyParts = 1;
        Arrays.fill(x, 0);
        Arrays.fill(y, 0);
        direction = 'R';
        startGame();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;
        //Rows Drawing Screen
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Color color;
                switch (mazesDemo[i][j]) {
                    case 3:
                        color = Color.RED;
                        break;
                    case 0:
                        color = Color.BLACK;
                        break;
                    case 1:
                        color = Color.WHITE;
                        break;
                    case 2:color=Color.BLUE;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mazesDemo[i][j]);
                }
                g.setColor(color);
                g.fillRect(j * 30, i * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(j * 30, i * 30, 30, 30);
            }
        }

        //Line between Game Play stage & Button Panel
        gd.setColor(Color.red);
        gd.setStroke(new BasicStroke(3));
        gd.drawLine(900,0,900,Height);

        //Snake Drawing Logic

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0 ){
                    gd.setColor(new Color(248, 55, 55));
                    gd.fillRect(x[i], y[i], UNIT_Size, UNIT_Size);
                    gd.setColor(Color.white);
                    gd.setStroke(new BasicStroke(3));
                    gd.drawOval(x[i] + 4, y[i] + 8, 8, 8);
                    gd.drawOval(x[i] + 20, y[i] + 8, 8, 8);
                    gd.setStroke(new BasicStroke(2));
                    gd.drawLine(x[i] + 4, y[i] + 25, x[i] + 28, y[i] + 25);
                } else {
                    gd.setColor(new Color(83, 130, 241));
                    gd.fillRect(x[i], y[i], UNIT_Size, UNIT_Size );

            }
        }

        //New Fruit
        gd.drawImage(food, fruitX, fruitY, null);

        //Score Updating
        gd.setColor(new Color(93, 1, 1));
        gd.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        gd.drawString("Score:" + Score, 910 ,870);

        gd.drawImage(logo, 900,0,null);

        if(!running && new_game.isVisible()){
            Gameover(g);
        }
    }



    // Path finding
    public void pathFinding(){
        nodeDemoStack = new Stack<>();
        for(int i=nodeStackDirection.size()-1;i>0;i--){
            nodeDemoStack.add(nodeStackDirection.get(i));
        }
    }


    //Moving Snake Method
    public  void move(){
        for(int i=bodyParts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
            if(!nodeDemoStack.isEmpty()) {
                NodeDemo nodePrev = nodeDemoStack.pop();
                int n=0;
                int xPrev = nodePrev.getY();
                int yPrev = nodePrev.getX();
                if((xPrev*30 - x[0] ==30) && (yPrev*30 - y[0]==0)){// right
                    n = 0;
                }
                else if((x[0] - xPrev*30 == 30)&& (yPrev*30 - y[0]==0)){//left
                    n = 1;
                }
                else if((y[0] - yPrev*30 == 30)&&(xPrev*30 - x[0]==0)){// top
                    n = 2;
                }
                else if((yPrev*30 - y[0] ==30)&&(xPrev*30 - x[0]==0)){//down
                    n = 3;
                }
                switch (n) {
                    case 0:
                        x[0] = x[0] + UNIT_Size;
                        break;
                    case 1:
                    x[0] = x[0] - UNIT_Size;
                        break;
                    case 2:
                    y[0] = y[0] - UNIT_Size;
                        break;
                    case 3:
                        y[0] = y[0] + UNIT_Size;
                        break;
                }
            }
    }

    //New Fruit Method
    public void newfruit() {
        final String[] Food_Images = new String[]{"src/img/ic_orange.png", "src/img/ic_fruit.png", "src/img/ic_cherry.png",
                "src/img/ic_berry.png", "src/img/ic_coconut_.png", "src/img/ic_peach.png", "src/img/ic_watermelon.png", "src/img/ic_orange.png",
                "src/img/ic_pomegranate.png"};
        Random random = new Random();
        fruitX = random.nextInt(900/ UNIT_Size) * UNIT_Size;
        fruitY = random.nextInt(Height/ UNIT_Size) * UNIT_Size;
        int y = fruitX/30;//J
        int x = fruitY/30;//I
        while(!checkWall(x,y)){
            fruitX = random.nextInt(900/ UNIT_Size) * UNIT_Size;
            fruitY = random.nextInt(Height/ UNIT_Size) * UNIT_Size;
            y = fruitX/30;
            x = fruitY/30;
        }
        mazesDemo[fruitY/30][fruitX/30]=3;
        food = new ImageIcon(Food_Images[random.nextInt(8)]).getImage().getScaledInstance(35,35,5);
    }

    public boolean checkWall(int i,int j){
        if(mazesDemo[i][j]==0){
            return false;
        }
        else{
            return true;
        }
    }

    //Check if snakes eat fruit or not
    public void checkFruit(){
        if(x[0]==fruitX && y[0] ==fruitY){
            mazesDemo[fruitY/30][fruitX/30]=1;
            Audio clicked = new Audio("src/sounds/eat.wav");
            clicked.audio.start();
            System.out.println("AppleI:"+fruitY/30+", AppleJ:"+fruitX/30);
            newfruit();
            bodyParts++;
            positionSnake = new ArrayList<>();
            for(int i=0;i<bodyParts;i++){
                NodeDemo nodeDemoPosition = new NodeDemo();
                nodeDemoPosition.setX(x[i]);
                nodeDemoPosition.setY(y[i]);
                positionSnake.add(nodeDemoPosition);
            }

            resetNewVisited();
            System.out.println("X-Now:"+x[0]+", Y-Now:"+y[0]);
            if(bfs.isSelected()){
                System.out.println("BFS checked");
            }
            else if(dfs.isSelected()){
                System.out.println("DFS checked");
                algorithm.setNewVisited();
                System.out.println("I:"+y[0]/30+",J:"+x[0]/30);
                nodeStackDirection = algorithm.dfs(mazesDemo,algorithm.getVisited(),y[0]/30,x[0]/30,positionSnake);
                pathFinding();
            }
            else if(aStart.isSelected()){
                System.out.println("AStart");
            }
            Score+=5;
        }
    }


    //Reset new visited
    public void resetNewVisited(){
        algorithm.setNewVisited();
    }


    //Checking Game over conditions
    public void checkCollision(){
        for(int i=bodyParts; i>0; i--) {
            //If Body Touches itself
            if (x[0] == x[i] && y[0] == y[i])
                running = false;
            //Collision with sides
            if (x[0] >= (Width - 300))  //Right Border
                running = false;
            if (x[0] < 0)              //Left Border
                running = false;
            if (y[0] < 0)              //Up Border
                running = false;
            if (y[0] >= Height)
                running = false;    //Down Border

            if (!running) {
                Audio clicked = new Audio("src/sounds/die.wav");
                clicked.audio.start();
                timer.stop();
            }
        }
    }


    //Game Over Message
    public void Gameover(Graphics g){
        g.setColor(new Color(157, 6, 6));
        g.setFont(new Font("Georgia", Font.BOLD, 60));
        FontMetrics fontMetrics  = getFontMetrics(g.getFont());
        g.drawString("Game Over", (Width-fontMetrics.stringWidth("Game Over"))/2, (int) ((Height-fontMetrics.stringWidth("Game Over"))/1.5));
        System.out.println("Vị trí chiếm chỗ:");
    }
    //Play Button Logic
    void play(){
        play.setVisible(false);
        pausee.setVisible(true);
        timer.start();
    }
    //Pause Button Logic
    void pause() {
        play.setVisible(true);
        pausee.setVisible(false);
        timer.stop();
    }

    void audio(){
        Audio clicked = new Audio("src/sounds/click.wav");
        clicked.audio.start();
    }

    //Action Performed Method
    // Game loop
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkCollision();
            checkFruit();

        }
        repaint();
        if(e.getSource() == bfs){
            System.out.println("BFS checked");
        }
        else if(e.getSource() == dfs){
            System.out.println("DFS checked");
        }
        else if( e.getSource()==aStart){
            System.out.println("AStart");
        }
        if(e.getSource() == start)
            startGame();

        if(e.getSource() == new_game){
            New_game();
//            play();
            repaint();
        }

        if(e.getSource() == about){
            if(new_game.isVisible() && pausee.isVisible())
                pause();
            new About();
        }

        if(e.getSource() == help){
            if(new_game.isVisible() && pausee.isVisible())
                pause();;
           new Help();
        }

        if(e.getSource() == pausee)
            pause();

        if(e.getSource() == play)
            play();
    }

    //KeyBoard Control
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

            switch (e.getKeyCode()){
//                case KeyEvent.VK_LEFT:
//                    if(direction!='R')
//                        direction = 'L';
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    if(direction!='L')
//                        direction = 'R';
//                    break;
//
//                case KeyEvent.VK_UP:
//                    if(direction!='D')
//                        direction = 'U';
//                    break;
//                case KeyEvent.VK_DOWN:
//                    if(direction!='U')
//                        direction = 'D';
//                    break;
                case KeyEvent.VK_N:
                    if(!start.isVisible()) {
                     audio();
                        New_game();
                    }

                    break;
                case KeyEvent.VK_ENTER:
                    if(!new_game.isVisible()) {
                        audio();
                        startGame();
                    }
                    break;
                case KeyEvent.VK_P:
                    if(pausee.isVisible()){
                        audio();
                        pause();
                    }
                    else{
                        audio();
                        play();
                    }
                    break;
            }
        }
    }
}

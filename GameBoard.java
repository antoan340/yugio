import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;



public class GameBoard extends JFrame implements MouseListener {
    protected Monsters[][] monsters;
    protected Monsters selectedMonster;
    protected Magics[][] magics;
    protected Magics selectedMagic;
    int rand, gameBegin =0 ,diceO=0,diceT=0;
    Color ppl=Color.WHITE;
    int rowStart, colStart;
    int gameTurn=0;
    JLabel labelPo = new JLabel();
    JLabel labelPt = new JLabel();
    JLabel attackL = new JLabel();
    JLabel moveL = new JLabel();
    JLabel endL = new JLabel();
int move,attack;
    public GameBoard() {
        this.monsters = new Monsters[18][18];
        this.magics = new Magics[18][18];
        labelPo.setText("Player 1 Dice = "+diceO);
        labelPo.setBounds(610,200,100,50);
        labelPt.setText("Player 2 Dice = "+diceT);
        labelPt.setBounds(610,400,100,50);
        attackL.setText("Attack");
        attackL.setBounds(610,100,100,50);
        moveL.setText("Move");
        moveL.setBounds(14*50+10,100,100,50);
        endL.setText("END ");
        endL.setBounds(16*50+10,100,100,50);
        this.add(labelPt);
        this.add(labelPo);
        this.add(attackL);
        this.add(moveL);
        this.add(endL);
        this.setLayout(null);

        setTitle("YUGIOHHHHHHHHH");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }
    public void draw(int row,int turn){
        if (turn == 1) {
             ppl=Color.blue;
        }else ppl=Color.WHITE;
        for (int i = 0 ; i<6;i++){
            rand = ThreadLocalRandom.current().nextInt(1, 7);
            switch (rand) {
                case 1 -> this.monsters[row][i] = (new Monsters(row,i,Color.MAGENTA,ppl,"Drunken Knight",5,5,5,5));
                case 2 -> this.monsters[row][i] = (new Monsters(row,i,Color.PINK,ppl,"Sofisticated Sam",10,5,4,1));
                case 3 -> this.monsters[row][i] = (new Monsters(row,i,Color.GREEN,ppl,"Sand turtle",5,10,1,4));
                case 4 -> this.monsters[row][i] = (new Monsters(row,i,Color.YELLOW,ppl,"Magic cat",5,5,5,5));
                case 5 -> this.monsters[row][i] = (new Monsters(row,i,Color.RED,ppl,"Reckless canibal",4,6,8,10));
                default ->this.monsters[row][i] = (new Monsters(row,i,Color.BLACK,ppl,"Dog eating bug",10,2,8,5));
            }
        }
        rand = ThreadLocalRandom.current().nextInt(1, 7);
        switch (rand) {
            case 1 -> this.magics[row][7] = (new Magics(row, 7, Color.MAGENTA, "Heal the world", 5));
            case 2 -> this.magics[row][7] = (new Magics(row, 7, Color.PINK, "Move", 10));
            case 3 -> this.magics[row][7] = (new Magics(row, 7, Color.GREEN, "Defence up", 5));
            case 4 -> this.magics[row][7] = (new Magics(row, 7, Color.YELLOW, "Attack on", 5));
            default -> this.magics[row][7] = (new Magics(row, 7, Color.RED, "War", 4));
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());

        if(gameBegin<1){
            if(row==6&&col==13&&diceO==0){
                rand = ThreadLocalRandom.current().nextInt(1, 7);
                diceO=rand;
                labelPo.setText("Player 1 Dice = "+diceO);
                draw(1,0);

            }
            if(row==10&&col==13&&diceT==0){
                rand = ThreadLocalRandom.current().nextInt(1, 7);
                diceT =rand;
                labelPt.setText("Player 2 Dice = "+diceT);
                draw(14,1);
            }
            repaint();
            if(diceO>0&&diceT>0){
                if(diceO>diceT){
                    gameTurn++;
                }
                gameBegin=2;

            }
        }
        else {
            if (row < 15 && col < 14 && row > 0) {
                if(selectedMonster!=null||selectedMagic!=null){
                    if(attack==1){
                        attackMonsters(gameTurn, row, col, rowStart, colStart);
                        attack=0;

                    }
                    if(move==1) {
                        moveMonsters(gameTurn, row, col, rowStart, colStart);
                        move=0;
                    }
                }
                else {
                    rowStart=row;
                    colStart=col;
                    if (hasMonster(row, col)) {
                        selectedMonster = monsters[row][col];
                    }
                }
            }
            if(row==3&&col==13)

                attack=1;
            if(row==3&&col==17){
                System.out.println("next turn");
                gameTurn++;
            }
            if(row==3&&col==15){
                move=1;
            }
        }

    }
public void moveMonsters(int turn,int row,int col,int rowStart,int colStart){
    if (turn %2==0) {
        ppl=Color.blue;
    }else ppl=Color.WHITE;
    if (selectedMonster != null) {
        if(monsters[rowStart][colStart].id.equals("Drunken Knight")){
            this.monsters[row][col] = (new Monsters(row,col,Color.MAGENTA,ppl,"Drunken Knight",5,5,5,5));
        }
        if(monsters[rowStart][colStart].id.equals("Sofisticated Sam")){
            this.monsters[row][col] = (new Monsters(row,col,Color.PINK,ppl,"Sofisticated Sam",10,5,4,1));
        }
        if(monsters[rowStart][colStart].id.equals("Sand turtle")){
            this.monsters[row][col] = (new Monsters(row,col,Color.GREEN,ppl,"Sand turtle",5,10,1,4));
        }
        if(monsters[rowStart][colStart].id.equals("Magic cat")){
            this.monsters[row][col] = (new Monsters(row,col,Color.YELLOW,ppl,"Magic cat",5,5,5,5));
        }
        if(monsters[rowStart][colStart].id.equals("Reckless canibal")){
            this.monsters[row][col] = (new Monsters(row,col,Color.RED,ppl,"Reckless canibal",4,6,8,10));
        }
        if(monsters[rowStart][colStart].id.equals("Dog eating bug")){
            this.monsters[row][col] = (new Monsters(row,col,Color.BLACK,ppl,"Dog eating bug",10,2,8,5));
        }
        if(row!=rowStart||col!=colStart)
        monsters[rowStart][colStart]=null;
        repaint();
        selectedMonster=null;
    }
}

public void attackMonsters(int turn,int row,int col,int rowStart,int colStart){
        if(hasMonster(row,col)){
           if( monsters[rowStart][colStart].attack>monsters[row][col].defence){
               monsters[row][col]=null;
           }
           else monsters[row][col].defence-=monsters[rowStart][colStart].attack;
        }
}







    @Override

    public void paint(Graphics g) {
        super.paint(g);
        GameTile tile = new GameTile(0,0);
        tile.render(g);
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                renderGamePiece(g, row, col);
            }
        }

    }






    private Monsters getMonster(int row, int col) {
        return this.monsters[row][col];

    }

    private boolean hasMonster(int row, int col) {
        return this.getMonster(row, col) != null;
    }
    private Magics getMagic(int row, int col) {
        return this.magics[row][col];

    }

    private boolean hasMagic(int row, int col) {
        return this.getMagic(row, col) != null;
    }




    private void renderGamePiece(Graphics g, int row, int col) {
       if (this.hasMonster(row, col)) {
           Monsters p = (Monsters) this.getMonster(row, col);
         p.render(g);

       }
        if (this.hasMagic(row, col)) {
            Magics p1 = (Magics) this.getMagic(row, col);
            p1.render(g);

        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }

}
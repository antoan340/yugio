
import java.awt.*;

public class Monsters extends Cards {
    public Color color,color1;

    public Monsters(int row, int col, Color color,Color color1, String id, int ap, int dp, int mana, int move) {
        super();
        this.row = row;
        this.col = col;
        this.color = color;
        this.color1 = color1;
        this.id = id;
        this.attack = ap;
        this.defence= dp;
        this.mana= mana;
        this.move = move;
    }

    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(this.color);
        g.fillOval(x+10, y, 48, 48);
        g.setColor(this.color1);
        g.fillOval(x+15, y+5, 20, 20);
    }

}
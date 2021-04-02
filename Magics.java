import java.awt.*;

public class Magics extends Cards {
    public Color color,color1;

    public Magics(int row, int col, Color color, String id, int ap) {
        super();
        this.row = row;
        this.col = col;
        this.color = color;
        this.color1 = color1;
        this.id = id;
    }

    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(this.color);
        g.setColor(this.color1);
        g.fillRect(x+10, y, 48, 48);
    }

}
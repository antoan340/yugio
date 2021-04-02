
import java.awt.*;

public class GameTile {

    public static final int TILE_SIZE = 50;

    private float row;
    private float col;
    private float tileSize;


    public GameTile(float row, float col) {

        this.row        = row;
        this.col        = col;
        this.tileSize   = 50;
    }

    public void render(Graphics g){
        RenderCubes(g);
        RenderBorders(g);
        renderGameOtions(g);

    }

    public void RenderCubes(Graphics g) {
        g.setColor(Color.GRAY);
        for(int i=10;i<610;i+=50){
            for (int j=100; j<700;j+=50){
                g.fillRect(i,j,50,50);
            }
        }
    }

    public void RenderBorders(Graphics g){
        g.setColor(Color.black);
        for (int i=100; i<=700 ;i+=50){
            for(int j=8;j<=608;j+=50){
                if(i<700&&j<608) {
                    g.fillRect(j, i, 2, 50);
                    g.fillRect(j, i, 50, 2);
                }  if(i==700&&j<608){
                    g.fillRect(j, i, 50, 2);
                }
                if(i<700&&j==608){
                    g.fillRect(j, i, 2, 52);
                }

            }
        }
        g.fillRect(660,300,50,50);
        g.fillRect(660,500,50,50);
    }
    public void renderGameOtions(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(660,150,50,50);
        g.setColor(Color.RED);
        g.fillRect(760,150,50,50);
        g.setColor(Color.GRAY);
        g.fillRect(860,150,50,50);
    }
}
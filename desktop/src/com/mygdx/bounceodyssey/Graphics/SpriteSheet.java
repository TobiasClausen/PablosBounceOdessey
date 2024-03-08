package com.mygdx.bounceodyssey.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {
    private Texture sheet;
    private TextureRegion[][] sprites;

    public SpriteSheet(String fileName, int rows, int cols) {

        sheet = new Texture(fileName);


        sprites = new TextureRegion[rows][cols];


        int spriteWidth = sheet.getWidth() / cols;
        int spriteHeight = sheet.getHeight() / rows;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[i][j] = new TextureRegion(sheet, j * spriteWidth, i * spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }


    public TextureRegion getSprite(int row, int col) {
        return sprites[row][col];
    }


    public void dispose() {
        sheet.dispose();
    }

}




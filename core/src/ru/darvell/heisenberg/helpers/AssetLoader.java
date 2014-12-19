package ru.darvell.heisenberg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Класс для загрузки ресурсов
 */
public class AssetLoader {

    public static Animation heaisAnimation;
    public static TextureRegion textur;
    public static TextureRegion sandBox;
    public static TextureRegion bullet_r;
    public static TextureRegion map;

    public static void load(){

        Texture mainTExture = new Texture(Gdx.files.internal("data/tiles/main.png"));
        sandBox = new TextureRegion(mainTExture, 0, 0, 32, 32);

        Texture texture1 = new Texture(Gdx.files.internal("data/heisenberg_r.png"));
        TextureRegion textureRegion1 = new TextureRegion(texture1,0,0,32,64);

        textur = new TextureRegion(texture1,0,0,32,64);

        Texture bulletTexture = new Texture(Gdx.files.internal("data/bullet_r.png"));
        bullet_r = new TextureRegion(bulletTexture, 0, 0, 5, 3);

        Texture mapTexture = new Texture(Gdx.files.internal("data/maps/testmap.png"));
        map = new TextureRegion(mapTexture, 0, 0, 800, 400);

        Texture texture2 = new Texture(Gdx.files.internal("data/heisenberg_r_1.png"));
        TextureRegion textureRegion2 = new TextureRegion(texture2,0,0,32,64);

        Texture texture3 = new Texture(Gdx.files.internal("data/heisenberg_r_2.png"));
        TextureRegion textureRegion3 = new TextureRegion(texture3,0,0,32,64);

        TextureRegion[] heisSprites = {textureRegion1, textureRegion2, textureRegion3};
        heaisAnimation = new Animation(0.06f, heisSprites);

    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
//        texture.dispose();
    }
}

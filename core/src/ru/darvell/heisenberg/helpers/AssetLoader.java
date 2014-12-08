package ru.darvell.heisenberg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by darvell on 08.12.14.
 */
public class AssetLoader {

    public static Animation heaisAnimation;
    public  static TextureRegion textur;

    public static void load(){


        Texture texture1 = new Texture(Gdx.files.internal("data/heisenberg_r.png"));
        TextureRegion textureRegion1 = new TextureRegion(texture1,0,0,32,64);

        textur = new TextureRegion(texture1,0,0,32,64);

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

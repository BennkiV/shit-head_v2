package com.mygdx.shithead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cards {

        public enum Ability{
            STARTER, NORMAL, ALWAYS, REVERSE, UNDER, PASS, END, ACE, JOKER, ERROR
        }
        protected String kind;
        protected int value;
        protected Ability ability;
        protected String imageDirect;
        protected Texture cardTexture;
        protected Rectangle rectangle;

        //_______________________________________________
        //  Set
        public void setValue(int _value){
            value = _value;
        }

        public void setKind(String _kind){
            kind = _kind;
        }

        public void setImageDirect(String _direct){
            imageDirect = _direct;
            cardTexture = new Texture(Gdx.files.internal(imageDirect));
        }

        public void setRectangle(Rectangle _rectangle){
            rectangle = _rectangle;
        }

        public void setRectangle(){
            rectangle = new Rectangle();
            rectangle.x = 0;
            rectangle.y = 0;
            rectangle.width = 165;
            rectangle.height = 242;
        }

        public void setAbility(Ability _ability){
            ability = _ability;
        }

        //________________________________________________
        // Get
        public int getValue(){
            return value;
        }

        public String getKind(){
            return kind;
        }

        public String getImageDirect(){
            return imageDirect;
        }

        public Texture getCardTexture(){
            return cardTexture;
        }

        public Rectangle getRectangle() {
        return rectangle;
    }

        // return the ability as a short for 'game'
        public Ability getAbility(){
            return ability ;
        }

        //__________________________________________________
        // Other
        public void editRectangle(float _x, float _y){
            rectangle.x = _x;
            rectangle.y = _y;
    }

        public void editTextureDownBoardCards(){
            cardTexture = new Texture(Gdx.files.internal("CardBack.png"));
        }

        // restores the texture of the card
        public void restoreTexture(){
            switch (value){
                case(1):    cardTexture = new Texture(Gdx.files.internal(kind+"_A.png")); break;
                case(11):   cardTexture = new Texture(Gdx.files.internal(kind+"_J.png")); break;
                case(12):   cardTexture = new Texture(Gdx.files.internal(kind+"_Q.png")); break;
                case(13):   cardTexture = new Texture(Gdx.files.internal(kind+"_K.png")); break;
                default: cardTexture = new Texture(Gdx.files.internal(kind+"_"+value+".png"));
            }
        }

        //  Print
        public void printCards(){
            System.out.printf("%s, %d\n", kind, value);
        }
}

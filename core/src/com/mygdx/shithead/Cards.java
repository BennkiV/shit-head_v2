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
        protected Texture backTexture;
        protected Rectangle rectangle;

        Cards() {}

        Cards(String kind, int value) {
            this.kind = kind;
            this.value = value;
            this.ability = getAbilityByValue();

            cardTexture = new Texture(Gdx.files.internal(getTextureName()));
            backTexture = new Texture(Gdx.files.internal("CardBack.png"));
            imageDirect = getTextureName();

            rectangle = new Rectangle();
            rectangle.x = 0;
            rectangle.y = 0;
            rectangle.width = 165;
            rectangle.height = 242;

        }

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
        public Ability getAbilityByValue() {
            switch (this.value) {
                case 1:
                    return Ability.ACE;
                case 2:
                    return Ability.ALWAYS;
                case 3:
                    return Ability.REVERSE;
                case 4:
                    return Ability.STARTER;
                case 7:
                    return Ability.UNDER;
                case 8:
                    return Ability.PASS;
                case 10:
                    return Ability.END;
                default:
                    return Ability.NORMAL;
            }
        }

        public String getTextureName() {
            String color = this.kind;
            int value = this.value;
            String val;

            switch (value) {
                case 1:
                    val = "A";
                    break;
                case 11:
                    val = "J";
                    break;
                case 12:
                    val = "Q";
                    break;
                case 13:
                    val = "K";
                    break;
                default:
                    val = Integer.toString(value);
                    break;

            }

            String path = color+"_"+val+".png";

            return path;
        }

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
            System.out.println(kind+", "+value);
        }
}

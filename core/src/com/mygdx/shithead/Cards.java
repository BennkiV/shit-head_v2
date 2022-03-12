package com.mygdx.shithead;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Card class contains card information.
 * Kind = Symbol like heart
 * Value = number of cared, like 8 of ace
 * imageDirect = contains path to the card image
 * cardTexture = png of the card (front)
 * backTexture = png of the card (back)
 * rectangle = rectangle describing the card mathematical
 *
 * Ability = special functionality in shithead
 */
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

    /**
     * Basic constructor.
     */
    Cards() {}

    /**
     * Constructor to set a cared by the given kind and value.
     * The kind decides if its a heart, ace, pic, spades.
     * The value sets the value like 1,2,..., A.
     * The card texture is selected out of the android -> assets
     * folder by using the function getTextureName to select the
     * correct name.
     * Same for backTexture but the png name is predefined.
     * Rectangle is defined and represents the size of the card.png.
     * @param kind
     * @param value
     */
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

    /**
     * Sets the value.
     * @param _value
     */
    public void setValue(int _value){
            value = _value;
        }

    /**
     * Sets the kind.
     * @param _kind
     */
    public void setKind(String _kind){
            kind = _kind;
       }

    /**
     * Set imageDirect, by parsing the png name.
     * @param _direct
     */
    public void setImageDirect(String _direct){
            imageDirect = _direct;
            cardTexture = new Texture(Gdx.files.internal(imageDirect));
    }

    /**
     * Set the Rectangle by parsing the new Rectangle.
     * @param _rectangle
     */
    public void setRectangle(Rectangle _rectangle){
            rectangle = _rectangle;
    }

    /**
     * Can be used to reset the rectangle to its prestate (Card.png size)
     */
    public void setRectangle(){
            rectangle = new Rectangle();
            rectangle.x = 0;
            rectangle.y = 0;
            rectangle.width = 165;
            rectangle.height = 242;
        }

    /**
     * Set the ability.
     * @param _ability
     */
    public void setAbility(Ability _ability){
            ability = _ability;
        }

        //________________________________________________
        // Get

    /**
     * Returns ability as enum Ability, by
     * given cared value.
     * @return
     */
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

    /**
     * Returns the Texture name of the selected cared.
     * Name contains kind_value.png, value for J,Q,K,A
     * is set by the given value via a switch.
     * @return
     */
    public String getTextureName() {
            String val;

            switch (this.value) {
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
                    val = Integer.toString(this.value);
                    break;
            }

            return this.kind+"_"+val+".png";
    }

    /**
     * Return value.
     * @return
     */
    public int getValue(){
            return value;
    }

    /**
     * Return kind.
     * @return
     */
    public String getKind(){
            return kind;
    }

    /**
     * Return imageDirect
     * @return
     */
    public String getImageDirect(){
            return imageDirect;
        }

    /**
     * Retrun CaredTexture
     * @return
     */
    public Texture getCardTexture(){
            return cardTexture;
    }

    /**
     * Return rectangle.
     * @return
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * return the ability as a short for 'game'
     * @return
     */
    public Ability getAbility(){
            return ability ;
    }

        //__________________________________________________
        // Other

    /**
     * Edit size of Rectangle
     * @param _x
     * @param _y
     */
    public void editRectangle(float _x, float _y){
            rectangle.x = _x;
            rectangle.y = _y;
    }

    /**
     *
     */
    public void editTextureDownBoardCards(){
            cardTexture = new Texture(Gdx.files.internal("CardBack.png"));
        }

        // restores the texture of the card

    /**
     * Restores texture of a cared, used for up-side-down cards
     */
    public void restoreTexture(){
            switch (value){
                case(1):    cardTexture = new Texture(Gdx.files.internal(kind+"_A.png")); break;
                case(11):   cardTexture = new Texture(Gdx.files.internal(kind+"_J.png")); break;
                case(12):   cardTexture = new Texture(Gdx.files.internal(kind+"_Q.png")); break;
                case(13):   cardTexture = new Texture(Gdx.files.internal(kind+"_K.png")); break;
                default: cardTexture = new Texture(Gdx.files.internal(kind+"_"+value+".png"));
            }
    }

    /**
     * Prints cared in console
     */
    public void printCards(){
            System.out.println(kind+", "+value);
        }
}

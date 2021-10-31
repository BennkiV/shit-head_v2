package com.mygdx.shithead;

public class Cards {
        protected char kind;
        protected int value;
        protected String imageDirect;

        //  Set
        public void setValue(int _value){
            value = _value;
        }

        public void setKind(char _kind){
            kind = _kind;
        }

        public void setImageDirect(String _direct){
            imageDirect = _direct;
        }

        // Get
        public int getValue(){
            return value;
        }

        public char getKind(){
            return kind;
        }

        public String getImageDirect(){
            return imageDirect;
        }

        //  Print
        public void printCards(){
            System.out.printf("%c, %d\n", kind, value);
        }
}

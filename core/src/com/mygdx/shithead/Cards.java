package com.mygdx.shithead;

public class Cards {
        protected char kind;
        protected int value;

        //  Set
        public void setValue(int _value){
            value = _value;
        }

        public void setKind(char _kind){
            kind = _kind;
        }

        // Get
        public int getValue(){
            return value;
        }

        public char getKind(){
            return kind;
        }

        //  Print
        public void printCards(){
            System.out.printf("%c, %d\n", kind, value);
        }
}

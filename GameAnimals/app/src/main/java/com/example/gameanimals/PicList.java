package com.example.gameanimals;

public class PicList {
    static public String getName(int index){
        String name;
        switch(index){
            case 0:{
                name="cat";
                break;
            }
            case 1:{
                name="elephant";
                break;
            }
            case 2:{
                name="fish";
                break;
            }
            case 3:{
                name="giraffe";
                break;
            }
            case 4:{
                name="honey";
                break;
            }
            case 5:{
                name="hypo";
                break;
            }
            case 6:{
                name="lion";
                break;
            }
            case 7:{
                name="pig";
                break;
            }
            case 8:{
                name="tiger";
                break;
            }
            case 9:{
                name="wolf";
                break;
            }
            default:{
                name="";
            }
        }
        return name;
    }
}

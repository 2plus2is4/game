package to_come;

import tests.Iterator;
import tests.Iterator_concrete;
import java.util.ArrayList;

public class FlyWeight {

    ArrayList<String> sizes=new ArrayList<String>();

    FlyWeight( int weidth, int height, State state){
        sizes.add("m");
        sizes.add("l");
        sizes.add("xl");
        for(int i=0; i < 7; ++i) {
            PlateFactory.getPlate(getRandomSize(),weidth,height,state,"red");
            PlateFactory.getPlate(getRandomSize(),weidth,height,state,"blue");
            PlateFactory.getPlate(getRandomSize(),weidth,height,state,"green");
        }
    }

    private String getRandomSize() {
        return sizes.get((int)(Math.random()*sizes.size()));
    }

}
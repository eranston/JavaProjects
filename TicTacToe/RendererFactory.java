import java.io.Console;

public class RendererFactory {
    


    RendererFactory(){

    }

    public Renderer buildRenderer(String type, int size ){
        //TODO: check for type corectness
        String lowerCaseType = type.toLowerCase();
        if(lowerCaseType == "console"){
            ConsoleRenderer renderer = new ConsoleRenderer(size);
            return renderer;
        }
        if (lowerCaseType == "void") {
            VoidRenderer renderer = new VoidRenderer();
            return renderer;
            
        }
        //TODO: add more player type
        return null;
    }

}

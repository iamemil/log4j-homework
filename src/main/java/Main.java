
import org.apache.logging.log4j.*;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Marker AssertionMarker = MarkerManager.getMarker("ASSERTION");
    private static final Marker GibberishMarker = MarkerManager.getMarker("GIBBERISH");

    public static void main(String[] args) {
        int iteration =0;
        try{
            iteration = Integer.parseInt(args[0]);

            String song[] ={
                    "Hickory, dickory, dock.",
                    "The mouse ran up the clock.",
                    "The clock struck one,",
                    "The mouse ran down,",
                    "Hickory, dickory, dock.",
                    "The mouse ran up the clock.",
                    "The clock struck one,",
                    "The mouse ran down,",
                    "Hickory, dickory, dock."
            };
            for (int i =0; i < iteration;i++){
                ThreadContext.push(String.valueOf(i));

                if(song[i].contains("The mouse")){
                    logger.debug(AssertionMarker,song[i]);
                }
                else if(song[i].contains("dickory")){
                    logger.error(GibberishMarker,song[i]);
                }
                else{
                    logger.fatal(song[i]);
                }

                ThreadContext.clearAll();
                try{
                    Thread.sleep(1500);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        } catch (NumberFormatException nfe){
            System.out.println("argument should be int");
        }


    }

}

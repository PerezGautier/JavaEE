package bowlingTest;


import Bowling.MultiPlayerBowling;
import bowling.SinglePlayerGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author pedago
 */
public class MultiPlayerBowlingTest {
    private MultiPlayerBowling bowling1;
    private String[]playerList = { "John","Paul","Georges","Ringo"};
    
    @Before
    public void setUp() {
            bowling1 = new MultiPlayerBowling();
    }
    
    @After
    public void tearDown(){
        bowling1 = null;
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void StartNewGameTest() throws Exception{
        String[]vide = {};
        bowling1.startNewGame(vide);
    }

}

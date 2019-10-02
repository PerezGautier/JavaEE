package bowlingTest;


import Bowling.MultiPlayerBowling;
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
    public void startNewGameTestException() throws Exception{
        String[]vide = {};
        bowling1.startNewGame(vide);
    }
    @Test
    public void startNewGameTest() throws Exception{
        assertEquals("Prochain tir : joueur John, tour n°1, boule n°1",bowling1.startNewGame(this.playerList));
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void LancerTestException() throws Exception{
        bowling1.lancer(9);
    }
    
    @Test
    public void lancerTest() throws Exception{
        bowling1.startNewGame(this.playerList);
        assertEquals("Prochain tir : joueur Paul, tour n°1, boule n°1",bowling1.lancer(8));
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void scoreTestException() throws Exception{
        bowling1.startNewGame(this.playerList);
        bowling1.scoreFor("Jack");
    }
    
    @Test
    public void scoreTest() throws Exception{
        bowling1.startNewGame(this.playerList);
        bowling1.lancer(8);
        assertEquals(8,bowling1.scoreFor("John"));
        assertEquals(0,bowling1.scoreFor("Paul"));
        bowling1.lancer(2);
        assertEquals(2,bowling1.scoreFor("Paul"));
    }

}

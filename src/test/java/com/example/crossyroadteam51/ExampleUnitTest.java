package com.example.crossyroadteam51;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import android.widget.ImageView;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private int spriteXTest;
    private int spriteYTest;
    private int scoreTest;

    @Test
    public void moveUpTest() {
        spriteXTest = 20;
        spriteYTest = 700;
        moveUp();
        assertEquals(20, spriteXTest);
        assertEquals(680, spriteYTest);
    }

    @Test
    public void moveDownTest() {
        spriteXTest = 20;
        spriteYTest = 700;
        moveDown();
        assertEquals(20, spriteXTest);
        assertEquals(720, spriteYTest);
    }

    @Test
    public void moveRightTest() {
        spriteXTest = 20;
        spriteYTest = 700;
        moveRight();
        assertEquals(40, spriteXTest);
        assertEquals(700, spriteYTest);
    }

    @Test
    public void moveLeftTest() {
        spriteXTest = 20;
        spriteYTest = 700;
        moveLeft();
        assertEquals(0, spriteXTest);
        assertEquals(700, spriteYTest);
    }

    public void moveUp() {
        if (canMoveY(spriteYTest - 20)) {
            spriteYTest -= 20; // move the view 10 pixels up
        }
    }

    public void moveDown() {
        if (canMoveY(spriteYTest + 10)) {
            spriteYTest += 20; // move the view 10 pixels up
        }
    }

    public void moveLeft() {
        if (canMoveX(spriteXTest - 20)) {
            spriteXTest -= 20; // move the view 10 pixels to the left
        }
    }

    public void moveRight() {
        if (canMoveX(spriteXTest + 10)) {
            spriteXTest += 20;// move the view 10 pixels to the right
        }
    }

    public boolean canMoveY(int y) {
        if (y > 1468 || y < - 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canMoveX(int x) {
        if (x + 10 > 920 || x + 10 < -60) {
            return false;
        } else {
            return true;
        }
    }
    
     @Test
    public void testNullName() {
        GameActivity myMock = mock(GameActivity.class);
        String result = myMock.nullName(myMock.getNameText());
        assertNull(result);
    }

    @Test
    public void testNullChar() {
        GameActivity myMock = mock(GameActivity.class);
        String result = myMock.nullChar(myMock.getCharText());
        assertNull(result);
    }

    @Test
    public void testNullDiff() {
        GameActivity myMock = mock(GameActivity.class);
        String result = myMock.nullDiff(myMock.getDiffText());
        assertNull(result);
    }
    @Test
    public void testNullLives() {
        GameActivity myMock = mock(GameActivity.class);
        String result = myMock.nullLives(myMock.getLivesText());
        assertNull(result);
    }
    //public void test

    /**
    @Test
    public void testCanMoveY() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        boolean result = myMock.canMove(myMock.spriteX, myMock.spriteY);
        assertEquals(true, result);
    }
    
    @Test
    public void testCanMoveX() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        boolean result = myMock.canMove(myMock.spriteX);
        assertEquals(true, result);
    }
     */

    /**
    @Test
    public void testMoveUp() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        myMock.moveUp(myMock.getSprite());
    }
    
    @Test
    public void testMoveDown() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        myMock.moveDown(myMock.getSprite());
    }
    
    @Test
    public void testMoveLeft() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        myMock.moveLeft(myMock.getSprite());
    }
    
    @Test
    public void testMoveRight() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        myMock.moveRight(myMock.getSprite());
    }
    */

    /**
     * tests if car generates on screen
     */

//    @Test
//    public void testCarGenerates() {
//        MainGameActivity myMock = mock(MainGameActivity.class);
//        ImageView myCar = mock(ImageView.class);
//        when(myMock.getCar()).thenReturn(myCar);
//        assertNotNull(myMock.getCar());
//    }

    /**
     * tests if bus generates on screen
     */

//    @Test
//    public void testBusGenerates() {
//        MainGameActivity myMock = mock(MainGameActivity.class);
//        ImageView myBus = mock(ImageView.class);
//        when(myMock.getBus()).thenReturn(myBus);
//        assertNotNull(myMock.getBus());
//    }

    /**
     * tests if truck generates on screen
     */
//    @Test
//    public void testTruckGenerates() {
//        MainGameActivity myMock = mock(MainGameActivity.class);
//        ImageView myTruck = mock(ImageView.class);
//        when(myMock.getTruck()).thenReturn(myTruck);
//        assertNotNull(myMock.getTruck());
//    }
//
//
//    /**
//     * tests if vehicles coordinates are reset to correct X positions
//     */
//    @Test
//    public void testVehicleXSpawns() {
//        MainGameActivity myMock = mock(MainGameActivity.class);
//        assertEquals(myMock.getCarX(), myMock.getBusX(), myMock.getTruckX());
//    }
//
//    /**
//     * tests if vehicles coordinates are reset to correct Y positions
//     */
//    @Test
//    public void testVehicleYSpawns() {
//        MainGameActivity myMock = mock(MainGameActivity.class);
//        assertEquals(myMock.getCarY(), myMock.getBusY(), myMock.getTruckY());
//    }
    /**
    @Test
    public void testScoreIncreases10() {
        MainGameActivity testClass = new MainGameActivity();
        spriteYTest = 2000;
        moveUp();
        testClass.updateScore();
        assertEquals(2040, testClass.getScore());
    }

    @Test
    public void testScoreIncreases20() {
        MainGameActivity testClass = new MainGameActivity();
        spriteYTest = 1500;
        moveUp();
        testClass.updateScore();
        assertEquals(1520, testClass.getScore());
    }

    @Test
    public void testScoreIncreases30() {
        MainGameActivity testClass = new MainGameActivity();
        spriteYTest = 765;
        moveUp();
        testClass.updateScore();
        assertEquals(795, testClass.getScore());
    }

    @Test
    public void testScoreIncreases40() {
        MainGameActivity testClass = new MainGameActivity();
        spriteYTest = 600;
        moveUp();
        testClass.updateScore();
        assertEquals(640, testClass.getScore());
    }
    */
}

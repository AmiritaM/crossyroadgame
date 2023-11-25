package com.example.crossyroadteam51;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Sprint5UnitTest {

    @Test
    public void testCollisionLog1() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog1(true);
            return null;
        }).when(myMock).checkCollisions();

        myMock.checkCollisions();
        assertFalse(myMock.isTouchingLog1());
    }

    @Test
    public void testNoCollisionLog1() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog1(false);
            return null;
        }).when(myMock).checkCollisions();

        myMock.checkCollisions();
        assertFalse(myMock.isTouchingLog1());
    }

    @Test
    public void testCollisionLog2() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog2(true);
            return null;
        }).when(myMock).checkCollisions();

        myMock.checkCollisions();
        assertFalse(myMock.isTouchingLog2());
    }

    @Test
    public void testNoCollisionLog2() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog2(false);
            return null;
        }).when(myMock).checkCollisions();

        myMock.checkCollisions();
        assertFalse(myMock.isTouchingLog2());
    }

    @Test
    public void testLog1Generates() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        ImageView log1 = mock(ImageView.class);
        when(myMock.getLog1()).thenReturn(log1);
        assertNotNull(myMock.getLog1());
    }

    @Test
    public void testLog2Generates() {
        MainGameActivity myMock = mock(MainGameActivity.class);
        ImageView log2 = mock(ImageView.class);
        when(myMock.getLog2()).thenReturn(log2);
        assertNotNull(myMock.getLog2());
    }

    @Test
    public void testLog1X() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog1(true);
            return null;
        }).when(myMock).checkCollisions();

        assertEquals(myMock.getSpriteX(), myMock.getLog1X());
    }

    @Test
    public void testLog1Y() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog1(true);
            return null;
        }).when(myMock).checkCollisions();

        assertEquals(myMock.getSpriteY(), myMock.getLog1Y());
    }

    @Test
    public void testLog2X() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog2(true);
            return null;
        }).when(myMock).checkCollisions();

        assertEquals(myMock.getSpriteX(), myMock.getLog2X());
    }

    @Test
    public void testLog2Y() {
        MainGameActivity myMock = mock(MainGameActivity.class);

        doAnswer(invocation -> {
            myMock.setTouchingLog2(true);
            return null;
        }).when(myMock).checkCollisions();

        assertEquals(myMock.getSpriteY(), myMock.getLog2Y());
    }

}
package com.davena.exercises.Third_lesson.basic_ClickCounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClickCounterTest {

    @Test
    void undo() {
    }

    @Test
    void reset() {

        ClickCounter cc = new ClickCounter();
        cc.click();
        cc.click();
        cc.click();
        assertEquals(3, cc.getValue());
        cc.reset();
        assertEquals(0, cc.getValue());
    }

    @Test
    void testToString() {

    }

    @Test
    void getValue() {
        ClickCounter cc = new ClickCounter();
        cc.click();
        cc.click();
        cc.click();
        assertEquals(3, cc.getValue());
    }

    @Test
    void click() {
        ClickCounter cc = new ClickCounter();
        assertEquals(0, cc.getValue());
        cc.click();
        assertEquals(1, cc.getValue());
    }
}
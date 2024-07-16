package io.github.gabrielnavas.mockito_advanced.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
 * Used when you need to force parameters and returns,
 * legacy systems or libraries that we don't know exactly how to work
 * */

public class SpyTest {

    @Test
    public void testSpyExample1() {
        // Given
        List<String> spyArrayList = spy(ArrayList.class);

        // When & Then / Assert
        assertEquals(0, spyArrayList.size());

        when(spyArrayList.size()).thenReturn(5);

        // try to add foo bar
        spyArrayList.add("Foo-Bar");

        // the size is 5
        assertEquals(5, spyArrayList.size());
    }

    @Test
    public void testSpyExample2() {
        // Given
        List<String> spyArrayList = spy(ArrayList.class);

        // When & Then
        spyArrayList.add("Foo");
        assertEquals(1, spyArrayList.size());

        spyArrayList.add("Bar");
        assertEquals(2, spyArrayList.size());

        spyArrayList.add("Yah");
        assertEquals(3, spyArrayList.size());

        spyArrayList.remove("Foo");
        assertEquals(2, spyArrayList.size());

        spyArrayList.remove("Bar");
        assertEquals(1, spyArrayList.size());

        spyArrayList.remove("Yah");
        assertEquals(0, spyArrayList.size());
    }

    @Test
    public void testSpyExample3() {
        // Given
        List<String> spyArrayList = spy(ArrayList.class);

        // When & Then
        assertEquals(0, spyArrayList.size());
        when(spyArrayList.size()).thenReturn(5);
        assertEquals(5, spyArrayList.size());
    }

    @Test
    public void testSpyExample4() {
        // Given
        List<String> spyArrayList = spy(ArrayList.class);

        // When & Then
        spyArrayList.add("Yah");

        // is to add called?
        verify(spyArrayList).add("Yah");
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }
}

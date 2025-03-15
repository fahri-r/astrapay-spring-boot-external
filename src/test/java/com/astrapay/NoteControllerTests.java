package com.astrapay;

import com.astrapay.controller.NoteController;
import com.astrapay.service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NoteControllerTests {

    private NoteController noteController;

    @Mock
    private NoteService noteService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        noteController = new NoteController(noteService);
    }

    @Test
    public void shouldReturnNoData() {
        //Arrange

        //Action
        var result = noteController.getAllNotes();

        //Assert
        Assertions.assertEquals(0, result.size());
    }
}
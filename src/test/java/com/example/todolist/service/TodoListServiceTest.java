package com.example.todolist.service;

import com.example.todolist.handler.exception.DuplicatedException;
import com.example.todolist.handler.exception.NotFoundException;
import com.example.todolist.model.TodoList;
import com.example.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {
    private TodoListService todoListService;
    private TodoListRepository todoListRepository;
    @Captor
    private ArgumentCaptor<TodoList> todoListCaptor;


    @BeforeEach
    public void setup() {
        todoListRepository = Mockito.mock(TodoListRepository.class);
        todoListService = new TodoListService(todoListRepository);
    }

    @Test
    public void testAdd_NewTodoList_Success() throws DuplicatedException {
        TodoList todoList = buildTodoList();

        when(todoListRepository.existsByDescription(todoList.getDescription())).thenReturn(false);
        when(todoListRepository.save(any(TodoList.class))).thenReturn(todoList);

        TodoList result = todoListService.add(todoList);

        assertNotNull(result);
        assertEquals(todoList.getDescription(), result.getDescription());

        verify(todoListRepository).existsByDescription(todoList.getDescription());
        verify(todoListRepository).save(todoListCaptor.capture());

        TodoList capturedTodoList = todoListCaptor.getValue();
        assertEquals(todoList.getDescription(), capturedTodoList.getDescription());
    }

    @Test
    public void testAdd_DuplicateTodoList_ExceptionThrown() {
        TodoList todoList =buildTodoList();

        when(todoListRepository.existsByDescription(todoList.getDescription())).thenReturn(true);

        assertThrows(DuplicatedException.class, () -> todoListService.add(todoList));

        verify(todoListRepository).existsByDescription(todoList.getDescription());
        verify(todoListRepository, never()).save(any(TodoList.class));
    }

    @Test
    public void testRemove_ExistingTodoList_Success() throws NotFoundException {
        TodoList todoList = buildTodoList();
        Long todoListId = todoList.getId();

        when(todoListRepository.existsById(todoListId)).thenReturn(true);

        todoListService.remove(todoListId);

        verify(todoListRepository).existsById(todoListId);
        verify(todoListRepository).deleteById(todoListId);
    }

    @Test
    public void testRemove_NonExistingTodoList_ExceptionThrown() {
        TodoList todoList = buildTodoList();
        Long todoListId = todoList.getId();

        when(todoListRepository.existsById(todoListId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> todoListService.remove(todoListId));

        verify(todoListRepository).existsById(todoListId);
        verify(todoListRepository, never()).deleteById(todoListId);
    }

    private TodoList buildTodoList() {
        return TodoList.builder()
                .id(1L)
                .description("take math notes")
                .build();
    }
}

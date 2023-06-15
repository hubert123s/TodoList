package com.example.todolist.service;
import com.example.todolist.handler.exception.NotFoundException;
import com.example.todolist.model.TodoList;
import com.example.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class TodoListServiceTest {
    private TodoListService todoListService;
    private TodoListRepository todoListRepository;

    @BeforeEach
    public void setup() {
        todoListRepository = Mockito.mock(TodoListRepository.class);
        todoListService = new TodoListService(todoListRepository);
    }

    @Test
    public void testAddTodoList() {
        TodoList todoList = new TodoList(1L,"xd");
        todoListService.add(todoList);
        Mockito.verify(todoListRepository).save(todoList);
    }
    @Test
    public void shouldAddTodoList(){
        TodoList todoList =getTodoList();
        Mockito.when(todoListRepository.save(any(TodoList.class))).thenReturn(todoList);
        TodoList retrieveTodoList = todoListService.add(todoList);
        assertThat(retrieveTodoList.getDescription()).isEqualTo(todoList.getDescription());
    }
    @Test
    public void shouldEditTodoList2() throws NotFoundException {
        long id = 1L;
        TodoList todoList = new TodoList();
        todoList.setId(id);
        todoList.setDescription("Original description");

        TodoList updatedTodoList = new TodoList();
        updatedTodoList.setId(id);
        updatedTodoList.setDescription("Updated description");

        Mockito.when(todoListRepository.existsById(id)).thenReturn(true);
        Mockito.when(todoListRepository.save(updatedTodoList)).thenReturn(updatedTodoList);

        TodoList editedTodoList = todoListService.edit(id, updatedTodoList);

        Assertions.assertEquals(updatedTodoList, editedTodoList);
        Mockito.verify(todoListRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(todoListRepository, Mockito.times(1)).save(updatedTodoList);
    }
    @Test
    public void shouldThrowNotFoundExceptionWhenIdNotFound() {
        long id = 1L;
        TodoList updatedTodoList = new TodoList();
        updatedTodoList.setId(id);
        updatedTodoList.setDescription("Updated description");

        Mockito.when(todoListRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> {
            todoListService.edit(id, updatedTodoList);
        });

        Mockito.verify(todoListRepository, Mockito.times(1)).existsById(id);
        Mockito.verify(todoListRepository, Mockito.never()).save(Mockito.any(TodoList.class));
    }
    private TodoList getTodoList(){
        return TodoList.builder()
                .id(1L)
                .description("take math notes")
                .build();
    }
}

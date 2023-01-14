package com.example.taskManagerApi;

import com.example.taskManagerApi.task.Task;
import com.example.taskManagerApi.task.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TaskRepositoryTest {
    @Autowired private TaskRepository repo;

    @Test
    public void addTask(){
        Task task = new Task();
        task.setTaskName("Task1");
        task.setTaskDesc("Task 1 should be finished before 26th Jan, 2023");

        Task savedTask = repo.save(task);
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
    }

    @Test
    public void listTasks(){
        Iterable<Task> tasks = repo.findAll();
        Assertions.assertThat(tasks).hasSizeGreaterThan(0);

        for(Task task:tasks){
            System.out.println(task);
        }
    }

    @Test
    public void getTask(){
        Integer id = 1;
        Optional<Task> task = repo.findById(id);

        Assertions.assertThat(task).isPresent();
        System.out.println(task.get());
    }
}

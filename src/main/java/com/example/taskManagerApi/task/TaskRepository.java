package com.example.taskManagerApi.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task , Integer > {

}

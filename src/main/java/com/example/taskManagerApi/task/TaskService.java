package com.example.taskManagerApi.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> listAll(){
        return (List<Task>) repo.findAll();
    }

    public Task save(Task task){
        return repo.save(task);
    }

    public Task get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id) throws NoSuchTaskException {
        if(!repo.existsById(id)){
            throw new NoSuchTaskException();
        }
        repo.deleteById(id);
    }

}

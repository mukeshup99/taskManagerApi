package com.example.taskManagerApi.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Task>> listAll(){
      List<Task> listAll = service.listAll();
      if(listAll.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return new ResponseEntity<>(listAll, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task){
        Task savedTask = service.save(task);
        return new ResponseEntity<>(service.get(savedTask.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getOne(@PathVariable("id") Integer id){
        Task task = service.get(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task updatedTask = service.save(task);
        return new ResponseEntity<>(service.get(updatedTask.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Integer id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchTaskException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

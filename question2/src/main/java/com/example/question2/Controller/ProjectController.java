package com.example.question2.Controller;

import com.example.question2.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ts")
public class ProjectController {

    List<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Project project, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body("Project added");
    }

    @GetMapping("/projects")
    public ResponseEntity displayProjects(){

        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid @RequestBody Project project,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Project updated");
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){

       return ResponseEntity.status(HttpStatus.OK).body("Project deleted");
    }

    @GetMapping("/change/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){

            if(projects.get(index).getStatus().equals("Not started")) {
                projects.get(index).setStatus("Progress");
            }
            else if(projects.get(index).getStatus().equals("Progress")) {
                projects.get(index).setStatus("Completed");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Status changed");


    }

    @GetMapping("/search/{title}")
    public ResponseEntity search(@PathVariable String title){

        for(Project p : projects){
            if(p.getTitle().equals(title))return ResponseEntity.status(HttpStatus.OK).body(p);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found");
    }

    @GetMapping("/displayall/{companyname}")
    public ResponseEntity displayAll(@PathVariable String companyname ){
        List<Project> projectsBycompany = new ArrayList<>();
        for(Project p : projects){
            if(p.getCompanyName().equals(companyname)) projectsBycompany.add(p);
        }
        if(projectsBycompany.size()==0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(projectsBycompany);
        return ResponseEntity.status(HttpStatus.OK).body(projectsBycompany);
    }

}

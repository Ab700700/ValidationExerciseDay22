package com.example.question3.Controller;

import com.example.question3.Model.EventSystem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eventsys")
public class EventSystemController {

    List<EventSystem> events= new ArrayList<>();


    @GetMapping("/displayall")
    public ResponseEntity display(){
         return ResponseEntity.status(HttpStatus.OK).body(events);

    }

    @GetMapping("/event/{index}")
    public ResponseEntity event(@PathVariable int index){
        return ResponseEntity.status(HttpStatus.OK).body(events.get(index));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index , @Valid @RequestBody EventSystem event, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody EventSystem event, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body("Event added");
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index ,@Valid @RequestBody EventSystem event,Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        events.set(index,event);
        return ResponseEntity.status(HttpStatus.OK).body("Event Updated");

    }

    @PutMapping("/change/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index , @PathVariable int capacity){

            if(capacity<25) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("at least 25");
            EventSystem ev = events.get(index);
            ev.setCapacity(capacity);
            return ResponseEntity.status(HttpStatus.OK).body("Capacity changed");
        }


    @GetMapping("/searchid/{id}")
    public ResponseEntity searchById(@PathVariable String id){
        for(EventSystem ev : events){
            if(ev.getId().equals(id)) return ResponseEntity.status(HttpStatus.OK).body(ev);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

}

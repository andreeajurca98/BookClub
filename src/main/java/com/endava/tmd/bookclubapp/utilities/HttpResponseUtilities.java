package com.endava.tmd.bookclubapp.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseUtilities {
    public static ResponseEntity<String> wrongParameters(){
        return new ResponseEntity<>("Parameters introduced are wrong!", HttpStatus.NOT_ACCEPTABLE);
    }

    public static ResponseEntity<String> noContentFound(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<String> operationWasDone(final String body){
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<String> insertDone(final String body){
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static ResponseEntity<String> dataConflict(final String body){
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<String> notAcceptable(final String body){
        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
}

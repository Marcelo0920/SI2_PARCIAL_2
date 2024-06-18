package com.parcial2.si2.controller;

import com.parcial2.si2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PutMapping("/register")
    public ResponseEntity<String> registerAttendance(@RequestParam Long classId, @RequestParam boolean isPresent){
        if(attendanceService.registerAttendence(classId, isPresent)){
            return ResponseEntity.ok("Asistencia registrada con exito");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

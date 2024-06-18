package com.parcial2.si2.controller;

import com.parcial2.si2.dto.AttendanceRequest;
import com.parcial2.si2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;



    @PutMapping("/register")
    public ResponseEntity<String> registerAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        if(attendanceService.registerAttendence(attendanceRequest.getClassId(), attendanceRequest.isPresent())) {
            return ResponseEntity.ok("Asistencia registrada con exito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

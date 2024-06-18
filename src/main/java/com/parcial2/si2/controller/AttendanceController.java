package com.parcial2.si2.controller;

import com.parcial2.si2.dto.AttendanceRequest;
import com.parcial2.si2.model.Attendance;
import com.parcial2.si2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public ResponseEntity<String> createAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        Long classId = attendanceRequest.getClassId();
        if (attendanceService.createAttendance(classId)) {
            return ResponseEntity.ok("Asistencia creada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attendance>> getAttendancesByClass(@RequestBody AttendanceRequest attendanceRequest) {
        Long classId = attendanceRequest.getClassId();
        List<Attendance> attendances = attendanceService.getAttendancesByClass(classId);
        if (!attendances.isEmpty()) {
            return ResponseEntity.ok(attendances);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

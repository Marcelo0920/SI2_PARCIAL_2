package com.parcial2.si2.service;

import com.parcial2.si2.model.Attendance;
import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.repository.AttendanceRepository;
import com.parcial2.si2.repository.ClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassRepository classRepository;


    public List<Attendance> getAttendancesByClass(Long classId) {
        Optional<CourseClass> optionalClass = classRepository.findById(classId);
        if (optionalClass.isPresent()) {
            CourseClass clase = optionalClass.get();
            List<Attendance> attendances = attendanceRepository.findByClase(clase);
            // Asignar el id de la clase a cada Attendance
            attendances.forEach(attendance -> attendance.getClase().setId(clase.getId()));
            return attendances;
        } else {
            return null; // O lanzar una excepción, dependiendo de la lógica de tu aplicación
        }
    }


    @Scheduled(cron = "0 */3 * * * *")
    public void createDailyAttendences(){
        String currentDay = getCurrentDayInSpanish();
        List<CourseClass> todayClasses = findClassesByDate(currentDay);

        LocalDate today = LocalDate.now();
        for(CourseClass clase : todayClasses){
            Attendance attendance = new Attendance();
            attendance.setClase(clase);
            attendance.setDate(today);
            attendance.setIsPresent(null);
            attendance.setLicencia(null);
            attendanceRepository.save(attendance);
        }
    }

    //correr la funcion todos los dias a las 23:40
    @Scheduled(cron = "0 */7 * * * *")
    public void checkUnregisteredAttendances() {
        String currentDay = getCurrentDayInSpanish();
        List<CourseClass> todayClasses = findClassesByDate(currentDay);

        for (CourseClass clase : todayClasses) {
            String[] classDays = getClassByDay(clase.getId());
            boolean isTodayClass = false;

            for (String classDay : classDays) {
                if (classDay.equalsIgnoreCase(currentDay)) {
                    isTodayClass = true;
                    break;
                }
            }

            if (isTodayClass) {
                // Buscar registros de asistencia no registrados
                List<Attendance> unregisteredAttendances = attendanceRepository.findByClaseIdAndDateAndIsPresentIsNullAndLicenciaIsNull(clase.getId(), LocalDate.now());

                for (Attendance attendance : unregisteredAttendances) {
                    if (attendance.getIsPresent() == null && attendance.getLicencia() == null) {
                        attendance.setIsPresent(false);
                        attendanceRepository.save(attendance);
                    }
                }
            }
        }
    }

    //Registrar asistencia via admin
    @Transactional
    public boolean createAttendance(Long classId){
        Optional<CourseClass> optionalClass = classRepository.findById(classId);

        if(optionalClass.isPresent()){
            CourseClass clase = optionalClass.get();
            LocalDate today = LocalDate.now();

            // Crear nuevo registro de Attendance
            Attendance attendance = new Attendance();
            attendance.setClase(clase);
            attendance.setDate(today);
            attendance.setIsPresent(true); // Establecer isPresent en true
            attendance.setLicencia(null);
            attendanceRepository.save(attendance);

            return true; // Se pudo crear la asistencia
        }

        return false; // No se encontró la clase con el ID especificado
    }

   //Registrar Asistencia
   @Transactional
   public boolean registerAttendence(Long classId, boolean isPresent){
       Optional<CourseClass> optionalClass = classRepository.findById(classId);

       if(optionalClass.isPresent()){
           CourseClass clase = optionalClass.get();

           // Buscar el registro de Attendance
           List<Attendance> attendances = attendanceRepository.findByClaseAndDate(clase, LocalDate.now());

           if(!attendances.isEmpty()){
               Attendance attendance = attendances.get(0);
               attendance.setIsPresent(isPresent);
               attendanceRepository.save(attendance);
               return true; // Se pudo registrar la asistencia
           } else {
               return false; // No se encontró registro de asistencia para la clase y fecha especificadas
           }
       }

       return false; // No se encontró la clase con el ID especificado
   }


    public String getCurrentDayInSpanish(){
        return LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toLowerCase();
    }

    public List<CourseClass> findClassesByDate(String day){
        List<CourseClass> allClasses = classRepository.findAll();
        List<Long> classIds = new ArrayList<>();

        for (CourseClass clase : allClasses){
            String[] classDays = getClassByDay(clase.getId());
            for(String classDay : classDays){
                if(classDay.equalsIgnoreCase(day)){
                    classIds.add(clase.getId());
                    break;
                }
            }
        }
        return classRepository.findByIdIn(classIds);
    }

    public String[] getClassByDay(Long classId){
        CourseClass clase = classRepository.findById(classId).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        return clase.getDias().split(",");
    }
}

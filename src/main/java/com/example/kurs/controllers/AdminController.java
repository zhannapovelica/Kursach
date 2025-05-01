package com.example.kurs.controllers;

import com.example.kurs.models.*;
import com.example.kurs.repositories.CourseRepo;
import com.example.kurs.repositories.DoctorRepository;
import com.example.kurs.repositories.ReceptionRepo;
import com.example.kurs.repositories.UserRepository;
import com.example.kurs.services.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    ReceptionRepo receptionRepo;
    @Autowired
    EmailService emailService;
    @GetMapping("/menu")
    public String admin() {
        return "adminMenu";
    }

    @GetMapping("/security")
    public String security() {
        return "admin-menu/rule-settings/security";
    }

    @GetMapping("/general")
    public String general() {
        return "admin-menu/rule-settings/general";
    }

    @GetMapping("/sending")
    public String sending() {
        return "admin-menu/monitor-analitic/sending";
    }

    @GetMapping("/statistics")
    public String statistics() {
        return "admin-menu/monitor-analitic/statistics";
    }

    @GetMapping("/results-of-test")
    public String resultsOfTest() {
        return "admin-menu/monitor-analitic/results-of-test";
    }

    @GetMapping("/community")
    public String community() {
        return "admin-menu/rule-content/community";
    }

    @GetMapping("/centers-map")
    public String centersMap() {
        return "admin-menu/rule-content/centers-map";
    }

    @GetMapping("/social-benefits")
    public String socialBenefits() {
        return "admin-menu/rule-content/social-benefits";
    }

    @GetMapping("/appoint-of-user")
    public String appointOfUser(Model model) {
        List<Reception> receptions = receptionRepo.findByAcceptFalse();
        model.addAttribute("receptions", receptions);
        return "admin-menu/rule-appointments/appoint-of-user";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "admin-menu/rule-programs/schedule";
    }

    @GetMapping("/add-program")
    public String addProgram(Model model) {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        ArrayList<Doctor> doctors1 = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getCourse() == null) {
                doctors1.add(doctor);
            }
        }
        model.addAttribute("doctors", doctors1);
        return "admin-menu/rule-programs/add-program";
    }

    @GetMapping("/list-of-programs")
    public String listOfPrograms(Model model) {
        Iterable<MedicalCourse> courses = courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "admin-menu/rule-programs/list-of-programs";
    }

    @GetMapping("/verification")
    public String verification(Model model) {
        Iterable<User> users = userRepository.findAll();
        ArrayList<User> users1 = new ArrayList<>();
        for (User u : users) {
            if (u.getStatus() == Status.UNACTIVE) {
                users1.add(u);
            }
        }
        model.addAttribute("users", users1);
        return "admin-menu/rule-users/verification";
    }

    @GetMapping("/add-admin")
    public String addAdmin() {
        return "admin-menu/rule-users/add-admin";
    }

    @GetMapping("/doctor-menu-{id}")
    public String doctorMenu(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id).get();
        model.addAttribute("doctor", doctor);
        return "admin-menu/rule-users/doctor-menu";
    }

    @GetMapping("/doctor-menu-full")
    public String doctorMenuFull(Model model) {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "admin-menu/rule-users/doctor-menu-full";
    }

    @GetMapping("/list-of-users")
    public String listOfUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-menu/rule-users/list-of-users";
    }

    @PostMapping("/unbloc-user")
    public String unblocUser(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).get();
        user.setStatus(Status.UNACTIVE);
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-menu/rule-users/list-of-users";
    }

    @PostMapping("/bloc-user")
    public String blocUser(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).get();
        user.setStatus(Status.BLOCKED);
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-menu/rule-users/list-of-users";
    }

    @PostMapping("/verify-us")
    public String verifyUs(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).get();
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/verification";
    }

    @PostMapping("/admin-creat")
    public String registration(@RequestParam String name, @RequestParam String surname, @RequestParam String lastName,
                               @RequestParam int age, @RequestParam String email,
                               @RequestParam String password) {
        User user = new User(surname, name, lastName, age, email, password, Role.ADMIN);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/menu";
    }

    @PostMapping("/doctor-create")
    public String createDoctor() {
        Doctor doctor = new Doctor();
        doctorRepository.save(doctor);
        return "redirect:/doctor-menu-full";
    }

    @GetMapping("{id}-image")
    public ResponseEntity<byte[]> getEntityImage(@PathVariable Long id) {
        byte[] image = doctorRepository.findById(id).get().getImage();
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        String mimeType = null;
        try {
            mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @PostMapping("/doctor-redact")
    public String redactDoctor(@RequestParam long id, @RequestParam String fullName, @RequestParam String position, @RequestParam String specialization,
                               @RequestParam int age, @RequestParam String contacts, @RequestParam String education, @RequestParam String experience,
                               @RequestParam String aboutCourse, @RequestParam String strategy, @RequestParam String moreInfo,
                               @RequestParam String goal, @RequestParam("image") MultipartFile image) {
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.setFullName(fullName);
        doctor.setPosition(position);
        doctor.setSpecialization(specialization);
        doctor.setAge(age);
        doctor.setContacts(contacts);
        doctor.setEducation(education);
        doctor.setExperience(experience);
        doctor.setAboutCourse(aboutCourse);
        doctor.setStrategy(strategy);
        doctor.setMoreInfo(moreInfo);
        doctor.setGoal(goal);
        try {
            doctor.setImage(image.getBytes());
            doctorRepository.save(doctor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/doctor-menu-full";
    }

    @PostMapping("/create-program")
    public String createProgram(@RequestParam String name, @RequestParam String purpose,
                                @RequestParam String description, @RequestParam String duration, @RequestParam String capacity,
                                @RequestParam String location, @RequestParam("doctorIds") List<Long> doctorIds,
                                @RequestParam("image") MultipartFile image) {
        Doctor doctor1 = null;
        try {
            MedicalCourse medicalCourse =  new MedicalCourse(name,purpose,description,duration,capacity,location,image.getBytes());
            for (Long doctorId : doctorIds) {
                Doctor doctor = doctorRepository.findById(doctorId)
                        .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
                medicalCourse.addDoctor(doctor);
                doctor1 = doctor;
            }
            courseRepo.save(medicalCourse);
            doctor1.setCourse(medicalCourse);
            doctorRepository.save(doctor1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/menu";
    }
    @GetMapping("{id}-image-course")
    public ResponseEntity<byte[]> getEntityImageCourse(@PathVariable Long id) {
        byte[] image = courseRepo.findById(id).get().getImage();
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        String mimeType = null;
        try {
            mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    @PostMapping("/accept")
    public String accept(@RequestParam long receptionId) {
        Reception reception = receptionRepo.findById(receptionId).get();
        reception.setAccept(true);
        receptionRepo.save(reception);
        return "redirect:/appoint-of-user";
    }
    @PostMapping("/decline")
    public String decline(@RequestParam long receptionId, @RequestParam String reason) {
        Reception reception = receptionRepo.findById(receptionId).get();
        emailService.sendDeclineEmail(reception.getUser().getEmail(), reception, reason);
        receptionRepo.delete(reception);
        return "redirect:/appoint-of-user";
    }
}

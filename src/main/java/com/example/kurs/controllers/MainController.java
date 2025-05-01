package com.example.kurs.controllers;

import com.example.kurs.models.FidBack;
import com.example.kurs.models.MedicalCourse;
import com.example.kurs.models.Reception;
import com.example.kurs.models.User;
import com.example.kurs.repositories.CourseRepo;
import com.example.kurs.repositories.FidBackRepo;
import com.example.kurs.repositories.ReceptionRepo;
import com.example.kurs.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    ReceptionRepo receptionRepo;
    @Autowired
    FidBackRepo fidBackRepo;
    @GetMapping("/")
    public String index() {
        return "index";
    }@GetMapping("/static")
    public String stat() {
        return "redirect:/";
    }
    @GetMapping("/back")
    public String back() {
        return "redirect:/";
    }
    @GetMapping("/profile")
    public String prof(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "profile";
        }
        return "redirect:/enter";
    }

    @GetMapping("/enter")
    public String enter( HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/profile";
        }
        else {
            Iterable<Reception> receptions = receptionRepo.findAll();
            ArrayList<Reception> receptionArrayList = new ArrayList<>();
            for (Reception reception : receptions) {
                if (reception.getUser().getId() == user.getId()) {
                    receptionArrayList.add(reception);
                }
            }
            model.addAttribute("receptions", receptionArrayList);
            return "cabinet";
        }
    }
    @GetMapping("/logout")
    public String logout( HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/profile";
        }
        session.removeAttribute("user");
        return "redirect:/profile";
    }
    @GetMapping("/profile/data")
    @ResponseBody
    public User getProfileData(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
    @GetMapping("/doctor")
    public String doc() {
        return "doctor";
    }
    @GetMapping("/military")
    public String military() {
        return "military";
    }
    @GetMapping("/veteran")
    public String veteran() {
        return "veteran";
    }
    @GetMapping("/family")
    public String family() {
        return "family";
    }
    @GetMapping("/test")
    public String train() {
        return "train-test";
    }
    @GetMapping("/visit")
    public String visit(Model model) {
        Iterable<MedicalCourse> courses = courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "visit-doctor";
    }
    @GetMapping("/chatbot")
    public String showChatBot() {
        return "test-version-of-bot";
    }
    @GetMapping("/doctors")
    public String doctors() {
        return "doctor-pages";
    }
    @GetMapping("/reception-{id}")
    public String reception(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/profile";
        }
        else {
            MedicalCourse course = courseRepo.findById(id).get();
            model.addAttribute("course", course);
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            String[] locations = course.getLocation().split(";");
            model.addAttribute("locations", locations);
            return "reception";
        }
    }
    @PostMapping("/create-reception")
    public String reception(HttpSession session, @RequestParam long courseId, @RequestParam long userId,
                            @RequestParam LocalDateTime datetime, @RequestParam String description, @RequestParam String selectedLocation) {
        if (session.getAttribute("user") == null) {
            return "redirect:/profile";
        }
        else {
            User user = userRepository.findById(userId).get();
            MedicalCourse course = courseRepo.findById(courseId).get();
            Reception reception = new Reception(user, datetime, description, selectedLocation, course);
            receptionRepo.save(reception);
            course.addReception(reception);
            courseRepo.save(course);
            return "redirect:/profile";
        }
    }
    @GetMapping("/feedbacks")
    public String fidback(Model model) {
        Pageable topTwo = PageRequest.of(0, 2);
        List<FidBack> topLikes = fidBackRepo.findTop2ByOrderByLikesDesc(topTwo);
        List<FidBack> topDislikes = fidBackRepo.findTop2ByOrderByDislikesDesc(topTwo);

        List<Long> excludeIds = new ArrayList<>();
        excludeIds.addAll(topLikes.stream().map(FidBack::getId).toList());
        excludeIds.addAll(topDislikes.stream().map(FidBack::getId).toList());

        Pageable recentSix = PageRequest.of(0, 6);
        List<FidBack> recentFeedbacks = fidBackRepo.findRecentExcludingIds(excludeIds, recentSix);

        model.addAttribute("topLikes", topLikes);
        model.addAttribute("topDislikes", topDislikes);
        model.addAttribute("recentFeedbacks", recentFeedbacks);
        return "feedbacks";
    }

    @GetMapping("/feedback/more")
    @ResponseBody
    public List<FidBack> loadMoreFeedbacks(@RequestParam int page, @RequestParam List<Long> excludeIds) {
        Pageable pageable = PageRequest.of(page, 6);
        return fidBackRepo.findRecentExcludingIds(excludeIds, pageable);
    }
    @PostMapping("/create-feedback")
    public String createFidBak(HttpSession session, @RequestParam String description) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/profile";
        }
        else {
            FidBack fidBack = new FidBack(user, description);
            fidBackRepo.save(fidBack);
            return "redirect:/profile";
        }
    }
    @PostMapping("/like")
    public String like(HttpSession session, @RequestParam long feedbackId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/profile";
        }
        else{
            FidBack fidBack = fidBackRepo.findById(feedbackId).get();
            fidBack.setLikes(fidBack.getLikes() + 1);
            fidBackRepo.save(fidBack);
            return "redirect:/feedbacks";
        }
    }
    @PostMapping("/deslike")
    public String dislike(HttpSession session, @RequestParam long feedbackId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/profile";
        }
        else{
            FidBack fidBack = fidBackRepo.findById(feedbackId).get();
            fidBack.setDislikes(fidBack.getDislikes() + 1);
            fidBackRepo.save(fidBack);
            return "redirect:/feedbacks";
        }
    }
}

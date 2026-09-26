package com.campusflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to CampusFlow");
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("title", "My Courses");
        return "courses";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("title", "Attendance");
        return "attendance";
    }

    @GetMapping("/assignments")
    public String assignments(Model model) {
        model.addAttribute("title", "Assignments");
        return "assignments";
    }

    @GetMapping("/grades")
    public String grades(Model model) {
        model.addAttribute("title", "Grades");
        return "grades";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title", "My Profile");
        return "profile";
    }
}

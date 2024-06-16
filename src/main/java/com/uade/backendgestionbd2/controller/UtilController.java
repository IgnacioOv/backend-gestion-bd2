package com.uade.backendgestionbd2.controller;

import com.uade.backendgestionbd2.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private UtilService utilService;

    @GetMapping("/skill-level")
    public ResponseEntity<Object> getSkillLevelAll() {
        return ResponseEntity.ok(utilService.getSkillLevelAll());
    }

    @GetMapping("/roles")
    public ResponseEntity<Object> getRolesAll() {
        return ResponseEntity.ok(utilService.getRolesAll());
    }

}

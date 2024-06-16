package com.uade.backendgestionbd2.service;

import com.uade.backendgestionbd2.util.Roles;
import com.uade.backendgestionbd2.util.SkillLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilService {


    public List<SkillLevel> getSkillLevelAll() {
        return List.of(SkillLevel.values());
    }

    public List<Roles> getRolesAll() {
        return List.of(Roles.values());
    }

}

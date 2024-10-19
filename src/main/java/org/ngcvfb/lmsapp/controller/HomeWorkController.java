package org.ngcvfb.lmsapp.controller;

import org.ngcvfb.lmsapp.model.HomeWorkModel;
import org.ngcvfb.lmsapp.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeworks")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    @GetMapping("/theme/{themeId}")
    public List<HomeWorkModel> getAllHomeWorksByThemeId(@PathVariable Long themeId) {
        return homeWorkService.getAllHomeWorksByThemeId(themeId);
    }

    @GetMapping("/{homeWorkId}")
    public ResponseEntity<HomeWorkModel> getHomeWorkById(@PathVariable Long homeWorkId) {
        return homeWorkService.getHomeWorkById(homeWorkId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/theme/{themeId}")
    public HomeWorkModel createHomeWork(@RequestBody HomeWorkModel homework, @PathVariable Long themeId) {
        return homeWorkService.createHomeWork(homework, themeId);
    }

    @PutMapping("/{homeWorkId}")
    public ResponseEntity<HomeWorkModel> updateHomeWork(@PathVariable Long homeWorkId, @RequestBody HomeWorkModel homework) {
        return ResponseEntity.ok(homeWorkService.updateHomeWork(homeWorkId, homework));
    }

    @DeleteMapping("/{homeWorkId}")
    public ResponseEntity<Void> deleteHomeWork(@PathVariable Long homeWorkId) {
        homeWorkService.deleteHomeWork(homeWorkId);
        return ResponseEntity.noContent().build();
    }
}

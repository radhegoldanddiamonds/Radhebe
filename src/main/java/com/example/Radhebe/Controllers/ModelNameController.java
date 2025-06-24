package com.example.Radhebe.Controllers;

import com.example.Radhebe.Entity.ModelName;
import com.example.Radhebe.Services.ModelNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/model-names")
@CrossOrigin(origins = "http://localhost:5173")
public class ModelNameController {

    @Autowired
    private ModelNameService modelNameService;

    @GetMapping
    public ResponseEntity<List<ModelName>> getAllModelNames() {
        List<ModelName> modelNames = modelNameService.getAllModelNames();
        return ResponseEntity.ok(modelNames);
    }

    @PostMapping
    public ResponseEntity<ModelName> createModelName(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ModelName modelName = modelNameService.createModelName(name.trim());
        return ResponseEntity.ok(modelName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModelName(@PathVariable String id) {
        boolean deleted = modelNameService.deleteModelName(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

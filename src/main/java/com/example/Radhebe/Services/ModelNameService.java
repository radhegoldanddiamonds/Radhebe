package com.example.Radhebe.Services;
import com.example.Radhebe.Entity.ModelName;
import com.example.Radhebe.Repository.ModelNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModelNameService {

    @Autowired
    private ModelNameRepository modelNameRepository;

    public List<ModelName> getAllModelNames() {
        return modelNameRepository.findAll();
    }

    public ModelName createModelName(String name) {
        ModelName modelName = new ModelName(name);
        return modelNameRepository.save(modelName);
    }

    public boolean deleteModelName(String id) {
        Optional<ModelName> modelName = modelNameRepository.findById(UUID.fromString(id));
        if (modelName.isPresent()) {
            modelNameRepository.deleteById(UUID.fromString(id));
            return true;
        }
        return false;
    }
}

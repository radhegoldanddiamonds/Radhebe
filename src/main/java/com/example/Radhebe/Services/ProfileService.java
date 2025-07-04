package com.example.Radhebe.Services;

import com.example.Radhebe.DTO.CreateProfileRequest;
import com.example.Radhebe.DTO.UpdateProfileRequest;
import com.example.Radhebe.Entity.Profile;
import com.example.Radhebe.Entity.UserType;
import com.example.Radhebe.Repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfiles(String userType, Pageable pageable) {
        if (userType != null && !userType.isEmpty()) {
            Page<Profile> page = profileRepository.findByUserTypeIgnoreCase(userType, pageable);
            return page.getContent();
        }

        Page<Profile> page = profileRepository.findAll(pageable);
        return page.getContent();
    }

    public Profile findById(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
    }

    public Profile createProfile(CreateProfileRequest request) {
        // Check if phone number already exists
        if (profileRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ValidationException("A profile with this phone number already exists");
        }

        Profile profile = new Profile();
        profile.setUsername(request.getUsername());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setShopName(request.getShopName());
        profile.setVillage(request.getVillage());
        profile.setUserType(UserType.valueOf(request.getUserType()));
        profile.setCreatedBy(request.getCreatedBy());

        return profileRepository.save(profile);
    }

    public Profile updateProfile(UUID id, UpdateProfileRequest request) {
        Profile profile = findById(id);

        if (request.getUsername() != null) profile.setUsername(request.getUsername());
        if (request.getPhoneNumber() != null) profile.setPhoneNumber(request.getPhoneNumber());
        if (request.getShopName() != null) profile.setShopName(request.getShopName());
        if (request.getVillage() != null) profile.setVillage(request.getVillage());
        if (request.getUserType() != null) profile.setUserType(UserType.valueOf(request.getUserType()));

        return profileRepository.save(profile);
    }
    public boolean deleteProfile(UUID id) {

        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            profileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

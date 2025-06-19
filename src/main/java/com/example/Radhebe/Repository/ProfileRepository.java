package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.Profile;
import com.example.Radhebe.Entity.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Profile> findByPhoneNumber(String phoneNumber);

    Optional<Profile> findByEmail(String username);

    Optional<Profile> findByUsername(String username);

    List<Profile> findByUserType(UserType userType);

    Page<Profile> findByUserTypeIgnoreCase(String userType, Pageable pageable);

    List<Profile> findByVillageContainingIgnoreCase(String village);

    List<Profile> findByShopNameContainingIgnoreCase(String shopName);

    @Query("SELECT p FROM Profile p WHERE " +
            "(:userType IS NULL OR p.userType = :userType) AND " +
            "(:village IS NULL OR LOWER(p.village) LIKE LOWER(CONCAT('%', :village, '%'))) AND " +
            "(:shopName IS NULL OR LOWER(p.shopName) LIKE LOWER(CONCAT('%', :shopName, '%')))")
    List<Profile> findWithFilters(@Param("userType") UserType userType,
                                  @Param("village") String village,
                                  @Param("shopName") String shopName,
                                  Pageable pageable);
}

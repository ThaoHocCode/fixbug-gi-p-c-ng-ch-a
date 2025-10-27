package vn.ute.utescore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ute.utescore.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    
    // Tìm theo tên vai trò
    @Query("SELECT r FROM Roles r WHERE r.RoleName = :roleName")
    Optional<Roles> findByRoleName(@Param("roleName") String roleName);
    
    // Tìm theo tên vai trò (không phân biệt hoa thường)
    @Query("SELECT r FROM Roles r WHERE LOWER(r.RoleName) = LOWER(:roleName)")
    Optional<Roles> findByRoleNameIgnoreCase(@Param("roleName") String roleName);
    
    // Lấy tất cả vai trò
    @Query("SELECT r FROM Roles r ORDER BY r.RoleID")
    List<Roles> findAllOrdered();
}

package vn.ute.utescore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ute.utescore.model.GiaThue;

@Repository
public interface GiaThueRepository extends JpaRepository<GiaThue, Integer> {
    
    // Tìm theo loại sân
    @Query("SELECT g FROM GiaThue g WHERE g.LoaiSan = :loaiSan")
    List<GiaThue> findByLoaiSan(@Param("loaiSan") String loaiSan);
    
    // Tìm theo trạng thái
    @Query("SELECT g FROM GiaThue g WHERE g.TrangThai = :trangThai")
    List<GiaThue> findByTrangThai(@Param("trangThai") String trangThai);
    
    // Tìm giá thuê đang áp dụng
    @Query("SELECT g FROM GiaThue g WHERE g.TrangThai = 'Active' ORDER BY g.NgayApDung DESC")
    List<GiaThue> findActivePricing();
    
    // Tìm giá thuê theo loại sân và trạng thái
    @Query("SELECT g FROM GiaThue g WHERE g.LoaiSan = :loaiSan AND g.TrangThai = :trangThai")
    List<GiaThue> findByLoaiSanAndTrangThai(@Param("loaiSan") String loaiSan, @Param("trangThai") String trangThai);
    
    // Tìm giá thuê theo khung giờ
    @Query("SELECT g FROM GiaThue g WHERE g.KhungGioBatDau <= :time AND g.KhungGioKetThuc >= :time")
    List<GiaThue> findByTimeRange(@Param("time") java.time.LocalTime time);
}


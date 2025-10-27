package vn.ute.utescore.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ute.utescore.model.ThueSan;

@Repository
public interface ThueSanRepository extends JpaRepository<ThueSan, Integer> {
    
    // Tìm theo khách hàng
    @Query("SELECT t FROM ThueSan t WHERE t.khachHang.maKhachHang = :maKhachHang")
    List<ThueSan> findByKhachHang_MaKhachHang(@Param("maKhachHang") Integer maKhachHang);
    
    // Tìm theo sân
    @Query("SELECT t FROM ThueSan t WHERE t.sanBong.maSan = :maSan")
    List<ThueSan> findBySanBong_MaSan(@Param("maSan") Integer maSan);
    
    // Tìm theo ngày thuê
    @Query("SELECT t FROM ThueSan t WHERE CAST(t.ngayThue AS DATE) = :ngay")
    List<ThueSan> findByNgayThue(@Param("ngay") LocalDate ngay);
    
    // Tìm theo khoảng thời gian
    @Query("SELECT t FROM ThueSan t WHERE t.ngayThue BETWEEN :startDate AND :endDate")
    List<ThueSan> findByNgayThueBetween(@Param("startDate") LocalDateTime startDate, 
                                      @Param("endDate") LocalDateTime endDate);
    
    // Kiểm tra xung đột với lịch thuê hiện có (dùng native query để ép kiểu TIME)
    @Query(value = "SELECT * FROM ThueSan WHERE MaSan = :maSan " +
           "AND CAST(NgayThue AS DATE) = CAST(:ngay AS DATE) " +
           "AND ((CAST(CAST(KhungGioBatDau AS TIME) AS TIME) <= CAST(CAST(:startTime AS TIME) AS TIME) AND CAST(CAST(KhungGioKetThuc AS TIME) AS TIME) > CAST(CAST(:startTime AS TIME) AS TIME)) " +
           "OR (CAST(CAST(KhungGioBatDau AS TIME) AS TIME) < CAST(CAST(:endTime AS TIME) AS TIME) AND CAST(CAST(KhungGioKetThuc AS TIME) AS TIME) >= CAST(CAST(:endTime AS TIME) AS TIME)) " +
           "OR (CAST(CAST(KhungGioBatDau AS TIME) AS TIME) >= CAST(CAST(:startTime AS TIME) AS TIME) AND CAST(CAST(KhungGioKetThuc AS TIME) AS TIME) <= CAST(CAST(:endTime AS TIME) AS TIME)))", 
           nativeQuery = true)
    List<ThueSan> findConflictingBookings(@Param("maSan") Integer maSan,
                                         @Param("ngay") LocalDate ngay,
                                         @Param("startTime") String startTime,
                                         @Param("endTime") String endTime);
    
    // Thống kê theo sân
    @Query("SELECT COUNT(t) FROM ThueSan t WHERE t.sanBong.maSan = :maSan")
    long countBySanBong(@Param("maSan") Integer maSan);
    
    // Thống kê theo ngày
    @Query("SELECT COUNT(t) FROM ThueSan t WHERE CAST(t.ngayThue AS DATE) = :ngay")
    long countByNgayThue(@Param("ngay") LocalDate ngay);
    
    // Thống kê theo khoảng thời gian tạo
    @Query("SELECT COUNT(t) FROM ThueSan t WHERE t.ngayTao BETWEEN :startDate AND :endDate")
    long countByNgayTaoBetween(@Param("startDate") LocalDateTime startDate, 
                               @Param("endDate") LocalDateTime endDate);
}


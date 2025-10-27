package vn.ute.utescore.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ute.utescore.model.ThanhToan;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
    
    // Find all payments - simple
    List<ThanhToan> findAll();
    
    // Find all payments with successful status (Thành Công) - using exact match
    @Query(value = "SELECT * FROM ThanhToan WHERE TrangThaiThanhToan = 'Thành Công'", nativeQuery = true)
    List<ThanhToan> findSuccessfulPayments();
    
    // Find all payments with pending status (Đang xử lý)
    @Query("SELECT t FROM ThanhToan t WHERE t.TrangThaiThanhToan = 'Đang xử lý'")
    List<ThanhToan> findPendingPayments();
    
    // Find payments by booking ID
    List<ThanhToan> findByThueSan_MaDonDat(Integer maDonDat);
    
    // Find successful payments by date range
    @Query(value = "SELECT * FROM ThanhToan WHERE TrangThaiThanhToan = 'Thành công' AND NgayThanhToan BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<ThanhToan> findSuccessfulPaymentsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    // Calculate total revenue in a date range
    @Query(value = "SELECT COALESCE(SUM(SoTienNhan), 0) FROM ThanhToan WHERE TrangThaiThanhToan = 'Thành công' AND NgayThanhToan BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal calculateTotalRevenue(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    // Count successful payments in a date range
    @Query(value = "SELECT COUNT(*) FROM ThanhToan WHERE TrangThaiThanhToan = 'Thành công' AND NgayThanhToan BETWEEN :startDate AND :endDate", nativeQuery = true)
    Long countSuccessfulPayments(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    // Find uncompleted payments
    @Query("SELECT t FROM ThanhToan t WHERE t.TrangThaiThanhToan <> :status")
    List<ThanhToan> findUncompletedPayments(@Param("status") String status);
}


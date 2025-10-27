package vn.ute.utescore.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "SanBong")
public class SanBong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSan")
    private Integer maSan;

    @Column(name = "TenSan", columnDefinition = "NVARCHAR(20)", nullable = false)
    private String tenSan;

    @Column(name = "LoaiSan", columnDefinition = "NVARCHAR(20)", nullable = false)
    private String loaiSan;

    @Column(name = "KhuVuc", columnDefinition = "NVARCHAR(50)")
    private String khuVuc;

    @Column(name = "DuongDanGGMap", columnDefinition = "NVARCHAR(255)")
    private String duongDanGGMap;

    @Column(name = "GioMoCua")
    private LocalTime gioMoCua;

    @Column(name = "GioDongCua")
    private LocalTime gioDongCua;

    @Column(name = "MoTa", columnDefinition = "NVARCHAR(255)")
    private String moTa;

    @Lob
    @Column(name = "HinhAnh")
    private byte[] hinhAnh;

    @Column(name = "TrangThai", columnDefinition = "NVARCHAR(50)")
    private String trangThai;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    // ========== QUAN HỆ ==========
    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<ThueSan> thueSans;

    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<BaoTri> baoTris;

    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<TinhNangSan> tinhNangSans;

    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<GopYHeThong> gopYHeThongs;

    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<Camera> cameras;

    @JsonIgnore
    @OneToMany(mappedBy = "sanBong", cascade = CascadeType.ALL)
    private List<LichSuTrangThaiSan> lichSuTrangThaiSans;

    // ========== CONSTRUCTORS ==========
    public SanBong() {}

    public SanBong(Integer maSan, String tenSan, String loaiSan, String khuVuc, String duongDanGGMap,
                   LocalTime gioMoCua, LocalTime gioDongCua, String moTa, byte[] hinhAnh,
                   String trangThai, LocalDateTime ngayTao, LocalDateTime ngayCapNhat) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.loaiSan = loaiSan;
        this.khuVuc = khuVuc;
        this.duongDanGGMap = duongDanGGMap;
        this.gioMoCua = gioMoCua;
        this.gioDongCua = gioDongCua;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    // ========== GETTERS & SETTERS ==========

    public Integer getMaSan() { return maSan; }
    public void setMaSan(Integer maSan) { this.maSan = maSan; }

    public String getTenSan() { return tenSan; }
    public void setTenSan(String tenSan) { this.tenSan = tenSan; }

    public String getLoaiSan() { return loaiSan; }
    public void setLoaiSan(String loaiSan) { this.loaiSan = loaiSan; }

    public String getKhuVuc() { return khuVuc; }
    public void setKhuVuc(String khuVuc) { this.khuVuc = khuVuc; }

    public String getDuongDanGGMap() { return duongDanGGMap; }
    public void setDuongDanGGMap(String duongDanGGMap) { this.duongDanGGMap = duongDanGGMap; }

    public LocalTime getGioMoCua() { return gioMoCua; }
    public void setGioMoCua(LocalTime gioMoCua) { this.gioMoCua = gioMoCua; }

    public LocalTime getGioDongCua() { return gioDongCua; }
    public void setGioDongCua(LocalTime gioDongCua) { this.gioDongCua = gioDongCua; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public byte[] getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(byte[] hinhAnh) { this.hinhAnh = hinhAnh; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDateTime ngayTao) { this.ngayTao = ngayTao; }

    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }

    public List<ThueSan> getThueSans() { return thueSans; }
    public void setThueSans(List<ThueSan> thueSans) { this.thueSans = thueSans; }

    public List<BaoTri> getBaoTris() { return baoTris; }
    public void setBaoTris(List<BaoTri> baoTris) { this.baoTris = baoTris; }

    public List<TinhNangSan> getTinhNangSans() { return tinhNangSans; }
    public void setTinhNangSans(List<TinhNangSan> tinhNangSans) { this.tinhNangSans = tinhNangSans; }

    public List<GopYHeThong> getGopYHeThongs() { return gopYHeThongs; }
    public void setGopYHeThongs(List<GopYHeThong> gopYHeThongs) { this.gopYHeThongs = gopYHeThongs; }

    public List<Camera> getCameras() { return cameras; }
    public void setCameras(List<Camera> cameras) { this.cameras = cameras; }

    public List<LichSuTrangThaiSan> getLichSuTrangThaiSans() { return lichSuTrangThaiSans; }
    public void setLichSuTrangThaiSans(List<LichSuTrangThaiSan> lichSuTrangThaiSans) { this.lichSuTrangThaiSans = lichSuTrangThaiSans; }
}
